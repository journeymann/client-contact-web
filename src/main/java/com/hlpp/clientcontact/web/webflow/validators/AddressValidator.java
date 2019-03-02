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

package com.hlpp.clientcontact.web.webflow.validators;

import com.hlpp.clientcontact.domain.Address;
import com.hlpp.clientcontact.domain.BrokerAddress;
import com.hlpp.clientcontact.domain.BrokerDealerAddress;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import com.hlpp.clientcontact.dao.PersonDao;
import com.hlpp.clientcontact.dao.PolicyDao;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;
import java.util.Iterator;


/**
 * Person Spring Web Flow Validator.
 * Spring Web Flow activated validation based on bean name (${model} + 'Validator').
 *
 * @author Casmon Gordon
 */
@Component
public class AddressValidator {


    @Autowired
    protected PersonDao personDao = null;

    @Autowired
    protected PolicyDao policyDao = null;


    /**
     * Spring Web Flow activated validation (validate + ${state}).
     * Validates 'addressForm' view state after binding to address.
     */
    public void validateAddressForm(Address address, MessageContext context) {

		boolean isNew = (address.getId() == null)? true :  false;
		boolean isPrimary = address.getMainaddress().equals("Yes");

        if (!StringUtils.hasText(address.getMainaddress())) {
            context.addMessage(new MessageBuilder().error().source("mainaddress").code("contact.address.form.mainaddress.error").build());
        }
        if (!StringUtils.hasText(address.getAddress1())) {
            context.addMessage(new MessageBuilder().error().source("address1").code("contact.address.form.address1.error").build());
        }
        if (!StringUtils.hasText(address.getCity())) {
            context.addMessage(new MessageBuilder().error().source("city").code("contact.address.form.city.error").build());
        }

		if(isPrimary){
			//Person person = personDao.findPersonById(address.getClientid());
			//Set<Address> addresses = person.getAddresses();
			Set<Address> addresses = personDao.findPersonAddressByClientId(address.getClientid());
			Iterator iter = addresses.iterator();
			while(iter.hasNext()){
				Address addr = (Address)iter.next();
				if(isPrimary && addr.getMainaddress().equals("Yes")){
					//if(address.getId() != addr.getId())
					if(!address.equals(addr))
						context.addMessage(new MessageBuilder().error().source("addr1").code("address.form.duplicate.primary.error").build());
				}
			}

		}
	}

    /**
     * Spring Web Flow activated validation (validate + ${state}).
     * Validates 'addressBrokerForm' view state after binding to address.
     */
    public void validateAddressBrokerForm(BrokerAddress address, MessageContext context) {

		boolean isNew = (address.getId() == null)? true :  false;
		boolean isPrimary = address.getMainaddress().equals("Yes");

        if (!StringUtils.hasText(address.getMainaddress())) {
            context.addMessage(new MessageBuilder().error().source("mainaddress").code("broker.address.form.mainaddress.error").build());
        }
        if (!StringUtils.hasText(address.getAddress1())) {
            context.addMessage(new MessageBuilder().error().source("address1").code("broker.address.form.address1.error").build());
        }
        if (!StringUtils.hasText(address.getCity())) {
            context.addMessage(new MessageBuilder().error().source("city").code("broker.address.form.city.error").build());
        }

		if(isPrimary){
			//Broker broker = policyDao.findBrokerById(address.getClientid());
			//Set<BrokerAddress> addresses = broker.getAddresses();
			Set<BrokerAddress> addresses = policyDao.findBrokerAddressByClientId(address.getClientid());
			Iterator iter = addresses.iterator();
			while(iter.hasNext()){
				BrokerAddress addr = (BrokerAddress)iter.next();
				if(isPrimary && addr.getMainaddress()!= null && addr.getMainaddress().equals("Yes")){
					//if(address.getId() != addr.getId())
					if(!address.equals(addr))
						context.addMessage(new MessageBuilder().error().source("addr1").code("address.form.duplicate.primary.error").build());
				}
			}

		}
	}


    /**
     * Spring Web Flow activated validation (validate + ${state}).
     * Validates 'addressDealerForm' view state after binding to address.
     */
    public void validateAddressDealerForm(BrokerDealerAddress address, MessageContext context) {

		boolean isNew = (address.getId() == null)? true :  false;
		boolean isPrimary = address.getMainaddress().equals("Yes");

        if (!StringUtils.hasText(address.getMainaddress())) {
            context.addMessage(new MessageBuilder().error().source("mainaddress").code("dealer.address.form.mainaddress.error").build());
        }
        if (!StringUtils.hasText(address.getAddress1())) {
            context.addMessage(new MessageBuilder().error().source("address1").code("dealer.address.form.address1.error").build());
        }
        if (!StringUtils.hasText(address.getCity())) {
            context.addMessage(new MessageBuilder().error().source("city").code("dealer.address.form.city.error").build());
        }

		if(isPrimary){
			//BrokerDealer dealer = policyDao.findBrokerDealerById(address.getClientid());
			//Set<BrokerDealerAddress> addresses = dealer.getAddresses();
			Set<BrokerDealerAddress> addresses = policyDao.findBrokerDealerAddressByClientId(address.getClientid());
			Iterator iter = addresses.iterator();
			while(iter.hasNext()){
				BrokerDealerAddress addr = (BrokerDealerAddress)iter.next();
				if(isPrimary && addr.getMainaddress()!= null && addr.getMainaddress().equals("Yes")){
					//if(address.getId() != addr.getId())
					if(!address.equals(addr))
						context.addMessage(new MessageBuilder().error().source("addr1").code("address.form.duplicate.primary.error").build());
				}
			}

		}
	}

}
