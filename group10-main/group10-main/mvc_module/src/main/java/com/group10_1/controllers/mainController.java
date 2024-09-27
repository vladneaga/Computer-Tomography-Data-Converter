package com.group10_1.controllers;

import CT_CONVERTER.Simple_CT_Converter;
import CT_CONVERTER_API.WrongFileFormatException;

import MyLoggerPackage.ConsoleLogger;

import MyLoggerPackage.FileLogger;
import MyLoggerPackage.LoggerFactory;
import MyLoggerPackage_API.LoggerInterface;
import com.group10_1.util.PatientDataValidator;
import com.group10_1.util.ressourceBundles.PublicListResourceBundle;
import com.group10_1.util.ressourceBundles.Resource_de_DE;
import com.group10_1.util.ressourceBundles.Resource_en_US;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * Controller class for handling main application logic, file uploads, language changes, and patient data manipulation.
 */
@Controller
@RequestMapping("")
public class mainController {
    // Save the status of the choice box
    boolean musterstadt1 = true;
    // Boolean to track file format choice
    Boolean fileFormat1;
    // Static instances of converter, English and German resource bundles

    static Simple_CT_Converter simpleCtConverter = new Simple_CT_Converter();
   static Resource_en_US englishBundle = new Resource_en_US();
    static Resource_de_DE germanBundle = new Resource_de_DE();
    static PublicListResourceBundle resourceBundle = germanBundle;
    static File loggerFile = Paths.get("mvc_module/src/main/resources/transitFiles/loggerFile.txt").toAbsolutePath().toFile();
    static ConsoleLogger consoleLogger= (ConsoleLogger) LoggerFactory.createLogger("console", null);
    static FileLogger fileLogger= (FileLogger) LoggerFactory.createLogger("file",  loggerFile);

    // Autowired PatientDataValidator for form validation
    @Autowired
    PatientDataValidator patientDataValidator;
    // Static files to read and write during the application lifecycle

    static File readCtFile = Paths.get("mvc_module/src/main/resources/transitFiles/experimental.ct").toAbsolutePath().toFile();
   static File readTxtFile = Paths.get("mvc_module/src/main/resources/transitFiles/experimental.txt").toAbsolutePath().toFile();
   static File readBinFile = Paths.get("mvc_module/src/main/resources/transitFiles/experimental.bin").toAbsolutePath().toFile();
   static File zipFile = Paths.get("mvc_module/src/main/resources/transitFiles/experimental.zip").toAbsolutePath().toFile();
    /**
     * Handles the main page mapping.
     *
     * @param model Model to add attributes for rendering
     * @return String representing the view name ("main")
     */
   @GetMapping("/")
    public String mainPage(Model model) {
    if(fileFormat1 != null) {
        musterstadt1 = fileFormat1;
    }
        model.addAttribute("musterstadt1", musterstadt1);
        model.addAttribute("bundle", resourceBundle.getContents());
        String message = "User accessed the main page.";
        consoleLogger.log(LoggerInterface.LogLevel.INFO, message);
        fileLogger.log(LoggerInterface.LogLevel.INFO, message);
        return "main";
    }
    /**
     * Handles the language change mapping.
     *
     * @param model    Model to add attributes for rendering
     * @param language String representing the selected language
     * @return String representing the redirection to the main page
     */
    @PostMapping("/changeLanguage")
    public String changeLanguage(Model model, @RequestParam("language")String language) {
        if(language.equals("de")) {
            resourceBundle = germanBundle;
        } else {
            resourceBundle = englishBundle;
        }

        String message = "User changed language to " + (language.equals("en") ? "English." : "German.");
        consoleLogger.log(LoggerInterface.LogLevel.INFO, message);
        fileLogger.log(LoggerInterface.LogLevel.INFO, message);
        return "redirect:/";
    }
    /**
     * Handles the post request for the main page.
     *
     * @param model  Model to add attributes for rendering
     * @param choice boolean representing the file format choice
     * @return String representing the view name ("main")
     */
    @PostMapping("/")
    public String mainPagePost(Model model, @RequestParam("choice") boolean choice) {
        musterstadt1 = choice;

        model.addAttribute("musterstadt1", musterstadt1);
        model.addAttribute("fileFormat1", fileFormat1);
        model.addAttribute("bundle", resourceBundle.getContents());
        String message = "User changed format to " + (musterstadt1 ? "Musterstadt1." : "Musterstadt2.");
        consoleLogger.log(LoggerInterface.LogLevel.INFO, message);
        fileLogger.log(LoggerInterface.LogLevel.INFO, message);
        return "main";

    }
    /**
     * Handles the file upload for Musterstadt1 format.
     *
     * @param model   Model to add attributes for rendering
     * @param ctFile  MultipartFile representing the Musterstadt1 format file
     * @return String representing the view name ("patient")
     */
    @PostMapping("/musterstadt1")
    public String readMusterstadt1(Model model, @RequestParam("ctFile") MultipartFile ctFile) {
        try {
            ctFile.transferTo(readCtFile);
            System.out.println(readCtFile.getAbsolutePath());
            simpleCtConverter.readFromDataType1(readCtFile);
        } catch (IOException | WrongFileFormatException e) {
            String message = "Error at file uploading.";
            consoleLogger.log(LoggerInterface.LogLevel.ERROR, message);
            fileLogger.log(LoggerInterface.LogLevel.ERROR, message);
            throw new RuntimeException(e);
        }
        model.addAttribute("patient", simpleCtConverter.getPatient());
            fileFormat1 = true;
        model.addAttribute("fileFormat1", fileFormat1);
        model.addAttribute("bundle", resourceBundle.getContents());
        String message = "User uploaded a .ct file.";
        consoleLogger.log(LoggerInterface.LogLevel.INFO, message);
        fileLogger.log(LoggerInterface.LogLevel.INFO, message);
        return "patient";
    }
    /**
     * Handles the file upload for Musterstadt2 format.
     *
     * @param model   Model to add attributes for rendering
     * @param txtFile MultipartFile representing the Musterstadt2 format txt file
     * @param binFile MultipartFile representing the Musterstadt2 format bin file
     * @return String representing the view name ("patient")
     */
    @PostMapping("/musterstadt2")
    public String readMusterstadt2(Model model, @RequestParam("txtFile") MultipartFile txtFile, @RequestParam("binFile") MultipartFile binFile) {

        try {
            System.out.println(binFile.getSize());
            txtFile.transferTo(readTxtFile);
            if (readBinFile.exists()) {
                System.out.println( readBinFile.delete());
              readBinFile = Paths.get("mvc_module/src/main/resources/transitFiles/experimental.bin").toAbsolutePath().toFile();
            }

            // Proceed with the transfer
/*
            binFile.transferTo(readBinFile);
*/
            // Delay to give time for any previous locks to be released
            Thread.sleep(500);
            Files.copy(binFile.getInputStream(), readBinFile.toPath(), StandardCopyOption.REPLACE_EXISTING);


            simpleCtConverter.readFromDataType2(readTxtFile, readBinFile);

        } catch (IOException | WrongFileFormatException | InterruptedException e) {
            String message = "Error at file uploading.";
            consoleLogger.log(LoggerInterface.LogLevel.ERROR, message);
            fileLogger.log(LoggerInterface.LogLevel.ERROR, message);
            System.out.println(e.getCause());
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        model.addAttribute("patient", simpleCtConverter.getPatient());
        fileFormat1 = false;
        model.addAttribute("fileFormat1", fileFormat1);
        model.addAttribute("bundle", resourceBundle.getContents());
        String message = "User uploaded a .txt and .bin file.";
        consoleLogger.log(LoggerInterface.LogLevel.INFO, message);
        fileLogger.log(LoggerInterface.LogLevel.INFO, message);
        return "patient";
    }
    /**
     * Handles the patient data page mapping.
     *
     * @param model Model to add attributes for rendering
     * @return String representing the view name ("patient")
     */
    @GetMapping("/patientData")
    public String patiendData(Model model) {
        Simple_CT_Converter.Patient patient = simpleCtConverter.getPatient();
        if(patient.getName()!=null && patient.getBirth()!=null) {
            model.addAttribute("patient", simpleCtConverter.getPatient());
        }
        model.addAttribute("fileFormat1", fileFormat1);
        model.addAttribute("bundle", resourceBundle.getContents());
        String message = "User visits the page with patient data.";
        consoleLogger.log(LoggerInterface.LogLevel.INFO, message);
        fileLogger.log(LoggerInterface.LogLevel.INFO, message);
        return "patient";
    }
    /**
     * Handles the patient data edit page mapping.
     *
     * @param model Model to add attributes for rendering
     * @return String representing the view name ("edit")
     */
    @GetMapping("/edit")
    public String patientDataEdit(Model model) {
        model.addAttribute("patient", simpleCtConverter.getPatient());
        model.addAttribute("bundle", resourceBundle.getContents());
        String message = "User is editing the patient data.";
        consoleLogger.log(LoggerInterface.LogLevel.INFO, message);
        fileLogger.log(LoggerInterface.LogLevel.INFO, message);
        return "edit";
    }
    /**
     * Handles the form submission for patient data edit.
     *
     * @param model          Model to add attributes for rendering
     * @param patient        Patient object with edited data
     * @param bindingResult  BindingResult for form validation
     * @return String representing the redirection to the patient data page
     */
    @PostMapping("/edit/check")
    public String patientDataEditCheck(Model model, @ModelAttribute("patient") Simple_CT_Converter.Patient patient,
                                       BindingResult bindingResult) {
        // Validate patient data
        patientDataValidator.validate(patient, bindingResult);
        if(bindingResult.hasErrors()) {
            String message = "Error at editing patient data.";
            consoleLogger.log(LoggerInterface.LogLevel.WARN, message);
            fileLogger.log(LoggerInterface.LogLevel.WARN, message);
            model.addAttribute("bundle", resourceBundle.getContents());
            return "/edit";
        }
        // Copy edited patient details to the original patient
       copyPatientDetails(patient);
        String message = "Patient data edited successfully.";
        consoleLogger.log(LoggerInterface.LogLevel.INFO, message);
        fileLogger.log(LoggerInterface.LogLevel.INFO, message);
        try {
            if(fileFormat1)
            simpleCtConverter.writeDataType1(readCtFile);
            else {
                simpleCtConverter.writeDataType2JustTxt(readTxtFile);
            }
        } catch (WrongFileFormatException e) {
            throw new RuntimeException(e);
        }
        // Redirect to patient data page
        return "redirect:/patientData";
    }


    /**
     * Copies edited patient details to the original patient.
     *
     * @param patient Patient object with edited data
     */
    public void copyPatientDetails(Simple_CT_Converter.Patient patient) {
        Simple_CT_Converter.Patient copyPatient = simpleCtConverter.getPatient();
        copyPatient.setName(patient.getName());
        copyPatient.setBirth(patient.getBirth());
        copyPatient.setHeight(patient.getHeight());
        copyPatient.setWeight(patient.getWeight());
        copyPatient.setIZ(patient.getIZ());
    }

}
