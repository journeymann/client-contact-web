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
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.PersistenceContext;

import com.hlpp.clientcontact.domain.Broker;
import com.hlpp.clientcontact.domain.BrokerContact;
import com.hlpp.clientcontact.domain.BrokerDealer;
import com.hlpp.clientcontact.domain.DealerContact;
import com.hlpp.clientcontact.domain.Address;
import com.hlpp.clientcontact.domain.BrokerAddress;
import com.hlpp.clientcontact.domain.BrokerDealerAddress;
import com.hlpp.clientcontact.domain.Policy;
import com.hlpp.clientcontact.domain.Person;
import com.hlpp.clientcontact.domain.ContactCase;
import com.hlpp.clientcontact.domain.SelectionLists;
import com.hlpp.clientcontact.domain.State;
import com.hlpp.clientcontact.domain.DataItem;
import com.hlpp.clientcontact.domain.Field;
import com.hlpp.clientcontact.domain.ContactType;
import com.hlpp.clientcontact.domain.PolicyDataOutput;
import com.hlpp.clientcontact.domain.PolicyDataInput;
import com.hlpp.clientcontact.domain.PersonDataOutput;
import com.hlpp.clientcontact.domain.PersonDataInput;

import com.hlpp.clientcontact.domain.Attribute;
import com.hlpp.clientcontact.domain.CFilter;
import com.hlpp.clientcontact.domain.Filter;
import com.hlpp.clientcontact.domain.SubFilter;
import com.hlpp.clientcontact.domain.Display;
import com.hlpp.clientcontact.dao.util.QueryHelper;

import com.hlpp.clientcontact.dao.util.MaintenanceHelper;
import com.hlpp.clientcontact.domain.maintenance.ClientGroup;
import com.hlpp.clientcontact.domain.maintenance.CaseManager;
import com.hlpp.clientcontact.domain.maintenance.Team;
import com.hlpp.clientcontact.domain.maintenance.Series;
import com.hlpp.clientcontact.domain.maintenance.Country;
import com.hlpp.clientcontact.domain.maintenance.Library;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Statement;
import java.sql.CallableStatement;

import oracle.jdbc.*;

import javax.naming.Context;
import javax.naming.InitialContext;

import com.hlpp.clientcontact.security.SecureUserDetails;
import org.springframework.security.core.context.SecurityContextHolder;


/**
 * Policy DAO implementation.
 *
 * @author Casmon Gordon
 */
@Repository("policyDao")
@Transactional(readOnly = true)
public class PolicyDaoImpl implements PolicyDao {

    private EntityManager em = null;

    private final String DELETED = "Deleted";
    private final String ACTIVE = "Active";

    /**
     * Sets the entity manager.
     */
    @PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }

    /**
     * Find policy by id.
     */
    public Policy findPolicyById(Integer id) {
        Policy policy = em.find(Policy.class, id);
        return policy;
    }


    /**
     * Find new policy
     */
    public Policy findNewPolicy() {
        Policy policy = new Policy();
        return policy;
    }


    /**
     * Find new address
     */
    public Address findNewPersonAddress(Integer p_id) {
		Address address = new Address();
		address.setClientid(p_id);
        return address;
    }


    /**
     * Find new broker address
     */
    public BrokerAddress findNewBrokerAddress(Integer b_id) {
		BrokerAddress address = new BrokerAddress();
		address.setClientid(b_id);
        return address;
    }

    /**
     * Find new dealer address
     */
    public BrokerDealerAddress findNewDealerAddress(Integer d_id) {
		BrokerDealerAddress address = new BrokerDealerAddress();
		address.setClientid(d_id);
        return address;
    }


    /**
     * Find brokers.
     */
    public Broker findBrokerById(Integer id) {
        Broker broker = em.find(Broker.class, id);
        return broker;
    }


    /**
     * Find field.
     */
    public Field findFieldById(Integer id) {
        return em.find(Field.class, id);
    }


    /**
     * Find brokers/dealers.
     */
    public BrokerDealer findBrokerDealerById(Integer id) {
        BrokerDealer dealer = em.find(BrokerDealer.class, id);
        return dealer;
    }


    /**
     * Find contact.
     */
    public ContactCase findContactCaseById(Integer id) {

		ContactCase contact = em.find(ContactCase.class, id);
        return contact;
    }


    /**
     * Find contact.
     */
    public ContactCase findNewContactCase(Integer p_id, Integer caseid) {

		ContactCase contact = new ContactCase();
		Person p = em.find(Person.class, p_id);
		contact.setContactid(p_id);
		contact.setCaseid(caseid);
		contact.setPerson(p);
        return contact;
    }



    /**
     * Find query helper collections.
     */
    @SuppressWarnings("unchecked")
    public QueryHelper findQueryHelper(){

		return findQueryHelper("");

	}

    /**
     * Find query helper collections.
     */
    @SuppressWarnings("unchecked")
    public QueryHelper findQueryHelper(String param){

		QueryHelper query = new QueryHelper();
		query.setAttributes(this.findAttributes());
		query.setCfilters(this.findCFilters());
		query.setPfilters(this.findPFilters());
		query.setSfilters(this.findSFilters(param));
		query.setDisplays(this.findDisplays());
		query.cleanup();

		return query;

	}

    /**
     * Find maintenance helper collections.
     */
    @SuppressWarnings("unchecked")
    public MaintenanceHelper findMaintenanceHelper(){

		return findMaintenanceHelper("");

	}

    /**
     * Find maintenance helper collections.
     */
    @SuppressWarnings("unchecked")
    public MaintenanceHelper findMaintenanceHelper(String param){

		MaintenanceHelper helper = new MaintenanceHelper();

		helper.setClientGroups(this.findClientGroups());
		helper.setCaseManagers(this.findCaseManagers());
		helper.setTeams(this.findTeams());
		helper.setSeries(this.findSeries());
		helper.setCountries(this.findCountries());
		helper.setLibraries(this.findLibraries());
		helper.setTables(this.findTables());
		helper.setTableSelected(param);

		return helper;

	}

    /**
     * Find selectionLists
     */
    @SuppressWarnings("unchecked")
    public SelectionLists findSelectionLists() {

		ArrayList<ContactType> contacttypes = (ArrayList<ContactType>)this.findContactTypes();
		ArrayList<DataItem> series = (ArrayList<DataItem>)this.findDataList(DataItem.SERIES);
		ArrayList<DataItem> client_groups = (ArrayList<DataItem>)this.findDataList(DataItem.CLIENTGROUP);
		ArrayList<DataItem> teams = (ArrayList<DataItem>)this.findDataList(DataItem.TEAM);
		ArrayList<DataItem> system = (ArrayList<DataItem>)this.findDataList(DataItem.SYSTEM);
		ArrayList<DataItem> casemanagers = (ArrayList<DataItem>)this.findDataList(DataItem.CASEMANAGER);
		ArrayList<State> states = (ArrayList<State>)this.findStates();

		ArrayList<DataItem> brokers = new ArrayList<DataItem>();
		ArrayList<DataItem> policys = new ArrayList<DataItem>();
		ArrayList<DataItem> dealers = new ArrayList<DataItem>();
		ArrayList<DataItem> countries = new ArrayList<DataItem>();
		ArrayList<DataItem> yesno = new ArrayList<DataItem>();


		DataItem dt = new DataItem();

		for (Broker b : this.findBrokers()) {
			dt = new DataItem();
			dt.setKey(b.getBrokerid().toString());
			dt.setValue(b.getBrokerCompany());

			brokers.add(dt);
		}

		for (BrokerDealer d : this.findBrokerDealers()) {
			dt = new DataItem();
			dt.setKey(d.getBrokerDealerid().toString());
			dt.setValue(d.getBrokerDealerName());

			dealers.add(dt);
		}

		for (Policy p : this.findPolicys()) {
			dt = new DataItem();
			dt.setKey(p.getCaseid().toString());
			dt.setValue(p.getCasecode());

			policys.add(dt);
		}

		for (Country c : this.findCountries()) {
			dt = new DataItem();
			dt.setKey(c.getCountryCode());
			dt.setValue(c.getCountry());

			countries.add(dt);
		}


		Collections.sort(contacttypes);
		Collections.sort(series);
		Collections.sort(client_groups);
		Collections.sort(teams);
		Collections.sort(system);
		Collections.sort(casemanagers);
		Collections.sort(brokers);
		Collections.sort(dealers);
		Collections.sort(policys);

		dt = new DataItem();
		dt.setKey(null);
		dt.setValue("Please select");

		ContactType ct = new ContactType();
		ct.setId(0);
		ct.setType("Please select");
		ct.setSeq(0);

		contacttypes.add(0,ct);
		client_groups.add(0,dt);
		series.add(0,dt);
		teams.add(0,dt);
		system.add(0,dt);
		casemanagers.add(0,dt);
		brokers.add(0,dt);
		dealers.add(0,dt);

		dt = new DataItem();
		dt.setKey("");
		dt.setValue("");
		yesno.add(0, dt);

		dt = new DataItem();
		dt.setKey("Yes");
		dt.setValue("Yes");
		yesno.add(1, dt);

		dt = new DataItem();
		dt.setKey("No");
		dt.setValue("No");
		yesno.add(2, dt);

		SelectionLists lists = new SelectionLists();
		lists.setContacttypes(contacttypes);
		lists.setSeries(series);
		lists.setClient_groups(client_groups);
		lists.setTeams(teams);
		lists.setSystem(system);
		lists.setCasemanagers(casemanagers);
		lists.setBrokers(brokers);
		lists.setDealers(dealers);
		lists.setStates(states);
		lists.setCountries(countries);
		lists.setPolicys(policys);
		lists.setYesno(yesno);

		return lists;

    }



	@SuppressWarnings("unchecked")
    private Connection getConnection() throws SQLException{

		//return JDBCUtil.getConnection("jdbc/CONTACTS_DS");


		DataSource ds = null;

		try{

			Context initContext = new InitialContext();
			Context envContext  = (Context)initContext.lookup("java:/comp/env");

			ds = (DataSource)envContext.lookup("jdbc/CONTACTS_DS");

        }catch (NamingException nex)
        {
			throw new SQLException(nex.getMessage());
        }

		Connection conn = ds.getConnection();
		return conn;

	}



	@SuppressWarnings("unchecked")
    public Collection<PolicyDataOutput> findQueryResults(PolicyDataInput param){

        Connection conn = null;
        CallableStatement stmt = null;
        String sql;
        Collection list = null;
        String output = "OK";
        Collection<PolicyDataOutput> results=null;

        try {
            conn = this.getConnection();

            sql = "BEGIN pkg_read.sp_GetReportList(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?); END;";
            stmt = (CallableStatement) conn.prepareCall(sql);
			stmt.setString(1, param.getParam1());
			stmt.setString(2, param.getParam2());
			stmt.setString(3, param.getServicingbroker());
			stmt.setString(4, param.getCommissionablebroker());
			stmt.setString(5, param.getBrokerrecord());
			stmt.setString(6, param.getPrimaryservicecontact());
			stmt.setString(7, param.getPrimarycompliancecontact());
			stmt.setString(8, param.getKeyclientcontact());
			stmt.setString(9, param.getOwnercontact());
			stmt.setString(10, param.getAuthorizedsignatory());
			stmt.setString(11, param.getReportalaccess());
			stmt.setString(12, param.getElectronicreport());
			stmt.setString(13, param.getClientreport());
			stmt.setString(14, param.getFundlevelreport());
			stmt.setString(15, param.getCustomdeliverable());
			stmt.setString(16, param.getNewsletter());
			stmt.setString(17, param.getSSsweep());
			stmt.setString(18, param.getDeathclaimnotification());
			stmt.setString(19, param.getPpmamendment());
			stmt.setString(20, param.getAnnualsemireport());
			stmt.setString(21, param.getFundprospectussupplement());
			stmt.setString(22, param.getPrivacynotice());
			stmt.setString(23, param.getTls());
			stmt.registerOutParameter(24, OracleTypes.CURSOR);
			stmt.registerOutParameter(25, OracleTypes.VARCHAR);

            stmt.execute();
			results = processPolicyResults(stmt, param);
            output  = stmt.getString(25);
		} catch (java.sql.SQLException e){
			//TODO
        } finally {
                try {
                	if(stmt != null)
                    	stmt.close();
                	if (conn != null)
                    conn.close();
                } catch (Exception e) {
                    //TODO
                }
        }
        return results;

	}



	@SuppressWarnings("unchecked")
    public Collection<PersonDataOutput> findPersonQueryResults(PersonDataInput param){

        Connection conn = null;
        CallableStatement stmt = null;
        String sql;
        Collection list = null;
        String output = "OK";
        Collection<PersonDataOutput> results=null;

		System.out.println("************************ person report param="+param.getParam1()+"********************************");
        try {
            conn = this.getConnection();

            sql = "BEGIN pkg_read.sp_GetReportContactList(?,?,?); END;";
            stmt = (CallableStatement) conn.prepareCall(sql);
			stmt.setString(1, param.getParam1());
			stmt.registerOutParameter(2, OracleTypes.CURSOR);
			stmt.registerOutParameter(3, OracleTypes.VARCHAR);

            stmt.execute();
			results = processPersonResults(stmt, param);
            output  = stmt.getString(3);
		} catch (java.sql.SQLException e){
			//TODO
        } finally {
                try {
                	if(stmt != null)
                    	stmt.close();
                	if (conn != null)
                    conn.close();
                } catch (Exception e) {
                    //TODO
                }
        }
        return results;
	}

    /**
     * Find query result policy collections.
     */
    @SuppressWarnings("unchecked")
    private Collection<PolicyDataOutput> processPolicyResults(java.sql.CallableStatement stmt, PolicyDataInput param) throws SQLException{
		PolicyDataOutput line = new PolicyDataOutput();

        ArrayList results = new ArrayList();
        ResultSet cursor = null;
        try {
           	cursor = (ResultSet) stmt.getObject(24);

            while (cursor.next()) {
                line = new PolicyDataOutput();
                line.setContactid(cursor.getInt("contact_id"));
				line.setFirstname((cursor.getString("first_name")==null)? " " : cursor.getString("first_name"));
				line.setLastname((cursor.getString("last_name")==null)? "  " : cursor.getString("last_name"));
				line.setCaseNumber((cursor.getString("casecode")==null)? "" : cursor.getString("casecode"));
				line.setContacttype((cursor.getString("contact_type")==null)? " " : cursor.getString("contact_type"));
				line.setJobtitle((cursor.getString("job_title")==null)? " " : cursor.getString("job_title"));
				line.setWorkcompany((cursor.getString("work_company")==null)? " " : cursor.getString("work_company"));
				line.setEmail((cursor.getString("email_address")==null)? " " : cursor.getString("email_address"));
				line.setClientgroup((cursor.getString("client_group")==null)? " " : cursor.getString("client_group"));
				line.setInformalownername((cursor.getString("informal_owner_name")==null)? " " : cursor.getString("informal_owner_name"));
				line.setFormalownername((cursor.getString("formal_owner_name")==null)? " " : cursor.getString("formal_owner_name"));
				line.setSystemcode((cursor.getString("system_code")==null)? " " : cursor.getString("system_code"));
				line.setSeriescode((cursor.getString("series_code")==null)? " " : cursor.getString("series_code"));
				line.setTeamcode((cursor.getString("team_code")==null)? " " : cursor.getString("team_code"));
				line.setCasemanager((cursor.getString("case_manager")==null)? " " : cursor.getString("case_manager"));
				line.setPpmnumber((cursor.getString("ppm_no")==null)? " " : cursor.getString("ppm_no"));
				line.setPolicynotes((cursor.getString("policy_notes")==null)? " " : cursor.getString("policy_notes"));
				line.setCommissionablebroker((cursor.getString("commissionable_broker")==null)? " " : cursor.getString("commissionable_broker"));
				line.setServicingbroker((cursor.getString("servicing_broker")==null)? " " : cursor.getString("servicing_broker"));
				line.setBrokerrecord((cursor.getString("broker_record")==null)? " " : cursor.getString("broker_record"));
				line.setPrimaryservicecontact((cursor.getString("primary_service_contact")==null)? " " : cursor.getString("primary_service_contact"));
				line.setPrimarycompliancecontact((cursor.getString("primary_compliance_contact")==null)? " " : cursor.getString("primary_compliance_contact"));
				line.setKeyclientcontact((cursor.getString("key_client_contact")==null)? " " : cursor.getString("key_client_contact"));
				line.setOwnercontact((cursor.getString("owner_contact")==null)? " " : cursor.getString("owner_contact"));
				line.setAuthorizedsignatory((cursor.getString("authorized_signatory")==null)? " " : cursor.getString("authorized_signatory"));
				line.setReportalaccess((cursor.getString("reportal_access")==null)? " " : cursor.getString("reportal_access"));
				line.setElectronicreport((cursor.getString("electronic_report")==null)? " " : cursor.getString("electronic_report"));
				line.setClientreport((cursor.getString("client_report")==null)? " " : cursor.getString("client_report"));
				line.setFundlevelreport((cursor.getString("fund_level_report")==null)? " " : cursor.getString("fund_level_report"));
				line.setCustomdeliverable((cursor.getString("custom_deliverable")==null)? " " : cursor.getString("custom_deliverable"));
				line.setNewsletter((cursor.getString("newsletter")==null)? " " : cursor.getString("newsletter"));
				line.setSSsweep((cursor.getString("social_security_sweep")==null)? " " : cursor.getString("social_security_sweep"));
				line.setDeathclaimnotification((cursor.getString("death_claim_notification")==null)? " " : cursor.getString("death_claim_notification"));
				line.setPpmamendment((cursor.getString("ppm_amendment")==null)? " " : cursor.getString("ppm_amendment"));
				line.setAnnualsemireport((cursor.getString("annual_semi_report")==null)? " " : cursor.getString("annual_semi_report"));
				line.setFundprospectussupplement((cursor.getString("fund_prospectus_upplement")==null)? " " : cursor.getString("fund_prospectus_upplement"));
				line.setPrivacynotice((cursor.getString("privacy_notice")==null)? " " : cursor.getString("privacy_notice"));
				line.setBrokercompany((cursor.getString("broker_company")==null)? " " : cursor.getString("broker_company"));
				line.setTls((cursor.getString("tls")==null)? " " : cursor.getString("tls"));

				line.setWorkphone((cursor.getString("primary_work_phone")==null)? " " : cursor.getString("primary_work_phone"));
				line.setAddr1((cursor.getString("addr1")==null)? " " : cursor.getString("addr1"));
				line.setAddr2((cursor.getString("addr2")==null)? " " : cursor.getString("addr2"));
				line.setCity((cursor.getString("city")==null)? " " : cursor.getString("city"));
				line.setState((cursor.getString("state")==null)? " " : cursor.getString("state"));
				line.setZip((cursor.getString("zip")==null)? " " : cursor.getString("zip"));
				line.setCountry((cursor.getString("country")==null)? " " : cursor.getString("country"));

				line.setCellphone((cursor.getString("person_cell_phone")==null)? " " : cursor.getString("person_cell_phone"));
				line.setTitle((cursor.getString("title")==null)? " " : cursor.getString("title"));
				line.setFax((cursor.getString("primary_fax")==null)? " " : cursor.getString("primary_fax"));
				line.setMainaddress((cursor.getString("main_address")==null)? " " : cursor.getString("main_address"));
				line.setBrokercompany((cursor.getString("broker_company")==null)? " " : cursor.getString("broker_company"));
				line.setBrokerdealer((cursor.getString("brokerdealer_name")==null)? " " : cursor.getString("brokerdealer_name"));

				line.setFax2((cursor.getString("secondary_fax")==null)? " " : cursor.getString("secondary_fax"));
				line.setWorkphone2((cursor.getString("secondary_work_phone")==null)? " " : cursor.getString("secondary_work_phone"));
				line.setNotes((cursor.getString("notes")==null)? " " : cursor.getString("notes"));



				boolean addressShow = false;

				for (String s : param.getDisp()) {

					int i = new Integer(s).intValue();

					switch(i){
						case Display.ADDRESS: addressShow = true; break;
					}

				}

				if(addressShow){
					if(line.getMainaddress()!= null && line.getMainaddress().equals("Yes")){
						results.add(line);
					}
				}else{
					results.add(line);
				}



            }//while
          }//try
          finally
          {
        	  try{
        		  if(cursor != null)
        			  cursor.close();
        	  }
        	  catch(Exception e)
        	  {
				//TODO
        	  }
          }

        return results;

	}


    /**
     * Find query result person collections.
     */
    @SuppressWarnings("unchecked")
    private Collection<PersonDataOutput> processPersonResults(java.sql.CallableStatement stmt, PersonDataInput param) throws SQLException{
		PersonDataOutput line = new PersonDataOutput();

        ArrayList results = new ArrayList();
        ResultSet cursor = null;
        try {
			cursor = (ResultSet) stmt.getObject(2);

            while (cursor.next()) {
                line = new PersonDataOutput();
                line.setContactid(cursor.getInt("contact_id"));
				line.setFirstname(cursor.getString("first_name"));
				line.setLastname(cursor.getString("last_name"));
				line.setContacttype(cursor.getString("contact_type"));
				line.setJobtitle(cursor.getString("job_title"));
				line.setWorkcompany(cursor.getString("work_company"));
				line.setEmail(cursor.getString("email_address"));

				line.setWorkphone(cursor.getString("primary_work_phone"));
				line.setAddr1(cursor.getString("addr1"));
				line.setAddr2(cursor.getString("addr2"));
				line.setCity(cursor.getString("city"));
				line.setState(cursor.getString("state"));
				line.setZip(cursor.getString("zip"));
				line.setCountry(cursor.getString("country"));

				line.setCellphone(cursor.getString("person_cell_phone"));
				line.setTitle(cursor.getString("title"));
				line.setFax(cursor.getString("primary_fax"));
				line.setMainaddress(cursor.getString("main_address"));
				line.setBrokercompany(cursor.getString("broker_company"));
				line.setBrokerdealer(cursor.getString("brokerdealer_name"));
				line.setFax2(cursor.getString("secondary_fax"));
				line.setWorkphone2(cursor.getString("secondary_work_phone"));
				line.setNotes(cursor.getString("notes"));

				boolean addressShow = false;

				for (String s : param.getDisp()) {

					int i = new Integer(s).intValue();

					switch(i){
						case Display.ADDRESS: addressShow = true; break;
					}

				}

				if(addressShow){
					if(line.getMainaddress()!= null && line.getMainaddress().equals("Yes")){
						results.add(line);
					}
				}else{
					results.add(line);
				}


            }//while
          }//try
          finally
          {
        	  try{
        		  if(cursor != null)
        			  cursor.close();
        	  }
        	  catch(Exception e)
        	  {
				//TODO
        	  }
          }

        return results;

	}

    /**
     * Find attributes
     */
    @SuppressWarnings("unchecked")
    public Collection<Attribute> findAttributes() {
        return em.createNamedQuery("findReportAttributeInOrder").getResultList();
    }

    /**
     * Find CFilters
     */
    @SuppressWarnings("unchecked")
    public Collection<CFilter> findCFilters() {
        return em.createNamedQuery("findReportContactFilterInOrder").getResultList();
    }


    /**
     * Find Filters
     */
    @SuppressWarnings("unchecked")
    public Collection<Filter> findPFilters() {
        return em.createNamedQuery("findReportFilterInOrder").getResultList();
    }



    /**
     * Find Second Filters values
     */
    @SuppressWarnings("unchecked")
    public Collection<SubFilter> findSFilters(String param) {
        return em.createNamedQuery("findReportSubFilterInOrder").setParameter("searchFilter",param).getResultList();
    }


    /**
     * Find Displays
     */
    @SuppressWarnings("unchecked")
    public Collection<Display> findDisplays() {

        Collection<Display> list = new ArrayList<Display>();

        Display display = new Display();
		display.setId(Display.EMAIL);
        display.setDesc("Email Address");
        list.add(display);

		display = new Display();
		display.setId(Display.EMAIL_NO_DUP);
        display.setDesc("Email Address, No duplicates");
        list.add(display);


		display = new Display();
		display.setId(Display.ADDRESS);
        display.setDesc("Primary Address");
        list.add(display);

		display = new Display();
		display.setId(Display.PHONE);
        display.setDesc("Phone Number");
        list.add(display);


		display = new Display();
		display.setId(Display.ALL);
        display.setDesc("All Contact Person Fields");
        list.add(display);


        return list;
    }

    /**
     * Find policys using a start index and max number of results.
     */
    @SuppressWarnings("unchecked")
    public Collection<Policy> findPolicys(final int startIndex, final int maxResults) {
        return em.createQuery("select p from Policy p where p.status != 'Deleted' and p.caseid != 200 order by p.casecode")
            .setFirstResult(startIndex).setMaxResults(maxResults).getResultList();
    }



    /**
     * Find broker addresses.
     */
    @SuppressWarnings("unchecked")
    public Set<BrokerAddress> findBrokerAddressByClientId(Integer id) {
        Query query = em.createQuery("select a from BrokerAddress a where a.clientid = :id and a.status != 'Deleted'");
        query.setParameter("id", id);
        Collection list = query.getResultList();

        return new HashSet<BrokerAddress>(list);
    }


    /**
     * Find dealer addresses.
     */
    @SuppressWarnings("unchecked")
    public Set<BrokerDealerAddress> findBrokerDealerAddressByClientId(Integer id) {
        Query query = em.createQuery("select a from BrokerDealerAddress a where a.clientid = :id and a.status != 'Deleted'");
        query.setParameter("id", id);
        Collection list = query.getResultList();

        return new HashSet<BrokerDealerAddress>(list);
    }


    /**
     * Find policys.
     */
    @SuppressWarnings("unchecked")
    public Collection<Policy> findPolicys() {
        return em.createQuery("select p from Policy p where p.status != 'Deleted' and p.caseid != 200 order by p.casecode").getResultList();
    }


    /**
     * Find states.
     */
    @SuppressWarnings("unchecked")
    public Collection<State> findStates() {
        return em.createQuery("select s from State s order by s.code").getResultList();
    }


    /**
     * Find contacttypes.
     */
    @SuppressWarnings("unchecked")
    public Collection<ContactType> findContactTypes() {
        return em.createQuery("select c from ContactType c order by c.type").getResultList();
    }


    /**
     * Find contacts.
     */
    @SuppressWarnings("unchecked")
    public Collection<ContactCase> findContactCases() {
        return em.createQuery("select p from ContactCase p where p.status != 'Deleted' order by p.caseid").getResultList();
    }


    /**
     * Find fields.
     */
    @SuppressWarnings("unchecked")
    public Collection<Field> findFields() {
        return em.createQuery("select p from Field p order by p.name").getResultList();
    }

    /**
     * Find policys.
     */
    @SuppressWarnings("unchecked")
    public Collection<Policy> findPolicys(String term) {
        return em.createQuery("select p from Policy p where where p.status != 'Deleted' p.caseNumber like :term and p.caseid != 200 order by p.casecode")
        .setParameter("term", term).getResultList();
    }



    /**
     * Find policys.
     */
    @SuppressWarnings("unchecked")
    public Collection<String> findCaseNumbers(String term) {
        return em.createQuery("select p.casecode from Policy p where p.caseid != 200 order by p.casecode").getResultList();
    }



    /**
     * Find brokers.
     */
    @SuppressWarnings("unchecked")
    public Collection<Broker> findBrokers() {
        return em.createQuery("select b from Broker b where b.status != 'Deleted' order by b.brokerCompany").getResultList();
    }


    /**
     * Find brokers/dealers.
     */
    @SuppressWarnings("unchecked")
    public Collection<BrokerDealer> findBrokerDealers() {
        return em.createQuery("select b from BrokerDealer b where b.status != 'Deleted' order by b.brokerDealerName").getResultList();
    }


    /**
     * Find contacts.
     */
    @SuppressWarnings("unchecked")
    public Collection<ContactCase> findContactsByPolicyId(Integer id) {
        return em.createQuery("select b from ContactCase b, Person p where p.contactid = b.contactid and p.status != 'Deleted' and b.status != 'Deleted' and b.caseid = :id").setParameter("id", id).getResultList();
    }

    /**
     * Find contacts.
     */
    @SuppressWarnings("unchecked")
    public Collection<ContactCase> findContactsByBrokerCompanyId(Integer id) {
        return em.createQuery("select distinct b from ContactCase b, Person p where b.status != 'Deleted' and p.status != 'Deleted' and p.contactid = b.contactid and p.brokercompanyid = :id").setParameter("id", id).getResultList();
    }


    /**
     * Find broker policies
     */
    @SuppressWarnings("unchecked")
    public Collection<String> findCasesByBrokerCompanyId(Integer id) {
        return em.createQuery("select distinct o.casecode from ContactCase b, Policy o, Person p where p.contactid = b.contactid and b.status != 'Deleted' and p.status != 'Deleted' and o.caseid = b.caseid and p.brokercompanyid = :id order by o.casecode").setParameter("id", id).getResultList();
    }

    /**
     * Find contacts.
     */
    @SuppressWarnings("unchecked")
    public Collection<ContactCase> findContactsByBrokerDealerId(Integer id) {
        return em.createQuery("select distinct b from ContactCase b, Person p where b.status != 'Deleted' and p.status != 'Deleted' and p.contactid = b.contactid and p.brokerdealerid = :id").setParameter("id", id).getResultList();
    }


    /**
     * Find delaer policies.
     */
    @SuppressWarnings("unchecked")
    public Collection<String> findCasesByBrokerDealerId(Integer id) {
        return em.createQuery("select distinct o.casecode from ContactCase b, Policy o, Person p where p.contactid = b.contactid and b.status != 'Deleted' and p.status != 'Deleted' and o.caseid = b.caseid and p.brokerdealerid = :id order by o.casecode").setParameter("id", id).getResultList();
    }


    /**
     * Find contact policies
     */
    @SuppressWarnings("unchecked")
    public Collection<String> findCasesByContactId(Integer id) {
        return em.createQuery("select distinct p.casecode from ContactCase b , Policy p where p.caseid = b.caseid and p.caseid != 200 and b.status != 'Deleted' and b.contactid = :id order by p.casecode").setParameter("id", id).getResultList();
    }

    /**
     * Find brokers.
     */
    @SuppressWarnings("unchecked")
    public Collection<Broker> findBrokersByPolicyId(Integer id) {
        return em.createQuery("select distinct b from Broker b, ContactCase c, Person p where b.brokerid = p.brokercompanyid and p.contactid = c.contactid and p.status != 'Deleted' and c.status != 'Deleted' and c.caseid = :id order by b.brokerCompany").setParameter("id", id).getResultList();
    }

    /**
     * Find dealers.
     */
    @SuppressWarnings("unchecked")
    public Collection<BrokerDealer> findBrokerDealersByPolicyId(Integer id) {
        return em.createQuery("select distinct d from BrokerDealer d, ContactCase c, Person p where d.brokerDealerid = p.brokerdealerid and p.contactid=c.contactid and p.status != 'Deleted' and c.status != 'Deleted' and c.caseid = :id order by d.brokerDealerName").setParameter("id", id).getResultList();
    }

    /**
     * Find policys by case numbers.
     */
    @SuppressWarnings("unchecked")
    public Collection<Policy> findPolicysByCaseNumber(String casecode) {
        return em.createQuery("select p from Policy p where p.casecode = :casecode and p.caseid != 200 order by p.casecode").setParameter("casecode", casecode).getResultList();
    }

    /**
     * Find contacts by personid and caseid.
     */
    public Collection<ContactCase> findContactsByIdAndCase(Integer p_id, Integer case_id){
        Query query= em.createQuery("select c from ContactCase c where c.contactid = :p_id and c.status != 'Deleted' and c.caseid = :case_id");
        query.setParameter("p_id", p_id);
		query.setParameter("case_id", case_id);
        return query.getResultList();
	}

    /**
     * Find contacts by personid and brokerid.
     */
    public Collection<ContactCase> findContactsByPersonIdAndBrokerId(Integer p_id, Integer b_id){
        Query query= em.createQuery("select c from ContactCase c, Person p where c.contactid = p.contactid and c.status != 'Deleted' and c.contactid = :p_id and p.brokercompanyid = :b_id");
        query.setParameter("p_id", p_id);
		query.setParameter("b_id", b_id);
        return query.getResultList();
	}

    /**
     * Find contacts by personid and broker dealer id
     */
    public Collection<ContactCase> findContactsByPersonIdAndDealerId(Integer p_id, Integer d_id){
        Query query= em.createQuery("select c from ContactCase c, Person p where c.contactid = p.contactid and c.status != 'Deleted' and c.contactid = :p_id and p.brokerdealerid = :d_id");
        query.setParameter("p_id", p_id);
		query.setParameter("d_id", d_id);
        return query.getResultList();
	}


    /**
     * Find contacts by personid.
     */
    @SuppressWarnings("unchecked")
    public Collection<ContactCase> findContactsByPersonId(Integer p_id){
        return em.createQuery("select c from ContactCase c where c.contactid = :p_id and c.status != 'Deleted'").setParameter("p_id", p_id).getResultList();
    }



    /**
     * Find client groups.
     */
    @SuppressWarnings("unchecked")
    public Collection<ClientGroup> findClientGroups() {
        return em.createQuery("select p from ClientGroup p").getResultList();
    }


    /**
     * Find case managers.
     */
    @SuppressWarnings("unchecked")
    public Collection<CaseManager> findCaseManagers() {
        return em.createQuery("select p from CaseManager p").getResultList();
    }


    /**
     * Find teams.
     */
    @SuppressWarnings("unchecked")
    public Collection<Team> findTeams() {
        return em.createQuery("select p from Team p").getResultList();
    }

    /**
     * Find series.
     */
    @SuppressWarnings("unchecked")
    public Collection<Series> findSeries() {
        return em.createQuery("select p from Series p").getResultList();
    }

    /**
     * Find countries.
     */
    @SuppressWarnings("unchecked")
    public Collection<Country> findCountries() {
        return em.createQuery("select p from Country p").getResultList();
    }

    /**
     * Find libraries.
     */
    @SuppressWarnings("unchecked")
    public Collection<Library> findLibraries() {
        return em.createQuery("select p from Library p").getResultList();
    }

    /**
     * Find maintenance tables.
     */
    @SuppressWarnings("unchecked")
    public String[] findTables() {

		SecureUserDetails user = (SecureUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String dept = user.getDepartment();

		String[] list = { "ClientGroup", "CaseManager", "Team", "Series", "Country", "Library" };

		if(!dept.equals("CPO")){
			list = new String[1];
			list[0] = "Country";
	  	}

        return list;
    }

    /**
     * Saves policy.
     */
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public Policy save(Policy policy) {
		SecureUserDetails user = (SecureUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Integer userid = user.getUserid();

		policy.setUpdateby(userid.toString());
		policy.setUpdatedate(new Date());
		policy.setStatus(ACTIVE);

		Policy saved_policy = em.merge(policy);

        return saved_policy;
    }


    /**
     * Saves broker.
     */
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public Broker save(Broker broker) {
		SecureUserDetails user = (SecureUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Integer userid = user.getUserid();

		broker.setUpdatedBy(userid.toString());
		broker.setUpdateDate(new Date());
		broker.setStatus(ACTIVE);

        return em.merge(broker);
    }


    /**
     * Saves contact.
     */
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public ContactCase save(ContactCase contact) {

		SecureUserDetails user = (SecureUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Integer userid = user.getUserid();

		contact.setUpdatedby(userid.toString());
		contact.setUpdateddate(new Date());
		contact.setStatus(ACTIVE);

		ContactCase saved_contact = em.merge(contact);

        return saved_contact;
    }


    /**
     * Saves broker/dealer.
     */
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public BrokerDealer save(BrokerDealer dealer) {
		SecureUserDetails user = (SecureUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Integer userid = user.getUserid();

		dealer.setUpdatedBy(userid.toString());
		dealer.setUpdateDate(new Date());
		dealer.setStatus(ACTIVE);

        return em.merge(dealer);
    }


    /**
     * Deletes policy.
     */
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public void delete(Policy policy, Integer userid) {
		policy.setUpdateby(userid.toString());
		policy.setUpdatedate(new Date());
		policy.setStatus(DELETED);
        em.merge(policy);
    }


    /**
     * Deletes contact.
     */
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public void delete(ContactCase contact, Integer userid) {
		contact.setUpdatedby(userid.toString());
		contact.setUpdateddate(new Date());
		contact.setStatus(DELETED);
        em.merge(contact);
    }


    /**
     * Deletes broker.
     */
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public void delete(Broker broker, Integer userid) {
		broker.setUpdatedBy(userid.toString());
		broker.setUpdateDate(new Date());
		broker.setStatus(DELETED);
        em.merge(broker);
    }

    /**
     * Deletes broker/dealer.
     */
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public void delete(BrokerDealer dealer, Integer userid) {
		dealer.setUpdatedBy(userid.toString());
		dealer.setUpdateDate(new Date());
		dealer.setStatus(DELETED);
        em.merge(dealer);
    }



    /**
     * Saves broker to policy.
     */
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public Policy saveBroker(Integer id, Broker broker) {
        Policy policy = findPolicyById(id);

        if (policy.getBrokers().contains(broker)) {
            policy.getBrokers().remove(broker);
        }

        policy.getBrokers().add(broker);

        return save(policy);
    }

    /**
     * Deletes broker from policy.
     */
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public Policy deleteBroker(Integer id, Integer brokerId) {
        Policy policy = findPolicyById(id);

        Broker broker = new Broker();
        broker.setId(brokerId);

        if (policy.getBrokers().contains(broker)) {
            for (Broker a : policy.getBrokers()) {
                if (a.getId().equals(brokerId)) {
                    em.remove(a);
                    policy.getBrokers().remove(broker);

                    break;
                }
            }
        }

        return policy;
    }

    /**
     * Saves address to broker.
     */
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public Broker saveBrokerAddress(Integer id, BrokerAddress address) {

		SecureUserDetails user = (SecureUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Integer userid = user.getUserid();

		address.setUpdateby(userid);
		address.setUpdated(new Date());
		address.setStatus(ACTIVE);

        Broker broker = findBrokerById(id);

        if (broker.getAddresses().contains(address)) {
            broker.getAddresses().remove(address);
        }

        broker.getAddresses().add(address);

        return save(broker);
    }

    /**
     * Deletes address from broker.
     */
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public Broker deleteBrokerAddress(Integer id, Integer addressId) {
		SecureUserDetails user = (SecureUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Integer userid = user.getUserid();

        Broker broker = findBrokerById(id);

        BrokerAddress address = new BrokerAddress();
        address.setId(addressId);

        if (broker.getAddresses().contains(address)) {
            for (BrokerAddress a : broker.getAddresses()) {
                if (a.getId().equals(addressId)) {
                    //em.remove(a);
					a.setUpdateby(userid);
					a.setUpdated(new Date());
					a.setStatus(DELETED);
					em.merge(a);

                    broker.getAddresses().remove(address);

                    break;
                }
            }
        }

        return broker;
    }


    /**
     * Saves address to brokerDealer.
     */
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public BrokerDealer saveDealerAddress(Integer id, BrokerDealerAddress address) {

		SecureUserDetails user = (SecureUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Integer userid = user.getUserid();

		address.setUpdateby(userid);
		address.setUpdated(new Date());
		address.setStatus(ACTIVE);

        BrokerDealer dealer = findBrokerDealerById(id);

        if (dealer.getAddresses().contains(address)) {
            dealer.getAddresses().remove(address);
        }

        dealer.getAddresses().add(address);

        return save(dealer);
    }

    /**
     * Deletes address from brokerDealer.
     */
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public BrokerDealer deleteDealerAddress(Integer id, Integer addressId) {
		SecureUserDetails user = (SecureUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Integer userid = user.getUserid();

        BrokerDealer dealer = findBrokerDealerById(id);

        BrokerDealerAddress address = new BrokerDealerAddress();
        address.setId(addressId);

        if (dealer.getAddresses().contains(address)) {
            for (BrokerDealerAddress a : dealer.getAddresses()) {
                if (a.getId().equals(addressId)) {
                    //em.remove(a);
					a.setUpdateby(userid);
					a.setUpdated(new Date());
					a.setStatus(DELETED);
					em.merge(a);

                    dealer.getAddresses().remove(address);

                    break;
                }
            }
        }

        return dealer;
    }


	@SuppressWarnings("unchecked")
    public Collection<BrokerContact> findBrokerContacts(Integer id){

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        String sql = "";
        String val= "";
        Collection list = null;
        String output = "OK";
        Collection<BrokerContact> results = null;
        BrokerContact item = new BrokerContact();

        try {
            conn = this.getConnection();
			sql="select * from vw_brokercompany_contactcase where brokercompany_id="+id+" order by contact_name asc";;

            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            results = new ArrayList<BrokerContact>();

            while (rs != null && rs.next()) {

            	item = new BrokerContact();
            	item.setBrokerid(id);
            	item.setCompanyName(rs.getString("company_name"));
            	item.setContactName(rs.getString("contact_name"));
            	item.setContactid(rs.getInt("contact_id"));
            	item.setContactType(rs.getString("contact_type"));
            	item.setCasecodes(rs.getString("casecode"));

           		results.add(item);
            }

		} catch (java.sql.SQLException e){
			//TODO
        } finally {
                try {
                	if(stmt != null)
                    	stmt.close();
                	if (conn != null)
                    conn.close();
                } catch (Exception e) {
                    //TODO
                }
        }
        return results;
	}

	@SuppressWarnings("unchecked")
    public Collection<DealerContact> findDealerContacts(Integer id){

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        String sql = "";
        String val= "";
        Collection list = null;
        String output = "OK";
        Collection<DealerContact> results = null;
        DealerContact item = new DealerContact();

        try {
            conn = this.getConnection();
			sql="select * from vw_brokerdelaer_contactcase where brokerdealer_id="+id+" order by contact_name asc";

            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            results = new ArrayList<DealerContact>();

            while (rs != null && rs.next()) {

            	item = new DealerContact();
            	item.setDealerid(id);
            	item.setDealerName(rs.getString("brokerdealer_name"));
            	item.setContactName(rs.getString("contact_name"));
            	item.setContactid(rs.getInt("contact_id"));
            	item.setContactType(rs.getString("contact_type"));
            	item.setCasecodes(rs.getString("casecode"));

           		results.add(item);
            }

		} catch (java.sql.SQLException e){
			//TODO
        } finally {
                try {
                	if(stmt != null)
                    	stmt.close();
                	if (conn != null)
                    conn.close();
                } catch (Exception e) {
                    //TODO
                }
        }
        return results;
	}




	@SuppressWarnings("unchecked")
    public Collection<DataItem> findDataList(int type){

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        String sql = "";
        String val= "";
        Collection list = null;
        String output = "OK";
        Collection<DataItem> results = null;
        DataItem item = new DataItem();

		System.out.println("************************ Get DataList type="+type+" ********************************");
        try {
            conn = this.getConnection();

			switch(type){

				case DataItem.CLIENTGROUP : sql = "select * from master_clientgroup order by client_group asc"; break;
				case DataItem.SERIES : sql = "select * from master_series order by series_code asc"; break;
				case DataItem.TEAM : sql = "select * from master_team order by team_code asc"; break;
				case DataItem.CASEMANAGER : sql = "select * from master_casemanager where status='Active' order by case_manager asc"; break;
				case DataItem.SYSTEM : sql = "select * from master_system order by system_code asc"; break;

			}


            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            results = new ArrayList<DataItem>();

            while (rs != null && rs.next()) {

				switch(type){

					case DataItem.CLIENTGROUP : val = rs.getString("client_group"); break;
					case DataItem.SERIES : val = rs.getString("series_code"); break;
					case DataItem.TEAM : val = rs.getString("team_code"); break;
					case DataItem.CASEMANAGER : val = rs.getString("case_manager"); break;
					case DataItem.SYSTEM : val = rs.getString("system_code"); break;

				}

            	item = new DataItem();
            	item.setKey(val);
            	item.setValue(val);

           		results.add(item);
            }

		} catch (java.sql.SQLException e){
			//TODO
        } finally {
                try {
                	if(stmt != null)
                    	stmt.close();
                	if (conn != null)
                    conn.close();
                } catch (Exception e) {
                    //TODO
                }
        }
        return results;
	}

	@SuppressWarnings("unchecked")
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public String updateMaintTable(String value, String table_name, Integer id, String opt){

		SecureUserDetails user = (SecureUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Integer userid = user.getUserid();

		String output = "Success";

		if(opt.equals("I") || opt.equals("U")){
			if(table_name.equals("ClientGroup")){
				ClientGroup row = em.find(ClientGroup.class, id);
				if(row==null) row = new ClientGroup();
				row.setClientGroup(value);
				row.setUpdateBy(userid);
				row.setUpdateDate(new Date());
				em.merge(row);
			}else if(table_name.equals("CaseManager")){
				CaseManager row = em.find(CaseManager.class, id);
				if(row==null) row = new CaseManager();
				row.setStatus("Active");
				row.setCaseManager(value);
				row.setUpdateBy(userid);
				row.setUpdateDate(new Date());
				em.merge(row);
			}else if(table_name.equals("Team")){
				Team row = em.find(Team.class, id);
				if(row==null) row = new Team();
				row.setTeam(value);
				row.setUpdateBy(userid);
				row.setUpdateDate(new Date());
				em.merge(row);
			}else if(table_name.equals("Series")){
				Series row = em.find(Series.class, id);
				if(row==null) row = new Series();
				row.setSeries(value);
				row.setUpdateBy(userid);
				row.setUpdateDate(new Date());
				em.merge(row);
			}else if(table_name.equals("Country")){
				Country row = em.find(Country.class, id);
				if(row==null) row = new Country();
				if(value!=null) row.setCountryCode((value.substring(0,3)).toUpperCase());
				row.setCountry(value);
				row.setUpdateBy(userid);
				row.setUpdateDate(new Date());
				em.merge(row);
			}else if(table_name.equals("Library")){
				Library row = em.find(Library.class, id);
				if(row==null) row = new Library();
				row.setLibrary(value);
				row.setUpdateBy(userid);
				row.setUpdateDate(new Date());
				em.merge(row);
			}

		}else if(opt.equals("D")){
			if(table_name.equals("CaseManager")){
				CaseManager row = em.find(CaseManager.class, id);
				em.remove(row);
			}
		}

		return output;
	}



}
