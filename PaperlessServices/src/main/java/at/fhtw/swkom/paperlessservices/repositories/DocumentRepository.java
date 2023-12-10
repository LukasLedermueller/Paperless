package at.fhtw.swkom.paperlessservices.repositories;

import lombok.extern.slf4j.Slf4j;
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
    public int updateDocumentContentByFilename(String content, String filename) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String updateQuery = "UPDATE documents_document SET content = ? WHERE filename = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
                preparedStatement.setString(1, content);
                preparedStatement.setString(2, filename);

                int affectedRows = preparedStatement.executeUpdate();
                return affectedRows;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }
}
