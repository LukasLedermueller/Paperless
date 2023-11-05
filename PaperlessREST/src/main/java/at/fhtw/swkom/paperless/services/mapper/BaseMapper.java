package at.fhtw.swkom.paperless.services.mapper;

import org.mapstruct.Mapper;
import org.openapitools.jackson.nullable.JsonNullable;

@Mapper
public interface BaseMapper {
    default <T> JsonNullable<T> map(T entity) {
        return JsonNullable.of(entity);
    }
    default <T> T map(JsonNullable<T> jsonNullable) {
        return jsonNullable == null ? null : jsonNullable.orElse(null);
    }
}