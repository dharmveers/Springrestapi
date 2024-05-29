package com.springrestapi.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class HelperUtil {
    @Value("${document.path}")
    private static String documentPath;

    private static final String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/src/main/resources/image";
    public static String uploadFile(MultipartFile file) throws IOException {

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Path path = Paths.get(UPLOAD_DIRECTORY).resolve(fileName);
        Files.copy(file.getInputStream(),path,StandardCopyOption.REPLACE_EXISTING);

        return path.toString();
    }
}
