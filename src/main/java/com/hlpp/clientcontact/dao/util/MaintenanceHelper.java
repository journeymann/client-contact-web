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

package com.hlpp.clientcontact.dao.util;

import java.io.Serializable;
import java.util.Collection;

import com.hlpp.clientcontact.domain.maintenance.ClientGroup;
import com.hlpp.clientcontact.domain.maintenance.CaseManager;
import com.hlpp.clientcontact.domain.maintenance.Team;
import com.hlpp.clientcontact.domain.maintenance.Series;
import com.hlpp.clientcontact.domain.maintenance.Country;
import com.hlpp.clientcontact.domain.maintenance.Library;

/**
 *
 * @author Casmon Gordon
 */
public class MaintenanceHelper implements Serializable {

    private Collection<ClientGroup> clientgroups = null;
    private Collection<CaseManager> casemanagers = null;
    private Collection<Team> teams = null;
    private Collection<Series> series = null;
    private Collection<Country> countries = null;
    private Collection<Library> libraries = null;


    private String[] tables = null;

	private String table = null;

	/**
     * Gets list of <code>ClientGroup</code>es.
     */
    public Collection<ClientGroup> getClientGroups() {
        return clientgroups;
    }

    /**
     * Sets list of <code>ClientGroup</code>es.
     */
    public void setClientGroups(Collection<ClientGroup> clientgroups) {
        this.clientgroups = clientgroups;
    }

	/**
     * Gets list of <code>CaseManager</code>es.
     */
    public Collection<CaseManager> getCaseManagers() {
        return casemanagers;
    }

    /**
     * Sets list of <code>CaseManager</code>es.
     */
    public void setCaseManagers(Collection<CaseManager> casemanagers) {
        this.casemanagers = casemanagers;
    }

	/**
     * Gets list of <code>Team</code>es.
     */
    public Collection<Team> getTeams() {
        return teams;
    }

    /**
     * Sets list of <code>Team</code>es.
     */
    public void setTeams(Collection<Team> teams) {
        this.teams = teams;
    }

	/**
     * Gets list of <code>Series</code>es.
     */
    public Collection<Series> getSeries() {
        return series;
    }

    /**
     * Sets list of <code>Series</code>es.
     */
    public void setSeries(Collection<Series> series) {
        this.series = series;
    }

	/**
     * Gets list of <code>Countries</code>es.
     */
    public Collection<Country> getCountries() {
        return countries;
    }

    /**
     * Sets list of <code>Countries</code>es.
     */
    public void setCountries(Collection<Country> countries) {
        this.countries = countries;
    }

	/**
     * Gets list of <code>Libraries</code>es.
     */
    public Collection<Library> getLibraries() {
        return libraries;
    }

    /**
     * Sets list of <code>Libraries</code>es.
     */
    public void setLibraries(Collection<Library> libraries) {
        this.libraries = libraries;
    }

    /**
     * Gets table
     */
    public String getTableSelected() {
        return table;
    }

    /**
     * Sets table
     */
    public void setTableSelected(String table) {
        this.table = table;
    }

    /**
     * Gets tables
     */
    public String[] getTables() {
        return tables;
    }

    /**
     * Sets tables
     */
    public void setTables(String[] tables) {
        this.tables = tables;
    }


}