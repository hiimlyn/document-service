package hiimlyn.dev.documentservice.service;

import hiimlyn.dev.documentservice.config.R2Props;
import hiimlyn.dev.documentservice.dto.FileRecord;
import hiimlyn.dev.documentservice.mapper.FileRecordMapper;
import hiimlyn.dev.documentservice.service.s3.S3UploadService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
@AllArgsConstructor
public class MediaUploadService {

	private final S3UploadService s3UploadService;
	private final FileRecordService fileRecordService;
	private final R2Props props;
	private final FileRecordMapper fileRecordMapper;

	public FileRecord uploadMedia(byte[] mediaData, String fileName) {
		// upload media to s3
		try {
			s3UploadService.uploadFile(mediaData, fileName);
		} catch (Exception e) {
			log.error("Upload to S3 failed", e);
			return null;
		}

		try {
			var fileRecord = FileRecord.builder()
					.fileKey(fileName)
					.originalFileName(fileName)
					.bucket(props.getBucket())
					.size_bytes((long) mediaData.length)
					.contentType("application/octet-stream")
					.createdAt(LocalDateTime.now())
					.build();

			var recorded = fileRecordService.saveFileRecord(fileRecordMapper.toEntity(fileRecord));
			return fileRecordMapper.toDto(recorded);
		} catch (Exception e) {
			log.error("Saving file record failed", e);
			// TODO: handle emit to queue for retry later
		}
		return null;
	}
}