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

package com.hlpp.clientcontact.domain.maintenance;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

/**
 *
 * @author Casmon Gordon
 */
@MappedSuperclass
public abstract class AbstractMaintenance {

	protected Integer id = null;
    protected Date update_date = null;
    protected Integer user_id = null;
    /**
     * Returns a hash code value for the object.
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;

        result = prime * result + ((id == null) ? 0 : id.hashCode());

        return result;
    }

    public abstract boolean equals(Object obj);

    @Transient
    public abstract String getValue();

	/**
	 * @return the user_id
	 */
    @Column(name = "UPDATED_BY")
	public Integer getUpdateBy() {
		return user_id;
	}

	/**
	 * @param user_id the user_id to set
	 */
	public void setUpdateBy(Integer user_id) {
		this.user_id = user_id;
	}

	/**
	 * @return the update_date
	 */
    @Column(name = "UPDATED_DATE")
	public Date getUpdateDate() {
		return update_date;
	}

	/**
	 * @param update_date the update_date to set
	 */
	public void setUpdateDate(Date update_date) {
		this.update_date = update_date;
	}



}