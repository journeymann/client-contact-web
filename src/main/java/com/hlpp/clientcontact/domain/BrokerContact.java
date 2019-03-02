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

package com.hlpp.clientcontact.domain;


/**
 *
 * @author Casmon Gordon
 */

public class BrokerContact {

	private Integer id = null;
	private Integer brokerid = null;
	private String companyName = null;
	private Integer contactid = null;
	private String contactName = null;
	private String contactType = null;
	private String casecodes = null;


    /**
     * Gets id (primary key).
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets id (primary key).
     */
    public void setId(Integer id) {
        this.id = id;
    }


    /**
     * Gets broker id
     */
    public Integer getBrokerid() {
        return brokerid;
    }

    /**
     * Sets broker id
     */
    public void setBrokerid(Integer brokerid) {
        this.brokerid = brokerid;
    }


    /**
     * Gets contact type.
     */
    public String getContactType() {
        return contactType;
    }

    /**
     * Sets contact type.
     */
    public void setContactType(String contactType) {
        this.contactType = contactType;
    }

    /**
     * Gets seq
     */
    public Integer getContactid() {
        return contactid;
    }

    /**
     * Sets seq
     */
    public void setContactid(Integer contactid) {
        this.contactid = contactid;
    }


    /**
     * Gets contact company name.
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * Sets contact company name .
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }


    /**
     * Gets contact name.
     */
    public String getContactName() {
        return contactName;
    }

    /**
     * Sets contact name .
     */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }


    /**
     * Gets contact casaes.
     */
    public String getCasecodes() {
        return casecodes;
    }

    /**
     * Sets contact cases .
     */
    public void setCasecodes(String casecodes) {
        this.casecodes = casecodes;
    }



}