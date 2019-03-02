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

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.NamedNativeQuery;

/**
 * Annotation configured person bean.
 *
 * @author Casmon Gordon
 */
//@Entity
//@NamedNativeQuery(name = "findPolicyReport", query = "call pkg_read.sp_GetReportList(:p1, :p2, :sb, :cb, :br, :psc, :pcc, :kcc, :oc, :as, :ra, :er, :cr, :fr, :cd, :n, :sss, :dn, :pa, :ar, :fp, :pn, ?, ?)", resultClass = PolicyDataOutput.class)
//@NamedNativeQuery(name = "findPolicyReport", query = "call pkg_read.sp_GetReportList(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", resultClass = PolicyDataOutput.class)
public class PolicyDataOutput implements Serializable {

    private static final long serialVersionUID = -8712872385957386182L;

    private Integer id = null;
    private Integer contactid = null;

    private String firstname = null;
    private String lastname = null;
    private String caseNumber = null;

    private String contacttype = null;
    private String jobtitle = null;
    private String workcompany = null;
    private String clientgroup = null;
    private String informalownername = null;
    private String formalownername = null;
    private String systemcode = null;
    private String seriescode = null;
    private String teamcode = null;
    private String casemanager = null;
    private String policynotes = null;
    private String brokercompany = null;
    private String ppmnumber = null;

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

    private String email = null;
    private String cellphone = null;
    private String addr1 = null;
    private String addr2 = null;
    private String city = null;
    private String state = null;
    private String zip = null;
    private String country = null;

    private String title = null;
    private String workphone = null;
    private String fax = null;
    private String mainaddress = null;
    private String brokerdealer = null;

    private String workphone2 = null;
    private String fax2 = null;
    private String notes = null;

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
     * Gets contactid
     */
    public Integer getContactid() {
        return contactid;
    }

    /**
     * Sets contactid
     */
    public void setContactid(Integer contactid) {
        this.contactid = contactid;
    }

	/**
	 * @return the firstname
	 */
    @Column(name="FIRST_NAME")
	public String getFirstname() {
		return firstname;
	}

	/**
	 * @param firstname the firstname to set
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	/**
	 * @return the lastname
	 */
	@Column(name="LAST_NAME")
	public String getLastname() {
		return lastname;
	}

	/**
	 * @param lastname the lastname to set
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	/**
	 * @return the caseNumber
	 */
    @Column(name="CASECODE")
	public String getCaseNumber() {
		return caseNumber;
	}

	/**
	 * @param caseNumber the caseNumber to set
	 */
	public void setCaseNumber(String caseNumber) {
		this.caseNumber = caseNumber;
	}

	/**
	 * @return the commissionablebroker
	 */
    @Column(name="COMMISSIONABLE_BROKER")
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
	 @Column(name="SERVICING_BROKER")
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
	 @Column(name="BROKER_RECORD")
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
	 @Column(name="PRIMARY_SERVICE_CONTACT")
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
	 @Column(name="PRIMARY_COMPLIANCE_CONTACT")
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
	 @Column(name="KEY_CLIENT_CONTACT")
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
	 @Column(name="OWNER_CONTACT")
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
	 @Column(name="REPORTAL_ACCESS")
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
	 @Column(name="CLIENT_REPORT")
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
	 @Column(name="FUND_LEVEL_REPORT")
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
	 @Column(name="CUSTOM_DELIVERABLE")
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
	 @Column(name="AUTHORIZED_SIGNATORY")
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
	 @Column(name="NEWSLETTER")
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
	 @Column(name="SOCIAL_SECURITY_SWEEP")
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
	 @Column(name="DEATH_CLAIM_NOTIFICATION")
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
	 @Column(name="ELECTRONIC_REPORT")
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
	 @Column(name="PPM_AMENDMENT")
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
	 @Column(name="ANNUAL_SEMI_REPORT")
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
	 @Column(name="PRIVACY_NOTICE")
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
	 @Column(name="FUND_PROSPECTUS_UPPLEMENT")
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
	 * @return the email
	 */
	 @Column(name="EMAIL_ADDRESS")
	public String getEmail() {
		return email;
	}

	/**
	 * @param privacynotice the privacynotice to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the phone
	 */
	 @Transient
	public String getCellphone() {
		return cellphone;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setCellphone(String phone) {
		this.cellphone = phone;
	}

	/**
	 * @return the contacttype
	 */
	public String getContacttype() {
		return contacttype;
	}

	/**
	 * @param contacttype the contacttype to set
	 */
	public void setContacttype(String contacttype) {
		this.contacttype = contacttype;
	}

	/**
	 * @return the jobtitle
	 */
	public String getJobtitle() {
		return jobtitle;
	}

	/**
	 * @param jobtitle the jobtitle to set
	 */
	public void setJobtitle(String jobtitle) {
		this.jobtitle = jobtitle;
	}

	/**
	 * @return the workcompany
	 */
	public String getWorkcompany() {
		return workcompany;
	}

	/**
	 * @param workcompany the workcompany to set
	 */
	public void setWorkcompany(String workcompany) {
		this.workcompany = workcompany;
	}

	/**
	 * @return the clientgroup
	 */
	public String getClientgroup() {
		return clientgroup;
	}

	/**
	 * @param clientgroup the clientgroup to set
	 */
	public void setClientgroup(String clientgroup) {
		this.clientgroup = clientgroup;
	}

	/**
	 * @return the informalownername
	 */
	public String getInformalownername() {
		return informalownername;
	}

	/**
	 * @param informalownername the informalownername to set
	 */
	public void setInformalownername(String informalownername) {
		this.informalownername = informalownername;
	}

	/**
	 * @return the formalownername
	 */
	public String getFormalownername() {
		return formalownername;
	}

	/**
	 * @param formalownername the formalownername to set
	 */
	public void setFormalownername(String formalownername) {
		this.formalownername = formalownername;
	}

	/**
	 * @return the systemcode
	 */
	public String getSystemcode() {
		return systemcode;
	}

	/**
	 * @param systemcode the systemcode to set
	 */
	public void setSystemcode(String systemcode) {
		this.systemcode = systemcode;
	}

	/**
	 * @return the seriescode
	 */
	public String getSeriescode() {
		return seriescode;
	}

	/**
	 * @param seriescode the seriescode to set
	 */
	public void setSeriescode(String seriescode) {
		this.seriescode = seriescode;
	}

	/**
	 * @return the teamcode
	 */
	public String getTeamcode() {
		return teamcode;
	}

	/**
	 * @param teamcode the teamcode to set
	 */
	public void setTeamcode(String teamcode) {
		this.teamcode = teamcode;
	}

	/**
	 * @return the casemanager
	 */
	public String getCasemanager() {
		return casemanager;
	}

	/**
	 * @param casemanager the casemanager to set
	 */
	public void setCasemanager(String casemanager) {
		this.casemanager = casemanager;
	}

	/**
	 * @return the policynotes
	 */
	public String getPolicynotes() {
		return policynotes;
	}

	/**
	 * @param policynotes the policynotes to set
	 */
	public void setPolicynotes(String policynotes) {
		this.policynotes = policynotes;
	}

	/**
	 * @return the brokercompany
	 */
	public String getBrokercompany() {
		return brokercompany;
	}

	/**
	 * @param brokercompany the brokercompany to set
	 */
	public void setBrokercompany(String brokercompany) {
		this.brokercompany = brokercompany;
	}

	/**
	 * @return the ppmnumber
	 */
	public String getPpmnumber() {
		return ppmnumber;
	}

	/**
	 * @param ppmnumber the ppmnumber to set
	 */
	public void setPpmnumber(String ppmnumber) {
		this.ppmnumber = ppmnumber;
	}

	/**
	 * @return the address
	 */
	public String getAddr1() {
		return addr1;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}

	/**
	 * @return the address
	 */
	public String getAddr2() {
		return addr2;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}


	/**
	 * @return the address
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param address the address to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the address
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param address the address to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the address
	 */
	public String getZip() {
		return zip;
	}

	/**
	 * @param address the address to set
	 */
	public void setZip(String zip) {
		this.zip = zip;
	}

	/**
	 * @return the address
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param address the address to set
	 */
	public void setCountry(String country) {
		this.country = country;
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

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the workphone
	 */
	public String getWorkphone() {
		return workphone;
	}

	/**
	 * @param workphone the workphone to set
	 */
	public void setWorkphone(String workphone) {
		this.workphone = workphone;
	}


	/**
	 * @return the workphone2
	 */
	public String getWorkphone2() {
		return workphone2;
	}

	/**
	 * @param workphone2 the workphone2 to set
	 */
	public void setWorkphone2(String workphone) {
		this.workphone2 = workphone;
	}


	/**
	 * @return the fax
	 */
	public String getFax() {
		return fax;
	}

	/**
	 * @param fax the fax to set
	 */
	public void setFax(String fax) {
		this.fax = fax;
	}

	/**
	 * @return the fax2
	 */
	public String getFax2() {
		return fax2;
	}

	/**
	 * @param fax2 the fax2 to set
	 */
	public void setFax2(String fax) {
		this.fax2 = fax;
	}


	/**
	 * @return the mainaddress
	 */
	public String getMainaddress() {
		return mainaddress;
	}

	/**
	 * @param mainaddress the mainaddress to set
	 */
	public void setMainaddress(String mainaddress) {
		this.mainaddress = mainaddress;
	}

	/**
	 * @return the brokerdealer
	 */
	public String getBrokerdealer() {
		return brokerdealer;
	}

	/**
	 * @param brokerdealer the brokerdealer to set
	 */
	public void setBrokerdealer(String brokerdealer) {
		this.brokerdealer = brokerdealer;
	}

	/**
	 * @return the notes
	 */
	public String getNotes() {
		return notes;
	}

	/**
	 * @param notes the notes to set
	 */
	public void setNotes(String notes) {
		this.notes = notes;
	}



}
