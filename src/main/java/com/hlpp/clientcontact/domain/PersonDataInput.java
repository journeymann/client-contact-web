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

/**
 *
 * @author Casmon Gordon
 */
public class PersonDataInput{

    private String param1 = null;

	private String[] disp = null;

	/**
	 * @return param1
	 */
	public String getParam1() {
		return param1;
	}

	/**
	 * @param param1 to set
	 */
	public void setParam1(String param1) {
		this.param1 = param1;
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

}
