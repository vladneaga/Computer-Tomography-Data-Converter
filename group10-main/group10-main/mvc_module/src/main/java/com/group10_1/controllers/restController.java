package com.group10_1.controllers;


import CT_CONVERTER_API.WrongFileFormatException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import static com.group10_1.controllers.mainController.*;

/**
 * The {@code RestController} class is a Spring MVC controller annotated with {@code @RestController}.
 * It handles HTTP requests and produces HTTP responses in a RESTful manner.
 */
@RestController
public class restController {
    /**
     * Retrieves a file as a {@code FileSystemResource} and sends it as the response to the client.
     *
     * @param response The HTTP servlet response used to send the file.
     * @return A {@code FileSystemResource} representing the file.
     */
    @RequestMapping(value = "/downloadMusterstadt1", method = RequestMethod.GET, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    @ResponseBody
    public FileSystemResource getFile1(HttpServletResponse response) {
        try {
            simpleCtConverter.writeDataType1(readCtFile);
        } catch (WrongFileFormatException e) {
            throw new RuntimeException(e);
        }
        // Set the content type to octet-stream
        response.setContentType("application/octet-stream");
        // Set the suggested file name with the .ct extension
        response.setHeader("Content-Disposition", "attachment; filename=data.ct");

        return new FileSystemResource(readCtFile);

    }
    /**
     * Retrieves multiple files as a {@code FileSystemResource} and sends them as the response to the client.
     *
     * @param response The HTTP servlet response used to send the files.
     * @return A {@code FileSystemResource} representing the files.
     */
    @RequestMapping(value = "/downloadMusterstadt2", method = RequestMethod.GET, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    @ResponseBody
    public FileSystemResource getFiles(HttpServletResponse response) {
        try {
            simpleCtConverter.writeDataType2(readTxtFile, readBinFile);
            // create a ZipOutputStream object
            FileOutputStream fos = new FileOutputStream(zipFile);
            ZipOutputStream zos = new ZipOutputStream(fos);
            File[] filesToBeWritten = new File[]{readTxtFile, readBinFile};

            for (int i = 0; i < filesToBeWritten.length; i++) {
                File srcFile = new File(filesToBeWritten[i].getAbsolutePath());
                FileInputStream fis = new FileInputStream(srcFile);

                // Start writing a new file entry
                zos.putNextEntry(new ZipEntry(srcFile.getName()));

                int length;
                // create byte buffer
                byte[] buffer = new byte[1024];

                // read and write the content of the file
                while ((length = fis.read(buffer)) > 0) {
                    zos.write(buffer, 0, length);
                }
                // current file entry is written and current zip entry is closed
                zos.closeEntry();

                // close the InputStream of the file
                fis.close();
            }


            // close the ZipOutputStream
            zos.close();
            /*  System.out.println("\nThe file is written successfully");*/

        } catch (IOException ioe) {
            System.out.println("Error creating zip file: " + ioe);
        } catch (WrongFileFormatException e) {
            throw new RuntimeException(e);
        }
        response.setContentType("application/octet-stream");
        // Set the suggested file name with the .ct extension
        response.setHeader("Content-Disposition", "attachment; filename=data.zip");
        return new FileSystemResource(zipFile);
    }
}


