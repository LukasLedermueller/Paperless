package at.fhtw.swkom.paperless.services.mapper;

import at.fhtw.swkom.paperless.entities.*;
import at.fhtw.swkom.paperless.repositories.DocumentsCorrespondentRepository;
import at.fhtw.swkom.paperless.repositories.DocumentsDocumentTagsRepository;
import at.fhtw.swkom.paperless.repositories.DocumentsDocumenttypeRepository;
import at.fhtw.swkom.paperless.repositories.DocumentsStoragepathRepository;
import at.fhtw.swkom.paperless.services.dto.DocumentDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.openapitools.jackson.nullable.JsonNullable;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class DocumentMapperTest {
    @MockBean
    private DocumentsCorrespondentRepository documentsCorrespondentRepository;
    @MockBean
    private DocumentsDocumenttypeRepository documentsDocumenttypeRepository;
    @MockBean
    private DocumentsStoragepathRepository documentsStoragepathRepository;
    @MockBean
    private DocumentsDocumentTagsRepository documentsDocumentTagsRepository;
    @InjectMocks
    DocumentMapper documentMapper = DocumentMapper.INSTANCE;
    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void testEntityToDto() {
        Integer id = 1;
        Integer correspondentId = 2;
        Integer documentTypeId = 3;
        Integer storagePathId = 4;
        String title = "Title";
        String content = "Content";
        Integer documentsDocumentTag1Id = 100;
        Integer documentsDocumentTag2Id= 101;
        OffsetDateTime created = OffsetDateTime.of(LocalDate.of(2030, 1, 1), LocalTime.of(3, 0, 0), ZoneOffset.ofHours(1));
        OffsetDateTime modified = OffsetDateTime.of(LocalDate.of(2010, 1, 1), LocalTime.of(1, 0, 0), ZoneOffset.ofHours(1));
        OffsetDateTime added = OffsetDateTime.of(LocalDate.of(2000, 1, 1), LocalTime.of(0, 0, 0), ZoneOffset.ofHours(1));
        String archiveSerialNumber = "5";
        String originalFileName = "OriginalFileName";
        String archivedFileName = "ArchivedFileName";

        DocumentsCorrespondent documentsCorrespondent = new DocumentsCorrespondent();
        documentsCorrespondent.setId(correspondentId);

        DocumentsDocumenttype documentsDocumenttype = new DocumentsDocumenttype();
        documentsDocumenttype.setId(documentTypeId);

        DocumentsStoragepath documentsStoragepath = new DocumentsStoragepath();
        documentsStoragepath.setId(storagePathId);

        DocumentsDocumentTags documentsDocumentTag1 = new DocumentsDocumentTags();
        documentsDocumentTag1.setId(documentsDocumentTag1Id);

        DocumentsDocumentTags documentsDocumentTag2 = new DocumentsDocumentTags();
        documentsDocumentTag2.setId(documentsDocumentTag2Id);

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
        documentsDocument.setCorrespondent(documentsCorrespondent);
        documentsDocument.setDocumentType(documentsDocumenttype);
        documentsDocument.setStoragePath(documentsStoragepath);
        documentsDocument.setDocumentDocumentsDocumentTagses(Set.of(documentsDocumentTag1, documentsDocumentTag2));

        // TODO:
        // String checksum
        // String storageType
        // String filename
        // String mimeType
        // String archiveChecksum
        // AuthUser owner
        // Set<DocumentsNote> documentDocumentsNotes

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
        expectedDocumentDto.setCorrespondent(JsonNullable.of(correspondentId));
        expectedDocumentDto.setDocumentType(JsonNullable.of(documentTypeId));
        expectedDocumentDto.setStoragePath(JsonNullable.of(storagePathId));
        expectedDocumentDto.setTags(JsonNullable.of(List.of(documentsDocumentTag1Id, documentsDocumentTag2Id)));

        DocumentDto documentDto = documentMapper.entityToDto(documentsDocument);

        System.out.println("Expected -----------------");
        System.out.println(expectedDocumentDto);

        System.out.println("Actual -----------------");
        System.out.println(documentDto);

        // NOTE: compare parameters individually instead of comparing expectedDocumentDto to documentDto to prevent race condition errors
        // comparing expectedDocumentDto to documentDto can result in unsorted documentTag lists and may fail

        assertEquals(expectedDocumentDto.getId(), documentDto.getId());
        assertEquals(expectedDocumentDto.getTitle(), documentDto.getTitle());
        assertEquals(expectedDocumentDto.getContent(), documentDto.getContent());
        assertEquals(expectedDocumentDto.getCreated(), documentDto.getCreated());
        assertEquals(expectedDocumentDto.getModified(), documentDto.getModified());
        assertEquals(expectedDocumentDto.getAdded(), documentDto.getAdded());
        assertEquals(expectedDocumentDto.getArchiveSerialNumber(), documentDto.getArchiveSerialNumber());
        assertEquals(expectedDocumentDto.getArchivedFileName(), documentDto.getArchivedFileName());
        assertEquals(expectedDocumentDto.getOriginalFileName(), documentDto.getOriginalFileName());
        assertEquals(expectedDocumentDto.getCorrespondent(), documentDto.getCorrespondent());
        assertEquals(expectedDocumentDto.getDocumentType(), documentDto.getDocumentType());
        assertEquals(expectedDocumentDto.getStoragePath(), documentDto.getStoragePath());
        assertEquals(expectedDocumentDto.getTags().get().size(), documentDto.getTags().get().size());
        assertTrue(expectedDocumentDto.getTags().get().containsAll(documentDto.getTags().get()));
        assertTrue(documentDto.getTags().get().containsAll(expectedDocumentDto.getTags().get()));
    }

    @Test
    void testDtoToEntity() {
        Integer id = 1;
        Integer correspondentId = 2;
        Integer documentTypeId = 3;
        Integer storagePathId = 4;
        Integer documentsDocumentTag1Id = 100;
        Integer documentsDocumentTag2Id= 101;
        String title = "Title";
        String content = "Content";
        OffsetDateTime created = OffsetDateTime.of(LocalDate.of(2030, 1, 1), LocalTime.of(3, 0, 0), ZoneOffset.ofHours(1));
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
        documentDto.setCorrespondent(JsonNullable.of(correspondentId));
        documentDto.setDocumentType(JsonNullable.of(documentTypeId));
        documentDto.storagePath(storagePathId);
        documentDto.setTags(JsonNullable.of(List.of(documentsDocumentTag1Id, documentsDocumentTag2Id)));

        DocumentsCorrespondent documentsCorrespondent = new DocumentsCorrespondent();
        documentsCorrespondent.setId(correspondentId);

        DocumentsDocumenttype documentsDocumenttype = new DocumentsDocumenttype();
        documentsDocumenttype.setId(documentTypeId);

        DocumentsStoragepath documentsStoragepath = new DocumentsStoragepath();
        documentsStoragepath.setId(storagePathId);

        DocumentsDocumentTags documentsDocumentTag1 = new DocumentsDocumentTags();
        documentsDocumentTag1.setId(documentsDocumentTag1Id);

        DocumentsDocumentTags documentsDocumentTag2 = new DocumentsDocumentTags();
        documentsDocumentTag2.setId(documentsDocumentTag2Id);

        Mockito.when(documentsCorrespondentRepository.findById(2)).thenReturn(java.util.Optional.of(documentsCorrespondent));
        Mockito.when(documentsDocumenttypeRepository.findById(3)).thenReturn(java.util.Optional.of(documentsDocumenttype));
        Mockito.when(documentsStoragepathRepository.findById(4)).thenReturn(java.util.Optional.of(documentsStoragepath));
        Mockito.when(documentsDocumentTagsRepository.findById(100)).thenReturn(java.util.Optional.of(documentsDocumentTag1));
        Mockito.when(documentsDocumentTagsRepository.findById(101)).thenReturn(java.util.Optional.of(documentsDocumentTag2));

        DocumentsDocument expectedDocumentsDocument = new DocumentsDocument();
        expectedDocumentsDocument.setId(id);
        expectedDocumentsDocument.setTitle(title);
        expectedDocumentsDocument.setContent(content);
        expectedDocumentsDocument.setCreated(created);
        expectedDocumentsDocument.setModified(modified);
        expectedDocumentsDocument.setAdded(added);
        expectedDocumentsDocument.setArchiveSerialNumber(Integer.valueOf(archiveSerialNumber));
        expectedDocumentsDocument.setArchiveFilename(archivedFileName);
        expectedDocumentsDocument.setOriginalFilename(originalFileName);
        expectedDocumentsDocument.setCorrespondent(documentsCorrespondent);
        expectedDocumentsDocument.setDocumentType(documentsDocumenttype);
        expectedDocumentsDocument.setStoragePath(documentsStoragepath);
        expectedDocumentsDocument.setDocumentDocumentsDocumentTagses(Set.of(documentsDocumentTag1, documentsDocumentTag2));

        // TODO:
        // String checksum
        // String storageType
        // String filename
        // String mimeType
        // String archiveChecksum
        // AuthUser owner
        // Set<DocumentsNote> documentDocumentsNotes

        DocumentsDocument documentsDocument = documentMapper.dtoToEntity(documentDto);

        System.out.println("Expected -----------------");
        System.out.println(expectedDocumentsDocument);

        System.out.println("Actual -----------------");
        System.out.println(documentsDocument);

        // NOTE: compare parameters individually instead of comparing expectedDocumentsDocument to documentsDocument to prevent race condition errors
        // comparing expectedDocumentDto to documentsDocument can result in unsorted documentTag lists and thus may fail

        assertEquals(expectedDocumentsDocument.getId(), documentsDocument.getId());
        assertEquals(expectedDocumentsDocument.getTitle(), documentsDocument.getTitle());
        assertEquals(expectedDocumentsDocument.getContent(), documentsDocument.getContent());
        assertEquals(expectedDocumentsDocument.getCreated(), documentsDocument.getCreated());
        assertEquals(expectedDocumentsDocument.getModified(), documentsDocument.getModified());
        assertEquals(expectedDocumentsDocument.getAdded(), documentsDocument.getAdded());
        assertEquals(expectedDocumentsDocument.getArchiveSerialNumber(), documentsDocument.getArchiveSerialNumber());
        assertEquals(expectedDocumentsDocument.getArchiveFilename(), documentsDocument.getArchiveFilename());
        assertEquals(expectedDocumentsDocument.getOriginalFilename(), documentsDocument.getOriginalFilename());
        assertEquals(expectedDocumentsDocument.getCorrespondent(), documentsDocument.getCorrespondent());
        assertEquals(expectedDocumentsDocument.getDocumentType(), documentsDocument.getDocumentType());
        assertEquals(expectedDocumentsDocument.getStoragePath(), documentsDocument.getStoragePath());
        assertEquals(expectedDocumentsDocument.getDocumentDocumentsDocumentTagses().size(), documentsDocument.getDocumentDocumentsDocumentTagses().size());
        assertTrue(expectedDocumentsDocument.getDocumentDocumentsDocumentTagses().containsAll(documentsDocument.getDocumentDocumentsDocumentTagses()));
        assertTrue(documentsDocument.getDocumentDocumentsDocumentTagses().containsAll(expectedDocumentsDocument.getDocumentDocumentsDocumentTagses()));
    }
}
