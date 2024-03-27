package com.example.project.controllers;

import com.example.project.domain.UploadFileResponse;
import com.example.project.services.FileStorageService;
import jakarta.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.support.ServletContextAwareProcessor;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.util.Objects;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/uploadFile")
public class FileUploadController {
    private ServletContext servletContext;

    @Autowired
    private FileStorageService fileStorageService;

    @PostMapping
    public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file) {
        String fileName = fileStorageService.storeFile(file);
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(fileName)
                .toUriString();

        String[] parts = fileName.split("/");
        String imageName = parts[parts.length - 1];

        return new UploadFileResponse(imageName, fileDownloadUri, file.getContentType(), file.getSize());
    }

    @GetMapping("/downloadFile/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName) {
        Resource resource = fileStorageService.loadFileAsResource(fileName);
        if (resource instanceof UrlResource) {
            UrlResource urlResource = (UrlResource) resource;
            try {
                if (urlResource.getURL().toString().toLowerCase().endsWith(".jpg") ||
                        urlResource.getURL().toString().toLowerCase().endsWith(".png") ||
                        urlResource.getURL().toString().toLowerCase().endsWith(".gif")) {
                    byte[] byteArray = Files.readAllBytes(urlResource.getFile().toPath());
                    ByteArrayResource byteArrayResource = new ByteArrayResource(byteArray);
                    return ResponseEntity.ok()
                            .contentType(MediaType.IMAGE_JPEG)
                            .header(HttpHeaders.CONTENT_DISPOSITION,
                                    "inline; filename=\"" + resource.getFilename() + "\"")
                            .body(byteArrayResource);
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}
