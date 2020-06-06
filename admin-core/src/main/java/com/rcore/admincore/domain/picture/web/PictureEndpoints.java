package com.rcore.admincore.domain.picture.web;

import com.rcore.adapter.domain.picture.PictureAdapter;
import com.rcore.adapter.domain.picture.dto.PictureDTO;
import com.rcore.adapter.domain.token.TokenAdapter;
import com.rcore.adapter.domain.user.dto.UserDTO;
import com.rcore.admincore.domain.picture.application.PictureWebMapper;
import com.rcore.admincore.domain.picture.web.api.PictureWeb;
import com.rcore.domain.base.port.SearchResult;
import com.rcore.domain.token.exception.AuthenticationException;
import com.rcore.domain.token.exception.AuthorizationException;
import com.rcore.restapi.exceptions.NotFoundApiException;
import com.rcore.restapi.web.api.request.SearchApiRequest;
import com.rcore.restapi.web.api.response.OkApiResponse;
import com.rcore.restapi.web.api.response.SearchApiResponse;
import com.rcore.restapi.web.api.response.SuccessApiResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Api(tags = "Picture API")
@RestController("pictureAdminEndpoints")
@RequiredArgsConstructor
public class PictureEndpoints {

    private final PictureAdapter pictureAdapter;
    private final TokenAdapter tokenAdapter;
    private final PictureWebMapper pictureWebMapper;

    @ApiOperation("Список изображения")
    @GetMapping(value = Routes.ROOT, produces = MediaType.APPLICATION_JSON_VALUE)
    public SuccessApiResponse<SearchApiResponse<PictureWeb>> all(@ModelAttribute SearchApiRequest request) throws AuthenticationException, AuthorizationException {
        SearchResult<PictureDTO> result = pictureAdapter.getAdmin()
                .find(request.toSearchRequest());
        return SuccessApiResponse.of(
                SearchApiResponse.withItemsAndCount(
                        pictureWebMapper.mapAll(result.getItems()),
                        result.getCount()
                ));
    }

    @ApiOperation("Информация об изображении")
    @GetMapping(value = Routes.BY_ID, produces = MediaType.APPLICATION_JSON_VALUE)
    public SuccessApiResponse<PictureWeb> get(@PathVariable String id) throws AuthenticationException, AuthorizationException, NotFoundApiException {
        return SuccessApiResponse.of(pictureAdapter.getAdmin()
                .findById(id)
                .map(pictureWebMapper::map)
                .orElseThrow(() -> new NotFoundApiException("Неверный идентификатор изображения", "PICTURE", "NOT_FOUND")));
    }

    @ApiOperation("Удаление изображения")
    @DeleteMapping(value = Routes.BY_ID, produces = MediaType.APPLICATION_JSON_VALUE)
    public OkApiResponse delete(@PathVariable String id) throws AuthenticationException, AuthorizationException, NotFoundApiException {
        PictureDTO picture = pictureAdapter.getAdmin()
                .findById(id)
                .orElseThrow(() -> new NotFoundApiException("Неверный идентификатор изображения", "PICTURE", "NOT_FOUND"));

        pictureAdapter.getAdmin().delete(picture);
        return OkApiResponse.of();
    }

    @ApiOperation("Загрузка изображения")
    @PostMapping(value = Routes.ROOT, produces = MediaType.APPLICATION_JSON_VALUE)
    public SuccessApiResponse<String> upload(@RequestParam MultipartFile file) throws AuthenticationException, IOException, AuthorizationException {
       PictureDTO picture = pictureAdapter.getAdmin()
               .create(file.getInputStream(), file.getOriginalFilename(), file.getContentType(), false);

       return SuccessApiResponse.of(picture.getId());
    }


}
