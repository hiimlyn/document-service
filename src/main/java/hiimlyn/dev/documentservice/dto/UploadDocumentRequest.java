package hiimlyn.dev.documentservice.dto;

import lombok.Data;

@Data
public class UploadDocumentRequest {
    private String documentName;
    private String documentType;
}
