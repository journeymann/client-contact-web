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
import java.util.HashMap;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Annotation configured address bean.
 *
 * @author Casmon Gordon
 */
@Entity
@Table(name="ADDRESS")
public class BrokerAddress implements Serializable {

    private static final long serialVersionUID = 7851794269407495684L;

    private Integer id = null;
    private Integer clientid = null;
    private String address1 = null;
    private String address2 = null;
    private String city = null;
    private String state = null;
    private String zipPostal = null;
    private String country = null;
    private String addresstype = null;
    private String mainaddress = null;
    private String createby = null;
    private Integer updateby = null;
    private Date created = null;
    private Date updated = null;
	HashMap<String,String> yesnoMap = new HashMap<String,String>();
	private SelectionLists lists2 = null;
    private String status = null;

    /**
     * Gets id (primary key).
     */
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "ADDRESS_ID")
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
     * Gets address.
     */
    @Column(name = "ADDR1")
    public String getAddress1() {
        return address1;
    }

    /**
     * Sets address.
     */
    public void setAddress1(String address) {
        this.address1 = address;
    }

    /**
     * Gets city.
     */
    @Column(name = "CITY")
    public String getCity() {
        return city;
    }

    /**
     * Sets city.
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Gets state.
     */
    @Column(name = "STATE")
    public String getState() {
        return state;
    }

    /**
     * Sets state.
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * Gets zip or postal code.
     */
    @Column(name = "ZIP")
    public String getZipPostal() {
        return zipPostal;
    }

    /**
     * Sets zip or postal code.
     */
    public void setZipPostal(String zipPostal) {
        this.zipPostal = zipPostal;
    }

    /**
     * Gets country.
     */
    @Column(name = "COUNTRY")
    public String getCountry() {
        return country;
    }

    /**
     * Sets country.
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Gets date created.
     */
    @Column(name = "CREATED_DATE")
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
	 * @return the address2
	 */
    @Column(name = "ADDR2")
	public String getAddress2() {
		return address2;
	}

	/**
	 * @param address2 the address2 to set
	 */
	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	/**
	 * @return the addresstype
	 */
    @Column(name = "ADDRESS_TYPE")
	public String getAddresstype() {
		return addresstype;
	}

	/**
	 * @param addresstype the addresstype to set
	 */
	public void setAddresstype(String addresstype) {
		this.addresstype = addresstype;
	}

	/**
	 * @return the mainaddress
	 */
    @Column(name = "MAIN_ADDRESS")
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
	 * @return the createby
	 */
    @Column(name = "CREATED_USER")
	public String getCreateby() {
		return createby;
	}

	/**
	 * @param createby the createby to set
	 */
	public void setCreateby(String createby) {
		this.createby = createby;
	}

	/**
	 * @return the updateby
	 */
    @Column(name = "UPDATED_BY")
	public Integer getUpdateby() {
		return updateby;
	}

	/**
	 * @param updateby the updateby to set
	 */
	public void setUpdateby(Integer updateby) {
		this.updateby = updateby;
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
     * Returns a string representation of the object.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(this.getClass().getName() + "-");
        sb.append("  id=" + id);
        sb.append("  addresss1=" + address1);
        sb.append("  addresss2=" + address2);
        sb.append("  city=" + city);
        sb.append("  state=" + state);
        sb.append("  zipPostal=" + zipPostal);
        sb.append("  country=" + country);
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

        final BrokerAddress other = (BrokerAddress) obj;

        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id)) {
            return false;
        }

        return true;
    }

	/**
     * Gets list of <code>YesnoMap</code>es.
     */
	@Transient
    public HashMap<String,String> getYesnoMap() {

		yesnoMap.put("","");
		yesnoMap.put("Yes","Yes");
		yesnoMap.put("No","No");


        return yesnoMap;
    }

    /**
     * Sets list of <code>YesnoMap</code>es.
     */
    public void setYesnoMap(HashMap<String,String> map) {
        this.yesnoMap = map;
    }

    /**
     * Gets clientid
     */
	@Column(name="CLIENT_ID")
    public Integer getClientid() {
        return clientid;
    }

    /**
     * Sets clientid.
     */
    public void setClientid(Integer id) {
        this.clientid = id;
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
     * Sets list of <code>SelectionLists</code>es.
     */
    public BrokerAddress addSelectionLists(SelectionLists lists2) {
        this.setSelectionLists(lists2);
        return this;
    }

    /**
     * Gets status.
     */
    @Column(name = "STATUS")
    public String getStatus() {
        return status;
    }

    /**
     * Sets status.
     */
    public void setStatus(String status) {
        this.status = status;
    }



}
