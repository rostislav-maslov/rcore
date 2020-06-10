package com.rcore.restapi.web.endpoints;

import com.rcore.adapter.domain.file.FileAdapter;
import com.rcore.adapter.domain.file.dto.FileDTO;
import com.rcore.commons.utils.DomainUtils;
import com.rcore.domain.file.entity.FileEntity;
import com.rcore.domain.file.exception.FileAccessException;
import com.rcore.domain.file.exception.FileNotFoundException;
import com.rcore.domain.token.exception.AuthenticationException;
import com.rcore.domain.token.exception.AuthorizationException;
import com.rcore.restapi.exceptions.BadRequestApiException;
import com.rcore.restapi.exceptions.NotFoundApiException;
import com.rcore.restapi.infrastructure.file.FileSender;

import com.rcore.restapi.web.api.response.OkApiResponse;
import com.rcore.restapi.web.api.response.SuccessApiResponse;
import com.rcore.restapi.web.endpoints.api.UploadFileDTO;
import com.rcore.restapi.web.endpoints.routes.FileRoutes;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;

@RequiredArgsConstructor
@RestController
public class FileEndpoints {

    private final FileSender fileSender;
    private final FileAdapter fileAdapter;

    @GetMapping(FileRoutes.BY_ID)
    public void getFile(@PathVariable String id,
                        HttpServletResponse response) throws IOException, FileNotFoundException, FileAccessException {
        fileSender.send(id, response);
    }

    @PostMapping(FileRoutes.UPLOAD)
    public SuccessApiResponse<String> upload(@RequestBody UploadFileDTO file) throws IOException {
        return SuccessApiResponse.of(
                fileAdapter.getAll().create(new ByteArrayInputStream(file.getData()), file.getFileName(), file.getContentType()).getId()
        );
    }

    @DeleteMapping(FileRoutes.BY_ID)
    public OkApiResponse deleteById(@PathVariable String id) throws AuthenticationException, AuthorizationException, FileAccessException, BadRequestApiException {

        try {
            fileAdapter.getAll().deleteById(id);
        } catch (FileNotFoundException e) {
            throw  new BadRequestApiException("Файлы", "Неверный идентификатор файла", DomainUtils.getDomain(FileEntity.class), "NOT_FOUND");
        }
        return OkApiResponse.of();
    }



}
