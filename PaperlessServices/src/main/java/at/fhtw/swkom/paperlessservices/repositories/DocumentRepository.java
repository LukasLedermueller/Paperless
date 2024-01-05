package at.fhtw.swkom.paperlessservices.repositories;

import at.fhtw.swkom.paperlessservices.services.dto.Document;
import lombok.extern.slf4j.Slf4j;
import org.openapitools.jackson.nullable.JsonNullable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.*;

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
    public int updateDocumentContentById(String content, Integer id) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String updateQuery = "UPDATE documents_document SET content = ? WHERE id = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
                preparedStatement.setString(1, content);
                preparedStatement.setInt(2, id);

                int affectedRows = preparedStatement.executeUpdate();
                return affectedRows;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }
    public Document getDocumentById(Integer id) {
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
                    document.setTitle(JsonNullable.of(resultSet.getString("filename")));
                    System.out.println("ORIGINAL FILENAME: " + resultSet.getString("filename"));
                    return document;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
