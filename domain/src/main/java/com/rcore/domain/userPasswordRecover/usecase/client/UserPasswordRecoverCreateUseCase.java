package com.rcore.domain.userPasswordRecover.usecase.client;

import com.rcore.domain.user.entity.UserEntity;
import com.rcore.domain.user.exception.UserNotFoundException;
import com.rcore.domain.user.port.UserRepository;
import com.rcore.domain.userPasswordRecover.entity.UserPasswordRecoverEntity;
import com.rcore.domain.userPasswordRecover.port.EmailSender;
import com.rcore.domain.userPasswordRecover.port.UserPasswordRecoverIdGenerator;
import com.rcore.domain.userPasswordRecover.port.UserPasswordRecoverRepository;

import java.util.Date;
import java.util.Optional;
import java.util.Random;

class UserPasswordRecoverCreateUseCase {
    protected final UserPasswordRecoverRepository userPasswordRecoverRepository;
    protected final UserRepository userRepository;
    protected final UserPasswordRecoverIdGenerator userPasswordRecoverIdGenerator;
    private final EmailSender emailSender;

    public UserPasswordRecoverCreateUseCase(UserPasswordRecoverRepository userPasswordRecoverRepository, UserRepository userRepository, UserPasswordRecoverIdGenerator userPasswordRecoverIdGenerator, EmailSender emailSender) {
        this.userPasswordRecoverRepository = userPasswordRecoverRepository;
        this.userRepository = userRepository;
        this.userPasswordRecoverIdGenerator = userPasswordRecoverIdGenerator;
        this.emailSender = emailSender;
    }

    private String generateCode() {
        int min = 100000, max = 999999;
        Random random = new Random();
        return String.valueOf(random.nextInt((max - min)) + min);
    }

    private Optional<UserPasswordRecoverEntity> create(UserEntity userEntity) {
        UserPasswordRecoverEntity userPasswordRecoverEntity = new UserPasswordRecoverEntity();

        userPasswordRecoverEntity.setId(userPasswordRecoverIdGenerator.generate());
        userPasswordRecoverEntity.setUserId(userEntity.getId());
        userPasswordRecoverEntity.setCode(generateCode());
        userPasswordRecoverEntity.setExpiredAt(new Date(new Date().getTime() + userPasswordRecoverEntity.getExpiredTime()));

        return userPasswordRecoverRepository.save(userPasswordRecoverEntity);
    }

    public UserPasswordRecoverEntity create(String email) throws UserNotFoundException {
        email = email.toLowerCase();
        Optional<UserPasswordRecoverEntity> userPasswordCurrent = userPasswordRecoverRepository.findActiveByEmail(email);

        UserPasswordRecoverEntity userPasswordRecoverEntity = null;

        if(userPasswordCurrent.isPresent()){
            userPasswordRecoverEntity = userPasswordCurrent.get();
        }else {
            Optional<UserEntity> userEntity = userRepository.findByEmail(email);
            if (userEntity.isPresent() == false) throw new UserNotFoundException();
            userPasswordRecoverEntity = create(userEntity.get()).get();
        }

        userPasswordRecoverEntity.tryIncrement();
        emailSender.send(email, userPasswordRecoverEntity.getCode());
        userRepository.save(userPasswordRecoverEntity);

        return userPasswordRecoverEntity;
    }

}
