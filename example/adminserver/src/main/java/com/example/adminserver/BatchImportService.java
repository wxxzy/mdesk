package com.example.adminserver;

import org.springframework.web.multipart.MultipartFile;

public interface BatchImportService {
    public String batchImport(String fileName, MultipartFile mfile, String userName);
}
