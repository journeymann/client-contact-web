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

import com.hlpp.clientcontact.domain.Person;
import com.hlpp.clientcontact.dao.PersonDao;
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

import javax.servlet.http.HttpServletRequest;

/**
 * Person form controller.
 *
 * @author Casmon Gordon
 */
@Controller
public class PersonController {
    public static final String SEARCH_VIEW_PATH_KEY = "/type/contacts";
    public static final String SEARCH_VIEW_PATH_KEY_REDIRECT_DETAIL = "redirect:/type/redirect.html?ptype=";

    public static final String SEARCH_VIEW_KEY = "redirect:/type/contacts.html";
    public static final String SEARCH_MODEL_KEY = "persons";
    public static final String DETAIL_MODEL_KEY = "person";

    @Autowired
    protected PersonDao personDao = null;


    @Autowired
    protected PolicyDao policyDao = null;

    /**
     * For every request for this controller, this will
     * create a person instance for the form.
     */
    @ModelAttribute
    public Person newRequest(@RequestParam(required=false) Integer id) {
        return (id != null ? personDao.findPersonById(id) : new Person());
    }

    /**
     * <p>Person form request.</p>
     *
     * <p>Expected HTTP GET and request '/person/form'.</p>
     */
    @RequestMapping(value="/person/form", method=RequestMethod.GET)
    public void form() {}

    /**
     * <p>Saves a person.</p>
     *
     * <p>Expected HTTP POST and request '/person/form'.</p>
     */
    @RequestMapping(value="/person/form", method=RequestMethod.POST)
    public void form(Person person, Model model) {

		boolean hasErrors = false;

        if (person.getCreated() == null) {
            person.setCreated(new Date());
        }
		if(person.getFirstName()!=null) person.setFirstName(person.getFirstName().trim());
		if(person.getLastName()!=null) person.setLastName(person.getLastName().trim());


        Person result = personDao.save(person);

        // set id from create
        if (person.getId() == null) {
            person.setId(result.getId());
        }

        model.addAttribute("statusMessageKey", "person.form.msg.success");
    }

    /**
     * <p>Deletes a person.</p>
     *
     * <p>Expected HTTP POST and request '/person/delete'.</p>
     */
    @RequestMapping(value="/person/delete", method=RequestMethod.POST)
    public String delete(Person person) {
		SecureUserDetails user = (SecureUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Integer userid = user.getUserid();

        personDao.delete(person, userid);

        return SEARCH_VIEW_PATH_KEY_REDIRECT_DETAIL+"X";
    }

    /**
     * <p>Searches for all persons and returns them in a
     * <code>Collection</code>.</p>
     *
     * <p>Expected HTTP GET and request '/person/search'.</p>
     */
    @RequestMapping(value="/person/search", method=RequestMethod.GET)
    public @ModelAttribute(SEARCH_MODEL_KEY) Collection<Person> search() {
        return personDao.findPersons();
    }


    /**
     * <p>Searches for all persons and returns them in a
     * <code>Collection</code>.</p>
     *
     * <p>Expected HTTP GET and request '/type/contacts'.</p>
     */
    @RequestMapping(value="/type/contacts", method=RequestMethod.GET)
    public @ModelAttribute(SEARCH_MODEL_KEY) Collection<Person> list(HttpServletRequest request) {
		SecureUserDetails user = (SecureUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		request.getSession().setAttribute("user", user);
		request.getSession().setAttribute("tab_selection", "Contact");
		request.getSession().setAttribute("selectionLists", policyDao.findSelectionLists());

        return personDao.findPersons();
    }

    /**
     * <p>Searches for all persons and returns them in a
     * <code>Collection</code>.</p>
     *
     * <p>Expected HTTP GET and request '/type/contacts_select'.</p>
     */
    @RequestMapping(value="/type/contacts_select", method=RequestMethod.GET)
    public @ModelAttribute(SEARCH_MODEL_KEY) Collection<Person> list_person() {
        return personDao.findPersons();
    }

    /**
     * <p>Deletes a person.</p>
     *
     * <p>Expected HTTP POST and request '/broker_person/delete'.</p>
     */
    @RequestMapping(value="/broker_person/delete", method=RequestMethod.POST)
    public String deletebrokercontact(Integer id, Integer flow_id) {
		SecureUserDetails user = (SecureUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Integer userid = user.getUserid();

		Person person = personDao.findPersonById(id);

		person.setBrokercompanyid(null);
		personDao.save(person);

        //personDao.delete(person, userid);

        return SEARCH_VIEW_PATH_KEY_REDIRECT_DETAIL+"B&pflow_id=" + flow_id;
    }

	/**
     * <p>Deletes a person.</p>
     *
     * <p>Expected HTTP POST and request '/dealer_person/delete'.</p>
     */
    @RequestMapping(value="/dealer_person/delete", method=RequestMethod.POST)
    public String deletedealercontact(Integer id, Integer flow_id) {
		SecureUserDetails user = (SecureUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Integer userid = user.getUserid();

		Person person = personDao.findPersonById(id);

		person.setBrokerdealerid(null);
        personDao.save(person);
        //personDao.delete(person, userid);

        return SEARCH_VIEW_PATH_KEY_REDIRECT_DETAIL+"D&pflow_id=" + flow_id;
    }



    /**
     * <p>Searches for all contacts and returns them in a
     * <code>Collection</code>.</p>
     *
     * <p>Expected HTTP GET and request '/policy/view_contact'.</p>
     */
    @RequestMapping(value="/policy/view_contact", method=RequestMethod.GET)
    public @ModelAttribute(DETAIL_MODEL_KEY) Person find(@RequestParam(required=false) Integer id) {
        return (id != null ? personDao.findPersonById(id) : new Person());
    }

    /**
     * <p>Searches for all contacts and returns them in a
     * <code>Collection</code>.</p>
     *
     * <p>Expected HTTP GET and request '/compliance/view_contact'.</p>
     */
    @RequestMapping(value="/compliance/view_contact", method=RequestMethod.GET)
    public @ModelAttribute(DETAIL_MODEL_KEY) Person get(@RequestParam(required=false) Integer id) {
        return (id != null ? personDao.findPersonById(id) : new Person());
    }


    /**
     * <p>Searches for all contacts and returns them in a
     * <code>Collection</code>.</p>
     *
     * <p>Expected HTTP GET and request '/broker/view_contact'.</p>
     */
    @RequestMapping(value="/broker/view_contact", method=RequestMethod.GET)
    public @ModelAttribute(DETAIL_MODEL_KEY) Person bfind(@RequestParam(required=false) Integer id) {
        return (id != null ? personDao.findPersonById(id) : new Person());
    }

    /**
     * <p>Searches for all contacts and returns them in a
     * <code>Collection</code>.</p>
     *
     * <p>Expected HTTP GET and request '/dealer/view_contact'.</p>
     */
    @RequestMapping(value="/dealer/view_contact", method=RequestMethod.GET)
    public @ModelAttribute(DETAIL_MODEL_KEY) Person dget(@RequestParam(required=false) Integer id) {
        return (id != null ? personDao.findPersonById(id) : new Person());
    }

    /**
     * <p>Searches for all contacts and returns them in a
     * <code>Collection</code>.</p>
     *
     * <p>Expected HTTP GET and request '/type/view_contact'.</p>
     */
    @RequestMapping(value="/type/view_contact", method=RequestMethod.GET)
    public @ModelAttribute(DETAIL_MODEL_KEY) Person cget(@RequestParam(required=false) Integer id) {
        return (id != null ? personDao.findPersonById(id) : new Person());
    }


}
