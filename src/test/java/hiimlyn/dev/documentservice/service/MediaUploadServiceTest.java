package hiimlyn.dev.documentservice.service;

import hiimlyn.dev.documentservice.config.R2Props;
import hiimlyn.dev.documentservice.dto.FileRecord;
import hiimlyn.dev.documentservice.entity.FileRecordEntity;
import hiimlyn.dev.documentservice.mapper.FileRecordMapper;
import hiimlyn.dev.documentservice.service.s3.S3UploadService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import software.amazon.awssdk.services.s3.model.S3Exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MediaUploadServiceTest {
	private AutoCloseable closeable;
	private MediaUploadService mediaUploadService;

	@Mock
	private S3UploadService s3UploadService;

	@Mock
	private FileRecordService fileRecordService;

	@Mock
	private R2Props r2Props;

	@Mock
	private FileRecordMapper fileRecordMapper;

	@BeforeEach
	void setUp() {
		closeable = openMocks(this);
		when(r2Props.getBucket()).thenReturn("test-bucket");
		when(fileRecordMapper.toDto(any())).thenReturn(FileRecord.builder()
				.originalFileName("testfile.txt")
				.build());
		mediaUploadService = new MediaUploadService(s3UploadService, fileRecordService, r2Props, fileRecordMapper);
	}

	@AfterAll
	void tearDown() throws Exception {
		closeable.close();
	}

	@Test
	void uploadMedia_givenValidInput_shouldUploadSuccessfully() {
		var fileRecord = new FileRecordEntity();
		fileRecord.setOriginalFileName("testfile.txt");
		doNothing().when(s3UploadService).uploadFile(any(byte[].class), anyString());
		when(fileRecordService.saveFileRecord(any())).thenReturn(fileRecord);

		assertEquals("testfile.txt",
				mediaUploadService.uploadMedia("sample data".getBytes(), "testfile.txt")
						.getOriginalFileName());
	}

	@Test
	void uploadMedia_whenS3UploadFails_shouldReturnNull() {
		doThrow(S3Exception.class).when(s3UploadService).uploadFile(any(byte[].class), anyString());

		assertNull(mediaUploadService.uploadMedia("sample data".getBytes(), "testfile.txt"));
	}

	@Test
	void uploadMedia_whenFileRecordSaveFails_shouldReturnNull() {
		doNothing().when(s3UploadService).uploadFile(any(byte[].class), anyString());
		when(fileRecordService.saveFileRecord(any())).thenThrow(RuntimeException.class);

		assertNull(mediaUploadService.uploadMedia("sample data".getBytes(), "testfile.txt"));
	}
}
