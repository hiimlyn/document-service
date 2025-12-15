package hiimlyn.dev.documentservice.controller;

import hiimlyn.dev.documentservice.service.s3.inteface.UploadService;
import lombok.AllArgsConstructor;
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

    private final UploadService uploadService;

    @PostMapping("/upload")
    // handle multipart form data
    public ResponseEntity<String> uploadDocument(@RequestParam("file") MultipartFile file) throws IOException {

        var result = uploadService.uploadFile(file.getBytes(), file.getOriginalFilename());
        return ResponseEntity.ok(result);
    }
}
