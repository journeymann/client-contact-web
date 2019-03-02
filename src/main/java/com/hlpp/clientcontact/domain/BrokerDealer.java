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
@Table(name="BROKERDEALER")
public class BrokerDealer implements Serializable, Comparable<BrokerDealer> {

    private static final long serialVersionUID = -8712872385957386182L;

    private Integer id = 0;
    private Integer brokerDealerid = 0;
    private String brokerDealerName = null;
    private Integer brokerCompanyId = 0;
    private String phone = null;
    private String fax = null;
    private String contactPerson = null;
    private String status = null;
    private String notes = null;
    private Date created = null;
    private String updatedBy = null;
    private Date updateDate = null;
    private Set<BrokerDealerAddress> addresses = null;
	private Collection<ContactCase> contactCases = null;
    private Collection<DealerContact> contacts = null;
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
	 * @return the brokerDealerid
	 */
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="BROKERDEALER_ID")
	public Integer getBrokerDealerid() {
		return brokerDealerid;
	}

	/**
	 * @param brokerDealerid the brokerDealerid to set
	 */
	public void setBrokerDealerid(Integer brokerDealerid) {
		this.brokerDealerid = brokerDealerid;
	}

	/**
	 * @return the brokerDealerName
	 */
    @Column(name="BROKERDEALER_NAME")
	public String getBrokerDealerName() {
		return brokerDealerName;
	}

	/**
	 * @param brokerDealerName the brokerDealerName to set
	 */
	public void setBrokerDealerName(String brokerDealerName) {
		this.brokerDealerName = brokerDealerName;
	}

	/**
	 * @return the brokerCompanyId
	 */
    @Column(name="BROKERCOMPANY_ID")
	public Integer getBrokerCompanyId() {
		return brokerCompanyId;
	}

	/**
	 * @param brokerCompanyId the brokerCompanyId to set
	 */
	public void setBrokerCompanyId(Integer brokerCompanyId) {
		this.brokerCompanyId = brokerCompanyId;
	}

	/**
	 * @return the phone
	 */
    @Column(name="PHONE")
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the fax
	 */
    @Column(name="FAX")
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
	 * @return the contactPerson
	 */
    @Column(name="CONTACT_PERSON")
	public String getContactPerson() {
		return contactPerson;
	}

	/**
	 * @param contactPerson the contactPerson to set
	 */
	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
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
	 * @return the created
	 */
	public Date getCreated() {
		return created;
	}

	/**
	 * @param created the created to set
	 */
	public void setCreated(Date created) {
		this.created = created;
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
     * Gets list of <code>BrokerDealerAddress</code>es.
     */
    @OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL, mappedBy="clientid")
    @JoinColumn(name="CLIENT_ID")
	@Where(clause = "status != 'Deleted'")
    public Set<BrokerDealerAddress> getAddresses() {
        return addresses;
    }

    /**
     * Sets list of <code>BrokerAddress</code>es.
     */
    public void setAddresses(Set<BrokerDealerAddress> addresses) {
        this.addresses = addresses;
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
     * Gets list of <code>DealerContact</code>es.
     */
	@Transient
    public Collection<DealerContact> getContacts() {
        return contacts;
    }

    /**
     * Sets list of <code>ContactCase</code>es.
     */
    public void setContacts(Collection<DealerContact> contacts) {
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
        sb.append("  brokerDealerName=" + brokerDealerName);
        sb.append("  phone=" + phone);
        sb.append("  fax=" + fax);
        sb.append("  contact person=" + contactPerson);
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
        final BrokerDealer other = (BrokerDealer) obj;

        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }

        return true;
    }


	public BrokerDealerAddress findDealerAddressById(Integer id) {
        BrokerDealerAddress result = null;

        if (addresses != null) {
            for (BrokerDealerAddress address : addresses) {
                if (address.getId().equals(id)) {
                    result = address;

                    break;
                }
            }
        }

        return result;
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
    public int compareTo(BrokerDealer d) {
        return this.getBrokerDealerid() - d.getBrokerDealerid() ;
    }

	//@Transient
    //public boolean getHasValidAddress() {

	//	if(addresses.isEmpty()) return false;
	//	if(!addresses.isEmpty() && addresses.size() == 1){
	//		if(addresses.contains(new BrokerDealerAddress())) return false;
	//	}

	//	return true;

    //}

	@Transient
    public Set<BrokerDealerAddress> getAddressesNonEmpty() {
		if(addresses.isEmpty()){
			Set<BrokerDealerAddress> temp = new HashSet<BrokerDealerAddress>();
			temp.add(new BrokerDealerAddress());
			return temp;
		}
        return addresses;
    }


}
