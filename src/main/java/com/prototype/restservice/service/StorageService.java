package com.prototype.restservice.service;

import org.springframework.web.multipart.MultipartFile;

public interface StorageService {

    /**
     * Stores an uploaded file.
     *
     * @param file a file to store
     */
    void store(MultipartFile file);
}
