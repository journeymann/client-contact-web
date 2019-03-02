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
import java.util.Date;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.PersistenceContext;

import com.hlpp.clientcontact.domain.Address;
import com.hlpp.clientcontact.domain.Person;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import com.hlpp.clientcontact.security.SecureUserDetails;
import org.springframework.security.core.context.SecurityContextHolder;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;

/**
 * Person DAO implementation.
 *
 * @author Casmon Gordon
 */
@Repository("personDao")
@Transactional(readOnly = true)
public class PersonDaoImpl implements PersonDao {


    @Autowired
    protected PolicyDao policyDao = null;

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
     * Find persons.
     */
    public Person findPersonById(Integer id) {
        Person person = em.find(Person.class, id);
        //person.setAddresses(this.findPersonAddressByClientId(id));
        person.setSelectionLists(policyDao.findSelectionLists());
        return person;

    }

    /**
     * Create person.
     */
    public Person findNewPerson() {
        Person person = new Person();
        person.setSelectionLists(policyDao.findSelectionLists());
        return person;
    }

    /**
     * Find persons using a start index and max number of results.
     */
    @SuppressWarnings("unchecked")
    public Collection<Person> findPersons(final int startIndex, final int maxResults) {
        return em.createQuery("select p from Person p where p.status != 'Deleted' order by p.lastName, p.firstName")
            .setFirstResult(startIndex).setMaxResults(maxResults).getResultList();
    }

    /**
     * Find persons.
     *
    @SuppressWarnings("unchecked")
    public Collection<Person> findPersons() {
        return em.createQuery("select p from Person p where p.status != 'Deleted' order by p.lastName, p.firstName").getResultList();
    }*/


	@SuppressWarnings("unchecked")
    public Collection<Person> findPersons(){

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        String sql = "";
        String val= "";
        Collection list = null;
        String output = "OK";
        Collection<Person> results = null;
        Person item = new Person();

        try {
            conn = this.getConnection();
			sql="select * from contact p where p.status != 'Deleted' order by p.last_name asc";

            stmt = conn.createStatement();
            long startTime = System.currentTimeMillis();

            rs = stmt.executeQuery(sql);
            results = new ArrayList<Person>();

            while (rs.next()) {

            	item = new Person();
            	item.setContactid(rs.getInt("contact_id"));
				item.setFirstName(rs.getString("first_name"));
				item.setLastName(rs.getString("last_name"));
				item.setPrimaryphone(rs.getString("primary_work_phone"));
				item.setCellphone(rs.getString("person_cell_phone"));
				item.setCompany(rs.getString("work_company"));
				item.setJobtitle(rs.getString("job_title"));

           		results.add(item);
            }
             long endTime = System.currentTimeMillis();
			 System.out.println("Total elapsed time in execution of method callMethod() is :"+ (endTime-startTime)/1000);

		} catch (java.sql.SQLException e){
		 	System.out.println("SQL Exception "+e.getMessage());
        } finally {
                try {
                	if(stmt != null)
                    	stmt.close();
                	if (conn != null)
                    conn.close();
                } catch (Exception e) {
         		 	System.out.println("Exception "+e.getMessage());
                }
        }
        return results;
	}

    /**
     * Find persons by last name.
     */
    @SuppressWarnings("unchecked")
    public Collection<Person> findPersonsByLastName(String lastName) {
        return em.createQuery("select p from Person p where p.lastName = :lastName and p.status != 'Deleted' order by p.lastName, p.firstName")
            .setParameter("lastName", lastName).getResultList();
    }

    /**
     * Find persons by first and last name.
     */
    @SuppressWarnings("unchecked")
    public Collection<Person> findPersonsByFirstAndLastName(String firstName, String lastName) {
        Query query= em.createQuery("select p from Person p where p.firstName = :firstName and p.lastName = :lastName");
        query.setParameter("firstName", firstName);
        query.setParameter("lastName", lastName);
        return query.getResultList();
    }


    /**
     * Find broker addresses.
     */
    @SuppressWarnings("unchecked")
    public Set<Address> findPersonAddressByClientId(Integer id) {
        Query query = em.createQuery("select a from Address a where a.clientid = :id and a.status != 'Deleted'");
        query.setParameter("id", id);
        Collection list = query.getResultList();

        return new HashSet<Address>(list);
    }



    /**
     * Saves person.
     */
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public Person save(Person person) {
		SecureUserDetails user = (SecureUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Integer userid = user.getUserid();

		person.setUpdate_by(userid.toString());
		person.setUpdated(new Date());
		person.setStatus(ACTIVE);

		Person saved_person = em.merge(person);
        saved_person.setSelectionLists(policyDao.findSelectionLists());
        return saved_person;
    }

    /**
     * Deletes person.
     */
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public void delete(Person person, Integer userid) {
		person.setUpdate_by(userid.toString());
		person.setUpdated(new Date());
		person.setStatus(DELETED);
        em.merge(person);
    }

    /**
     * Saves address to person.
     */
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public Person saveAddress(Integer id, Address address) {

		SecureUserDetails user = (SecureUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Integer userid = user.getUserid();

		address.setUpdateby(userid);
		address.setUpdated(new Date());
		address.setStatus(ACTIVE);

        Person person = findPersonById(id);

        if (person.getAddresses().contains(address)) {
            person.getAddresses().remove(address);
        }

        person.getAddresses().add(address);


        return save(person);
    }

    /**
     * Deletes address from person.
     */
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public Person deleteAddress(Integer id, Integer addressId) {
		SecureUserDetails user = (SecureUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Integer userid = user.getUserid();

        Person person = findPersonById(id);

        Address address = new Address();
        address.setId(addressId);

        if (person.getAddresses().contains(address)) {
            for (Address a : person.getAddresses()) {
                if (a.getId().equals(addressId)) {
                    //em.remove(a);
					a.setUpdateby(userid);
					a.setUpdated(new Date());
					a.setStatus(DELETED);
					em.merge(a);

                    person.getAddresses().remove(address);

                    break;
                }
            }
        }

        return person;
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




}
