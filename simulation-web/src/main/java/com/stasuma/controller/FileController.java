package com.stasuma.controller;

import com.stasuma.service.*;
import com.stasuma.util.Constants;
import com.stasuma.util.FileValidator;
import com.stasuma.util.UploadedFile;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Controller
@SessionAttributes("filename")
public class FileController {
    private static final Logger LOGGER = Logger.getLogger(FileController.class);

    private final String rootPath = Constants.PATH_TO_APP_FOLDER;
    private final String fileName = Constants.XML_FILE_NAME;

    @Autowired
    private SimulationWebInfoService simulationWebInfoService;
    @Autowired
    private BusWebService busWebService;
    @Autowired
    private RouteWebService routeWebService;
    @Autowired
    private SimulationWebWorkTimeService simulationWebWorkTimeService;
    @Autowired
    private StopWebService stopWebService;
    @Autowired
    private FileValidator fileValidator;

    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView uploadFile(@ModelAttribute("uploadedFile") UploadedFile uploadedFile, BindingResult result){
        ModelAndView modelAndView = new ModelAndView();
        MultipartFile file = uploadedFile.getFile();
        fileValidator.validate(uploadedFile, result);

        if (result.hasErrors()) {
            modelAndView.addObject("error", true);
            modelAndView.setViewName("main");
            LOGGER.debug("Error during add file to server.");
        }else {
            try {
                byte[] bytes = file.getBytes();
                File dir = new File(rootPath + File.separator);
                if (!dir.exists()) {
                    if(!dir.mkdir()){
                        modelAndView.addObject("error", true);
                        modelAndView.setViewName("main");
                        return modelAndView;
                    }
                }
                File loadFile = new File(dir.getAbsolutePath() + File.separator + Constants.XML_FILE_NAME);
                try(BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(loadFile))) {
                    stream.write(bytes);
                    stream.flush();
                }
                RedirectView redirectView = new RedirectView("uploaded");
                redirectView.setStatusCode(HttpStatus.FOUND);
                modelAndView.setView(redirectView);
                modelAndView.addObject("filename", fileName);
                LOGGER.debug("File successfully loaded to server");
                deleteAllDataFromDB();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        return modelAndView;
    }

    private void deleteAllDataFromDB(){
        simulationWebInfoService.deleteAllInfo();
        busWebService.deleteAll();
        routeWebService.deleteAll();
        simulationWebWorkTimeService.delete();
        stopWebService.deleteAll();
        LOGGER.debug("All old data deleted");
    }

    @RequestMapping(value = "/uploaded", method = RequestMethod.GET)
    public String uploaded(){
        return "main";
    }

}
