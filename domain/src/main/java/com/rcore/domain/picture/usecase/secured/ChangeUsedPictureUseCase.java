package com.rcore.domain.picture.usecase.secured;

import com.rcore.domain.picture.access.ChangeUsedPictureAccess;
import com.rcore.domain.picture.entity.PictureEntity;
import com.rcore.domain.picture.exception.PictureNotFoundException;
import com.rcore.domain.picture.port.PictureRepository;
import com.rcore.domain.picture.usecase.secured.commands.ChangeUsedPictureCommand;
import com.rcore.domain.token.exception.AuthenticationException;
import com.rcore.domain.token.exception.AuthorizationException;
import com.rcore.domain.token.usecase.AuthorizationByTokenUseCase;
import com.rcore.domain.user.exception.TokenExpiredException;

public class ChangeUsedPictureUseCase extends PictureAdminBaseUseCase {

    public ChangeUsedPictureUseCase(PictureRepository pictureRepository, AuthorizationByTokenUseCase authorizationByTokenUseCase) throws AuthorizationException {
        super(pictureRepository, new ChangeUsedPictureAccess(), authorizationByTokenUseCase);
    }

    public PictureEntity changeUsed(ChangeUsedPictureCommand changeUsedPictureCommand) throws PictureNotFoundException, AuthenticationException, AuthorizationException, TokenExpiredException {
        checkAccess();
        PictureEntity picture = pictureRepository.findById(changeUsedPictureCommand.getId())
                .orElseThrow(PictureNotFoundException::new);

        picture.setIsUsed(changeUsedPictureCommand.getIsUsed());
        picture = pictureRepository.save(picture);
        return picture;
    }
}
