package com.marin.sftpfilestorage.controller;

import com.marin.sftpfilestorage.service.SftpService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@RestController
@AllArgsConstructor
public class FileController {

    private final SftpService sftpService;

    @PostMapping("/upload")
    public String uploadFile(MultipartFile file) throws IOException {


    }

}
