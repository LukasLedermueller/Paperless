package at.fhtw.swkom.paperless.services.mapper;

import at.fhtw.swkom.paperless.models.*;
import at.fhtw.swkom.paperless.services.dto.DocumentDto;
import io.swagger.v3.core.util.Json;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import org.junit.jupiter.api.Test;
import org.openapitools.jackson.nullable.JsonNullable;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.*;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DocumentMapperTest {
    @Test
    void testEntityToDto() {
        Integer id = 1;
        Integer correspondent = 2;
        Integer documentType = 3;
        Integer storagePath = 4;
        String title = "Title";
        String content = "Content";
        List<Integer> tags = List.of(100, 101);
        OffsetDateTime created = OffsetDateTime.of(LocalDate.of(2030, 1, 1), LocalTime.of(3, 0, 0), ZoneOffset.ofHours(1));
        OffsetDateTime createdDate = OffsetDateTime.of(LocalDate.of(2020, 1, 1), LocalTime.of(2, 0, 0), ZoneOffset.ofHours(1));
        OffsetDateTime modified = OffsetDateTime.of(LocalDate.of(2010, 1, 1), LocalTime.of(1, 0, 0), ZoneOffset.ofHours(1));
        OffsetDateTime added = OffsetDateTime.of(LocalDate.of(2000, 1, 1), LocalTime.of(0, 0, 0), ZoneOffset.ofHours(1));
        String archiveSerialNumber = "5";
        String originalFileName = "OriginalFileName";
        String archivedFileName = "ArchivedFileName";

        DocumentsDocument documentsDocument = new DocumentsDocument();
        documentsDocument.setId(id);
        documentsDocument.setTitle(title);
        documentsDocument.setContent(content);
        documentsDocument.setCreated(created);
        documentsDocument.setModified(modified);
        documentsDocument.setAdded(added);
        documentsDocument.setArchiveSerialNumber(Integer.valueOf(archiveSerialNumber));
        documentsDocument.setArchiveFilename(archivedFileName);
        documentsDocument.setOriginalFilename(originalFileName);

        // TODO:
        /*
        String checksum
        String storageType
        String filename
        String mimeType
        String archiveChecksum
        DocumentsCorrespondent correspondent
        DocumentsDocumenttype documentType
        DocumentsStoragepath storagePath
        AuthUser owner
        Set<DocumentsNote> documentDocumentsNotes
        Set<DocumentsDocumentTags> documentDocumentsDocumentTagses
        */

        DocumentDto expectedDocumentDto = new DocumentDto();
        expectedDocumentDto.setId(id);
        expectedDocumentDto.setTitle(JsonNullable.of(title));
        expectedDocumentDto.setContent(JsonNullable.of(content));
        expectedDocumentDto.setCreated(created);
        expectedDocumentDto.setModified(modified);
        expectedDocumentDto.setAdded(added);
        expectedDocumentDto.setArchiveSerialNumber(JsonNullable.of(archiveSerialNumber));
        expectedDocumentDto.setArchivedFileName(JsonNullable.of(archivedFileName));
        expectedDocumentDto.setOriginalFileName(JsonNullable.of(originalFileName));

        // TODO:
        /*
        expectedDocumentDto.setCorrespondent(JsonNullable.of(correspondent));
        expectedDocumentDto.setDocumentType(JsonNullable.of(documentType));
        expectedDocumentDto.setStoragePath(JsonNullable.of(storagePath));
        expectedDocumentDto.setTags(JsonNullable.of(tags));
        expectedDocumentDto.setCreatedDate(createdDate);
         */

        DocumentMapperImpl documentMapperImpl = new DocumentMapperImpl();
        DocumentDto documentDto = documentMapperImpl.entityToDto(documentsDocument);

        System.out.println("Expected -----------------");
        System.out.println(expectedDocumentDto);

        System.out.println("Actual -----------------");
        System.out.println(documentDto);

        assertEquals(expectedDocumentDto, documentDto);
    }

    @Test
    void testDtoToEntity() {
        Integer id = 1;
        Integer correspondent = 2;
        Integer documentType = 3;
        Integer storagePath = 4;
        String title = "Title";
        String content = "Content";
        List<Integer> tags = List.of(100, 101);
        OffsetDateTime created = OffsetDateTime.of(LocalDate.of(2030, 1, 1), LocalTime.of(3, 0, 0), ZoneOffset.ofHours(1));
        OffsetDateTime createdDate = OffsetDateTime.of(LocalDate.of(2020, 1, 1), LocalTime.of(2, 0, 0), ZoneOffset.ofHours(1));
        OffsetDateTime modified = OffsetDateTime.of(LocalDate.of(2010, 1, 1), LocalTime.of(1, 0, 0), ZoneOffset.ofHours(1));
        OffsetDateTime added = OffsetDateTime.of(LocalDate.of(2000, 1, 1), LocalTime.of(0, 0, 0), ZoneOffset.ofHours(1));
        String archiveSerialNumber = "5";
        String originalFileName = "OriginalFileName";
        String archivedFileName = "ArchivedFileName";

        DocumentDto documentDto = new DocumentDto();
        documentDto.setId(id);
        documentDto.setTitle(JsonNullable.of(title));
        documentDto.setContent(JsonNullable.of(content));
        documentDto.setCreated(created);
        documentDto.setModified(modified);
        documentDto.setAdded(added);
        documentDto.setArchiveSerialNumber(JsonNullable.of(archiveSerialNumber));
        documentDto.setArchivedFileName(JsonNullable.of(archivedFileName));
        documentDto.setOriginalFileName(JsonNullable.of(originalFileName));

        // TODO:
        /*
        documentDto.setCorrespondent(JsonNullable.of(correspondent));
        documentDto.setDocumentType(JsonNullable.of(documentType));
        documentDto.setStoragePath(JsonNullable.of(storagePath));
        documentDto.setTags(JsonNullable.of(tags));
        documentDto.setCreatedDate(createdDate);
         */

        DocumentsDocument expextedDocumentsDocument = new DocumentsDocument();
        expextedDocumentsDocument.setId(id);
        expextedDocumentsDocument.setTitle(title);
        expextedDocumentsDocument.setContent(content);
        expextedDocumentsDocument.setCreated(created);
        expextedDocumentsDocument.setModified(modified);
        expextedDocumentsDocument.setAdded(added);
        expextedDocumentsDocument.setArchiveSerialNumber(Integer.valueOf(archiveSerialNumber));
        expextedDocumentsDocument.setArchiveFilename(archivedFileName);
        expextedDocumentsDocument.setOriginalFilename(originalFileName);

        // TODO:
        /*
        String checksum
        String storageType
        String filename
        String mimeType
        String archiveChecksum
        DocumentsCorrespondent correspondent
        DocumentsDocumenttype documentType
        DocumentsStoragepath storagePath
        AuthUser owner
        Set<DocumentsNote> documentDocumentsNotes
        Set<DocumentsDocumentTags> documentDocumentsDocumentTagses
        */

        DocumentMapperImpl documentMapperImpl = new DocumentMapperImpl();
        DocumentsDocument documentsDocument = documentMapperImpl.dtoToEntity(documentDto);

        System.out.println("Expected -----------------");
        System.out.println(expextedDocumentsDocument);

        System.out.println("Actual -----------------");
        System.out.println(documentsDocument);

        // assertEquals(expextedDocumentsDocument, documentsDocument);
    }
}