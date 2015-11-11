package com.linhphan.androidboilerplate.util;

import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by linhphan on 11/11/15.
 */
public class FileUtil {
    /**
     * check if the external storage is available for reading and writing
     * @return true if...
     */
    public static boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state);
    }

    /**
     * check if the external storage is available to at least reading
     * @return true if...
     */
    public static boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state) || Environment.MEDIA_MOUNTED_READ_ONLY.equals(state);
    }

    /**
     * copy a special file
     * @param fileName
     * @param sourceDir
     * @param DestinationDir
     * @return the path that locate the new file
     */
    public static String copyFile(String fileName, String sourceDir, String DestinationDir) {
        InputStream inputStream;
        OutputStream outputStream;

        File dir = new File(DestinationDir);
        if (!dir.exists())
            dir.mkdirs();

        try {
            inputStream = new FileInputStream(sourceDir + fileName);
            File file = new File(DestinationDir + fileName);
            outputStream = new FileOutputStream(file);

            byte[] buffer = new byte[1024];
            int read;
            while ((read = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, read);
            }

            outputStream.flush();
            inputStream.close();
            outputStream.close();

            return file.getAbsolutePath();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * delete a special file in a special directory
     * @param dir
     * @param fileName
     * @return true if the file was deleted otherwise return false
     */
    public static boolean deleteFile(String dir, String fileName) {
        return (new File(dir + fileName)).delete();

    }

    /**
     * move a file to a special directory
     * @param fileName
     * @param sourceDir
     * @param destinationDir
     * @return the path that new file is located new destination folder
     */
    public static String moveFile(String fileName, String sourceDir, String destinationDir){
        InputStream inputStream = null;
        OutputStream outputStream = null;

        File dir = new File(destinationDir);
        if (!dir.exists())
            dir.mkdirs();

        try {
            File in = new File(sourceDir + fileName);
            File out = new File(destinationDir + fileName);
            inputStream = new FileInputStream(in);
            outputStream = new FileOutputStream(out);

            byte[] buffer = new byte[1024];
            int read;
            while ((read = inputStream.read(buffer)) != -1){
                outputStream.write(buffer, 0, read);
            }

            outputStream.flush();
            inputStream.close();
            outputStream.close();

            in.delete();//delete the old file

            return out.getAbsolutePath();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
