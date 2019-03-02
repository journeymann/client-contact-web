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

import com.hlpp.clientcontact.domain.Policy;
import com.hlpp.clientcontact.domain.ContactCase;
import com.hlpp.clientcontact.domain.Broker;
import com.hlpp.clientcontact.domain.BrokerDealer;
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
 * Compliance form controller.
 *
 * @author Casmon Gordon
 */
@Controller
public class ComplianceController {
    public static final String SEARCH_VIEW_PATH_KEY = "/type/contacts";

    public static final String SEARCH_VIEW_KEY = "redirect:index.html";
    public static final String SEARCH_MODEL_KEY = "policys";
    public static final String DETAIL_MODEL_KEY = "policy";

    @Autowired
    protected PolicyDao policyDao = null;

    /**
     * For every request for this controller, this will
     * create a policy instance for the form.
     */
    @ModelAttribute
    public Policy newRequest(@RequestParam(required=false) Integer id) {
        return (id != null ? policyDao.findPolicyById(id) : new Policy());
    }

    /**
     * <p>Compliance form request.</p>
     *
     * <p>Expected HTTP GET and request '/compliance/form'.</p>
     */
    @RequestMapping(value="/compliance/form", method=RequestMethod.GET)
    public void form() {}

    /**
     * <p>Saves a policy.</p>
     *
     * <p>Expected HTTP POST and request '/compliance/form'.</p>
     */
    @RequestMapping(value="/compliance/form", method=RequestMethod.POST)
    public void form(Policy policy, Model model) {
        if (policy.getCreated() == null) {
            policy.setCreated(new Date());
        }

        Policy result = policyDao.save(policy);

        // set id from create
        if (policy.getId() == null) {
            policy.setId(result.getId());
        }

        model.addAttribute("statusMessageKey", "policy.form.msg.success");
    }

    /**
     * <p>Deletes a policy.</p>
     *
     * <p>Expected HTTP POST and request '/compliance/delete'.</p>
     */
    @RequestMapping(value="/compliance/delete", method=RequestMethod.POST)
    public @ModelAttribute(SEARCH_MODEL_KEY) Collection<Policy> delete(Policy policy) {
		SecureUserDetails user = (SecureUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Integer userid = user.getUserid();

        policyDao.delete(policy, userid);

        return policyDao.findPolicys();
    }

    /**
     * <p>Searches for all policys and returns them in a
     * <code>Collection</code>.</p>
     *
     * <p>Expected HTTP GET and request '/compliance/search'.</p>
     */
    @RequestMapping(value="/compliance/search", method=RequestMethod.GET)
    public @ModelAttribute(SEARCH_MODEL_KEY) Collection<Policy> search() {
        return policyDao.findPolicys();
    }


    /**
     * <p>Searches for all policys and returns them in a
     * <code>Collection</code>.</p>
     *
     * <p>Expected HTTP GET and request '/type/compliance'.</p>
     */
    @RequestMapping(value="/type/compliance", method=RequestMethod.GET)
    public @ModelAttribute(SEARCH_MODEL_KEY) Collection<Policy> list(HttpServletRequest request) {
		SecureUserDetails user = (SecureUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		request.getSession().setAttribute("user", user);
		request.getSession().setAttribute("tab_selection", "Compliance");

        return policyDao.findPolicys();
    }

    /**
     * <p>Searches for a policys and returns it in a
     * <code>Policy</code> object.</p>
     *
     * <p>Expected HTTP GET and request '/policy/detail'.</p>
     */
    @RequestMapping(value="/compliance/detail", method=RequestMethod.GET)
    public @ModelAttribute(DETAIL_MODEL_KEY) Policy display(@RequestParam(required=false) Integer id) {
        Policy policy= policyDao.findPolicyById(id);
        Collection<ContactCase> contacts = policyDao.findContactsByPolicyId(id);
        Collection<Broker> brokers = policyDao.findBrokersByPolicyId(id);
        Collection<BrokerDealer> dealers = policyDao.findBrokerDealersByPolicyId(id);
        policy.setContactCases(contacts);
        policy.setBrokers(brokers);
        policy.setBrokerDealers(dealers);
        return policy;
    }

}
