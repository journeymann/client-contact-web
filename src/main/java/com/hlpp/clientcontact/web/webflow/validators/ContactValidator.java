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

import com.hlpp.clientcontact.domain.ContactCase;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
import org.springframework.stereotype.Component;
import com.hlpp.clientcontact.dao.PolicyDao;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Collection;


/**
 * Person Spring Web Flow Validator.
 * Spring Web Flow activated validation based on bean name (${model} + 'Validator').
 *
 * @author Casmon Gordon
 */
@Component
public class ContactValidator {


    @Autowired
    protected PolicyDao policyDao = null;

    /**
     * Spring Web Flow activated validation (validate + ${state}).
     * Validates 'contactForm' view state after binding to contact.
     */
    public void validateContactForm(ContactCase contact, MessageContext context) {


		boolean isNew = (contact.getContactcaseid()==null)? true :  false;

        Collection<ContactCase> contacts = policyDao.findContactsByIdAndCase(contact.getContactid(), contact.getCaseid());

        if (!contacts.isEmpty() && isNew) {
            context.addMessage(new MessageBuilder().error().source("contacttype").code("contact.form.duplicate.type.error").build());
        }



    }

}
