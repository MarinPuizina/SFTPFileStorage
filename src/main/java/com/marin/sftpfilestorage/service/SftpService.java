package com.marin.sftpfilestorage.service;

import org.springframework.integration.sftp.session.DefaultSftpSessionFactory;
import org.springframework.stereotype.Service;

@Service
public class SftpService {

    private DefaultSftpSessionFactory getSessionFactory() {

        DefaultSftpSessionFactory sessionFactory = new DefaultSftpSessionFactory();
        sessionFactory.setHost("127.0.0.1");
        sessionFactory.setPort(22);
        sessionFactory.setAllowUnknownKeys(true);
        sessionFactory.setUser("marin");
        sessionFactory.setPassword("password123");

        return sessionFactory;
    }

}
