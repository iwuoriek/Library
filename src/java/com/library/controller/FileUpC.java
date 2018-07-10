/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.library.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.servlet.http.Part;

/**
 *
 * @author Kelechi
 */
@Named("upload")
@RequestScoped
public class FileUpC {

    private Part uploadedFile;
    private String text;

    public void setUploadedFile(Part uploadedFile) {
        this.uploadedFile = uploadedFile;
    }

    public Part getUploadedFile() {
        return uploadedFile;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void upload() {
        try {
            if (uploadedFile != null) {
                File file = new File("D:\\Uploads\\image.png");
                InputStream in = uploadedFile.getInputStream();
                OutputStream out = new java.io.FileOutputStream(file);
                text = "Name: "+uploadedFile.getName()
                        +", SubmittedFileName: "+uploadedFile.getSubmittedFileName()
                        +", Size: "+uploadedFile.getSize()
                        +", Type: "+uploadedFile.getContentType();
                int c;
                while((c = in.read())!= -1){
                    out.write(c);
                }
                out.close();
                in.close();
            }
        } catch (IOException e) {

        }
    }

}
