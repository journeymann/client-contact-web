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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Annotation configured library bean.
 *
 * @author Casmon Gordon
 */
@Entity
@Table(name="MASTER_SYSTEM")
public class Library extends AbstractMaintenance implements Serializable {

    private static final long serialVersionUID = 7851794269407495684L;

	private String library = null;
	private Integer seq = null;

    /**
     * Gets id (primary key).
     */
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
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
     * Gets library
     */
    @Column(name = "SYSTEM_CODE")
    public String getLibrary() {
        return library;
    }

    /**
     * Sets library.
     */
    public void setLibrary(String library) {
        this.library = library;
    }


    /**
     * Gets seq
     */
    @Column(name = "SEQ")
    public Integer getSequence() {
        return seq;
    }

    /**
     * Sets seq.
     */
    public void setSequence(Integer seq) {
        this.seq = seq;
    }


	/**
     * Returns a string representation of the object.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(this.getClass().getName() + "-");
        sb.append("  library=" + library);
        sb.append("  seq=" + seq);

        return sb.toString();
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

        final Library other = (Library) obj;

        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id)) {
            return false;
        }

        return true;
    }

    /**
     * Gets value
     */
    @Override
    @Transient
    public String getValue() {
        return this.library;
    }


}