/*
 * Copyright 2007-2009 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hlpp.clientcontact.dao;

import java.util.Collection;

//import org.springframework.security.annotation.Secured;
import com.hlpp.clientcontact.domain.User;
//import org.springframework.security.core.GrantedAuthority;

import com.hlpp.clientcontact.security.SecureUserDetails;

/**
 * User DAO interface.
 *
 * @author Casmon Gordon
 */
public interface UserDao {

    /**
     * Find user by username.
     */
    public User getUser(String username);


    /**
     * Find user by id.
     */
    public User findUserById(Integer id);


    /**
     * Find authorities.
     */
    //public Collection<GrantedAuthority> getAuthorities(String username);


    /**
     * Find users.
     */
    public Collection<User> findUsers();

    /**
     * Saves user.
     */
    public User save(User user);

    /**
     * Deletes user.
     */
    //@Secured ({"ROLE_ADMIN"})
    public void delete(User user, Integer userid);


    /**
     * Find the user that is logged in.
     */
	public SecureUserDetails findUser();


}






