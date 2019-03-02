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


public class DataItem implements Serializable,Comparable<DataItem> {


    public static final int CLIENTGROUP = 1;
    public static final int SERIES = 2;
    public static final int TEAM = 3;
    public static final int CASEMANAGER = 4;
    public static final int SYSTEM = 5;

    private String key = null;
	private String value = null;


    /**
     * Gets key.
     */
    public String getKey() {
        return key;
    }

    /**
     * Sets key.
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * Gets value.
     */
    public String getValue() {
        return value;
    }

    /**
     * Sets value.
     */
    public void setValue(String value) {
        this.value = value;
    }


    public int compareTo(DataItem d) {
        return (this.getValue().compareTo(d.getValue()));
    }


}