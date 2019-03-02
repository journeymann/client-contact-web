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

import com.hlpp.clientcontact.domain.BrokerDealer;
import com.hlpp.clientcontact.domain.Person;
import com.hlpp.clientcontact.domain.ContactCase;
import com.hlpp.clientcontact.domain.DealerContact;
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
 * BrokerDealer form controller.
 *
 * @author Casmon Gordon
 */
@Controller
public class BrokerDealerController {
    public static final String SEARCH_VIEW_PATH_KEY = "/type/contacts";

    public static final String SEARCH_VIEW_KEY = "redirect:index.html";
    public static final String SEARCH_MODEL_KEY = "dealers";
    public static final String DETAIL_MODEL_KEY = "dealer";
    public final String ACTIVE = "Active";

    @Autowired
    protected PolicyDao policyDao = null;

    @Autowired
    protected PersonDao personDao = null;

    /**
     * For every request for this controller, this will
     * create a dealer instance for the form.
     */
    @ModelAttribute
    public BrokerDealer newRequest(@RequestParam(required=false) Integer id) {
        return (id != null ? policyDao.findBrokerDealerById(id) : new BrokerDealer());
    }

    /**
     * <p>BrokerDealer form request.</p>
     *
     * <p>Expected HTTP GET and request '/dealer/form'.</p>
     */
    @RequestMapping(value="/dealer/form", method=RequestMethod.GET)
    public void form() {}

    /**
     * <p>Saves a dealer.</p>
     *
     * <p>Expected HTTP POST and request '/dealer/form'.</p>
     */
    @RequestMapping(value="/dealer/form", method=RequestMethod.POST)
    public void form(BrokerDealer dealer, Model model) {
        if (dealer.getCreated() == null) {
            dealer.setCreated(new Date());
        }

        BrokerDealer result = policyDao.save(dealer);

        // set id from create
        if (dealer.getId() == null) {
            dealer.setId(result.getId());
        }

        model.addAttribute("statusMessageKey", "broker.dealer.form.msg.success");
    }

    /**
     * <p>Deletes a dealer.</p>
     *
     * <p>Expected HTTP POST and request '/dealer/delete'.</p>
     */
    @RequestMapping(value="/dealer/delete", method=RequestMethod.POST)
    public @ModelAttribute(SEARCH_MODEL_KEY) Collection<BrokerDealer> delete(BrokerDealer dealer) {
		SecureUserDetails user = (SecureUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Integer userid = user.getUserid();

        policyDao.delete(dealer, userid);

        return policyDao.findBrokerDealers();
    }

    /**
     * <p>Searches for all dealers and returns them in a
     * <code>Collection</code>.</p>
     *
     * <p>Expected HTTP GET and request '/dealer/search'.</p>
     */
    @RequestMapping(value="/dealer/search", method=RequestMethod.GET)
    public @ModelAttribute(SEARCH_MODEL_KEY) Collection<BrokerDealer> search() {
        return policyDao.findBrokerDealers();
    }


    /**
     * <p>Searches for all dealers and returns them in a
     * <code>Collection</code>.</p>
     *
     * <p>Expected HTTP GET and request '/type/broker_dealer'.</p>
     */
    @RequestMapping(value="/type/broker_dealer", method=RequestMethod.GET)
    public @ModelAttribute(SEARCH_MODEL_KEY) Collection<BrokerDealer> list(HttpServletRequest request) {
		SecureUserDetails user = (SecureUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		request.getSession().setAttribute("user", user);
		request.getSession().setAttribute("tab_selection", "Dealer");
		request.getSession().setAttribute("selectionLists", policyDao.findSelectionLists());

        return policyDao.findBrokerDealers();
    }

    /**
     * <p>Searches for a dealers and returns it in a
     * <code>BrokerDealer</code> object.</p>
     *
     * <p>Expected HTTP GET and request '/dealer/detail'.</p>
     */
    @RequestMapping(value="/dealer/detail", method=RequestMethod.GET)
    public @ModelAttribute(DETAIL_MODEL_KEY) BrokerDealer display(@RequestParam("id") Integer id, @RequestParam(value="p_id", required=false) Integer p_id, Model model) {

		/*
		 * If a p_id is passed then add the person as a dealer contact
		 */
		if(p_id != null){


			Collection<ContactCase> contacts = policyDao.findContactsByPersonIdAndDealerId(p_id, id);

	        if (contacts.isEmpty()) {

				Person person = personDao.findPersonById(p_id);
				person.setBrokerdealerid(id);
				personDao.save(person);

				model.addAttribute("statusMessageKey", "contact.dealer.form.msg.success");
			}else{
        		model.addAttribute("statusMessageKey", "contact.form.duplicate.type.error");
			}
		}

        BrokerDealer dealer = policyDao.findBrokerDealerById(id);
        Collection<ContactCase> contacts = policyDao.findContactsByBrokerDealerId(id);
        Collection<DealerContact> contacts_no_dup = policyDao.findDealerContacts(id);
        Collection<String> cases = policyDao.findCasesByBrokerDealerId(id);

		for (ContactCase contact : contacts) {
			contact.setCases(policyDao.findCasesByContactId(contact.getContactid()));
		}

        dealer.setContactCases(contacts);
        dealer.setContacts(contacts_no_dup);
        dealer.setCases(cases);
        return dealer;
    }


}
