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
import java.util.HashSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * Annotation configured person bean.
 *
 * @author Casmon Gordon
 */
@Entity
@Table(name="USERS")
public class User implements Serializable {

    private static final long serialVersionUID = -8712872385957386188L;

    private Integer id = 0;
	private String username;
	private String password;
	private boolean enabled;
	private String firstname;
	private String lastname;
	private String department;
	private String email;
	private String roletype;
	private String status;
	private String groupcode;
	private String shortname;
	private String updateby;
	private Date updatedate;
    private Date created = null;

	private Set<Authority>authorities = new HashSet<Authority>();


    /**
     * Gets id (primary key). //TODO
     */
    //@Id TODO
    //@GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="USER_ID")
    public Integer getId() {
        return id;
    }

    /**
     * Sets id (primary key). //TODO
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Gets username (primary key).
     */
    @Id
    @Column(name="LOGIN_NAME")
	public String getUsername(){
		return this.username;
	}

	public void setUsername(String username){
		this.username = username;
	}

    /**
     * Gets password.
     */
    @Column(name="PASSWORD")
	public String getPassword(){
		return this.password;
	}

	public void setPassword(String password){
		this.password = password;
	}

    /**
     * Gets enabled.
     */
    @Column(name="ENABLED")
	public boolean getEnabled(){
		return this.enabled;
	}

	public void setEnabled(boolean enabled){
		this.enabled = enabled;
	}

    /**
     * Gets department
     */
    @Column(name="DEPARTMENT")
	public String getDepartment(){
		return this.department;
	}

	public void setDepartment(String department){
		this.department = department;
	}

    /**
     * Gets email.
     */
    @Column(name="EMAIL")
	public String getEmail(){
		return this.email;
	}

	public void setEmail(String email){
		this.email = email;
	}

    /**
     * Gets date created.
     */
    @Column(name="CREATED")
    public Date getCreated() {
        return created;
    }

    /**
     * Sets date created.
     */
    public void setCreated(Date created) {
        this.created = created;
    }

    /**
	 * @return the firstname
	 */
    @Column(name="FIRST_NAME")
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
    @Column(name="LAST_NAME")
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
	 * @return the roletype
	 */
    @Column(name="ROLE_TYPE")
	public String getRoletype() {
		return roletype;
	}

	/**
	 * @param roletype the roletype to set
	 */
	public void setRoletype(String roletype) {
		this.roletype = roletype;
	}

	/**
	 * @return the status
	 */
    @Column(name="STATUS")
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the groupcode
	 */
    @Column(name="USER_GROUP")
	public String getGroupcode() {
		return groupcode;
	}

	/**
	 * @param groupcode the groupcode to set
	 */
	public void setGroupcode(String groupcode) {
		this.groupcode = groupcode;
	}

	/**
	 * @return the shortname
	 */
    @Column(name="SHORT_NAME")
	public String getShortname() {
		return shortname;
	}

	/**
	 * @param shortname the shortname to set
	 */
	public void setShortname(String shortname) {
		this.shortname = shortname;
	}

	/**
	 * @return the updateby
	 */
    @Column(name="UPDATED_BY")
	public String getUpdateby() {
		return updateby;
	}

	/**
	 * @param updateby the updateby to set
	 */
	public void setUpdateby(String updateby) {
		this.updateby = updateby;
	}

	/**
	 * @return the updatedate
	 */
    @Column(name="UPDATED_DATE")
	public Date getUpdatedate() {
		return updatedate;
	}

	/**
	 * @param updatedate the updatedate to set
	 */
	public void setUpdatedate(Date updatedate) {
		this.updatedate = updatedate;
	}

	/**
	 * @return the fullname
	 */
	public String getFullname() {
		return firstname + " " + lastname;
	}

	/**
	 * @param updateby the updateby to set
	 */
	public void setFullname(String fullname) {

		if(fullname==null) return;
		this.firstname = fullname.substring(0, fullname.indexOf(" "));
		this.lastname = fullname.substring(fullname.indexOf(" "), fullname.length());
	}

	/**
     * Gets list of <code>Authorities</code>es.
     */
 	@OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL,mappedBy="user")
    @JoinColumn(name="USERNAME", nullable=false)
 	public Set<Authority> getAuthorities() {
 		return authorities;
 	}

 	public void setAuthorities(Set<Authority> authorities) {
 		this.authorities = authorities;
 	}

}