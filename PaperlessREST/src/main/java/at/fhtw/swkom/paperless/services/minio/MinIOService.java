package at.fhtw.swkom.paperless.services.minio;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import io.minio.*;
import io.minio.errors.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;



@Slf4j
@Service
public class MinIOService {

    @Value("${minio.endpoint}")
    private String minioEndpoint;
    @Value("${minio.port}")
    private String port;
    @Value("${minio.accessKey}")
    private String accessKey;
    @Value("${minio.secretKey}")
    private String secretKey;
    @Value("${minio.bucketName}")
    private String bucketName;

    public MinIOService() {
    }

    public void uploadDocument(MultipartFile document) throws Exception {

        try {
            MinioClient minioClient = MinioClient.builder()
                    .endpoint(minioEndpoint + ":" + port)
                    .credentials(accessKey, secretKey)
                    .build();

            boolean found = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
            if (!found) {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
            }
            String documentName = document.getOriginalFilename();
            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(bucketName)
                            .object(documentName)
                            .stream(document.getInputStream(), document.getSize(), -1)
                            .contentType(document.getContentType())
                            .build()
            );
            log.info("Document uploaded: " + documentName);
        } catch (MinioException | IOException | InvalidKeyException | NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new MinioException(e.getMessage());
        }
    }

    public InputStream getFileInputStream(String fileName) throws Exception {
        try {
            MinioClient minioClient = MinioClient.builder()
                    .endpoint(minioEndpoint + ":" + port)
                    .credentials(accessKey, secretKey)
                    .build();

            GetObjectArgs getObjectArgs = GetObjectArgs.builder()
                    .bucket(bucketName)
                    .object(fileName)
                    .build();

            return minioClient.getObject(getObjectArgs);
        } catch (Exception e) {
            e.printStackTrace();
            throw new MinioException(e.getMessage());
        }
    }

    public void deleteDocumentByFilename(String fileName) throws Exception {
        try {
            MinioClient minioClient = MinioClient.builder()
                    .endpoint(minioEndpoint + ":" + port)
                    .credentials(accessKey, secretKey)
                    .build();
            minioClient.removeObject(
                    RemoveObjectArgs.builder().bucket(bucketName).object(fileName).build());
        } catch (Exception e) {
            e.printStackTrace();
            throw new MinioException(e.getMessage());
        }
    }
}