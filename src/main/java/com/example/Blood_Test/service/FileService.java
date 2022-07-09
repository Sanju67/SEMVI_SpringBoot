package com.example.Blood_Test.service;

import java.nio.file.Path;
import java.nio.file.Paths;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import com.example.Blood_Test.model.FileData;

@Service
public class FileService {

    @Value("${files.path}")
    private String filesPath;

    public Resource download(String filename) {
        try {
            Path file = Paths.get(filesPath)
                             .resolve(filename);
            Resource resource = new UrlResource(file.toUri());
            System.out.println("Path got  : " + filesPath);
            System.out.println();
            System.out.println("file.toUri value : "+file.toUri() );
            System.out.println("resource value : " + resource);
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not read the file!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

    public List<FileData> list() {
        try {
            Path root = Paths.get(filesPath);

            if (Files.exists(root)) {
                return Files.walk(root, 1)
                            .filter(path -> !path.equals(root))
                            .filter(path -> path.toFile()
                                                .isFile())
                            .collect(Collectors.toList())
                            .stream()
                            .map(this::pathToFileData)
                            .collect(Collectors.toList());
            }

            return Collections.emptyList();
        } catch (IOException e) {
            throw new RuntimeException("Could not list the files!");
        }
    }

    private FileData pathToFileData(Path path) {
        FileData fileData = new FileData();
        String filename = path.getFileName()
                              .toString();
        fileData.setFilename(filename);

        try {
            fileData.setContentType(Files.probeContentType(path));
            fileData.setSize(Files.size(path));
        } catch (IOException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }

        return fileData;
    }
}