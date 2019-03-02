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
import java.util.ArrayList;


/**
 *
 * @author Casmon Gordon
 */
public class SelectionLists implements Serializable {

	private ArrayList<ContactType> contacttypes = new ArrayList<ContactType>();
	private ArrayList<DataItem> series = new ArrayList<DataItem>();
	private ArrayList<DataItem> client_groups = new ArrayList<DataItem>();
	private ArrayList<DataItem> teams = new ArrayList<DataItem>();
	private ArrayList<DataItem> system = new ArrayList<DataItem>();
	private ArrayList<DataItem> casemanagers = new ArrayList<DataItem>();

	private ArrayList<DataItem> brokers = new ArrayList<DataItem>();
	private ArrayList<DataItem> dealers = new ArrayList<DataItem>();

	private ArrayList<State> states = new ArrayList<State>();
	private ArrayList<DataItem> countries = new ArrayList<DataItem>();
	private ArrayList<DataItem> policys = new ArrayList<DataItem>();
	private ArrayList<DataItem> yesno = new ArrayList<DataItem>();

	/**
	 * @return the contacttypes
	 */
	public ArrayList<ContactType> getContacttypes() {
		return contacttypes;
	}
	/**
	 * @param contacttypes the contacttypes to set
	 */
	public void setContacttypes(ArrayList<ContactType> contacttypes) {
		this.contacttypes = contacttypes;
	}
	/**
	 * @return the series
	 */
	public ArrayList<DataItem> getSeries() {
		return series;
	}
	/**
	 * @param series the series to set
	 */
	public void setSeries(ArrayList<DataItem> series) {
		this.series = series;
	}
	/**
	 * @return the client_groups
	 */
	public ArrayList<DataItem> getClient_groups() {
		return client_groups;
	}
	/**
	 * @param client_groups the client_groups to set
	 */
	public void setClient_groups(ArrayList<DataItem> client_groups) {
		this.client_groups = client_groups;
	}
	/**
	 * @return the teams
	 */
	public ArrayList<DataItem> getTeams() {
		return teams;
	}
	/**
	 * @param teams the teams to set
	 */
	public void setTeams(ArrayList<DataItem> teams) {
		this.teams = teams;
	}
	/**
	 * @return the system
	 */
	public ArrayList<DataItem> getSystem() {
		return system;
	}
	/**
	 * @param system the system to set
	 */
	public void setSystem(ArrayList<DataItem> system) {
		this.system = system;
	}
	/**
	 * @return the casemanagers
	 */
	public ArrayList<DataItem> getCasemanagers() {
		return casemanagers;
	}
	/**
	 * @param casemanagers the casemanagers to set
	 */
	public void setCasemanagers(ArrayList<DataItem> casemanagers) {
		this.casemanagers = casemanagers;
	}

	/**
	 * @return the brokers
	 */
	public ArrayList<DataItem> getBrokers() {
		return brokers;
	}
	/**
	 * @param brokers the brokers to set
	 */
	public void setBrokers(ArrayList<DataItem> brokers) {
		this.brokers = brokers;
	}

	/**
	 * @return the dealers
	 */
	public ArrayList<DataItem> getDealers() {
		return dealers;
	}
	/**
	 * @param dealers the dealers to set
	 */
	public void setDealers(ArrayList<DataItem> dealers) {
		this.dealers = dealers;
	}

	/**
	 * @return the states
	 */
	public ArrayList<State> getStates() {
		return states;
	}

	/**
	 * @param states the states to set
	 */
	public void setStates(ArrayList<State> states) {
		this.states = states;
	}

	/**
	 * @return the countries
	 */
	public ArrayList<DataItem> getCountries() {
		return countries;
	}
	/**
	 * @param countries the countries to set
	 */
	public void setCountries(ArrayList<DataItem> countries) {
		this.countries = countries;
	}

	/**
	 * @return the policys
	 */
	public ArrayList<DataItem> getPolicys() {
		return policys;
	}
	/**
	 * @param policys the policys to set
	 */
	public void setPolicys(ArrayList<DataItem> policys) {
		this.policys = policys;
	}

	/**
	 * @return the yesno
	 */
	public ArrayList<DataItem> getYesno() {
		return yesno;
	}
	/**
	 * @param policys the policys to set
	 */
	public void setYesno(ArrayList<DataItem> yesno) {
		this.yesno = yesno;
	}



}