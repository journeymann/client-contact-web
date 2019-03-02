package com.hlpp.clientcontact.web.poi;


import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import java.util.StringTokenizer;
import java.util.Iterator;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.servlet.view.document.AbstractExcelView;
import com.hlpp.clientcontact.domain.PolicyDataOutput;
import com.hlpp.clientcontact.domain.PolicyDataInput;
import com.hlpp.clientcontact.domain.PersonDataOutput;
import com.hlpp.clientcontact.domain.PersonDataInput;
import com.hlpp.clientcontact.domain.Display;

public class ExcelReportView extends AbstractExcelView{

	@Override
	protected void buildExcelDocument(Map model, HSSFWorkbook workbook,
		HttpServletRequest request, HttpServletResponse response)
		throws Exception {

		// Set the headers
	    response.setHeader("Content-Type", "application/octet-stream");
	    response.setHeader("Content-Disposition", "attachment; filename=report.xls");

		Map datamap = (Map)model.get("model");

		Object obj = (Object)datamap.get("type");
		boolean isPolicyType = obj.getClass().equals(PolicyDataOutput.class);
		String[] disp = null;

		if(isPolicyType){
			ArrayList<PolicyDataOutput> data = (ArrayList<PolicyDataOutput>) datamap.get("data");
			if(data==null) data = new ArrayList<PolicyDataOutput>();
			PolicyDataInput params = (PolicyDataInput) datamap.get("params");
			disp = params.getDisp();
		}else{
			ArrayList<PersonDataOutput> data = (ArrayList<PersonDataOutput>) datamap.get("data");
			if(data==null) data = new ArrayList<PersonDataOutput>();
			PersonDataInput params = (PersonDataInput) datamap.get("params");
			disp = params.getDisp();
		}

		boolean emailShow = false;
		boolean phoneShow = false;
		boolean addressShow = false;
		boolean otherShow = false;
		boolean removeDup = false;

		for (String s : disp) {

			int i = new Integer(s).intValue();

			switch(i){
				case Display.EMAIL: emailShow = true; break;
				case Display.EMAIL_NO_DUP: emailShow = true; removeDup = true; break;
				case Display.ADDRESS: addressShow = true; break;
				case Display.PHONE: phoneShow = true; break;
				case Display.ALL: emailShow = true; phoneShow = true; addressShow = true; otherShow= true; break;
			}

		}

		//create a wordsheet
		if(isPolicyType){
			HSSFSheet sheet = workbook.createSheet("Policy Search Report");
			ArrayList<PolicyDataOutput> data = (ArrayList<PolicyDataOutput>) datamap.get("data");
			PolicyDataInput params = (PolicyDataInput) datamap.get("params");

			int pos = 0;
			int rowNum = 0;

			HSSFRow param_row = sheet.createRow(rowNum++);
			param_row.createCell(pos++).setCellValue("Query Parameters");

			pos = 0;
			param_row = sheet.createRow(rowNum++);
			param_row.createCell(pos++).setCellValue("Primary Filter: ");
			param_row.createCell(pos++).setCellValue(params.getParam1());

			pos = 0;
			param_row = sheet.createRow(rowNum++);
			param_row.createCell(pos++).setCellValue("Secondary Filter: ");
			param_row.createCell(pos++).setCellValue(params.getParam2());

			pos = 0;
			param_row = sheet.createRow(rowNum++);
			param_row.createCell(pos++).setCellValue("Attributes:");
			param_row.createCell(pos++).setCellValue(getAttributesDisplayString(params));

			pos = 0;
			param_row = sheet.createRow(rowNum++);
			param_row.createCell(pos++).setCellValue("Display: ");
			param_row.createCell(pos++).setCellValue(getDisplayOptionsString(params.getDisp()));

			pos = 0;
			param_row = sheet.createRow(rowNum++);
			param_row.createCell(pos++).setCellValue("");

			pos = 0;
			HSSFRow header = sheet.createRow(rowNum++);

			boolean hideColumn = (this.getVarColumnName(params.getParam1()).equalsIgnoreCase("None"))? true : false;

			if (otherShow) header.createCell(pos++).setCellValue("Title");
			header.createCell(pos++).setCellValue("Person First Name");
			header.createCell(pos++).setCellValue("Person Last Name");
			header.createCell(pos++).setCellValue("Case #");
			if(!removeDup) header.createCell(pos++).setCellValue("Contact Type");
			if(!hideColumn) header.createCell(pos++).setCellValue(this.getVarColumnName(params.getParam1()));
			if (otherShow) {
				header.createCell(pos++).setCellValue("Job Title");
				header.createCell(pos++).setCellValue("Work Company");
				header.createCell(pos++).setCellValue("Broker Company");
				header.createCell(pos++).setCellValue("Broker Dealer");
			}

			if (params.getCommissionablebroker() != null) header.createCell(pos++).setCellValue("Commissionable Broker");
			if (params.getServicingbroker() != null) header.createCell(pos++).setCellValue("Servicing Broker");
			if (params.getBrokerrecord() != null) header.createCell(pos++).setCellValue("Broker Record");
			if (params.getPrimaryservicecontact() != null) header.createCell(pos++).setCellValue("Primary Service Contact");
			if (params.getPrimarycompliancecontact() != null) header.createCell(pos++).setCellValue("Primary Compliance Contact");
			if (params.getKeyclientcontact() != null) header.createCell(pos++).setCellValue("Key Client Contact");
			if (params.getOwnercontact() != null) header.createCell(pos++).setCellValue("Owner Contact");
			if (params.getReportalaccess() != null) header.createCell(pos++).setCellValue("Reportal Access");
			if (params.getClientreport() != null) header.createCell(pos++).setCellValue("Client Report");
			if (params.getFundlevelreport() != null) header.createCell(pos++).setCellValue("Fund Level Report");
			if (params.getCustomdeliverable() != null) header.createCell(pos++).setCellValue("Custom Deliverable");
			if (params.getAuthorizedsignatory() != null) header.createCell(pos++).setCellValue("Authorized Signatory");
			if (params.getNewsletter() != null) header.createCell(pos++).setCellValue("Newsletter");
			if (params.getSSsweep() != null) header.createCell(pos++).setCellValue("Social Security Sweep");
			if (params.getDeathclaimnotification() != null) header.createCell(pos++).setCellValue("Death Claim Notification");
			if (params.getElectronicreport() != null) header.createCell(pos++).setCellValue("Electronic Report");
			if (params.getPpmamendment() != null) header.createCell(pos++).setCellValue("PPM Amendment");
			if (params.getAnnualsemireport() != null) header.createCell(pos++).setCellValue("Annual and Semi-annual Report");
			if (params.getPrivacynotice() != null) header.createCell(pos++).setCellValue("Privacy Notice");
			if (params.getFundprospectussupplement() != null) header.createCell(pos++).setCellValue("Fund Prospectus Supplement");
			if (params.getTls() != null) header.createCell(pos++).setCellValue("TLS");
			if (emailShow) header.createCell(pos++).setCellValue("Email Address");
			if (phoneShow) {
				header.createCell(pos++).setCellValue("Cell Phone");
				header.createCell(pos++).setCellValue("Primary Work Phone");
				header.createCell(pos++).setCellValue("Secondary Work Phone");

				if (otherShow) {
					header.createCell(pos++).setCellValue("Primary Fax");
					header.createCell(pos++).setCellValue("Secondary Fax");
				}
			}
			if (addressShow) {
				header.createCell(pos++).setCellValue("Primary");
				header.createCell(pos++).setCellValue("Address 1");
				header.createCell(pos++).setCellValue("Address 2");
				header.createCell(pos++).setCellValue("City");
				header.createCell(pos++).setCellValue("State");
				header.createCell(pos++).setCellValue("Zip");
				header.createCell(pos++).setCellValue("Country");
			}
			if (otherShow) {
				header.createCell(pos++).setCellValue("Notes");
			}

			if(removeDup) {
				data = processDuplicates(data, params);
				data = processDuplicateContacts(data);
				data = processDuplicateCases(data);
			}

			if(phoneShow && !addressShow){
				data = processDuplicatePhoneNumbers(data);
			}

			if(data != null){
				for (PolicyDataOutput item : data) {
					pos = 0;
					if(item==null) continue;
					//create the row data
					HSSFRow row = sheet.createRow(rowNum++);
					if (otherShow) row.createCell(pos++).setCellValue(item.getTitle());
					row.createCell(pos++).setCellValue(item.getFirstname());
					row.createCell(pos++).setCellValue(item.getLastname());
					row.createCell(pos++).setCellValue(item.getCaseNumber());
					if(!removeDup) row.createCell(pos++).setCellValue(item.getContacttype());
					if(!hideColumn) row.createCell(pos++).setCellValue(this.getVarColumnValue(params.getParam1(),item));

					if (otherShow) {
						row.createCell(pos++).setCellValue(item.getJobtitle());
						row.createCell(pos++).setCellValue(item.getWorkcompany());
						row.createCell(pos++).setCellValue(item.getBrokercompany());
						row.createCell(pos++).setCellValue(item.getBrokerdealer());
					}
					if (params.getCommissionablebroker() != null) row.createCell(pos++).setCellValue(item.getCommissionablebroker());
					if (params.getServicingbroker() != null) row.createCell(pos++).setCellValue(item.getServicingbroker());
					if (params.getBrokerrecord() != null) row.createCell(pos++).setCellValue(item.getBrokerrecord());
					if (params.getPrimaryservicecontact() != null) row.createCell(pos++).setCellValue(item.getPrimaryservicecontact());
					if (params.getPrimarycompliancecontact() != null) row.createCell(pos++).setCellValue(item.getPrimarycompliancecontact());
					if (params.getKeyclientcontact() != null) row.createCell(pos++).setCellValue(item.getKeyclientcontact());
					if (params.getOwnercontact() != null) row.createCell(pos++).setCellValue(item.getOwnercontact());
					if (params.getReportalaccess() != null) row.createCell(pos++).setCellValue(item.getReportalaccess());
					if (params.getClientreport() != null) row.createCell(pos++).setCellValue(item.getClientreport());
					if (params.getFundlevelreport() != null) row.createCell(pos++).setCellValue(item.getFundlevelreport());
					if (params.getCustomdeliverable() != null) row.createCell(pos++).setCellValue(item.getCustomdeliverable());
					if (params.getAuthorizedsignatory() != null) row.createCell(pos++).setCellValue(item.getAuthorizedsignatory());
					if (params.getNewsletter() != null) row.createCell(pos++).setCellValue(item.getNewsletter());
					if (params.getSSsweep() != null) row.createCell(pos++).setCellValue(item.getSSsweep());
					if (params.getDeathclaimnotification() != null) row.createCell(pos++).setCellValue(item.getDeathclaimnotification());
					if (params.getElectronicreport() != null) row.createCell(pos++).setCellValue(item.getElectronicreport());
					if (params.getPpmamendment() != null) row.createCell(pos++).setCellValue(item.getPpmamendment());
					if (params.getAnnualsemireport() != null) row.createCell(pos++).setCellValue(item.getAnnualsemireport());
					if (params.getPrivacynotice() != null) row.createCell(pos++).setCellValue(item.getPrivacynotice());
					if (params.getFundprospectussupplement() != null) row.createCell(pos++).setCellValue(item.getFundprospectussupplement());
					if (params.getTls() != null) row.createCell(pos++).setCellValue(item.getTls());
					if (emailShow) row.createCell(pos++).setCellValue(item.getEmail());
					if (phoneShow) {
						row.createCell(pos++).setCellValue(item.getCellphone());
						row.createCell(pos++).setCellValue(item.getWorkphone());
						row.createCell(pos++).setCellValue(item.getWorkphone2());
						if (otherShow) {
							row.createCell(pos++).setCellValue(item.getFax());
							row.createCell(pos++).setCellValue(item.getFax2());
						}
					}
					if (addressShow){
						row.createCell(pos++).setCellValue(item.getMainaddress());
						row.createCell(pos++).setCellValue(item.getAddr1());
						row.createCell(pos++).setCellValue(item.getAddr2());
						row.createCell(pos++).setCellValue(item.getCity());
						row.createCell(pos++).setCellValue(item.getState());
						row.createCell(pos++).setCellValue(item.getZip());
						row.createCell(pos++).setCellValue(item.getCountry());
					}
					if (otherShow) {
						row.createCell(pos++).setCellValue(item.getNotes());
					}

				}
			}

		}else{
			HSSFSheet sheet = workbook.createSheet("Person Search Report");
			ArrayList<PersonDataOutput> data = (ArrayList<PersonDataOutput>) datamap.get("data");
			PersonDataInput params = (PersonDataInput) datamap.get("params");

			int pos = 0;
			int rowNum = 0;

			HSSFRow param_row = sheet.createRow(rowNum++);
			param_row.createCell(pos++).setCellValue("Query Parameters: ");

			pos = 0;
			param_row = sheet.createRow(rowNum++);
			param_row.createCell(pos++).setCellValue("People: ");
			param_row.createCell(pos++).setCellValue(params.getParam1());

			pos = 0;
			param_row = sheet.createRow(rowNum++);
			param_row.createCell(pos++).setCellValue("Display: ");
			param_row.createCell(pos++).setCellValue(getDisplayOptionsString(params.getDisp()));

			pos = 0;
			param_row = sheet.createRow(rowNum++);
			param_row.createCell(pos++).setCellValue("");

			pos = 0;
			HSSFRow header = sheet.createRow(rowNum++);
			if (otherShow) header.createCell(pos++).setCellValue("Title");
			header.createCell(pos++).setCellValue("Person First Name");
			header.createCell(pos++).setCellValue("Person Last Name");
			header.createCell(pos++).setCellValue("Contact Type");
			if (otherShow) {
				header.createCell(pos++).setCellValue("Job Title");
				header.createCell(pos++).setCellValue("Work Company");
				header.createCell(pos++).setCellValue("Broker Company");
				header.createCell(pos++).setCellValue("Broker Dealer");
			}
			if (emailShow) header.createCell(pos++).setCellValue("Email Address");
			if (phoneShow) {
				header.createCell(pos++).setCellValue("Cell Phone");
				header.createCell(pos++).setCellValue("Primary Work Phone");
				header.createCell(pos++).setCellValue("Secondary Work Phone");
				if (otherShow) {
					header.createCell(pos++).setCellValue("Primary Fax");
					header.createCell(pos++).setCellValue("Secondary Fax");
				}
			}
			if (addressShow) {
				header.createCell(pos++).setCellValue("Primary Address");
				header.createCell(pos++).setCellValue("Address 1");
				header.createCell(pos++).setCellValue("Address 2");
				header.createCell(pos++).setCellValue("City");
				header.createCell(pos++).setCellValue("State");
				header.createCell(pos++).setCellValue("Zip");
				header.createCell(pos++).setCellValue("Country");
			}
			if (otherShow) {
				header.createCell(pos++).setCellValue("Notes");
			}

			if(removeDup) {
				data = processDuplicatePersons(data, params);
			}

			if(data != null){
				for (PersonDataOutput item : data) {
					pos = 0;
					//create the row data
					HSSFRow row = sheet.createRow(rowNum++);
					if (otherShow) row.createCell(pos++).setCellValue(item.getTitle());
					row.createCell(pos++).setCellValue(item.getFirstname());
					row.createCell(pos++).setCellValue(item.getLastname());
					row.createCell(pos++).setCellValue(item.getContacttype());
					if (otherShow) {
						row.createCell(pos++).setCellValue(item.getJobtitle());
						row.createCell(pos++).setCellValue(item.getWorkcompany());
						row.createCell(pos++).setCellValue(item.getBrokercompany());
						row.createCell(pos++).setCellValue(item.getBrokerdealer());
					}
					if (emailShow) row.createCell(pos++).setCellValue(item.getEmail());
					if (phoneShow){
						row.createCell(pos++).setCellValue(item.getCellphone());
						row.createCell(pos++).setCellValue(item.getWorkphone());
						row.createCell(pos++).setCellValue(item.getWorkphone2());
						if (otherShow) {
							row.createCell(pos++).setCellValue(item.getFax());
							row.createCell(pos++).setCellValue(item.getFax2());
						}
					}
					if (addressShow){
						row.createCell(pos++).setCellValue(item.getMainaddress());
						row.createCell(pos++).setCellValue(item.getAddr1());
						row.createCell(pos++).setCellValue(item.getAddr2());
						row.createCell(pos++).setCellValue(item.getCity());
						row.createCell(pos++).setCellValue(item.getState());
						row.createCell(pos++).setCellValue(item.getZip());
						row.createCell(pos++).setCellValue(item.getCountry());
					}
					if (otherShow) {
						row.createCell(pos++).setCellValue(item.getNotes());
					}

				}
			}//if data != null
		}//else create worksheet
	}


	private String getVarColumnName(String param){

		String desc = "None";

		if(param.equalsIgnoreCase("Multi-Select Client Group") || param.equalsIgnoreCase("All Client Group")){
			desc = "Group";
		}else if(param.equalsIgnoreCase("Multi-Select Library") || param.equalsIgnoreCase("All Libraries")){
			desc = "Library";
		}else if(param.equalsIgnoreCase("Multi-Select Team") || param.equalsIgnoreCase("All Teams")){
			desc = "Team";
		}else if(param.equalsIgnoreCase("Multi-Select Case Manager") || param.equalsIgnoreCase("All Case Managers")){
			desc = "Case Manager";
		}else if(param.equalsIgnoreCase("Multi-Select Broker Company") || param.equalsIgnoreCase("All Broker Companies")){
			desc = "Broker Company";
		}else if(param.equalsIgnoreCase("Multi-Select Series") || param.equalsIgnoreCase("All Series")){
			desc = "Series";
		}else if(param.equalsIgnoreCase("Multi-Select Contact Type") || param.equalsIgnoreCase("All Contact Types")){
			desc = "Contact Type";
		}else if(param.equalsIgnoreCase("Multi-Select PPM #") || param.equalsIgnoreCase("All PPM #")){
			desc = "PPM #";
		}

		return desc;
	}



	private String getVarColumnValue(String param, PolicyDataOutput item){

		String desc = "None";

		if(param.equalsIgnoreCase("Multi-Select Client Group") || param.equalsIgnoreCase("All Client Group")){
			desc = item.getClientgroup();
		}else if(param.equalsIgnoreCase("Multi-Select Library") || param.equalsIgnoreCase("All Libraries")){
			desc = item.getSystemcode();
		}else if(param.equalsIgnoreCase("Multi-Select Team") || param.equalsIgnoreCase("All Teams")){
			desc = item.getTeamcode();
		}else if(param.equalsIgnoreCase("Multi-Select Case Manager") || param.equalsIgnoreCase("All Case Managers")){
			desc = item.getCasemanager();
		}else if(param.equalsIgnoreCase("Multi-Select Broker Company") || param.equalsIgnoreCase("All Broker Companies")){
			desc = item.getBrokercompany();
		}else if(param.equalsIgnoreCase("Multi-Select Series") || param.equalsIgnoreCase("All Series")){
			desc = item.getSeriescode();
		}else if(param.equalsIgnoreCase("Multi-Select Contact Type") || param.equalsIgnoreCase("All Contact Types")){
			desc = item.getContacttype();
		}else if(param.equalsIgnoreCase("Multi-Select PPM #") || param.equalsIgnoreCase("All PPM #")){
			desc = item.getPpmnumber();
		}

		return desc;
	}


	private String getAttributesDisplayString(PolicyDataInput params){

		String out= "";

		if (params.getCommissionablebroker() != null) out += " Commissionable Broker=" + params.getCommissionablebroker()+",";
		if (params.getServicingbroker() != null) out += " Servicing Broker=" + params.getServicingbroker()+",";
		if (params.getBrokerrecord() != null) out += " Broker Record=" + params.getBrokerrecord()+",";
		if (params.getPrimaryservicecontact() != null) out += " Primary Service Contact=" + params.getPrimaryservicecontact()+",";
		if (params.getPrimarycompliancecontact() != null) out += " Primary Compliance Contact=" + params.getPrimarycompliancecontact()+",";
		if (params.getKeyclientcontact() != null) out += " Key Client Contact=" + params.getKeyclientcontact()+",";
		if (params.getOwnercontact() != null) out += " Owner Contact=" + params.getOwnercontact()+",";
		if (params.getReportalaccess() != null) out += " Reportal Access=" + params.getReportalaccess()+",";
		if (params.getClientreport() != null) out += " Client Report=" + params.getClientreport()+",";
		if (params.getFundlevelreport() != null) out += " Fund Level Report=" + params.getFundlevelreport()+",";
		if (params.getCustomdeliverable() != null) out += " Custom Deliverable=" + params.getCustomdeliverable()+",";
		if (params.getAuthorizedsignatory() != null) out += " Authorized Signatory=" + params.getAuthorizedsignatory() +",";
		if (params.getNewsletter() != null) out += " Newsletter=" + params.getNewsletter()+",";
		if (params.getSSsweep() != null) out += " Social Security Sweep=" + params.getSSsweep()+",";
		if (params.getDeathclaimnotification() != null) out += " Death Claim Notification=" + params.getDeathclaimnotification()+",";
		if (params.getElectronicreport() != null) out += " Electronic Report=" + params.getElectronicreport()+",";
		if (params.getPpmamendment() != null) out += " PPM Amendment=" + params.getPpmamendment()+",";
		if (params.getAnnualsemireport() != null) out += " Annual and Semi-annual Report=" + params.getAnnualsemireport()+",";
		if (params.getPrivacynotice() != null) out += " Privacy Notice=" + params.getAnnualsemireport()+",";
		if (params.getFundprospectussupplement() != null) out += " Fund Prospectus Supplement=" + params.getFundprospectussupplement()+",";
		if (params.getTls() != null) out += " TLS." + params.getTls()+".";

		return out;

	}

	private String getDisplayOptionsString(String[] disp){

		String out = "";

		for (String s : disp) {

			int i = new Integer(s).intValue();

			switch(i){
				case Display.EMAIL: out+= " E-mail addresses."; break;
				case Display.EMAIL_NO_DUP: out+= " E-mail addresses.(No Dup)"; break;
				case Display.ADDRESS: out+= " Physical addresses."; break;
				case Display.PHONE: out+= " Phone numbers."; break;
				case Display.ALL: out+= " All Contact fields."; break;
			}

		}

		return out;

	}


	private boolean samePerson(PolicyDataOutput item, PolicyDataOutput prev){
		if(prev==null || item==null) return false;
		if(prev.getContactid().intValue() == item.getContactid().intValue()) return true;

		return false;
	}


	private boolean samePerson(PersonDataOutput item, PersonDataOutput prev){
		if(prev==null || item==null) return false;
		if(prev.getContactid().intValue() == item.getContactid().intValue()) return true;

		return false;
	}



	private ArrayList<PolicyDataOutput> compareAttributes(ArrayList<PolicyDataOutput> data, PolicyDataInput params) {

		ArrayList<PolicyDataOutput> output = new ArrayList<PolicyDataOutput>();
		ArrayList<PolicyDataOutput> temp = new ArrayList<PolicyDataOutput>();

		PolicyDataOutput prev = null;
		PolicyDataOutput save = null;
		PolicyDataOutput last = null;
		boolean different = false;
		System.out.println("*********************Processing duplicates attributes");
		for (PolicyDataOutput item : data) {
			if(prev==null) {
				prev=item;
				continue;
			}
			System.out.println("*********************Processing duplicates attributes for "+item.getFirstname()+" "+item.getLastname());

			if (!different && params.getCommissionablebroker() != null) different = !prev.getCommissionablebroker().equals(item.getCommissionablebroker());
			if (!different && params.getServicingbroker() != null) different = !prev.getServicingbroker().equals(item.getServicingbroker());
			if (!different && params.getBrokerrecord() != null) different = !prev.getBrokerrecord().equals(item.getBrokerrecord());
			if (!different && params.getPrimaryservicecontact() != null) different = !prev.getPrimaryservicecontact().equals(item.getPrimaryservicecontact());
			if (!different && params.getPrimarycompliancecontact() != null) different = !prev.getPrimarycompliancecontact().equals(item.getPrimarycompliancecontact());
			if (!different && params.getKeyclientcontact() != null) different = !prev.getKeyclientcontact().equals(item.getKeyclientcontact());
			if (!different && params.getOwnercontact() != null) different = !prev.getOwnercontact().equals(item.getOwnercontact());
			if (!different && params.getReportalaccess() != null) different = !prev.getReportalaccess().equals(item.getReportalaccess());
			if (!different && params.getClientreport() != null) different = !prev.getClientreport().equals(item.getClientreport());
			if (!different && params.getFundlevelreport() != null) different = !prev.getFundlevelreport().equals(item.getFundlevelreport());
			if (!different && params.getCustomdeliverable() != null) different = !prev.getCustomdeliverable().equals(item.getCustomdeliverable());
			if (!different && params.getAuthorizedsignatory() != null) different = !prev.getAuthorizedsignatory().equals(item.getAuthorizedsignatory());
			if (!different && params.getNewsletter() != null) different = !prev.getNewsletter().equals(item.getNewsletter());
			if (!different && params.getSSsweep() != null) different = !prev.getSSsweep().equals(item.getSSsweep());
			if (!different && params.getDeathclaimnotification() != null) different = !prev.getDeathclaimnotification().equals(item.getDeathclaimnotification());
			if (!different && params.getElectronicreport() != null) different = !prev.getElectronicreport().equals(item.getElectronicreport());
			if (!different && params.getPpmamendment() != null) different = !prev.getPpmamendment().equals(item.getPpmamendment());
			if (!different && params.getAnnualsemireport() != null) different = !prev.getAnnualsemireport().equals(item.getAnnualsemireport());
			if (!different && params.getPrivacynotice() != null) different = !prev.getPrivacynotice().equals(item.getPrivacynotice());
			if (!different && params.getFundprospectussupplement() != null) different = !prev.getFundprospectussupplement().equals(item.getFundprospectussupplement());
			if (!different && params.getTls() != null) different = !prev.getTls().equals(item.getTls());

			if(!different){
				if (save == null) save = prev;
				save.setCaseNumber(save.getCaseNumber()+","+item.getCaseNumber());
				System.out.println("*********************Consolidate cases for "+item.getFirstname()+" "+item.getLastname());
			}else{
				if (save == null) save = prev;
				output.add(save);
				System.out.println("*********************Saving to output for "+item.getFirstname()+" "+item.getLastname());
				save = null;
			}
			prev=item;
			different = false;
		}
		output.add(save);

		return output;

	}


	private PolicyDataOutput removeDuplicateCases(PolicyDataOutput item){

		Set<String> cases = new HashSet<String>();
		String caseNumber =  "";
		StringTokenizer st = new StringTokenizer(item.getCaseNumber(), ",");
		while(st.hasMoreTokens()){
			cases.add(st.nextToken());
		}

		Iterator iter = cases.iterator();
		while(iter.hasNext()){
			caseNumber += (String)iter.next()+",";
		}

		item.setCaseNumber(caseNumber);

		return item;
	}


	private ArrayList<PolicyDataOutput> processDuplicates(ArrayList<PolicyDataOutput> data, PolicyDataInput params){

		ArrayList<PolicyDataOutput> output = new ArrayList<PolicyDataOutput>();
		ArrayList<PolicyDataOutput> temp = new ArrayList<PolicyDataOutput>();

		PolicyDataOutput prev = null;
		PolicyDataOutput save = null;
		boolean process = false;

		System.out.println("*********************Processing duplicates");

		for (PolicyDataOutput item : data) {
			if(prev == null){
				prev = item;
				temp.add(prev);
				continue;
			}

			if(samePerson(item, prev)) {
				System.out.println("*********************Same Person!" + item.getFirstname()+" "+item.getLastname());
				temp.add(item);
				prev = item;
			}else{
				if(temp.size() == 1){
					output.addAll(temp);
				}else{
					output.addAll(compareAttributes(temp, params));
				}
				temp = new ArrayList<PolicyDataOutput>();
				temp.add(item);
				prev=item;
			}

		}

		output.addAll(temp);

		return output;
	}


	private ArrayList<PolicyDataOutput> processDuplicateCases(ArrayList<PolicyDataOutput> data){

		ArrayList<PolicyDataOutput> output = new ArrayList<PolicyDataOutput>();
		ArrayList<PolicyDataOutput> temp = new ArrayList<PolicyDataOutput>();

		System.out.println("*********************Processing duplicates Cases");

		for (PolicyDataOutput item : data) {
			if(item!=null) temp.add(removeDuplicateCases(item));
		}

		output.addAll(temp);

		return output;
	}


	private ArrayList<PolicyDataOutput> processDuplicateContacts(ArrayList<PolicyDataOutput> data){

		ArrayList<PolicyDataOutput> output = new ArrayList<PolicyDataOutput>();
		ArrayList<PolicyDataOutput> temp = new ArrayList<PolicyDataOutput>();

		PolicyDataOutput prev = null;
		System.out.println("*********************Processing duplicates Cases");

		for (PolicyDataOutput item : data) {
			if(prev == null){
				prev = item;
				temp.add(prev);
				continue;
			}
			if(!this.samePerson(item, prev)) temp.add(item);
			prev = item;
		}

		output.addAll(temp);

		return output;
	}


	private ArrayList<PersonDataOutput> processDuplicatePersons(ArrayList<PersonDataOutput> data, PersonDataInput params){

		ArrayList<PersonDataOutput> output = new ArrayList<PersonDataOutput>();
		ArrayList<PersonDataOutput> temp = new ArrayList<PersonDataOutput>();

		PersonDataOutput prev = null;
		PersonDataOutput save = null;
		boolean process = false;

		System.out.println("*********************Processing duplicates Person");

		for (PersonDataOutput item : data) {
			if(prev == null){
				prev = item;
				temp.add(prev);
				continue;
			}

			if(!samePerson(item, prev)) {
				System.out.println("*********************Not Same Contact Person!" + item.getFirstname()+" "+item.getLastname());
				temp.add(item);
			}
			prev = item;
		}

		output.addAll(temp);

		return output;
	}


	private ArrayList<PolicyDataOutput> processDuplicatePhoneNumbers(ArrayList<PolicyDataOutput> data){

		ArrayList<PolicyDataOutput> output = new ArrayList<PolicyDataOutput>();
		ArrayList<PolicyDataOutput> temp = new ArrayList<PolicyDataOutput>();

		PolicyDataOutput prev = null;
		PolicyDataOutput save = null;
		boolean process = false;

		System.out.println("*********************Processing Policy duplicates Phone Numbers");

		for (PolicyDataOutput item : data) {
			if(prev == null){
				prev = item;
				temp.add(prev);
				continue;
			}

			if(!item.getWorkphone().equals(prev.getWorkphone())) {
				System.out.println("*********************Different Policy Contact Phone number!" + item.getFirstname()+" "+item.getLastname()+" "+item.getWorkphone());
				temp.add(item);
			}
			prev = item;
		}

		output.addAll(temp);

		return output;
	}


}

