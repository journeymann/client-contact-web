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

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.NamedNativeQuery;

/**
 * Annotation configured person bean.
 *
 * @author Casmon Gordon
 */
public class PersonDataOutput implements Serializable {

    private static final long serialVersionUID = -8712872385957386182L;

    private Integer id = null;
    private Integer contactid = null;

    private String firstname = null;
    private String lastname = null;

    private String contacttype = null;
    private String jobtitle = null;
    private String workcompany = null;

    private String email = null;
    private String cellphone = null;
    private String addr1 = null;
    private String addr2 = null;
    private String city = null;
    private String state = null;
    private String zip = null;
    private String country = null;

    private String title = null;
    private String workphone = null;
    private String fax = null;
    private String mainaddress = null;
    private String brokercompany = null;
    private String brokerdealer = null;

    private String workphone2 = null;
    private String fax2 = null;
    private String notes = null;


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
     * Gets contactid
     */
    public Integer getContactid() {
        return contactid;
    }

    /**
     * Sets contactid
     */
    public void setContactid(Integer contactid) {
        this.contactid = contactid;
    }

	/**
	 * @return the firstname
	 */
	public String getFirstname() {
		return firstname;
	}

	/**
	 * @param firstname the firstname to set
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	/**
	 * @return the lastname
	 */
	public String getLastname() {
		return lastname;
	}

	/**
	 * @param lastname the lastname to set
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}


	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param privacynotice the privacynotice to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the phone
	 */
	public String getCellphone() {
		return cellphone;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setCellphone(String phone) {
		this.cellphone = phone;
	}

	/**
	 * @return the contacttype
	 */
	public String getContacttype() {
		return contacttype;
	}

	/**
	 * @param contacttype the contacttype to set
	 */
	public void setContacttype(String contacttype) {
		this.contacttype = contacttype;
	}

	/**
	 * @return the jobtitle
	 */
	public String getJobtitle() {
		return jobtitle;
	}

	/**
	 * @param jobtitle the jobtitle to set
	 */
	public void setJobtitle(String jobtitle) {
		this.jobtitle = jobtitle;
	}

	/**
	 * @return the workcompany
	 */
	public String getWorkcompany() {
		return workcompany;
	}

	/**
	 * @param workcompany the workcompany to set
	 */
	public void setWorkcompany(String workcompany) {
		this.workcompany = workcompany;
	}


	/**
	 * @return the address
	 */
	public String getAddr1() {
		return addr1;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}

	/**
	 * @return the address
	 */
	public String getAddr2() {
		return addr2;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}


	/**
	 * @return the address
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param address the address to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the address
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param address the address to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the address
	 */
	public String getZip() {
		return zip;
	}

	/**
	 * @param address the address to set
	 */
	public void setZip(String zip) {
		this.zip = zip;
	}

	/**
	 * @return the address
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param address the address to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the workphone
	 */
	public String getWorkphone() {
		return workphone;
	}

	/**
	 * @param workphone the workphone to set
	 */
	public void setWorkphone(String workphone) {
		this.workphone = workphone;
	}

	/**
	 * @return the workphone2
	 */
	public String getWorkphone2() {
		return workphone2;
	}

	/**
	 * @param workphone2 the workphone2 to set
	 */
	public void setWorkphone2(String workphone) {
		this.workphone2 = workphone;
	}

	/**
	 * @return the fax
	 */
	public String getFax() {
		return fax;
	}

	/**
	 * @param fax the fax to set
	 */
	public void setFax(String fax) {
		this.fax = fax;
	}

	/**
	 * @return the fax2
	 */
	public String getFax2() {
		return fax2;
	}

	/**
	 * @param fax2 the fax2 to set
	 */
	public void setFax2(String fax) {
		this.fax2 = fax;
	}


	/**
	 * @return the mainaddress
	 */
	public String getMainaddress() {
		return mainaddress;
	}

	/**
	 * @param mainaddress the mainaddress to set
	 */
	public void setMainaddress(String mainaddress) {
		this.mainaddress = mainaddress;
	}

	/**
	 * @return the brokercompany
	 */
	public String getBrokercompany() {
		return brokercompany;
	}

	/**
	 * @param brokercompany the brokercompany to set
	 */
	public void setBrokercompany(String brokercompany) {
		this.brokercompany = brokercompany;
	}

	/**
	 * @return the brokerdealer
	 */
	public String getBrokerdealer() {
		return brokerdealer;
	}

	/**
	 * @param brokerdealer the brokerdealer to set
	 */
	public void setBrokerdealer(String brokerdealer) {
		this.brokerdealer = brokerdealer;
	}

	/**
	 * @return the notes
	 */
	public String getNotes() {
		return notes;
	}

	/**
	 * @param notes the notes to set
	 */
	public void setNotes(String notes) {
		this.notes = notes;
	}


}
