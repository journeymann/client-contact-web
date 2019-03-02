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
import java.util.Collection;
import java.util.ArrayList;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Annotation configured person bean.
 *
 * @author Casmon Gordon
 */
@Entity
@Table(name="CONTACTCASE")
public class ContactCase implements Serializable {

    private static final long serialVersionUID = -8712872385957386182L;

    private Integer id = null;
    private Integer contactcaseid = null;
    private Integer caseid = null;
    private Integer contactid = null;
    private String contacttype = "Unspecified";
    private String commissionablebroker = "";
    private String servicingbroker = "";
    private String brokerrecord = "";
    private String primaryservicecontact = "";
    private String primarycompliancecontact = "";
    private String keyclientcontact = "";
    private String ownercontact = "";
    private String reportalaccess = "";
    private String clientreport = "";
    private String fundlevelreport = "";
    private String customdeliverable = "";
    private String authorizedsignatory = "";
    private String newsletter = "";
    private String SSsweep = "";
    private String deathclaimnotification = "";
    private String electronicreport = "";
    private String ppmamendment = "";
    private String annualsemireport = "";
    private String privacynotice = "";
    private String fundprospectussupplement = "";
    private String tls = "";
    private String updatedby = "";
    private Date updateddate = null;
    private String updateuser = "";
    private Integer brokerdealerid = null; //TODO remove
    private Integer brokercompanyid = null; //TODO remove
	private Person person = new Person();
    private Collection<String> cases = new ArrayList<String>();
	private SelectionLists lists2 = null;
    private String status = null;

    /**
     * Gets id (primary key).
     */
    //@Id
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
	 * @return the contactcaseid
	 */
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="CONTACTCASE_ID")
	public Integer getContactcaseid() {
		return contactcaseid;
	}

	/**
	 * @param contactcaseid the contactcaseid to set
	 */
	public void setContactcaseid(Integer contactcaseid) {
		this.contactcaseid = contactcaseid;
	}

    /**
	 * @return the contactid
	 */
    @Column(name="CONTACT_ID")
	public Integer getContactid() {
		return contactid;
	}

	/**
	 * @param contactcaseid the contactid to set
	 */
	public void setContactid(Integer contactid) {
		this.contactid = contactid;
	}



	/**
	 * @return the caseid
	 */
    @Column(name="CASE_ID")
	public Integer getCaseid() {
		return caseid;
	}

	/**
	 * @param caseid the caseid to set
	 */
	public void setCaseid(Integer caseid) {
		this.caseid = caseid;
	}

	/**
	 * @return the contacttype
	 */
    @Column(name="CONTACT_TYPE")
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
	 * @return the updatedby
	 */
    @Column(name="UPDATED_BY")
	public String getUpdatedby() {
		return updatedby;
	}

	/**
	 * @param updatedby the updatedby to set
	 */
	public void setUpdatedby(String updatedby) {
		this.updatedby = updatedby;
	}

	/**
	 * @return the updateddate
	 */
    @Column(name="UPDATED_DATE")
	public Date getUpdateddate() {
		return updateddate;
	}

	/**
	 * @param updateddate the updateddate to set
	 */
	public void setUpdateddate(Date updateddate) {
		this.updateddate = updateddate;
	}

	/**
	 * @return the updateuser
	 */
    @Column(name="UPDATED_USER")
	public String getUpdateuser() {
		return updateuser;
	}

	/**
	 * @param updateuser the updateuser to set
	 */
	public void setUpdateuser(String updateuser) {
		this.updateuser = updateuser;
	}

	/**
	 * @return the brokerdealerid
	 */
	@Transient
	public Integer getBrokerdealerid() {
		return person.getBrokerdealerid();
	}

	/**
	 * @param brokerdealerid the brokerdealerid to set
	 */
	public void setBrokerdealerid(Integer brokerdealerid) {
		person.setBrokerdealerid(brokerdealerid);
	}

	/**
	 * @return the brokercompanyid
	 */
	@Transient
	public Integer getBrokercompanyid() {
		return person.getBrokercompanyid();
	}

	/**
	 * @param brokercompanyid the brokercompanyid to set
	 */
	public void setBrokercompanyid(Integer brokercompanyid) {
		person.setBrokercompanyid(brokercompanyid);
	}


	/**
     * Gets <code>Person</code>es.
     */
    @OneToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinColumn(name="CONTACT_ID", nullable=false, insertable = false, updatable = false)
    public Person getPerson() {
        return person;
    }

    /**
     * Sets <code>Person</code>es.
     */
    public void setPerson(Person person) {
        this.person = person;
    }

	/**
     * Gets list of <code>Case</code>es.
     */
	@Transient
    public Collection<String> getCases() {
        return cases;
    }

    /**
     * Sets list of <code>Case</code>es.
     */
    public void setCases(Collection<String> cases) {
        this.cases = cases;
    }

	/**
     * Gets list of <code>SelectionLists</code>es.
     */
	@Transient
    public SelectionLists getSelectionLists() {
        return lists2;
    }

    /**
     * Sets list of <code>SelectionLists</code>es.
     */
    public void setSelectionLists(SelectionLists lists2) {
        this.lists2 = lists2;
    }


	/**
	 * @return the status
	 */
    @Column(name = "STATUS")
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}


	/**
	 * @return the tls
	 */
    @Column(name = "TLS")
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
        final ContactCase other = (ContactCase) obj;

        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }

        return true;
    }
}
