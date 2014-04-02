package com.ub.core.user.service;

import com.ub.core.base.role.Role;
import com.ub.core.user.models.UserDoc;
import com.ub.core.user.models.UserStatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;


@Service
public class UserSecurityService implements UserDetailsService {


    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDoc userDoc = userService.getUserByEmail(username);
        if (userDoc == null || userDoc.getUserStatus().equals(UserStatusEnum.BLOCK))
            userDoc = null;
        org.springframework.security.core.userdetails.User userDetail = new User(
                userDoc.getEmail(),
                userDoc.getPassword(), true, true, true, true,
                getAuthorities(userDoc.getRoles())
        );
        return userDetail;
    }

    public List<GrantedAuthority> getAuthorities(Set<Role> roleDocList) {
        List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();
        for (Iterator<Role> i = roleDocList.iterator(); i.hasNext(); ) {
            Role item = i.next();
            authList.add(new SimpleGrantedAuthority(item.getRoleTitle()));

        }
        return authList;
    }


}
