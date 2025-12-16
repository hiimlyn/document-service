package hiimlyn.dev.documentservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "file_records")
public class FileRecordEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;

	@Column(name = "file_key", nullable = false)
	private String fileKey;

	@Column(name = "bucket", nullable = false)
	private String bucket;

	@Column(name = "original_filename", nullable = false)
	private String originalFileName;

	@Column(name = "content_type")
	private String contentType;

	@Column(name = "size_bytes")
	private Long size_bytes;

	@Column(name = "created_at", nullable = false)
	private LocalDateTime createdAt;

	@JdbcTypeCode(SqlTypes.JSON)
	@Column(name = "custom_metadata", columnDefinition = "jsonb")
	private Map<String, String> customMetadata;
}
