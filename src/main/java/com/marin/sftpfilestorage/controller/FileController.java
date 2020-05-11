package com.marin.sftpfilestorage.controller;

import com.marin.sftpfilestorage.service.SftpService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class FileController {

    private final SftpService sftpService;

    

}
