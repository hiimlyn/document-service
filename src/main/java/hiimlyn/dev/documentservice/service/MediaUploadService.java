package hiimlyn.dev.documentservice.service;

import hiimlyn.dev.documentservice.service.s3.S3UploadService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MediaUploadService {

	private final S3UploadService s3UploadService;

	public String uploadMedia(byte[] mediaData, String fileName) {
		// upload media to s3

		var result = s3UploadService.uploadFile(mediaData, fileName);
		return "s3://" + result;
//		return "http://media.service/uploaded/" + System.currentTimeMillis();
	}
}
