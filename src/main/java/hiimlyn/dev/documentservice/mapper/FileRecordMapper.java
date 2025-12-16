package hiimlyn.dev.documentservice.mapper;

import hiimlyn.dev.documentservice.dto.FileRecord;
import hiimlyn.dev.documentservice.entity.FileRecordEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring" )
public interface FileRecordMapper {

    FileRecordEntity toEntity(FileRecord dto);

    FileRecord toDto(FileRecordEntity entity);
}
