package org.example;

import CT_CONVERTER.CT_Converter;
import CT_CONVERTER.Simple_CT_Converter;
import MyLoggerPackage.LoggerFactory;
import org.springframework.core.io.ResourceLoader;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void printAny() {
        LoggerFactory.createLogger("console", null);
    }
    public static void main(String[] args)  {
        System.out.println("Hello world!");
        String resourceName = "sample-files/dataViewer2.txt";
        Simple_CT_Converter ctConverter = new Simple_CT_Converter();
        ClassLoader classLoader = ResourceLoader.class.getClassLoader();
        try {
            //ctConverter.readFromDataType1("sample-files/dataViewer2.ct");
            ctConverter.readFromDataType2("sample-files/dataViewer2.txt",
                    "sample-files/dataViewer2.bin");
            ctConverter.writeDataType2("sample-files/fileToBeFulled.txt",
                    "sample-files/fileToBeFulled.bin");
            ctConverter.writeDataType1("sample-files/fileToBeFulled.ct");
            ctConverter.readFromDataType1("sample-files/fileToBeFulled.ct");

            ctConverter.writeDataType1("sample-files/dataViewer1.ct");
        } catch (CT_Converter.WrongFileFormatException e) {
            throw new RuntimeException(e);
        }

    }
}