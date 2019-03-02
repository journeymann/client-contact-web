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

package com.hlpp.clientcontact.web.json;


import com.hlpp.clientcontact.dao.PolicyDao;
import com.hlpp.clientcontact.dao.PolicyDaoImpl;
import com.hlpp.clientcontact.web.json.impl.JSONArray;

public class SearchDelegate {

	public String lookupCases(String term)
	{

		PolicyDao dao = new PolicyDaoImpl();
		JSONArray ar = new JSONArray();

		System.out.println("***********************************About to Add case number to list**************************");

		for (String s : dao.findCaseNumbers(term)) {

				ar.put(s);
				System.out.println("***********************************Adding case number "+s+" to list**************************");
		}

		System.out.println("***********************************Added case number to list**************************");

		return ar.toString();
	}



/*
	public String lookupCases(String term)
	{

		String val = "";

		PolicyDao dao = new PolicyDaoImpl();
		JSONArray ar = new JSONArray();

		//ArrayList<Policy> list = (ArrayList)dao.findPolicys(term);
		//ArrayList<String> list = (ArrayList)dao.findCaseNumbers(term);

		//System.out.println("******************************After get list**************************");
		//System.out.println("***************** "+list.size());

		//Iterator iter = list.iterator();
		//Policy p;
		//String s;

		System.out.println("***********************************About to Add case number to list**************************");

		//while(iter.hasNext()){
		//for (Policy p : dao.findPolicys(term)) {
		for (String s : dao.findCaseNumbers(term)) {

				//p = (Policy)iter.next();
				//s = (String)iter.next();
				//ar.put(p.getId()+ "-" + p.getCaseNumber());
				ar.put(s);
				System.out.println("***********************************Adding case number "+s+" to list**************************");
		}

		System.out.println("***********************************Added case number to list**************************");

		return ar.toString();
	}

*/
}
