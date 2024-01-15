package at.fhtw.swkom.paperlessservices.repositories;

import at.fhtw.swkom.paperlessservices.exceptions.DocumentNotFoundException;
import at.fhtw.swkom.paperlessservices.services.dto.Document;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.openapitools.jackson.nullable.JsonNullable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@Component
public class DocumentRepository {
    /*@Value("${db.url}")
    private String url;

    @Value("${db.user}")
    private String user;

    @Value("${db.password}")
    private String password;*/
    String url="jdbc:postgresql://paperless-persistence:5432/paperless";
    String user="postgres";
    String password="postgres";

    public DocumentRepository() {}
    public int updateDocumentContentById(String content, String fileName, Integer id) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String updateQuery = "UPDATE documents_document SET content = ?, archive_filename = ? WHERE id = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
                preparedStatement.setString(1, content);
                preparedStatement.setString(2, fileName);
                preparedStatement.setInt(3, id);

                int affectedRows = preparedStatement.executeUpdate();
                return affectedRows;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }
    public Document getDocumentById(Integer id) throws Exception {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String selectQuery = "SELECT * FROM documents_document WHERE id = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
                preparedStatement.setInt(1, id);

                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    Document document = new Document();
                    document.setId(resultSet.getInt("id"));
                    document.setOriginalFileName(JsonNullable.of(resultSet.getString("original_filename")));
                    document.setContent(JsonNullable.of(resultSet.getString("content")));
                    document.setTitle(JsonNullable.of(resultSet.getString("title")));
                    return document;
                } else {
                    throw new DocumentNotFoundException("Document with id " + id + " not found");
                }
            }
        } catch (DocumentNotFoundException e) {
            e.printStackTrace();
            throw new EntityNotFoundException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
    }
}
