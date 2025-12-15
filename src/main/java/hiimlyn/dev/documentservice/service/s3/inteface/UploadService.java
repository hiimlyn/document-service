package hiimlyn.dev.documentservice.service.s3.inteface;

public interface UploadService {
    void uploadFile(byte[] fileData, String fileName);

    String upLoadWithMetaData(byte[] fileData, String fileName, String contentType, java.util.Map<String, String> metadata);
//    String uploadBulkFiles(java.util.List<byte[]> filesData, java.util.List<String> fileNames);
}
