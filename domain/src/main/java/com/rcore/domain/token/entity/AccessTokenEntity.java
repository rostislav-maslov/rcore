package com.rcore.domain.token.entity;

import com.rcore.domain.base.entity.BaseEntity;
import com.rcore.domain.role.entity.Role;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
public class AccessTokenEntity extends BaseEntity {
    private String id;
    private String userId;
    private Set<Role> roles;
    private Date expireAt = new Date();

    private String createFromRefreshTokenId;

    private String sign;

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

    public static String sign(String accessTokenId, Long expireAt, RefreshTokenEntity refreshTokenEntity){
        String signString = refreshTokenEntity.getId() +
                refreshTokenEntity.getUserId() +
                refreshTokenEntity.getExpireAt().toString() +
                expireAt.toString() +
                accessTokenId;

        return hash(signString);
    }
}
