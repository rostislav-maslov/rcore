package com.rcore.restapi.web.endpoints;

import com.rcore.adapter.domain.picture.PictureAdapter;
import com.rcore.adapter.domain.picture.dto.PictureDTO;
import com.rcore.commons.utils.DomainUtils;
import com.rcore.domain.picture.entity.PictureEntity;
import com.rcore.domain.picture.exception.PictureAccessException;
import com.rcore.domain.picture.exception.PictureNotFoundException;
import com.rcore.domain.token.exception.AuthenticationException;
import com.rcore.domain.token.exception.AuthorizationException;
import com.rcore.restapi.exceptions.BadRequestApiException;
import com.rcore.restapi.infrastructure.picture.PictureSender;
import com.rcore.restapi.web.api.response.OkApiResponse;
import com.rcore.restapi.web.api.response.SuccessApiResponse;
import com.rcore.restapi.web.endpoints.api.UploadFileDTO;
import com.rcore.restapi.web.endpoints.routes.PictureRoutes;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;

@RequiredArgsConstructor
@RestController
public class PictureEndpoints {

    private final PictureSender pictureSender;
    private final PictureAdapter pictureAdapter;

    @GetMapping(PictureRoutes.NOT_SECURE_BY_ID)
    public void getPicture(
            @PathVariable String id,
            @RequestParam(required = false) Integer width,
            HttpServletResponse response
    ) throws PictureNotFoundException, PictureAccessException, IOException {
        pictureSender.send(id, width, response);
    }

    @PostMapping(value = PictureRoutes.UPLOAD, produces = MediaType.APPLICATION_JSON_VALUE)
    public SuccessApiResponse<String> upload(@RequestBody UploadFileDTO request) throws AuthenticationException, AuthorizationException {

        PictureDTO picture = pictureAdapter.getAll().create(new ByteArrayInputStream(request.getData()), request.getFileName(), request.getContentType());
        return SuccessApiResponse.of(picture.getId());
    }

    @DeleteMapping(PictureRoutes.BY_ID)
    public OkApiResponse deleteById(@PathVariable String id) throws AuthenticationException, AuthorizationException, BadRequestApiException {
        try {
            pictureAdapter.getAll().deleteById(id);
        } catch (PictureNotFoundException e) {
            throw new BadRequestApiException("Изображения", "Неверный идентификатор файла", DomainUtils.getDomain(PictureEntity.class), "NOT_FOUND");
        }
        return OkApiResponse.of();
    }
}
