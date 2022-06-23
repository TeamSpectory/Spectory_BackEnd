package com.spectory.Post.domain;
import com.amazonaws.services.s3.model.ObjectMetadata;
import java.io.InputStream;

public interface ImageUpload {
    void uploadFile(InputStream inputStream, ObjectMetadata objectMetadata, String fileName);
    String getFileUrl(String fileName);
}
