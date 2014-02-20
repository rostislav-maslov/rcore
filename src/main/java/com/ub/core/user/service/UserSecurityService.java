package com.ub.core.user.service;

import com.ub.core.user.models.EmailUserDoc;
import com.ub.core.user.models.RoleDoc;
import com.ub.core.user.models.UserStatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Anton
 * Date: 1/16/14
 * Time: 11:52 AM
 * To change this template use File | Settings | File Templates.
 */

@Service
public class UserSecurityService implements UserDetailsService {


    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        EmailUserDoc emailUserDoc = userService.getUserByEmail(username);
        if (emailUserDoc.getUserDoc() == null || emailUserDoc.getUserDoc().getUserStatus().equals(UserStatusEnum.BLOCK))
            emailUserDoc = null;
        org.springframework.security.core.userdetails.User userDetail = new org.springframework.security.core.userdetails.User(emailUserDoc.getEmail(), emailUserDoc.getPassword(), true, true, true, true, getAuthorities(emailUserDoc.getUserDoc().getRoleDocList()));
        return userDetail;
    }

    public List<GrantedAuthority> getAuthorities(List<RoleDoc> roleDocList) {
        List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();
        for (Iterator<RoleDoc> i = roleDocList.iterator(); i.hasNext(); ) {
            RoleDoc item = i.next();
            authList.add(new SimpleGrantedAuthority(item.getRoleTitle()));

        }
        return authList;
    }


}
