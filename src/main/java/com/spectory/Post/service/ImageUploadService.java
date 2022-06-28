package com.spectory.Post.service;

import com.amazonaws.AmazonClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.TransferManagerBuilder;
import com.amazonaws.services.s3.transfer.Upload;
import com.spectory.Message;
import com.spectory.Post.domain.ImageUpload;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.UUID;


@Slf4j
@Service
public class ImageUploadService {
    //private final AmazonS3 amazonS3;
    private final AmazonS3Client s3Client;
    //private final ImageUpload imageUpload;
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Value("${cloud.aws.s3.bucket.url}")
    private String bucketUrl;

    public ImageUploadService (AmazonS3Client amazonS3) {
        this.s3Client = amazonS3;
    }

    public String imageUpload(MultipartFile multipartFile) throws IOException {
        String originalName = multipartFile.getOriginalFilename();
        String url;

        try {
            final String ext = originalName.substring(originalName.lastIndexOf('.'));
            final String savedName = createFileName(originalName);
            File file = new File(System.getProperty("user.dir") + savedName);
            multipartFile.transferTo(file);
            uploadS3(savedName, file);
            url = bucketUrl + savedName;
            file.delete();
        } catch (StringIndexOutOfBoundsException e) {
            url = null;
        }
        return url;
    }

    private void uploadS3(String fileName, File file) {
        TransferManager transferManager = TransferManagerBuilder.standard()
                .withS3Client(this.s3Client).build();

        PutObjectRequest request = new PutObjectRequest(bucket, fileName, file);
        Upload upload = transferManager.upload(request);
        try {
            upload.waitForCompletion();
        } catch (AmazonClientException amazonClientException) {
            System.out.println(amazonClientException.getMessage());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private String createFileName(String name) {
        LocalDate now = LocalDate.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalTime timeNow = LocalTime.now();
        DateTimeFormatter timeFormatter= DateTimeFormatter.ofPattern("HHmmss");
        return (now.format(dateFormatter) + timeNow.format(timeFormatter) + name);
    }
}
