 <%/*
********************************************************************************************
Name: [DCPARRegisterCaseProg]		Created By: [Prema.k]		Created on: [05/22/2002]
Purpose: [YOUR PURPOSE]
Modification Logs:
Modified By 			Date			Marker			Reason & description
kishan kanneganti		08/10/2006						FO-351 Remove One Time Grand Parent
[MODIFIED NAME]		    [MM/DD/YYYY]					[REASON FOR CHANGE]
Amruta Gadre			04/16/2007						Defect#BRGUS00040536
Prasad Padmanabhuni		05/23/2007			            BRGUS00047832 - CR 20857 
Prasad Padmanabhuni		05/29/2007			            BRGUS00047832 - CR 20857 -- Adding 'Required Information Details' field
Prasad Padmanabhuni		06/07/2007			            BRGUS00047832 - CR20857 
Gopi Upadhyayula        06/15/2007		  #1	     	Dev Activity#BRGUS00048622(CR-20389)
Rahul Asar				08/16/2007						BRGUS00063214 - Program page error - 
														moved onload script(stmts) to function
Vamsi Krishna         	11/05/2006                		BRGUS00079611
Gopi Upadhyayula		11/09/2007		  #2			DA-BRGUS00080599(CR 22242)
Chatsupa M.             11/12/2007                      BRGUS00074098 - expand size of Medicaid Type 
Gopi Upadhyayula		11/15/2007		  #3			DEFECT#BRGUS00082439
Prasad Padmanabhuni		11/17/2007						Defect#BRGUS00082661
Gopi Upadhyayula		12/07/2007		  #4			DEFECT#BRGUS00079611
Gopi Upadhyayula		12/19/2007		  #5			DEFECT#BRGUS00085109
Gopi Upadhyayula		12/19/2007		  #6			DEFECT#BRGUS00087319
Gopi Upadhyayula		01/08/2007		  #7			DEFECT#BRGUS00087319
Gopi Upadhyayula		01/29/2007		  #8			DEFECT#BRGUS00059065
Susmitha Arakala        02/01/2008                      DEFECT#BRGUS00094530
Gopi Upadhyayula		02/05/2007		  #9			DEFECT#BRGUS00092757
Prasad Padmanabhuni		02/072008						Defect#BRGUS00096846--Show the values for reactivation fields 
																			  when closed, or denied
Gopi Upadhyayula		02/12/2007		  #10			DEFECT#BRGUS00092757
Gopi Upadhyayula		02/18/2007		  #11			DEFECT#BRGUS00098375
Gopi Upadhyayula		02/21/2008		  #12			DEFECT#BRGUS00098487(Change the tabbing order)
Balaji Chakilam			03/18/2009						DEFECT#BRGUS00127302(Alignment changes)
Prasad Padmanabhuni	    05/06/2009						WR#BRGUS00160381-- Removing eight week check and not allowing future date for application date
Ashok R Pulagum			11/21/2009						Label/dropdown value Changes for Program Request
********************************************************************************************
*/%>
<%/* NOT EDITABLE - FW Page Directive, Imports, and URIs */%>   
<%@ include file = "/fwincludes/docTypeInclude.jsp" %>
<%@ include file = "/fwincludes/directives.jsp" %>
<%/* EDITABLE - Page Specific Imports and URIs (You can add code to this section) */%>
<%@ include file = "/fwincludes/docTypeInclude.jsp" %>
<%@ include file = "/includes/DcDirectives.jsp" %>
<%/* EDITABLE - Page Specific Imports and URIs (You can add code to this section) */%>
<%@page import="gov.state.nextgen.framework.business.bo.FwReferenceTableManager,gov.state.nextgen.business.entities.individual.*,gov.state.nextgen.business.entities.cases.*, java.util.*"%>
<%/* EDITABLE - Page Specific useBean tags(You can add code to this section) */%>
<jsp:useBean id="programs" class="gov.state.nextgen.presentation.view.pageelements.Programs" scope="application"/>
<jsp:useBean id="programType" class="gov.state.nextgen.presentation.view.pageelements.ProgramType" scope="application"/>
<jsp:useBean id="category" class="gov.state.nextgen.presentation.view.pageelements.Category" scope="application"/>
<%--New bean id is added for ND-33996 and the bean id withdrawClosureReason is replaced by requestType--%>
<jsp:useBean id="requestType" class="gov.state.nextgen.presentation.view.pageelements.RequestType" scope="application"/>
<jsp:useBean id="isReferredByChip" class="gov.state.nextgen.presentation.view.pageelements.IsReferredByChip" scope="application"/>
<jsp:useBean id="chipApplication" class="gov.state.nextgen.presentation.view.pageelements.ChipApplication" scope="application"/>
<jsp:useBean id="allProgramsIndividuals" class="gov.state.nextgen.presentation.view.pageelements.AllProgramsIndividuals" scope="application"/>
<jsp:useBean id="priorMedicaid" class="gov.state.nextgen.presentation.view.pageelements.PriorMedicaid" scope="application"/>
<jsp:useBean id="programStatus" class="gov.state.nextgen.presentation.view.pageelements.ProgramStatus" scope="application"/>
<jsp:useBean id="reactivationDate" class="gov.state.nextgen.presentation.view.pageelements.ReactivationDate" scope="application"/>
<jsp:useBean id="reactivationReason" class="gov.state.nextgen.presentation.view.pageelements.ReactivationReason" scope="application"/>
<jsp:useBean id="withdrawClosureReason" class="gov.state.nextgen.presentation.view.pageelements.WithdrawClosureReason" scope="application"/>
<jsp:useBean id="applicationDate" class="gov.state.nextgen.presentation.view.pageelements.ApplicationDate" scope="application"/>
<jsp:useBean id="effectiveBeginDate" class="gov.state.nextgen.presentation.view.pageelements.EffectiveBeginDate" scope="application"/>
<jsp:useBean id="endDate" class="gov.state.nextgen.presentation.view.pageelements.EndDate" scope="application"/>
<jsp:useBean id="statusDate" class="gov.state.nextgen.presentation.view.pageelements.StatusDate" scope="application"/>
<jsp:useBean id="conversionDate" class="gov.state.nextgen.presentation.view.pageelements.ConversionDate" scope="application"/>
<jsp:useBean id="priorMedicaidDates" class="gov.state.nextgen.presentation.view.pageelements.PriorMedicaidDates" scope="application"/>
<jsp:useBean id="withdrawCloseSw" class="gov.state.nextgen.presentation.view.pageelements.WithdrawCloseSw" scope="application"/>
<jsp:useBean id="withdrawClosureDt" class="gov.state.nextgen.presentation.view.pageelements.WithdrawClosureDt" scope="application"/>
<jsp:useBean id="firstPrgDate" class="gov.state.nextgen.presentation.view.pageelements.FirstPrgDate" scope="application"/>
<jsp:useBean id="appDate" class="gov.state.nextgen.presentation.view.pageelements.AppDate" scope="application"/>
<jsp:useBean id="medicaidTypeCd" class="gov.state.nextgen.presentation.view.pageelements.MedicaidTypeCd" scope="application"/>
<jsp:useBean id="dhs3043Sw" class="gov.state.nextgen.presentation.view.pageelements.Dhs3043Sw" scope="application"/>
<jsp:useBean id="isReappOrReinstate" class="gov.state.nextgen.presentation.view.pageelements.IsReappOrReinstate" scope="application"/>
<jsp:useBean id="waitListScreeningRequest" class="gov.state.nextgen.presentation.view.pageelements.WaitListScreeningRequest" scope="application"/>
<jsp:useBean id="isReinstatement" class="gov.state.nextgen.presentation.view.pageelements.IsReinstatement" scope="application"/>
<%-- bean id changed from reinstateDate to  reinstatementEffectiveDate--%>
<jsp:useBean id="reinstatementEffectiveDate" class="gov.state.nextgen.presentation.view.pageelements.ReinstatementEffectiveDate" scope="application"/>
<jsp:useBean id="reinstateReason" class="gov.state.nextgen.presentation.view.pageelements.ReinstateReason" scope="application"/>
<jsp:useBean id="reqInfDetails" class="gov.state.nextgen.presentation.view.pageelements.ReqInfDetails" scope="application"/>
<%--
<jsp:useBean id="dhs3043Sw" class="gov.state.nextgen.presentation.view.pageelements.Dhs3043Sw" scope="application"/>
--%> 
 
<jsp:useBean id="priorMedicaidOtherDate" class="gov.state.nextgen.presentation.view.pageelements.PriorMedicaidOtherDate" scope="application"/>
<jsp:useBean id="withdrawAdditionalInfo" class="gov.state.nextgen.presentation.view.pageelements.WithdrawAdditionalInfo" scope="application"/>
<jsp:useBean id="reappDtMode" class="java.lang.String" scope="request"/>
<%java.sql.Timestamp goLiveDate = (java.sql.Timestamp)request.getAttribute ("goLiveDate"); %>
<%java.sql.Timestamp currentDate = gov.state.nextgen.framework.util.FwCalendar.getInstance().getDate().getTimestamp();%>
<%String convertedCase = (String) request.getAttribute("isConvertedCase");%>
<jsp:useBean id="firstDcCaseProgramCargo" class="gov.state.nextgen.common.cargo.custom.DcCaseProgramCargo" scope="request"/>
<jsp:useBean id="dcCaseProgramCargo" class="gov.state.nextgen.common.cargo.custom.DcCaseProgramCargo" scope="request"/>
<jsp:useBean id="oldCaseProgramCargo" class="gov.state.nextgen.common.cargo.custom.DcCaseProgramCargo" scope="request"/>
<jsp:useBean id="requestDates" class="java.util.ArrayList" scope="request"/>
<jsp:useBean id="histNavMode" class="java.lang.String" scope="request"/>
<jsp:useBean id="extendSOP" class="gov.state.nextgen.presentation.view.pageelements.ExtendSOP" scope="application"/>
<jsp:useBean id="extensionActionDt" class="gov.state.nextgen.presentation.view.pageelements.ExtensionActionDt" scope="application"/>
<jsp:useBean id="dateOfCompliance" class="gov.state.nextgen.presentation.view.pageelements.DateOfCompliance" scope="application"/>
<!-- Added for ND-41332 -->
<jsp:useBean id="requestingKinshipCare" class="gov.state.nextgen.presentation.view.pageelements.RequestingKinshipCare" scope="application"/>
<jsp:useBean id="requestingDiversion" class="gov.state.nextgen.presentation.view.pageelements.RequestingDiversion" scope="application"/>
<jsp:useBean id="shortTermNeed" class="gov.state.nextgen.presentation.view.pageelements.ShortTermNeed" scope="application"/>
<%/* NOT EDITABLE - Head Tag Include */%>
<%@ include file = "/fwincludes/headTag_NEW.jsp"%>
<STYLE>
.form570{
	color:#333333;
	background-color : #FFFFFF;
	font-family : arial,verdana, Helvetica, sans-serif;
	font-size : 11px;
	width : 570px;
}
</STYLE>

<% 
//CH-3650 - shomenon - boolean to check if the logged-in user is Supervisor. Would be used in JS validation
boolean isSupervisor = request.getAttribute(ARConstants.IS_SUPERVISOR) != null ? ((Boolean)(request.getAttribute(ARConstants.IS_SUPERVISOR))).booleanValue() : false;
String ARDCMODE=(String)request.getAttribute("ARDCMODE");
boolean protectAppDates=false;
if(request.getAttribute("CASE_STATUS") != null && (request.getAttribute("CASE_STATUS").equals("DN") || request.getAttribute("CASE_STATUS").equals("TN"))){
	pageMode = 0;
}
//CH-4007 - amirajkar - Added below condition for making this screen read only for history records.
if(dcCaseProgramCargo.getHistNavInd() != '\u0000' && dcCaseProgramCargo.getHistNavInd() != 'P' && dcCaseProgramCargo.getHistNavInd() != 'S'){
	pageMode = 0;
}
String caseReqDate = "";
if(dcCaseProgramCargo.getApplicationReceivedDt()!=null ){
	caseReqDate = dcCaseProgramCargo.getApplicationReceivedDt().toString().substring(0,10);
}
if(request.getAttribute(DcConstants.PROTECT_PROG_APP_DATE)!=null)
{
	protectAppDates = ((Boolean)request.getAttribute(DcConstants.PROTECT_PROG_APP_DATE)).booleanValue();
}


String progStatusCd="";
String caseMode="";
if(dcCaseProgramCargo.getProgStatusCd()!=null && (("DN").equalsIgnoreCase(dcCaseProgramCargo.getProgStatusCd()) || ("TN").equalsIgnoreCase(dcCaseProgramCargo.getProgStatusCd())))
{
	progStatusCd=dcCaseProgramCargo.getProgStatusCd();
}
if(request.getAttribute("CASE_MODE")!=null)
{
	caseMode = ((String)request.getAttribute("CASE_MODE"));
}


String cfmc = "N";
if(request.getAttribute("CashFapMaCdcExists")!=null)
{
	cfmc = (String)request.getAttribute("CashFapMaCdcExists");
}
String validateDhs3043ForDss = "N";   
//#3-CODE-ADD-START
if(request.getAttribute("validateDhs3043ForDss")!=null)
{
	validateDhs3043ForDss = (String)request.getAttribute("validateDhs3043ForDss");
}
//#3-CODE-ADD-END
//CR20626 - DA - BRGUS00126533 - Arakalas
String enableGroupAtFault = (String)request.getAttribute("enableGroupAtFault");
if(enableGroupAtFault == null && pageMode == 1){
	enableGroupAtFault = "N";
}
java.sql.Timestamp extensionDate = null;
if("Y".equalsIgnoreCase(enableGroupAtFault) && pageMode == 1){
	extensionDate = (java.sql.Timestamp)request.getAttribute("extensionDate");
}
java.sql.Timestamp dtOfCompliance = null;
if("Y".equalsIgnoreCase(enableGroupAtFault) && pageMode == 1){
	dtOfCompliance = (java.sql.Timestamp)request.getAttribute("reqDateOfCompliance");
}
//End CR20626 - DA - BRGUS00126533
//BRGUS00167864 -- padmanabhunip
	String isIOExists = "false";
	if(request.getAttribute("isIOExists")!=null){
		isIOExists = (String)request.getAttribute("isIOExists");
	}
	String isOtherProgsExists = "false";
	if(request.getAttribute("isOtherProgsExists")!=null){
		isOtherProgsExists = (String)request.getAttribute("isOtherProgsExists");
	}
	
//ND-63397
String enableRevToOpen= (String)request.getAttribute("revertToOpenEnable");

String enableLiheapSw = null;

if (gov.state.nextgen.framework.business.entities.cargo.custom.FwProperty
		.getInstance()
		.getProperty(
				gov.state.nextgen.framework.util.FwConstants.APPLICATION_PROPERTY_FILE,
				"enableLiheap") != null) {
	enableLiheapSw = gov.state.nextgen.framework.business.entities.cargo.custom.FwProperty
			.getInstance()
			.getProperty(
					gov.state.nextgen.framework.util.FwConstants.APPLICATION_PROPERTY_FILE,
					"enableLiheap");
} else {
	enableLiheapSw = "N";
}

String ccapPresent="N";

ccapPresent= (String)request.getAttribute("isCCAPCase");
%>
<%@ include file="/fwincludes/DcJavascript.jsp" %>
<script type="text/javascript">
function doReset(formName){

    var vlh_coll="";
    var vlh_count="";
    var vlh_req_page="";
    // alert("Entered doReset() method, with form name as "+ formName);
    displayMessages(" "," ");
    copyErrorFields();
    // alert("Executed Copy error fields ");
    errorFields.length=0;
    highlightFields();
    // alert("Executed highlight fields ");
    resetInnerHTML();

    // alert("formName " + formName);
    if(formName.VLH_COLL_KEY!="undefined" && formName.VLH_COLL_KEY!=null){
        vlh_coll = formName.VLH_COLL_KEY.value;
        vlh_count = formName.VLH_COUNT.value;
        vlh_req_page = formName.REQUESTED_PAGE_ID.value;
    }
    formName.reset();
    //nikunj starts
   
    	document.form1.monthwithdrawClosureDt.disabled = true;
    	document.form1.datewithdrawClosureDt.disabled = true;
    	document.form1.yearwithdrawClosureDt.disabled = true;
    	document.form1.dimg_withdrawClosureDt.disabled = true;
    	$('#withdrawClosureDt').datepicker("disable");
    /*	document.form1.requestType.disabled = true;
    	document.form1.reqInfDetails.disabled = true;*/ 
    	
    //nikunj ends
     if(document.getElementById('cin')!=null && document.getElementById('CINDisplay')){
    	   sendValueIndividual(document.getElementById('cin'),'CINDisplay');
     }
    return false;
    
}

var goLiveDt = null;
var liheapImplDt = null;
var enableLihSw = "<%=enableLiheapSw%>";
//var origStyleValues = {};
$(document).ready(function(){
	
	/* origStyleValues["reactivationReason"] = $("#reactivationReason")[0].getAttribute('style');
	$("#reactivationReason").parent().on('DOMSubtreeModified',function(){
		if(!$("#reactivationReason").prop('disabled')){
			$("#reactivationReason").prop('style','');
		}
	}); */
	
	<% if(null != request.getAttribute(DcConstants.GO_LIVE_DATE)){ %>
		goLiveDt = new Date('<%=(String) request.getAttribute(DcConstants.GO_LIVE_DATE)%>');
	<% } %>
	
	<% if(null != request.getAttribute(DcConstants.LIHEAP_IMPLEMENTATION_DATE)){ %>
		liheapImplDt = new Date('<%=(String) request.getAttribute(DcConstants.LIHEAP_IMPLEMENTATION_DATE)%>');
	<% } %>
	
});

function validateAppStartDt(){
	var result = true;
	var appStartDt = new Date($("#monthapplicationDate").val()+"/"
			+$("#dateapplicationDate").val()+"/"+$("#yearapplicationDate").val());
	var progVal = $("#programs").val();
	var progArr = ['TF','FS','CD'];
	var comparisonDate = null;
	if(null != goLiveDt){
		comparisonDate = new Date(goLiveDt);
		comparisonDate.setMonth(comparisonDate.getMonth()-6);
	}
	if(null != comparisonDate && comparisonDate>appStartDt && progArr.indexOf(progVal)!=-1){
		var comparisonDtStr = (comparisonDate.getYear()+1900) + "/"
				+ (comparisonDate.getMonth()+1) + "/" + comparisonDate.getDate();
		alertMe('DC5000',document.form1.applicationDate,'<%=applicationDate.LABEL%>',comparisonDtStr);
		result = false;
	} else if(null != liheapImplDt && liheapImplDt>appStartDt && progVal=='LI' && enableLihSw=='Y'){
		alertMe('DC5000',document.form1.applicationDate,'<%=applicationDate.LABEL%>',
				'<%=(String) request.getAttribute(DcConstants.LIHEAP_IMPLEMENTATION_DATE)%>');
		result = false;
	}
	return result;
}

</script>
<script type="text/javascript">
<% java.lang.Boolean edit = (java.lang.Boolean) request.getAttribute(DcConstants.EDIT_FLAG); 
	//BRGUS00111724 - chakalamb - getting the edit flag from the request variable EDIT_SW.
	if(edit == null){
   		edit = (java.lang.Boolean) request.getAttribute("EDIT_SW");
	}
%>

function enableDisableWaitListTab(){
	
	var t5 = "tab5";
	if(document.form1.waitListScreeningRequest !=null && document.form1.waitListScreeningRequest.value == "Y"){
		enableTabs(t5);
	}else{
		disableTabs(t5);		
	}
	
}   
/*function Modified for SysId BRGUS00069842. We should't allow user to modify mMedicaidTypeCd 
  once program has been Approved status*/ 
function enableDisableMedicaidType()
{
 
  var edit='<%=edit%>';
 }
		   
//#1 CODE-ADD-START
function enableDisableExpitedTab(){
	var t4 = "tab4";
	if(document.form1.expScreenVisited!=null && document.form1.expScreenVisited.value!="" && 
		document.form1.expScreenVisited=="Y"){
		enableTabs(t4);
	}else{
		//disableTabs(t4);		
	}
}

//#1 CODE-ADD-END
//BRGUS00063214-RA-moved script to method from onload call. 
//Onload will now invoke disableDtFields() function
function disableDtFields(){
	if(document.form1.priorMedicaidDates != null) { 
		document.form1.priorMedicaidDates.disabled = true; 
		document.form1.monthpriorMedicaidOtherDate.disabled = true;
		document.form1.datepriorMedicaidOtherDate.disabled = true; 
		document.form1.yearpriorMedicaidOtherDate.disabled = true;
	}
}
//#6-CODE-ADD-START
function defaultWithdrawClosureDt(){
	var withdrawClosureSw = "";
	if(document.form1.withdrawCloseSw != null && document.form1.withdrawCloseSw != "" && document.form1.withdrawCloseSw != " "){
		withdrawClosureSw = document.form1.withdrawCloseSw.value;
	}
	
	if(withdrawClosureSw == 'Y'){
	    //BRGUS00160589 : date validations change for CDT and CST time zones
		var sysDate = getToday(); //new Date(document.form1.SysDate.value);
		document.form1.monthwithdrawClosureDt.value = sysDate.getMonth();
		document.form1.datewithdrawClosureDt.value = sysDate.getDate();
		document.form1.yearwithdrawClosureDt.value = sysDate.getYear();
		document.form1.monthwithdrawClosureDt.disabled=true;
		document.form1.datewithdrawClosureDt.disabled=true;
		document.form1.yearwithdrawClosureDt.disabled=true;
		$('#withdrawClosureDt').datepicker("disable");
	}
}
//#6-CODE-ADD-END
//CR20626 - DA - BRGUS00126533 - Arakalas
//BRGUS00138032 - Corrected logic to enable/disable Date of Compliance
function enableExtendSOP(){	
	enableComplianceDt();
	var extendSOPSw = "<%=enableGroupAtFault%>";
	if(extendSOPSw == 'Y'){
		document.form1.extendSOP.disabled=false;		
	
	}else{
		document.form1.extendSOP.disabled=true;
	}
	
}



function disableWithdrawCloseSw(){
	var progStatus="<%=progStatusCd%>";
	var caseMode="<%=caseMode%>";
	if(progStatus!=null && progStatus!=undefined && (progStatus=='DN'|| progStatus=='TN') && caseMode=='RE' )
	{
		document.form1.withdrawCloseSw.disabled=true;
	}else{
		document.form1.withdrawCloseSw.disabled=false;
	}
}


function enableComplianceDt(){
	var extSOPSw = document.form1.extendSOP.value;
	if(extSOPSw == 'Y'){
		document.form1.monthdateOfCompliance.disabled=false;
		document.form1.datedateOfCompliance.disabled=false;
		document.form1.yeardateOfCompliance.disabled=false;
		document.form1.dateOfCompliance.disabled=false;
	}else{
	//BRGUS00134379 - Arakalas - dt shd blank out upon selection of Grp at Fault to NO
		document.form1.monthdateOfCompliance.value='mm';
		document.form1.datedateOfCompliance.value='dd';
		document.form1.yeardateOfCompliance.value='yyyy';
		document.form1.monthdateOfCompliance.disabled=true;
		document.form1.datedateOfCompliance.disabled=true;
		document.form1.yeardateOfCompliance.disabled=true;
		document.form1.dateOfCompliance.disabled=true;
	}
}
//BRGUS00134379 - Arakalas - Clear SOP fields on Re-Request Program
function clearSOPFieldsForPS(){	
	var programStatusSw = document.form1.programStatus.value;
	if(programStatusSw == 'PE'){
		clearSOPFields();
	}
}
function clearSOPFieldsForRR(){
	var reappSw = document.form1.isReappOrReinstate.value;
	if(reappSw == 'RA' || reappSw == 'RI'){
		clearSOPFields();
	}
}
//End BRGUS00134379
function enableApplicationDate(){
	if((document.form1.monthapplicationDate.disabled) && (document.form1.dateapplicationDate.disabled) && (document.form1.yearapplicationDate.disabled))
		{
		$('#applicationDate').datepicker("disable");
		}
	}
	
function clearSOPFields(){		
	document.form1.monthdateOfCompliance.value='mm';
	document.form1.monthdateOfCompliance.disabled=true;
	document.form1.datedateOfCompliance.value='dd';
	document.form1.datedateOfCompliance.disabled=true;
	document.form1.yeardateOfCompliance.value='yyyy';
	document.form1.yeardateOfCompliance.disabled=true;		
	document.form1.dateOfCompliance.disabled=true;
	document.getElementById("extActionDtDisp").innerHTML = "";	
	document.form1.extendSOP.value=" ";		
	document.form1.extendSOP.disabled=true;
	
}
function validateComplianceDate(){
<%if(dtOfCompliance != null) {  %>
	var dateOfCompMonth = "<%=dtOfCompliance.getMonth()%>";
	var dateOfCompDate =  "<%=dtOfCompliance.getDate()%>";
	var dateOfCompYear =  "<%=dtOfCompliance.getYear()+1900%>";
	var dateOfComp = new Date(dateOfCompYear, dateOfCompMonth, dateOfCompDate);
	var dateOfComplianceMonth = document.form1.monthdateOfCompliance.value;
	var dateOfComplianceDate = document.form1.datedateOfCompliance.value;
	var dateOfComplianceYear = document.form1.yeardateOfCompliance.value;
	var dtOfComp = new Date(dateOfComplianceYear, dateOfComplianceMonth-1, dateOfComplianceDate);
	
	if(dtOfComp > dateOfComp){		
		alertMe('DC4070',document.form1.dateOfCompliance, document.form1.dateOfCompliance);
		return false;
	}
	
	<% }%>
	
}

function setApplicationDate() {
	if(document.form1.appRecMonth.value == "") {
		document.form1.appRecMonth.value = document.form1.monthapplicationDate.value;
	}
	if(document.form1.appRecDay.value == "") {
		document.form1.appRecDay.value = document.form1.dateapplicationDate.value;
	}
	if(document.form1.appRecYear.value == "") {
		document.form1.appRecYear.value = document.form1.yearapplicationDate.value;
	}
}
	
	
//End CR20626 - DA - BRGUS00126533
</script>
<%
// JSP Variables and code - initializing and setting values to variables from Results_item
String editProgram="";
String histNavProgram="";
String editThisRec="";
if( ((Boolean)request.getAttribute(DcConstants.EDIT_FLAG)) != null  && 
    ((Boolean)request.getAttribute(DcConstants.EDIT_FLAG)).booleanValue() == true  ){
	 editProgram="Y";
	 }
	 else{
	 editProgram="N";
	 }
if( ((String)request.getAttribute("histNavMode")) != null  && 
    ((String)request.getAttribute("histNavMode")).equals("true") ){
	 histNavProgram="Y";
	 }
	 else{
	 histNavProgram="N";
	 }	 
if(editProgram.equals("Y") || histNavProgram.equals("Y")){
editThisRec="Y";
}
else{
editThisRec="N";  
}
int iTab = 1;
String histNav = "";
String voidSw = "";
String progCd = "";
String waitlist="";
String isrein="";
String reinDate="";
String reinrsn="";
String reqdetails="";
String requestingKinshipVal="";
String requestingDiversionVal="";
String shortTermNeedVal="";
byte receMode = 0;
 
String chipAppNumber = "";
String isRefByChip = "";
String programStatusCd = "";
String reactivationReasonCd = "";
String withdrawReasonCd = "";
String reactDate="";
String reactRsn="";
String reappDate="";
String wdrRsn="";
String progReqDate="";
String progTp="";
String withdrawDate = "";
String defWithDrawSw = "";
String reqDatesArray []=null;
java.sql.Timestamp statusDt = null;
java.sql.Timestamp conversionDt = null;
String priorDate = "";
//String gsFSAssistance="";
//Added for BRGUS00047832 - CR 20857 
String withdrawlAddInfo = "";
//CR20626 - DA - BRGUS00126533 - arakalas
String extendSOPSw = "";
java.sql.Timestamp extensionActionDate = null;
String dateOfComp = "";
String dateOfSOPExt = "";
StringBuffer recReasonChk = new StringBuffer();
StringBuffer onChangePmDtJS = new StringBuffer();
byte eleMode = 1;
byte recMode = 0;
byte reapplyMode = 1;
byte nonEditPrgSt = 0;
byte prgMode = 1;
byte nonEditPriorDt = 0;
byte prgTypeMode = 1;
byte effDtsMode = 1;
byte fileDtMode = 1;
byte gsMode = 0;
ArrayList prStatCode = new ArrayList();
ArrayList prStatDesc = new ArrayList();
ArrayList medicDateCds = new ArrayList();
ArrayList medicDatesVals = new ArrayList();
StringBuffer progTypeCd = new StringBuffer(); 
String disableEndDt = "";
String name1 = "";
String name2 = "";
String caseNum = "0";
String beginDt = null;
String endDt = null;
String pmOverrideDate = "";
String prgStatusDateYr = "";
String prgStatusDateMth = "";
String prgStatusDateDt = "";
String medTypeCd="";
String onLoadJS="";
String onChangePrgStatJs = "";		
String validatePrgType = "";
String validatePrg = "";
String onChangeMedDtJS = "";
String validateWithdrawDt = "";
String validateWithdrawRsn = "";
validateWithdrawRsn = ""; 
String validateReqDate = "";
String subsidyChk = "";
progReqDate = currentDate.toString(); 
String firstProgDtYr = "";
String firstProgDtMth = "";
String firstProgDtDt = "";
String[] effBegDates=null;
char convSw = '\u0000';

if(firstDcCaseProgramCargo.getRequestDt() != null){
firstProgDtYr = firstDcCaseProgramCargo.getEffBeginDt().toString().substring(0,4);
firstProgDtMth = firstDcCaseProgramCargo.getEffBeginDt().toString().substring(5,7);
firstProgDtDt = firstDcCaseProgramCargo.getEffBeginDt().toString().substring(8,10);
}
boolean rescindVal = (dcCaseProgramCargo.getProgRescindCd() == null 
||dcCaseProgramCargo.getProgRescindCd().trim().equals(""))? false:true;
long editRow = 0;
boolean editFlag =	(dcCaseProgramCargo.getRequestDt() != null )
					? ((request.getAttribute(DcConstants.EDIT_FLAG) != null) ?
						((Boolean) request.getAttribute(DcConstants.EDIT_FLAG)).booleanValue():true)
					: false;
String caseRegDate = ((java.sql.Timestamp)request.getAttribute(DcConstants.CASE_RECEIVED_DATE)).toString().substring(0,10);
// KISHAN
if(request.getAttribute("earliestSrecordAppDate")!= null ){
String earliestSrecordAppDate = ((java.sql.Timestamp)request.getAttribute("earliestSrecordAppDate")).toString().substring(0,10);
}
// KISHAN END
String isReappDate = "N";
boolean terEver = (request.getAttribute("terEver") == null) ? false: ((Boolean)request.getAttribute("terEver")).booleanValue();

String catCd="";
String catDesc="";
String progDesc="";
 	
if(dcCaseProgramCargo != null )
{
 if(dcCaseProgramCargo.getWaitlistScreeningSw() !=0){
 waitlist=(String.valueOf(dcCaseProgramCargo.getWaitlistScreeningSw()));
}
if(dcCaseProgramCargo.getReinstateSw()!=0){
isrein=String.valueOf(dcCaseProgramCargo.getReinstateSw());
}
if(dcCaseProgramCargo.getKinshipSw()!=0){
requestingKinshipVal=String.valueOf(dcCaseProgramCargo.getKinshipSw());
}
if(dcCaseProgramCargo.getDiversionSw()!=0){
requestingDiversionVal=String.valueOf(dcCaseProgramCargo.getDiversionSw());
}
if(dcCaseProgramCargo.getShortTermNeedSw()!=0){
shortTermNeedVal=String.valueOf(dcCaseProgramCargo.getShortTermNeedSw());
}
if(dcCaseProgramCargo.getProgRescindDt()!=null){
reinDate=dcCaseProgramCargo.getProgRescindDt().toString();
}

if(dcCaseProgramCargo.getProgRescindCd()!= null){
reinrsn=dcCaseProgramCargo.getProgRescindCd();
}

if(dcCaseProgramCargo.getWithdrawAddtionalInfo()!= null){
reqdetails=dcCaseProgramCargo.getWithdrawAddtionalInfo();
}
	
	if(dcCaseProgramCargo.getProgStatusDt() != null){
		prgStatusDateYr = dcCaseProgramCargo.getProgStatusDt().toString().substring(0,4);
		prgStatusDateMth = dcCaseProgramCargo.getProgStatusDt().toString().substring(5,7);
		prgStatusDateDt = dcCaseProgramCargo.getProgStatusDt().toString().substring(8,10);
		statusDt = dcCaseProgramCargo.getProgStatusDt();
	}
	if(dcCaseProgramCargo.getConversionDt() != null){
		conversionDt = dcCaseProgramCargo.getConversionDt();
	}
	if(dcCaseProgramCargo.getCaseNum() != 0) 
	caseNum = String.valueOf(dcCaseProgramCargo.getCaseNum());
    if(dcCaseProgramCargo.getProgCd() != null) {
	progCd = dcCaseProgramCargo.getProgCd();
	progDesc = FwReferenceTableManager.getValueByColumn(true,"PROGRAM",progCd,"Description");
	//change added by nil begin
	if('\u0000'!=dcCaseProgramCargo.getCvModeSw()){
		convSw=dcCaseProgramCargo.getCvModeSw();
	}
	
	prgMode = 0;
	}
	
	// CH-957 - shomenon - commenting the removed columns
	//if(dcCaseProgramCargo.getChipAppNum() != null)
	//chipAppNumber = dcCaseProgramCargo.getChipAppNum();
	//if(dcCaseProgramCargo.getChipReferralSw() != 0)
	//isRefByChip = String.valueOf(dcCaseProgramCargo.getChipReferralSw());
	if(dcCaseProgramCargo.getRequestDt() != null)
	progReqDate = dcCaseProgramCargo.getRequestDt().toString();
	
	if(dcCaseProgramCargo.getHistorySeq()!=0){
	editThisRec="Y";
	}
	
	if(dcCaseProgramCargo.getProgStatusCd() != null)
	{
	programStatusCd = dcCaseProgramCargo.getProgStatusCd().trim();
	}
	else
	{
	    programStatusCd = "PE";
	}
	
	if(dcCaseProgramCargo.getProgRescindDt() != null)
	reactDate = dcCaseProgramCargo.getProgRescindDt().toString();
	
	if(dcCaseProgramCargo.getProgRescindCd() != null)
	reactRsn = dcCaseProgramCargo.getProgRescindCd();
	
	if(dcCaseProgramCargo.getApplicationDt() != null)
	reappDate = dcCaseProgramCargo.getApplicationDt().toString();
	//System.out.println("reapp date = "+ reappDate);
	
	if(dcCaseProgramCargo.getProgWithdrawCd() != null)
	wdrRsn   = dcCaseProgramCargo.getProgWithdrawCd();  
	
	if(dcCaseProgramCargo.getWithdrawDt() != null)
	withdrawDate = dcCaseProgramCargo.getWithdrawDt().toString();
	
	if(dcCaseProgramCargo.getWithdrawSw() != 0)
	defWithDrawSw = String.valueOf(dcCaseProgramCargo.getWithdrawSw());
	
	if(dcCaseProgramCargo.getHistorySeq()!=0){
	editThisRec="Y";
	}
	
	if(dcCaseProgramCargo.getPriorMedicaidCd() != null) {
	progTp = dcCaseProgramCargo.getPriorMedicaidCd();
	prgTypeMode = 0;
	}
//CH-955:Start:Field Medicaid Type has been removed	
//	if(dcCaseProgramCargo.getMedicaidTypeCd() != null)
//	medTypeCd = dcCaseProgramCargo.getMedicaidTypeCd();
	
	if(dcCaseProgramCargo.getPriorMedicaidDates() != null)
	priorDate = dcCaseProgramCargo.getPriorMedicaidDates();
	
//	if(dcCaseProgramCargo.getGrandparentSubsidySw() != 0)
//	gsFSAssistance=String.valueOf(dcCaseProgramCargo.getGrandparentSubsidySw());
//	System.out.println("GrandParentSubsidySw : " + gsFSAssistance);
//	gsFSAssistance = "N";
	
	if(dcCaseProgramCargo.getPriorMedicaidOverrideDate() != null)
	pmOverrideDate=dcCaseProgramCargo.getPriorMedicaidOverrideDate().toString();
	
	progTypeCd.append("\"" + progCd);
	progTypeCd.append(progTp + "\"");
	if(dcCaseProgramCargo.getEffBeginDt()!= null) {
		beginDt =  dcCaseProgramCargo.getEffBeginDt().toString();
	}else {
		beginDt=caseRegDate;
	}
	
	effBegDates = beginDt.split("-");
	
	if(dcCaseProgramCargo.getEffEndDt()!= null){
		endDt =  dcCaseProgramCargo.getEffEndDt().toString();
	} 
  //Added for BRGUS00047832 - CR 20857 
	//CH-955:Start:Withdraw additional info is removed from the screen. Hence following if construct is commented.
	// if(dcCaseProgramCargo.getWithdrawAdditionalInfo()!= null){
		// withdrawlAddInfo =  dcCaseProgramCargo.getWithdrawAdditionalInfo();
	// } 
	
	
	//CR20626 - DA - BRGUS00126533 - Arakalas
	//CH-955:Start:Ankit:Extension Action Date is removed from the screen
	//if(dcCaseProgramCargo.getExtendSopSw() != 0)
		//extendSOPSw = String.valueOf(dcCaseProgramCargo.getExtendSopSw());
	
	
	/*	
	if(dcCaseProgramCargo.getSopExtensionActionDt() != null){
		extensionActionDate = dcCaseProgramCargo.getSopExtensionActionDt();
	}else if(extensionDate != null){
		extensionActionDate = extensionDate;
	}	
	
	if(	extensionActionDate != null){
		dateOfSOPExt = extensionActionDate.toString();		
	}
	*/
	
	//if(dcCaseProgramCargo.getComplianceDt() != null)
		//dateOfComp = dcCaseProgramCargo.getComplianceDt().toString();
	//CH-955:End:Ankit:Extension Action Date is removed from the screen
	
	//End CR20626 - DA - BRGUS00126533
}
// Retrieve Program Status(can be sent from server-this data)
Map refData2 = FwReferenceTableManager.getReferenceTableRow(true,"EDEDGSTATUS");
Iterator keys = refData2.keySet().iterator();
while(keys.hasNext()) {
	name1 = (String)keys.next();
	RefTableData rtd = new RefTableData();
	rtd = (RefTableData)refData2.get(name1);
	prStatCode.add((String) rtd.getRefrTableCode());
	
	Iterator addi = rtd.refrTableAddiData.keySet().iterator();
	while(addi.hasNext()) {
		name2 = (String)addi.next();
		if(name2.equals("DCDESCRIPTION") )
		prStatDesc.add((String) rtd.refrTableAddiData.get(name2));
	}
}                     
%>


<%/* EDITABLE - Additional JSP Scriplets (You can add code to this section) - this include contains all js functions */%> 
<% //System.out.println("before including dccaseprogram jsp");%>
<%@ include file = "/includes/DCCaseProgram.jsp" %>
<% //System.out.println("after including dccaseprogram jsp");%>
<%
	//call Of JS functions /modes of different fields starts here
	//End Date JS logic
	//System.out.println("dcCaseProgramCargo.getEffEndDt()");
	    
	//if(dcCaseProgramCargo.getEffEndDt()  !=  null) {
	//	disableEndDt = "document.form1.monthendDate.disabled = true;document.form1.yearendDate.disabled = true;document.form1.dateendDate.disabled = true;";
	//}else {
	//	disableEndDt = "enableFieldsOnYes('N',document.form1.monthendDate,document.form1.dateendDate,document.form1.yearendDate);";
	//}
	//Grand Subsidy sw JS/Mode logic
   
 //  subsidyChk = "enableFieldsOnNo(document.form1.programs,document.form1.grandParentSubsidySw)";
   //subsidyChk = "enableDisableGParent(document.form1.programs,document.form1.grandParentSubsidySw)";
  // String gsFSjs = "validate = 'isComplete("+grandParentSubsidySw.LABEL+"~document.form1."+grandParentSubsidySw.FIELD_NAME+")'";
   if(progCd.equals("TF")){
		gsMode = 1;
	} 
	//OnLoad JS Logic in edit mode and insert mode
	
	//CH-955:Start:Ankit:Remove call to disableWithdrawAdditionalInfo and withdrawClosureReason  since the field is removed from the screen
	String withdrawJS = "enableFieldsOnYesForWithDraw(document.form1.withdrawCloseSw,document.form1.monthwithdrawClosureDt,document.form1.datewithdrawClosureDt,document.form1.yearwithdrawClosureDt,document.form1.requestType,document.form1.reqInfDetails);";  // added function for BRGUS00047832 - CR 20857//#6-CODE-MOD//#7-CODE-MOD--UNDO-CHANGES(6,7)-01/31/2008 
	//CH-955:End:Ankit:Remove call to disableWithdrawAdditionalInfo and withdrawClosureReason since the field is removed from the screen
	//ND-33996 : reinstate reason field is removed and reactivation reason field has the same logic
	String reinJS = "enableFieldsOnYes(document.form1.reinstatementEffectiveDate,document.form1.reactivationReason);";
	String validateCCWithdraw = " validate=\"checkCCWithdrawl()\" ";
	String onLdDisWithdrawJS = "";
	String onChangeWithdrawJS= "onChange = \"" + withdrawJS + "\"";
	onChangeWithdrawJS+=validateCCWithdraw;
	String onChangeReinJS= "onChange = \"" + reinJS + "\"";
	//Production patch
	//BRGUS00063214-RA-moved script to method from onload call. 
	StringBuffer priorDtJS = new StringBuffer("disableDtFields()");
	String overridePMdisable = "";
	
   // overridePMdisable = "enableFieldsOnNo(document.form1.priorMedicaidDates,document.form1.monthpriorMedicaidOtherDate,document.form1.datepriorMedicaidOtherDate,document.form1.yearpriorMedicaidOtherDate)";
   overridePMdisable = "overrideEnableOnNo()";
	//BRGUS00133967 -- JosephI1 -- Filter for WITHDRAW reasons modified.
	//CH-955:Start:Ankit: Remove call to disableFieldsOnNo.,;preLoadWithDrawReason();filterwithdrawClosureReason();retainWithdrawSelection(),enableDisableMedicaidType(),enableDisableDhs3043(),enableDisableReactivationDateReason() The fields specified in the parameter have been removed form the screen--new fn added by nil enableDisableApplicationStatDt
	onLoadJS ="onLoad=\"disableTabs('tab4');disableTabs('tab5');" + priorDtJS.toString() + ";" + overridePMdisable + ";"  + subsidyChk +  ";" +disableEndDt + "disableProgStat();" +  withdrawJS + ";setDateIfChanged();disableWithdrawFields();onloadRulesForRR();enableApplicationDate();initializeIOVars();disablePrgmWithdrawClosureSection();disableReinstatement();disableRevertDate();disableLiheap();"+"validateTanfLabels();enableReactivation();setApplicationDate();enableDisableApplicationStatDt();disableWithdrawCloseSw();" + "\"" ;//#9-CODE-MOD//#8-CODE-MOD//CODE-AG-MOD-02/20/2007-DEFECT#BRGUS00028562-enableDisableMedicaidType()added to disable Medicaid Type on load
	//CH-955:End:Ankit: Remove call to ;preLoadWithDrawReason();filterwithdrawClosureReason();retainWithdrawSelection() The fields specified in the parameter have been removed form the screen
	
	//END OF BRGUS00133967
	if(editThisRec.equals("Y"))
	{	
		prgMode = 0;
		prgTypeMode = 0;
		eleMode = 1;
		if(reappDtMode.equals("1"))	{
	reapplyMode = 1;
		}
		if((!progTp.equals("P0") ) ){
	effDtsMode = 0;  
		}  
		if((programStatusCd.equals("TN") || programStatusCd.equals("DN")) && !rescindVal){
	onLdDisWithdrawJS = ";disableWithDrawSw()";
		}
		//recMode = 1;
		//production patch
		//BRGUS00133967 -- JosephI1 -- Filter for WITHDRAW reasons modified.
		//CH-955:Start:Ankit: Remove call to enableFieldsOnYes,;preLoadWithDrawReason();filterwithdrawClosureReason();retainWithdrawSelection(),enableDisableMedicaidType(),. The fields specified in the parameter have been removed form the screen --new fn added enableDisableApplicationStatDt
		onLoadJS = "onLoad=\"disableTabs('tab4');disableTabs('tab5');"+ priorDtJS.toString() +";" + subsidyChk + ";clearRecFields();enableRein();disableWithdrawFields();disableRevertDate();validateTanfLabels();enableReactivation();disableLiheap();disableOtherTabs();enableDisableApplicationStatDt();disableWithdrawCloseSw();";
		//CH-955:Start:Ankit: Remove call to enableFieldsOnYes,;preLoadWithDrawReason();filterwithdrawClosureReason();retainWithdrawSelection(). The fields specified in the parameter have been removed form the screen
		//END OF BRGUS00133967
		//CH-955:End:Ankit: Remove call to enableFieldsOnYes. The fields specified in the parameter have been removed form the screen
		
		//onLoadJS += "enableFieldsOnYes('N',document.form1.monthreactivationDate,";
		//onLoadJS += "document.form1.datereactivationDate,document.form1.yearreactivationDate,";
		//onLoadJS += "document.form1.monthreapplicationDate,document.form1.datereapplicationDate,";
		//onLoadJS += "document.form1.yearreapplicationDate,";
		//onLoadJS += "document.form1.reactivationReason);";
		//BRGUS00133967 -- JosephI1 -- Filter for WITHDRAW reasons modified.
		//CH-955:Start:Ankit:Remove call to disableWithdrawAdditionalInfo,enableExtendSOP(),enableDisableReactivationDateReason() since the field is removed from the screen -- new fn enableDisableApplicationStatDt added
		onLoadJS += withdrawJS +  ";onLoadCheckProgStatus();disableProgStat();enableDisableExpitedTab();onloadRulesForRR();enableApplicationDate();enableRein();disablePrgmWithdrawClosureSection();validateTanfLabels();enableReactivation();enableDisableApplicationStatDt();disableWithdrawCloseSw();\"";//#9-CODE-MOD//#8-CODE-MOD// added function for BRGUS00047832 - CR 20857  // added function for BRGUS00047832 - CR 20857 //#1 CODE-MOD
		//CH-955:End:Ankit:Remove call to disableWithdrawAdditionalInfo,enableExtendSOP() since the field is removed from the screen
		//END OF BRGUS00133967
		//CH-955:Start:Ankit: Remove call to disableFieldsOnNo. The fields specified in the parameter have been removed form the screen
		//validateWithdrawRsn = "validate=\"isComplete(" + withdrawClosureReason.LABEL + "~document.form1." + withdrawClosureReason.FIELD_NAME+ ")"; 
		//CH-955:End:Ankit: Remove call to disableFieldsOnNo. The fields specified in the parameter have been removed form the screen
		
		//Program status section logic
		if(programStatusCd.equals("AP") || programStatusCd.equals("PE")) { //take out these rules and put in server side
	nonEditPrgSt = 0;
		} 
		String manRecReason = "validate = \"isComplete(" + reactivationReason.LABEL + "~document.form1." + reactivationReason.FIELD_NAME + ")\"";
		//CH-955:Start:Ankit:Removed call to disableFieldsOnNo since the fields passed as parameters have been removed from the screen.
		recReasonChk.append("onChange=\"setReqDate();clearReqDates();enableWithDrawSw();resetToOldValuesForRec();\"" );
		//CH-955:End:Ankit:Removed call to disableFieldsOnNo since the fields passed as parameters have been removed from the screen.
		recReasonChk.append("validate=\"setReqDate()\"" );
	}
	//with draw section logic
	//withdraw logic
	//DEFECT#BRGUS00094530
	if( (programStatusCd.equals("TN") || programStatusCd.equals("DN")) && (!(progCd.equalsIgnoreCase("DS"))) ) { //take out these rules and put in server side
		//defWithDrawSw = "N";
		recMode = 1;
	} else if( programStatusCd.equals("PE") && (rescindVal) && (!(progCd.equalsIgnoreCase("DS")))) {
		recMode = 1;
		//eleMode = 0;
	}   
	//Program status section logic
	if(!editThisRec.equals("Y")) {
	
		
 		programStatusCd="PE";
		nonEditPrgSt = 0;
		 
		//program type field logic
       validatePrgType="validate='isComplete(Programs~document.form1.programType);canRequestLTC()'";
	   validatePrgType += " onChange = 'disableEffBeginDate();setDateIfChanged();checkPgmType()' ";
	   //Program field logic
	//CH-955:Start:Ankit:Remove call to filterWithdrawClosureReason,enableDisableMedicaidType();;enableDisableDhs3043() : 
	   validatePrg = "onChange = ';checkPgm();tanfLabels();" + subsidyChk + "' ";
		//CH-955:End:Ankit:Remove call to filterWithdrawClosure Reason: 
		//validatePrg += "validate='validateProg(Programs~form1.programs, progCodesArr)'";
		//withdraw reason
		//validateWithdrawRsn += " onChange=\"disableFieldsOnNo(document.form1.isReferredByChip,document.form1.chipApplication)\""; 

		//priorMedic dates logic
		if (reqDatesArray != null && reqDatesArray.length >= 1) {

			nonEditPriorDt = 1;
			medicDateCds.add("");
			medicDatesVals.add("");
			for (int i = 0; i < reqDatesArray.length; i++) {
				medicDateCds.add(reqDatesArray[i]);
				medicDatesVals.add(reqDatesArray[i]);
			}
			medicDateCds.add("OT");
			medicDatesVals.add("Other");
		} else {
			nonEditPriorDt = 1;
			medicDateCds.add("");
			medicDatesVals.add("");
			medicDateCds.add("OT");
			medicDatesVals.add("Other");
			reqDatesArray = new String[2];
		}

		onChangeMedDtJS = "onChange = \"setDateIfChanged()\" validate=\"chkPriorMaDts()\"";
		onChangePmDtJS.append("onChange = \"setDateIfChanged();");
		onChangePmDtJS.append(overridePMdisable);
		onChangePmDtJS.append("\"");
		onChangePmDtJS
				.append("validate=\"isComplete(Prior Medicaid Dates~document.form1.priorMedicaidDates);chkPriorMaDts()\"");

	}
	//program status field logics 			
	//if(nonEditPrgSt == 1) {
	//CH-955:Start:Ankit:Removed call to enableFieldsOnYes,enableDisableReactivationDateReason() since the fields passed as parameters have been removed from the screen.
	onChangePrgStatJs = "onChange='checkDenied();checkProgStatus();enableWithDrawSw();resetToOldValuesForPrgStatus();enableDisableApplicationDt();disableWithdrawFields();clearSOPFieldsForPS();updateProgStatusSelectedIndex();'";//#9-CODE-MOD
	//CH-955:End:Ankit:Removed call to enableFieldsOnYes since the fields passed as parameters have been removed from the screen.                          
	//}
	// Date Requested JS Logic.			  
	StringBuffer jsChk = new StringBuffer();
	boolean validationChkForAppDt = false;
	int earliestSrecordYear = 0;
	String isCvLoadedRec = (String) request
			.getAttribute("isCvLoadedRec");
	java.sql.Timestamp earliestSrecordAppDate = (java.sql.Timestamp) request
			.getAttribute("earliestSrecordAppDate");
	if (editThisRec.equals("Y")
			&& firstDcCaseProgramCargo.getRequestDt() != null) {
		String stsDtChk = "";
		//int earliestSrecordYear = 0;
		if (earliestSrecordAppDate != null) {
			earliestSrecordYear = earliestSrecordAppDate.getYear() + 1900;
		}
		if (dcCaseProgramCargo.getPriorMedicaidCd() != null
				&& dcCaseProgramCargo.getPriorMedicaidCd().equals("P0")) {
			jsChk.append("onChange = \"setEffBeginDateForRec()");
			jsChk.append(stsDtChk);
			if ("Y".equals(isCvLoadedRec)
					&& earliestSrecordAppDate != null) {
				//BRGUS00161259 --padmanabhunip -- removed the function call here and adding as input var validation, in order to get only one validation
				jsChk.append("\"validate = \"setReqDate();");
				validationChkForAppDt = true;
			} else {
				jsChk.append("\"validate = \"setReqDate();greaterThanAppDate(form1.applicationDate,form1.monthapplicationDate,form1.dateapplicationDate,form1.yearapplicationDate,'Application Date');");
			}
			jsChk.append(stsDtChk);
			jsChk.append("\"");
		} else {
			jsChk.append("onChange = \"setEffBeginDateForRec()");
			jsChk.append(stsDtChk);
			jsChk.append("\"validate = \"setReqDate()");
			jsChk.append(stsDtChk);
			jsChk.append("\"");
		}
	} else if (!editThisRec.equals("Y")) {
		jsChk.append("onChange = \"setEffBeginDateForRec()\"validate = \"setReqDate();checkGoLiveDt();greaterThanAppDate(form1.applicationDate,form1.monthapplicationDate,form1.dateapplicationDate,form1.yearapplicationDate,'Application Date')\"");
	} else {
		jsChk.append("onChange = \"setEffBeginDateForRec()\"validate = \"setReqDate();checkGoLiveDt(); if(document.form1.programStatus.value == 'PE' )compareDates(document.form1.monthapplicationDate,document.form1.dateapplicationDate,document.form1.yearapplicationDate,document.form1.monthprgStatusDate,document.form1.dateprgStatusDate,document.form1.yearprgStatusDate, document.form1.applicationDate,'Application Date','Status Date' )\"");
	}
	StringBuffer effBeginDtJSChk = new StringBuffer();
	String effStsDtChk = "";

	if (editFlag
			&& dcCaseProgramCargo.getPriorMedicaidCd().equals("P0")) {
		if (dcCaseProgramCargo.getProgStatusCd().equals("TN")
				|| ((dcCaseProgramCargo.getProgStatusCd().equals("PE") || dcCaseProgramCargo
						.getProgStatusCd().equals("DN")) && terEver)) {
			//effStsDtChk = ";greaterEffDateThanStatusDate()";
		}
	}
	effBeginDtJSChk.append("onChange = \"setDateIfChanged()");
	effBeginDtJSChk.append(effStsDtChk);
	effBeginDtJSChk.append("\"validate=\"");
	//effBeginDtJSChk.append("greaterThanAppDate(form1.effectiveBeginDate,form1.montheffectiveBeginDate,form1.dateeffectiveBeginDate,form1.yeareffectiveBeginDate,'Effective Begin Date')");//#10-CODE-COMMENT
	effBeginDtJSChk.append(effStsDtChk);
	effBeginDtJSChk.append("\"");
	boolean readonly = false;
	readonly = (securityAttr.getPermission() == 0 || pageMode == 0) ? true
			: false;

	//System.out.println("Read only from caseProgram..........."+readonly);
	if (!readonly) {
		readonly = (dcCaseProgramCargo.getVoidSw() == 'Y' || (dcCaseProgramCargo
				.getHistNavInd() == 'M' && dcCaseProgramCargo
				.getHistNavInd() == 'F')) ? true : false;
	}

	// Reset all modes to 0 if security/pagemode/histNavind/void sw does not permit
	if (readonly) {
		eleMode = 0;
		recMode = 0;
		reapplyMode = 0;
		nonEditPrgSt = 0;
		prgMode = 0;
		nonEditPriorDt = 0;
		prgTypeMode = 0;
		effDtsMode = 0;
		fileDtMode = 0;
		gsMode = 0;
	}

	if (protectAppDates) {
		//fileDtMode = 0;
	}
	if (dcCaseProgramCargo != null
			&& dcCaseProgramCargo.getHistNavInd() != 0) {
		histNav = String.valueOf(dcCaseProgramCargo.getHistNavInd());
	}
	if (dcCaseProgramCargo != null
			&& dcCaseProgramCargo.getVoidSw() != 0) {
		voidSw = String.valueOf(dcCaseProgramCargo.getVoidSw());
	}

	if (request.getAttribute("ReappDateRequired") != null) {
		boolean isReappDateReq = ((Boolean) request
				.getAttribute("ReappDateRequired")).booleanValue();
		isReappDate = (isReappDateReq == true) ? "Y" : "N";
		//	if(isReappDateReq) {commented as on 08/25/03
		//reappDate = "";
		//}
	}

	//System.out.println("element mode ........."+eleMode);	
	//BRGUS00165706 -- padmanabhunip
	String isFCTMAUser = (String) request.getAttribute("IS_FCTMA_USER");

	String dcCaseMode = (String) request
			.getAttribute(DcConstants.CASE_MODE);
	byte og;
	if (dcCaseMode.equals("OG")) {
		og = 0;
	} else {
		og = 1;
	}
%>


<%/* EDITABLE WITH PERMISSION (You can add code to this section) - Body and Form Tags */%>
<TL:FWBodyTag security="<%=securityAttr.getPermission()%>" voidSw="<%=voidSw%>" histNavInd="<%=histNav%>" mode = "<%= pageMode%>" caseMode = "<%= simulation %>"  other ="<%=onLoadJS%>" />
<form name="form1" method="post" action="/ControllerServlet"  >
<%/* NOT EDITABLE - Framework Hidden Fields */%>
<%@ include file = "/fwincludes/hiddenFields.jsp" %>
<%/* EDITABLE - Page Specific Hidden Fields (You can add code to this section) */%>
 <%
 if(request.getAttribute("EDIT_FLAG")!=null){
 %>
 <input type="hidden" name="DCPAREditMode" value="<%=String.valueOf(request.getAttribute("EDIT_FLAG"))%>" />
 <%
 }
 %>
<input type="hidden" name="navigation">
<input type="hidden" name="CFMCdExist" value="<%=cfmc%>" />
<input type="hidden" name="valDhs3043" value="<%=validateDhs3043ForDss%>"/><%--#3-CODE-ADD--%>
<% if("Y".equals(isCvLoadedRec) && earliestSrecordAppDate!=null){%>
<!--CH:955:Start:Ankit:Remove call to validateWithdrawReason since the corresponding field has been removed from the screen  -->
<input type="hidden" name="checkDss3043" data-validate="validateDhs3043();" />
<%}else{%>
<!--CH:955:Start:Ankit:Remove call to validateWithdrawReason since the corresponding field has been removed from the screen  -->
<input type="hidden" name="checkDss3043" data-validate="validateDhs3043();" /><%--#11-CODE-MOD--%><%--#8-CODE-ADD--%> 
<%} %>

<input type="hidden" name="validateAppStartDt" id="validateAppStartDt" data-validate="validateAppStartDt();" />

<input type="hidden" name="editRowId" value="<%=request.getAttribute("editRowId")%>">
<input type="hidden" name="appDate" value="<%=caseRegDate%>">
<input type="hidden" name="yearappDate" value="<%=caseRegDate.substring(0,4)%>">
<input type="hidden" name="monthappDate" value="<%=caseRegDate.substring(5,7)%>">
<input type="hidden" name="dateappDate" value="<%=caseRegDate.substring(8,10)%>">
<input type="hidden" name="yeargoLiveDt" value="<%=goLiveDate.toString().substring(0,4)%>">
<input type="hidden" name="monthgoLiveDt" value="<%=goLiveDate.toString().substring(5,7)%>">
<input type="hidden" name="dategoLiveDt" value="<%=goLiveDate.toString().substring(8,10)%>">
<input type="hidden" name="yearappReceivedDate" value="<%=caseReqDate!=""?caseReqDate.substring(0,4):""%>">
<input type="hidden" name="monthappReceivedDate" value="<%=caseReqDate!=""?caseReqDate.substring(5,7):""%>">
<input type="hidden" name="dateappReceivedDate" value="<%=caseReqDate!=""?caseReqDate.substring(8,10):""%>">
<input type="hidden" name="yearpriorMaDate">
<input type="hidden" name="monthpriorMaDate" >  
<input type="hidden" name="datepriorMaDate" >
<input type="hidden" name="priorMaDate" >
<input type = "hidden" name = "<%= firstPrgDate.FIELD_NAME %>" value = "" >
<input type="hidden" name="yearfirstPrgDate" value="<%=firstProgDtYr %>" >
<input type="hidden" name="monthfirstPrgDate" value="<%=firstProgDtMth%>">
<input type="hidden" name="datefirstPrgDate" value="<%=firstProgDtDt%>">
<input type="hidden" name="prgStatusDate" value="" >
<input type="hidden" name="yearprgStatusDate" value="<%=prgStatusDateYr %>" >
<input type="hidden" name="monthprgStatusDate" value="<%=prgStatusDateMth%>">
<input type="hidden" name="dateprgStatusDate" value="<%=prgStatusDateDt%>">
<input type="hidden" name="initialFileDate"  value="<%=(dcCaseProgramCargo != null && dcCaseProgramCargo.getRequestDt() != null ) ? dcCaseProgramCargo.getRequestDt().toString() : new String("")%>">
<!-- CH-955:Start:Ankit:Removed call to isNumeric since the fields passed as parameters have been removed from the screen.-->
<input type="hidden" name="validateApplicationNumber" data-validate="setEffectiveBeginDate();" ><%--FO803--%><%--BRGUS00102230-upadhyayulag-call javascript function setEffectiveBeginDate() to set the prior medicaid date--%>
<!-- CH-955:End:Ankit:Removed call to isNumeric since the fields passed as parameters have been removed from the screen.-->
<input type="hidden" name="expScreenVisited" value="<%=request.getAttribute("expScreenVisited")%>"><%--#1 CODE-ADD--%>
<input type="hidden" name="progStatusSelectedIndex" value="0"><%--#1 CODE-ADD--%>
<input type="hidden" name = "progCd1" value=<%=dcCaseProgramCargo.getProgCd()%> />
<%--#11 CODE-ADD-START--%>
		<% 
			DcCasesCargo ad = (DcCasesCargo)(request.getAttribute("dcCasesCargo"));
			String str = null;
			if(ad!=null && dcCaseProgramCargo.getApplicationDt()!=null)str = dcCaseProgramCargo.getApplicationDt().toString();
		%>
 
 		<% 
 			boolean appdateNull = str != null && !str.equals("");
		%>
			  <input type="hidden" name="appRecMonth"   value="<%=appdateNull?str.substring(5,7):""%>">
	          <input type="hidden" name="appRecDay"   value="<%=appdateNull?str.substring(8,10):""%>">
	          <input type="hidden" name="appRecYear"   value="<%=appdateNull?str.substring(0,4):""%>">	
	          
	    <% 
			str = null;
			if(ad!=null && dcCaseProgramCargo.getApplicationReceivedDt()!=null)
				str = dcCaseProgramCargo.getApplicationReceivedDt().toString();
		%>
 
 		<% 
 			appdateNull = str != null && !str.equals("");
		%>
			  <input type="hidden" name="appRecDateMonth"   value="<%=appdateNull?str.substring(5,7):""%>">
	          <input type="hidden" name="appRecDateDay"   value="<%=appdateNull?str.substring(8,10):""%>">
	          <input type="hidden" name="appRecDateYear"   value="<%=appdateNull?str.substring(0,4):""%>">
			  <input type=hidden name="checkAppStartDate" data-validate="checkAppStartDate()"> 
			  <input type=hidden name="checkAppStartDate" data-validate="rein()">
			  
<%--#11 CODE-ADD-END--%>
<%/* NOT EDITABLE - Top of Page Include */%>
<%@ include file = "/fwincludes/tabs_NEW.jsp" %>
<%/* EDITABLE - Page Level Icon Includes (You can add code to this section) */%>
<DCTagLibrary:Comments keyOne = "<%= caseNum %>" keyTwo = "<%= progTypeCd.toString() %>" />
<%/*@ include file = "/includes/common/DcCorrespondenceImages.jsp"*/ %>
<%/* NOT EDITABLE - Spacer Images */%>
                      
<div id="MainHeader" Repeat="N">
<%/* EDITABLE - Page Header Include (You can change this include.) */%>
<%@ include file="/includes/DcPageHeader.jsp" %>
<%/* NOT EDITABLE - Begin Outter HTML Table  and  messages file*/%>
<%@ include file="/fwincludes/messageDisplay.jsp" %>
<%/* EDITABLE (You can add code to this section) - Page Specific Table(s) - This is each developers area for coding the presentation. */%>
<%/* EDITABLE - Action Buttons (You can change this include.) */%>
           
<% StringBuffer sb = new StringBuffer(" onclick=\"javascript:setActionFieldAndSubmit(document.form1,'"+ViewConstants.VCR_NEXT_BUTTON+"');return false\" ");
   String vcrNextScript = sb.toString();
   
   sb = new StringBuffer(" onclick=\"javascript:setActionFieldAndSubmit(document.form1,'"+ViewConstants.VCR_PREVIOUS_BUTTON+"');return false\" ");
   String vcrPrevScript = sb.toString();
   
   
   sb = new StringBuffer(" onclick=\"javascript:return doReset(document.form1) \" ");
   String resetScript = sb.toString();
   
   sb = new StringBuffer(" onclick=\"javascript:return setActionFieldAndSubmit(document.form1,'"+ViewConstants.ADD_ARROW_BUTTON+"');return false \" ");
   String addArrowScript = sb.toString();
  
   sb = new StringBuffer("onclick=\"javascript:return setActionFieldAndSubmit(document.form1,'"+ViewConstants.PREVIOUS_BUTTON+"');return false\" " );
   String prevScript = sb.toString();
   
   sb =  new StringBuffer("onclick=\"javascript:return setActionFieldAndSubmit(document.form1,'"+ViewConstants.NEXT_BUTTON+"');return false\" " );
   String nextScript = sb.toString();
  
    sb =  new StringBuffer("onclick=\"javascript:setActionFieldAndSubmit(document.form1,'"+ViewConstants.CANCEL_BUTTON+"','N');return false\" " );
   String cancelScript = sb.toString();
   
   sb=null;
   String appCaseMode = "0";
  if(((String)request.getAttribute(DcConstants.CASE_MODE)).equals(DcConstants.MODE_INTAKE) || ((String)request.getAttribute(DcConstants.CASE_MODE)).equals(DcConstants.MODE_COMPLETEACTION)) {
   		appCaseMode = "1";
	} 
//System.out.println("appCaseMode..............."+appCaseMode);
		
	//system.out.println("checking null pinter exception 9" );
 %>
	  
	  
        <table  style="display:none">
	  <tr> 
      <td ><TL:FWLabelTag bean="<%=effectiveBeginDate%>" /> 
      </td>
      <td > <TL:FWGroupCustomDateTag 
			  beanId="effectiveBeginDate"  
			  pageMode = "1"
			  eightWeekChk = "N"
			  mode="<%= effDtsMode %>"  
			  disableLabel="Y" 
			  tabIndex="1" 
			  dateValue="<%=beginDt%>"
				 other = "<%= effBeginDtJSChk.toString() %>"
              /> </td>
      </tr>
<tr>
<td  > <TL:FWLabelTag bean="<%=endDate%>" /> 
      </td>
      <td  > <TL:FWGroupCustomDateTag			  beanId="endDate"
			  pageMode = "1"
			  disableLabel="Y"
			  tabIndex="4" 
			  mode = "<%= effDtsMode %>"
			  dateValue="<%= endDt %>"
		      /> </td>
  </tr>
 
  
  </table>

	
<hr class="noShade">
<table>
          <caption>
            <H2 class="Black_Heading_2"> 
            Programs 
            </H2>
          </caption>
</table>
	
		   <table title="Programs" summary="Programs" class="bodyFormLabels">
          <tr> 
		  
    <%
	if(editThisRec.equals("Y") || voidSw.equals("Y") ){
	if(progCd.equalsIgnoreCase("DS")){
	%>
	<input type="hidden" name="isDss" value="Y" />
	<%
	}
	else{
	 %>
	 <input type="hidden" name="isDss" value="N" />
	 <%}%>
      <td> <TL:FWDivTag elementId="4" bean="<%=programs%>"   question = "N" />
      </td>
      <td > 
        <input type="hidden" name="programs" value="<%=progCd%>" >
        <b><%=progDesc%> </b>
        <%	 } else { %>
        <input type="hidden" name="isDss" value="N" />
		 <td > <TL:FWLabelTag elementId="4" bean="<%=programs%>"   question = "N" /> 
      </td>
      

<td  id="ToaDescId"> <TL:FWRTSelectTag referenceTableName="PROGRAM" bean="<%=programs%>" filter="YES" filterColumn="ACTIVESW" elementId="4" filterValue="Y" multiple="N" emptySpace="Y" tabindex="9" selected="<%=progCd%>" size="5" other="<%=validatePrg%>"
                 />
        <% } %>
      </td>
 </tr>
 </table>
 <script type="text/javascript">
 function validateTanfLabels()
 {
 	var progCd ='<%=progCd%>';
 	if(progCd!='TF'){
 		$('.tanfFields').hide();
 	}
 }
 function tanfLabels()
 {
 	var progCd =document.form1.programs.value;
 	if(progCd!='TF'){
 		$('.tanfFields').hide();
 	}else{
 		$('.tanfFields').show();
 	}
 
 }
 function disableOtherTabs(){
	disableTabs('tab3');
 }
 </script>
 <table  class="bodyFormLabels">
 
 <tr class="tanfFields">
 <td>
 <TL:FWLabelTag bean="<%=requestingKinshipCare%>" elementId="11" /></td>
 <td><TL:FWRTSelectTag referenceTableName="YESNO" bean="<%=requestingKinshipCare%>" multiple="N" emptySpace="Y" tabindex="1" selected="<%=requestingKinshipVal%>" size="5" /></td>
 </tr>
 <tr class="tanfFields">
 <td>
 <TL:FWLabelTag bean="<%=requestingDiversion%>"  elementId="11" /></td>
 <td><TL:FWRTSelectTag referenceTableName="YESNO" bean="<%=requestingDiversion%>" multiple="N" emptySpace="Y" tabindex="1" selected="<%=requestingDiversionVal%>" size="5"  /></td>
 </tr>
 <tr class="tanfFields">
 <td>
 <TL:FWLabelTag bean="<%=shortTermNeed%>"  elementId="11" /></td>
 <td><TL:FWRTSelectTag referenceTableName="YESNO" bean="<%=shortTermNeed%>" multiple="N" emptySpace="Y" tabindex="1" selected="<%=shortTermNeedVal%>" size="5"  other="onchange=\"onChangeReinstate();\"" /></td>
 </tr> 
 
 
 </table>
 
 <%-- ND-33996 : Field Removed  --%>
 <%-- <tr>
 
 <td><TL:FWLabelTag bean="<%=waitListScreeningRequest%>" elementId="10"  />
                </td>
                <td><TL:FWRTSelectTag referenceTableName="YESNO" bean="<%=waitListScreeningRequest%>" elementId="10"  multiple="N" emptySpace="Y" tabindex="" selected="<%=waitlist%>" size="5" 
                 /></td>
                </td>
</tr> --%>
<table class="bodyFormLabels">
<tr>         			 	<td >
<TL:FWLabelTag elementId="5" bean="<%=programStatus%>" forValue="select1"/>
</td>
      		
<td ><TL:FWSelectTag elementId="5" bean = "<%=programStatus%>"   size="5" id="select1" tabindex="1"  display="<%=prStatDesc%>" value="<%=prStatCode%>" selected="<%=programStatusCd%>"  multiple="N" show = "1" other="<%=onChangePrgStatJs%>" />
      </td> 
    
	</tr>
	<!--CH:955:Start:Ankit:Removed this field from the screen-->
	
	<!-- 	
      <td ><TL:FWLabelTag bean="<%=programType%>"  /> 
      </td>
      --> 
    <!--CH:955:End:Ankit:Removed this field from the screen-->
		  <tr> 
      <%
        String appRcvdDate = null;
			appRcvdDate =((java.sql.Timestamp)request.getAttribute("APPLICATION_RECEIVED_DATE")).toString() ;
			String fmt=appRcvdDate.substring(0, 10);
			String yearValue=appRcvdDate.substring(0,4);
			String monthValue=appRcvdDate.substring(5,7);
			String dateValue=appRcvdDate.substring(8,10);
			String appdt=monthValue+"/01/"+yearValue;
			
		    String statusDtTemp="";
	        if(statusDt!=null){
	        	statusDtTemp=statusDt.toString();
	        	appdt="";
	        	appdt=appdt+statusDtTemp.substring(5,7)+"/";
	        	appdt=appdt+statusDtTemp.substring(8,10)+"/";
	        	appdt=appdt+statusDtTemp.substring(0,4);
	        }
        %>	  
      <td > 
      <TL:FWLabelTag elementId="6" bean="<%=applicationDate%>" /> 
      </td>
	<% if(!editThisRec.equals("Y")) {

	%>
	      <td ><TL:FWGroupCustomDateTag  
	    			beanId     = "applicationDate"   
	    			pageMode   = "1"
	    			tabIndex   = "1"
	    			disableLabel = "Y" 
	    			pastDate="N" dateValue  = "<%=reappDate%>"
					 mode="<%= fileDtMode %>"  
					other = "<%= jsChk.toString() %>"
				    elementId="6"
	  				/> </td>
					<% 
					}
					 else { 
					 if(!(programStatusCd.equalsIgnoreCase("DN") || programStatusCd.equalsIgnoreCase("TN")) ){
					 	fileDtMode=0;
					 //#5-CODE-ADD-START	
					 }else{
					 	fileDtMode=1;
					 }
					 //#5-CODE-ADD-START	
					 %>
	            
					 

<td > <TL:FWGroupCustomDateTag  
	    			beanId     = "applicationDate"  
	    			pageMode   = "1"
	    			tabIndex   = "1"
	    			disableLabel = "Y" 
	    			pastDate="N"
					dateValue  = "<%=reappDate%>"   
					 mode="<%= fileDtMode %>"  
					other = "<%= jsChk.toString() %>"
				    elementId="6"
	  				/> </td>
  				<%if(validationChkForAppDt){%>
					<input type="hidden" name="validateDatesVar" data-validate="checkEarliestCVLoadedRecord('<%=earliestSrecordYear%>','<%=earliestSrecordAppDate.getMonth()%>','<%=earliestSrecordAppDate.getDate()%>');">
					
  				<%}%>
	  				
					  <% //system.out.println("checking null pointer exception 6.3");
				  }%>
				  

	  	  </table>

	  	  <table title="Programs" summary="Programs" class="bodyFormLabels">
	  	  
	  	  <tr>
	  	  <%
	  	  		java.util.ArrayList reAppValues= new ArrayList();
	  	  		java.util.ArrayList reAppDisplay= new ArrayList();
	  	  		reAppValues.add("RA");
	  	  		reAppValues.add("RI");
	  	  		reAppDisplay.add("Application form filed or updated");
	  	  		reAppDisplay.add("Reinstatement needed");
	  	   %>
	  	   <script type="text/javascript">
	  	   var rrProgStatus="";
	  	   var rrmonthAppDate="";
	  	   var rrdateAppDate="";
	  	   var rryearAppDate="";
	  	   var rrMonth="mm";
	  	   var rrDate="dd";
	  	   var rrYear="yyyy";
	  	   var rrReason="";
	  	   <% if(oldCaseProgramCargo!=null && oldCaseProgramCargo.getProgCd()!=null){
	  	   java.text.SimpleDateFormat rrsdf = new  java.text.SimpleDateFormat("MM/dd/yyyy");
	  	   
	  	   if(oldCaseProgramCargo.getApplicationDt()!=null){
	  	   				String  rrAppDateStr = rrsdf.format(oldCaseProgramCargo.getApplicationDt()); %>
	  	   				rrProgStatus = "<%=oldCaseProgramCargo.getProgStatusCd()%>";
	  	   				rrmonthAppDate ="<%=rrAppDateStr.substring(0,2)%>";
	  	   				rrdateAppDate ="<%=rrAppDateStr.substring(3,5)%>";
	  	   				rryearAppDate ="<%=rrAppDateStr.substring(6,10)%>";
	  	   				
	  	   				<%}%>
	  	   				<%if(oldCaseProgramCargo.getProgRescindCd()!=null){%>
	  	   						rrReason = "<%=oldCaseProgramCargo.getProgRescindCd()%>";
	  	   				<%}%>
	  	   				<%if(oldCaseProgramCargo.getProgRescindDt()!=null){
	  	   						String  rrStr = rrsdf.format(oldCaseProgramCargo.getProgRescindDt());
	  	   				%>
	  	   				rrMonth ="<%=rrStr.substring(0,2)%>";
	  	   				rrDate ="<%=rrStr.substring(3,5)%>";
	  	   				rrYear ="<%=rrStr.substring(6,10)%>";
	  	   				<%}%>
	  	   <% }%>
	  	   
	  	   <%-- BRGUS00129089 On Load rules for Re Request / Re Activate fields --%>
	  	  
	  	   function onloadRulesForRR(){


	  	   		<%if(editProgram.equalsIgnoreCase("Y")){%>
	  	   		//CH-955:Start:Ankit:Commented references to reactivation date since the field is removed from the screen	
	  	   		//document.form1.monthreactivationDate.disabled=true;
				//document.form1.datereactivationDate.disabled=true;
				//document.form1.yearreactivationDate.disabled=true;
	  	   		//CH-955:End:Ankit:Commented references to reactivation date since the field is removed from the screen

				document.form1.reactivationReason.disabled=true;
				$("#reactivationReason").attr('style','background-color:#C0C0C0');
				document.form1.programStatus.disabled=true;
	  	   		//document.form1.monthapplicationDate.disabled=true;
	  	   		//document.form1.dateapplicationDate.disabled=true;
	  	   		//document.form1.yearapplicationDate.disabled=true;
	  	   		<%if(oldCaseProgramCargo.getProgStatusCd()!=null && !oldCaseProgramCargo.getProgStatusCd().equals("PE") && dcCaseProgramCargo.getProgStatusCd().equals("PE")){%>
	  	   					//CH-955:Start:Ankit:Commenting this line since the field is removed from the screen
	  	   					//document.form1.isReappOrReinstate.options[1].selected=true;
	  	   					//CH-955:End:Ankit:Commenting this line since the field is removed from the screen
	  	   		<%}%>
	  	   		
	  	   					<%if(dcCaseProgramCargo.getProgRescindCd()!=null &&
	  	   					(dcCaseProgramCargo.getProgRescindCd()!=null && !dcCaseProgramCargo.getProgRescindCd().trim().equals(""))) {%>
	  	   									//CH-955:Start:Ankit:Commenting this line since the field is removed from the screen
	  	   									//document.form1.isReappOrReinstate.options[2].selected=true;
	  	   									//CH-955:End:Ankit:Commenting this line since the field is removed from the screen
	  	   									//CH-955:Start:Ankit:Commented references to reactivation date since the field is removed from the screen
										  	//document.form1.monthreactivationDate.disabled=false;
								  	   		//document.form1.datereactivationDate.disabled=false;
	  	   									//document.form1.yearreactivationDate.disabled=false;
	  	   									//CH-955:End:Ankit:Commented references to reactivation date since the field is removed from the screen
	  	   									
	  	   									document.form1.reactivationReason.disabled=false;
	  	   								  $("#reactivationReason").attr('style','background-color:#FFFFFF');
	  	   					<%}else{
	  	   								String rrPrgStat = oldCaseProgramCargo.getProgStatusCd();
	  	   								if(rrPrgStat!= null && (rrPrgStat.equals("PE") || rrPrgStat.equals("AP")))
	  	   							 {%>
	  	   							 		//CH-955:Start:Ankit:Commenting this line since the field is removed from the screen
	  	   									//document.form1.isReappOrReinstate.disabled  = true;
	  	   									//CH-955:End:Ankit:Commenting this line since the field is removed from the screen
	  	   									document.form1.reactivationReason.disabled=true;
	  	   								$("#reactivationReason").attr('style','background-color:#C0C0C0');
	  	   								<%}else{%>
	  	   								//CH-955:Start:Ankit:Commenting this line since the field is removed from the screen
	  	   								//document.form1.isReappOrReinstate.disabled  = false;
	  	   								//CH-955:End:Ankit:Commenting this line since the field is removed from the screen
	  	   								document.form1.reactivationReason.disabled=false;
	  	   							$("#reactivationReason").attr('style','background-color:#FFFFFF');
	  	   								<%}%>
	  	   					<%}%>				
	  	   			<%}else{ %>
	  	   				//CH-955:Start:Ankit:Commenting this line since the field is removed from the screen
	  	   				//document.form1.isReappOrReinstate.disabled=true;
	  	   				//CH-955:End:Ankit:Commenting this line since the field is removed from the screen
	  	<% } %>
	  	<%	boolean isUserInHelperRole = false;
	  	
	  		if (request.getAttribute(DcConstants.IS_USER_IN_HELPER_ROLE) != null) {
	  			isUserInHelperRole = (Boolean) request.getAttribute(DcConstants.IS_USER_IN_HELPER_ROLE);	
	  		}
 			
			if (isUserInHelperRole) {%>
				document.form1.reactivationReason.disabled = false;
				$("#reactivationReason").attr('style','background-color:#FFFFFF');
			<%}
			else {
				long daysAfterClosure = 0;
				
				if (request.getAttribute(DcConstants.DAYS_AFTER_CLOSURE) != null) {
					daysAfterClosure = (Long) request.getAttribute(DcConstants.DAYS_AFTER_CLOSURE);
				}
				
				if (daysAfterClosure > 3) {%>
					document.form1.reactivationReason.disabled = true;
					$("#reactivationReason").attr('style','background-color:#C0C0C0');
				<%} else {%>
					document.form1.reactivationReason.disabled = false;
					$("#reactivationReason").attr('style','background-color:#FFFFFF');
				<%}
			}%>		
	  	   }
	  	   
function enableFieldsOnYesForWithDraw(withdrawCloseSw,monthwithdrawClosureDt,datewithdrawClosureDt,yearwithdrawClosureDt,requestType,reqInfDetails){
	//ND-33996 : DEFAULT DATE IS SET TO APPLICATION START DATE 
	if(withdrawCloseSw.value=='Y'){
		document.form1.monthwithdrawClosureDt.disabled=false;
		document.form1.datewithdrawClosureDt.disabled=false;
		document.form1.yearwithdrawClosureDt.disabled=false;
		document.form1.requestType.disabled=false;
		document.form1.reqInfDetails.disabled=false;
		document.form1.dimg_withdrawClosureDt.disabled=false;
		$('#withdrawClosureDt').datepicker("enable");
	}else{
		$('#monthwithdrawClosureDt').val('MM');
		$('#datewithdrawClosureDt').val('DD');
		$('#yearwithdrawClosureDt').val('YYYY');
		$('#requestType').val('');
		$('#reqInfDetails').val('');
		//$('#dimg_withdrawClosureDt').val('');
		
		document.form1.monthwithdrawClosureDt.disabled=true;
		document.form1.datewithdrawClosureDt.disabled=true;
		document.form1.yearwithdrawClosureDt.disabled=true;
		document.form1.requestType.disabled=true;
		document.form1.reqInfDetails.disabled=true;
		document.form1.dimg_withdrawClosureDt.disabled=true;
		$('#withdrawClosureDt').datepicker("disable");
		//document.form1.dimg_withdrawClosureDt.disabled=true;
	}
}
function disableLiheap(){
	if(null != enableLihSw && enableLihSw =="N"){
			$("select option[value='LI']").hide();
	}else if(null != enableLihSw && enableLihSw =="Y"){
			$("select option[value='LI']").show();
	}
}
function disableRevertDate() {
	$('#reinstatementEffectiveDate').datepicker("disable");
	//ND-33996 : THIS METHOD IS CALLED TO SET THE DEFAULT APPLICATION DATE
	appStartDate();
}
//ND-33996 : THIS METHOD IS TO SET THE DEFAULT APPLICATION DATE
function appStartDate()
{
	var mm='<%=monthValue%>';
	var dd='<%=dateValue%>';
	var year='<%=yearValue%>';
	//CHANGES MADE FOR ND:40673
	<% if(null != request.getAttribute("applicationDateEmpty") && 
		"Y".equals((String) request.getAttribute("applicationDateEmpty"))) {%>
		document.form1.monthapplicationDate.value=mm;
		document.form1.dateapplicationDate.value=dd;
		document.form1.yearapplicationDate.value=year;
	<%}%>
}
//ND:33996 - A NEW FUNCTION ADDED TO VALIDATE APPLICATION START DATE FIELD
function checkAppStartDate()
{
	var mm='<%=monthValue%>';
	var dd='<%=dateValue%>';
	var yyyy='<%=yearValue%>';
	var ccapProgPresent='<%=ccapPresent%>';
	var date=document.form1.dateapplicationDate.value;
	var month=document.form1.monthapplicationDate.value;
	var year=document.form1.yearapplicationDate.value;
	var errArr=createErrArr(document.form1.dateapplicationDate,document.form1.monthapplicationDate,document.form1.yearapplicationDate);
	if(ccapProgPresent!='Y'){
	if(yyyy>year)
	{
		alertMe('DC3060',errArr,"Application Start Date","Application Received Date");
		return false;
	}else if(year==yyyy)
	{
		if(mm>month)
		{
			alertMe('DC3060',errArr,"Application Start Date","Application Received Date");
			return false;
		}
		if(mm==month)
		{
			if(dd>date)
			{
				alertMe('DC3060',errArr,"Application Start Date","Application Received Date");
				return false;
			}
			
		}
	}
	}
}	
function enableRein(){
var kk ='<%=caseStatusAction%>';
var cmode='<%=caseModeCdString%>';
var rein=document.form1.isReinstatement.value;
//ND-33996 : Commented as 'Waitlist Screening Request' field is removed.
/* if(kk=="Pending" && cmode=="Intake"){

	//document.form1.waitListScreeningRequest.disabled=false;
}
else{
//document.form1.waitListScreeningRequest.disabled=true;
} */
var ps=document.getElementsByName('programStatus')[0].value;

if(ps=="TN" || ps=="DN"){
//alert("in ps==TN");
//Code modified according to ND-33996
if(rein=="YES" || rein=="Y")
{
document.form1.isReinstatement.disabled=false;
document.form1.monthreinstatementEffectiveDate.disabled = false;
document.form1.datereinstatementEffectiveDate.disabled = false;
document.form1.yearreinstatementEffectiveDate.disabled = false;
document.form1.reactivationReason.disabled = false;
$("#reactivationReason").attr('style','background-color:#FFFFFF');
document.form1.dimg_reinstatementEffectiveDate.disabled = false; 
$('#reinstatementEffectiveDate').datepicker("enable");
}
else{
	//document.form1.isReinstatement.disabled=true;
	
	//document.form1.isReinstatement.disabled=true;
	//document.getElementsByName("isReinstatement")[0].selectedIndex = 0;
	
	document.form1.reactivationReason.disabled=true;
	$("#reactivationReason").attr('style','background-color:#C0C0C0');
	document.form1.monthreinstatementEffectiveDate.disabled = true;
	document.form1.datereinstatementEffectiveDate.disabled = true;
	document.form1.yearreinstatementEffectiveDate.disabled = true;
	
	document.getElementsByName("monthreinstatementEffectiveDate")[0].value="mm";
	document.getElementsByName("datereinstatementEffectiveDate")[0].value = "dd";
	document.getElementsByName("yearreinstatementEffectiveDate")[0].value = "yyyy";

	document.getElementsByName("reactivationReason")[0].selectedIndex = 0;
	
}
}
else{
	document.form1.isReinstatement.disabled=true;
	
	document.form1.isReinstatement.disabled=true;
	document.getElementsByName("isReinstatement")[0].selectedIndex = 0;
	
	document.form1.reactivationReason.disabled=true;
	$("#reactivationReason").attr('style','background-color:#C0C0C0');
	document.form1.monthreinstatementEffectiveDate.disabled = true;
	document.form1.datereinstatementEffectiveDate.disabled = true;
	document.form1.yearreinstatementEffectiveDate.disabled = true;
	
	document.getElementsByName("monthreinstatementEffectiveDate")[0].value="mm";
	document.getElementsByName("datereinstatementEffectiveDate")[0].value = "dd";
	document.getElementsByName("yearreinstatementEffectiveDate")[0].value = "yyyy";

	document.getElementsByName("reactivationReason")[0].selectedIndex = 0;
	}

}
    function   disableReinstatement() {
    	
    	var ps=document.getElementsByName('programStatus')[0].value;
    	var pCd = document.getElementsByName("programs")[0].value;
    	if(ps=="TN" && (pCd == "MA" || pCd == "TF")){
    		  
    	       document.form1.isReinstatement.disabled=false;
	    		if(document.form1.isReinstatement.value=='Y' || document.form1.isReinstatement.value=='YES'){
	    			document.form1.monthreinstatementEffectiveDate.disabled = false;
	        		document.form1.datereinstatementEffectiveDate.disabled = false;
	        		document.form1.yearreinstatementEffectiveDate.disabled = false;
	        		document.form1.reactivationReason.disabled = false;
	        		$("#reactivationReason").attr('style','background-color:#FFFFFF');
	        		document.form1.dimg_reinstatementEffectiveDate.disabled = false; 	
	    		}else{

	    			document.form1.monthreinstatementEffectiveDate.disabled = true;
	        		document.form1.datereinstatementEffectiveDate.disabled = true;
	        		document.form1.yearreinstatementEffectiveDate.disabled = true;
	        		document.form1.reactivationReason.disabled = true;
	        		$("#reactivationReason").attr('style','background-color:#C0C0C0');
	        		document.form1.dimg_reinstatementEffectiveDate.disabled = true;
	    		}
    		
    		
    		}
    	else{

    		document.form1.isReinstatement.disabled=true;
    		
    		document.form1.isReinstatement.disabled=true;
    		document.getElementsByName("isReinstatement")[0].selectedIndex = 0;
    		
    		document.form1.reactivationReason.disabled=true;
    		$("#reactivationReason").attr('style','background-color:#C0C0C0');
    		document.form1.monthreinstatementEffectiveDate.disabled = true;
    		document.form1.datereinstatementEffectiveDate.disabled = true;
    		document.form1.yearreinstatementEffectiveDate.disabled = true;
    		document.getElementsByName("monthreinstatementEffectiveDate")[0].value="mm";
    		document.getElementsByName("datereinstatementEffectiveDate")[0].value = "dd";
    		document.getElementsByName("yearreinstatementEffectiveDate")[0].value = "yyyy";

    		document.form1.reactivationReason.disabled = true;
    		$("#reactivationReason").attr('style','background-color:#C0C0C0');
    		document.getElementsByName("reactivationReason")[0].selectedIndex = 0;
    	}
    	// code to enable disable Withdrawal/Closure
    	//ps="TN";
    	if(ps=="DN" || ps=="TN"){
    		/* document.getElementsByName("monthwithdrawClosureDt").value="mm";
    		document.getElementsByName("datewithdrawClosureDt").value = "dd";
    		document.getElementsByName("yearwithdrawClosureDt").value = "yyyy"; */
    		//document.form1.dimg_withdrawClosureDt.disabled = true;
    		document.getElementsByName("monthwithdrawClosureDt")[0].value="MM";
    		document.getElementsByName("datewithdrawClosureDt")[0].value = "DD";
    		document.getElementsByName("yearwithdrawClosureDt")[0].value = "YYYY";
    		document.getElementsByName("withdrawCloseSw")[0].selectedIndex = 0;
    		
    	}
    	if(ps=="TN"){
    		if(document.getElementById('monthwithdrawClosureDt').value!='mm' && document.getElementById('datewithdrawClosureDt').value!='dd' && document.getElementById('yearwithdrawClosureDt').value!='yyyy'){
    			var withdrawalDate = new Date(document.getElementById('datewithdrawClosureDt').value, document.getElementById('monthwithdrawClosureDt').value-1, document.getElementById('yearwithdrawClosureDt').value);
    			var currentDate = new Date(Calendar.getYear(), Calendar.getMonth(), Calendar.getDate());//Client machine date
    			withdrawalDate.setDate(withdrawalDate.getDate() + 3);
    			if(currentDate > withdrawalDate){
    				document.form1.isReinstatement.disabled=true;
    			}else{
    				document.form1.isReinstatement.disabled=false;
    			}
    		}
    	}
    	
    	/* document.form1.withdrawCloseSw.disabled=false;
    	document.form1.monthwithdrawClosureDt.disabled=true;
    	document.form1.datewithdrawClosureDt.disabled=true;
    	document.form1.yearwithdrawClosureDt.disabled=true;
    	document.form1.requestType.disabled=true;
    	document.form1.reqInfDetails.disabled=true;
		document.form1.dimg_withdrawClosureDt.disabled=true; */
    }
<%---BRGUS00129089 Switch to help the user in Reactivate or Re request the program--%>
	  	  	  	   </script>
<script >
//var allTWPrograms = new Array();
//var allLTCPrograms = new Array();
//allTWPrograms.push(new Array("TW","FS"));
//allTWPrograms.push(new Array("TW","TF"));
//allTWPrograms.push(new Array("TW","MA"));
//allTWPrograms.push(new Array("TW","CC"));
//allTWPrograms.push(new Array("TW","MC"));
//allLTCPrograms.push(new Array("LT","MA"));
//Start:BRGUS00048493
function enableProgram()
{
   <%
	  if(editThisRec.equals("Y")){
	  ;
	  }
	  else{
	%>
	var selectedProgram = "";
	/*for( var i=0; i<document.form1.category.length; ++i){
		if( document.form1.category[i].checked){
			selectedProgram =  document.form1.category[i].value;
			break;
			}
	}*/
	document.form1.hidCategory.value = selectedProgram;
/*	if(selectedProgram=="TW"){*/
		//document.form1.programs.disabled=false;
		//initiateListCascade('hidCategory', 'twPrograms','programs',allTWPrograms,'Y')
/*	}
	else
	if(selectedProgram=="LT"){
		document.form1.programs.disabled=false;
		initiateListCascade('hidCategory', 'ltcPrograms','programs',allLTCPrograms,'Y')
	}*/
	document.form1.programs.value="<%=progCd%>";
	<%}%>
}
function disableProgram()
{
	<% if(!editThisRec.equals("Y")){ %>
	document.form1.programs.disabled=true;
<%	}%>
}
function disableHousehold(){
	var selectedProgram = document.form1.programs.value;
	if (selectedProgram == "CC"){
		document.form1.withdrawCloseSw.value="N";
		//#7-CODE-COMMENT-START-UNCOMMENT-01/31/2008
		document.form1.monthwithdrawClosureDt.value="";
		document.form1.datewithdrawClosureDt.value="";
		document.form1.yearwithdrawClosureDt.value="";
		document.form1.withdrawClosureDt.value="";
		//#7-CODE-COMMENT-END--UNCOMMENT-01/31/2008
		document.form1.requestType.value="";
		document.form1.withdrawCloseSw.disabled=true;
		//#7-CODE-COMMENT-START--UNCOMMENT-01/31/2008
		document.form1.monthwithdrawClosureDt.disabled=true;
		document.form1.datewithdrawClosureDt.disabled=true;
		document.form1.yearwithdrawClosureDt.disabled=true;
		$('#withdrawClosureDt').datepicker("disable");
		//#7-CODE-COMMENT-END--UNCOMMENT-01/31/2008
		document.form1.requestType.disabled=true;
	}else{
		document.form1.withdrawCloseSw.disabled=false;
		//document.form1.monthwithdrawDt.disabled=false;
		//document.form1.datewithdrawDt.disabled=false;
		//document.form1.yearwithdrawDt.disabled=false;
		//document.form1.withdrawReason.disabled=false;
	}
}
function canRequestLTC(){
	ltcFlag = true;
	<%if(request.getAttribute("LTC_Program_Sw") != null && !(((Boolean)request.getAttribute("LTC_Program_Sw")).booleanValue())) {%>
		ltcFlag = false;
	<% } %>
	//alert(document.form1.programs.value);
	if((document.form1.programs.value == 'CC' || document.form1.programs.value == 'MC' ) && !ltcFlag) {
		alertMe('DC3051');
		return false;
	}
	return true;
}
function checkCCWithdrawl(){
	var selectedProgram = document.form1.programs.value;
	if(selectedProgram == "CC" && document.form1.withdrawCloseSw.value == "Y") {
		alertMe('DC3061');
		return false;
	}
	else 
		return true;
}
function enableDisableGParent(field1,field2){
	if(!(field1.value == 'TF')){
		field2.selectedIndex = 1;
		field2.disabled = true;
	}else {
		field2.selectedIndex = 0;
		field2.disabled = true;
	}
	
}
function overrideEnableOnNo(){
	
	if(<%=nonEditPriorDt%> == 1){
		enableFieldsOnNo(document.form1.priorMedicaidDates,document.form1.monthpriorMedicaidOtherDate,document.form1.datepriorMedicaidOtherDate,document.form1.yearpriorMedicaidOtherDate);	
	}
}
function enableDisableDhs3043(){
//#3-CODE-MOD-START
	//var cfmc = document.form1.CFMCdExist.value;
	var isDhs3043Req = document.form1.valDhs3043.value;
	var isDssCase = document.form1.isDss.value;
	var dssSelected = document.form1.programs.value;
	if(dssSelected!=null){
		if(dssSelected=='DS' && isDhs3043Req=='Y'){
			document.form1.dhs3043Sw.disabled=false;
		}else{	
			//document.form1.dhs3043Sw.value='';
			document.form1.dhs3043Sw.disabled=true;	
		}
	}else{
		if(isDssCase=='Y' && isDhs3043Req == 'Y'){
					document.form1.dhs3043Sw.disabled=false;
		}else{
			//document.form1.dhs3043Sw.value='';
			document.form1.dhs3043Sw.disabled=true;	
		}
	}
//#3-CODE-MOD-END	
	if(dssSelected == 'DS'){
		document.form1.dhs3043Sw.disabled = false;
	}
}
function validateDhs3043(){
	var dssSelected = document.form1.programs.value;
	//#3-CODE-MOD-START
	var isDhs3043Req = document.form1.valDhs3043.value;
	if(dssSelected=='DS' && isDhs3043Req=='Y'){//#4-CODE-MOD
		if(document.form1.dhs3043Sw.value=='Y' || document.form1.dhs3043Sw.value=='N'){
			return true;
		}else{
			//alertMe('DC026',document.form1.dhs3043Sw,'yes/no for Client declared eligible on DHS-3043?' );
			alertMe('GL003',document.form1.dhs3043Sw,document.form1.dhs3043Sw);
			return false;
		}
	//#3-CODE-MOD-END
	}else{
		return true;
	}
}
</script>
<script >
function enableDisableApplicationDt(){
var progStatus="";
	progStatus = "<%=programStatusCd%>";
	var newProgStatus = document.form1.programStatus.value;
	if(progStatus=="DN" || progStatus=="TN"){
		if(newProgStatus=="PE"){
			document.form1.monthapplicationDate.disabled=false;
			document.form1.dateapplicationDate.disabled=false;
			document.form1.yearapplicationDate.disabled=false;
			$("#applicationDate").datepicker("enable");
			//Erase application date when program is requested
			document.form1.monthapplicationDate.value="";
			document.form1.dateapplicationDate.value="";
			document.form1.yearapplicationDate.value="";
		}
		
	}
}
//new function nil begin
function enableDisableApplicationStatDt(){
var convSwMd = '<%=convSw%>';
var progCd ='<%=progCd%>';

		if(progCd == "CD" && convSwMd == 'Y' ) //added else if condn by nil begin 
			{
			document.form1.monthapplicationDate.disabled=false;
			document.form1.dateapplicationDate.disabled=false;
			document.form1.yearapplicationDate.disabled=false;
			$('#applicationDate').datepicker("enable");
			} //added else if condn by nil end
		}

// new function nil end 
function disableWithdrawFields(){
	if(document.form1.programStatus.value=='DN' || document.form1.programStatus.value=='TN'){
		document.form1.withdrawCloseSw.value='N';
		document.form1.withdrawCloseSw.options[1].defaultSelected=true;
		//document.form1.withdrawCloseSw.disabled=true;
		//#7-CODE-COMMENT-START--UNCOMMENT-01/31/2008
		document.form1.monthwithdrawClosureDt.value='';
		document.form1.datewithdrawClosureDt.value='';
		document.form1.yearwithdrawClosureDt.value='';
		//document.form1.monthwithdrawClosureDt.disabled=true;
		//document.form1.datewithdrawClosureDt.disabled=true;
		//document.form1.yearwithdrawClosureDt.disabled=true;
		//#7-CODE-COMMENT-END--UNCOMMENT-01/31/2008		
		//document.form1.withdrawClosureReason.value='';
		//document.form1.withdrawClosureReason.disabled=true;
	}
	
}
function disableWithdrawAdditionalInfo(){
	if(document.form1.requestType.value=='P'){
		document.form1.withdrawAdditionalInfo.disabled=false;
	}else{
		document.form1.withdrawAdditionalInfo.disabled=true;
		document.form1.withdrawAdditionalInfo.value='';
	}
}
//#8-CODE-ADD-START
function validateWithdrawReason(){
	if(document.form1.requestType.disabled ==false){
		var progSelected = document.form1.programs.value;
		if(progSelected=='FS'){
			if(document.form1.requestType != null && document.form1.requestType != "" &&
					document.form1.requestType != " "){
				if(document.form1.requestType.value=='Z'){
				    //BRGUS00160589 : date validations change for CDT and CST time zones
					var sysDate = getToday(); //new Date(document.form1.SysDate.value);
					var tempSysDate = new Date(sysDate.getYear(),sysDate.getMonth(),sysDate.getDate());
					var appDateMonth = document.form1.monthapplicationDate;
					var appDateDate = document.form1.dateapplicationDate;
					var appDateYear = document.form1.yearapplicationDate;
					if(isCompleteDate(appDateYear,appDateMonth,appDateDate)){
						var appReqDate = new Date(appDateYear.value,appDateMonth.value-1,appDateDate.value);
						appReqDate.setDate(appReqDate.getDate()+ parseInt(29));
						if(tempSysDate > appReqDate){
							return true;
						}else{
							alertMe('DC4041',document.form1.requestType);
							return false;
						}											
					}
				}
			}
		}
	}
	return true;	
}
//BRGUS00133967 -- JosephI1 -- Filter for WITHDRAW reasons modified.
function reasonDetails(code1,description1,filter1){
	this.code = code1;
	this.description = description1;
	this.filter = filter1;
	}
function preLoadWithDrawReason(){
	reasonDetailsTable = new Array();
	<%
		String reason="";
		String reasons[]= new String[3];
		List denialReasonList = (List) request.getAttribute("WITHDRAW_REASONS");
		if(denialReasonList!=null){
			for(int k=0;k<denialReasonList.size();k++){
				reason = (String)denialReasonList.get(k);
				reasons = reason.split(",");%>
				reasonDetailsTable.push(new reasonDetails("<%=reasons[0]%>","<%=reasons[1]%>","<%=reasons[2]%>"));
		<%}
	 }%>
}
//END OF BRGUS00133967
function filterwithdrawClosureReason(){
 //Janaganib:10/22/2008:BRGUS00130194:withdraw reasons should be listed in the dropdown on program request screen while navigating back from program request individual screen
    var  programCode= document.form1.programs.value;
    //BRGUS00133967 -- JosephI1 -- Filter for WITHDRAW reasons modified.
   // withdrawIOR(selectedWithdReasonCd);
  	var nooptions = document.form1.requestType.options.length;
	var fullReasons = reasonDetailsTable.length;
		for(k=0;k<nooptions;k++){
			document.form1.requestType.options.remove(1);
		}
		document.form1.requestType.options.length = 0;	
		document.form1.requestType.options.add(new Option('',''));
		for(i=0;i<fullReasons;i++)
			{
				if(reasonDetailsTable[i].filter.match(programCode) != null){
					document.form1.requestType.options.add(new Option(reasonDetailsTable[i].description,reasonDetailsTable[i].code));
				}
	  		}
    /*var editMode='edit';
	if(editMode!='true'){
		withdrawIOR();
	}
	if(editMode=='true'){
		var withdrawReason = 'wdrRsn.trim';//BRGUS00100312-upadhyayulag-CODE-MOD-Mar 03, 2008-Trim for space values in Converted Cases
		if(withdrawReason==""){
			withdrawIOR();
		}
	} */
    
	
}
function retainWithdrawSelection(){
var selectedWithdReasonCd='<%=wdrRsn%>';
	for(i=0;i<document.form1.requestType.options.length;i++){
	if(selectedWithdReasonCd!='' && selectedWithdReasonCd==document.form1.requestType.options[i].value){
			if(document.form1.requestType.options[i]!=null){
				document.form1.requestType.options[i].selected=true;
			}
		}
	}
}
//END OF BRGUS00133967
//#8-CODE-ADD-END
//#9-CODE-ADD-START
function enableDisableReactivationDateReason(){
	var editMode='<%=edit%>';
	var newProgStatus = document.form1.programStatus.value;
	if(editMode!='true'){
		//CH-955:Start:Ankit:Commented references to reactivation date since the field is removed from the screen
		//document.form1.monthreactivationDate.value = '';
		//document.form1.datereactivationDate.value = '';
		//document.form1.yearreactivationDate.value = '';
		//document.form1.monthreactivationDate.disabled = true;
		//document.form1.datereactivationDate.disabled = true;
		//document.form1.yearreactivationDate.disabled = true;
		document.form1.reactivationReason.value = '';
		//CH-955:End:Ankit:Commented references to reactivation date since the field is removed from the screen
		document.form1.reactivationReason.disabled = true;
		$("#reactivationReason").attr('style','background-color:#C0C0C0');
	}
	if(editMode=='true'){
		var progStatus="";
		progStatus = "<%=programStatusCd%>";
		if(progStatus=="DN" || progStatus=="TN"){
			if(newProgStatus=="DN" || newProgStatus=="TN"){
			//CH-955:Start:Ankit:Commented references to reactivation date since the field is removed from the screen
				//document.form1.monthreactivationDate.disabled = false;
				//document.form1.datereactivationDate.disabled = false;
				//document.form1.yearreactivationDate.disabled = false;
				//document.form1.monthreactivationDate.value = '';
				//document.form1.datereactivationDate.value = '';
				//document.form1.yearreactivationDate.value = '';
				//CH-955:End:Ankit:Commented references to reactivation date since the field is removed from the screen
				document.form1.reactivationReason.disabled = false;
				$("#reactivationReason").attr('style','background-color:#FFFFFF');
				document.form1.reactivationReason.value = '';
			}else{
			//CH-955:Start:Ankit:Commented references to reactivation date since the field is removed from the screen
				//document.form1.monthreactivationDate.value = '';
				//document.form1.datereactivationDate.value = '';
				//document.form1.yearreactivationDate.value = '';
				//document.form1.monthreactivationDate.disabled = true;
				//document.form1.datereactivationDate.disabled = true;
				//document.form1.yearreactivationDate.disabled = true;
				//CH-955:End:Ankit:Commented references to reactivation date since the field is removed from the screen
				document.form1.reactivationReason.value = '';
				document.form1.reactivationReason.disabled = true;
				$("#reactivationReason").attr('style','background-color:#C0C0C0');
			}
		}else{
		//CH-955:Start:Ankit:Commented references to reactivation date since the field is removed from the screen
			//document.form1.monthreactivationDate.value = '';
			//document.form1.datereactivationDate.value = '';
			//document.form1.yearreactivationDate.value = '';
			//document.form1.monthreactivationDate.disabled = true;
			//document.form1.datereactivationDate.disabled = true;
			//document.form1.yearreactivationDate.disabled = true;
			//CH-955:End:Ankit:Commented references to reactivation date since the field is removed from the screen
			document.form1.reactivationReason.value = '';
			document.form1.reactivationReason.disabled = true;
			$("#reactivationReason").attr('style','background-color:#C0C0C0');
		}
	}
}
//#9-CODE-ADD-END
//#11-CODE-ADD-START

function validateCaseAppReceivedDate(){
	//BEGIN-BRGUS00126398-upadhyayulag-Check if program status is diabled or not
	if(document.form1.monthapplicationDate.disabled==false 
		&& document.form1.dateapplicationDate.disabled==false
		&& document.form1.yearapplicationDate.disabled==false
		){
		//END-BRGUS00126398
		//CH-955:Start:Ankit:Program Type field is removed from the screen hence this condition is commented
//			if(document.form1.programType.value == 'P0') {
				setApplicationDate();
				var appMonth= document.form1.appRecDateMonth;
				var appDay= document.form1.appRecDateDay;
				var appYear= document.form1.appRecDateYear;
				var appReceivedDate = new Date(appYear.value,appMonth.value-1,appDay.value);
				var appDateMonth = document.form1.monthapplicationDate;
				var appDateDate = document.form1.dateapplicationDate;
				var appDateYear = document.form1.yearapplicationDate;
				//BRGUS00100312-upadhyayulag-CODE-MOD-Mar 04, 2008-If the date is not entered no need to throw the error
				//since already there is a validation to enter the complete data in the date field
				if(appDateMonth.value != "" && appDateMonth.value != "mm" && appDateMonth.value != "MM" &&
				   	appDateDate.value != "" && appDateDate.value != "dd" && appDateDate.value != "DD" &&
						appDateYear.value != "" && appDateYear.value != "yyyy" && appDateYear.value != "YYYY"){
					var appReqDate = new Date(appDateYear.value,appDateMonth.value-1,appDateDate.value);
					if ("<%=convertedCase%>" == "N") {
						if(appReqDate < appReceivedDate){
							var errArr=createErrArr(document.form1.monthapplicationDate,document.form1.dateapplicationDate,document.form1.yearapplicationDate);
							var errString1 = "Application Start Date";
							var errString2 = "Application Received Date";
							alertMe('DC3060',errArr,errString1,errString2);
							return false;
						}
					}
				}
				//END BRGUS00100312
//			}
	}
}

//#11-CODE-ADD-END
function updateProgStatusSelectedIndex(){
document.form1.progStatusSelectedIndex.value = document.form1.programStatus.selectedIndex;
}
function validateRSSWorker(){
	var retVal = true;
	<%
	if("Y".equals((String)request.getAttribute("IS_RSS_WORKER"))){
	%>	
	if(!document.form1.isReappOrReinstate.disabled){
		var oldIsReappOrReinstate = getDefaultValue(document.form1.isReappOrReinstate);
		var isReappOrReinstate = document.form1.isReappOrReinstate.value;
		if( oldIsReappOrReinstate !="RI" && isReappOrReinstate == "RI"){
			alertMe("DC4116",document.form1.isReappOrReinstate,"");
			retVal = false;
		}
	}
	 <%}%>
    return retVal;
}
//BRGUS00165706 -- padmanabhunip 
function validateFCTMAUser(mode){
if(mode==0)
return true;
var retVal = true;
	
	
	return retVal;
}
var isIOExists;
var isOtherProgsExists;
function initializeIOVars(){
	isIOExists = '<%=isIOExists%>';
	isOtherProgsExists = '<%=isOtherProgsExists%>';
}
function disablePrgmWithdrawClosureSection(){
	var progCd ='<%=progCd%>';
	if(progCd=="MA"){
		//document.form1.withdrawCloseSw.disabled = "true";
		//document.form1.monthwithdrawClosureDt.disabled = "true";
		//document.form1.datewithdrawClosureDt.disabled = "true";
		//document.form1.yearwithdrawClosureDt.disabled = "true";
	}
}
 function onChangeReinstate(){

var itt = document.getElementById('isReinstatement').value;
 if(itt=='N'||itt==null||itt==""||itt==" "){

document.form1.reactivationReason.disabled=true;
$("#reactivationReason").attr('style','background-color:#C0C0C0');
//$("#reactivationReason").attr('style',origStyleValues["reactivationReason"]);
document.form1.monthreinstatementEffectiveDate.disabled = true;
document.form1.datereinstatementEffectiveDate.disabled = true;
document.form1.yearreinstatementEffectiveDate.disabled = true;
document.getElementsByName("monthreinstatementEffectiveDate")[0].value="mm";
document.getElementsByName("datereinstatementEffectiveDate")[0].value = "dd";
document.getElementsByName("yearreinstatementEffectiveDate")[0].value = "yyyy";
document.form1.dimg_reinstatementEffectiveDate.disabled = true;
document.getElementsByName("reactivationReason")[0].selectedIndex = 0;
$('#reinstatementEffectiveDate').datepicker("disable");

}



else{

document.form1.reactivationReason.disabled=false;
$("#reactivationReason").attr('style','');
document.form1.monthreinstatementEffectiveDate.disabled = false;
document.form1.datereinstatementEffectiveDate.disabled = false;
document.form1.yearreinstatementEffectiveDate.disabled = false;
document.form1.dimg_reinstatementEffectiveDate.disabled = false;
$('#reinstatementEffectiveDate').datepicker("enable");

}
 enableReactivation(); 
}

function rein(){
	var st=document.getElementById('isReinstatement').value;
	var rr=document.getElementById('reactivationReason').value;
	var flag=true;
	if(st=='Y'){
	/* if(rr==" " ){
	alertMe('GL051',document.getElementById('reinstateReason'),'Reinstate Reason');
	return false;
	} */
	
	//if(document.getElementsByName("monthreinstateDate")[0].getAttribute("value")=='mm' && document.getElementsByName("datereinstateDate")[0].getAttribute("value")=='dd' && document.getElementsByName("yearreinstateDate")[0].getAttribute("value")=='yyyy'){
	if((document.getElementById('monthreinstatementEffectiveDate').value=='mm' && document.getElementById('datereinstatementEffectiveDate').value=='dd' && document.getElementById('yearreinstatementEffectiveDate').value=='yyyy') ||
			(document.getElementById('monthreinstatementEffectiveDate').value=='' && document.getElementById('datereinstatementEffectiveDate').value=='' && document.getElementById('yearreinstatementEffectiveDate').value=='')
			){
		/* alertMe('GL051',document.form1.monthreinstateDate);
		alertMe('GL051',document.form1.datereinstateDate);
		alertMe('GL051',document.form1.yearreinstateDate); */
		var errArr = createErrArr(document.getElementById('monthreinstatementEffectiveDate'), document.getElementById('datereinstatementEffectiveDate'), document.getElementById('yearreinstatementEffectiveDate'));
		alertMe('DC4811', errArr, document.getElementById('monthreinstatementEffectiveDate'), document.getElementById('isReinstatement'));
		flag=false;
	}
	
	
	if(document.getElementById('reactivationReason').value==' '){
		/* alertMe('GL051',document.form1.monthreinstateDate);
		alertMe('GL051',document.form1.datereinstateDate);
		alertMe('GL051',document.form1.yearreinstateDate); */
		var errArr = createErrArr(document.getElementById('reactivationReason'));
		alertMe('DC4811', errArr, document.getElementById('reactivationReason'), document.getElementById('isReinstatement'));
		flag=false;
	}

	}
	return flag;
}

function withdrawal(){
	
	var flag=true;
	var wp=document.getElementById('withdrawCloseSw').value;
	var wr=document.getElementById('requestType').value;
	if(wp=='Y'){
		if(document.form1.monthwithdrawClosureDt.value=='MM' && document.form1.datewithdrawClosureDt.value=='DD' && document.form1.yearwithdrawClosureDt.value=='YYYY'){
			/* alertMe('GL051',document.form1.monthreinstateDate);
			alertMe('GL051',document.form1.datereinstateDate);
			alertMe('GL051',document.form1.yearreinstateDate); */
			var errArr = createErrArr(document.getElementById('monthwithdrawClosureDt'),document.getElementById('datewithdrawClosureDt'),document.getElementById('yearwithdrawClosureDt'));
			alertMe('DC4811', errArr, document.getElementById('monthwithdrawClosureDt'), document.getElementById('withdrawCloseSw'));
			flag=false;
		}
		if(wr==''||wr==""||wr==' '||wr==" ")
		{
			var errArr = createErrArr(document.getElementById('requestType'));
			alertMe('DC4811',errArr,document.getElementById('requestType'),document.getElementById('withdrawCloseSw'));
			flag=false;
		}
	}
	return flag;
}


/* function enableWaitlist(){
var =document.getElementsByName("caseStatus")[0].value;
alert(kk);} */

function enableReactivation(){
	var caseAct='<%=request.getAttribute("dbCaseAction")!=null?(String)request.getAttribute("dbCaseAction"):""%>';
	if("<%=enableRevToOpen%>" == "true" || (form1.programStatus.value == 'DN' && (caseAct=="RC" || caseAct=="PI"))){
		document.form1.reactivationReason.disabled=false;
		$("#reactivationReason").attr('style','background-color:#FFFFFF');
	}
}
 
function fourMonthsInPastCheck(mode) {
	// Changes for Read only mode
	if(mode==0){
		return true;
	}
		
	var isSupr = '<%=isSupervisor%>';
		<%-- var modeARDC = '<%=ARDCMODE%>';
	if(modeARDC == 'DC'){
		return true;
	} --%>
	var field1 = document.form1.yearapplicationDate;
	var field2 = document.form1.monthapplicationDate;
	var field3 = document.form1.dateapplicationDate;
	
	var errArr = createErrArr(field2,field3,field1);
	
	var date2 = new Date(document.form1.yearapplicationDate.value,document.form1.monthapplicationDate.value-1,document.form1.dateapplicationDate.value);
	var date1 = new Date(document.form1.appRecYear.value,document.form1.appRecMonth.value - 1,document.form1.appRecDay.value);
	date1.setMonth(date1.getMonth() - 4);
	
	/* var sysDate = getToday();//Gets date from the server
   if(sysDate!= null && sysDate != ""){
        today = sysDate;
   	}
   	else {
   	    today = new Date(Calendar.getYear(), Calendar.getMonth(), Calendar.getDate());//Client machine date
   	}
	
	var sixteenWeeksTime = 16*7*24*60*60*1000; */
	var programCode = '<%=progCd%>';
	if(date2 < date1 && (isSupr == 'false') && programCode!='CD') {
		alertMe('DC4215', errArr, '<%=applicationDate.LABEL%>');
		return false;
	}
	
   	return true;
}

</script>
	  	   <!-- 
			 	<td >
<TL:FWLabelTag bean="<%=isReappOrReinstate%>"/>
</td>
				<td ><TL:FWSelectTag bean = "<%=isReappOrReinstate%>"   size="20" emptySpace="Y" tabindex="15"  display="<%=reAppDisplay%>" value="<%=reAppValues%>"  multiple="N" other="onchange=\"onChangeRulesForRR();clearSOPFieldsForRR();\"" /> 	  	  
				 
	  	  		<input type="hidden" name="validateRR" validate="validateRRReact();validateRSSWorker();" />-->
	  	  </tr>
	  	  
	  	  </table> 
 <table title="Programs" summary="Programs" class="bodyFormLabels">	  	
  <tr> 	  	    	  
<td><TL:FWLabelTag bean="<%=isReinstatement%>" question="Y" elementId="11" />
                </td>
                <td><TL:FWRTSelectTag referenceTableName="YESNO" bean="<%=isReinstatement%>" multiple="N" emptySpace="Y" tabindex="1" selected="<%=isrein%>" size="5"  other="onchange=\"onChangeReinstate();\"" /> 
                </td>
                </tr>
                </table>
	<table title="Programs" summary="Programs" class="bodyFormLabels">
	  	  <tr> 
      <td ><TL:FWDivTag bean="<%=statusDate%>" elementId="12" /> 
      </td>
      <td title="Status Date"> <%=appdt%> 
      </td> 
      <%-- <td value=<%=appRcvdDate %>></td> --%>
      </tr>
    

      <%//CR20626 - DA - BRGUS00126533 - arakalas %>
     <!--CH-955:Start:Ankit:the field medicaid type from the screen -->
     <!-- 
  
      </tr>
<tr>
<td >
<TL:FWLabelTag bean="<%=extendSOP%>"/>
</td>
      		
      <td ><TL:FWRTSelectTag referenceTableName="YESNO" size="5" bean="<%=extendSOP%>"  emptySpace ="Y" selected="<%=extendSOPSw%>" tabindex="15" multiple="N" other="onChange=\"enableComplianceDt();\" validate=\"validateComplianceDate();\" "/> 
      </td> 
  		-->
     
     
     </table>

 <!--      <table title="Programs" summary="Programs" class="bodyFormLabels">
     <tr> -->
     <!--CH-955:Start:Ankit:the field medicaid type from the screen -->
     <!-- 
     <td >
	  	  <TL:FWLabelTag bean="<%=medicaidTypeCd%>"/>
	  	  </td>
	  	  <%--BRGUS00074098  --%>
	  	  <td >
	  	  <TL:FWRTSelectTag referenceTableName="MEDICAIDTYPE" bean="<%=medicaidTypeCd%>" selected="<%=medTypeCd %>" emptySpace="Y"  tabindex="16"  size="33" /> 
	  	  </td>
	  	   -->
	  	   <!--CH-955:End:Ankit:the field medicaid type from the screen -->
	 <!-- 	  </tr>

		<tr>-->
	  	   <%//CR20626 - DA - BRGUS00126533 - arakalas%>
	  	   <!-- 
	      <td >
<TL:FWLabelTag bean="<%=extensionActionDt%>"/>
</td>
	      		
	      <td id="extActionDtDisp" > <%=(extensionActionDate != null)?(new java.text.SimpleDateFormat("MM/dd/yyyy")).format(extensionActionDate):"" %> 
	      <input type="hidden" name="extensionActionDt1"	value="<%=(extensionActionDate != null)?(new java.text.SimpleDateFormat("MM/dd/yyyy")).format(extensionActionDate):"" %>" />
	  	  <span style=display:none> <TL:FWGroupCustomDateTag  
	    			beanId     = "extensionActionDt"  
	    			pageMode   = "1"
	    			disableLabel = "Y" 
					dateValue  = "<%=dateOfSOPExt%>" 					
	  				/> </span>
	  	   </td>
	  	    
		   </tr>
<tr>-->

<!--	  <td ><TL:FWLabelTag bean="<%=conversionDate%>"  /> 
      </td>
      </tr>
<tr>
<td > <%=(conversionDt != null)?(new java.text.SimpleDateFormat("MM/dd/yyyy")).format(conversionDt):"" %> 
      </td> 
     	
     	</tr>
     	</table>-->

  <!--    	 <table title="Programs" summary="Programs" class="bodyFormLabels">
     	<tr> -->
     	<%
	  	  String dhs3043Sw1="";
	  	  
//	  	  if(dcCaseProgramCargo!=null && dcCaseProgramCargo.getDhs3043Sw()!=0)
//	  	  {
//	  	  	dhs3043Sw1 = String.valueOf(dcCaseProgramCargo.getDhs3043Sw());
//	  	  }
	  	   %>
	  	   
	  	  <!-- CH-955"Start:Ankit:Removed field dhs3043Sw from the screen
	  	  <td >
	  	  <TL:FWLabelTag bean="<%=dhs3043Sw%>"/>
	  	  </td> 
	  	  <td >
	  	  <TL:FWRTSelectTag referenceTableName="YESNO" bean="<%=dhs3043Sw%>" selected="<%=dhs3043Sw1 %>" emptySpace="Y"  tabindex="17"  size="3" /> 
	  	  </td>
	  	  
	  	  CH-955:End:Ankit:Removed field dhs3043Sw from the screen
	  	  --> 
		  
		  <%//CR20626 - DA - BRGUS00126533 - arakalas%>
		  <!-- CH-955:Start:Ankit:Removed Date of Compliance from the screen
		  </tr>
<tr>
<td > <TL:FWLabelTag bean="<%=dateOfCompliance%>" /> 
     	  </td>
	 
	      <td ><TL:FWGroupCustomDateTag  
	    			beanId     = "dateOfCompliance"  
	    			pageMode   = "1"
	    			tabIndex   = "18"
	    			disableLabel = "Y" 
					dateValue  = "<%=dateOfComp%>" 					
	  				/> </td>	
	
		  <% //system.out.println("checking null pointer exception 7");
		  if(nonEditPriorDt == 1) { //system.out.println("checking null pointer exception 7.1");
		  %>
</tr>
	  	  </table>-->	

      <table title="Programs" summary="Programs" class="bodyFormLabels">
		  <tr style="display: none;">
			
      <td >
<TL:FWLabelTag bean="<%=priorMedicaidDates%>"/>
</td>
      		
      <td ><TL:FWSelectTag bean = "<%=priorMedicaidDates%>"   size="15" id="select1" tabindex="1"    display="<%=medicDatesVals%>" selected = "<%= priorDate %>" value="<%=medicDateCds%>"    multiple="N" show = "1"  other="<%=onChangePmDtJS.toString() %>" /> 
      </td>
	  </tr>
<tr style="display: none;">
<td >
<TL:FWLabelTag bean="<%=priorMedicaidOtherDate%>" />
</td>
	        
      <td ><TL:FWGroupCustomDateTag  
    			beanId     = "priorMedicaidOtherDate"  
    			pageMode   = "1" 
				isRequired = "Y"
				pastDate = "Y"
    			tabIndex   = "1"
    			disableLabel = "Y" 
				dateValue = "<%= pmOverrideDate %>"
				other="<%=onChangeMedDtJS%>"
				/> </td>
	  	  </tr>  
		  <% } %>
		  
          <!--<tr > 
		   
      <td >

</td>
				
      <td >

</td>
				
      </tr>
<tr>
<td >

</td>
				
			</tr>
		  -->
		  </table>

<hr class="noShade">
<table>
          <caption>
            <H2 class="Black_Heading_2"> 
            Revert to Open Program - Complete This Section When Reverting Closed Programs to Open  
            </H2>
          </caption>
</table>
	
		<table title="Programs" summary="Programs" class="bodyFormLabels">
	<tr>
		<td height="26" width="150"><%-- <TL:FWLabelTag
			bean="<%=reinstatementEffectiveDate%>" required="N" elementId="13" /> --%>
		
	<TL:FWGroupCustomDateTag
			beanId="reinstatementEffectiveDate" isRequired="N" pageMode="1"
			 mode="<%=  receMode %>"
			tabIndex="1" dateValue="<%=reinDate%>"		  
			isNextMonthLast="Y" futureDate="N"/></td>
		
	</tr>
       
    <%-- <tr>   
      <td><TL:FWLabelTag bean="<%=reinstateReason%>" elementId="14" />
                </td>
                <td><TL:FWRTSelectTag elementId="14" referenceTableName="REACTIVATIONREASON" bean="<%=reinstateReason%>"  multiple="N" emptySpace="Y" tabindex="" selected="<%=reinrsn%>" size="5" />
                </td>
</tr> --%>

  
  <tr>      		
 <td>     		      		
<TL:FWLabelTag elementId="7" bean="<%=reactivationReason%>"  />
</td>
 	
      <td ><TL:FWRTSelectTag elementId="7" referenceTableName="DCREACTIVATIONREASONCD" mode = "<%= recMode %>" size="5" bean="<%=reactivationReason%>" emptySpace="Y" selected="<%=reactRsn%>" tabindex="1" filter="YES" filterColumn="ACTIVESW" filterValue="Y" multiple="N"  other="<%= recReasonChk.toString() %>"/> 
      </td>
      </tr>
    
		  
	<!-- ND-33996 : Field is removed -->	    
      <!-- </tr>
<tr>
<td >
<TL:FWLabelTag elementId="9" bean="<%=reactivationDate%>" colon="N"/>
</td>
-->
</tr>
<tr>
	         
      <td ><div id="testYES" style="visibility: hidden"> <TL:FWGroupCustomDateTag  elementId="9" 	beanId     = "reactivationDate"  	pageMode   = "1" tabIndex   = "21"  disableLabel = "Y"  mode = "<%= recMode %>" 	dateValue  = "<%=reactDate%>"      /> 
      </div></td>
      		
      </tr>
			</table>

<hr class="noShade">
<table>
          <caption>
            <H2 class="Black_Heading_2"> 
            <!-- ND-33996 : Changes made in section header -->
            Client Request - Program Withdrawal/Closure 
            </H2>
          </caption>
</table>
        <%
        // BRGUS00133648 - Disable withdrawal fileds for approved SER and DSS
        if(editFlag)
        {
        if(programStatusCd.equalsIgnoreCase("AP")){
        if(progCd.equalsIgnoreCase("SE")
        	|| progCd.equalsIgnoreCase("DS")){
        	eleMode = 0;
        	}
        	}
        	if(progCd.equalsIgnoreCase("MA")){
        	eleMode = 0;
        	}
			}
         %>
		  <table title="Program Withdraw/Closure" summary="Program Withdraw/Closure" class="bodyFormLabels">
		  <tr>
      <td >
<TL:FWLabelTag  	bean="<%=withdrawCloseSw%>" />
</td>
      		
      <td ><TL:FWRTSelectTag  referenceTableName="YESNO" size="3" bean="<%=withdrawCloseSw%>" emptySpace="Y" selected="<%=defWithDrawSw%>" tabindex="1" multiple="N"  other="<%= onChangeWithdrawJS %>"/> 
      
      </td>
	 
	  <%--//#7-CODE-COMMENT-START--UNCOMMENT-01/31/2008--%>
	  </tr>
	
	   
<tr>
<td >
<TL:FWLabelTag bean="<%=withdrawClosureDt%>"/>
</td>
      <%--//CH-12957 - amirajkar - Changed the pastDate to "N" to remove validation which is not mentioned in storyboard--%>		
      <td > <TL:FWGroupCustomDateTag   beanId= "withdrawClosureDt"  pastDate="N" 	pageMode   = "1" tabIndex   = "1"	disableLabel = "Y" 	
      dateValue  = "<%=withdrawDate%>"  other="validate=\"isDateComplete(document.form1.monthwithdrawClosureDt,document.form1.datewithdrawClosureDt,document.form1.yearwithdrawClosureDt)\""   /> 
      </td>
      </tr>
    
    


      <tr>
		  <td>
<TL:FWLabelTag bean="<%=requestType%>" elementId="15" />
</td>
<!-- changes made for 40656 -->
	      <%if (progCd.equals("TF")){ %>
	       <td ><TL:FWRTSelectTag  referenceTableName="DCWITHDRAWREASONCD"  mode = "<%=eleMode%>" size="33" bean="<%=requestType%>" emptySpace="Y" selected="<%=wdrRsn%>" tabindex="1"  /> </td> 
	      <%}else{ %>	
	      	<td>
	      	<TL:FWRTSelectTag bean="<%=requestType%>" referenceTableName ="DCWITHDRAWREASONCD" emptySpace="Y"  size="33" selected="<%=wdrRsn%>" tabindex="1"  filter="YES" filterColumn="ACTIVESW" filterValue="Y"/>
	      <% }%>

      </td>  
	  	  </tr> 
	  	
	  	    <tr>
		<td height="26" width="122"><TL:FWLabelTag
			bean="<%=reqInfDetails%>" question="N" /></td>
		<td height="26" width="601"><TL:FWTextTag elementId="16"
			bean="<%=reqInfDetails%>" tabindex="" maxlength="100"
			 style="form350" value="<%=reqdetails%>"
			other="validate=\"isCompleteWithdrawAddlInfo(document.form1);\"" />
		</td>
	</tr>	  
	  	 
	  	  
	 
	  	 </table> 
	  	  
	  	 
	  	  <!-- Added for BRGUS00047832 - CR 20857  -->
  	  <tr>
	<!--CH-955:Start:Ankit:Removed additional info field from the screen
       <td >
<TL:FWLabelTag bean="<%=withdrawAdditionalInfo%>" question = "N" />
</td>  -->
	   <%--BEGIN-BRGUS00106497-upadhyayulag-Mandate the Additional Information text depending on the withdraw reason--%>
       <!-- 
       <td ><TL:FWTextTag bean="<%=withdrawAdditionalInfo%>" tabindex="31" maxlength="100" style="form570"  value="<%=withdrawlAddInfo%>" other="validate=\"isCompleteWithdrawAddlInfo(document.form1);\"" />
        -->	
        <!--CH-955:End:Ankit:Removed additional info field from the screen
       <%--END-BRGUS00106497--%>
       </td>
	 </tr>
	  	  
		  </table>

	
		  <br>
		    <!-- table width="723" border="0" cellspacing="0" cellpadding="0" class="bodyDividerTable">
          <caption> <h2 class="bodyDividerTableHeader">MIChild</caption>
          </caption>
        </table>  
		
		<table width="723" title="MiChild" summary="MiChild" border="0" class="bodyFormLabels"> 
          <tr-- > 
		    
      <!-- td height="26" width="127"><TL:FWLabelTag bean="<%=isReferredByChip%>" colon="N"/></td-->
	        
      <!-- /td-->
      		
      <!-- td height="26" width="122"><TL:FWLabelTag bean="<%=chipApplication%>"/></td>
      		
			</tr>
			</table-->
			<%-- 
			 <table width="723" border="0" cellspacing="0" cellpadding="0" class="bodyDividerTable">
          <tr class="bodyDividerTableHeader"> 
            <td height="17">One Time Grandparent Subsidy</td>
          </tr>
        </table>  
		<table class="bodyFormLabels">
			 <tr> 
			 
      <td >
<TL:FWLabelTag bean="<%=grandParentSubsidySw%>" />
</td>
		
      <td > <TL:FWRTSelectTag  referenceTableName="YESNO" bean="<%=grandParentSubsidySw%>"  emptySpace="Y" selected="<%=gsFSAssistance%>"  tabindex="30"  size="5"  other="<%=gsFSjs%>"  /> 
      </td>
	   
 	
			</tr> 
				
        </table>

--%>
<%/* EDITABLE - Comments (You can add code to this section) */%>	
<%@ include file = "/includes/common/Comments.jsp" %> 
<%
  appCaseMode = "0";
  if(((String)request.getAttribute(DcConstants.CASE_MODE)).equals(DcConstants.MODE_INTAKE) || ((String)request.getAttribute(DcConstants.CASE_MODE)).equals(DcConstants.MODE_COMPLETEACTION)) {
   		appCaseMode = "1";
	} 
//System.out.println("appCaseMode..............."+appCaseMode);
		
	//system.out.println("checking null pinter exception 9" );
 %>
<DCTagLibrary:DcButtonTag  buttonType     = "VCR_RCPN" 
	vcrPrevScript="<%=vcrPrevScript%>"
 	 vcrNextScript="<%=vcrNextScript%>"
    resetScript="<%=resetScript%>"
     addArrowScript="<%=addArrowScript%>"
     prevScript    ="<%=prevScript%>"
      nextScript    ="<%=nextScript%>"
	  cancelScript = "<%=cancelScript%>"
     histNavInd="<%=histNav%>"
    appCaseMode="<%= appCaseMode %>"
    /> 
         <div id="testYES" style="visibility: hidden">      
        
        <%
        String selectedProgramType ="";
        if(dcCaseProgramCargo.getPriorMedicaidCd() != null) {
        	selectedProgramType = dcCaseProgramCargo.getPriorMedicaidCd();  
        }
        
        %> 
         
      <TL:FWRTSelectTag referenceTableName="PROGRAMTYPE" bean="<%=programType%>" selected="<%=selectedProgramType%>"  emptySpace="Y"    size="17" /> 
      
      </div>
    
 <%/* End of Print Functionality */ %> 
 <input type="hidden" name="fourMonthsInPastCheck" id="fourMonthsInPastCheck" data-validate="fourMonthsInPastCheck('<%=og%>');">
<input type="hidden" name="validateWithdrawDate" data-validate = "withdrawal()">	
 <input type = "hidden" name = "validateFCTMAUser" data-validate = "validateFCTMAUser('<%=og%>')">
 <input type = "hidden" name = "validateProgForIO" data-validate = "validateProgForIO(isIOExists,isOtherProgsExists,'<%=og%>','<%=edit%>')">
</Div>                 
  	 <%--JosephI1-- BRGUS00144257-- Last Update User changes --%> 
   <TL:FWUpdateUserTag cargo="<%=dcCaseProgramCargo%>" />    
   	<%--END OF JosephI1--BRGUS00144257-- Last Update User changes --%>    	
<%/* NOT EDITABLE - End Outer HTML Table */%>	            
<%@ include file="/fwincludes/endTable_NEW.jsp"%>