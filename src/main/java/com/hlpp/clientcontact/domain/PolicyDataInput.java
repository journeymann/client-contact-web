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
public class PolicyDataInput{

    private String param1 = null;
    private String param2 = null;

    private String commissionablebroker = null;
    private String servicingbroker = null;
    private String brokerrecord = null;
    private String primaryservicecontact = null;
    private String primarycompliancecontact = null;
    private String keyclientcontact = null;
    private String ownercontact = null;
    private String reportalaccess = null;
    private String clientreport = null;
    private String fundlevelreport = null;
    private String customdeliverable = null;
    private String authorizedsignatory = null;
    private String newsletter = null;
    private String SSsweep = null;
    private String deathclaimnotification = null;
    private String electronicreport = null;
    private String ppmamendment = null;
    private String annualsemireport = null;
    private String privacynotice = null;
    private String fundprospectussupplement = null;
    private String tls = null;

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
	 * @return param2
	 */
	public String getParam2() {
		return param2;
	}

	/**
	 * @param param2 to set
	 */
	public void setParam2(String param2) {
		this.param2 = param2;
	}

	/**
	 * @return the commissionablebroker
	 */
	public String getCommissionablebroker() {
		return commissionablebroker;
	}

	/**
	 * @param commissionablebroker the commissionablebroker to set
	 */
	public void setCommissionablebroker(String commissionablebroker) {
		this.commissionablebroker = commissionablebroker;
	}

	/**
	 * @return the servicingbroker
	 */
	public String getServicingbroker() {
		return servicingbroker;
	}

	/**
	 * @param servicingbroker the servicingbroker to set
	 */
	public void setServicingbroker(String servicingbroker) {
		this.servicingbroker = servicingbroker;
	}

	/**
	 * @return the brokerrecord
	 */
	public String getBrokerrecord() {
		return brokerrecord;
	}

	/**
	 * @param brokerrecord the brokerrecord to set
	 */
	public void setBrokerrecord(String brokerrecord) {
		this.brokerrecord = brokerrecord;
	}

	/**
	 * @return the primaryservicecontact
	 */
	public String getPrimaryservicecontact() {
		return primaryservicecontact;
	}

	/**
	 * @param primaryservicecontact the primaryservicecontact to set
	 */
	public void setPrimaryservicecontact(String primaryservicecontact) {
		this.primaryservicecontact = primaryservicecontact;
	}

	/**
	 * @return the primarycompliancecontact
	 */
	public String getPrimarycompliancecontact() {
		return primarycompliancecontact;
	}

	/**
	 * @param primarycompliancecontact the primarycompliancecontact to set
	 */
	public void setPrimarycompliancecontact(String primarycompliancecontact) {
		this.primarycompliancecontact = primarycompliancecontact;
	}

	/**
	 * @return the keyclientcontact
	 */
	public String getKeyclientcontact() {
		return keyclientcontact;
	}

	/**
	 * @param keyclientcontact the keyclientcontact to set
	 */
	public void setKeyclientcontact(String keyclientcontact) {
		this.keyclientcontact = keyclientcontact;
	}

	/**
	 * @return the ownercontact
	 */
	public String getOwnercontact() {
		return ownercontact;
	}

	/**
	 * @param ownercontact the ownercontact to set
	 */
	public void setOwnercontact(String ownercontact) {
		this.ownercontact = ownercontact;
	}

	/**
	 * @return the reportalaccess
	 */
	public String getReportalaccess() {
		return reportalaccess;
	}

	/**
	 * @param reportalaccess the reportalaccess to set
	 */
	public void setReportalaccess(String reportalaccess) {
		this.reportalaccess = reportalaccess;
	}

	/**
	 * @return the clientreport
	 */
	public String getClientreport() {
		return clientreport;
	}

	/**
	 * @param clientreport the clientreport to set
	 */
	public void setClientreport(String clientreport) {
		this.clientreport = clientreport;
	}

	/**
	 * @return the fundlevelreport
	 */
	public String getFundlevelreport() {
		return fundlevelreport;
	}

	/**
	 * @param fundlevelreport the fundlevelreport to set
	 */
	public void setFundlevelreport(String fundlevelreport) {
		this.fundlevelreport = fundlevelreport;
	}

	/**
	 * @return the customdeliverable
	 */
	public String getCustomdeliverable() {
		return customdeliverable;
	}

	/**
	 * @param customdeliverable the customdeliverable to set
	 */
	public void setCustomdeliverable(String customdeliverable) {
		this.customdeliverable = customdeliverable;
	}

	/**
	 * @return the authorizedsignatory
	 */
	public String getAuthorizedsignatory() {
		return authorizedsignatory;
	}

	/**
	 * @param authorizedsignatory the authorizedsignatory to set
	 */
	public void setAuthorizedsignatory(String authorizedsignatory) {
		this.authorizedsignatory = authorizedsignatory;
	}

	/**
	 * @return the newsletter
	 */
	public String getNewsletter() {
		return newsletter;
	}

	/**
	 * @param newsletter the newsletter to set
	 */
	public void setNewsletter(String newsletter) {
		this.newsletter = newsletter;
	}

	/**
	 * @return the sSsweep
	 */
	public String getSSsweep() {
		return SSsweep;
	}

	/**
	 * @param sSsweep the sSsweep to set
	 */
	public void setSSsweep(String sSsweep) {
		SSsweep = sSsweep;
	}

	/**
	 * @return the deathclaimnotification
	 */
	public String getDeathclaimnotification() {
		return deathclaimnotification;
	}

	/**
	 * @param deathclaimnotification the deathclaimnotification to set
	 */
	public void setDeathclaimnotification(String deathclaimnotification) {
		this.deathclaimnotification = deathclaimnotification;
	}

	/**
	 * @return the electronicreport
	 */
	public String getElectronicreport() {
		return electronicreport;
	}

	/**
	 * @param electronicreport the electronicreport to set
	 */
	public void setElectronicreport(String electronicreport) {
		this.electronicreport = electronicreport;
	}

	/**
	 * @return the ppmamendment
	 */
	public String getPpmamendment() {
		return ppmamendment;
	}

	/**
	 * @param ppmamendment the ppmamendment to set
	 */
	public void setPpmamendment(String ppmamendment) {
		this.ppmamendment = ppmamendment;
	}

	/**
	 * @return the annualsemireport
	 */
	public String getAnnualsemireport() {
		return annualsemireport;
	}

	/**
	 * @param annualsemireport the annualsemireport to set
	 */
	public void setAnnualsemireport(String annualsemireport) {
		this.annualsemireport = annualsemireport;
	}

	/**
	 * @return the privacynotice
	 */
	public String getPrivacynotice() {
		return privacynotice;
	}

	/**
	 * @param privacynotice the privacynotice to set
	 */
	public void setPrivacynotice(String privacynotice) {
		this.privacynotice = privacynotice;
	}

	/**
	 * @return the fundprospectussupplement
	 */
	public String getFundprospectussupplement() {
		return fundprospectussupplement;
	}

	/**
	 * @param fundprospectussupplement the fundprospectussupplement to set
	 */
	public void setFundprospectussupplement(String fundprospectussupplement) {
		this.fundprospectussupplement = fundprospectussupplement;
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

	/**
	 * @return the tls
	 */
	public String getTls() {
		return tls;
	}

	/**
	 * @param tls to set
	 */
	public void setTls(String tls) {
		this.tls = tls;
	}

}
