package com.rcore.adapter.domain.picture;

import com.rcore.adapter.domain.picture.dto.PictureDTO;
import com.rcore.adapter.domain.picture.mapper.PictureMapper;
import com.rcore.adapter.domain.role.mapper.RoleMapper;
import com.rcore.adapter.domain.user.dto.UserDTO;
import com.rcore.adapter.domain.user.mapper.UserMapper;
import com.rcore.domain.base.port.SearchResult;
import com.rcore.domain.file.exception.FileNotFoundException;
import com.rcore.domain.picture.config.PictureConfig;
import com.rcore.domain.picture.entity.PictureEntity;
import com.rcore.domain.token.exception.AuthenticationException;
import com.rcore.domain.token.exception.AuthorizationException;
import lombok.RequiredArgsConstructor;

import java.io.InputStream;
import java.util.Optional;

@RequiredArgsConstructor
public class PictureAdminAdapter {
    private PictureMapper pictureMapper = new PictureMapper();
    private UserMapper userMapper = new UserMapper(new RoleMapper());
    private final PictureConfig pictureConfig;

    public PictureDTO create(UserDTO actor, InputStream content, String fileName, String contentType, boolean isPrivate) throws AuthorizationException, AuthenticationException {
        return pictureMapper.map(pictureConfig.admin.createUseCase()
                .create(content, fileName, contentType, isPrivate));
    }

    public Boolean delete(UserDTO actor, PictureDTO picture) throws AuthorizationException, AuthenticationException {
        return pictureConfig.admin.deleteUseCase()
                .delete(pictureMapper.inverseMap(picture));
    }

    public PictureDTO update(UserDTO actor, PictureDTO picture) throws AuthorizationException, FileNotFoundException, AuthenticationException {
        return pictureMapper.map(pictureConfig.admin.updateUseCase()
                .update(pictureMapper.inverseMap(picture)));
    }

    public Optional<PictureDTO> findById(UserDTO actor, String id) throws AuthorizationException, AuthenticationException {
        return pictureConfig.admin.viewUseCase()
                .findById(id)
                .map(pictureMapper::map);
    }

    public SearchResult<PictureDTO> find(UserDTO actor, Long size, Long skip) throws AuthorizationException, AuthenticationException {
        SearchResult<PictureEntity> result = pictureConfig.admin
                .viewUseCase().find(size, skip);

        return SearchResult.withItemsAndCount(
                pictureMapper.mapAll(result.getItems()),
                result.getCount()
        );
    }

}
