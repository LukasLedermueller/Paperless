package at.fhtw.swkom.paperless.controller;

import at.fhtw.swkom.paperless.services.dto.AckTasks200Response;
import at.fhtw.swkom.paperless.services.dto.AckTasksRequest;
import at.fhtw.swkom.paperless.services.dto.BulkEditRequest;
import at.fhtw.swkom.paperless.services.dto.CreateCorrespondentRequest;
import at.fhtw.swkom.paperless.services.dto.CreateDocumentType200Response;
import at.fhtw.swkom.paperless.services.dto.CreateGroupRequest;
import at.fhtw.swkom.paperless.services.dto.CreateSavedViewsRequest;
import at.fhtw.swkom.paperless.services.dto.CreateStoragePath200Response;
import at.fhtw.swkom.paperless.services.dto.CreateStoragePathRequest;
import at.fhtw.swkom.paperless.services.dto.CreateTag200Response;
import at.fhtw.swkom.paperless.services.dto.CreateTagRequest;
import at.fhtw.swkom.paperless.services.dto.CreateUISettings200Response;
import at.fhtw.swkom.paperless.services.dto.CreateUISettingsRequest;
import at.fhtw.swkom.paperless.services.dto.CreateUserRequest;
import org.springframework.format.annotation.DateTimeFormat;
import at.fhtw.swkom.paperless.services.dto.GetCorrespondents200Response;
import at.fhtw.swkom.paperless.services.dto.GetDocument200Response;
import at.fhtw.swkom.paperless.services.dto.GetDocumentMetadata200Response;
import at.fhtw.swkom.paperless.services.dto.GetDocumentSuggestions200Response;
import at.fhtw.swkom.paperless.services.dto.GetDocumentTypes200Response;
import at.fhtw.swkom.paperless.services.dto.GetDocuments200Response;
import at.fhtw.swkom.paperless.services.dto.GetGroups200Response;
import at.fhtw.swkom.paperless.services.dto.GetSavedViews200Response;
import at.fhtw.swkom.paperless.services.dto.GetStoragePaths200Response;
import at.fhtw.swkom.paperless.services.dto.GetTags200Response;
import at.fhtw.swkom.paperless.services.dto.GetTasks200ResponseInner;
import at.fhtw.swkom.paperless.services.dto.GetUISettings200Response;
import at.fhtw.swkom.paperless.services.dto.GetUsers200Response;
import at.fhtw.swkom.paperless.services.dto.GetUsers200ResponseResultsInner;
import java.time.OffsetDateTime;
import at.fhtw.swkom.paperless.services.dto.SelectionData200Response;
import at.fhtw.swkom.paperless.services.dto.SelectionDataRequest;
import at.fhtw.swkom.paperless.services.dto.Statistics200Response;
import at.fhtw.swkom.paperless.services.dto.UpdateCorrespondent200Response;
import at.fhtw.swkom.paperless.services.dto.UpdateCorrespondentRequest;
import at.fhtw.swkom.paperless.services.dto.UpdateDocument200Response;
import at.fhtw.swkom.paperless.services.dto.UpdateDocumentRequest;
import at.fhtw.swkom.paperless.services.dto.UpdateDocumentType200Response;
import at.fhtw.swkom.paperless.services.dto.UpdateDocumentTypeRequest;
import at.fhtw.swkom.paperless.services.dto.UpdateGroup200Response;
import at.fhtw.swkom.paperless.services.dto.UpdateGroupRequest;
import at.fhtw.swkom.paperless.services.dto.UpdateStoragePath200Response;
import at.fhtw.swkom.paperless.services.dto.UpdateStoragePathRequest;
import at.fhtw.swkom.paperless.services.dto.UpdateTag200Response;
import at.fhtw.swkom.paperless.services.dto.UpdateTagRequest;
import at.fhtw.swkom.paperless.services.dto.UpdateUserRequest;
import at.fhtw.swkom.paperless.services.dto.UserInfo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.context.request.NativeWebRequest;

import jakarta.validation.constraints.*;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import jakarta.annotation.Generated;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-09-25T10:10:40.571770Z[Etc/UTC]")
@Controller
@RequestMapping("${openapi.paperlessRestServer.base-path:}")
@CrossOrigin
public class ApiApiController implements ApiApi {

    private final NativeWebRequest request;

    @Autowired
    public ApiApiController(NativeWebRequest request) {
        this.request = request;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

}
