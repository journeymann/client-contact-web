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

import com.hlpp.clientcontact.dao.util.MaintenanceHelper;
import com.hlpp.clientcontact.domain.maintenance.AbstractMaintenance;
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
import javax.servlet.http.HttpServletResponse;

import com.hlpp.clientcontact.security.SecureUserDetails;
import org.springframework.security.core.context.SecurityContextHolder;


/**
 * Maintenance form controller.
 *
 * @author Casmon Gordon
 */
@Controller
public class MaintenanceController {
    public static final String SEARCH_VIEW_PATH_KEY = "/type/contacts";

    public static final String SEARCH_VIEW_KEY = "redirect:index.html";
    public static final String SEARCH_MODEL_KEY = "maintenancehelper";
    public static final String DETAIL_MODEL_KEY = "maint";


    @Autowired
    protected PolicyDao policyDao = null;

    /**
     * For every request for this controller, this will
     * create a query instance for the form.
     */
    @ModelAttribute
    public MaintenanceHelper newRequest(@RequestParam(required=false) Integer id) {
        return policyDao.findMaintenanceHelper();
    }

    /**
     * <p>Maintenance form request.</p>
     *
     * <p>Expected HTTP GET and request '/query/form'.</p>
     */
    @RequestMapping(value="/maintenance/form", method=RequestMethod.GET)
    public void form() {}

    /**
     * <p>Saves a query.</p>
     *
     * <p>Expected HTTP POST and request '/query/form'.</p>
     */
    @RequestMapping(value="/maintenance/form", method=RequestMethod.POST)
    public void form(MaintenanceHelper helper, Model model) {
		//TODO
        model.addAttribute("statusMessageKey", "maintenance.form.msg.success");
    }

    /**
     * <p>Searches for all tables and returns them in a
     * <code>Collection</code>.</p>
     *
     * <p>Expected HTTP GET and request '/type/maintenence'.</p>
     */
    @RequestMapping(value="/type/maintenance", method=RequestMethod.GET)
    public @ModelAttribute(SEARCH_MODEL_KEY) MaintenanceHelper list3(HttpServletRequest request, ModelMap model) {
		SecureUserDetails user = (SecureUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		request.getSession().setAttribute("user", user);
		request.getSession().setAttribute("tab_selection", "Maintenance");

        return policyDao.findMaintenanceHelper();
    }


	@ExceptionHandler(NoSuchRequestHandlingMethodException.class)
	public ModelAndView handleException (NoSuchRequestHandlingMethodException ex) {
		ModelAndView mav = new ModelAndView();
		return mav;
	}

    @RequestMapping(value="/json/maint", method=RequestMethod.GET)
	public String find(@RequestParam("value") String searchString, final ModelMap model) {
		Collection ilist = null;


		if(searchString.equalsIgnoreCase("ClientGroup")){
			ilist = policyDao.findMaintenanceHelper().getClientGroups();
		}else if(searchString.equalsIgnoreCase("CaseManager")){
			ilist = policyDao.findMaintenanceHelper().getCaseManagers();
		}else if(searchString.equalsIgnoreCase("Team")){
			ilist = policyDao.findMaintenanceHelper().getTeams();
		}else if(searchString.equalsIgnoreCase("Series")){
			ilist = policyDao.findMaintenanceHelper().getSeries();
		}else if(searchString.equalsIgnoreCase("Country")){
			ilist = policyDao.findMaintenanceHelper().getCountries();
		}else if(searchString.equalsIgnoreCase("Library")){
			ilist = policyDao.findMaintenanceHelper().getLibraries();
		}else{
			ilist = new ArrayList<AbstractMaintenance>();
	    }


		Collection<JSONObject> list = new ArrayList<JSONObject>();
		JSONObject obj = new JSONObject();

		try{
			for (AbstractMaintenance s : (ArrayList<AbstractMaintenance>)ilist) {
				obj = new JSONObject();
				obj.put("value", s.getValue());
				list.add(obj);
			}
		}catch(JSONException je){
			//TODO
		}

		JSONArray json = new JSONArray();

		model.addAttribute("maint", (json.put(list)).toString());

		return "jsonView";
	}


    /**
     * <p>Searches for table and returns values in a
     * <code>Collection</code>.</p>
     *
     * <p>Expected HTTP GET and request '/type/edit_table'.</p>
     */
    @RequestMapping(value="/type/edit_table", method=RequestMethod.GET)
    public @ModelAttribute(DETAIL_MODEL_KEY) MaintenanceHelper find2(@RequestParam(required=false) String searchString, final HttpServletResponse response) {

		response.setHeader("Cache-Control", "no-cache");

		MaintenanceHelper helper = policyDao.findMaintenanceHelper();
		helper.setTableSelected(searchString);

		return helper;

    }


    /**
     * <p>Updates maintenance tables
     *
     * <p>Expected HTTP GET and request '/ajax/maint'.</p>
     */
    @RequestMapping(value="/ajax/maint", method=RequestMethod.GET)
    public String update(@RequestParam(required=false) String value, String searchString, Integer id, String opt ) {

		String result = policyDao.updateMaintTable(value, searchString, id, opt);

		return "type/edit_table";

    }




}
