package at.fhtw.swkom.paperlessservices.services;

import at.fhtw.swkom.paperlessservices.exceptions.MinioException;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import io.minio.*;
import io.minio.errors.ErrorResponseException;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@Slf4j
@Service
public class MinIOService {

    String minioEndpoint = "http://MinIO:9000";
    String accessKey = "minioadmin";
    String secretKey = "minioadmin";
    String bucketName = "documents";

    public MultipartFile getDocumentFile(String documentName) throws MinioException {
        try {
            log.debug("try to get document with name " + documentName.toString());
            MinioClient minioClient = MinioClient.builder()
                    .endpoint(minioEndpoint)
                    .credentials(accessKey, secretKey)
                    .build();

            // Get metadata to retrieve content type
            StatObjectResponse statObjectResponse = minioClient.statObject(
                    StatObjectArgs.builder()
                            .bucket(bucketName)
                            .object(documentName.toString())
                            .build()
            );

            // Fetch the content type from the metadata
            String contentType = statObjectResponse.contentType();

            try (InputStream stream = minioClient.getObject(
                    GetObjectArgs.builder()
                            .bucket(bucketName)
                            .object(documentName.toString())
                            .build())) {

                // Create a MockMultipartFile object with the determined content type
                return new MockMultipartFile("file", documentName.toString(), contentType, stream);
            }
        } catch (ErrorResponseException e) {
            e.printStackTrace();
            throw new MinioException("Error response from MinIO: " + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            throw new MinioException("Error reading stream or fetching metadata from MinIO: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new MinioException("Error fetching document file from MinIO: " + e.getMessage());
        }
    }
}
