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

package com.hlpp.clientcontact.dao.util;

import java.io.Serializable;
import java.util.Collection;
import java.util.ArrayList;

import com.hlpp.clientcontact.domain.Attribute;
import com.hlpp.clientcontact.domain.CFilter;
import com.hlpp.clientcontact.domain.Filter;
import com.hlpp.clientcontact.domain.SubFilter;
import com.hlpp.clientcontact.domain.Display;

/**
 *
 * @author Casmon Gordon
 */
public class QueryHelper implements Serializable {

    private Collection<Attribute> attributes = null;
    private Collection<Filter> pfilter = null;
    private Collection<SubFilter> sfilter = null;
    private Collection<Display> displays = null;
    private Collection<CFilter> cfilter = null;
	private String pval = null;
	private String sval = null;
	private String cval = null;
	private String[] yes = null;
	private String[] no = null;
	private String[] disp = null;


	/**
     * Gets list of <code>Attribute</code>es.
     */
    public Collection<Attribute> getAttributes() {
        return attributes;
    }

    /**
     * Sets list of <code>Attribute</code>es.
     */
    public void setAttributes(Collection<Attribute> attributes) {
        this.attributes = attributes;
    }

	/**
     * Gets list of <code>Filter</code>es.
     */
    public Collection<Filter> getPfilters() {
        return pfilter;
    }

    /**
     * Sets list of <code>Filter</code>es.
     */
    public void setPfilters(Collection<Filter> pfilter) {
        this.pfilter = pfilter;
    }

	/**
     * Gets list of <code>Filter</code>es.
     */
    public Collection<SubFilter> getSfilters() {
        return sfilter;
    }

    /**
     * Sets list of <code>Filter</code>es.
     */
    public void setSfilters(Collection<SubFilter> sfilter) {
        this.sfilter = sfilter;
    }

	/**
     * Gets list of <code>Display</code>es.
     */
    public Collection<Display> getDisplays() {
        return displays;
    }

    /**
     * Sets list of <code>Display</code>es.
     */
    public void setDisplays(Collection<Display> displays) {
        this.displays = displays;
    }

    /**
     * Gets pval
     */
    public String getPval() {
        return pval;
    }

    /**
     * Sets pval
     */
    public void setPval(String pval) {
        this.pval = pval;
    }


    /**
     * Gets sval
     */
    public String getSval() {
        return sval;
    }

    /**
     * Sets sval
     */
    public void setSval(String sval) {
        this.sval = sval;
    }


    /**
     * Gets yes
     */
    public String[] getYes() {
        return yes;
    }

    /**
     * Sets yes
     */
    public void setYes(String[] yes) {
        this.yes = yes;
    }

    /**
     * Gets no
     */
    public String[] getNo() {
        return no;
    }

    /**
     * Sets no
     */
    public void setNo(String[] no) {
        this.no = no;
    }

    /**
     * Gets disp
     */
    public String[] getDisp() {
        return disp;
    }

    /**
     * Sets disp
     */
    public void setDisp(String[] disp) {
        this.disp = disp;
    }

	/**
     * Gets list of <code>Filter</code>es.
     */
    public Collection<CFilter> getCfilters() {
        return cfilter;
    }

    /**
     * Sets list of <code>Filter</code>es.
     */
    public void setCfilters(Collection<CFilter> cfilter) {
        this.cfilter = cfilter;
    }


    /**
     * Gets cval
     */
    public String getCval() {
        return cval;
    }

    /**
     * Sets cval
     */
    public void setCval(String cval) {
        this.cval = cval;
    }


    /**
     * Removes unwanted attributes
     */
    public void cleanup() {
		if(this.attributes.isEmpty()) return;

		Collection<Attribute> list = new ArrayList<Attribute>();

		for (Attribute a : this.attributes ) {
			if (a.getId() != 6 && a.getId() != 22) list.add(a);
		}

		this.attributes = list;

    }

}