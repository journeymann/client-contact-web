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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Annotation configured person bean.
 *
 * @author Casmon Gordon
 */
@Entity
@Table(name="CASES")
public class Policy implements Serializable, Comparable<Policy>{

    private static final long serialVersionUID = -8712872385957386282L;

    private Integer id = 0;
    private Integer caseid = 0;
    private String casecode = null;
    private String clientGroupCode = null;
    private String formalOwnerName = null;
    private String informalOwnerName = null;
    private String library = null;
    private String series = null;
    private String team = null;
    private String caseManager = null;
    private String ppm = null;
    private String notes = null;
    private String status = null;
    private String updateby = null;
    private Date updatedate = null;
    private Integer broker_id = 0;
    private Collection<Broker> brokers = null;
    private Collection<BrokerDealer> dealers = null;
    private Collection<ContactCase> contactCases = null;
    private Date created = null;
	private SelectionLists lists2 = null;

    /**
     * Gets id (primary key).
     */
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
     * Gets caseNumber
     */
    @Column(name="CASECODE")
    public String getCasecode() {
        return casecode;
    }

    /**
     * Sets caseNumber
     */
    public void setCasecode(String casecode) {
        this.casecode = casecode;
    }

    /**
     * Gets formalOwnerName
     */
    @Column(name="FORMAL_OWNER_NAME")
    public String getFormalOwnerName() {
        return formalOwnerName;
    }

    /**
     * Sets formalOwnerName
     */
    public void setFormalOwnerName(String formalOwnerName) {
        this.formalOwnerName = formalOwnerName;
    }

    /**
     * Gets library
     */
    @Column(name="SYSTEM_CODE")
    public String getLibrary() {
        return library;
    }

    /**
     * Sets library
     */
    public void setLibrary(String library) {
        this.library = library;
    }

    /**
     * Gets series
     */
    @Column(name="SERIES_CODE")
    public String getSeries() {
        return series;
    }

    /**
     * Sets series
     */
    public void setSeries(String series) {
        this.series = series;
    }


    /**
     * Gets team
     */
    @Column(name="TEAM_CODE")
    public String getTeam() {
        return team;
    }

    /**
     * Sets team
     */
    public void setTeam(String team) {
        this.team = team;
    }

    /**
     * Gets caseManager
     */
    @Column(name="CASE_MANAGER")
    public String getCaseManager() {
        return caseManager;
    }

    /**
     * Sets caseManager
     */
    public void setCaseManager(String caseManager) {
        this.caseManager = caseManager;
    }


    /**
     * Gets broker_id
     */
    @Column(name="BROKER_ID")
    public Integer getBroker_id() {
        return broker_id;
    }

    /**
     * Sets broker_id
     */
    public void setBroker_id(Integer broker_id) {
        this.broker_id = broker_id;
    }

    /**
     * Gets date created.
     */
    public Date getCreated() {
        return created;
    }

    /**
     * Sets date created.
     */
    public void setCreated(Date created) {
        this.created = created;
    }

    /**
	 * @return the caseid
	 */
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
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
	 * @return the clientGroupCode
	 */
    @Column(name="CLIENT_GROUP")
	public String getClientGroupCode() {
		return clientGroupCode;
	}

	/**
	 * @param clientGroupCode the clientGroupCode to set
	 */
	public void setClientGroupCode(String clientGroupCode) {
		this.clientGroupCode = clientGroupCode;
	}

	/**
	 * @return the informalownerName
	 */
    @Column(name="INFORMAL_OWNER_NAME")
	public String getInformalOwnerName() {
		return informalOwnerName;
	}

	/**
	 * @param informalownerName the informalownerName to set
	 */
	public void setInformalOwnerName(String informalownerName) {
		this.informalOwnerName = informalownerName;
	}

	/**
	 * @return the ppm
	 */
    @Column(name="PPM_NO")
	public String getPpm() {
		return ppm;
	}

	/**
	 * @param ppm the ppm to set
	 */
	public void setPpm(String ppm) {
		this.ppm = ppm;
	}

	/**
	 * @return the notes
	 */
    @Column(name="POLICY_NOTES")
	public String getNotes() {
		return notes;
	}

	/**
	 * @param notes the notes to set
	 */
	public void setNotes(String notes) {
		this.notes = notes;
	}

	/**
	 * @return the status
	 */
    @Column(name="STATUS")
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
	 * @return the updateby
	 */
    @Column(name="UPDATED_BY")
	public String getUpdateby() {
		return updateby;
	}

	/**
	 * @param updateby the updateby to set
	 */
	public void setUpdateby(String updateby) {
		this.updateby = updateby;
	}

	/**
	 * @return the updatedate
	 */
    @Column(name="UPDATED_DATE")
	public Date getUpdatedate() {
		return updatedate;
	}

	/**
	 * @param updatedate the updatedate to set
	 */
	public void setUpdatedate(Date updatedate) {
		this.updatedate = updatedate;
	}

	/**
     * Gets list of <code>Brokers</code>es.
     */
    //@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
    //@JoinColumn(name="POLICY_ID", nullable=false)
	@Transient
    public Collection<Broker> getBrokers() {
        return brokers;
    }

    /**
     * Sets list of <code>Brokers</code>es.
     */
    public void setBrokers(Collection<Broker> brokers) {
        this.brokers = brokers;
    }

	/**
     * Gets list of <code>BrokerDealers</code>es.
     */
    //@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
    //@JoinColumn(name="POLICY_ID", nullable=false)
	@Transient
    public Collection<BrokerDealer> getBrokerDealers() {
        return dealers;
    }

    /**
     * Sets list of <code>BrokerDealers</code>es.
     */
    public void setBrokerDealers(Collection<BrokerDealer> dealers) {
        this.dealers = dealers;
    }


	/**
     * Gets list of <code>ContactCase</code>es.
     */
    //@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
    //@JoinColumn(name="CASE_ID", nullable=false, insertable = false, updatable = false)
	@Transient
    public Collection<ContactCase> getContactCases() {
        return contactCases;
    }

    /**
     * Sets list of <code>ContactCase</code>es.
     */
    public void setContactCases(Collection<ContactCase> contacts) {
        this.contactCases = contacts;
    }

	/**
     * Gets <code>Broker</code>.
     */
    public Broker findBrokerById(Integer id) {
        Broker result = null;

        if (brokers != null) {
            for (Broker broker : brokers) {
                if (broker.getId().equals(id)) {
                    result = broker;

                    break;
                }
            }
        }

        return result;
    }

    /**
     * Returns a string representation of the object.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(this.getClass().getName() + "-");
        sb.append("  id=" + id);
        sb.append("  Formal Owner Name=" + formalOwnerName);
        sb.append("  brokers=[");

        if (brokers != null) {
            for (Broker broker : brokers) {
                sb.append(broker.toString());
            }
        }

        sb.append("]");

        sb.append("  created=" + created);

        return sb.toString();
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
        final Policy other = (Policy) obj;

        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }

        return true;
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


    @Override
    public int compareTo(Policy p) {
        return (this.getCasecode().compareTo(p.getCasecode()) );
    }


}

