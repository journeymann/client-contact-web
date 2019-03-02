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

import com.hlpp.clientcontact.domain.ContactCase;
import com.hlpp.clientcontact.dao.PolicyDao;
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
 * Contact form controller.
 *
 * @author Casmon Gordon
 */
@Controller
public class ContactController {
    public static final String SEARCH_VIEW_PATH_KEY = "/type/contacts";

    public static final String SEARCH_VIEW_KEY = "redirect:index.html";
    public static final String SEARCH_MODEL_KEY = "contacts";
    public static final String DETAIL_MODEL_KEY = "contact";

    @Autowired
    protected PolicyDao policyDao = null;

    /**
     * For every request for this controller, this will
     * create a contact instance for the form.
     */
    @ModelAttribute
    public ContactCase newRequest(@RequestParam("id") Integer id, @RequestParam(value="p_id", required=false) Integer p_id, @RequestParam(value="flow_id", required=false) Integer case_id, Model model) {
		ContactCase contact;
		if(id != null)
			contact= policyDao.findContactCaseById(id);
		else
			contact= policyDao.findNewContactCase(p_id, case_id);

        return contact;
    }

    /**
     * <p>ContactCase form request.</p>
     *
     * <p>Expected HTTP GET and request '/contact/form'.</p>
     */
    @RequestMapping(value="/contact/form", method=RequestMethod.GET)
    public void form() {}

    /**
     * <p>Saves a contact.</p>
     *
     * <p>Expected HTTP POST and request '/contact/form'.</p>
     */
    @RequestMapping(value="/contact/form", method=RequestMethod.POST)
    public String form(ContactCase contact, Model model) {

        ContactCase result = policyDao.save(contact);

        // set id from create
        if (contact.getId() == null) {
            contact.setId(result.getId());
        }

        model.addAttribute("statusMessageKey", "contact.form.msg.success");

        return SEARCH_VIEW_PATH_KEY;
    }

    /**
     * <p>Deletes a contact.</p>
     *
     * <p>Expected HTTP POST and request '/contact/delete'.</p>
     */
    @RequestMapping(value="/contact/delete", method=RequestMethod.POST)
    public String delete(ContactCase contact, @RequestParam("type") String type, @RequestParam("flow_id") String flow_id) {
		SecureUserDetails user = (SecureUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Integer userid = user.getUserid();

		Collection<ContactCase> contacts = policyDao.findContactsByPersonId(contact.getContactid());

		//if last contactcase then add to dummy policy
		if(contacts.size()==1){

        	ContactCase temp = policyDao.findNewContactCase(contact.getContactid(), 200);
			policyDao.save(temp);

		}

		policyDao.delete(contact, userid);

        String path_key = SEARCH_VIEW_PATH_KEY;

        if(type.equals("P")){
			path_key = "redirect:/type/redirect.html?ptype=P&pflow_id="+flow_id;
		}else if(type.equals("C")){
			path_key = "redirect:/type/redirect.html?ptype=C&pflow_id="+flow_id;
		}else if(type.equals("B")){
			path_key = "redirect:/type/redirect.html?ptype=B&pflow_id="+flow_id;
		}else if(type.equals("D")){
			path_key = "redirect:/type/redirect.html?ptype=D&pflow_id="+flow_id;
		}

        return path_key;
    }

    /**
     * <p>Searches for all contacts and returns them in a
     * <code>Collection</code>.</p>
     *
     * <p>Expected HTTP GET and request '/contact/search'.</p>
     */
    @RequestMapping(value="/contact/search", method=RequestMethod.GET)
    public @ModelAttribute(SEARCH_MODEL_KEY) Collection<ContactCase> search() {
        return policyDao.findContactCases();
    }


}
