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
    
    public String uploadProfilePic(Part file, String email){
        String newPath = null;
        try{
            String fileName = email+".png";
            String filePath = ROOT+"\\Documents\\NetBeansProjects\\Library\\web\\Upload\\To\\"+fileName;
            File f = new File(filePath);
            try (InputStream in = file.getInputStream(); OutputStream out = new java.io.FileOutputStream(f);) {
                int c;
                newPath = "Upload/To/"+fileName;
                while((c = in.read())!= -1){
                    out.write(c);
                }
            }
        }catch (IOException e){
            System.out.println("=========IOException: "+e);
        }
        return newPath;
    }
    
    public String uploadBook(Part file){
        String newPath = null;
        try{
            String fileName = file.getSubmittedFileName();
            String filePath = ROOT+"\\Documents\\NetBeansProjects\\Library\\web\\Upload\\To\\"+fileName;
            File f = new File(filePath);
            InputStream in = file.getInputStream();
            OutputStream out = new java.io.FileOutputStream(f);
            int c;
            newPath = "Upload/To/"+fileName;
            while((c = in.read())!= -1){
                out.write(c);
            }
            in.close();
            out.close();
        }catch (IOException e){
            System.out.println("=========IOException: "+e);
        }
        return newPath;
    }
    
    public String uploadBookCover(Part file){
        String newPath = null;
        try{
            String fileName = file.getSubmittedFileName();
            String filePath = ROOT+"\\Documents\\NetBeansProjects\\Library\\web\\Upload\\To\\"+fileName;
            File f = new File(filePath);
            InputStream in = file.getInputStream();
            OutputStream out = new java.io.FileOutputStream(f);
            int c;
            newPath = "Upload/To/"+fileName;
            while((c = in.read())!= -1){
                out.write(c);
            }
            in.close();
            out.close();
        }catch (IOException e){
            System.out.println("=========IOException: "+e);
        }
        return newPath;
    }
    
    public void downloadFile(){
        
    }
}
