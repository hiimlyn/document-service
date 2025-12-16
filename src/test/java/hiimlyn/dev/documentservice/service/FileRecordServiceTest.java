package hiimlyn.dev.documentservice.service;

import hiimlyn.dev.documentservice.entity.FileRecordEntity;
import hiimlyn.dev.documentservice.repository.FileRecordRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class FileRecordServiceTest {
	AutoCloseable closeable;
	FileRecordService fileRecordService;

	@Mock
	FileRecordRepository fileRecordRepository;

	@BeforeAll
	void setUp() {
		closeable = openMocks(this);
		fileRecordService = new FileRecordService(fileRecordRepository);
	}

	@AfterAll
	void tearDown() throws Exception {
		closeable.close();
	}

	@Test
	void saveFileRecord_givenValidInput_shouldSaveSuccessfully() {
		var entity = new FileRecordEntity();
		entity.setOriginalFileName("testfile.txt");

		when(fileRecordRepository.save(any())).thenReturn(entity);

		assertEquals("testfile.txt", fileRecordService.saveFileRecord(entity).getOriginalFileName());
	}
}
