package JPEG;

import java.io.File;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author kruminsh
 */
public class FileUtils {
    
    public static boolean fileExists(File file) {
        return file.exists() && !file.isDirectory();
    }
    
    public static boolean deleteFile(File file) {
        if (fileExists(file)) {
            return file.delete();
        } else {
            return false;
        }
    }
    
    public static String getExtension(String fileName) {
        String extension = "";
        int index = fileName.lastIndexOf('.');
        if (index >= 0) {
            extension = fileName.substring(index + 1);
        }
        return extension;
    }
}
