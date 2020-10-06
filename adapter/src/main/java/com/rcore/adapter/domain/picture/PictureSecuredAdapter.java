package com.rcore.adapter.domain.picture;

import com.rcore.adapter.domain.picture.dto.PictureDTO;
import com.rcore.adapter.domain.picture.mapper.PictureMapper;
import com.rcore.adapter.domain.role.mapper.RoleMapper;
import com.rcore.adapter.domain.user.dto.UserDTO;
import com.rcore.adapter.domain.user.mapper.UserMapper;
import com.rcore.domain.base.port.SearchRequest;
import com.rcore.domain.base.port.SearchResult;
import com.rcore.domain.file.exception.FileNotFoundException;
import com.rcore.domain.picture.config.PictureConfig;
import com.rcore.domain.picture.entity.PictureEntity;
import com.rcore.domain.picture.exception.PictureNotFoundException;
import com.rcore.domain.picture.usecase.secured.commands.ChangeUsedPictureCommand;
import com.rcore.domain.token.exception.AuthenticationException;
import com.rcore.domain.token.exception.AuthorizationException;
import lombok.RequiredArgsConstructor;

import java.io.InputStream;
import java.util.Optional;

@RequiredArgsConstructor
public class PictureSecuredAdapter {
    private PictureMapper pictureMapper = new PictureMapper();
    private final PictureConfig pictureConfig;

    public PictureDTO create(InputStream content, String fileName, String contentType, boolean isPrivate) throws AuthorizationException, AuthenticationException {
        return pictureMapper.map(pictureConfig.admin.createUseCase()
                .create(content, fileName, contentType, isPrivate));
    }

    public Boolean delete(PictureDTO picture) throws AuthorizationException, AuthenticationException {
        return pictureConfig.admin.deleteUseCase()
                .delete(pictureMapper.inverseMap(picture));
    }

    public void deleteUnused() throws AuthorizationException, AuthenticationException {
        pictureConfig.admin.deleteUnusedUseCase()
                .deleteUnused();
    }

    public PictureDTO changeUsed(ChangeUsedPictureCommand changeUsedPictureCommand) throws AuthorizationException, PictureNotFoundException, AuthenticationException {
        return pictureMapper.map(pictureConfig.admin.changeUsedPictureUseCase()
                .changeUsed(changeUsedPictureCommand));
    }

    public PictureDTO update(PictureDTO picture) throws AuthorizationException, FileNotFoundException, AuthenticationException {
        return pictureMapper.map(pictureConfig.admin.updateUseCase()
                .update(pictureMapper.inverseMap(picture)));
    }

    public Optional<PictureDTO> findById(String id) throws AuthorizationException, AuthenticationException {
        return pictureConfig.admin.viewUseCase()
                .findById(id)
                .map(pictureMapper::map);
    }

    public SearchResult<PictureDTO> find(SearchRequest request) throws AuthorizationException, AuthenticationException {
        SearchResult<PictureEntity> result = pictureConfig.admin
                .viewUseCase().find(request);

        return SearchResult.withItemsAndCount(
                pictureMapper.mapAll(result.getItems()),
                result.getCount()
        );
    }

}
