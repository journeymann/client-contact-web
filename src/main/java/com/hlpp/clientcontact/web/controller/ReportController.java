package com.hlpp.clientcontact.web.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Collection;

import org.springframework.web.servlet.ModelAndView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.hlpp.clientcontact.domain.PolicyDataOutput;
import com.hlpp.clientcontact.domain.PolicyDataInput;
import com.hlpp.clientcontact.dao.PolicyDao;
import com.hlpp.clientcontact.dao.util.QueryHelper;

import com.hlpp.clientcontact.domain.PersonDataOutput;
import com.hlpp.clientcontact.domain.PersonDataInput;


/**
 * Person form controller.
 *
 * @author Casmon Gordon
 */
 @Controller
 @SessionAttributes
public class ReportController{


	private final String BOTH = "Both";
	private final String YES = "Yes";
	private final String NO = "No";

    @Autowired
    protected PolicyDao policyDao = null;

    /**
     * <p>Expected HTTP GET and request '/misc/report'.</p>
     */
    @RequestMapping(value="/misc/report", method=RequestMethod.POST)
	public ModelAndView report(@ModelAttribute("queryhelper") QueryHelper queryhelper, @RequestParam("output") String output) {

		String [] yesList = queryhelper.getYes();
		String [] noList = queryhelper.getNo();
		String [] disp = queryhelper.getDisp();

		System.out.println("********************* yes list size="+yesList.length+" ******************");
		System.out.println("********************* no list size="+noList.length+" ******************");
		System.out.println("********************* display option="+disp+" ******************");


		PolicyDataInput params = new PolicyDataInput();
		params.setParam1(queryhelper.getPval());
		params.setParam2(queryhelper.getSval());
		params.setDisp(queryhelper.getDisp());

		for (String s : queryhelper.getYes()) {

			int i = new Integer(s).intValue();

			System.out.println("********************* checking display (yes) option value="+i+" ******************");
			switch(i){
				case 1 : params.setAnnualsemireport(YES); break;
				case 2 : params.setAuthorizedsignatory(YES); break;
				case 3 : params.setBrokerrecord(YES); break;
				case 4 : params.setClientreport(YES); break;
				case 5 : params.setCommissionablebroker(YES); break;
				//case 6 : contacttype field;
				case 7 : params.setCustomdeliverable(YES); break;
				case 8 : params.setDeathclaimnotification(YES); break;
				case 9 : params.setElectronicreport(YES); break;
				case 10 : params.setFundlevelreport(YES); break;
				case 11 : params.setFundprospectussupplement(YES); break;
				case 12 : params.setKeyclientcontact(YES); break;
				case 13 : params.setNewsletter(YES); break;
				case 14 : params.setOwnercontact(YES); break;
				case 15 : params.setPpmamendment(YES); break;
				case 16 : params.setPrimarycompliancecontact(YES); break;
				case 17 : params.setPrimaryservicecontact(YES); break;
				case 18 : params.setPrivacynotice(YES); break;
				case 19 : params.setReportalaccess(YES); break;
				case 20 : params.setServicingbroker(YES); break;
				case 21 : params.setSSsweep(YES); break;
				//case 22 : name field;
				case 23 : params.setTls(YES); break;
			}
		}

		for (String s : queryhelper.getNo()) {

			int i = new Integer(s).intValue();
			System.out.println("********************* checking display (no) option value="+i+" ******************");
			switch(i){
				case 1 : if(params.getAnnualsemireport() != null && params.getAnnualsemireport().equals(YES)) params.setAnnualsemireport(BOTH); else params.setAnnualsemireport(NO); break;
				case 2 : if(params.getAuthorizedsignatory() != null && params.getAuthorizedsignatory().equals(YES)) params.setAuthorizedsignatory(BOTH); else params.setAuthorizedsignatory(NO); break;
				case 3 : if(params.getBrokerrecord() != null && params.getBrokerrecord().equals(YES)) params.setBrokerrecord(BOTH); else params.setBrokerrecord(NO); break;
				case 4 : if(params.getClientreport() != null && params.getClientreport().equals(YES)) params.setClientreport(BOTH); else params.setClientreport(NO); break;
				case 5 : if(params.getCommissionablebroker() != null && params.getCommissionablebroker().equals(YES)) params.setCommissionablebroker(BOTH); else params.setCommissionablebroker(NO); break;
				//case 6 : contacttype field;
				case 7 : if(params.getCustomdeliverable() != null && params.getCustomdeliverable().equals(YES)) params.setCustomdeliverable(BOTH); else params.setCustomdeliverable(NO); break;
				case 8 : if(params.getDeathclaimnotification() != null && params.getDeathclaimnotification().equals(YES)) params.setDeathclaimnotification(BOTH); else params.setDeathclaimnotification(NO); break;
				case 9 : if(params.getElectronicreport() != null && params.getElectronicreport().equals(YES)) params.setElectronicreport(BOTH); else params.setElectronicreport(NO); break;
				case 10 : if(params.getFundlevelreport() != null && params.getFundlevelreport().equals(YES)) params.setFundlevelreport(BOTH); else params.setFundlevelreport(NO); break;
				case 11 : if(params.getFundprospectussupplement() != null && params.getFundprospectussupplement().equals(YES)) params.setFundprospectussupplement(BOTH); else params.setFundprospectussupplement(NO); break;
				case 12 : if(params.getKeyclientcontact() != null && params.getKeyclientcontact().equals(YES)) params.setKeyclientcontact(BOTH); else params.setKeyclientcontact(NO); break;
				case 13 : if(params.getNewsletter() != null && params.getNewsletter().equals(YES)) params.setNewsletter(BOTH); else params.setNewsletter(NO); break;
				case 14 : if(params.getOwnercontact() != null && params.getOwnercontact().equals(YES)) params.setOwnercontact(BOTH); else params.setOwnercontact(NO); break;
				case 15 : if(params.getPpmamendment() != null && params.getPpmamendment().equals(YES)) params.setPpmamendment(BOTH); else params.setPpmamendment(NO); break;
				case 16 : if(params.getPrimarycompliancecontact() != null && params.getPrimarycompliancecontact().equals(YES)) params.setPrimarycompliancecontact(BOTH); else params.setPrimarycompliancecontact(NO); break;
				case 17 : if(params.getPrimaryservicecontact() != null && params.getPrimaryservicecontact().equals(YES)) params.setPrimaryservicecontact(BOTH); else params.setPrimaryservicecontact(NO); break;
				case 18 : if(params.getPrivacynotice() != null && params.getPrivacynotice().equals(YES)) params.setPrivacynotice(BOTH); else params.setPrivacynotice(NO); break;
				case 19 : if(params.getReportalaccess() != null && params.getReportalaccess().equals(YES)) params.setReportalaccess(BOTH); else params.setReportalaccess(NO); break;
				case 20 : if(params.getServicingbroker() != null && params.getServicingbroker().equals(YES)) params.setServicingbroker(BOTH); else params.setServicingbroker(NO); break;
				case 21 : if(params.getSSsweep() != null && params.getSSsweep().equals(YES)) params.setSSsweep(BOTH); else params.setSSsweep(NO); break;
				//case 22 : name field;
				case 23 : if(params.getTls() != null && params.getTls().equals(YES)) params.setTls(BOTH); else params.setTls(NO); break;
			}
		}


		Collection<PolicyDataOutput> data = policyDao.findQueryResults(params);

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("data", data);
		model.put("params", params);
		model.put("type", new PolicyDataOutput());

		if(output ==null || "".equals(output)){
			//return normal view
			return new ModelAndView("Summary","data",data);

		}else if("EXCEL".equals(output.toUpperCase())){
			//return excel view
			return new ModelAndView("ExcelSummary","model",model);

		}else{
			//return normal view
			return new ModelAndView("Summary","data",data);

		}
	}


    /**
     * <p>Expected HTTP GET and request '/misc/report_person'.</p>
     */
    @RequestMapping(value="/misc/report_person", method=RequestMethod.POST)
	public ModelAndView reportPerson(@ModelAttribute("queryhelper") QueryHelper queryhelper, @RequestParam("output") String output) {

		PersonDataInput params = new PersonDataInput();
		params.setParam1(queryhelper.getCval());
		params.setDisp(queryhelper.getDisp());

		Collection<PersonDataOutput> data = policyDao.findPersonQueryResults(params);

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("data", data);
		model.put("params", params);
		model.put("type", new PersonDataOutput());

		if(output ==null || "".equals(output)){
			//return normal view
			return new ModelAndView("Summary","data",data);

		}else if("EXCEL".equals(output.toUpperCase())){
			//return excel view
			return new ModelAndView("ExcelSummary","model",model);

		}else{
			//return normal view
			return new ModelAndView("Summary","data",data);

		}
	}



}