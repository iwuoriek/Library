/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.library.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.servlet.http.Part;
import org.springframework.stereotype.Component;

/**
 *
 * @author Kelechi
 */
@Component
public class FileUploader {

    public static final String ROOT = System.getProperty("user.home") + "\\Upload\\To\\";

    private void uploadFile(Part file, String fileName, String filePath) {
        try {
            File f = new File(filePath + fileName);
            Path directory = Paths.get(filePath);
            if(Files.notExists(directory, LinkOption.NOFOLLOW_LINKS)){
                Files.createDirectories(directory);
            }
            if (!f.exists()) {
                f.createNewFile();
            }
            try (InputStream in = file.getInputStream(); OutputStream out = new FileOutputStream(f);) {
                int c;
                while ((c = in.read()) != -1) {
                    out.write(c);
                }
            }
        } catch (IOException e) {
            System.out.println("IOException: " + e);
        }
    }

    public String imageUpload(Part file) {
        String fileName = null;
        if (file.getContentType().equals("image/png") || file.getContentType().equals("image/jpeg")) {
            fileName = FacesUtil.getUserId() + ".png";
            String filePath = ROOT + "\\Images\\";
            uploadFile(file, fileName, filePath);
        }
        return fileName;
    }

    public String uploadBook(Part file, String bookId) {
        String fileName = null;
        if (file.getContentType().equals("application/pdf")) {
            fileName = bookId + ".pdf";
            String filePath = ROOT + "\\Books\\";
            uploadFile(file, fileName, filePath);
        }
        return fileName;
    }

    public String uplaodBookCover(Part file, String bookId) {
        String fileName = null;
        if (file.getContentType().equals("image/png") || file.getContentType().equals("image/jpeg")) {
            fileName = bookId + ".png";
            String filePath = ROOT + "\\Books\\Cover\\";
            uploadFile(file, fileName, filePath);
        }
        return fileName;
    }
}
