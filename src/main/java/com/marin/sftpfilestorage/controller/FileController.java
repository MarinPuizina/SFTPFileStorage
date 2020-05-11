package com.marin.sftpfilestorage.controller;

import com.marin.sftpfilestorage.service.SftpService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@AllArgsConstructor
@Slf4j
public class FileController {

    private final SftpService sftpService;

    @PostMapping("/upload")
    public String uploadFile(@RequestParam(value = "file") MultipartFile file) throws IOException {

        log.info("Started file upload");
        log.info("Original File Name --> " + file.getOriginalFilename());
        log.info("Name --> " + file.getName());

        sftpService.uploadFile(file.getOriginalFilename(), file.getInputStream());

        log.info("Done");

        return "File Uploaded";
    }

}
