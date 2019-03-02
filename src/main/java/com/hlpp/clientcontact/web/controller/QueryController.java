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
import java.util.ArrayList;

import com.hlpp.clientcontact.dao.util.QueryHelper;
import com.hlpp.clientcontact.domain.SubFilter;
import com.hlpp.clientcontact.dao.PolicyDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;

import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import com.hlpp.clientcontact.web.json.impl.JSONArray;
import com.hlpp.clientcontact.web.json.impl.JSONObject;
import com.hlpp.clientcontact.web.json.impl.JSONException;

import javax.servlet.http.HttpServletRequest;

import com.hlpp.clientcontact.security.SecureUserDetails;
import org.springframework.security.core.context.SecurityContextHolder;


/**
 * Query form controller.
 *
 * @author Casmon Gordon
 */
@Controller
public class QueryController {

    public static final String SEARCH_VIEW_PATH_KEY = "/type/contacts";

    public static final String SEARCH_VIEW_KEY = "redirect:index.html";
    public static final String SEARCH_MODEL_KEY = "queryhelper";

    @Autowired
    protected PolicyDao policyDao = null;

    /**
     * For every request for this controller, this will
     * create a query instance for the form.
     */
    @ModelAttribute
    public QueryHelper newRequest(@RequestParam(required=false) Integer id) {
        return (id != null ? policyDao.findQueryHelper() : new QueryHelper());
    }

    /**
     * <p>Query form request.</p>
     *
     * <p>Expected HTTP GET and request '/query/form'.</p>
     */
    @RequestMapping(value="/query/form", method=RequestMethod.GET)
    public void form() {}

    /**
     * <p>Saves a query.</p>
     *
     * <p>Expected HTTP POST and request '/query/form'.</p>
     */
    @RequestMapping(value="/query/form", method=RequestMethod.POST)
    public void form(QueryHelper query, Model model) {
		//TODO
        model.addAttribute("statusMessageKey", "query.form.msg.success");
    }

    /**
     * <p>Searches for all querys and returns them in a
     * <code>Collection</code>.</p>
     *
     * <p>Expected HTTP GET and request '/query/search'.</p>
     */
    @RequestMapping(value="/query/search", method=RequestMethod.GET)
    public @ModelAttribute(SEARCH_MODEL_KEY) QueryHelper search() {
        return policyDao.findQueryHelper();
    }


    /**
     * <p>Searches for all querys and returns them in a
     * <code>Collection</code>.</p>
     *
     * <p>Expected HTTP GET and request '/type/query'.</p>
     */
    @RequestMapping(value="/type/query", method=RequestMethod.GET)
    public @ModelAttribute(SEARCH_MODEL_KEY) QueryHelper list(HttpServletRequest request) {
		SecureUserDetails user = (SecureUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		request.getSession().setAttribute("user", user);
		request.getSession().setAttribute("tab_selection", "Query_Policy");

        return policyDao.findQueryHelper();
    }

    /**
     * <p>Searches for all querys and returns them in a
     * <code>Collection</code>.</p>
     *
     * <p>Expected HTTP GET and request '/type/query_person'.</p>
     */
    @RequestMapping(value="/type/query_person", method=RequestMethod.GET)
    public @ModelAttribute(SEARCH_MODEL_KEY) QueryHelper list2(HttpServletRequest request) {
		SecureUserDetails user = (SecureUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		request.getSession().setAttribute("user", user);
		request.getSession().setAttribute("tab_selection", "Query_Person");

        return policyDao.findQueryHelper();
    }


    /**
     * <p>Searches for selected querys and returns them in a
     * <code>Collection</code>.</p>
     *
     * <p>Expected HTTP GET and request '/json/dd_query'.</p>
     */
    /*@RequestMapping(value="/json/dd_query", method=RequestMethod.GET)
    public @ModelAttribute(SEARCH_MODEL_KEY) QueryHelper populate(@RequestParam(value="value", required=true) String selectedPrimary){
        return policyDao.findQueryHelper(selectedPrimary);
    }*/


    @RequestMapping(value="/json/query", method=RequestMethod.GET)
	public String find(@RequestParam("value") String searchString, final ModelMap model) {
		Collection<SubFilter> ilist = policyDao.findQueryHelper(searchString).getSfilters();

		Collection<JSONObject> list = new ArrayList<JSONObject>();
		JSONObject obj = new JSONObject();

		try{
			for (SubFilter s : ilist) {
				obj = new JSONObject();
				obj.put("value", s.getValue());
				list.add(obj);
			}
		}catch(JSONException je){
			//TODO
		}

		JSONArray json = new JSONArray();

		model.addAttribute("query", (json.put(list)).toString());

		return "jsonView";
	}


      @ExceptionHandler(NoSuchRequestHandlingMethodException.class)
      public ModelAndView handleException (NoSuchRequestHandlingMethodException ex) {
        ModelAndView mav = new ModelAndView();
        return mav;
      }


}
