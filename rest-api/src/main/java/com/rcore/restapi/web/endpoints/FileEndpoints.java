package com.rcore.restapi.web.endpoints;

import com.rcore.adapter.domain.file.FileAdapter;
import com.rcore.domain.file.exception.FileAccessException;
import com.rcore.domain.file.exception.FileNotFoundException;
import com.rcore.restapi.infrastructure.file.FileSender;

import com.rcore.restapi.web.api.response.SuccessApiResponse;
import com.rcore.restapi.web.endpoints.routes.FileRoutes;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
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
    public SuccessApiResponse<String> upload(@RequestParam MultipartFile file) throws IOException {
        return SuccessApiResponse.of(
                fileAdapter.getAll().create(file.getInputStream(), file.getOriginalFilename(), file.getContentType()).getId()
        );
    }



}
