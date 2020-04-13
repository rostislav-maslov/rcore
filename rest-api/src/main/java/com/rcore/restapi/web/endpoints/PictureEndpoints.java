package com.rcore.restapi.web.endpoints;

import com.rcore.adapter.domain.picture.PictureAllAdapter;
import com.rcore.adapter.domain.picture.dto.PictureDTO;
import com.rcore.domain.picture.exception.PictureAccessException;
import com.rcore.domain.picture.exception.PictureNotFoundException;
import com.rcore.restapi.infrastructure.picture.PictureSender;
import com.rcore.restapi.web.api.response.SuccessApiResponse;
import com.rcore.restapi.web.endpoints.routes.PictureRoutes;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
@RestController
public class PictureEndpoints {

    private final PictureSender pictureSender;
    private final PictureAllAdapter pictureAllAdapter;

    @GetMapping(PictureRoutes.BY_ID)
    public void getPicture(
            @PathVariable String id,
            @RequestParam(required = false) Integer width,
            HttpServletResponse response
    ) throws PictureNotFoundException, PictureAccessException, IOException {
        pictureSender.send(id, width, response);
    }

    @PostMapping(PictureRoutes.UPLOAD)
    public SuccessApiResponse<String> upload(@RequestParam MultipartFile file) throws IOException {

        PictureDTO picture = pictureAllAdapter.create(file.getInputStream(), file.getOriginalFilename(), file.getContentType());
        return SuccessApiResponse.of(picture.getId());
    }
}
