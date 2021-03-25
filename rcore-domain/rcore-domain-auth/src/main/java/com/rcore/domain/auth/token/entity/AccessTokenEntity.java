package com.rcore.domain.auth.token.entity;

import com.rcore.domain.auth.credential.entity.CredentialEntity;
import com.rcore.domain.commons.entity.BaseEntity;
import com.rcore.domain.security.model.AccessTokenData;
import com.rcore.domain.security.model.CredentialDetails;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

/**
 * Токен авторизации
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccessTokenEntity extends BaseEntity<String> {
    protected CredentialEntity credential;
    protected LocalDateTime expireAt = LocalDateTime.now();
    protected RefreshTokenEntity.Status status = RefreshTokenEntity.Status.ACTIVE;
    protected String createByRefreshTokenId;

    protected String sign;

    private static String hash(String st) {
        MessageDigest messageDigest = null;
        byte[] digest = new byte[0];

        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(st.getBytes());
            digest = messageDigest.digest();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        BigInteger bigInt = new BigInteger(1, digest);
        String md5Hex = bigInt.toString(16);

        while( md5Hex.length() < 32 ){
            md5Hex = "0" + md5Hex;
        }

        return md5Hex;
    }

    public static String sign(String accessTokenId, Long expireAt, RefreshTokenEntity refreshTokenEntity) {
        String signString = refreshTokenEntity.getId() +
                refreshTokenEntity.getCredential().getId() +
                refreshTokenEntity.getExpireAt().toString() +
                refreshTokenEntity.getSalt() +
                expireAt.toString() +
                accessTokenId;

        return hash(signString);
    }

    public Boolean isActive() {
        if (this.status != RefreshTokenEntity.Status.ACTIVE) return false;
        if (LocalDateTime.now().isAfter(expireAt)) return false;

        return true;
    }

    public void expire() {
        this.status = RefreshTokenEntity.Status.EXPIRED;
    }

    public void deactivate() {
        this.status = RefreshTokenEntity.Status.INACTIVE;
    }

    public AccessTokenData toAccessTokenData() {
        return new AccessTokenData(
                this.getId(),
                this.getCredential().getId(),
                this.getCredential().getRoles()
                        .stream()
                        .map(role -> new CredentialDetails.Role(role.getRole().getName()))
                        .collect(Collectors.toList()),
                this.getCreatedAt(),
                this.getExpireAt());
    }
}
