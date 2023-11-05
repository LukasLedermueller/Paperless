package at.fhtw.swkom.paperless.services.mapper;

import at.fhtw.swkom.paperless.entities.*;
import at.fhtw.swkom.paperless.repositories.*;
import at.fhtw.swkom.paperless.services.dto.DocumentDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import org.openapitools.jackson.nullable.JsonNullable;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

@Mapper
public abstract class DocumentMapper implements BaseMapper {
    static DocumentMapper INSTANCE = Mappers.getMapper(DocumentMapper.class);
    @Autowired
    private DocumentsCorrespondentRepository documentsCorrespondentRepository;
    @Autowired
    private DocumentsDocumenttypeRepository documentsDocumenttypeRepository;
    @Autowired
    private DocumentsStoragepathRepository documentsStoragepathRepository;
    @Autowired
    private DocumentsDocumentTagsRepository documentsDocumentTagsRepository;

    // ---------------------------------
    // ---------- entityToDto ----------
    // ---------------------------------
    @Mapping(target="archivedFileName", source="archiveFilename")
    @Mapping(target="originalFileName", source="originalFilename")
    @Mapping(target="correspondent", source="correspondent", qualifiedByName="correspondent")
    @Mapping(target="documentType", source="documentType", qualifiedByName="documentType")
    @Mapping(target="storagePath", source="storagePath", qualifiedByName="storagePath")
    @Mapping(target="tags", source="documentDocumentsDocumentTagses", qualifiedByName="tags")
    public abstract DocumentDto entityToDto(DocumentsDocument documentsDocument);
    @Named("correspondent")
    JsonNullable<Integer> mapDocumentsCorrespondentToJsonNullableInteger(DocumentsCorrespondent documentsCorrespondent) {
        return documentsCorrespondent != null ? JsonNullable.of(documentsCorrespondent.getId()) : JsonNullable.undefined();
    }
    @Named("documentType")
    JsonNullable<Integer> mapDocumentsDocumentTypeToJsonNullableInteger(DocumentsDocumenttype documentsDocumenttype) {
        return documentsDocumenttype != null ? JsonNullable.of(documentsDocumenttype.getId()) : JsonNullable.undefined();
    }
    @Named("storagePath")
    JsonNullable<Integer> mapDocumentsStoragePathToJsonNullableInteger(DocumentsStoragepath documentsStoragepath) {
        return documentsStoragepath != null ? JsonNullable.of(documentsStoragepath.getId()) : JsonNullable.undefined();
    }
    @Named("tags")
    JsonNullable<List<Integer>> mapDocumentsDocumentTagsSetToJsonNullableIntegerList(Set<DocumentsDocumentTags> documentsDocumentTags) {
        if(documentsDocumentTags == null) {
            return JsonNullable.undefined();
        }
        List<Integer> documentsDocumentTagsIds = new ArrayList<>();
        for(DocumentsDocumentTags documentsDocumentTag : documentsDocumentTags) {
            documentsDocumentTagsIds.add(documentsDocumentTag.getId());
        }
        return JsonNullable.of(documentsDocumentTagsIds);
    }

    // ---------------------------------
    // ---------- dtoToEntity ----------
    // ---------------------------------
    @Mapping(target="archiveFilename", source="archivedFileName")
    @Mapping(target="originalFilename", source="originalFileName")
    @Mapping(target="correspondent", source="correspondent", qualifiedByName="correspondent")
    @Mapping(target="documentType", source="documentType", qualifiedByName="documentType")
    @Mapping(target="storagePath", source="storagePath", qualifiedByName="storagePath")
    @Mapping(target="documentDocumentsDocumentTagses", source="tags", qualifiedByName="tags")
    public abstract DocumentsDocument dtoToEntity(DocumentDto documentDto);
    @Named("correspondent")
    DocumentsCorrespondent mapJsonNullableIntegerToDocumentsCorrespondent(JsonNullable<Integer> value) {
        return value == null ? null : documentsCorrespondentRepository.findById(value.get()).orElse(null);
    }
    @Named("documentType")
    DocumentsDocumenttype mapJsonNullableIntegerToDocumentsDocumenttype(JsonNullable<Integer> value) {
        return value == null ? null : documentsDocumenttypeRepository.findById(value.get()).orElse(null);
    }
    @Named("storagePath")
    DocumentsStoragepath mapJsonNullableIntegerToDocumentsStoragepath(JsonNullable<Integer> value) {
        return value == null ? null : documentsStoragepathRepository.findById(value.get()).orElse(null);
    }
    @Named("tags")
    Set<DocumentsDocumentTags> mapJsonNullableIntegerListToDocumentsDocumentTagsSet(JsonNullable<List<Integer>> values) {
        if(!values.isPresent()) {
            return null;
        }
        Set<DocumentsDocumentTags> documentsDocumentTagsSet = new HashSet<>();
        for(Integer id : values.get()) {
            Optional<DocumentsDocumentTags> documentsDocumentTag = documentsDocumentTagsRepository.findById(id);
            if(documentsDocumentTag.isPresent()) {
                documentsDocumentTagsSet.add(documentsDocumentTag.get());
            }
        }
        return documentsDocumentTagsSet;
    }
}
