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

package com.hlpp.clientcontact.web.controller;

import java.util.Collection;
import java.util.Date;

import com.hlpp.clientcontact.domain.User;
import com.hlpp.clientcontact.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.hlpp.clientcontact.security.SecureUserDetails;
import org.springframework.security.core.context.SecurityContextHolder;


/**
 * User form controller.
 *
 * @author Casmon Gordon
 */
@Controller
public class UserController {
    public static final String SEARCH_VIEW_PATH_KEY = "/user/search";

    public static final String SEARCH_VIEW_KEY = "redirect:search.html";
    public static final String SEARCH_MODEL_KEY = "users";

    @Autowired
    protected UserDao userDao = null;

    /**
     * For every request for this controller, this will
     * create a user instance for the form.
     */
    @ModelAttribute
    public User newRequest(@RequestParam(required=false) Integer id) {
        return (id != null ? userDao.findUserById(id) : new User());
    }

    /**
     * <p>User form request.</p>
     *
     * <p>Expected HTTP GET and request '/user/form'.</p>
     */
    @RequestMapping(value="/user/form", method=RequestMethod.GET)
    public void form() {}

    /**
     * <p>Saves a user.</p>
     *
     * <p>Expected HTTP POST and request '/user/form'.</p>
     */
    @RequestMapping(value="/user/form", method=RequestMethod.POST)
    public void form(User user, Model model) {
        if (user.getCreated() == null) {
            user.setCreated(new Date());
        }

        User result = userDao.save(user);

        // set id from create
        if (user.getId() == null) {
            user.setId(result.getId());
        }

        model.addAttribute("statusMessageKey", "user.form.msg.success");
    }

    /**
     * <p>Deletes a user.</p>
     *
     * <p>Expected HTTP POST and request '/user/delete'.</p>
     */
    @RequestMapping(value="/user/delete", method=RequestMethod.POST)
    public String delete(User user) {
		SecureUserDetails sec_user = (SecureUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Integer userid = sec_user.getUserid();

        userDao.delete(user, userid);

        return SEARCH_VIEW_PATH_KEY;
    }

    /**
     * <p>Searches for all users and returns them in a
     * <code>Collection</code>.</p>
     *
     * <p>Expected HTTP GET and request '/user/search'.</p>
     */
    @RequestMapping(value="/user/search", method=RequestMethod.GET)
    public @ModelAttribute(SEARCH_MODEL_KEY) Collection<User> search() {
        return userDao.findUsers();
    }

}
