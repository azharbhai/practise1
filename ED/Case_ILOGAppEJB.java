package com.nextgen.IE.vos;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.nextgen.IE.codeValues.EnumNoticeReason;
import com.nextgen.IE.codeValues.EnumTypes.EnumAppSubmittedBy;
import com.nextgen.IE.codeValues.EnumTypes.EnumBenefitStatusCode;
import com.nextgen.IE.codeValues.EnumTypes.EnumCCLevelOfCare;
import com.nextgen.IE.codeValues.EnumTypes.EnumCaseMode;
import com.nextgen.IE.codeValues.EnumTypes.EnumEDBCActionCode;
import com.nextgen.IE.codeValues.EnumTypes.EnumEdTanfType;
import com.nextgen.IE.codeValues.EnumTypes.EnumElderlyDisabledInd;
import com.nextgen.IE.codeValues.EnumTypes.EnumEligibilityGroupIndicator;
import com.nextgen.IE.codeValues.EnumTypes.EnumEligibilityResultCode;
import com.nextgen.IE.codeValues.EnumTypes.EnumEligibilityStatus;
import com.nextgen.IE.codeValues.EnumTypes.EnumExpenseType;
import com.nextgen.IE.codeValues.EnumTypes.EnumHouseholdStatus;
import com.nextgen.IE.codeValues.EnumTypes.EnumInterviewMode;
import com.nextgen.IE.codeValues.EnumTypes.EnumInterviewStatus;
import com.nextgen.IE.codeValues.EnumTypes.EnumLiheapType;
import com.nextgen.IE.codeValues.EnumTypes.EnumLivingArrangement;
import com.nextgen.IE.codeValues.EnumTypes.EnumNonCoopTypeCode;
import com.nextgen.IE.codeValues.EnumTypes.EnumPartStatusCode;
import com.nextgen.IE.codeValues.EnumTypes.EnumPrimaryHeat;
import com.nextgen.IE.codeValues.EnumTypes.EnumPriorMedicaidCd;
import com.nextgen.IE.codeValues.EnumTypes.EnumProgram;
import com.nextgen.IE.codeValues.EnumTypes.EnumProgramCategory;
import com.nextgen.IE.codeValues.EnumTypes.EnumProgramStatus;
import com.nextgen.IE.codeValues.EnumTypes.EnumRace;
import com.nextgen.IE.codeValues.EnumTypes.EnumRelationshipType;
import com.nextgen.IE.codeValues.EnumTypes.EnumResult;
import com.nextgen.IE.codeValues.EnumTypes.EnumReviewCategory;
import com.nextgen.IE.codeValues.EnumTypes.EnumRunMode;
import com.nextgen.IE.codeValues.EnumTypes.EnumSchoolEnrollment;
import com.nextgen.IE.codeValues.EnumTypes.EnumSimplifiedReporting;
import com.nextgen.IE.codeValues.EnumTypes.EnumTriggerCode;
import com.nextgen.IE.codeValues.EnumTypes.EnumTypeOfAssistance;
import com.nextgen.IE.codeValues.EnumTypes.EnumTypeOfHome;
import com.nextgen.IE.codeValues.EnumTypes.EnumVCLCode;
import com.nextgen.IE.codeValues.EnumTypes.EnumWithdrawReasonCode;
import com.nextgen.IE.codeValues.EnumTypes.EnumYesNo;

public class Case implements Serializable {
	
	private static final long serialVersionUID = 8963174099713460347L;
	/**********************Added for Child Care*******************/
	public  final String ELIGIBILITY_END_DT = "ELIG_END_DT";
	public  final String ELIGIBILITY_BEGIN_DT = "ELIG_BEGIN_DT";
	public  final String ESTIMATED_ELIGIBILITY_END_DT = "EST_ELIG_END_DT";
	public  final String SEMI_ANNUAL_REVIEW_DUE_DT = "SEMI_ANNUAL_REVIEW_DT";
	public  final String SIMPLIFIED_REPORTN_GRP_CD = "SIMPLIFIED_REPORTN_GRP_CD";
	public  final String CERT_END_DT = "CERT_END_DT";
    public  final String MID_CERT_END_DT = "MID_CERT_END_DT";
    public  final String CERT_BEGIN_DT = "CERT_BEGIN_DT";
    public  final String DESPOSITION_CERT_BEGIN_DT = "DESPOSITION_CERT_BEGIN_DT";
    public  final String NEW_CERT_APPL_DT = "NEW_CERT_APPL_DT";
    public  final String PRGM_REQ_APP_DT = "PRGM_REQ_APP_DT";
    public  final String PAY_BEG_DT = "PAY_BEG_DT";
    public  final String INTAKE = "IN";
    public  final String PERIODIC_REVIEW = "PR";
    public  final String DISP_ELIG_BEG_DT_KEY = "DISP_ELIG_BEG_DT";
	private EnumProgramCategory programCategories=EnumProgramCategory.NULL;
	private boolean previouslyAuthorizedIsExpeditedSw;
	

	private boolean waitlistScreeningSw;
	private double employmentAmt;
	private double childSupportReceivedAmt;
	private double socialSecurityAmt;
	private double benefitsAmt;
	private double otherIncomeAmt;
	private double otherDeductionAmt;
	private double childSupportDedAmt;
	private EnumRunMode runMode = EnumRunMode.ONLINE;
	private Date biCutOffDt;
	private Date liheapCutOffDt;
	private Map statusOfPrevEdm;
	private Map statusOfCurrEdm;
	private Map statusOfCurrIndvEdm;
	private Date negativeActionDate;
	private boolean protectivePayeeInf = false;
	//CCAP 
	private boolean receivingAssetForCCAP;

	//private EnumTriggerCo triggerCode = EnumTriggerCode.NULL;
	private String progRescInd;
	private EnumProgram programCd = EnumProgram.NULL;
	private Date eligibilityDeterminationMonth;
	private Date progStsDt;
	private Date pgMaxMnth;
	private Date disposition_elig_beg_dt;
	private boolean adverseActionAllowed;
	private Date snapETFormSentDate;
	private Date snapETForSignedDate;
	private EnumInterviewMode interviewMode = EnumInterviewMode.NULL;
	private Date interviewDate;
	private Date paymentBegDt;
	private Date recertduedate;    
	private boolean benefitIssued;
	private boolean benefitsIssuedEDM = false;
	private boolean TANFBenefitTimelyReported = false;
	private boolean isReapplication =false;
	private Date certificationEndDt;
	private Date runFromDate;
	private Date reviewStartDate;
	private boolean reviewStartedForAnyProgInCase = false;
	private String reviewPendingCd;
	private boolean caseDataChangePresent = false;
	private boolean isTaskActive = false;
	private Date minCSCD;
	private Date maxVerfDate;
	private Date minProgramDate;
    private boolean isTaskSustainedBenefits = false;
    private boolean isTaskApprovedBenefits = false;
    private boolean isSameDayIssuanceTrigger = false;
    private Date task380StartDate;
    //CR-676
    private boolean receiveReviewFormsNotices = false; 
    private Date authRepIDVerfDate;
	private boolean migrantOrSeasonalFarmWorker;
	private boolean postponedVerificationProvided;
	private boolean previousExpedited;
	private boolean hasAuthRep=false;
	private EnumAppSubmittedBy appSubmittedBy = EnumAppSubmittedBy.NULL;
	
	public boolean isHasAuthRep() {
		return hasAuthRep;
	}

	public void setHasAuthRep(boolean hasAuthRep) {
		this.hasAuthRep = hasAuthRep;
	}
    
	public boolean isTaskSustainedBenefits() {
		return isTaskSustainedBenefits;
	}

	public void setTaskSustainedBenefits(boolean isTaskSustainedBenefits) {
		this.isTaskSustainedBenefits = isTaskSustainedBenefits;
	}
	
	public boolean isTaskApprovedBenefits() {
		return isTaskApprovedBenefits;
	}

	public void setTaskApprovedBenefits(boolean isTaskApprovedBenefits) {
		this.isTaskApprovedBenefits = isTaskApprovedBenefits;
	}
	private double previousCountableTANFGrantAmount=0.0;
	
	public double getPreviousCountableTANFGrantAmount() {
		return previousCountableTANFGrantAmount;
	}

	public void setPreviousCountableTANFGrantAmount(
			double previousCountableTANFGrantAmount) {
		this.previousCountableTANFGrantAmount = previousCountableTANFGrantAmount;
	}
	
	public boolean isReapplication() {
		return isReapplication;
	}

	public void setReapplication(boolean isReapplication) {
		this.isReapplication = isReapplication;
	}

	public String getReviewPendingCd() {
		return reviewPendingCd;
	}

	public void setReviewPendingCd(String reviewPendingCd) {
		this.reviewPendingCd = reviewPendingCd;
	}
	
	public Date getReviewStartDate() {
		return reviewStartDate;
	}

	public void setReviewStartDate(Date reviewStartDate) {
		this.reviewStartDate = reviewStartDate;
	}

	private Date reviewPacketRecvdDate;
	
    public EnumYesNo requestingKinship = EnumYesNo.NULL;

	public Date getReviewPacketRecvdDate() {
		return reviewPacketRecvdDate;
	}

	public void setReviewPacketRecvdDate(Date reviewPacketRecvdDate) {
		this.reviewPacketRecvdDate = reviewPacketRecvdDate;
	}
	private Date estimatedEligibilityEnddt;
	

	public Date getEstimatedEligibilityEnddt() {
		return estimatedEligibilityEnddt;
	}

	public void setEstimatedEligibilityEnddt(Date estimatedEligibilityEnddt) {
		this.estimatedEligibilityEnddt = estimatedEligibilityEnddt;
	}


	private boolean followUpNoticeSent;
	private Date workingDayMonthAfterReviewSentDt;
	private Date reviewDueDate;
	private Date formReceiveDate;
	private Date formSentDate;
	private EnumInterviewStatus interviewStatus = EnumInterviewStatus.NULL;	
	private Date verificationDueDate;
	private boolean immediatelyPreviousCertificationDenied;
	private boolean previouslyPostponedVerificationsProvided;
	private boolean alreadyReceivedLumpSum = false;
	
	public boolean isAlreadyReceivedLumpSum() {
		return alreadyReceivedLumpSum;
	}

	public void setAlreadyReceivedLumpSum(boolean alreadyReceivedLumpSum) {
		this.alreadyReceivedLumpSum = alreadyReceivedLumpSum;
	}

	public EnumInterviewStatus getInterviewStatus() {
		return interviewStatus;
	}

	public void setInterviewStatus(EnumInterviewStatus interviewStatus) {
		this.interviewStatus = interviewStatus;
	}

	public Date getCertificationEndDt() {
		return certificationEndDt;
	}

	public void setCertificationEndDt(Date certificationEndDt) {
		this.certificationEndDt = certificationEndDt;
	}

	public boolean isFollowUpNoticeSent() {
		return followUpNoticeSent;
	}

	public void setFollowUpNoticeSent(boolean followUpNoticeSent) {
		this.followUpNoticeSent = followUpNoticeSent;
	}
	public Date getWorkingDayMonthAfterReviewSentDt() {
		return workingDayMonthAfterReviewSentDt;
	}

	public void setWorkingDayMonthAfterReviewSentDt(
			Date workingDayMonthAfterReviewSentDt) {
		this.workingDayMonthAfterReviewSentDt = workingDayMonthAfterReviewSentDt;
	}

	public Date getReviewDueDate() {
		return reviewDueDate;
	}

	public void setReviewDueDate(Date reviewDueDate) {
		this.reviewDueDate = reviewDueDate;
	}

	public Date getFormReceiveDate() {
		return formReceiveDate;
	}

	public void setFormReceiveDate(Date formReceiveDate) {
		this.formReceiveDate = formReceiveDate;
	}

	public Date getFormSentDate() {
		return formSentDate;
	}

	public void setFormSentDate(Date formSentDate) {
		this.formSentDate = formSentDate;
	}

	public Date getRecertduedate() {
		return recertduedate;
	}
	public void setRecertduedate(Date recertduedate) {
		this.recertduedate = recertduedate;
	}


	public Date getPaymentBegDt() {
		return paymentBegDt;
	}

	public void setPaymentBegDt(Date paymentBegDt) {
		this.paymentBegDt = paymentBegDt;
	}

	public boolean isBenefitIssued() {
		return benefitIssued;
	}

	public void setBenefitIssued(boolean benefitIssued) {
		this.benefitIssued = benefitIssued;
	}
	
	public EnumInterviewMode getInterviewMode() {
		return interviewMode;
	}

	public void setInterviewMode(EnumInterviewMode interviewMode) {
		this.interviewMode = interviewMode;
	}
	public Date getInterviewDate() {
		return interviewDate;
	}

	public void setInterviewDate(Date interviewDate) {
		this.interviewDate = interviewDate;
	} 
	public Date getSnapETFormSentDate() {
		return snapETFormSentDate;
	}

	public void setSnapETFormSentDate(Date snapETFormSentDate) {
		this.snapETFormSentDate = snapETFormSentDate;
	}

	public Date getSnapETForSignedDate() {
		return snapETForSignedDate;
	}

	public void setSnapETForSignedDate(Date snapETForSignedDate) {
		this.snapETForSignedDate = snapETForSignedDate;
	}
	
	public Date getDisposition_elig_beg_dt() {
		return disposition_elig_beg_dt;
	}

	public void setDisposition_elig_beg_dt(Date disposition_elig_beg_dt) {
		this.disposition_elig_beg_dt = disposition_elig_beg_dt;
	}

	public void setBiCutOffDt(Date biCutOffDt) {
		this.biCutOffDt = biCutOffDt;
	}

	public Date getBiCutOffDt() {
		return biCutOffDt;
	}

	public void setWaitlistScreeningSw(boolean waitlistScreeningSw) {
		this.waitlistScreeningSw = waitlistScreeningSw;
	}

	public boolean isWaitlistScreeningSw() {
		return waitlistScreeningSw;
	}

	public double getEmploymentAmt() {
		return employmentAmt;
	}

	public void setEmploymentAmt(double employmentAmt) {
		this.employmentAmt = employmentAmt;
	}

	public double getChildSupportReceivedAmt() {
		return childSupportReceivedAmt;
	}

	public void setChildSupportReceivedAmt(double childSupportReceivedAmt) {
		this.childSupportReceivedAmt = childSupportReceivedAmt;
	}

	public double getSocialSecurityAmt() {
		return socialSecurityAmt;
	}

	public void setSocialSecurityAmt(double socialSecurityAmt) {
		this.socialSecurityAmt = socialSecurityAmt;
	}

	public double getBenefitsAmt() {
		return benefitsAmt;
	}

	public void setBenefitsAmt(double benefitsAmt) {
		this.benefitsAmt = benefitsAmt;
	}

	public double getOtherIncomeAmt() {
		return otherIncomeAmt;
	}

	public void setOtherIncomeAmt(double otherIncomeAmt) {
		this.otherIncomeAmt = otherIncomeAmt;
	}

	public double getOtherDeductionAmt() {
		return otherDeductionAmt;
	}

	public void setOtherDeductionAmt(double otherDeductionAmt) {
		this.otherDeductionAmt = otherDeductionAmt;
	}

	public double getChildSupportDedAmt() {
		return childSupportDedAmt;
	}

	public void setChildSupportDedAmt(double childSupportDedAmt) {
		this.childSupportDedAmt = childSupportDedAmt;
	}

	public void setProgramCategories(EnumProgramCategory programCategories) {
		this.programCategories = programCategories;
	}

	public EnumProgramCategory getProgramCategories() {
		return programCategories;
	}	
	
	public Map getStatusOfPrevEdm() {
		return statusOfPrevEdm;
	}

	public void setStatusOfPrevEdm(Map statusOfPrevEdm) {
		this.statusOfPrevEdm = statusOfPrevEdm;
	}

	/**********************Ended for Child Care*******************/
	/*FINANCIAL MODULE*/
	//By Shivani Bhat
	private boolean overrideRecoupment;
	private boolean dataNotCollected;
	private String addrCountyCd;
	
	private ArrayList<Medical_Bill>	medicalBillList= new ArrayList<Medical_Bill>();

	private ArrayList<Used_Bills> usedBillList= new ArrayList<Used_Bills>();
	public void setMedicalBillList(ArrayList<Medical_Bill> medicalBillList) {
		this.medicalBillList = medicalBillList;
	}

	public ArrayList<Medical_Bill> getMedicalBillList() {
		return medicalBillList;
	}
	
	public void setAddrCountyCd(String addrCountyCd) {
		this.addrCountyCd = addrCountyCd;
	}

	public String getAddrCountyCd() {
		return addrCountyCd;
	}

	public void createUsed_Bills(Medical_Bill mb,FilingUnit fu,int check){
		if(mb!=null && fu!=null){
			Used_Bills ub=new Used_Bills();
			ub.setSequenceNumber(mb.getSequenceNumber());
			ub.setFilingUnitNumber(fu.getFilingUnitNum());
			ub.setUsedMonth(fu.getEligibilityDeterminationMonth());
			
			if(check==1){
				ub.setUsedAmt(0);
				ub.setDhsPaidSw(true);
				ub.setDhsPaidAmt(mb.getOutsBillAmt());
			}
			else if(check==2){
				ub.setUsedAmt(mb.getRemainingBillAmt());
				ub.setTempFilingUnitTraceID(fu.getFilingunitTraceID());
				ub.setProviderNotifySw(true);
				
			}
			else if(check==3){
				ub.setUsedAmt(mb.getRemainingBillAmt());
				ub.setTempFilingUnitTraceID(fu.getFilingunitTraceID());
				
			}
			else if (check==4)
			{
				ub.setUsedAmt(0);
				ub.setDhsPaidSw(true);
				ub.setDhsPaidAmt(mb.getCntblBillAmt());
				ub.setTempFilingUnitTraceID(fu.getFilingunitTraceID());
			}
			if (null != this.usedBillsList
					&& !this.usedBillsList.isEmpty()) {
				this.usedBillsList.add(ub);
			}
			else{
				this.usedBillsList=new ArrayList<Used_Bills>();
				this.usedBillsList.add(ub);

			}
		}
	}
	
	//Ended by Shivani Bhat
	
	

	// by shivani bhat for 1050_620
	private Date caseConvDt;
	private Date caseConvDtMinusOneMonth;
	private boolean dummyMEcreated ;
	
	private String activityType ;
	
	private boolean clientMcReq ;
	private EnumTypeOfAssistance mcPrevApproved ;
	
	private boolean fmaReactivated;
	private boolean tp08Exists;
	//by shivani bhat
 
	private EnumPriorMedicaidCd priorMedicaidCd= EnumPriorMedicaidCd.NULL;
	private String ruleTrace;
	private boolean buildRCAFilingUnit = true;
	private String programProcessCode;
	private boolean caseConverted = false;
	private Date cashConversionDate;
	// private boolean cashConverted = false;
	private boolean denyApplication = false;
	private ArrayList<FilingUnit> filingUnitList = new ArrayList<FilingUnit>();
	private boolean firstEligibleChildProcessed = false;
	private ArrayList<Person> personList = new ArrayList<Person>();
	private EnumProgramStatus programStatusCode = EnumProgramStatus.NULL;
	private Date withdrawDate;
	private EnumYesNo withdrawSwitch = EnumYesNo.NULL;
	private EnumWithdrawReasonCode withdrawlReason = EnumWithdrawReasonCode.NULL;
	private Date applicationDate;
	private Date applicationReceivedDate;
	private ReferenceValue refValue;
	private boolean meetIDRequirement;
	private boolean nonETCounty;
	private boolean waiversw;
	private boolean bestIndSw;
	private Date runDate;
	private boolean dummyTANFcreated = false;
	private boolean dummyRCAcreated = false;
	private EnumYesNo casemode;
	private EnumYesNo livingOnReservation = EnumYesNo.NULL;
	
	private String medicaidTypeCd = new String();
	
	
	private boolean priorMonthInAppDtYear;
	private boolean TMAGroupReq;
	 
	// Resources
	private double tempOriginalCashValueAmt;
	private double tempClientContributionAmt;
	private double tempCountablePercentage;
	private boolean tempTamperedWithSw;
	private boolean praCreateSw;
	/**
	 * @return the praCreateSw
	 */
	public boolean isPraCreateSw() {
		return praCreateSw;
	}

	/**
	 * @param praCreateSw the praCreateSw to set
	 */
	public void setPraCreateSw(boolean praCreateSw) {
		this.praCreateSw = praCreateSw;
	}

	// income
	private boolean incomeDataNotCollected;
	

	private double caseNumber;

	// SNAP Income Budget
	private long numberOfDaysInApplicationMonth;
	// private Date reApplicationDate;
	private double applicationRecertificationFpil130LimitAmt;
	// private boolean overrideMode;
	private int dayOfAppMnth;

	// TANF Income Budget
	private long startDateOfAid;
	private Date edbcClosureDt;

	// Missing Verifications
	private ArrayList<CaseProfileVCL> caseProfileVCLList = new ArrayList<CaseProfileVCL>();
	private ArrayList<VerificationPgm> verificationPgmList = new ArrayList<VerificationPgm>();

	private Date unabletolocateDt;
		private Date tempDate;
	
	//MA
	private boolean unabletolocate=false;
	private boolean form3503SentDtSw=false;
	private boolean failedtoprovideinfo=false;
	private boolean failedtoprovideinfoTanF=false;
	private ArrayList<ChildSupportGoodCause> childsupportgoodcauses = new ArrayList<ChildSupportGoodCause>();
	
	private boolean dummyFMAcreated;
	
	//Added by Manohar FU
	private ArrayList<Ed_inactive_toa> Ed_inactive_toas=new ArrayList<Ed_inactive_toa>();
	
	private long callingIndvId;

	public long getCallingIndvId() {
		return callingIndvId;
	}

	private boolean instMeBuilt;
	private boolean AMPEligible;
	private String medicaidTypePrevApproved;
	/* Added by Sumona */
//	private boolean DataNotCollected;
	private Date triggerSpanBegDt;
	private Date triggerSpanEndDt;
	private boolean isManualCascade = false;
	private EnumTriggerCode triggerCode=EnumTriggerCode.NULL;;
	
	public EnumTriggerCode getTriggerCode() {
		return triggerCode;
	}

	public void setTriggerCode(EnumTriggerCode triggerCode) {
		this.triggerCode = triggerCode;
	}

	public Date getTriggerSpanBegDt() {
		return triggerSpanBegDt;
	}

	public void setTriggerSpanBegDt(Date triggerSpanBegDt) {
		this.triggerSpanBegDt = triggerSpanBegDt;
	}

	public Date getTriggerSpanEndDt() {
		return triggerSpanEndDt;
	}

	public void setTriggerSpanEndDt(Date triggerSpanEndDt) {
		this.triggerSpanEndDt = triggerSpanEndDt;
	}

	public boolean isManualCascade() {
		return isManualCascade;
	}

	public void setManualCascade(boolean isManualCascade) {
		this.isManualCascade = isManualCascade;
	}
	public boolean isItemPendingForVerificationForAnyFilingUnit(
			CaseProfileVCL caseProfileVCL) {
		boolean itemPending = false;
		if (caseProfileVCL != null) {
			for (VerificationPgm verificationPgm : verificationPgmList) {
				if (verificationPgm != null) {
					if (verificationPgm.getVclIndvId() == caseProfileVCL
							.getCaseProfileIndvId()
							&& verificationPgm.getVclCode().equals(
									caseProfileVCL.getVclCode())
							&& verificationPgm.getVclDetailRef()
									.equalsIgnoreCase(
											caseProfileVCL
													.getCaseProfileDetailRef())) {
						itemPending = true;
						break;
					}
				}
			}
		}
		return itemPending;
	}

	public boolean isResourcePendingForVerificationForAnyFilingUnit(
			Resource resource) {
		boolean resourcePending = false;
		if (resource != null) {
			for (VerificationPgm verificationPgm : verificationPgmList) {
				if (verificationPgm != null) {
					if (verificationPgm.getVclIndvId() == resource
							.getResourcePersonID()
							&& verificationPgm.getVclCode().equals(
									resource.getVclCode())
							&& verificationPgm.getVclDetailRef()
									.equalsIgnoreCase(
											resource.getRscDetailRef())) {
						resourcePending = true;
						break;
					}
				}
			}
		}
		return resourcePending;
	}
	/**
	 *Copies the Eligibility Dates from the previously authorised record to the new EDG
	 * @param unauthEdEligCargo EdEligibilityCargo
	 * @throws EDBCException
	 */
	private void copyPrevEdEligDates(FilingUnit filingUnit, EnumTypeOfAssistance toa) {
    try {
		//    Map prevEDMStatusMap = new HashMap<EnumTypeOfAssistance, HashMap<String, HashMap<String, String>>>();
		Map<EnumTypeOfAssistance, HashMap<String, HashMap<String, String>>> prevEDMStatusMap = this.statusOfPrevEdm;
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date eligBegDt = null;
		Date estEligEndDt = null;
		Date semiAnnualReviewDt = null;
		String simplifiedReprtnGrpCd = null;
		Date certEndDt = null;
		Date midCertEndDt = null;
		Date certBgnDt = null;
		Date dispostnCertBgnDt = null;
		Date newCertApplDt = null;
		Date prgmReqAppDt = null;
		Date payBegDt = null;
		Date dispositnEligBegDt = null;
		boolean authEdEligCargoPresnt = false;
		boolean previousEligCarogPresent = false;
		String authCgStatusCd = "";
		if (null != prevEDMStatusMap
				&& prevEDMStatusMap.containsKey(toa)) {
			Map<String, HashMap<String, String>> cargoTypeMap = prevEDMStatusMap
					.get(toa);
			Map<String, String> statusMap = null;
			if (null != cargoTypeMap
					&& cargoTypeMap
							.containsKey("PreviousOngoingAuthorizedRecord")) {
				statusMap = cargoTypeMap.get("PreviousOngoingAuthorizedRecord");
				authEdEligCargoPresnt = true;
			} else if (null != cargoTypeMap
					&& cargoTypeMap.containsKey("PreviousEDMCurrentRun")) {
				statusMap = cargoTypeMap.get("PreviousEDMCurrentRun");
				previousEligCarogPresent = true;
			}
			if (!authEdEligCargoPresnt && previousEligCarogPresent) {
				//defect 93127 - if there's no auth record, check if we have prev processed record.
				//authEdEligCargo = this.getPrevProcessedEdgRecord(unauthEdEligCargo);
				if (null != statusMap.get(DISP_ELIG_BEG_DT_KEY)) {
					dispositnEligBegDt = dateFormat.parse(statusMap
							.get(DISP_ELIG_BEG_DT_KEY));
					this.disposition_elig_beg_dt=dispositnEligBegDt;
				}
				return;
			}

			if (null != statusMap.get("CG_STATUS_CD")) {
				authCgStatusCd = statusMap.get("CG_STATUS_CD");
			}

			if (null != statusMap.get(ELIGIBILITY_BEGIN_DT)) {
				eligBegDt = dateFormat.parse(statusMap
						.get(ELIGIBILITY_BEGIN_DT));
			}
			if (null != statusMap.get(ESTIMATED_ELIGIBILITY_END_DT)) {
				estEligEndDt = dateFormat.parse(statusMap
						.get(ESTIMATED_ELIGIBILITY_END_DT));
				this.estimatedEligibilityEnddt=estEligEndDt;
			}
			if (null != statusMap.get(SEMI_ANNUAL_REVIEW_DUE_DT)) {
				semiAnnualReviewDt = dateFormat.parse(statusMap
						.get(SEMI_ANNUAL_REVIEW_DUE_DT));
			}
			if (null != statusMap.get(SIMPLIFIED_REPORTN_GRP_CD)) {
				simplifiedReprtnGrpCd = statusMap
						.get(SIMPLIFIED_REPORTN_GRP_CD);
			}
			else{
				simplifiedReprtnGrpCd= EnumSimplifiedReporting.SR3.toString();
			}
			if (null != statusMap.get(CERT_END_DT)) {
				certEndDt = dateFormat.parse(statusMap.get(CERT_END_DT));
			}
			if (null != statusMap.get(MID_CERT_END_DT)) {
				midCertEndDt = dateFormat.parse(statusMap.get(MID_CERT_END_DT));
			}
			if (null != statusMap.get(CERT_BEGIN_DT)) {
				certBgnDt = dateFormat.parse(statusMap.get(CERT_BEGIN_DT));
			}
			if (null != statusMap.get(DESPOSITION_CERT_BEGIN_DT)) {
				dispostnCertBgnDt = dateFormat.parse(statusMap
						.get(DESPOSITION_CERT_BEGIN_DT));
			}
			if (null != statusMap.get(NEW_CERT_APPL_DT)) {
				newCertApplDt = dateFormat.parse(statusMap
						.get(NEW_CERT_APPL_DT));
			}
			if (null != statusMap.get(PRGM_REQ_APP_DT)) {
				prgmReqAppDt = dateFormat.parse(statusMap.get(PRGM_REQ_APP_DT));
			}
			if (null != statusMap.get(PAY_BEG_DT)) {
				payBegDt = dateFormat.parse(statusMap.get(PAY_BEG_DT));
			}
			if (null != statusMap.get(DISP_ELIG_BEG_DT_KEY)) {
				dispositnEligBegDt = dateFormat.parse(statusMap
						.get(DISP_ELIG_BEG_DT_KEY));
				this.disposition_elig_beg_dt=dispositnEligBegDt;
			}

			if (authEdEligCargoPresnt) {
				// Check for TN status is added for defect 105848
				if (authCgStatusCd != null && authCgStatusCd.equals("TN")) {
					if (EnumYesNo.Y
							.equals(DateUtilities.compareDatesEqualTo(
									DateUtilities
											.getFirstDayOfMonth(this.eligibilityDeterminationMonth),
									payBegDt))) {

						filingUnit.setEligibilityBeginDt(eligBegDt);
						filingUnit.setEligibilitybegdt(eligBegDt);

						// BRGUS00034735
						if (dispositnEligBegDt != null) {
							filingUnit
									.setDispositionEligBegDt(dispositnEligBegDt);
						}
						// end BRGUS00034735
						//CCB 129329 - c/f ProgReqAppDt
						filingUnit.setProgReqAppDt(prgmReqAppDt);
						//copyGroupNumIfCMA(authEdEligCargo,unauthEdEligCargo);

						if (!(this.activityType.equals(PERIODIC_REVIEW) || this.activityType
								.equals(INTAKE))) {

							if (estEligEndDt != null) {
								if (EnumYesNo.Y.equals(DateUtilities
										.compareDatesGreaterThanEqualTo(
												estEligEndDt, payBegDt))
										|| (isSemiAnnualTermination(certEndDt,
												semiAnnualReviewDt))) {
									filingUnit
											.setEstimatedeligenddt(estEligEndDt);
									filingUnit.setCertBeginDt(certBgnDt);
									// BRGUS00034735
									if (dispostnCertBgnDt != null) {
										filingUnit
												.setDispositionCertBegDt(dispostnCertBgnDt);
									}
									if (newCertApplDt != null) {
										filingUnit
												.setNewCertApplDt(newCertApplDt);
									}
									// end BRGUS00034735
									// BRGUS00144177 to populate the cert_end_date for reactivated FS.
									if (certEndDt != null) {
										filingUnit.setCertEndDt(certEndDt);
									}
									if (midCertEndDt != null) {
										//filingUnit.setMidCertEndDt(midCertEndDt);
									}
									if (semiAnnualReviewDt != null) {
										filingUnit
												.setSemiAnnualReviewDate(semiAnnualReviewDt);
									}
								}

							}

							filingUnit
							.setSimplifiedReportingTypeCode(EnumSimplifiedReporting
									.valueOf(simplifiedReprtnGrpCd));

							return;
						}
						return;
					} else { // payment dates not equal.
						//first condition for break in eligibility.Eg: 3/2004 'A' TN record opendated exists
						//and now 6/2004 'AP' segment has been built.
						if (!previousEligCarogPresent) {
							return;
						} else {
							filingUnit.setEligibilitybegdt(eligBegDt);
							filingUnit.setEligibilityBeginDt(eligBegDt);

							//CCB 129329 - c/f progReqAppDt
							filingUnit.setProgReqAppDt(prgmReqAppDt);
							if (estEligEndDt != null) {
								if (EnumYesNo.Y.equals(DateUtilities
										.compareDatesGreaterThanEqualTo(
												estEligEndDt, payBegDt))) {
									filingUnit
											.setEstimatedeligenddt(estEligEndDt);
									filingUnit.setCertBeginDt(certBgnDt);
									// BRGUS00034735
									if (dispostnCertBgnDt != null) {
										filingUnit
												.setDispositionCertBegDt(dispostnCertBgnDt);
									}
									if (newCertApplDt != null) {
										filingUnit
												.setNewCertApplDt(newCertApplDt);
									}
									// end BRGUS00034735
								}
							}
							//copyGroupNumIfCMA(authEdEligCargo,unauthEdEligCargo);
							return;
						}
					}
				}
			}

			filingUnit.setEligibilitybegdt(eligBegDt);
			filingUnit.setEligibilityBeginDt(eligBegDt);

			//		 BRGUS00034735
			if (dispositnEligBegDt != null) {
				filingUnit.setDispositionEligBegDt(dispositnEligBegDt);
			}
			// end BRGUS00034735
			filingUnit.setProgReqAppDt(prgmReqAppDt);
			filingUnit.setEstimatedeligenddt(estEligEndDt);
			//copyGroupNumIfCMA(authEdEligCargo,unauthEdEligCargo);

			// CMA Cert Period changes	- 9/16/03
			filingUnit.setCertBeginDt(certBgnDt);
			//		 BRGUS00034735
			if (dispostnCertBgnDt != null) {
				filingUnit.setDispositionCertBegDt(dispostnCertBgnDt);
			}
			if (newCertApplDt != null) {
				filingUnit.setNewCertApplDt(newCertApplDt);
			}
			// end BRGUS00034735

			// wipe off est elig end dt if FS - PR
			// defect 156574 c). CertBegDt need to be carried forward from last auth records for CC program like FS
			if (this.activityType.equals(PERIODIC_REVIEW) && (this.programCd.equals(EnumProgram.valueOf("FS"))
					|| this.programCd.equals(EnumProgram.valueOf("CD")))) {
				filingUnit.setEstimatedeligenddt(null);
				filingUnit.setCertBeginDt(null);

			}

			/*
			Timestamp authSpReviewDt = authEdEligCargo.getSpReviewDt();
			Timestamp unauthPayBegDt = unauthEdEligCargo.getPaymentBegDt();

			if (authSpReviewDt != null
				&& DateComparisons.dateGTEQDateInTimestamp(authSpReviewDt, unauthPayBegDt))	{
				filingUnit.setSpReviewDt(authSpReviewDt);
				String authSpReviewCd = authEdEligCargo.getSpReviewCd();
				filingUnit.setSpReviewCd(authSpReviewCd);
			}*/

			// For streamline reporting
			// If FS EDG is not in Intake or Periodic review then copy from last auth record.
			//<BRGUS00098342><kucherlapatis><05/23/2008><Java Fix To Set Mid Cert End Date Only When There Is An Authorized OnGoing Record>
			if (this.programCd.equals(EnumProgram.valueOf("FS"))) {
				if (!this.activityType.equals(INTAKE)
						&& !this.activityType.equals(PERIODIC_REVIEW)) {
					filingUnit
							.setSimplifiedReportingTypeCode(EnumSimplifiedReporting
									.valueOf(simplifiedReprtnGrpCd));
					// BRGUS00031758
					if (certEndDt != null) {
						filingUnit.setCertEndDt(certEndDt);
					}
					if (midCertEndDt != null) {
						//	filingUnit.setMidCertEndDt(midCertEndDt);
					}
					//BRGUS00069324
					if (semiAnnualReviewDt != null) {
						filingUnit.setSemiAnnualReviewDate(semiAnnualReviewDt);
					}

					// end BRGUS00031758
				}
			}
		}
		//For defect# 129421
		//	if (unauthEdEligCargo.getDelayRsnCd() == null) {
		//		filingUnit.setDelayRsnCd(authEdEligCargo.getDelayRsnCd());
		//		filingUnit.setDelayRsnDt(authEdEligCargo.getDelayRsnDt());
		//	}
		//For defect# 129421
	} catch (Exception e) {
		e.printStackTrace();
	}

	}
	private boolean isSemiAnnualTermination(Date certEndDt,Date semiAnnualReviewDt) {
		boolean semiAnnualtermination = false;
		if (this.programCd.equals(EnumProgram.valueOf("FS"))
				&& certEndDt != null) {
			if (EnumYesNo.Y.equals(DateUtilities.compareDatesGreaterThanEqualTo(certEndDt,DateUtilities.getFirstDayOfMonth(this.eligibilityDeterminationMonth))) 
				&& semiAnnualReviewDt != null) {
			
				semiAnnualtermination = true;
			}
		}
		return semiAnnualtermination;
	}
	public boolean isNonFinPendingForVerificationForAnyFilingUnit(
			NonFinData nonFin) {
		boolean nonFinPending = false;
		if (nonFin != null) {
			for (VerificationPgm verificationPgm : verificationPgmList) {
				if (verificationPgm != null) {
					if (verificationPgm.getVclIndvId() == nonFin
							.getNonFinIndvId()
							&& verificationPgm.getVclCode().equals(
									nonFin.getVclCode())
							&& verificationPgm.getVclDetailRef()
									.equalsIgnoreCase(
											nonFin.getNonFinDetailRef())) {
						nonFinPending = true;
						break;
					}
				}
			}
		}
		return nonFinPending;
	}

	public boolean isExpensePaymentPendingForVerificationForAnyFilingUnit(
			ExpensePayment expensePayment) {
		boolean expensePaymentPending = false;
		if (expensePayment != null) {
			for (VerificationPgm verificationPgm : verificationPgmList) {
				if (verificationPgm != null) {
					if (verificationPgm.getVclIndvId() == expensePayment
							.getExpensePaymentIndvId()
							&& verificationPgm.getVclCode().equals(
									expensePayment.getVclCode())
							&& verificationPgm
									.getVclDetailRef()
									.equalsIgnoreCase(
											expensePayment
													.getExpensePaymentDetailRef())) {
						expensePaymentPending = true;
						break;
					}
				}
			}
		}
		return expensePaymentPending;
	}

	public boolean isExpensePendingForVerificationForAnyFilingUnit(
			Expense expense) {
		boolean expensePending = false;
		if (expense != null) {
			for (VerificationPgm verificationPgm : verificationPgmList) {
				if (verificationPgm != null) {
					if (verificationPgm.getVclIndvId() == expense
							.getExpenseIndvId()
							&& verificationPgm.getVclCode().equals(
									expense.getVclCode())
							&& verificationPgm.getVclDetailRef()
									.equalsIgnoreCase(
											expense.getExpenseDetailRef())) {
						expensePending = true;
						break;
					}
				}
			}
		}
		return expensePending;
	}

	public boolean isIncomePayExpensePendingForVerificationForAnyFilingUnit(
			IncomePayExpense incomePayExpense) {
		boolean incomePayExpensePending = false;
		if (incomePayExpense != null) {
			for (VerificationPgm verificationPgm : verificationPgmList) {
				if (verificationPgm != null) {
					if (verificationPgm.getVclIndvId() == incomePayExpense
							.getIncomePayExpenseIndvId()
							&& verificationPgm.getVclCode().equals(
									incomePayExpense.getVclCode())
							&& verificationPgm
									.getVclDetailRef()
									.equalsIgnoreCase(
											incomePayExpense
													.getIncomePayExpenseDetailRef())) {
						incomePayExpensePending = true;
						break;
					}
				}
			}
		}
		return incomePayExpensePending;
	}

	public boolean isIncomePendingForVerificationForAnyFilingUnit(Income income) {
		boolean incomePending = false;
		if (income != null) {
			for (VerificationPgm verificationPgm : verificationPgmList) {
				if (verificationPgm != null) {
					if (verificationPgm.getVclIndvId() == income
							.getIncomeIndvId()
							&& verificationPgm.getVclCode().equals(
									income.getVclCode())
							&& verificationPgm.getVclDetailRef()
									.equalsIgnoreCase(
											income.getIncomeDetailRef())) {
						incomePending = true;
						break;
					}
				}
			}
		}
		return incomePending;
	}

	public void initialize6000_783ForAllPendingItemsForVerification(
			CaseProfileVCL caseProfileVCL) {
		if (caseProfileVCL != null) {
			for (VerificationPgm verificationPgm : verificationPgmList) {
				if (verificationPgm != null) {
					if (verificationPgm.getVclIndvId() == caseProfileVCL
							.getCaseProfileIndvId()
							&& verificationPgm.getVclCode().equals(
									caseProfileVCL.getVclCode())
							&& verificationPgm.getVclDetailRef()
									.equalsIgnoreCase(
											caseProfileVCL
													.getCaseProfileDetailRef())) {
						verificationPgm.var6000_783 = true;
					}
				}
			}
		}
	}

	public void initialize6000_783ForAllPendingItemsForVerificationForResource(
			Resource resource) {
		if (resource != null) {
			for (VerificationPgm verificationPgm : verificationPgmList) {
				if (verificationPgm != null) {
					if (verificationPgm.getVclIndvId() == resource
							.getResourcePersonID()
							&& verificationPgm.getVclCode().equals(
									resource.getVclCode())
							&& verificationPgm.getVclDetailRef()
									.equalsIgnoreCase(
											resource.getRscDetailRef())) {
						verificationPgm.var6000_783 = true;
					}
				}
			}
		}
	}

	public void initialize6000_783ForAllPendingItemsForVerificationForNonFin(
			NonFinData nonFin) {
		if (nonFin != null) {
			for (VerificationPgm verificationPgm : verificationPgmList) {
				if (verificationPgm != null) {
					if (verificationPgm.getVclIndvId() == nonFin
							.getNonFinIndvId()
							&& verificationPgm.getVclCode().equals(
									nonFin.getVclCode())
							&& verificationPgm.getVclDetailRef()
									.equalsIgnoreCase(
											nonFin.getNonFinDetailRef())) {
						verificationPgm.var6000_783 = true;
					}
				}
			}
		}
	}

	public void initialize6000_783ForAllPendingItemsForVerificationForExpensePayment(
			ExpensePayment expensePayment) {
		if (expensePayment != null) {
			for (VerificationPgm verificationPgm : verificationPgmList) {
				if (verificationPgm != null) {
					if (verificationPgm.getVclIndvId() == expensePayment
							.getExpensePaymentIndvId()
							&& verificationPgm.getVclCode().equals(
									expensePayment.getVclCode())
							&& verificationPgm
									.getVclDetailRef()
									.equalsIgnoreCase(
											expensePayment
													.getExpensePaymentDetailRef())) {
						verificationPgm.var6000_783 = true;
					}
				}
			}
		}
	}

	public void initialize6000_783ForAllPendingItemsForVerificationForExpense(
			Expense expense) {
		if (expense != null) {
			for (VerificationPgm verificationPgm : verificationPgmList) {
				if (verificationPgm != null) {
					if (verificationPgm.getVclIndvId() == expense
							.getExpenseIndvId()
							&& verificationPgm.getVclCode().equals(
									expense.getVclCode())
							&& verificationPgm.getVclDetailRef()
									.equalsIgnoreCase(
											expense.getExpenseDetailRef())) {
						verificationPgm.var6000_783 = true;
					}
				}
			}
		}
	}

	public void initialize6000_783ForAllPendingItemsForVerificationForIncomePayExpense(
			IncomePayExpense incomePayExpense) {
		if (incomePayExpense != null) {
			for (VerificationPgm verificationPgm : verificationPgmList) {
				if (verificationPgm != null) {
					if (verificationPgm.getVclIndvId() == incomePayExpense
							.getIncomePayExpenseIndvId()
							&& verificationPgm.getVclCode().equals(
									incomePayExpense.getVclCode())
							&& verificationPgm
									.getVclDetailRef()
									.equalsIgnoreCase(
											incomePayExpense
													.getIncomePayExpenseDetailRef())) {
						verificationPgm.var6000_783 = true;
					}
				}
			}
		}
	}

	public void initialize6000_783ForAllPendingItemsForVerificationForIncome(
			Income income) {
		if (income != null) {
			for (VerificationPgm verificationPgm : verificationPgmList) {
				if (verificationPgm != null) {
					if (verificationPgm.getVclIndvId() == income
							.getIncomeIndvId()
							&& verificationPgm.getVclCode().equals(
									income.getVclCode())
							&& verificationPgm.getVclDetailRef()
									.equalsIgnoreCase(
											income.getIncomeDetailRef())) {
						verificationPgm.var6000_783 = true;
					}
				}
			}
		}
	}
	
	
	public void childSuppCauseTrigger(ChildSupportGoodCause childSupportGoodCause){
		
		ArrayList<FilingUnitPerson> fup=this.getFilingUnitList().get(0).getFilingUnitPersonList();
		
		if(fup!=null && !fup.isEmpty()){
			
			for(FilingUnitPerson filingUnitPerson:fup){
				
				if(childSupportGoodCause.getCustparentindvid()==filingUnitPerson.getPerson().getPersonID()){
					
					filingUnitPerson.setSendCOTrigger(true);
					filingUnitPerson.setProgcd(this.getFilingUnitList().get(0).getProgram());
					
					
				}
				
				
			}
			
			
			
		}
		
		
		
		
	}

	public ArrayList<VerificationPgm> getVerificationPgmList() {
		return verificationPgmList;
	}

	public void setVerificationPgmList(
			ArrayList<VerificationPgm> verificationPgmList) {
		this.verificationPgmList = verificationPgmList;
	}

	public double getCaseNumber() {
		return caseNumber;
	}

	public void setCaseNumber(double caseNumber) {
		this.caseNumber = caseNumber;
	}

	public Date getRunDate() {
		return runDate;
	}

	public void setRunDate(Date runDate) {
		this.runDate = runDate;
	}

	public boolean isBuildRCAFilingUnit() {
		return buildRCAFilingUnit;
	}

	public void setBuildRCAFilingUnit(boolean buildRCAFilingUnit) {
		this.buildRCAFilingUnit = buildRCAFilingUnit;
	}

	public void setProgramProcessCode(String programProcessCode) {
		this.programProcessCode = programProcessCode;
	}

	public String getProgramProcessCode() {
		return programProcessCode;
	}

	public boolean isCaseConverted() {
		return caseConverted;
	}

	public void setCaseConverted(boolean caseConverted) {
		this.caseConverted = caseConverted;
	}

	public Date getCashConversionDate() {
		return cashConversionDate;
	}

	public void setCashConversionDate(Date cashConversionDate) {
		this.cashConversionDate = cashConversionDate;
	}

	/*
	 * public boolean isCashConverted() { return cashConverted; }
	 * 
	 * public void setCashConverted(boolean cashConverted) { this.cashConverted
	 * = cashConverted; }
	 */

	public boolean isDenyApplication() {
		return denyApplication;
	}

	public void setDenyApplication(boolean denyApplication) {
		this.denyApplication = denyApplication;
	}

	public ArrayList<FilingUnit> getFilingUnitList() {
		return filingUnitList;
	}

	public void setFilingUnitList(ArrayList<FilingUnit> filingUnitList) {
		this.filingUnitList = filingUnitList;
	}

	public boolean isFirstEligibleChildProcessed() {
		return firstEligibleChildProcessed;
	}

	public void setFirstEligibleChildProcessed(
			boolean firstEligibleChildProcessed) {
		this.firstEligibleChildProcessed = firstEligibleChildProcessed;
	}

	public ArrayList<Person> getPersonList() {
		return personList;
	}

	public void setPersonList(ArrayList<Person> personList) {
		this.personList = personList;
	}

	public EnumProgramStatus getProgramStatusCode() {
		return programStatusCode;
	}

	public void setProgramStatusCode(EnumProgramStatus programStatusCode) {
		this.programStatusCode = programStatusCode;
	}

	public Date getWithdrawDate() {
		return withdrawDate;
	}

	public void setWithdrawDate(Date withdrawDate) {
		this.withdrawDate = withdrawDate;
	}

	public EnumYesNo getWithdrawSwitch() {
		return withdrawSwitch;
	}

	public void setWithdrawSwitch(EnumYesNo withdrawSwitch) {
		this.withdrawSwitch = withdrawSwitch;
	}

	public void setRefValue(ReferenceValue refValue) {
		this.refValue = refValue;
	}

	public ReferenceValue getRefValue() {
		return refValue;
	}

	public Case() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Date getApplicationDate() {
		return applicationDate;
	}

	public void setApplicationDate(Date applicationDate) {
		this.applicationDate = applicationDate;
	}

	/**
	 * Determines the number of persons in a Case.
	 * 
	 * @return Number of persons.
	 */
	
	
	//*************************************Added by Pavan************************
	//financial module
	
	private int shelterArea;
	private boolean respForHeatCostOtherthanRent;
	private boolean resExHeatCost;
	
	public boolean isRespForHeatCostOtherthanRent() {
		return respForHeatCostOtherthanRent;
	}

	public void setRespForHeatCostOtherthanRent(boolean respForHeatCostOtherthanRent) {
		this.respForHeatCostOtherthanRent = respForHeatCostOtherthanRent;
	}

	public boolean isResExHeatCost() {
		return resExHeatCost;
	}

	public void setResExHeatCost(boolean resExHeatCost) {
		this.resExHeatCost = resExHeatCost;
	}

	public void setShelterArea(int shelterArea) {
		this.shelterArea = shelterArea;
	}

	public int getShelterArea() {
		return shelterArea;
	}
	
	private ArrayList<Used_Bills> usedBillsList;
	
	public void createUsedBills(Medical_Bill mb,FilingUnit fu,int check){
		if(mb!=null && fu!=null){
			Used_Bills ub=new Used_Bills();
			ub.setSequenceNumber(mb.getSequenceNumber());
			ub.setFilingUnitNumber(fu.getFilingUnitNum());
			ub.setUsedAmt(mb.getRemainingBillAmt());
			ub.setUsedMonth(fu.getEligibilityDeterminationMonth());
			ub.setTempFilingUnitTraceID(fu.getFilingunitTraceID());
			if(check==1){
				ub.setProviderNotifySw(true);
			}
			else if(check==2){
				ub.setDhsPaidSw(true);
				ub.setUsedAmt(0);
				ub.setDhsPaidAmt(mb.getRemainingBillAmt());
			}
			else if(check==3){
				ub.setDhsPaidSw(true);
				ub.setUsedAmt(0);
				ub.setDhsPaidAmt(Utilities.lesserOf(mb.getOutsBillAmt(), (mb.getCntblBillAmt()-mb.getRemainingBillAmt())));
			}
			else if(check==4){
				ub.setDhsPaidSw(true);
				ub.setUsedAmt(0);
				ub.setDhsPaidAmt(mb.getOutsBillAmt());
			}
			if (null != this.usedBillsList
					&& !this.usedBillsList.isEmpty()) {
				this.usedBillsList.add(ub);
			}
			else{
				this.usedBillsList=new ArrayList<Used_Bills>();
				this.usedBillsList.add(ub);

			}
		}
	}

	
	//FIP budgeting corrections in Perform variable level 
	public boolean var5040_671;
	public boolean var5040_660;
	public boolean var5040_670	;
	
	
	//*************************************Ended by Pavan************************
	
	public Integer personListSize() {
		return this.personList.size();
	}

	public boolean isMeetIDRequirement() {
		return meetIDRequirement;
	}

	public void setMeetIDRequirement(boolean meetIDRequirement) {
		this.meetIDRequirement = meetIDRequirement;
	}

	public boolean isNonETCounty() {
		return nonETCounty;
	}

	public void setNonETCounty(boolean nonETCounty) {
		this.nonETCounty = nonETCounty;
	}

	public boolean isWaiversw() {
		return waiversw;
	}

	public void setWaiversw(boolean waiversw) {
		this.waiversw = waiversw;
	}

	public double getTempOriginalCashValueAmt() {
		return tempOriginalCashValueAmt;
	}

	public void setTempOriginalCashValueAmt(double tempOriginalCashValueAmt) {
		this.tempOriginalCashValueAmt = tempOriginalCashValueAmt;
	}

	public double getTempClientContributionAmt() {
		return tempClientContributionAmt;
	}

	public void setTempClientContributionAmt(double tempClientContributionAmt) {
		this.tempClientContributionAmt = tempClientContributionAmt;
	}

	public double getTempCountablePercentage() {
		return tempCountablePercentage;
	}

	public void setTempCountablePercentage(double tempCountablePercentage) {
		this.tempCountablePercentage = tempCountablePercentage;
	}

	/**
	 * this method will create a new filing unit object and set the few required
	 * attributes to it as mentioned in DT 1020_630
	 * 
	 * @param filingUnitPerson
	 * @return FilingUnit
	 */
	
	public void determineSimplfiedCdFromPrvEdm(String simplifiedCd, FilingUnit fu){
	      
        if(null != simplifiedCd){

   if(simplifiedCd.equals(EnumSimplifiedReporting.SR1.toString())){
                      fu.setReviewCategory(EnumReviewCategory.SIXM);
        //edEligibilityCargo.setSimplifiedReportingGrpCd(EnumSimplifiedReporting.SR1.toString());
        }else if(simplifiedCd.equals(EnumSimplifiedReporting.SR2.toString())){
                      fu.setReviewCategory(EnumReviewCategory.SIXMI);
        //edEligibilityCargo.setSimplifiedReportingGrpCd(EnumSimplifiedReporting.SR2.toString());
        }else if(simplifiedCd.equals(EnumSimplifiedReporting.SR3.toString())){
                      fu.setReviewCategory(EnumReviewCategory.TWELVEMI);
        //edEligibilityCargo.setSimplifiedReportingGrpCd(EnumSimplifiedReporting.SR3.toString());
       }
        }
 }


	public void createRCAFilingUnit(FilingUnitPerson filingUnitPerson) {
		FilingUnit newfilingUnit = new FilingUnit();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos = null;
		ObjectInputStream ois = null;
		if (null != this.filingUnitList.get(0).getTypeOfAssistance()
				&& !this.filingUnitList.get(0).getTypeOfAssistance().equals(
						EnumTypeOfAssistance.NULL)) {
			try {
				oos = new ObjectOutputStream(baos);
				oos.writeObject(this.filingUnitList.get(0));
				ByteArrayInputStream bais = new ByteArrayInputStream(baos
						.toByteArray());
				ois = new ObjectInputStream(bais);
				newfilingUnit = (FilingUnit) ois.readObject();
				this.filingUnitList.add(newfilingUnit);

				oos.close();
				ois.close();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		} else {
			newfilingUnit = this.filingUnitList.get(0);
		}
		newfilingUnit.setProgram(EnumProgram.TF);
		newfilingUnit.setFilingUnitProcessed(true);	
		newfilingUnit.setTypeOfAssistance(EnumTypeOfAssistance.RAPC);
		newfilingUnit
				.setGroupNoticeReasonList(new ArrayList<EnumNoticeReason>());
		for (FilingUnitPerson fUnitPerson : newfilingUnit
				.getFilingUnitPersonList()) {
			fUnitPerson.setPartStatusCode(EnumPartStatusCode.NULL);
			fUnitPerson.clearNoticeReason(true);
			fUnitPerson
					.setEligibilityGroupIndicator(EnumEligibilityGroupIndicator.NULL);
			if (fUnitPerson.getPerson().getPersonID() == filingUnitPerson
					.getPerson().getPersonID()) {
				fUnitPerson
						.setEligibilityGroupIndicator(EnumEligibilityGroupIndicator.C);
				fUnitPerson.setConsiderIncome(true);
				fUnitPerson.setConsiderResource(true);
			}
		}
	}

	/**
	 * this method will create a closure filing unit object
	 * 
	 * @return void
	 */
	public void createClosureFilingUnit() {
		for (FilingUnit fUnit : this.filingUnitList) {
			fUnit.setFilingUnitProcessed(true);
			fUnit.setDefaultDenialFilingUnit(true);
			for (FilingUnitPerson fuPerson : fUnit.getFilingUnitPersonList()) {
				if (!this.dummyTANFcreated
						&& null != fUnit.getTypeOfAssistance()
						&& fUnit.getTypeOfAssistance().equals(
								EnumTypeOfAssistance.TP01)) {
					this.dummyTANFcreated = true;
					return;
				} else if (!this.dummyTANFcreated) {
					this.dummyRCAcreated = true;
					fUnit.setProgram(EnumProgram.TF);
					fUnit.setTypeOfAssistance(EnumTypeOfAssistance.RAPC);
					return;
				} else if (!this.dummyRCAcreated
						&& fuPerson.getPerson().isPath3Processing()) {
					FilingUnit newfilingUnit = new FilingUnit();
					ByteArrayOutputStream baos = new ByteArrayOutputStream();
					ObjectOutputStream oos = null;
					ObjectInputStream ois = null;
					try {
						oos = new ObjectOutputStream(baos);
						oos.writeObject(this.filingUnitList.get(0));
						ByteArrayInputStream bais = new ByteArrayInputStream(
								baos.toByteArray());
						ois = new ObjectInputStream(bais);
						newfilingUnit = (FilingUnit) ois.readObject();
						newfilingUnit.setProgram(EnumProgram.TF);
						newfilingUnit.setFilingUnitProcessed(true);
						newfilingUnit.setDefaultDenialFilingUnit(true);
						newfilingUnit
								.setTypeOfAssistance(EnumTypeOfAssistance.RAPC);
						newfilingUnit
								.setGroupNoticeReasonList(new ArrayList<EnumNoticeReason>());
						this.dummyRCAcreated = true;
						for (FilingUnitPerson fUnitPerson : newfilingUnit
								.getFilingUnitPersonList()) {
							fUnitPerson
									.setEligibilityGroupIndicator(EnumEligibilityGroupIndicator.NULL);
							fUnitPerson
									.setPartStatusCode(EnumPartStatusCode.NULL);
							fUnitPerson.clearNoticeReason(true);
						}
						this.filingUnitList.add(newfilingUnit);
						oos.close();
						ois.close();
					} catch (IOException e) {
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}
					return;

				}
			}
		}
	}

	/**
	 * this method will create a new filing unit for Elderly & Disabled person
	 * 
	 * @param filingUnitPerson
	 * @return FilingUnit
	 */
	public void createPotentialElderlyDisabledFilingUnit(
			FilingUnitPerson filingUnitPerson) {
		FilingUnit newfilingUnit = new FilingUnit();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos = null;
		ObjectInputStream ois = null;
		try {
			oos = new ObjectOutputStream(baos);
			oos.writeObject(this.filingUnitList.get(0));
			ByteArrayInputStream bais = new ByteArrayInputStream(baos
					.toByteArray());
			ois = new ObjectInputStream(bais);
			newfilingUnit = (FilingUnit) ois.readObject();
			newfilingUnit.setSNAPTestReq(true);
			newfilingUnit.setSNAPElderlyGroup(true);
			for (FilingUnitPerson fUnitPerson : newfilingUnit
					.getFilingUnitPersonList()) {
				fUnitPerson
						.setEligibilityGroupIndicator(EnumEligibilityGroupIndicator.X);
				fUnitPerson.setPartStatusCode(EnumPartStatusCode.NULL);
				if (fUnitPerson.getPerson().getPersonID() == filingUnitPerson
						.getPerson().getPersonID()) {
					fUnitPerson
							.setEligibilityGroupIndicator(EnumEligibilityGroupIndicator.C);
					fUnitPerson.getPerson().setSNAPElderlyDisabledPerson(
							EnumElderlyDisabledInd.P);
					fUnitPerson.setPrimaryPerson(true);
					fUnitPerson.setConsiderIncome(true);
					fUnitPerson.setConsiderResource(true);
				}
			}
			this.filingUnitList.add(newfilingUnit);
			oos.close();
			ois.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * this method checks if a person is receiving Cash (TANF or RCA) in any
	 * other Filing Unit
	 * 
	 * @param fuPerson
	 * @return boolean
	 */
	public boolean personRecievingCashInOtherFilingUnit(
			FilingUnitPerson fuPerson) {

		for (FilingUnit filingUnit : this.filingUnitList) {
			if (null != filingUnit.getProgram()
					&& filingUnit.getProgram().equals(EnumProgram.TF)) {
				if (null != filingUnit.getTypeOfAssistance()
						&& (filingUnit.getTypeOfAssistance().equals(
								EnumTypeOfAssistance.RAPC) || filingUnit
								.getTypeOfAssistance().equals(
										EnumTypeOfAssistance.TP01))) {
					for (FilingUnitPerson filingUnitPerson : filingUnit
							.getFilingUnitPersonList()) {
						if (filingUnitPerson.getPerson().getPersonID() == fuPerson
								.getPerson().getPersonID()) {
							if (null != filingUnitPerson
									.getEligibilityGroupIndicator()
									&& filingUnitPerson
											.getEligibilityGroupIndicator()
											.equals(
													EnumEligibilityGroupIndicator.C)) {
								return true;
							}
						}
					}
				}
			}
		}
		return false;
	}

	/**
	 * this method exclude potential Elderly and Disabled person from all other
	 * Filing Units
	 * 
	 * @param fuPerson
	 */
	public void excludePersonFromOtherFilingUnit(FilingUnitPerson fuPerson) {
		for (FilingUnit filingUnit : this.filingUnitList) {
			if (filingUnit != null && !filingUnit.isSNAPElderlyGroup()) {
				for (FilingUnitPerson filingUnitPerson : filingUnit
						.getFilingUnitPersonList()) {
					if (filingUnitPerson.getPerson().getPersonID() == fuPerson
							.getPerson().getPersonID()) {
						filingUnitPerson
								.setEligibilityGroupIndicator(EnumEligibilityGroupIndicator.X);
						filingUnitPerson
								.setPartStatusCode(EnumPartStatusCode.NULL);
					}
				}
			}
		}
	}

	/**
	 * Checks if a TANF/RCA group is created
	 * 
	 * @param theCase
	 *            Check if the group is created for this case.
	 * @param boolean Whether TANF/RCA Record is created
	 */
	public boolean groupCreatedForTANForRCA() 
	{
		if (null != this.filingUnitList && !this.filingUnitList.isEmpty()) 
		{
			for (FilingUnit fUnit : this.filingUnitList) 
			{
				if (null != fUnit.getProgram() && fUnit.getProgram().equals(EnumProgram.TF)) 
				{
					if (null != fUnit.getTypeOfAssistance() && fUnit.getTypeOfAssistance().equals(EnumTypeOfAssistance.TP01))
							/*|| fUnit.getTypeOfAssistance().equals(EnumTypeOfAssistance.RAPC) commenting as only one TOA gets created in TANF*/
					{
						if (null != fUnit.getFilingUnitPersonList() && !fUnit.getFilingUnitPersonList().isEmpty()) 
						{
							for (FilingUnitPerson fUPerson : fUnit.getFilingUnitPersonList()) 
							{
								if (null != fUPerson.getEligibilityGroupIndicator()
										&& fUPerson.getEligibilityGroupIndicator().equals(EnumEligibilityGroupIndicator.C)
										||(fUPerson.getEligibilityGroupIndicator().equals(EnumEligibilityGroupIndicator.E))
										   && fUPerson.getPerson()!=null && fUPerson.getPerson().isBenefitCAPChild() ==true) //benefit cap child check
								{
									return true;
								}
							}
						}
					}
				}
			}
		}
		return false;
	}
	
	
	public boolean isDummyTANFcreated() {
		return dummyTANFcreated;
	}

	public void setDummyTANFcreated(boolean dummyTANFcreated) {
		this.dummyTANFcreated = dummyTANFcreated;
	}

	public boolean isDummyRCAcreated() {
		return dummyRCAcreated;
	}

	public void setDummyRCAcreated(boolean dummyRCAcreated) {
		this.dummyRCAcreated = dummyRCAcreated;
	}

	public boolean isIncomeDataNotCollected() {
		return incomeDataNotCollected;
	}

	public void setIncomeDataNotCollected(boolean incomeDataNotCollected) {
		this.incomeDataNotCollected = incomeDataNotCollected;
	}

	
	/**
	 * @param numberOfDaysInApplicationMonth
	 *            the numberOfDaysInApplicationMonth to set
	 */
	public void setNumberOfDaysInApplicationMonth(
			long numberOfDaysInApplicationMonth) {
		this.numberOfDaysInApplicationMonth = numberOfDaysInApplicationMonth;
	}

	/**
	 * @return the numberOfDaysInApplicationMonth
	 */
	public long getNumberOfDaysInApplicationMonth() {
		return numberOfDaysInApplicationMonth;
	}

	/**
	 * @param applicationRecertificationFpil130LimitAmt
	 *            the applicationRecertificationFpil130LimitAmt to set
	 */
	public void setApplicationRecertificationFpil130LimitAmt(
			double applicationRecertificationFpil130LimitAmt) {
		this.applicationRecertificationFpil130LimitAmt = applicationRecertificationFpil130LimitAmt;
	}

	/**
	 * @return the applicationRecertificationFpil130LimitAmt
	 */
	public double getApplicationRecertificationFpil130LimitAmt() {
		return applicationRecertificationFpil130LimitAmt;
	}

	public ArrayList<CaseProfileVCL> getCaseProfileVCLList() {
		return caseProfileVCLList;
	}

	public void setCaseProfileVCLList(
			ArrayList<CaseProfileVCL> caseProfileVCLList) {
		this.caseProfileVCLList = caseProfileVCLList;
	}

	public void setStartDateOfAid(long startDateOfAid) {
		this.startDateOfAid = startDateOfAid;
	}

	public long getStartDateOfAid() {
		return startDateOfAid;
	}

	public Date getUnabletolocateDt() {
		return unabletolocateDt;
	}

	public void setUnabletolocateDt(Date unabletolocateDt) {
		this.unabletolocateDt = unabletolocateDt;
	}

	public int getDayOfAppMnth() {
		return dayOfAppMnth;
	}

	public void setDayOfAppMnth(int dayOfAppMnth) {
		this.dayOfAppMnth = dayOfAppMnth;
	}

	public Date getEdbcClosureDt() {
		return edbcClosureDt;
	}

	public void setEdbcClosureDt(Date edbcClosureDt) {
		this.edbcClosureDt = edbcClosureDt;
	}

	public EnumWithdrawReasonCode getWithdrawlReason() {
		return withdrawlReason;
	}

	public void setWithdrawlReason(EnumWithdrawReasonCode withdrawlReason) {
		this.withdrawlReason = withdrawlReason;
	}

	public EnumYesNo getLivingOnReservation() {
		return livingOnReservation;
	}

	public void setLivingOnReservation(EnumYesNo livingOnReservation) {
		this.livingOnReservation = livingOnReservation;
	}

	public String getRuleTrace() {
		return ruleTrace;
	}

	public void setRuleTrace(String ruleTrace) {
		this.ruleTrace = ruleTrace;
	}

	public void setUnabletolocate(boolean unabletolocate) {
		this.unabletolocate = unabletolocate;
	}

	public boolean isUnabletolocate() {
		return unabletolocate;
	}

	public void setForm3503SentDtSw(boolean form3503SentDtSw) {
		this.form3503SentDtSw = form3503SentDtSw;
	}

	public boolean isForm3503SentDtSw() {
		return form3503SentDtSw;
	}

	public boolean isTempTamperedWithSw() {
		return tempTamperedWithSw;
	}

	public void setTempTamperedWithSw(boolean tempTamperedWithSw) {
		this.tempTamperedWithSw = tempTamperedWithSw;
	}

	public void setFailedtoprovideinfo(boolean failedtoprovideinfo) {
		this.failedtoprovideinfo = failedtoprovideinfo;
	}

	public boolean isFailedtoprovideinfo() {
		return failedtoprovideinfo;
	}

	public void setChildsupportgoodcauses(ArrayList<ChildSupportGoodCause> childsupportgoodcauses) {
		this.childsupportgoodcauses = childsupportgoodcauses;
	}
	public void setMedicaidTypeCd(String medicaidTypeCd) {
		this.medicaidTypeCd = medicaidTypeCd;
	}

	public ArrayList<ChildSupportGoodCause> getChildsupportgoodcauses() {
		return childsupportgoodcauses;
	}
	public String getMedicaidTypeCd() {
		return medicaidTypeCd;
	}

	public void setCasemode(EnumYesNo casemode) {
		this.casemode = casemode;
	}

	public EnumYesNo getCasemode() {
		return casemode;
	}

	public void setDummyFMAcreated(boolean dummyFMAcreated) {
		this.dummyFMAcreated = dummyFMAcreated;
	}
	public boolean isDummyFMAcreated() {
		return dummyFMAcreated;
	}

	public void setPriorMedicaidCd(EnumPriorMedicaidCd priorMedicaidCd) {
		this.priorMedicaidCd = priorMedicaidCd;
	}
	public EnumPriorMedicaidCd getPriorMedicaidCd() {
		return priorMedicaidCd;
	}
	
	// spouse is applying for same type of assistance
	public boolean getSpouseTA(FilingUnit toFU) {
		if (toFU.getFilingUnitPersonList()!= null){
			for (FilingUnitPerson toFUP : toFU
						.getFilingUnitPersonList()){
				Person toPerson=toFUP.getPerson();
				if (toPerson.getRelationshipList() != null) {
					for (Relationship relationship : toPerson.getRelationshipList()) {
						if (relationship.getRelationshipType().equals(
								EnumRelationshipType.SPS)) {
							for(FilingUnit fu: this.getFilingUnitList()){
								for (FilingUnitPerson fUnitPerson : fu
										.getFilingUnitPersonList()) {
						if(relationship.getPerson().getPersonID()==fUnitPerson.getPerson().getPersonID()){
							if(fu.getTypeOfAssistance().equals(toFU.getTypeOfAssistance())){
									
								return true;
									
								}
							}
						}
					}
				}
			}
			}
		}
		}
		return false;
	}
	public boolean checkGroupIndicatorAndProgram(
			FilingUnitPerson filingUnitPerson) {
		if (this.filingUnitList != null) {
			for (FilingUnit filingUnit : this.filingUnitList) {
				if (filingUnit.getFilingUnitPersonList() != null && filingUnit.isEdgProcessed()) {
					for (FilingUnitPerson fUnitPerson : filingUnit
							.getFilingUnitPersonList()) {
						if (fUnitPerson.getPerson().getPersonID() == filingUnitPerson
								.getPerson().getPersonID()) {
							if (fUnitPerson.getEligibilityGroupIndicator()
									.equals(EnumEligibilityGroupIndicator.C)
									&& ((filingUnit.getProgram()
											.equals(EnumProgram.MA))
									|| (filingUnit.getProgram()
											.equals(EnumProgram.TMA))
									|| (filingUnit.getProgram()
											.equals(EnumProgram.ME)))) {
								return true;
							}
						}
					}
				}
			}
		}
		return false;
	}
		
	
	
	public boolean checkIfHKPresumtiveIndvIsInCG(FilingUnitPerson filingUnitPerson){
		if(this.filingUnitList!=null){
			for(FilingUnit filingUnit: this.filingUnitList){
				if(filingUnit.getFilingUnitPersonList()!=null){
					for(FilingUnitPerson fUnitPerson:filingUnit.getFilingUnitPersonList()){
						if(fUnitPerson.getPerson().getPersonID()==filingUnitPerson.getPerson().getPersonID()){
							if(filingUnitPerson.getEligibilityGroupIndicator().equals(EnumEligibilityGroupIndicator.C)&&(fUnitPerson.getPerson().isIndvHKPresumptive())){
								return true;
								}
							}
						}
					}
				}
			}
		return false;
		}

	
	public void setDummyedgprocfalse() {
		if (null != this.filingUnitList && !this.filingUnitList.isEmpty()) {
			for (FilingUnit fUnit : this.filingUnitList) {

				if (fUnit.isDummyGroupCreated()) {
					fUnit.setEdgProcessed(false);
					dummyFMAcreated = false;
				}

			}
		}

	}

	public void setCaseConvDt(Date caseConvDt) {
		this.caseConvDt = caseConvDt;
	}

	public Date getCaseConvDt() {
		return caseConvDt;
	}

	public boolean checkGroupIndicatorAndProgram1(
			FilingUnitPerson filingUnitPerson) {
		if (this.filingUnitList != null) {
			for (FilingUnit filingUnit : this.filingUnitList) {
				if (filingUnit.getFilingUnitPersonList() != null) {
					for (FilingUnitPerson fUnitPerson : filingUnit
							.getFilingUnitPersonList()) {
						if (fUnitPerson.getPerson().getPersonID() == filingUnitPerson
								.getPerson().getPersonID()) {
							if (fUnitPerson.getEligibilityGroupIndicator()
									.equals(EnumEligibilityGroupIndicator.C)
									&& ((filingUnit.getProgram()
											.equals(EnumProgram.MA)) || (filingUnit
											.getProgram()
											.equals(EnumProgram.TMA)))) {
								return true;
							}
						}
					}
				}
			}
		}
		return false;
	}
	public void setCaseConvDtMinusOneMonth(Date caseConvDtMinusOneMonth) {
		this.caseConvDtMinusOneMonth = caseConvDtMinusOneMonth;
	}

	public Date getCaseConvDtMinusOneMonth() {
		return caseConvDtMinusOneMonth;
	}
	
	

	public void setActivityType(String activityType) {
		this.activityType = activityType;
	}

	public String getActivityType() {
		return activityType;
	}


	public void setPriorMonthInAppDtYear(boolean priorMonthInAppDtYear) {
		this.priorMonthInAppDtYear = priorMonthInAppDtYear;
	}

	public boolean isPriorMonthInAppDtYear() {
		return priorMonthInAppDtYear;
	}
	
	//By Shivani Bhat for 9,10,11 of 1050_370
	public boolean indvHasSpouseInQMB(FilingUnitPerson filingUnitPerson,EnumTypeOfAssistance tpcode){
		if(this.filingUnitList!=null){
			for(FilingUnit filingUnit: this.filingUnitList){
				if(filingUnit.getFilingUnitPersonList()!=null){
					for(FilingUnitPerson fUnitPerson:filingUnit.getFilingUnitPersonList()){
						if(fUnitPerson.getPerson().getRelationshipList()!= null){
							for(Relationship rel: fUnitPerson.getPerson().getRelationshipList()){
								if(rel.getRelationshipType().equals(EnumRelationshipType.SPS)){
									for(FilingUnitPerson fUnitSpouse:filingUnit.getFilingUnitPersonList()){
										if(fUnitSpouse.getPerson().getPersonID()==rel.getRelationShipPersonID()){
											if(filingUnit.getTypeOfAssistance().equals(tpcode)&&
													fUnitSpouse.getEligibilityGroupIndicator().equals(EnumEligibilityGroupIndicator.InCG)&&
													fUnitSpouse.getPerson().isLivesWithSpouse()){
												return true;
											}	
										}
									}

								}
							}
						}

					}
				}
			}
		}

		return false;
	}
	
	//by shivani bhat for 13 0f 1050_370
	
	public boolean ALMDBuiltForEdm(EnumTypeOfAssistance tpCode){

		for(FilingUnit filingUnit: this.filingUnitList){
			if(filingUnit.getTypeOfAssistance().equals(EnumTypeOfAssistance.TP26)&&
					filingUnit.isEdgProcessed()){
				return true;
			}	
		}
		return false;
	}
	
	//by shivani bhat for 1050_430
	
	public boolean MCGroupHasBeenProcessed(){

		for(FilingUnit filingUnit: this.filingUnitList){
			if(filingUnit.getProgram().equals(EnumProgram.MC)&&
					filingUnit.isEdgProcessed()){
				return true;
			}	
		}
		return false;
	}

	public boolean individualIncludedInSSIMedicaid(EnumTypeOfAssistance tpCode){
		for(FilingUnit filingUnit: this.filingUnitList){
			if( (filingUnit.getProgram().equals(EnumProgram.ME) &&(filingUnit.getTypeOfAssistance().equals(tpCode)))){
				return true;
			}
		}
		return false;

	}
	
	public boolean individualIncludedInMCME(EnumProgram progCd){
		for(FilingUnit filingUnit: this.filingUnitList){
			if(filingUnit.getFilingUnitPersonList()!=null){
				for(FilingUnitPerson fUnitPerson:filingUnit.getFilingUnitPersonList()){
					if((fUnitPerson.getEligibilityGroupIndicator().equals(EnumEligibilityGroupIndicator.InCG))&& 
							(filingUnit.getProgram().equals(progCd))){
						return true;
					}
				}
			}
		}
		return false;

	}
	
	public boolean individualEntitledToMedicarePatA(){
		for(FilingUnit filingUnit: this.filingUnitList){
			if(filingUnit.getFilingUnitPersonList()!=null){
				for(FilingUnitPerson fUnitPerson:filingUnit.getFilingUnitPersonList()){
					if(fUnitPerson.getPerson().isEntitledForMedicarePartA()){
						return true;
					}
				}
			}
		}
		return false;

	}

	public void setClientMcReq(boolean clientMcReq) {
		this.clientMcReq = clientMcReq;
	}

	public boolean isClientMcReq() {
		return clientMcReq;
	}

	public void setMcPrevApproved(EnumTypeOfAssistance mcPrevApproved) {
		this.mcPrevApproved = mcPrevApproved;
	}

	public EnumTypeOfAssistance getMcPrevApproved() {
		return mcPrevApproved;
	}
	public boolean indvRecOrExpToLIF() {
		Iterator<FilingUnit> itr = filingUnitList.iterator();

		while (itr.hasNext()) {
			FilingUnit fu = (FilingUnit) itr.next();
			if ((fu
					.containsPersonWhoseEligibilityGroupIndicatorIsInAssistanceUnit())
					&& (fu.getProgram().equals(EnumProgram.MA))
					&& (fu.getTpCode().equals(EnumTypeOfAssistance.TP08)))

			{
				return true;
			}
		}
		return false;
	}
	/*
	 Is there an edg_group in the case whose programCd = 'MA' and tpCode = 'TP08' and edgProcessed = false 
	 */
	public boolean lifeEdgNotFound() {
		Iterator<FilingUnit> itr = filingUnitList.iterator();

		while (itr.hasNext()) {
			FilingUnit fu = (FilingUnit) itr.next();
			if ((fu.getProgram().equals(EnumProgram.MA))
					&& (fu.getTpCode().equals(EnumTypeOfAssistance.TP08))
					&& (fu.isEdgProcessed())) {
				return true;
			}
		}
		return false;
	}
	/*Type of Assistance = TMA (negate = FMA other than 1931 or TMA) and LIF is not approved 
	 * 
	 */
	public boolean chkTOATMAandLIFNotApprov() 
	{
		
			for (FilingUnit filingUnit : this.filingUnitList) {
				if (filingUnit.getFilingUnitPersonList() != null) {
					for (FilingUnitPerson fUnitPerson : filingUnit
							.getFilingUnitPersonList()) {
						
						
						if ((TMAGroupReq)&& !((fUnitPerson.getPerson().isTmaCertificationEnded()
								&& (DateUtilities
										.compareDatesGreaterThanEqualTo(
												filingUnit
														.getEligibilityDeterminationMonth(),
												edbcClosureDt)==EnumYesNo.N))
								&& !((fUnitPerson.getPerson().getAuthTpCd() != EnumTypeOfAssistance.TP08)&& (DateUtilities
										.compareDatesGreaterThanEqualTo(
												filingUnit
														.getEligibilityDeterminationMonth(),
												edbcClosureDt)==EnumYesNo.N)))) {
							return true;
						}
					}
				}
			}
			return false;
		}
		
	
	public boolean chkAnyIndivRecMEOrInstBuilt() {

		for (FilingUnit filingUnit : this.filingUnitList) {
			if (filingUnit.getFilingUnitPersonList() != null) {
				for (FilingUnitPerson fUnitPerson : filingUnit
						.getFilingUnitPersonList()) {
					if ((fUnitPerson.getPerson().isInUnauthMedicalGroup())
							|| ((fUnitPerson.getPerson()
									.isReceivingSSIMedicaid()) && medicaidTypeCd
									.equalsIgnoreCase("SS"))) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	
	

	public boolean checkSpouseGroupIndicatorTpCodeAndDate(FilingUnitPerson filingUnitPerson,EnumTypeOfAssistance tpcode,EnumEligibilityGroupIndicator en){
        if(this.filingUnitList!=null){
              for(FilingUnit fUnit: this.filingUnitList){
                    if(fUnit.getFilingUnitPersonList()!=null){
                          for(FilingUnitPerson fUnitPerson: fUnit.getFilingUnitPersonList()){
                                if (fUnitPerson.getPerson().getRelationshipList() != null) {
                                      for(Relationship relationship: fUnitPerson.getPerson().getRelationshipList()){
                                            if(relationship.getRelationshipType().equals(EnumRelationshipType.SPS)){
                                                  if(filingUnitPerson.getPerson().getPersonID()==relationship.getPerson().getPersonID()){
                                                        if(fUnitPerson.getEligibilityGroupIndicator().equals(en)&&fUnit.getTpCode().equals(tpcode)&&((fUnitPerson.isIndvMeetsLHReqSw()==false) || (fUnitPerson.getPerson().isPrevADCarExistsForSpouse() && (DateUtilities.compareDatesGreaterThanEqualTo(edbcClosureDt,fUnit.getEligibilityDeterminationMonth()).equals(EnumYesNo.Y))))){
                                                              return true;
                                                        }
                                                  }
                                            }     
                                      }
                                }
                          }
                    }
              }
        }
        return false;
        }

	public boolean checkTpCodeAndProgram(FilingUnitPerson filingUnitPerson,EnumProgram program,EnumTypeOfAssistance tpCode){
		if(this.filingUnitList!=null){
			for(FilingUnit filingUnit: this.filingUnitList){
				if(filingUnit.getFilingUnitPersonList()!=null){
					for(FilingUnitPerson fUnitPerson:filingUnit.getFilingUnitPersonList()){
						if(fUnitPerson.getPerson().getPersonID()==filingUnitPerson.getPerson().getPersonID()){
							if(filingUnit.getProgram().equals(program)&&(filingUnit.getTpCode().equals(tpCode)) && (filingUnit.isCatEligSwitch())){
								return true;
							}
						}
					}
				}
			}
		}
		return false;
	}

	public void setCallingIndvId(long callingIndvId) {
		this.callingIndvId = callingIndvId;
	}

	
public void setInstMeBuilt(boolean instMeBuilt) {
		this.instMeBuilt = instMeBuilt;
	}

	public boolean isInstMeBuilt() {
		return instMeBuilt;
	}

	public void setAMPEligible(boolean aMPEligible) {
		AMPEligible = aMPEligible;
	}

	public boolean isAMPEligible() {
		return AMPEligible;
	}

	public void setMedicaidTypePrevApproved(String medicaidTypePrevApproved) {
		this.medicaidTypePrevApproved = medicaidTypePrevApproved;
	}

	public String getMedicaidTypePrevApproved() {
		return medicaidTypePrevApproved;
	}
	public void setCallingIndvId(int callingIndvId) {
		this.callingIndvId = callingIndvId;
	}
	
	public void setTMAGroupReq(boolean tMAGroupReq) {
		TMAGroupReq = tMAGroupReq;
	}

	public boolean isTMAGroupReq() {
		return TMAGroupReq;
	}
	

		public boolean checkGroupIndicatorProgram(FilingUnitPerson filingUnitPerson) {
		if (this.filingUnitList != null) {
			for (FilingUnit filingUnit : this.filingUnitList) {
				if (filingUnit.getFilingUnitPersonList() != null) {
					for (FilingUnitPerson fUnitPerson : filingUnit
							.getFilingUnitPersonList()) {
						if (fUnitPerson.getPerson().getPersonID() == filingUnitPerson
								.getPerson().getPersonID()) {
							if (fUnitPerson.getEligibilityGroupIndicator()
									.equals(EnumEligibilityGroupIndicator.C)
									&& ((filingUnit.getProgram()
											.equals(EnumProgram.MA))
									|| (filingUnit.getProgram()
											.equals(EnumProgram.TMA)))) {
								return true;
							}
						}
					}
				}
			}
		}
		return false;
	}
	
	public boolean checkGroupIndicatorAndTpCode(FilingUnitPerson filingUnitPerson){
		if(this.filingUnitList!=null){
			for(FilingUnit filingUnit: this.filingUnitList){
				if(filingUnit.getFilingUnitPersonList()!=null){
					for(FilingUnitPerson fUnitPerson:filingUnit.getFilingUnitPersonList()){
						if(fUnitPerson.getPerson().getPersonID()==filingUnitPerson.getPerson().getPersonID()){
							if(fUnitPerson.getEligibilityGroupIndicator().equals(EnumEligibilityGroupIndicator.C)&&filingUnit.getTpCode().equals(EnumTypeOfAssistance.TP03)){
								return true;
								}
							}
						}
					}
				}
			}
		return false;
		}
	
	public boolean checkSpouseGroupIndicatorAndTpCode(FilingUnitPerson filingUnitPerson,EnumTypeOfAssistance tpcode,EnumEligibilityGroupIndicator en){
		if(this.filingUnitList!=null){
			for(FilingUnit filingUnit: this.filingUnitList){
				if(filingUnit.getFilingUnitPersonList()!=null){
					for(FilingUnitPerson fUnitPerson:filingUnit.getFilingUnitPersonList()){
						if (fUnitPerson.getPerson().getRelationshipList() != null) {
							for(Relationship relationship: fUnitPerson.getPerson().getRelationshipList()){
								if(relationship.getRelationshipType().equals(EnumRelationshipType.SPS)){
									if(filingUnitPerson.getPerson().getPersonID()==relationship.getPerson().getPersonID()){	
										if((fUnitPerson.getEligibilityGroupIndicator().equals(EnumEligibilityGroupIndicator.C)) && (filingUnit.getTpCode().equals(tpcode))){
										return true;
										}
									}
								}	
							}
						}
					}
				}
			}
		}
		return false;
		}
	
	public boolean checkSpouseGroupIndicatorAndTpCode1(FilingUnitPerson filingUnitPerson,EnumTypeOfAssistance tpcode,EnumEligibilityGroupIndicator en){
		if(this.filingUnitList!=null){
			for(FilingUnit filingUnit: this.filingUnitList){
				if(filingUnit.getFilingUnitPersonList()!=null){
					for(FilingUnitPerson fUnitPerson:filingUnit.getFilingUnitPersonList()){
						if (fUnitPerson.getPerson().getRelationshipList() != null) {
							for(Relationship relationship: fUnitPerson.getPerson().getRelationshipList()){
								if(relationship.getRelationshipType().equals(EnumRelationshipType.SPS)){
									if(filingUnitPerson.getPerson().getPersonID()==relationship.getPerson().getPersonID()){
										if((fUnitPerson.getEligibilityGroupIndicator().equals(EnumEligibilityGroupIndicator.C)) && (filingUnit.getTpCode().equals(tpcode)) && (fUnitPerson.getPerson().isLivesWithSpouse())){
										return true;
										}
									}
								}	
							}
						}
					}
				}
			}
		}
		return false;
		}

	public void setEd_inactive_toas(ArrayList<Ed_inactive_toa> ed_inactive_toas) {
		Ed_inactive_toas = ed_inactive_toas;
	}

	public ArrayList<Ed_inactive_toa> getEd_inactive_toas() {
		return Ed_inactive_toas;
	}
	public void setDummyMEcreated(boolean dummyMEcreated) {
		this.dummyMEcreated = dummyMEcreated;
	}

	public boolean isDummyMEcreated() {
		return dummyMEcreated;
	}
	public boolean checkGroupIndicatorProgramAndTpCode(FilingUnitPerson filingUnitPerson){
		if(this.filingUnitList!=null){
			for(FilingUnit filingUnit: this.filingUnitList){
				if(filingUnit.getFilingUnitPersonList()!=null){
					for(FilingUnitPerson fUnitPerson:filingUnit.getFilingUnitPersonList()){
						if(fUnitPerson.getPerson().getPersonID()==filingUnitPerson.getPerson().getPersonID()){
							if(fUnitPerson.getEligibilityGroupIndicator().equals(EnumEligibilityGroupIndicator.C)&&(filingUnit.getProgram().equals(EnumProgram.MA))||(filingUnit.getProgram().equals(EnumProgram.TMA))||(filingUnit.getProgram().equals(EnumProgram.ME))
									&& filingUnit.getTpCode().equals(EnumTypeOfAssistance.TP40)){
								return true;
								}
							}
						}
					}
				}
			}
		return false;
		}
	
	//by shivani bhat
	public boolean LIFEdgNotFormed(EnumTypeOfAssistance tpCode){
		for(FilingUnit filingUnit: this.filingUnitList){
			if( (filingUnit.getProgram().equals(EnumProgram.MA) &&(filingUnit.getTypeOfAssistance().equals(tpCode)) &&(filingUnit.isEdgProcessed()==false))){
				return true;
			}
		}
		return false;

	}

	public void setFmaReactivated(boolean fmaReactivated) {
		this.fmaReactivated = fmaReactivated;
	}

	public boolean isFmaReactivated() {
		return fmaReactivated;
	}

	public void setTp08Exists(boolean tp08Exists) {
		this.tp08Exists = tp08Exists;
	}

	public boolean isTp08Exists() {
		return tp08Exists;
	}

	public void setTempDate(Date tempDate) {
		this.tempDate = tempDate;
	}

	public Date getTempDate() {
		return tempDate;
	}

	
	public void setOverrideRecoupment(boolean overrideRecoupment) {
		this.overrideRecoupment = overrideRecoupment;
	}

	public boolean isOverrideRecoupment() {
		return overrideRecoupment;
	}
	
	//Missing Verification
	
	//Sumit VCL
	private boolean ssiFilingUnitProcessedSwitch;
	private boolean userDoingRedet;
	
	
	public boolean isUserDoingRedet() {
		return userDoingRedet;
	}

	public void setUserDoingRedet(boolean userDoingRedet) {
		this.userDoingRedet = userDoingRedet;
	}

	public void setDataNotCollected(boolean dataNotCollected) {
		this.dataNotCollected = dataNotCollected;
	}

	public boolean isDataNotCollected() {
		return dataNotCollected;
	}
public boolean isSsiFilingUnitProcessedSwitch() {
		return ssiFilingUnitProcessedSwitch;
	}

	public void setSsiFilingUnitProcessedSwitch(boolean ssiFilingUnitProcessedSwitch) {
		this.ssiFilingUnitProcessedSwitch = ssiFilingUnitProcessedSwitch;
	}
	//Missing Verification
	// added by sahil
	
	private boolean newIndvReqForAid;
	
	public void setNewIndvReqForAid(boolean newIndvReqForAid) {
		this.newIndvReqForAid = newIndvReqForAid;
	}

	public boolean isNewIndvReqForAid() {
		return newIndvReqForAid;
	}
	
 public boolean  personInNursingFacilityOrLongTermCare(){
		if(this.filingUnitList!=null){
			for(FilingUnit filingUnit: this.filingUnitList){
				if(filingUnit.getFilingUnitPersonList()!=null){
					for(FilingUnitPerson fUnitPerson:filingUnit.getFilingUnitPersonList()){
						if(fUnitPerson.getPerson().getPersonID()==fUnitPerson.getPerson().getPersonID()){
							if ((fUnitPerson.getPerson().getLaTypeCd().equals(EnumLivingArrangement.NF)) || (fUnitPerson.getPerson().getLaTypeCd().equals(EnumLivingArrangement.HH))) {
								return true;
							}
						}
					}
				}
				
			}
		} return false;
 }
	
 public boolean isResourceTransferPendingForVerificationForAnyFilingUnit(
			ResourceTransfer resourceTransfer) {
		boolean resourceTransferPending = false;
		if (resourceTransfer != null) {
			for (VerificationPgm verificationPgm : verificationPgmList) {
				if (verificationPgm != null) {
					if (verificationPgm.getVclIndvId() == resourceTransfer
							.getTransferIndvId()
							&& verificationPgm.getVclCode().equals(
									resourceTransfer.getVclCode())
							&& verificationPgm.getVclDetailRef()
									.equalsIgnoreCase(
											resourceTransfer.getTransferResourceDetailRef())) {
						resourceTransferPending = true;
						break;
					}
				}
			}
		}
		return resourceTransferPending;
	}
 
	 public void createNewFilingUnit(EnumTypeOfAssistance typeOfAssistance){
		 FilingUnit filingUnit=new FilingUnit();
		 ArrayList<FilingUnitPerson> filingUnitPersonList = new ArrayList<FilingUnitPerson>();
		 ArrayList<FilingUnit> filingUnitList = new ArrayList<FilingUnit>();  
		 filingUnit.setTypeOfAssistance(typeOfAssistance);
		 //filingUnit.setFailedIncomeTest(this.person.isFailedIncomeTest());
		 filingUnit.setActivityType(EnumCaseMode.valueOf(this.getActivityType()));
		 //filingUnit.setParNum(this.person.getParNum());
		 filingUnit.setEligibilityDeterminationMonth(this.getEligibilityDeterminationMonth());
		 filingUnit.setProgram(this.getProgramCd());
	
		 filingUnit.setProgramStatus(this.getProgramStatusCode());
		 filingUnit.setFinancialEligibilityTestRequired(true);
		 filingUnit.setResourceTestRequired(true);
		 filingUnit.setCertificationEndDate(this.certificationEndDt);   
		
		 
		 filingUnit.setEdbcRunMode("ONLINE");
		 filingUnit.setNegativeActionDate(this.negativeActionDate);
		 filingUnit.setSnapETFormSentDate(this.getSnapETFormSentDate());
		 filingUnit.setSnapETForSignedDate(this.getSnapETForSignedDate());
		 filingUnit.setRecertDueDate(this.getRecertduedate());
		 filingUnit.setEligibilityDeterminationMonth(this.eligibilityDeterminationMonth);
		 filingUnit.setReviewduedt(this.reviewDueDate);   
		 filingUnit.setFailedIncomeTest(true);	
		 filingUnit.setPaymentbegdt(this.paymentBegDt);
		 //filingUnit.setSnapETForSignedDate(this.person.getSnapETForSignedDate());
		 //System.out.println("this.interviewDate: "+this.interviewDate);
		 filingUnit.setInterviewDate(this.interviewDate);
		 filingUnit.setInterviewMode(this.interviewMode);
		 filingUnit.setImmediatelyPreviousCertificationDenied(this.immediatelyPreviousCertificationDenied);
		 filingUnit.setPreviouslyPostponedVerificationsProvided(this.previouslyPostponedVerificationsProvided);
		 //filingUnit.setForm3503SentDtSwitch(this.person.isForm3503SentDtSwitch());
		 copyPrevEdEligDates(filingUnit,typeOfAssistance);
		 
		 DateUtilities.LIHEAP_END_MONTH = this.getLiheapEndMonth();
		 DateUtilities.LIHEAP_START_MONTH = this.getLiheapStartMonth();
		 
		 for(Person person : this.personList){
			// System.out.println("this.person in create filing:"+person.getPersonID());
			 FilingUnitPerson filingUnitPerson=new FilingUnitPerson();
			 filingUnitPerson.setPerson(person);
			 filingUnitPerson.setProgcd(this.programCd); 
			 
			 filingUnitPersonList.add(filingUnitPerson);
		 }
		 filingUnit.setFilingUnitPersonList(filingUnitPersonList);
		 if((this.programCd != null) && (this.programCd.toString().equals("LI"))){
			 filingUnit.getFilingUnit_LIHEAPBudget().setHeatIncluded(this.isRntIncludeHeatingSw());
			 filingUnit.getFilingUnit_LIHEAPBudget().setEstimatedCostOfHeat(this.getEstimatedCostOfHeat());
			 filingUnit.getFilingUnit_LIHEAPBudget().setMonthlyRent(this.getLiheapHouseRentAmount());
			 filingUnit.getFilingUnit_LIHEAPBudget().setPrimaryHeatType(this.getLiheapPrimaryHeatType());
			 filingUnit.getFilingUnit_LIHEAPBudget().setNumberOfBedrooms(this.getLiheapNumberOfBedrooms());
			 if(this.isEmergencyLiheap() && (this.getPriorMedicaidCd()!=null) && this.getPriorMedicaidCd().equals(EnumPriorMedicaidCd.P0)){
				 filingUnit.getFilingUnit_LIHEAPBudget().setLiheapType(EnumLiheapType.EL);
			 }
			 else{
				 filingUnit.getFilingUnit_LIHEAPBudget().setLiheapType(EnumLiheapType.LI);
			 }
			 if(this.getLiheapTypeOfHome() != null){
				 filingUnit.getFilingUnit_LIHEAPBudget().setBuilding(this.getLiheapTypeOfHome().toString());
			 }
		 }
		 
		 filingUnitList.add(filingUnit);
		 this.setFilingUnitList(filingUnitList);
	 }
	 
	 public void createFilingUnitForCCAP(){
		 FilingUnit filingUnit=new FilingUnit();
		 ArrayList<FilingUnitPerson> filingUnitPersonList = new ArrayList<FilingUnitPerson>();
		 ArrayList<FilingUnit> filingUnitList = new ArrayList<FilingUnit>();  
		 filingUnit.setTypeOfAssistance(EnumTypeOfAssistance.CDCS);
		 filingUnit.setActivityType(EnumCaseMode.valueOf(this.getActivityType()));
		 filingUnit.setEligibilityDeterminationMonth(this.getEligibilityDeterminationMonth());
		 filingUnit.setProgram(EnumProgram.CD);
		 filingUnit.setProgramStatus(this.getProgramStatusCode());
		 filingUnit.setInterviewDate(this.interviewDate);
		 filingUnit.setInterviewMode(this.interviewMode);
		 filingUnit.setChildCareGrantType(EnumEdTanfType.CC);
		 filingUnit.setPaymentbegdt(this.paymentBegDt);
		 filingUnit.setEdbcRunMode("ONLINE");
		 filingUnit.setNegativeActionDate(this.negativeActionDate);
		 filingUnit.setRecertDueDate(this.getRecertduedate());
		 filingUnit.setCertificationEndDate(this.certificationEndDt);
		 filingUnit.setReviewduedt(this.reviewDueDate);
		 
		 copyPrevEdEligDates(filingUnit,EnumTypeOfAssistance.CDCS);
		 for(Person person : this.personList){
			// System.out.println("this.person in create filing:"+person.getPersonID());
			 FilingUnitPerson filingUnitPerson=new FilingUnitPerson();
			 if(person.isApplicant()){
				 filingUnitPerson.setTargetSw(true);
			 }
			 filingUnitPerson.setPerson(person);
			 filingUnitPersonList.add(filingUnitPerson);
		 }
		 filingUnit.setFilingUnitPersonList(filingUnitPersonList);
		 filingUnitList.add(filingUnit);
		 this.setFilingUnitList(filingUnitList);
	 }
	 /**this method retrieves the attribute value from the statusmap. This map stores value per TOA.
		 * @param toa
		 * @param cargoType
		 * @param attribute
		 * @return
		 */
		public String lookUpStatusByTOA(EnumTypeOfAssistance toa, String cargoType,
				String attribute) {
			String refValue = "";

			if (toa.equals(EnumTypeOfAssistance.NULL)) {
				return refValue;
			}
			else {
				refValue = lookupStatusData(toa, cargoType, attribute);
				return refValue;	
			}
		}
		
	// CR 911 changes start
	public double lookUpStatusByTOADouble(EnumTypeOfAssistance toa,
			String cargoType, String attribute) {
		String refValue = "";
		double returnValue = 0;
		if (!toa.equals(EnumTypeOfAssistance.NULL)) {
			refValue = lookupStatusData(toa, cargoType, attribute);
			if (refValue != null && !refValue.trim().isEmpty()) {
				returnValue = Double.parseDouble(refValue);
			}
		}
		return returnValue;
	}

	/*public int lookUpStatusByTOAInt(EnumTypeOfAssistance toa, String cargoType,
			String attribute) {
		String refValue = "";
		int returnValue = 0;
		if (!toa.equals(EnumTypeOfAssistance.NULL)) {
			refValue = lookupStatusData(toa, cargoType, attribute);
			if (refValue != null && !refValue.trim().isEmpty()) {
				returnValue = Integer.parseInt(refValue);
			}
		}
		return returnValue;
	}*/

	// CR 911 changes end
				
		
		public String lookUpStatusByTOACcap(EnumTypeOfAssistance toa, String cargoType,
				String attribute) {
			String refValue = "";

			if (toa.equals(EnumTypeOfAssistance.NULL)) {
				return refValue;
			}
			else {
				refValue = lookupStatusDataCcap(toa, cargoType, attribute);
				return refValue;	
			}
		}
		
		public String lookupStatusData(EnumTypeOfAssistance toa, String cargoType,
				String attribute) {
			if(null != statusOfPrevEdm)
			{
				Map toaMap = (Map) statusOfPrevEdm.get(toa);
				if(toaMap!=null){
					Map edmMap = (Map) toaMap.get(cargoType);
					if (edmMap!=null){
						if(edmMap.get(attribute)!=null){
							return (edmMap.get(attribute)).toString();
						}
					}
				}
			}


			return "";
		} 
		
		
		public String lookupStatusDataCcap(EnumTypeOfAssistance toa, String cargoType,
				String attribute) {
			if(null != statusOfCurrEdm)
			{
				Map toaMap = (Map) statusOfCurrEdm.get(toa);
				if(toaMap!=null){
					Map edmMap = (Map) toaMap.get(cargoType);
					if (edmMap!=null){
						if(edmMap.get(attribute)!=null){
							return (edmMap.get(attribute)).toString();
						}
					}
				}
			}


			return "";
		} 
		
		
		 /**this method retrieves the attribute value from the statusmap. This map stores value per TOA.
		 * @param toa
		 * @param cargoType
		 * @param attribute
		 * @return
		 */
		public String lookUpStatusByIndvId(Long indvId, String cargoType,
				String attribute) {
			String refValue = "";

			if (null== indvId) {
				return refValue;
			}
			else {
				refValue = lookupStatusData(indvId, cargoType, attribute);
				return refValue;	
			}
		}
		
		public long lookUpStatusByIndvIdLong(Long indvId, String cargoType,
				String attribute) 
		{
			Long refValue = (long) 0;
			if(null==indvId)
			{
				return refValue;
			}
			refValue = Long.parseLong(lookupStatusData(indvId, cargoType, attribute));
			return refValue;	
		}

		
		public String lookUpStatusByIndvIdCcap(Long indvId, String cargoType,
				String attribute) 
		{
			String refValue = "";
			if(null==indvId)
			{
				return refValue;
			}
			refValue = lookupStatusDataCcap(indvId, cargoType, attribute);
			return refValue;	
		}
		
		public String lookupStatusData(Long indvId, String cargoType,
				String attribute) {
			if(null != statusOfPrevIndvEdm)
			{
				Map toaMap = (Map) statusOfPrevIndvEdm.get(indvId);
				if(toaMap!=null){
					Map edmMap = (Map) toaMap.get(cargoType);
					if (edmMap!=null){
						if(edmMap.get(attribute)!=null){
							return (edmMap.get(attribute)).toString();
						}
					}
				}
			}


			return "";
		} 
		
		public String lookupStatusDataCcap(Long indvId, String cargoType,
				String attribute) {
			if(null != statusOfCurrIndvEdm)
			{
				Map toaMap = (Map) statusOfCurrIndvEdm.get(indvId);
				if(toaMap!=null){
					Map edmMap = (Map) toaMap.get(cargoType);
					if (edmMap!=null){
						if(edmMap.get(attribute)!=null){
							return (edmMap.get(attribute)).toString();
						}
					}
				}
			}


			return "";
		} 
		
	public void setUsedBillList(ArrayList<Used_Bills> usedBillList) {
		this.usedBillList = usedBillList;
	}

	public ArrayList<Used_Bills> getUsedBillList() {
		return usedBillList;
	}
	
	
	//ended by sahil
	

	//---------------------------PRODUCTION MERGE----------------------------
	
	private boolean houseHoldFailedECE = false;	
	private boolean houseHoldFailedECENonFin = false;
	private boolean houseHoldFailedECEResources = false;

	public boolean isHouseHoldFailedECEResources() {
		return houseHoldFailedECEResources;
	}

	public void setHouseHoldFailedECEResources(boolean houseHoldFailedECEResources) {
		this.houseHoldFailedECEResources = houseHoldFailedECEResources;
	}

	public boolean isHouseHoldFailedECENonFin() {
		return houseHoldFailedECENonFin;
	}

	public void setHouseHoldFailedECENonFin(boolean houseHoldFailedECENonFin) {
		this.houseHoldFailedECENonFin = houseHoldFailedECENonFin;
	}

	public boolean isHouseHoldFailedECE() {
		return houseHoldFailedECE;
	}

	public void setHouseHoldFailedECE(boolean houseHoldFailedECE) {
		this.houseHoldFailedECE = houseHoldFailedECE;
	}
	

	public Date getApplicationReceivedDate() {
		return applicationReceivedDate;
	}

	public void setApplicationReceivedDate(Date applicationReceivedDate) {
		this.applicationReceivedDate = applicationReceivedDate;
	}

	public EnumRunMode getRunMode() {
		return runMode;
	}

	public void setRunMode(EnumRunMode runMode) {
		this.runMode = runMode;
	}

	public String getProgRescInd() {
		return progRescInd;
	}

	public void setProgRescInd(String progRescInd) {
		this.progRescInd = progRescInd;
	}

	public EnumProgram getProgramCd() {
		return programCd;
	}

	public void setProgramCd(EnumProgram programCd) {
		this.programCd = programCd;
	}

	public Date getEligibilityDeterminationMonth() {
		return eligibilityDeterminationMonth;
	}

	public void setEligibilityDeterminationMonth(
			Date eligibilityDeterminationMonth) {
		this.eligibilityDeterminationMonth = eligibilityDeterminationMonth;
	}

	public Date getProgStsDt() {
		return progStsDt;
	}

	public void setProgStsDt(Date progStsDt) {
		this.progStsDt = progStsDt;
	}

	public Date getPgMaxMnth() {
		return pgMaxMnth;
	}

	public void setPgMaxMnth(Date pgMaxMnth) {
		this.pgMaxMnth = pgMaxMnth;
	}

	public boolean isAdverseActionAllowed() {
		return adverseActionAllowed;
	}

	public void setAdverseActionAllowed(boolean adverseActionAllowed) {
		this.adverseActionAllowed = adverseActionAllowed;
	}
	
	public Date getNegativeActionDate() {
		return negativeActionDate;
	}

	public void setNegativeActionDate(Date negativeActionDate) {
		this.negativeActionDate = negativeActionDate;
	}
	
	/*ND-38607 changes start here*/
	
	private boolean waiverSwSP;
	
	public boolean isWaiverSwSP() {
		return waiverSwSP;
	}

	public void setWaiverSwSP(boolean waiverSwSP) {
		this.waiverSwSP = waiverSwSP;
	}
	private Map statusOfPrevIndvEdm;
	public Map getStatusOfPrevIndvEdm() {
		return statusOfPrevIndvEdm;
	}

	public void setStatusOfPrevIndvEdm(Map statusOfPrevIndvEdm) {
		this.statusOfPrevIndvEdm = statusOfPrevIndvEdm;
	}

	//By Vikram
	private EnumYesNo requestingDiversion = EnumYesNo.NULL;
	private EnumYesNo shortTermNeed = EnumYesNo.NULL;
	private int diversionCount;
	
	public EnumYesNo getRequestingDiversion() {
		return requestingDiversion;
	}
	
	public void setRequestingDiversion(EnumYesNo requestingDiversion) {
		this.requestingDiversion = requestingDiversion;
	}

	public EnumYesNo getRequestingKinship() {
		return requestingKinship;
	}

	public void setRequestingKinship(EnumYesNo requestingKinship) {
		this.requestingKinship = requestingKinship;
	}

	public int getDiversionCount() {
		return diversionCount;
	}

	public void setDiversionCount(int diversionCount) {
		this.diversionCount = diversionCount;
	}

	public EnumYesNo getShortTermNeed() {
		return shortTermNeed;
	}

	public void setShortTermNeed(EnumYesNo shortTermNeed) {
		this.shortTermNeed = shortTermNeed;
	}
	
	public Date getVerificationDueDate() {
		return verificationDueDate;
	}

	public void setVerificationDueDate(Date verificationDueDate) {
		this.verificationDueDate = verificationDueDate;
	}
	public boolean isImmediatelyPreviousCertificationDenied() {
		return immediatelyPreviousCertificationDenied;
	}
	public void setImmediatelyPreviousCertificationDenied(
			boolean immediatelyPreviousCertificationDenied) {
		this.immediatelyPreviousCertificationDenied = immediatelyPreviousCertificationDenied;
	}
	public boolean isPreviouslyPostponedVerificationsProvided() {
		return previouslyPostponedVerificationsProvided;
	}
	public void setPreviouslyPostponedVerificationsProvided(
			boolean previouslyPostponedVerificationsProvided) {
		this.previouslyPostponedVerificationsProvided = previouslyPostponedVerificationsProvided;
	}
	private boolean householdTanffraud;
	
	
	public boolean isHouseholdTanffraud() {
		return householdTanffraud;
	}

	public void setHouseholdTanffraud(boolean householdTanffraud) {
		this.householdTanffraud = householdTanffraud;
	}
	public boolean changeSubmittedBySelfMemberOrAr;
	public boolean isChangeSubmittedBySelfMemberOrAr() {
		return changeSubmittedBySelfMemberOrAr;
	}
	public void setChangeSubmittedBySelfMemberOrAr(
			boolean changeSubmittedBySelfMemberOrAr) {
		this.changeSubmittedBySelfMemberOrAr = changeSubmittedBySelfMemberOrAr;
	}
	public boolean benefitReductionSwitch;
	public boolean isBenefitReductionSwitch() {
		return benefitReductionSwitch;
	}
	public void setBenefitReductionSwitch(boolean benefitReductionSwitch) {
		this.benefitReductionSwitch = benefitReductionSwitch;
	}
	public boolean tanfApprovedForSnap;
	public boolean tanfApprovedForSnap() {
		return tanfApprovedForSnap;
	}
	public void setTanfApprovedForSnap(boolean tanfApprovedForSnap) {
		this.tanfApprovedForSnap = tanfApprovedForSnap;
	}
	public boolean reviewNeverHappened;
	public boolean isReviewNeverHappened() {
		return reviewNeverHappened;
	}
	public void setReviewNeverHappened(boolean reviewNeverHappened) {
		this.reviewNeverHappened = reviewNeverHappened;
	}
	public boolean closedForReviewMRPostpndVerification;
	
	
	public boolean isClosedForReviewMRPostpndVerification() {
		return closedForReviewMRPostpndVerification;
	}

	public void setClosedForReviewMRPostpndVerification(
			boolean closedForReviewMRPostpndVerification) {
		this.closedForReviewMRPostpndVerification = closedForReviewMRPostpndVerification;
	}
	private boolean closedAsLumpsumRecieved= false;

	public boolean isClosedAsLumpsumRecieved() {
		return closedAsLumpsumRecieved;
	}

	public void setClosedAsLumpsumRecieved(boolean closedAsLumpsumRecieved) {
		this.closedAsLumpsumRecieved = closedAsLumpsumRecieved;
	}
	
	public boolean isPreviouslyAuthorizedIsExpeditedSw() {
		return previouslyAuthorizedIsExpeditedSw;
	}

	public void setPreviouslyAuthorizedIsExpeditedSw(
			boolean previouslyAuthorizedIsExpeditedSw) {
		this.previouslyAuthorizedIsExpeditedSw = previouslyAuthorizedIsExpeditedSw;
	}

	public boolean compareReportedAndClientAwareDatesWith(Date date){
		boolean incomeExists = false, expenseExists = false;
		if((date != null) && (this.getFilingUnitList() != null)){
			for(FilingUnit fu : this.getFilingUnitList()){
				if((fu != null) && (fu.getFilingUnitPersonList() != null)){
					for(FilingUnitPerson fup : fu.getFilingUnitPersonList()){
						if(fup != null && fup.getEligibilityGroupIndicator().equals(EnumEligibilityGroupIndicator.C)){							
							Person p = fup.getPerson();
							if((p != null) && (p.getIncomeList() != null)){
								for(Income inc : p.getIncomeList()){
									if(inc != null){
										incomeExists = true;
										if((inc.getIncomeReportDate() != null) && (inc.getIncomeDiscoveryDt() != null)){
											if(inc.getIncomeReportDate().compareTo(date) > 0 ){
												if(inc.getIncomeDiscoveryDt().compareTo(date) < 0){
													return true;
												}
											}
										}
									}
								}
							}
							if(p.getExpenseList() != null){
								for(Expense exp : p.getExpenseList()){
									if(exp != null){
										expenseExists = true;
										if((exp.getExpenseReportDt() != null) && (exp.getExpenseDiscoveryDt() != null)){
											if(exp.getExpenseReportDt().compareTo(date) > 0 ){
												if(exp.getExpenseDiscoveryDt().compareTo(date) < 0){
													return true;
												}
											}
										}										
									}
								}
							}							
						}
					}
				}
			}
		}
		if(incomeExists || expenseExists){
			return false;
		}
		return false;
	}


    public boolean var9000_300;
	public boolean var9000_310;
	public boolean var9000_320;
	public boolean var9000_330;
	public boolean var9000_330_2;
	public boolean var9000_340;
	public boolean var9000_410;
	
	
    private double previousGrossIncome;
	
	public double getPreviousGrossIncome() {
		return previousGrossIncome;
	}

	public void setPreviousGrossIncome(double previousGrossIncome) {
		this.previousGrossIncome = previousGrossIncome;
	}
	
	
	public double lookupData(EnumTypeOfAssistance toa, String cargoType,
			String attribute) {
		if(null != statusOfPrevEdm && !statusOfPrevEdm.isEmpty())
		{
			Map toaMap = (Map) statusOfPrevEdm.get(toa);
			if(null != toaMap){
				Map edmMap = (Map) toaMap.get(cargoType);
				if (null != edmMap){
					if(edmMap.get(attribute)!=null){
						Double benefitAmt = Double.parseDouble((String) edmMap.get(attribute));
						return benefitAmt;
					}
				}
			}
		}
		return 0;
	}
	
	public double lookupDataForCurrentRun(EnumTypeOfAssistance toa, String cargoType,
			String attribute) {
		if(null != statusOfCurrEdm && !statusOfCurrEdm.isEmpty())
		{
			Map toaMap = (Map) statusOfCurrEdm.get(toa);
			if(null != toaMap){
				Map edmMap = (Map) toaMap.get(cargoType);
				if (null != edmMap){
					if(edmMap.get(attribute)!=null){
						Double benefitAmt = Double.parseDouble((String) edmMap.get(attribute));
						return benefitAmt;
					}
				}
			}
		}
		return 0;
	}
	
	public Date lookupPayBegDateForCurrentRun(EnumTypeOfAssistance toa, String cargoType,
			String attribute) throws ParseException {
		if(null != statusOfCurrEdm && !statusOfCurrEdm.isEmpty())
		{
			Map toaMap = (Map) statusOfCurrEdm.get(toa);
			if(null != toaMap){
				Map edmMap = (Map) toaMap.get(cargoType);
				if (null != edmMap){
					if(edmMap.get(attribute)!=null){
						SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
						Date benefitPayBegDate =  sdf.parse((String) edmMap.get(attribute));
						return benefitPayBegDate;
					}
				}
			}
		}
		return null;
	}
	
	public Date lookupLastAuthDate() throws ParseException {
		if(null != statusOfPrevEdm)
		{
			Map toaMap = (Map) statusOfPrevEdm.get(EnumTypeOfAssistance.TP09);			
			if(null != toaMap){
				Map edmMap = (Map) toaMap.get("PreviousOngoingAuthorizedRecord");
				if (null != edmMap){
					if(edmMap.get("DI_ACTION_DT")!=null){						
						String dt = (String) edmMap.get("DI_ACTION_DT");
						SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");	
						Date diActionDate = sdf.parse(dt);
						return diActionDate;
					}
				}
			}
		}
		return null;
	}
	
	
	
	public boolean lookupStatusDataForNewIndividual(Long indvId) {
		if((null != statusOfPrevIndvEdm) && (!statusOfPrevIndvEdm.isEmpty())){
			Map toaMap = (Map) statusOfPrevIndvEdm.get(indvId);			
			if((null == toaMap) || (toaMap.isEmpty())){
				return true;				
			}			
			if((null != toaMap) && (!toaMap.isEmpty())){
				Map authMap = (Map) toaMap.get("PreviousOngoingAuthorizedRecord");
			    if((null == authMap) || (authMap.isEmpty())){
			    	return true;
			    }
			}
		}
		return false;
	} 
	
	public boolean lookupStatusDataForNewIndividualIntake(Long indvId) {
		if((null != statusOfPrevIndvEdm) && (!statusOfPrevIndvEdm.isEmpty())){
			Map toaMap = (Map) statusOfPrevIndvEdm.get(indvId);			
			if((null == toaMap) || (toaMap.isEmpty())){
				return true;				
			}
			if((null != toaMap) && (!toaMap.isEmpty())){
			    Map authMap = (Map) toaMap.get("PreviousEDMCurrentRun");
			    if((null == authMap) || (authMap.isEmpty())){
			    	return true;
			    }			
			}
		}
		return false;
	} 
	
	public boolean newlyAddedMember(){
		boolean newMember=false;
        FilingUnit fu=this.getFilingUnitList().get(0);
		if(!fu.getFilingUnitPersonList().isEmpty() || fu.getFilingUnitPersonList().size() >0){
			for(FilingUnitPerson fup: fu.getFilingUnitPersonList()){
				Person per=fup.getPerson();
				Long indivId = per.getPersonID();
				newMember = lookupStatusDataForNewIndividual(indivId);
				if(true==newMember){
					if(EnumEligibilityGroupIndicator.C.equals(fup.getEligibilityGroupIndicator())){
						return true;
					}
				}				
			}
		}
		return false;
	}
	
	public boolean anyMemberMovedOutOfPAP()
	{
		String movedOut;
        FilingUnit fu=this.getFilingUnitList().get(0);
		if(fu.getFilingUnitPersonList()!=null && !fu.getFilingUnitPersonList().isEmpty())
		{
			for(FilingUnitPerson fup: fu.getFilingUnitPersonList())
			{
				Person per=fup.getPerson();
				Long indivId = per.getPersonID();
				if(per.isEligibleTANFPAP()==false)	
				{
					movedOut = lookUpStatusByIndvId(indivId,"PreviousOngoingAuthorizedRecord","PAP_SW");
					if(movedOut.equals("Y"))
						return true;
				}
			}
		}
		return false;
	}
	public boolean newlyAddedMemberIntake(){
		if(DateUtilities.getLastDayOfMonth(this.getEligibilityDeterminationMonth()).compareTo(DateUtilities.getLastDayOfMonth(this.getApplicationDate())) == 0){
			return this.newEligibleMemberAddedInFirstMonth();
		}
		boolean newMember=false;
        FilingUnit fu=this.getFilingUnitList().get(0);
		if(!fu.getFilingUnitPersonList().isEmpty() || fu.getFilingUnitPersonList().size() >0){
			for(FilingUnitPerson fup: fu.getFilingUnitPersonList()){
				Person per=fup.getPerson();
				Long indivId = per.getPersonID();
				newMember = lookupStatusDataForNewIndividualIntake(indivId);
				if(true==newMember){
					if(EnumEligibilityGroupIndicator.C.equals(fup.getEligibilityGroupIndicator())){
						return true;
					}
				}				
			}
		}
		return false;
	}
	public boolean newlyAddedMemberOnFirstDayOfMonth(){
		boolean newMember=false;
        FilingUnit fu=this.getFilingUnitList().get(0);
		if(!fu.getFilingUnitPersonList().isEmpty() || fu.getFilingUnitPersonList().size() >0){
			for(FilingUnitPerson fup: fu.getFilingUnitPersonList()){
				Person per=fup.getPerson();
					if(EnumEligibilityGroupIndicator.C.equals(fup.getEligibilityGroupIndicator())){
						if(DateUtilities.isFirstDayOfTheMonth(per.getEffectiveBeginDate())){
							return true;
						}					
				}			
			}
		}
		return false;
	}
	public boolean newEligibleMemberAddedInFirstMonth(){
        FilingUnit fu=this.getFilingUnitList().get(0);
        if(fu.getFilingUnitPersonList() == null || fu.getFilingUnitPersonList().isEmpty()){
        	return false;
        }
        else{
			for(FilingUnitPerson fup: fu.getFilingUnitPersonList()){
				if(EnumEligibilityGroupIndicator.C.equals(fup.getEligibilityGroupIndicator())){
					if(null != fu.getPaymentbegdt() && null != fup.getPerson().getEffectiveBeginDate()){
						if(fu.getPaymentbegdt().compareTo(fup.getPerson().getEffectiveBeginDate()) < 0){
							return true;
						}
					}
				}			
			}
		}
		return false;
	}
	public List<FilingUnitPerson> findNewMemberAddedInFirstMonth(EnumPartStatusCode partStatusCode){
		List<FilingUnitPerson> newPersonList = new ArrayList<FilingUnitPerson>();
		FilingUnit fu=this.getFilingUnitList().get(0);
        if(fu.getFilingUnitPersonList() == null || fu.getFilingUnitPersonList().isEmpty()){
        	return newPersonList;
        }
        else{
			for(FilingUnitPerson fup: fu.getFilingUnitPersonList()){
				if(EnumEligibilityGroupIndicator.C.equals(fup.getEligibilityGroupIndicator())){
					if(fu.getPaymentbegdt().compareTo(fup.getPerson().getEffectiveBeginDate()) < 0){
						if(DateUtilities.compareInMonthsEqualTo(fup.getPerson().getEffectiveBeginDate(), eligibilityDeterminationMonth).toString().equals("Y")){
							if(partStatusCode == null || partStatusCode.equals(EnumPartStatusCode.NULL)){
								newPersonList.add(fup);
							}
							else{
								if(partStatusCode.equals(fup.getPartStatusCode())){
									newPersonList.add(fup);
								}							
							}
						}
					}
				}
			}
        }
		return newPersonList;
	}
	public List<FilingUnitPerson> findNewMemberAdded(EnumPartStatusCode partStatusCode){
		
		boolean newMember=false;
		List<FilingUnitPerson> newPersonList = new ArrayList<FilingUnitPerson>();
		List<FilingUnit> fuList = this.getFilingUnitList();
		if(fuList == null || fuList.isEmpty()){
			return null;
		}
		FilingUnit fu=fuList.get(0);
		
		//for intake and first month
		if( (fu.getActivityType() == EnumCaseMode.IN) && (DateUtilities.getLastDayOfMonth(this.getEligibilityDeterminationMonth()).compareTo(DateUtilities.getLastDayOfMonth(this.getApplicationDate())) == 0) ){
			return this.findNewMemberAddedInFirstMonth(partStatusCode);
		}
		
		if(!fu.getFilingUnitPersonList().isEmpty() || fu.getFilingUnitPersonList().size() >0){
			for(FilingUnitPerson fup: fu.getFilingUnitPersonList()){
				Person per=fup.getPerson();
				Long indivId = per.getPersonID();
				if(fu.getActivityType() == EnumCaseMode.PR || fu.getActivityType() == EnumCaseMode.IR){
					newMember = this.lookupStatusDataForNewIndividual(indivId);
				}
				if(fu.getActivityType() == EnumCaseMode.IN){
					newMember = this.lookupStatusDataForNewIndividualIntake(indivId);
				}
				if(true==newMember){					
					if(EnumEligibilityGroupIndicator.C.equals(fup.getEligibilityGroupIndicator())){
						if(DateUtilities.compareInMonthsEqualTo(per.getEffectiveBeginDate(), eligibilityDeterminationMonth).toString().equals("Y")){
							if(partStatusCode == null || partStatusCode.equals(EnumPartStatusCode.NULL)){
								newPersonList.add(fup);
							}
							else{
								if(partStatusCode.equals(fup.getPartStatusCode())){
									newPersonList.add(fup);
								}							
							}
						}
					}
				}
			}
		}
		return newPersonList;
	}
	
	public int totalNewEligibleMembersAdded(){
		List<FilingUnitPerson> newMembersAdded = this.findNewMemberAdded(null);
		if(newMembersAdded == null || newMembersAdded.isEmpty()){
			return 0;
		}
		return newMembersAdded.size();
	}
	public int totalNewEligibleCaretakersAdded(){
		List<FilingUnitPerson> newMembersAdded = this.findNewMemberAdded(EnumPartStatusCode.ET);
		if(newMembersAdded == null || newMembersAdded.isEmpty()){
			return 0;
		}
		return newMembersAdded.size();
	}
	public int totalNewEligibleChildrenAdded(){
		List<FilingUnitPerson> newMembersAdded = this.findNewMemberAdded(EnumPartStatusCode.EC);
		if(newMembersAdded == null || newMembersAdded.isEmpty()){
			return 0;
		}
		return newMembersAdded.size();
	}
	public boolean anyNewMemAddedMonthBeforeEdbc(Date runDate){
		boolean newMember=false;
        FilingUnit fu=this.getFilingUnitList().get(0);
		if(!fu.getFilingUnitPersonList().isEmpty() || fu.getFilingUnitPersonList().size() >0){
			for(FilingUnitPerson fup: fu.getFilingUnitPersonList()){
				Person per=fup.getPerson();
				Long indivId = per.getPersonID();
				newMember = lookupStatusDataForNewIndividual(indivId);
				if(true == newMember){
					if(EnumEligibilityGroupIndicator.C.equals(fup.getEligibilityGroupIndicator())){
						if(DateUtilities.compareInMonthsEqualTo(runDate, DateUtilities.addMonthsToDate(per.getEffectiveBeginDate(), 1)).equals(EnumYesNo.Y)){					
							return true;
						}
					}
				}				
			}
		}
		return false;
	}
	
	public boolean earliestNewMemberAddedMonthBeforeEdbc(Date runDate) throws ParseException{
		boolean newMember=false;
		String dt = "2999-12-31";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		Date earliestDate = sdf.parse(dt);
        FilingUnit fu=this.getFilingUnitList().get(0);
		if(!fu.getFilingUnitPersonList().isEmpty() || fu.getFilingUnitPersonList().size() >0){
			for(FilingUnitPerson fup: fu.getFilingUnitPersonList()){
				Person per=fup.getPerson();
				Long indivId = per.getPersonID();
				newMember = lookupStatusDataForNewIndividual(indivId);
				if(true == newMember){
					if(EnumEligibilityGroupIndicator.C.equals(fup.getEligibilityGroupIndicator())){
						if(DateUtilities.compareInMonthsGreaterThanEqualTo(earliestDate, per.getEffectiveBeginDate()).equals(EnumYesNo.Y)){
		                      earliestDate =  per.getEffectiveBeginDate(); 
						}
					}
				}				
			}
			if(null != earliestDate){
				if(DateUtilities.compareInMonthsEqualTo(runDate, DateUtilities.addMonthsToDate(earliestDate, 1)).equals(EnumYesNo.Y)){					
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean reportDateOfIncMonthBeforeEdbc(Date runDate) throws ParseException{
		String dt = "1000-12-31";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		Date latestReportedDate = sdf.parse(dt);
        FilingUnit fu=this.getFilingUnitList().get(0);
		if(!fu.getFilingUnitPersonList().isEmpty() || fu.getFilingUnitPersonList().size() >0){
			for(FilingUnitPerson fup: fu.getFilingUnitPersonList()){
				Person per=fup.getPerson();
				ArrayList<Income> incList = per.getIncomeList();
				if(null != per.getIncomeList() || !per.getIncomeList().isEmpty()){
					for(Income inc: incList){
						if(DateUtilities.compareInMonthsGreaterThan(inc.getIncomeReportDate(), latestReportedDate).equals(EnumYesNo.Y)){
							latestReportedDate= inc.getIncomeReportDate();
						}
			        }
			    }
		    }
			if(DateUtilities.compareInMonthsEqualTo(runDate, DateUtilities.addMonthsToDate(latestReportedDate, 1)).equals(EnumYesNo.Y)){
				return true;
			}
		}	
		return false;
	}
	
	
	public boolean edmGreaterThanExpenseReported(Date edm) throws ParseException{
		int count = 0, ladCount = 0;
        FilingUnit fu=this.getFilingUnitList().get(0);
		if(!fu.getFilingUnitPersonList().isEmpty() || fu.getFilingUnitPersonList().size() >0){
			for(FilingUnitPerson fup: fu.getFilingUnitPersonList()){
				Person per=fup.getPerson();	
				ArrayList<Expense> expList = per.getExpenseList();
				if(null != per.getExpenseList() || !per.getExpenseList().isEmpty()){
					for(Expense exp: expList){
					  /*Date lastAuthDate = lookupLastAuthDate();
						if(DateUtilities.compareDatesGreaterThan(exp.getExpenseReportDt(), lastAuthDate).equals(EnumYesNo.Y)){*/
							ladCount = ladCount + 1;
							if(DateUtilities.compareInMonthsGreaterThanEqualTo(exp.getExpenseReportDt(), edm).equals(EnumYesNo.Y)){					
								count = count + 1;
							}
					  //}
					}
				}				
			}
			if(0 == count && 0 != ladCount){
				return true;
			}
		}
		return false;
	}
	
	// Added for new 9000_310 DT
	
	
	public boolean vrfWasPendingForNewMember(){
        boolean newMember = false;
		FilingUnit fu=this.getFilingUnitList().get(0);
		if(!fu.getFilingUnitPersonList().isEmpty() || fu.getFilingUnitPersonList().size() >0){
			for(FilingUnitPerson fup: fu.getFilingUnitPersonList()){
				Person per=fup.getPerson();
				ArrayList<Verification> verificationList = per.getVerificationListForOUR();				
				Long indivId = per.getPersonID();
				newMember = lookupStatusDataForNewIndividual(indivId);
				if(true==newMember){
					if(EnumEligibilityGroupIndicator.C.equals(fup.getEligibilityGroupIndicator())){
						if (verificationList != null
								&& !verificationList.isEmpty()) {
							return true;
						}
				    }
			    }
		    }
		}	
		return false;
	}
	
	
	public boolean vrfRecvdDateAfterVrfDueDate() throws ParseException{
		boolean newMember=false;
		int count = 0;
		String dt = "1000-12-31";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		Date latestVrfDueDt = sdf.parse(dt);
		Date latestVrfRecvdDt = sdf.parse(dt);
        FilingUnit fu=this.getFilingUnitList().get(0);
		if(!fu.getFilingUnitPersonList().isEmpty() || fu.getFilingUnitPersonList().size() >0){
			for(FilingUnitPerson fup: fu.getFilingUnitPersonList()){
				Person per=fup.getPerson();
				ArrayList<Verification> verificationList = per.getVerificationListForOUR();
				Long indivId = per.getPersonID();
				newMember = lookupStatusDataForNewIndividual(indivId);
				if(true==newMember){
					if(EnumEligibilityGroupIndicator.C.equals(fup.getEligibilityGroupIndicator())){
						if (verificationList != null
								&& !verificationList.isEmpty()) {
							for (Verification verf : verificationList) {					
								if(DateUtilities.compareDatesGreaterThan(verf.getVclDueDt(), latestVrfDueDt).equals(EnumYesNo.Y)){
									count = count + 1;
									latestVrfDueDt = verf.getVclDueDt();
									latestVrfRecvdDt = verf.getValidSourceRcvDt();
								}	
							}
					    }
					}
			    }
			}
			if(0 != count){
				if(DateUtilities.compareDatesGreaterThan(latestVrfRecvdDt, latestVrfDueDt).equals(EnumYesNo.Y)){
					return true;
				}
			}
		}
		return false;
	}
	
	
	public boolean edmGreaterThanPgmRqstDt(Date edm){
		boolean newMember=false;
        FilingUnit fu=this.getFilingUnitList().get(0);
		if(!fu.getFilingUnitPersonList().isEmpty() || fu.getFilingUnitPersonList().size() >0){
			for(FilingUnitPerson fup: fu.getFilingUnitPersonList()){
				Person per=fup.getPerson();
				Long indivId = per.getPersonID();
				newMember = lookupStatusDataForNewIndividual(indivId);
				if(true==newMember){
					if(EnumEligibilityGroupIndicator.C.equals(fup.getEligibilityGroupIndicator())){
						if(null != per.getPrgRequestDt()){
							if(DateUtilities.compareInMonthsGreaterThan(edm, per.getPrgRequestDt()).equals(EnumYesNo.Y)){					
								return true;
							}
						}else{
							if(DateUtilities.compareInMonthsGreaterThan(edm, per.getEffectiveBeginDate()).equals(EnumYesNo.Y)){					
								return true;
							}
						}
					}
				}				
			}
		}
		return false;
	}
	
	
	public boolean edmEqualToPgmRqstDt(Date edm){
		boolean newMember=false;
        FilingUnit fu=this.getFilingUnitList().get(0);
		if(!fu.getFilingUnitPersonList().isEmpty() || fu.getFilingUnitPersonList().size() >0){
			for(FilingUnitPerson fup: fu.getFilingUnitPersonList()){
				Person per=fup.getPerson();
				Long indivId = per.getPersonID();
				newMember = lookupStatusDataForNewIndividual(indivId);
				if(true==newMember){
					if(EnumEligibilityGroupIndicator.C.equals(fup.getEligibilityGroupIndicator())){
						if(null != per.getPrgRequestDt()){
							if(DateUtilities.compareInMonthsEqualTo(edm, per.getPrgRequestDt()).equals(EnumYesNo.Y)){					
								return true;
							}
						}else{
							if(DateUtilities.compareInMonthsEqualTo(edm, per.getEffectiveBeginDate()).equals(EnumYesNo.Y)){					
								return true;
							}
						}
					}
				}				
			}
		}
		return false;
	}
	
	
	public boolean vrfDueDateInEdm(Date edm) throws ParseException{
		boolean newMember=false;
		int count = 0;
		String dt = "1000-12-31";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		Date latestVrfDueDt = sdf.parse(dt);
        FilingUnit fu=this.getFilingUnitList().get(0);
		if(!fu.getFilingUnitPersonList().isEmpty() || fu.getFilingUnitPersonList().size() >0){
			for(FilingUnitPerson fup: fu.getFilingUnitPersonList()){
				Person per=fup.getPerson();
				ArrayList<Verification> verificationList = per.getVerificationListForOUR();				
				Long indivId = per.getPersonID();
				newMember = lookupStatusDataForNewIndividual(indivId);
				if(true==newMember){
					if(EnumEligibilityGroupIndicator.C.equals(fup.getEligibilityGroupIndicator())){
						if (verificationList != null
								&& !verificationList.isEmpty()) {
							for (Verification verf : verificationList) {
								if(DateUtilities.compareDatesGreaterThanEqualTo(verf.getVclDueDt(), latestVrfDueDt).equals(EnumYesNo.Y)){
									count = count + 1;
									latestVrfDueDt =  verf.getVclDueDt();  
								}					
							}
					    }
					}
			    }
		    }
			if(0 != count){
				if(DateUtilities.compareInMonthsEqualTo(edm, latestVrfDueDt).equals(EnumYesNo.Y)){					
					return true;
				}					
			}
		}
		return false;
	}
	
	public boolean edmGreaterThanVrfDueDate(Date edm) throws ParseException{
		boolean newMember=false;
		int count = 0;
		String dt = "1000-12-31";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		Date latestVrfDueDt = sdf.parse(dt);
        FilingUnit fu=this.getFilingUnitList().get(0);
		if(!fu.getFilingUnitPersonList().isEmpty() || fu.getFilingUnitPersonList().size() >0){
			for(FilingUnitPerson fup: fu.getFilingUnitPersonList()){
				Person per=fup.getPerson();
				ArrayList<Verification> verificationList = per.getVerificationListForOUR();				
				Long indivId = per.getPersonID();
				newMember = lookupStatusDataForNewIndividual(indivId);
				if(true==newMember){
					if(EnumEligibilityGroupIndicator.C.equals(fup.getEligibilityGroupIndicator())){
						if (verificationList != null
								&& !verificationList.isEmpty()) {
							for (Verification verf : verificationList) {
								if(DateUtilities.compareDatesGreaterThanEqualTo(verf.getVclDueDt(), latestVrfDueDt).equals(EnumYesNo.Y)){
									count = count + 1;
									latestVrfDueDt =  verf.getVclDueDt();  
								}													
							}
					    }
					}
			    }
		    }
			if(0 != count){
				if(DateUtilities.compareInMonthsGreaterThan(edm, latestVrfDueDt).equals(EnumYesNo.Y)){					
					return true;
				}
			}
			
		}
		return false;
	}
	
	/* Added for Change in Income */
	
	public boolean vrfWasPendingForIncomeChange() throws ParseException{
		FilingUnit fu=this.getFilingUnitList().get(0);
		if(!fu.getFilingUnitPersonList().isEmpty() || fu.getFilingUnitPersonList().size() >0){
			for(FilingUnitPerson fup: fu.getFilingUnitPersonList()){
				Person per=fup.getPerson();
				ArrayList<Verification> verificationList = per.getVerificationListForOUR();
				if (null != verificationList
						&& !verificationList.isEmpty()) {
					for (Verification verf : verificationList) {
						Date lastAuthDate = lookupLastAuthDate();
						if(DateUtilities.compareDatesGreaterThan(verf.getVclDueDt(), lastAuthDate).equals(EnumYesNo.Y)){
							if(("V050".equals(verf.getVclCode().toString())) || ("V060".equals(verf.getVclCode().toString())) || ("V120".equals(verf.getVclCode().toString()))){								
								return true;
							}
						}						
					}
			    }
		    }
		}	
		return false;
	}
	
	
	public boolean vrfRecvdDateForIncomeAfterVrfDueDate() throws ParseException{
		Date VrfDueDt = null;
		Date VrfRecvdDt = null;
		FilingUnit fu=this.getFilingUnitList().get(0);
		if(!fu.getFilingUnitPersonList().isEmpty() || fu.getFilingUnitPersonList().size() >0){
			for(FilingUnitPerson fup: fu.getFilingUnitPersonList()){
				Person per=fup.getPerson();
				ArrayList<Verification> verificationList = per.getVerificationListForOUR();
				if (null != verificationList
						&& !verificationList.isEmpty()) {
					for (Verification verf : verificationList) {
						Date lastAuthDate = lookupLastAuthDate();
						if(DateUtilities.compareDatesGreaterThan(verf.getVclDueDt(), lastAuthDate).equals(EnumYesNo.Y)){
							if(("V050".equals(verf.getVclCode().toString())) || ("V060".equals(verf.getVclCode().toString())) || ("V120".equals(verf.getVclCode().toString()))){								
								VrfDueDt =  verf.getVclDueDt();
								VrfRecvdDt = verf.getValidSourceRcvDt();
								if(DateUtilities.compareDatesGreaterThan(VrfRecvdDt, VrfDueDt).equals(EnumYesNo.Y)){
									return true;
								}
							}
						}						
					}
			    }
			}
		}	
		return false;
	}
	
	
	public boolean edmGreateThanIncReported(Date edm) throws ParseException{
		int count = 0, ladCount = 0;
        FilingUnit fu=this.getFilingUnitList().get(0);
		if(!fu.getFilingUnitPersonList().isEmpty() || fu.getFilingUnitPersonList().size() >0){
			for(FilingUnitPerson fup: fu.getFilingUnitPersonList()){
				Person per=fup.getPerson();	
				ArrayList<Income> incList = per.getIncomeList();
				if(null != per.getIncomeList() || !per.getIncomeList().isEmpty()){
					for(Income inc: incList){
					  /*Date lastAuthDate = lookupLastAuthDate();
						if(DateUtilities.compareDatesGreaterThan(inc.getIncomeReportDate(), lastAuthDate).equals(EnumYesNo.Y)){*/
							ladCount = ladCount + 1;
							if(DateUtilities.compareInMonthsGreaterThanEqualTo(inc.getIncomeReportDate(), edm).equals(EnumYesNo.Y)){					
								count = count + 1;	
							}
					  //}				
					}
				}				
			}
			if(0 == count && 0 != ladCount){
				return true;
			}
		}
		return false;
	}
	
	public boolean edmEqualToIncReported(Date edm) throws ParseException{
        FilingUnit fu=this.getFilingUnitList().get(0);
		if(!fu.getFilingUnitPersonList().isEmpty() || fu.getFilingUnitPersonList().size() >0){
			for(FilingUnitPerson fup: fu.getFilingUnitPersonList()){
				Person per=fup.getPerson();	
				ArrayList<Income> incList = per.getIncomeList();
				if(null != per.getIncomeList() || !per.getIncomeList().isEmpty()){
					for(Income inc: incList){
					  /*Date lastAuthDate = lookupLastAuthDate();
						if(DateUtilities.compareDatesGreaterThan(inc.getIncomeReportDate(), lastAuthDate).equals(EnumYesNo.Y)){*/													
							if(DateUtilities.compareInMonthsEqualTo(edm, inc.getIncomeReportDate()).equals(EnumYesNo.Y)){					
								return true;
							}
					  //}
					}
				}				
			}
		}
		return false;
	}
	
	
	public boolean edmGreaterThanVrfDueDateForIncome(Date edm) throws ParseException{
		int count = 0;
		String dt = "1000-12-31";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		Date latestVrfDueDt = sdf.parse(dt);		
		FilingUnit fu=this.getFilingUnitList().get(0);
		if(!fu.getFilingUnitPersonList().isEmpty() || fu.getFilingUnitPersonList().size() >0){
			for(FilingUnitPerson fup: fu.getFilingUnitPersonList()){
				Person per=fup.getPerson();
				ArrayList<Verification> verificationList = per.getVerificationListForOUR();
				if (null != verificationList && !verificationList.isEmpty()) {
					for (Verification verf : verificationList){	
						Date lastAuthDate = lookupLastAuthDate();
						if(DateUtilities.compareDatesGreaterThan(verf.getVclDueDt(), lastAuthDate).equals(EnumYesNo.Y)){
							if(("V050".equals(verf.getVclCode().toString())) || ("V060".equals(verf.getVclCode().toString())) || ("V120".equals(verf.getVclCode().toString()))){							
								if(DateUtilities.compareDatesGreaterThanEqualTo(verf.getVclDueDt(), latestVrfDueDt).equals(EnumYesNo.Y)){								
									count = count + 1;
									latestVrfDueDt =  verf.getVclDueDt();
								}				
							}
						}
					}			
				}
			}
			if(0 != count){					
				if(DateUtilities.compareInMonthsGreaterThan(edm, latestVrfDueDt).equals(EnumYesNo.Y)){							
					return true;
				}
			}
		}	
		return false;
	}
	
	
	public boolean vrfDueDateForIncomeInEdm(Date edm) throws ParseException{
		int count = 0;
		String dt = "1000-12-31";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		Date latestVrfDueDt = sdf.parse(dt);
		FilingUnit fu=this.getFilingUnitList().get(0);
		if(!fu.getFilingUnitPersonList().isEmpty() || fu.getFilingUnitPersonList().size() >0){
			for(FilingUnitPerson fup: fu.getFilingUnitPersonList()){
				Person per=fup.getPerson();
				ArrayList<Verification> verificationList = per.getVerificationListForOUR();
				if (null != verificationList && !verificationList.isEmpty()) {
					for (Verification verf : verificationList){
						Date lastAuthDate = lookupLastAuthDate();
						if(DateUtilities.compareDatesGreaterThan(verf.getVclDueDt(), lastAuthDate).equals(EnumYesNo.Y)){
							if(("V050".equals(verf.getVclCode().toString())) || ("V060".equals(verf.getVclCode().toString())) || ("V120".equals(verf.getVclCode().toString()))){
								if(DateUtilities.compareDatesGreaterThanEqualTo(verf.getVclDueDt(), latestVrfDueDt).equals(EnumYesNo.Y)){
									count = count + 1;
									latestVrfDueDt =  verf.getVclDueDt();  
								}				
							}
						}
					}
				}
			}
			if(0 != count){
				if(DateUtilities.compareInMonthsEqualTo(edm, latestVrfDueDt).equals(EnumYesNo.Y)){					
					return true;
				}
			}
		}	
		return false;
	}

	public boolean isFailedtoprovideinfoTanF() {
		return failedtoprovideinfoTanF;
	}

	public void setFailedtoprovideinfoTanF(boolean failedtoprovideinfoTanF) {
		this.failedtoprovideinfoTanF = failedtoprovideinfoTanF;
	} 
    private boolean residenceIndianSw;
	private boolean lostContactSw;
	
	public boolean isResidenceIndianSw() {
		return residenceIndianSw;
	}

	public void setResidenceIndianSw(boolean residenceIndianSw) {
		this.residenceIndianSw = residenceIndianSw;
	}	
	
	public boolean isLostContactSw() {
		return lostContactSw;
	}

	public void setLostContactSw(boolean lostContactSw) {
		this.lostContactSw = lostContactSw;
	}
	
	private EnumCaseMode caseMode= EnumCaseMode.NULL;
	public EnumCaseMode getCaseMode() {
		return caseMode;
	}

	public void setCaseMode(EnumCaseMode caseMode) {
		this.caseMode = caseMode;
	}
	
	private Date semiAnnualDate;
	public Date getSemiAnnualDate() {
		return semiAnnualDate;
	}

	public void setSemiAnnualDate(Date semiAnnualDate) {
		this.semiAnnualDate = semiAnnualDate;
	}
	
private Date monthlyRepReviewStartDate;
	
	private Date monthlyRepReviewCompDate;
	
	private String monthlyRepReviewCd;
	public Date getMonthlyRepReviewStartDate() {
		return monthlyRepReviewStartDate;
	}

	public void setMonthlyRepReviewStartDate(Date monthlyRepReviewStartDate) {
		this.monthlyRepReviewStartDate = monthlyRepReviewStartDate;
	}

	public Date getMonthlyRepReviewCompDate() {
		return monthlyRepReviewCompDate;
	}

	public void setMonthlyRepReviewCompDate(Date monthlyRepReviewCompDate) {
		this.monthlyRepReviewCompDate = monthlyRepReviewCompDate;
	}

	public String getMonthlyRepReviewCd() {
		return monthlyRepReviewCd;
	}

	public void setMonthlyRepReviewCd(String monthlyRepReviewCd) {
		this.monthlyRepReviewCd = monthlyRepReviewCd;
	}
	
	private boolean monthlyRepCompSw;
	public boolean isMonthlyRepCompSw() {
		return monthlyRepCompSw;
	}

	public void setMonthlyRepCompSw(boolean monthlyRepCompSw) {
		this.monthlyRepCompSw = monthlyRepCompSw;
	}

	public Map getStatusOfCurrEdm() {
		return statusOfCurrEdm;
	}

	public void setStatusOfCurrEdm(Map statusOfCurrEdm) {
		this.statusOfCurrEdm = statusOfCurrEdm;
	}

	public Map getStatusOfCurrIndvEdm() {
		return statusOfCurrIndvEdm;
	}

	public void setStatusOfCurrIndvEdm(Map statusOfCurrIndvEdm) {
		this.statusOfCurrIndvEdm = statusOfCurrIndvEdm;
	}

	
	private boolean monthAfterAppMonthWasSuspended;
	public boolean isMonthAfterAppMonthWasSuspended() {
		return monthAfterAppMonthWasSuspended;
	}

	public void setMonthAfterAppMonthWasSuspended(
			boolean monthAfterAppMonthWasSuspended) {
		this.monthAfterAppMonthWasSuspended = monthAfterAppMonthWasSuspended;
	}


    public Date lookupDate() throws ParseException {
		if(null != statusOfPrevEdm)
		{
			Map toaMap = (Map) statusOfPrevEdm.get(EnumTypeOfAssistance.TP01);			
			if(null != toaMap && !toaMap.isEmpty()){
				Map edmMap = (Map) toaMap.get("PreviousOngoingAuthorizedRecord");
				if (null != edmMap && !edmMap.isEmpty() && (null!=edmMap.get("CG_STATUS_CD")&& (!"DN".equalsIgnoreCase((String)edmMap.get("CG_STATUS_CD"))))){
					if(edmMap.get(ESTIMATED_ELIGIBILITY_END_DT)!=null){						
						String dt = (String) edmMap.get(ESTIMATED_ELIGIBILITY_END_DT);
						SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");	
						Date estimatedEligEndDate = sdf.parse(dt);
						return estimatedEligEndDate;
					}
				}
			}
		}
		return null;
	}
	
	
	
	
	private double previousGrossIncomeForTanf;
	private double previousNetIncomeForTanf;
	
	public double getPreviousGrossIncomeForTanf() {
		return previousGrossIncomeForTanf;
	}

	public void setPreviousGrossIncomeForTanf(double previousGrossIncomeForTanf) {
		this.previousGrossIncomeForTanf = previousGrossIncomeForTanf;
	}

	public double getPreviousNetIncomeForTanf() {
		return previousNetIncomeForTanf;
	}

	public void setPreviousNetIncomeForTanf(double previousNetIncomeForTanf) {
		this.previousNetIncomeForTanf = previousNetIncomeForTanf;
	}
	
	private double previousNetIncomeForSnap;
	
	
	public double getPreviousNetIncomeForSnap() {
		return previousNetIncomeForSnap;
	}

	public void setPreviousNetIncomeForSnap(double previousNetIncomeForSnap) {
		this.previousNetIncomeForSnap = previousNetIncomeForSnap;
	}

	public boolean individualSanctionCured(Date edm){
		FilingUnit fu=this.getFilingUnitList().get(0);
		if(null != fu.getFilingUnitPersonList() && !fu.getFilingUnitPersonList().isEmpty()){
			for(FilingUnitPerson fup:fu.getFilingUnitPersonList()){
				Person per = fup.getPerson();
				Long indivId = per.getPersonID();
				String partStatusCd = lookupStatusData(indivId, "PreviousOngoingAuthorizedRecord", "EDG_PART_STATUS_CODE");				
			    if(null != partStatusCd && !partStatusCd.isEmpty()){
			    	if(!(EnumPartStatusCode.EA.toString()).equals(partStatusCd) && !(EnumPartStatusCode.EC.toString()).equals(partStatusCd) && !(EnumPartStatusCode.ET.toString()).equals(partStatusCd)){
			    		if(null != per.getNonCooperationList() && !per.getNonCooperationList().isEmpty()){
			    			for(NonCooperation ncoop:per.getNonCooperationList()){
			    				if(EnumNonCoopTypeCode.JTNEW.equals(ncoop.getNonCooperationTypeCode()) || EnumNonCoopTypeCode.CSNC.equals(ncoop.getNonCooperationTypeCode())){
			    					if(DateUtilities.compareInMonthsGreaterThanEqualTo(edm, DateUtilities.addMonthsToDate(ncoop.getComplyDate(), 1)).equals(EnumYesNo.Y)){
						    			return true;
						    		}
			    				}
			    			}		    			
			    		}
			    	}
			    }			
			}
		}		
		return false;
	}
	
	
	public boolean newlyAddedMemberToAssistanceUnit(){
		Double aus= 0.0;
        FilingUnit fu=this.getFilingUnitList().get(0);
        aus = lookupData(fu.getTypeOfAssistance(), "PreviousOngoingAuthorizedRecord", "ASS_UNIT_SIZE");
        
		if(fu.getAssistanceUnitSize() > aus){
			return true;
		}
		return false;
	}
	
	
	public boolean changeIsReportedTimelyForNewlyAddedMemberForTanf(){
		boolean newMember=false;
		int newMemCount=0, reportedTimelyCount=0 ;
        FilingUnit fu=this.getFilingUnitList().get(0);
        if(null != fu.getFilingUnitPersonList() && !fu.getFilingUnitPersonList().isEmpty()){
			for(FilingUnitPerson fup: fu.getFilingUnitPersonList()){
				Person per=fup.getPerson();
				Long indivId = per.getPersonID();
				newMember = lookupStatusDataForNewIndividual(indivId);
				if(true==newMember){
					newMemCount = newMemCount + 1;
					if(EnumEligibilityGroupIndicator.C.equals(fup.getEligibilityGroupIndicator())){
						if(false == per.isNewBornSwitch()){
							if(DateUtilities.compareDatesGreaterThanEqualTo(DateUtilities.addDaysToDate(per.getDiscoveryDate(), 5), per.getReportDate()).equals(EnumYesNo.Y)){
								reportedTimelyCount = reportedTimelyCount + 1;
							}
						}else if(true == per.isNewBornSwitch()){
							if(DateUtilities.compareDatesGreaterThanEqualTo(DateUtilities.addDaysToDate(per.getDiscoveryDate(), 10), per.getReportDate()).equals(EnumYesNo.Y)){
								reportedTimelyCount = reportedTimelyCount + 1;
							}
						}
					}
				}				
			}
			if(0 != newMemCount && newMemCount == reportedTimelyCount){
				return true;
			}
		}        		
		return false;
	}
	
	public boolean changeIsVerifiedTimelyForNewlyAddedMemberForTanf(){
		boolean newMember=false;
		int newMemCount=0, verifiedTimelyCount=0 ;
        FilingUnit fu=this.getFilingUnitList().get(0);
		if(null != fu.getFilingUnitPersonList() && !fu.getFilingUnitPersonList().isEmpty()){
			for(FilingUnitPerson fup: fu.getFilingUnitPersonList()){
				Person per=fup.getPerson();
				Long indivId = per.getPersonID();
				newMember = lookupStatusDataForNewIndividual(indivId);
				if(true==newMember){
					newMemCount = newMemCount + 1;
					if(EnumEligibilityGroupIndicator.C.equals(fup.getEligibilityGroupIndicator())){
						if(DateUtilities.compareDatesGreaterThanEqualTo(DateUtilities.addDaysToDate(per.getReportDate(), 30), per.getVerfRecvdDate()).equals(EnumYesNo.Y)){
							verifiedTimelyCount = verifiedTimelyCount + 1;
						}	
					}
				}				
			}
			if(0 != newMemCount && newMemCount == verifiedTimelyCount){
				return true;
			}			
		}
		return false;
	}
	
	public boolean changeIsVerifiedTimelyForIncomeDecreaseForTanf(){
		int count = 0, incCount = 0;
		FilingUnit fu=this.getFilingUnitList().get(0);
		if(null != fu.getFilingUnitPersonList() && !fu.getFilingUnitPersonList().isEmpty()){
			for(FilingUnitPerson fup: fu.getFilingUnitPersonList()){
				Person per=fup.getPerson();	
				ArrayList<Income> incList = per.getIncomeList();
				if(null != per.getIncomeList() && !per.getIncomeList().isEmpty()){
					for(Income inc: incList){
						incCount = incCount + 1;
						if(DateUtilities.compareDatesGreaterThan(inc.getIncomeVerfReceivedDt(), DateUtilities.addDaysToDate(inc.getIncomeReportDate(), 10)).equals(EnumYesNo.Y)){					
							count = count +1;
						}																	
					}
				}				
			}
			if(0 != incCount && 0 == count){
				return true;
			}
		}
		return false;
	} 
	
	
	
	
	public boolean edmGreaterThanPgmRqstDtForTanf(Date edm){
		boolean newMember=false;
        FilingUnit fu=this.getFilingUnitList().get(0);
		if(null != fu.getFilingUnitPersonList() && !fu.getFilingUnitPersonList().isEmpty()){
			for(FilingUnitPerson fup: fu.getFilingUnitPersonList()){
				Person per=fup.getPerson();
				Long indivId = per.getPersonID();
				newMember = lookupStatusDataForNewIndividual(indivId);
				if(true==newMember){
					if(EnumEligibilityGroupIndicator.C.equals(fup.getEligibilityGroupIndicator())){
						if(DateUtilities.compareInMonthsGreaterThanEqualTo(edm, per.getPrgRequestDt()).equals(EnumYesNo.Y)){					
							return true;
						}
					}
				}				
			}
		}
		return false;
	}
	
	public boolean edmGreaterThanChangeReportedDtForNewMemberForTanf(Date edm){
		boolean newMember=false;
        FilingUnit fu=this.getFilingUnitList().get(0);
		if(null != fu.getFilingUnitPersonList() && !fu.getFilingUnitPersonList().isEmpty()){
			for(FilingUnitPerson fup: fu.getFilingUnitPersonList()){
				Person per=fup.getPerson();
				Long indivId = per.getPersonID();
				newMember = lookupStatusDataForNewIndividual(indivId);
				if(true==newMember){
					if(EnumEligibilityGroupIndicator.C.equals(fup.getEligibilityGroupIndicator())){
						if(DateUtilities.compareInMonthsGreaterThan(edm, per.getReportDate()).equals(EnumYesNo.Y)){					
							return true;
						}
					}
				}				
			}
		}
		return false;
	}
	
	public boolean edmGreaterThanChangeReportedDtForIncDecreaseForTanf(Date edm){
		int count = 0, incCount = 0;
		FilingUnit fu=this.getFilingUnitList().get(0);
		if(null != fu.getFilingUnitPersonList() && !fu.getFilingUnitPersonList().isEmpty()){
			for(FilingUnitPerson fup: fu.getFilingUnitPersonList()){
				Person per=fup.getPerson();	
				ArrayList<Income> incList = per.getIncomeList();
				if(null != per.getIncomeList() && !per.getIncomeList().isEmpty()){
					for(Income inc: incList){
						incCount = incCount + 1;
						if(DateUtilities.compareInMonthsGreaterThanEqualTo(inc.getIncomeReportDate(), edm).equals(EnumYesNo.Y)){					
							count = count +1;
						}																	
					}
				}				
			}
			if(0 != incCount && 0 == count){
				return true;
			}
		}
		return false;
	} 
	
	
	public boolean edmGreaterThanVrfRecvdDateForNewPersonForTanf(Date edm){
		boolean newMember=false;
        FilingUnit fu=this.getFilingUnitList().get(0);
		if(null != fu.getFilingUnitPersonList() && !fu.getFilingUnitPersonList().isEmpty()){
			for(FilingUnitPerson fup: fu.getFilingUnitPersonList()){
				Person per=fup.getPerson();
				Long indivId = per.getPersonID();
				newMember = lookupStatusDataForNewIndividual(indivId);
				if(true==newMember){
					if(EnumEligibilityGroupIndicator.C.equals(fup.getEligibilityGroupIndicator())){
						if(DateUtilities.compareInMonthsGreaterThan(edm, per.getVerfRecvdDate()).equals(EnumYesNo.Y)){					
							return true;
						}
					}
				}				
			}
		}
		return false;
	}
	
	
	public boolean edmGreaterThanVerfRecvdDtForIncDecreaseForTanf(Date edm){
		int count = 0, incCount = 0;
		FilingUnit fu=this.getFilingUnitList().get(0);
		if(null != fu.getFilingUnitPersonList() && !fu.getFilingUnitPersonList().isEmpty()){
			for(FilingUnitPerson fup: fu.getFilingUnitPersonList()){
				Person per=fup.getPerson();	
				ArrayList<Income> incList = per.getIncomeList();
				if(null != per.getIncomeList() && !per.getIncomeList().isEmpty()){
					for(Income inc: incList){
						incCount = incCount + 1;
						if(DateUtilities.compareInMonthsGreaterThanEqualTo(inc.getIncomeVerfReceivedDt(), edm).equals(EnumYesNo.Y)){					
							count = count +1;
						}																	
					}
				}				
			}
			if(0 != incCount && 0 == count){
				return true;
			}
		}
		return false;
	} 
	
	
	//Added for TANF OUR Overpayment
	
	
	public boolean individualLeftHousehold(){
		FilingUnit fu=this.getFilingUnitList().get(0);
		if(!fu.getFilingUnitPersonList().isEmpty() && 0 < fu.getFilingUnitPersonList().size()){
			for(FilingUnitPerson fup:fu.getFilingUnitPersonList()){
				Person per = fup.getPerson();
				Long indivId = per.getPersonID();
				String partStatusCd = lookupStatusData(indivId, "PreviousOngoingAuthorizedRecord", "EDG_PART_STATUS_CODE");				
			    if(null != partStatusCd && !partStatusCd.isEmpty()){
			    	if((EnumPartStatusCode.EA.toString()).equals(partStatusCd) || (EnumPartStatusCode.IA.toString()).equals(partStatusCd) || (EnumPartStatusCode.EC.toString()).equals(partStatusCd) || (EnumPartStatusCode.IC.toString()).equals(partStatusCd) || (EnumPartStatusCode.ET.toString()).equals(partStatusCd)){
			    		if(per.getHouseholdStatus().equals(EnumHouseholdStatus.N)){
			    			return true;                        
	    				}			    				
			    	}
			    }			
			}
		}		
		return false;
	}
	
	
	public Double lookupStatusDataForInvForTanf(Long indvId, String cargoType,
			String attribute) {
		if(null != statusOfPrevIndvEdm && !statusOfPrevIndvEdm.isEmpty())
		{
			Map toaMap = (Map) statusOfPrevIndvEdm.get(indvId);
			if(null != toaMap && !toaMap.isEmpty()){
				Map edmMap = (Map) toaMap.get(cargoType);
				if (null != edmMap && !edmMap.isEmpty()){
					if(edmMap.get(attribute)!=null){
						Double reqValue = Double.parseDouble((String) edmMap.get(attribute));
						return reqValue;
					}
				}
			}
		}


		return 0.0;
	} 
	
	
	public boolean eligibleChildMovedToLicensedFosterHome(){
		FilingUnit fu=this.getFilingUnitList().get(0);
		if(null != fu.getFilingUnitPersonList() && !fu.getFilingUnitPersonList().isEmpty()){
			for(FilingUnitPerson fup:fu.getFilingUnitPersonList()){
				Person per = fup.getPerson();
				Long indivId = per.getPersonID();
				String partStatusCd = lookupStatusData(indivId, "PreviousOngoingAuthorizedRecord", "EDG_PART_STATUS_CODE");
			    if(null != partStatusCd && !partStatusCd.isEmpty()){			    	
			    	if((EnumPartStatusCode.EC.toString()).equals(partStatusCd)){
			    		if(EnumPartStatusCode.XC.equals(fup.getPartStatusCode())){	
			    			if(EnumLivingArrangement.FO.equals(per.getLaTypeCd())){
			    				return true;
			    			}		    			
			    		}			    	
			    	}
			    }			
			}
		}	
		return false;
	}
	
	
	public boolean eligibleChildAboveEighteenDropsSchool(Date edm){
		FilingUnit fu=this.getFilingUnitList().get(0);
		if(null != fu.getFilingUnitPersonList() && !fu.getFilingUnitPersonList().isEmpty()){
			for(FilingUnitPerson fup:fu.getFilingUnitPersonList()){
				Person per = fup.getPerson();
				Long indivId = per.getPersonID();
				String partStatusCd = lookupStatusData(indivId, "PreviousOngoingAuthorizedRecord", "EDG_PART_STATUS_CODE");		
			    if(null != partStatusCd && !partStatusCd.isEmpty()){
			    	if((EnumPartStatusCode.EC.toString()).equals(partStatusCd)){
			    		if(EnumPartStatusCode.XC.equals(fup.getPartStatusCode())){
			    			if(per.getAgeInMonths(edm) >= (18*12) ){
			    				if(EnumSchoolEnrollment.NS.equals(per.getSchoolEnrollmentCode())){
				    				return true;
				    			}
			    			}		    			
			    		}			    	
			    	}
			    }			
			}
		}	
		return false;
	}
	
	
	public boolean changeReportedTimelyForDroppingSchool(){
		int dropsCount=0, reportTimelyCount=0;
		FilingUnit fu=this.getFilingUnitList().get(0);
		if(null != fu.getFilingUnitPersonList() && !fu.getFilingUnitPersonList().isEmpty()){
			for(FilingUnitPerson fup:fu.getFilingUnitPersonList()){
				Person per = fup.getPerson();
				Long indivId = per.getPersonID();
				String partStatusCd = lookupStatusData(indivId, "PreviousOngoingAuthorizedRecord", "EDG_PART_STATUS_CODE");
			    if(null != partStatusCd && !partStatusCd.isEmpty()){
			    	if((EnumPartStatusCode.EC.toString()).equals(partStatusCd)){
			    		if(EnumPartStatusCode.XC.equals(fup.getPartStatusCode())){
			    			if(EnumSchoolEnrollment.NS.equals(per.getSchoolEnrollmentCode())){
			    				dropsCount = dropsCount + 1;			    							    				
			    				if(DateUtilities.compareDatesGreaterThanEqualTo(DateUtilities.addDaysToDate(per.getDiscoveryDateForEducation(), 5), per.getEducationReportDate()).equals(EnumYesNo.Y)){
			    					reportTimelyCount = reportTimelyCount + 1;
			    				}
			    			}		    			
			    		}			    	
			    	}
			    }			
			}
			if(0 != dropsCount && dropsCount == reportTimelyCount){
				return true;
			}
		}	
		return false;
	}
	
	public boolean edmGreaterThanChangeStartDateForLivingArrChange(Date edm){
		int laChangeCount = 0, edmGreaterCnt=0;
		FilingUnit fu=this.getFilingUnitList().get(0);
		if(null != fu.getFilingUnitPersonList() && !fu.getFilingUnitPersonList().isEmpty()){
			for(FilingUnitPerson fup:fu.getFilingUnitPersonList()){
				Person per = fup.getPerson();
				Long indivId = per.getPersonID();
				String partStatusCd = lookupStatusData(indivId, "PreviousOngoingAuthorizedRecord", "EDG_PART_STATUS_CODE");
			    if(null != partStatusCd && !partStatusCd.isEmpty()){			    	
			    	if((EnumPartStatusCode.EC.toString()).equals(partStatusCd)){
			    		if(EnumPartStatusCode.XC.equals(fup.getPartStatusCode())){
			    			if(EnumLivingArrangement.FO.equals(per.getLaTypeCd())){
			    				laChangeCount = laChangeCount + 1;			    				
			    				if(DateUtilities.compareInMonthsGreaterThan(edm, per.getLivingArrStrtChangeDt()).equals(EnumYesNo.Y)){
			    					edmGreaterCnt = edmGreaterCnt + 1;
			    				}
			    			}		    			
			    		}			    	
			    	}
			    }			
			}			
			if(0 != laChangeCount && laChangeCount == edmGreaterCnt){
				return true;
			}
		}	
		return false;
	}
	
	public boolean edmGreaterThanChangeStartDateForDroppingSchool(Date edm){
		int dropsCount=0, edmGreaterCount=0;
		FilingUnit fu=this.getFilingUnitList().get(0);
		if(null != fu.getFilingUnitPersonList() && !fu.getFilingUnitPersonList().isEmpty()){
			for(FilingUnitPerson fup:fu.getFilingUnitPersonList()){
				Person per = fup.getPerson();
				Long indivId = per.getPersonID();
				String partStatusCd = lookupStatusData(indivId, "PreviousOngoingAuthorizedRecord", "EDG_PART_STATUS_CODE");
			    if(null != partStatusCd && !partStatusCd.isEmpty()){
			    	if((EnumPartStatusCode.EC.toString()).equals(partStatusCd)){
			    		if(EnumPartStatusCode.XC.equals(fup.getPartStatusCode())){
			    			if(EnumSchoolEnrollment.NS.equals(per.getSchoolEnrollmentCode())){
			    				dropsCount = dropsCount + 1;
			    				if(DateUtilities.compareInMonthsGreaterThan(edm, per.getEduStrtChangeDt()).equals(EnumYesNo.Y)){
			    					edmGreaterCount = edmGreaterCount + 1;
			    				}
			    			}		    			
			    		}			    	
			    	}
			    }			
			}
			if(0 != dropsCount && dropsCount == edmGreaterCount){
				return true;
			}
		}	
		return false;
	}
	
	public boolean edmGreaterThanEqualToChangeDateForIndividualLeftHousehold(Date edm){
		FilingUnit fu=this.getFilingUnitList().get(0);
		if(null != fu.getFilingUnitPersonList() && !fu.getFilingUnitPersonList().isEmpty()){
			for(FilingUnitPerson fup:fu.getFilingUnitPersonList()){
				Person per = fup.getPerson();
				Long indivId = per.getPersonID();
				String partStatusCd = lookupStatusData(indivId, "PreviousOngoingAuthorizedRecord", "EDG_PART_STATUS_CODE");				
			    if(null != partStatusCd && !partStatusCd.isEmpty()){
			    	if((EnumPartStatusCode.EA.toString()).equals(partStatusCd) || (EnumPartStatusCode.IA.toString()).equals(partStatusCd) || (EnumPartStatusCode.EC.toString()).equals(partStatusCd) || (EnumPartStatusCode.IC.toString()).equals(partStatusCd) || (EnumPartStatusCode.ET.toString()).equals(partStatusCd)){
			    		if(per.getHouseholdStatus().equals(EnumHouseholdStatus.N)){
			    			if(DateUtilities.compareInMonthsGreaterThanEqualTo(edm, per.getEffBeginDateDcIndvHHStatus()).equals(EnumYesNo.Y)){
		    					return true; 
		    				}			    							    			                       
	    				}			    				
			    	}
			    }			
			}
		}		
		return false;
	}
	
	
	public boolean edmGreaterThanEqualToChangeDateForNewlyAddedMember(Date edm){
		boolean newMember=false;
        FilingUnit fu=this.getFilingUnitList().get(0);
		if(null != fu.getFilingUnitPersonList() && !fu.getFilingUnitPersonList().isEmpty()){
			for(FilingUnitPerson fup: fu.getFilingUnitPersonList()){
				Person per=fup.getPerson();
				Long indivId = per.getPersonID();
				newMember = lookupStatusDataForNewIndividual(indivId);				
				if(true==newMember){					
					if(EnumEligibilityGroupIndicator.C.equals(fup.getEligibilityGroupIndicator())){
						if(DateUtilities.compareInMonthsGreaterThanEqualTo(edm, per.getEffBeginDateDcIndvHHStatus()).equals(EnumYesNo.Y)){
	    					return true; 
	    				}
					}
				}				
			}
		}
		return false;
	}

	public boolean isBenefitsIssuedEDM() {
		return benefitsIssuedEDM;
	}

	public void setBenefitsIssuedEDM(boolean benefitsIssuedEDM) {
		this.benefitsIssuedEDM = benefitsIssuedEDM;
	}


	public boolean isReceivingAssetForCCAP() {
		return receivingAssetForCCAP;
	}

	public void setReceivingAssetForCCAP(boolean receivingAssetForCCAP) {
		this.receivingAssetForCCAP = receivingAssetForCCAP;
	}
	
		private String countyCd;
		private boolean ineligibleForTribalLiheap;
		
		/*Added by Vikram for LIHEAP Details Screen*/
		private boolean ownRentHomeCd;
		private boolean rntIncludeHeatingSw;
		private boolean rntFreeHmeSw;
		private boolean rcvRentalAsstSw;
		private double estimatedCostOfHeat;
		private EnumPrimaryHeat liheapPrimaryHeatType;
		/*Changes by Vikram End Here*/
		
		public String getCountyCd() {
			return countyCd;
		}
		public void setCountyCd(String countyCd) {
			this.countyCd = countyCd;
		}
		public boolean isIneligibleForTribalLiheap() {
			return ineligibleForTribalLiheap;
		}
		public void setIneligibleForTribalLiheap(boolean ineligibleForTribalLiheap) {
			this.ineligibleForTribalLiheap = ineligibleForTribalLiheap;
		}
		public boolean isOwnRentHomeCd() {
			return ownRentHomeCd;
		}

		public void setOwnRentHomeCd(boolean ownRentHomeCd) {
			this.ownRentHomeCd = ownRentHomeCd;
		}

		public boolean isRntIncludeHeatingSw() {
			return rntIncludeHeatingSw;
		}

		public void setRntIncludeHeatingSw(boolean rntIncludeHeatingSw) {
			this.rntIncludeHeatingSw = rntIncludeHeatingSw;
		}

		public boolean isRntFreeHmeSw() {
			return rntFreeHmeSw;
		}

		public void setRntFreeHmeSw(boolean rntFreeHmeSw) {
			this.rntFreeHmeSw = rntFreeHmeSw;
		}

		public boolean isRcvRentalAsstSw() {
			return rcvRentalAsstSw;
		}

		public void setRcvRentalAsstSw(boolean rcvRentalAsstSw) {
			this.rcvRentalAsstSw = rcvRentalAsstSw;
		}
		
		public boolean allIndividualsAmericanIndians(){
			int perCount = 0, aiCount = 0;
	        FilingUnit fu=this.getFilingUnitList().get(0);
			if(null != fu.getFilingUnitPersonList() && !fu.getFilingUnitPersonList().isEmpty()){
				for(FilingUnitPerson fup: fu.getFilingUnitPersonList()){
					perCount = perCount + 1; 
					Person per=fup.getPerson();
					if(EnumRace.AI.equals(per.getRace())){
						aiCount = aiCount + 1;
					}				
				}
				if(0 != perCount && perCount == aiCount){
					return true;
				}
			}
			return false;
		}
	
	private Date monthlyReportReceivedDate;

	public Date getMonthlyReportReceivedDate() {
		return monthlyReportReceivedDate;
	}

	public void setMonthlyReportReceivedDate(Date monthlyReportReceivedDate) {
		this.monthlyReportReceivedDate = monthlyReportReceivedDate;
	}

	/**
	 * @param minDate 
	 * Return false only if any of the income is reported untimely Discovery
	 * date = 01 and Reported date = 11 -->true
	 */
	public boolean checkChangeIncomeReportTimely() {
		boolean result = true;
		FilingUnit fu = this.getFilingUnitList().get(0);
		if (null != fu.getFilingUnitPersonList()
				&& !fu.getFilingUnitPersonList().isEmpty()) {
			for (FilingUnitPerson fup : fu.getFilingUnitPersonList()) {
				Person per = fup.getPerson();
				ArrayList<Income> incList = per.getIncomeList();
				if (!(fup.getEligibilityGroupIndicator()
						.equals(EnumEligibilityGroupIndicator.X))
						&& null != incList && !incList.isEmpty()) {
					for (Income inc : incList) {
						if (!inc.isTimelySw() && !inc.isExemptIncome()) {
							if (maxVerfDate == null
									|| EnumYesNo.Y.equals(DateUtilities
											.compareDatesGreaterThan(inc
													.getIncomeVerfReceivedDt(),
													maxVerfDate))) {
								maxVerfDate = inc.getIncomeVerfReceivedDt();
								System.out.println("maxVerfDate Income timely "+maxVerfDate);
							}
							result = false;
						}
						if (EnumYesNo.Y.equals(DateUtilities
								.compareDatesGreaterThanEqualTo(
										inc.getIncomeReportDate(),
										minProgramDate))) {
							if (minCSCD == null
									|| EnumYesNo.Y.equals(DateUtilities
											.compareDatesGreaterThan(minCSCD,
													inc.getIncomeEffBeginDt()))) {
								minCSCD = inc.getIncomeEffBeginDt();
								System.out.println("CCAP-Income-set-Min CSCD "+inc.getDescriptionText() + minCSCD);
							}
						}
					}
				}
			}
		}
		return result;
	}

	/**
	 * Return false only if any of the expense is reported untimely Discovery
	 * date = 01 and Reported date = 11 -->true 
	 */
	public boolean checkChangeExpenseReportTimely() {
		boolean result = true;
		FilingUnit fu = this.getFilingUnitList().get(0);
		if (null != fu.getFilingUnitPersonList()
				&& !fu.getFilingUnitPersonList().isEmpty()) {
			for (FilingUnitPerson fup : fu.getFilingUnitPersonList()) {
				Person per = fup.getPerson();
				ArrayList<Expense> expList = per.getExpenseList();
				if (!(fup.getEligibilityGroupIndicator()
						.equals(EnumEligibilityGroupIndicator.X))
						&& null != expList && !expList.isEmpty()) {
					for (Expense exp : expList) {
						if (!exp.isTimelySw() && !exp.isExemptExpense()) {
							if (maxVerfDate == null
									|| EnumYesNo.Y
											.equals(DateUtilities.compareDatesGreaterThan(
													exp.getExpenseVerfReceivedDt(),
													maxVerfDate))) {
								maxVerfDate = exp.getExpenseVerfReceivedDt();
								System.out.println("maxVerfDate expense timely "+maxVerfDate);
							}
							result = false;
						}
						if (EnumYesNo.Y.equals(DateUtilities
								.compareDatesGreaterThanEqualTo(
										exp.getExpenseReportDt(),
										minProgramDate))) {
							if (minCSCD == null
									|| EnumYesNo.Y
											.equals(DateUtilities.compareDatesGreaterThan(
													minCSCD,
													exp.getExpenseEffBeginDt()))) {
								minCSCD = exp.getExpenseEffBeginDt();
								System.out.println("CCAP-Expense-set-Min CSCD "+exp.getDescriptionText() + minCSCD);
							}
						}
					}
				}
			}
		}
		return result;
	}

	
	/**
	 * @param minDate 
	 * Return false only if any of the income is reported untimely Discovery
	 * date = 01 and Reported date = 11 -->true
	 */
	public boolean checkChangeIncomeVerifiedTimely() {
		boolean result = true;
		FilingUnit fu = this.getFilingUnitList().get(0);
		if (null != fu.getFilingUnitPersonList()
				&& !fu.getFilingUnitPersonList().isEmpty()) {
			for (FilingUnitPerson fup : fu.getFilingUnitPersonList()) {
				Person per = fup.getPerson();
				ArrayList<Income> incList = per.getIncomeList();
				if (!(fup.getEligibilityGroupIndicator()
						.equals(EnumEligibilityGroupIndicator.X))
						&& null != incList && !incList.isEmpty()) {
					for (Income inc : incList) {
						if ((DateUtilities.compareDatesGreaterThan(
								inc.getIncomeVerfReceivedDt(),
								DateUtilities.addDaysToDate(
										inc.getIncomeReportDate(), 10))
								.equals(EnumYesNo.Y)) && !inc.isExemptIncome()) {
							if (maxVerfDate == null
									|| EnumYesNo.Y.equals(DateUtilities
											.compareDatesGreaterThan(inc
													.getIncomeVerfReceivedDt(),
													maxVerfDate))) {
								maxVerfDate = inc.getIncomeVerfReceivedDt();
								System.out.println("maxVerfDate Income timely 2 "+maxVerfDate);
							}
							result = false;
						}
					}
				}
			}
		}
		return result;
	}
	/**
	 * Return false only if any of the expense is verified untimely Reported
	 * date = 01 and Verified date = 11 -->true
	 */
	public boolean checkChangeExpenseVerifiedTimely() {
		boolean result = true;
		FilingUnit fu = this.getFilingUnitList().get(0);
		if (null != fu.getFilingUnitPersonList()
				&& !fu.getFilingUnitPersonList().isEmpty()) {
			for (FilingUnitPerson fup : fu.getFilingUnitPersonList()) {
				Person per = fup.getPerson();
				ArrayList<Expense> expList = per.getExpenseList();
				if (!(fup.getEligibilityGroupIndicator()
						.equals(EnumEligibilityGroupIndicator.X))
						&& null != expList && !expList.isEmpty()) {
					for (Expense exp : expList) {
						if ((DateUtilities.compareDatesGreaterThan(
								exp.getExpenseVerfReceivedDt(),
								DateUtilities.addDaysToDate(
										exp.getExpenseReportDt(), 10))
								.equals(EnumYesNo.Y)) && !exp.isExemptExpense()) {
							if (maxVerfDate == null
									|| EnumYesNo.Y
											.equals(DateUtilities.compareDatesGreaterThan(
													exp.getExpenseVerfReceivedDt(),
													maxVerfDate))) {
								maxVerfDate = exp.getExpenseVerfReceivedDt();
								System.out.println("maxVerfDate expense timely 2"+maxVerfDate);
							}
							result = false;
						}
					}
				}
			}
		}
		return result;
	}

	/**
	 * Return false only if any of the allowable Activity is reported untimely
	 * Discovery date = 01 and Reported date = 11 true
	 */
	public boolean checkChangedAllowableActivityReportTimely() {
		boolean result = true;
		FilingUnit fu = this.getFilingUnitList().get(0);
		if (null != fu.getFilingUnitPersonList()
				&& !fu.getFilingUnitPersonList().isEmpty()) {
			for (FilingUnitPerson fup : fu.getFilingUnitPersonList()) {
				if (!fup.getPartStatusCode().equals(EnumPartStatusCode.ET)) {
					continue;
				}
				Person per = fup.getPerson();
				ArrayList<AllowableActivities> allowAct = per.getAllowableAct();
				if (null != allowAct && !allowAct.isEmpty()) {
					for (AllowableActivities act : allowAct) {
						if (DateUtilities.compareDatesGreaterThan(
								act.getReportDate(),
								DateUtilities.addDaysToDate(
										act.getActivityDiscoveryDt(), 10))
								.equals(EnumYesNo.Y)) {
							if (maxVerfDate == null
									|| EnumYesNo.Y
											.equals(DateUtilities.compareDatesGreaterThan(
													act.getActivityVerfReceivedDt(),
													maxVerfDate))) {
								maxVerfDate = act.getActivityVerfReceivedDt();
								System.out.println("maxVerfDate activity timely "+maxVerfDate);
							}
							result = false;
						}
					}
				}
			}
		}
		return result;
	}

	/**
	 * Return false only if any of the child care needs is reported untimely
	 * Discovery date = 01 and Reported date = 10 -->true Discovery date = 01
	 * and Reported date = 11 -->false
	 */
	public boolean checkChangedChildCareNeedsReportTimely() {
		boolean result = true;
		FilingUnit fu = this.getFilingUnitList().get(0);
		if (null != fu.getFilingUnitPersonList()
				&& !fu.getFilingUnitPersonList().isEmpty()) {
			for (FilingUnitPerson fup : fu.getFilingUnitPersonList()) {
				if (!fup.getPartStatusCode().equals(EnumPartStatusCode.EC)) {
					continue;
				}
				Person per = fup.getPerson();
				if (DateUtilities.compareDatesGreaterThan(
						per.getChildCareReportedDt(),
						DateUtilities.addDaysToDate(
								per.getChildCareDiscoveryDt(), 10)).equals(
						EnumYesNo.Y)) {
					if (maxVerfDate == null
							|| EnumYesNo.Y
									.equals(DateUtilities.compareDatesGreaterThan(
											per.getChildCareVerfReceivedDt(),
											maxVerfDate))) {
						maxVerfDate = per.getChildCareVerfReceivedDt();
						System.out.println("maxVerfDate child care timely "+maxVerfDate);
					}
					result = false;
					
				}
				if (EnumYesNo.Y.equals(DateUtilities
						.compareDatesGreaterThanEqualTo(
								per.getChildCareReportedDt(), minProgramDate))) {
					if (minCSCD == null
							|| EnumYesNo.Y.equals(DateUtilities
									.compareDatesGreaterThan(minCSCD,
											per.getChildCareEffBeginDt()))) {
						minCSCD = per.getChildCareEffBeginDt();
						System.out.println("CCAP-ChildCare-set-Min CSCD "+per.getPersonName() + minCSCD);
					}
				}
			}
		}
		return result;
	}

	/**
	 * Return false only if any of the allowable activity is verified untimely
	 * Reported date = 01 and Verified date = 10 -->true Reported date = 01 and
	 * Verified date = 11 -->false
	 */
	public boolean checkChangedAllowableActivityVerifiedTimely() throws ParseException {
		FilingUnit fu = this.getFilingUnitList().get(0);
		ArrayList<Verification> verificationList = fu.getVerificationList();
		Date VrfDueDt = null;
		Date VrfRecvdDt = null;
		boolean result = true;
		if (null != fu.getFilingUnitPersonList() && !fu.getFilingUnitPersonList().isEmpty()) {
			for (FilingUnitPerson fup : fu.getFilingUnitPersonList()) {
				if (!fup.getPartStatusCode().equals(EnumPartStatusCode.ET)) {
					continue;
				}
				Person per = fup.getPerson();
				if (null != verificationList && !verificationList.isEmpty()) {
					for (Verification verf : verificationList) {
						if (verf.getVclIndvId() == fup.getPerson().getPersonID()) {
							Date lastAuthDate = lookupLastAuthDate();
							if (DateUtilities.compareDatesGreaterThan(verf.getVclDueDt(), lastAuthDate)
									.equals(EnumYesNo.Y) && verf.getVclCode().equals(EnumVCLCode.V600)) {
								VrfDueDt = verf.getVclDueDt();
								VrfRecvdDt = fu.getEdbcrundt();
								if (DateUtilities.compareDatesGreaterThan(VrfRecvdDt, VrfDueDt).equals(EnumYesNo.Y)) {
									if (maxVerfDate == null || EnumYesNo.Y
											.equals(DateUtilities.compareDatesGreaterThan(VrfRecvdDt, maxVerfDate))) {
										maxVerfDate = VrfRecvdDt;
										System.out.println("maxVerfDate activity verified timely " + maxVerfDate);
									}
									result = false;
								}
							}
						}
					}
				}

				ArrayList<AllowableActivities> allowAct = per.getAllowableAct();
				if (null != allowAct && !allowAct.isEmpty()) {
					for (AllowableActivities act : allowAct) {
						if (DateUtilities.compareDatesGreaterThan(act.getActivityVerfReceivedDt(),
								DateUtilities.addDaysToDate(act.getReportDate(), 10)).equals(EnumYesNo.Y)) {
							if (maxVerfDate == null || EnumYesNo.Y.equals(DateUtilities
									.compareDatesGreaterThan(act.getActivityVerfReceivedDt(), maxVerfDate))) {
								maxVerfDate = act.getActivityVerfReceivedDt();
								System.out.println("maxVerfDate allowable activity verified timely " + maxVerfDate);
							}
							result = false;
						}
					}
				}

			}
		}
		return result;
	}

	/**
	 * Return false only if any of the child care needs is verified untimely
	 * Reported date = 01 and Verified date = 10 -->true Reported date = 01 and
	 * Verified date = 11 -->false
	 */
	public boolean checkChangedChildCareNeedsVerifiedTimely() {
		FilingUnit fu = this.getFilingUnitList().get(0);
		boolean result = true;
		if (null != fu.getFilingUnitPersonList() && !fu.getFilingUnitPersonList().isEmpty() && fu.var9000_350_CCAP) {
			for (FilingUnitPerson fup : fu.getFilingUnitPersonList()) {
				if (!fup.getPartStatusCode().equals(EnumPartStatusCode.EC)) {
					continue;
				}
				Person per = fup.getPerson();
				if (DateUtilities.compareDatesGreaterThan(per.getChildCareVerfReceivedDt(),
						DateUtilities.addDaysToDate(per.getChildCareReportedDt(), 10)).equals(EnumYesNo.Y)) {
					if (maxVerfDate == null || EnumYesNo.Y.equals(
							DateUtilities.compareDatesGreaterThan(per.getChildCareVerfReceivedDt(), maxVerfDate))) {
						maxVerfDate = per.getChildCareVerfReceivedDt();
						System.out.println("maxVerfDate child care verified timely " + maxVerfDate);
						System.out.println("var9000_350_CCAP" + fu.var9000_350_CCAP);
					}
					result = false;
				}
			}
		}
		return result;
	}

	
	public double getEstimatedCostOfHeat() {
		return estimatedCostOfHeat;
	}

	public void setEstimatedCostOfHeat(double estimatedCostOfHeat) {
		this.estimatedCostOfHeat = estimatedCostOfHeat;
	}

	public EnumPrimaryHeat getLiheapPrimaryHeatType() {
		return liheapPrimaryHeatType;
	}

	public void setLiheapPrimaryHeatType(EnumPrimaryHeat liheapPrimaryHeatType) {
		this.liheapPrimaryHeatType = liheapPrimaryHeatType;
	}
	private double liheapHouseRentAmount;
	public double getLiheapHouseRentAmount() {
		return liheapHouseRentAmount;
	}

	public void setLiheapHouseRentAmount(double liheapHouseRentAmount) {
		this.liheapHouseRentAmount = liheapHouseRentAmount;
	}
	private String heatingSeason;
	private int liheapNumberOfBedrooms;
	private EnumTypeOfHome liheapTypeOfHome;
	private EnumLiheapType liheapType;
	private boolean liheapHomeSplitLevel; 
	public String getHeatingSeason() {
		return heatingSeason;
	}

	public void setHeatingSeason(String heatingSeason) {
		this.heatingSeason = heatingSeason;
	}
	
	public int getLiheapNumberOfBedrooms() {
		return liheapNumberOfBedrooms;
	}
	
	public void setLiheapNumberOfBedrooms(int liheapNumberOfBedrooms) {
		this.liheapNumberOfBedrooms = liheapNumberOfBedrooms;
	}
	
	public EnumLiheapType getLiheapType() {
		return liheapType;
	}

	public void setLiheapType(EnumLiheapType liheapType) {
		this.liheapType = liheapType;
	}

	public EnumTypeOfHome getLiheapTypeOfHome() {
		return liheapTypeOfHome;
	}

	public void setLiheapTypeOfHome(EnumTypeOfHome liheapTypeOfHome) {
		this.liheapTypeOfHome = liheapTypeOfHome;
	}

	public boolean isLiheapHomeSplitLevel() {
		return liheapHomeSplitLevel;
	}

	public void setLiheapHomeSplitLevel(boolean liheapHomeSplitLevel) {
		this.liheapHomeSplitLevel = liheapHomeSplitLevel;
	}
	
	public Date liheapPrevHeatingSeason(){
		if(this.heatingSeason == null){
			return null;
		}
		int prevHeatingSeason=Integer.parseInt(this.heatingSeason.trim())-1;
	//	heatingSeason = DateUtilities.nullifyTime(heatingSeason);
		Calendar appCal = Calendar.getInstance();
	//	appCal.setTime(heatingSeason);
		int appYear = appCal.get(Calendar.YEAR);
		
		Calendar liheapHeatSeason = Calendar.getInstance();		
		liheapHeatSeason.set(Calendar.DAY_OF_MONTH, 31);
		liheapHeatSeason.set(Calendar.MONTH,4 );
		liheapHeatSeason.set(Calendar.YEAR, prevHeatingSeason);
		//expenseStartCal.add(Calendar.DATE, 1);
		
		Date liheapPrevHeatYear = DateUtilities.nullifyTime(liheapHeatSeason.getTime());
		
	
		return liheapPrevHeatYear;
	}
	
	public boolean isEmergencyLiheap;


	public boolean isEmergencyLiheap() {
		return isEmergencyLiheap;
	}

	public void setEmergencyLiheap(boolean isEmergencyLiheap) {
		this.isEmergencyLiheap = isEmergencyLiheap;
	}
	public EnumResult appMonthFinTestResult;
	public EnumResult getAppMonthFinTestResult() {
		return appMonthFinTestResult;
	}
	public void setAppMonthFinTestResult(EnumResult appMonthFinTestResult) {
		this.appMonthFinTestResult = appMonthFinTestResult;
	}
	public boolean isLiheapPriorMonthRequested;
	public boolean isLiheapPriorMonthRequested() {
		return isLiheapPriorMonthRequested;
	}
	public void setLiheapPriorMonthRequested(boolean isLiheapPriorMonthRequested) {
		this.isLiheapPriorMonthRequested = isLiheapPriorMonthRequested;
	}
	private FilingUnit underpaymentFU;
	private FilingUnit overPaymentFU;
	
	
	public FilingUnit getOverPaymentFU() {
		return overPaymentFU;
	}

	public void setOverPaymentFU(FilingUnit overPaymentFU) {
		this.overPaymentFU = overPaymentFU;
	}

	public FilingUnit getUnderpaymentFU() {
		return underpaymentFU;
	}

	public void setUnderpaymentFU(FilingUnit underpaymentFU) {
		this.underpaymentFU = underpaymentFU;
	}
	
	
	public void createUnderpaymentFUObject(double currentBenefitAmt, double priorBenefitAmt, EnumBenefitStatusCode code){
		FilingUnit currFu = this.getFilingUnitList().get(0);
		FilingUnit clonedFu = Utilities.cloneObject(currFu);
		
		clonedFu.setActivityType(EnumCaseMode.IR);
		clonedFu.setAdverseActionAllowedSw(false);
		clonedFu.setBenefitAmount(currentBenefitAmt);
		clonedFu.setBenefitStatusCode(code);
		if(code == EnumBenefitStatusCode.IN){
			clonedFu.setEdbcActionCode(EnumEDBCActionCode.UNDERPAYMENT);
			clonedFu.setNetBenefitAmount(currentBenefitAmt-priorBenefitAmt);
			this.underpaymentFU = clonedFu;
		}else{
			clonedFu.setEdbcActionCode(EnumEDBCActionCode.PENDING_OVERPAYMENT);
			clonedFu.setNetBenefitAmount(priorBenefitAmt-currentBenefitAmt);
			clonedFu.setRecoupmentAdviceSw(true);
			this.overPaymentFU = clonedFu;	
		}
	}
	public boolean isTANFBenefitTimelyReported() {
		return TANFBenefitTimelyReported;
	}

	public void setTANFBenefitTimelyReported(boolean tANFBenefitTimelyReported) {
		TANFBenefitTimelyReported = tANFBenefitTimelyReported;
	}	
	public Date getLiheapCutOffDt() {
			return liheapCutOffDt;
		}

		public void setLiheapCutOffDt(Date liheapCutOffDt) {
			this.liheapCutOffDt = liheapCutOffDt;
		}
	
	private Date emergencyLiheapStartDate;
	private Date emergencyLiheapEndDate;
	public Date getEmergencyLiheapStartDate() {
		return emergencyLiheapStartDate;
	}

	public void setEmergencyLiheapStartDate(Date emergencyLiheapStartDate) {
		this.emergencyLiheapStartDate = emergencyLiheapStartDate;
	}

	public Date getEmergencyLiheapEndDate() {
		return emergencyLiheapEndDate;
	}

	public void setEmergencyLiheapEndDate(Date emergencyLiheapEndDate) {
		this.emergencyLiheapEndDate = emergencyLiheapEndDate;
	}
	
	public boolean checkForComplianceAndGoodCause(Date complyDt, Date goodCauseDt){
		
		

		
		boolean checkSw1 = false, checkSw2 = false;
		if(null != this.getApplicationDate()){
			if(null != complyDt){
				if(DateUtilities.compareDatesEqualTo(this.getApplicationDate(), complyDt).equals(EnumYesNo.N)){
				checkSw1 = true;
					if(DateUtilities.compareInMonthsGreaterThan(this.eligibilityDeterminationMonth, complyDt).equals(EnumYesNo.N)){
					checkSw2 = true;
						if(null != goodCauseDt){
							if(DateUtilities.compareDatesGreaterThan(complyDt, goodCauseDt).equals(EnumYesNo.Y)){
								if(DateUtilities.compareDatesEqualTo(this.getApplicationDate(), goodCauseDt).equals(EnumYesNo.N)){
									if(DateUtilities.compareInMonthsGreaterThan(this.eligibilityDeterminationMonth, goodCauseDt).equals(EnumYesNo.N)){
										if(DateUtilities.compareInMonthsEqualTo(this.eligibilityDeterminationMonth, goodCauseDt).equals(EnumYesNo.Y)){
											return true;
										}
									}
								}
							}else{
								if(DateUtilities.compareInMonthsEqualTo(this.eligibilityDeterminationMonth, complyDt).equals(EnumYesNo.Y)){
									return true;
								}
							}	
						}else{
							if(DateUtilities.compareInMonthsEqualTo(this.eligibilityDeterminationMonth, complyDt).equals(EnumYesNo.Y)){
								return true;
							}
						}
					}
				}
			}
			if(null != goodCauseDt && false == checkSw1 && false == checkSw2){
				if(DateUtilities.compareDatesEqualTo(this.getApplicationDate(), goodCauseDt).equals(EnumYesNo.N)){
					if(DateUtilities.compareInMonthsGreaterThan(this.eligibilityDeterminationMonth, goodCauseDt).equals(EnumYesNo.N)){
						if(DateUtilities.compareInMonthsEqualTo(this.eligibilityDeterminationMonth, goodCauseDt).equals(EnumYesNo.Y)){
							return true;
						}	
					}
				}
			}
			
		}
		return false;
	}
	
	
    private boolean supplDueToOnDecInIncSw;
	
	public boolean isSupplDueToOnDecInIncSw() {
		return supplDueToOnDecInIncSw;
	}

	public void setSupplDueToOnDecInIncSw(boolean supplDueToOnDecInIncSw) {
		this.supplDueToOnDecInIncSw = supplDueToOnDecInIncSw;
	}
public int liheapStartMonth;
	public int liheapEndMonth;

	public int getLiheapStartMonth() {
		return liheapStartMonth;
	}

	public void setLiheapStartMonth(int liheapStartMonth) {
		this.liheapStartMonth = liheapStartMonth;
	}

	public int getLiheapEndMonth() {
		return liheapEndMonth;
	}

	public void setLiheapEndMonth(int liheapEndMonth) {
		this.liheapEndMonth = liheapEndMonth;
	}
	
	private EnumInterviewStatus interviewIntakeStatus = EnumInterviewStatus.NULL;
	private EnumInterviewStatus interviewReviewStatus = EnumInterviewStatus.NULL;

	public EnumInterviewStatus getInterviewIntakeStatus() {
		return interviewIntakeStatus;
	}

	public void setInterviewIntakeStatus(EnumInterviewStatus interviewIntakeStatus) {
		this.interviewIntakeStatus = interviewIntakeStatus;
	}
	public EnumInterviewStatus getInterviewReviewStatus() {
		return interviewReviewStatus;
	}
	public void setInterviewReviewStatus(EnumInterviewStatus interviewReviewStatus) {
		this.interviewReviewStatus = interviewReviewStatus;
	}	
	
	public Date getRunFromDate() {
		return runFromDate;
	}

	public void setRunFromDate(Date runFromDate) {
		this.runFromDate = runFromDate;
	}
	public boolean isProtectivePayeeInf() {
		return protectivePayeeInf;
	}

	public void setProtectivePayeeInf(boolean protectivePayeeInf) {
		this.protectivePayeeInf = protectivePayeeInf;
	}

	private boolean override;
	private OverrideDetails overrideDetails;
	public boolean isOverride() {
		return override;
	}
	public void setOverride(boolean override) {
		this.override = override;
	}
	public OverrideDetails getOverrideDetails() {
		return overrideDetails;
	}
	public void setOverrideDetails(OverrideDetails overrideDetails) {
		this.overrideDetails = overrideDetails;
	}
	public boolean isCaseDataChangePresent() {
		return caseDataChangePresent;
	}

	public void setCaseDataChangePresent(boolean caseDataChangePresent) {
		this.caseDataChangePresent = caseDataChangePresent;
	}
	
	public boolean isTaskActive() {
		return isTaskActive;
	}

	public void setTaskActive(boolean isTaskActive) {
		this.isTaskActive = isTaskActive;
	}
	
	public Date getMinCSCD() {
		return minCSCD;
	}

	public void setMinCSCD(Date minCSCD) {
		this.minCSCD = minCSCD;
	}

	public Date getMaxVerfDate() {
		return maxVerfDate;
	}

	public void setMaxVerfDate(Date maxVerfDate) {
		this.maxVerfDate = maxVerfDate;
	}
	
	/**
	 * Return false only if any of the non fin data is reported untimely Discovery
	 * date = 01 and Reported date = 11 -->true 
	 */
	public boolean checkChangeNonFinReportTimely() {
		boolean result = true;
		FilingUnit fu = this.getFilingUnitList().get(0);
		if (null != fu.getFilingUnitPersonList()
				&& !fu.getFilingUnitPersonList().isEmpty()) {
			for (FilingUnitPerson fup : fu.getFilingUnitPersonList()) {
				Person per = fup.getPerson();
				if (fup.getEligibilityGroupIndicator().equals(EnumEligibilityGroupIndicator.X) && 
						lookUpStatusByIndvId(fup.getPerson().getPersonID(), "PreviousOngoingAuthorizedRecord",
								"GROUP_IND").equals("X")) {
						continue;
				}
				ArrayList<NonFinData> nfList = per.getNonFinDataList();
				if (null != nfList && !nfList.isEmpty()) {
					for (NonFinData nf : nfList) {
						if (!nf.isTimelySw() && nf.isCcapSw()) {
							if (maxVerfDate == null
									|| EnumYesNo.Y.equals(DateUtilities
											.compareDatesGreaterThan(nf
													.getNonFinVerfReceivedDt(),
													maxVerfDate))) {
								maxVerfDate = nf.getNonFinVerfReceivedDt();
								System.out.println("CCAP-NonFin-set-Max Verf Dt " +nf.getNonFinDataType() + maxVerfDate);	
							}
							result= false;
						}
						if (EnumYesNo.Y
								.equals(DateUtilities
										.compareDatesGreaterThanEqualTo(
												nf.getNonFinReportDt(),
												minProgramDate)) && nf.isTimelySw()) {
							if (minCSCD == null
									|| EnumYesNo.Y.equals(DateUtilities
											.compareDatesGreaterThan(minCSCD,
													nf.getEffectiveBeginDt()))
									&& nf.isCcapSw()) {
								minCSCD = nf.getEffectiveBeginDt();
								System.out.println("CCAP-NonFin-set-Min CSCD " +nf.getNonFinDataType() + minCSCD);
							}
						}
					}
				}
			}
		}
		return result;
	}

	
	/**
	 * @param minDate 
	 * Return false only if any of the non fin is reported untimely Discovery
	 * date = 01 and Reported date = 11 -->true
	 */
	public boolean checkChangeNonFinVerifiedTimely() {
		boolean result = true;
		FilingUnit fu = this.getFilingUnitList().get(0);
		if (null != fu.getFilingUnitPersonList()
				&& !fu.getFilingUnitPersonList().isEmpty()) {
			for (FilingUnitPerson fup : fu.getFilingUnitPersonList()) {
				Person per = fup.getPerson();
				ArrayList<NonFinData> nfList = per.getNonFinDataList();
				if (null != nfList && !nfList.isEmpty()) {
					for (NonFinData nf : nfList) {
						if ((DateUtilities.compareDatesGreaterThan(
								nf.getNonFinVerfReceivedDt(),
								DateUtilities.addDaysToDate(
										nf.getNonFinReportDt(), 10))
								.equals(EnumYesNo.Y)) && nf.isCcapSw()) {
							if (maxVerfDate == null
									|| EnumYesNo.Y.equals(DateUtilities
											.compareDatesGreaterThan(nf
													.getNonFinVerfReceivedDt(),
													maxVerfDate))) {
								maxVerfDate = nf.getNonFinVerfReceivedDt();
								System.out.println("CCAP-NonFin-set-Max Verf Dt " +nf.getNonFinDataType() + maxVerfDate);
							}
							result = false;
						}
					}
				}
			}
		}
		return result;
	}
	
	/**
	 * @param minDate 
	 * Return false only if any of the changes is verified untimely Discovery
	 * date = 01 and Reported date = 11 -->true
	 */
	public boolean checkAllChangesVerifiedTimely() throws ParseException{
		boolean result = true;
		Date VrfDueDt = null;
		Date VrfRecvdDt = null;
		FilingUnit fu=this.getFilingUnitList().get(0);
		for (FilingUnitPerson fup : fu.getFilingUnitPersonList()) {
			Person per = fup.getPerson();
			if (fup.getEligibilityGroupIndicator().equals(EnumEligibilityGroupIndicator.X) && 
					lookUpStatusByIndvId(fup.getPerson().getPersonID(), "PreviousOngoingAuthorizedRecord",
							"GROUP_IND").equals("X")) {
					continue;
			}
			ArrayList<Verification> verificationList = per.getVerificationListForOUR();
			if (null != verificationList && !verificationList.isEmpty()) {
				for (Verification verf : verificationList) {
					Date lastAuthDate = lookupLastAuthDate();
					if(DateUtilities.compareDatesGreaterThan(verf.getVclDueDt(), lastAuthDate).equals(EnumYesNo.Y) &&
							verf.getVclTypeOfAssistance().equals(EnumTypeOfAssistance.CDCS)){
						VrfDueDt =  verf.getVclDueDt();
						VrfRecvdDt = verf.getValidSourceRcvDt();
						if(DateUtilities.compareDatesGreaterThan(VrfRecvdDt, VrfDueDt).equals(EnumYesNo.Y)){			
							if (maxVerfDate == null || EnumYesNo.Y.equals(DateUtilities
												.compareDatesGreaterThan(VrfRecvdDt,maxVerfDate))) {
								maxVerfDate = VrfRecvdDt;
								System.out.println("maxVerfDate all changes verified timely "+maxVerfDate);
							}
							result = false;		
						}
					}						
				}
			}
			
			ArrayList<NonFinData> nfList = per.getNonFinDataList();
			if (null != nfList && !nfList.isEmpty()) {
				for (NonFinData nf : nfList) {
					if ((DateUtilities.compareDatesGreaterThan(
							nf.getNonFinVerfReceivedDt(),
							DateUtilities.addDaysToDate(
									nf.getNonFinReportDt(), 10))
							.equals(EnumYesNo.Y)) && nf.isCcapSw()) {
						if (maxVerfDate == null
								|| EnumYesNo.Y.equals(DateUtilities
										.compareDatesGreaterThan(nf
												.getNonFinVerfReceivedDt(),
												maxVerfDate))) {
							maxVerfDate = nf.getNonFinVerfReceivedDt();
							System.out.println("CCAP-NonFin-set-Max Verf Dt " +nf.getNonFinDataType() + maxVerfDate);
						}
						result = false;
					}
				}
			}
			
		}
		
		boolean checkExpenseVerified = checkChangeExpenseVerifiedTimely();
		boolean checkIncomeVerified = checkChangeIncomeVerifiedTimely();
		if (!checkExpenseVerified || !checkIncomeVerified) {
			result = false;
		}
			return result;
	}
	
	public int levelOfCareIncreasedForAnyPerson(Map<Long, EnumCCLevelOfCare> indvLocMap){
		FilingUnit fu = this.getFilingUnitList().get(0);
		List<FilingUnitPerson> fupList = fu.getFilingUnitPersonList();
		if(fupList != null){
			for(FilingUnitPerson fup : fupList){
				long indvId = fup.getPerson().getPersonID();
				String prevLevelOfCare = this.lookUpStatusByIndvId(indvId, "PreviousOngoingAuthorizedRecord", "LEVEL_OF_CARE");
				if((null != prevLevelOfCare) && (!prevLevelOfCare.isEmpty())){
					if((indvLocMap != null) && (indvLocMap.containsKey(indvId))){
						EnumCCLevelOfCare overrideLoc = indvLocMap.get(indvId);
						if(overrideLoc != null){
							if(overrideLoc.toString().equalsIgnoreCase(prevLevelOfCare)){
								continue;
							}
							else if(fu.compLevelOfCare(prevLevelOfCare, overrideLoc)){
								return -1;
							}
							else{
								return 1;
							}
						}
					}
				}
			}
		}
		return 0;
	}
	
	/**
	 * @param minDate 
	 * Return false only if any of the income for this individual is reported untimely Discovery
	 * date = 01 and Reported date = 11 -->true
	 */
	public boolean checkChangeIncomeReportTimely(Person eligChild) {
		boolean result = true;
		FilingUnit fu = this.getFilingUnitList().get(0);
		if (null != fu.getFilingUnitPersonList()
				&& !fu.getFilingUnitPersonList().isEmpty()) {
			for (FilingUnitPerson fup : fu.getFilingUnitPersonList()) {
				Person per = fup.getPerson();
				if (fup.getPartStatusCode().equals(EnumPartStatusCode.EC) 
					&& !"EC".equals(lookUpStatusByIndvId(fup.getPerson().getPersonID(), "PreviousOngoingAuthorizedRecord","EDG_PART_STATUS_CODE"))
					&& (DateUtilities.compareInMonthsGreaterThanEqualTo(eligibilityDeterminationMonth, per.getEffBeginDateDcIndvHHStatus())==EnumYesNo.Y)) {
					ArrayList<Income> incList = per.getIncomeList();
					if (!(fup.getEligibilityGroupIndicator()
							.equals(EnumEligibilityGroupIndicator.X))
							&& null != incList && !incList.isEmpty()) {
						for (Income inc : incList) {
							if (!inc.isTimelySw() && !inc.isExemptIncome()) {
								if (maxVerfDate == null
										|| EnumYesNo.Y.equals(DateUtilities
												.compareDatesGreaterThan(inc
														.getIncomeVerfReceivedDt(),
														maxVerfDate))) {
									maxVerfDate = inc.getIncomeVerfReceivedDt();
									System.out.println("CCAP-Income-set-Max Verf Dt " +inc.getDescriptionText() + maxVerfDate);
								}
								result = false;
							}
						}
					}
				}
			}
		}
		return result;
	}
	
	/**
	 * Return false only if any of the expense for this individual is reported untimely Discovery
	 * date = 01 and Reported date = 11 -->true 
	 */
	public boolean checkChangeExpenseReportTimely(Person eligChild) {
		boolean result = true;
		FilingUnit fu = this.getFilingUnitList().get(0);
		if (null != fu.getFilingUnitPersonList()
				&& !fu.getFilingUnitPersonList().isEmpty()) {
			for (FilingUnitPerson fup : fu.getFilingUnitPersonList()) {
				Person per = fup.getPerson();
				if (fup.getPartStatusCode().equals(EnumPartStatusCode.EC) && 
				!"EC".equals(lookUpStatusByIndvId(fup.getPerson().getPersonID(), "PreviousOngoingAuthorizedRecord","EDG_PART_STATUS_CODE"))
				&& (DateUtilities.compareInMonthsGreaterThanEqualTo(eligibilityDeterminationMonth, per.getEffBeginDateDcIndvHHStatus())==EnumYesNo.Y)) {
					ArrayList<Expense> expList = per.getExpenseList();
					if (!(fup.getEligibilityGroupIndicator()
						.equals(EnumEligibilityGroupIndicator.X))
						&& null != expList && !expList.isEmpty()) {
						for (Expense exp : expList) {
							if (!exp.isTimelySw() && !exp.isExemptExpense()) {
								if (maxVerfDate == null
									|| EnumYesNo.Y
											.equals(DateUtilities.compareDatesGreaterThan(
													exp.getExpenseVerfReceivedDt(),
													maxVerfDate))) {
									maxVerfDate = exp.getExpenseVerfReceivedDt();
									System.out.println("CCAP-Expense-set-Max Verf Dt " +exp.getDescriptionText() + maxVerfDate);
								}
								result = false;
							}
						}
					}
				}
			}
		}
		return result;
	}
	

	/**
	 * Return false only if any of the non fin data for this individual is reported untimely Discovery
	 * date = 01 and Reported date = 11 -->true 
	 */
	public boolean checkChangeNonFinReportTimely(Person eligChild) {
		boolean result = true;
		FilingUnit fu = this.getFilingUnitList().get(0);
		if (null != fu.getFilingUnitPersonList()
				&& !fu.getFilingUnitPersonList().isEmpty()) {
			for (FilingUnitPerson fup : fu.getFilingUnitPersonList()) {
				Person per = fup.getPerson();
				if (fup.getPartStatusCode().equals(EnumPartStatusCode.EC) 
				&& (!"EC".equals(lookUpStatusByIndvId(fup.getPerson().getPersonID(), "PreviousOngoingAuthorizedRecord","EDG_PART_STATUS_CODE")))
				&& (DateUtilities.compareInMonthsGreaterThanEqualTo(eligibilityDeterminationMonth, per.getEffBeginDateDcIndvHHStatus())==EnumYesNo.Y)) {
					ArrayList<NonFinData> nfList = per.getNonFinDataList();
					if (null != nfList && !nfList.isEmpty()) {
						for (NonFinData nf : nfList) {
							if (!nf.isTimelySw() && nf.isCcapSw()) {
								if (maxVerfDate == null
									|| EnumYesNo.Y.equals(DateUtilities
											.compareDatesGreaterThan(nf
													.getNonFinVerfReceivedDt(),
													maxVerfDate))) {
									maxVerfDate = nf.getNonFinVerfReceivedDt();
									System.out.println("CCAP-NonFin-set-Max Verf Dt " +nf.getNonFinDataType() + maxVerfDate);
								}
								result= false;
							}
						}
					}
				}
			}
		}
		return result;
	}
	
	/**
	 * @param minDate 
	 * Return false only if any of the changes is verified untimelyfor this individual Discovery
	 * date = 01 and Reported date = 11 -->true
	 */
	public boolean checkAllChangesVerifiedTimely(Person per) throws ParseException{
		boolean result = true;
		Date VrfDueDt = null;
		Date VrfRecvdDt = null;
		FilingUnit fu=this.getFilingUnitList().get(0);
		ArrayList<Verification> verificationList = fu.getVerificationList();
			for (FilingUnitPerson fup : fu.getFilingUnitPersonList()) {
				if (fup.getPartStatusCode().equals(EnumPartStatusCode.EC) 
						&& (!"EC".equals(lookUpStatusByIndvId(fup.getPerson().getPersonID(), "PreviousOngoingAuthorizedRecord","EDG_PART_STATUS_CODE")))
						&& (DateUtilities.compareInMonthsGreaterThanEqualTo(eligibilityDeterminationMonth, per.getEffBeginDateDcIndvHHStatus())==EnumYesNo.Y)) {
					Person eligChild = fup.getPerson();

				if(null != verificationList && !verificationList.isEmpty()){
					for (Verification verf : verificationList) {
						if(verf.getVclIndvId() == eligChild.getPersonID()
								&& (DateUtilities.compareInMonthsGreaterThanEqualTo(eligibilityDeterminationMonth, per.getEffBeginDateDcIndvHHStatus())==EnumYesNo.Y)) {
							Date lastAuthDate = lookupLastAuthDate();
							if(DateUtilities.compareDatesGreaterThan(verf.getVclDueDt(), lastAuthDate).equals(EnumYesNo.Y)){
								VrfDueDt =  verf.getVclDueDt();
								VrfRecvdDt = verf.getValidSourceRcvDt();
								if(DateUtilities.compareDatesGreaterThan(VrfRecvdDt, VrfDueDt).equals(EnumYesNo.Y)){			
									if (maxVerfDate == null || EnumYesNo.Y.equals(DateUtilities
											.compareDatesGreaterThan(VrfRecvdDt,maxVerfDate))) {
										maxVerfDate = VrfRecvdDt;
										System.out.println("CCAP-all verified timely VCL -set-Max Verf Dt " +verf.getVclIndvId() + maxVerfDate);
									}
									result = false;		
								}
							}
						}
					}
				}
				
				ArrayList<NonFinData> nfList = eligChild.getNonFinDataList();
				if (null != nfList && !nfList.isEmpty()) {
					for (NonFinData nf : nfList) {
						if ((DateUtilities.compareDatesGreaterThan(
								nf.getNonFinVerfReceivedDt(),
								DateUtilities.addDaysToDate(
										nf.getNonFinReportDt(), 10))
								.equals(EnumYesNo.Y)) && nf.isCcapSw()) {
							if (maxVerfDate == null
									|| EnumYesNo.Y.equals(DateUtilities
											.compareDatesGreaterThan(nf
													.getNonFinVerfReceivedDt(),
													maxVerfDate))) {
								maxVerfDate = nf.getNonFinVerfReceivedDt();
								System.out.println("CCAP-all verified timely Non-Fin -set-Max Verf Dt " +nf.getNonFinDataType() + maxVerfDate);
							}
							result = false;
						}
					}
				}
				
			}
		}
		boolean childCareNeedTimelyReported = checkChangedChildCareNeedsVerifiedTimely(per);
		if (!childCareNeedTimelyReported) {
			return false;
		}
		return result;
	}
	
	/**
	 * Return false only if any of the child care needs for this individual is reported untimely
	 * Discovery date = 01 and Reported date = 10 -->true Discovery date = 01
	 * and Reported date = 11 -->false
	 */
	public boolean checkChangedChildCareNeedsReportTimely(Person eligChild) {
		boolean result = true;
		FilingUnit fu = this.getFilingUnitList().get(0);
		if (null != fu.getFilingUnitPersonList()
				&& !fu.getFilingUnitPersonList().isEmpty()) {
			for (FilingUnitPerson fup : fu.getFilingUnitPersonList()) {
				if ((!fup.getPartStatusCode().equals(EnumPartStatusCode.EC))
					&& (!"EC".equals(lookUpStatusByIndvId(fup.getPerson().getPersonID(), "PreviousOngoingAuthorizedRecord","EDG_PART_STATUS_CODE")))) {
					continue;
				}
				Person per = fup.getPerson();
				if (DateUtilities.compareDatesGreaterThan(
						per.getChildCareReportedDt(),DateUtilities.addDaysToDate(per.getChildCareDiscoveryDt(), 10)).equals(EnumYesNo.Y)
					&& (DateUtilities.compareInMonthsGreaterThanEqualTo(eligibilityDeterminationMonth, per.getEffBeginDateDcIndvHHStatus())==EnumYesNo.Y)) {
					if (maxVerfDate == null
					|| EnumYesNo.Y.equals(DateUtilities.compareDatesGreaterThan(per.getChildCareVerfReceivedDt(),maxVerfDate))) {
						maxVerfDate = per.getChildCareVerfReceivedDt();
						System.out.println("maxVerfDate checkChangedChildCareNeedsReportTimely "+maxVerfDate);
					}
					result = false;
					
				}
			}
		}
		return result;
	}

	/**
	 * Return false only if any of the child care needs for this individual is verified untimely
	 * Reported date = 01 and Verified date = 10 -->true Reported date = 01 and
	 * Verified date = 11 -->false
	 */
	public boolean checkChangedChildCareNeedsVerifiedTimely(Person eligChild) {
		FilingUnit fu = this.getFilingUnitList().get(0);
		boolean result = true;
		if (null != fu.getFilingUnitPersonList()
				&& !fu.getFilingUnitPersonList().isEmpty()) {
			for (FilingUnitPerson fup : fu.getFilingUnitPersonList()) {
				if (!fup.getPartStatusCode().equals(EnumPartStatusCode.EC) && 
						!"EC".equals(lookUpStatusByIndvId(fup.getPerson().getPersonID(), "PreviousOngoingAuthorizedRecord",
								"EDG_PART_STATUS_CODE"))) {
						continue;
				}
				Person per = fup.getPerson();
				if (DateUtilities.compareDatesGreaterThan(per.getChildCareVerfReceivedDt(),DateUtilities.addDaysToDate(per.getChildCareReportedDt(), 10)).equals(EnumYesNo.Y)) {
					if (maxVerfDate == null
						|| EnumYesNo.Y.equals(DateUtilities.compareDatesGreaterThan(per.getChildCareVerfReceivedDt(),maxVerfDate))) {
						maxVerfDate = per.getChildCareVerfReceivedDt();
						System.out.println("maxVerfDate checkChangedChildCareNeedsVerifiedTimely "+maxVerfDate);
						
					}
					result = false;
					
				}
			}
		}
		return result;
	}

	public Date getMinProgramDate() {
		return minProgramDate;
	}

	public void setMinProgramDate(Date minProgramDate) {
		this.minProgramDate = minProgramDate;
	}
	
	public boolean sanctionWorkDedCalForAnyPerson(FilingUnit fu)
	{
		if(fu.getFilingUnitPersonList()!=null && !fu.getFilingUnitPersonList().isEmpty())
		{
			for(FilingUnitPerson filingUnitPerson: fu.getFilingUnitPersonList())
			{
				System.out.println("512"+filingUnitPerson.isSanctionWorkDedCal());
				if(filingUnitPerson.isSanctionWorkDedCal())
					return true;
			}
		}
		return false;
	}
	
	private Date firstDiversionMonth;
	
	public Date getFirstDiversionMonth() {
		return firstDiversionMonth;
	}

	public void setFirstDiversionMonth(Date firstDiversionMonth) {
		this.firstDiversionMonth = firstDiversionMonth;
	}
	
	private Map<String, String> cancelledAuthMap;

	public Map<String, String> getCancelledAuthMap() {
		return cancelledAuthMap;
	}

	public void setCancelledAuthMap(Map<String, String> cancelledAuthMap) {
		this.cancelledAuthMap = cancelledAuthMap;
	}
	
	private Map<String, String> previousEdbcAuthMap;

	public Map<String, String> getPreviousEdbcAuthMap() {
		return previousEdbcAuthMap;
	}

	public void setPreviousEdbcAuthMap(Map<String, String> previousEdbcAuthMap) {
		this.previousEdbcAuthMap = previousEdbcAuthMap;
	}
	
	private Map<Long, HashMap<String, HashMap<String, String>>> previousEdbcAuthIndvMap;

	Map<Long, HashMap<String, HashMap<String, String>>> prevCancelledIndvEDMStatusMap;

	public Map<Long, HashMap<String, HashMap<String, String>>> getPrevCancelledIndvEDMStatusMap() {
		return prevCancelledIndvEDMStatusMap;
	}

	public void setPrevCancelledIndvEDMStatusMap(
			Map<Long, HashMap<String, HashMap<String, String>>> prevCancelledIndvEDMStatusMap) {
		this.prevCancelledIndvEDMStatusMap = prevCancelledIndvEDMStatusMap;
	}
	
	public String getPreviousEdbcAuthValueFor(String key){
		if((null == key) 
				|| (null == getPreviousEdbcAuthMap()) 
				|| (null == getPreviousEdbcAuthMap().get(key))
				|| (getPreviousEdbcAuthMap().get(key).isEmpty())){
			return null;
		}
		return getPreviousEdbcAuthMap().get(key);
	}
	
	public double getPreviousEdbcAuthDoubleValueFor(String key){
		if((null == key) 
				|| (null == getPreviousEdbcAuthMap()) 
				|| (null == getPreviousEdbcAuthMap().get(key))
				|| (getPreviousEdbcAuthMap().get(key).isEmpty())){
			return 0;
		}
		return Double.parseDouble(getPreviousEdbcAuthMap().get(key));
	}
	
	public int getPreviousEdbcAuthIntValueFor(String key){
		if((null == key) 
				|| (null == getPreviousEdbcAuthMap()) 
				|| (null == getPreviousEdbcAuthMap().get(key))
				|| (getPreviousEdbcAuthMap().get(key).isEmpty())){
			return 0;
		}
		return Integer.parseInt(getPreviousEdbcAuthMap().get(key));
	}
	
	public void setPreviousEdbcAuthToCancelledAuth(){
		this.previousEdbcAuthMap = this.cancelledAuthMap;
		this.previousEdbcAuthIndvMap = this.getPrevCancelledIndvEDMStatusMap();
	}
	
	public void setPreviousEdbcAuthToPrevOngAuth(){
		this.previousEdbcAuthMap = new HashMap<String, String>();
		FilingUnit varFilingUnit = this.getFilingUnitList().get(0);
//		previousEdbcAuthMap.put("DI_ACTION_DT", this.lookUpStatusByTOA(varFilingUnit.getTypeOfAssistance(), "PreviousOngoingAuthorizedRecord", "DI_ACTION_DT"));
//		previousEdbcAuthMap.put("ASS_UNIT_SIZE", this.lookUpStatusByTOA(varFilingUnit.getTypeOfAssistance(), "PreviousOngoingAuthorizedRecord", "ASS_UNIT_SIZE"));
//		previousEdbcAuthMap.put("CG_STATUS_CD", this.lookUpStatusByTOA(varFilingUnit.getTypeOfAssistance(), "PreviousOngoingAuthorizedRecord", "CG_STATUS_CD"));
		previousEdbcAuthMap.put("NET_BENEFIT_AMT", this.lookUpStatusByTOA(varFilingUnit.getTypeOfAssistance(), "PreviousOngoingAuthorizedRecord", "NET_BENEFIT_AMT"));
		previousEdbcAuthMap.put("BENEFIT_AMT", this.lookUpStatusByTOA(varFilingUnit.getTypeOfAssistance(), "PreviousOngoingAuthorizedRecord", "BENEFIT_AMT"));
//		previousEdbcAuthMap.put("PAYMENT_AMT", this.lookUpStatusByTOA(varFilingUnit.getTypeOfAssistance(), "PreviousOngoingAuthorizedRecord", "PAYMENT_AMT"));
//		previousEdbcAuthMap.put("SPEND_DOWN_IND",this.lookUpStatusByTOA(varFilingUnit.getTypeOfAssistance(), "PreviousOngoingAuthorizedRecord", "SPEND_DOWN_IND"));
//		previousEdbcAuthMap.put("NET_INCOME_TANF", String.valueOf(this.getPreviousNetIncomeForTanf()));
//		previousEdbcAuthMap.put("GROSS_INCOME_TANF",  String.valueOf(this.getPreviousGrossIncomeForTanf()));
//		previousEdbcAuthMap.put("NET_INCOME_FS", String.valueOf(this.getPreviousNetIncomeForSnap()));
//		previousEdbcAuthMap.put("GROSS_INCOME_FS", String.valueOf(this.getPreviousGrossIncome()));
		previousEdbcAuthMap.put("COPAY_AMOUNT", this.lookUpStatusByTOA(varFilingUnit.getTypeOfAssistance(), "PreviousOngoingAuthorizedRecord", "COPAY_AMOUNT"));
		previousEdbcAuthMap.put("PAY_BEG_DT",this.lookUpStatusByTOA(varFilingUnit.getTypeOfAssistance(), "PreviousOngoingAuthorizedRecord","PAY_BEG_DT"));
		previousEdbcAuthIndvMap = this.getStatusOfPrevIndvEdm();
	}
	
	public boolean isBestIndSw() {
		return bestIndSw;
	}

	public void setBestIndSw(boolean bestIndSw) {
		this.bestIndSw = bestIndSw;
	}
	
	public boolean isSameDayIssuanceTrigger() {
		return isSameDayIssuanceTrigger;
	}

	public void setSameDayIssuanceTrigger(boolean isSameDayIssuanceTrigger) {
		this.isSameDayIssuanceTrigger = isSameDayIssuanceTrigger;
	}
	public boolean isReviewStartedForAnyProgInCase() {
		return reviewStartedForAnyProgInCase;
	}

	public void setReviewStartedForAnyProgInCase(
			boolean reviewStartedForAnyProgInCase) {
		this.reviewStartedForAnyProgInCase = reviewStartedForAnyProgInCase;
	}

	public boolean considerCancelledAuthRecord = false;
	//Added as a part of CR 607
	public Date lookupDateForNewIndividual(Person per) {
		Object toaMapObj = statusOfPrevIndvEdm.get(per.getPersonID());
		if (null == toaMapObj){
			return per.getEffectiveBeginDate();
		}
		else{
			Map toaMap = (Map)toaMapObj;	
			Object authMap = toaMap.get("PreviousOngoingAuthorizedRecord");
			if((null != authMap) && (!((Map)authMap).isEmpty())){
				return null;
			}
			else
			{
				Object edMapObj = (Map) toaMap.get("PreviousEDMCurrentRun");
				if(null!=edMapObj && (!((Map)edMapObj).isEmpty()))
				{
					return per.getEffectiveBeginDate();
				}
			}
		}
		return null;
	}
	public boolean hasActiveUtilityExpense() {
		for (Person per : this.getPersonList()) {
			for(Expense exp : per.getExpenseList()) {
				if (exp.getExpenseType() == EnumExpenseType.HE ||
						exp.getExpenseType() == EnumExpenseType.CO ||
						exp.getExpenseType() == EnumExpenseType.SW ||
						exp.getExpenseType() == EnumExpenseType.WA ||
						exp.getExpenseType() == EnumExpenseType.EL ||
						exp.getExpenseType() == EnumExpenseType.TE ||
						exp.getExpenseType() == EnumExpenseType.GA ||
						exp.getExpenseType() == EnumExpenseType.RE) {
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean checkSUAAllowance() {
		for (Person per : this.getPersonList()) {
			for (Expense exp : per.getExpenseList()) {
				if ( (
						exp.getExpenseType() == EnumExpenseType.HE ||
						exp.getExpenseType() == EnumExpenseType.CO ||
						exp.getExpenseType() == EnumExpenseType.RE) &&
						exp.isExpenseCountable()){
						return true;
				}
			}
		}
		
		return false;
	}
	
	public boolean checkLUAAllowance() {
		
		int totalEligibleExpenses = 0;
		for (Person per : this.getPersonList()) {
			for (Expense exp : per.getExpenseList()) {
				if ( (
						exp.getExpenseType() == EnumExpenseType.SW ||
						exp.getExpenseType() == EnumExpenseType.WA ||
						exp.getExpenseType() == EnumExpenseType.EL || 
						exp.getExpenseType() == EnumExpenseType.TE ||
						exp.getExpenseType() == EnumExpenseType.GA) &&
						exp.isExpenseCountable()) {
							totalEligibleExpenses++;

				}
			}
		}
		if (totalEligibleExpenses >= 2) {
			return true;
		}
		return false;
	}
	
	public boolean checkMUAAllowance() {
		
		for (Person per : this.getPersonList()) {
			for (Expense exp : per.getExpenseList()) {
				if ( (
					exp.getExpenseType() == EnumExpenseType.SW || 
					exp.getExpenseType() == EnumExpenseType.WA ||
					exp.getExpenseType() == EnumExpenseType.EL ||
					exp.getExpenseType() == EnumExpenseType.GA) &&
					exp.isExpenseCountable()) {
						return true;
				}
			}
		}
		return false;
	}
	
	public boolean checkTAAllowance() {
		
		for (Person per : this.getPersonList()) {
			for (Expense exp : per.getExpenseList()) {
				if (exp.getExpenseType() == EnumExpenseType.TE && 
					exp.isExpenseCountable()) {
					return true;
				}
			}
		}
		return false;
	}

	public Date getTask380StartDate() {
		return task380StartDate;
	}

	public void setTask380StartDate(Date task380StartDate) {
		this.task380StartDate = task380StartDate;
	}

	public EnumAppSubmittedBy getAppSubmittedBy() {
		return appSubmittedBy;
	}

	public void setAppSubmittedBy(EnumAppSubmittedBy appSubmittedBy) {
		this.appSubmittedBy = appSubmittedBy;
	}

	public boolean caseProcessedWithinSevenDays(FilingUnit filingUnit){
		Person primaryPerson = null;
		for(Person person : this.getPersonList()){
			if(person.isApplicant()){
				primaryPerson = person;
				break;
			}
		}
		
		if(filingUnit.getGroupNoticeReasonList().contains(EnumNoticeReason.ED0415)){
			return false;
		}else{
			if(EnumCaseMode.IN.equals(filingUnit.getActivityType()) &&
					DateUtilities.compareDatesLessThanEqualTo(this.getRunDate(),
							DateUtilities.getLaterDate(DateUtilities.addDaysToDate(this.applicationDate, 7), 
									DateUtilities.addDaysToDate(identityVerificationDate(primaryPerson), 7)) ).equals(EnumYesNo.Y)){
				return true;
			}else if(EnumCaseMode.PR.equals(filingUnit.getActivityType()) &&
					DateUtilities.compareDatesLessThanEqualTo(this.getRunDate(),
							DateUtilities.getLaterDate(DateUtilities.addDaysToDate(this.formReceiveDate, 7),
									DateUtilities.addDaysToDate(identityVerificationDate(primaryPerson), 7)) ).equals(EnumYesNo.Y)){
				return true;	
			}
		}
		return false;
	}	

	public Date identityVerificationDate(Person per) {
		Date identityVerfDate = null;
		Date authRepIdenity = null;
		Date idVerfDate = null;
		
		if(per.isApplicant()){
			for(NonFinData nonFinData : per.getNonFinDataList()){
				if(null!=nonFinData.getNonFinDataType() && "identity".equals(nonFinData.getNonFinDataType())){
					identityVerfDate = nonFinData.getNonFinVerfReceivedDt();
					break;
				}
			}
			
			if(appSubmittedBy.equals(EnumAppSubmittedBy.AR)){
				authRepIdenity = this.getAuthRepIDVerfDate();
			}
		}
		
		if(authRepIdenity!=null && identityVerfDate!=null){
			idVerfDate = DateUtilities.getLaterDate(identityVerfDate, authRepIdenity);
		}else{
			idVerfDate= authRepIdenity!=null? authRepIdenity : identityVerfDate;
		}
		return idVerfDate;
	}

	public boolean isReceiveReviewFormsNotices() {
		return receiveReviewFormsNotices;
	}

	public void setReceiveReviewFormsNotices(boolean receiveReviewFormsNotices) {
		this.receiveReviewFormsNotices = receiveReviewFormsNotices;
	}	

	public Date getAuthRepIDVerfDate() {
		return authRepIDVerfDate;
	}

	public void setAuthRepIDVerfDate(Date authRepIDVerfDate) {
		this.authRepIDVerfDate = authRepIDVerfDate;
	}

	public boolean isMigrantOrSeasonalFarmWorker() {
		return migrantOrSeasonalFarmWorker;
	}

	public void setMigrantOrSeasonalFarmWorker(boolean migrantOrSeasonalFarmWorker) {
		this.migrantOrSeasonalFarmWorker = migrantOrSeasonalFarmWorker;
	}

	public boolean isPostponedVerificationProvided() {
		return postponedVerificationProvided;
	}

	public void setPostponedVerificationProvided(
			boolean postponedVerificationProvided) {
		this.postponedVerificationProvided = postponedVerificationProvided;
	}

	public boolean isPreviousExpedited() {
		return previousExpedited;
	}

	public void setPreviousExpedited(boolean previousExpedited) {
		this.previousExpedited = previousExpedited;
	}

	private boolean previouslyAuthorizedSNAPIsExpeditedSw;

	public boolean isPreviouslyAuthorizedSNAPIsExpeditedSw() {
		return previouslyAuthorizedSNAPIsExpeditedSw;
	}

	public void setPreviouslyAuthorizedSNAPIsExpeditedSw(
			boolean previouslyAuthorizedSNAPIsExpeditedSw) {
		this.previouslyAuthorizedSNAPIsExpeditedSw = previouslyAuthorizedSNAPIsExpeditedSw;
	}

	public Map<Long, HashMap<String, HashMap<String, String>>> getPreviousEdbcAuthIndvMap() {
		return previousEdbcAuthIndvMap;
	}

	public void setPreviousEdbcAuthIndvMap(Map<Long, HashMap<String, HashMap<String, String>>> previousEdbcAuthIndvMap) {
		this.previousEdbcAuthIndvMap = previousEdbcAuthIndvMap;
	}
	
	public boolean pendingRecordsExist = true;
	public boolean checkTimelyVerifiedLevelOfCareChanges() throws ParseException {
		boolean nonFin = checkChangeNonFinVerifiedTimely();
		boolean allowableActivity = checkChangedAllowableActivityVerifiedTimely();
		boolean childCare = checkChangedChildCareNeedsVerifiedTimely();
		return (nonFin && allowableActivity && childCare);
	}
}
