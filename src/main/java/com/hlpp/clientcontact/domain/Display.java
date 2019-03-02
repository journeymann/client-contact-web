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


/**
 *
 * @author Casmon Gordon
 */
public class Display implements Serializable {

    private static final long serialVersionUID = 7851794269407495684L;
	public static final int EMAIL = 1;
	public static final int EMAIL_NO_DUP = 2;
	public static final int ADDRESS = 3;
	public static final int PHONE = 4;
	public static final int ALL = 5;

	private Integer id = null;
	private Integer yes = null;
	private String desc = null;


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
     * Gets desc.
     */

    public String getDesc() {
        return desc;
    }

    /**
     * Sets desc.
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }


    /**
     * Gets Yes value.
     */
    public Integer getYes() {
        return yes;
    }

    /**
     * Sets Yes value.
     */
    public void setYes(Integer yes) {
        this.yes = yes;
    }

}