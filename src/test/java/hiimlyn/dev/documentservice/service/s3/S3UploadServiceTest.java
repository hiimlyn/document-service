package hiimlyn.dev.documentservice.service.s3;

import hiimlyn.dev.documentservice.config.R2Props;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class S3UploadServiceTest {

	private AutoCloseable closeable;

	private S3UploadService s3UploadService;

	@Mock
	private S3Client s3Client;

	@Mock
	private R2Props props;

	@BeforeAll
	void setUp() {
		closeable = openMocks(this);
		when(props.getBucket()).thenReturn("test-bucket");
		s3UploadService = new S3UploadService(s3Client, props);
	}

	@AfterAll
	void tearDown() throws Exception {
		closeable.close();
	}


	@Test
	void uploadFile_whenValidFile_ShouldUploadFileSuccessfully() {
		when(s3Client.putObject(any(PutObjectRequest.class), any(RequestBody.class))).thenReturn(null);

		s3UploadService.uploadFile("Test data".getBytes(), "testfile.txt");
		verify(s3Client, times(1)).putObject(any(PutObjectRequest.class), any(RequestBody.class));
	}

}
