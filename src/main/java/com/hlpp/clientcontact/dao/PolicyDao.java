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

package com.hlpp.clientcontact.dao;

import java.util.Collection;
import java.util.Set;

import com.hlpp.clientcontact.domain.Broker;
import com.hlpp.clientcontact.domain.BrokerDealer;
import com.hlpp.clientcontact.domain.Policy;
import com.hlpp.clientcontact.domain.Address;
import com.hlpp.clientcontact.domain.Field;
import com.hlpp.clientcontact.domain.BrokerAddress;
import com.hlpp.clientcontact.domain.BrokerDealerAddress;
import com.hlpp.clientcontact.domain.PolicyDataOutput;
import com.hlpp.clientcontact.domain.PolicyDataInput;
import com.hlpp.clientcontact.domain.PersonDataOutput;
import com.hlpp.clientcontact.domain.PersonDataInput;
import com.hlpp.clientcontact.domain.ContactCase;
import com.hlpp.clientcontact.domain.State;
import com.hlpp.clientcontact.domain.BrokerContact;
import com.hlpp.clientcontact.domain.DealerContact;
import com.hlpp.clientcontact.domain.SelectionLists;
import com.hlpp.clientcontact.dao.util.QueryHelper;
import com.hlpp.clientcontact.dao.util.MaintenanceHelper;
//import org.springframework.security.annotation.Secured;


/**
 * Policy DAO interface.
 *
 * @author Casmon Gordon
 */
public interface PolicyDao {

    /**
     * Find policy by id.
     */
    public Policy findPolicyById(Integer id);

    /**
     * Find new policy.
     */
    public Policy findNewPolicy();

    /**
     * Find broker by id.
     */
    public Broker findBrokerById(Integer id);

    /**
     * Find broker by id.
     */
    public BrokerDealer findBrokerDealerById(Integer id);

    /**
     * Find policys.
     */
    public Collection<Policy> findPolicys();


    /**
     * Find policys.
     */
    public Collection<Policy> findPolicys(String term);

    /**
     * Find brokers.
     */
    public Collection<Broker> findBrokersByPolicyId(Integer id);

    /**
     * Find dealers.
     */
    public Collection<BrokerDealer> findBrokerDealersByPolicyId(Integer id);

    /**
     * Find contacts
     */
    public Collection<ContactCase> findContactsByPolicyId(Integer id);

    /**
     * Find contacts
     */
    public Collection<ContactCase> findContactsByBrokerCompanyId(Integer id);

    /**
     * Find contacts
     */
    public Collection<ContactCase> findContactsByBrokerDealerId(Integer id);

    /**
     * Find case numbers
     */
    public Collection<String> findCaseNumbers(String term);

    /**
     * Find brokers.
     */
    public Collection<Broker> findBrokers();


    /**
     * Find brokers.
     */
    public Collection<BrokerDealer> findBrokerDealers();

    /**
     * Find policys using a start index and max number of results.
     */
    public Collection<Policy> findPolicys(final int startIndex, final int maxResults);

    /**
     * Find policys by case name.
     */
    public Collection<Policy> findPolicysByCaseNumber(String caseNumber);

    /**
     * Saves policy.
     */
    public Policy save(Policy policy);

    /**
     * Saves broker.
     */
    public Broker save(Broker broker);


    /**
     * Saves contact.
     */
    public ContactCase save(ContactCase contact);


    /**
     * Saves broker/dealer.
     */
    public BrokerDealer save(BrokerDealer dealer);

    /**
     * Deletes policy.
     */
    //@Secured ({"ROLE_ADMIN"})
    public void delete(Policy policy, Integer userid);

    /**
     * Deletes contact.
     */
    //@Secured ({"ROLE_ADMIN"})
    public void delete(ContactCase contact, Integer userid);

    /**
     * Deletes broker.
     */
    //@Secured ({"ROLE_ADMIN"})
    public void delete(Broker broker, Integer userid);

    /**
     * Deletes broker/dealer.
     */
    //@Secured ({"ROLE_ADMIN"})
    public void delete(BrokerDealer dealer, Integer userid);


    /**
     * Saves broker to policy by adding or updating record.
     */
    public Policy saveBroker(Integer id, Broker broker);

    /**
     * Deletes broker.
     */
    //@Secured ({"ROLE_ADMIN"})
    public Policy deleteBroker(Integer id, Integer brokerId);

    /**
     * Saves address to broker by adding or updating record.
     */
    public Broker saveBrokerAddress(Integer id, BrokerAddress address);

    /**
     * Saves address to dealer by adding or updating record.
     */
    public BrokerDealer saveDealerAddress(Integer id, BrokerDealerAddress address);

    /**
     * Deletes address.
     */
    public Broker deleteBrokerAddress(Integer id, Integer addressId);

    /**
     * Deletes address.
     */
    public BrokerDealer deleteDealerAddress(Integer id, Integer addressId);

    /**
     * Find broker policies
     */
	public Collection<String> findCasesByBrokerCompanyId(Integer id);

    /**
     * Find delaer policies.
     */
    public Collection<String> findCasesByBrokerDealerId(Integer id);

    /**
     * Find contact policies
     */
    public Collection<String> findCasesByContactId(Integer id);

    /**
     * Find selectionLists
     */
    public SelectionLists findSelectionLists();

    /**
     * Find contact.
     */
    public ContactCase findContactCaseById(Integer id);

    /**
     * Find new contact.
     */
    public ContactCase findNewContactCase(Integer p_id, Integer caseid);


    /**
     * Find contacts.
     */
    public Collection<ContactCase> findContactCases();

    /**
     * Find field.
     */
    public Field findFieldById(Integer id);

    /**
     * Find fields.
     */
    public Collection<Field> findFields();

    /**
     * Find query helper collections.
     */
    public QueryHelper findQueryHelper();

    /**
     * Find query helper collections.
     */
    public QueryHelper findQueryHelper(String param);

    /**
     * Find maintenance helper collections.
     */
    public MaintenanceHelper findMaintenanceHelper();

    /**
     * Find maintenance helper collections.
     */
    public MaintenanceHelper findMaintenanceHelper(String param);

    /**
     * Find result of policy query.
     */
    public Collection<PolicyDataOutput> findQueryResults(PolicyDataInput param);

    /**
     * Find result of person query.
     */
    public Collection<PersonDataOutput> findPersonQueryResults(PersonDataInput param);


    /**
     * Find collection of broker contacts
     */
    public Collection<BrokerContact> findBrokerContacts(Integer id);

    /**
     * Find collection of dealer contacts
     */
    public Collection<DealerContact> findDealerContacts(Integer id);


    /**
     * Find collection of contacts
     */
    public Collection<ContactCase> findContactsByIdAndCase(Integer p_id, Integer c_id);

    /**
     * Find new address
     */
    public Address findNewPersonAddress(Integer p_id) ;

    /**
     * Find new broker address
     */
    public BrokerAddress findNewBrokerAddress(Integer b_id) ;
    /**
     * Find new dealer address
     */
    public BrokerDealerAddress findNewDealerAddress(Integer d_id) ;

    /**
     * Find collection of contacts
     */
    public Collection<ContactCase> findContactsByPersonIdAndBrokerId(Integer p_id, Integer b_id);

    /**
     * Find collection of contacts
     */
    public Collection<ContactCase> findContactsByPersonIdAndDealerId(Integer p_id, Integer d_id);

    /**
     * Find collection of States
     */
    public Collection<State> findStates();

    /**
     * Find collection of Broker Address by Client Id
     */
    public Set<BrokerAddress> findBrokerAddressByClientId(Integer id);

    /**
     * Find collection of Broker Dealer Address by Client Id
     */
    public Set<BrokerDealerAddress> findBrokerDealerAddressByClientId(Integer id);

    /**
     * Find collection of Contact Cases by person Id
     */
    public Collection<ContactCase> findContactsByPersonId(Integer p_id);


    /**
     * Update maintenance tables
     */
	public String updateMaintTable(String value, String searchString, Integer id, String opt);


}
