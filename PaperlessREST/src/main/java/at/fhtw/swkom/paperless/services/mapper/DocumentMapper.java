package at.fhtw.swkom.paperless.services.mapper;

import at.fhtw.swkom.paperless.models.DocumentsCorrespondent;
import at.fhtw.swkom.paperless.models.DocumentsDocument;
import at.fhtw.swkom.paperless.models.DocumentsDocumenttype;
import at.fhtw.swkom.paperless.models.DocumentsStoragepath;
import at.fhtw.swkom.paperless.services.dto.DocumentDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.openapitools.jackson.nullable.JsonNullable;

@Mapper
public interface DocumentMapper extends JsonNullableMapper {
    @Mapping(target="archivedFileName", source="archiveFilename")
    @Mapping(target="originalFileName", source="originalFilename")
    DocumentDto entityToDto(DocumentsDocument documentsDocument);
    DocumentsDocument dtoToEntity(DocumentDto documentDto);
    default JsonNullable<Integer> map(DocumentsCorrespondent documentsCorrespondent) {
        return documentsCorrespondent!=null ? JsonNullable.of(documentsCorrespondent.getId()) : JsonNullable.undefined();
    }
    default JsonNullable<Integer> map(DocumentsDocumenttype documentsDocumenttype) {
        return documentsDocumenttype!=null ? JsonNullable.of(documentsDocumenttype.getId()) : JsonNullable.undefined();
    }
    default JsonNullable<Integer> map(DocumentsStoragepath documentsStoragepath) {
        return documentsStoragepath!=null ? JsonNullable.of(documentsStoragepath.getId()) : JsonNullable.undefined();
    }
}
