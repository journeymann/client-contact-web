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
import java.util.HashSet;
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
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.annotations.Where;

/**
 * Annotation configured person bean.
 *
 * @author Casmon Gordon
 */
@Entity
@Table(name="BROKERCOMPANY")
public class Broker implements Serializable, Comparable<Broker> {

    private static final long serialVersionUID = -8712872385957386182L;

    private Integer id = 0;
    private Integer brokerid = 0;
    private Integer policyid = 0;
    private String brokerCompany = null;
    private String primaryPhone = null;
    private String primaryFax = null;
    private String secondaryPhone = null;
    private String secondaryFax = null;
    private String status = null;
    private String notes = null;
    private Date created = null;
    private String updatedBy = null;
    private Date updateDate = null;
    private Set<BrokerAddress> addresses = null;
    private Collection<ContactCase> contactCases = null;
    private Collection<BrokerContact> contacts = null;
    private Collection<String> cases = null;
	private SelectionLists lists2 = null;

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
     * Gets broker company.
     */
    @Column(name="COMPANY_NAME")
    public String getBrokerCompany() {
        return brokerCompany;
    }

    /**
     * Sets broker company.
     */
    public void setBrokerCompany(String brokerCompany) {
        this.brokerCompany = brokerCompany;
    }


    /**
     * Gets broker id
     */
    @Id
    @Column(name="BROKERCOMPANY_ID")
    @GeneratedValue(strategy=GenerationType.AUTO)
    public Integer getBrokerid() {
        return brokerid;
    }

    /**
     * Sets broker id
     */
    public void setBrokerid(Integer id) {
        this.brokerid = id;
    }

    /**
     * Gets policy id
     */
    @Column(name="POLICY_ID")
    public Integer getPolicyId() {
        return policyid;
    }

    /**
     * Sets policy id
     */
    public void setPolicyId(Integer id) {
        this.policyid = id;
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
	 * @return the primaryPhone
	 */
    @Column(name="PRIMARY_PHONE")
	public String getPrimaryPhone() {
		return primaryPhone;
	}

	/**
	 * @param primaryPhone the primaryPhone to set
	 */
	public void setPrimaryPhone(String primaryPhone) {
		this.primaryPhone = primaryPhone;
	}

	/**
	 * @return the primaryFax
	 */
	@Column(name="PRIMARY_FAX")
	public String getPrimaryFax() {
		return primaryFax;
	}

	/**
	 * @param primaryFax the primaryFax to set
	 */
	public void setPrimaryFax(String primaryFax) {
		this.primaryFax = primaryFax;
	}

	/**
	 * @return the secondaryPhone
	 */
	@Column(name="SECONARY_PHONE")
	public String getSecondaryPhone() {
		return secondaryPhone;
	}

	/**
	 * @param secondaryPhone the secondaryPhone to set
	 */
	public void setSecondaryPhone(String secondaryPhone) {
		this.secondaryPhone = secondaryPhone;
	}

	/**
	 * @return the secondaryFax
	 */
	@Column(name="SECONARY_FAX")
	public String getSecondaryFax() {
		return secondaryFax;
	}

	/**
	 * @param secondaryFax the secondaryFax to set
	 */
	public void setSecondaryFax(String secondaryFax) {
		this.secondaryFax = secondaryFax;
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
	 * @return the notes
	 */
	@Column(name="NOTES")
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
	 * @return the updatedBy
	 */
	@Column(name="UPDATED_BY")
	public String getUpdatedBy() {
		return updatedBy;
	}

	/**
	 * @param updatedBy the updatedBy to set
	 */
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	/**
	 * @return the updateDate
	 */
	@Column(name="UPDATED_DATE")
	public Date getUpdateDate() {
		return updateDate;
	}

	/**
	 * @param updateDate the updateDate to set
	 */
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}


    /**
     * Gets list of <code>BrokerAddress</code>es.
     */
    @OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL, mappedBy="clientid")
    @JoinColumn(name="CLIENT_ID")
	@Where(clause = "status != 'Deleted'")
    public Set<BrokerAddress> getAddresses() {
        return addresses;
    }

    /**
     * Sets list of <code>BrokerAddress</code>es.
     */
    public void setAddresses(Set<BrokerAddress> addresses) {
        this.addresses = addresses;
    }


	public BrokerAddress findBrokerAddressById(Integer id) {
        BrokerAddress result = null;

        if (addresses != null) {
            for (BrokerAddress address : addresses) {
                if (address.getId().equals(id)) {
                    result = address;

                    break;
                }
            }
        }

        return result;
    }


	/**
     * Gets list of <code>ContactCase</code>es.
     */
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
     * Gets list of <code>BrokerContact</code>es.
     */
	@Transient
    public Collection<BrokerContact> getContacts() {
        return contacts;
    }

    /**
     * Sets list of <code>ContactCase</code>es.
     */
    public void setContacts(Collection<BrokerContact> contacts) {
        this.contacts = contacts;
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
     * Returns a string representation of the object.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(this.getClass().getName() + "-");
        sb.append("  id=" + id);
        sb.append("  brokerCompany=" + brokerCompany);

        sb.append("  addresses=[");

        if (addresses != null) {
            for (BrokerAddress address : addresses) {
                sb.append(address.toString());
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
        final Broker other = (Broker) obj;

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
    public int compareTo(Broker b) {
        return this.getBrokerid() - b.getBrokerid();
    }

	//@Transient
    //public boolean getHasValidAddress() {

	//	if(addresses.isEmpty()) return false;
	//	if(!addresses.isEmpty() && addresses.size() == 1){
	//		if(addresses.contains(new BrokerAddress())) return false;
	//	}

	//	return true;

    //}

	@Transient
    public Set<BrokerAddress> getAddressesNonEmpty() {
		if(addresses.isEmpty()){
			Set<BrokerAddress> temp = new HashSet<BrokerAddress>();
			temp.add(new BrokerAddress());
			return temp;
		}
        return addresses;
    }



}
