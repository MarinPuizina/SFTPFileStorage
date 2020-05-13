package com.marin.sftpfilestorage.service;

import com.jcraft.jsch.*;
import org.springframework.integration.sftp.session.DefaultSftpSessionFactory;
import org.springframework.integration.sftp.session.SftpSession;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

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

    public void uploadFile(String fileName, InputStream inputStream) {

        SftpSession session = getSessionFactory().getSession();

        FileInputStream fileInputStream = null;
        try {
            session.write(inputStream, "upload/" + fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        session.close();

    }

    public String downloadFile(String fileName) {

        String absoluteFileName = "upload/" + fileName;
        String tmpFile = "tmp/" + fileName;

        JSch jSch = new JSch();

        try {

            Session session = jSch.getSession("marin", "127.0.0.1", 22);
            session.setPassword("password123");
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();

            ChannelSftp sftp = (ChannelSftp) session.openChannel("sftp");
            sftp.connect();
            sftp.lcd("tmp");
            sftp.get(absoluteFileName, fileName);

        } catch (JSchException e) {
            e.printStackTrace();
        } catch (SftpException e) {
            e.printStackTrace();
        }


        return tmpFile;
    }

}
