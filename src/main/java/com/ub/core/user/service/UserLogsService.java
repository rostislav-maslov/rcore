package com.ub.core.user.service;

import com.ub.core.user.models.UserLoginStatusEnum;
import com.ub.core.user.models.UserLogsDoc;
import eu.bitwalker.useragentutils.UserAgent;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by fyzu on 16.09.16.
 */
@Component
public class UserLogsService {

    @Autowired private MongoTemplate mongoTemplate;

    private static final Integer MAX_RECORDS = 20;

    public UserLogsDoc save(UserLogsDoc userLogsDoc) {
        if (userLogsDoc.getUserId() != null) {

            Criteria criteria = Criteria.where("userId").is(userLogsDoc.getUserId());
            Integer count = (int) mongoTemplate.count(new Query(criteria), UserLogsDoc.class);

            if (count >= MAX_RECORDS) {
                Query query = new Query(criteria);
                query.limit(count - MAX_RECORDS + 1);
                query.with(new Sort(Sort.Direction.ASC, "createdAt"));
                //mongoTemplate.remove(query, UserLogsDoc.class);
                // query limit - не работает на метод remove
                for(UserLogsDoc doc : mongoTemplate.find(query, UserLogsDoc.class)) {
                    mongoTemplate.remove(doc);
                }
            }

            mongoTemplate.save(userLogsDoc);
        }
        return userLogsDoc;
    }

    public UserLogsDoc findById(ObjectId id) {
        return mongoTemplate.findById(id, UserLogsDoc.class);
    }

    public List<UserLogsDoc> findByUserId(ObjectId id) {
        Query query = new Query(Criteria.where("userId").is(id));
        query.with(new Sort(Sort.Direction.DESC, "createdAt"));
        return mongoTemplate.find(query, UserLogsDoc.class);
    }

    public UserLogsDoc logging(ObjectId userId, UserLoginStatusEnum status) {
        UserLogsDoc userLogsDoc = new UserLogsDoc();
        userLogsDoc.setUserId(userId);
        userLogsDoc.setStatus(status);

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                .getRequest();
        String ip = request.getHeader("X-Real-IP");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Forwarded-For");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        userLogsDoc.setIp(ip);

        UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
        userLogsDoc.setBrowser(userAgent.getBrowser().getName());
        userLogsDoc.setBrowserVersion(userAgent.getBrowserVersion().toString());
        userLogsDoc.setOperatingSystem(userAgent.getOperatingSystem().getName());
        userLogsDoc.setDeviceType(userAgent.getOperatingSystem().getDeviceType().getName());

        userLogsDoc = save(userLogsDoc);

        return userLogsDoc;
    }

}
