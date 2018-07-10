/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.library.aspect;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.servlet.http.Part;
import org.springframework.stereotype.Component;

/**
 *
 * @author Kelechi
 */
@Component
public class FileUploader {
    private static final String ROOT = System.getProperty("user.home");
    public String uploadFile(Part file){
        String filePath = null;
        InputStream in;
        OutputStream out;
//        File f;
        try{
            String fileName = file.getSubmittedFileName();
            filePath = ROOT+"\\Upload\\To\\"+fileName;
            File f = new File(filePath);
            in = file.getInputStream();
            out = new java.io.FileOutputStream(f);
            int c;
            System.out.println("File Name: "+file.getSubmittedFileName()+", File Size: "+file.getSize()+", Content Type: "+file.getContentType()+", File Path: "+filePath);
            while((c = in.read())!= -1){
                out.write(c);
            }
            in.close();
            out.close();
        }catch (IOException e){
            System.out.println("=========IOException: "+e);
        }
        return filePath;
    }
    
    public void downloadFile(){
        
    }
}
