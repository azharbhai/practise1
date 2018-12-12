var strFieldNames = new Array('Dyna Id', 'In-Household Joint Owner Name', 'Outside-Household Joint Owner Name', 'Joint Ownership Start / Change Date', 'Joint Ownership End Date', 'Joint Owner Percentage Share of Income (%)', '');
var strFieldValues = new Array('newdynaid', 'jointOwnerName', 'nameOrOrganization', 'jOCircumstancesStartChangeDate', 'jOEndDate', 'jOBusinessOwnershipPercentageShare','detailedSequenceNum', 'dynaSeqNum');
 
selectedDynaListNum=0
$(function() { 
	var lastsel;
	var dynawidth = $('.Heading_1').width();
$("#grid0").jqGrid({
 datatype: "jsonstring",
 datastr:GetJSON1(),

colNames:[strFieldNames[0], strFieldNames[1], strFieldNames[2], strFieldNames[3], strFieldNames[4], strFieldNames[5], strFieldNames[6]],
colMandReq:['-1', '-1', '-1','41','-1','42'],

colModel:[ 
	{name:'newdynaid', index:'newdynaid', width: 50, editable:true, editoptions:{readonly:false, size:5}, hidden:true,sortable:false},

	{name:strFieldValues[1], index:strFieldValues[1], editable:true, readonly:false, width:100,
		edittype:"select",
		editoptions:{value:getIndvDropDown ,dataEvents: [{type: 'change',fn: function(e) {populateJointOwnerNameDetails(this.value);}}]},  hidden:false, sortable:true,
		stype: 'select', searchoptions : {edittype:"select", formatter:'select',sopt: ['cn','eq'], value:getIndvDropDown  }}, 

	{name:strFieldValues[2], index:strFieldValues[2], editable:true, readonly:false, width:100,
		editoptions:{readonly:false, size:30,
			dataEvents:[{type:'blur', fn: function(e){setElementValues(this);}},{type:'focus', fn: function(e){setValuesOnFocus(this);}}]},
			hidden:false,sortable:true}, 



			{name:strFieldValues[3], index:strFieldValues[3], editable:true, readonly:false, width:80, formatter:'date', formatoptions: {srcformat:'m-d-Y', newformat:'m/d/Y'},
				editoptions:{required : true,
					dataEvents:[{type: 'blur', fn: function(e){checkDateValues(this);}},{type:'keypress', fn: function(e){checkKeyUp(this);}} ],
					dataInit: function(el) {setTimeout(function() { $(el).datepicker({dateFormat:"mm/dd/yy"    }); }, 200); } },
				searchoptions : {
					dataEvents:[{type: 'blur', fn: function(e){checkDateValues(this);}},{type:'keypress', fn: function(e){checkKeyUpInSearch(this);}} ],
					dataInit : function(elem){ setTimeout(function(){ $(elem).datepicker({dateFormat:"mm/dd/yy"});}, 100);}}}, 
			
 



					{name:strFieldValues[4], index:strFieldValues[4], editable:true, readonly:false, width:80, formatter:'date', formatoptions: {srcformat:'m-d-Y', newformat:'m/d/Y'},
						editoptions:{required : true,
							dataEvents:[{type: 'blur', fn: function(e){checkDateValues(this);}},{type:'keypress', fn: function(e){checkKeyUp(this);}} ],
							dataInit: function(el) {setTimeout(function() { $(el).datepicker({dateFormat:"mm/dd/yy"    }); }, 200); } },
						searchoptions : {
							dataEvents:[{type: 'blur', fn: function(e){checkDateValues(this);}},{type:'keypress', fn: function(e){checkKeyUpInSearch(this);}} ],
							dataInit : function(elem){ setTimeout(function(){ $(elem).datepicker({dateFormat:"mm/dd/yy"});}, 100);}}}, 

	{name:strFieldValues[5], index:strFieldValues[5], editable:true, readonly:false, width:100,
		editoptions:{readonly:false, size:3,
			dataEvents:[{type:'blur', fn: function(e){setElementValues(this);}},{type:'focus', fn: function(e){setValuesOnFocus(this);}}]},
			hidden:false,sortable:true}, 

	{name:'delImg', index:'delImg', width: 50, editable:false, editoptions:{value: setDelImage}, hidden:false, sortable:false, search: false,  formatter: setDelImage},
],

	
	postData: {},
	rowNum: 10,
	height: "100%",
	width: dynawidth,
	shrinkToFit: true,
	autowidth: false,
	rownumbers: false,
	pager: '#pager0',
	sortname: 'id',
	
viewrecords: true,
	sortorder: "asc",
	emptyrecords: "Empty records",
	loadonce: true,
	sortable: true,
	
rowList: [6,10,20,40,60,80,100],

	loadComplete: function() {
		 var grid0 = jQuery("#grid0");
		 var allDropDownElements = getAllDropDownElements0(); 
		var allDropDownElementRefTables = getAllDropDownElementRefTables0();
		 processLoadComplete(grid0, allDropDownElements, allDropDownElementRefTables, 0);
		 populateJointOwnerName();
		 if($("#grid0").jqGrid('getGridParam', 'selrow') == null){
			 $("#jqgridCustomErrMsg0")[0].innerHTML='';
		 }
		 
		
	},

	jsonReader:{root:"rows", page: "page", total: "total", records: "records", repeatitems: false, cell: "", id: "id"},

	onSelectRow: function(id) {

		 var grid0 = $("#grid0");
		 var rowId = grid0.jqGrid('getGridParam','selrow');
		 processSelectRowEvent(id, 0);
		 if("new_row"==rowId){ 
			if(document.getElementById(rowId+"_jointOwnerName").value==null || document.getElementById(rowId+"_jointOwnerName").value==''){
				document.getElementById(rowId+"_jointOwnerName").value='';
			}
			 //document.getElementById(rowId+"_jOCircumstancesStartChangeDate").value = document.form1.effectiveBeginDate.value;
		 } 
		if(document.getElementById(rowId+"_nameOrOrganization")!=null){
			 if(document.getElementById(rowId+"_nameOrOrganization").value!=''){
				 document.getElementById(rowId+"_jointOwnerName").value='';
			 	}
		}
		
		
		},

	onCellSelect: function(rowid) {
		 },

	onPaging: function (pgButton) {
		 var grid0 = $("#grid0");
		 processPaging(grid0, pgButton, 0);
		},

	onSortCol:function(id) {
		resetMainGrid(0);
		},

	gridComplete:function() {
		 var grid0 = $("#grid0");
		 var pager0Center = $("#pager0_center");
		 processGridComplete(grid0, pager0Center, 0);
		 },

	caption:"Joint Ownership Information"
	});


	$("#grid0").jqGrid('navGrid','#pager0', {edit:false, add:false, del:false, search:false, refresh: false},{}, {}, {}, {}	);
	
	/*$("#grid0").navButtonAdd('#pager0', {
		caption:"Search",
		id: "rowSearch",
		buttonicon: "ui-icon-search",
		onClickButton: function(){
		    openSearchModal(0);
		},
		position: "center",
		title:"Search",
		cursor: "pointer"
	 } );*/

	
	$("#grid0").navButtonAdd('#pager0', {
		caption:"Add",
		id: "rowAdd",
		buttonicon: "ui-icon-plus",
		onClickButton:function(){
		    createNewRow('', true, 0);
		   // document.getElementById("new_row_jOCircumstancesStartChangeDate").value = document.form1.effBeginDt.value;

		    $("#new_row_jointOwnerName").attr('title', 'In-Household Joint Owner Name');
		    $("#new_row_nameOrOrganization").attr('title', 'Outside-Household Joint Owner Name');
		    $("#new_row_jOCircumstancesStartChangeDate").attr('title', 'Joint Ownership Start/Change Date');
			$("#new_row_jOEndDate").attr('title', 'Joint Ownership End Date');
			$("#new_row_jOBusinessOwnershipPercentageShare").attr('title', 'Joint Owner Percentage Share of Income (%)');  
			//$("#new_row_jOBusinessIncomePercentageShare").attr('title', 'Joint Owner Percentage Share of Business Income');  
		},
		position: "center",
		title:"Add Record",
		cursor: "pointer"
	 } );

	
	/*$("#grid0").navButtonAdd('#pager0', {
		caption:"Reset",
		id:"rowRefresh",
		buttonicon: "ui-icon-refresh",
		onClickButton: function(){
		    resetGridRecords(0);
		 },
		position: "center",
		title:"Reset Search",
		cursor: "pointer"
	 } );*/


});

function getIndvDropDown(){
	return clientIndvHiddenFieldData;
}
var jointOwnernameArray = new Array();
jointOwnernameArray[''] = '';

var clientIndvHiddenFieldData = ':';
var totalSize = clientIndvLst.length;
var clientIndvValue;
var clientIndvText;
for (var index = 0; index < totalSize; index++) {
      clientIndvValue = clientIndvLst[index];
      clientIndvText = clientNameLst[index];
      jointOwnernameArray[clientIndvValue] = clientIndvText;
      
      clientIndvHiddenFieldData = clientIndvHiddenFieldData + ";"
                   + clientIndvValue + ":" + clientIndvText;
}




function populateJointOwnerNameDetails(newValue){
    var grid = $("#grid0");
    var rowId = grid.jqGrid('getGridParam','selrow');
    //var houseHoldDetails = jointOwnernameDetailsArray[newValue].split("-");
}

function populateJointOwnerName(){
	var grid = $("#grid0");
    var numberOfRecords = grid.getGridParam("records");
	for(var rowId=1;rowId<=numberOfRecords;rowId++){
		var rowData = grid.getRowData(rowId);
		grid.jqGrid('setCell', rowId, 'jointOwnerName', jointOwnernameArray[rowData['jointOwnerName']]);
		//var houseHoldDetails = jointOwnernameDetailsArray[rowData['indvId']].split("-");
	}
}

function isCompleteDynaJo(rowId){
	//isValidateON=true;
	displayMessages(" "," ");
	//copyErrorFields();
	//errorFields.length=0;
	//validationErrors = new Array();
	
	//Validate that either 'Joint Owner Name' or 'Name / Organization' is entered when saving the joint ownership
 	var nameOrOrgVal = jQuery('#' + rowId + '_' + 'nameOrOrganization');
 	var jointOwnerVal = jQuery('#' + rowId + '_' + 'jointOwnerName');
 	var errArr = createErrArr('jointOwnerName','nameOrOrganization');
 	if('' != jointOwnerVal.val() && '' != nameOrOrgVal.val()){
 				dynaAlertMe("GL061", errArr,'In-Household Joint Owner Name','Outside-Household Joint Owner Name');
 				  highlightDynaField(rowId,'jointOwnerName');
 				  highlightDynaField(rowId,'nameOrOrganization');
 				return false;			
	}else if( '' == jointOwnerVal.val() && '' == nameOrOrgVal.val()){
	 			dynaAlertMe("GL061", errArr,'In-Household Joint Owner Name','Outside-Household Joint Owner Name');
	 			 highlightDynaField(rowId,'jointOwnerName');
				  highlightDynaField(rowId,'nameOrOrganization');
	 			return false;	 
 	}
 	//Validate that 'Joint Ownership Circumstances Start / Change Date' is entered
 	var startDate = jQuery('#' + rowId + '_' + 'jOCircumstancesStartChangeDate');		
	if(startDate.val() ==''){
				dynaAlertMe("GL003", 'jOCircumstancesStartChangeDate','Joint Ownership Start / Change Date');
				 highlightDynaField(rowId,'jOCircumstancesStartChangeDate');
				return false;
	}
	
 	//Check whether 'Joint Owner Percentage Share of Business Ownership' && 'Joint Owner Percentage Share of Business Income' are entered
 	var joBusinessOwnershipPercentShare = jQuery('#' + rowId + '_' + 'jOBusinessOwnershipPercentageShare');
 	//var joBusinessIncomePercentShare = jQuery('#' + rowId + '_' + 'jOBusinessIncomePercentageShare');
 	//var hsWeekPersonParticipates = jQuery('#' + rowId + '_' + 'hrsWeekPersonParticipates');
 	if (joBusinessOwnershipPercentShare.val()=='') {  
 			dynaAlertMe("GL003",'jOBusinessOwnershipPercentageShare','Joint Owner Percentage Share of Business Ownership');
 			 highlightDynaField(rowId,'jOBusinessOwnershipPercentageShare');
			return false;
	}
	/*if (joBusinessIncomePercentShare.val()=='') {  
			dynaAlertMe("GL003",'jOBusinessIncomePercentageShare','Joint Owner Percentage Share of Business Income');
			 highlightDynaField(rowId,'jOBusinessIncomePercentageShare');
			return false;
	}*/
	/*if (hsWeekPersonParticipates.val()=='') {  
		dynaAlertMe("GL003",'hsWeekPersonParticipates','Hours per Week Person Actively Participates');
		 highlightDynaField(rowId,'hrsWeekPersonParticipates');
		return false;
}*/
	   
 	//Check whether 'Joint Owner Percentage Share of Business Ownership' && 'Joint Owner Percentage Share of Business Income' are numeric
 	
 	/*if (!isInteger(joBusinessOwnershipPercentShare.val()) && !isInteger(joBusinessIncomePercentShare.val())){
 		errArr = createErrArr('jOBusinessOwnershipPercentageShare','jOBusinessIncomePercentageShare');
 		dynaAlertMe("DC4221",errArr,'Joint Owner Percentage Share of Business Ownership and Joint Owner Percentage Share of Business Income ');
 		highlightDynaField(rowId,'jOBusinessOwnershipPercentageShare');
 		highlightDynaField(rowId,'jOBusinessIncomePercentageShare');
		return false;
	}*/
 	 if (!isInteger(joBusinessOwnershipPercentShare.val())){
 		dynaAlertMe("GL001",'jOBusinessOwnershipPercentageShare','Joint Owner Percentage Share of Income (%)');
 		highlightDynaField(rowId,'jOBusinessOwnershipPercentageShare');
		return false;
	}
 	/*else if (!isInteger(joBusinessIncomePercentShare.val())){
		dynaAlertMe("DC4221",'jOBusinessIncomePercentageShare','Joint Owner Percentage Share of Business Income');
		highlightDynaField(rowId,'jOBusinessIncomePercentageShare');
		return false;
	}*/
 	/*else if (!isInteger(hsWeekPersonParticipates.val())){
		dynaAlertMe("DC4221",'hsWeekPersonParticipates','Hours per Week Person Actively Participates');
		highlightDynaField(rowId,'hrsWeekPersonParticipates');
		return false;
	}*/
	
	//Check whether 'Joint Owner Percentage Share of Business Ownership' && 'Joint Owner Percentage Share of Business Income' 
	//lie in range of 1-100
 	
 	/*if((joBusinessOwnershipPercentShare.val() > 100 || joBusinessOwnershipPercentShare.val() <=0) && (joBusinessIncomePercentShare.val() > 100 || joBusinessIncomePercentShare.val() <=0)){
 		errArr = createErrArr('jOBusinessOwnershipPercentageShare','jOBusinessIncomePercentageShare');
 		dynaAlertMe("DC4305",errArr,'Joint Owner Percentage Share of Business Ownership and Joint Owner Percentage Share of Business Income');
		highlightDynaField(rowId,'jOBusinessOwnershipPercentageShare');
		highlightDynaField(rowId,'jOBusinessIncomePercentageShare');
		 return false;
	}*/
 	 if(joBusinessOwnershipPercentShare.val() > 100.00 || joBusinessOwnershipPercentShare.val() <=0.00){
		dynaAlertMe("DC4305",'jOBusinessOwnershipPercentageShare','Joint Owner Percentage Share of Business Ownership');
		highlightDynaField(rowId,'jOBusinessOwnershipPercentageShare');
		 return false;
	}
 	/*else if(joBusinessIncomePercentShare.val() > 100 || joBusinessIncomePercentShare.val() <=0){
		dynaAlertMe("DC4305",'jOBusinessIncomePercentageShare','Joint Owner Percentage Share of Business Income');
		highlightDynaField(rowId,'jOBusinessIncomePercentageShare');
		 return false;
	}*/
	
	//Validate if percent of business ownership is 100% then the Share of Business Income must be 100%
	/*if(joBusinessOwnershipPercentShare.val()==100 && joBusinessIncomePercentShare.val()!=100 ){
		dynaAlertMe("DC4307",'jOBusinessOwnershipPercentageShare','Joint Owner Percentage Share of Business Ownership','100','Joint Owner Percentage Share of Business Income','100');
		highlightDynaField(rowId,'jOBusinessOwnershipPercentageShare');
		return false;
	}*/
	
	return true;
}

function compareDynaDatesCheck(beginDate, endDate, targetField, dateString1, dateString2){
    var barrDate = beginDate.split("/");
    if(barrDate==beginDate)
    	barrDate = beginDate.split("-");
    var month1 = barrDate[0];
    var day1 = barrDate[1];
    var year1 = barrDate[2];
    var earrDate = endDate.split("/");
    if(earrDate==endDate)
    	earrDate = endDate.split("-");
    var month2 = earrDate[0];
    var day2 = earrDate[1];
    var year2 = earrDate[2];
    var month1Val = month1;
    var day1Val = day1;
    if (day1Val=="disableDay") day1Val="01";
    var year1Val = year1;
    var month2Val = month2;
    var day2Val = day2;
    if (day2Val=="disableDay") day2Val="01";
    var year2Val = year2;

    if ((month1Val ==null && day1Val ==null && year1Val ==null) || (month1Val =="" && day1Val =="" && year1Val =="")) {
        return true;
    } else {
        var dateOne = new Date(year1Val, month1Val - 1, day1Val);
    }

    if ((month2Val ==null && day2Val ==null && year2Val ==null) || (month2Val =="" && day2Val =="" && year2Val =="")) {
        return true;
    } else {
        var dateTwo = new Date(year2Val, month2Val - 1, day2Val);
    }

    if(dateOne < dateTwo){
    return false;
    //alert("Returning false from compareDates");
    }
    return true;
}

function isDateValid(targetField,higlightField,dateString,rowId) {
	
	 var vDate = targetField.val();
    var arrDate = vDate.split("/");

    var errArr = createErrArr(targetField);

    var monthVal = arrDate[0];
    var dayVal   = arrDate[1];
    var yearVal  = arrDate[2];
    var errArr2=createErrArr(monthVal,dayVal,yearVal);
    if(yearVal<=1879){
		dynaAlertMe("GL021",higlightField,dateString,'01/01/1880');
		highlightDynaField(rowId,higlightField);
		return false;	
	}
		if (dayVal=="disableDay") dayVal="01";
		var test = "";
		
		 if (validateDynaMonth(monthVal) && validateDynaDay(dayVal) && validateDynaYear(yearVal) && validateEntireDate(monthVal,dayVal,yearVal)) {
			var thisDateStr = monthVal + "/ " + dayVal + "/ " + yearVal;

			var thisDate = new Date( thisDateStr );
		
			var thisDateGMTString = thisDate.toGMTString();
			var thisDatefinalarray = thisDateGMTString.split( " " );
			var thisMonthNum = getMonthNumber(thisDatefinalarray[2]);
			
			if ( thisMonthNum == monthVal ) {
				return true;
			} else {
				return createDynaErrorMessages(monthVal,dayVal,yearVal,dateString);
			}
			return true;
	  	}else if (! validateDynaMonth(monthVal) || ! validateDynaDay(dayVal) || ! validateDynaYear(yearVal) || ! validateEntireDate(monthVal,dayVal,yearVal)){
						dynaAlertMe("GL017",higlightField,dateString);
						highlightDynaField(rowId,higlightField);
						return false;
		}else if(!limitDate(targetField,dateString)){
			return false;
		}
		 return true;
		}
//Validate that 'Joint Ownership Circumstances Start / Change Date' and endDate are valid
function validateDates(rowId){
	/*displayDynaMessages('','');
	if(!isDateValid(jQuery('#' + rowId + '_' + 'jOCircumstancesStartChangeDate'),'jOCircumstancesStartChangeDate','Joint Ownership Start / Change Date',rowId))
		return false;
	if(jQuery('#' + rowId + '_' + 'jOEndDate').val()!=''){
		if(!isDateValid(jQuery('#' + rowId + '_' + 'jOEndDate'),'jOEndDate','Joint Ownership End Date',rowId))
			return false;
		}*/
	return true;
	}

function isDuplicateRecordJointOwner(otherFields,selectDropDownFields,gridNum){
    var grid = $('#grid'+gridNum);
    var sel_id = grid.jqGrid('getGridParam', 'selrow');
    var data = $("#grid0").jqGrid('getGridParam', 'data');
    var ownerNameToBeChecked = jQuery('#' + sel_id + '_' + 'jointOwnerName').val();
    var endDateCheck = jQuery('#' + sel_id + '_' + 'jOEndDate').val();
	var beginDateCheck = jQuery('#' + sel_id + '_' + 'jOCircumstancesStartChangeDate').val();
    var selectedRecordText = getSelectedRecordText(otherFields,selectDropDownFields,gridNum);
    /*if(selectedRecordText==''){
    	return false;
    }*/
    var numberOfRecords = data.length;
    if(numberOfRecords==1 && sel_id == 'new_row'){
    	return false;
    }
    for(var count=0;count<numberOfRecords;count++){
    	if(count+1!=sel_id){
    		var rdata = data[count];
    		if(rdata.jointOwnerName!=null){
    			if((jointOwnernameArray[ownerNameToBeChecked]==jointOwnernameArray[rdata.jointOwnerName]) || (jointOwnernameArray[ownerNameToBeChecked]==rdata.jointOwnerName) && (jointOwnernameArray[ownerNameToBeChecked]!="")){
    				if(endDateCheck=='' && (rdata.jOEndDate=='' || rdata.jOEndDate==' ' || rdata.jOEndDate==null)){
    					return true;
    				}
    				if(rdata.jOEndDate=='' || rdata.jOEndDate==' ' || rdata.jOEndDate==null){
    					if(compareDynaDatesCheck(endDateCheck,rdata.jOCircumstancesStartChangeDate,endDateCheck,'',''))
    						return true;
    					else
    						return false;
    				}
    				/*if(endDateCheck==''){
    					if(compareDynaDatesCheck(rdata.jOEndDate,beginDateCheck, rdata.jOEndDate,'',''))
    						return true;
    					else
    						return false;
    				}*/
    			}
    		}
        }
    }
    return false;

}

function isDuplicateRecordCheck(otherFields,selectDropDownFields,gridNum){
    var grid = $('#grid'+gridNum);
    var sel_id = grid.jqGrid('getGridParam', 'selrow');
    var selectedRecordText = getSelectedRecordText(otherFields,selectDropDownFields,gridNum);
    var numberOfRecords = grid.getGridParam("records");
    if(numberOfRecords==1 && sel_id == 'new_row'){
    	return false;
    }
    for(var count=1;count<numberOfRecords;count++){
        var rdata = grid.getRowData(count);
        if(sel_id != count){
            var rowText = getRowFieldsText(rdata,otherFields);
            rowText = rowText + getRowFieldsText(rdata,selectDropDownFields);
            if(selectedRecordText == rowText){
                return true;
            }
        }
    }
    return false;

}


function validateFormData(rowId) {
	displayDynaMessages('','');
	removeHighlights(rowId);
	var grid = $('#grid0');
	var errorMsgInnerHtmlTxt = innerHtmlTxt[0];	
	var isValid = true;
    var obj = jQuery.parseJSON(GetJSON1());
	//var noOfRecords = obj.records;
	var varErrorDiv = document.getElementById("jqgridCustomErrMsg0");
	var ownerName = jQuery('#' + rowId + '_' + 'jointOwnerName');
	var organization = jQuery('#' + rowId + '_' + 'nameOrOrganization');
	var incShare = jQuery('#' + rowId + '_' + 'jOBusinessOwnershipPercentageShare');
	var endDate = jQuery('#' + rowId + '_' + 'jOEndDate');
	var beginDate = jQuery('#' + rowId + '_' + 'jOCircumstancesStartChangeDate');
	var ownershipShare = jQuery('#' + rowId + '_' + 'jOBusinessOwnershipPercentageShare');
	//var incomeShare = jQuery('#' + rowId + '_' + 'jOBusinessIncomePercentageShare');
	//var mandatoryFieldNames = new Array('Joint Ownership Start/Change Date', 'Joint Owner Percentage Share of Business Ownership(%)', 'Joint Owner Percentage Share of Business Income(%)');
	//var mandatoryFields = new Array('jOCircumstancesStartChangeDate', 'jOBusinessOwnershipPercentageShare');
	var otherFields = new Array();
    var selectDropDownFields = new Array('jointOwnerName');

    /*if(!isCompleteDyna(rowId, mandatoryFields,mandatoryFieldNames)){
		return false;
	}*/
	if(!isCompleteDynaJo(rowId)){
		return false;
	}
	if ($(beginDate).val() != "" && !isDateValid(beginDate,beginDate,'Joint Ownership Start / Change Date', rowId))
	{
		return false;
	}
	if ($(endDate).val() != "" && !isDateValid(endDate, endDate,'Joint Ownership End Date', rowId))
	{
		return false;
	}
	
	if(!validateDates(rowId)){
		return false;
	}

	if(!validateNameorOrganization(organization.val())){
		dynaAlertMe('GL016','nameOrOrganization','Outside-Household Joint Owner Name');
		return false;
	}
	/*if(!validateNameorOrganization(incShare.val())){
		dynaAlertMe('GL016','jOBusinessOwnershipPercentageShare','Joint Owner Percentage Share ofIncome (%)');
		return false;
	}*/
	
	if (!dynafutureDateForDcDates(beginDate,'Joint Ownership Start / Change Date')){
		dynaAlertMe('GL200','jOCircumstancesStartChangeDate','Joint Ownership Start / Change Date');
		return false;
	}
	if (!compareDynaDates(endDate,beginDate, endDate,'Joint Ownership End Date','Joint Ownership Start / Change Date')){
		return false;
	}
	/*if (validateHoursPerWeek(rowId)){
		dynaAlertMe('DC4620','hrsWeekPersonParticipates','');
		return false;
	}*/
	if(isDuplicateRecordJointOwner(otherFields,selectDropDownFields,0))
		{
        dynaAlertMe('DC066');
        return false;
		}
	if(isDuplicateRecordJointOwnerOutHH())
	{
		dynaAlertMe('DC066');
		return false;
	}
	var otherFieldsData = new Array('jOCircumstancesStartChangeDate','jOEndDate','jOBusinessOwnershipPercentageShare');
	if(isDuplicateRecordCheck(otherFieldsData,selectDropDownFields,0))
		{
		dynaAlertMe('GL045','data','data');
		return false;
		}
	return true;
}

function validateAfterDynaSubmit0(){
	return true;
}

function validateNameorOrganization(val){
	   if (val==null || val.length==0) return true;
	    var regexp = /^[a-zA-Z-']+$/;
	    if (val.search(regexp) != -1) {
	           return true;
	    } else {
	        return false;
	    }
}

function removeHighlights(rowId){
	removeDynaHighlightField(rowId,'nameOrOrganization');
	removeDynaHighlightField(rowId,'jointOwnerName');
	removeDynaHighlightField(rowId,'jOCircumstancesStartChangeDate');
	removeDynaHighlightField(rowId,'jOEndDate');
	removeDynaHighlightField(rowId,'jOBusinessOwnershipPercentageShare');
	//removeDynaHighlightField(rowId,'jOBusinessIncomePercentageShare');
	
}

function validateHoursPerWeek(rowId){
	/*var hsWeekPersonParticipates = jQuery('#' + rowId + '_' + 'hrsWeekPersonParticipates');
	   var incomeDocVal = 0;
	   incomeDocVal = hsWeekPersonParticipates.val();
	   if(parseInt(incomeDocVal) > 168) {
			return true;
	   }
	   return false;*/
}

function validateAfterDynaSubmit(){
	return true;
}

function getAllDropDownElements0() {
		return 'jointOwnerName';
}

function getAllDropDownElementRefTables0() {
		return '';
}

function setElementValues(obj){
		return true;
}

function setValuesOnFocus(obj){
		return true;	
}

function checkDateValues(obj){
 return true;
}

function isDuplicateRecordJointOwnerOutHH(){
    var grid = $('#grid0');
    var sel_id = grid.jqGrid('getGridParam', 'selrow');
    var data = $("#grid0").jqGrid('getGridParam', 'data');
    var ownerNameToBeChecked = jQuery('#' + sel_id + '_' + 'nameOrOrganization').val();
    var endDateCheck = jQuery('#' + sel_id + '_' + 'jOEndDate').val();
	var beginDateCheck = jQuery('#' + sel_id + '_' + 'jOCircumstancesStartChangeDate').val();
    var numberOfRecords = data.length;
    if(numberOfRecords==1 && sel_id == 'new_row'){
    	return false;
    }
    for(var count=0;count<numberOfRecords;count++){
    	if(count+1!=sel_id){
    		var rdata = data[count];
    		if(rdata.nameOrOrganization!=null){
    			if(ownerNameToBeChecked!="" && ownerNameToBeChecked==rdata.nameOrOrganization){
    				if(endDateCheck=='' && (rdata.jOEndDate=='' || rdata.jOEndDate==' ' || rdata.jOEndDate==null)){
    					return true;
    				}
    				if(rdata.jOEndDate=='' || rdata.jOEndDate==' ' || rdata.jOEndDate==null){
    					if(compareDynaDatesCheck(endDateCheck,rdata.jOCircumstancesStartChangeDate,endDateCheck,'',''))
    						return true;
    					else
    						return false;
    				}
    			}
    		}
        }
    }
    return false;

}

selectedDynaListNum=-1;