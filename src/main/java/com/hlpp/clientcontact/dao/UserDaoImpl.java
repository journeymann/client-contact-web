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
import java.util.Date;

import com.hlpp.clientcontact.domain.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
//import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import com.hlpp.clientcontact.security.SecureUserDetails;
import org.springframework.security.core.context.SecurityContextHolder;


/**
 * User DAO implementation.
 *
 * @author Casmon Gordon
 */
@Repository("userDao")
@Transactional(readOnly = true)
public class UserDaoImpl implements UserDao {

    private EntityManager em = null;

    private final String DELETED = "Deleted";

    /**
     * Sets the entity manager.
     */
    @PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }

    /**
     * Find user.
     */
    public User getUser(String username) {
        return em.find(User.class, username);
        //List<User> list =  (List)em.createQuery("select p from User p where p.username = :username").setParameter("username", username).getResultList();
        //return list.get(0);
    }

    /**
     * Find users.
     */
    public User findUserById(Integer id) {
        return em.find(User.class, id);
    }

    /**
     * Find users.
     */
    @SuppressWarnings("unchecked")
    public Collection<User> findUsers() {
        return em.createQuery("select p from User p where p.status != 'Deleted' order by p.username").getResultList();
    }

    /**
     * Saves user.
     */
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public User save(User user) {
		//user.setPassword(new ShaPasswordEncoder(256).encodePassword(user.getPassword(),""));
        return em.merge(user);
    }

    /**
     * Deletes user.
     */
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public void delete(User user, Integer userid) {
		user.setUpdateby(userid.toString());
		user.setUpdatedate(new Date());
		user.setStatus(DELETED);
        em.merge(user);
    }


    /**
     * Find the user who is logged in.
     */
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public SecureUserDetails findUser() {
		SecureUserDetails user = (SecureUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user;
    }



}
