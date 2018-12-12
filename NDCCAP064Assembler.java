package gov.state.nextgen.co.bo;

import edu.emory.mathcs.backport.java.util.Arrays;
import gov.state.nextgen.co.util.CONoticeUtility;
import gov.state.nextgen.co.util.xsd.schema.othernotices.ChimesCorrespondence;
import gov.state.nextgen.co.util.xsd.schema.othernotices.HccActivityAllowedList;
import gov.state.nextgen.co.util.xsd.schema.othernotices.HccBenefitPeriodList;
import gov.state.nextgen.co.util.xsd.schema.othernotices.HccChildCareList;
import gov.state.nextgen.co.util.xsd.schema.othernotices.HouseholdCopyCertificate;
import gov.state.nextgen.co.util.xsd.schema.othernotices.MetaData;
import gov.state.nextgen.co.util.xsd.schema.othernotices.NoticeData;
import gov.state.nextgen.co.util.xsd.schema.othernotices.PccChildCareList;
import gov.state.nextgen.co.util.xsd.schema.othernotices.TextTable;
import gov.state.nextgen.common.bo.BiUtils;
import gov.state.nextgen.common.cargo.custom.COCorrespondence;
import gov.state.nextgen.common.cargo.custom.CcapStateMaxRateCargo;
import gov.state.nextgen.common.cargo.custom.CoRequestHistoryCargo;
import gov.state.nextgen.common.cargo.custom.DcActivityScheduleCargo;
import gov.state.nextgen.common.cargo.custom.DcIndvCargo;
import gov.state.nextgen.common.cargo.custom.EdCaseRecertDatesCargo;
import gov.state.nextgen.common.cargo.custom.EdEligibilityCargo;
import gov.state.nextgen.common.cargo.custom.PmAddressCargo;
import gov.state.nextgen.common.cargo.custom.PmCcapProvidersCargo;
import gov.state.nextgen.common.cargo.custom.PmProviderChildAssocCargo;
import gov.state.nextgen.common.cargo.custom.PmProviderPersonCargo;
import gov.state.nextgen.common.cargo.custom.VEdEligibilityIndvCargo;
import gov.state.nextgen.common.dao.custom.EdEligibilityDAO;
import gov.state.nextgen.common.exception.CoException;
import gov.state.nextgen.common.util.ALSOPUtil;
import gov.state.nextgen.common.util.CoConstants;
import gov.state.nextgen.common.util.CoDebugger;
import gov.state.nextgen.common.util.DateFormatter;
import gov.state.nextgen.common.util.ReferenceTableAccess;
import gov.state.nextgen.framework.business.entities.cargo.custom.RefTableData;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ibm.icu.impl.duration.Period;




/**
 * The class <code> NDCCAP064Assembler </code> 
 * <dl>
 * <dt>
 * <b>NDCCAP064Assembler</b></dt>
 * </dl>
 * 
 * @author akshayas
 * @version %New%
 * 
 */
public class NDCCAP064Assembler extends DCAssembler implements CoBaseAssembler {

    /**
     * @param args
     */

    public NDCCAP064Assembler() {
        super();
    }

    @Override
    /**
     * Method to populate the values for the notice
     * @author akshayas
     * @param coCorrespondence
     * @return ChimesCorrespondence
     * @throws CoException
     * 
     */
    public ChimesCorrespondence getChimesCorrespondence(COCorrespondence coCorrespondence) throws CoException {

        ChimesCorrespondence chimesCorrespondence = new ChimesCorrespondence();
        
		DateFormat dateFormat1=new SimpleDateFormat(CoConstants.DATE_FORMAT_DD_MMM_YYYY);
		String refDate = dateFormat1.format(coCorrespondence.getGenerateDate().getTime());
		
		DateFormat dateFormat2 = new SimpleDateFormat(CoConstants.DATE_FORMAT);
        HouseholdCopyCertificate householdCopyCertificate=new HouseholdCopyCertificate();
        
        Long case_num=null;
 	  // Integer provider_id= new Integer((int)coCorrespondence.getProviderId());
        if(null!=coCorrespondence.getCaseAppNumber()){
 		   case_num=Long.parseLong(coCorrespondence.getCaseAppNumber());
 	   }else if(null!=coCorrespondence.getChipAppNum()){
     	   case_num=Long.parseLong(coCorrespondence.getChipAppNum());
        }else {
     	   CoRequestHistoryCargo[] coRequestHistorycargo =(CoRequestHistoryCargo[])coDAOServices.getCoRequestHistoryByCoReqSeq(coCorrespondence.getCoReqSeq());   
             if(coRequestHistorycargo!=null && coRequestHistorycargo.length>0 && coRequestHistorycargo[0]!=null) {
				  if(coRequestHistorycargo[0].getCaseNum()!=null) {
					  case_num=coRequestHistorycargo[0].getCaseNum();
					  }
				  }
        }
 	  HashMap<Long,Integer> combo =new HashMap<Long,Integer>();
 	   HashMap<HashMap<Long,Integer>,List<HccChildCareList>> childTableMap=new HashMap<HashMap<Long,Integer>,List<HccChildCareList>>();  
 	   List<HccActivityAllowedList> allowableActivityTableList= new ArrayList<HccActivityAllowedList>();
       ArrayList<Long> childIndvIds = new ArrayList<Long>();
       String age = "";
       String levelofcare = "";
       String assnEndDate="";
      
             
        /**set metadata **/
       MetaData metaData = CONoticeUtility.getMetaData(coCorrespondence, CoConstants.T_NDCCAP064);
       chimesCorrespondence.setMetaData(metaData);
        
        NoticeData noticeData = new NoticeData();
        /** set additional comments **/
        noticeData= populateManualData(coCorrespondence, householdCopyCertificate,noticeData);
		///HccChildCareList childTable= null;
		 List<HccChildCareList> childTable= null;
		 List<HccChildCareList> newchildTable= new ArrayList<HccChildCareList>();
	 	 List<HccChildCareList> associationDates =null;
	 	HccChildCareList  childCareListExisting= null;
        
        try{
            householdCopyCertificate=setCoPayCertDatesEdEligibility(dateFormat2, householdCopyCertificate,case_num); 
        	PmProviderChildAssocCargo[] pmProviderChildAssocCargos =(PmProviderChildAssocCargo[])coDAOServices.findByCasenumHistnavAssocDates(case_num); // getting all the children who have PM association in case and respective provider ids 
        	if (null != pmProviderChildAssocCargos && pmProviderChildAssocCargos.length > 0) { 
        		for(PmProviderChildAssocCargo pmProviderChildAssocCargo : pmProviderChildAssocCargos ){// getting provider details for those provider id
        			PmCcapProvidersCargo[] pmProviderCargos =(PmCcapProvidersCargo[])coDAOServices.getProviderDetailsMetaData(pmProviderChildAssocCargo.getProviderId()); 
        			childCareListExisting=new HccChildCareList();
        			childTable=new ArrayList<HccChildCareList>();
        			String providertype =providertype(pmProviderCargos);
        			/** set provider name **/
        			String providerName = populateProviderName(pmProviderChildAssocCargo.getProviderId(),pmProviderCargos);
        			if(null==providerName){
        				providerName=CoConstants.SPACE;
        			}
        			/** populate child name**/
        			if(pmProviderChildAssocCargo!=null){
        				combo.put(pmProviderChildAssocCargo.getChildIndvId(),pmProviderChildAssocCargo.getProviderId());
        				childTableMap.put(combo,newchildTable);
        				childIndvIds.add(pmProviderChildAssocCargo.getChildIndvId());
        			}    	   

        			//   if (childIndvIds!=null && childIndvIds.size()>0) {                         
        			DcIndvCargo[] dcIndvCargos = (DcIndvCargo[]) coDAOServices.getDcIndividualDetails(pmProviderChildAssocCargo.getChildIndvId());
        			if (dcIndvCargos != null && dcIndvCargos.length>0) {
        				for (DcIndvCargo dcIndvCargo : dcIndvCargos) {
        					if(dcIndvCargo!=null){
        						
        						VEdEligibilityIndvCargo[] edIndvEligibilityCargos = (VEdEligibilityIndvCargo[]) coDAOServices.getLevelofCarePartstatusASC(case_num,dcIndvCargo.getIndvId(),coCorrespondence.getGenerateDate());
        						boolean futureChild=false;
        						boolean excludedChild = false;
        						 Timestamp excludedChildMonth =null;
		          		    	 String prevLevelOfCare = CoConstants.EMPTY_STRING;
		          		    	VEdEligibilityIndvCargo edIndvEligibilityCargo1=null;
		          		    	 for(VEdEligibilityIndvCargo edIndvEligibilityCargo : edIndvEligibilityCargos){
		          		    		 if(pmProviderChildAssocCargo.getAssignBegDt().equals(edIndvEligibilityCargo.getPaymentBegDt()))
		          		    		 {
		          		    			edIndvEligibilityCargo1=edIndvEligibilityCargo;
		          		    			break;
		          		    		 }
		          		    	 }
		          		    	 
		          		    	 if(null!=edIndvEligibilityCargo1 && null != edIndvEligibilityCargo1.getLevelOfCareCd())
		          		    	 {
		          		    	 
		          		    	 for(VEdEligibilityIndvCargo edIndvEligibilityCargo : edIndvEligibilityCargos){
		          		    	 		if(!excludedChild && "XC".equals(edIndvEligibilityCargo.getPartStatusCd())){
		          		    	 			excludedChildMonth = ALSOPUtil.getLastDayOfMonth(edIndvEligibilityCargo.getPaymentBegDt());
		          		    	 			excludedChild = true;
		          		    	 		}
		          		    	 	}
        						
        						
        						if(childTableMap.containsKey(combo)) {
        							
        							//childTable= new HccChildCareList();
        							/** set provider association **/
			          		    	 
			          		    	if(dcIndvCargo.getDobDt().compareTo(pmProviderChildAssocCargo.getAssignBegDt())>0)
			          		    	{
			          		    		futureChild=true;
			          		    	}
        		    		    		if (pmProviderChildAssocCargo != null)
        		    		    		{
        		    		    			if(pmProviderChildAssocCargo.getChildIndvId() == dcIndvCargo.getIndvId())
        		    		    			{
        		    		    				associationDates= new ArrayList<HccChildCareList>();
        		    		    				assnEndDate=ALSOPUtil.getStringFromTS(pmProviderChildAssocCargo.getAssignEndDt());
        		    		    				
        		    		    				List<Timestamp> months=   ALSOPUtil.getAllMonths(pmProviderChildAssocCargo.getAssignBegDt(), pmProviderChildAssocCargo.getAssignEndDt());
        		    		    				/** ND-110116 Start*/
        		    		    				if(null != excludedChildMonth){
            		    		    				assnEndDate=ALSOPUtil.getStringFromTS(excludedChildMonth);
            		    		    				months=   ALSOPUtil.getAllMonths(pmProviderChildAssocCargo.getAssignBegDt(), excludedChildMonth);

        		    		    				}
        		    		    				/** ND-110116 End*/
        		    		    				//assnEndDate=pmProviderChildAssocCargo.getAssignEndDt();
        		    		    	    		for(Timestamp beginDate :months)
        		    		    	    		{
        		    		    	    		Timestamp lastday =ALSOPUtil.getLastDayOfMonth(beginDate);
        		    		    	    	    HccChildCareList assocDates=new HccChildCareList();
        		    		    	    	    assocDates.setHccAssociationStartDate(ALSOPUtil.getStringFromTS(beginDate));
        		    		    	    	    assocDates.setHccAssociationEndDate(ALSOPUtil.getStringFromTS(lastday));
        		    		    	    	    associationDates.add(assocDates);
        		    		    	    		
        		    		    	    		}
        		    		    	    		
        		    		    	    		childCareListExisting.setHccAssociationStartDate(ALSOPUtil.getStringFromTS(pmProviderChildAssocCargo.getAssignBegDt()));
        	        							childCareListExisting.setHccAssociationEndDate(assnEndDate);
        		    		    			}
        		    		    		}
        		    		    	
        							
        							
        		
        							
        							/** set childname **/
        							String childName=String.valueOf(CONoticeUtility.getFormattedName(dcIndvCargo.getFirstName(), dcIndvCargo.getMidName(),dcIndvCargo.getLastName()));
        							if(null==childName){
        								childName=CoConstants.SPACE;
        							}
        							childCareListExisting.setHccChildName(childName);
        							age = agegroup(age, dcIndvCargo);
        							/** populate provider name **/
        							childCareListExisting.setHccProviderName(providerName);
        							
        							
        							/**populate level of care **/

        							try{
        			    				  int count=0;
        			    				//  dcIndvCargo.setIndvId(100152478);
        			    				 // 100152478--dcIndvCargo.getIndvId()
        			    				 
        			    				 // boolean ageCheckValidation=true;
        			        		      	
        			        		      if(edIndvEligibilityCargos!=null && edIndvEligibilityCargos.length>0 ) {
        			        		      for(HccChildCareList assocDates : associationDates){
        			        		    	  boolean agecheck = false;
            			    				 
        			        		    	  VEdEligibilityIndvCargo edIndvEligibilityCargoTest = null;
        			        		    	  VEdEligibilityIndvCargo edIndvEligibilityCargoLast=new VEdEligibilityIndvCargo() ;
        			          		    	int cnt=  (edIndvEligibilityCargos.length) -1;
        			          		    	
        			          		    	edIndvEligibilityCargoLast=edIndvEligibilityCargos[cnt];
        			          		    	 
        			        		    	  for(VEdEligibilityIndvCargo edIndvEligibilityCargo : edIndvEligibilityCargos){
        			        		    		  if (assocDates.getHccAssociationStartDate().equals(ALSOPUtil.getStringFromTS(edIndvEligibilityCargo.getPaymentBegDt()))){
        			        		    			  edIndvEligibilityCargoTest= new VEdEligibilityIndvCargo();
        			        		    			  edIndvEligibilityCargoTest = edIndvEligibilityCargo;
        			        		    			  break;
        			        		    		  }
        			        		    	  }
        			        		    	  
        			        		    	  if(!futureChild)
        			        		    	  {
        			        		    	  agecheck=checkifAgeGroupChanges(dcIndvCargo.getDob(),assocDates.getHccAssociationStartDate(),assocDates.getHccAssociationEndDate());
        			        		    	  }
        			        		    	  
        										 HccChildCareList childCareListCurr =new HccChildCareList();
        									  childCareListCurr.setHccAssociationStartDate(childCareListExisting.getHccAssociationStartDate());
        			    		    		  childCareListCurr.setHccAssociationEndDate(childCareListExisting.getHccAssociationEndDate());
        			    		    		  childCareListCurr.setHccChildName(childCareListExisting.getHccChildName());
        			    		    		  childCareListCurr.setHccProviderName(childCareListExisting.getHccProviderName());
        										
        										 
        				        				      if(null != edIndvEligibilityCargoTest){
        				        				    	  if(CoConstants.HALF_TIME.equalsIgnoreCase(edIndvEligibilityCargoTest.getLevelOfCareCd())){
        				    						  levelofcare = CoConstants.PART_TIME; 
        				    					      
        				        				      }else if(CoConstants.HO.equalsIgnoreCase(edIndvEligibilityCargoTest.getLevelOfCareCd())){
        				    						  levelofcare = CoConstants.HOURLY; 
        				        					  }
        				        				      else
        				        				    	  levelofcare=  edIndvEligibilityCargoTest.getLevelOfCareCd();
        				        				    	  
        				        				      }else {
        				        				    	  if( edIndvEligibilityCargoLast.getLevelOfCareCd().equalsIgnoreCase(CoConstants.HALF_TIME)){
        				    	    						  levelofcare = CoConstants.PART_TIME; 
        				    	    					      
        				    	        				      }else if(CoConstants.HO.equalsIgnoreCase(edIndvEligibilityCargoLast.getLevelOfCareCd())){
        				    	    						  levelofcare = CoConstants.HOURLY; 
        				    	        					  }
        				    	        				      else
        				    	        				    	  levelofcare=  edIndvEligibilityCargoLast.getLevelOfCareCd();
        				        						 
        				        						  
        				        	    		      }
        				        				      /**ND-110116 Start */
        				        				      if (null != levelofcare && !CoConstants.EMPTY_STRING.equals(levelofcare) ){
        				        				    	  prevLevelOfCare = levelofcare;
        				        				      }else if((null == levelofcare || CoConstants.EMPTY_STRING.equals(levelofcare)) && excludedChild){
        				        				    	  levelofcare = prevLevelOfCare;
        				        				      }
        				        				      /**ND-110116 End */
        				        					  
        				        	        	
        										 
        										 
        										  if (count == 0)
        				        				  {
        											  childCareListExisting.setHccLevelOfCare(levelofcare);
        											  childCareListCurr.setHccLevelOfCare(levelofcare);
        											  
        				        					  
        				        				  }
        				        				  else
        				        				  {
        				        					  if( childCareListExisting.getHccLevelOfCare().equalsIgnoreCase(levelofcare) && agecheck == false )
        				        					  {
        				        						  
        				        						 // childCareListCurr.setAssociationBeginDate(edIndvEligibilityCargo.getPaymentBegDt().toString());
        				        						  childCareListCurr.setHccLevelOfCare(levelofcare);
        				        						// childTable.add(childCareListExisting);
        				        						 childCareListExisting=childCareListCurr;
        				        						 
        				        						 
        				        					  }
        				        					  else if(agecheck == true && childCareListExisting.getHccLevelOfCare().equalsIgnoreCase(levelofcare) && edIndvEligibilityCargoTest !=null)
        				        					  {
        				        						  Timestamp lastDay=BiUtils.getLastDayOfMonth(edIndvEligibilityCargoTest.getPaymentBegDt()) ;
        					        						 childCareListCurr.setHccAssociationStartDate(ALSOPUtil.getStringFromTS(BiUtils.getFirstDayOfNextMonth(edIndvEligibilityCargoTest.getPaymentBegDt())));
        					        						 childCareListExisting.setHccAssociationEndDate(ALSOPUtil.getStringFromTS(lastDay));
        					        						 childTable.add(childCareListExisting);
        					        						 childCareListCurr.setHccLevelOfCare(levelofcare);
        					        						 childCareListExisting=childCareListCurr;
        				        					  }
        				        					  else
        				        						 
        				        					  {
        				        						  if(edIndvEligibilityCargoTest !=null && agecheck== false)
        				        						  {
        				        						 Timestamp lastDay=BiUtils.getLastDayOfPreviousMonth(edIndvEligibilityCargoTest.getPaymentBegDt()) ;
        				        						 childCareListCurr.setHccAssociationStartDate(ALSOPUtil.getStringFromTS(edIndvEligibilityCargoTest.getPaymentBegDt()));
        				        						 childCareListExisting.setHccAssociationEndDate(ALSOPUtil.getStringFromTS(lastDay));
        				        						 childTable.add(childCareListExisting);
        				        						 childCareListCurr.setHccLevelOfCare(levelofcare);
        				        						 childCareListExisting=childCareListCurr;
        				        						 
        				        						  }
        				        						  else if(agecheck==true && edIndvEligibilityCargoTest!=null)
        				        						  {
        				        							  HccChildCareList newCurrentchildCareList = new HccChildCareList();
        				        							  childCareListExisting.setHccAssociationEndDate(ALSOPUtil.getStringFromTS(BiUtils.getLastDayOfPreviousMonth(edIndvEligibilityCargoTest.getPaymentBegDt())));
        				        							  
        				        							  childTable.add(childCareListExisting);
        				        							  
        				        							  newCurrentchildCareList.setHccAssociationStartDate(assocDates. getHccAssociationStartDate());
        				        							  newCurrentchildCareList.setHccAssociationEndDate(assocDates.getHccAssociationEndDate());
        				        							  newCurrentchildCareList.setHccLevelOfCare(levelofcare);
        				        							  newCurrentchildCareList.setHccChildName(childCareListExisting.getHccChildName());
        				        							  newCurrentchildCareList.setHccProviderName(childCareListExisting.getHccProviderName());
        				        							  
        				        							  
        				        							  childTable.add(newCurrentchildCareList);
        				        							  childCareListCurr.setHccAssociationStartDate(ALSOPUtil.getStringFromTS(BiUtils.getFirstDayOfNextMonth(edIndvEligibilityCargoTest.getPaymentBegDt())));
        				        							  childCareListCurr.setHccAssociationEndDate(assnEndDate);
        				        							  childCareListCurr.setHccLevelOfCare(levelofcare);
        				        							  childCareListExisting=childCareListCurr;
        				        							  
        				        							  
        				        						  }
        				        						  else if(agecheck==true && edIndvEligibilityCargoTest==null)
        				        						  {
        				        							  Timestamp lastDay=BiUtils.getLastDayOfMonth(ALSOPUtil.getTSfromString(assocDates.getHccAssociationStartDate())) ;
        				        							  Timestamp firstDayOfNextMonth=BiUtils.getFirstDayOfNextMonth(ALSOPUtil.getTSfromString(assocDates. getHccAssociationStartDate()));
        						        						 childCareListCurr.setHccAssociationStartDate(ALSOPUtil.getStringFromTS(firstDayOfNextMonth));
        						        					     childCareListExisting.setHccAssociationEndDate(ALSOPUtil.getStringFromTS(lastDay));
        						        						 childTable.add(childCareListExisting);
        						        						 childCareListCurr.setHccLevelOfCare(childCareListExisting.getHccLevelOfCare());
        						        						// childTable.add(childCareListCurr);
        						        						 childCareListExisting=childCareListCurr;
        					        						  }
        				        						
        				        					  }
        				        				  }
        										 
        				        				  count++;
        				        				 if(count==associationDates.size())
        				        				 {
        				        				     childTable.add(childCareListCurr);
        				        				   
        				        				 }
        				        				 
        				        				 
        									 
        			        		    	  
        			        		      }
        			        		      
        			        		      
        			        		      
        			        		      }
        			        					 else if(null==edIndvEligibilityCargos){
        			               				  levelofcare=CoConstants.SPACE;
        			               				  childCareListExisting.setHccLevelOfCare(levelofcare);
        			               			  }
        			               			 
        			           			  
        			           			 }catch(Exception e){
        								CoDebugger.debugInformation(e.getMessage());
        							}
        						}
        						/**location of care**/
        						for(HccChildCareList childLists : childTable )
        	    				  {
        							if(childLists.getHccLevelOfCare()!=null && (childLists.getHccLevelOfCare().equalsIgnoreCase(CoConstants.PART_TIME) || childLists.getHccLevelOfCare().equalsIgnoreCase(CoConstants.FULL_TIME) || childLists.getHccLevelOfCare().equalsIgnoreCase(CoConstants.HOURLY))){
        							if(childLists.getHccCareLocation() == null)
        							{
        							setlocationofcare(pmProviderChildAssocCargo.getProviderId(), pmProviderCargos, childLists);
            						if(null==childLists.getHccCareLocation()||childLists.getHccCareLocation().equalsIgnoreCase(CoConstants.EMPTY_STRING)){
            							childLists.setHccCareLocation(CoConstants.SPACE);
            						}
        							}
        	    				  }
        							else
        								childLists.setHccCareLocation(CoConstants.SPACE);
        	    					  }
        						

        						/**populate state max rate**/
        						String effectiveDt="01-OCT-17";
        						String oldEffectiveDt="01-MAR-17";
        						String eff_beg_dt="10/01/2017";
        						String ageGroup=null;
        						CcapStateMaxRateCargo[] ccapStateMaxRateCargos=null;
        					//	if( levelofcare!=null && (levelofcare.equalsIgnoreCase(CoConstants.PART_TIME) || levelofcare.equalsIgnoreCase(CoConstants.FULL_TIME) || levelofcare.equalsIgnoreCase(CoConstants.HOURLY))){
        						//	if(levelofcare.equalsIgnoreCase(CoConstants.HOURLY)){
        						//		levelofcare=CoConstants.HO;
        						//	}        			
        							try {
        								/**OLD SMR AND NEW SMR CHANGES**/
        								for(HccChildCareList childLists : childTable )
                	    				  {
        									if((ALSOPUtil.getTSfromString(childLists.getHccAssociationStartDate()).compareTo(ALSOPUtil.getTSfromString(eff_beg_dt))<0) &&
        											ALSOPUtil.getTSfromString(childLists.getHccAssociationEndDate()).compareTo(ALSOPUtil.getTSfromString(eff_beg_dt))>0)
        									{
        										HccChildCareList newchildLists=new HccChildCareList();
        										newchildLists.setHccAssociationEndDate(childLists.getHccAssociationEndDate());
        										newchildLists.setHccAssociationStartDate(eff_beg_dt);
        										newchildLists.setHccCareLocation(childLists.getHccCareLocation());
        										newchildLists.setHccChildName(childLists.getHccChildName());
        										newchildLists.setHccProviderName(childLists.getHccProviderName());
        										newchildLists.setHccLevelOfCare(childLists.getHccLevelOfCare());
        										childLists.setHccAssociationEndDate(ALSOPUtil.getStringFromTS(BiUtils.getLastDayOfPreviousMonth(ALSOPUtil.getTSfromString(eff_beg_dt))));
        										
        										newchildTable.add(childLists);
        										newchildTable.add(newchildLists);
        									
        										
        									}
        									else
        										newchildTable.add(childLists);
                	    				  }        								       								      								
        								
        								
        								/**Setting SMR**/
        								for(HccChildCareList childLists : newchildTable )
              	    				  {
        									if( childLists.getHccLevelOfCare()!=null &&  (childLists.getHccLevelOfCare().equalsIgnoreCase(CoConstants.PART_TIME) || childLists.getHccLevelOfCare().equalsIgnoreCase(CoConstants.FULL_TIME) || childLists.getHccLevelOfCare().equalsIgnoreCase(CoConstants.HOURLY)))
        									{
        										     										
        										if(childLists.getHccStateMaximumRate()==null)
        										{
        											if(!futureChild)
        			    							{
        												ageGroup=getAgeGroupforassocDate(childLists.getHccAssociationStartDate(),childLists.getHccAssociationEndDate(),dcIndvCargo.getDob());
        			    							}
        											else
        												ageGroup="IN";
        											
        								if(ALSOPUtil.getTSfromString(childLists.getHccAssociationStartDate()).compareTo(ALSOPUtil.getTSfromString(eff_beg_dt))<0)
        								{
        								 ccapStateMaxRateCargos = (CcapStateMaxRateCargo[])coDAOServices.getOLDStateMaxRate(ageGroup,providertype,childLists.getHccLevelOfCare(),oldEffectiveDt);
        								}
        								else
        								{
            							 ccapStateMaxRateCargos = (CcapStateMaxRateCargo[])coDAOServices.getStateMaxRate(ageGroup,providertype,childLists.getHccLevelOfCare(),effectiveDt);
            								
        								}
        									
        								if(ccapStateMaxRateCargos!=null && ccapStateMaxRateCargos.length>0 && ccapStateMaxRateCargos[0]!=null) {

        									String maxrate = CONoticeUtility.getFormattedAmount(ccapStateMaxRateCargos[0].getMaxRate());
        									if(maxrate!=null) {
        										childLists.setHccStateMaximumRate(maxrate);

        									}else if(null==maxrate){
        										childLists.setHccStateMaximumRate(CoConstants.SPACE);
        									}
        								}else if(null==ccapStateMaxRateCargos){
        									childLists.setHccStateMaximumRate(CoConstants.SPACE);
        								}
        									}
        									}
        									else
        										childLists.setHccStateMaximumRate(CoConstants.SPACE);
              	    				  }
        							} catch (Exception e) {
        								CoDebugger.debugInformation(e.getMessage());
        							}
        						//}else{
        						//	childCareListExisting.setHccStateMaximumRate(CoConstants.SPACE);
        					//	}
        						combo.put(dcIndvCargo.getIndvId(),pmProviderChildAssocCargo.getProviderId());
        						childTableMap.put(combo,newchildTable);
        						
        						

        					}
        				}
        			}
        		}
        		}
        		for(HccChildCareList childLists : newchildTable )
				  {
        			if (childLists.getHccLevelOfCare().equals(CoConstants.PART_TIME))
        			{
        				childLists.setHccLevelOfCare(CoConstants.PART_TIME_VAL);
        			}
        			else if( (childLists.getHccLevelOfCare().equals(CoConstants.FULL_TIME)))
        			{
        				childLists.setHccLevelOfCare(CoConstants.FULL_TIME_VAL);
        			}
				  }
         
         
		 /** populate the childTableList **/
         if(childTableMap!=null && childTableMap.size()>0 && childTableMap.values()!=null && childTableMap.values().size()>0 
        		  && householdCopyCertificate!=null && householdCopyCertificate.getHccChildCare()!=null){
        	 
        	 householdCopyCertificate.getHccChildCare().addAll(newchildTable);
        		// householdCopyCertificate.getHccChildCare().addAll(childTableMap.get());       	  
          }
         /** populate allowable activities per child **/        			  
		 /* allowableActivityTableList=populateAllowableActivities(refDate,allowableActivityTableList, childIndvIds,householdCopyCertificate);
         householdCopyCertificate.setHccActivityAllowed(allowableActivityTableList);*/
        EdEligibilityCargo[] edEligibilityDao= (EdEligibilityCargo[])coDAOServices.getEligibilityInfo(case_num);
        if(edEligibilityDao.length>0)
        {        	
        householdCopyCertificate.setEligibilityStartDate(ALSOPUtil.getStringFromTS(edEligibilityDao[0].getEligibilityBegDt()));
        
        }	
         
        
        noticeData.setHouseholdCopyCertificate(householdCopyCertificate);
          	   }
        	 //}
        }catch(Exception e)
        {
        	CoDebugger.debugException(" NDCCAP064Assembler -->Exception " +e.getMessage(), e);
        }
           
        chimesCorrespondence.setNoticeData(noticeData);
        return chimesCorrespondence;
    }

	private void setlocationofcare(Integer provider_id,
			PmCcapProvidersCargo[] pmProviderCargos, HccChildCareList childTable) {
		try{
		if(pmProviderCargos!=null && pmProviderCargos.length>0 && pmProviderCargos[0]!=null)
		  {
		   if(pmProviderCargos[0].getOrganizationType()!=null && CoConstants.IN.equals(pmProviderCargos[0].getOrganizationType()) && (pmProviderCargos[0].getHistNavInd()=='S' || pmProviderCargos[0].getHistNavInd()=='P'))
		   {
			   if(null!=pmProviderCargos[0].getCareSw() && pmProviderCargos[0].getCareSw().equalsIgnoreCase(CoConstants.P)){
				  PmAddressCargo[] pmaddress;
					pmaddress = (PmAddressCargo[])coDAOServices.getLocationofcareforAR(provider_id,CoConstants.ADDR_TYPE_CD_PA);
				
				  locationofcare(childTable, pmaddress);

				
			   } else if(null!=pmProviderCargos[0].getCareSw() && pmProviderCargos[0].getCareSw().equalsIgnoreCase(CoConstants.C)){        	    			   
				   PmProviderPersonCargo[] pmProviderPerson = (PmProviderPersonCargo[])coDAOServices.getLocationofcareforARtypeC(Long.valueOf(provider_id));
				   if(pmProviderPerson!=null && pmProviderPerson.length>0 && pmProviderPerson[0]!=null)
			          {
			    	   if(pmProviderPerson[0].getInHomeSw()!=null && pmProviderPerson[0].getInHomeSw().equalsIgnoreCase(CoConstants.Y))
			    	   {
			    		   PmAddressCargo[] pmaddress = (PmAddressCargo[])coDAOServices.getLocationofcareforAR(provider_id,CoConstants.CLIENT);
			    			  locationofcare(childTable, pmaddress);
			    		   
			    	   }
			    	   if(pmProviderPerson[0].getInHomeSw()!=null && pmProviderPerson[0].getInHomeSw().equalsIgnoreCase(CoConstants.N)){
			    			  PmAddressCargo[] pmaddress = (PmAddressCargo[])coDAOServices.getLocationofcareforAR(provider_id,CoConstants.ADDR_TYPE_CD_PA);
			    			  locationofcare(childTable, pmaddress);
			    		   
			          }
			    }else{
					   PmAddressCargo[] pmaddress = (PmAddressCargo[])coDAOServices.getLocationofcareforAR(provider_id,CoConstants.ADDR_TYPE_CD_PA);
					   if(pmaddress!=null && pmaddress.length>0 && pmaddress[0]!=null){
						   locationofcare(childTable, pmaddress);
					   }
				   }
				   
				   
			   }
			   else
			   {
				if(null != provider_id)
				{
					PmAddressCargo[] pmaddress = (PmAddressCargo[])coDAOServices.getLocationofcareforAR(provider_id,CoConstants.ADDR_TYPE_CD_PA);
					if(pmaddress!=null && pmaddress.length>0 && pmaddress[0]!=null){
						   locationofcare(childTable, pmaddress);
					   }
				}
			   }
		   } else  if(pmProviderCargos[0].getOrganizationType()!=null && CoConstants.OR.equals(pmProviderCargos[0].getOrganizationType()) && (pmProviderCargos[0].getHistNavInd()=='S' || pmProviderCargos[0].getHistNavInd()=='P')){

			  PmAddressCargo[] pmaddress = (PmAddressCargo[])coDAOServices.getLocationofcareforAR(provider_id,CoConstants.ADDR_TYPE_CD_PA);
			  locationofcare(childTable, pmaddress);
		   
 	          
		   }
		  }
		} catch (CoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void locationofcare(HccChildCareList childTable,
			PmAddressCargo[] pmaddress) {
		if(null != pmaddress && pmaddress.length>0 && null != pmaddress[0]){
			StringBuilder addressLine=new StringBuilder(CoConstants.EMPTY_STRING);
			if(null!=pmaddress[0].getAddrLine1()){
			addressLine.append(pmaddress[0].getAddrLine1());
			}
            if(null != pmaddress[0].getAddrLine2()){
            	addressLine.append(System.lineSeparator()+pmaddress[0].getAddrLine2().toString());
            }
            if(null != pmaddress[0].getAddrLine3()){
       		   addressLine.append(System.lineSeparator()+pmaddress[0].getAddrLine3().toString());
            }
            
            StringBuilder adresLine4=new StringBuilder(CoConstants.EMPTY_STRING);
     		String addrCity=pmaddress[0].getAddrCity();
     		String addrStateCd=pmaddress[0].getAddrStateCd();
     		String addrZip5=pmaddress[0].getAddrZip5();
     		
     		if(addrCity!=null){
     			adresLine4.append(addrCity+CoConstants.SPACE);
     		}
     		
     		if(addrStateCd!=null){
     			adresLine4.append(addrStateCd+CoConstants.SPACE);
     		}
     		
     		if(addrZip5!=null){
     			adresLine4.append(addrZip5 + CoConstants.SPACE);
     		}
     		
     		if(pmaddress[0].getAddrZip4()!=null){
     			adresLine4.append(CoConstants.HYPHEN+pmaddress[0].getAddrZip4());
     		} 
		 	if(adresLine4!=null){
		 		addressLine.append(System.lineSeparator()+adresLine4);
		 	}
		 	if(addressLine.equals(CoConstants.EMPTY_STRING)){
		 		 childTable.setHccCareLocation(CoConstants.SPACE);
		 	}else{
		 		childTable.setHccCareLocation(addressLine.toString());
		 		}
		  }
	}

	private String agegroup(String age, DcIndvCargo dcIndvCargo) {
		if(Integer.parseInt(dcIndvCargo.getAge())<2) {
		    age="IN";
		  } else if(Integer.parseInt(dcIndvCargo.getAge())<4 && Integer.parseInt(dcIndvCargo.getAge())>=2) {
			age="TO";
		  } else if(Integer.parseInt(dcIndvCargo.getAge())<6 && Integer.parseInt(dcIndvCargo.getAge())>=4) {
			age="PS";
		  } else if(Integer.parseInt(dcIndvCargo.getAge())>=6) {
			age="OT";
		  }
		return age;
	}

	private HouseholdCopyCertificate setCoPayCertDatesEdEligibility(DateFormat dateFormat2,
			HouseholdCopyCertificate householdCopyCertificate, Long case_num) {
		try{
		/** set certification date ed_eligibility **/
        EdEligibilityCargo[] edEligibilityCargo = (EdEligibilityCargo[])coDAOServices.findAllProgramsCaseAsc(case_num);
        
        List<HccBenefitPeriodList> benfitPeriodList = new ArrayList<HccBenefitPeriodList>();
        HccBenefitPeriodList prevBenefitPeriod = new HccBenefitPeriodList();
        HccBenefitPeriodList currBenfitPeriod = null;
        int count =0;
        String reviewDate = "";
        EdCaseRecertDatesCargo[] edCaseRecertDatesCargo=null;
        EdCaseRecertDatesCargo edCaseRecertDatesCargo1=null;
        
        if(null!=edEligibilityCargo && edEligibilityCargo.length>0){
        	for(EdEligibilityCargo edEligCargo : edEligibilityCargo){
        		edCaseRecertDatesCargo=(EdCaseRecertDatesCargo[])coDAOServices.getReviewDate(edEligCargo.getCaseNum(), edEligCargo.getEdgNum());
        		if(null != edCaseRecertDatesCargo && edCaseRecertDatesCargo.length > 0){
        			edCaseRecertDatesCargo1 = edCaseRecertDatesCargo[0];
        			reviewDate = DateFormatter.dateToString(edCaseRecertDatesCargo1.getRecertReviewDueDt());
        			currBenfitPeriod= new HccBenefitPeriodList();
        			if(edEligCargo.getPaymentBegDt()!=null){
        				currBenfitPeriod.setHccStartDate(dateFormat2.format(edEligCargo.getPaymentBegDt()));
        			}
        			if(edEligCargo.getPaymentEndDt()!=null){
        				currBenfitPeriod.setHccEndDate(dateFormat2.format(edEligCargo.getPaymentEndDt()));
        			}
        			String coPay =CoConstants.EMPTY_STRING;
        			if(0!=edEligCargo.getCopayAmt()){     		
        				coPay = CONoticeUtility.getFormattedAmount(edEligCargo.getCopayAmt());
        				currBenfitPeriod.setHccCopayAmount(coPay);
        			}else if(0==edEligCargo.getCopayAmt()){
        				coPay = CONoticeUtility.getFormattedAmount(0);
        				currBenfitPeriod.setHccCopayAmount(coPay);
        			}
        			if(count == 0){
            			prevBenefitPeriod = currBenfitPeriod;
            		}
            		else{
            			if(prevBenefitPeriod.getHccCopayAmount().equalsIgnoreCase(currBenfitPeriod.getHccCopayAmount())){
            				currBenfitPeriod.setHccStartDate(prevBenefitPeriod.getHccStartDate());
            				currBenfitPeriod.setHccEndDate(currBenfitPeriod.getHccEndDate());
            			}
            			else{
            				benfitPeriodList.add(prevBenefitPeriod);
            				prevBenefitPeriod = currBenfitPeriod;
            			}
            		}
        			prevBenefitPeriod = currBenfitPeriod;
            		count ++;
            		if(count == edEligibilityCargo.length){
            			currBenfitPeriod.setHccEndDate(reviewDate);
            			benfitPeriodList.add(currBenfitPeriod);
            		}
        		}
        	}
        }
        householdCopyCertificate.getHccBenfitPeriod().addAll(benfitPeriodList);
        householdCopyCertificate.setRecertDate(reviewDate);	
        
        /*CoDebugger.debugMessage("ND-90952 EDELIG");
        if(null!=edEligibilityCargo && edEligibilityCargo.length>0 && edEligibilityCargo[0]!=null){
        	CoDebugger.debugMessage("ND-90952 EDELIG LENGTH"+edEligibilityCargo.length);
        	CoDebugger.debugMessage("ND-90952 EDELIG Begin date:"+edEligibilityCargo[0].getEligibilityBegDt());
        	CoDebugger.debugMessage("ND-90952 EDELIG end date :"+edEligibilityCargo[0].getEligibilityEndDt());
        	*//** set cert start date **//*
        	if(edEligibilityCargo[0].getEligibilityBegDt()!=null){
        		 householdCopyCertificate.setHccStartDate(dateFormat2.format(edEligibilityCargo[0].getEligibilityBegDt()));
        	   }
        	*//** set copay**//*
        	String coPay =CoConstants.EMPTY_STRING;
        	if(0!=edEligibilityCargo[0].getCopayAmt()){
        	  coPay = CONoticeUtility.getFormattedAmount(edEligibilityCargo[0].getCopayAmt());
          	  householdCopyCertificate.setHccCopayAmount(coPay);
        	}else if(0==edEligibilityCargo[0].getCopayAmt()){
          	  coPay = CONoticeUtility.getFormattedAmount(0);
          	  householdCopyCertificate.setHccCopayAmount(coPay);
        	}
          	*//** set cert end date **//*  
        	EdEligibilityCargo[] edEligibilityEndDateCargo = (EdEligibilityCargo[])coDAOServices.findCCAPeligEndDate(case_num);    
        	if (null!=edEligibilityEndDateCargo[0].getEligibilityEndDt()){
        		householdCopyCertificate.setHccEndDate(dateFormat2.format(edEligibilityEndDateCargo[0].getEligibilityEndDt()));
        	}else if(null==edEligibilityEndDateCargo[0].getEligibilityEndDt() && null!=edEligibilityEndDateCargo[0].getEstimatedEligEndDt()){
        		householdCopyCertificate.setHccEndDate(dateFormat2.format(edEligibilityEndDateCargo[0].getEstimatedEligEndDt()));
        	}
        	          
        }*/
	 }catch(Exception e)
		{
		 CoDebugger.debugException("NDCCAP064Assembler -->Exception in populating Cert date and copay " +e.getMessage(), e);
		}
        return householdCopyCertificate;
	}

	private NoticeData populateManualData(COCorrespondence coCorrespondence,
			HouseholdCopyCertificate householdCopyCertificate,
			NoticeData noticeData) throws CoException {
		    noticeData.setHouseholdCopyCertificate(householdCopyCertificate);
            return noticeData;
	}
	
	private boolean checkifAgeGroupChanges(String dob, String assocBeginDt ,String assocEnddt)
	{
		String ageGroupBegin=null;
		String ageGroupEnd=null;
		boolean ageCheck=false;
		Double ageBegin=getAgeInYears(dob,assocBeginDt);
		Double ageEnd=getAgeInYears(dob,assocEnddt);
		
		
		ageGroupBegin=getAgeGroup(ageBegin);
		ageGroupEnd=getAgeGroup(ageEnd);
		if(ageGroupBegin.equals(ageGroupEnd))
			ageCheck=false;
		else
			ageCheck=true;
		
		if(ALSOPUtil.getTSfromString(dob).equals(ALSOPUtil.getFirstDayOfMonth(ALSOPUtil.getTSfromString(dob))))
		{
			Double ageCurrentMonth=getAgeInYears(dob,assocBeginDt);
			Double agePrevMonth=getAgeInYears(dob,ALSOPUtil.getStringFromTS(BiUtils.getLastDayOfPreviousMonth(ALSOPUtil.getTSfromString(assocBeginDt))));
			ageGroupBegin=getAgeGroup(ageCurrentMonth);
			ageGroupEnd=getAgeGroup(agePrevMonth);
			if(ageGroupBegin.equals(ageGroupEnd))
				ageCheck=false;
			else
				ageCheck=true;
		}
		
		return ageCheck;
	}

	private Double getAgeInYears(String dob,String assocDate)
	{
		Calendar assocDate1 = Calendar.getInstance();
		Calendar dob1 = Calendar.getInstance();
		dob1.setTime(ALSOPUtil.getTSfromString(dob));
		assocDate1.setTime(ALSOPUtil.getTSfromString(assocDate));
		if (dob1.after(assocDate1)) {
		  throw new IllegalArgumentException("Can't be born in the future");
		}
		int year1 = assocDate1.get(Calendar.YEAR);
		int year2 = dob1.get(Calendar.YEAR);
		int age = year1 - year2;
		int month1 = assocDate1.get(Calendar.MONTH);
		int month2 = dob1.get(Calendar.MONTH);
		  if (month1 == month2) {
		  int day1 = assocDate1.get(Calendar.DAY_OF_MONTH);
		  int day2 = dob1.get(Calendar.DAY_OF_MONTH);
		  if (day2 > day1) {
		    age--;
		  }
		}
		  else if(month2>month1)
			  age--;
		return Double.valueOf(age);
	}
	
	
	
	private String getAgeGroupforassocDate(String begin_dt,String end_dt,String dob)
	{
		
		String ageGroup=null;
		String  ageGroupBegin=null;
		String ageGroupEnd=null;
		Boolean ageCheck = false;
		Double ageBegin=getAgeInYears(dob,begin_dt);
		Double ageEnd=getAgeInYears(dob,end_dt);
		
		ageGroupBegin=getAgeGroup(ageBegin);
		ageGroupEnd=getAgeGroup(ageEnd);
		
		
		
		
		
		return ageGroupBegin;
	}
	
	private String getAgeGroup(Double age)
	{
		String ageGroup=null;
		if(age<2) {
			ageGroup="IN";
		  } else if(age<4 && age>=2) {
			  ageGroup="TO";
		  } else if(age<6 && age>=4) {
			  ageGroup="PS";
		  } else {
			  ageGroup="OT";
		  }
		return ageGroup;
	}

	private String populateProviderName(Integer provider_id, PmCcapProvidersCargo[] pmProviderCargos) {
		String providerName="";
		try{
		
        if(pmProviderCargos!=null && pmProviderCargos.length>0 && pmProviderCargos[0]!=null)
        {
  	   if(pmProviderCargos[0].getOrganizationType()!=null && CoConstants.IN.equals(pmProviderCargos[0].getOrganizationType()))
  	   {
  		   providerName=String.valueOf(CONoticeUtility.getFormattedName(pmProviderCargos[0].getFirstName(), 
  				   pmProviderCargos[0].getMidName(), pmProviderCargos[0].getLastName()));
  		   
  	   } else if(pmProviderCargos[0].getOrganizationType()!=null && 
  			   CoConstants.OR.equals(pmProviderCargos[0].getOrganizationType())){
        	if(null!=pmProviderCargos[0].getBusinessName())	{
  		   providerName= pmProviderCargos[0].getBusinessName();
  	      }else if(null!=pmProviderCargos[0].getFirstName() || null!=pmProviderCargos[0].getLastName() ||null!=pmProviderCargos[0].getMidName()){
  	    	providerName=String.valueOf(CONoticeUtility.getFormattedName(pmProviderCargos[0].getFirstName(), 
    		pmProviderCargos[0].getMidName(), pmProviderCargos[0].getLastName()));
    		   
  	      }
  	      }
        }

                    
		} catch(Exception e){
			CoDebugger.debugException("NDCCAP064Assembler -->Exception in populating providerName " +e.getMessage(), e);
		}
		return providerName;
	}

	private String providertype(PmCcapProvidersCargo[] pmProviderCargos) {
		
		String providertype="";
		String [] CE_licenseTypes={"CC","ML", "OS", "PS", "SA"};
		List CE_licenseTypeList= Arrays.asList(CE_licenseTypes);
		if(pmProviderCargos!=null && pmProviderCargos[0]!=null && pmProviderCargos[0].getLicenseType()!=null){
			String licenseType=pmProviderCargos[0].getLicenseType();
		
		if(licenseType.equalsIgnoreCase("AR")) {
        	   providertype = "AP";
           } else if(licenseType.equalsIgnoreCase("FC") || licenseType.equalsIgnoreCase("GF") ||licenseType.equalsIgnoreCase("GH")) {
        	   providertype = "LF";   
           } else if(licenseType.equalsIgnoreCase("SD") || licenseType.equalsIgnoreCase("TR") || licenseType.equalsIgnoreCase("I")) {
        	   providertype = "SD"; 
           } else if(CE_licenseTypeList.contains(licenseType)){
        	   providertype = "CE"; 
           }
		}
		return providertype;
	}

/*	private List<HccActivityAllowedList> populateAllowableActivities(String refDate,List<HccActivityAllowedList> allowableActivityTableList,
			ArrayList<Long> indvids,HouseholdCopyCertificate hcc) {
	try{
		hcc.setHccisacivityallowed(CoConstants.N);
		DcActivityScheduleCargo[]	  dcActivityScheduleCargos = coDAOServices.getCareTakerIndvId(indvids, refDate);
		CoDebugger.debugMessage("ND-90952 AllowableActivities");
		
		  if(dcActivityScheduleCargos!=null && dcActivityScheduleCargos.length>0)
		  {
			 for(DcActivityScheduleCargo dcActivityScheduleCargo:dcActivityScheduleCargos){
				 CoDebugger.debugMessage("ND-90952 AllowableActivities length of result :"+dcActivityScheduleCargos.length);
				 if(dcActivityScheduleCargo!=null && dcActivityScheduleCargo.getAllowableActivitiesCd()!=null){
					 CoDebugger.debugMessage("ND-90952 AllowableActivities code :"+dcActivityScheduleCargo.getAllowableActivitiesCd());
					 RefTableData[] refTable = ReferenceTableAccess.getRefTableData(CoConstants.ALLOWABLEACTIVITIES, CoConstants.CODE,
							 dcActivityScheduleCargo.getAllowableActivitiesCd());
					 if(refTable!=null && refTable.length>0 && refTable[0]!=null) {
						 HccActivityAllowedList allowableActivity=new HccActivityAllowedList();
						 CoDebugger.debugMessage("ND-90952 AllowableActivities code table:"+refTable[0].getRefrTableDesc());
						 allowableActivity.setHccAllowableActivity(refTable[0].getRefrTableDesc());
						 allowableActivityTableList.add(allowableActivity);
						 hcc.setHccisacivityallowed(CoConstants.Y);

					 }
				 }
			  }
		  }
		}catch(Exception e){
		 CoDebugger.debugException("NDCCAP064Assembler -->Exception in populating AllowableActivities " +e.getMessage(), e);  
		}
		  return allowableActivityTableList;
	}*/

}

