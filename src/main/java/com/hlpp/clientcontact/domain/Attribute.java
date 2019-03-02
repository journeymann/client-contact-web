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
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.persistence.NamedNativeQuery;

/**
 * Annotation configured Filter bean.
 *
 * @author Casmon Gordon
 */
@Entity
@NamedNativeQuery(name = "findReportAttributeInOrder", query = "select * from field_desc t order by field_id", resultClass = Attribute.class)
public class Attribute implements Serializable {

    private static final long serialVersionUID = 7851794269407495684L;

	private Integer id = null;
	private Integer yes = null;
	private Integer no = null;
	private String attribute = null;

    /**
     * Gets id (primary key).
     */
    @Id
    @Column(name = "FIELD_ID")
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
     * Gets filter.
     */
    @Column(name = "FIELD_NAME")
    public String getAttribute() {
        return attribute;
    }

    /**
     * Sets name.
     */
    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }


    /**
     * Gets Yes value.
     */
	@Transient
    public Integer getYes() {
        return yes;
    }

    /**
     * Sets Yes value.
     */
    public void setYes(Integer yes) {
        this.yes = yes;
    }


    /**
     * Gets No value.
     */
	@Transient
    public Integer getNo() {
        return no;
    }

    /**
     * Sets No value.
     */
    public void setNo(Integer no) {
        this.no = no;
    }


	/**
     * Returns a string representation of the object.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(this.getClass() + "-");
        sb.append("  id=" + id);
        sb.append("  attribute=" + attribute);
        sb.append("  yes=" + yes);
        sb.append("  no=" + no);

        return sb.toString();
    }

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

    /**
     * Indicates whether some other object is equal to this one.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }

        final Attribute other = (Attribute) obj;

        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id)) {
            return false;
        }

        return true;
    }



}