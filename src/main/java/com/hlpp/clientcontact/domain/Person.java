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
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.annotations.Where;

/**
 * Annotation configured person bean.
 *
 * @author Casmon Gordon
 */
@Entity
@Table(name="CONTACT")
public class Person implements Serializable {

    private static final long serialVersionUID = -8712872385957386182L;

    private Integer id = 0;
    private Integer contactid = 0;
    private String firstName = null;
    private String lastName = null;
    private String cellphone = null;
    private String department = null;
    private String jobtitle = null;
    private String title = null;
    private String company = null;
    private String notes = null;
    private String email = null;
    private String primaryphone = null;
    private String secondaryphone = null;
    private String primaryfax = null;
    private String secondaryfax = null;
    private String status = null;
    private Date updated = null;
    private String update_by = null;
    private Date created = null;
    private Integer brokerdealerid;
    private Integer brokercompanyid;
	private SelectionLists lists2 = null;
    private Set<Address> addresses = null;
    private Collection<String> cases = null;

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
     * Gets first name.
     */
    @Column(name="FIRST_NAME")
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets first name.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets last name.
     */
    @Column(name = "LAST_NAME")
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets last name.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets cell phone.
     */
    @Column(name = "PERSON_CELL_PHONE")
    public String getCellphone() {
        return cellphone;
    }

    /**
     * Sets cell phone.
     */
    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    /**
     * Gets job title.
     */
    @Column(name = "JOB_TITLE")
    public String getJobtitle() {
        return jobtitle;
    }

    /**
     * Sets job title.
     */
    public void setJobtitle(String jobtitle) {
        this.jobtitle = jobtitle;
    }

    /**
	 * @return the title
	 */
    @Column(name = "TITLE")
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
	 * @return the company
	 */
    @Column(name = "WORK_COMPANY")
	public String getCompany() {
		return company;
	}

	/**
	 * @param company the company to set
	 */
	public void setCompany(String company) {
		this.company = company;
	}

	/**
	 * @return the notes
	 */
    @Column(name = "NOTES")
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
	 * @return the email
	 */
    @Column(name = "EMAIL_ADDRESS")
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the primaryphone
	 */
    @Column(name = "PRIMARY_WORK_PHONE")
	public String getPrimaryphone() {
		return primaryphone;
	}

	/**
	 * @param primaryphone the primaryphone to set
	 */
	public void setPrimaryphone(String primaryphone) {
		this.primaryphone = primaryphone;
	}

	/**
	 * @return the secondaryphone
	 */
    @Column(name = "SECONDARY_WORK_PHONE")
	public String getSecondaryphone() {
		return secondaryphone;
	}

	/**
	 * @param secondaryphone the secondaryphone to set
	 */
	public void setSecondaryphone(String secondaryphone) {
		this.secondaryphone = secondaryphone;
	}

	/**
	 * @return the primaryfax
	 */
    @Column(name = "PRIMARY_FAX")
	public String getPrimaryfax() {
		return primaryfax;
	}

	/**
	 * @param primaryfax the primaryfax to set
	 */
	public void setPrimaryfax(String primaryfax) {
		this.primaryfax = primaryfax;
	}

	/**
	 * @return the secondaryfax
	 */
    @Column(name = "SECONDARY_FAX")
	public String getSecondaryfax() {
		return secondaryfax;
	}

	/**
	 * @param secondaryfax the secondaryfax to set
	 */
	public void setSecondaryfax(String secondaryfax) {
		this.secondaryfax = secondaryfax;
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
	 * @return the updated
	 */
    @Column(name = "UPDATED_DATE")
	public Date getUpdated() {
		return updated;
	}

	/**
	 * @param updated the updated to set
	 */
	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	/**
	 * @return the update_by
	 */
    @Column(name = "UPDATED_BY")
	public String getUpdate_by() {
		return update_by;
	}

	/**
	 * @param update_by the update_by to set
	 */
	public void setUpdate_by(String update_by) {
		this.update_by = update_by;
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
     * Sets contact id.
     */
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="CONTACT_ID")
    public Integer getContactid() {
        return contactid;
    }

    /**
     * Sets id (primary key).
     */
    public void setContactid(Integer id) {
        this.contactid = id;
    }

	/**
	 * @return the brokerdealerid
	 */
    @Column(name="BROKERDEALER_ID")
	public Integer getBrokerdealerid() {
		return brokerdealerid;
	}

	/**
	 * @param brokerdealerid the brokerdealerid to set
	 */
	public void setBrokerdealerid(Integer brokerdealerid) {
		this.brokerdealerid = brokerdealerid;
	}

	/**
	 * @return the brokercompanyid
	 */
	@Column(name="BROKERCOMPANY_ID")
	public Integer getBrokercompanyid() {
		return brokercompanyid;
	}

	/**
	 * @param brokercompanyid the brokercompanyid to set
	 */
	public void setBrokercompanyid(Integer brokercompanyid) {
		this.brokercompanyid = brokercompanyid;
	}


	/**
     * Gets list of <code>Address</code>es.
     */
    @OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL, mappedBy="clientid")
    @JoinColumn(name="CLIENT_ID", nullable=false)
	@Where(clause = "status != 'Deleted'")
	public Set<Address> getAddresses() {
        return addresses;
    }

    /**
     * Sets list of <code>Address</code>es.
     */
    public void setAddresses(Set<Address> addresses) {
        this.addresses = addresses;
    }

    public Address findAddressById(Integer id) {
        Address result = null;

        if (addresses != null) {
            for (Address address : addresses) {
                if (address.getId().equals(id)) {
                    result = address;

                    break;
                }
            }
        }

        return result;
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
        sb.append("  firstName=" + firstName);
        sb.append("  lastName=" + lastName);

        sb.append("  addresses=[");

        if (addresses != null) {
            for (Address address : addresses) {
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
        final Person other = (Person) obj;

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
	 * @return the company shortened
	 */
    @Transient
	public String getCompany2() {
		String[] elements = company.split(" ");
		return (elements.length > 2)? (elements[0] + " " + elements[1] + " " + elements[2]) : (elements[0] + " " + elements[1]);
	}

	/**
	 * @param company the company to set
	 */
	public void setCompany2(String company) {
		this.company = company;
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

}
