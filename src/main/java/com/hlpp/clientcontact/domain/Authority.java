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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;


/**
 * Annotation configured person bean.
 *
 * @author Casmon Gordon
 */

@Entity
@Table(name="authorities")
public class Authority implements Serializable{

    private static final long serialVersionUID = -8712872385957386187L;

 	private User user;
 	private String authority;
 	private String username;

 	@ManyToOne(fetch=FetchType.EAGER)
 	@JoinColumn(name="username",nullable=false)
 	@PrimaryKeyJoinColumn(name="fk_authorities_users")
 	public User getUser() {
 	 	return user;
 	}

 	public void setUser(User user) {
 		this.user = user;
 	}


 	@Column(name="authority",length = 50,nullable=false)
 	public String getAuthority() {
 		return authority;
 	}

 	public void setAuthority(String authority) {
 		this.authority = authority;
 	}

    /**
     * Gets username (primary key).
     */
    @Id
    @Column(name="USERNAME")
	public String getUsername(){
		return this.username;
	}

	public void setUsername(String username){
		this.username = username;
	}


}