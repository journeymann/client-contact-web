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

import com.hlpp.clientcontact.domain.Field;
import com.hlpp.clientcontact.dao.PolicyDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * Desc form controller.
 *
 * @author Casmon Gordon
 */
@Controller
public class DescController {
    public static final String SEARCH_VIEW_PATH_KEY = "/type/contacts";

    public static final String SEARCH_VIEW_KEY = "redirect:index.html";
    public static final String SEARCH_MODEL_KEY = "fields";
    public static final String DETAIL_MODEL_KEY = "field";

    @Autowired
    protected PolicyDao policyDao = null;

    /**
     * For every request for this controller, this will
     * create a field instance for the form.
     */
    @ModelAttribute
    public Field newRequest(@RequestParam(required=false) Integer id) {
        return (id != null ? policyDao.findFieldById(id) : new Field());
    }

    /**
     * <p>Field form request.</p>
     *
     * <p>Expected HTTP GET and request '/field/form'.</p>
     */
    @RequestMapping(value="/misc/form", method=RequestMethod.GET)
    public void form() {}


    /**
     * <p>Searches for all fields and returns them in a
     * <code>Collection</code>.</p>
     *
     * <p>Expected HTTP GET and request '/policy/desc'.</p>
     */
    @RequestMapping(value="/policy/desc", method=RequestMethod.GET)
    public @ModelAttribute(DETAIL_MODEL_KEY) Field find(@RequestParam(required=false) Integer id) {
        return (id != null ? policyDao.findFieldById(id) : new Field());
    }

    /**
     * <p>Searches for all fields and returns them in a
     * <code>Collection</code>.</p>
     *
     * <p>Expected HTTP GET and request '/compliance/desc'.</p>
     */
    @RequestMapping(value="/compliance/desc", method=RequestMethod.GET)
    public @ModelAttribute(DETAIL_MODEL_KEY) Field get(@RequestParam(required=false) Integer id) {
        return (id != null ? policyDao.findFieldById(id) : new Field());
    }

    /**
     * <p>Searches for all fields and returns them in a
     * <code>Collection</code>.</p>
     *
     * <p>Expected HTTP GET and request '/field/search'.</p>
     */
    @RequestMapping(value="/misc/desc_all", method=RequestMethod.GET)
    public @ModelAttribute(SEARCH_MODEL_KEY) Collection<Field> search() {
        return policyDao.findFields();
    }

}
