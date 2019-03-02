
package com.hlpp.clientcontact.service;

import com.hlpp.clientcontact.dao.UserDao;

import com.hlpp.clientcontact.security.SecureUserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import com.hlpp.clientcontact.domain.User;
import com.hlpp.clientcontact.domain.Authority;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.ArrayList;
import java.util.Iterator;


@Service("userDetailsService")
public class UserService implements UserDetailsService {
     @Autowired private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

	@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
        // load user
        User user = userDao.getUser(username);

        if (user != null) {
            return convertUser(user);
        } else {
            throw new UsernameNotFoundException("No user with username '" + username + "' found!");
        }
    }


    private SecureUserDetails convertUser(User user){
			SecureUserDetails userDetails = new SecureUserDetails();

			userDetails.setUsername(user.getUsername());
			userDetails.setPassword(user.getPassword());
			userDetails.setEnabled(user.getEnabled());
			userDetails.setFullname(user.getFullname());
			userDetails.setDepartment(user.getGroupcode());
			userDetails.setEmail(user.getEmail());
			userDetails.setUserid(user.getId());

			ArrayList list = new ArrayList<GrantedAuthority>();

			Iterator iter = user.getAuthorities().iterator();

			while(iter.hasNext()){
				String role = ((Authority)iter.next()).getAuthority();
				list.add(new GrantedAuthorityImpl(role));
			}

			userDetails.setAuthorities(list);

		return userDetails;
	}


}