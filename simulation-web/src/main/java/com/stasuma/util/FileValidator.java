package com.stasuma.util;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class FileValidator implements Validator {
    private static final String requiredType = ".xml";

    @Override
    public void validate(Object uploadedFile, Errors errors) {
        UploadedFile file = (UploadedFile) uploadedFile;
        if (file.getFile().getSize() == 0) {
            errors.rejectValue("file", "uploadForm.selectFile", "Please select a file!");
            return;
        }
        String fileName = file.getFile().getOriginalFilename();
        String type = fileName.substring(fileName.lastIndexOf('.'));
        System.out.println(type);
        if (!type.equals(requiredType)) {
            errors.rejectValue("file", "uploadForm.typeFile", "Use file type of .xml");
        }
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

}