package com.example.adminserver.service;

import org.springframework.web.multipart.MultipartFile;

public interface BatchImportService {
    String batchImport(String fileName, MultipartFile mfile, String userName);
}
