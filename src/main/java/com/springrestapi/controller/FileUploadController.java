package com.springrestapi.controller;

import com.springrestapi.services.Impl.FileStorageService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@CrossOrigin(origins = "*")
@RestController
public class FileUploadController {
    private final FileStorageService fileStorageService;

    public FileUploadController(FileStorageService fileStorageService) {
        this.fileStorageService = fileStorageService;
    }

    @PostMapping(value = "/upload",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadFile(@RequestPart MultipartFile multipartFile){
        if(multipartFile.isEmpty()){
            return ResponseEntity.ok("No file chosen");
        }
        String storedFile = fileStorageService.storeFile(multipartFile);
        String imageUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/uploads/files/")
                .path(storedFile)
                .toUriString();
        return ResponseEntity.ok(imageUrl);
    }

    private static final String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/src/main/resources/image/";

    @GetMapping("/uploads/{filename:.+}")
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
        Path filePath = Paths.get(UPLOAD_DIRECTORY).resolve(filename).normalize();
        Resource resource;
        try {
            resource = new UrlResource(filePath.toUri());
        } catch (MalformedURLException e) {
            throw new RuntimeException("File not found: " + filename);
        }

        if (!resource.exists() || !resource.isReadable()) {
            throw new RuntimeException("File not found: " + filename);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG); // Adjust content type based on your image type

        return ResponseEntity.ok()
                .headers(headers)
                .body(resource);
    }
    @PostMapping("/upload/image")
    public ResponseEntity<Object> uploadImage(@RequestParam("image") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("Please upload an image file");
        }

        try {
            String filename = StringUtils.cleanPath(file.getOriginalFilename());
            Path uploadPath = Paths.get(UPLOAD_DIRECTORY).resolve(filename);
            Files.copy(file.getInputStream(), uploadPath, StandardCopyOption.REPLACE_EXISTING);

            String imageUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/uploads/")
                    .path(filename)
                    .toUriString();

            // Get image dimensions (width and height)
            int width = getImageWidth(uploadPath);
            int height = getImageHeight(uploadPath);

            // Construct the JSON response object
            UploadedImage uploadedImage = new UploadedImage(imageUrl, filename, width, height);

            return ResponseEntity.ok(uploadedImage);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload image");
        }
    }
    // Get image width
    private int getImageWidth(Path imagePath) {
        try {
            BufferedImage image = ImageIO.read(imagePath.toFile());
            return image.getWidth();
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }

    // Get image height
    private int getImageHeight(Path imagePath) {
        try {
            BufferedImage image = ImageIO.read(imagePath.toFile());
            return image.getHeight();
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }
    static class UploadedImage {
        private final String src;
        private final String alt;
        private final int width;
        private final int height;

        public UploadedImage(String src, String alt, int width, int height) {
            this.src = src;
            this.alt = alt;
            this.width = width;
            this.height = height;
        }

        public String getSrc() {
            return src;
        }

        public String getAlt() {
            return alt;
        }

        public int getWidth() {
            return width;
        }

        public int getHeight() {
            return height;
        }
    }

}
