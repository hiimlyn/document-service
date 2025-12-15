package hiimlyn.dev.documentservice.repository;

import hiimlyn.dev.documentservice.entity.FileRecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRecordRepository extends JpaRepository<FileRecordEntity, String> {
}
