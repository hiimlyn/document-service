package hiimlyn.dev.documentservice.service;

import hiimlyn.dev.documentservice.entity.FileRecordEntity;
import hiimlyn.dev.documentservice.repository.FileRecordRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FileRecordService {
	private final FileRecordRepository fileRecordRepository;

	public FileRecordEntity saveFileRecord(FileRecordEntity fileRecord) {
		return fileRecordRepository.save(fileRecord);
	}
}
