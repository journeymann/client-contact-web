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

import com.hlpp.clientcontact.domain.Broker;
import com.hlpp.clientcontact.domain.Person;
import com.hlpp.clientcontact.domain.ContactCase;
import com.hlpp.clientcontact.domain.BrokerContact;
import com.hlpp.clientcontact.dao.PolicyDao;
import com.hlpp.clientcontact.dao.PersonDao;
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
 * Broker form controller.
 *
 * @author Casmon Gordon
 */
@Controller
public class BrokerController {

    public static final String SEARCH_VIEW_PATH_KEY = "/type/contacts";

    public static final String SEARCH_VIEW_KEY = "redirect:index.html";
    public static final String SEARCH_MODEL_KEY = "brokers";
    public static final String DETAIL_MODEL_KEY = "broker";
    public final String ACTIVE = "Active";

    @Autowired
    protected PolicyDao policyDao = null;

    @Autowired
    protected PersonDao personDao = null;


    /**
     * For every request for this controller, this will
     * create a broker instance for the form.
     */
    @ModelAttribute
    public Broker newRequest(@RequestParam(required=false) Integer id) {
        return (id != null ? policyDao.findBrokerById(id) : new Broker());
    }

    /**
     * <p>Broker form request.</p>
     *
     * <p>Expected HTTP GET and request '/broker/form'.</p>
     */
    @RequestMapping(value="/broker/form", method=RequestMethod.GET)
    public void form() {}

    /**
     * <p>Saves a broker.</p>
     *
     * <p>Expected HTTP POST and request '/broker/form'.</p>
     */
    @RequestMapping(value="/broker/form", method=RequestMethod.POST)
    public @ModelAttribute(SEARCH_MODEL_KEY) Collection<Broker> form(Broker broker, Model model) {
        if (broker.getCreated() == null) {
            broker.setCreated(new Date());
        }

        Broker result = policyDao.save(broker);

        // set id from create
        if (broker.getId() == null) {
            broker.setId(result.getId());
        }

        model.addAttribute("statusMessageKey", "broker.form.msg.success");
        return policyDao.findBrokers();
    }

    /**
     * <p>Deletes a broker.</p>
     *
     * <p>Expected HTTP POST and request '/broker/delete'.</p>
     */
    @RequestMapping(value="/broker/delete", method=RequestMethod.POST)
    public @ModelAttribute(SEARCH_MODEL_KEY) Collection<Broker> delete(Broker broker) {

		SecureUserDetails user = (SecureUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Integer userid = user.getUserid();

        policyDao.delete(broker, userid);

        return policyDao.findBrokers();
    }

    /**
     * <p>Searches for all brokers and returns them in a
     * <code>Collection</code>.</p>
     *
     * <p>Expected HTTP GET and request '/broker/search'.</p>
     */
    @RequestMapping(value="/broker/search", method=RequestMethod.GET)
    public @ModelAttribute(SEARCH_MODEL_KEY) Collection<Broker> search() {
        return policyDao.findBrokers();
    }

    /**
     * <p>Searches for all brokers and returns them in a
     * <code>Collection</code>.</p>
     *
     * <p>Expected HTTP GET and request '/type/broker'.</p>
     */
    @RequestMapping(value="/type/broker_company", method=RequestMethod.GET)
    public @ModelAttribute(SEARCH_MODEL_KEY) Collection<Broker> list(HttpServletRequest request) {
		SecureUserDetails user = (SecureUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		request.getSession().setAttribute("user", user);
		request.getSession().setAttribute("tab_selection", "Broker");
		request.getSession().setAttribute("selectionLists", policyDao.findSelectionLists());

        return policyDao.findBrokers();
    }

    /**
     * <p>Searches for a brokers and returns it in a
     * <code>Broker</code> object.</p>
     *
     * <p>Expected HTTP GET and request '/broker/detail'.</p>
     */
    @RequestMapping(value="/broker/detail", method=RequestMethod.GET)
    public @ModelAttribute(DETAIL_MODEL_KEY) Broker display(@RequestParam("id") Integer id, @RequestParam(value="p_id", required=false) Integer p_id, Model model) {

		/*
		 * If a p_id is passed then add the person as a broker contact
		 */

		if(p_id != null){


			Collection<ContactCase> contacts = policyDao.findContactsByPersonIdAndBrokerId(p_id, id);

	        if (contacts.isEmpty()) {

				Person person = personDao.findPersonById(p_id);
				person.setBrokercompanyid(id);
				personDao.save(person);

				model.addAttribute("statusMessageKey", "contact.broker.form.msg.success");
			}else{
        		model.addAttribute("statusMessageKey", "contact.form.duplicate.type.error");
			}
		}


        Broker broker= policyDao.findBrokerById(id);
        Collection<ContactCase> contacts = policyDao.findContactsByBrokerCompanyId(id);
        Collection<BrokerContact> contacts_no_dup = policyDao.findBrokerContacts(id);
        Collection<String> cases = policyDao.findCasesByBrokerCompanyId(id);

		for (ContactCase contact : contacts) {
			contact.setCases(policyDao.findCasesByContactId(contact.getContactid()));
		}

        broker.setContactCases(contacts);
        broker.setContacts(contacts_no_dup);
        broker.setCases(cases);

        return broker;
    }

}
