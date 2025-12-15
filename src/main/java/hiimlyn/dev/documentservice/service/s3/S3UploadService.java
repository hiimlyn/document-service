package hiimlyn.dev.documentservice.service.s3;

import hiimlyn.dev.documentservice.config.R2Props;
import hiimlyn.dev.documentservice.service.s3.inteface.UploadService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.util.Map;

@Slf4j
@Service
@AllArgsConstructor
public class S3UploadService implements UploadService {

    private final S3Client s3Client;
    private final R2Props props;


    @Override
    public void uploadFile(byte[] fileData, String fileName) {
        // Use r2Client here
        var req = PutObjectRequest.builder()
                .bucket(props.getBucket())
                .key(fileName)
                .build();

        s3Client.putObject(req, RequestBody.fromBytes(fileData));

    }

    @Override
    public String upLoadWithMetaData(byte[] fileData, String fileName, String contentType, Map<String, String> metadata) {
        return "";
    }
}
