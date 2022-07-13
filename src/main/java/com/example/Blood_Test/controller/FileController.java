package com.example.Blood_Test.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.Blood_Test.model.FileData;
import com.example.Blood_Test.service.FileService;

@RestController
public class FileController {

	@Autowired
    private final FileService fileService;
	private final Path root = Paths.get("files");
	
    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getfiles")
    public List<FileData> list() {
        return fileService.list();
    }
    
    @RequestMapping(method = RequestMethod.POST , value = "/upload/{filename}")
    public void upload(@PathVariable("file") MultipartFile file,@PathVariable("filename") String filename) {
		try {
			System.out.println("Inside upload service " + file);
			System.out.println("file name " + filename);
//			 Files.copy(file.getInputStream(), this.root.resolve((file).getOriginalFilename()));
			Files.copy(file.getInputStream(),this.root.resolve(filename)) ;
		} catch (Exception e) {
		  throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
		}
	  }
    
    @RequestMapping(method = RequestMethod.GET, value = "/downloadfile/{filename}")
    @ResponseBody
    public ResponseEntity<Resource> downloadFile(@PathVariable("filename") String filename) throws IOException {
    	System.out.println("Request for file received ..."+ filename);
    	File f = new File(filename);
    	System.out.println("file path :"+ f.getAbsolutePath());
        Resource file = (Resource) fileService.download(filename);
        Path path = ((org.springframework.core.io.Resource) file).getFile()
                        .toPath();

        return ResponseEntity.ok()
                             .header(HttpHeaders.CONTENT_TYPE, Files.probeContentType(path))
                             .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                             .body(file);
    }
}
