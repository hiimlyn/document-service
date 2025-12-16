package hiimlyn.dev.documentservice.controller;

import hiimlyn.dev.documentservice.dto.FileRecord;
import hiimlyn.dev.documentservice.service.MediaUploadService;
import hiimlyn.dev.documentservice.service.s3.inteface.UploadService;
import lombok.AllArgsConstructor;
import org.apache.tomcat.util.http.fileupload.FileUpload;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@AllArgsConstructor
@RequestMapping("/documents")
public class DocumentController {

    private final MediaUploadService mediaUploadService;
    @PostMapping("/upload")
    // handle multipart form data
    public ResponseEntity<FileRecord> uploadDocument(@RequestParam("file") MultipartFile file) throws IOException {

        var result = mediaUploadService.uploadMedia(file.getBytes(), file.getOriginalFilename());
        return ResponseEntity.ok(result);
    }
}
