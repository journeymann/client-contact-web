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

import com.hlpp.clientcontact.domain.Policy;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import com.hlpp.clientcontact.dao.PolicyDao;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

/**
 * Person Spring Web Flow Validator.
 * Spring Web Flow activated validation based on bean name (${model} + 'Validator').
 *
 * @author Casmon Gordon
 */
@Component
public class PolicyValidator {

	private final int CASE_CODE_LENGTH = 13;

    @Autowired
    protected PolicyDao policyDao = null;

    /**
     * Spring Web Flow activated validation (validate + ${state}).
     * Validates 'policyForm' view state after binding to person.
     */
    public void validatePolicyForm(Policy policy, MessageContext context) {

		boolean isNew = (policy.getCaseid()==0)? true :  false;

        if (!StringUtils.hasText(policy.getCasecode())) {
            context.addMessage(new MessageBuilder().error().source("casecode").code("policy.form.casecode.missing.error").build());
        }

        if (policy.getCasecode().indexOf(" ") > -1) {
            context.addMessage(new MessageBuilder().error().source("casecode").code("policy.form.casecode.spaces.error").build());
        }

        if (policy.getCasecode().length() > CASE_CODE_LENGTH) {
            context.addMessage(new MessageBuilder().error().source("casecode").code("policy.form.casecode.length.error").build());
        }

		ArrayList<Policy> list = (ArrayList)policyDao.findPolicysByCaseNumber(policy.getCasecode());

		if(!list.isEmpty() && isNew){
			context.addMessage(new MessageBuilder().error().source("casecode").code("policy.form.casecode.duplicate.error").build());
		}



    }

}
