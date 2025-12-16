package hiimlyn.dev.documentservice.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@Builder
public class FileRecord {

	private String id;

	private String fileKey;

	private String bucket;

	private String originalFileName;

	private String contentType;

	private Long size_bytes;

	private LocalDateTime createdAt;

	private Map<String, String> customMetadata;
}
