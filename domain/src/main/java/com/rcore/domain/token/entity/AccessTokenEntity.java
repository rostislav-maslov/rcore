package com.rcore.domain.token.entity;

import com.rcore.domain.base.entity.BaseEntity;
import com.rcore.domain.access.entity.Access;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class AccessTokenEntity extends BaseEntity {
    private String id;
    private String userId;
    private Set<Access> accesses;
    private LocalDateTime expireAt = LocalDateTime.now();
    private RefreshTokenEntity.Status status = RefreshTokenEntity.Status.ACTIVE;
    private String createFromRefreshTokenId;

    private String sign;

    public Boolean readyForRefresh() {
        if (status.equals(RefreshTokenEntity.Status.ACTIVE))
            return true;
        if (status.equals(RefreshTokenEntity.Status.EXPIRED))
            return true;
        return false;
    }

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


    public boolean equalsRequestedToken(Object o) {
        if (this == o)
            return true;

        if (o instanceof AccessTokenEntity) {
            AccessTokenEntity other = (AccessTokenEntity) o;

            return other.getId().equals(getId())
                    && other.getUserId().equals(getUserId())
                    && other.getExpireAt().equals(getExpireAt())
                    && other.getStatus().name().equals(getStatus().name())
                    && other.getCreateFromRefreshTokenId().equals(getCreateFromRefreshTokenId())
                    && other.getSign().equals(getSign())
                    && other.getCreatedAt().withNano(0).equals(getCreatedAt().withNano(0))
                    && other.getUpdatedAt().withNano(0).equals(getUpdatedAt().withNano(0));
        }

        return false;
    }

    public static String sign(String accessTokenId, Long expireAt, RefreshTokenEntity refreshTokenEntity) {
        String signString = refreshTokenEntity.getId() +
                refreshTokenEntity.getUserId() +
                refreshTokenEntity.getExpireAt().toString() +
                refreshTokenEntity.getSalt() +
                expireAt.toString() +
                accessTokenId;

        return hash(signString);
    }

    public Boolean isActive() {
        return LocalDateTime.now().isBefore(expireAt);
    }
}
