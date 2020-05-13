package com.marin.sftpfilestorage.controller;

import com.marin.sftpfilestorage.service.SftpService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;

@RestController
@AllArgsConstructor
@Slf4j
public class FileController {

    private final SftpService sftpService;

    @PostMapping("/upload")
    public String uploadFile(MultipartFile file) throws IOException {

        log.info("Started file upload");
        log.info("Original File Name --> " + file.getOriginalFilename());
        log.info("Name --> " + file.getName());

        sftpService.uploadFile(file.getOriginalFilename(), file.getInputStream());

        log.info("Done");

        return "File Uploaded";
    }

    @GetMapping("/download/{filename}")
    public Resource downloadFile(@PathVariable String filename) {

        log.info("Started file download");
        String tmpFilename = sftpService.downloadFile(filename);
        log.info("Tmp file name -> " + tmpFilename);

        Path filepath = Path.of(tmpFilename);
        UrlResource resource = null;

        try {
            resource = new UrlResource(filepath.toUri());
            log.info("Done");
            return resource;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return null;
    }

}
