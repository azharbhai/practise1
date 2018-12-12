
package gov.state.nextgen.co.batch.bo;


import gov.state.nextgen.co.batch.util.CoBatchConstants;
import gov.state.nextgen.co.util.COFormsUtility;
import gov.state.nextgen.co.util.CONoticeUtility;
import gov.state.nextgen.co.util.COPrintPreviewHelper;
import gov.state.nextgen.co.util.CoTaskCreationUtil;
import gov.state.nextgen.co.util.validator.SchemaValidator;
import gov.state.nextgen.co.util.xsd.schema.Batch;
import gov.state.nextgen.co.util.xsd.schema.CorrList;
import gov.state.nextgen.co.util.xsd.schema.CoverSheetDetail;
import gov.state.nextgen.co.util.xsd.schema.Envelope;
import gov.state.nextgen.co.util.xsd.schema.EnvelopeList;
import gov.state.nextgen.common.bo.AlAlertManager;
import gov.state.nextgen.common.bo.BiUtils;
import gov.state.nextgen.common.bo.BvUtils;
import gov.state.nextgen.common.bo.CoAssembler;
import gov.state.nextgen.common.bo.CoDAOServices;
import gov.state.nextgen.common.bo.CoRecipientsManager;
import gov.state.nextgen.common.bo.ICoBatch;
import gov.state.nextgen.common.bo.TmTaskManager;
import gov.state.nextgen.common.cargo.custom.ArAppAddrCargo;
import gov.state.nextgen.common.cargo.custom.ArApplicationForAidCargo;
import gov.state.nextgen.common.cargo.custom.BiFsDetailCargo;
import gov.state.nextgen.common.cargo.custom.BiIssuanceTriggerCargo;
import gov.state.nextgen.common.cargo.custom.BiLiheapDetailCargo;
import gov.state.nextgen.common.cargo.custom.BiTanfDetailCargo;
import gov.state.nextgen.common.cargo.custom.BvClaimCargo;
import gov.state.nextgen.common.cargo.custom.BvClaimHeaderCargo;
import gov.state.nextgen.common.cargo.custom.BvClaimRecoveryCargo;
import gov.state.nextgen.common.cargo.custom.BvMaPremiumPymtDtlsCargo;
import gov.state.nextgen.common.cargo.custom.COCorrespondence;
import gov.state.nextgen.common.cargo.custom.CoMassMailingReqCargo;
import gov.state.nextgen.common.cargo.custom.CoMasterCargo;
import gov.state.nextgen.common.cargo.custom.CoRequestHistoryCargo;
import gov.state.nextgen.common.cargo.custom.CoRequestRecipientsCargo;
import gov.state.nextgen.common.cargo.custom.DcAuthRepCargo;
import gov.state.nextgen.common.cargo.custom.DcCaseAddressesCargo;
import gov.state.nextgen.common.cargo.custom.DcCaseIndividualCargo;
import gov.state.nextgen.common.cargo.custom.DcCaseProgramCargo;
import gov.state.nextgen.common.cargo.custom.DcCaseProgramIndvCargo;
import gov.state.nextgen.common.cargo.custom.DcCasesCargo;
import gov.state.nextgen.common.cargo.custom.DcChildSuppNonCoopCargo;
import gov.state.nextgen.common.cargo.custom.DcHeadOfHouseholdCargo;
import gov.state.nextgen.common.cargo.custom.DcIndvAbawdCargo;
import gov.state.nextgen.common.cargo.custom.DcIndvCargo;
import gov.state.nextgen.common.cargo.custom.DcIndvNonCooperationCargo;
import gov.state.nextgen.common.cargo.custom.DcLiheapCargo;
import gov.state.nextgen.common.cargo.custom.DcUnearnedIncomeCargo;
import gov.state.nextgen.common.cargo.custom.DcWorcReferralCargo;
import gov.state.nextgen.common.cargo.custom.EdCaseRecertDatesCargo;
import gov.state.nextgen.common.cargo.custom.EdChangeReportingDtlsCargo;
import gov.state.nextgen.common.cargo.custom.EdDcIndvDisqPenaltiesCargo;
import gov.state.nextgen.common.cargo.custom.EdEligNoticeReasonsCargo;
import gov.state.nextgen.common.cargo.custom.EdEligibilityCargo;
import gov.state.nextgen.common.cargo.custom.EdIndvEligibilityCargo;
import gov.state.nextgen.common.cargo.custom.In1095bNormalizedCustomCargo;
import gov.state.nextgen.common.cargo.custom.MoEmployeeAppsCargo;
import gov.state.nextgen.common.cargo.custom.MoEmployeeCasesCargo;
import gov.state.nextgen.common.cargo.custom.MoEmployeesCargo;
import gov.state.nextgen.common.cargo.custom.MoOfficeAddressesCargo;
import gov.state.nextgen.common.cargo.custom.MoOfficesCargo;
import gov.state.nextgen.common.cargo.custom.PmAddressCargo;
import gov.state.nextgen.common.cargo.custom.PmCcapProvidersCargo;
import gov.state.nextgen.common.cargo.custom.PmLiheapPaymentCargo;
import gov.state.nextgen.common.cargo.custom.PmLiheapVendorCargo;
import gov.state.nextgen.common.cargo.custom.PmProviderChildAssocCargo;
import gov.state.nextgen.common.cargo.custom.PmProviderStatusCargo;
import gov.state.nextgen.common.cargo.custom.PmVendorCaseAssocCargo;
import gov.state.nextgen.common.cargo.custom.TmTaskStatusCargo;
import gov.state.nextgen.common.cargo.custom.VBiCcdmiPayeeCargo;
import gov.state.nextgen.common.cargo.custom.VBiLiheapPayeeCargo;
import gov.state.nextgen.common.cargo.custom.VBiWarrantDetailCargo;
import gov.state.nextgen.common.cargo.custom.VCoManualDataValuesCargo;
import gov.state.nextgen.common.cargo.custom.VCoRequestCargo;
import gov.state.nextgen.common.cargo.custom.VEdEligibilityIndvCargo;
import gov.state.nextgen.common.collection.custom.ArAppAddrCollection;
import gov.state.nextgen.common.collection.custom.CoMassMailingReqCollection;
import gov.state.nextgen.common.collection.custom.CoRequestHistoryCollection;
import gov.state.nextgen.common.collection.custom.CoRequestRecipientsCollection;
import gov.state.nextgen.common.collection.custom.DcAuthRepCollection;
import gov.state.nextgen.common.collection.custom.DcCaseAddressesCollection;
import gov.state.nextgen.common.collection.custom.EdCaseRecertDatesCollection;
import gov.state.nextgen.common.collection.custom.MoEmployeeAppsCollection;
import gov.state.nextgen.common.collection.custom.MoEmployeeCasesCollection;
import gov.state.nextgen.common.collection.custom.MoEmployeesCollection;
import gov.state.nextgen.common.collection.custom.MoOfficeAddressesCollection;
import gov.state.nextgen.common.collection.custom.VCoRequestCollection;
import gov.state.nextgen.common.exception.CoException;
import gov.state.nextgen.common.exception.NoDataFoundException;
import gov.state.nextgen.common.util.ALSOPUtil;
import gov.state.nextgen.common.util.Age;
import gov.state.nextgen.common.util.AlIAlerts;
import gov.state.nextgen.common.util.CoAgeCalculator;
import gov.state.nextgen.common.util.CoConstants;
import gov.state.nextgen.common.util.CoDateFactory;
import gov.state.nextgen.common.util.CoDebugger;
import gov.state.nextgen.common.util.CoRequestGenerator;
import gov.state.nextgen.common.util.CorrespondenceServices;
import gov.state.nextgen.common.util.DocumentBuilder;
import gov.state.nextgen.common.util.ICoPCL;
import gov.state.nextgen.common.util.InTriggerTableUtil;
import gov.state.nextgen.common.util.ProviderNotices;
import gov.state.nextgen.common.util.ReferenceTableAccess;
import gov.state.nextgen.common.util.StringUtils;
import gov.state.nextgen.common.util.TaskConstants;
import gov.state.nextgen.common.util.TmITasks;
import gov.state.nextgen.framework.batch.controller.GenericBatchController;
import gov.state.nextgen.framework.batch.util.FwBatchConstants;
import gov.state.nextgen.framework.batch.util.FwBatchFile;
import gov.state.nextgen.framework.business.bo.FwReferenceTableManager;
import gov.state.nextgen.framework.business.entities.cargo.custom.FwBatchRunControlCargo;
import gov.state.nextgen.framework.business.entities.cargo.custom.FwDate;
import gov.state.nextgen.framework.business.entities.cargo.custom.FwFTPCargo;
import gov.state.nextgen.framework.business.entities.cargo.custom.FwFTPCollection;
import gov.state.nextgen.framework.business.entities.cargo.custom.FwProperty;
import gov.state.nextgen.framework.business.entities.cargo.custom.RefTableData;
import gov.state.nextgen.framework.collection.custom.FwBatchRunControlCollection;
import gov.state.nextgen.framework.exception.ApplicationException;
import gov.state.nextgen.framework.exception.FrameworkException;
import gov.state.nextgen.framework.exception.GenericBatchException;
import gov.state.nextgen.framework.exception.GenericValidationException;
import gov.state.nextgen.framework.management.ftp.FwUserInfo;
import gov.state.nextgen.framework.security.SecurityServiceFactory;
import gov.state.nextgen.framework.util.Debug;
import gov.state.nextgen.framework.util.FwCalendar;
import gov.state.nextgen.framework.util.FwConstants;
import gov.state.nextgen.framework.util.FwPropertyLoader;
import gov.state.nextgen.framework.util.ILog;
import gov.state.nextgen.framework.util.IProperty;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;
import java.util.Vector;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import org.apache.commons.lang.ArrayUtils;
import org.apache.pdfbox.util.PDFMergerUtility;

import us.nd.state.itd.apps.itd.filenet.ws.v2.AddDocumentDT;
import us.nd.state.itd.apps.itd.filenet.ws.v2.DocumentInfoDT;
import us.nd.state.itd.apps.itd.filenet.ws.v2.FileNetWebServices2Delegate;
import us.nd.state.itd.apps.itd.filenet.ws.v2.FileNetWebServices2Service;
import us.nd.state.itd.apps.itd.filenet.ws.v2.PropertyDT;

import com.deloitte.common.objects.framework.CheckedApplicationException;

/**
 * The <code>CoBatch</code> class provides methods for CO batch programs.
 * The class CoBatch has most of the common logic that is required for 
 * inserting correspondence triggers and correspondence generation
 */
public class CoBatch extends CoAbstractBatch implements ICoBatch {

	/**
	 * Field fileCommitted.
	 */
	boolean fileCommitted = false;

	/**
	 * Field massCoSw.
	 */
	protected char massCoSw = 'N';

	/**
	 * Field loggingSW.
	 */
	protected String loggingSW = "Y";

	/**
	 * Field successfull.
	 */
	protected boolean successfull = true;

	/**
	 * Field coReqGen.
	 */
	protected CoRequestGenerator coReqGen = null;

	/**
	 * Field coDAOServices.
	 */
	protected CoDAOServices coDAOServices = null;

	/**
	 * Field coConnection.
	 */
	protected java.sql.Connection coConnection = null;

	/**
	 * Field lookupTable.
	 */
	@SuppressWarnings("rawtypes")
	protected java.util.HashMap lookupTable = null;

	/**
	 * Field exceptionBufferRecord.
	 */
	protected StringBuffer exceptionBufferRecord = null;

	/**
	 * Field summaryLogicalFileName.
	 */
	protected String summaryLogicalFileName = null;

	/**
	 * Field optFileNames.
	 */
	protected String optFileNames = null;

	/**
	 * Field coverSheetPcl.
	 */
	protected String coverSheetPcl = null;

	/**
	 * Field errorLogStream.
	 */
	protected OutputStream errorLogStream = null;

	/**
	 * Field coLogFile.
	 */
	protected BufferedOutputStream coLogFile = null;

	/**
	 * Field DOC_ID_1020.
	 */
	private final static String DOC_ID_1020 = "FXX032";

	/**
	 * Field DOC_ID_3503SER.
	 */
	private final static String DOC_ID_3503SER = "FXX069";  // BRGUS00118836 - gundan - CR 20974

	/**
	 * Field DOC_ID_TF0001.
	 */
	private final static String DOC_ID_TF0001 = "TF0001";

	/**
	 * Field DOC_ID_1830.
	 */
	private final static String DOC_ID_1830 = "FXX300";

	// BRGUS00160565 - NaredlaS - For DHS - 1010 - SP
	/**
	 * Field DOC_ID_FXX259.
	 */
	private final static String DOC_ID_FXX259 = "FXX259";

	/**
	 * Field docIds.
	 */
	protected String docIds = null;

	/**
	 * Field docIds.
	 */
	protected String docName = "";

	/**
	 * Field logicalFileName
	 */
	private String logicalFileName = null; 

	/**
	 * Field replacementWarrantSuccessCount.
	 */
	protected long replacementWarrantSuccessCount = 0;

	/**
	 * Field initialWarrantSuccessCount.
	 */
	protected long initialWarrantSuccessCount = 0;

	/**
	 * Field updatingTrigger.
	 */
	protected boolean updatingTrigger = false;

	/**
	 * Field exceptionRecordBuf.
	 */
	protected StringBuffer exceptionRecordBuf = new StringBuffer();

	/**
	 * Field exceptionRecordSummary.
	 */
	protected StringBuffer exceptionRecordSummary = new StringBuffer();

	/**
	 * Field exceptionTriggers.
	 */
	protected List<COCorrespondence> exceptionTriggers = new ArrayList<COCorrespondence>();

	/**
	 * Field failureCount.
	 */
	protected long failureCount = 0;

	/**
	 * Field coFileHandler.
	 */	
	private BufferedWriter coFileHandler = null ;

	/**
	 * Field SAVE_POINT.
	 */	
	private static final String SAVE_POINT = "COBATCHSAVEPOINT";

	//BRGUS00120559 - bhattaj
	private String currentEnv = null;


	//Kshama
	private static final String FILE_NAME_1 = "CO";

	//Kshama
	private static final String FILE_NAME_2 = "NO";

	/**
	 * Field for absoluteFileName
	 */
	private String absoluteFileName;
	FwDate fwd = new FwDate();


	ILog log = CoDebugger.getLogger();
	
	private Map<String, CoUpdateDBBean> coRecipientsUpdateMap = null;

	private List<String> newRecipientsList = new ArrayList<String>();

	private List<String> oldRecipientsList = new ArrayList<String>();

	//For TRIGGER_BATCH_SUMMARY
	protected long recordCounter = 0;
	protected long successCounter = 0;
	protected long failureCounter = 0;

	//For NOTICE_BATCH_SUMMARY
	protected long readCount = 0; 
	protected long successCount = 0; 
	protected long failCount = 0;
	
	//For NOTICE_BATCH_SUMMARY
	protected long top60ReadCount = 0; 
	protected long top60SuccessCount = 0; 
	protected long top60FailCount = 0;
	/** To check the count of the blank notices  **/
	protected long blankNoticeCounter = 0;

	
	//For NOTICE_BATCH_SUMMARY
	protected long alert502ReadCount = 0; 
	protected long alert502SuccessCount = 0; 
	protected long alert502FailCount = 0;
		
	
	/**
	 * Field replacedBarCodeText.
	 */	
	//private String replacedBarCodeText = CoConstants.EMPTY_STRING; //unsed varaiable

	protected static Properties ftpProperty = null;
	static {
		IProperty propLoader = FwProperty.getInstance();
		ftpProperty = propLoader.getProperties(CoConstants.CORRESPONDENCE_PROPERTIES);
	}	

	/**
	 * Constructor for CoBatch. 
	 */
	public CoBatch() {
		coDAOServices = new CoDAOServices();
	}
	/**
	 *get any additional parameters for the specific program
	 * This class could have been an abstract class -- <BR>
	 * but its instance is used to use initiateCorrespondence<BR>
	 * Hence this method is implemented here.<BR>
	 * OVERRIDE THIS METHOD APPROPRIATELY IN THE SUBCLASSES
	 * @param vtr Vector
	 * @return int
	 */
	@SuppressWarnings("rawtypes")
	protected int getAdditionalFuctionalParameters(Vector vtr) throws Exception {
		return 0;
	}

	/**
	 * set the initial parameters
	 * should be called as the first line after being overriden
	 * ie.) super.setInitialize() should be the first line in the 
	 * overriden methods definition in the sub-class 
	 */
	protected void setInitialize() {
		exceptionBufferRecord = new StringBuffer();

		try {

			//Create a Log File for logging Co Activity
			if (("Y").equals(loggingSW)) //log only based on this sw
			{
				coLogFile =
						(BufferedOutputStream) getCoLogFile(jobId
								+ "_"
								+ parallelRunId
								+ "_LOGTmp");
			}
		} catch (Exception e) {
			CoDebugger.debugWarning("Exception creating Co Log File..." + e);
		}
		if (coLogFile != null) {
			writeCoLog("Starting Co Log ..", coLogFile);
		}
	}
	/**
	 *method to release resources and exit batch
	 * @return int
	 */
	protected int exitBatch() {
		cleanUpCachedResources();
		return -1;
	}
	/**
	 * This method is used by all the individual batch drivers for the main
	 * processing. It is used to fetch triggers for all the correspondences
	 * scheduled and invoking the assemblers for generating XMLs.
	 * 
	 * @param jobId
	 * @return retValue int
	 * @throws Exception
	 * @author kschopra
	 */
	protected int processCoBatch(String jobId) throws Exception {

		VCoRequestCargo[] coRequestCargos = null;
		CoRequestRecipientsCargo[] coReqRptCargos = null;
		CoAssembler assembler = null;
		COCorrespondence coCorrespondence = new COCorrespondence();
		CoDebugger.debugInformation("processCoBatch-----------------Line410");
		coCorrespondence.setAsOfDate(CorrespondenceServices.getYYYYMMDDFormattedDateStr(asOfDate, CoBatchConstants.asOfDtMMDDYYYY));
		CoDebugger.debugInformation("processCoBatch-----------------Line415");
		
		/**ND-99344 First Day Of Next Month Calculation for alert 502*/
		SimpleDateFormat format=new SimpleDateFormat("MM/dd/yyyy");
		Date date=format.parse(asOfDate);	
		Timestamp alert502firstWorkingDayOfNextMonth=ALSOPUtil.getFirstWorkingDayOfMonth(ALSOPUtil.getFirstDayOfNextMonth(new Timestamp(date.getTime())));

		
		long processedRecords = 0;
		try {
			CoDebugger.debugInformation("processCoBatch-----------------Line420");
			coConnection = tbc.getConnection();
			CoDebugger.debugInformation("processCoBatch-----------------Line422");
			coConnection.setAutoCommit(false);
			coDAOServices.setConnection(coConnection);
			CoDebugger.debugInformation("processCoBatch-----------------Line425");
		} catch (Exception e) {
			return this.handleBatchException(e, 1, null, null);
		}

		/** Fetch CO Request History records for the given job id.*/
		try {
			CoDebugger.debugInformation("processCoBatch-----------------Line432");
			coRequestCargos = this.getCOTriggers(coConnection, jobId);			
			CoDebugger.debugInformation("processCoBatch-----------------Line435");
			coLogicalCommit();	
			if(null!=coRequestCargos && coRequestCargos.length>0){
				readCount = coRequestCargos.length;
			}
			if(readCount==0){
				docName=getDocNameFromJobID( jobId);	
			}
			readCount=0;
			if (coRequestCargos != null && coRequestCargos.length > 0) {
				if (coLogFile != null) {
					writeCoLog("Total no of records - " + coRequestCargos.length, coLogFile);
				}
				for (VCoRequestCargo coRequestCargo : coRequestCargos) {
					try {
						if (!isReprintRequest(coRequestCargo.getT2CoReqSeq())) {
							/** Transaction Management*/
							tbc.savepoint(null, "CoSavePoint");

							/** Map CoRequestCargo to COCorrespondence object.*/
							COPrintPreviewHelper.mapCargoToCo(coRequestCargo, coCorrespondence);

							/**Check if the document is Form 1095B or not
							If not only then proceed with updating recipients.*/
							boolean form1095B = false;
							String docId = coCorrespondence.getDocId();
							if(docId!= null && docId.equalsIgnoreCase(CoConstants.NDMAELF23)){
								form1095B = true;
							}
							if (coCorrespondence.getCaseAppNumber() != null && !coCorrespondence.getCaseAppNumber().startsWith("T")) {								
								/** Check if the latest recipients differ from the original recipients.*/
								if (!form1095B && isRecipientChanged(coCorrespondence)) {
									/** Delete existing CO_REQUEST_RECIPIENTS based on the CO_REQ_SEQ.*/
									coDAOServices.deleteCoRequestRecipients(coCorrespondence.getCoReqSeq());

									/** Insert new CO_REQUEST_RECIPIENTS by fetching the latest recipients.*/
									CoRecipientsManager coRecipientsManager = new CoRecipientsManager(coDAOServices);
									coRecipientsManager.configureRecipientsForCoReqSeq(coCorrespondence);
								}
							}
							
							/** Fetch recipients for given CO Request History record.*/
							long coReqSeq = ((VCoRequestCargo) coRequestCargo).getT2CoReqSeq();
							coReqRptCargos = getCoReqRecipientsForReqSeq(coReqSeq);
							if (coLogFile != null) {
								if(form1095B){
									writeCoLog("case Num  - " + coCorrespondence.getApptId() + "   -    No of recipients for  - " + coReqRptCargos.length, coLogFile);
								}else{
									writeCoLog("case Num  - " + coCorrespondence.getCaseAppNumber() + "   -    No of recipients for  - " + coReqRptCargos.length, coLogFile);
								}
							}
							long coRptSeq = 0;
							CoRequestHistoryCargo reqHisCargo=new CoRequestHistoryCargo();
							reqHisCargo.setCoReqSeq(coReqSeq);
							Object[] params=new Object[]{reqHisCargo};
							CoRequestHistoryCollection reqHisColl=new CoRequestHistoryCollection();
							CoRequestHistoryCargo[] reqHisCargoArr=(CoRequestHistoryCargo[])reqHisColl.select("findByReqSeq",params);
							boolean generateXmlForProviderVendor=false;
							if(null!=reqHisCargoArr && reqHisCargoArr.length>0){
								if((docId.equals(CoConstants.CO_DOC_ID_NCH049)) && (null!=reqHisCargoArr[0] && null!=reqHisCargoArr[0].getMiscParms() && ((CoConstants.MASS_MAIL_PROVIDER.equalsIgnoreCase(reqHisCargoArr[0].getMiscParms())) || (CoConstants.MASS_MAIL_VENDOR.equalsIgnoreCase(reqHisCargoArr[0].getMiscParms()))))){
									generateXmlForProviderVendor=true;
								}else if(null!=reqHisCargoArr[0] && (null!=(String.valueOf(reqHisCargoArr[0].getRequestTypeCd())) && ( CoConstants.V.equalsIgnoreCase(String.valueOf(reqHisCargoArr[0].getRequestTypeCd())) || CoConstants.P.equalsIgnoreCase(String.valueOf(reqHisCargoArr[0].getRequestTypeCd())) || CoConstants.S.equalsIgnoreCase(String.valueOf(reqHisCargoArr[0].getRequestTypeCd()))))){
									generateXmlForProviderVendor=true;
								}
							}
							if(coReqRptCargos.length>0){
								for (int j = 0; j < coReqRptCargos.length; j++) {
								try{
									readCount++;
									coRptSeq = ((CoRequestRecipientsCargo) coReqRptCargos[j]).getCoRptSeq();
									log.log(CoConstants.CO_NAME,ILog.INFO ,"The recipient data present  for CO_RPT_SEQ -  "+coRptSeq+" is "+coReqRptCargos[j].getRecipientData());
									coCorrespondence.setRecipientData(coReqRptCargos[j].getRecipientData());
									coCorrespondence.setRecipientType(coReqRptCargos[j].getRecipientType());
									/** Evaluate file name for the given CO Request and CO Request Recipient. - reverting to generateDt	**/							
									String xmlFileName = getXMLFileNameForNotice(coCorrespondence.getDocId(), coCorrespondence.getDocName(), coCorrespondence.getCaseAppNumber(), coReqSeq, coRptSeq, coRequestCargo.getGenerateDt());
									if (form1095B){
										/** Form 1095B is individual driven and not case number driven.
										 
										For Form 1095B IndividualId is stored in BenefitNum column of CO_REQUEST_HISTORY
										Data is sourced from legacy system as well and it will voilate constranits on IdvId and caseNumber
										Hence we use BENEFIT_NUM = INDV_ID and REASON_CD_LIST = CASE_NUMBER */
										xmlFileName = getXMLFileNameForNotice(coCorrespondence.getDocId(), coCorrespondence.getDocName(), String.valueOf(coCorrespondence.getApptId()), coReqSeq, coRptSeq, coRequestCargo.getGenerateDt());
									}
									
									if ( (coCorrespondence.getCaseAppNumber()==null ||CoConstants.ZERO_STRING.equalsIgnoreCase(coCorrespondence.getCaseAppNumber()) || coCorrespondence.getCaseAppNumber().isEmpty() ||generateXmlForProviderVendor) 
											&& (coCorrespondence.getProviderId()>0) ){		
										/** For CCAP Provider and LiHEAP Vendor Notices, setting Provider/ vendor ID in fileName */
										xmlFileName = getXMLFileNameForNoticeForProvidersAndVendors(coCorrespondence.getDocId(), coCorrespondence.getDocName(), String.valueOf(coCorrespondence.getApptId()), coReqSeq, coRptSeq, coRequestCargo.getGenerateDt(),coCorrespondence.getProviderId(), coRequestCargo.getGenerateDt());
									}
									
									docName = coCorrespondence.getDocName();
									coCorrespondence.setFileLocation(xmlFileName);
									coCorrespondence.setCoRptSeq(coRptSeq);
	
									/** Set job id and document name in CoCorrespondence
									object for a given doc Id.**/
									coCorrespondence.setJobId(jobId);
									coCorrespondence.setDocName(coRequestCargo.getDocName());
	
									/** Get assembler class handle to be invoked for the*/
									assembler = getHandleForAssembler();
	
									/** Invoke assembler to generate XML file to be sent HP Exstream **/
									assembler.genarateDocument(coCorrespondence, 0);
									processedRecords++;
									
									/** ND-91320 **/
									if (coCorrespondence.getDocId()
											.equalsIgnoreCase(
													CoConstants.NDHCREF09)
											&& coCorrespondence
													.getIsManualyGenerated()) {
										updateEdCaseRecertDateForNDHCREF09(coCorrespondence);
									}
									
									successCount++;
									
								} catch (CoException e) {
									failCount++;
									/** Transaction Management */
									tbc.rollbackCoSpring(null, "CoSavePoint");
									CoDebugger.debugException(e.getMessage(),e);
									CoDebugger.debugInformation("Fail Count Incremented. Exception while invoking assembler for job: " + jobId + " Could not invoke assembler to generate XML and/or PDF file for coReqSeq: " + coCorrespondence.getCoReqSeq() +". Skipping this trigger");
									if(null != e.getMessage() && e.getMessage().contains(CoConstants.BLANK)){
										blankNoticeCounter++;
										 String expSummary=SchemaValidator.getformattedException(e);
										CoDebugger.debugInformation("Blank Notice Count Incremented. Exception while validating the XML for jobId:  " + jobId + " Could not process the XML for PDF file generation for coReqSeq: " + coCorrespondence.getCoReqSeq() +". Suppressing  this notice");
										writeException("The Notice has been suppressed due to missing mandatory fields for CoReqSeq No: "+coCorrespondence.getCoReqSeq(), CoConstants.BLANK +" notice reason: "+expSummary, e, false);
									}						
									else if(null != e.getMessage()){
										writeException("Exception while invoking assembler for job: " + jobId + " Could not invoke assembler to generate XML and/or PDF file for coReqSeq: " + coCorrespondence.getCoReqSeq(), e.getMessage(), e, false);
									}else{
										writeException("Exception while invoking assembler for job: " + jobId + " Could not invoke assembler to generate XML and/or PDF file for coReqSeq: " + coCorrespondence.getCoReqSeq(), e.toString(), e, false);
										}
									continue;
									}	
								}
								
							}else{
								failCount++;
								writeException("No recepient found for coReqSeq: " + coCorrespondence.getCoReqSeq() + " and job: " + jobId+". Skipping this trigger.", "No recepient found for coReqSeq: " + coCorrespondence.getCoReqSeq() + " and job: " + jobId+". Skipping this trigger.", null, false);
							}
							/** Transaction Management*/
							coLogicalCommit();
						}
						if (coLogFile != null) {
							writeCoLog("Processed records - " + processedRecords, coLogFile);
						}
					} catch (CoException e) {
						failCount++;
						/** Transaction Management */
						tbc.rollbackCoSpring(null, "CoSavePoint");
						CoDebugger.debugException(e.getMessage(),e);
						CoDebugger.debugInformation("Fail Count Incremented. Exception while invoking assembler for job: " + jobId + " Could not invoke assembler to generate XML and/or PDF file for coReqSeq: " + coCorrespondence.getCoReqSeq() +". Skipping this trigger");
						if(null != e.getMessage() && e.getMessage().contains(CoConstants.BLANK)){
							blankNoticeCounter++;
							 String expSummary=SchemaValidator.getformattedException(e);
							CoDebugger.debugInformation("Blank Notice Count Incremented. Exception while validating the XML for jobId:  " + jobId + " Could not process the XML for PDF file generation for coReqSeq: " + coCorrespondence.getCoReqSeq() +". Suppressing  this notice");
							writeException("The Notice has been suppressed due to missing mandatory fields for CoReqSeq No: "+coCorrespondence.getCoReqSeq(), CoConstants.BLANK +" notice reason: "+expSummary, e, false);
						}						
						else if(null != e.getMessage()){
							writeException("Exception while invoking assembler for job: " + jobId + " Could not invoke assembler to generate XML and/or PDF file for coReqSeq: " + coCorrespondence.getCoReqSeq(), e.getMessage(), e, false);
						}else{
							writeException("Exception while invoking assembler for job: " + jobId + " Could not invoke assembler to generate XML and/or PDF file for coReqSeq: " + coCorrespondence.getCoReqSeq(), e.toString(), e, false);
						}
						continue;
					} catch (Exception e) {
						failCount++;
						/** Transaction Management */
						tbc.rollbackCoSpring(null, "CoSavePoint");
						CoDebugger.debugException(e.getMessage(),e);
						CoDebugger.debugInformation("Fail Count Incremented. Exception while invoking assembler for job: " + jobId + " Could not invoke assembler to generate XML and/or PDF file for coReqSeq: " + coCorrespondence.getCoReqSeq() +". Skipping this trigger");
						if(e.getMessage()!=null){
							writeException("Exception while invoking assembler for job: " + jobId + " Could not invoke assembler to generate XML and/or PDF file for coReqSeq: " + coCorrespondence.getCoReqSeq(), e.getMessage(), e, false);
						}else{
							writeException("Exception while invoking assembler for job: " + jobId + " Could not invoke assembler to generate XML and/or PDF file for coReqSeq: " + coCorrespondence.getCoReqSeq(), e.toString(), e, false);
						}
						continue;
					}
					/**
					 *  ----------------- TOP Trigger Logic-BEGIN-ND-85903-BEGIN --------------
					 */
					if(coRequestCargo.getT1DocId() != null && (coRequestCargo.getT1DocId().equalsIgnoreCase(CoConstants.DOCID_NCH0057))){
						CoDebugger.debugInformation("INSIDE TOP Trigger Logic-BEGIN-ND-85903-BEGIN");
						List<String> claimIds=null;
						try{
						//CALL trigger API InTriggerTableUtil
							InTriggerTableUtil utilTable= new InTriggerTableUtil();
							
							//AUTOMATIC
							if(!coCorrespondence.getIsManualyGenerated()){
								CoDebugger.debugInformation("AUTOMATIC FLOW::::: coCorrespondence getIsManualyGenerated()::"+coCorrespondence.getIsManualyGenerated());
								if(null!=coCorrespondence.getMiscParameters()){
									claimIds=Arrays.asList(coCorrespondence.getMiscParameters().split("\\s*,\\s*"));
								}else{
									CoDebugger.debugInformation("AUTOMATIC FLOW::::: coCorrespondence getMiscParameters() is NULL");
								}
							//MANUAL	
							}else{
								CoDebugger.debugInformation("MANUAL FLOW::::: coCorrespondence getIsManualyGenerated()::"+coCorrespondence.getIsManualyGenerated());
								CoDebugger.debugInformation("MANUAL FLOW:::::");
								Object[] vCoManualDataValuesCargoArr = null;
								try {
									vCoManualDataValuesCargoArr = coDAOServices.getVCoManualDataValues(coCorrespondence.getCoReqSeq());
								} catch (NoDataFoundException e) {
									CoDebugger.debugException("NoDataFoundException", e);
								}
								if (null != vCoManualDataValuesCargoArr && vCoManualDataValuesCargoArr.length > 0) {
									for(Object obj:vCoManualDataValuesCargoArr){
										VCoManualDataValuesCargo vCoManualDataValuesCargo=(VCoManualDataValuesCargo)obj;
										if(null !=vCoManualDataValuesCargo){
											if (vCoManualDataValuesCargo.getFieldName().equals(CoConstants.CLAIM_ID)) {
												if(null!=vCoManualDataValuesCargo.getFieldContent()){
													claimIds=Arrays.asList(vCoManualDataValuesCargo.getFieldContent().split("\\s*,\\s*"));
												}else{
													CoDebugger.debugInformation("CLAIM_ID:::Empty Field Content");	
												}
											}
										}
									}
								}
							}
							if(null!=claimIds && claimIds.size()>0){
								CoDebugger.debugInformation("CLAIM ID LIST"+claimIds);
								top60ReadCount=top60ReadCount+claimIds.size();
								// Call Trigger Insertion API
								boolean flag=utilTable.insertTOP60Trigger(claimIds, Long.valueOf(coCorrespondence.getCaseAppNumber()).longValue(), 
										coCorrespondence.getIndvId(), generateNCH0057TriggerDate(coCorrespondence), jobId);
								CoDebugger.debugInformation("insertTOP60Trigger Complete::"+flag);
								top60SuccessCount++;
							}else{
								CoDebugger.debugInformation("CLAIM ID EMPTY LIST:::Empty LIST Size 0000-NO Content");	
							}
						}catch(Exception e){
							top60FailCount++;
							CoDebugger.debugException(e.getMessage(),e);
							CoDebugger.debugInformation("Fail Count Incremented. Exception while invoking assembler for job:");
						}
					}
					/**
					 * ------------------ TOP Trigger Logic--END ----------------
					 */
					
					
					
					/**
					 * ------------------ ALERT 502 Trigger Logic--BEGIN ----------------
					 */
					
					
					
					if(coRequestCargo.getT1DocId() != null && (coRequestCargo.getT1DocId().equalsIgnoreCase(CoConstants.NDHCGNF15))){
						docIds=CoConstants.NDHCGNF15;
						alert502ReadCount++;
						CoDebugger.debugInformation("Alert 502 Trigger Insertion started for: "+coRequestCargo.getCaseNum());

						try{
							generateAlert(coRequestCargo.getCaseNum(),  CoConstants.REMINDER_TO_REPORT_CHANGES_ALERT_ID, alert502firstWorkingDayOfNextMonth);
							alert502SuccessCount++;
							CoDebugger.debugInformation("Alert 502 Trigger Insertion complete for: "+coRequestCargo.getCaseNum());

						}catch(Exception e){
							alert502FailCount++;
							CoDebugger.debugException(e.getMessage(),e);
							CoDebugger.debugInformation("Alert 502 Fail Count Incremented. Exception while invoking assembler for job:");
						}
					
					}
					/**
					 * ------------------ ALERT 502 Trigger Logic--END ----------------
					 */
					/** ------------------ Task 381 Trigger Logic--BEGIN ----------------
	                    */
	                    if(coRequestCargo.getT1DocId() != null && (coRequestCargo.getT1DocId().equalsIgnoreCase(CoConstants.NDFSN125))){
	                    	CoDebugger.debugInformation("Inside If block to create task 381");
	                          try {
	                          long caseNum = coRequestCargo.getCaseNum();
	                          List<Long> taskList = new ArrayList<Long>();
	                          FwDate fDate = null;
	                          Map<String, Object> taskParams = new HashMap<>();
	                          List placeHolderVal = new ArrayList<>();
	                          placeHolderVal.add(String.valueOf(caseNum));
	                          
	                          Timestamp nextDay = null;
		                      String mailDt = null;
		                      nextDay = ALSOPUtil.addDays(coCorrespondence.getGenerateDate(), 1);
		                      CoDebugger.debugInformation("coCorrespondence getGenerateDate " + nextDay.toString());
		              		  nextDay = ALSOPUtil.adjustToWorkingDay(nextDay, 1);
		              		  CoDebugger.debugInformation("Next working day --- " + nextDay.toString());
		                          
		                      DateFormat dtFormat = new SimpleDateFormat("MM/dd/yyyy");
		                      mailDt =  dtFormat.format(nextDay); 
		                      CoDebugger.debugInformation("Mailing date or task effective date --- " + mailDt);  
	                          
	                          Timestamp mailDtTimeStamp=ALSOPUtil.addDays(ALSOPUtil.getTSfromString(mailDt), 10);
	                  		  CoDebugger.debugInformation("mailDtTimeStamp "+mailDtTimeStamp);
	                  		  String mailDtString=ALSOPUtil.getStringFromTS(mailDtTimeStamp);
	                  		  CoDebugger.debugInformation("mailDtString "+mailDtString);
	                          
	                          placeHolderVal.add(mailDtString);
	                          CoDebugger.debugInformation("place holder list values" + placeHolderVal);
	                          
	                          taskParams.put(TaskConstants.TASK_ID, CoConstants.TASK_381);
	                          
	                         // The effective begin date is the print date
	          				 taskParams.put(TaskConstants.EFFECTIVE_BEGIN_DATE,ALSOPUtil.getTSfromString(mailDt));
	          				 
	          				taskParams.put(TaskConstants.CASE_APP_NUMBER,coRequestCargo.getCaseNum());

	          				 // The due date is print date plus 10 days
	          				 taskParams.put(TaskConstants.DUE_DATE,
	          						ALSOPUtil.addDays(ALSOPUtil.getTSfromString(mailDt),10));
	                          
	                          taskParams.put(TaskConstants.TASK_DESCRIPTION_HOLDERS, placeHolderVal);
	                          
	                          fDate = FwCalendar.getInstance().getDate();
	                   		  Timestamp currenttimestamp = fDate.getTimestamp();
	                   		  CoDebugger.debugInformation("currenttimestamp "+currenttimestamp);
	                          TmITasks taskManager = TmTaskManager.getInstance();
	                          
	                          CoDebugger.debugInformation("Next day --- " + nextDay);
	                          TmTaskStatusCargo[] tmcargoarray = CoTaskCreationUtil.checkTaskExists(
	                        		  coCorrespondence.getGenerateDate(), String.valueOf(caseNum),
	                  				CoConstants.TASK_381);
	                  		  CoDebugger.debugInformation("Getting cargo details in get task detail" + tmcargoarray.length);
	                  		if(null!=tmcargoarray && tmcargoarray.length>0) {
	                  			CoDebugger.debugInformation("tmcargoarray seq num " + tmcargoarray[0].getTaskSeqNum());
	                  		  }
	                          
	                  		if (null == tmcargoarray || tmcargoarray.length ==0) {
	                  			taskList= taskManager.createTasks(taskParams);
		                  		CoDebugger.debugInformation("Task Created" + taskList.size());
		        				CoDebugger.debugInformation("Task List Details" + taskList.get(0));
		                        CoDebugger.debugInformation("Task 381 Trigger Insertion complete for: "+coRequestCargo.getCaseNum());
	                  		}
	                          }
	                          catch(Exception ex){
	                                 CoDebugger.debugException(ex.getMessage(),ex);
	                                 CoDebugger.debugInformation("Task 381 Trigger Insertion Failed.");
	       
	                          }
	                    }
	                    /** ------------------ Task 381 Trigger Logic--END----------------
	                     */
				}
			}
		} catch (Exception e) {
			/** added by animagarwal to add exceptions to FW_BATCH_EXCEPTIONS */
			writeException("Exception while processing triggers for: " + jobId, "...ABORTING JOB " + jobId, e, true);
			writeCoLog("Starting Co Log ..", coLogFile);
			throw new CoException("Exception while processing triggers for: " + jobId +  "...ABORTING JOB " + e);
		}finally {
			/** generate the summary report */
			if(docIds != null && (docIds.equalsIgnoreCase(CoConstants.NDHCREN17) || docIds.equalsIgnoreCase(CoConstants.NDMAELF23))){
				CoDebugger.debugInformation(CoConstants.TRIGGER_BATCH_SUMMARY + " ReadCount ---> " + recordCounter);
				CoDebugger.debugInformation(CoConstants.TRIGGER_BATCH_SUMMARY + " SuccessCount ---> " + successCounter);
				CoDebugger.debugInformation(CoConstants.TRIGGER_BATCH_SUMMARY + " FailureCount ---> " + failureCounter);
				CoDebugger.debugInformation(CoConstants.NOTICE_BATCH_SUMMARY + " ReadCount ---> " + readCount);
				CoDebugger.debugInformation(CoConstants.NOTICE_BATCH_SUMMARY + " SuccessCount ---> " + successCount);
				CoDebugger.debugInformation(CoConstants.NOTICE_BATCH_SUMMARY + " FailureCount ---> " + failCount);	
				CoDebugger.debugInformation(CoConstants.NOTICE_BATCH_SUMMARY + " BlankNoticeCount ---> " + blankNoticeCounter);	
				generateSummaryReport(CoConstants.TRIGGER_BATCH_SUMMARY, recordCounter, successCounter, failureCounter, CoConstants.NOTICE_BATCH_SUMMARY, readCount, successCount, failCount, blankNoticeCounter);
			}else if(docIds != null && (docIds.equalsIgnoreCase(CoConstants.DOCID_NCH0057))){
				CoDebugger.debugInformation(CoConstants.TOP_IN_BATCH_SUMMARY + " ReadCount ---> " + top60ReadCount);
				CoDebugger.debugInformation(CoConstants.TOP_IN_BATCH_SUMMARY + " SuccessCount ---> " + top60SuccessCount);
				CoDebugger.debugInformation(CoConstants.TOP_IN_BATCH_SUMMARY + " FailureCount ---> " + top60FailCount);
				generateSummaryReport(CoConstants.TOP_IN_BATCH_SUMMARY, top60ReadCount, top60SuccessCount, top60FailCount, blankNoticeCounter);
				
			}else if(docIds != null && (docIds.equalsIgnoreCase(CoConstants.NDHCGNF15))){
				CoDebugger.debugInformation(CoConstants.NOTICE_BATCH_SUMMARY + " ReadCount ---> " + readCount);
				CoDebugger.debugInformation(CoConstants.NOTICE_BATCH_SUMMARY + " SuccessCount ---> " + successCount);
				CoDebugger.debugInformation(CoConstants.NOTICE_BATCH_SUMMARY + " FailureCount ---> " + failCount);	
				CoDebugger.debugInformation(CoConstants.NOTICE_BATCH_SUMMARY + " BlankNoticeCount ---> " + blankNoticeCounter);	
				CoDebugger.debugInformation(CoConstants.ALERT_502_BATCH_SUMMARY + " ReadCount ---> " + alert502ReadCount);
				CoDebugger.debugInformation(CoConstants.ALERT_502_BATCH_SUMMARY + " SuccessCount ---> " + alert502SuccessCount);
				CoDebugger.debugInformation(CoConstants.ALERT_502_BATCH_SUMMARY + " FailureCount ---> " + alert502FailCount);
				generateSummaryReport(CoConstants.ALERT_502_BATCH_SUMMARY, alert502ReadCount, alert502SuccessCount, alert502FailCount, CoConstants.NOTICE_BATCH_SUMMARY, readCount, successCount, failCount, blankNoticeCounter);

			
			}else{
				CoDebugger.debugInformation(CoConstants.NOTICE_BATCH_SUMMARY + " ReadCount ---> " + readCount);
				CoDebugger.debugInformation(CoConstants.NOTICE_BATCH_SUMMARY + " SuccessCount ---> " + successCount);
				CoDebugger.debugInformation(CoConstants.NOTICE_BATCH_SUMMARY + " FailureCount ---> " + failCount);
				CoDebugger.debugInformation(CoConstants.NOTICE_BATCH_SUMMARY + " BlankNoticeCount ---> " + blankNoticeCounter);
				generateSummaryReport(CoConstants.NOTICE_BATCH_SUMMARY, readCount, successCount, failCount, blankNoticeCounter);		
			}
		}
		return 0;
	}
	

	/**
	 * Method to get DocName from jobId.
	 * Should not be overriden
	 * @return String
	 */

	public String getDocNameFromJobID(String jobId){
		String docId=getDocIDsTobeProcessed(jobId);
		String docName=CoConstants.EMPTY_STRING;
		if(null!=docId){
		 CoMasterCargo[] coMasterCargo;
		try {
			coMasterCargo = ( CoMasterCargo[])coDAOServices.getCoMasterDataByDocId(docId);
			if(coMasterCargo!=null && coMasterCargo.length>0 && coMasterCargo[0]!=null) {
				  docName=coMasterCargo[0].getDocName();
			  }
		   } catch (CoException e) {
			CoDebugger.debugException("Exception in getting DocName",e);
		  }
		}
		return docName == null || docName.length() == 0 ? null: docName;
	}

	/**
	 * Main processing done here.
	 * Should not be overriden
	 * @return int
	 */
	@SuppressWarnings("rawtypes")
	protected int processBatch() throws Exception {
		/** gets logical file name **/
		logicalFileName = getLogicalFileName();
		/** initialize file handles **/
		initializeCoFileHandler();

		/** Call the method to read the request table **/
		VCoRequestCargo[] coReq = null;
		long readCount = 0;
		long successCount = 0;
		StringBuffer printStringBuf = new StringBuffer();
		List<COCorrespondence> triggers = new ArrayList<COCorrespondence>();
		List<String> printStrings = new ArrayList<String>();
		List<List<CoRequestRecipientsCargo>> allRecipientCargo = new ArrayList<List<CoRequestRecipientsCargo>>(); /** multiple rec change */
		List<List> allPrintString = new ArrayList<List>(); /*multiple rec change */
		DocumentBuilder documentBuilder = null;
		COCorrespondence coTrgrBean = null;
		CoRequestRecipientsCargo coReqRecipientCargo = null;
		/** BRGUS00138388 - bhattaj  */
		boolean exceptTrigFlag = false;
		try {
			coConnection = tbc.getConnection();
			/** passed to CoDAOServices for the document Build process */
			coConnection.setAutoCommit(false);
			coDAOServices.setConnection(coConnection);
		} catch (Exception e) {

			return this.handleBatchException(e, 1, null, null);
		}
		try {
			coReq = this.getTriggers(coConnection);
		} catch (Exception e) {
			CoDebugger.debugException("Exception in getting trigger", e);
			CoDebugger.debugInformation(e.getMessage());
			throw new CoException("CoBatch --> getTriggers Failed");
		}
		/** The array contains the correspondence objects	*/
		long coReqSize = 0;
		if(coReq != null) {
			coReqSize = coReq.length;
		}
		CoDebugger.debugInformation("Job: "+ jobId 
				+ ", Parallel Run Id: "+ this.parallelRunId 
				+ "  Selected trigger size for this instance:  " + coReqSize);


		List printStringList = null;
		List coRptSeqList = null;
		int multipleRecSize = 0;
		List<CoRequestRecipientsCargo> coReqRecipientCargoList = null;
		boolean shouldUpdateReqHistoryBlob = false;
		boolean exceptionFlag = false;
		boolean bannerAdded = false;
		int rtrn = -1;

		boolean updateTriggerAsErrored = false;
		String currentCaseProviderAppString = CoConstants.EMPTY_STRING;
		String oldCaseProviderAppString = CoConstants.EMPTY_STRING;
		String currentCaseProvAppNum = CoConstants.EMPTY_STRING;
		String currentDocId = CoConstants.EMPTY_STRING;
		String oldCaseProvAppNum = CoConstants.EMPTY_STRING;
		String oldDocId = CoConstants.EMPTY_STRING;
		String barCode2o5String = CoConstants.EMPTY_STRING;
		/**		 BRGUS00110572 - thatiparthis  - Begin */
		String oldVerfCoverSheetString = null;
		String currentVerfCoverSheetString = null;
		boolean dynamicVerfCoverSheetRequired = false;

		long oldCoReqSeq = 0;
		long currentCoReqSeq = 0;
		/** BRGUS00140997 - Bhattaj - Verf Coversheet required for DHS-1514 Notice also
		    BRGUS00110572 - thatiparthis  - Begin */
		boolean isVerification = (CoBatchConstants.CO_PVERF_DLY.equals(jobId) 
				|| CoBatchConstants.CO_P1514_DLY.equals(jobId));

		for (int i = 0; i < coReqSize; i++) {

			currentCaseProviderAppString = CoConstants.EMPTY_STRING;
			currentCaseProvAppNum = CoConstants.EMPTY_STRING;
			currentDocId = CoConstants.EMPTY_STRING;
			currentCoReqSeq = 0;
			exceptTrigFlag = false;
			updateTriggerAsErrored = false;
			try {
				if ((coConnection == null) || (coConnection.isClosed())) {
					coConnection = tbc.getConnection();
					coConnection.setAutoCommit(false);
					coDAOServices.setConnection(coConnection);
				}
			} catch (Exception e) {
				writeException(
						"Fatal error connection closed --could not obtain a new connection",
						"Could not refresh the connection ...ABORTING JOB "+ jobId ,
						null,
						true);
				CoDebugger.debugInformation(" before aborting the job : " + jobId);
				CoDebugger.debugException("before aborting the job:", e);
				try{
					if(coConnection == null){
						CoDebugger.debugInformation("connection is null : "+ jobId);
					}else if(coConnection.isClosed()){
						CoDebugger.debugInformation("connection is closed : "+ jobId);
					}					
				}catch(SQLException f){
					CoDebugger.debugInformation(" sqlexception while printing connection status and before aborting the job :1");
					CoDebugger.debugInformation(f.getMessage());
					CoDebugger.debugException("sqlexception while printing connection status and before aborting the job", f);
				}				
				abortJob();
				CoDebugger.debugInformation(" after aborting the job : "+ jobId);
				CoDebugger.debugInformation(e.getMessage());
			}
			tbc.savepoint(coConnection,SAVE_POINT);
			coTrgrBean = new COCorrespondence();
			readCount++;
			CoDebugger.debugInformation("Trigger read count.........." + readCount);
			if (coLogFile != null) {
				writeCoLog("Trigger read count.........." + readCount, coLogFile);
			}
			COPrintPreviewHelper.mapCargoToCo(coReq[i], coTrgrBean);
			/** set runMode as 'B' */
			coTrgrBean.setRunMode(CoConstants.BATCH_CD);

			/** BRGUS00110572 - thatiparthis  - Begin */
			if (isVerification){
				coTrgrBean.setCoBatchJobId(CoBatchConstants.CO_PVERF_DLY);
			}

			/** BRGUS00110572 - thatiparthis  - End */
			coDAOServices.setIsOnline(false);
			printStringList = null;
			coRptSeqList = null;
			try {
				/** connection set in CoDAOServices within this method */
				updatingTrigger = isUpdatingTrigger(coTrgrBean.getDocId());
				if (coLogFile != null) {
					writeCoLog(
							"Case : "+ coTrgrBean.getCaseAppNumber()
							+ " ReqSeq : " + coTrgrBean.getCoReqSeq(), coLogFile);
				}
				documentBuilder = new DocumentBuilder(coDAOServices);

				allRecipientCargo.clear();
				allPrintString.clear();

				printStringList = documentBuilder.buildDocument(coTrgrBean);

				//BRGUS00108565 - bhattaj - set printMode as 'B' when printed in Batch
				coTrgrBean.setPrintMode(CoConstants.CHAR_B);

				//multiple rec change
				coRptSeqList = documentBuilder.getCoRptSeqList(); 
				triggers.add(coTrgrBean);
			} catch (CoException e) {
				//increase failureCount
				failureCount++;
				updateTriggerAsErrored = true;
				exceptionTriggers.add(coTrgrBean);
				this.handleBatchException(e, 4, printStringBuf, coTrgrBean);
			} catch (Exception e) {
				if(updateTriggerAsErrored == false) {
					exceptionTriggers.add(coTrgrBean);
					failureCount++;
				}
				updateTriggerAsErrored = true;
				this.handleBatchException(e, 5, printStringBuf, coTrgrBean);
			} 
			//NG-4597: Remove all CRITICAL sonar issues
			//NG-6427 Sonar Issues Blocker
			multipleRecSize =
					coRptSeqList != null
					&& printStringList != null
					&& coRptSeqList.size() == printStringList.size()
					? coRptSeqList.size()
							: 0;

					if (coRptSeqList != null
							&& printStringList != null
							&& coRptSeqList.size() != printStringList.size()) {

						exceptionTriggers.add(coTrgrBean);
						writeException(
								"PrintString and recipient size mismatch",
								coTrgrBean.getCaseAppNumber()
								+ " : "
								+ coTrgrBean.getCoReqSeq(),
								new CoException(
										"PrintString and recipient size mismatch",
										000),
										false);
					}
					coReqRecipientCargoList = new ArrayList<CoRequestRecipientsCargo>();
					allPrintString.add((ArrayList)printStringList);

					for (int multipleRec = 0;
							multipleRec < multipleRecSize;
							multipleRec++) {
						coReqRecipientCargo = new CoRequestRecipientsCargo();
						coReqRecipientCargo.setCoReqSeq(coTrgrBean.getCoReqSeq());
						coReqRecipientCargo.setCoRptSeq(
								Long.parseLong((String) coRptSeqList.get(multipleRec)));
						coReqRecipientCargo.setPrintType(CoConstants.PRINT_MODE_BATCH);
						coReqRecipientCargoList.add(coReqRecipientCargo);

					}
					allRecipientCargo.add(coReqRecipientCargoList);

					for (int index = 0; index < multipleRecSize; index++) { 

						CoDebugger.debugInformation(
								"Connection id...after build document  for read Count "
										+ readCount
										+ " "
										+ coDAOServices.getConnection());
						writeCoLog("Above Record Successful..", coLogFile);
						//get the printString for current recipient
						currentCaseProviderAppString = (String) printStringList.get(index);
						//get current case/app/provider id and doc id
						currentDocId = coTrgrBean.getDocId();
						//current co_req_seq
						currentCoReqSeq = coTrgrBean.getCoReqSeq();

						if(ProviderNotices.providerNoticeDocList.contains(currentDocId)) {
							currentCaseProvAppNum = String.valueOf(coTrgrBean.getProviderId());
							if(currentCaseProvAppNum != null && ("0").equalsIgnoreCase(currentCaseProvAppNum)) {
								currentCaseProvAppNum = coTrgrBean.getCaseAppNumber();
							}
						} else {
							//BRGUS00077376 - bhattaj - read Provider Id from MiscParameters for DHS-022-A
							if(CoConstants.FXX160_DOC_ID.equals(currentDocId)) {
								currentCaseProvAppNum = coTrgrBean.getMiscParameters();
							} else if("FXX089".equals(currentDocId)){ //Removed from Provider Docs CR 20172
								currentCaseProvAppNum = coTrgrBean.getChipAppNum();
							} else {
								currentCaseProvAppNum = coTrgrBean.getCaseAppNumber();
							}
						}
						successCount++;
						shouldUpdateReqHistoryBlob = coTrgrBean.getUpdateRequestHistoryBlob();
						if (shouldUpdateReqHistoryBlob
								&& index == (multipleRecSize - 1)) {
							printStrings.add(printStringBuf.toString());
						}
					}
					//updating Co History for Errored triggers
					if (updateTriggerAsErrored || exceptionTriggers.size() != 0) {
						updateTriggerAsErrored = false;
						try {
							exceptionFlag =
									coDAOServices.updateExceptionRecords(
											exceptionTriggers,
											coConnection);
							if ((coConnection == null) || (coConnection.isClosed())) {
								//abnormal condition
								abortJob();
							}
							if (exceptionFlag) {
								writeCoLog(	"After Commit Exception record, co_req_seq: "+ coTrgrBean.getCoReqSeq()+ " " + coConnection, coLogFile);
							} else { 


								try {
									tbc.rollback(coConnection,SAVE_POINT);
								}catch(Exception ex) {
									CoDebugger.debugException("rollback() failed for the Job_ID", ex);
									CoDebugger.debugInformation(ex.getMessage());
									throw new CoException("rollback() failed for the Job_ID..."+jobId);
									
								}
								//write exception 
								CoDebugger.debugInformation("ERROR updating history with failed triggers," +
										" Could not update Trigger Tables with Errrored Triggers ...");
								
							}
							exceptionTriggers.clear();
						} catch (SQLException ex) {
							exceptionTriggers.clear();
							if (ex.getErrorCode() == 17009) {
								CoDebugger.debugInformation(
										"17009 error occured--1-CoReqSeq = "
												+ coTrgrBean.getCoReqSeq());
							} else {
								this.handleBatchException(
										ex,
										16,
										printStringBuf,
										coTrgrBean);
							}
						} catch (Exception e) {
							this.handleBatchException(
									e,
									16,
									printStringBuf,
									coTrgrBean);
						}
					}

					/** updating Co History for Success trigger */
					if (triggers.size() != 0) {
						CoDebugger.debugInformation(
								"Commit Size reached for Success trigger"
										+ triggers.size()
										+ " CoDAOSErvices Conn "
										+ coDAOServices.getConnection()
										+ " Con "
										+ coConnection);

						rtrn = -1;
						try {
							rtrn =
									coDAOServices.updateHistory(
											triggers,
											printStrings,
											coConnection,
											allRecipientCargo,
											allPrintString);
						} catch (CoException ex) {
							CoDebugger.debugInformation(ex.getMessage());
							CoDebugger.debugException("Update History", ex);
							try {
								tbc.rollback(coConnection, SAVE_POINT);
							} catch (Exception e) {
								CoDebugger.debugException("rollback() failed for the Job_ID" +jobId, e);
								CoDebugger.debugInformation(e.getMessage());
								throw new CoException("rollback() failed for the Job_ID..."+jobId);

							}
							writeException(
									"ERROR updating History-Detail-Recipient within for "
											+ coTrgrBean.getCoReqSeq(),
											"Could not update Trigger Tables...procedure 1 for "
													+ coTrgrBean.getCoReqSeq(),
													null,
													false);
							//BRGUS00138388 - bhattaj 
							//exception the exceptioned out record
							//BRGUS00139179 - bhattaj - As per QA suggestion, moving up
							tbc.savepoint(coConnection,SAVE_POINT);
							try {
								//add exceptioned out record
								exceptionTriggers.add(triggers.get(0));
								coDAOServices.updateExceptionRecords(exceptionTriggers,
										coConnection);
								//clear exceptioned record
								exceptionTriggers.clear();
								CoDebugger.debugWarning("Updating exceptioned out record as E..");
								exceptTrigFlag = true;
							} catch (Exception expt) {
								CoDebugger.debugException("Updating exceptioned out record as E..", expt);
								CoDebugger.debugInformation(expt.getMessage());
								tbc.rollback(coConnection, SAVE_POINT);
							}
						}
						if ((coConnection == null) || (coConnection.isClosed())) {
							//abnormal condition
							abortJob();
						}
						if (rtrn == 0) {
							writeCoLog(
									"After Commit Success Trigger inside for ..con: "
											+ coConnection,
											coLogFile);
						} else {
							//should not happen - perform cleanup and exit
							try {
								//BRGUS00138388 - bhattaj 
								if(!exceptTrigFlag) {
									tbc.rollback(coConnection,SAVE_POINT);
								}
							}catch(Exception ex) {
								CoDebugger.debugException("rollback() failed for the Job_ID..."+jobId, ex);
								CoDebugger.debugInformation(ex.getMessage());
								throw new CoException("rollback() failed for the Job_ID..."+jobId);
							}
							writeCoLog(
									"After Rollback Triggers inside loop for "
											+ coTrgrBean.getCoReqSeq(),
											coLogFile);
							writeException(
									"ERROR updating History-Detail-Recipient within for "
											+ coTrgrBean.getCoReqSeq(),
											"Could not update Trigger Tables...for "
													+ coTrgrBean.getCoReqSeq(),
													null,
													false);
						}
						triggers.clear();
						printStrings.clear();
					}
					// Commit the Connection if successful start writing feed file else throw the exception
					try {
						logicalCommit(coConnection);
					} catch(Exception ex){
						CoDebugger.debugException("commit() failed for the Job_ID..."+jobId, ex);
						CoDebugger.debugInformation(ex.getMessage());
						throw new CoException("commit() failed for the Job_ID..."+jobId);
					}
					//			 BRGUS00110572 - thatiparthis  - Begin
					if (isVerification) {
						if (currentCaseProviderAppString != null
								&& currentCaseProviderAppString.trim().length() > 0) {
							currentVerfCoverSheetString = getVerfCoverSheetString(
									currentCaseProviderAppString,
									currentCaseProvAppNum, coTrgrBean);
						}
					}
					//			 BRGUS00110572 - thatiparthis  - End		
					// start of formatting feed file
					if((rtrn == 0) && (!bannerAdded) && (currentCaseProviderAppString != null) 
							&& (currentCaseProviderAppString.trim().length() > 0)) {
						// add banner page only
						writeFlatFile(getBannerPageDetails(new StringBuffer("")).toString());
						bannerAdded = true;
						oldCaseProviderAppString = currentCaseProviderAppString;
						oldCaseProvAppNum = currentCaseProvAppNum;
						oldDocId = currentDocId;
						oldCoReqSeq = currentCoReqSeq; 

						//				 BRGUS00110572 - thatiparthis  - Begin
						if(isVerification) {
							oldVerfCoverSheetString = currentVerfCoverSheetString;
							writeFlatFile(oldVerfCoverSheetString);
						}
					}
					if(i == coReqSize - 1) {
						//add last two records and trailer page also
						StringBuffer lastTwoRecordsBuf = new StringBuffer("");
						if(bannerAdded && (oldCaseProviderAppString != null) 
								&& (oldCaseProviderAppString.trim().length() > 0)
								&& (oldCoReqSeq != currentCoReqSeq)) {

							barCode2o5String = getFeedStringWith2o5(false,
									currentCaseProvAppNum, 
									oldCaseProvAppNum,
									oldDocId,
									oldCaseProviderAppString);

							lastTwoRecordsBuf.append(
									CoConstants.RETURN_NEWLINE_CHAR + barCode2o5String 
									+ CoConstants.RETURN_NEWLINE_CHAR + "***");

							//					 BRGUS00110572 - thatiparthis  - Begin
							if(isVerification) {
								dynamicVerfCoverSheetRequired = !(currentCaseProvAppNum.equals(oldCaseProvAppNum));
								if(dynamicVerfCoverSheetRequired){
									lastTwoRecordsBuf.append(currentVerfCoverSheetString);
								} 
							}
							//					 BRGUS00110572 - thatiparthis  - End
						}

						if(bannerAdded && currentCaseProviderAppString != null 
								&& currentCaseProviderAppString.trim().length() > 0) {

							barCode2o5String = getFeedStringWith2o5(true,
									currentCaseProvAppNum, 
									currentCaseProvAppNum,//consider last caseProvAppNum
									currentDocId,
									currentCaseProviderAppString);

							lastTwoRecordsBuf.append(
									CoConstants.RETURN_NEWLINE_CHAR + barCode2o5String 
									+ CoConstants.RETURN_NEWLINE_CHAR + "***");

						}
						if(bannerAdded) {
							writeFlatFile(getTrailerPageDetails(lastTwoRecordsBuf).toString());
							try {
								//close file handler
								if(coFileHandler != null) {
									coFileHandler.close();
								}
							}catch(IOException e){
								CoDebugger.debugException("Error closing file",e);
							}
						}
					} else if(bannerAdded && (rtrn == 0)
							&& (currentCaseProviderAppString != null) 
							&& (currentCaseProviderAppString.trim().length() > 0)
							&& (oldCoReqSeq != currentCoReqSeq)){

						barCode2o5String = getFeedStringWith2o5(false,
								currentCaseProvAppNum, 
								oldCaseProvAppNum,
								oldDocId,
								oldCaseProviderAppString);

						writeFlatFile(barCode2o5String + CoConstants.RETURN_NEWLINE_CHAR + "***"); 

						//				 BRGUS00110572 - thatiparthis  - Begin
						if(isVerification) {
							dynamicVerfCoverSheetRequired = !(currentCaseProvAppNum.equals(oldCaseProvAppNum));
							if(dynamicVerfCoverSheetRequired){
								writeFlatFile(currentVerfCoverSheetString) ;
							}
						} 
						//				 BRGUS00110572 - thatiparthis  - End
						oldCaseProviderAppString = currentCaseProviderAppString;
						oldCaseProvAppNum = currentCaseProvAppNum;
						oldDocId = currentDocId;
						oldCoReqSeq = currentCoReqSeq;

						//				 BRGUS00110572 - thatiparthis  - Begin
						if(isVerification) {
							oldVerfCoverSheetString = currentVerfCoverSheetString;
						}
					}// end of formatting feed file

					if (!(("").equals(exceptionRecordBuf.toString()))) {
						// write into Fw_Batch_Exceptions if exception occurred
						this.handleBatchException(null, 13, printStringBuf, coTrgrBean);
					}
		} //LOOP ENDS HERE

		// Code had been added to get the record Count of the File
		// Created by job to Generate the CO Daily batch Checklist Report since zero
		// byte files will be not transferred to CPC.


		try {
			if (!"Windows XP".equalsIgnoreCase(System.getProperty("os.name")))
			{
				long recordCount = tbc.getFileRecordCount(absoluteFileName);
				//BRGUS00122271 - bhattaj - passing parallel run Id

				tbc.setOutputFileRecordCount(logicalFileName, asOfDate,
						recordCount, Integer.parseInt(parallelRunId));
			}
		}catch (Exception e){
			CoDebugger.debugException("Record count of file failed", e);
			CoDebugger.debugInformation(e.getMessage());
		}

		try {
			//finally commit to the data base
			coConnection.commit();
		} catch (java.sql.SQLException e) {
			this.handleBatchException(e, 18, printStringBuf, null);
		}
		//generate the report 
		generateReport(readCount, readCount, successCount, failureCount);

		//Close the Co Log file.....
		if (coLogFile != null) {
			writeCoLog("Ending Log...", coLogFile);
			coLogFile = null;
		}
		cleanUpCachedResources();
		return 0;
	}

	/**
	 *method to handle all exception in co batch
	 * @param e Throwable
	 * @param expID int
	 * @param printStringBuf StringBuffer
	 * @param coTrgrBean COCorrespondence
	 * @return int
	 */
	protected int handleBatchException(
			Throwable e,
			int expID,
			StringBuffer printStringBuf,
			COCorrespondence coTrgrBean) {
		String msgText = "";
		int errorCode = 0;
		int retCd = 0;
		// Kannegantik BRGUS00112795 - To find out if the doc_id is in the list 
		//of the doc_ids for which the exception has to be logged as info
		boolean isExceptionLoggedAsInfo = isExceptionLoggedAsInfo(coTrgrBean.getDocId());
		switch (expID) {
		case 1 :

			exceptionRecordBuf.delete(0, exceptionRecordBuf.length());
			//clear the buffer
			exceptionRecordBuf
			.append("Exception while getting the connection in processBatch")
			.append(e + " :" + e.getMessage());
			CoDebugger.logWithLevel(
					"Exception while getting the connection in processBatch",
					ILog.FATAL);
			retCd = -1;
			break;
		case 2 :

			writeException(
					"getRequestCargoFromResultSet",
					"Exception retreiving cargo from rs "
							+ e
							+ " :"
							+ e.getMessage(),
							e,
							true);
			break;

		case 3 :

			writeException(
					"getRequestCargoFromResultSet",
					"Result Set is null cannot proceed..",
					null,
					true);
			break;
		case 4 :
			//this flag is set for each trigger
			if (updatingTrigger) {
				try {
					tbc.rollback(coConnection,SAVE_POINT);
				} catch (Exception ex) {
					writeException(
							"Fatal Exception..Aborting..",
							"Could not roll back update Trigger "
									+ ex
									+ " :"
									+ ex.getMessage(),
									ex,
									true);
				}
			}
			writeCoLog("Above Record Failed....", coLogFile);

			CoDebugger.debugWarning(
					"Build Document Error ..processing readCount "
							+ e
							+ " "
							+ e.getMessage());
			printStringBuf.delete(0, printStringBuf.length());
			//clear the buffer
			printStringBuf.append(
					"99999 : Exception received from PrintString - ").append(
							e.toString());
			errorCode =
					e instanceof CoException
					? ((CoException) e).getErrorCode()
							: 0;
					{
						exceptionRecordSummary.append(
								"CoReqSeq:"
										+ coTrgrBean.getCoReqSeq()
										+ " :case app num : "
										+ coTrgrBean.getCaseAppNumber()
										+ ": co dt Seq :"
										+ coTrgrBean.getCoDetSeq());
						exceptionRecordBuf.append(e);
						exceptionRecordBuf.append(" :");
						exceptionRecordBuf.append(e.getMessage());
					}
					if (errorCode == 0) {
						CoDebugger.logWithLevel(
								"CoException with error code 0"
										+ e
										+ " "
										+ e.getMessage(),
										ILog.ERROR);
						if (coLogFile != null) {
							writeCoLog(
									"CoException with error code 0"
											+ e
											+ " "
											+ e.getMessage(),
											coLogFile);
						}
						errorCode = 16376;
					}
					successfull = false;
					break;
		case 5 :
			//this flag is set for each trigger
			if (updatingTrigger) {
				try {
					tbc.rollback(coConnection,SAVE_POINT);
				} catch (Exception ex) {
					writeException(
							"Fatal Exception..Aborting..",
							"Could not roll back update Trigger "
									+ ex
									+ " :"
									+ ex.getMessage(),
									ex,
									true);
				}
			}
			exceptionTriggers.add(coTrgrBean);
			errorCode = 16376;
			CoDebugger.logWithLevel(
					"Exception from buildDocument " + e + " " + e.getMessage(),
					ILog.ERROR);
			if (coLogFile != null) {
				writeCoLog(
						"Exception from buildDocument "
								+ e
								+ " "
								+ e.getMessage(),
								coLogFile);
			}
			exceptionRecordSummary.append(
					"CoReqSeq:"
							+ coTrgrBean.getCoReqSeq()
							+ " :case app num : "
							+ coTrgrBean.getCaseAppNumber()
							+ ": co dt Seq :"
							+ coTrgrBean.getCoDetSeq());
			exceptionRecordBuf
			.append("Unexpected Exception ..")
			.append(e)
			.append(" ")
			.append(e.getMessage());
			successfull = false;
			break;
		case 6 :
			//this flag is set for each trigger
			if (updatingTrigger) {
				try {
					tbc.rollback(coConnection,SAVE_POINT);
				} catch (Exception ex) {
					writeException(
							"Fatal Exception..Aborting..",
							"Could not roll back update Trigger "
									+ ex
									+ " :"
									+ ex.getMessage(),
									ex,
									true);
				}
			}
			exceptionTriggers.add(coTrgrBean);
			successfull = false;
			errorCode = 16376;
			CoDebugger.logWithLevel(
					"Exception from buildDocument " + e + " " + e.getMessage(),
					ILog.ERROR);
			if (coLogFile != null) {
				writeCoLog(
						"Exception from buildDocument "
								+ e
								+ " "
								+ e.getMessage(),
								coLogFile);
			}
			exceptionRecordSummary.append(
					"CoReqSeq:"
							+ coTrgrBean.getCoReqSeq()
							+ " :case app num : "
							+ coTrgrBean.getCaseAppNumber()
							+ ": co dt Seq :"
							+ coTrgrBean.getCoDetSeq());
			exceptionRecordBuf
			.append("Unexpected Exception ..")
			.append(e)
			.append(" ")
			.append(e.getMessage());

			break;
		case 7 :

			//this flag is set for each trigger
			if (updatingTrigger) {
				try {
					tbc.rollback(coConnection,SAVE_POINT);
				} catch (Exception ex) {
					writeException(
							"Fatal Exception..Aborting..",
							"Could not roll back update Trigger "
									+ ex
									+ " :"
									+ ex.getMessage(),
									ex,
									true);
				}
			}
			exceptionTriggers.add(coTrgrBean);
			successfull = false;
			errorCode = 16376;
			CoDebugger.logWithLevel(
					"Exception from buildDocument " + e + " " + e.getMessage(),
					ILog.ERROR);
			if (coLogFile != null) {
				writeCoLog(
						"Exception from buildDocument "
								+ e
								+ " "
								+ e.getMessage(),
								coLogFile);
			}

			exceptionRecordBuf
			.append("Unexpected Exception ..")
			.append(e)
			.append(" ")
			.append(e.getMessage());
			break;
		case 8 :
			//An error occurred..perform cleanup and abort.. print Stack Trace.
			//this flag is set for each trigger
			if (updatingTrigger) {
				try {
					tbc.rollback(coConnection,SAVE_POINT);
				} catch (Exception ex) {
					writeException(
							"Fatal Exception..Aborting..",
							"Could not roll back update Trigger "
									+ ex
									+ " :"
									+ ex.getMessage(),
									ex,
									true);
				}
			}
			if (coLogFile != null) {
				writeCoLog(
						"Unexpected error..Aborting after cleanup.."
								+ e
								+ " "
								+ e.getMessage(),
								coLogFile);
			}
			writeException(
					"Fatal Exception..Aborting..",
					"Look in Std err for Stack Trace.."
							+ e
							+ " :"
							+ e.getMessage(),
							e,
							true);
			break;
		case 9 :

			if (!("").equals(exceptionRecordBuf.toString().trim()))
				//for UAT Patch -- use the CoException Messages always
			{
				msgText = exceptionRecordBuf.toString();
				exceptionRecordBuf.delete(0, exceptionRecordBuf.length());
			}else {
				msgText = getErrorMessage(errorCode);
			}
			exceptionRecordBuf.delete(0, exceptionRecordBuf.length());
			//clear the buffer
			exceptionRecordBuf
			.append(String.valueOf(coTrgrBean.getCoReqSeq()))
			.append("\t")
			.append(coTrgrBean.getCaseAppNumber())
			.append("\t")
			.append(msgText);
			exceptionRecordSummary.delete(
					0,
					exceptionRecordSummary.length());
			//clear the buffer
			exceptionRecordSummary
			.append(String.valueOf(coTrgrBean.getCoReqSeq()))
			.append("\t")
			.append(coTrgrBean.getCaseAppNumber())
			.append("\t")
			.append(errorCode);
			successfull = false;
			printStringBuf.delete(0, printStringBuf.length());
			//clear the buffer
			printStringBuf.append(msgText).append(" ").append(
					printStringBuf.toString());
			break;
		case 10 :

			successfull = false;

			exceptionRecordBuf.delete(0, exceptionRecordBuf.length());
			//clear the buffer
			exceptionRecordBuf
			.append(String.valueOf(coTrgrBean.getCoReqSeq()))
			.append("\t")
			.append(coTrgrBean.getCaseAppNumber())
			.append("\t")
			.append("Error in PCL ")
			.append(e);
			exceptionRecordSummary.delete(
					0,
					exceptionRecordSummary.length());
			//clear the buffer
			exceptionRecordSummary
			.append(String.valueOf(coTrgrBean.getCoReqSeq()))
			.append("\t")
			.append(coTrgrBean.getCaseAppNumber())
			.append("\t")
			.append("Error in PCL");
			break;
		case 11 :

			msgText = getErrorMessage(330);
			exceptionRecordBuf.delete(0, exceptionRecordBuf.length());
			//clear the buffer
			exceptionRecordBuf
			.append(String.valueOf(coTrgrBean.getCoReqSeq()))
			.append("\t")
			.append(coTrgrBean.getCaseAppNumber())
			.append("\t")
			.append(msgText);
			exceptionRecordSummary.delete(
					0,
					exceptionRecordSummary.length());
			//clear the buffer
			exceptionRecordSummary
			.append(String.valueOf(coTrgrBean.getCoReqSeq()))
			.append("\t")
			.append(coTrgrBean.getCaseAppNumber())
			.append("\t")
			.append("Invalid PCL");
			if (coLogFile != null) {
				writeCoLog("BAD PCL FOR ABOVE TRIGGER..", coLogFile);
			}
			break;
		case 12 :

			msgText = getErrorMessage(335);
			exceptionRecordBuf.delete(0, exceptionRecordBuf.length());
			//clear the buffer
			exceptionRecordBuf
			.append(String.valueOf(coTrgrBean.getCoReqSeq()))
			.append("\t")
			.append(coTrgrBean.getCaseAppNumber())
			.append("\t")
			.append(msgText);
			exceptionRecordSummary.delete(
					0,
					exceptionRecordSummary.length());
			//clear the buffer
			exceptionRecordSummary
			.append(String.valueOf(coTrgrBean.getCoReqSeq()))
			.append("\t")
			.append(coTrgrBean.getCaseAppNumber())
			.append("\t")
			.append("PCL File Error");
			break;
		case 13 :
			//Kanneganik  BRGUS00112795 to write to FW_BATCH_EXCEPTIONS
			// as info 
			if(isExceptionLoggedAsInfo && (FwBatchConstants.INFO.equals(coTrgrBean.getExceptionType()))){ // COMMENT: REFACTORED THROUGH AUTOMATED APPROACH. OLD VALUE:'BatchConstants',NEW NAME:'FwBatchConstants'
				writeExceptionAsInfo(
						exceptionRecordSummary.toString(),
						exceptionRecordBuf.toString(),
						null,
						false);
			}else{// Kannegantik END
				writeException(
						exceptionRecordSummary.toString(),
						exceptionRecordBuf.toString(),
						null,
						false);
			}
			//write to FW_EXCEPTIONS and return

			msgText = ""; //reset this value
			exceptionRecordSummary.delete(
					0,
					exceptionRecordSummary.length());
			// empty the buffer 				
			exceptionRecordBuf.delete(0, exceptionRecordBuf.length());
			// empty the buffer 

			break;
		case 14 :

			writeException(
					"Fatal Exception..Aborting..operation 1",
					"Could not roll back update Trigger operation 1"
							+ e
							+ " :"
							+ e.getMessage(),
							e,
							true);
			break;
		case 15 :

			writeException(
					"Fatal Exception..Aborting..",
					"Could not roll back update Trigger "
							+ e
							+ " :"
							+ e.getMessage(),
							e,
							true);
			break;
		case 16 :
			CoDebugger.debugException(
					"Exception trying to commit/rollback "
							+ coConnection
							+ " Exception = "
							+ e,
							e);
			//write exception and exit
			writeException(
					"Exception trying to commit/rollback ",
					"Exception = " + e,
					e,
					true);
			writeCoLog(
					"Exception while updating exception records: "
							+ e
							+ "  : "
							+ coConnection,
							coLogFile);
			break;
		case 17 :
			CoDebugger.debugException(
					"Exception during update Request History and History detail tables "
							+ e,
							e);
			try {
				if (coConnection != null && !coConnection.isClosed()) {
					try {
						tbc.rollback(coConnection,SAVE_POINT);
						CoDebugger.debugInformation("After rollback");
					} catch (Exception sexc) {
						CoDebugger.debugException(
								"Could not roll back - outside for",
								sexc);
					}
				}
			} catch (SQLException se) {
				CoDebugger.debugException("updateConnection failed", se);
			}
			if (coLogFile != null) {
				writeCoLog(
						"Exception while updating trigger records outside for : "
								+ e
								+ "  : "
								+ coConnection,
								coLogFile);
			}
			writeException(
					"Exception committing Co Triggers outside for ",
					"Exception = " + e + " :" + e.getMessage(),
					e,
					true);
			break;
		case 18 :

			exceptionRecordBuf.delete(0, exceptionRecordBuf.length());
			//clear the buffer
			exceptionRecordBuf
			.append("Exception while closing the connection in processBatch ")
			.append(e + " :" + e.getMessage());
			CoDebugger.debugException(
					"Exception while closing the connection in processBatch",
					e);
			writeException(
					"Error closing connection",
					exceptionRecordBuf.toString(),
					e,
					true);
			break;
		case 19 :

			exceptionRecordBuf.delete(0, exceptionRecordBuf.length());
			//clear the buffer
			String errText = getErrorMessage(16416);
			//This is the error code for the error message from Ref - Table
			exceptionRecordBuf
			.append("Error while calling sendFileToCentralPrint() \t")
			.append(e + " :" + e.getMessage() + " \t " + errText);
			//write exception and return
			writeException(
					"Error sending file to Central Print",
					exceptionRecordBuf.toString(),
					e,
					false);
			exceptionRecordBuf.delete(0, exceptionRecordBuf.length());
			CoDebugger.debugWarning("Error thrown in sendFileToCentralPrint");
			break;
		default :
			break;
		}
		return retCd;
	}
	/**
	 *get the triggers to process
	 * will be overriden by the sub-class
	 * Not used (06/12/2003)
	 * @return VCoRequestCargo[]
	 */
	protected VCoRequestCargo[] getTriggersToProcess() {
		return null;
	}
	/**
	 *set any required parameters
	 * will be overriden by the sub-class
	 * @param coTrgrBean COCorrespondence
	 * @param successCount long
	 * @return COCorrespondence
	 */
	protected COCorrespondence setAnyRequiredParameters(
			COCorrespondence coTrgrBean,
			long successCount) {
		return null;
	}

	/**
	 * send file to central print facility
	 * to be used by sub classes
	 * Print Queue name is passed as Parameter. Hence 
	 * this method does not change in subclasses
	 * Modified on Dec 24th 2002 
	 */
	protected void sendFileToCentralPrint() {
		sendFileToCentralPrint("COCentralPrint.ksh");
	}
	/**
	 *send file to central print
	 * will not be overriden by sub-classes
	 * instead getPhysicalFileNames() would be overriden
	 * @param shellScript String
	 */
	protected void sendFileToCentralPrint(String shellScript) {
		StringBuffer exceptionRecordBuf = new StringBuffer("");
		Runtime rt = Runtime.getRuntime();
		String[] physicalFileNames = null;
		physicalFileNames = getPhysicalFileNames();
		int numberOfFiles = physicalFileNames.length;
		String finalcommand = " ";
		Process proc = null;
		int exitValue = 0;
		try {
			for (int i = 0; i < numberOfFiles; i++) {
				if (!("").equals(printQueue.trim())
						&& !("").equals(
								physicalFileNames[i].trim())) //send to Central Print only if we have valid PrintQueue and file
				{
					finalcommand =
							getFinalCommand(
									physicalFileNames[i],
									shellScript,
									printQueue);
					if(finalcommand.length()>0 && finalcommand != null){
						finalcommand = finalcommand.replace('\\', '/');
						CoDebugger.debugMessage("ND-89611 Final command :"+finalcommand);
						//The env is in Windows style
						if (coLogFile != null) {
							writeCoLog(
									"Final Command CentralPrint: " + finalcommand,
									coLogFile);
						}
						 proc = rt.exec(finalcommand);
					}
					
					proc.waitFor();
					//Check for the exit value
					exitValue = proc.exitValue();
					if (exitValue == 1) {
						writeException(
								"COCentralPrint.ksh ERROR",
								"COCentralPrint.ksh ERROR Exit Value 1 Queue may be invalid",
								null,
								false);
					} else if (exitValue == 255 && coLogFile != null) {
						writeCoLog(
								"COCentralPrint.ksh ERROR Exit Value 255  File name Does not exist",
								coLogFile);
					} else if (
							exitValue != 0 && coLogFile != null) {//Any other error
						writeCoLog(
								"COCentralPrint.ksh ERROR"
										+ " Exit Value "
										+ exitValue,
										coLogFile);
					}
					if (coLogFile != null) {
						writeCoLog("Exiting SendToCentralPrint ", coLogFile);
					}
				} else {
					//either print Queue or File Name is not valid -- write Exception Record
					writeException(
							"Error while sending to Central Print",
							"Either Queue Not Specified or File Name inValid",
							null,
							false);
				}
			} //for			
		} catch (Exception e) {
			exceptionRecordBuf.delete(0, exceptionRecordBuf.length());
			//clear the buffer
			String msgText = getErrorMessage(16381);
			//This is the error code for the error message from Ref - Table
			exceptionRecordBuf.append(
					"Error inside sendFileToCentralPrint() \t").append(
							e + " :" + e.getMessage() + " \t " + msgText);
			writeException(
					"Error sending file to Central Print",
					exceptionRecordBuf.toString(),
					e,
					false);
			exceptionRecordBuf.delete(0, exceptionRecordBuf.length());
			CoDebugger.debugWarning(
					"Exception while sending files to Central Print Facility: "
							+ e);
			if (coLogFile != null) {
				writeCoLog(
						"Exception while sending files to Central Print Facility: "
								+ e,
								coLogFile);
			}
		}
	}

	/**
	 * reset any parameter for every document
	 * over-ride this appropriately for every 
	 * extending class 
	 */
	protected void resetAnyParamsForEveryDocument(){
	}
	/**
	 *process PCL
	 * over-ride this appropriately for every
	 * extending class
	 * @param atrigger COCorrespondence
	 * @param pclDriver ICoPCL
	 * @param pclString String
	 * @return boolean
	 */
	protected boolean processPCL(
			COCorrespondence atrigger,
			ICoPCL pclDriver,
			String pclString) {
		return false;
	}

	/**
	 * This method is used to write and flush the file for each CO trigger
	 * 
	 * @param printString Content of the file
	 * @return logical name of the file
	 */
	public String writeFlatFile(String printString)	{
		//array of bytes
		byte[] byteString=null;
		printString = CoConstants.RETURN_NEWLINE_CHAR + printString;
		byteString = printString.getBytes();

		try {
			//writes content into file
			if(coFileHandler != null) {
				coFileHandler.write(new String(byteString));
				coFileHandler.flush();
			}
		}catch(Exception e){
			CoDebugger.debugException("Exception writing batchfile",e);
		}
		return logicalFileName;
	}

	/**
	 * gets Logical file name (This will read RT for getting Logical filen ame) 
	 * @return String
	 */
	private String getLogicalFileName() {

		String logicalFile = "";
		try {
			logicalFile = ReferenceTableAccess.getRefDescription(jobId,
					CoBatchConstants.RT_BATCHDETAILS, CoBatchConstants.FLD_LOGICAL_FILE_NAME);
			logicalFile = this.parallelRunId + "_" +logicalFile+"Dat";
		} catch(Exception ex) {
			CoDebugger.debugException("Error occurred getting logical file name ",ex);
		}
		if(logicalFile != null && (logicalFile.equalsIgnoreCase(jobId) || ("").equalsIgnoreCase(logicalFile.trim()))) {
			if (jobId.equalsIgnoreCase(CoBatchConstants.CO_PMNUL_DLY)) {
				//fix the logical file name for that design
				logicalFile = this.parallelRunId + "_" + "MNULDat";
			} else if (jobId
					.equalsIgnoreCase(CoBatchConstants.CO_PVERF_DLY)) {
				logicalFile = this.parallelRunId + "_" + "VERFDat";
			} else if (jobId
					.equalsIgnoreCase(CoBatchConstants.CO_GENPL_DLY)) {
				logicalFile = this.parallelRunId + "_" + "GENPDat";
			} else if (jobId
					.equalsIgnoreCase(CoBatchConstants.CO_PDNFW_DLY)) {
				logicalFile = this.parallelRunId + "_" + "DNFWDat";
			} else if (jobId
					.equalsIgnoreCase(CoBatchConstants.CO_P146A_MLY)) {
				logicalFile = this.parallelRunId + "_" + "146ADat";
			} else if (jobId
					.equalsIgnoreCase(CoBatchConstants.CO_P240A_DLY)) {
				logicalFile = this.parallelRunId + "_" + "240ADat";
			} else if (jobId
					.equalsIgnoreCase(CoBatchConstants.CO_P432C_DLY)) {
				logicalFile = this.parallelRunId + "_" + "432CDat";
			} else if (jobId
					.equalsIgnoreCase(CoBatchConstants.CO_P1046_MLY)) {
				logicalFile = this.parallelRunId + "_" + "1046Dat";
			} else if (jobId
					.equalsIgnoreCase(CoBatchConstants.CO_P4487_MLY)) {
				logicalFile = this.parallelRunId + "_" + "4487Dat";
			} else if (jobId
					.equalsIgnoreCase(CoBatchConstants.CO_P1010_MLY)) {
				logicalFile = this.parallelRunId + "_" + "1010Dat";
			} else if (jobId
					.equalsIgnoreCase(CoBatchConstants.CO_P2063_MLY)) {
				logicalFile = this.parallelRunId + "_" + "2063Dat";
			} else if (jobId
					.equalsIgnoreCase(CoBatchConstants.CO_P3688_DLY)) {
				logicalFile = this.parallelRunId + "_" + "3688Dat";
			} else if (jobId
					.equalsIgnoreCase(CoBatchConstants.CO_P1099_DLY)) {//CR20172
				logicalFile = this.parallelRunId + "_" + "1099Dat";
			} else if (jobId
					.equalsIgnoreCase(CoBatchConstants.CO_P0198_DLY)) {
				logicalFile = this.parallelRunId + "_" + "0198Dat";
			} else if (jobId
					.equalsIgnoreCase(CoBatchConstants.CO_P1605_DLY)) {//Gajda WR 144931 - DHS-805 Has been discontinued.	
				logicalFile = this.parallelRunId + "_" + "1605Dat";
			} else if (jobId
					.equalsIgnoreCase(CoBatchConstants.CO_P4634_DLY)) {
				logicalFile = this.parallelRunId + "_" + "4634Dat";
			} else if (jobId
					.equalsIgnoreCase(CoBatchConstants.CO_P4656_MLY)) {
				logicalFile = this.parallelRunId + "_" + "4656Dat";
			} else if (jobId
					.equalsIgnoreCase(CoBatchConstants.CO_P2240_DLY)) {
				logicalFile = this.parallelRunId + "_" + "2240Dat";
			} else if (jobId
					.equalsIgnoreCase(CoBatchConstants.CO_P4358_DLY)) {
				logicalFile = this.parallelRunId + "_" + "4358Dat";
			} else if (jobId
					.equalsIgnoreCase(CoBatchConstants.CO_P4354_DLY)) {
				logicalFile = this.parallelRunId + "_" + "4354Dat";
			} else if (jobId
					.equalsIgnoreCase(CoBatchConstants.CO_P4807_DLY)) {
				logicalFile = this.parallelRunId + "_" + "4807Dat";
			} else if (jobId
					.equalsIgnoreCase(CoBatchConstants.CO_P1241_ALY)) {
				logicalFile = this.parallelRunId + "_" + "1241Dat";
			} else if (jobId
					.equalsIgnoreCase(CoBatchConstants.CO_P0506_ALY)) {
				logicalFile = this.parallelRunId + "_" + "0506Dat";
			} else if (jobId
					.equalsIgnoreCase(CoBatchConstants.CO_P0505_ALY)) {
				logicalFile = this.parallelRunId + "_" + "0505Dat";
			} else if (jobId
					.equalsIgnoreCase(CoBatchConstants.CO_P0504_MLY)) {
				logicalFile = this.parallelRunId + "_" + "0504Dat";
			} else if (jobId
					.equalsIgnoreCase(CoBatchConstants.CO_P0508_DLY)) {
				logicalFile = this.parallelRunId + "_" + "0508Dat";
			} else if (jobId
					.equalsIgnoreCase(CoBatchConstants.CO_P0586_WLY)) {
				logicalFile = this.parallelRunId + "_" + "0586Dat";
			} else if (jobId
					.equalsIgnoreCase(CoBatchConstants.CO_P0556_DLY)) {
				logicalFile = this.parallelRunId + "_" + "0556Dat";
			} else if (jobId
					.equalsIgnoreCase(CoBatchConstants.CO_P4101_ALY)) {
				logicalFile = this.parallelRunId + "_" + "4101Dat";
			} else if (jobId
					.equalsIgnoreCase(CoBatchConstants.CO_P0512_DLY)) {
				logicalFile = this.parallelRunId + "_" + "0512Dat";
			} else if (jobId
					.equalsIgnoreCase(CoBatchConstants.CO_P4033_QLY)) {
				logicalFile = this.parallelRunId + "_" + "4033Dat";
			} else if (jobId
					.equalsIgnoreCase(CoBatchConstants.CO_P3380_MLY)) {
				logicalFile = this.parallelRunId + "_" + "3380Dat";
			} else if (jobId
					.equalsIgnoreCase(CoBatchConstants.CO_P432P_DLY)) {
				logicalFile = this.parallelRunId + "_" + "4327Dat";
			} else if (jobId
					.equalsIgnoreCase(CoBatchConstants.CO_P4639_QLY)) {
				logicalFile = this.parallelRunId + "_" + "4639Dat";
			} else if (jobId
					.equalsIgnoreCase(CoBatchConstants.CO_P1440_DLY)) {
				logicalFile = this.parallelRunId + "_" + "1440Dat";
			} else if (jobId
					.equalsIgnoreCase(CoBatchConstants.CO_P0553_ALY)) {
				logicalFile = this.parallelRunId + "_" + "0553Dat";
			}else if (jobId
					.equalsIgnoreCase(CoBatchConstants.CO_P0504_DLY)) {
				logicalFile = this.parallelRunId + "_" + "0504Dat";
			}
		}

		return  logicalFile;
	}

	/**
	 * gets billing codes
	 * @param jobID String
	 * @return String
	 */
	private String getBillingCode(String jobID) {
		String billingCode = " ";
		try {
			billingCode = ReferenceTableAccess.getRefDescription(jobID,
					CoBatchConstants.RT_BATCHDETAILS, CoBatchConstants.FLD_BILLINGCODE);
			if(billingCode != null && billingCode.equalsIgnoreCase(jobID)) {
				billingCode = " ";
			}
			//BRGUS00120559 - bhattaj - BRT should be BRP for production
			if(CoBatchConstants.PRD_ENV.equals(currentEnv)) {
				billingCode = billingCode.replace(CoBatchConstants.CHAR_T, CoBatchConstants.CHAR_P); 
			}
		}catch(Exception ex) {
			CoDebugger.debugException("Error Occurred getting billing code", ex);
			CoDebugger.debugInformation("Error Occurred getting billing code......."+ex.getMessage());
		}
		return billingCode;
	}

	/**
	 * gets priority codes
	 * @param jobID String
	 * @return String
	 */
	private String getPriorityCode(String jobID) {
		String priorityCode = "";
		try {
			priorityCode = ReferenceTableAccess.getRefDescription(jobID,
					CoBatchConstants.RT_BATCHDETAILS, CoBatchConstants.FLD_PRIORITY);
			if(priorityCode != null && priorityCode.equalsIgnoreCase(jobID)) {
				priorityCode = "20"; //default priority
			}
		}catch(Exception ex) {
			CoDebugger.debugException("Error Occurred getting billing code...", ex);
		}
		return priorityCode.trim();
	}

	/**
	 * gets destination file name 
	 * @param cpcPriortyCd String
	 * @param billingCode String 
	 * @param logicalFileNameInner String
	 * @param folder String
	 * @return String
	 */


	private String getDestinationFileName(String cpcPriortyCd, String billingCode, String logicalFileNameInner, String folderName) {

		String destinationFileName = "";
		try {

			/* Defect ID : BRGUS00099324
			 * Added By:   WaliaR
			 * Date:       02/27/2008
			 * Change:     Add priorty to Physical file name 
			 */



			//Defect ID : BRGUS00099324  WaliaR 
			if(logicalFileNameInner != null) {
				destinationFileName = "\\"+folderName +"\\"+cpcPriortyCd+"_"+billingCode+"_"+jobId+"."+logicalFileNameInner+"-"+
						this.asOfDate.replace('/','-')+"."+this.tbc.getParallelRunNum()+".flt";
			}	

		} catch(Exception ex){
			CoDebugger.debugException("Error Occurred getting run number", ex);
			CoDebugger.debugInformation("Error Occurred getting run number....."+ex.getMessage());
		}
		return destinationFileName;
	}
	/**
	 * over-loaded
	 * gets destination file name
	 * @param cpcPriortyCd String
	 * @param billingCode String 
	 * @param logicalFileNameInner String
	 * @return String
	 */


	private String getDestinationFileName(String cpcPriortyCd, String billingCode, String logicalFileNameInner) {

		String destinationFileName = "";

		try {
			/* Defect ID : BRGUS00099324
			 * Added By  : Waliar
			 * Date      : 02/27/2008
			 * Change    : Add priorty to Physical file name 
			 */


			//Defect ID : BRGUS00099324  WaliaR	
			if(logicalFileNameInner != null) {
				destinationFileName = cpcPriortyCd + "_" + billingCode+"_"+jobId+"."+logicalFileNameInner+"-"+
						this.asOfDate.replace('/','-')+"."+tbc.getParallelRunNum()+".flt";
			}

		} catch(Exception ex){
			CoDebugger.debugException("Error Occurred getting run number", ex);
			CoDebugger.debugInformation("Error Occurred getting run number....."+ex.getMessage());
		}
		return destinationFileName;
	}

	/**
	 *generates the report
	 * take care that the headline passed to this method does not exceed 120 char - the DB column limit	
	 * Should not be over-riden in the sub-class as it is not required
	 * @param coReqSize long
	 * @param readCount long
	 * @param successCount long
	 * @param failureCount long
	 * @param headline String
	 */
	protected void generateReportNow(
			long coReqSize,
			long readCount,
			long successCount,
			long failureCount,
			String headline) {
		try {
			String[] reportLines =
				{
					tbc.formatString(headline, headline.length()),
					tbc.formatString("Summary Report", 50),
					//tbc.formatString("Number of Records to Process - ", 50)+ String.valueOf(coReqSize) , -- this value is not available in ResulltSet Process
					tbc.formatString(FwBatchConstants.MESSAGE5, 50) // COMMENT: REFACTORED THROUGH AUTOMATED APPROACH. OLD VALUE:'BatchConstants',NEW NAME:'FwBatchConstants'
					+ String.valueOf(readCount),


					tbc.formatString(FwBatchConstants.MESSAGE6, 50) // COMMENT: REFACTORED THROUGH AUTOMATED APPROACH. OLD VALUE:'BatchConstants',NEW NAME:'FwBatchConstants'
					+ String.valueOf(successCount),
					tbc.formatString(FwBatchConstants.MESSAGE4, 50) // COMMENT: REFACTORED THROUGH AUTOMATED APPROACH. OLD VALUE:'BatchConstants',NEW NAME:'FwBatchConstants'
					+ String.valueOf(failureCount)};
			String[] columnTypes =
				{
					tbc.formatString(FwBatchConstants.REPORT_TITLE, 20), // COMMENT: REFACTORED THROUGH AUTOMATED APPROACH. OLD VALUE:'BatchConstants',NEW NAME:'FwBatchConstants'
					tbc.formatString(FwBatchConstants.REPORT_HEADER, 20), // COMMENT: REFACTORED THROUGH AUTOMATED APPROACH. OLD VALUE:'BatchConstants',NEW NAME:'FwBatchConstants'
					//tbc.formatString(FwBatchConstants.REPORT_BODY, 20), // COMMENT: REFACTORED THROUGH AUTOMATED APPROACH. OLD VALUE:'BatchConstants',NEW NAME:'FwBatchConstants'
					tbc.formatString(FwBatchConstants.REPORT_BODY, 20), // COMMENT: REFACTORED THROUGH AUTOMATED APPROACH. OLD VALUE:'BatchConstants',NEW NAME:'FwBatchConstants'
					tbc.formatString(FwBatchConstants.REPORT_BODY, 20), // COMMENT: REFACTORED THROUGH AUTOMATED APPROACH. OLD VALUE:'BatchConstants',NEW NAME:'FwBatchConstants'
					tbc.formatString(FwBatchConstants.REPORT_BODY, 20)}; // COMMENT: REFACTORED THROUGH AUTOMATED APPROACH. OLD VALUE:'BatchConstants',NEW NAME:'FwBatchConstants'
			tbc.generateReport(reportLines, columnTypes);
			if (coLogFile != null) {
				writeCoLog(
						"After Writing to Batch Summary..Read count = "
								+ readCount
								+ " Success : "
								+ successCount
								+ " Failures : "
								+ failureCount,
								coLogFile);
			}
		} catch (Exception ex) {
			//perform cleanup and exit
			CoDebugger.debugException(
					"Exception writing to Summary Table ",
					ex);
			CoDebugger.debugInformation(" before aborting the job :3");
			try {
				if (coConnection == null) {
					CoDebugger.debugInformation("connection is null :3");
				} else if (coConnection.isClosed()) {
					CoDebugger.debugInformation("connection is closed :3");
				}
			} catch (SQLException f) {
				CoDebugger.debugException("sqlexception while printing connection status and before aborting the job :3",f);
				CoDebugger.debugInformation(
						" sqlexception while printing connection status and before aborting the job :3"+f.getMessage());
			}
			abortJob();
			CoDebugger.debugInformation(" after aborting the job :3");
		}
	}
	/**
	 *generate report
	 * override this implementation if required
	 * @param coReqSize long
	 * @param readCount long
	 * @param successCount long
	 * @param failureCount long
	 */
	protected void generateReport(
			long coReqSize,
			long readCount,
			long successCount,
			long failureCount) {
		generateReportNow(
				coReqSize,
				readCount,
				successCount,
				failureCount,
				"Process Pending Correspondence Requests - Summary Report");
	}
	/**
	 *populate the physical file names for printing
	 * over-ride this in the extending class
	 * @return String[]
	 */
	protected String[] getPhysicalFileNames() {
		return null;
	}
	/**
	 *get the final command
	 * should not be over-riden
	 * @param file String
	 * @param command String
	 * @param printQueue String
	 * @return String
	 */
	private String getFinalCommand(
			String file,
			String command,
			String printQueue) {


		StringBuffer temp = new StringBuffer("/usr/bin/ksh ");
		temp.append(environment).append("/co/script/").append(command);
		temp.append(" ").append(printQueue).append(" ");
		temp.append(file);
		return temp.toString();
	}
	/**
	 *post process the PCL's before generating the report
	 * over-ride this in the extending class
	 * @return boolean
	 */
	protected boolean postProcessPCLs() {
		return false;
	}
	/**
	 *pre process Banner Page
	 * over-ride this in the extending class
	 * @return boolean
	 */
	protected boolean preProcessBannerPages() {
		return false;
	}

	/**
	 *getInstance to be used by CoServiceFactory only
	 * Will be called only by Sub-Systems
	 * @return ICoBatch
	 */
	public static ICoBatch getInstance() {
		ICoBatch co = (ICoBatch) new CoBatch();
		return co;
	}
	/**@deprecated
	 * not used
	 * to initiate correspondence
	 * @param aCoRequest COCorrespondence
	 * @param aPropFilePath String
	 * @param aConn Connection
	 * @return int
	 * @throws java.sql.SQLException 
	 */
	public int initiateCorrespondence(
			COCorrespondence aCoRequest,
			String aPropFilePath,
			Connection aConn)
					throws java.sql.SQLException {

		if (aConn == null) {
			throw new SQLException("Connection is null");
		} else if (aConn.isClosed()) {
			throw new SQLException("Connection is closed");
		} else if (aConn.isReadOnly()) {
			throw new SQLException("Connection is read only");
		}
		try { // All exceptions propogated to this level
			initiateBatchCorrespondence(aCoRequest, aPropFilePath, aConn);
			return 0;
		} catch (CoException e) {
			CoDebugger.debugException(
					"Exception propogated to CoBatch " + e + " :" + e.getMessage(),
					e);
			return e.getErrorCode();
		}
	}
	/**@deprecated
	 * please use public int initiateCorrespondence(COCorrespondence aCoRequest, Connection aConn,String asOfDate)
	 * to initiate correspondence
	 * @param aCoRequest COCorrespondence
	 * @param aConn Connection
	 * @return int
	 * @throws java.sql.SQLException 
	 */
	public int initiateCorrespondence(
			COCorrespondence aCoRequest,
			Connection aConn)
					throws java.sql.SQLException {

		if (aConn == null) {
			throw new SQLException("Connection is null");
		} else if (aConn.isClosed()) {
			throw new SQLException("Connection is closed");
		} else if (aConn.isReadOnly()) {
			throw new SQLException("Connection is read only");
		}
		try {
			// All exceptions propogated to this level
			initiateBatchCorrespondence(aCoRequest, aConn);
			return 0;
		} catch (CoException e) {
			CoDebugger.debugException(
					"Exception propogated to CoBatch  - could not intiate Correspondence "
							+ e
							+ " :"
							+ e.getMessage(),
							e);
			return e.getErrorCode();
		}
	}
	/**
	 *to initiate correspondence in Batch
	 * @param aCoRequest COCorrespondence
	 * @param aConn Connection
	 * @param asOfDate String
	 * @return int
	 * @throws SQLException 
	 */
	public int initiateCorrespondence(
			COCorrespondence aCoRequest,
			Connection aConn,
			String asOfDate) throws SQLException{
		if(aConn == null){
			throw new SQLException("Connection is null");
		}else if (aConn.isClosed()){
			throw new SQLException("Connection is closed");
		}else if(aConn.isReadOnly()){
			throw new SQLException("Connection is read only");
		}
		try {
			if (validateAsOfDate(asOfDate)) {
				CoDateFactory.setBatchDate(asOfDate);
			}
			initiateBatchCorrespondence(aCoRequest, aConn);
			return 0;
		} catch (CoException e) {
			CoDebugger.debugException(
					"Exception propogated to CoBatch  - could not intiate Correspondence "
							+ e
							+ " :"
							+ e.getMessage(),
							e);
			return e.getErrorCode();
		}
	}
	/**
	 *initiates Batch Correspondence
	 * @param aCoRequest COCorrespondence
	 * @param aConn Connection
	 * @return COCorrespondence
	 * @throws CoException 
	 */
	private COCorrespondence initiateBatchCorrespondence(
			COCorrespondence aCoRequest,
			Connection aConn)
					throws CoException {
		// Properties assumed to have been pre-set *		 
		coDAOServices.setConnection(aConn);
		if (coReqGen == null) {
			coReqGen = new CoRequestGenerator(coDAOServices);
		}

		try {
			aCoRequest = coReqGen.createRequest(aCoRequest, aConn);
		}

		catch (Exception e) {
			CoDebugger.debugInformation(e.getMessage());
			CoDebugger.debugException("Request Not created", e);
			aCoRequest = null;
		}
		//NG-6427 Sonar issues Blocker
		CoDebugger.debugInformation("Created Request: From CoBatch");
		return aCoRequest;
	}
	/**
	 *initiates Batch Correspondence
	 * @param aCoRequest COCorrespondence
	 * @param aPropFilePath String
	 * @param aConn Connection
	 * @return COCorrespondence
	 * @throws CoException 
	 */
	private COCorrespondence initiateBatchCorrespondence(
			COCorrespondence aCoRequest,
			String aPropFilePath,
			Connection aConn)
					throws CoException {
		coDAOServices.setConnection(aConn); // added by manoj
		if (aPropFilePath != null && !("").equals(aPropFilePath)) {
			initiateProperties(aPropFilePath);
		} else {
			CoDebugger.debugWarning(
					"WARNING!! Properties File Path not provided in initiateBatchCorrespondence");
		}
		if (coReqGen == null) {
			coReqGen = new CoRequestGenerator(coDAOServices);
		}

		try {
			aCoRequest = coReqGen.createRequest(aCoRequest, aConn);
		} catch (OutOfMemoryError e) {
			CoDebugger.debugException("Request Not created", e);			
			CoDebugger.debugInformation(e.getMessage());
			aCoRequest = null;
		}
		CoDebugger.debugInformation("Created Request: From CoBatch");
		return aCoRequest;
	}
	/**
	 *initiate properties
	 * @param aPropertiesFileName String
	 * @return int
	 */
	protected int initiateProperties(String aPropertiesFileName) {
		Properties properties = null;
		File file = null;
		FileInputStream fi = null;
		try {
			file = new File(aPropertiesFileName);
			fi = new FileInputStream(file);
			properties = new Properties();
			properties.load(fi);
			//set the connection manager variables
		} catch (Exception e) {
			CoDebugger.debugException(
					"Exception in initiateProperties - " + e,
					e);
			return -1;
		}
		finally{
			 if(fi != null){
	                try {
	                	fi.close();
	                } catch (IOException e) {
	                    CoDebugger.debugException(e.getMessage(), e);
	                }
	            }
		}
		try {
			//set the reference table
			FwReferenceTableManager.setProperties(properties); // COMMENT: REFACTORED THROUGH AUTOMATED APPROACH. OLD VALUE:'FwReferenceTableManager',NEW NAME:'FwReferenceTableManager' // COMMENT: REFACTORED THROUGH AUTOMATED APPROACH. OLD VALUE:'ReferenceTableManager',NEW NAME:'FwReferenceTableManager'
		} catch (Exception e) {
			CoDebugger.debugException("Exception in setProperties - " + e, e);
			return -1;
		}
		return 0;
	}
	/**
	 *Get the docIds for selecting the trigger. This method is currently being used by <BR>
	 * CoBatchDocumentDriver and CoBatchEDBCDriver as of 09/30/02
	 * @return String
	 */
	protected String getDocIds() {
		StringTokenizer st = new StringTokenizer(docIds, ",");
		int cnt = st.countTokens();
		StringBuffer docIdStringForSelection = new StringBuffer("");
		for (int i = 0; i < cnt; i++) {
			docIdStringForSelection.append("'");
			docIdStringForSelection.append(st.nextToken());
			docIdStringForSelection.append("'");
			if (!((i + 1) == cnt))
			{
				docIdStringForSelection.append(", ");
			}
		}
		return docIdStringForSelection.toString();
	}
	/**
	 *This method returns the PCLString. Subclasses may override this method to derive specific functionality
	 * @param acopcl ICoPCL
	 * @param aco COCorrespondence
	 * @return String
	 */
	protected String getPCL(ICoPCL acopcl, COCorrespondence aco) {
		return acopcl.generatePCL();
	}
	/**
	 * @deprecated
	 *This method checks if the pclString is Valid.
	 * This piece of code had to be removed from processBatch method since Coupons have to be dealth with differently
	 * @param apclString - String The PCL String to check.
	 * @return boolean
	 */
	protected boolean isValidPCL(String apclString) {
		return false;
	}

	/**
	 * Method to write exception info to batch exception table
	 * 
	 * @param summery Name of control summary heading
	 * @param detail error message details
	 * @param tbc GenericBatchController Object
	 */
	public static void writeExceptionInfo(
			String summery,
			String detail,
			GenericBatchController tbc) {
		try {
			int statusCode =
					tbc.writeExceptionRecord(
							FwBatchConstants.WARNING, // COMMENT: REFACTORED THROUGH AUTOMATED APPROACH. OLD VALUE:'BatchConstants',NEW NAME:'FwBatchConstants'
							summery,
							detail,
							FwBatchConstants.REPORT_BODY, // COMMENT: REFACTORED THROUGH AUTOMATED APPROACH. OLD VALUE:'BatchConstants',NEW NAME:'FwBatchConstants'
							false);
		} catch (GenericBatchException e) {
			CoDebugger.debugException(
					"exception Info message - e.getMessage()",
					e);
		}
	}
	/**
	 * gets a handle to the Co Log File
	 * @param logicalLogFileName String
	 * @return OutputStream
	 * @throws GenericBatchException 
	 */
	protected OutputStream getCoLogFile(String logicalLogFileName)
			throws GenericBatchException {
		FwBatchFile batchFile = tbc.getOutputFileStream(logicalLogFileName); // COMMENT: REFACTORED THROUGH AUTOMATED APPROACH. OLD VALUE:'BatchFile',NEW NAME:'FwBatchFile'
		OutputStream out = (OutputStream) batchFile.getBufferedStream();
		return out;
	}
	/**
	 * Write to a Stream with the String provided - added for the purpose of logging Co activity
	 * @param strToWrite String
	 * @param stream OutputStream
	 * @return boolean
	 */
	protected boolean writeCoLog(String strToWrite, OutputStream stream) {
		if (stream != null) {
			try {
				strToWrite =
						strToWrite
						+ " logged at "
						+ new java.util.Date()
				+ NEW_LINE_CHAR
				+ CARRIAGE_RETURN_CHAR;
				//append each String in a new Line 
				byte[] bytesToWrite = strToWrite.getBytes("ISO8859_1");
				stream.write(bytesToWrite);
				stream.flush();
			} catch (Exception e) {
				CoDebugger.debugException("Exception in  writeCoLog():" + e, e);
				return false;
			}
		}
		return true;
	}
	/**
	 *returns the triggers for processing 
	 * to be overridden in the subclasses
	 * @param readConnection Connection


	 * @return VCoRequestCargo[]
	 */
	protected VCoRequestCargo[] getTriggers(Connection readConnection) throws Exception {
		return null;
	}

	/**
	 * This method is used to return the triggers for processing 
	 * To be overridden in the subclasses
	 * @param readConnection Connection
	 * @return VCoRequestCargo[]
	 * @author kschopra
	 */
	protected VCoRequestCargo[] getCOTriggers(Connection readConnection, String jobId) throws Exception {
		return null;
	}

	/**
	 * Override as required..This method is used presently in CoBatchDriver to get the Stuffing limits from the 
	 * Ref Table
	 * @return boolean
	 */
	protected boolean setStuffingLimits() {
		return true;
	}
	/**Write to File
	 * This method is called from writePCL and WritePCLWithSplit methods to 
	 * do File IO also called from Banner\trailer methods
	 * In case of banner(\trailer) methods - resetting the offset is incorrect
	 * @param aout OutputStream
	 * @param apclString String
	 * @param resetOffSet boolean
	 * @return boolean
	 */
	protected boolean writeString(
			OutputStream aout,
			String apclString,
			boolean resetOffSet) {



		try {
			byte[] bytespcl = apclString.getBytes("ISO8859_1");
			aout.write(bytespcl); //append to the file
			//aout.flush();	 flush is done while committing	
			if (resetOffSet) {
				resetAnyParamsForEveryDocument(); //reset the offset	
			}
			return true;
		} catch (Exception e) {
			CoDebugger.debugException("Error performing File I/O " + e, e);
			if (coLogFile != null) {
				writeCoLog("Error performing File I/O " + e, coLogFile);
			}
			return false;
		}
	}
	/**
	 *Method to write pcl to filesystem
	 * @param aout OutputStream
	 * @param apclString String
	 * @return boolean
	 */
	protected boolean writeString(OutputStream aout, String apclString) {
		return writeString(aout, apclString, true);
	}

	/**
	 *This method validates whether the asofdate passed is in mm/dd/yyyy
	 * format
	 * @param asOfDt String
	 * @return boolean
	 */
	private boolean validateAsOfDate(String asOfDt) {
		if (asOfDt != null) {
			asOfDt = asOfDt.trim();
			if (asOfDt.length() != 10) //  -- mm/dd/yyyy
			{
				return false;
			}
			if (asOfDt.indexOf("/") == -1) //'/' does not occur
			{
				return false;
			}
			if (asOfDt.indexOf('/') != 2) //first occurance of '/'
			{
				return false;
			}
			if (asOfDt.indexOf('/', 3) != 5) //second occurance of '/'
			{
				return false;
			}
			return true;
		}
		return false;
	}

	/**
	 * This method does cleanupOperations like write Trailer -
	 * send to CentralPrint etc - added on 05/15/2003 - for handling cleanup in abnormal terminations
	 * This will append a trailer to committed files (no commit is done here) and send them to Central Print
	 * Though the files are closed only in tbc.close
	 * it looks like it is ok if the (flushed) files are sent to CPF here. 
	 */

	protected void performFileCleanup() {

		if (coLogFile != null) {
			//Added for BRIDGES - Lakshmi
			writeFlatFile(CoConstants.RETURN_NEWLINE_CHAR 
					+ "***");
			if (fileCommitted) //this indicates that there has been committed files
			{
				postProcessPCLs();
				if (coLogFile != null) {
					writeCoLog(
							"Abnormal condition  - after File cleanup",
							coLogFile);
				}
				CoDebugger.debugInformation(
						"Abnormal condition  - after File cleanup");
			} else {
				writeCoLog("Abnormal Condition - no file cleanup.", coLogFile);
			}
			CoDebugger.debugInformation(
					"Abnormal Condition - no file cleanup.");
		}
	}

	/**
	 *This method will iterate through a resultSet and return the Cargo
	 * @param rsLocal ResultSet
	 * @param statement Statement
	 * @return VCoRequestCargo[]
	 * @throws SQLException 
	 */
	protected VCoRequestCargo[] getRequestCargoFromResultSet(
			ResultSet rsLocal,
			Statement statement)
					throws SQLException {
		/* This is needed since we use the SQL in the Batch job (not DAO) for 
		 * querying the load for the job.
		 * This mechanism has undergone a lot of changes back and forth
		 * Initially the call was via the DAO - which was changed when we had memory issues
		 * and the SQL called directly in the Batch process.
		 * Using this approach we had the problem of some strange SQLExceptions (ORA-01555,ORA-17412 etc)
		 * when dealing with loads over 5000. So this change is made now to use the for loop as before but 
		 * since we use the range of case_app_nums (for prlel run) we call it directly here within the batch process
		 * Calling it directly in the batch process could reduce any additional overhead (if any - not likely for Type 0) in
		 * Framework Collection\Cargo logic.
		 */
		List<VCoRequestCargo> v = new ArrayList<VCoRequestCargo>();
		VCoRequestCargo row = null;
		while (rsLocal.next()) {
			row = new VCoRequestCargo();
			getRowFromResultSet(row, rsLocal);
			v.add(row);
		}
		//close rsLocal
		rsLocal.close();

		if (statement != null) {
			statement.close();
		}
		VCoRequestCargo colrows[] = new VCoRequestCargo[v.size()];
		v.toArray(colrows);
		return colrows;
	}
	/**
	 *getRow from ResultSet -- copied from DAO
	 * @param row VCoRequestCargo
	 * @param rs ResultSet
	 * @return VCoRequestCargo
	 */
	protected VCoRequestCargo getRowFromResultSet(
			VCoRequestCargo row,
			ResultSet rs) {
		try {
			row.setT2DocId(rs.getString("T2_DOC_ID"));
			row.setAppNum(rs.getString("APP_NUM"));
			row.setStufferSw(getCharacterFromResultSet(rs, "STUFFER_SW"));
			row.setT3CreateUserId(rs.getString("T3_CREATE_USER_ID"));
			row.setAssistanceList(rs.getString("ASSISTANCE_LIST"));
			row.setT2CreateUserId(rs.getString("T2_CREATE_USER_ID"));
			row.setApptId(rs.getLong("APPT_ID"));
			row.setGenerateDt(rs.getTimestamp("GENERATE_DT"));
			row.setT1CreateUserId(rs.getString("T1_CREATE_USER_ID"));
			row.setLanguageCd(rs.getString("LANGUAGE_CD"));
			row.setReasonCdList(rs.getString("REASON_CD_LIST"));
			row.setProgramCd(rs.getString("PROGRAM_CD"));
			row.setEdgTraceId(rs.getLong("EDG_TRACE_ID"));
			row.setLogId(rs.getLong("LOG_ID"));
			row.setT1DocTypeCd(getCharacterFromResultSet(rs, "T1_DOC_TYPE_CD"));
			row.setReprintSw(getCharacterFromResultSet(rs, "REPRINT_SW"));
			row.setEmpId(rs.getLong("EMP_ID"));
			row.setPendingTrigSw(getCharacterFromResultSet(rs, "PENDING_TRIG_SW"));
			row.setCoDetSeq(rs.getLong("CO_DET_SEQ"));
			row.setT2CoReqSeq(rs.getLong("T2_CO_REQ_SEQ"));
			row.setManuallyGeneratedSw(getCharacterFromResultSet(rs, "MANUALLY_GENERATED_SW"));
			row.setBenefitNum(rs.getString("BENEFIT_NUM"));
			row.setOrigPrintDt(rs.getTimestamp("ORIG_PRINT_DT"));
			row.setCoRptSeq(rs.getLong("CO_RPT_SEQ"));
			row.setMassEnabledSw(
					getCharacterFromResultSet(rs, "MASS_ENABLED_SW"));
			row.setIndvId(rs.getLong("INDV_ID"));
			row.setDraftSw(getCharacterFromResultSet(rs, "DRAFT_SW"));
			row.setGenerateManuallySw(
					getCharacterFromResultSet(rs, "GENERATE_MANUALLY_SW"));
			row.setPrintDt(rs.getTimestamp("PRINT_DT"));
			row.setEdgNum(rs.getLong("EDG_NUM"));
			row.setOfficeNum(rs.getLong("OFFICE_NUM"));
			row.setHistorySw(getCharacterFromResultSet(rs, "HISTORY_SW"));
			row.setReqDt(rs.getTimestamp("REQ_DT"));
			row.setChipAppNum(rs.getString("CHIP_APP_NUM"));
			row.setRequestTypeCd(
					getCharacterFromResultSet(rs, "REQUEST_TYPE_CD"));
			row.setMassGeneratedSw(
					getCharacterFromResultSet(rs, "MASS_GENERATED_SW"));
			row.setActionCd(getCharacterFromResultSet(rs, "ACTION_CD"));
			row.setT1DocId(rs.getString("T1_DOC_ID"));
			row.setCaseNum(rs.getLong("CASE_NUM"));
			row.setHstPrintString(rs.getString("HST_PRINT_STRING"));
			row.setT3CoReqSeq(rs.getLong("T3_CO_REQ_SEQ"));
			row.setMiscParms(rs.getString("MISC_PARMS"));
			row.setFreeformSw(getCharacterFromResultSet(rs, "FREEFORM_SW"));
			row.setPrintMode(getCharacterFromResultSet(rs, "PRINT_MODE"));

			row.setProviderId(rs.getLong("PROVIDER_ID")); // set provider id irrespective of the request_type_code

			configureCaseAppNum(row, rs);

			return row;
		} catch (Exception e) {
			CoDebugger.debugException("getRowFromResultSet...." + e, e);
			writeException(
					"Inside getRowFromResultSet",
					"getRowFromResultSet " + e + " :" + e.getMessage(),
					e,
					false);
			return null;
		}
	}
	/**
	 *Copied from DAO
	 * @param rs ResultSet
	 * @param col String
	 * @return char
	 * @throws SQLException 
	 */
	private char getCharacterFromResultSet(ResultSet rs, String col)
			throws SQLException {
		String result = rs.getString(col);
		if (result != null && result.length() > 0){
			return result.charAt(0);
		}else {
			return 0;
		}
	}
	/**
	 *Method to write exceptions, perform cleanup and exit
	 * If exceptionsummary and exceptionDetail is passed - it writes to FW_BATCH_EXCEPTION 
	 * If exitFlag is true - it performs cleanup and Exit
	 * @param exceptionSummary String
	 * @param exceptionDetail String
	 * @param e Throwable
	 * @param exitFlag boolean*/
	protected void writeException(
			String exceptionSummary,
			String exceptionDetail,
			Throwable e,
			boolean exitFlag) {

		//This method may be moved to a helper class
		try {
			int statusCode = 0;
			String exType = FwBatchConstants.WARNING; //by default // COMMENT: REFACTORED THROUGH AUTOMATED APPROACH. OLD VALUE:'BatchConstants',NEW NAME:'FwBatchConstants'
			if (exitFlag) {
				exType = FwBatchConstants.FATAL; // COMMENT: REFACTORED THROUGH AUTOMATED APPROACH. OLD VALUE:'BatchConstants',NEW NAME:'FwBatchConstants'
			}
			if (exceptionSummary != null && exceptionDetail != null) {
				if (e != null) {
					CoDebugger.debugException(exceptionDetail, e);
					if(!exceptionDetail.contains(CoConstants.BLANK))
					{
					statusCode =
							tbc.writeExceptionRecord(
									exType,
									"Exception in :" + exceptionSummary,
									exceptionDetail + e + " :" + e.getMessage(),
									FwBatchConstants.REPORT_BODY, // COMMENT: REFACTORED THROUGH AUTOMATED APPROACH. OLD VALUE:'BatchConstants',NEW NAME:'FwBatchConstants'
									false);
					}else{
						exType=CoConstants.SUPPRESSION_EXP_CD;
						statusCode =
								tbc.writeExceptionRecord(
										exType,
										exceptionSummary,
										exceptionDetail,
										FwBatchConstants.REPORT_BODY, // COMMENT: REFACTORED THROUGH AUTOMATED APPROACH. OLD VALUE:'BatchConstants',NEW NAME:'FwBatchConstants'
										false);
					}
				} else { //not an exception
					if (exitFlag) {
						CoDebugger.logWithLevel(exceptionDetail, ILog.FATAL);
					} else {
						CoDebugger.logWithLevel(exceptionDetail, ILog.WARNING);
					}
					statusCode =
							tbc.writeExceptionRecord(
									exType,
									"Exception in :" + exceptionSummary,
									exceptionDetail,
									FwBatchConstants.REPORT_BODY, // COMMENT: REFACTORED THROUGH AUTOMATED APPROACH. OLD VALUE:'BatchConstants',NEW NAME:'FwBatchConstants'
									false);
				}
				if (statusCode == -1) {
					CoDebugger.logWithLevel(
							"Could not write to FW_BATCH_EXCEPTIONS.."
									+ exceptionSummary
									+ " : Ret value -1",
									ILog.FATAL);
					System.exit(-1);
				}
			}
		} catch (Exception ex) {

			CoDebugger.logWithLevel(
					"Could not write to FW_BATCH_EXCEPTIONS.."
							+ exceptionSummary
							+ " :"
							+ ex
							+ " :"
							+ ex.getMessage(),
							ILog.FATAL);
			if (coLogFile != null) {
				writeCoLog(
						"Could not write to FW_BATCH_EXCEPTIONS.."
								+ ex
								+ " :"
								+ ex.getMessage(),
								coLogFile);
			}
			exitFlag = true;
		}
		if (exitFlag) {
			CoDebugger.debugInformation(" before aborting the job :2");
			try{
				if(coConnection == null){
					CoDebugger.debugInformation("connection is null :2");
				}else if(coConnection.isClosed()){
					CoDebugger.debugInformation("connection is closed :2");
				}					
			}catch(SQLException f){
				CoDebugger.debugException("sqlexception while printing connection status and before aborting the job :2", f);
				CoDebugger.debugInformation(" sqlexception while printing connection status and before aborting the job :2"+f.getMessage());
			}				

			abortJob();
			CoDebugger.debugInformation(" after aborting the job :2");
		}
	}


	// Kannegantik 
	/**
	 *Method to write exceptions, perform cleanup and exit
	 * If exceptionsummary and exceptionDetail is passed - it writes to FW_BATCH_EXCEPTION 
	 * If exitFlag is true - it performs cleanup and Exit
	 * @param exceptionSummary String
	 * @param exceptionDetail String
	 * @param e Throwable
	 * @param exitFlag boolean*/
	protected void writeExceptionAsInfo(
			String exceptionSummary,
			String exceptionDetail,
			Throwable e,
			boolean exitFlag) {

		//This method may be moved to a helper class
		try {
			int statusCode = 0;
			String exType = FwBatchConstants.INFO; //by default // COMMENT: REFACTORED THROUGH AUTOMATED APPROACH. OLD VALUE:'BatchConstants',NEW NAME:'FwBatchConstants'
			if (exitFlag) {
				exType = FwBatchConstants.FATAL; // COMMENT: REFACTORED THROUGH AUTOMATED APPROACH. OLD VALUE:'BatchConstants',NEW NAME:'FwBatchConstants'
			}
			if (exceptionSummary != null && exceptionDetail != null) {
				if (e != null) {
					CoDebugger.debugException(exceptionDetail, e);
					statusCode =
							tbc.writeExceptionRecord(
									exType,
									"Exception in :" + exceptionSummary,
									exceptionDetail + e + " :" + e.getMessage(),
									FwBatchConstants.REPORT_BODY, // COMMENT: REFACTORED THROUGH AUTOMATED APPROACH. OLD VALUE:'BatchConstants',NEW NAME:'FwBatchConstants'
									false);
				} else { //not an exception
					if (exitFlag) {
						CoDebugger.logWithLevel(exceptionDetail, ILog.FATAL);
					} else {
						CoDebugger.logWithLevel(exceptionDetail, ILog.WARNING);
					}
					statusCode =
							tbc.writeExceptionRecord(
									exType,
									"Exception in :" + exceptionSummary,
									exceptionDetail,
									FwBatchConstants.REPORT_BODY, // COMMENT: REFACTORED THROUGH AUTOMATED APPROACH. OLD VALUE:'BatchConstants',NEW NAME:'FwBatchConstants'
									false);
				}
				if (statusCode == -1) {
					CoDebugger.logWithLevel(
							"Could not write to FW_BATCH_EXCEPTIONS.."
									+ exceptionSummary
									+ " : Ret value -1",
									ILog.FATAL);
					System.exit(-1);
				}
			}
		} catch (Exception ex) {

			CoDebugger.logWithLevel(
					"Could not write to FW_BATCH_EXCEPTIONS.."
							+ exceptionSummary
							+ " :"
							+ ex
							+ " :"
							+ ex.getMessage(),
							ILog.FATAL);
			if (coLogFile != null) {
				writeCoLog(
						"Could not write to FW_BATCH_EXCEPTIONS.."
								+ ex
								+ " :"
								+ ex.getMessage(),
								coLogFile);
			}
			exitFlag = true;
		}
		if (exitFlag) {
			CoDebugger.debugInformation(" before aborting the job :2");
			try{
				if(coConnection == null){
					CoDebugger.debugInformation("connection is null :2");
				}else if(coConnection.isClosed()){
					CoDebugger.debugInformation("connection is closed :2");
				}					
			}catch(SQLException f){
				CoDebugger.debugException("sqlexception while printing connection status and before aborting the job :2", f);
				CoDebugger.debugInformation(" sqlexception while printing connection status and before aborting the job :2"+f.getMessage());
			}				

			abortJob();
			CoDebugger.debugInformation(" after aborting the job :2");
		}
	}
	// kannegantik END
	/**
	 * This method is used to make an abnormal termination
	 * It will try to close resources, perform cleanup and exit 
	 */
	private void abortJob() {
		try {
			if (coConnection != null && !coConnection.isClosed()) {
				coConnection.commit();
				coConnection.close();
			}
		} catch (java.sql.SQLException se) {
			CoDebugger.logWithLevel(
					"Could not close connection coConnection before exiting.."
							+ se
							+ " :"
							+ se.getMessage(),
							ILog.FATAL);
			if (coLogFile != null) {
				writeCoLog(
						"Could not close connection updateConnection before exiting.."
								+ se
								+ " :"
								+ se.getMessage(),
								coLogFile);
			}
		}
		//********WRITE TRAILER PAGE AND SEND TO CPF IF REQUIRED (IF FILE IS OPENED AND WRITTEN TO
		performFileCleanup();
		//***********CLOSE TBC
		try {
			tbc.stop(FwBatchConstants.ABORT); // COMMENT: REFACTORED THROUGH AUTOMATED APPROACH. OLD VALUE:'BatchConstants',NEW NAME:'FwBatchConstants'
		} catch (Exception ex) {
			CoDebugger.logWithLevel(
					"Exception closing tbc...error writing exception record " + ex,
					ILog.FATAL);
			System.exit(-1);
		}
		//EXIT
		System.exit(-1);
	}
	/**
	 *Method to log exception and terminate Warrant Job
	 * @param co COCorrespondence
	 * @param e Throwable*/
	protected void terminateWarrantJob(COCorrespondence co, Throwable e) {
		String exceptionSummaryStr =
				"Warrant Exception..Aborting"
						+ co.getCoReqSeq()
						+ "\t"
						+ co.getCaseAppNumber();
		String exceptionDetailStr =
				co.getCoReqSeq()
				+ "\t"
				+ co.getCaseAppNumber()
				+ "\t Edg : "
				+ co.getEdgeNumber()
				+ "\t Warrant: "
				+ co.getBenefitNumber();
		writeException(exceptionSummaryStr, exceptionDetailStr, e, true);
	}
	/**Creates a Log file for printing StackTrace -
	 * @param t Throwable
	 * 
	 */
	protected void writeStackTrace(Throwable t) {
		try {
			//Fatal error log - to print Stack Trace
			if (errorLogStream == null) {
				errorLogStream =
						(BufferedOutputStream) getCoLogFile("ERRORLOG"
								+ "_"
								+ parallelRunId
								+ "_LOGTmp");
			}
		} catch (GenericBatchException tbe) {
			CoDebugger.logWithLevel(
					"Could not write to Fatal error log "
							+ tbe
							+ " "
							+ tbe.getMessage(),
							ILog.FATAL);
		}
	}
	/**
	 *updates the records after SAS Authorization happens.
	 * This method will be called by DI only for updating the records that 
	 * need SAS Authorization
	 * @param caseNum long
	 * @param edgNumList ArrayList
	 * @param aConn Connection
	 * @param asOfDate String
	 * @return int
	 */
	@SuppressWarnings("rawtypes")
	public int updateRecordsForSaSAuthorization(
			long caseNum,
			List edgNumList,
			Connection aConn,
			String asOfDate) {
		if (validateAsOfDate(asOfDate)) {
			CoDateFactory.setBatchDate(asOfDate);
		}
		// Properties assumed to have been pre-set *		 
		coDAOServices.setConnection(aConn);
		int rtnCode = 0;
		//for find box bug added by Jagannath 
		if(edgNumList !=null){
			Debug.println(
					"SAS AUTHORIZATIO REQUEST is caseNum = "
							+ caseNum
							+ " edgNumList = "
							+ edgNumList.toString());
		}
		if (caseNum == 0) {
			Debug.println(
					"CaseNum not set for SAS Authorization: error CODE IS 303");
			return 303;
		} else if (edgNumList == null || edgNumList.size() <= 0) {
			Debug.println(
					"EdgNum not set for SAS Authorization: error CODE IS 303");
			return 303;
		}
		//Call CoRequestGenerator to update the SAS records

		CoRequestGenerator coRequestGen = new CoRequestGenerator(coDAOServices);
		try {
			coRequestGen.updateRecordsForSASAuthorization(
					caseNum,
					edgNumList,
					aConn);
		} catch (CoException e) {
			CoDebugger.debugException("updateRecordsForSaSAuthorization failed", e);
			CoDebugger.debugInformation(e.getMessage());
			rtnCode = e.getErrorCode();
			return rtnCode;
		}

		return rtnCode;
	}
	/**
	 *this method sets the updatingTrigger Flag based on the docId
	 * Also, it sets the appropriate connection to use for building the document
	 * @param docId String
	 * @return boolean
	 * @throws GenericBatchException 
	 * @throws SQLException 
	 */
	protected boolean isUpdatingTrigger(String docId)
			throws GenericBatchException, SQLException {
		boolean isUpdateTrigger = false;
		if (docId.equals(DOC_ID_1020)
				|| docId.equals(CoConstants.FXX380_DOC_ID) // BRGUS00224259 - NaredlaS - For DHS-3503-C
				|| docId.equals(DOC_ID_TF0001)
				//|| docId.equals(DOC_ID_TF0002)
				|| docId.equals(DOC_ID_1830)
				|| docId.equals(DOC_ID_FXX259) // BRGUS00160565 - NaredlaS - For DHS - 1010 - SP
				|| docId.equals(DOC_ID_3503SER)) {  // BRGUS00118836 - gundan - CR 20974
			isUpdateTrigger = true;
		} else {
			isUpdateTrigger = false;
		}
		return isUpdateTrigger;
	}

	/**
	 *This method creates a String of DocIds that require BRM envelopes
	 * the String can bedirectly plugged into the SQL IN clause
	 * @param requiredDocList ArrayList
	 * @param docIds2Exclude ArrayList
	 * @return String
	 */
	@SuppressWarnings("rawtypes")
	protected String filterRequiredDocList(
			List requiredDocList,
			List docIds2Exclude) {

		StringBuffer docIdStrBuf = new StringBuffer("");
		int listSize = requiredDocList.size();
		String documentId = null;
		if (listSize > 0) {
			boolean firstElement = true;
			for (int i = 0; i < listSize; i++) {
				documentId = (String) requiredDocList.get(i);
				if (docIds2Exclude != null
						&& docIds2Exclude.contains(documentId)) {
					continue;
				}
				if (firstElement) {
					docIdStrBuf.append("'");
					firstElement = false;
				} else {
					docIdStrBuf.append(",'");
				}
				docIdStrBuf.append(documentId).append("'");
			}
		}
		return docIdStrBuf.toString();
	}
	/**
	 *This method creates a String of DocIds specified in the parameters
	 * the String can bedirectly plugged into the SQL IN clause
	 * This can be used in place of the method getDocIds() in this class
	 * to string the doc Ids and also to exclude the Warrants & Coupon Doc Ids from the list
	 * @param documentIds String
	 * @param docIds2Exclude ArrayList
	 * @return String
	 */
	@SuppressWarnings("rawtypes")
	protected String excludeDocIds(
			String documentIds,
			List docIds2Exclude) {

		StringTokenizer st = new StringTokenizer(documentIds, ",");
		int cnt = st.countTokens();
		StringBuffer docIdStrBuf = new StringBuffer("");
		String justTheDocID = null;
		if (cnt > 0) {
			boolean firstElement = true;
			for (int i = 0; i < cnt; i++) {
				justTheDocID = st.nextToken();
				if (docIds2Exclude != null
						&& docIds2Exclude.contains(justTheDocID)) {
					continue;
				}
				if (firstElement) {
					docIdStrBuf.append("'");
					firstElement = false;
				} else {
					docIdStrBuf.append(",'");
				}
				docIdStrBuf.append(justTheDocID).append("'");
			}
		}
		return docIdStrBuf.toString();
	}
	/**
	 *Method configureCaseAppNum.
	 * @param row VCoRequestCargo
	 * @param rs ResultSet
	 * @throws SQLException 
	 */
	protected void configureCaseAppNum(VCoRequestCargo row, ResultSet rs)
			throws SQLException {
		char type = getCharacterFromResultSet(rs, "REQUEST_TYPE_CD");
		String caseAppNum = null;
		if (type == 'A') {
			caseAppNum = rs.getString("APP_NUM");
			if (caseAppNum == null) {
				caseAppNum = rs.getString("CHIP_APP_NUM");
			}
		} else if (type == 'C') {
			caseAppNum = String.valueOf(rs.getLong("CASE_NUM"));
		} else if (type == 'L') {
			caseAppNum = String.valueOf(rs.getLong("LOG_ID"));
		}
		row.setCaseAppNum(caseAppNum);
	}

	/**
	 * Field chipAppList.
	 */
	public static final List<String> chipAppList = new ArrayList<String>();
	static {
		chipAppList.add("FXX074");
		chipAppList.add("FXX075");
	}
	/**
	 *Method retrieveCaseAppNum.
	 * @param rs ResultSet
	 * @return String
	 * @throws SQLException 
	 */
	protected String retrieveCaseAppNum(ResultSet rs) throws SQLException {
		StringBuffer tempBuffer = new StringBuffer();
		char requestType = rs.getString("REQUEST_TYPE_CD").charAt(0);
		String docId = rs.getString("T1_DOC_ID");
		if (requestType == 'C') {
			//case number
			tempBuffer.append(rs.getLong("CASE_NUM"));
		} else if (requestType == 'A') {
			//application number or log id

			if (chipAppList.contains(docId)) {
				tempBuffer.append(rs.getString("CHIP_APP_NUM"));
			} else {
				tempBuffer.append(rs.getString("APP_NUM"));
			}		
		}else if (requestType == 'S') {		//Changes For ITO Correspondence - gajda
			if (docId.equalsIgnoreCase(CoConstants.FXX322_DOC_ID)) {
				tempBuffer.append(rs.getString("CHIP_APP_NUM"));			
			}
		}
		return tempBuffer.toString();
	}

	/**
	 * This method retrieveCaseAppNumForCargo.
	 * 
	 * @param cargo CO trigger info
	 * @return Case or App or provider id
	 * @throws SQLException 
	 */
	protected String retrieveCaseAppNumForCargo(VCoRequestCargo cargo) throws SQLException {
		StringBuffer tempBuffer = new StringBuffer();
		char requestType = cargo.getRequestTypeCd();
		if (requestType == 'C') {
			//case number
			tempBuffer.append(String.valueOf(cargo.getCaseNum()));
		} else if (requestType == 'A') {
			tempBuffer.append(String.valueOf(cargo.getAppNum()));
		}
		return tempBuffer.toString();
	}

	/**
	 * gives the list of doc ids to be processed
	 * @param jobID String
	 * @return String
	 */
	public String getDocIDsTobeProcessed(String jobID) {
		String docIdsTobeProcessed = CoConstants.EMPTY_STRING;
		String docs = CoConstants.EMPTY_STRING;
		try {
			//docs 
			docs = ReferenceTableAccess.getRefDescription(jobID,
					CoBatchConstants.RT_BATCHDETAILS,CoBatchConstants.FLD_DOCIDS);
			if(docs != null && !docs.trim().equalsIgnoreCase(jobID)) {
				if(docs.indexOf(",") >= 1) {
					String[] arrDocs = docs.split("\\,");
					if(arrDocs !=null && arrDocs.length !=0) {
						for(int i = 0;i<arrDocs.length;i++) {
							if(i == arrDocs.length-1) {
								docIdsTobeProcessed += "'"+arrDocs[i].trim()+"'";
							} else {
								docIdsTobeProcessed += "'"+arrDocs[i].trim()+"',";
							}
						}
					}
				}else {
					docIdsTobeProcessed = docs.trim();
				}
			}
		} catch(Exception ex) {
			CoDebugger.debugException("Failed to get list of doc ids to be processed", ex);
			CoDebugger.debugInformation("Failed to get list of doc ids to be processed"+ex.getMessage());
		}
		return docIdsTobeProcessed;
	}

	/**
	 * gives the array of doc ids to be processed
	 * 
	 * @param jobID
	 *            String
	 * @return String[]
	 * 
	 *         Added by Mohit as part of Spring batch implementation ND-2917
	 */
	public String[] getDocIDArrayTobeProcessed(String jobID) {
		String[] arrDocs = null;
		String docs = CoConstants.EMPTY_STRING;
		try {
			// docs
			docs = ReferenceTableAccess.getRefDescription(jobID,
					CoBatchConstants.RT_BATCHDETAILS,
					CoBatchConstants.FLD_DOCIDS);

			if (docs != null && !docs.trim().equalsIgnoreCase(jobID)) {
				if (docs.indexOf(",") >= 1){
					arrDocs = docs.split("\\,");
				}else {
					arrDocs = new String[1];
					arrDocs[0] = docs.trim();
				}
			}
		} catch (Exception ex) {
			CoDebugger.debugException("CoBatch.getDocIDArrayTobeProcessed() : Failed to get list of doc ids to be processed", ex);
			CoDebugger
			.debugInformation("CoBatch.getDocIDArrayTobeProcessed() : Failed to get list of doc ids to be processed"+ex.getMessage());
		}
		return arrDocs;
	}

	/**
	 * Just for Testing
	 * @param jobID String
	 * @return String
	 */


	/**
	 * gets folder name for the respective designs
	 * @param jobID String
	 * @return String
	 */
	private String getFolderName(String jobID) {
		//default directory
		String folderName = "data";
		try {
			folderName = ReferenceTableAccess.getRefDescription(jobID,CoBatchConstants.RT_BATCHDETAILS,CoBatchConstants.FLD_FOLDER_NAME);
			// for testing only
			if(folderName != null && (folderName).trim().equalsIgnoreCase(jobID)) {
				if(jobID != null && jobID.equalsIgnoreCase(CoBatchConstants.CO_P4487_MLY)) {
					folderName = "4487";
				}else if(jobID !=null && jobID.equalsIgnoreCase(CoBatchConstants.CO_P1010_MLY)) {
					folderName = "1010";
				}else if(jobID != null && jobID.equalsIgnoreCase(CoBatchConstants.CO_PVERF_DLY)) {
					folderName = "MVERF";
				}else if(jobID != null && jobID.equalsIgnoreCase(CoBatchConstants.CO_PMNUL_DLY)) {
					folderName = "MMANUAL";
				}else if(jobID != null && (jobID.equalsIgnoreCase(CoBatchConstants.CO_GENPL_DLY)
						|| jobID.equalsIgnoreCase(CoBatchConstants.CO_PAPPL_DLY))) {
					folderName = "CLEANUP";
				}else if(jobID != null && jobID.equalsIgnoreCase(CoBatchConstants.CO_P1605_DLY)) {
					folderName = "M1605";
				}else if(jobID != null && jobID.equalsIgnoreCase(CoBatchConstants.CO_P0198_DLY)) {
					folderName = "198";
				}else if(jobID != null && jobID.equalsIgnoreCase(CoBatchConstants.CO_P4639_QLY)) {
					folderName = "4639";
				}else if(jobID != null && jobID.equalsIgnoreCase(CoBatchConstants.CO_PDNFW_DLY)) {
					folderName = "DNF";
				}else if(jobID != null && jobID.equalsIgnoreCase(CoBatchConstants.CO_P0508_DLY)) {
					folderName = "508";
				}else if(jobID != null && jobID.equalsIgnoreCase(CoBatchConstants.CO_P3688_DLY)) {
					folderName = "M3688";
				}else if(jobID != null && jobID.equalsIgnoreCase(CoBatchConstants.CO_P3380_MLY)) {
					folderName = "3380";
				}else if(jobID != null && jobID.equalsIgnoreCase(CoBatchConstants.CO_P0556_DLY)) {
					folderName = "556";
				}else if(jobID != null && jobID.equalsIgnoreCase(CoBatchConstants.CO_P432P_DLY)) {
					folderName = "M4327P";
				}else if(jobID != null && jobID.equalsIgnoreCase(CoBatchConstants.CO_P1046_MLY)) {
					folderName = "M1046";
				}else if(jobID != null && jobID.equalsIgnoreCase(CoBatchConstants.CO_P146A_MLY)) {
					folderName = "M1046A";
				}else if(jobID != null && jobID.equalsIgnoreCase(CoBatchConstants.CO_P2063_MLY)) {
					folderName = "M2063";
				}else if(jobID != null && jobID.equalsIgnoreCase(CoBatchConstants.CO_P2063_MLY)) {
					folderName = "M2063";
				}else if(jobID != null && jobID.equalsIgnoreCase(CoBatchConstants.CO_P1440_DLY)) {
					folderName = "M1440";
				}else if(jobID !=null && jobID.equalsIgnoreCase(CoBatchConstants.CO_P240A_DLY)) {
					folderName = "M2240A";
				}else if(jobID != null && jobID.equalsIgnoreCase(CoBatchConstants.CO_P2240_DLY)) {
					folderName = "M2240"; 
				}else if(jobID != null && jobID.equalsIgnoreCase(CoBatchConstants.CO_P432C_DLY)) {
					folderName = "M4327C"; 
				}else if(jobID != null && jobID.equalsIgnoreCase(CoBatchConstants.CO_P1099_DLY)) {//CR 20172
					folderName = "1099"; 
				}else if(jobID != null && jobID.equalsIgnoreCase(CoBatchConstants.CO_P4634_DLY)) {//Gajda WR144931  DHS-805 Has been discontinued
					folderName = "4634"; 
				}else if(jobID != null && jobID.equalsIgnoreCase(CoBatchConstants.CO_P4656_MLY)) {
					folderName = "4656"; 
				}else if(jobID != null && jobID.equalsIgnoreCase(CoBatchConstants.CO_P4358_DLY)) {
					folderName = "4358"; 
				}else if(jobID != null && jobID.equalsIgnoreCase(CoBatchConstants.CO_P4354_DLY)) {
					folderName = "4354"; 
				}else if(jobID != null && jobID.equalsIgnoreCase(CoBatchConstants.CO_P4807_DLY)) {
					folderName = "4807"; 
				}else if(jobID != null && jobID.equalsIgnoreCase(CoBatchConstants.CO_P1241_ALY)) {
					folderName = "1241"; 
				}else if(jobID != null && jobID.equalsIgnoreCase(CoBatchConstants.CO_P0506_ALY)) {
					folderName = "506"; 
				}else if(jobID != null && jobID.equalsIgnoreCase(CoBatchConstants.CO_P0505_ALY)) {
					folderName = "505"; 
				}else if(jobID != null && jobID.equalsIgnoreCase(CoBatchConstants.CO_P0504_MLY)) {
					folderName = "504"; 
				}else if(jobID != null && jobID.equalsIgnoreCase(CoBatchConstants.CO_P0509_MLY)) {// BRGUS00145719 - ThatiparthiS  -- Changed as monthly job.
					folderName = "509"; 
				}else if(jobID != null && jobID.equalsIgnoreCase(CoBatchConstants.CO_P0586_WLY)) {
					folderName = "586"; 
				}else if(jobID != null && jobID.equalsIgnoreCase(CoBatchConstants.CO_P4101_ALY)) {
					folderName = "4101"; 
				}else if(jobID != null && jobID.equalsIgnoreCase(CoBatchConstants.CO_P0512_DLY)) {
					folderName = "512"; 
				}else if(jobID != null && jobID.equalsIgnoreCase(CoBatchConstants.CO_P4033_QLY)) {
					folderName = "4033"; 
				}else if(jobID != null && jobID.equalsIgnoreCase(CoBatchConstants.CO_P4033_QLY)) {
					folderName = "4033"; 
				}else if(jobID != null && jobID.equalsIgnoreCase(CoBatchConstants.CO_P0553_ALY)) {
					folderName = "553"; 
				}else if(jobID != null && jobID.equalsIgnoreCase(CoBatchConstants.CO_P0504_DLY)) {
					folderName = "504"; 
				}

			}
		}catch(Exception ex) {
			CoDebugger.debugException("Error getting the folder name", ex);
			CoDebugger.debugInformation("Error getting the folder name ......."+ex.getMessage());
		}
		if(folderName !=null){
			folderName = folderName.trim();
		}
		return folderName;
	}// end of method

	/**
	 * gets banner details
	 * @param buf StringBuffer
	 * @return StringBuffer
	 */
	private StringBuffer getBannerPageDetails(StringBuffer buf) {
		//get required parameters
		//BRGUS00120559 - bhattaj - Not needed

		String billingCode = getBillingCode(jobId);

		/* Defect ID : BRGUS00099324
		 * Added By  : Waliar
		 * Date      : 02/27/2008
		 * Change    : Add priorty to Physical file name 
		 *                
		 */

		String priorityCd = getPriorityCode(jobId);

		//Defect ID : BRGUS00099324  WaliaR

		String physicalFilename = getDestinationFileName(priorityCd,billingCode,logicalFileName);
		//BRGUS00120559 - bhattaj - Environment variable changed
		//format the String
		if(buf != null) {
			String formattedString = CoBatchConstants.BANNER_RECORD_SET + CoConstants.FLATFILE_SEPARATOR
					+ jobId + CoConstants.FLATFILE_SEPARATOR + CoBatchConstants.BANNER_DESIGN_ID
					+ CoConstants.FLATFILE_SEPARATOR + this.asOfDate+CoConstants.FLATFILE_SEPARATOR
					+ currentEnv + CoConstants.FLATFILE_SEPARATOR + physicalFilename
					+ CoConstants.FLATFILE_SEPARATOR + priorityCd 
					+ CoConstants.RETURN_NEWLINE_CHAR + "***" + CoConstants.RETURN_NEWLINE_CHAR;

			//insert formatted string at the first position
			buf.insert(0,formattedString);
		}
		return buf;
	}

	/**
	 * gets trailer details
	 * @param buf StringBuffer
	 * @return StringBuffer
	 */
	private StringBuffer getTrailerPageDetails(StringBuffer buf) {
		//get required parameters
		//BRGUS00120559 - bhattaj - Not needed

		String billingCode = getBillingCode(jobId);
		/* Defect ID : BRGUS00099324
		 * Added By:   WaliaR
		 * Date:       02/27/2008
		 * Change:     Add priorty to Physical file name 
		 */
		String priorityCode = getPriorityCode(jobId);

		//Defect ID : BRGUS00099324  WaliaR

		String physicalFilename = getDestinationFileName(priorityCode,billingCode,logicalFileName);
		//BRGUS00120559 - bhattaj - Environment variable changed
		//format the String
		if(buf != null) {
			String formattedString = CoConstants.RETURN_NEWLINE_CHAR + CoBatchConstants.TRAILER_RECORD_SET
					+ CoConstants.FLATFILE_SEPARATOR + jobId + CoConstants.FLATFILE_SEPARATOR
					+ CoBatchConstants.TRAILER_DESIGN_ID + CoConstants.FLATFILE_SEPARATOR
					+ this.asOfDate + CoConstants.FLATFILE_SEPARATOR + currentEnv + CoConstants.FLATFILE_SEPARATOR
					+ physicalFilename + CoConstants.FLATFILE_SEPARATOR
					+ priorityCode + CoConstants.RETURN_NEWLINE_CHAR+"***";

			//append formattedString at the end of the content
			buf.append(formattedString);
		}
		return buf;
	}

	/**
	 * Method getTIERSBatchController
	 * gets an instance of GenericBatchController 
	 * @return GenericBatchController
	 */
	protected GenericBatchController getBatchController(){
		GenericBatchController tbc = super.getBatchController();
		tbc.setGsingleFTP(false);
		return tbc;
	}

	/**
	 * Method initializeCoFileHandler
	 * gets destination file name and makes entry into Fw_Batch_FTP_Run_Control table
	 * also initializes BufferedWriter instance
	 * @return boolean
	 */
	private boolean initializeCoFileHandler() {
		String destinationFileName = "";
		//BRGUS00120559 - bhattaj - get current environment 
		currentEnv = getEnvironment();

		String billingCode = this.getBillingCode(jobId);
		String folderName = this.getFolderName(jobId);

		/* Defect ID : BRGUS00099324
		 * Added By:   WaliaR
		 * Date:       02/27/2008
		 * Change:     Add priorty to Physical file name 
		 */
		String cpcPriortyCd = this.getPriorityCode(jobId);

		try {

			//Defect ID : BRGUS00099324  WaliaR 

			destinationFileName = getDestinationFileName(cpcPriortyCd, billingCode,logicalFileName, folderName);


			//FwBatchFile coNewFile = 	tbc.getOutputSteam(logicalFileName); // COMMENT: REFACTORED THROUGH AUTOMATED APPROACH. OLD VALUE:'BatchFile',NEW NAME:'FwBatchFile'
			FwBatchFile coNewFile = 	tbc.getOutputFileName(logicalFileName); // COMMENT: REFACTORED THROUGH AUTOMATED APPROACH. OLD VALUE:'BatchFile',NEW NAME:'FwBatchFile'
			// makes an entry into Fw_batch_ftp_run_control table
			coNewFile.setTargetFileName(destinationFileName);
			coFileHandler = (BufferedWriter) coNewFile.getBufferedStream();
			absoluteFileName = coNewFile.getPhysicalFileName();
		} catch (GenericBatchException e) {
			CoDebugger.debugException("Exception while getting file instance",e);
			return false;
		}
		return true;
	}

	/**
	 * gets 2o5 record data
	 * @param lastRecord boolean
	 * @param currentCaseProvApp String
	 * @param oldCaseProvApp String
	 * @param docId String
	 * @return String
	 */
	private String getFeedStringWith2o5(boolean lastRecord,
			String currentCaseProvApp, 
			String oldCaseProvApp, 
			String docId,
			String oldCaseProviderAppString){

		String finalReplacedString = CoConstants.EMPTY_STRING;
		String start2o5String = CoConstants.EMPTY_STRING;
		String dynamic2o5String = CoConstants.EMPTY_STRING;
		String endS2o5tring = CoConstants.EMPTY_STRING;


		if((!lastRecord) && currentCaseProvApp.equalsIgnoreCase(oldCaseProvApp)) {

			//get 2o5 start of set 
			start2o5String = CorrespondenceServices.get2o5BarCodeString(oldCaseProvApp, docId,
					CoConstants.BARCODE_CODE_START_RECORD_ID, 
					CoConstants.BARCODE_END_FOR_SAME_CASE);

			//get 2o5 end of set for same case
			endS2o5tring = CorrespondenceServices.get2o5BarCodeString(oldCaseProvApp, docId,
					CoConstants.BARCODE_CODE_RECORD_ID, 
					CoConstants.BARCODE_END_FOR_SAME_CASE);

			//It hits same case after current trigger so dynamic2o5String and endS2o5tring are same
			dynamic2o5String = endS2o5tring;

			//final replaced String
			finalReplacedString = CorrespondenceServices.getReplacedStringWithBarCode(false,
					oldCaseProviderAppString, start2o5String, 
					dynamic2o5String, endS2o5tring );

		} else {

			//get 2o5 start of set 
			start2o5String = CorrespondenceServices.get2o5BarCodeString(oldCaseProvApp, docId,
					CoConstants.BARCODE_CODE_START_RECORD_ID, 
					CoConstants.BARCODE_END_FOR_SAME_CASE);

			//get 2o5 dynamic set 
			dynamic2o5String = CorrespondenceServices.get2o5BarCodeString(oldCaseProvApp, docId,
					CoConstants.BARCODE_CODE_RECORD_ID, 
					CoConstants.BARCODE_END_FOR_SAME_CASE);

			//get 2o5 end of set 
			endS2o5tring = CorrespondenceServices.get2o5BarCodeString(oldCaseProvApp, docId,
					CoConstants.BARCODE_CODE_RECORD_ID, 
					CoConstants.BARCODE_END_OF_SET);

			//final replaced String
			finalReplacedString = CorrespondenceServices.getReplacedStringWithBarCode(true,
					oldCaseProviderAppString, start2o5String, 
					dynamic2o5String, endS2o5tring );

		}
		return finalReplacedString;
	}

	// Kannegantik BRGUS00112795
	/**
	 * BRGUS00119006 - bhattaj - Modified to add FXX009 document id for exception type Info
	 * BRGUS00115388 - bhattaj - Modified to add TF0002 document Id for exception type Info
	 * this method is used to find out if the Exception
	 * is to be logged as Info in FwBatchExceptions
	 * @param docId String
	 * @return boolean
	 */
	private boolean isExceptionLoggedAsInfo(String docId){
		boolean result = false;
		List<String> docIdList = new ArrayList<String>();
		//Gajda WR 144931 DHS-805 Has been discontinued.
		docIdList.add(CoConstants.FXX221_DOC_ID);
		docIdList.add(CoConstants.FXX198_DOC_ID);
		docIdList.add(CoConstants.TF0002_DOC_ID);
		docIdList.add(CoConstants.FXX009_DOC_ID);
		// BRGUS00120559 - NaredlaS - Added TF0001 to this list
		docIdList.add(CoConstants.TF0001_DOC_ID);
		//BRGUS00124279 - bhattaj - Added FXX126 and FXX127 for DHS-1150
		docIdList.add(CoConstants.FXX126_DOC_ID);
		docIdList.add(CoConstants.FXX127_DOC_ID);
		// BRGUS00124279 - NaredlaS - Added FXX032 for DHS - 3503
		docIdList.add(CoConstants.FXX032_DOC_ID);
		// BRGUS00224259 - NaredlaS - Added FXX380 for DHS - 3503-C
		docIdList.add(CoConstants.FXX380_DOC_ID);
		// WR BRGUS00128239 - NaredlaS - Added FXX031 for DHS - 0560
		docIdList.add(CoConstants.FXX031_DOC_ID);
		// WR BRGUS00124499 - bhatta - Added FXX321 for DHS - 0170
		docIdList.add(CoConstants.FXX321_DOC_ID);
		// BRGUS00135868 - bhatta - Added FXX069 for DHS-3503 SER to fix batch exception
		//  BRGUS00146822 - thatiparthis
		docIdList.add(CoConstants.FXX069_DOC_ID);
		docIdList.add(CoConstants.FXX041_DOC_ID);
		docIdList.add(CoConstants.FXX042_DOC_ID);
		docIdList.add(CoConstants.FXX117_DOC_ID);
		docIdList.add(CoConstants.FXX124_DOC_ID); 
		// BRGUS00150415 - thatiparthis - for DHS-1496P AND 1496C
		docIdList.add(CoConstants.FXX013_DOC_ID);
		docIdList.add(CoConstants.FXX009_DOC_ID); 
		// BRGUS00215272 - bhattaj - for DHS-508
		docIdList.add(CoConstants.FXX217_DOC_ID); 


		if(docIdList.contains(docId)){
			result = true;
		}
		return result;
	}
	//	 Kannegantik END

	//	 BRGUS00110572 - thatiparthis  - Begin

	/**
	 * This is is used for getting the officeaddress
	 * 
	 * @param currentCaseProviderAppString File Content for the current record
	 * @return contains file content with office address
	 */
	protected String getOfficeAddress(String currentCaseProviderAppString) {
		int officeAddrIndex = currentCaseProviderAppString.indexOf("700001");
		int addresseeIndex = currentCaseProviderAppString.indexOf("700002");
		if(officeAddrIndex > 0 && addresseeIndex > 0) {
			String officeAddress = currentCaseProviderAppString.substring(officeAddrIndex, addresseeIndex);
			return CorrespondenceServices.replace(officeAddress,"700001", "710001");
		} else {
			return CoConstants.EMPTY_STRING;
		}
	}


	/**
	 * This method is used for getting cover sheet.
	 * 
	 * @param currentCaseProviderAppString file content for current record
	 * @param currentCaseProvAppNum current record caseapp num
	 * @param coTrgrBean CO trigger object
	 * @return contains formatted info for trigger record
	 */
	protected String getVerfCoverSheetString(
			String currentCaseProviderAppString, String currentCaseProvAppNum,
			COCorrespondence coTrgrBean) {

		String verfOfficeAddress = getOfficeAddress(currentCaseProviderAppString);
		String verfClientAddress = coTrgrBean.getVerfClientAddress();
		String verfReplyAddress = CorrespondenceServices.replace(
				verfOfficeAddress, "710001", "710004");
		String verfCoverSheetHeader = "710003|Case Number:     "
				+ currentCaseProvAppNum
				+ "|Date:                   "
				+ CorrespondenceServices.getStrFormattedOfTimestamp(
						CoDateFactory.getSystemTimestamp(),
						CoConstants.DATE_FORMAT_MM);
		return (CoConstants.RETURN_NEWLINE_CHAR + "7777_EN_1_341|"
				+ CoConstants.RETURN_NEWLINE_CHAR + verfOfficeAddress
				+ verfClientAddress + CoConstants.RETURN_NEWLINE_CHAR
				+ verfCoverSheetHeader + CoConstants.RETURN_NEWLINE_CHAR
				+ verfReplyAddress
				+ getBarCode(currentCaseProvAppNum).toString()
				+ CoConstants.RETURN_NEWLINE_CHAR + "***" + CoConstants.RETURN_NEWLINE_CHAR);
	}


	/**
	 * this method is used for getting barcode
	 * 
	 * @param currentCaseProvAppNum CaseAppNum for current CO record
	 * @return Contains formatted barcode with caseAppProviderId
	 */
	protected StringBuffer getBarCode(String currentCaseProvAppNum) {
		StringBuffer printString2o5 = new StringBuffer("");
		//construct 2o5 String
		printString2o5.append(CoConstants.RETURN_NEWLINE_CHAR
				+ CoConstants.BARCODE_CODE_START_RECORD_ID);
		printString2o5.append(CoConstants.FLATFILE_SEPARATOR);
		printString2o5.append(CorrespondenceServices
				.getNineDigitCaseNum(currentCaseProvAppNum));
		printString2o5.append(CoConstants.BARCODE_END_FOR_SAME_CASE);
		printString2o5.append(CoConstants.BARCODE_INSERTER_FEED);

		return printString2o5;
	}
	//	 BRGUS00110572 - thatiparthis  - Begin

	/**
	 * This method is used for getting barcode
	 * 
	 * @param coReqSeq
	 *            long - CoReqSeq for current V_CO_REQUEST record
	 * @return coReqRptCargos CoRequestRecipientsCargo
	 * @author kschopra
	 */
	protected CoRequestRecipientsCargo[] getCoReqRecipientsForReqSeq(
			long coReqSeq) throws CoException {
		Object[] objParams = new Object[1];
		CoRequestRecipientsCollection coReqRecipientsCol = new CoRequestRecipientsCollection();
		CoRequestRecipientsCargo[] coReqRptCargos = null; 

		coReqRecipientsCol.getCargo().setCoReqSeq(coReqSeq);
		objParams[0] = coReqRecipientsCol.getCargo();

		try {
			coReqRptCargos = (CoRequestRecipientsCargo[])coReqRecipientsCol.select("findByAllRecipients", objParams);
		} catch (FrameworkException e) {
			throw new CoException(
					"Exception while fetching CO Request Recipients for the given CO_REQ_SEQ "
							+ e);
		} catch (ApplicationException e) {
			throw new CoException(
					"Exception while fetching CO Request Recipients for the given CO_REQ_SEQ "
							+ e);
		}
		return coReqRptCargos;
	}

	/**
	 * This method is used to formulate the name of the XML file for the
	 * Recipients for a given correspondence.
	 * 
	 * @param coReqSeq
	 * @param coRptSeq
	 * @param generateDate
	 * @return sbFileName StringBuffer
	 * @author kschopra
	 */
	protected String getXMLFileNameForRecipient(long coReqSeq, long coRptSeq,
			Date generateDate) {

		StringBuffer sbFileName = new StringBuffer();


		sbFileName.append(FILE_NAME_1);
		sbFileName.append("_");
		sbFileName.append(coReqSeq);
		sbFileName.append("_");
		sbFileName.append(coRptSeq);
		sbFileName.append("_");

		String strDt = (new SimpleDateFormat(CoConstants.DATE_FORMAT_yyyyMMdd)
		.format(generateDate));
		sbFileName.append(strDt);

		sbFileName.append(CoConstants.XML_FILE_EXTN);

		return sbFileName.toString();
	}

	/**
	 * This method is used to formulate the name of the XML file for the
	 * Recipients for a given correspondence.
	 * 
	 * @param coReqSeq
	 * @param coRptSeq
	 * @param generateDate
	 * @return sbFileName StringBuffer
	 * @author kschopra
	 */
	protected String getXMLFileNameForNotice(String docId,String docName,String caseNum, long coReqSeq, long coRptSeq,
			Date generateDate) {

		String groupCd= "";
		try{
			groupCd = ReferenceTableAccess.getRefDescription(docId,
					CoBatchConstants.RT_GROUPCODES);
		}catch(Exception e){
			CoDebugger.debugException("Error getting Group Code", e);
			CoDebugger.debugInformation(e.getMessage());
		}

		StringBuffer sbFileName = new StringBuffer();		
		sbFileName.append(ftpProperty.getProperty(CoConstants.CO_HP_ENV));
		sbFileName.append("_");
		sbFileName.append(FILE_NAME_2);
		sbFileName.append("_");
		sbFileName.append(docId);
		sbFileName.append("_");
		sbFileName.append(docName);
		sbFileName.append("_");
		sbFileName.append(caseNum);
		sbFileName.append("_");
		sbFileName.append(groupCd);
		sbFileName.append("_");
		sbFileName.append(coReqSeq);
		sbFileName.append("_");
		sbFileName.append(coRptSeq);
		sbFileName.append("_");

		String strDt = (new SimpleDateFormat(CoConstants.DATE_FORMAT_yyyyMMdd)
		.format(generateDate));
		sbFileName.append(strDt);

		sbFileName.append(CoConstants.XML_FILE_EXTN);

		return sbFileName.toString();
	}

	/**
	 * This method is used to fetch handle for the MasterAssembler.
	 * 
	 * @return assembler CoAssembler
	 */
	private CoAssembler getHandleForAssembler() {
		String className = "gov.state.nextgen.co.bo.MasterAssembler";
		CoAssembler assembler = null;
		try {
			assembler = (CoAssembler) Class.forName(className).newInstance();
		} catch (Exception e) {			
			CoDebugger.debugException(e.getMessage(), e);
		}
		return assembler;
	}



	/**
	 * This method will Create the Envelope Xml (Batch 1B) 
	 * It retrieve the correspondence records which have been processed by Batch 1A i.e., the Form/Notices processor. 
	 * @return
	 */
	public int createEnvelope() {
		// TODO Auto-generated method stub
		try {
			writeCoLog(" Batch 1B : Starting createEnvelope method", coLogFile);
			tbc = getBatchController();
			//Getting the Connection
			writeCoLog(" Batch 1B : createEnvelope method : getting connection", coLogFile);
			coConnection = tbc.getConnection();
			//Getting the Xml File Names which will be added to Envelope.xml


			//Map of CASE_NUM/CO_REQ_SEQ/CO_RPT_SEQ = RECIPIENT_TYPE/RECIPIENT_DATA
			Map<String,String> recipientDetailsMap = new HashMap<String, String>();

			//Map of LocationPath= COR_REQ_SEQ_NUM
			Map<String,String> locationPathCorSeqNumMap = new HashMap<String, String>();

			//Map for Recipient Id and the xmls to be sent to the recipient.
			//Multiple xmls to the Recipeint will be stored in the value @ seperated
			List<Map<String,String>> recipientMapList = createRecipientIdXmlNamesMap(recipientDetailsMap, locationPathCorSeqNumMap);

			if(recipientMapList != null){
				createEnvelopeXml(recipientMapList,recipientDetailsMap,locationPathCorSeqNumMap);
			}
			writeCoLog(" Batch 1B : Ending createRecipientIdXmlNamesMap method", coLogFile);
		} catch (Exception e) {
			writeException(
					"Exception in createEnvelope method for Batch 1B ",
					"Exception in createEnvelope method for Batch 1B"+ jobId ,
					e,
					true); 

			CoDebugger.debugException(e.getMessage(), e);
		}

		return 0;
	}

	/**
	 * This method is used to get all the Records for getting List of xml file names which are processed by Batch 1A from V_CO_REQUEST.  
	 * It will create the Recipient Id and Xml Name Map.
	 * @param 
	 * @return xmlNameList
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	protected List<Map<String,String>> createRecipientIdXmlNamesMap(Map<String,String> recipientDetailsMap,Map<String,String> locationPathCorSeqNumMap) throws Exception {

		writeCoLog(" Batch 1B : Starting createRecipientIdXmlNamesMap method", coLogFile);
		List<Map<String,String>> recipientMapList = new ArrayList<Map<String,String>>();
		Map<String,String> recipientIdXmlNameMap = new HashMap<String,String>();
		Map<String,String> recipientIdXmlNameMapForBigForms = new HashMap<String,String>();
		recipientMapList.add(recipientIdXmlNameMap);
		recipientMapList.add(recipientIdXmlNameMapForBigForms);
		try {
			writeCoLog(" Batch 1B : createRecipientIdXmlNamesMap method : Getting List of xml names which are processed by Batch 1A from V_CO_REQUEST table", coLogFile);
			//Getting List of xml names which are processed by Batch 1A from V_CO_REQUEST table which will be a part of Envelope.xml
			VCoRequestCollection vCoReqColl = new VCoRequestCollection(coConnection);
			VCoRequestCargo[] vCoRequestCargoArr =(VCoRequestCargo[]) vCoReqColl.select("findBatchRecordNotPrinted");

			String xmlName = null;
			String recipientId = null;
			writeCoLog(" Batch 1B : createRecipientIdXmlNamesMap method : Creating Map for Recipient Id and the xmls to be sent to the recipient.", coLogFile);
			String caseAppNumber = null;

			for(int i = 0;i < vCoRequestCargoArr.length ; i++){
				//Checking for Case or Application and getting the case or application number accordingly
				if(CoConstants.CASE == vCoRequestCargoArr[i].getRequestTypeCd()){
					caseAppNumber = String.valueOf(vCoRequestCargoArr[i].getCaseNum());
				}else if(CoConstants.APPLICATION == vCoRequestCargoArr[i].getRequestTypeCd()){
					caseAppNumber = vCoRequestCargoArr[i].getAppNum();
				}

				// Set only file name in case of XML and complete path in case of PDF 
				String filePath = vCoRequestCargoArr[i].getAssistanceList();

				//TODO - uncomment the code after ADOBE Live Cycle code changes to read the pdf file path.
				if (filePath != null && filePath.contains(CoConstants.XML_FILE_EXTN)) {
					xmlName = filePath.substring(filePath
							.lastIndexOf(File.separator) + 1);
				} else if (filePath != null && filePath.contains(CoConstants.PDF_FILE_EXTN)) {
					xmlName = filePath;
				}

				locationPathCorSeqNumMap.put(xmlName, vCoRequestCargoArr[i].getCoDetSeq()+CoConstants.SLASH + vCoRequestCargoArr[i].getCoRptSeq()
						+CoConstants.SLASH + vCoRequestCargoArr[i].getRequestTypeCd());

				recipientDetailsMap.put(caseAppNumber + CoConstants.SLASH +vCoRequestCargoArr[i].getCoDetSeq()+ CoConstants.SLASH +
						vCoRequestCargoArr[i].getCoRptSeq(), vCoRequestCargoArr[i].getRecipientType()+ CoConstants.SLASH + 
						vCoRequestCargoArr[i].getRequestTypeCd() + CoConstants.SLASH +vCoRequestCargoArr[i].getReasonCdList());

				if (null != vCoRequestCargoArr[i].getReasonCdList())
				{
					recipientId = (vCoRequestCargoArr[i].getReasonCdList()
							.substring(vCoRequestCargoArr[i].getReasonCdList().lastIndexOf(
									CoConstants.HYPHEN) + 1)).trim();
					if (bigFormsList != null && bigFormsList.contains(vCoRequestCargoArr[i].getT1DocId())){
						if (recipientIdXmlNameMapForBigForms.containsKey(recipientId + CoConstants.SLASH
								+ caseAppNumber)){
							// If there are multiple xmls for a Recipient , the xml
							// names will be @ seperated.
							recipientIdXmlNameMapForBigForms.put(
									recipientId + CoConstants.SLASH + caseAppNumber,
									recipientIdXmlNameMapForBigForms.get(recipientId + CoConstants.SLASH
											+ caseAppNumber)
											+ CoConstants.AT_THE_RATE + xmlName);
						} else{
							recipientIdXmlNameMapForBigForms.put(recipientId + CoConstants.SLASH + caseAppNumber,
									xmlName);
						}
					}else{
						if (recipientIdXmlNameMap.containsKey(recipientId + CoConstants.SLASH
								+ caseAppNumber)){
							// If there are multiple xmls for a Recipient , the xml
							// names will be @ seperated.
							recipientIdXmlNameMap.put(
									recipientId + CoConstants.SLASH + caseAppNumber,
									recipientIdXmlNameMap.get(recipientId + CoConstants.SLASH
											+ caseAppNumber)
											+ CoConstants.AT_THE_RATE + xmlName);
						} else{
							recipientIdXmlNameMap.put(recipientId + CoConstants.SLASH + caseAppNumber,
									xmlName);
						}
					}
				}

			}

		} catch (Exception e) {
			writeException(
					"Exception in createRecipientIdXmlNamesMap method for Batch 1B ",
					"Exception in fetching Correspondence Records or will creating the Map.."+ jobId ,
					e,
					true);

			CoDebugger.debugException(e.getMessage(), e);
			throw e;
		}
		return recipientMapList;
	}

	/**
	 * Create Envelope xml which will group xml names based on Recipients
	 * @param recipientIdXmlNameMap which has Key as Recipeint ID and value as the Xml names delimited by @.
	 * @return 
	 */
	public void createEnvelopeXml(List<Map<String,String>> recipientMapList, Map<String, String> recipientDetailsMap,Map<String, String> locationPathCorSeqNumMap){

		writeCoLog(" Batch 1B : Starting createEnvelopeXml method : There are Xml File names, hence we need to create Envelope.xml", coLogFile);
		//Batch is the root node. It consists of EnvelopeList object.
		Batch batch = new Batch();
		//EnvelopeList consists of Envelope objects.
		EnvelopeList envelopeListBatch = new EnvelopeList();
		java.util.List<Envelope> envelopeList = new ArrayList<Envelope>();
		//Envelope consists of CorrList object.
		Envelope envelope = null;
		//CorrList consists of Corr objects.		
		CorrList corrList = null;
		java.util.List<String> corr = null;
		//Map of Correspondence sequence number with the Name and Address of the Sender.
		Map<String,String> corrSeqNumSenderAddressMap = new HashMap<String, String>();
		String xmlNameStr = null;
		String recipientIdCaseNum = null;
		String[] xmlNameArr = null;
		String[] strArr = null;

		Iterator<Map<String,String>> listIter = recipientMapList.iterator();
		while (listIter.hasNext())
		{
			@SuppressWarnings("unchecked")
			Map<String, String> recipientIdXmlNameMap = (Map<String, String>) listIter.next();
			Iterator<String> recipientIdXmlNameMapIter = recipientIdXmlNameMap.keySet().iterator();
			// Iterating over the Recipient Id and getting the Xml file names.
			while (recipientIdXmlNameMapIter.hasNext())
			{
				// CH-27410-Kunal-Start
				try
				{
					recipientIdCaseNum = recipientIdXmlNameMapIter.next();
					corr = new ArrayList<String>();
					xmlNameStr = recipientIdXmlNameMap.get(recipientIdCaseNum);
					xmlNameArr = xmlNameStr.split(CoConstants.AT_THE_RATE);
					// Adding all the correspondence for a particular recipient.
					for (int i = 0; i < xmlNameArr.length; i++)
					{
						corr.add(xmlNameArr[i]);
					}
					corrList = new CorrList();
					corrList.setCorr(corr);

					envelope = new Envelope();
					envelope.setCorrList(corrList);

					String corSeqNumCoRptNum = locationPathCorSeqNumMap.get(xmlNameArr[0]);
					CoDebugger.debugInformation("createEnvelopeXml-corSeqNumCoRptNum:"
							+ corSeqNumCoRptNum);
					strArr = corSeqNumCoRptNum.split(CoConstants.SLASH);
					// Setting Recipient Seq number and paading it with 3 zeros.
					if (null != strArr && strArr.length >= 2){
						CoDebugger.debugInformation("createEnvelopeXml-strArr[0]:" + strArr[0]);
						CoDebugger.debugInformation("createEnvelopeXml-strArr[1]:" + strArr[1]);
						while (strArr[1].length() < 9){
							strArr[1] = "0".concat(strArr[1]);
						}
						envelope.setId(strArr[1]);
					} else{
						envelope.setId("000000000");
					}
					CoDebugger.debugInformation("createEnvelopeXml-envelopeId:" + envelope.getId());

					envelopeList.add(envelope);

					CoverSheetDetail coverShtDtl = setCoverSheetInformation(
							corrSeqNumSenderAddressMap, xmlNameArr[0], recipientDetailsMap,
							locationPathCorSeqNumMap);
					envelope.setCoverSheetDetail(coverShtDtl);
				} catch (Exception e){
					writeException(
							"Exception in createEnvelopeXml method for CO Batch while referencing individual xml",
							"Exception in method.." + jobId + "--" + recipientIdCaseNum, e, true);
				}
				// CH-27410-Kunal-End
			}
		}

		envelopeListBatch.setEnvelope(envelopeList);
		batch.setEnvelopeList(envelopeListBatch);

		try{
			JAXBContext jaxbContext = JAXBContext.newInstance( "gov.state.nextgen.co.util.xsd.schema" );
			Marshaller marshaller = jaxbContext.createMarshaller();
			String envelopeXmlFilePath = (String)ftpProperty.getProperty(CoConstants.CO_TEMP_XML_PATH);

			File file = new File( envelopeXmlFilePath + (String)ftpProperty.getProperty(CoConstants.CO_ENVELOPE_XML_NAME));
			if(file.createNewFile()){
				writeCoLog(" Batch 1B : End of createEnvelopeXml method : Envelope.xml file does not exists. Created new Envelope.xml.", coLogFile);
			}
			marshaller.marshal( batch, file );
			writeCoLog(" Batch 1B : End of createEnvelopeXml method : The Envelope.xml is created.", coLogFile);

		}catch(Exception e){
			writeException(
					"Exception in createEnvelopeXml method for Batch 1B ",
					"Exception in marshalling.."+ jobId ,
					e,
					true);
			CoDebugger.debugException(e.getMessage(), e);
		}
	}

	/**
	 * Creation of Cover Sheet Information
	 * @param corrSeqNumSenderAddressMap
	 * @param corSeqNum
	 */
	private CoverSheetDetail setCoverSheetInformation(
			Map<String, String> corrSeqNumSenderAddressMap, String xmlFileName,
			Map<String, String> recipientDetailsMap,
			Map<String, String> locationPathCorSeqNumMap)
	{
		writeCoLog(" Batch 1B : Starting setCoverSheetInformation method.",
				coLogFile);

		String corSeqRecpSeqStr = locationPathCorSeqNumMap.get(xmlFileName);
		String[] xmlNameSplitArr = corSeqRecpSeqStr.split(CoConstants.SLASH);

		String coReqSeqNum = null;
		String recipientSeqNum = null;
		if (null != xmlNameSplitArr[0]){
			coReqSeqNum = xmlNameSplitArr[0];
		}
		if (null != xmlNameSplitArr[1]){
			recipientSeqNum = xmlNameSplitArr[1];
		}
		CoverSheetDetail coverShtDtl = new CoverSheetDetail();
		// Setting Sender Information
		String caseAppNumber = setSenderDetail(coReqSeqNum, coverShtDtl);
		coverShtDtl.setCaseNum(caseAppNumber);
		// Setting Recipient Information
		setRecipientDetail(coverShtDtl, caseAppNumber, coReqSeqNum,
				recipientSeqNum, recipientDetailsMap);
		writeCoLog(" Batch 1B : End of setCoverSheetInformation method.",
				coLogFile);
		return coverShtDtl;
	}

	/**
	 * 
	 * @param coverShtDtl
	 * @param caseNumber
	 * @param coReqSeqNum
	 * @param recipientSeqNum
	 */
	private void setRecipientDetail(CoverSheetDetail coverShtDtl,String caseAppNumber,String coReqSeqNum,String recipientSeqNum,Map<String,String> recipientDetailsMap) {
		//1. Get all correspondence for CoSeqNum  
		boolean isHOH = false;
		boolean isAuthRep = false;
		try {

			String recpTypAndRecpData = recipientDetailsMap.get(caseAppNumber+CoConstants.SLASH +coReqSeqNum+CoConstants.SLASH +recipientSeqNum);
			String[] strArr = recpTypAndRecpData.split(CoConstants.SLASH);
			String recipientType = strArr[0];
			String recipientId =  (recpTypAndRecpData.substring(recpTypAndRecpData.lastIndexOf(CoConstants.HYPHEN)+1)).trim();
			String requestTypeCd = strArr[1];

			if(CoConstants.C.equalsIgnoreCase(requestTypeCd)){
				//Checking if Authorized Representative
				if(CoConstants.AUTHORISED_REP.equalsIgnoreCase(recipientType)){
					isAuthRep = true;
				}else if(CoConstants.CLIENT.equalsIgnoreCase(recipientType)){
					isHOH = true;
				}

				DcCaseAddressesCollection dcCaseAddrColl = new DcCaseAddressesCollection(coConnection);
				DcCaseAddressesCargo dcCaseAddressesCargo = new DcCaseAddressesCargo();
				DcCaseAddressesCargo[] dcCaseAddressesArr = null;

				if(isHOH){
					//Get Address of Head of Household
					dcCaseAddressesCargo.setCaseNum(Long.parseLong(caseAppNumber));  
					dcCaseAddressesCargo.setEffBeginDt(new Timestamp(fwd.getTimeInMillis()));
					dcCaseAddressesCargo.setEffEndDt(new Timestamp(fwd.getTimeInMillis()));
					dcCaseAddrColl.setCargo(dcCaseAddressesCargo);
					dcCaseAddressesArr = (DcCaseAddressesCargo[]) dcCaseAddrColl.select("findByCaseNumber");

					//Setting Recipient Name
					coverShtDtl.setRecipientName(getRecipientName(recipientId));


				}else if(isAuthRep){
					//Get Recipients Organization
					DcAuthRepCollection dcAuthRepColl = new DcAuthRepCollection(coConnection);
					DcAuthRepCargo dcAuthRepCargo = new DcAuthRepCargo();
					dcAuthRepCargo.setCaseNum(Long.parseLong(caseAppNumber));
					dcAuthRepCargo.setAuthrepSeqNum(Long.parseLong(recipientId));
					dcAuthRepColl.setCargo(dcAuthRepCargo);
					long caseAddrSeqNum = 0;
					DcAuthRepCargo[] dcAuthRepArr = (DcAuthRepCargo[]) dcAuthRepColl.select("findByCaseNumAuthSeqNumByEffDates");
					//dcAuthRepArr will have multiple records out of which only one is valid.
					//If there is only one record in dcAuthRepArr and HistNavInd is S then take caseAddrSeqNum from dcAuthRepArr[0]
					//Else iterate through dcAuthRepArr and select the cargo for which HistNavInd is P
					if(null != dcAuthRepArr && dcAuthRepArr.length > 0 && null != dcAuthRepArr[0] ){
						if(dcAuthRepArr.length == 1 && dcAuthRepArr[0].getHistNavInd() == 'S'){
							caseAddrSeqNum = dcAuthRepArr[0].getCaseAddrSeqNum();
						}else{
							for(int i = 0; i < dcAuthRepArr.length ; i++ ){
								if(null != dcAuthRepArr[i] && dcAuthRepArr[i].getHistNavInd() == 'P'){
									dcAuthRepArr[0] = dcAuthRepArr[i];
									break;
								}
							}
						}
					}

					if(null != dcAuthRepArr && dcAuthRepArr.length > 0 && null != dcAuthRepArr[0] ){
						caseAddrSeqNum = dcAuthRepArr[0].getCaseAddrSeqNum();
						//Setting Recipient Organisation	
						coverShtDtl.setRecipientOrg(dcAuthRepArr[0].getAuthrepOrgName());
						//Setting Recipient Name
						coverShtDtl.setRecipientName((String.valueOf(COFormsUtility
								.getFormattedName(dcAuthRepArr[0].getAuthrepFirstName(),
										dcAuthRepArr[0].getAuthrepMidName(),
										dcAuthRepArr[0].getAuthrepLastName()))));
					}

					//Get Address of Authorized Representative
					dcCaseAddressesCargo.setCaseNum(Long.parseLong(caseAppNumber));
					dcCaseAddressesCargo.setCaseAddrSeqNum(caseAddrSeqNum);
					dcCaseAddressesCargo.setEffEndDt(new Timestamp(fwd.getTimeInMillis()));
					dcCaseAddrColl.setCargo(dcCaseAddressesCargo);
					dcCaseAddressesArr = (DcCaseAddressesCargo[]) dcCaseAddrColl.select("findByCaseNumAndCaseAddrSeqNum");
				}

				//Address Formatting
				if(null != dcCaseAddressesArr && dcCaseAddressesArr.length > 0 && null != dcCaseAddressesArr[0]){
					dcCaseAddressesCargo = dcCaseAddressesArr[0];
					Map<String, String> addressMap = new HashMap<String, String>(); 
					addressMap.put("addressFormat",dcCaseAddressesCargo.getAddrFormat());
					//CH-25860-Kunal-Start
					addressMap.put(CoConstants.ADDRESS_MODE,
							CoConstants.ADDRESS_MODE_RECEIVER);
					//CH-25860-Kunal-End
					addressMap.put(CoConstants.ADDRESS_LINE,dcCaseAddressesCargo.getAddrLine()); 
					addressMap.put(CoConstants.ADDRESS_LINE_1,dcCaseAddressesCargo.getAddrLine1()); 
					addressMap.put(CoConstants.ADDRESS_LINE_2,dcCaseAddressesCargo.getAddrLine2()); 
					addressMap.put(CoConstants.ADDRESS_CARE_OF_LINE,dcCaseAddressesCargo.getAddrCareOfLine()); 
					addressMap.put(CoConstants.ADDRESS_ST_DIR_CD, dcCaseAddressesCargo.getAddrStDirCd()); 
					addressMap.put(CoConstants.ADDRESS_ST_TYPE_CD,dcCaseAddressesCargo.getAddrStTypeCd()); 
					addressMap.put(CoConstants.ADDRESS_POST_DIR_CD,dcCaseAddressesCargo.getAddrPostDirCd()); 
					addressMap.put(CoConstants.ADDRESS_DWELLING_TYPE_CD,dcCaseAddressesCargo.getAddrDwellingTypeCd()); 
					addressMap.put(CoConstants.ADDRESS_STATE_CD,dcCaseAddressesCargo.getAddrStateCd());
					addressMap.put(CoConstants.ADDRESS_ST_NUM,dcCaseAddressesCargo.getAddrStNum());
					addressMap.put(CoConstants.ADDRESS_STREET_NM,dcCaseAddressesCargo.getAddrStNm());
					addressMap.put(CoConstants.ADDRESS_ST_NUM_FRAC,dcCaseAddressesCargo.getAddrStNumFrac());
					addressMap.put(CoConstants.ADDRESS_APT_NUM,dcCaseAddressesCargo.getAddrAptNum());
					addressMap.put(CoConstants.ADDRESS_CITY,dcCaseAddressesCargo.getAddrCity());
					addressMap.put(CoConstants.ADDRESS_ZIP_4,dcCaseAddressesCargo.getAddrZip4());
					addressMap.put(CoConstants.ADDRESS_ZIP_5,dcCaseAddressesCargo.getAddrZip5());
					addressMap.put(CoConstants.ADDRESS_COUNTRY_CD,dcCaseAddressesCargo.getAddrCountryCd());
					addressMap.put(CoConstants.ADDRESS_MILITRY_PO_CD,dcCaseAddressesCargo.getAddrMilitaryPoCd());
					addressMap.put(CoConstants.ADDRESS_STATE_MILITRY_CD,dcCaseAddressesCargo.getAddrStateMilitaryCd());
					String[] addrLinArr = String.valueOf(COFormsUtility.getFormattedAddress(addressMap)).split("\n");
					if(null != addrLinArr && addrLinArr.length > 0){
						if(null != addrLinArr[0])
						{
							coverShtDtl.setRecipientAddLine1(addrLinArr[0].trim());
						}
						if(addrLinArr.length > 1 && null != addrLinArr[1])
						{
							coverShtDtl.setRecipientAddLine2(addrLinArr[1].trim());
						}
						if(addrLinArr.length > 2 && null != addrLinArr[2])
						{
							coverShtDtl.setRecipientAddLine3(addrLinArr[2].trim());
						}
						if(addrLinArr.length==4 && null != addrLinArr[3])
						{
							coverShtDtl.setRecipientAddLine4(addrLinArr[3].trim());
						}
					}
				}	
			}else if(CoConstants.A.equalsIgnoreCase(requestTypeCd)){

				ArAppAddrCollection arAppAddrColl = new ArAppAddrCollection(coConnection);
				ArAppAddrCargo arAppAddrCargo = new ArAppAddrCargo();
				ArAppAddrCargo[] arAppAddrArr = null;
				arAppAddrCargo.setAppNum(caseAppNumber);
				arAppAddrColl.setCargo(arAppAddrCargo);
				arAppAddrArr = (ArAppAddrCargo[]) arAppAddrColl.select("findByAppNumAdrrTypMaPa");

				//Setting Recipient Name
				coverShtDtl.setRecipientName(getRecipientName(recipientId));

				//Address Formatting
				if(null != arAppAddrArr && arAppAddrArr.length > 0 && null != arAppAddrArr[0]){
					arAppAddrCargo = arAppAddrArr[0];
					Map<String, String> addressMap = new HashMap<String, String>(); 
					addressMap.put("addressFormat",arAppAddrCargo.getAddrFormatCd());
					//CH-25860-Kunal-Start
					addressMap.put(CoConstants.ADDRESS_MODE,
							CoConstants.ADDRESS_MODE_RECEIVER);
					//CH-25860-Kunal-End
					addressMap.put(CoConstants.ADDRESS_LINE,arAppAddrCargo.getAddrLine()); 
					addressMap.put(CoConstants.ADDRESS_LINE_1,arAppAddrCargo.getAddrLine1()); 
					addressMap.put(CoConstants.ADDRESS_CARE_OF_LINE,arAppAddrCargo.getAddrCareOfLine()); 
					addressMap.put(CoConstants.ADDRESS_ST_DIR_CD, arAppAddrCargo.getAddrStDirCd()); 
					addressMap.put(CoConstants.ADDRESS_ST_TYPE_CD,arAppAddrCargo.getAddrStTypeCd()); 
					addressMap.put(CoConstants.ADDRESS_POST_DIR_CD,arAppAddrCargo.getAddrPostDirCd()); 
					addressMap.put(CoConstants.ADDRESS_DWELLING_TYPE_CD,arAppAddrCargo.getAddrDwellingTypeCd()); 
					addressMap.put(CoConstants.ADDRESS_STATE_CD,arAppAddrCargo.getAddrStateCd());
					addressMap.put(CoConstants.ADDRESS_ST_NUM,arAppAddrCargo.getAddrStNum());
					addressMap.put(CoConstants.ADDRESS_STREET_NM,arAppAddrCargo.getAddrStNm());
					addressMap.put(CoConstants.ADDRESS_ST_NUM_FRAC,arAppAddrCargo.getAddrStNumFrac());
					addressMap.put(CoConstants.ADDRESS_APT_NUM,arAppAddrCargo.getAddrAptNum());
					addressMap.put(CoConstants.ADDRESS_CITY,arAppAddrCargo.getAddrCity());
					addressMap.put(CoConstants.ADDRESS_ZIP_4,arAppAddrCargo.getAddrZip4());
					addressMap.put(CoConstants.ADDRESS_ZIP_5,arAppAddrCargo.getAddrZip5());
					addressMap.put(CoConstants.ADDRESS_COUNTRY_CD,arAppAddrCargo.getAddrCntry());
					addressMap.put(CoConstants.ADDRESS_MILITRY_PO_CD,arAppAddrCargo.getAddrMilitary());
					String[] addrLinArr = String.valueOf(COFormsUtility.getFormattedAddress(addressMap)).split("\n");
					if(null != addrLinArr && addrLinArr.length > 0){
						if(null != addrLinArr[0])
						{
							coverShtDtl.setRecipientAddLine1(addrLinArr[0].trim());
						}
						if(addrLinArr.length > 1 && null != addrLinArr[1])
						{
							coverShtDtl.setRecipientAddLine2(addrLinArr[1].trim());
						}
						if(addrLinArr.length > 2 && null != addrLinArr[2])
						{
							coverShtDtl.setRecipientAddLine3(addrLinArr[2].trim());
						}
						if(addrLinArr.length==4 && null != addrLinArr[3])
						{
							coverShtDtl.setRecipientAddLine4(addrLinArr[3].trim());
						}
					}
				}	

			}
		} catch (ApplicationException e) {
			writeException(
					"Exception in setRecipientDetail method for Batch 1B ",
					"Exception in creating the Recipient Address.."+ jobId ,
					e,
					true);
		} catch (FrameworkException e) {
			writeException(
					"Exception in setRecipientDetail method for Batch 1B ",
					"Exception in creating the Recipient Address.."+ jobId ,
					e,
					true);
		} catch (CoException e) {
			writeException(
					"Exception in setRecipientDetail method for Batch 1B ",
					"Exception in creating the Recipient Address.."+ jobId ,
					e,
					true);
		}

	}
	/**
	 * Get the Sender Detail like the worker Name and Office Address of the worker is set in the CoverSheetDetail.
	 * @param corSeqNum
	 * @param coverShtDtl
	 */
	private String setSenderDetail(String corSeqNum, CoverSheetDetail coverShtDtl) {
		writeCoLog(" Batch 1B : Start of setSenderDetail method.", coLogFile);
		String caseAppNumber = null;
		boolean isCase = false;
		boolean isApplication = false;
		//CH-25846-Kunal-Start
		CoDAOServices coDaoServices = new CoDAOServices();
		Object[] moOfficesCargoArr = null;
		MoOfficesCargo moOfficesCargo = null;
		//CH-25846-Kunal-End
		try {
			//1. Get the Case number from CO_REQUEST_HISTORY for CO_SEQ_NUM
			CoRequestHistoryCollection coReqHistColl = new CoRequestHistoryCollection(coConnection);
			CoRequestHistoryCargo coReqCargo = new CoRequestHistoryCargo();
			coReqCargo.setCoReqSeq(Long.parseLong(corSeqNum));
			coReqHistColl.setCargo(coReqCargo);
			writeCoLog(" Batch 1B : ", coLogFile);
			CoRequestHistoryCargo[] coReqHistArr = (CoRequestHistoryCargo[]) coReqHistColl.select("findByReqSeq");
			if(null != coReqHistArr && coReqHistArr.length > 0 && null != coReqHistArr[0]){
				//Check Request Type Cd where Application or Case and Set Case number or Application Number accordingly.
				if(CoConstants.CASE == coReqHistArr[0].getRequestTypeCd()){
					caseAppNumber = String.valueOf(coReqHistArr[0].getCaseNum());
					isCase = true;
				}else if(CoConstants.APPLICATION == coReqHistArr[0].getRequestTypeCd()){
					caseAppNumber = coReqHistArr[0].getAppNum();
					isApplication = true;
				}
				CoDebugger
				.debugInformation("setSenderDetail-->Case Number being processed:"
						+ caseAppNumber);
			}

			long empId = 0;
			long officeNum = 0;
			//If Case number is not zero then it is a Case
			if(isCase){

				//2. Get EMP_ID from MO_EMPLOYEE_CASES for case_num
				MoEmployeeCasesCollection moEmpCaseColl  = new MoEmployeeCasesCollection(coConnection);
				MoEmployeeCasesCargo moEmpCaseCargo = new MoEmployeeCasesCargo();
				moEmpCaseCargo.setCaseNum(Long.valueOf(caseAppNumber));
				moEmpCaseCargo.setAssignBeginDt(new Timestamp(fwd.getTimeInMillis()));
				moEmpCaseCargo.setAssignEndDt(new Timestamp(fwd.getTimeInMillis()));
				moEmpCaseColl.setCargo(moEmpCaseCargo);

				MoEmployeeCasesCargo[] moEmpCaseCarArr = (MoEmployeeCasesCargo[]) moEmpCaseColl.select("findByCaseNumDateForCO");
				if(null != moEmpCaseCarArr && moEmpCaseCarArr.length > 0 && null != moEmpCaseCarArr[0]){
					empId = moEmpCaseCarArr[0].getEmpId();
				}

			}else if(isApplication){

				//2. Get EMP_ID from MO_EMPLOYEE_APPS for Application number
				MoEmployeeAppsCollection moEmpAppColl  = new MoEmployeeAppsCollection(coConnection);
				MoEmployeeAppsCargo moEmpAppCargo = new MoEmployeeAppsCargo();
				moEmpAppCargo.setAppNum(caseAppNumber);
				moEmpAppColl.setCargo(moEmpAppCargo);

				MoEmployeeAppsCargo[] moEmpAppCarArr = (MoEmployeeAppsCargo[]) moEmpAppColl.select("findByAppNumWithEffectiveDates");
				if (null != moEmpAppCarArr && moEmpAppCarArr.length > 0
						&& null != moEmpAppCarArr[0]){
					empId = moEmpAppCarArr[0].getEmpId();
				} else{
					Object[] arApplicationForAidCargoArr = coDaoServices
							.getArApplForAid(caseAppNumber);
					if (arApplicationForAidCargoArr != null
							&& arApplicationForAidCargoArr.length > 0){
						ArApplicationForAidCargo arApplicationForAidCargo = (ArApplicationForAidCargo) arApplicationForAidCargoArr[0];
						if (arApplicationForAidCargo != null){
							officeNum = arApplicationForAidCargo.getOfficeNum();
						}
					}

				}
			}

			//3. Get the First Name + Midd Name + Last Name from MO_EMPLOYEES
			MoEmployeesCollection moEmpColl = new MoEmployeesCollection(coConnection);
			MoEmployeesCargo moEmpCargo = new MoEmployeesCargo();
			moEmpCargo.setEmpId(empId);
			moEmpColl.setCargo(moEmpCargo);
			MoEmployeesCargo[] moEmpCargoArr =(MoEmployeesCargo[]) moEmpColl.select("findByEmpId");
			if(null != moEmpCargoArr && moEmpCargoArr.length > 0 && null != moEmpCargoArr[0]){
				//CH-25846-Kunal-Start


				moOfficesCargoArr = coDaoServices.getOfficeInfo(moEmpCargoArr[0].getPriOfficeNum());
				moOfficesCargo = new MoOfficesCargo();

				if (null != moOfficesCargoArr && moOfficesCargoArr.length > 0)
				{
					moOfficesCargo = (MoOfficesCargo) moOfficesCargoArr[0];
				}
				coverShtDtl.setSenderName(moOfficesCargo.getOfficeName());
				//CH-25846-Kunal-End
				String phoneNumber = (null != moEmpCargoArr[0].getPhNum()?moEmpCargoArr[0].getPhNum():CoConstants.EMPTY_STRING);
				if(0 != moEmpCargoArr[0].getPhoneNumExt()){
					phoneNumber += moEmpCargoArr[0].getPhoneNumExt();
				}
				coverShtDtl.setWorkerPhone(COFormsUtility.formatPhoneNo(phoneNumber)); //Setting Worker Phone Number
				//4. Get the Office_Num from MO_EMPLOYEE_OFFICES for the EMP_ID
				//CH-26347-Kunal-Start
				officeNum = moEmpCargoArr[0].getPriOfficeNum();
				//CH-26347-Kunal-End
			}

			//5. Get the MO_OFFICE_ADDRESSES for Office_Num and Address_Type_CD = MA or PA
			MoOfficeAddressesCollection moOfficeAddrColl = new MoOfficeAddressesCollection(coConnection);
			MoOfficeAddressesCargo moOfficeAddrCargo = new MoOfficeAddressesCargo();
			moOfficeAddrCargo.setOfficeNum(officeNum);
			moOfficeAddrColl.setCargo(moOfficeAddrCargo);
			MoOfficeAddressesCargo[] moOfficeAddrCargoArr =(MoOfficeAddressesCargo[]) moOfficeAddrColl.select("findByOffNumAdrrTypMaPa");


			if(null != moOfficeAddrCargoArr && moOfficeAddrCargoArr.length > 0 && null != moOfficeAddrCargoArr[0]){
				moOfficeAddrCargo = moOfficeAddrCargoArr[0];
				Map<String, String> addressMap = new HashMap<String, String>(); 
				addressMap.put(CoConstants.ADDRESS_LINE,moOfficeAddrCargo.getAddrLine()); 
				addressMap.put(CoConstants.ADDRESS_ST_DIR_CD, moOfficeAddrCargo.getAddrStDirCd()); 
				addressMap.put(CoConstants.ADDRESS_ST_TYPE_CD,moOfficeAddrCargo.getAddrStTypeCd()); 
				addressMap.put(CoConstants.ADDRESS_POST_DIR_CD,moOfficeAddrCargo.getDrivingDirections()); 
				addressMap.put(CoConstants.ADDRESS_DWELLING_TYPE_CD,moOfficeAddrCargo.getAddrDwellingTypeCd()); 
				addressMap.put(CoConstants.ADDRESS_STATE_CD,moOfficeAddrCargo.getAddrStateCd());
				addressMap.put(CoConstants.ADDRESS_ST_NUM,moOfficeAddrCargo.getAddrStNum());
				addressMap.put(CoConstants.ADDRESS_STREET_NM,moOfficeAddrCargo.getAddrStNm());
				addressMap.put(CoConstants.ADDRESS_ST_NUM_FRAC,moOfficeAddrCargo.getAddrStNumFrac());
				addressMap.put(CoConstants.ADDRESS_APT_NUM,moOfficeAddrCargo.getAddrAptNum());
				addressMap.put(CoConstants.ADDRESS_CITY,moOfficeAddrCargo.getAddrCity());
				addressMap.put(CoConstants.ADDRESS_ZIP_4,moOfficeAddrCargo.getAddrZip4());
				addressMap.put(CoConstants.ADDRESS_ZIP_5,moOfficeAddrCargo.getAddrZip5());
				addressMap.put(CoConstants.ADDRESS_COUNTRY_CD,moOfficeAddrCargo.getAddrCountyCd());
				String[] addrLinArr = String.valueOf(COFormsUtility.getFormattedAddress(addressMap)).split("\n");
				if(null != addrLinArr){
					if(null != addrLinArr[0] && addrLinArr.length > 0)
					{
						coverShtDtl.setSenderAddLine1(addrLinArr[0].trim());
					}
					if(addrLinArr.length > 1 && null != addrLinArr[1])
					{
						coverShtDtl.setSenderAddLine2(addrLinArr[1].trim());
					}
					if(addrLinArr.length > 2 && null != addrLinArr[2])
					{
						coverShtDtl.setSenderAddLine3(addrLinArr[2].trim());
					}
					if(addrLinArr.length ==4 && null != addrLinArr[3])
					{
						coverShtDtl.setSenderAddLine4(addrLinArr[3].trim());
					}
				}
			}

		} catch (ApplicationException e) {
			writeException(
					"Exception in setSenderDetail method for Batch 1B ",
					"Exception in creating the Sender Address.."+ jobId ,
					e,
					true);
		} catch (FrameworkException e) {
			writeException(
					"Exception in setSenderDetail method for Batch 1B ",
					"Exception in creating the Sender Address.."+ jobId ,
					e,
					true);
		} catch (CoException e) {
			writeException(
					"Exception in setSenderDetail method for Batch 1B ",
					"Exception in creating the Sender Address.."+ jobId ,
					e,
					true);
		}
		writeCoLog(" Batch 1B : End of setSenderDetail method.", coLogFile);
		return caseAppNumber;

	}

	/**
	 * This method is used to update the CHIMES database for the Correspondences
	 * processed succesfully. It will update the switches, location path of PDFs
	 * generated for Co Request and Co Request Recipients.
	 * 
	 * @return resultFlag int
	 * @throws NumberFormatException
	 * @throws CoException
	 * @author kschopra
	 */
	public int updateChimesDbWithPdfLocation() throws NumberFormatException,
	CoException {

		int resultFlag = 0;
		String coReqRptSeqNum = null;

		CoUpdateDBBean coUpdateDBBean = null;
		// Get Connection
		try {
			coConnection = tbc.getConnection();
			coConnection.setAutoCommit(false);
			coDAOServices.setConnection(coConnection);
		} catch (Exception e) {
			return this.handleBatchException(e, 1, null, null);
		}		
		// Get CSV file from Adobe.
		String srcFilePath = (String) ftpProperty.getProperty(CoConstants.CO_CSV_SRC_FILE_PATH);
		String destFilePath = (String) ftpProperty.getProperty(CoConstants.CO_CSV_DEST_FILE_PATH);
		String csvFileName = (String) ftpProperty.getProperty(CoConstants.CO_UPD_DB_CSV_FILE_NAME);

		String csvDestFilePath = getCSVFilePath(destFilePath, csvFileName);

		CoDebugger.debugInformation("Checking if .csv files already exists on batch server - "+csvDestFilePath);
		File file = new File (csvDestFilePath);
		if (!file.exists()){			


			writeException("Exception while getting CSV file from Adobe server", "File "
					+ csvDestFilePath + " doesn't exist on batch server for Job:" + jobId, null,
					true);
			CoDebugger.debugInformation("File Not Found - "+csvDestFilePath+", returning -1");
			return (-1);

		}else{
			CoDebugger.debugInformation("File exists on batch server...path - "+csvDestFilePath);
		}
		try {

			reportDiscrepancy(csvDestFilePath);
			//Map of CO_REQ_SEQ and Location of the PDF.
			createCoRequestUpdateMap(csvDestFilePath);

			//Update CO RECIPIENT SWITCHES
			Iterator<String> itr = coRecipientsUpdateMap.keySet().iterator(); 
			while(itr.hasNext()){

				coReqRptSeqNum = itr.next();
				coUpdateDBBean = coRecipientsUpdateMap.get(coReqRptSeqNum);

				if (coUpdateDBBean != null) {
					try
					{
						tbc.savepoint(null, "CoSavePointForUpdateCHIMESDb");
						//Update CO RECIPIENT SWITCHES
						coDAOServices.updateCoRecipientSwitches(
								Integer.parseInt(coUpdateDBBean.getRecipientSeq()),
								Integer.parseInt(coUpdateDBBean.getRequestHistorySeq()),
								coUpdateDBBean.getPdfFilPath());

						//Update CO REQUEST SWITCHES
						coDAOServices.updateCoRequestSwitches(Integer
								.parseInt(coUpdateDBBean.getRequestHistorySeq()),Integer.parseInt(coUpdateDBBean.getRecipientSeq()));

						coLogicalCommit();

					} catch (CoException e)
					{
						try{
							tbc.rollback(null, "CoSavePointForUpdateCHIMESDb");
						} catch (Exception e1) {
							CoDebugger.debugException("Could not roll back - ", e1);
						}
						writeException(
								"Could not udpate CO switches for jobId:" + jobId,
								"Exception while updating switches for coReqSeq:"
										+ coUpdateDBBean.getRequestHistorySeq()
										+ " and coRptSeq:"
										+ coUpdateDBBean.getRecipientSeq(), e,
										false);
					} catch (Exception e) 
					{
						try{
							tbc.rollback(null, "CoSavePointForUpdateCHIMESDb");
						} catch (Exception e1) {
							CoDebugger.debugException("Could not roll back - ", e1);
						}
						writeException(
								"Could not udpate CO switches for jobId:" + jobId,
								"Exception while updating switches for coReqSeq:"
										+ coUpdateDBBean.getRequestHistorySeq()
										+ " and coRptSeq:"
										+ coUpdateDBBean.getRecipientSeq(), e,
										false);
					} 		
				}
			}
		} catch (Exception e) {
			if(null!=coUpdateDBBean){
			CoDebugger.debugException(
					"Exception during updateChimesDbWithPdfLocation()- "
							+ coUpdateDBBean.getRecipientSeq(), e);
			}
			resultFlag = -1;
		} 		
		return resultFlag;
	}


	/**
	 * This method is used to update the ND database for the Correspondences
	 * processed succesfully. It will update the switches, location path of PDFs
	 * generated for Co Request and Co Request Recipients.
	 * 
	 * @return resultFlag int
	 * @throws NumberFormatException
	 * @throws CoException
	 * @author kschopra
	 */
	public int updateDbWithPdfLocation() throws NumberFormatException, CoException {

		int resultFlag = 0;
		String coReqRptSeqNum = null;
		CoUpdateDBBean coUpdateDBBean = null;

		// Get Connection
		try {
			coConnection = tbc.getConnection();
			coConnection.setAutoCommit(false);
			coDAOServices.setConnection(coConnection);
		} catch (Exception e) {
			return this.handleBatchException(e, 1, null, null);
		}

		try {

			CoDebugger.debugInformation("In reportDiscrepancy-->");
			int totalNumberOfXMLs = 0;
			int totalNumberOfPDFs = 0;
			String xmlFilePath = (String) ftpProperty.getProperty(CoConstants.CO_TEMP_XML_PATH);
			String pdfFilePath = (String) ftpProperty.getProperty(CoConstants.CO_PDF_FILES_PATH);
			String emailPdfFilePath = (String) ftpProperty.getProperty(CoConstants.CO_EMAIL_PDF_FILES_PATH);
			File xmlDir = new File(xmlFilePath);
			File pdfDir = new File(pdfFilePath);
			File emailDir = new File(emailPdfFilePath);
			File[] xmlFiles = null;
			File[] pdfFiles = null;
			File[] emailPdfFiles = null;
			File[] allPDFs = null;
			if (xmlDir.isDirectory()) {
				xmlFiles = xmlDir.listFiles();
				totalNumberOfXMLs = ((xmlFiles.length)); 
				CoDebugger.debugInformation("Number of XML files in " +xmlFilePath+" --->"+totalNumberOfXMLs);
			}

			if (pdfDir.isDirectory()) {
				pdfFiles = pdfDir.listFiles();
				CoDebugger.debugInformation("Number of PDF files in " +pdfFilePath+" --->"+pdfFiles.length);
			}

			if (emailDir.isDirectory()) {
				emailPdfFiles = emailDir.listFiles();
				CoDebugger.debugInformation("Number of PDF files in " +emailPdfFilePath+" --->"+emailPdfFiles.length);
			}

			totalNumberOfPDFs = emailPdfFiles.length + pdfFiles.length; 
			CoDebugger.debugInformation("Total number of XMLs --->"+totalNumberOfXMLs);
			CoDebugger.debugInformation("Total number of PDFs --->"+totalNumberOfPDFs);
			CoDebugger.debugInformation("Diffecernce --->"+ (totalNumberOfXMLs - totalNumberOfPDFs));

			if(totalNumberOfXMLs > totalNumberOfPDFs){
				CoDebugger.debugInformation("Begin: Waiting for more PDFs");
				int counter = 0;
				while (totalNumberOfXMLs != totalNumberOfPDFs) {
					Thread.sleep(10000);
					if (counter == 30) {
						break;
					}
					counter++;
					CoDebugger.debugInformation("Counter ---> "+counter+" of 30");
				}

				CoDebugger.debugInformation("End: Waiting for more PDFs");

				if (xmlDir.isDirectory()) {
					xmlFiles = xmlDir.listFiles();
					totalNumberOfXMLs = xmlFiles.length; 
					CoDebugger.debugInformation("After Wait: Number of XML files in " +xmlFilePath+" --->"+totalNumberOfXMLs);
				}

				if (pdfDir.isDirectory()) {
					pdfFiles = pdfDir.listFiles();
					CoDebugger.debugInformation("After Wait: Number of PDF files in " +pdfFilePath+" --->"+pdfFiles.length);
				}

				if (emailDir.isDirectory()) {
					emailPdfFiles = emailDir.listFiles() ;
					CoDebugger.debugInformation("Number of PDF files in " +emailPdfFilePath+" --->"+emailPdfFiles.length);
				}

				totalNumberOfPDFs = emailPdfFiles.length + pdfFiles.length; 
				CoDebugger.debugInformation("After Wait:Total number of XMLs --->"+totalNumberOfXMLs);
				CoDebugger.debugInformation("After Wait: Total number of PDFs --->"+totalNumberOfPDFs);
				CoDebugger.debugInformation("Diffecernce --->"+ (totalNumberOfXMLs - totalNumberOfPDFs));
				if (totalNumberOfXMLs != totalNumberOfPDFs) {
					CoDebugger.debugInformation("Discrepancy found in HP processing on - " + asOfDate + "Number of PDF files processed - " + totalNumberOfPDFs + " is different from Number of input XMLs - " + totalNumberOfXMLs);
					writeCoLog("Discrepancy found in HP processing on - " + asOfDate + "Number of PDF files processed - " + totalNumberOfPDFs + " is different from Number of input XMLs - " + totalNumberOfXMLs, coLogFile);
					writeException("Could not udpate CO switches for jobId:" + jobId, "Discrepancy found in HP processing on - " + asOfDate + "Number of PDF files processed - " + totalNumberOfPDFs + " is different from Number of input XMLs - " + totalNumberOfXMLs, null, true);

				}
			}

			readCount = totalNumberOfPDFs;
			if (totalNumberOfPDFs > 0) {
				if(totalNumberOfPDFs == pdfFiles.length){
					allPDFs = pdfFiles;
				}else if(totalNumberOfPDFs == emailPdfFiles.length){
					allPDFs = emailPdfFiles;
				}else{
					allPDFs = new File[totalNumberOfPDFs];
					int i = -1;
					for (File file : pdfFiles) {
						i++;
						allPDFs[i] = file;
					}
					for (File file : emailPdfFiles) {
						i++;
						allPDFs[i] = file;
					}
				}

				String strArr[] = null;
				CoUpdateDBBean updateDBBean = null;
				coRecipientsUpdateMap = new HashMap<String, CoUpdateDBBean>();
				int initialCount = 1;
				Map<String, Integer> totalNoticesPertype = new HashMap<String, Integer>();
				Map<String, Integer> totalNoticesPerGroup = new HashMap<String, Integer>();
				pdfFiles = pdfDir.listFiles();
				for (File file : allPDFs) {
					String fileName = file.getName();
					strArr = fileName.split(CoConstants.FILE_NAME_SEPERATOR);
					if (null != strArr && strArr.length > 7) {
						String strRptSeq = strArr[7];
						String strReqSeq = strArr[6];
						String strNoticeType = strArr[2];
						String strGroupName = strArr[5];
						if (!coRecipientsUpdateMap.containsKey(strRptSeq)) {
							updateDBBean = new CoUpdateDBBean();
							updateDBBean.setRecipientSeq(strRptSeq);
							updateDBBean.setRequestHistorySeq(strReqSeq);
							updateDBBean.setPdfFilPath(file.getAbsolutePath());
							if (!totalNoticesPertype.containsKey(strNoticeType)) {
								totalNoticesPertype.put(strNoticeType,
										initialCount);
							} else {
								totalNoticesPertype.put(strNoticeType, totalNoticesPertype.get(strNoticeType)+1);

							}

							if (!totalNoticesPerGroup.containsKey(strGroupName)) {
								totalNoticesPerGroup.put(strGroupName,
										initialCount);
							} else {
								totalNoticesPerGroup.put(strGroupName, totalNoticesPerGroup.get(strGroupName)+1);

							}

							coRecipientsUpdateMap.put(strRptSeq, updateDBBean);
						}
					}
				}
				CoDebugger
				.debugInformation("Total PDFs per Notice Type and per group names");

				for (String key : totalNoticesPertype.keySet()) {
					CoDebugger.debugInformation("\n Notice Type: " + key + " Number of PDFs: " + totalNoticesPertype.get(key));
				}

				for (String key : totalNoticesPerGroup.keySet()) {
					CoDebugger
					.debugInformation("\n Group Name: " + key + " Number of PDFs: " + totalNoticesPerGroup.get(key));
				}



				Iterator<String> itr = coRecipientsUpdateMap.keySet().iterator();
				while (itr.hasNext()) {
					coReqRptSeqNum = itr.next();
					coUpdateDBBean = coRecipientsUpdateMap.get(coReqRptSeqNum);
					if (coUpdateDBBean != null) {
						try {
							tbc.savepoint(null, "CoSavePointForUpdateCHIMESDb");
							coDAOServices.updateCoRecipientSwitches(Integer.parseInt(coUpdateDBBean.getRecipientSeq()), Integer.parseInt(coUpdateDBBean.getRequestHistorySeq()), coUpdateDBBean.getPdfFilPath());
							coDAOServices.updateCoRequestSwitches(Integer.parseInt(coUpdateDBBean.getRequestHistorySeq()),Integer.parseInt(coUpdateDBBean.getRecipientSeq()));
							boolean success = coDAOServices.updateCoTempXml(Long.valueOf(coUpdateDBBean.getRequestHistorySeq()), Long.valueOf(coUpdateDBBean.getRecipientSeq()) ,null, CoConstants.PROC, null);
							CoDebugger.debugInformation("CoTempXml update    " + success);
							/* Upadating the VCL_REQUEST_DATE */ 
							COCorrespondence coCorrespondence = new COCorrespondence();
							String pdfPath = coUpdateDBBean.getPdfFilPath();
							String fileName = pdfPath.substring(pdfPath.lastIndexOf("\\")+1);
							strArr = fileName.split(CoConstants.FILE_NAME_SEPERATOR);
							String caseAppNum = strArr[4];
							
							String docId = strArr[2];
							if(!caseAppNum.isEmpty() && docId.equals(CoConstants.NDHCELN18)){
								coCorrespondence.setCaseAppNumber(caseAppNum);
								coDAOServices.updateVclRequestDateForNCH034(coCorrespondence);
							}
							successCount++;
							coLogicalCommit();
						} catch (CoException e) {
							failCount++;
							tbc.rollback(null, "CoSavePointForUpdateCHIMESDb");
							writeException("Could not udpate CO switches for jobId:" + jobId, "Exception while updating switches for coReqSeq:" + coUpdateDBBean.getRequestHistorySeq() + " and coRptSeq:" + coUpdateDBBean.getRecipientSeq(), e, false);
							CoDebugger.debugException("Could not udpate CO switches for jobId ---> " + jobId + " Exception while updating switches for coReqSeq ---> " + coUpdateDBBean.getRequestHistorySeq() + " and coRptSeq ---> " + coUpdateDBBean.getRecipientSeq(), e);
							throw new CoException("Could not udpate CO switches for jobId ---> " + jobId + " Exception while updating switches for coReqSeq ---> " + coUpdateDBBean.getRequestHistorySeq() + " and coRptSeq ---> " + coUpdateDBBean.getRecipientSeq());
						}
					}
				}
			}

		} catch (Exception e) {
			if(null!=coUpdateDBBean){
			CoDebugger.debugException("Exception during updateChimesDbWithPdfLocation()- " + coUpdateDBBean.getRecipientSeq(), e);
			resultFlag = -1;
			throw new CoException("Exception during updateChimesDbWithPdfLocation() ---> " + coUpdateDBBean.getRecipientSeq());
			}
		}finally{
			generateSummaryReport(CoConstants.UPDATE_BATCH_SUMMARY, readCount, successCount, failCount, blankNoticeCounter);
		}
		return resultFlag;
	}

	/**
	 * @author asanghvi Reports discrepancy in FW_BATCH_EXCEPTIONS if number of
	 *         XML files present is different from number of PDFs processed by
	 *         Adobe.
	 * @throws CoException
	 */
	private void reportDiscrepancy(String csvFilePath) throws CoException
	{
		CoDebugger.debugInformation("In reportDiscrepancy-->");
		int countXMLFiles = 0;
		int countPDFFiles = 0;
		String xmlFilePath = (String) ftpProperty.getProperty(CoConstants.CO_TEMP_XML_PATH);
		File file = new File(xmlFilePath);
		if (file.isDirectory())
		{
			File[] files = file.listFiles();
			countXMLFiles = ((files.length) - 2); // subtracting 2 because one
			// is envelope.xml and other
			// is CSV file present in
			// the same directory
			CoDebugger.debugInformation("Number of XML files -" + countXMLFiles);
		}
		BufferedReader reader;
		FileReader readFile = null;
		try
		{
			readFile = new FileReader(csvFilePath);
			reader = new BufferedReader(readFile);
			while (reader.readLine() != null)
			{
				countPDFFiles++;
			}
			CoDebugger.debugInformation("Number of PDF processed -" + countPDFFiles);
			reader.close();
		} catch (FileNotFoundException e)
		{
			//e.printStackTrace(); //ND-12709
			CoDebugger.debugException(e.getMessage(), e);
		} catch (IOException ie)
		{

			CoDebugger.debugException(ie.getMessage(), ie);
		}
		finally{
			if(readFile!=null){
				try {
					readFile.close();
				} catch (IOException ie) {

					CoDebugger.debugException(ie.getMessage(), ie);
				}
			}
		}

		if (countXMLFiles != countPDFFiles)
		{
			writeException("Discrepancy found in Adobe processing on - " + asOfDate,
					"Number of PDF files processed - " + countPDFFiles
					+ " is different from Number of input XMLs - " + countXMLFiles,
					new Throwable(), false);
		}
	}

	/**
	 * This method is used to create CO REQUEST and CO REQUEST RECIPIENTS maps
	 * from the CSV file generated by Adobe Live Cycle.
	 * 
	 * @throws CoException
	 * @author kschopra
	 */
	private void createCoRequestUpdateMap(String csvFilePath) throws CoException {

		String line = null;
		String strArr[] = null;
		String strLineArr[] = null;
		String strFileName = null;

		CoUpdateDBBean coUpdateDBBean = null;

		BufferedReader buffReader = null;
		FileReader fr = null;

		coRecipientsUpdateMap = new HashMap<String, CoUpdateDBBean>();

		try {
			CoDebugger
			.debugInformation("createCoRequestUpdateMap: Looking for file - "
					+ csvFilePath);
			fr = new FileReader(csvFilePath);
			buffReader = new BufferedReader(fr);
			
			while ((line = buffReader.readLine()) != null) {
				strLineArr = line.split(CoConstants.CSV_FILE_SEPERATOR);
				for(int i=0;i<strLineArr.length;i++) {
					strFileName = strLineArr[i].substring(strLineArr[i].lastIndexOf(File.separator));
					strArr = strFileName.split(CoConstants.FILE_NAME_SEPERATOR);
					String strRptSeq = strArr[5].substring(strArr[5].indexOf("r")+1, strArr[5].lastIndexOf("."));
					String strReqSeq = strArr[4].substring(strArr[4].indexOf("n")+1);
					if (!coRecipientsUpdateMap.containsKey(strRptSeq)) {
						coUpdateDBBean = new CoUpdateDBBean();

						// Checks and fetches the New Correspondence Sequence for re-print request.
						long coReqSeqReprint = coDAOServices.getCoReqSeqByFilePathAndReprint(strRptSeq, strLineArr[i]);
						if (coReqSeqReprint > 0) {
							coUpdateDBBean.setRequestHistorySeq(String.valueOf(coReqSeqReprint));
						} else {
							coUpdateDBBean.setRequestHistorySeq(strReqSeq);							
						}
						coUpdateDBBean.setRecipientSeq(strRptSeq);
						coUpdateDBBean.setPdfFilPath(strLineArr[i]);

						coRecipientsUpdateMap.put(strRptSeq, coUpdateDBBean);
					}
				}
			}
		} catch (FileNotFoundException e) {
			CoDebugger.debugException(
					"FileNotFoundException while creating Correspondence DB switches map",
					e);
		} catch (IOException e) {
			CoDebugger.debugException(
					"IOException while creating Correspondence DB switches map",
					e);
		}
		finally{
			 if(buffReader != null){
	                try {
	                	buffReader.close();
	                } catch (IOException e) {
	                    CoDebugger.debugException(e.getMessage(), e);
	                }
	            }
			 if(fr != null){
	                try {
	                	fr.close();
	                } catch (IOException e) {
	                    CoDebugger.debugException(e.getMessage(), e);
	                }
	            }
			
		}
	}


	/**
	 * This method is used to get specified file from the source and destination
	 * folders. It uses the Generic SFTP Service by FW.
	 * 
	 * @param srcFilePath String
	 * @param destFilePath String
	 * @param csvFileName String
	 * @return retValue int
	 * @throws CoException
	 * @author kschopra
	 */
	public int getSFTPFiles(String srcFilePath, String destFilePath)
			throws CoException {
		int retValue = -1;
		try {
			// Invoke SFTP for Correspondence XML Files
			retValue = this.sftpFiles(srcFilePath,destFilePath);
		} catch (Exception e) {
			CoDebugger.debugException(
					"Error while processing BatchCoDriver for Job-ID --> "
							+ jobId, e);
			throw new CoException(
					"Exception while processing batch job for Job-ID --> "
							+ jobId, ILog.FATAL);			
		}
		return retValue;
	}


	/**
	 * 
	 * @param srcFilePath
	 * @param destFilePath
	 * @return
	 * @throws CoException
	 * @author kschopra
	 */
	private int sftpFiles(String srcFilePath, String destFilePath) throws CoException, CheckedApplicationException {
		FwFTPCollection ftpCollection = new FwFTPCollection();
		FwFTPCargo ftpCargo = new FwFTPCargo();

		ftpCargo=new FwFTPCargo ();

		//Configure SFTP USER CREDENTIALS.
		configureFTPUserInfo(ftpCargo);

		ftpCargo.setSourceFile(srcFilePath);
		ftpCargo.setDestinationFile (destFilePath);

		CoDebugger.debugInformation("Source File path : "
				+ ftpCargo.getSourceFile() + "Destination File path : "
				+ ftpCargo.getDestinationFile());
		ftpCollection.addCargo (ftpCargo);
		int i = 1;
		String numOfAttempts = (String)ftpProperty.getProperty(CoConstants.NUM_OF_ATTEMPTS);
		while (i <= (Integer.valueOf(numOfAttempts)).intValue())
		{
			try 
			{
				CoDebugger.debugInformation("Waiting..."
						+ (Long.valueOf((String) ftpProperty
								.getProperty(CoConstants.SLEEP_INTERVAL)))
								/ 1000 + "secs");
				Thread.sleep(Long.valueOf((String) ftpProperty
						.getProperty(CoConstants.SLEEP_INTERVAL)));
				CoDebugger
				.debugInformation("Trying to get .csv file from Adobe...attempt - "
						+ i);
				ftpCollection.execute(FwConstants.FTP, FwConstants.FTP_RECEIVE);
				CoDebugger.debugInformation("SFTP result for attempt - " + i
						+ "-->" + ftpCargo.isResultFlag());
				if (!ftpCargo.isResultFlag())
				{
					i++;
					continue;
				}
				CoDebugger.debugInformation("SFTP execute method completed for Job Id - " + jobId );
				break;
			} catch (Exception e) {
				CoDebugger.debugException(
						"Exception while SFTPing XML files to Adobe watch folder",
						e);
				throw new CoException(
						"Exception while SFTPing XML files to Adobe watch folder"
								+ e.getMessage());
			}		
		}
		if (ftpCargo.isResultFlag ()) {
			CoDebugger.debugInformation("SFTP return value ----> " + ftpCargo.isResultFlag());
			CoDebugger.debugInformation("SFTP successful to destination folder: " +destFilePath);
			return 0;
		} else {
			CoDebugger.logWithLevel(
					"Exception while SFTPing XML files to Adobe watch folder",
					ILog.FATAL);
			return -1;
		}
	}	


	/**
	 * This method is used to set FTP configuration details.
	 * 
	 * @param ftpCargo
	 * @author kschopra
	 */
	private void configureFTPUserInfo(FwFTPCargo ftpCargo)throws CheckedApplicationException {
		String host=(String)ftpProperty.getProperty(CoConstants.FTP_SERVER);
		String user= (String)ftpProperty.getProperty (CoConstants.FTP_USER);
		//CR-06 Password Encryption Decryption
		//The property 'FTP_PASSWORD' is missing in the correspondence.properties file. We need to make necessary changes in the properties file inorder to make it work.
		String password= SecurityServiceFactory.getPasswordSecurityService().decrypt((String) ftpProperty.getProperty (CoConstants.FTP_PASSWORD));
		String port= (String) ftpProperty.getProperty (CoConstants.FTP_PORT);

		int portNumber=Integer.parseInt (port);
		FwUserInfo ui = new FwUserInfo (user, host, password, portNumber);
		ui.setSecureFlag (true); //This is for SFTP connect. By default this value is false.

		ftpCargo.setUserInfo (ui);
	}	


	/**
	 * 
	 * @param triggerType
	 * @return
	 * @throws CoException 
	 */
	public int createCorrespondenceTriggers(String triggerType) throws CoException {

		int retValue = 0;
		COCorrespondence coCorrespondence = new COCorrespondence();
		List<Long> triggerList=new ArrayList<Long>();
		CoMassMailingReqCollection coMassMailingReqColl = null;
		StringBuffer totalReadCount = null;
		StringBuffer totalFailed = null;
		StringBuffer totalSuccess = null;
		Set<Long> activeCases = null;
		Set<Integer> activeProviders = null;
		Set<Integer> activeVendors = null;
		Map<String,String> failedMap = new HashMap<String,String>();
		// Get Connection
		try {
			coConnection = tbc.getConnection();
			coConnection.setAutoCommit(false);
			coDAOServices.setConnection(coConnection);
		} catch (Exception e) {
			return this.handleBatchException(e, 1, null, null);
		}

		try {
			List<COCorrespondence> coCorrespondenceList = null;
			DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
			long totalExpected = 0;
			docName = "";
			if (triggerType != null && triggerType.equalsIgnoreCase(CoConstants.CO_DOC_ID_NCH049)) {

				docName = "MassMailing";
				totalReadCount = new StringBuffer();
				totalFailed = new StringBuffer();
				totalSuccess = new StringBuffer();
				coCorrespondenceList = new ArrayList<COCorrespondence>();
				try {
					coMassMailingReqColl = new CoMassMailingReqCollection();
					CoMassMailingReqCargo[] arrCoMassMailingCargos = null;

					//to get all co_mass_mailing_req unprocessed cargos
					arrCoMassMailingCargos = coDAOServices.getCoMassMailingReqCargos();

					if (arrCoMassMailingCargos != null && arrCoMassMailingCargos.length > 0) {					


						//iterating per co_mass_mailing_request
						for (int i = 0; i < arrCoMassMailingCargos.length; i++) {		
							activeCases = new HashSet<Long>();		
							activeProviders= new HashSet<Integer>();
							activeVendors=new HashSet<Integer>();
							try {
								if(arrCoMassMailingCargos[i]!=null && arrCoMassMailingCargos[i].getProgramLst()!=null){
									String scheduledDate = df.format(arrCoMassMailingCargos[i].getSchdDt());
									String[] dataEntered=arrCoMassMailingCargos[i].getProgramLst().split(",");
									String prog="";								
									if(dataEntered!=null && dataEntered.length>0){
										prog=dataEntered[0];
									}	

									if("CCAP".equals(prog)){
										prog=CoConstants.PROGRAM_CD;
									}else if("LIHEAP".equals(prog)){
										prog=CoConstants.PROGRAM_LI;
									}

									if(CoConstants.PROGRAM_MA.equalsIgnoreCase(prog) || CoConstants.PROGRAM_FS.equalsIgnoreCase(prog) ||  CoConstants.PROGRAM_TF.equalsIgnoreCase(prog) || (CoConstants.PROGRAM_CD.equalsIgnoreCase(prog) && dataEntered!=null && dataEntered.length>1 && "HOUSEHOLD".equalsIgnoreCase(dataEntered[1]))){							


										//COND 1:to get eligibilityRecords									
										VEdEligibilityIndvCargo[] eligibilityRecords=(VEdEligibilityIndvCargo[]) coDAOServices.getCoMassMailEligible(scheduledDate,prog);
										HashMap<Long,ArrayList<Long>> eligibleMap=new HashMap<Long,ArrayList<Long>>();
										if(eligibilityRecords!=null && eligibilityRecords.length>0){
											for(VEdEligibilityIndvCargo eligibilityRecord:eligibilityRecords){
												if(!eligibleMap.isEmpty() && eligibleMap.containsKey(eligibilityRecord.getT1CaseNum())){
													List<Long> caseIndvs=eligibleMap.get(eligibilityRecord.getT1CaseNum());
													if(caseIndvs!=null && !caseIndvs.contains(eligibilityRecord.getIndvId())){
														caseIndvs.add(eligibilityRecord.getIndvId());
													}
												}else{
													ArrayList<Long> indvs=new ArrayList<Long>();
													indvs.add(eligibilityRecord.getIndvId());
													eligibleMap.put(eligibilityRecord.getT1CaseNum(), indvs);
												}
											}
										}

										if(eligibilityRecords!=null && eligibilityRecords.length>0){
											//Start:For extracting Filtering Criteria and Values
											String[] arrRecordPerReq=arrCoMassMailingCargos[i].getProgramLst().split(";");

											HashMap<String,ArrayList<String>> filterusingData= new HashMap<String,ArrayList<String>>();
											Set<String> filterCriterias=new HashSet<String>();

											//to extract distinct filter Criteria and Values
											initializeFilterCriteriasValues(arrRecordPerReq,filterusingData,filterCriterias);																

											if(prog.equals(CoConstants.PROGRAM_MA)){						

												if(!filterCriterias.isEmpty()){							

													for(String filterCriteria:filterCriterias){

														if(filterCriteria.equals(CoConstants.TYPEOFASSISTANCE) && filterusingData.containsKey(CoConstants.TYPEOFASSISTANCE) && filterusingData.get(CoConstants.TYPEOFASSISTANCE)!=null && !filterusingData.get(CoConstants.TYPEOFASSISTANCE).isEmpty()){
															ArrayList<String> typesOfAssistances=filterusingData.get(CoConstants.TYPEOFASSISTANCE);	
															if(typesOfAssistances!=null && !typesOfAssistances.isEmpty()){
																for(VEdEligibilityIndvCargo eligibilityRecord:eligibilityRecords){
																	String toa=eligibilityRecord.getTypeOfAssistanceCd();
																	if(toa!=null){
																		if(("MS01".equals(toa) && typesOfAssistances.contains("QMB")) ||
																				("MS02".equals(toa) && typesOfAssistances.contains("SLMB")) ||
																				("MS03".equals(toa) && typesOfAssistances.contains("QI1")) ||
																				("WD10".equals(toa) && typesOfAssistances.contains("WORKDISAB")) ||
																				("CW10".equals(toa) && typesOfAssistances.contains("CHILDDISAB")) ||
																				("MA84".equals(toa) && typesOfAssistances.contains("WOMWAY")) ||
																				(eligibilityRecord.getCoverageGroup()!=null && ("MX".equals(eligibilityRecord.getCoverageGroup()) || "HS".equals(eligibilityRecord)) && typesOfAssistances.contains("ALLACA")) ||
																				("MA86".equals(toa) && typesOfAssistances.contains("MEDFRAIL")) ||
																				("MA83".equals(toa) && typesOfAssistances.contains("HEALTHSTPS")) ||
																				(("MA87".equals(toa) || "MA85".equals(toa) || ("MA86".equals(toa) && eligibilityRecord.getCoe()!=null && ("M077".equals(eligibilityRecord.getCoe()) || "M089".equals(eligibilityRecord.getCoe()))) || 
																						("MA84".equals(toa) && eligibilityRecord.getCoe()!=null && ("M064".equals(eligibilityRecord.getCoe())))) && typesOfAssistances.contains("ACAMA")) ||
																						(typesOfAssistances.contains("ALLAS") && toa!=null)){																	
																			long caseNum=eligibilityRecord.getT1CaseNum();
																			addCasesForMassMail(activeCases,caseNum);																
																		}
																	}
																}
															}
														}

														if(CoConstants.LIVINGARRANGMENT.equals(filterCriteria) && filterusingData.containsKey(CoConstants.LIVINGARRANGMENT) && filterusingData.get(CoConstants.LIVINGARRANGMENT)!=null &&  !filterusingData.get(CoConstants.LIVINGARRANGMENT).isEmpty() && filterusingData.get(CoConstants.LIVINGARRANGMENT).contains("LTC")){
															DcCaseIndividualCargo[] dcIndvLivngLTCCargos=coDAOServices.getDcLivingArrMassMailing(scheduledDate,"LT");
															if(dcIndvLivngLTCCargos!=null && dcIndvLivngLTCCargos.length>0){
																for(DcCaseIndividualCargo dcIndvLivngLTCCargo:dcIndvLivngLTCCargos){
																	if(eligibleMap!=null && !eligibleMap.isEmpty() && eligibleMap.containsKey(dcIndvLivngLTCCargo.getCaseNum()) && eligibleMap.get(dcIndvLivngLTCCargo.getCaseNum())!=null && eligibleMap.get(dcIndvLivngLTCCargo.getCaseNum()).contains(dcIndvLivngLTCCargo.getIndvId())){
																		
																			long caseNum=dcIndvLivngLTCCargo.getCaseNum();
																			addCasesForMassMail(activeCases,caseNum);
																		
																	}
																}


															}


														}
													}
													//adding trigger in trigger List
													addingTriggerForMassMail(activeCases,coCorrespondenceList,arrCoMassMailingCargos, i, prog);

												}

												totalExpected = totalExpected + activeCases.size();
												CoDebugger.debugInformation("createCorrespondenceTriggers ---> For massMailingID  ---> " + arrCoMassMailingCargos[i].getMassMailingSeqNum()
														+ " Expected triggerCount for ---> " + activeCases.size());	
											}//end of MA

											if(prog.equals(CoConstants.PROGRAM_FS)){						

												if(!filterCriterias.isEmpty()){											

													//Criterias basis Check
													for(String filterCriteria:filterCriterias){

														if(filterCriteria.equals(CoConstants.ELDERLY) && filterusingData.containsKey(CoConstants.ELDERLY) && filterusingData.get(CoConstants.ELDERLY)!=null && !filterusingData.get(CoConstants.ELDERLY).isEmpty() && filterusingData.get(CoConstants.ELDERLY).contains("ELD")){												
															if(eligibilityRecords!=null && eligibilityRecords.length>0){
																for(VEdEligibilityIndvCargo caseIndvCargo: eligibilityRecords){
																
																	if(null != caseIndvCargo.getYoungChDob()){
																	Date birthDate=new Date(caseIndvCargo.getYoungChDob().getTime());
																	Age age = CoAgeCalculator.calculateAge(birthDate);
																	
																	if ((age.getYears() > 65) || (age.getYears() == 65)){
																		if(eligibleMap!=null && !eligibleMap.isEmpty() && eligibleMap.containsKey(caseIndvCargo.getT1CaseNum()) && eligibleMap.get(caseIndvCargo.getT1CaseNum()).contains(caseIndvCargo.getIndvId())){
																			long caseNum=caseIndvCargo.getT1CaseNum();
																			addCasesForMassMail(activeCases,caseNum);
																		}
																	}
																}
															}
														}

														}else if(filterCriteria.equals(CoConstants.DISABLED) && filterusingData.containsKey(CoConstants.DISABLED) && filterusingData.get(CoConstants.DISABLED)!=null && !filterusingData.get(CoConstants.DISABLED).isEmpty() && filterusingData.get(CoConstants.DISABLED).contains("DISB")){
															if(eligibilityRecords!=null && eligibilityRecords.length>0){
																for(VEdEligibilityIndvCargo eligibilityRecord:eligibilityRecords){
																	if(eligibilityRecord.getDisabilitySw()=='Y'){
																		long caseNum=eligibilityRecord.getT1CaseNum();
																		addCasesForMassMail(activeCases,caseNum);
																	}
																}

															}


														}else if(filterCriteria.equals(CoConstants.CHILDREN) && filterusingData.containsKey(CoConstants.CHILDREN) && filterusingData.get(CoConstants.CHILDREN)!=null && !filterusingData.get(CoConstants.CHILDREN).isEmpty() && (filterusingData.get(CoConstants.CHILDREN).contains("Y") || filterusingData.get(CoConstants.CHILDREN).contains("N"))){
															if(eligibilityRecords!=null && eligibilityRecords.length>0){
																for(VEdEligibilityIndvCargo caseIndvCargo: eligibilityRecords){
																	if(null != caseIndvCargo.getYoungChDob()){
																	Date birthDate=new Date(caseIndvCargo.getYoungChDob().getTime());
																	Age age = CoAgeCalculator.calculateAge(birthDate);
																	if ((age.getYears() < 19) || (age.getYears() == 19)){
																		if(eligibleMap!=null && !eligibleMap.isEmpty() && eligibleMap.containsKey(caseIndvCargo.getT1CaseNum()) && eligibleMap.get(caseIndvCargo.getT1CaseNum()).contains(caseIndvCargo.getIndvId())){
																			long caseNum=caseIndvCargo.getT1CaseNum();
																			addCasesForMassMail(activeCases,caseNum);
																		}
																	}
																}
															}
														}
														
														}else if(filterCriteria.equals(CoConstants.MASS_MAIL_ABAWD) && filterusingData.containsKey(CoConstants.MASS_MAIL_ABAWD) &&  filterusingData.get(CoConstants.MASS_MAIL_ABAWD)!=null && !filterusingData.get(CoConstants.MASS_MAIL_ABAWD).isEmpty() && filterusingData.get(CoConstants.MASS_MAIL_ABAWD).contains(CoConstants.MASS_MAIL_ABAWD)){
															if(eligibilityRecords!=null && eligibilityRecords.length>0){
																for(VEdEligibilityIndvCargo eligibilityRecord:eligibilityRecords){
																	if(eligibilityRecord.getT1AbawdSw()=='Y'){
																		long caseNum=eligibilityRecord.getT1CaseNum();
																		addCasesForMassMail(activeCases,caseNum);
																	}
																}
                                                              
															}
														}


													}

													//adding trigger in trigger List
													addingTriggerForMassMail(activeCases,coCorrespondenceList,arrCoMassMailingCargos, i, prog);

												}



												totalExpected = totalExpected + activeCases.size();
												CoDebugger.debugInformation("createCorrespondenceTriggers ---> For massMailingID  ---> " + arrCoMassMailingCargos[i].getMassMailingSeqNum()
														+ " Expected triggerCount for ---> " + activeCases.size());	

											}//end of FS

											if(prog.equals(CoConstants.PROGRAM_TF)){
												if(!filterCriterias.isEmpty()){

													for(String filterCriteria:filterCriterias){

														if(filterCriteria.equals(CoConstants.COUNTY) && filterusingData.containsKey(CoConstants.COUNTY) && filterusingData.get(CoConstants.COUNTY)!=null && !filterusingData.get(CoConstants.COUNTY).isEmpty()){
															String counties="";
															String prefix="";

															for(String county: filterusingData.get(CoConstants.COUNTY)){
																counties=counties+prefix+"'"+county + "'";
																prefix=",";
															}												
															DcCaseAddressesCargo[] dcCaseAddressesCargos=(DcCaseAddressesCargo[])coDAOServices.getDcAddressMassMailing(scheduledDate,counties);
															if(dcCaseAddressesCargos!=null && dcCaseAddressesCargos.length>0){
																for(DcCaseAddressesCargo dcCaseAddressesCargo:dcCaseAddressesCargos){
																	if(dcCaseAddressesCargo.getAddrCountyCd()!=null){
																		if(eligibleMap!=null && !eligibleMap.isEmpty() && eligibleMap.containsKey(dcCaseAddressesCargo.getCaseNum())){
																			long caseNum=dcCaseAddressesCargo.getCaseNum();
																			addCasesForMassMail(activeCases,caseNum);		
																		}
																	}
																}
															}
														}							


													}

													//adding trigger in trigger List
													addingTriggerForMassMail(activeCases,coCorrespondenceList,arrCoMassMailingCargos, i, prog);						

												}

												totalExpected = totalExpected + activeCases.size();
												CoDebugger.debugInformation("createCorrespondenceTriggers ---> For massMailingID  ---> " + arrCoMassMailingCargos[i].getMassMailingSeqNum()
														+ " Expected triggerCount for ---> " + activeCases.size());					

											}	

											if(prog.equals(CoConstants.PROGRAM_CD)){
												if(!filterCriterias.isEmpty()){

													for(String filterCriteria:filterCriterias){

														if(filterCriteria.equals(CoConstants.COUNTY) && filterusingData.containsKey(CoConstants.COUNTY) && filterusingData.get(CoConstants.COUNTY)!=null && !filterusingData.get(CoConstants.COUNTY).isEmpty()){
															String counties="";
															String prefix="";

															for(String county: filterusingData.get(CoConstants.COUNTY)){
																counties=counties+prefix+"'"+county + "'";
																prefix=",";
															}												
															DcCaseAddressesCargo[] dcCaseAddressesCargos=(DcCaseAddressesCargo[])coDAOServices.getDcAddressMassMailing(scheduledDate,counties);
															if(dcCaseAddressesCargos!=null && dcCaseAddressesCargos.length>0){
																for(DcCaseAddressesCargo dcCaseAddressesCargo:dcCaseAddressesCargos){
																	if(dcCaseAddressesCargo.getAddrCountyCd()!=null){
																		if(eligibleMap!=null && !eligibleMap.isEmpty() && eligibleMap.containsKey(dcCaseAddressesCargo.getCaseNum())){
																			long caseNum=dcCaseAddressesCargo.getCaseNum();
																			addCasesForMassMail(activeCases,caseNum);		
																		}
																	}
																}
															}
														}	//end of first criteria check-COUNTY					


														//second criteria--homeless switch check
														if(filterCriteria.equals(CoConstants.HOMELESS) && filterusingData.containsKey(CoConstants.HOMELESS) && filterusingData.get(CoConstants.HOMELESS)!=null && !filterusingData.get(CoConstants.HOMELESS).isEmpty()){
															if(eligibleMap!=null && !eligibleMap.isEmpty()){
																Set<Long> eligibleCases=eligibleMap.keySet();
																if(eligibleCases!=null){

																	String caseNumbers="";
																	String prefix="";
																	for(long caseNum:eligibleCases){
																		caseNumbers=caseNumbers+prefix+caseNum;
																		prefix=",";
																	}


																	ArrayList<String> homelessValues=filterusingData.get(CoConstants.HOMELESS);
																	if(homelessValues!=null){
																		String[] homelessValArr=new String[homelessValues.size()];
																		int index=0;
																		for(String homelessVal:homelessValues){
																			homelessValArr[index]=homelessVal;
																			index++;
																		}

																		String homelessSwitches="";
																		prefix="";
																		for(String homelessVal:homelessValArr){
																			homelessSwitches=homelessSwitches+prefix+ "'"+ homelessVal+"'";
																			prefix=",";
																		}


																		DcCaseAddressesCargo[] dcaddressDetails=(DcCaseAddressesCargo[])coDAOServices.getHomelessDetailsForMassMailCO(scheduledDate,caseNumbers,homelessSwitches);
																		if(dcaddressDetails!=null && dcaddressDetails.length>0){
																			for(DcCaseAddressesCargo dcCargo:dcaddressDetails){
																				long caseNum=dcCargo.getCaseNum();
																				addCasesForMassMail(activeCases,caseNum);
																			}

																		}
																	}						

																}
															}


														}//end of second criteria



														//third criteria check
														if(filterCriteria.equals(CoConstants.GRANTTYPE) && filterusingData.containsKey(CoConstants.GRANTTYPE) && filterusingData.get(CoConstants.GRANTTYPE)!=null && !filterusingData.get(CoConstants.GRANTTYPE).isEmpty()){
															if(eligibilityRecords!=null && eligibilityRecords.length>0){																

																for(VEdEligibilityIndvCargo  eligibilityRecord:eligibilityRecords){														
																	if(eligibilityRecord.getCoverageGroup()!=null){															
																		if(filterusingData.get(CoConstants.GRANTTYPE).contains(eligibilityRecord.getCoverageGroup())){
																			long caseNum=eligibilityRecord.getT1CaseNum();
																			addCasesForMassMail(activeCases,caseNum);
																		}														

																	}
																}

															}												
														}//END OF CRITERIA

														//fourth criteria
														if(filterCriteria.equals(CoConstants.LICENSETYPE) && filterusingData.containsKey(CoConstants.LICENSETYPE) && filterusingData.get(CoConstants.LICENSETYPE)!=null && !filterusingData.get(CoConstants.LICENSETYPE).isEmpty()){
															String licenses="";
															String prefix="";

															for(String license: filterusingData.get(CoConstants.LICENSETYPE)){
																licenses=licenses+prefix+"'"+license + "'";
																prefix=",";
															}	

															PmCcapProvidersCargo[] pmCcapProvidersArr=(PmCcapProvidersCargo[]) coDAOServices.getProviderLicenseTypesMassMail(scheduledDate,licenses);
															if(pmCcapProvidersArr!=null && pmCcapProvidersArr.length>0){
																Set<Integer> provds=new HashSet<Integer>();
																for(PmCcapProvidersCargo pmCcapProvider:pmCcapProvidersArr){
																	if(pmCcapProvider.getProviderId()!=null){
																		Integer providerId=pmCcapProvider.getProviderId();

																		if(providerId>0 && !provds.isEmpty() && !provds.contains(providerId)){
																			provds.add(providerId);
																		}															
																	}
																}

																if(!provds.isEmpty()){
																	String provs="";
																	prefix="";
																	for(Integer provid:provds){
																		provs=provs+prefix+provid;
																		prefix=",";
																	}

																	//getProviderAssocs
																	PmProviderChildAssocCargo[] pmassocs=(PmProviderChildAssocCargo[])coDAOServices.getProviderAssocs(scheduledDate,provs);
																	if(pmassocs!=null && pmassocs.length>0){
																		for(PmProviderChildAssocCargo pmassoc:pmassocs){
																			if(pmassoc.getCaseNum()>0){
																				long caseNum=pmassoc.getCaseNum();
																				addCasesForMassMail(activeCases,caseNum);
																			}
																		}
																	}

																}
															}
														}//end of fourth license type criteria check



													}

													//adding trigger in trigger List
													addingTriggerForMassMail(activeCases,coCorrespondenceList,arrCoMassMailingCargos, i, prog);						

												}

												totalExpected = totalExpected + activeCases.size();
												CoDebugger.debugInformation("createCorrespondenceTriggers ---> For massMailingID  ---> " + arrCoMassMailingCargos[i].getMassMailingSeqNum()
														+ " Expected triggerCount for ---> " + activeCases.size());	


											}
										
											

										}//END IF NOTHING ELIGIBLE FOR THE PROG
									}else if("LI".equalsIgnoreCase(prog) && dataEntered!=null && dataEntered.length>1 && "HOUSEHOLD".equalsIgnoreCase(dataEntered[1])){
										//LIHEAP HOUSEHOLD FLOW										
										CoDebugger.debugInformation("HOUSEHOLD LIHEAP FLOW  STARTED>>>>>>>>>" );
										
										//For Filtering criterias and values
										String[] arrRecordPerReq=arrCoMassMailingCargos[i].getProgramLst().split(";");

										HashMap<String,ArrayList<String>> filterusingData= new HashMap<String,ArrayList<String>>();
										Set<String> filterCriterias=new HashSet<String>();
										
										//to extract distinct filter Criteria and Values
										initializeFilterCriteriasValues(arrRecordPerReq,filterusingData,filterCriterias);

										
										if(!filterCriterias.isEmpty()){ 		
									
											SimpleDateFormat datetimeFormatter1 = new SimpleDateFormat(CoConstants.DATE_FORMAT);
											Date batchAsOfDate = datetimeFormatter1.parse(asOfDate);										
											
											
											Timestamp currentTimeStamp = new Timestamp(batchAsOfDate.getTime()); 									
											int currMonth=ALSOPUtil.getMM(currentTimeStamp);
											int currYear=ALSOPUtil.getYYYY(currentTimeStamp);
											
											int eyear=currYear;
											if(currMonth>5){
												 eyear=currYear+1;
											}
											 Calendar starCal=Calendar.getInstance();
											 starCal.set(eyear-1, 9, 1);
											 String sDate= df.format(starCal.getTime());
										
											
											 
											 Calendar endCal=Calendar.getInstance();
											 endCal.set(eyear, 4, 31);
											 String eDate= df.format(endCal.getTime());
											 
											 CoDebugger.debugInformation("LIHEAP CURRENT FISCAL YEAR STARTDATE "+ sDate);
											 CoDebugger.debugInformation("LIHEAP CURRENT FISCAL YEAR ENDDATE "+ eDate);
											
											//eleigible records as per current heating season
											VEdEligibilityIndvCargo[] eligibilityRecords=(VEdEligibilityIndvCargo[]) coDAOServices.getByEdStatusTypeCO(sDate,eDate,prog,"'"+CoConstants.EDBC_STATUS_CD_APPROVED+"'");
										
											HashMap<Long,ArrayList<Long>> eligibleMap=new HashMap<Long,ArrayList<Long>>();
											if(eligibilityRecords!=null && eligibilityRecords.length>0){
												for(VEdEligibilityIndvCargo eligibilityRecord:eligibilityRecords){
													if(!eligibleMap.isEmpty() && eligibleMap.containsKey(eligibilityRecord.getT1CaseNum())){
														List<Long> caseIndvs=eligibleMap.get(eligibilityRecord.getT1CaseNum());
														if(caseIndvs!=null && !caseIndvs.contains(eligibilityRecord.getIndvId())){
															caseIndvs.add(eligibilityRecord.getIndvId());
														}
													}else{
														ArrayList<Long> indvs=new ArrayList<Long>();
														indvs.add(eligibilityRecord.getIndvId());
														eligibleMap.put(eligibilityRecord.getT1CaseNum(), indvs);
													}
												}
											}
											

											for(String filterCriteria:filterCriterias){
												
												if(filterCriteria.equals(CoConstants.FIXED_INCOME) && filterusingData.containsKey(CoConstants.FIXED_INCOME) && filterusingData.get(CoConstants.FIXED_INCOME)!=null && !filterusingData.get(CoConstants.FIXED_INCOME).isEmpty()){
													CoDebugger.debugInformation("HOUSEHOLD LIHEAP FLOW FIXED INCOME  STARTED>>>>>>>>>" );
													String unearnedIncomeTypes="";
													String prefix="";
													
													for(String unearnedIncomeType: filterusingData.get(CoConstants.FIXED_INCOME)){
														unearnedIncomeTypes=unearnedIncomeTypes+prefix+"'"+unearnedIncomeType + "'";
														prefix=",";
													}	
													

													DcUnearnedIncomeCargo[] dcUnearnedIncomeCargoArr=(DcUnearnedIncomeCargo[])coDAOServices.getByUnearnedIncomeTypeCO(unearnedIncomeTypes);
													if(null!=dcUnearnedIncomeCargoArr && dcUnearnedIncomeCargoArr.length>0){
														Set<Long> dcUnearnedIndvs=new HashSet<Long>();
														for( DcUnearnedIncomeCargo dcUnearnedIncomeCargo:dcUnearnedIncomeCargoArr){
															if(null!=dcUnearnedIndvs && !dcUnearnedIndvs.contains(dcUnearnedIncomeCargo.getIndvId())){
																dcUnearnedIndvs.add(dcUnearnedIncomeCargo.getIndvId());
															}															
														}
														
														for(VEdEligibilityIndvCargo eligibilityRecord:eligibilityRecords){
															if(null!=eligibilityRecord && dcUnearnedIndvs.contains(eligibilityRecord.getIndvId())){																
																	long caseNum=eligibilityRecord.getT1CaseNum();
																	addCasesForMassMail(activeCases,caseNum);	
																
															}
														}
														
														
													}
												}
												
												//scenario: PRIMARY FUEL TYPE												
												if(filterCriteria.equals(CoConstants.PRIMARY_FUEL_TYPE) && filterusingData.containsKey(CoConstants.PRIMARY_FUEL_TYPE) && filterusingData.get(CoConstants.PRIMARY_FUEL_TYPE)!=null && !filterusingData.get(CoConstants.PRIMARY_FUEL_TYPE).isEmpty()){
													CoDebugger.debugInformation("HOUSEHOLD LIHEAP FLOW PRIMARY FUEL STARTED>>>>>>>>>" );
													String primaryFuelTypes="";
													String prefix="";

													for(String primaryFuelType: filterusingData.get(CoConstants.PRIMARY_FUEL_TYPE)){
														if(primaryFuelType.equals(CoConstants.FILTER_VALUE_FUELOIL)){
															primaryFuelType=CoConstants.PRIMARY_FUEL_FUELOIL;
														}else if(primaryFuelType.equals(CoConstants.FILTER_VALUE_NATURALGAS)){
															primaryFuelType=CoConstants.PRIMARY_FUEL_NATURALGAS;
														}else if(primaryFuelType.equals(CoConstants.FILTER_VALUE_PROPANE)){
															primaryFuelType=CoConstants.PRIMARY_FUEL_PROPANE;
														}else if(primaryFuelType.equals(CoConstants.FILTER_VALUE_ELECTRICITY)){
															primaryFuelType=CoConstants.PRIMARY_FUEL_ELECTRICITY;
														}else if(primaryFuelType.equals(CoConstants.FILTER_VALUE_WOOD)){
															primaryFuelType=CoConstants.PRIMARY_FUEL_WOOD;
														}else if(primaryFuelType.equals(CoConstants.FILTER_VALUE_COAL)){
															primaryFuelType=CoConstants.PRIMARY_FUEL_COAL;
														}else if(primaryFuelType.equals(CoConstants.FILTER_VALUE_OTHER)){
															primaryFuelType=CoConstants.PRIMARY_FUEL_OTHER;
														}
														primaryFuelTypes=primaryFuelTypes+prefix+"'"+primaryFuelType + "'";
														prefix=",";
													}	
													
													String heatingSeason=String.valueOf(eyear);
													DcLiheapCargo[] dcLiheapCargoArr=(DcLiheapCargo[])coDAOServices.getByPrimaryFuelTypeCO(heatingSeason,primaryFuelTypes);
													if(null!=dcLiheapCargoArr && dcLiheapCargoArr.length>0){
														for(DcLiheapCargo dcLiheapCargo:dcLiheapCargoArr){
															if(null!=dcLiheapCargo && dcLiheapCargo.getCaseNum()>0 && null!=eligibleMap && eligibleMap.containsKey(dcLiheapCargo.getCaseNum())){
																long caseNum=dcLiheapCargo.getCaseNum();
																addCasesForMassMail(activeCases,caseNum);
															}
														}
													}
												} //end of primary fuel type
											

												//scenario: SECONDARY FUEL TYPE
												if(filterCriteria.equals(CoConstants.SECONDARY_FUEL_TYPE) && filterusingData.containsKey(CoConstants.SECONDARY_FUEL_TYPE) && filterusingData.get(CoConstants.SECONDARY_FUEL_TYPE)!=null && !filterusingData.get(CoConstants.SECONDARY_FUEL_TYPE).isEmpty()){
													CoDebugger.debugInformation("HOUSEHOLD LIHEAP FLOW SECONDARY FUEL STARTED>>>>>>>>>" );
													String secondaryFuelTypes="";
													String prefix="";

													for(String secondaryFuelType: filterusingData.get(CoConstants.SECONDARY_FUEL_TYPE)){
														if(secondaryFuelType.equals(CoConstants.FILTER_VALUE_FUELOIL)){
															secondaryFuelType=CoConstants.PRIMARY_FUEL_FUELOIL;
														}else if(secondaryFuelType.equals(CoConstants.FILTER_VALUE_NATURALGAS)){
															secondaryFuelType=CoConstants.PRIMARY_FUEL_NATURALGAS;
														}else if(secondaryFuelType.equals(CoConstants.FILTER_VALUE_PROPANE)){
															secondaryFuelType=CoConstants.PRIMARY_FUEL_PROPANE;
														}else if(secondaryFuelType.equals(CoConstants.FILTER_VALUE_ELECTRICITY)){
															secondaryFuelType=CoConstants.PRIMARY_FUEL_ELECTRICITY;
														}else if(secondaryFuelType.equals(CoConstants.FILTER_VALUE_WOOD)){
															secondaryFuelType=CoConstants.PRIMARY_FUEL_WOOD;
														}else if(secondaryFuelType.equals(CoConstants.FILTER_VALUE_COAL)){
															secondaryFuelType=CoConstants.PRIMARY_FUEL_COAL;
														}else if(secondaryFuelType.equals(CoConstants.FILTER_VALUE_OTHER)){
															secondaryFuelType=CoConstants.PRIMARY_FUEL_OTHER;
														}
														secondaryFuelTypes=secondaryFuelTypes+prefix+"'"+secondaryFuelType + "'";
														prefix=",";
													}	
													
													String heatingSeason=String.valueOf(eyear);
													DcLiheapCargo[] dcLiheapCargoArr=(DcLiheapCargo[])coDAOServices.getBySecondaryFuelTypeCO(heatingSeason,secondaryFuelTypes);
													if(null!=dcLiheapCargoArr && dcLiheapCargoArr.length>0){
														for(DcLiheapCargo dcLiheapCargo:dcLiheapCargoArr){
															if(null!=dcLiheapCargo && dcLiheapCargo.getCaseNum()>0 && null!=eligibleMap && eligibleMap.containsKey(eyear)){
																long caseNum=dcLiheapCargo.getCaseNum();
																addCasesForMassMail(activeCases,caseNum);
															}
														}
													}
												} //end of SECONDARY FUEL TYPE		
												
												//CRITERIA: COUNTY
												
												//fourth criteria
												if(filterCriteria.equals(CoConstants.COUNTY) && filterusingData.containsKey(CoConstants.COUNTY) && filterusingData.get(CoConstants.COUNTY)!=null && !filterusingData.get(CoConstants.COUNTY).isEmpty()){
													CoDebugger.debugInformation("HOUSEHOLD LIHEAP FLOW  COUNTY STARTED>>>>>>>>>" );
													String counties="";
													String prefix="";

													for(String county: filterusingData.get(CoConstants.COUNTY)){
														counties=counties+prefix+"'"+county + "'";
														prefix=",";
													}												
													DcCaseAddressesCargo[] dcCaseAddressesCargos=(DcCaseAddressesCargo[])coDAOServices.getDcAddressMassMailing(scheduledDate,counties);
													if(dcCaseAddressesCargos!=null && dcCaseAddressesCargos.length>0){
														for(DcCaseAddressesCargo dcCaseAddressesCargo: dcCaseAddressesCargos){
															if(null!=dcCaseAddressesCargo && null!=dcCaseAddressesCargo.getAddrCountyCd()){
																if(dcCaseAddressesCargo.getCaseNum()>0 && null!=eligibleMap && eligibleMap.containsKey(dcCaseAddressesCargo.getCaseNum())){
																	long caseNum=dcCaseAddressesCargo.getCaseNum();
																	addCasesForMassMail(activeCases,caseNum);
																}
															}
														}
													}
												}	//end of  criteria check-COUNTY
												
												
												// criteria: case Status
												if(filterCriteria.equals(CoConstants.CASE_STATUS) && filterusingData.containsKey(CoConstants.CASE_STATUS) && filterusingData.get(CoConstants.CASE_STATUS)!=null && !filterusingData.get(CoConstants.CASE_STATUS).isEmpty()){
													CoDebugger.debugInformation("HOUSEHOLD LIHEAP FLOW CASE STATUS  STARTED>>>>>>>>>" );
													String statuses="";
													String prefix="";

													for(String status: filterusingData.get(CoConstants.CASE_STATUS)){
														statuses=statuses+prefix+"'"+status + "'";
														prefix=",";
													}												
													VEdEligibilityIndvCargo[] vEdEligibilityIndvArr=(VEdEligibilityIndvCargo[])coDAOServices.getByEdStatusTypeCO(sDate,eDate,prog,statuses);
													if(null!=vEdEligibilityIndvArr && vEdEligibilityIndvArr.length>0){
														for(VEdEligibilityIndvCargo vEdEligibilityIndvCargo:vEdEligibilityIndvArr){
															if(null!=vEdEligibilityIndvCargo && vEdEligibilityIndvCargo.getT1CaseNum()>0){
																long caseNum=vEdEligibilityIndvCargo.getT1CaseNum();
																addCasesForMassMail(activeCases,caseNum);
															}
														}
													}
												}	//end of Case status criteria
												
												//criteria FISCAL YEAR
												if(filterCriteria.equals(CoConstants.FISCAL_YEAR) && filterusingData.containsKey(CoConstants.FISCAL_YEAR) && filterusingData.get(CoConstants.FISCAL_YEAR)!=null && !filterusingData.get(CoConstants.FISCAL_YEAR).isEmpty()){
													CoDebugger.debugInformation("HOUSEHOLD LIHEAP FLOW FISCAL YEAR STARTED>>>>>>>>>" );
													if(null!=eligibilityRecords && eligibilityRecords.length>0){
														for(String fiscalYear:filterusingData.get(CoConstants.FISCAL_YEAR)){
															
															if(null!=fiscalYear && fiscalYear.equals(CoConstants.PREV_FY)){
																
																int prevFYendYear=eyear-1;
																
																 Calendar starCal2=Calendar.getInstance();
																 starCal2.set(prevFYendYear-1, 9, 1);
																 String sDate2= df.format(starCal2.getTime());
																 
																 Calendar endCal2=Calendar.getInstance();
																 endCal2.set(prevFYendYear, 4, 31);
																 String eDate2= df.format(endCal2.getTime());
																
																
																VEdEligibilityIndvCargo[] vEdEligibilityIndCargos=(VEdEligibilityIndvCargo[])coDAOServices.getByEdStatusTypeCO(sDate2,eDate2,prog,"'"+CoConstants.EDBC_STATUS_CD_APPROVED+"'");
																if(null!=vEdEligibilityIndCargos && vEdEligibilityIndCargos.length>0){
																	for(VEdEligibilityIndvCargo vEdEligibilityIndCargo:vEdEligibilityIndCargos){
																		long caseNum=vEdEligibilityIndCargo.getT1CaseNum();
																		addCasesForMassMail(activeCases, caseNum);
																	}
																}
															}
															
															if(null!=fiscalYear && fiscalYear.equals(CoConstants.CURR_FY) && null!=eligibleMap){
																for(long caseNum : eligibleMap.keySet()){
																	addCasesForMassMail(activeCases,caseNum);
																}
															}
													}
														
													}
													
													
												}//END criteria FISCAL YEAR
												
												//criteria case closure/denial
												if(filterCriteria.equals(CoConstants.CLOSURE_DENIAL) && filterusingData.containsKey(CoConstants.CLOSURE_DENIAL) && filterusingData.get(CoConstants.CLOSURE_DENIAL)!=null && !filterusingData.get(CoConstants.CLOSURE_DENIAL).isEmpty()){
													CoDebugger.debugInformation("HOUSEHOLD LIHEAP FLOW CASE CLOSURE STARTED>>>>>>>>>" );						
													String reasons="";
													String prefix="";

													for(String reason: filterusingData.get(CoConstants.CLOSURE_DENIAL)){
														if(null!=reason && reason.equals(CoConstants.EXCESS_INCOME)){
															reason=CoConstants.ED_RSN_CD_EXCESS_INCOME;
														}else if(null!=reason && reason.equals(CoConstants.EXCESS_ASSET)){
															reason=CoConstants.ED_RSN_CD_EXCESS_ASSET;
														}
														reasons=reasons+prefix+"'"+reason + "'";
														prefix=",";
													}	
													EdEligNoticeReasonsCargo[] edEligNoticeReasonsCargoArr=(EdEligNoticeReasonsCargo[])coDAOServices.getByDenialRsnLI(sDate,eDate,reasons);
													if(null!=edEligNoticeReasonsCargoArr && edEligNoticeReasonsCargoArr.length>0){
														for(EdEligNoticeReasonsCargo edEligNoticeReasonsCargo:edEligNoticeReasonsCargoArr){
															if(null!=edEligNoticeReasonsCargo && edEligNoticeReasonsCargo.getCaseNum()>0){
																addCasesForMassMail(activeCases,edEligNoticeReasonsCargo.getCaseNum());
															}
														}
														
													}
												}//end of criteria case closure/denial
											}
											
											//adding trigger in trigger List
											addingTriggerForMassMail(activeCases,coCorrespondenceList,arrCoMassMailingCargos, i, prog);	
										}
										
										totalExpected = totalExpected + activeCases.size();
										CoDebugger.debugInformation("createCorrespondenceTriggers ---> For massMailingID  ---> " + arrCoMassMailingCargos[i].getMassMailingSeqNum()
												+ " Expected triggerCount for ---> " + activeCases.size());	

									} else if(("CD".equalsIgnoreCase(prog) && dataEntered!=null && dataEntered.length>1 && "PROVIDER".equalsIgnoreCase(dataEntered[1]))){
										CoDebugger.debugInformation("PROVIDER FLOW STARTED>>>>>>>>>" );
										//For Filtering criterias and values
										String[] arrRecordPerReq=arrCoMassMailingCargos[i].getProgramLst().split(";");

										HashMap<String,ArrayList<String>> filterusingData= new HashMap<String,ArrayList<String>>();
										Set<String> filterCriterias=new HashSet<String>();

										//to extract distinct filter Criteria and Values
										initializeFilterCriteriasValues(arrRecordPerReq,filterusingData,filterCriterias);

										if(!filterCriterias.isEmpty()){

											for(String filterCriteria:filterCriterias){

												if(filterCriteria.equals(CoConstants.COUNTY) && filterusingData.containsKey(CoConstants.COUNTY) && filterusingData.get(CoConstants.COUNTY)!=null && !filterusingData.get(CoConstants.COUNTY).isEmpty()){
													CoDebugger.debugInformation("PROVIDER FLOW  COUNTY STARTED>>>>>>>>>" );
													String counties="";
													String prefix="";

													for(String county: filterusingData.get(CoConstants.COUNTY)){
														counties=counties+prefix+"'"+county + "'";
														prefix=",";
													}												

													PmAddressCargo[] providersAddrArr=	(PmAddressCargo[])coDAOServices.getProviderCountyMassMail(scheduledDate,counties);
													PmProviderStatusCargo[] providerStatusArr=(PmProviderStatusCargo[])coDAOServices.getProviderStatusMassMail(scheduledDate);											


													if(providersAddrArr!=null && providersAddrArr.length>0){
														Set<Integer> providerActive= new HashSet<Integer>();
														for(PmProviderStatusCargo providerStatus:providerStatusArr){
															if(providerActive!=null && !providerActive.contains(providerStatus.getProviderId()))
																providerActive.add(providerStatus.getProviderId());
														}

														for(PmAddressCargo providerAddr:providersAddrArr){
															if(providerAddr.getAddrCountyCd()!=null){
																if(!providerActive.isEmpty() && providerActive.contains(providerAddr.getVendorProviderId())){
																	Integer providerId=providerAddr.getVendorProviderId();
																	if(providerId>0 && !activeProviders.contains(providerId)){
																		activeProviders.add(providerId);
																	}
																}

															}
														}
													}											

												}	//end of first criteria check-COUNTY					


												if(filterCriteria.equals(CoConstants.ENROLLMENTSTATUS) && filterusingData.containsKey(CoConstants.ENROLLMENTSTATUS) && filterusingData.get(CoConstants.ENROLLMENTSTATUS)!=null && !filterusingData.get(CoConstants.ENROLLMENTSTATUS).isEmpty()){
													CoDebugger.debugInformation("PROVIDER FLOW  ENROLLMENT STATUS STARTED>>>>>>>>>" );
													String statuses="";
													String prefix="";

													for(String status: filterusingData.get(CoConstants.ENROLLMENTSTATUS)){
														statuses=statuses+prefix+"'"+status + "'";
														prefix=",";
													}	

													PmProviderStatusCargo[] providerAllStatusArr=(PmProviderStatusCargo[])coDAOServices.getProviderAllStatusMassMail(scheduledDate,statuses);	
													if(providerAllStatusArr!=null && providerAllStatusArr.length>0){
														for(PmProviderStatusCargo providerAllStatus:providerAllStatusArr ){
															if(providerAllStatus.getProviderId()!=null){
																Integer providerId=providerAllStatus.getProviderId();
																if(providerId>0 && !activeProviders.contains(providerId)){
																	activeProviders.add(providerId);
																}
															}
														}

													}


												} //end of second criteria check


												if(filterCriteria.equals(CoConstants.LICENSETYPE) && filterusingData.containsKey(CoConstants.LICENSETYPE) && filterusingData.get(CoConstants.LICENSETYPE)!=null && !filterusingData.get(CoConstants.LICENSETYPE).isEmpty()){
													CoDebugger.debugInformation("PROVIDER FLOW  LICENSE TYPE STARTED>>>>>>>>>" );
													String licenses="";
													String prefix="";

													for(String license: filterusingData.get(CoConstants.LICENSETYPE)){
														licenses=licenses+prefix+"'"+license + "'";
														prefix=",";
													}	

													PmCcapProvidersCargo[] pmCcapProvidersArr=(PmCcapProvidersCargo[]) coDAOServices.getProviderLicenseTypesMassMail(scheduledDate,licenses);
													if(pmCcapProvidersArr!=null && pmCcapProvidersArr.length>0){
														for(PmCcapProvidersCargo pmCcapProvider:pmCcapProvidersArr){
															if(pmCcapProvider.getProviderId()!=null){
																Integer providerId=pmCcapProvider.getProviderId();
																if(providerId>0 && !activeProviders.contains(providerId)){
																	activeProviders.add(providerId);
																}
															}
														}
													}
												}//end of license type criteria check

											}

											//adding trigger in trigger List										
											if(activeProviders!=null && activeProviders.size()>0){
												for(Integer providerId:activeProviders){
													coCorrespondence= new COCorrespondence();
													coCorrespondence.setMassMailingId(arrCoMassMailingCargos[i].getMassMailingSeqNum());
													coCorrespondence.setMassGeneratedSw(CoConstants.CHAR_Y);
													coCorrespondence.setDocId(CoConstants.CO_DOC_ID_NCH049);
													coCorrespondence.setCaseAppNumber("");
													/**coCorrespondence.setCaseAppFlag('C');**/
													coCorrespondence.setProviderId(Long.parseLong(providerId.toString()));
													coCorrespondence.setDocType(CoConstants.CO_DOC_TYPE_CD_N);
													coCorrespondence.setAssistanceProgramCode(prog);
													coCorrespondence.setMiscParameters("P");
													coCorrespondence.setAttachments(arrCoMassMailingCargos[i].getAttachments());
													coCorrespondenceList.add(coCorrespondence);
												}

											}

										}

										totalExpected = totalExpected + activeProviders.size();
										CoDebugger.debugInformation("createCorrespondenceTriggers ---> For massMailingID  ---> " + arrCoMassMailingCargos[i].getMassMailingSeqNum()
												+ " Expected triggerCount for ---> " + activeProviders.size());	

									}//end of CCAP Provider Flow
									else if (("LI".equalsIgnoreCase(prog) && dataEntered!=null && dataEntered.length>1 && "VENDOR".equalsIgnoreCase(dataEntered[1]))){
										//start of Vendor Flow
										 CoDebugger.debugInformation("VENDOR FLOW STARTED>>>>>>>>>" );
										//For Filtering criterias and values
										String[] arrRecordPerReq=arrCoMassMailingCargos[i].getProgramLst().split(";");

										HashMap<String,ArrayList<String>> filterusingData= new HashMap<String,ArrayList<String>>();
										Set<String> filterCriterias=new HashSet<String>();

										//to extract distinct filter Criteria and Values
										initializeFilterCriteriasValues(arrRecordPerReq,filterusingData,filterCriterias);

										
										if(!filterCriterias.isEmpty()){

											for(String filterCriteria:filterCriterias){
												
												//processing per filterCriteria
												extractFilterCriteriaLIVendor(activeVendors, df,scheduledDate,filterusingData,filterCriteria);

											}
											

											//adding trigger in trigger List										
											if(activeVendors!=null && activeVendors.size()>0){
												for(Integer vendorId:activeVendors){
													coCorrespondence= new COCorrespondence();
													coCorrespondence.setMassMailingId(arrCoMassMailingCargos[i].getMassMailingSeqNum());
													coCorrespondence.setMassGeneratedSw(CoConstants.CHAR_Y);
													coCorrespondence.setDocId(CoConstants.CO_DOC_ID_NCH049);
													coCorrespondence.setCaseAppNumber("");
													/**coCorrespondence.setCaseAppFlag('C');**/
													coCorrespondence.setProviderId(Long.parseLong(vendorId.toString()));
													coCorrespondence.setDocType(CoConstants.CO_DOC_TYPE_CD_N);
													coCorrespondence.setAssistanceProgramCode(prog);
													coCorrespondence.setMiscParameters("V");
													coCorrespondence.setAttachments(arrCoMassMailingCargos[i].getAttachments());
													coCorrespondenceList.add(coCorrespondence);
												}

											}
										}
										
										totalExpected = totalExpected + activeVendors.size();
										CoDebugger.debugInformation("createCorrespondenceTriggers ---> For massMailingID  ---> " + arrCoMassMailingCargos[i].getMassMailingSeqNum()
												+ " Expected triggerCount for ---> " + activeVendors.size());	
									} // end of Vendor Flow

								}//end if no prog list populated
							} catch(NoDataFoundException nde){
								CoDebugger.debugException("No data in EdEligibility found", nde);
								CoDebugger.debugInformation("No data in EdEligibility found"+nde.getMessage());								
							}catch (CoException e) {
								CoDebugger.debugInformation("createCorrespondenceTriggers ---> Exception while creating COCorrespondence objects for massMailingID  ---> "
										+ arrCoMassMailingCargos[i].getMassMailingSeqNum());
								CoDebugger.debugException(e.getMessage(), e);
								if(e.getMessage()!=null){
									writeException("Exception while creating COCorrespondence objects for massMailingID  ---> "+ arrCoMassMailingCargos[i].getMassMailingSeqNum()+" jobId ---> " + jobId + " and docId ---> "+ triggerType,e.getMessage(), e, false);
								}else{
									writeException("Exception while creating COCorrespondence objects for massMailingID  ---> "+ arrCoMassMailingCargos[i].getMassMailingSeqNum()+" jobId ---> " + jobId + " and docId ---> "+ triggerType,e.toString(), e, false);
								}
								continue;
							}
							arrCoMassMailingCargos[i].setJobProcessedSw(CoConstants.YES_CHAR);
							arrCoMassMailingCargos[i].setMassMailingId(arrCoMassMailingCargos[i].getMassMailingSeqNum());
							coMassMailingReqColl.add(arrCoMassMailingCargos[i]);
						}
					} else {
						CoDebugger.debugInformation("createCorrespondenceTriggers ---> No Mass Mailing Request Found");
					}
				} catch (CoException e) {
					CoDebugger.debugInformation("createCorrespondenceTriggers ---> Exception while creating coCorrespondenceList for jobId ---> " + jobId + " and docId ---> "+ triggerType);
					CoDebugger.debugException(e.getMessage(), e);
					writeException("Could not insert triggers for jobId:" + jobId, "createCorrespondenceTriggers ---> Exception while creating coCorrespondenceList jobId ---> " + jobId + " and docId ---> "+ triggerType, e, false);
					throw new CoException("createCorrespondenceTriggers ---> Exception while creating coCorrespondenceList jobId ---> " + jobId + " and docId ---> "+ triggerType);
				}
			
			} else if (triggerType != null && (triggerType.equalsIgnoreCase(CoConstants.NDHCGNF15) 
					|| triggerType.equalsIgnoreCase(CoConstants.NDHCREN17))) {
				if(triggerType.equalsIgnoreCase(CoConstants.NDHCGNF15)){
					docName = "Reminder To Report Changes";
				}else{
					docName = "Health Care Coverage Closure";
				}
				EdEligibilityCargo[] edEligibilityCargos1 = null;
				EdEligibilityCargo[] edEligibilityCargos2 = null;


				totalReadCount = new StringBuffer();
				totalFailed = new StringBuffer();
				totalSuccess = new StringBuffer();
				coCorrespondenceList = new ArrayList<COCorrespondence>();
				final int noOfWorkingDays = 4;

				try {
					Timestamp currentTimeStamp = CoDateFactory.getTimestamp();
					CoDebugger.debugInformation("createCorrespondenceTriggers ---> JobId ---> "+ jobId+ " AS OF DATE Timestamp:  ------> " + currentTimeStamp.toString());
					java.util.Date currentDate = currentTimeStamp;
					Timestamp adequateDate = getAdequateNoticeDate(triggerType, currentDate);
					Timestamp lastWorkingDay = ALSOPUtil.getLastWorkingDayOfMonth(currentTimeStamp);
					CoDebugger.debugInformation("createCorrespondenceTriggers ---> JobId ---> "+ jobId+ " ADEQUATENOTICEDAY Timestamp:  ------> " + currentTimeStamp.toString());

					String enableNonACA = CoConstants.N;
			        if (FwPropertyLoader.getPropertyOf(FwConstants.APPLICATION_PROPERTY_FILE_NAME, "enableNonACA") != null) {
			        	enableNonACA = FwPropertyLoader.getPropertyOf(FwConstants.APPLICATION_PROPERTY_FILE_NAME, "enableNonACA");
			        } 
					
					if (adequateDate != null) {
						SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
						String currentDateString = dateFormat.format(currentDate);
						String adequateDateString = dateFormat.format(ALSOPUtil.addToPreviousWorkingDays(lastWorkingDay,noOfWorkingDays));
						//ND-58777 Adequate date check removed.
						adequateDateString=currentDateString;
						CoDebugger.debugInformation("createCorrespondenceTriggers ---> JobId ---> "+ jobId+ " AS OF DATE String:  ------> " + currentDateString);
						CoDebugger.debugInformation("createCorrespondenceTriggers ---> JobId ---> "+ jobId+ " ADEQUATENOTICEDAY String:  ------> " + adequateDateString);
						CoDebugger.debugInformation("createCorrespondenceTriggers ---> JobId ---> "+ jobId+ " ADEQUATENOTICEDAY & AS OF DATE Comparision   ------> BEGIN");
						CoDebugger.debugInformation("createCorrespondenceTriggers ---> JobId ---> "+ jobId+ " ADEQUATENOTICEDAY & AS OF DATE Comparision   ------> SUCCESS");
						CoDebugger.debugInformation("BEGIN: Trigger Creation For docId " + triggerType);
							if(triggerType.equalsIgnoreCase(CoConstants.NDHCGNF15)){
								edEligibilityCargos1 = (EdEligibilityCargo[]) coDAOServices.findEdEligibilityForNDHCGNF15(adequateDateString,CoConstants.YES_STRING_Y);
								if(enableNonACA.equals(CoConstants.Y))
								{								
								edEligibilityCargos2 = (EdEligibilityCargo[]) coDAOServices.findEdEligibilityForNDHCGNF15(adequateDateString,CoConstants.NO_STRING_N);
								}
								Set<EdEligibilityCargo> acaOnlySet=null;
								Set<EdEligibilityCargo> nonACASet=null;
								
								//identifyACAOnly
								if (edEligibilityCargos1 != null && edEligibilityCargos1.length > 0) {
									//CR-458 Check Change reporting TOA for both ACA and Non-ACA set:
									//edEligibilityCargos1=checkToaForChangeReporting(edEligibilityCargos1);
									//CR-458 Check Change reporting--END
									String checkACA=null;
									acaOnlySet = new HashSet<EdEligibilityCargo>();
									for (EdEligibilityCargo edEligibilityCargo : edEligibilityCargos1) {
										long caseNum = edEligibilityCargo.getCaseNum();
										CoDebugger.debugInformation("createCorrespondenceTriggers --->identifyACAOnly LOOP ****"+caseNum);
										checkACA=this.identifyACAOnlyMembers(caseNum);
										if(null!=checkACA && checkACA.equalsIgnoreCase(CoConstants.YES_STRING_Y)){
											CoDebugger.debugInformation("checkACA-->CoConstants.YES_STRING_Y-->CaseNum"+edEligibilityCargo.getCaseNum());
											acaOnlySet.add(edEligibilityCargo);
										}
									}

								}
								//identify Non ACA
								if (edEligibilityCargos2 != null && edEligibilityCargos2.length > 0) {
									//CR-458 Check Change reporting TOA for both ACA and Non-ACA set:
									edEligibilityCargos2=checkToaForChangeReporting(edEligibilityCargos2);
									//CR-458 Check Change reporting--END
									String checkACA=null;
									nonACASet = new HashSet<EdEligibilityCargo>();
									for (EdEligibilityCargo edEligibilityCargo : edEligibilityCargos2) {
										long caseNum = edEligibilityCargo.getCaseNum();
										CoDebugger.debugInformation("createCorrespondenceTriggers --->identifyNONACA LOOP #####"+caseNum);
										checkACA=this.identifyACAOnlyMembers(caseNum);
										if(null!=checkACA && checkACA.equalsIgnoreCase(CoConstants.NO_STRING_N)){
											nonACASet.add(edEligibilityCargo);
										}
									}
								}
								
								if(null!=acaOnlySet && acaOnlySet.size()>0){
									CoDebugger.debugInformation("ACA ONLY POPULATED::");
									CoDebugger.debugInformation("createCorrespondenceTriggers --->NDHCGNF15-->****-->acaOnlySet: "+acaOnlySet);
									//Convert ACA only to CaseNum List (NO change reporting Filter)
									triggerList=getACACaseNumList(acaOnlySet);
									
								}
								if(null!=nonACASet && nonACASet.size()>0){
									CoDebugger.debugInformation("NON ACA POPULATED::");
									CoDebugger.debugInformation("createCorrespondenceTriggers --->NDHCGNF15-->nonACASet2: "+nonACASet);
									//Convert Non ACA to CaseNum List (WITH change reporting Filter)
									triggerList.addAll(getNonAcaChangeReporting(nonACASet));
								}
								CoDebugger.debugInformation("FINAL Trigger List"+triggerList);
							}
							if (triggerList != null && triggerList.size()>0) {
								CoDebugger.debugInformation("createCorrespondenceTriggers ---> EdEligibilityCargo size for jobId ---> " + jobId + " docId ---> "+ triggerType +" is ---> "+ triggerList.size());
								activeCases = new HashSet<Long>();
								for (long caseNum : triggerList) {
									if (caseNum > 0 && !activeCases.contains(caseNum)) {
										activeCases.add(caseNum);
										coCorrespondence = new COCorrespondence();
										coCorrespondence.setDocId(triggerType);
										coCorrespondence.setCaseAppNumber(String.valueOf(caseNum));
										coCorrespondence.setCaseAppFlag('C');
										coCorrespondence.setDocType(CoConstants.CO_DOC_TYPE_CD_N);
										coCorrespondence.setAssistanceProgramCode(CoConstants.MA);
										coCorrespondenceList.add(coCorrespondence);
									}
								}
								totalExpected = activeCases.size();
							} else {
								CoDebugger.debugInformation("createCorrespondenceTriggers ---> Expected triggerCount for ---> " + jobId + " is  ---> 0");
							}
					} else {
						CoDebugger.debugInformation("createCorrespondenceTriggers ---> Failed to compute ADEQUATENOTICEDAY for " + jobId + "  ------> FAILED");
					}

				} catch (CoException e) {
					if(null==edEligibilityCargos1 && null == edEligibilityCargos2){
						writeException("Could not insert triggers for jobId:" + jobId, "createCorrespondenceTriggers ---> Exception while creating coCorrespondenceList edEligibilityCargos1 and edEligibilityCargos1 **** jobId ---> " + jobId + " and docId ---> "+ triggerType, e, false);
						throw new CoException("createCorrespondenceTriggers ---> Exception while creating edEligibilityCargos1 and edEligibilityCargos1 jobId ---> " + jobId + " and docId ---> "+ triggerType);	
					} else if(null == edEligibilityCargos2){
						CoDebugger.debugInformation("createCorrespondenceTriggers ---> Exception while creating edEligibilityCargos2##### for jobId ---> " + jobId + " and docId ---> "+ triggerType);
						CoDebugger.debugException(e.getMessage(), e);
					} else if(null == edEligibilityCargos1){
						CoDebugger.debugInformation("createCorrespondenceTriggers ---> Exception while creating edEligibilityCargos1***** for jobId ---> " + jobId + " and docId ---> "+ triggerType);
						CoDebugger.debugException(e.getMessage(), e);
					}
				
					
					CoDebugger.debugInformation("createCorrespondenceTriggers ---> Exception while creating coCorrespondenceList for jobId ---> " + jobId + " and docId ---> "+ triggerType);
					CoDebugger.debugException(e.getMessage(), e);
					writeException("Could not insert triggers for jobId:" + jobId, "createCorrespondenceTriggers ---> Exception while creating coCorrespondenceList jobId ---> " + jobId + " and docId ---> "+ triggerType, e, false);
					throw new CoException("createCorrespondenceTriggers ---> Exception while creating coCorrespondenceList jobId ---> " + jobId + " and docId ---> "+ triggerType);
				}
			}else if(triggerType != null && triggerType.equalsIgnoreCase(CoConstants.DOC_ID_NDTFMRN77)){
				docName = "Medical Socia Review Due";
				totalReadCount = new StringBuffer();
				totalFailed = new StringBuffer();
				totalSuccess = new StringBuffer();
				DcCaseIndividualCargo[] dcCaseIndvCargos = null;
				coCorrespondenceList = new ArrayList<COCorrespondence>();
				if (triggerType.equalsIgnoreCase(CoConstants.DOC_ID_NDTFMRN77)) {
					dcCaseIndvCargos = getTriggersForNDTFMRN77(dcCaseIndvCargos,asOfDate);
				}
				if(dcCaseIndvCargos != null){
					CoDebugger.debugInformation("Size of DcDisabilityRecords selected for " + CoConstants.DOC_ID_NDTFMRN77 + " --> "+dcCaseIndvCargos.length);

					Map<Long,Integer> caseNumberCountMap = new HashMap<Long,Integer>();
					Set<Long> caseNumbers = new HashSet<Long>();	
					boolean manualSwitch= true; 
					activeCases = new HashSet<Long>();
					for(DcCaseIndividualCargo dcCaseIndvCargo : dcCaseIndvCargos){
						long caseNum = dcCaseIndvCargo.getCaseNum();
						if(caseNum> 0 && !caseNumbers.contains(caseNum)){
							activeCases.add(caseNum);
							caseNumbers.add(dcCaseIndvCargo.getCaseNum());
							coCorrespondence = new COCorrespondence();
							coCorrespondence.setDocId(triggerType);
							coCorrespondence.setCaseAppNumber(String.valueOf(caseNum));
							coCorrespondence.setCaseAppFlag('C');
							coCorrespondence.setIndvId(dcCaseIndvCargo.getIndvId());
							coCorrespondence.setDocType(CoConstants.CO_DOC_TYPE_CD_N);
							coCorrespondence.setIsManualyGenerated(manualSwitch);
							coCorrespondenceList.add(coCorrespondence);
						}else{
							if(!caseNumberCountMap.containsKey(caseNum)){
								caseNumberCountMap.put(caseNum, 1);
							}
							int count = caseNumberCountMap.get(caseNum);
							caseNumberCountMap.put(caseNum, count+1);

						}
					}
					totalExpected = activeCases.size();
				}

			}else if(triggerType != null && triggerType.equalsIgnoreCase(CoConstants.DOC_ID_NDMATBN97)){

				docName = "Notification of Termination of Benefits";
				totalReadCount = new StringBuffer();
				totalFailed = new StringBuffer();
				totalSuccess = new StringBuffer();
				VEdEligibilityIndvCargo[] vEdEligibilityCargos = null;
				coCorrespondenceList = new ArrayList<COCorrespondence>();
				
				SimpleDateFormat datetimeFormatter1 = new SimpleDateFormat(CoConstants.DATE_FORMAT);
				Date batchAsOfDate = datetimeFormatter1.parse(asOfDate);
				Timestamp asOfDateTimestamp = new Timestamp(batchAsOfDate.getTime());
				
				vEdEligibilityCargos = getTriggersForNDMATBN97(vEdEligibilityCargos, asOfDateTimestamp);
				
				if(vEdEligibilityCargos != null){
					CoDebugger.debugInformation("Size of EdEligibilityRecords selected for " + CoConstants.DOC_ID_NDMATBN97 + " --> "+vEdEligibilityCargos.length);

					Map<Long,Integer> caseNumberCountMap = new HashMap<Long,Integer>();
					Set<Long> caseNumbers = new HashSet<Long>();	
					boolean manualSwitch= true; 
					activeCases = new HashSet<Long>();
					for(VEdEligibilityIndvCargo edEligibilityCargo : vEdEligibilityCargos){

						long caseNum = edEligibilityCargo.getT1CaseNum();
						if(caseNum> 0 && !caseNumbers.contains(caseNum)){
							activeCases.add(caseNum);
							caseNumbers.add(edEligibilityCargo.getT1CaseNum());
							coCorrespondence = new COCorrespondence();
							coCorrespondence.setDocId(triggerType);
							coCorrespondence.setCaseAppNumber(String.valueOf(caseNum));
							coCorrespondence.setCaseAppFlag('C');
							coCorrespondence.setIndvId(edEligibilityCargo.getIndvId());
							coCorrespondence.setDocType(CoConstants.CO_DOC_TYPE_CD_N);
							coCorrespondence.setIsManualyGenerated(manualSwitch);
							coCorrespondence.setAssistanceProgramCode(CoConstants.MA);
							coCorrespondenceList.add(coCorrespondence);
						}else{
							if(!caseNumberCountMap.containsKey(caseNum)){
								caseNumberCountMap.put(caseNum, 1);
							}
							int count = caseNumberCountMap.get(caseNum);
							caseNumberCountMap.put(caseNum, count+1);

						}
					}
					totalExpected = activeCases.size();
				}


			}else if(triggerType != null && triggerType.equalsIgnoreCase(CoConstants.DOC_ID_NDCCAP71)){

				docName = "Payment Notification Client";
				totalReadCount = new StringBuffer();
				totalFailed = new StringBuffer();
				totalSuccess = new StringBuffer();
				VBiCcdmiPayeeCargo[] vBiCcdmiPayeeCargo = null;
				VBiWarrantDetailCargo[] vBiWarrantDetailCargo = null;
				coCorrespondenceList = new ArrayList<COCorrespondence>();
				activeCases = new HashSet<Long>();
				Map<Long,Integer> caseNumberCountMap = new HashMap<Long,Integer>();
				Set<Long> caseNumbers = new HashSet<Long>();	

				if (triggerType.equalsIgnoreCase(CoConstants.DOC_ID_NDCCAP71)) {
					try {
						vBiCcdmiPayeeCargo = getCcdmiTriggersForNDCCAP71(vBiCcdmiPayeeCargo);
					} catch (Exception e) {
						CoDebugger.debugException("Exception while fetching cargos for NDCCAP71",e);
					}
					try {
						vBiWarrantDetailCargo = getWarrantTriggersForNDCCAP71(vBiWarrantDetailCargo);
					} catch (Exception e) {
						CoDebugger.debugException("Exception while fetching cargos for NDCCAP71",e);
					}
				}
				if(vBiCcdmiPayeeCargo != null){
					CoDebugger.debugInformation("Size of PaymentNotificationClientRecords selected from  VBiCcdmiPayee table for" + CoConstants.DOC_ID_NDCCAP71 + " --> "+vBiCcdmiPayeeCargo.length);

					boolean manualSwitch= true; 
					for(VBiCcdmiPayeeCargo vBiCcdmiPayee : vBiCcdmiPayeeCargo){

						long caseNum = vBiCcdmiPayee.getCaseNum();
						if(caseNum> 0 && !caseNumbers.contains(caseNum)){
							activeCases.add(caseNum);
							caseNumbers.add(vBiCcdmiPayee.getCaseNum());
							coCorrespondence = new COCorrespondence();
							coCorrespondence.setDocId(triggerType);
							coCorrespondence.setCaseAppNumber(String.valueOf(caseNum));
							coCorrespondence.setCaseAppFlag('C');
							coCorrespondence.setIndvId(vBiCcdmiPayee.getIndvId());
							coCorrespondence.setDocType(CoConstants.CO_DOC_TYPE_CD_N);
							coCorrespondence.setIsManualyGenerated(manualSwitch);
							coCorrespondence.setAssistanceProgramCode(CoConstants.PROGRAM_CD);
							coCorrespondenceList.add(coCorrespondence);
						}else{
							if(!caseNumberCountMap.containsKey(caseNum)){
								caseNumberCountMap.put(caseNum, 1);
							}
							int count = caseNumberCountMap.get(caseNum);
							caseNumberCountMap.put(caseNum, count+1);

						}
					}
				}
				if(vBiWarrantDetailCargo != null){
					CoDebugger.debugInformation("Size of PaymentNotificationClientRecords selected from  vBiWarrantDetail table for" + CoConstants.DOC_ID_NDCCAP71 + " --> "+vBiWarrantDetailCargo.length);

					boolean manualSwitch= true; 
					for(VBiWarrantDetailCargo vBiWarrantDetail : vBiWarrantDetailCargo){

						long caseNum = vBiWarrantDetail.getCaseNum();
						if(caseNum> 0 && !caseNumbers.contains(caseNum)){
							activeCases.add(caseNum);
							caseNumbers.add(vBiWarrantDetail.getCaseNum());
							coCorrespondence = new COCorrespondence();
							coCorrespondence.setDocId(triggerType);
							coCorrespondence.setCaseAppNumber(String.valueOf(caseNum));
							coCorrespondence.setCaseAppFlag('C');
							coCorrespondence.setIndvId(vBiWarrantDetail.getT3IndvId());
							coCorrespondence.setDocType(CoConstants.CO_DOC_TYPE_CD_N);
							coCorrespondence.setIsManualyGenerated(manualSwitch);
							coCorrespondence.setAssistanceProgramCode(CoConstants.PROGRAM_CD);
							coCorrespondenceList.add(coCorrespondence);
						}else{
							if(!caseNumberCountMap.containsKey(caseNum)){
								caseNumberCountMap.put(caseNum, 1);
							}
							int count = caseNumberCountMap.get(caseNum);
							caseNumberCountMap.put(caseNum, count+1);

						}
					}
				}

				totalExpected = activeCases.size();
			}else if(triggerType != null && triggerType.equalsIgnoreCase(CoConstants.DOC_ID_NDLI0086)){
				docName = "Liheap Vendor Payment";
				totalReadCount = new StringBuffer();
				totalFailed = new StringBuffer();
				totalSuccess = new StringBuffer();
				BiIssuanceTriggerCargo[] biIssuanceTriggerCargo = null;
				coCorrespondenceList = new ArrayList<COCorrespondence>();
				if (triggerType.equalsIgnoreCase(CoConstants.DOC_ID_NDLI0086)) {
					biIssuanceTriggerCargo = getTriggersForNDLI0086(biIssuanceTriggerCargo,asOfDate);
				}
				if(biIssuanceTriggerCargo != null){
					CoDebugger.debugInformation("Size of LiheapVendorPaymentRecords selected for " + CoConstants.DOC_ID_NDLI0086 + " --> "+biIssuanceTriggerCargo.length);
					Map<Long,List<Long>> vendorCaseMap = new HashMap<Long,List<Long>>();
					for(BiIssuanceTriggerCargo biTriggerCargo : biIssuanceTriggerCargo){
						if(biTriggerCargo.getProviderId() != 0){
						if(!vendorCaseMap.containsKey(biTriggerCargo.getProviderId())){
							List<Long> list = new ArrayList<Long>();
							list.add(biTriggerCargo.getCaseNum());
							vendorCaseMap.put(biTriggerCargo.getProviderId(), list);
						}else{
							List<Long> list = vendorCaseMap.get(biTriggerCargo.getProviderId());
							if(!list.contains(biTriggerCargo.getCaseNum())){
								list.add(biTriggerCargo.getCaseNum());
							}
							vendorCaseMap.put(biTriggerCargo.getProviderId(), list);
						}	
					  }
					}
					if(vendorCaseMap.size()>0){
						CoDebugger.debugInformation("Total number of Vendor id for which triggers needs to be inserted :  --> "+vendorCaseMap.size());
						boolean manualSwitch= true;
						int triggerCount = 0;
						for (Map.Entry<Long, List<Long>> entry : vendorCaseMap.entrySet()){
						    CoDebugger.debugInformation("Generating trigger for Vendor id --> "+entry.getKey() +"and DOC ID : NDLI0086");
						    if(null!=entry.getValue() && entry.getValue().size()>0){
						    	for(Long caseNum : entry.getValue()){
								    triggerCount = triggerCount+1;
							    	coCorrespondence = new COCorrespondence();
							    	coCorrespondence.setDocId(triggerType);
									coCorrespondence.setCaseAppNumber(String.valueOf(caseNum));
									coCorrespondence.setCaseAppFlag('V');
									coCorrespondence.setDocType(CoConstants.CO_DOC_TYPE_CD_N);
									coCorrespondence.setIsManualyGenerated(manualSwitch);
									coCorrespondence.setAssistanceProgramCode(CoConstants.LIHEAP);
									coCorrespondence.setProviderId(entry.getKey());
									coCorrespondenceList.add(coCorrespondence);
						    	}
						    }	
						}
						totalExpected = triggerCount;
					}else{
						CoDebugger.debugInformation("Total number Of Case Num for which triggers needs to be inserted :  --> "+0);
					}
				}
			}else if(triggerType != null && triggerType.equalsIgnoreCase(CoConstants.DOC_ID_NDLI0104)){
				docName = "Remittance Advice";
				totalReadCount = new StringBuffer();
				totalFailed = new StringBuffer();
				totalSuccess = new StringBuffer();
				coCorrespondenceList = new ArrayList<COCorrespondence>();
				SimpleDateFormat datetimeFormatter1 = new SimpleDateFormat(CoConstants.DATE_FORMAT);
				Date batchAsOfDate = datetimeFormatter1.parse(asOfDate);
				Timestamp asOfDateTimestamp = new Timestamp(batchAsOfDate.getTime());
				String createDate=new SimpleDateFormat(CoConstants.ddMMMyy).format(asOfDateTimestamp);
				PmLiheapPaymentCargo pmLiheapPaymentCargo[] = null;
				pmLiheapPaymentCargo = coDAOServices.getRemittanceRecipients(createDate);
				if(pmLiheapPaymentCargo != null){
					CoDebugger.debugInformation("Size of PM_LIHEAP_PAYMENT records selected for " + CoConstants.DOC_ID_NDLI0104 + " --> "+pmLiheapPaymentCargo.length);
					Map<Long,Integer> vendorNumberCountMap = new HashMap<Long,Integer>();
					Set<Long> vendorNumbers = new HashSet<Long>();	
					boolean manualSwitch= true; 
					activeCases = new HashSet<Long>();
					for(PmLiheapPaymentCargo cargo : pmLiheapPaymentCargo){

						long vendorId = cargo.getVendorId();
						if(vendorId> 0 && !vendorNumbers.contains(vendorId)){
							activeCases.add(vendorId);
							vendorNumbers.add(cargo.getVendorId());
							coCorrespondence = new COCorrespondence();
							coCorrespondence.setDocId(triggerType);
							coCorrespondence.setDocType(CoConstants.CO_DOC_TYPE_CD_N);
							coCorrespondence.setIsManualyGenerated(manualSwitch);
							coCorrespondence.setAssistanceProgramCode(CoConstants.LIHEAP);
							coCorrespondence.setProviderId(vendorId);
							coCorrespondence.setMiscParameters(CoConstants.V);
							coCorrespondence.setCaseAppFlag('V');
							coCorrespondenceList.add(coCorrespondence);
						}else{
							if(!vendorNumberCountMap.containsKey(vendorId)){
								vendorNumberCountMap.put(vendorId, 1);
							}
							int count = vendorNumberCountMap.get(vendorId);
							vendorNumberCountMap.put(vendorId, count+1);

						}
					}
					totalExpected = activeCases.size();	
				}
			}else if(triggerType != null && triggerType.equalsIgnoreCase(CoConstants.NDTANFF72)){

				docName = "School Verification - Information Letter";
				totalReadCount = new StringBuffer();
				totalFailed = new StringBuffer();
				totalSuccess = new StringBuffer();
				EdIndvEligibilityCargo[] edIndvEligibilityCargo = null;
				coCorrespondenceList = new ArrayList<COCorrespondence>();


				if (triggerType.equalsIgnoreCase(CoConstants.NDTANFF72)) {
					edIndvEligibilityCargo = getTriggersForNDTANFF72(edIndvEligibilityCargo);
				}
				if(null!=edIndvEligibilityCargo){
					CoDebugger.debugInformation("Size of EdEligibilityRecords selected for " + CoConstants.NDTANFF72 + " --> "+edIndvEligibilityCargo.length);

					/**Map<Long,Integer> caseNumberCountMap = new HashMap<Long,Integer>();**/
					Map<Long,List<Long>> caseIndvMap = new HashMap<Long,List<Long>>();/**This map contains key as case number and values as list of indv id**/
					Set<Long> caseNumbers = new HashSet<Long>();	
					boolean manualSwitch= true; 
					activeCases = new HashSet<Long>();
					/**This loop is to avoid duplicate trigger for same case,indv_id combo**/
					for(EdIndvEligibilityCargo edIndvCargo : edIndvEligibilityCargo){
						if(edIndvCargo.getCaseNum() != 0){
							if(!caseIndvMap.containsKey(edIndvCargo.getCaseNum())){
								List<Long> list = new ArrayList<Long>();
								list.add(edIndvCargo.getIndvId());
								caseIndvMap.put(edIndvCargo.getCaseNum(), list);
							}else{
								List<Long> list = caseIndvMap.get(edIndvCargo.getCaseNum());
								if(!list.contains(edIndvCargo.getIndvId())){
									list.add(edIndvCargo.getIndvId());
								}
								caseIndvMap.put(edIndvCargo.getCaseNum(), list);
							}	
						  }
					}
					
					if(caseIndvMap.size()>0){
						CoDebugger.debugInformation("Total number of indv id for which triggers needs to be inserted :  --> "+caseIndvMap.size());
						int triggerCount = 0;
						for (Map.Entry<Long, List<Long>> entry : caseIndvMap.entrySet()){
						    CoDebugger.debugInformation("Generating trigger for case num --> "+entry.getKey() +"and DOC ID : NDTANFF72");
						    if(null!=entry.getValue() && entry.getValue().size()>0){
						    	for(Long indvId : entry.getValue()){
								    triggerCount = triggerCount+1;					
									long caseNum = entry.getKey();
									if(caseNum> 0){
										activeCases.add(caseNum);
										caseNumbers.add(caseNum);
										coCorrespondence = new COCorrespondence();
										coCorrespondence.setDocId(triggerType);
										coCorrespondence.setCaseAppNumber(String.valueOf(caseNum));
										coCorrespondence.setCaseAppFlag('C');
										coCorrespondence.setMiscParameters(String.valueOf(indvId));/*Added as part of CR 819,ND-102419*/
										coCorrespondence.setDocType(CoConstants.CO_DOC_TYPE_CD_N);
										coCorrespondence.setIsManualyGenerated(manualSwitch);
										coCorrespondence.setAssistanceProgramCode(CoConstants.PROGRAM_TF);
										coCorrespondenceList.add(coCorrespondence);
									}

						    	}
						    }
						}

						totalExpected = triggerCount;
				}else{
					CoDebugger.debugInformation("Total number Of Case Num for which triggers needs to be inserted :  --> "+0);
				}
				}
			
			}else if(triggerType != null && triggerType.equalsIgnoreCase(CoConstants.NDEBN111)){
                docName = "Extra Benefits";
                totalReadCount = new StringBuffer();
				totalFailed = new StringBuffer();
				totalSuccess = new StringBuffer();
				coCorrespondenceList = new ArrayList<COCorrespondence>();
				SimpleDateFormat datetimeFormatter1 = new SimpleDateFormat(CoConstants.DATE_FORMAT);
				Date batchAsOfDate = datetimeFormatter1.parse(asOfDate);
				Timestamp asOfDateTimestamp = new Timestamp(batchAsOfDate.getTime());
				String createDate=new SimpleDateFormat(CoConstants.ddMMMyy).format(asOfDateTimestamp);
				BiIssuanceTriggerCargo[] issuanceCargo = coDAOServices.getNDEBN111triggers(createDate);
				Map<Long,String> caseProg=new HashMap();
				String progExist="";
				for(int i=0;i<issuanceCargo.length;i++){
					if(caseProg.containsKey(issuanceCargo[i].getCaseNum())){
						progExist=caseProg.get(issuanceCargo[i].getCaseNum());
						if(progExist!=null && null!=issuanceCargo[i].getProgramCd() && !progExist.contains(issuanceCargo[i].getProgramCd())){
							progExist+=CoConstants.COMMA+issuanceCargo[i].getProgramCd();
						}else if(progExist==null && null!=issuanceCargo[i].getProgramCd()){
							progExist=issuanceCargo[i].getProgramCd();
						}
						caseProg.put(issuanceCargo[i].getCaseNum(),progExist);
					}else if(!caseProg.containsKey(issuanceCargo[i].getCaseNum()) && null!=issuanceCargo[i].getProgramCd()){
						caseProg.put(issuanceCargo[i].getCaseNum(),issuanceCargo[i].getProgramCd());
					}
				}
				if(issuanceCargo != null && issuanceCargo.length>0){
					CoDebugger.debugInformation("Size of Bi_Issuance_Trigger records selected for " + CoConstants.NDEBN111 + " --> "+issuanceCargo.length);
					Map<Long,Integer> caseNumberCountMap = new HashMap<Long,Integer>();
					Set<Long> caseNumbers = new HashSet<Long>();	
					boolean manualSwitch= true; 
					activeCases = new HashSet<Long>();
					for(BiIssuanceTriggerCargo biTriggerCargo : issuanceCargo){

						long caseNum = biTriggerCargo.getCaseNum();
						if(caseNum> 0 && !caseNumbers.contains(caseNum)){
							activeCases.add(caseNum);
							caseNumbers.add(biTriggerCargo.getCaseNum());
							coCorrespondence = new COCorrespondence();
							coCorrespondence.setDocId(triggerType);
							coCorrespondence.setCaseAppNumber(String.valueOf(caseNum));
							coCorrespondence.setCaseAppFlag('C');
							coCorrespondence.setIndvId(biTriggerCargo.getIndvId());
							coCorrespondence.setDocType(CoConstants.CO_DOC_TYPE_CD_N);
							coCorrespondence.setIsManualyGenerated(manualSwitch);
							coCorrespondence.setAssistanceProgramCode(caseProg.get(biTriggerCargo.getCaseNum()));
							coCorrespondenceList.add(coCorrespondence);
						}else{
							if(!caseNumberCountMap.containsKey(caseNum)){
								caseNumberCountMap.put(caseNum, 1);
							}
							int count = caseNumberCountMap.get(caseNum);
							caseNumberCountMap.put(caseNum, count+1);

						}
					}
					totalExpected = activeCases.size();	
				}
}  else if(triggerType != null && triggerType.equalsIgnoreCase(CoConstants.CO_DOC_ID_CCAP061)){
				docName = "Provider Closing";
				totalReadCount = new StringBuffer();
				totalFailed = new StringBuffer();
				totalSuccess = new StringBuffer();
				activeCases = new HashSet<Long>();
				SimpleDateFormat format=new SimpleDateFormat("MM/dd/yyyy");
				Date expDate=format.parse(asOfDate);
				format=new SimpleDateFormat("dd-MMM-yyyy");

				String expiryDate=format.format(expDate);

				PmProviderChildAssocCargo[] assocCargo=null;
				coCorrespondenceList = new ArrayList<COCorrespondence>();


				if (triggerType.equalsIgnoreCase(CoConstants.NDCCAP061)) {
					assocCargo = getTriggersForNDCCAP061(assocCargo,expiryDate);
				}
				if(assocCargo != null){		
					CoDebugger.debugInformation("Size of PmProviderChildAssoc Records selected for " + CoConstants.NDCCAP061 + " --> "+assocCargo.length);
					Map<Long,Integer> caseNumberCountMap = new HashMap<Long,Integer>();
					Set<Long> caseNumbers = new HashSet<Long>();	
					boolean manualSwitch= false; 
					activeCases = new HashSet<Long>();
					for(PmProviderChildAssocCargo pmProviderChildAssocCargo : assocCargo){

						long caseNum = pmProviderChildAssocCargo.getCaseNum();
						if(caseNum> 0 ){
							activeCases.add(caseNum);
							caseNumbers.add(pmProviderChildAssocCargo.getCaseNum());
							coCorrespondence = new COCorrespondence();
							coCorrespondence.setDocId(triggerType);
							coCorrespondence.setCaseAppNumber(String.valueOf(caseNum));
							coCorrespondence.setCaseAppFlag(CoConstants.CASE);
							coCorrespondence.setManualyGenerated(manualSwitch);
							coCorrespondence.setProviderId(pmProviderChildAssocCargo.getProviderId());
							coCorrespondence.setIndvId(pmProviderChildAssocCargo.getChildIndvId());
							coCorrespondence.setAssistanceProgramCode(CoConstants.CCAP_PROGRAM_CD);
							coCorrespondence.setDocType(CoConstants.CO_DOC_TYPE_CD_N);
							coCorrespondenceList.add(coCorrespondence);
						}else{
							if(!caseNumberCountMap.containsKey(caseNum)){
								caseNumberCountMap.put(caseNum, 1);
							}
							int count = caseNumberCountMap.get(caseNum);
							caseNumberCountMap.put(caseNum, count+1);

						}
					}
					totalExpected = coCorrespondenceList.size();
				}	
			} else if(triggerType != null && triggerType.equalsIgnoreCase(CoConstants.NDHCCSN01)){

				DcChildSuppNonCoopCargo[] dcChildSuppNonCoopCargos = null;
				DcCaseIndividualCargo[] caseIndividualCargos=null;
				List<Long> custodialParentIndvIdList=null;
				DcCaseProgramIndvCargo[] dcCaseProgramIndvCargos=null;

				totalReadCount = new StringBuffer();
				totalFailed = new StringBuffer();
				totalSuccess = new StringBuffer();
				coCorrespondenceList = new ArrayList<COCorrespondence>();
				try {
					Timestamp currentTimeStamp = CoDateFactory.getTimestamp();
					CoDebugger.debugInformation("createCorrespondenceTriggers ---> JobId ---> "+ jobId+ " AS OF DATE Timestamp:  ------> " + currentTimeStamp.toString());
					SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
					String currentAsOfDateString = dateFormat.format(currentTimeStamp);
					CoDebugger.debugInformation("createCorrespondenceTriggers ---> JobId ---> "+ jobId+ " currentAsOfDateString :  ------> " + currentAsOfDateString.toString());
					/**
					Provides a list of dcChildSuppNonCoopCargos with Good Cause End Date approximately around 1 month post AS OF DATE
					eg: END date = 27 FEB or 28 MARCH or even month end
					Assumption is that most of the cases would have the month end as Good Cause End Date
					Calling findForAsOfDate function would align all the cases to the 
					15th of the month prior to the Good Cause End Date and compare with the AS OF Date to filter out the cases.
					 */ 
					dcChildSuppNonCoopCargos = (DcChildSuppNonCoopCargo[]) coDAOServices.findIndvIDForAbsentParentGoodCauseNDHCCSN01(currentAsOfDateString);
					if(null!=dcChildSuppNonCoopCargos && dcChildSuppNonCoopCargos.length>0){
						/**Align the Good Cause End Date to the As Of Date and compare
						Receive a list of custodialParentIndvIdList matching the As Of Date*/
						custodialParentIndvIdList=findForAsOfDate(currentAsOfDateString,dcChildSuppNonCoopCargos);
						/**Get the case number from INDV IDs*/
						if(null!=custodialParentIndvIdList){
							caseIndividualCargos=(DcCaseIndividualCargo[]) coDAOServices.findByCustodialIndvIdExistsInCase(custodialParentIndvIdList);
						}else{
							CoDebugger.debugInformation("createCorrespondenceTriggers ---> findForAsOfDate --->custodialParentIndvIdList is empty....");
						}
						if(null!=caseIndividualCargos && caseIndividualCargos.length>0){
							List<DcCaseProgramIndvCargo> caseProgramFilterList=new ArrayList<DcCaseProgramIndvCargo>();
							DcCaseProgramIndvCargo[] caseProgramIndvCargo = null;

							for(DcCaseIndividualCargo individualCargo :caseIndividualCargos){
								long caseNumber=individualCargo.getCaseNum();
								long indvID=individualCargo.getIndvId();
								boolean maExists=false;
								boolean tfExists=false;
								caseProgramIndvCargo =(DcCaseProgramIndvCargo[])coDAOServices.findCaseIndvMaTf(caseNumber,indvID);
								for(DcCaseProgramIndvCargo indvCargo:caseProgramIndvCargo){
									if(indvCargo.getProgCd().equalsIgnoreCase(CoConstants.PROGRAM_MA) && !maExists){
										caseProgramFilterList.add(indvCargo);
										maExists = true;
									}
									if(indvCargo.getProgCd().equalsIgnoreCase(CoConstants.PROGRAM_TF) && !tfExists){
										caseProgramFilterList.add(indvCargo);
										tfExists=true;
									}
								}
							}
							Map<Long,String> caseProg=new HashMap();
							if(null!=caseProgramFilterList){
								dcCaseProgramIndvCargos=(DcCaseProgramIndvCargo[]) caseProgramFilterList.toArray(new DcCaseProgramIndvCargo[caseProgramFilterList.size()]);
								String progExist="";
								for(int i=0;i<dcCaseProgramIndvCargos.length;i++){
									if(caseProg.containsKey(dcCaseProgramIndvCargos[i].getCaseNum())){
										progExist=caseProg.get(dcCaseProgramIndvCargos[i].getCaseNum());
										if(progExist!=null && null!=dcCaseProgramIndvCargos[i].getProgCd() && !progExist.contains(dcCaseProgramIndvCargos[i].getProgCd())){
											progExist+=CoConstants.COMMA+dcCaseProgramIndvCargos[i].getProgCd();
										}else if(progExist==null && null!=dcCaseProgramIndvCargos[i].getProgCd()){
											progExist=dcCaseProgramIndvCargos[i].getProgCd();
										}
										caseProg.put(dcCaseProgramIndvCargos[i].getCaseNum(),progExist);
									}else if(!caseProg.containsKey(dcCaseProgramIndvCargos[i].getCaseNum()) && null!=dcCaseProgramIndvCargos[i].getProgCd()){
										caseProg.put(dcCaseProgramIndvCargos[i].getCaseNum(),dcCaseProgramIndvCargos[i].getProgCd());
									}
								}
							}
							CoDebugger.debugInformation("createCorrespondenceTriggers ---> caseIndividualCargos size for jobId ---> " + jobId + " docId ---> "+ triggerType +" is ---> "+ caseIndividualCargos.length);
							activeCases = new HashSet<Long>();
							for (DcCaseProgramIndvCargo caseCargo : dcCaseProgramIndvCargos) {
								boolean caseExistInSet=activeCases.add(caseCargo.getCaseNum());
								if (caseExistInSet) {
									coCorrespondence = new COCorrespondence();
									coCorrespondence.setDocId(triggerType);
									coCorrespondence.setCaseAppNumber(String.valueOf(caseCargo.getCaseNum()));
									coCorrespondence.setCaseAppFlag('C');
									coCorrespondence.setDocType(CoConstants.CO_DOC_TYPE_CD_N);
									coCorrespondence.setAssistanceProgramCode(caseProg.get(caseCargo.getCaseNum()));
									coCorrespondenceList.add(coCorrespondence);
								}
							}
							totalExpected = activeCases.size();
						} else {
							CoDebugger.debugInformation("createCorrespondenceTriggers ---> Expected triggerCount for ---> " + jobId + " is  ---> 0");
						}

						CoDebugger.debugInformation("BEGIN: Trigger Creation For docId " + triggerType);
					}else{
						CoDebugger.debugInformation("createCorrespondenceTriggers ---> dcChildSuppNonCoopCargos ---> Expected triggerCount for ---> " + jobId + " is  ---> 0");
					}
				} catch (CoException e) {
					CoDebugger.debugInformation("createCorrespondenceTriggers ---> Exception while creating coCorrespondenceList for jobId ---> " + jobId + " and docId ---> "+ triggerType);
					CoDebugger.debugException(e.getMessage(), e);
					writeException("Could not insert triggers for jobId:" + jobId, "createCorrespondenceTriggers ---> Exception while creating coCorrespondenceList jobId ---> " + jobId + " and docId ---> "+ triggerType, e, false);
					throw new CoException("createCorrespondenceTriggers ---> Exception while creating coCorrespondenceList jobId ---> " + jobId + " and docId ---> "+ triggerType);
				}
			}else if(triggerType != null && triggerType.equalsIgnoreCase(CoConstants.DOC_NDUNN054)){
				docName = "Notice of Claim";
				totalReadCount = new StringBuffer();
				totalFailed = new StringBuffer();
				totalSuccess = new StringBuffer();
				BvClaimCargo[] bvClaimCargoArr = null;
				coCorrespondenceList = new ArrayList<COCorrespondence>();
				Map<Long,List<String>> caseClaimMap = new LinkedHashMap<Long,List<String>>();
				Map<Long,HashSet<String>> caseProgramMap = new LinkedHashMap<Long,HashSet<String>>();
				bvClaimCargoArr = getTriggersForNDUNN054(asOfDate);
				if(null!=bvClaimCargoArr){
					CoDebugger.debugInformation("Size of BvClaimsRecords selected for " + CoConstants.DOC_NDUNN054 + " --> "+bvClaimCargoArr.length);
					for (BvClaimCargo bvClaimCargo : bvClaimCargoArr) {
						BvClaimHeaderCargo[] bvClaimHeaderCargo = null;
						try{
							bvClaimHeaderCargo = coDAOServices.getCaseNumFromClaimIds(bvClaimCargo.getClaimId());
						}catch(Exception e){
							CoDebugger.debugInformation("createCorrespondenceTriggers ---> Exception while fetching case number claim--> "+bvClaimCargo.getClaimId()+" jobId ---> " + jobId + " and docId ---> "+ triggerType);
							CoDebugger.debugException(e.getMessage(), e);
						}
						
						if(null!=bvClaimHeaderCargo && bvClaimHeaderCargo.length>0 && bvClaimHeaderCargo[0].getCaseNum()>0){
							/**Picking only the claims that are pertaining to household*/
							long caseNum = bvClaimHeaderCargo[0].getCaseNum();
							/**Populating case claim map*/
							if(caseClaimMap.containsKey(caseNum)){
								caseClaimMap.get(caseNum).add(bvClaimCargo.getClaimId());
							}else{
								List<String> claimString = new ArrayList<String>();
								claimString.add(bvClaimCargo.getClaimId());
								caseClaimMap.put(caseNum, claimString);
							}
							
							/**Populating case program map*/
							String programCd = bvClaimCargo.getProgramCd();
							if(caseProgramMap.containsKey(caseNum)){
								caseProgramMap.get(caseNum).add(programCd);
							}else{
								HashSet<String> progString = new HashSet<String>();
								progString.add(programCd);
								caseProgramMap.put(caseNum, progString);
							}
						}
					}
				}else{
					CoDebugger.debugInformation("Size of BvClaimsRecords selected for " + CoConstants.DOC_NDUNN054 + " --> "+0);
				}
				if(caseClaimMap.size()>0){
					CoDebugger.debugInformation("Total number of Case Num for which triggers needs to be inserted : " + CoConstants.DOC_NDUNN054 + " --> "+caseClaimMap.size());
					boolean manualSwitch= false;
					int triggerCount = 0;
					for (Map.Entry<Long, List<String>> entry : caseClaimMap.entrySet()){
					    CoDebugger.debugInformation("Generating trigger for Case Number  --> "+entry.getKey() +"and DOC ID : "+CoConstants.DOC_NDUNN054);
					    triggerCount = triggerCount+1;
					    List<String> claimList = entry.getValue();
					    HashSet<String> programList = caseProgramMap.get(entry.getKey());
					    String claimListString = null!=claimList ? (org.apache.commons.lang.StringUtils.join(claimList, ',')) : CoConstants.EMPTY_STRING;
					    String programString =  null!=programList ? (org.apache.commons.lang.StringUtils.join(programList, ',')): CoConstants.EMPTY_STRING;
				    	coCorrespondence = new COCorrespondence();
				    	coCorrespondence.setDocId(triggerType);
						coCorrespondence.setCaseAppNumber(String.valueOf(entry.getKey()));
						coCorrespondence.setCaseAppFlag('C');
						coCorrespondence.setDocType(CoConstants.CO_DOC_TYPE_CD_N);
						coCorrespondence.setIsManualyGenerated(manualSwitch);
						coCorrespondence.setMiscParameters(claimListString);
						coCorrespondence.setAssistanceProgramCode(programString);
						coCorrespondenceList.add(coCorrespondence);
					}
					totalExpected = triggerCount;
				}else{
					CoDebugger.debugInformation("Total number Of Case Num for which triggers needs to be inserted : " + CoConstants.DOC_NDUNN054 + " --> "+0);
				}
			}else if(triggerType != null && triggerType.equalsIgnoreCase(CoConstants.DOC_ID_NDCCAP069)){/**@author karraj for 'Request for Payment form for Providers' ND-53967 */
				
				docName = "Request for Payment form for Providers";
				totalReadCount = new StringBuffer();
				totalFailed = new StringBuffer();
				totalSuccess = new StringBuffer();
				coCorrespondenceList = new ArrayList<COCorrespondence>();
				int triggerCount = 0;
				PmProviderChildAssocCargo pmProviderChildAssocCargo[] = null;
				if(jobId.contains("MLY"))
					pmProviderChildAssocCargo = getTriggersForNDCCAP069MLY(asOfDate);
				else
					pmProviderChildAssocCargo = getTriggersForNDCCAP069(asOfDate);
				if(pmProviderChildAssocCargo != null){
					CoDebugger.debugInformation("Size of PM_PROVIDER_CHILD_ASSOC records selected for " + CoConstants.DOC_ID_NDCCAP069 + " --> "+pmProviderChildAssocCargo.length);
					boolean manualSwitch= true; 
					activeCases = new HashSet<Long>();
					Map<Integer,Set<Long>> providerMap = new HashMap<Integer,Set<Long>>();
					for(PmProviderChildAssocCargo cargo : pmProviderChildAssocCargo){
						if(providerMap.containsKey(cargo.getProviderId())){
							providerMap.get(cargo.getProviderId()).add(cargo.getCaseNum());
						}else{
							Set<Long> caseList = new TreeSet<Long>();
							caseList.add(cargo.getCaseNum());
							providerMap.put(cargo.getProviderId(), caseList);
						}
					}
					for (Map.Entry<Integer, Set<Long>> entry : providerMap.entrySet()){
					    CoDebugger.debugInformation("Provider Id for " + CoConstants.DOC_ID_NDCCAP069 + " --> "+entry.getKey());
					    Set<Long> caseSet = entry.getValue();
					    for(Long caseNum: caseSet){
					    	triggerCount = triggerCount+1;
					    	CoDebugger.debugInformation("Inserting trigger for Case Number "+caseNum+ " and Provider Id "+entry.getKey());
					    	coCorrespondence = new COCorrespondence();
							coCorrespondence.setDocId(triggerType);
							coCorrespondence.setCaseAppNumber(String.valueOf(caseNum));
							if(jobId.contains("MLY")){
								coCorrespondence.setMiscParameters(String.valueOf(caseNum) + "|" + asOfDate);
							}else{
								coCorrespondence.setMiscParameters(String.valueOf(caseNum));
							}
							coCorrespondence.setChipAppNum(String.valueOf(caseNum));
							coCorrespondence.setCaseAppFlag(CoConstants.RECIPIENT_SPACES_PROVIDER);
							coCorrespondence.setProviderId(entry.getKey());
							coCorrespondence.setDocType(CoConstants.CO_DOC_TYPE_CD_N);
							coCorrespondence.setIsManualyGenerated(manualSwitch);
							coCorrespondence.setAssistanceProgramCode(CoConstants.CCAP_PROGRAM_CD);
							coCorrespondenceList.add(coCorrespondence);
					    }
					}
					totalExpected = triggerCount;	
				}
			} else if(triggerType != null && triggerType.equalsIgnoreCase(CoConstants.CO_DOC_ID_CCAP062)){/**@author tdatta for 'Household Closing' ND-54028  */
				docName = "Household Closing";
				totalReadCount = new StringBuffer();
				totalFailed = new StringBuffer();
				totalSuccess = new StringBuffer();
				activeCases = new HashSet<Long>();
				boolean manualSwitch= false; 

				PmProviderChildAssocCargo[] assocCargo=null;
				coCorrespondenceList = new ArrayList<COCorrespondence>();
				Date date=new Date();
				
				
				SimpleDateFormat format=new SimpleDateFormat("MM/dd/yyyy");
				date=format.parse(asOfDate);
				Calendar cal = Calendar.getInstance();
			    cal.setTime(date); 
				cal.add(Calendar.MONTH,1); 
				
				date=cal.getTime();
				format=new SimpleDateFormat("dd-MMM-yyyy");

				String reviewDate=format.format(date);
				
				if (triggerType.equalsIgnoreCase(CoConstants.NDCCAP062)) {
					assocCargo = getTriggersForNDCCAP062(assocCargo,reviewDate);			
					if (assocCargo!=null && assocCargo.length>0){if(assocCargo != null){		
						CoDebugger.debugInformation("Size of PmProviderChildAssoc Records selected for " + CoConstants.NDCCAP062 + " --> "+assocCargo.length);
						Map<Long,Integer> caseNumberCountMap = new HashMap<Long,Integer>();
						Set<Long> caseNumbers = new HashSet<Long>();	
						activeCases = new HashSet<Long>();
						for(PmProviderChildAssocCargo pmProviderChildAssocCargo : assocCargo){

							long caseNum = pmProviderChildAssocCargo.getCaseNum();
							if(caseNum> 0 ){
								activeCases.add(caseNum); 
								caseNumbers.add(pmProviderChildAssocCargo.getCaseNum());
								coCorrespondence = new COCorrespondence();  
								coCorrespondence.setDocId(triggerType); 
								coCorrespondence.setMiscParameters(String.valueOf(caseNum));  
								coCorrespondence.setCaseAppFlag(CoConstants.RECIPIENT_SPACES_PROVIDER); 
 								coCorrespondence.setManualyGenerated(manualSwitch);   
								coCorrespondence.setProviderId(pmProviderChildAssocCargo.getProviderId());
								coCorrespondence.setIndvId(pmProviderChildAssocCargo.getChildIndvId());
								coCorrespondence.setDocType(CoConstants.CO_DOC_TYPE_CD_N); 
								coCorrespondence.setAssistanceProgramCode(CoConstants.CCAP_PROGRAM_CD);
								coCorrespondenceList.add(coCorrespondence);
							}else{
								if(!caseNumberCountMap.containsKey(caseNum)){
									caseNumberCountMap.put(caseNum, 1);
								}
								int count = caseNumberCountMap.get(caseNum);
								caseNumberCountMap.put(caseNum, count+1);

							}
						}
						totalExpected = coCorrespondenceList.size();
					}	
					}
				}
			}else if (triggerType != null && triggerType.equalsIgnoreCase(CoConstants.DOC_ID_NDHCC013)) {
					/**
					 * @author shreyasingh 
					 * for 'Permanently Institutionalised'
					 * ND-60936
					 */
					docName = "Permanently Institutionalised Notification";
					totalReadCount = new StringBuffer();
					totalFailed = new StringBuffer();
					totalSuccess = new StringBuffer();
					coCorrespondenceList = new ArrayList<COCorrespondence>();
					int triggerCount = 0;
					Map<Long, Integer> caseNumberCountMap = new HashMap<Long, Integer>();
					Set<Long> caseNumbers = new HashSet<Long>();
					activeCases = new HashSet<Long>();
					EdEligibilityCargo edEligibilityCargo[] = null;
					edEligibilityCargo = getTriggersForNDHCC013(asOfDate);
					if (null != edEligibilityCargo && edEligibilityCargo.length > 0) {
						CoDebugger.debugInformation("Size of ED_ELIGIBILITY records selected for "
										+ CoConstants.DOC_ID_NDHCC013 + " --> "	+ edEligibilityCargo.length);
						for (EdEligibilityCargo cargo : edEligibilityCargo) {
							if (cargo.getCaseNum() > 0 && !caseNumbers.contains(cargo.getCaseNum())) {
								triggerCount++;
								activeCases.add(cargo.getCaseNum());
								caseNumbers.add(cargo.getCaseNum());
								coCorrespondence = new COCorrespondence();
								coCorrespondence.setDocId(triggerType);
								coCorrespondence.setCaseAppNumber(String.valueOf(cargo.getCaseNum()));
								coCorrespondence.setCaseAppFlag('C');
								coCorrespondence.setDocType(CoConstants.CO_DOC_TYPE_CD_N);
								coCorrespondence.setAssistanceProgramCode(CoConstants.MA);
								coCorrespondenceList.add(coCorrespondence);
							} else {
								if (!caseNumberCountMap.containsKey(cargo.getCaseNum())) {
									caseNumberCountMap.put(cargo.getCaseNum(), 1);
								}
								int count = caseNumberCountMap.get(cargo.getCaseNum());
								caseNumberCountMap.put(cargo.getCaseNum(), count + 1);
							}
						}
						totalExpected = triggerCount;
					}
		}else if(triggerType != null && triggerType.equalsIgnoreCase(CoConstants.NDCDN070)){
					Timestamp asOfDateTimestamp = null;
					java.text.SimpleDateFormat dateFormater = new java.text.SimpleDateFormat(CoBatchConstants.asOfDtMMDDYYYY);
					if(null!=asOfDate && !CoConstants.EMPTY_STRING.equals(asOfDate)){
						Date date = null;
						try {
							date = (Date)dateFormater.parse(asOfDate);
							asOfDateTimestamp = new Timestamp(date.getTime());
						} catch (ParseException e) {
							CoDebugger.debugException(e.getMessage(), e); 
							CoDebugger.debugInformation("Parse Exception while parsing as of date" + CoConstants.NDCDN070 + e);
							asOfDateTimestamp = CoDateFactory.getTimestamp();
						}		
					}else{
						asOfDateTimestamp = CoDateFactory.getTimestamp();
					}
					docName = CoConstants.NDCDN070_NOTICE_TITLE;
					totalReadCount = new StringBuffer();
					totalFailed = new StringBuffer();
					totalSuccess = new StringBuffer();
					BiIssuanceTriggerCargo[] biIssuanceTriggerCargo = null;
					coCorrespondenceList = new ArrayList<COCorrespondence>();					
					if (triggerType.equalsIgnoreCase(CoConstants.NDCDN070)) {					
						biIssuanceTriggerCargo = getTriggersForNDCDN070(biIssuanceTriggerCargo,asOfDateTimestamp);
					}
					if(biIssuanceTriggerCargo != null){
						CoDebugger.debugInformation("Size of BIIssuanceTriggereRecords selected for " + CoConstants.NDCDN070 + " --> "+biIssuanceTriggerCargo.length);
						Set<Long> providerIds = new HashSet<Long>();
						
						boolean manualSwitch= true; 
						
						for(BiIssuanceTriggerCargo biTriggerCargo : biIssuanceTriggerCargo){
							
							Long providerId = biTriggerCargo.getProviderId();
							if(providerId> 0 && !providerIds.contains(providerId)){
								
								providerIds.add(biTriggerCargo.getProviderId());
								coCorrespondence = new COCorrespondence();
								coCorrespondence.setDocId(triggerType);
								coCorrespondence.setCaseAppNumber("");
								coCorrespondence.setProviderId(Long.parseLong(providerId.toString()));
								coCorrespondence.setCaseAppFlag('S');
								coCorrespondence.setDocType(CoConstants.CO_DOC_TYPE_CD_N);
								coCorrespondence.setIsManualyGenerated(manualSwitch);
								coCorrespondence.setAssistanceProgramCode(CoConstants.PROGRAM_CD);
								coCorrespondence.setMiscParameters("P");
								coCorrespondenceList.add(coCorrespondence);
							}
						}
						totalExpected = totalExpected + providerIds.size();	
					}
		      }else if(triggerType != null && triggerType.equalsIgnoreCase(CoConstants.DOC_ID_NDTFREN17)){/**@author karraj for 'Non Receipt of Monthly Report' ND-60876 */
					docName = "Non Receipt of Monthly Report";
					totalReadCount = new StringBuffer();
					totalFailed = new StringBuffer();
					totalSuccess = new StringBuffer();
					coCorrespondenceList = new ArrayList<COCorrespondence>();
					SimpleDateFormat datetimeFormatter1 = new SimpleDateFormat(CoConstants.DATE_FORMAT);
					Date batchAsOfDate = datetimeFormatter1.parse(asOfDate);
					Timestamp asOfDateTimestamp = new Timestamp(batchAsOfDate.getTime());
					EdEligibilityCargo edEligibilityCargo[] = null;
					edEligibilityCargo = coDAOServices.findCaseNumbersForNonReciptOfMonthlyRpt(asOfDateTimestamp);
					if(edEligibilityCargo != null){
						CoDebugger.debugInformation("Total number of records selected from cargo ED_ELIGIBILITY " + CoConstants.DOC_ID_NDTFREN17 + " --> "+edEligibilityCargo.length);
						boolean manualSwitch= false; 
						activeCases = new HashSet<Long>();
						Map<Long,Integer> caseNumberCountMap = new HashMap<Long,Integer>();
						Set<Long> caseNumbers = new HashSet<Long>();
						for(EdEligibilityCargo cargo : edEligibilityCargo){
							long caseNum = cargo.getCaseNum();
							if(caseNum> 0 && !caseNumbers.contains(caseNum)){
								activeCases.add(caseNum);
								caseNumbers.add(cargo.getCaseNum());
								coCorrespondence = new COCorrespondence();
								coCorrespondence.setDocId(triggerType);
								coCorrespondence.setCaseAppNumber(String.valueOf(caseNum));
								coCorrespondence.setCaseAppFlag('C');
								coCorrespondence.setDocType(CoConstants.CO_DOC_TYPE_CD_N);
								coCorrespondence.setIsManualyGenerated(manualSwitch);
								coCorrespondence.setAssistanceProgramCode(CoConstants.PROGRAM_TF);
								coCorrespondenceList.add(coCorrespondence);
							}else{
								if(!caseNumberCountMap.containsKey(caseNum)){
									caseNumberCountMap.put(caseNum, 1);
								}
								int count = caseNumberCountMap.get(caseNum);
								caseNumberCountMap.put(caseNum, count+1);

							}
						}
						totalExpected = activeCases.size();	
					}
				}else if(triggerType != null && triggerType.equalsIgnoreCase(CoConstants.DOC_ID_NDLI8949)){
					docName = CoConstants.REQUEST_FOR_PAYMENT;
					totalReadCount = new StringBuffer();
					totalFailed = new StringBuffer();
					totalSuccess = new StringBuffer();
					coCorrespondenceList = new ArrayList<COCorrespondence>();
					SimpleDateFormat datetimeFormatter1 = new SimpleDateFormat(CoConstants.DATE_FORMAT);
					Date batchAsOfDate = datetimeFormatter1.parse(asOfDate);
					Timestamp asOfDateTimestamp = new Timestamp(batchAsOfDate.getTime());
					String createDate=new SimpleDateFormat(CoConstants.ddMMMyy).format(asOfDateTimestamp);
					
					String jobId=CoConstants.LIHEAP_RFP_JOB_ID;
					String updatedUser= jobId.replaceAll(CoConstants.HYPHEN,CoConstants.EMPTY_STRING);
					String lastRunDate=CoConstants.EMPTY_STRING;
					int prevRunMonth=CoConstants.ZEROTH_ELEMENT;
					int currentMonth=ALSOPUtil.getMM(asOfDateTimestamp);
					
					CoDebugger.debugInformation("CoBatch.8949Triggering --> Fetching the last Run Date where current date is  : "+createDate);
					FwBatchRunControlCollection fwBatchRunControlCollection = new FwBatchRunControlCollection();
					Object[] objParams = new Object[3];
					objParams[0]= jobId;
					objParams[1]= updatedUser;
					objParams[2]= createDate;
					FwBatchRunControlCargo[] fwBatchRunControlCargo = null;
					
					fwBatchRunControlCargo = (FwBatchRunControlCargo[]) fwBatchRunControlCollection.select("findLastSuccessfulRunBatch", objParams);
					
					if (fwBatchRunControlCargo != null && fwBatchRunControlCargo.length > 0) {
						for(FwBatchRunControlCargo fwBathRnControlCargo: fwBatchRunControlCargo){
							if(fwBathRnControlCargo.getAsOfDt().equals(asOfDateTimestamp)){
								continue;
							}else{
								Timestamp ts =ALSOPUtil.addDays(fwBathRnControlCargo.getAsOfDt(),1);
								lastRunDate= new SimpleDateFormat(CoConstants.ddMMMyy).format(ts);
								prevRunMonth=ALSOPUtil.getMM(ts);
								break;
							}
						}
					}else{
						Timestamp ts = ALSOPUtil.getFirstDayOfMonth(asOfDateTimestamp);
						lastRunDate= new SimpleDateFormat(CoConstants.ddMMMyy).format(ts);
						prevRunMonth=ALSOPUtil.getMM(ts);
					}
					
					if(lastRunDate.equals(CoConstants.EMPTY_STRING) || (prevRunMonth != 10 && currentMonth ==10)){
						Timestamp ts = ALSOPUtil.getFirstDayOfMonth(asOfDateTimestamp);
						lastRunDate= new SimpleDateFormat(CoConstants.ddMMMyy).format(ts);
					}
					
					/* Trigger for the payments made by vendor */
					PmLiheapPaymentCargo pmLiheapPaymentCargos[] = null;
					PmVendorCaseAssocCargo[] pmVendorCaseAssocCargos = null;
					PmLiheapVendorCargo[] pmLiheapVendorCargos = null;
					Map<Long,Integer> vendorNumberCountMap = new HashMap<Long,Integer>();
					Set<Long> vendorNumbers = new HashSet<Long>();
					boolean manualSwitch= true; 
					activeCases = new HashSet<Long>();
					
					try{
						pmLiheapPaymentCargos = coDAOServices.findReqForPaymentLIHEAP(lastRunDate, createDate);
					}catch(Exception e)	{
						CoDebugger.debugException(CoConstants.DOC_ID_NDLI8949+e.getMessage(), e);
					}
					
					if(null != pmLiheapPaymentCargos){
						CoDebugger.debugInformation("Size of PM_LIHEAP_PAYMENT records selected for " + CoConstants.DOC_ID_NDLI8949 + " --> "+pmLiheapPaymentCargos.length);
							
						for(PmLiheapPaymentCargo cargo : pmLiheapPaymentCargos){

							long vendorId = cargo.getVendorId();
							if(vendorId> 0 && !vendorNumbers.contains(vendorId)){
								activeCases.add(vendorId);
								vendorNumbers.add(cargo.getVendorId());
								coCorrespondence = new COCorrespondence();
								coCorrespondence.setDocId(triggerType);
								coCorrespondence.setDocType(CoConstants.CO_DOC_TYPE_CD_N);
								coCorrespondence.setIsManualyGenerated(manualSwitch);
								coCorrespondence.setAssistanceProgramCode(CoConstants.LIHEAP);
								coCorrespondence.setProviderId(vendorId);
								coCorrespondence.setMiscParameters(CoConstants.V);
								coCorrespondence.setCaseAppFlag('V');
								coCorrespondenceList.add(coCorrespondence);
							}else{
								if(!vendorNumberCountMap.containsKey(vendorId)){
									vendorNumberCountMap.put(vendorId, 1);
								}
								int count = vendorNumberCountMap.get(vendorId);
								vendorNumberCountMap.put(vendorId, count+1);

							}
						}
						
						}
						
						/* 2.Trigger for Newly Associated vendors */
						try{
							pmVendorCaseAssocCargos=coDAOServices.findAssociatedLiheapVendors(lastRunDate, createDate);
						}catch(Exception e)	{
							CoDebugger.debugException(CoConstants.DOC_ID_NDLI8949+e.getMessage(), e);
						}
						
						if(null!=pmVendorCaseAssocCargos && pmVendorCaseAssocCargos.length > 0){
							CoDebugger.debugInformation("Size of PM_LIHEAP_VENDOR_ASSOC records selected for " + CoConstants.DOC_ID_NDLI8949 + " --> "+pmVendorCaseAssocCargos.length);
								
							for(PmVendorCaseAssocCargo pmVendorCaseAssocCargo : pmVendorCaseAssocCargos){
								long vendorId = pmVendorCaseAssocCargo.getSpacesVendorId();
								if(vendorId> 0 && !vendorNumbers.contains(vendorId)){
									activeCases.add(vendorId);
									vendorNumbers.add(vendorId);
									coCorrespondence = new COCorrespondence();
									coCorrespondence.setDocId(triggerType);
									coCorrespondence.setDocType(CoConstants.CO_DOC_TYPE_CD_N);
									coCorrespondence.setIsManualyGenerated(manualSwitch);
									coCorrespondence.setAssistanceProgramCode(CoConstants.LIHEAP);
									coCorrespondence.setProviderId(vendorId);
									coCorrespondence.setMiscParameters(CoConstants.V);
									coCorrespondenceList.add(coCorrespondence);
								}else{
									if(!vendorNumberCountMap.containsKey(vendorId)){
										vendorNumberCountMap.put(vendorId, 1);
									}
									int count = vendorNumberCountMap.get(vendorId);
									vendorNumberCountMap.put(vendorId, count+1);

								}
							}
						}
						
						/* 3. Trigger for new vendors enrolled */
						try{
							pmLiheapVendorCargos=coDAOServices.findNewlyEnrolledVendor(lastRunDate, createDate);
						}catch(Exception e)	{
							CoDebugger.debugException(CoConstants.DOC_ID_NDLI8949+e.getMessage(), e);
						}
						
						if(null!=pmLiheapVendorCargos && pmLiheapVendorCargos.length > 0){
							CoDebugger.debugInformation("Size of PM_LIHEAP_VENDOR records selected for " + CoConstants.DOC_ID_NDLI8949 + " --> "+pmLiheapVendorCargos.length);
								
							for(PmLiheapVendorCargo pmLiheapVendorCargo : pmLiheapVendorCargos){
								long vendorId = pmLiheapVendorCargo.getSpacesVendorId();
								if(vendorId> 0 && !vendorNumbers.contains(vendorId)){
									activeCases.add(vendorId);
									vendorNumbers.add(vendorId);
									coCorrespondence = new COCorrespondence();
									coCorrespondence.setDocId(triggerType);
									coCorrespondence.setDocType(CoConstants.CO_DOC_TYPE_CD_N);
									coCorrespondence.setIsManualyGenerated(manualSwitch);
									coCorrespondence.setAssistanceProgramCode(CoConstants.LIHEAP);
									coCorrespondence.setProviderId(vendorId);
									coCorrespondence.setMiscParameters(CoConstants.V);
									coCorrespondenceList.add(coCorrespondence);
								}else{
									if(!vendorNumberCountMap.containsKey(vendorId)){
										vendorNumberCountMap.put(vendorId, 1);
									}
									int count = vendorNumberCountMap.get(vendorId);
									vendorNumberCountMap.put(vendorId, count+1);

								}
							}
						}
					if(!activeCases.isEmpty()){
						totalExpected = activeCases.size();	
					}
					
				}else if(triggerType != null && triggerType.equalsIgnoreCase(CoConstants.TRIGGER_TYPE_NDHCREN17_FDOM)){/**@author karraj for 'Advance notice of Closure for non MAS' ND-60876 */
					docName = "Advance notice of closure";
					triggerType = CoConstants.NDHCREN17;
					totalReadCount = new StringBuffer();
					totalFailed = new StringBuffer();
					totalSuccess = new StringBuffer();
					coCorrespondenceList = new ArrayList<COCorrespondence>();
					SimpleDateFormat datetimeFormatter1 = new SimpleDateFormat("MM/dd/yyyy");
					Date batchAsOfDate = datetimeFormatter1.parse(asOfDate);
					Timestamp asOfDateTimestamp = new Timestamp(batchAsOfDate.getTime());
					
					EdEligibilityCargo edEligibilityCargoTANF[] = null;
					EdEligibilityCargo edEligibilityCargoDiversion[] = null;
					EdIndvEligibilityCargo edIndvEligibilityCargoSNAPABAWD[] = null;

					Map<Long,List<String>> caseProgramMap = new HashMap<Long,List<String>>();
                    Map<Long, Set<Long>> caseIndvMapAbawd = new HashMap<Long, Set<Long>>();
                    Map<Long, Set<Long>> caseIndvMapAbawdExtd = new HashMap<Long, Set<Long>>();

					/**Creating set to add misc param for diversion and extended abawd cases*/
					Set<Long> divCases = new TreeSet<Long>();
					Set<Long> extendedABAWDCases = new TreeSet<Long>();
					
					/**Fetching required cases for triggers*/
                    edIndvEligibilityCargoSNAPABAWD = coDAOServices.findSNAPABAWDCaseDueForClosure(0,asOfDateTimestamp);
					edEligibilityCargoTANF = coDAOServices.findTANFCaseDueForClosure(0,asOfDateTimestamp);
					edEligibilityCargoDiversion = coDAOServices.findDiversionCaseDueForClosure(0,asOfDateTimestamp);

					CoDebugger.debugInformation("Total number of records selected from cargo ED_ELIGIBILITY for ABAWD SNAP for DOC_ID " + CoConstants.NDHCREN17 + " --> "+
							((null!=edIndvEligibilityCargoSNAPABAWD) ? edIndvEligibilityCargoSNAPABAWD.length : 0));
					CoDebugger.debugInformation("Total number of records selected from cargo ED_ELIGIBILITY for TANF for DOC_ID " + CoConstants.NDHCREN17 + " --> "+
							((null!=edEligibilityCargoTANF) ? edEligibilityCargoTANF.length : 0));
					CoDebugger.debugInformation("Total number of records selected from cargo ED_ELIGIBILITY for DIVERSION for DOC_ID " + CoConstants.NDHCREN17 + " --> "+
							((null!=edEligibilityCargoDiversion) ? edEligibilityCargoDiversion.length : 0));
					
					
				if (null != edIndvEligibilityCargoSNAPABAWD
						&& edIndvEligibilityCargoSNAPABAWD.length > 0) {

					for (EdIndvEligibilityCargo cargo : edIndvEligibilityCargoSNAPABAWD) {

						if (!caseIndvMapAbawd.containsKey(cargo.getCaseNum())) {
							Set<Long> abawdIndv = new HashSet<>();
							abawdIndv.add(cargo.getIndvId());
							caseIndvMapAbawd.put(cargo.getCaseNum(), abawdIndv);
						} else {
							Set<Long> abawdIndv = caseIndvMapAbawd.get(cargo
									.getCaseNum());
							if (!abawdIndv.contains(cargo.getIndvId())) {
								abawdIndv.add(cargo.getIndvId());
							}
							caseIndvMapAbawd.put(cargo.getCaseNum(), abawdIndv);
						}

					}
				}
				
				List<String> indvlist = new ArrayList<String>();
				if (caseIndvMapAbawd.size() > 0) {
					for (Entry<Long, Set<Long>> mapEntry : caseIndvMapAbawd
							.entrySet()) {
						for (Long value : mapEntry.getValue()) {
							indvlist.add(String.valueOf(value));
						}
					}
				}

				String indvID = org.apache.commons.lang.StringUtils.join(
						indvlist, ',');

				DcIndvAbawdCargo[] dcIndvAbawdCargo = coDAOServices
						.findSNAPABAWDIndvDueForClosure(indvID);

				for (Map.Entry<Long, Set<Long>> mp : caseIndvMapAbawd
						.entrySet()) {
					long caseNumber = mp.getKey();
					String indvIds=org.apache.commons.lang.StringUtils.join(
							mp.getValue(), ',');
					EdIndvEligibilityCargo[] edIndvEligibilityCargo= coDAOServices.findSNAPABAWDCaseDueNotForClosure(caseNumber, indvIds, asOfDateTimestamp);
					
					if(null != edIndvEligibilityCargo && edIndvEligibilityCargo.length > 0){
						continue;
					}else{
						Set<Long> matchedIndvSet = new HashSet<Long>();
						for (Long value : mp.getValue()) {
							if (dcIndvAbawdCargo.length > 0
									&& null != dcIndvAbawdCargo) {

								for (DcIndvAbawdCargo abawdCargo : dcIndvAbawdCargo) {
									if (value.equals(abawdCargo.getIndvId())) {
										matchedIndvSet.add(value);
									}
								}
							}
						}
						if ((null != matchedIndvSet && matchedIndvSet.size() >0)
								&& matchedIndvSet.size() == mp.getValue().size()) {
							if (!caseProgramMap.containsKey(mp.getKey())) {
								List<String> list = new ArrayList<String>();
								list.add(CoConstants.PROGRAM_FS);
								caseProgramMap.put(mp.getKey(), list);
							} else {
								List<String> list = caseProgramMap.get(mp.getKey());
								if (!list.contains(CoConstants.PROGRAM_FS)) {
									list.add(CoConstants.PROGRAM_FS);
								}
								caseProgramMap.put(mp.getKey(), list);
							}
						}
					
					}
				}
					
					if(null!=edEligibilityCargoTANF && edEligibilityCargoTANF.length>0){
						for(EdEligibilityCargo cargo : edEligibilityCargoTANF){
							if(!caseProgramMap.containsKey(cargo.getCaseNum())){
								List<String> list = new ArrayList<String>();
								list.add(CoConstants.PROGRAM_TF);
								caseProgramMap.put(cargo.getCaseNum(), list);
							}else{
								List<String> list = caseProgramMap.get(cargo.getCaseNum());
								if(!list.contains(CoConstants.PROGRAM_TF)){
									list.add(CoConstants.PROGRAM_TF);
								}
								caseProgramMap.put(cargo.getCaseNum(), list);
							}
						}
						
					}
					
					if(null!=edEligibilityCargoDiversion && edEligibilityCargoDiversion.length>0){
						for(EdEligibilityCargo cargo : edEligibilityCargoDiversion){
							if(!caseProgramMap.containsKey(cargo.getCaseNum())){
								List<String> list = new ArrayList<String>();
								list.add(CoConstants.PROGRAM_TF);
								caseProgramMap.put(cargo.getCaseNum(), list);
							}else{
								List<String> list = caseProgramMap.get(cargo.getCaseNum());
								if(!list.contains(CoConstants.PROGRAM_TF)){
									list.add(CoConstants.PROGRAM_TF);
								}
								caseProgramMap.put(cargo.getCaseNum(), list);
							}
							divCases.add(cargo.getCaseNum());
						}
						
					}
					
				/**Code added for cr 476 - starts*/
				/**Fetch all the cases in which the WWD is turning 65 next month*/
				DcCaseIndividualCargo[] dcCaseIndvcasesTurning19 = coDAOServices
						.getDcCaseIndvCasesAbtToCrossAgeLimit(0L, asOfDateTimestamp, CoConstants.CWD_ASSISTANCE_CODE);
				/**Fetch all the cases in which the CWD is turning 19 this month*/
				DcCaseIndividualCargo[] dcCaseIndvcasesTurning65 = coDAOServices
						.getDcCaseIndvCasesAbtToCrossAgeLimit(0L, asOfDateTimestamp, CoConstants.WWD_ASSISTANCE_CODE);
				/**Combine both of the above records*/
				DcCaseIndividualCargo[] dcCaseIndvcasesTurning19or65 = (DcCaseIndividualCargo[]) ArrayUtils
				.addAll(dcCaseIndvcasesTurning19, dcCaseIndvcasesTurning65);

				CoDebugger
						.debugInformation("Total number of records selected from DC_CASE_INDIVIDUAL for "
								+ "CWD and WWD cases turning 19 or 65 for "
								+ CoConstants.NDHCREN17
								+ " --> "
								+ ((null != dcCaseIndvcasesTurning19or65) ? dcCaseIndvcasesTurning19or65.length
										: 0));

				if (null != dcCaseIndvcasesTurning19or65
						&& dcCaseIndvcasesTurning19or65.length > 0) {
					for (DcCaseIndividualCargo cargo : dcCaseIndvcasesTurning19or65) {
						if (!caseProgramMap.containsKey(cargo.getCaseNum())) {
							/**
							 * If this case is already present in the case
							 * trigger list, Add the program name
							 */
							List<String> list = new ArrayList<String>();
							list.add(CoConstants.PROGRAM_MA);
							caseProgramMap.put(cargo.getCaseNum(), list);
						} else {
							/**
							 * Else, insert this case to the case trigger list, and
							 * Add the program name
							 */
							List<String> list = caseProgramMap.get(cargo
									.getCaseNum());
							if (!list.contains(CoConstants.PROGRAM_MA)) {
								list.add(CoConstants.PROGRAM_MA);
							}
							caseProgramMap.put(cargo.getCaseNum(), list);
						}
					}
				}
				/**Code added for cr 476 - ends*/
				
					if(caseProgramMap.size()>0){
						CoDebugger.debugInformation("Total number of Case Num for which triggers needs to be inserted : " + CoConstants.NDHCREN17 + " --> "+caseProgramMap.size());
						boolean manualSwitch= false;
						int triggerCount = 0;
						for (Map.Entry<Long, List<String>> entry : caseProgramMap.entrySet()){
						    CoDebugger.debugInformation("Generating trigger for Case Number  --> "+entry.getKey() +"and DOC ID : "+CoConstants.NDHCREN17);
						    triggerCount = triggerCount+1;
						    String miscParam = CoConstants.EMPTY_STRING;
						    List<String> programs = entry.getValue();
						    String programString = org.apache.commons.lang.StringUtils.join(programs, ',');
					    	coCorrespondence = new COCorrespondence();
							coCorrespondence.setDocId(CoConstants.NDHCREN17);
							coCorrespondence.setCaseAppNumber(String.valueOf(entry.getKey()));
							coCorrespondence.setCaseAppFlag('C');
							coCorrespondence.setDocType(CoConstants.CO_DOC_TYPE_CD_N);
							coCorrespondence.setIsManualyGenerated(manualSwitch);
							coCorrespondence.setAssistanceProgramCode(programString);
							if(divCases.contains(entry.getKey())){
								miscParam = CoConstants.CASE_DIVERSION;
							}
							if(extendedABAWDCases.contains(entry.getKey())){
								if(null!=miscParam && CoConstants.EMPTY_STRING.equals(miscParam)){
									miscParam = CoConstants.Extended_ABAWD;
								}else{
									miscParam = " , "+CoConstants.Extended_ABAWD;
								}
							}
							coCorrespondence.setMiscParameters(miscParam);
							coCorrespondenceList.add(coCorrespondence);
						}
						totalExpected = triggerCount;
					}else{
						CoDebugger.debugInformation("Total number Of Case Num for which triggers needs to be inserted : " + CoConstants.NDHCREN17 + " --> "+0);
					}
			} else if (triggerType != null
					&& triggerType
							.equalsIgnoreCase(CoConstants.NDCCTFLI51_DOC_ID)) {
				 /** ND-96939 **/
				docName = CoConstants.REPAYMENT_AGRREMENT_DOC_NAME;
				totalReadCount = new StringBuffer();
				totalFailed = new StringBuffer();
				totalSuccess = new StringBuffer();
				coCorrespondenceList = new ArrayList<COCorrespondence>();
				Map<Long, List<String>> caseClaimMap = new LinkedHashMap<Long, List<String>>();
				Map<Long, HashSet<String>> caseProgramMap = new LinkedHashMap<Long, HashSet<String>>();
				BvClaimCargo[] bvClaimCargo = null;
				SimpleDateFormat datetimeFormatter1 = new SimpleDateFormat(
						CoConstants.DATE_FORMAT);
				Date batchAsOfDate = datetimeFormatter1.parse(asOfDate);
				Timestamp asOfDateTimestamp = new Timestamp(
						batchAsOfDate.getTime());
				CoDebugger
				.debugInformation("Timestamp Date For DOC_ID "
						+ CoConstants.NDCCTFLI51_DOC_ID
						+ " --> "
						+ asOfDateTimestamp);
				bvClaimCargo = coDAOServices
						.findAllClaimIdsForNDCCTFLI51(asOfDateTimestamp);
				if (null != bvClaimCargo && bvClaimCargo.length > 0) {
					CoDebugger
					.debugInformation("Size of BvClaimsRecords selected for "
							+ CoConstants.NDCCTFLI51_DOC_ID
							+ " --> "
							+ bvClaimCargo.length);
					for (BvClaimCargo bvclaim : bvClaimCargo) {
						BvClaimCargo[] bvclaimAcargo = null;
						bvclaimAcargo = coDAOServices
								.findForBvclaimAForNDCCTFLI51(
										bvclaim.getClaimId(), asOfDateTimestamp);
						CoDebugger
						.debugInformation("Size of BvClaims_A_Records selected for "
								+ CoConstants.NDCCTFLI51_DOC_ID
								+ " --> "
								+ bvclaimAcargo.length);
						if(bvclaimAcargo != null && bvclaimAcargo.length >0)
						{
							for(BvClaimCargo bvclaimA:bvclaimAcargo)
							{
								if (!(bvclaimA.getErrorTypeCd()
										.equalsIgnoreCase("IP") || bvclaimA
										.getErrorTypeCd()
										.equalsIgnoreCase("FR"))) {
									BvClaimRecoveryCargo[] bvClaimRecoveryCargo = null;
									bvClaimRecoveryCargo = coDAOServices.findByClaimIdForNDCCTFLI51(bvclaim.getClaimId());
									long caseNum = 0L;
									if(bvClaimRecoveryCargo != null && bvClaimRecoveryCargo.length >0 && bvClaimRecoveryCargo[0].getCaseNum() >0 )
									{
										CoDebugger
										.debugInformation("Size of BvClaims_Recovery_Records selected for "
												+ CoConstants.NDCCTFLI51_DOC_ID
												+ " --> "
												+ bvClaimRecoveryCargo.length);
									caseNum = bvClaimRecoveryCargo[0]
											.getCaseNum();
									CoDebugger
									.debugInformation("Case Number from BV_CLAIM_RECOVERY For Doc_ID "
											+ CoConstants.NDCCTFLI51_DOC_ID
											+ " --> "
											+ caseNum);
								}
									/** Populating case claim map */
									if (caseClaimMap.containsKey(caseNum)) {
										caseClaimMap.get(caseNum).add(
												bvclaim.getClaimId());
									} else {
										List<String> claimString = new ArrayList<String>();
										claimString.add(bvclaim.getClaimId());
										caseClaimMap.put(caseNum, claimString);
									}

									/** Populating case program map */
									String programCd = bvclaim.getProgramCd();
									if (caseProgramMap.containsKey(caseNum)) {
										caseProgramMap.get(caseNum).add(programCd);
									} else {
										HashSet<String> progString = new HashSet<String>();
										progString.add(programCd);
										caseProgramMap.put(caseNum, progString);
									}
									break;
								}
							}
						}
					}
				} else {
					CoDebugger
							.debugInformation("Size of BvClaimsRecords selected for "
									+ CoConstants.NDCCTFLI51_DOC_ID
									+ " --> "
									+ 0);
				}

				if (caseClaimMap.size() > 0) {
					CoDebugger
							.debugInformation("Total number of Case Num for which triggers needs to be inserted : "
									+ CoConstants.NDCCTFLI51_DOC_ID
									+ " --> "
									+ caseClaimMap.size());
					boolean manualSwitch = false;
					int triggerCount = 0;
					for (Map.Entry<Long, List<String>> entry : caseClaimMap
							.entrySet()) {
						CoDebugger
								.debugInformation("Generating trigger for Case Number  --> "
										+ entry.getKey()
										+ "and DOC ID : "
										+ CoConstants.NDCCTFLI51_DOC_ID);
						triggerCount = triggerCount + 1;
						List<String> claimList = entry.getValue();
						HashSet<String> programList = caseProgramMap.get(entry
								.getKey());
						String claimListString = null != claimList ? (org.apache.commons.lang.StringUtils
								.join(claimList, ','))
								: CoConstants.EMPTY_STRING;
						CoDebugger
						.debugInformation("Claim List String For Case :" + claimListString);
						String programString = null != programList ? (org.apache.commons.lang.StringUtils
								.join(programList, ','))
								: CoConstants.EMPTY_STRING;
						CoDebugger
								.debugInformation("Program String For Case Num:"
										+ entry.getKey() + programString);
						coCorrespondence = new COCorrespondence();
						coCorrespondence.setDocId(triggerType);
						coCorrespondence.setCaseAppNumber(String.valueOf(entry
								.getKey()));
						coCorrespondence.setCaseAppFlag('C');
						coCorrespondence
								.setDocType(CoConstants.CO_DOC_TYPE_CD_N);
						coCorrespondence.setIsManualyGenerated(manualSwitch);
						coCorrespondence.setMiscParameters(claimListString);
						coCorrespondence
								.setAssistanceProgramCode(programString);
						coCorrespondenceList.add(coCorrespondence);

					}
					totalExpected = triggerCount;
				} else {
					CoDebugger
							.debugInformation("Total number Of Case Num for which triggers needs to be inserted : "
									+ CoConstants.NDCCTFLI51_DOC_ID
									+ " --> "
									+ 0);
				}

			} else if(triggerType != null && triggerType.equalsIgnoreCase(CoConstants.NDLIN084)){
			    	  	docName = CoConstants.NDLIN084_NOTICE_TITLE;
						totalReadCount 	= new StringBuffer();
						totalFailed 	= new StringBuffer();
						totalSuccess 	= new StringBuffer();
						
						Set<Long> wtEdgTraceIdSet = new HashSet<Long>();
						Set<Long> liEdgTraceIdSet = new HashSet<Long>();
						
						coCorrespondenceList=this.createCorrespondenceTriggerForNDLIN084(coCorrespondenceList,coCorrespondence, triggerType,wtEdgTraceIdSet,liEdgTraceIdSet);
						if(wtEdgTraceIdSet.size()>0){
							totalExpected = totalExpected + wtEdgTraceIdSet.size();
						}
						
						if(liEdgTraceIdSet.size()>0){
							totalExpected = totalExpected + liEdgTraceIdSet.size();
						}
						
			      }else if(triggerType != null && triggerType.equalsIgnoreCase(CoConstants.NDLIN085)){
			    	    /**@author shreyasingh **/
						CoDebugger.debugInformation("Liheap Client Payment " + CoConstants.NDLIN085);

						docName=CoConstants.NDLIN085_NOTICE_TITLE;
						totalReadCount = new StringBuffer();
						totalFailed = new StringBuffer();
						totalSuccess = new StringBuffer();
						BiIssuanceTriggerCargo[] biIssuanceTriggerCargo = null;
						PmLiheapPaymentCargo[] pmLiheapPaymemtCargo = null;
						coCorrespondenceList = new ArrayList<COCorrespondence>();
						biIssuanceTriggerCargo = getbiIssuanceTriggersForNDLIN085(biIssuanceTriggerCargo, asOfDate);
						pmLiheapPaymemtCargo = getpmLiheapPaymemtTriggersForNDLIN085(pmLiheapPaymemtCargo, asOfDate);
						if(null != biIssuanceTriggerCargo || null != pmLiheapPaymemtCargo ){
							Map<Long,Integer> caseNumberCountMap = new HashMap<Long,Integer>();
							Set<Long> caseNumbers = new HashSet<Long>();	
							boolean manualSwitch= true; 
							activeCases = new HashSet<Long>();
							if(null != biIssuanceTriggerCargo ){
							CoDebugger.debugInformation("Liheap Client Payment Records selected for biIssuanceTrigger " + CoConstants.NDLIN085 + " --> "+biIssuanceTriggerCargo.length);
								for(BiIssuanceTriggerCargo biTriggerCargo : biIssuanceTriggerCargo){
									long caseNum = biTriggerCargo.getCaseNum();
									if(caseNum> 0 && !caseNumbers.contains(caseNum)){
										activeCases.add(caseNum);
										caseNumbers.add(biTriggerCargo.getCaseNum());
										coCorrespondence = new COCorrespondence();
										coCorrespondence.setDocId(triggerType);
										coCorrespondence.setCaseAppNumber(String.valueOf(caseNum));
										coCorrespondence.setCaseAppFlag('C');
										coCorrespondence.setIndvId(biTriggerCargo.getIndvId());
										coCorrespondence.setDocType(CoConstants.CO_DOC_TYPE_CD_N);
										coCorrespondence.setIsManualyGenerated(manualSwitch);
										coCorrespondence.setAssistanceProgramCode(CoConstants.LIHEAP);
										coCorrespondenceList.add(coCorrespondence);
									}else{
										if(!caseNumberCountMap.containsKey(caseNum)){
											caseNumberCountMap.put(caseNum, 1);
										}
										int count = caseNumberCountMap.get(caseNum);
										caseNumberCountMap.put(caseNum, count+1);
									}
								}
							}
							if(null != pmLiheapPaymemtCargo ){
								CoDebugger.debugInformation("Liheap Client Payment Records selected for pmLiheapPaymemt " + CoConstants.NDLIN085 + " --> "+pmLiheapPaymemtCargo.length);
								for(PmLiheapPaymentCargo cargo : pmLiheapPaymemtCargo){
									long caseNum = cargo.getCaseNum();
									if(caseNum > 0 && !caseNumbers.contains(caseNum)){
										activeCases.add(caseNum);
										caseNumbers.add(cargo.getCaseNum());
										coCorrespondence = new COCorrespondence();
										coCorrespondence.setDocId(triggerType);
										coCorrespondence.setCaseAppNumber(String.valueOf(caseNum));
										coCorrespondence.setCaseAppFlag('C');
										coCorrespondence.setDocType(CoConstants.CO_DOC_TYPE_CD_N);
										coCorrespondence.setIsManualyGenerated(manualSwitch);
										coCorrespondence.setAssistanceProgramCode(CoConstants.LIHEAP);
										coCorrespondenceList.add(coCorrespondence);
									}else{
										if(!caseNumberCountMap.containsKey(caseNum)){
											caseNumberCountMap.put(caseNum, 1);
										}
										int count = caseNumberCountMap.get(caseNum);
										caseNumberCountMap.put(caseNum, count+1);
									}
								}
							}
							totalExpected = activeCases.size();
						}
					}else if(triggerType != null && triggerType.equalsIgnoreCase(CoConstants.NDHCMNN22)){
		                docName = "Intentional Program Violation Client";
		                totalReadCount = new StringBuffer();
						totalFailed = new StringBuffer();
						totalSuccess = new StringBuffer();
						coCorrespondenceList = new ArrayList<COCorrespondence>();
						SimpleDateFormat datetimeFormatter1 = new SimpleDateFormat(CoConstants.DATE_FORMAT);
						Date batchAsOfDate = datetimeFormatter1.parse(asOfDate);
						Timestamp asOfDateTimestamp = new Timestamp(batchAsOfDate.getTime());
						String createDate=new SimpleDateFormat(CoConstants.ddMMMyy).format(asOfDateTimestamp);
						EdDcIndvDisqPenaltiesCargo[] edDcIndvDisqPenaltiesCargos = coDAOServices.getNDHCMNN22triggers(createDate);
						if(edDcIndvDisqPenaltiesCargos != null){
							CoDebugger.debugInformation("Size of EdDcIndvDisqPenalties records selected for " + CoConstants.NDHCMNN22 + " --> "+edDcIndvDisqPenaltiesCargos.length);
							Map<Long,Integer> caseNumberCountMap = new HashMap<Long,Integer>();
							Set<Long> caseNumbers = new HashSet<Long>();	
							activeCases = new HashSet<Long>();
							for(EdDcIndvDisqPenaltiesCargo edDcIndvDisqPenaltiesCargo : edDcIndvDisqPenaltiesCargos){

								long caseNum = edDcIndvDisqPenaltiesCargo.getCaseNum();
								if(caseNum> 0 && !caseNumbers.contains(caseNum)){
									activeCases.add(caseNum);
									caseNumbers.add(edDcIndvDisqPenaltiesCargo.getCaseNum());
									coCorrespondence = new COCorrespondence();
									coCorrespondence.setDocId(triggerType);
									coCorrespondence.setCaseAppNumber(String.valueOf(caseNum));
									coCorrespondence.setCaseAppFlag('C');
									coCorrespondence.setIndvId(edDcIndvDisqPenaltiesCargo.getIndvId());
									coCorrespondence.setDocType(CoConstants.CO_DOC_TYPE_CD_N);
									if(edDcIndvDisqPenaltiesCargo.getProgramCd().equalsIgnoreCase(CoConstants.PROGRAM_FS)){
										coCorrespondence.setAssistanceProgramCode(CoConstants.PROGRAM_FS);
									}else if(edDcIndvDisqPenaltiesCargo.getProgramCd().equalsIgnoreCase(CoConstants.PROGRAM_CD)){
										coCorrespondence.setAssistanceProgramCode(CoConstants.PROGRAM_CD);
									}else if(edDcIndvDisqPenaltiesCargo.getProgramCd().equalsIgnoreCase(CoConstants.PROGRAM_TF)){
										coCorrespondence.setAssistanceProgramCode(CoConstants.PROGRAM_TF);
									}
									coCorrespondenceList.add(coCorrespondence);
								}else{
									if(!caseNumberCountMap.containsKey(caseNum)){
										caseNumberCountMap.put(caseNum, 1);
									}
									int count = caseNumberCountMap.get(caseNum);
									caseNumberCountMap.put(caseNum, count+1);

								}
							}
							totalExpected = activeCases.size();	
						}
}else if(triggerType != null && triggerType.equalsIgnoreCase(CoConstants.NDTNFN116)){
	docName = "TANF JOBS Sanctions";
    totalReadCount = new StringBuffer();
	totalFailed = new StringBuffer();
	totalSuccess = new StringBuffer();
	coCorrespondenceList = new ArrayList<COCorrespondence>();
	SimpleDateFormat datetimeFormatter1 = new SimpleDateFormat(CoConstants.DATE_FORMAT);
	Date batchAsOfDate = datetimeFormatter1.parse(asOfDate);
	Timestamp asOfDateTimestamp = new Timestamp(batchAsOfDate.getTime());
	String createDate=new SimpleDateFormat(CoConstants.ddMMMyy).format(asOfDateTimestamp);
	Timestamp currentTimeStamp = CoDateFactory.getTimestamp();
	CoDebugger.debugInformation("createCorrespondenceTriggers ---> JobId ---> "+ jobId+ " AS OF DATE Timestamp:  ------> " + currentTimeStamp.toString());
	java.util.Date currentDate = currentTimeStamp;
	Timestamp adequateDateTimeStamp = getAdequateNoticeDate(triggerType, currentDate);
	Timestamp firstDayOfPreviousMonth = BvUtils.getFirstDayOfPreviousMonth(currentDate);
	java.util.Date firstDayOfPreviousMonthDate = firstDayOfPreviousMonth;
	Timestamp adequateDateTimeStampOfPreviousMonth = getAdequateNoticeDate(triggerType, firstDayOfPreviousMonthDate);
	String adequateDateOfPreviousMonth = new SimpleDateFormat(CoConstants.ddMMMyy).format(adequateDateTimeStampOfPreviousMonth);
	java.util.Date adequateDate = adequateDateTimeStamp;
	Timestamp firstWorkingDayOfMonthTimeStamp = ALSOPUtil.getFirstWorkingDayOfMonth(currentTimeStamp);
	java.util.Date firstWorkingDayOfMonthDateFormat = firstWorkingDayOfMonthTimeStamp;
	String firstWorkingDayOfMonth = new SimpleDateFormat(CoConstants.ddMMMyy).format(firstWorkingDayOfMonthTimeStamp);
	DcIndvNonCooperationCargo[] dcIndvNonCooperationCargosForOpenCases = null;
	DcIndvNonCooperationCargo[] dcIndvNonCooperationCargosForClosedCases = null;
	DcWorcReferralCargo[] dcWorcReferralCargos = null;
	activeCases = new HashSet<Long>();
	if(currentDate.compareTo(firstWorkingDayOfMonthDateFormat)==0){
		dcIndvNonCooperationCargosForOpenCases = coDAOServices.getNDTNFN116triggersForOpenCasesOnFirstWorkingDayOfMonth(adequateDateOfPreviousMonth, firstWorkingDayOfMonth);
		dcIndvNonCooperationCargosForClosedCases = coDAOServices.getNDTNFN116triggersForClosedCasesOnFirstWorkingDayOfMonth(adequateDateOfPreviousMonth, firstWorkingDayOfMonth);
	}else if(currentDate.before(adequateDate)){
		dcIndvNonCooperationCargosForOpenCases = coDAOServices.getNDTNFN116triggersForOpenCases(createDate);
		dcIndvNonCooperationCargosForClosedCases = coDAOServices.getNDTNFN116triggersForClosedCases(createDate);
	}
	if(dcIndvNonCooperationCargosForOpenCases != null && dcIndvNonCooperationCargosForOpenCases.length>0){
		CoDebugger.debugInformation("Size of DcIndvNonCooperationCargo records selected for " + CoConstants.NDTNFN116 + " --> "+dcIndvNonCooperationCargosForOpenCases.length);
		Map<Long,Integer> caseNumberCountMap = new HashMap<Long,Integer>();
		Set<Long> caseNumbers = new HashSet<Long>();	
		for(DcIndvNonCooperationCargo dcIndvNonCooperationCargo : dcIndvNonCooperationCargosForOpenCases){
			long caseNum = dcIndvNonCooperationCargo.getCaseNum();
			if(caseNum> 0 && !caseNumbers.contains(caseNum)){
				activeCases.add(caseNum);
				caseNumbers.add(dcIndvNonCooperationCargo.getCaseNum());
				coCorrespondence = new COCorrespondence();
				coCorrespondence.setDocId(triggerType);
				coCorrespondence.setCaseAppNumber(String.valueOf(caseNum));
				coCorrespondence.setCaseAppFlag('C');
				coCorrespondence.setIndvId(dcIndvNonCooperationCargo.getIndvId());
				coCorrespondence.setDocType(CoConstants.CO_DOC_TYPE_CD_N);
				coCorrespondence.setAssistanceProgramCode(CoConstants.PROGRAM_TF);
				coCorrespondence.setGenerateDate(asOfDateTimestamp);
				if(!coDAOServices.checkActiveAutomaticTriggerExist(coCorrespondence)){
					coCorrespondenceList.add(coCorrespondence);
				}else{
					CoDebugger.debugMessage("CoDAOService.generateNDTNFN116Trigger-->Skipping this trigger. Active trigger for today already exist ");
				}
				
				
			}else{
				if(!caseNumberCountMap.containsKey(caseNum)){
					caseNumberCountMap.put(caseNum, 1);
				}
				int count = caseNumberCountMap.get(caseNum);
				caseNumberCountMap.put(caseNum, count+1);

			}
	}
}
	if(dcIndvNonCooperationCargosForClosedCases != null && dcIndvNonCooperationCargosForClosedCases.length>0){
		CoDebugger.debugInformation("Size of DcIndvNonCooperationCargo records selected for " + CoConstants.NDTNFN116 + " --> "+dcIndvNonCooperationCargosForClosedCases.length);
		Map<Long,Integer> caseNumberCountMap = new HashMap<Long,Integer>();
		Set<Long> caseNumbers = new HashSet<Long>();	
		for(DcIndvNonCooperationCargo dcIndvNonCooperationCargo : dcIndvNonCooperationCargosForClosedCases){
			long caseNum = dcIndvNonCooperationCargo.getCaseNum();
			dcWorcReferralCargos = (DcWorcReferralCargo[])coDAOServices.getDcWorcReferralForJOBSByIndvId(dcIndvNonCooperationCargo.getIndvId());
			if(null != dcWorcReferralCargos && dcWorcReferralCargos.length>0){
				String referralFor=dcWorcReferralCargos[0].getReferredToCd();
				if (CoConstants.RF_NEW.equalsIgnoreCase(referralFor) || CoConstants.RF_WORC.equalsIgnoreCase(referralFor)){
					if(caseNum> 0 && !caseNumbers.contains(caseNum)){
						activeCases.add(caseNum);
						caseNumbers.add(dcIndvNonCooperationCargo.getCaseNum());
						coCorrespondence = new COCorrespondence();
						coCorrespondence.setDocId(triggerType);
						coCorrespondence.setCaseAppNumber(String.valueOf(caseNum));
						coCorrespondence.setCaseAppFlag('C');
						coCorrespondence.setIndvId(dcIndvNonCooperationCargo.getIndvId());
						coCorrespondence.setDocType(CoConstants.CO_DOC_TYPE_CD_N);
						coCorrespondence.setAssistanceProgramCode(CoConstants.PROGRAM_TF);
						coCorrespondence.setGenerateDate(asOfDateTimestamp);
						if(!coDAOServices.checkActiveAutomaticTriggerExist(coCorrespondence)){
							coCorrespondenceList.add(coCorrespondence);
						}else{
							CoDebugger.debugMessage("CoDAOService.generateNDHCCN117Trigger-->Skipping this trigger. Active trigger for today already exist ");
						}
						
					}else{
						if(!caseNumberCountMap.containsKey(caseNum)){
							caseNumberCountMap.put(caseNum, 1);
						}
						int count = caseNumberCountMap.get(caseNum);
						caseNumberCountMap.put(caseNum, count+1);
					}
				}
			}
	}
}
	totalExpected = activeCases.size();
			} else if (triggerType != null
					&& triggerType
							.equalsIgnoreCase(CoConstants.DOC_ID_NDHCC107)) {
				/** @author smurthypv for 'Medicaid Premium notice */
				docName = "Medicaid Premium Statement";
				totalReadCount = new StringBuffer();
				totalFailed = new StringBuffer();
				totalSuccess = new StringBuffer();
				coCorrespondenceList = new ArrayList<COCorrespondence>();

				Set<Long> caseNumListWWDCWD = new HashSet<Long>();

				/** Get the case numbers eligible for Medicaid premium notice */
				caseNumListWWDCWD = getTriggersForNDHCC107(caseNumListWWDCWD);

				if (caseNumListWWDCWD != null && !caseNumListWWDCWD.isEmpty()) {
					CoDebugger
							.debugInformation("Number of triggers to be inserted for "
									+ CoConstants.DOC_ID_NDHCC107
									+ " --> "
									+ caseNumListWWDCWD.size());
					Map<Long, Integer> caseNumberCountMap = new HashMap<Long, Integer>();
					Set<Long> caseNumbers = new HashSet<Long>();
					for (long caseNum : caseNumListWWDCWD) {
						if (caseNum > 0 && !caseNumbers.contains(caseNum)) {
							caseNumbers.add(caseNum);

							/**
							 * Create a coCorrespondence object for each case
							 * number in the list
							 */
							coCorrespondence = new COCorrespondence();
							coCorrespondence
									.setDocId(CoConstants.DOC_ID_NDHCC107);
							coCorrespondence.setCaseAppNumber(String
									.valueOf(caseNum));
							coCorrespondence.setCaseAppFlag('C');
							coCorrespondence
									.setAssistanceProgramCode(CoConstants.PROGRAM_MA);
							coCorrespondence.setManualyGenerated(false);

							/**
							 * add each coCorrespondence object to the
							 * coCorrespondenceList to be inserted as trigger
							 */
							coCorrespondenceList.add(coCorrespondence);
						}
					}
					totalExpected = coCorrespondenceList.size();
				}

		/** CR 932 CCAP Certificate Provider & Household Copies */
		}else if (triggerType != null && triggerType.equalsIgnoreCase(CoConstants.CCAP_CERTIFICATES)) {/** @author tdatta */
			CoDebugger.debugInformation("createCorrespondenceTriggers ---> CCAP Certificate Provider & Household Copies ---> " + jobId + " docId ---> "+ triggerType);
			docName = "CCAP Certificate Provider & Household Copies";
			totalReadCount = new StringBuffer();
			totalFailed = new StringBuffer();
			totalSuccess = new StringBuffer();
			coCorrespondenceList = new ArrayList<COCorrespondence>();
			
			/**Fetch Batch Run Date*/
			String batchRunDt=getBatchRunDt(asOfDate);
				
			/**Map for storing caseNumber as key and respective provider associations*/
			Map<Long, Set<Integer>> map=new HashMap<Long, Set<Integer>>(); 
		
			/**Fetches Triggers for VM records**/
			getTriggersForNewAndUpdatedAssociations(map, batchRunDt);

			/**Fetches Triggers for ED records**/
			getTriggersForUpdatedCopayLOCOrHHSize(map, batchRunDt);
				
			if (!map.isEmpty()){
				for (Long caseNum: map.keySet()) {
					/**
					 * Create and add a coCorrespondence object for caseNum in the list for Household Copy Certificate 
					 */
					createAndAddCorrespondence(CoConstants.DOC_NDCCAP064,caseNum,CoConstants.ELEMENT_ZERO,CoConstants.CHAR_C, CoConstants.ELEMENT_ZERO,CoConstants.CO_DOC_TYPE_CD_N,CoConstants.PROGRAM_CD,false, coCorrespondenceList);

					/**
					 * Create and add a coCorrespondence object for each provider associated to the caseNum for Provider Copy Certificate 																																																																																																		 Provider Copy Certificate 
					 */
					Set<Integer> providerIds=map.get(caseNum);
					for (Integer providerId: providerIds){
						if (providerId > 0) {
							createAndAddCorrespondence(CoConstants.DOC_NDCCAP063,caseNum,providerId,CoConstants.CHAR_P,providerId,CoConstants.CO_DOC_TYPE_CD_N,CoConstants.PROGRAM_CD,false, coCorrespondenceList);						
						}		
					}	

				} 

			}

			totalExpected = coCorrespondenceList.size();
		}
			CoDebugger.debugInformation("createCorrespondenceTriggers ---> Total Expected trigger Count for jobId ---> " + jobId + " docId ---> "+ triggerType +" is ---> "+ totalExpected);
			if (coCorrespondenceList != null && totalExpected == coCorrespondenceList.size() && totalExpected > 0) {
				CoDebugger.debugInformation("createCorrespondenceTriggers ---> coCorrespondenceList size for docId ---> " + triggerType +" is ---> "+ coCorrespondenceList.size());
				try {
					tbc.savepoint(null, SAVE_POINT);
					boolean retVal = coDAOServices.processBulkCorrespondenceTriggersForTriggerBatch(jobId, triggerType, coCorrespondenceList, totalReadCount, totalFailed, totalSuccess, failedMap);
					if (retVal) {
						if(triggerType!= null && triggerType.equalsIgnoreCase(CoConstants.CO_DOC_ID_NCH049)){
							coMassMailingReqColl.update(coMassMailingReqColl);
						}
						coLogicalCommit();
					}else{
						tbc.rollbackCoSpring(null, SAVE_POINT);
					}

				} catch (Exception ex) {
					CoDebugger.debugInformation("createCorrespondenceTriggers ---> Exception while inserting COCorrespondence triggers for jobId ---> " + jobId + " docId ---> "+ triggerType);
					CoDebugger.debugException(ex.getMessage(), ex);
					tbc.rollbackCoSpring(null, SAVE_POINT);
					writeException("Could not insert triggers for for jobId ---> " + jobId + " docId ---> "+ triggerType, "createCorrespondenceTriggers ---> Exception while inserting COCorrespondence triggersfor jobId ---> " + jobId + " docId ---> "+ triggerType, ex, false);
				}

			} else if(triggerType!= null && triggerType.equalsIgnoreCase(CoConstants.CO_DOC_ID_NCH049) && totalExpected == 0) {
				CoDebugger.debugInformation("createCorrespondenceTriggers ---> coCorrespondenceList size for docId ---> " + triggerType +" is ---> "+ coCorrespondenceList.size());
				CoDebugger.debugInformation("No Correspondence Triggers to insert for Mass Mailing Notice");
				coMassMailingReqColl.update(coMassMailingReqColl);
				coLogicalCommit();
			}else{
				CoDebugger.debugInformation("createCorrespondenceTriggers ---> for jobId ---> " + jobId + " docId ---> "+ triggerType +" COUNT MISMATCH");
			}
		} catch (Exception e) {
			retValue = -1;
			CoDebugger.debugException("Exception while inserting correspondence triggers for jobId ---> " + jobId + " docId ---> "+ triggerType, e);
			writeException("Exception while inserting correspondence triggers for jobId ---> " + jobId + " docId ---> "+ triggerType, "Could not insert correspondence triggersfor jobId ---> " + jobId + " docId ---> "+ triggerType+ "...ABORTING JOB " + jobId, e,
					false);
		}finally{
			if (null!=totalReadCount && totalReadCount.length() == 0) {
				totalReadCount.append(0);
			}
			if (null!=totalSuccess && totalSuccess.length() == 0) {
				totalSuccess.append(0);
			}
			if (null!=totalFailed && totalFailed.length() == 0) {
				totalFailed.append(0);
			}
			generateSummaryReport(CoConstants.TRIGGER_BATCH_SUMMARY, Long.parseLong(totalReadCount.toString()), Long.parseLong(totalSuccess.toString()), Long.parseLong(totalFailed.toString()),blankNoticeCounter);
			/**Exception reporting*/
			if(null!=failedMap && failedMap.size()>0){
				Iterator<Entry<String,String>> it = failedMap.entrySet().iterator();
				while(it.hasNext()){
					String exceptionSummary = CoConstants.EMPTY_STRING;
					Map.Entry<String,String> exceptionEntry = (Map.Entry<String,String>)it.next();
					if(!"InsertFailure".equals(exceptionEntry.getKey())){
						exceptionSummary = "Trigger insertion failed for case/provider/vendor : "+exceptionEntry.getKey()+" and job id: "+jobId;
					}else{
						exceptionSummary = "Trigger insertion failed for job id: "+jobId;
					}
					String exceptionDetail = exceptionEntry.getValue();
					writeException(exceptionSummary, exceptionDetail, null, false);
				}
			}
		}

		return retValue;
	}

	/**
	 * getTriggersForNDHCC107.
	 * @author smurthypv
	 * @param caseListWWDCWD
	 * @return the Set containing all the case numbers eligible for notice
	 */
	private Set<Long> getTriggersForNDHCC107(Set<Long> caseListWWDCWD) {

		EdEligibilityCargo[] receivingCWDBenefits = null;
		EdEligibilityCargo[] receivingWWDBenefits = null;
		Map<Long, List<EdEligibilityCargo>> eligibilityMapWWDCWD = new HashMap<Long, List<EdEligibilityCargo>>();

		try {
			
			/** Fetch all the eligibility records of children with disability*/
			receivingCWDBenefits = (EdEligibilityCargo[]) coDAOServices
					.findActiveCWDCases(0, CoDateFactory.getTimestamp());
			
			/** Fetch all the eligibility records of workers with disability*/
			receivingWWDBenefits = (EdEligibilityCargo[]) coDAOServices
					.findActiveWWDCases(0, CoDateFactory.getTimestamp());
		} catch (Exception e) {
			CoDebugger.debugException(e.getMessage(), e);
			CoDebugger
					.debugInformation("Exception while fetching EdEligibilityRecords for Unpaid premium for WWD/CWD. Doc Id: "
							+ CoConstants.DOC_ID_NDHCC107 + e);
		}

		/** Combine both the records of CWD and WWD*/
		EdEligibilityCargo[] receivingCWDandWWDBenefits = (EdEligibilityCargo[]) ArrayUtils
				.addAll(receivingCWDBenefits, receivingWWDBenefits);

		/** Create a map with entry for each case number -
		 * Key : case number
		 * value : all the eligibility cargos pertaining to that case number*/
		this.populateEligibilityMapByCaseNum(eligibilityMapWWDCWD,
				receivingCWDandWWDBenefits);

		if (null != eligibilityMapWWDCWD && !eligibilityMapWWDCWD.isEmpty()) {
			Iterator<Entry<Long, List<EdEligibilityCargo>>> eligibilityMapWWDCWDIterator = eligibilityMapWWDCWD
					.entrySet().iterator();

			/** Iterate over each entry in the map */
			while (eligibilityMapWWDCWDIterator.hasNext()) {
				Map.Entry<Long, List<EdEligibilityCargo>> individualCaseEntry = (Map.Entry<Long, List<EdEligibilityCargo>>) eligibilityMapWWDCWDIterator
						.next();
				long caseNum = individualCaseEntry.getKey();
				List<EdEligibilityCargo> cargoListbyCaseNum = individualCaseEntry
						.getValue();
				if (null != cargoListbyCaseNum && !cargoListbyCaseNum.isEmpty()) {
					/**
					 * Iterate over each eligibility cargo for the particular case number
					 */
					for (EdEligibilityCargo cargo : cargoListbyCaseNum) {
						long indvId = this.getIndvIdFromEligibility(
								cargo.getCaseNum(), cargo.getEdgTraceId());
						if (null != cargo.getPaymentEndDt()) {
							/**
							 * Records for which payment end date is already
							 * populated
							 */
							BvMaPremiumPymtDtlsCargo[] premiumPaidCargo = null;
							try {
								
								/**Check if there are any premium payment records for that case number and indvid */
								premiumPaidCargo = coDAOServices
										.findIfPremiumPaidByMonth(indvId,
												cargo.getCaseNum(),
												cargo.getPaymentBegDt());
								
								/**If premium not paid, add to the set of notice eligible case number list*/
								if (!this.checkPremiumPaid(premiumPaidCargo,
										cargo)) {
									caseListWWDCWD.add(caseNum);
									
								}

							} catch (Exception e) {
								CoDebugger.debugException(e.getMessage(), e);
								CoDebugger
										.debugInformation("Exception while fetching BvMaPremiumPymtDtlsCargo for Unpaid premium for WWD/CWD. Doc Id: "
												+ CoConstants.DOC_ID_NDHCC107
												+ e);
							}
						} else {
							/**
							 * Records for which payment end date is not
							 * populated
							 */
							java.sql.Timestamp firstDayOfCurDate = BiUtils
									.getFirstDayOfMonth(CoDateFactory
											.getTimestamp());
							Timestamp firstDayOfNextMonth = BiUtils.addMonths(firstDayOfCurDate, 1);
							for (Timestamp begdate = cargo.getPaymentBegDt(); BiUtils
									.compareDate(begdate, firstDayOfNextMonth) != 1; begdate = BiUtils
									.addMonths(begdate, 1)) {
								BvMaPremiumPymtDtlsCargo[] premiumPaidCargo = null;
								try {
									
									/**Check if there are any premium payment records for that case number and indvid */
									premiumPaidCargo = coDAOServices
											.findIfPremiumPaidByMonth(indvId,
													cargo.getCaseNum(), begdate);
									
									/**If premium not paid, add to the list of notice eligible case number list */
									if (!this.checkPremiumPaid(premiumPaidCargo,
													cargo)) {
										caseListWWDCWD.add(caseNum);
										/**Break out of the loop as soon as one unpaid record is found*/
										break;
									}
								} catch (Exception e) {
									CoDebugger
											.debugException(e.getMessage(), e);
									CoDebugger
											.debugInformation("Exception while fetching BvMaPremiumPymtDtlsCargo for Unpaid premium for WWD/CWD. Doc Id: "
													+ CoConstants.DOC_ID_NDHCC107
													+ e);
								}
							}
						}
					}
				}
			}
		}
		/**return the Set containing all the case numbers eligible for notice*/
		return caseListWWDCWD;
	}

	/**
	 * @author karraj
	 * @param premiumPaidCargo
	 * @param edCargo
	 * @return
	 */
	private boolean checkPremiumPaid(
			BvMaPremiumPymtDtlsCargo[] premiumPaidCargo,
			EdEligibilityCargo edCargo) {
		CoDebugger
				.debugInformation("COBatch.countPremiumPaid for NDHCC107 - > start");
		double appliedAmount = 0.0;
		if (null == premiumPaidCargo
				|| (null != premiumPaidCargo && premiumPaidCargo.length == 0)) {
			/** If no payment record found, check if the spendDownAmt for the case is more that 0*/
			return (edCargo.getSpendDownAmt() > 0.0) ? false : true;
		}

		double premuimAmount = premiumPaidCargo[0].getPremiumAmt();
		for (BvMaPremiumPymtDtlsCargo cargo : premiumPaidCargo) {
			/**If payment record is found, check the collective amount applied of all the payment records*/
			appliedAmount += cargo.getAmountApplied();
		}
		/**Return true(premium paid) if the premium amount is greater than or equal to the applied amount */
		return appliedAmount >= premuimAmount ? true : false;
	}

	/**
	 * @author karraj
	 * @param caseNum
	 * @param edgTraceId
	 * @return
	 */
	private long getIndvIdFromEligibility(long caseNum, long edgTraceId) {

		CoDebugger
				.debugInformation("COBatch.getIndvIdFromEligibility for NDHCC107 - > start");
		CoDebugger.debugInformation("Case Num - > " + caseNum);
		CoDebugger.debugInformation("Edg Trace id - > " + edgTraceId);
		long indvId = 0;
		EdIndvEligibilityCargo[] edIndvEligibility = null;
		try {
			edIndvEligibility = (EdIndvEligibilityCargo[]) coDAOServices
					.getEdIndvEligibility(caseNum, edgTraceId);
		} catch (Exception e) {
			CoDebugger.debugException(e.getMessage(), e);
			CoDebugger
					.debugInformation("Exception while fetching EdIndvEligibilityCargo for case :"
							+ caseNum + e);
		}
		if (null != edIndvEligibility && edIndvEligibility.length > 0) {
			indvId = edIndvEligibility[0].getIndvId();
		}
		return indvId;

	}
	/**
	 * @author karraj
	 * @param eligibilityMapWWDCWD
	 * @param receivingCWDandWWDBenefits
	 * @return
	 */
	private Map<Long,List<EdEligibilityCargo>> populateEligibilityMapByCaseNum(Map<Long, List<EdEligibilityCargo>> eligibilityMapWWDCWD,
			EdEligibilityCargo[] receivingCWDandWWDBenefits) {
		CoDebugger
				.debugInformation("COBatch.populateEligibilityMapByCaseNum for NDHCC107 - > start");
		if (null != receivingCWDandWWDBenefits
				&& receivingCWDandWWDBenefits.length > 0) {
			for (EdEligibilityCargo cargo : receivingCWDandWWDBenefits) {
				long caseNum = cargo.getCaseNum();
				if (eligibilityMapWWDCWD.containsKey(caseNum)) {
					eligibilityMapWWDCWD.get(caseNum).add(cargo);
				} else {
					List<EdEligibilityCargo> listEd = new ArrayList<EdEligibilityCargo>();
					listEd.add(cargo);
					eligibilityMapWWDCWD.put(caseNum, listEd);
				}
			}
		}
		return eligibilityMapWWDCWD;
	}
	private COCorrespondence setCorrespondence(String triggerType,
			boolean manualSwitch, long caseNum) {
		COCorrespondence coCorrespondence;
		coCorrespondence = new COCorrespondence();
		coCorrespondence.setDocId(triggerType);
		coCorrespondence.setCaseAppNumber(String.valueOf(caseNum));
		coCorrespondence.setCaseAppFlag('C');
		coCorrespondence.setDocType(CoConstants.CO_DOC_TYPE_CD_N);
		coCorrespondence.setMiscParameters("C");
		coCorrespondence.setIsManualyGenerated(manualSwitch);
		return coCorrespondence;
	}
	private VEdEligibilityIndvCargo[] getTriggersForNDMATBN97(VEdEligibilityIndvCargo[] vEdEligibilityCargos, Timestamp asOfDate)
			throws CoException {
		try {
			vEdEligibilityCargos =  (VEdEligibilityIndvCargo[]) coDAOServices.findEdEligibilityForNDMATBN97(0,"MA",asOfDate);
		} catch (NoDataFoundException e) {
			CoDebugger.debugException(e.getMessage(), e); 
			CoDebugger.debugInformation("Exception while fetching EdEligibilityRecords for " + CoConstants.DOC_ID_NDMATBN97 + e);

		}
		return vEdEligibilityCargos;
	}
	private BiIssuanceTriggerCargo[] getTriggersForNDCCAP71(BiIssuanceTriggerCargo[] biIssuanceTriggerCargo)
			throws CoException {
		Timestamp asOfDateTimestamp = null;
		java.text.SimpleDateFormat dateFormater = new java.text.SimpleDateFormat(CoBatchConstants.asOfDtMMDDYYYY);
		if(null!=asOfDate && !CoConstants.EMPTY_STRING.equals(asOfDate)){
			Date date = null;
			try {
				date = (Date)dateFormater.parse(asOfDate);
				asOfDateTimestamp = new Timestamp(date.getTime());
			} catch (ParseException e) {
				CoDebugger.debugException(e.getMessage(), e); 
				CoDebugger.debugInformation("Parse Exception while parsing as of date" + CoConstants.DOC_ID_NDCCAP069 + e);
				asOfDateTimestamp = CoDateFactory.getTimestamp();
			}
			
		}else{
			asOfDateTimestamp = CoDateFactory.getTimestamp();
		}
		try {
			biIssuanceTriggerCargo =  (BiIssuanceTriggerCargo[]) coDAOServices.findBiTriggerForNDCCAP71(0,asOfDateTimestamp);
		} catch (NoDataFoundException e) {
			CoDebugger.debugException(e.getMessage(), e); 
			CoDebugger.debugInformation("Exception while fetching BiIssuanceTriggerRecords for " + CoConstants.DOC_ID_NDCCAP71 + e);

		}
		return biIssuanceTriggerCargo;
	}
	private VBiCcdmiPayeeCargo[] getCcdmiTriggersForNDCCAP71(VBiCcdmiPayeeCargo[] vBiCcdmiPayeeCargo)
			throws CoException {
		Timestamp asOfDateTimestamp = null;
		java.text.SimpleDateFormat dateFormater = new java.text.SimpleDateFormat(CoBatchConstants.asOfDtMMDDYYYY);
		if(null!=asOfDate && !CoConstants.EMPTY_STRING.equals(asOfDate)){
			Date date = null;
			try {
				date = (Date)dateFormater.parse(asOfDate);
				asOfDateTimestamp = new Timestamp(date.getTime());
			} catch (ParseException e) {
				CoDebugger.debugException(e.getMessage(), e); 
				CoDebugger.debugInformation("Parse Exception while parsing as of date" + CoConstants.DOC_ID_NDCCAP71 + e);
				asOfDateTimestamp = CoDateFactory.getTimestamp();
			}
			
		}else{
			asOfDateTimestamp = CoDateFactory.getTimestamp();
		}
		try {
			vBiCcdmiPayeeCargo =  (VBiCcdmiPayeeCargo[]) coDAOServices.findCcdmiTriggerForNDCCAP71(asOfDateTimestamp);
		} catch (NoDataFoundException e) {
			CoDebugger.debugException(e.getMessage(), e); 
			CoDebugger.debugInformation("Exception while fetching BiIssuanceTriggerRecords for " + CoConstants.DOC_ID_NDCCAP71 + e);

		}
		return vBiCcdmiPayeeCargo;
	}
	private VBiWarrantDetailCargo[] getWarrantTriggersForNDCCAP71(VBiWarrantDetailCargo[] vBiWarrantDetailCargo)
			throws CoException {
		Timestamp asOfDateTimestamp = null;
		java.text.SimpleDateFormat dateFormater = new java.text.SimpleDateFormat(CoBatchConstants.asOfDtMMDDYYYY);
		if(null!=asOfDate && !CoConstants.EMPTY_STRING.equals(asOfDate)){
			Date date = null;
			try {
				date = (Date)dateFormater.parse(asOfDate);
				asOfDateTimestamp = new Timestamp(date.getTime());
			} catch (ParseException e) {
				CoDebugger.debugException(e.getMessage(), e); 
				CoDebugger.debugInformation("Parse Exception while parsing as of date" + CoConstants.DOC_ID_NDCCAP71 + e);
				asOfDateTimestamp = CoDateFactory.getTimestamp();
			}
			
		}else{
			asOfDateTimestamp = CoDateFactory.getTimestamp();
		}
		try {
			vBiWarrantDetailCargo =  (VBiWarrantDetailCargo[]) coDAOServices.findWarrantTriggerForNDCCAP71(asOfDateTimestamp);
		} catch (NoDataFoundException e) {
			CoDebugger.debugException(e.getMessage(), e); 
			CoDebugger.debugInformation("Exception while fetching BiIssuanceTriggerRecords for " + CoConstants.DOC_ID_NDCCAP71 + e);

		}
		return vBiWarrantDetailCargo;
	}

	private BiIssuanceTriggerCargo[] getTriggersForNDLI0086(BiIssuanceTriggerCargo[] biIssuanceTriggerCargo)
			throws CoException {
		try {
			biIssuanceTriggerCargo =  (BiIssuanceTriggerCargo[]) coDAOServices.findBiTriggerForNDLI0086(0,"LI");
		} catch (NoDataFoundException e) {
			CoDebugger.debugException(e.getMessage(), e); 
			CoDebugger.debugInformation("Exception while fetching BiIssuanceTriggerRecords for " + CoConstants.DOC_ID_NDLI0086 + e);

		}
		return biIssuanceTriggerCargo;
	}
	private BiIssuanceTriggerCargo[] getTriggersForNDLI0086(BiIssuanceTriggerCargo[] biIssuanceTriggerCargo, String asOfDate)
			throws CoException {
		try {
			biIssuanceTriggerCargo =  (BiIssuanceTriggerCargo[]) coDAOServices.findBiTriggerForNDLI0086(0,"LI",asOfDate);
		} catch (Exception e) {
			CoDebugger.debugException(e.getMessage(), e); 
			CoDebugger.debugInformation("Exception while fetching BiIssuanceTriggerRecords for " + CoConstants.DOC_ID_NDLI0086 + e);

		}
		return biIssuanceTriggerCargo;
	}
	/**
	 * @author shreyasingh
	 * @param biIssuanceTriggerCargo
	 * @param pmLiheapPaymemtCargo
	 * @param asOfDate
	 */
	private BiIssuanceTriggerCargo[] getbiIssuanceTriggersForNDLIN085(BiIssuanceTriggerCargo[] biIssuanceTriggerCargo, String asOfDate){
		CoDebugger.debugInformation("Liheap Client Payment getbiIssuanceTriggerForNDLIN085" + CoConstants.NDLIN085 + "asOfDate "+asOfDate);

		try {
			biIssuanceTriggerCargo =  (BiIssuanceTriggerCargo[]) coDAOServices.findBiTriggerForNDLI0086(0,"LI",asOfDate);
		} catch (Exception e) {
			CoDebugger.debugException(e.getMessage(), e); 
			CoDebugger.debugInformation("Exception while fetching BiIssuanceTriggerRecords for " + CoConstants.NDLIN085 +" "+ e);
		}
		return biIssuanceTriggerCargo;
	}
	/**
	 * @author shreyasingh
	 * @param pmLiheapPaymemtCargo
	 * @param asOfDate
	 */
	private PmLiheapPaymentCargo[] getpmLiheapPaymemtTriggersForNDLIN085(PmLiheapPaymentCargo[] pmLiheapPaymemtCargo, String asOfDate){
		CoDebugger.debugInformation("Liheap Client Payment getpmLiheapPaymemtTriggersForNDLIN085" + CoConstants.NDLIN085 + "asOfDate "+asOfDate);
		try {
			SimpleDateFormat datetimeFormatter1 = new SimpleDateFormat(CoConstants.DATE_FORMAT);
			Date batchAsOfDate = datetimeFormatter1.parse(asOfDate);
			Timestamp asOfDateTimestamp = new Timestamp(batchAsOfDate.getTime());
			pmLiheapPaymemtCargo = (PmLiheapPaymentCargo[]) coDAOServices.getAllRejectedPayments(asOfDateTimestamp);

		}catch (Exception e) {
			CoDebugger.debugInformation("Error while fetching from PmLiheapPayment table");
			CoDebugger.debugException(e.getMessage(), e);
		}
		return pmLiheapPaymemtCargo;

	}
	
	/**
	 * 
	 * @param bvClaimCargos
	 * @return
	 * @throws CoException
	 */
	private BvClaimCargo[] getTriggersForNDUNN054(String asOfDate)throws CoException {
		BvClaimCargo[] bvClaimCargos = null;
		Timestamp asOfDateTime = null;
		java.text.SimpleDateFormat dateFormater = new java.text.SimpleDateFormat(CoBatchConstants.asOfDtMMDDYYYY);
		if(null!=asOfDate && !CoConstants.EMPTY_STRING.equals(asOfDate)){
			Date date = null;
			try {
				date = (Date)dateFormater.parse(asOfDate);
				asOfDateTime = new Timestamp(date.getTime());
			} catch (ParseException e) {
				CoDebugger.debugException(e.getMessage(), e); 
				CoDebugger.debugInformation("Parse Exception while parsing as of date" + CoConstants.DOC_NDUNN054 + e);
				asOfDateTime = CoDateFactory.getTimestamp();
			}
			
		}else{
			asOfDateTime = CoDateFactory.getTimestamp();
		}
		
		try{
			bvClaimCargos = (BvClaimCargo[]) coDAOServices.getCreateBvClaimCargoDetails(asOfDateTime);
			if (bvClaimCargos!=null && bvClaimCargos.length>0){
				CoDebugger.debugInformation("bvClaimCargoCreateSize -----------"+bvClaimCargos.length);
			}
			
		}catch (Exception e) {
			CoDebugger.debugException(e.getMessage(), e); 
			CoDebugger.debugInformation("Exception while fetching Claims created today for " + CoConstants.DOC_NDUNN054 + e);
		}		
		return bvClaimCargos;
	}

	private EdIndvEligibilityCargo[]  getTriggersForNDTANFF72(EdIndvEligibilityCargo[] edIndvEligibilityCargo)
			throws CoException {
		try {
			edIndvEligibilityCargo =  (EdIndvEligibilityCargo[]) coDAOServices.getAllIndvDetails(asOfDate);
		} catch (NoDataFoundException e) {
			CoDebugger.debugException(e.getMessage(), e); 
			CoDebugger.debugInformation("Exception while fetching EdIndvEligibilityCargo for " + CoConstants.NDTANFF72 + e);

		}
		return edIndvEligibilityCargo;
	}
	private BiIssuanceTriggerCargo[]  getTriggersForNDEBN111Insert(BiIssuanceTriggerCargo[] edCargos)
            throws CoException {
     BiIssuanceTriggerCargo[] edEligibilityCargos;
     edEligibilityCargos =   (BiIssuanceTriggerCargo[]) coDAOServices.getProgramsNDEBN111Insert();
     return edEligibilityCargos;
}

private CoRequestHistoryCargo[]  getTriggersForNDEBN111Delete(BiIssuanceTriggerCargo[] edCargos)
            throws CoException {
     CoRequestHistoryCargo[] edEligibilityCargos;
     edEligibilityCargos =   (CoRequestHistoryCargo[]) coDAOServices.getProgramsNDEBN111DeleteCaseNum();
     return edEligibilityCargos;
}


private BiIssuanceTriggerCargo[]  getTriggersForNDEBN111FinalCaseNum(String finalcaseNum, String caseNum)
            throws CoException {
     BiIssuanceTriggerCargo[] biIssuance;
     biIssuance =   (BiIssuanceTriggerCargo[]) coDAOServices.getProgramsNDEBN111FinalCaseNum(finalcaseNum, caseNum);
     return biIssuance;
}


private BiIssuanceTriggerCargo[]  getTriggersForNDEBN111RemoveCaseNum(String caseNum)
            throws CoException {
     BiIssuanceTriggerCargo[] biIssuance;
     biIssuance =   (BiIssuanceTriggerCargo[]) coDAOServices.getProgramsNDEBN111RemoveCaseNum(caseNum);
     return biIssuance;
}

private BiFsDetailCargo[]  getTriggersForNDEBN111(long caseNum)
            throws CoException {
     BiFsDetailCargo[] biFsDetailCargo;
     biFsDetailCargo =   (BiFsDetailCargo[]) coDAOServices.getBiFsDetailsProgFS(caseNum);
     return biFsDetailCargo;
}
private BiTanfDetailCargo[]  getTriggersForNDEBN111TF(long caseNum)
            throws CoException {
     BiTanfDetailCargo[] biFsDetailCargo;
     biFsDetailCargo =   (BiTanfDetailCargo[]) coDAOServices.getBiFsDetailsProgTF(caseNum);
     return biFsDetailCargo;
}
private VBiCcdmiPayeeCargo[]  getTriggersForNDEBN111CCAP(long caseNum)
            throws CoException {
     VBiCcdmiPayeeCargo[] biFsDetailCargo;
     biFsDetailCargo =   (VBiCcdmiPayeeCargo[]) coDAOServices.getBiDetailsProgCCAPInsertion(caseNum);
      return biFsDetailCargo;
}

private BiLiheapDetailCargo[]  getTriggersForNDEBN111LIHEAP(long caseNum)
        throws CoException {
	BiLiheapDetailCargo[] biliheapDetailCargo;
 biliheapDetailCargo =   (BiLiheapDetailCargo[]) coDAOServices.getBiLiheapDetails(String.valueOf(caseNum));
  return biliheapDetailCargo;
}

	private DcCaseIndividualCargo[] getTriggersForNDTFMRN77(DcCaseIndividualCargo[] dcCaseIndvCargos,String asOfDate)
			throws CoException {
		try {
			dcCaseIndvCargos =  (DcCaseIndividualCargo[]) coDAOServices.findDCDisabilityINdvForNDTFMRN77AsOfDate(asOfDate);
		} catch (NoDataFoundException e) {
			CoDebugger.debugException(e.getMessage(), e); 
			CoDebugger.debugInformation("Exception while fetching DCDisabilityRecords for " + CoConstants.DOC_ID_NDTFMRN77 + e);

		}
		return dcCaseIndvCargos;
	}

	private void addCasesForMassMail(Set<Long> activeCases, long caseNum) {
		if(caseNum>0 && !activeCases.contains(caseNum)){
			activeCases.add(caseNum);																
		}
	}

	private void addingTriggerForMassMail(Set<Long> activeCases,
			List<COCorrespondence> coCorrespondenceList,
			CoMassMailingReqCargo[] arrCoMassMailingCargos, int i, String prog) {
		COCorrespondence coCorrespondence;
		/**adding trigger for each of the cases*/
		if(activeCases!=null && activeCases.size()>0){
			for(long caseNum:activeCases){
				coCorrespondence= new COCorrespondence();
				coCorrespondence.setMassMailingId(arrCoMassMailingCargos[i].getMassMailingSeqNum());
				coCorrespondence.setMassGeneratedSw(CoConstants.CHAR_Y);
				coCorrespondence.setDocId(CoConstants.CO_DOC_ID_NCH049);
				coCorrespondence.setCaseAppNumber(String.valueOf(caseNum));
				coCorrespondence.setCaseAppFlag('C');
				coCorrespondence.setDocType(CoConstants.CO_DOC_TYPE_CD_N);
				coCorrespondence.setAssistanceProgramCode(prog);
				coCorrespondence.setMiscParameters("H");
				coCorrespondence.setAttachments(arrCoMassMailingCargos[i].getAttachments());
				coCorrespondenceList.add(coCorrespondence);
			}

		}
	}

	/**
	 * 
	 * @param srcFilePath
	 * @return
	 */
	private String getCSVFilePath(String srcFilePath, String srcFileName) {
		String csvFilePath = null;
		String csvFileName = null;
		String dateStamp = null;



		String csvFileExtn = (String) ftpProperty.getProperty(CoConstants.CSV_FILE_EXTN);
		String preDefinedDate = (String) ftpProperty.getProperty(CoConstants.CSV_PREDEFINED_DATE);
		CoDebugger.debugInformation("Predefined Date -->"+ preDefinedDate);
		if (StringUtils.isEmpty(preDefinedDate))
		{
			SimpleDateFormat sdf = new SimpleDateFormat(
					(String) ftpProperty
					.getProperty(CoConstants.CSV_DATE_FORMAT));
			dateStamp = sdf.format(new Date());
		} else
		{
			dateStamp = preDefinedDate;
		}
		csvFileName = srcFileName + dateStamp + csvFileExtn;
		csvFilePath = srcFilePath + csvFileName;

		return csvFilePath;
	}

	/**
	 * Returning the Recipient name
	 * @param recipientId
	 * @param isAuthRep
	 * @return
	 * @throws CoException 
	 * @throws NumberFormatException 
	 */
	public String getRecipientName(String recipientId) throws NumberFormatException, CoException{
		String recipientName = null;

		/**get name from DC_INDV*/
		DcIndvCargo[] dcIndvCargoArr  = (DcIndvCargo[])coDAOServices.getDcIndividualName(Long.parseLong(recipientId));

		if(null != dcIndvCargoArr && dcIndvCargoArr.length > 0 && null != dcIndvCargoArr[0]){
			recipientName =	(String.valueOf(COFormsUtility
					.getFormattedName(dcIndvCargoArr[0].getFirstName(),
							dcIndvCargoArr[0].getMidName(),
							dcIndvCargoArr[0].getLastName())));
		}

		return recipientName;
	}


	/**
	 * 
	 * @param coCorrespondence
	 */
	public boolean isRecipientChanged(COCorrespondence coCorrespondence) {

		boolean recipientFlag = false;

		boolean isOriginalAuthRep = false;
		DcAuthRepCargo dcAuthRepCargo = null;
		DcAuthRepCollection authRepCollection = new DcAuthRepCollection();
		Object[] objCaseParams = new Object[2];

		java.sql.Timestamp today = CoDateFactory.getTimestamp();	
		authRepCollection.getCargo().setCaseNum(Long.parseLong(coCorrespondence.getCaseAppNumber()));					
		objCaseParams[0] = authRepCollection.getCargo();
		objCaseParams[1] = today;

		try {
			DcAuthRepCargo[] dcAuthRep = (DcAuthRepCargo[]) authRepCollection
					.select("findActiveByCaseNum", objCaseParams);

			if(dcAuthRep != null && dcAuthRep.length > 0) {
				/** if authorized representative is present*/
				for(int index = 0; index < dcAuthRep.length; index++) {
					dcAuthRepCargo =  (DcAuthRepCargo)dcAuthRep[index];

					/**for SNAP Notice of Expiration */
					if(CoConstants.SNAP_EXPIRATION_DOC_ID.equalsIgnoreCase(coCorrespondence.getDocId())){
						if(CoConstants.YES_CHAR == dcAuthRepCargo.getRcvsSnapCertFormsSw()){
							isOriginalAuthRep = true;
						}
					}  /** for TANF Notice of Expiration */
					else if(CoConstants.TANF_EXPIRATION_DOC_ID.equalsIgnoreCase(coCorrespondence.getDocId())){
						if(CoConstants.YES_CHAR == dcAuthRepCargo.getRcvsTanfRedetFormsSw()){
							isOriginalAuthRep = true;
						}
					} else {
						if("O".equalsIgnoreCase(dcAuthRepCargo.getRcvsNonMedicaidNoticesCd())){// there will always be only one authrep receiving original
							isOriginalAuthRep = true;
						}					
					}
					//********************************************//
					newRecipientsList.add(String.valueOf(dcAuthRepCargo.getAuthrepSeqNum()));


					if((CoConstants.SNAP_EXPIRATION_DOC_ID.equalsIgnoreCase(coCorrespondence.getDocId()) 
							|| CoConstants.TANF_EXPIRATION_DOC_ID.equalsIgnoreCase(coCorrespondence.getDocId()))
							&& isOriginalAuthRep == true){
						break;
					} 
				}

				if(!isOriginalAuthRep){
					if(CoConstants.SNAP_EXPIRATION_DOC_ID.equalsIgnoreCase(coCorrespondence.getDocId()) 
							|| CoConstants.TANF_EXPIRATION_DOC_ID.equalsIgnoreCase(coCorrespondence.getDocId())){
						if(newRecipientsList != null){
							newRecipientsList.clear();
						}
					}
					setHoHIndividualData(coCorrespondence.getCaseAppNumber());
				}				
			} else {
				setHoHIndividualData(coCorrespondence.getCaseAppNumber());
			}

			/** Fetch Original Recipients at the time of correspondence creation.*/
			getOriginalRecipients(coCorrespondence.getCoReqSeq());
			if (newRecipientsList != null && oldRecipientsList != null
					&& newRecipientsList.size() == oldRecipientsList.size()) {
				for (String recipient: newRecipientsList) {
					if (oldRecipientsList.contains(recipient)) {
						recipientFlag = false;
					} else {
						recipientFlag = true;
						break;
					}
				}
			} else {
				recipientFlag = true;
			}
		} catch (FrameworkException e) {
			CoDebugger.debugException("FrameworkException while fetching Auth Rep for caseId :: " + coCorrespondence.getCaseAppNumber(), e);
		} catch (ApplicationException e) {
			CoDebugger.debugException("ApplicationException while fetching Auth Rep for caseId :: " + coCorrespondence.getCaseAppNumber(), e);
		} catch (CoException e) {
			CoDebugger.debugException("CoException while fetching HoHIndividual for caseId :: " + coCorrespondence.getCaseAppNumber(), e);
		}
		CoDebugger.debugInformation("recipientFlag ------"+recipientFlag);
		recipientFlag = false;
		CoDebugger.debugInformation("recipientFlag ------"+recipientFlag);
		return recipientFlag;
	}


	/**
	 * 
	 * @param caseNum
	 * @throws CoException
	 */
	private void setHoHIndividualData(String caseNum) throws CoException {
		DcHeadOfHouseholdCargo[] dcHeadOfHouseholdCargoArray;
		try {
			dcHeadOfHouseholdCargoArray = (DcHeadOfHouseholdCargo[]) coDAOServices
					.getHoHIndividual(Long.parseLong(caseNum));
			if (dcHeadOfHouseholdCargoArray != null
					&& dcHeadOfHouseholdCargoArray.length > 0) {
				if (dcHeadOfHouseholdCargoArray[0].getIndvId() > 0) {
					newRecipientsList.add(String.valueOf(dcHeadOfHouseholdCargoArray[0].getIndvId()));
				}
			}
		} catch (NumberFormatException e) {
			CoDebugger.debugException(
					"NumberFormatException while fetching HoHIndividual for caseId :: "
							+ caseNum, e);
		} catch (CoException e) {
			CoDebugger.debugException(
					"CoException while fetching HoHIndividual for caseId :: "
							+ caseNum, e);
		}	
	}	


	/**
	 * 
	 * @param coReqSeq
	 */
	private void getOriginalRecipients(long coReqSeq) {
		Object[] objCoReqRptArr = null;
		CoRequestRecipientsCargo coRecipientsCargo = null;
		String recipientData = null;
		String recipientId = null;

		try {
			objCoReqRptArr = coDAOServices.getCoRequestAllRecipients(coReqSeq);
			if (objCoReqRptArr != null && objCoReqRptArr.length > 0) {
				for (int i=0; i<objCoReqRptArr.length; i++) {
					coRecipientsCargo = (CoRequestRecipientsCargo) objCoReqRptArr[i];
					recipientData = coRecipientsCargo.getRecipientData();
					if (recipientData != null) {
						recipientId = recipientData.substring(recipientData.indexOf(CoConstants.RECIPIENT_DATA_NAME_SEPARATOR) + 1);
						oldRecipientsList.add(recipientId);
					}
				}
			}
		} catch (CoException e) {
			CoDebugger.debugException(
					"CoException while fetching Co Request Recipients for co_req_seq :: "
							+ coReqSeq, e);
		}
	}

	/**
	 * CHIMESMO-50189: Need not check for change in recipients if it is a re-print request
	 * @author asanghvi
	 * @param coReqSeq
	 * @return
	 */
	private boolean isReprintRequest(long coReqSeq)
	{
		Object[] objCoReqRptArr = null;
		CoRequestRecipientsCargo coRecipientsCargo = null;
		String locationPath = null;

		try
		{
			objCoReqRptArr = coDAOServices.getCoRequestAllRecipients(coReqSeq);
			if (objCoReqRptArr != null && objCoReqRptArr.length > 0)
			{
				for (int i = 0; i < objCoReqRptArr.length; i++)
				{
					coRecipientsCargo = (CoRequestRecipientsCargo) objCoReqRptArr[i];
					locationPath = coRecipientsCargo.getLocationPath();
					if (locationPath != null && locationPath.endsWith(".pdf"))
					{
						CoDebugger.debugInformation("isReprintRequest for-" + coReqSeq + ":TRUE");
						return false;
					}
				}
			}
		} catch (CoException e)
		{
			CoDebugger.debugException(
					"CoException while fetching Co Request Recipients for co_req_seq :: "
							+ coReqSeq, e);
		}
		return false;
	}


	/**
	 * This method is used merge pdf files
	 * 
	 * @param jobId
	 * @param groupBy
	 * @return retValue int
	 * @throws Exception
	 * @author rvelidandla
	 */
	protected int mergePdfFiles(String jobId,String groupBy) throws Exception {
		int resulFlag = 0;
		Map<String,ArrayList<String>> docIdMap = null;
		try {
			coConnection = tbc.getConnection();
			coConnection.setAutoCommit(false);
			coDAOServices.setConnection(coConnection);
		} catch (Exception e) {
			return this.handleBatchException(e, 1, null, null);
		}		

		/** Fetch DocIds from Co Master.*/
		try {
			docIdMap = new HashMap<String, ArrayList<String>>();
			if(null != groupBy && ("CaseNumAndGroupId").equals(groupBy)){
				docIdMap = fetchFileNamesByCaseNumAndGroupId(docIdMap);
			}else if(null != groupBy && ("CaseNum").equals(groupBy)){
				docIdMap = fetchFileNamesByCaseNum(docIdMap);
			}else if(null != groupBy && ("GroupId").equals(groupBy)){
				docIdMap = fetchFileNamesByGroupId(docIdMap);
			}else{
				docIdMap = this.getDocs(coConnection);
				docIdMap = fetchFileNamesByDocId(docIdMap);
			}
		} catch (Exception e) {
			resulFlag = -1;
			writeException(
					"Exception while fetching docIds from CO_MASTER ",
					"Could not fetch docIds for CO_MASTER ...ABORTING JOB "+ jobId ,
					e,
					true); 
			throw new CoException(		
					"Exception while fetching docIds from CO_MASTER " + e);
		}	
		/** merge PDF files by DocId*/
		try {
			mergePdfsByDocId(docIdMap);
			String folder1 = (String)ftpProperty.getProperty(CoConstants.CO_MERGED_PDF_FILES_PATH);
			String folder2 = (String)ftpProperty.getProperty(CoConstants.CO_1095B_MERGED_PDF_FILES_PATH);
			File file1 = new File(folder1+"trigger.txt");
			File file2 = new File(folder2+"trigger.txt");

			/**DELETE old trigger.txt if it exists*/
			if (file1.exists()) {
				CoDebugger.debugInformation("file already exists ---- "+file1.getAbsolutePath());
				CoDebugger.debugInformation("deleting file ---- "+file1.getAbsolutePath());
				if(file1.delete()){
					CoDebugger.debugInformation("deleted file successfully ---- "+file1.getAbsolutePath());
				}else{
					CoDebugger.debugInformation("failed to delete file successfully ---- "+file1.getAbsolutePath());
				}
			}

			if (file2.exists()) {
				CoDebugger.debugInformation("file already exists ---- "+file2.getAbsolutePath());
				CoDebugger.debugInformation("deleting file ---- "+file2.getAbsolutePath());
				if(file2.delete()){
					CoDebugger.debugInformation("deleted file successfully ---- "+file2.getAbsolutePath());
				}else{
					CoDebugger.debugInformation("failed to delete file successfully ---- "+file2.getAbsolutePath());
				}
			}

			if(file1.createNewFile()){
				CoDebugger.debugInformation("file created successfully filename ---- "+file1.getAbsolutePath());
				writeCoLog("file created successfully filename ---- "+file1.getAbsolutePath(), coLogFile);
			}
			if(file2.createNewFile()){
				CoDebugger.debugInformation("file created successfully filename ---- "+file2.getAbsolutePath());
				writeCoLog("file created successfully filename ---- "+file2.getAbsolutePath(), coLogFile);
			}
		} catch (Exception e) {
			resulFlag = -1;
			writeException("Exception while  merging the pdf files", jobId, e, false);
			throw new CoException("mergePdfFiles ----> Exception while merging PDFs ----> " + e);
		}
		generateSummaryReport(CoConstants.PBOWS_BATCH_SUMMARY, readCount, successCount, failCount, blankNoticeCounter);		

		return resulFlag;
	}

	/**
	 * This method is used to get active docIds from co_master table
	 * 
	 * @param readConnection
	 *            SQL Connection Object
	 * @param jobId
	 * @return  Map<String,ArrayList<String>> will be returned
	 * @throws Exception
	 */
	protected  Map<String,ArrayList<String>> getDocs(Connection readConnection) throws Exception {

		Map<String,ArrayList<String>> docIdMap = null;
		CoMasterCargo[] coMasterCargoArray =  null;
		try {
			coDAOServices.setConnection(readConnection);
			coMasterCargoArray = (CoMasterCargo[] ) coDAOServices.getActiveDocIds();			 
			if(null != coMasterCargoArray && coMasterCargoArray.length>0){
				docIdMap = new HashMap<String, ArrayList<String>>();
				for(CoMasterCargo mCargo:coMasterCargoArray){
					ArrayList<String> fNameList = new ArrayList<String>();
					docIdMap.put(mCargo.getDocName(), fNameList);
				}
			}	
		} catch (Exception e) {
			CoDebugger.logWithLevel("exception fetching the Docids...." + e,
					ILog.CRITICAL);
			writeCoLog("exception fetching the Docids...."+e, coLogFile);
			tbc.stop(FwBatchConstants.ABORT); 
			throw new CoException("Exception while stopping tbc... " + e);
		}
		return docIdMap;
	}


	/**
	 * This method is used to fectch the file names by DOC_ID 
	 * @param Map<String,ArrayList<String>>
	 * @return  
	 * @throws 
	 */
	public Map<String,ArrayList<String>> fetchFileNamesByDocId(Map<String,ArrayList<String>> fileNameMap){
		String folder = ftpProperty.getProperty(CoConstants.CO_PDF_FILES_PATH);;		
		try{		 
			File _folder = new File(folder);
			File[] filesInFolder = _folder.listFiles();
			for (File file : filesInFolder)
			{
				String fName = file.getName();
				try{
					String[] splitName = fName.split(CoConstants.UNDERSCORE);
					if(null!=fileNameMap && null!=fileNameMap.get(splitName[3])){	        		
						fileNameMap.get(splitName[3]).add(fName);
						readCount++;
					}
				}catch(Exception e){
					CoDebugger.debugInformation("Exception file name...."+fName);
					CoDebugger.debugException("Exception while fecting the file names...."+fName,e);
				}
			}

		}catch (Exception e) {			 
			CoDebugger.debugException("Exception while fecting the file names....",e);
			writeCoLog("Exception while fecting the file names...."+e, coLogFile);
		}	
		return fileNameMap;
	}

	/**
	 * This method is used to merge the PDF files by DOC_ID 
	 * @param Map<String,ArrayList<String>>
	 * @return  
	 * @throws 
	 */
	public void mergePdfsByDocId(Map<String, ArrayList<String>> fileNameMap) throws CoException {
		PDFMergerUtility mergePdf = null;
		String folder = null;
		long chunkSize = 0;
		try {
			String mailDate = (new SimpleDateFormat(CoConstants.DATE_FORMAT_yyyyMMdd).format(getNextWorkingDay(CoDateFactory.getTimestamp())));
			String generateDate = (new SimpleDateFormat(CoConstants.DATE_FORMAT_yyyyMMdd).format(CoDateFactory.getTimestamp()));
			folder = (String) ftpProperty.getProperty(CoConstants.CO_PDF_FILES_PATH);
			chunkSize = Long.valueOf((String) ftpProperty.getProperty(CoConstants.CO_PDF_CHUNK_SIZE));
			if (null != fileNameMap) {
				for (Map.Entry<String, ArrayList<String>> entry : fileNameMap.entrySet()) {
					mergePdf = new PDFMergerUtility();
					String docId = entry.getKey();
					ArrayList<String> fileList = entry.getValue();
					if (null != fileList && fileList.size() > 0) {
						readCount = readCount + fileList.size();
						if (fileList.size() < chunkSize) {
							for (String fileName : fileList) {
								File file = new File(folder + fileName);
								try {
									mergePdf.addSource(file);
									CoDebugger.debugInformation("mergePdfsByDocId ----> PDF File found ----> " + folder + fileName);
									successCount++;
								} catch (Exception e) {
									failCount++;
									CoDebugger.debugInformation("mergePdfsByDocId ----> PDF File not found ----> " + folder + fileName);
									CoDebugger.debugException("mergePdfsByDocId ----> PDF File moved while merge in process ----> " + folder + fileName, e);
									writeCoLog("mergePdfsByDocId ----> PDF File moved while merge in process ----> " + folder + fileName + " " + e, coLogFile);
									throw new CoException("mergePdfsByDocId ----> PDF File moved while merge in process ----> " + folder + fileName + " ---> " + e);
								}
							}
							if ("GR06".equals(docId)) {
								mergePdf.setDestinationFileName((String) ftpProperty.getProperty(CoConstants.CO_1095B_MERGED_PDF_FILES_PATH) + docId + CoConstants.OPEN_BRACKET + 1 + " of " + 1 + CoConstants.CLOSE_BRACKET + CoConstants.UNDERSCORE + generateDate + CoConstants.UNDERSCORE + mailDate + ".pdf");
							} else {
								mergePdf.setDestinationFileName((String) ftpProperty.getProperty(CoConstants.CO_MERGED_PDF_FILES_PATH) + docId + CoConstants.OPEN_BRACKET + 1 + " of " + 1 + CoConstants.CLOSE_BRACKET + CoConstants.UNDERSCORE + generateDate + CoConstants.UNDERSCORE + mailDate + ".pdf");
							}
							mergePdf.mergeDocuments();
						} else {
							List<List<String>> parts = new ArrayList<List<String>>();
							final int N = fileList.size();
							for (int i = 0; i < N; i += chunkSize) {
								parts.add(new ArrayList<String>(fileList.subList(i, Integer.valueOf(String.valueOf(Math.min(N, i + chunkSize))))));
							}
							int count = 1;
							for (List<String> fileNameList : parts) {
								mergePdf = new PDFMergerUtility();
								for (String fileName : fileNameList) {
									File file = new File(folder + fileName);

									try {
										mergePdf.addSource(file);
										CoDebugger.debugInformation("mergePdfsByDocId ----> PDF File found ----> " + folder + fileName);
										successCount++;
									} catch (Exception e) {
										failCount++;
										CoDebugger.debugInformation("mergePdfsByDocId ----> PDF File not found ----> " + folder + fileName);
										CoDebugger.debugException("mergePdfsByDocId ----> PDF File moved while merge in process ----> " + folder + fileName, e);
										writeCoLog("mergePdfsByDocId ----> PDF File moved while merge in process ----> " + folder + fileName + " " + e, coLogFile);
										throw new CoException("mergePdfsByDocId ----> PDF File moved while merge in process ----> " + folder + fileName + " ---> " + e);
									}
								}
								if ("GR06".equals(docId)) {
									mergePdf.setDestinationFileName((String) ftpProperty.getProperty(CoConstants.CO_1095B_MERGED_PDF_FILES_PATH) + docId + CoConstants.OPEN_BRACKET + count + " of " + parts.size() + CoConstants.CLOSE_BRACKET + CoConstants.UNDERSCORE + generateDate + CoConstants.UNDERSCORE + mailDate + ".pdf");
								} else {
									mergePdf.setDestinationFileName((String) ftpProperty.getProperty(CoConstants.CO_MERGED_PDF_FILES_PATH) + docId + CoConstants.OPEN_BRACKET + count + " of " + parts.size() + CoConstants.CLOSE_BRACKET + CoConstants.UNDERSCORE + generateDate + CoConstants.UNDERSCORE + mailDate + ".pdf");
								}
								mergePdf.mergeDocuments();
								count++;
							}
						}
					}
				}
			}
		} catch (Exception e) {
			failCount++;
			CoDebugger.debugException("Exception merging the PDF files ----> ", e);
			writeCoLog("Exception merging the PDF files ----> " + e, coLogFile);
			throw new CoException("mergePdfsByDocId ----> Exception merging the PDF files ----> " + e);
		}
	}

	/**
	 * This method is used to fectch the file names by casenum and groupid 
	 * @param Map<String,ArrayList<String>>
	 * @return  
	 * @throws 
	 */
	public Map<String,ArrayList<String>> fetchFileNamesByCaseNumAndGroupId(Map<String,ArrayList<String>> fileNameMap){
		String folder = ftpProperty.getProperty(CoConstants.CO_PDF_FILES_PATH);;		
		try{		 
			File _folder = new File(folder);
			File[] filesInFolder = _folder.listFiles();
			for (File file : filesInFolder)
			{
				String fName = file.getName();
				try{
					String[] splitName = fName.split(CoConstants.UNDERSCORE);
					String caseNumAndGroupId = splitName[4]+CoConstants.UNDERSCORE+splitName[5];
					if(null!=fileNameMap && null!=fileNameMap.get(caseNumAndGroupId)){	        		
						fileNameMap.get(caseNumAndGroupId).add(fName);
					}else{
						ArrayList<String> nameList = new ArrayList<String>();
						nameList.add(fName);
						fileNameMap.put(caseNumAndGroupId, nameList);
					}
				}catch(Exception e){
					CoDebugger.debugInformation("Exception file name...."+fName);
					CoDebugger.debugException("Exception while fecting the file names...."+fName,e);
				}
			}

		}catch (Exception e) {			 
			CoDebugger.debugException("Exception while fecting the file names....",e);
			writeCoLog("Exception while fecting the file names....exception ---------"+e, coLogFile);
		}	
		return fileNameMap;
	}

	/**
	 * This method is used to fectch the file names by casenum
	 * @param Map<String,ArrayList<String>>
	 * @return  
	 * @throws 
	 */
	public Map<String,ArrayList<String>> fetchFileNamesByCaseNum(Map<String,ArrayList<String>> fileNameMap){
		String folder = ftpProperty.getProperty(CoConstants.CO_PDF_FILES_PATH);;		
		try{		 
			File _folder = new File(folder);
			File[] filesInFolder = _folder.listFiles();
			for (File file : filesInFolder)
			{
				String fName = file.getName();
				try{
					String[] splitName = fName.split(CoConstants.UNDERSCORE);
					String caseNumAndGroupId = splitName[4];
					if(null!=fileNameMap && null!=fileNameMap.get(caseNumAndGroupId)){	        		
						fileNameMap.get(caseNumAndGroupId).add(fName);
					}else{
						ArrayList<String> nameList = new ArrayList<String>();
						nameList.add(fName);
						fileNameMap.put(caseNumAndGroupId, nameList);
					}
				}catch(Exception e){
					CoDebugger.debugInformation("Exception file name...."+fName);
					CoDebugger.debugException("Exception while fecting the file names...."+fName,e);
				}
			}

		}catch (Exception e) {			 
			CoDebugger.debugException("Exception while fecting the file names....",e);
			writeCoLog("Exception while fecting the file names....exception ---------"+e, coLogFile);
		}	
		return fileNameMap;
	}

	/**
	 * This method is used to fectch the file names by groupId
	 * @param Map<String,ArrayList<String>>
	 * @return  
	 * @throws 
	 */
	public Map<String,ArrayList<String>> fetchFileNamesByGroupId(Map<String,ArrayList<String>> fileNameMap){
		String folder = ftpProperty.getProperty(CoConstants.CO_PDF_FILES_PATH);;		
		try{
			File _folder = new File(folder);
			File[] filesInFolder = _folder.listFiles();
			for (File file : filesInFolder)
			{
				String fName = file.getName();
				try{
					String[] splitName = fName.split(CoConstants.UNDERSCORE);
					String caseNumAndGroupId = splitName[5];
					if(null!=fileNameMap && null!=fileNameMap.get(caseNumAndGroupId)){	        		
						fileNameMap.get(caseNumAndGroupId).add(fName);
					}else{
						ArrayList<String> nameList = new ArrayList<String>();
						nameList.add(fName);
						fileNameMap.put(caseNumAndGroupId, nameList);
					}
				}catch(Exception e){
					CoDebugger.debugInformation("Exception file name...."+fName);
					CoDebugger.debugException("Exception while fecting the file names...."+fName,e);
					writeCoLog("Exception while fecting the file names...."+fName+"exception ---------"+e, coLogFile);
				}
			}				
		}catch (Exception e) {
			CoDebugger.debugException("Exception while fecting the file names....",e);
		}	
		return fileNameMap;
	}

	/** 
	 * This method used to upload pdf files to FileNet 
	 * @param jobId
	 * @return retValue int
	 * @throws Exception
	 * @author rvelidandla
	 */
	protected int uploadPdfFilesToFileNet(String jobId) throws Exception {
		int resulFlag = 0;
		CoDebugger.debugInformation("uploadPdfFilesToFileNet ---> Before getting db connection");
		try {
			coConnection = tbc.getConnection();
			coConnection.setAutoCommit(false);
			coDAOServices.setConnection(coConnection);
		} catch (Exception e) {
			CoDebugger.debugInformation("uploadPdfFilesToFileNet ---> Failed to get db connection");
			CoDebugger.debugException(e.getMessage(), e);
			return this.handleBatchException(e, 1, null, null);
		}

		CoDebugger.debugInformation("uploadPdfFilesToFileNet ---> DB connection successful");

		CoDebugger.debugInformation("uploadPdfFilesToFileNet ---> Reading CORequestRecipients");
		CoRequestRecipientsCargo[] recArrar = null;
		try {
			recArrar = (CoRequestRecipientsCargo[]) coDAOServices.getRecordsForFileNetBatch();
			CoDebugger.debugInformation("uploadPdfFilesToFileNet ---> Reading CORequestRecipients Successful");
		} catch (Exception e) {
			CoDebugger.debugInformation("uploadPdfFilesToFileNet ---> Reading CORequestRecipients Failed");
			CoDebugger.debugException(e.getMessage(), e);
		}

		try {
			Properties fileNetProperty = FwProperty.getInstance().getProperties(CoConstants.FILENET_PROPERTIES);
			URL url = new URL(fileNetProperty.getProperty(CoConstants.FILE_NET_URL));

			FileNetWebServices2Service service = null;
			FileNetWebServices2Delegate delegate = null;
			CoDebugger.debugInformation("uploadPdfFilesToFileNet ---> Before getting FileNet connection");
			try {
				service = null;
				delegate = null;
				service = new FileNetWebServices2Service(url);
				delegate = service.getFileNetWebServices2Port();
			} catch (Exception e) {
				writeCoLog("Exception ------ " + e, coLogFile);
				CoDebugger.debugInformation("uploadPdfFilesToFileNet ---> Get FileNet connection FAILED");
				CoDebugger.debugException(e.getMessage(), e);	
				throw new CoException("uploadPdfFilesToFileNet ---> Exception while getting the FileNet connection " + e);
			}
			CoDebugger.debugInformation("uploadPdfFilesToFileNet ---> Get FileNet connection FAILED");

			int i = 0;
			if (null != recArrar && recArrar.length > 0) {
				CoDebugger.debugInformation("uploadPdfFilesToFileNet ---> Size of CoRequestRecipients = "+recArrar.length);
				readCount = recArrar.length;
				writeCoLog("File Count -------- " + readCount, coLogFile);

				int k = 0;

				CoDebugger.debugInformation("uploadPdfFilesToFileNet ---> Entering for loop");
				for (CoRequestRecipientsCargo recCargo : recArrar) {
					k++;					
					CoDebugger.debugInformation("uploadPdfFilesToFileNet ---> Total Counter: "+k);

					if (i == 100) {
						CoDebugger.debugInformation("uploadPdfFilesToFileNet ---> Reset FileNet Connection");
						try {
							service = null;
							delegate = null;
							service = new FileNetWebServices2Service(url);
							delegate = service.getFileNetWebServices2Port();
						} catch (Exception e) {
							writeCoLog("Exception ------ " + e, coLogFile);
							CoDebugger.debugInformation("uploadPdfFilesToFileNet ---> Reset FileNet Connection Failed");
							CoDebugger.debugException(e.getMessage(), e);
							throw new CoException("Exception while getting the FileNet connection " + e);
						}
						CoDebugger.debugInformation("uploadPdfFilesToFileNet ---> Reset FileNet Connection Success");
						i = 0;
					}					
					i++;

					String fileLocation = recCargo.getLocationPath();
					CoDebugger.debugInformation("uploadPdfFilesToFileNet ---> FileLocation = "+fileLocation);

					if (null != fileLocation && fileLocation.contains(".pdf")) {
						File file = new File(fileLocation);
						if (file.exists()) {
							try {
								String fName = file.getName();
								CoDebugger.debugInformation("uploadPdfFilesToFileNet ---> FileName = "+fName);
								String[] splitName = fName.split(CoConstants.UNDERSCORE);
								if (splitName != null && splitName.length >= 9) {
									String env = splitName[0];
									String docId = splitName[2];
									String docName = splitName[3];
									String caseNum = splitName[4];
									String providerId = null;
									if(caseNum.startsWith(CoConstants.MASS_MAIL_PROVIDER)){
										providerId = caseNum.substring(1);
										caseNum = CoConstants.MASS_MAIL_PROVIDER;
									}else if(caseNum.startsWith(CoConstants.MASS_MAIL_VENDOR)){
										providerId = caseNum.substring(1);
										caseNum = CoConstants.MASS_MAIL_VENDOR;
									}else{
										providerId = null;
										caseNum = splitName[4];
									}
									String groupId = splitName[5];
									String coReqSeqNum = splitName[6];
									String recSeqNum = splitName[7];
									String date = splitName[8];
									CoDebugger.debugInformation("uploadPdfFilesToFileNet ---> date: "+date);
									if(date.contains(CoConstants.PDF_FILE_EXTN)){
										date =  date.substring(0,  date.length()-4);
										CoDebugger.debugInformation("uploadPdfFilesToFileNet ---> After trimming date: "+date);
									}
									AddDocumentDT addDocumentDT = new AddDocumentDT();

									if (null != fileNetProperty.getProperty(CoConstants.APPLICATION_NAME)) {
										addDocumentDT.setApplicationName(fileNetProperty.getProperty(CoConstants.APPLICATION_NAME));
									} else {
										addDocumentDT.setApplicationName("SPACES-WP");
									}
									if (null != fileNetProperty.getProperty(CoConstants.APPLICATION_USER_ID)) {
										addDocumentDT.setApplicationUserId(fileNetProperty.getProperty(CoConstants.APPLICATION_USER_ID));
									} else {
										addDocumentDT.setApplicationUserId("FILENETBATCH");
									}
									addDocumentDT.setEnvironment(fileNetProperty.getProperty(CoConstants.FILE_NET_ENV));
									addDocumentDT.setFileNetUserId(fileNetProperty.getProperty(CoConstants.FILE_NET_USERID));
									addDocumentDT.setFileNetPassword(fileNetProperty.getProperty(CoConstants.FILE_NET_PASSWD));
									addDocumentDT.setObjectStoreName(fileNetProperty.getProperty(CoConstants.FILE_NET_OBJECT_STORE_NAME));
									addDocumentDT.setFolderName(fileNetProperty.getProperty(CoConstants.FILE_NET_FOLDER_NAME));
									addDocumentDT.setMajorVersion(fileNetProperty.getProperty(CoConstants.FILE_NET_MAJOR_VER));
									addDocumentDT.setDocumentClass(fileNetProperty.getProperty(CoConstants.FILE_NET_NOTICES_DOC_CLASS));
									addDocumentDT.setFileLocation(fileNetProperty.getProperty(CoConstants.FILE_NET_LOCATION));
									addDocumentDT.setDocumentTitle(fName);
									addDocumentDT.setFileName(fName);
									addDocumentDT.setMimeType(CoConstants.PDF);

									DataSource fds = new FileDataSource(file.getAbsolutePath());
									DataHandler handler = new DataHandler(fds);

									CoDebugger.debugInformation("uploadPdfFilesToFileNet ---> Got DataHandler & DataSource");

									String subjectTitle = docName.toUpperCase();
									addDocumentDT.setFileStream(handler);
									processAndAddPropertyDT(addDocumentDT, "String", "CaseNumber", caseNum);
									processAndAddPropertyDT(addDocumentDT, "String", "DocID", coReqSeqNum);
									processAndAddPropertyDT(addDocumentDT, "String", "SubjectTitle", subjectTitle);
									processAndAddPropertyDT(addDocumentDT, "date", "PrintDate", date);
									if(null!=providerId){
										processAndAddPropertyDT(addDocumentDT, "String", "ProviderID", providerId);
									}
									CoDebugger.debugInformation("uploadPdfFilesToFileNet ---> DocumentClass: "+addDocumentDT.getDocumentClass());
									CoDebugger.debugInformation("uploadPdfFilesToFileNet ---> DocumentTitle: "+fName);
									CoDebugger.debugInformation("uploadPdfFilesToFileNet ---> CaseNumber: "+caseNum);
									CoDebugger.debugInformation("uploadPdfFilesToFileNet ---> DocID: "+coReqSeqNum);
									CoDebugger.debugInformation("uploadPdfFilesToFileNet ---> SubjectTitle: "+subjectTitle);
									CoDebugger.debugInformation("uploadPdfFilesToFileNet ---> PrintDate: "+date);

									DocumentInfoDT infoReturn = delegate.addDocument(addDocumentDT);
									String documentId = infoReturn.getDocId();
									CoDebugger.debugInformation("uploadPdfFilesToFileNet ---> documentId--------"+documentId);

									writeCoLog("documentId--------" + documentId, coLogFile);
									/** Update pdf location */
									if (null != documentId) {										
										successCount++;
										CoDebugger.debugInformation("uploadPdfFilesToFileNet ---> SuccessCount:   " + successCount);
										coDAOServices.updateCoReqRecipientFileName(Integer.parseInt(coReqSeqNum), Integer.parseInt(recSeqNum), documentId);
										boolean success = coDAOServices.updateCoTempXml(Long.valueOf(coReqSeqNum), Long.valueOf(recSeqNum) ,null, CoConstants.IMGD, null);
										CoDebugger.debugInformation("uploadPdfFilesToFileNet ---> CoTempXml update " + success);
										coLogicalCommit();
									}else{
										failCount++;
										CoDebugger.debugInformation("uploadPdfFilesToFileNet ---> documentId-------- is null");
										writeException("Could not upload to filenet for job id:" + jobId, "Document id is null for fileName --------" + file.getName(), null, false);
									}

								}else{
									failCount++;
									CoDebugger.debugInformation("uploadPdfFilesToFileNet ---> FileName split < 9; fileName --> "+fName);
									writeException("Could not upload to filenet for jobId:" + jobId, "FileName split < 9 fileName --------" + file.getName(), null, false);
								}

							} catch (Exception e) {
								failCount++;
								CoDebugger.debugInformation("uploadPdfFilesToFileNet ---> FailureCount:   " + failCount);
								CoDebugger.debugInformation("uploadPdfFilesToFileNet ---> Exception fileName while processing --------" + file.getName());
								CoDebugger.debugException("Exception while uploading documents to FileNet", e);
								writeCoLog("Exception fileName --------" + file.getName() + "exception ---------" + e, coLogFile);
								writeException("Could not upload to filenet for jobId:" + jobId, "Exception fileName --------" + file.getName() + "exception ---------" + e, null, false);
							}
						} else {
							failCount++;
							CoDebugger.debugInformation("uploadPdfFilesToFileNet ---> File does not exist in NAS location fileName --------" + file.getName());
							writeCoLog("File not exits in NAS location fileName --------" + file.getName(), coLogFile);
							writeException("Could not udpate CO switches for jobId:" + jobId, "File not exist in NAS location fileName --------" + file.getName(), null, false);
						}
					}else{
						failCount++;
						CoDebugger.debugInformation("uploadPdfFilesToFileNet ---> File does not contain .pdf extension --------" + fileLocation);
						writeException("Could not upload files for jobId:" + jobId, "File does not contain .pdf extension --------" + fileLocation, null, false);
					}
				}
				CoDebugger.debugInformation("uploadPdfFilesToFileNet ---> Exiting for loop");

			} else {
				CoDebugger.debugInformation("uploadPdfFilesToFileNet ---> Size of CoRequestRecipients = 0");
			}

		} catch (Exception e) {
			resulFlag = -1;
			CoDebugger.debugInformation("uploadPdfFilesToFileNet ---> Exception "+e.getMessage());
			CoDebugger.debugException(e.getMessage(), e);
			writeException("Exception while getting the FileNet connection ", "Could not upload documents ...ABORTING JOB " + jobId, e, true);
			throw new CoException("Exception while getting the FileNet connection " + e);

		} finally {
			CoDebugger.debugInformation("uploadPdfFilesToFileNet ---> Generating Report Summary");
			generateSummaryReport(CoConstants.FILENET_BATCH__SUMMARY, readCount, successCount, failCount, blankNoticeCounter);
			CoDebugger.debugInformation("uploadPdfFilesToFileNet ---> Generating Report Summary Complete");
		}
		return resulFlag;
	}

	/** 
	 * This method used to move pdf files to email folder 
	 * @param jobId
	 * @return retValue int
	 * @throws Exception
	 * @author rvelidandla
	 */
	protected int moveEmailPdfFiles(String jobId) throws Exception {
		int resulFlag =0;
		try {
			coConnection = tbc.getConnection();
			coConnection.setAutoCommit(false);
			coDAOServices.setConnection(coConnection);
		} catch (Exception e) {
			return this.handleBatchException(e, 1, null, null);
		}		

		try {
			List<String> caseNumList = coDAOServices.getEmailCaseNums();
			if(null!=caseNumList && caseNumList.size()>0){
				String folder = ftpProperty.getProperty(CoConstants.CO_PDF_FILES_PATH);
				String desFolder = ftpProperty.getProperty(CoConstants.CO_EMAIL_PDF_FILES_PATH);
				File _folder = new File(folder);
				File[] filesInFolder = _folder.listFiles();
				for (File file : filesInFolder)
				{
					try{
						String fName = file.getName();
						String[] splitName = fName.split(CoConstants.UNDERSCORE);
						if(splitName!=null && splitName.length >= 9){	
							String docId = splitName[2];
							String caseNum = splitName[4];
							String coReqSeqNum = splitName[6];
							String coRptSeqNum = splitName[7];
							if(!CoConstants.ELECTTRONIC_SELECTION.equals(docId)){
								if(caseNumList.contains(caseNum)){
									readCount++;
									String oldPath = file.getAbsolutePath();
									boolean movefile = file.renameTo(new File(desFolder+fName));
									CoDebugger.debugInformation("moveEmailPdfFiles ---> oldPath ---> "+ oldPath);
									String newPath = "";
									try {
										File file1 = new File(desFolder+fName);
										if(file1.exists()){
											newPath = file1.getAbsolutePath();
											CoDebugger.debugInformation("moveEmailPdfFiles ---> newPath ---> "+ newPath);
											CoRequestRecipientsCargo[] coRequestRecipientsCargos = (CoRequestRecipientsCargo[]) coDAOServices.getCoRequestRecipient(Long.valueOf(coReqSeqNum), Long.valueOf(coRptSeqNum));
											if (coRequestRecipientsCargos != null && coRequestRecipientsCargos.length > 0 && coRequestRecipientsCargos[0] != null) {
												CoRequestRecipientsCargo coRequestRecipientsCargo = coRequestRecipientsCargos[0];
												if (coRequestRecipientsCargo != null && coRequestRecipientsCargo.getLocationPath() != null) {
													String locationPath = coRequestRecipientsCargo.getLocationPath();
													if(locationPath.equals(oldPath)){
														CoDebugger.debugInformation("moveEmailPdfFiles ---> updating location path for coReqSeq: "+coReqSeqNum+" and CoRptSeq: "+coRptSeqNum+" from: "+ oldPath+" to: "+newPath);
														CoRequestRecipientsCollection coReqRptCol = new CoRequestRecipientsCollection();
														coRequestRecipientsCargo.setLocationPath(newPath);
														coReqRptCol.setCargo(coRequestRecipientsCargo);
														coReqRptCol.update();
													}
												}
											}
										}
									} catch (Exception ex) {
										CoDebugger.debugInformation("moveEmailPdfFiles ---> Exception while updating location path for coReqSeq: "+coReqSeqNum+" and CoRptSeq: "+coRptSeqNum+" from: "+ oldPath+" to: "+newPath);
										CoDebugger.debugException(ex.getMessage(), ex);
									}
									boolean success = coDAOServices.updateCoTempXml(Long.valueOf(coReqSeqNum),Long.valueOf(coRptSeqNum) , null, null, CoConstants.EMAIL);
									CoDebugger.debugInformation("CoTempXml update    "+success);
									successCount++;
								}
							}
						}    		
					}catch (Exception e) {
						failCount++;
						CoDebugger.debugInformation("Exception fileName --------"+file.getName());
						CoDebugger.debugException("Exception while moving files to email folder",e);
						writeCoLog("Exception fileName --------"+file.getName()+"exception ---------"+e, coLogFile);
					}				
				}
			}

		} catch (Exception e) {	
			resulFlag = -1;
			writeException(
					"Exception while moving pdf files to email folder",
					"Could not upload documents ...ABORTING JOB "+ jobId ,
					e,
					true); 
			throw new CoException(		
					"Exception while moving pdf files to email folder " + e);			

		}finally {
			/**generate the summary report*/
			generateSummaryReport(
					CoConstants.EMAIL_BATCH_SUMMARY,
					readCount, successCount,
					failCount, blankNoticeCounter);			
		}

		return resulFlag;
	}


	private void processAndAddPropertyDT(AddDocumentDT requestDocumentDT, String propertyDTType, String propertyDTName, String propertyDTValue){
		PropertyDT oPropertyDT = new PropertyDT();
		oPropertyDT.setDatatype(propertyDTType);
		if(("date").equalsIgnoreCase(propertyDTType)){
			oPropertyDT.setDateFormat("MMddyyyy");
		}
		oPropertyDT.setName(propertyDTName);
		oPropertyDT.setValue(propertyDTValue);
		requestDocumentDT.getProperties().add(oPropertyDT);
	}

	/**
	 * 
	 * @param Timestamp
	 * @return Timestamp
	 * 
	 */
	public Timestamp getNextWorkingDay(Timestamp ts){		
		Timestamp nextDay = null;
		try{
			nextDay = ALSOPUtil.addDays(ts, 1);
			nextDay = ALSOPUtil.adjustToWorkingDay(nextDay, 1);			
			CoDebugger.debugMessage("Next working day --- "+nextDay.toString());
		}catch(Exception e){
			CoDebugger.debugException("Exception while getting next business day", e);
		}
		return nextDay;		
	}

	/**
	 * 
	 * @param docId
	 * @return
	 */
	public Timestamp getAdequateNoticeDate(String docId, java.util.Date asOfDate) throws CoException {
		Timestamp adequateDate = null;
		CoDebugger.debugInformation("DocId ---> " + docId);
		CoDebugger.debugInformation("AsOfDate ---> " + asOfDate);
		try {
			if (docId != null && docId.equalsIgnoreCase(CoConstants.NDHCREN17)) {
				/**ND-15934: BEGIN*/
				String code = "";
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(asOfDate);
				calendar.add(Calendar.MONTH, 1);
				java.util.Date adjustedDate = calendar.getTime();
				CoDebugger.debugInformation("AdjustedDate ---> " + adjustedDate);
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMM");
				code = dateFormat.format(adjustedDate);
				CoDebugger.debugInformation("CODE ---> " + code);
				RefTableData refTableRows[] = null;
				RefTableData refTableRow = new RefTableData();
				refTableRows = ReferenceTableAccess.getRefTableData(CoConstants.REF_TABLE_EDNEGACTIONDATE, CoConstants.CODE, code);
				if(refTableRows != null && refTableRows.length>0 && refTableRows[0] != null && refTableRows[0].getRefrTableAddiData() != null && refTableRows[0].getRefrTableAddiData().get("NEGACTIONDATE") != null){
					refTableRow = refTableRows[0];
					SimpleDateFormat sdfSource = new SimpleDateFormat("yyyy-MM-dd");
					String negActionDate = (String) refTableRow.getRefrTableAddiData().get("NEGACTIONDATE");
					CoDebugger.debugInformation("NEGACTIONDATE ---> " + negActionDate);
					java.util.Date date = sdfSource.parse(negActionDate);
					adequateDate = new java.sql.Timestamp(date.getTime());
				}else{
					CoDebugger.debugInformation("Failed to fecth RefTableData from EDNEGACTIONDATE for CODE ---> " + code);
				}
				/**ND-15934: BEGIN*/
			} else if(docId != null && (docId.equalsIgnoreCase(CoConstants.NDHCGNF15) || docId.equalsIgnoreCase(CoConstants.NDTNFN116))){
				String code = "";
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMM");
				code = dateFormat.format(asOfDate);
				CoDebugger.debugInformation("CODE ---> " + code);
				RefTableData refTableRows[] = null;
				RefTableData refTableRow = new RefTableData();
				refTableRows = ReferenceTableAccess.getRefTableData(CoConstants.REF_TABLE_EDADEQUATENOTICEDATE, CoConstants.CODE, code);
				if(refTableRows != null && refTableRows.length>0 && refTableRows[0] != null && refTableRows[0].getRefrTableAddiData() != null && refTableRows[0].getRefrTableAddiData().get("NEGACTIONDATE") != null){
					refTableRow = refTableRows[0];
					SimpleDateFormat sdfSource = new SimpleDateFormat("yyyy-MM-dd");
					String negActionDate = (String) refTableRow.getRefrTableAddiData().get("NEGACTIONDATE");
					CoDebugger.debugInformation("NEGACTIONDATE ---> " + negActionDate);
					java.util.Date date = sdfSource.parse(negActionDate);
					adequateDate = new java.sql.Timestamp(date.getTime());
				}else{
					CoDebugger.debugInformation("Failed to fecth RefTableData from EDNEGACTIONDATE for CODE ---> " + code);
				}
			}else{
				String description = ReferenceTableAccess.getRefDescription(docId, CoConstants.REF_TABLE_COMLYNOTICEDAY);
				if (description != null && description.length() > 0) {
					String[] array = description.split("\\-");
					if (array != null && array.length > 1) {
						String day = array[0];
						String action = array[1];
						if (action != null && action.length() > 0) {
							int dayNumber = Integer.parseInt(day);
							if (("LWD").equalsIgnoreCase(action)) {
								adequateDate = coDAOServices.getNthLastBusinessDay(dayNumber, asOfDate);
							} else if (("FWD").equalsIgnoreCase(action)) {
								adequateDate = coDAOServices.getNthFirstBusinessDay(dayNumber, asOfDate);
							} else if (("LD").equalsIgnoreCase(action)) {
								adequateDate = coDAOServices.getNthLastDayOfMonth(dayNumber, asOfDate);
							} else if (("FD").equalsIgnoreCase(action)) {
								adequateDate = coDAOServices.getNthFirstDayOfMonth(dayNumber, asOfDate);
							}
						}
					}
				}
			}
			CoDebugger.debugInformation("Adequate Notice Day for docId ---> " + docId + " is ---> " + ((null!=adequateDate)?adequateDate.toString():CoConstants.EMPTY_STRING));
		} catch (Exception e) {
			CoDebugger.debugException("Exception while getting adequate notice day for DocId ---> "+ docId, e);
			throw new CoException("Exception while getting adequate notice day for DocId ---> "+ docId);	
		}
		return adequateDate;
	}



	/**
	 * generates the report take care that the headline passed to this method
	 * does not exceed 120 char - the DB column limit Should not be over-riden
	 * in the sub-class as it is not required
	 * 
	 * @param1 code
	 * @param2[0] readCount/successCount
	 * @param2[1] successCount/failureCount
	 * @param2[2] failureCount/headline
	 * @param2[3] headline
	 */
	protected void generateSummaryReport(String code,
			long readCount, long successCount, long failureCount, long blankNoticeCount) {
		List<String> reportLines = new ArrayList<String>();
		List<String> columnTypes = new ArrayList<String>();
		try {
			columnTypes.add(tbc.formatString(FwBatchConstants.REPORT_TITLE, 20));
			columnTypes.add(tbc.formatString(FwBatchConstants.REPORT_BODY, 20));
			columnTypes.add(tbc.formatString(FwBatchConstants.REPORT_BODY, 20));
			columnTypes.add(tbc.formatString(FwBatchConstants.REPORT_BODY, 20));
			columnTypes.add(tbc.formatString(FwBatchConstants.REPORT_BODY, 20));
			if(CoConstants.NOTICE_BATCH_SUMMARY.equals(code)){
				/** For all other CO-Notice Batch Jobs */
				reportLines.add(CoConstants.HEADLINE + docName
						+ CoConstants.COLON);
				reportLines.add(formatReportLine(
						CoConstants.NOTICE_MESSAGE1,
						String.valueOf(readCount)));
				reportLines.add(formatReportLine(
						CoConstants.NOTICE_MESSAGE2,
						String.valueOf(successCount)));
				reportLines.add(formatReportLine(
						CoConstants.NOTICE_MESSAGE3,
						String.valueOf(failureCount)));
				reportLines.add(formatReportLine(
						CoConstants.NOTICE_MESSAGE4,						
						String.valueOf(blankNoticeCount)));
			}else if(CoConstants.TRIGGER_BATCH_SUMMARY.equals(code)){
				/** For all Trigger Batch Jobs */
				reportLines.add(CoConstants.HEADLINE + docName
						+ CoConstants.COLON);
				reportLines.add(formatReportLine(
						CoConstants.TRIGGER_MESSAGE1,
						String.valueOf(readCount)));
				reportLines.add(formatReportLine(
						CoConstants.TRIGGER_MESSAGE2,
						String.valueOf(successCount)));
				reportLines.add(formatReportLine(
						CoConstants.TRIGGER_MESSAGE3,
						String.valueOf(failureCount)));
			}else if(CoConstants.UPDATE_BATCH_SUMMARY.equals(code)){
				reportLines.add(CoConstants.UPDATE_BATCH_HEADLINE);
				reportLines.add(formatReportLine(
						CoConstants.UPDATE_BATCH_MESSAGE1,
						String.valueOf(readCount)));
				reportLines.add(formatReportLine(
						CoConstants.UPDATE_BATCH_MESSAGE2,
						String.valueOf(successCount)));
				reportLines.add(formatReportLine(
						CoConstants.UPDATE_BATCH_MESSAGE3,
						String.valueOf(failureCount)));
			}else if(CoConstants.FILENET_BATCH__SUMMARY.equals(code)){
				reportLines.add(CoConstants.UPDATE_BATCH_FILENET_HEADLINE);
				reportLines.add(formatReportLine(
						CoConstants.UPDATE_BATCH_FILENET_MESSAGE1,
						String.valueOf(readCount)));
				reportLines.add(formatReportLine(
						CoConstants.UPDATE_BATCH_FILENET_MESSAGE2,
						String.valueOf(successCount)));
				reportLines.add(formatReportLine(
						CoConstants.UPDATE_BATCH_FILENET_MESSAGE3,
						String.valueOf(failureCount)));
			}else if(CoConstants.PBOWS_BATCH_SUMMARY.equals(code)){
				reportLines.add(CoConstants.PBOWS_HEADLINE);
				reportLines.add(formatReportLine(
						CoConstants.PBOWS_MESSAGE1,
						String.valueOf(readCount)));
				reportLines.add(formatReportLine(
						CoConstants.PBOWS_MESSAGE2,
						String.valueOf(successCount)));
				reportLines.add(formatReportLine(
						CoConstants.PBOWS_MESSAGE3,
						String.valueOf(failureCount)));
			}else if(CoConstants.EMAIL_BATCH_SUMMARY.equals(code)){
				reportLines.add(CoConstants.EMAIL_HEADLINE);
				reportLines.add(formatReportLine(
						CoConstants.EMAIL_MESSAGE1,
						String.valueOf(readCount)));
				reportLines.add(formatReportLine(
						CoConstants.EMAIL_MESSAGE2,
						String.valueOf(successCount)));
				reportLines.add(formatReportLine(
						CoConstants.EMAIL_MESSAGE3,
						String.valueOf(failureCount)));
			}else if(CoConstants.TOP_IN_BATCH_SUMMARY.equals(code)){
				reportLines.add(CoConstants.HEADLINE + docName
						+ CoConstants.COLON);
				reportLines.add(formatReportLine(
						CoConstants.TOP60_NOTICE_MESSAGE1,
						String.valueOf(readCount)));
				reportLines.add(formatReportLine(
						CoConstants.TOP60_NOTICE_MESSAGE2,
						String.valueOf(successCount)));
				reportLines.add(formatReportLine(
						CoConstants.TOP60_NOTICE_MESSAGE3,
						String.valueOf(failureCount)));
			}

			/** generate summary only when condition met */
			if (reportLines.size() > 0) {

				String[] repArr = reportLines.toArray(new String[reportLines
				                                                 .size()]);

				String[] colArr = columnTypes.toArray(new String[columnTypes
				                                                 .size()]);

				tbc.generateReport(repArr, colArr);
			}
		} catch (Exception e) {
			CoDebugger
			.debugException(
					"Exception while writing summary to FW_BATCH_SUMMARY table",
					e);
		}
	}


	/**
	 * This method formats a String to a required length by padding with
	 * trailing White Space character. 
	 * @param line
	 * @param value
	 * @return
	 * @throws Exception
	 */
	private String formatReportLine(String line, String value) throws Exception {
		StringBuffer reportLine = new StringBuffer();
		reportLine.append(tbc.formatString(line.trim(),80));
		reportLine.append(tbc.formatString(value.trim(),10));
		return reportLine.toString();
	}


	protected int processCoBatchForm1095B(String jobId) throws Exception {

		VCoRequestCargo[] coRequestCargos = null;
		CoRequestRecipientsCargo[] coReqRptCargos = null;
		CoAssembler assembler = null;
		COCorrespondence coCorrespondence = new COCorrespondence();
		long processedRecords = 0;
		java.util.Date parsedDate = null;
		String generateDate = "";
		String strDt = null;
		docIds = CoConstants.NDMAELF23;
		docName = "Form 1095B";

		coCorrespondence.setAsOfDate(CorrespondenceServices.getYYYYMMDDFormattedDateStr(asOfDate, CoBatchConstants.asOfDtMMDDYYYY));

		try {
			coConnection = tbc.getConnection();
			coConnection.setAutoCommit(false);
			coDAOServices.setConnection(coConnection);
		} catch (Exception e) {
			return this.handleBatchException(e, 1, null, null);
		}

		try {
			SimpleDateFormat sdfSource = new SimpleDateFormat("MM/dd/yyyy");
			SimpleDateFormat sdfSource1 = new SimpleDateFormat("dd-MMM-yyyy");
			try {
				parsedDate = sdfSource.parse(asOfDate);
			} catch (Exception e) {
				throw new CoException("processCoBatchForm1095B ---> Error populating as of Date sdfSource.parse" + e, ILog.CRITICAL);
			}
			try {
				strDt = (new SimpleDateFormat("yyyy-MM-dd").format(parsedDate));
				generateDate = sdfSource1.format(parsedDate);
			} catch (Exception e) {
				throw new CoException("processCoBatchForm1095B ---> Error populating as of Date - new SimpleDateFormat....format(parsedDate)).toString()" + e, ILog.CRITICAL);
			}
		} catch (Exception e) {
			return this.handleBatchException(e, 1, null, null);
		}

		try {
			try {
				processBulkTriggersForFor1095B(parsedDate);
			} catch (Exception ex) {
				CoDebugger.debugInformation("processCoBatchForm1095B ---> Exception while creating triggers for jobId ---> " + jobId);
				CoDebugger.debugException(ex.getMessage(), ex);
				writeException("processCoBatchForm1095B ---> Exception while creating triggers for jobId ---> " + jobId, ex.getMessage(), ex, false);
				throw new CoException("processCoBatchForm1095B ---> Exception while creating triggers ---> " + ex);
			}
			try {
				try {
					CoDebugger.debugInformation("processCoBatchForm1095B ---> BEGIN: Update REASON_CD_LIST = Y For Non History Triggers");
					tbc.savepoint(null, "CoSavePoint");
					coDAOServices.updatePendingTriggerCountFor1095B(generateDate);
					CoDebugger.debugInformation("processCoBatchForm1095B ---> END: Update REASON_CD_LIST = Y For Non History Triggers ---> SUCCESS");
					coLogicalCommit();
				} catch (Exception e1) {
					tbc.rollback(null, "CoSavePoint");
					CoDebugger.debugInformation("processCoBatchForm1095B ---> END: Update REASON_CD_LIST = Y For Non History Triggers ---> FAILED");
					CoDebugger.debugException(e1.getMessage(), e1);
					throw new CoException("processCoBatchForm1095B ---> Exception while Updating REASON_CD_LIST = Y For Non History Triggers "+e1);
				}

				try {
					CoDebugger.debugInformation("processCoBatchForm1095B ---> BEGIN: Get Pending Non Processed Trigger Count");
					readCount = ((CoRequestHistoryCargo[]) coDAOServices.getPendingTriggerCountForForm1095B(generateDate))[0].getCoReqSeq();
					CoDebugger.debugInformation("processCoBatchForm1095B ---> END: Get Pending Non Processed Trigger Count ---> SUCCESS");
					CoDebugger.debugInformation("processCoBatchForm1095B ---> Pending Non Processed Trigger Count ---> " + readCount);
				} catch (Exception e1) {
					CoDebugger.debugInformation("processCoBatchForm1095B ---> END: Get Pending Non Processed Trigger Count ---> FAILED");
					CoDebugger.debugException(e1.getMessage(), e1);
					throw new CoException("processCoBatchForm1095B ---> Exception while Getting Pending Non Processed Trigger Count "+e1);
				}

				boolean flag = false;
				if (readCount > 0 && readCount > (successCount + failCount)) {
					flag = true;
				}

				while (flag) {
					CoDebugger.debugInformation("processCoBatchForm1095B ---> Pending Non Processed Trigger Count ---> " + readCount);
					CoDebugger.debugInformation("processCoBatchForm1095B ---> Pending Processed Success Trigger Count ---> " + successCount);
					CoDebugger.debugInformation("processCoBatchForm1095B ---> Pending Processed Failure Trigger Count ---> " + failCount);
					flag = false;
					try {
						coRequestCargos = (VCoRequestCargo[]) coDAOServices.getPendingTriggersForBatchRequest(CoConstants.NDMAELF23, "0", "0", strDt);
					} catch (Exception e) {
						CoDebugger.debugInformation("processCoBatchForm1095B ---> Exception getting pending triggers ---> FAILED");
						CoDebugger.debugException(e.getMessage(), e);
						throw new CoException("processCoBatchForm1095B ---> Exception getting pending triggers... " + e);
					}
					if ((coRequestCargos == null) || (coRequestCargos != null && coRequestCargos.length == 0)) {
						break;
					} else {
						if (coLogFile != null) {
							writeCoLog("Total no of records - " + coRequestCargos.length, coLogFile);
						}

						for (VCoRequestCargo coRequestCargo : coRequestCargos) {
							try {
								if (!isReprintRequest(coRequestCargo.getT2CoReqSeq())) {
									tbc.savepoint(null, "CoSavePoint");
									COPrintPreviewHelper.mapCargoToCo(coRequestCargo, coCorrespondence);

									long coReqSeq = ((VCoRequestCargo) coRequestCargo).getT2CoReqSeq();
									coReqRptCargos = getCoReqRecipientsForReqSeq(coReqSeq);

									if (coLogFile != null) {
										writeCoLog("case Num  - " + coCorrespondence.getApptId() + "   -    No of recipients for  - " + coReqRptCargos.length, coLogFile);
									}

									for (CoRequestRecipientsCargo coRequestRecipientsCargo : coReqRptCargos) {
										long coRptSeq = coRequestRecipientsCargo.getCoRptSeq();

										String recipientData = coRequestRecipientsCargo.getRecipientData();
										String recipientName = recipientData.substring(recipientData.indexOf("|") + 1, recipientData.indexOf("-")).trim();
										recipientName = recipientName.replaceAll("[^a-zA-Z ]", "").replaceAll(" ", "-");
										String xmlFileName = getXMLFileNameForNotice(coCorrespondence.getDocId(), coCorrespondence.getDocName(), String.valueOf(coCorrespondence.getApptId()),
												coReqSeq, coRptSeq, coRequestCargo.getGenerateDt());
										xmlFileName = xmlFileName.substring(0, xmlFileName.length() - 4) + "_" + recipientName + CoConstants.XML_FILE_EXTN;
										docName = coCorrespondence.getDocName();
										coCorrespondence.setFileLocation(xmlFileName);
										coCorrespondence.setCoRptSeq(coRptSeq);
										coCorrespondence.setJobId(jobId);
										coCorrespondence.setDocName(coRequestCargo.getDocName());

										assembler = getHandleForAssembler();

										try {
											assembler.genarateDocument(coCorrespondence, 0);
											processedRecords++;
											successCount++;
										} catch (Exception e) {
											CoDebugger.debugException(e.getMessage(), e);
											throw new CoException("processCoBatchForm1095B ---> Exception while creating correspondence coReqSeq ---> " + coCorrespondence.getCoReqSeq()
													+ " job for Job-ID --> " + jobId, ILog.CRITICAL);
										}
									}
									coLogicalCommit();
								}
								if (coLogFile != null) {
									writeCoLog("Processed records - " + processedRecords, coLogFile);
								}
							} catch (CoException e) {
								failCount++;
								tbc.rollback(null, "CoSavePoint");
								writeException("processCoBatchForm1095B ---> Exception while invoking assembler for job:" + jobId, "Could not invoke assembler to generate XML file for coReqSeq:"
										+ coCorrespondence.getCoReqSeq(), e, false);
							} catch (Exception e) {
								tbc.rollback(null, "CoSavePoint");
								writeException("processCoBatchForm1095B --->  Exception while invoking assembler for job:" + jobId, "Could not invoke assembler to generate XML file for coReqSeq:"
										+ coCorrespondence.getCoReqSeq(), e, false);
							}
						}
					}
					if (readCount > 0 && readCount > (successCount + failCount)) {
						flag = true;
					}
				}

			} catch (Exception e) {
				CoDebugger.debugInformation("processCoBatchForm1095B ---> Exception while fetching triggers and generating correspondence for ---> "+jobId);
				CoDebugger.debugException(e.getMessage(), e);
				writeException("processCoBatchForm1095B ---> Exception while fetching triggers and generating correspondence", "jobId ---> " + jobId, e, false);				
				throw new CoException("processCoBatchForm1095B ---> Exception while fetching triggers and generating correspondence for ---> "+jobId);
			}
		} catch (Exception ex1) {
			CoDebugger.debugInformation("processCoBatchForm1095B ---> Exception in processCoBatchForm1095B");
			CoDebugger.debugException(ex1.getMessage(), ex1);
			writeException("processCoBatchForm1095B ---> Exception in processCoBatchForm1095B ", ex1.getMessage() + jobId, ex1, false);
			throw new CoException("processCoBatchForm1095B ---> Exception in processCoBatchForm1095B");
		} finally {
			/** generate the summary report */
			CoDebugger.debugInformation(CoConstants.TRIGGER_BATCH_SUMMARY + " ReadCount ---> " + recordCounter);
			CoDebugger.debugInformation(CoConstants.TRIGGER_BATCH_SUMMARY + " SuccessCount ---> " + successCounter);
			CoDebugger.debugInformation(CoConstants.TRIGGER_BATCH_SUMMARY + " FailureCount ---> " + failureCounter);
			CoDebugger.debugInformation(CoConstants.NOTICE_BATCH_SUMMARY + " ReadCount ---> " + readCount);
			CoDebugger.debugInformation(CoConstants.NOTICE_BATCH_SUMMARY + " SuccessCount ---> " + successCount);
			CoDebugger.debugInformation(CoConstants.NOTICE_BATCH_SUMMARY + " FailureCount ---> " + failCount);
			generateSummaryReport(CoConstants.TRIGGER_BATCH_SUMMARY, recordCounter, successCounter, failureCounter, CoConstants.NOTICE_BATCH_SUMMARY, readCount, successCount, failCount, blankNoticeCounter);
		}
		return 0;
	}


	/**
	 * This method does the essential bulk processing needed to create 
	 * 1095B correspondence triggers in bulk.
	 * @param parsedDate
	 * @return
	 * @throws Exception
	 */
	protected boolean processBulkTriggersForFor1095B(java.util.Date parsedDate) throws Exception {

		In1095bNormalizedCustomCargo[] in1095bNormalizedCustomCargos = null;

		SimpleDateFormat df = new SimpleDateFormat("yyyy");
		long applicableYear = Long.parseLong(df.format(parsedDate)) - 1;
		boolean result = false;
		try {

			CoDebugger.debugInformation("BEGIN: FETCHING in1095bNormalizedCustomCargos");
			in1095bNormalizedCustomCargos = coDAOServices.getIn1095bNormalizedCustomCargoForBulkTriggers(applicableYear);
			CoDebugger.debugInformation("END: FETCHING in1095bNormalizedCustomCargos -----------> SUCCESS");

			if (in1095bNormalizedCustomCargos != null && in1095bNormalizedCustomCargos.length > 0) {
				recordCounter = in1095bNormalizedCustomCargos.length;
				try {
					tbc.savepoint(null, "CoSavePoint");
					result = coDAOServices.createBulkTriggersForFor1095B(jobId, in1095bNormalizedCustomCargos, "\'N\'", applicableYear);
					successCounter = recordCounter;
					coLogicalCommit();
					tbc.commitTransaction();
				} catch (Exception e) {
					tbc.rollback(null, "CoSavePoint");
					failureCounter = recordCounter;
					CoDebugger.debugException(e.getMessage(), e);
					result = false;
					throw new CoException("Exception while creating correspondence trigger for Form1095B");
				}
			} 
			CoDebugger.debugInformation("Expected Number of new triggers ---> " + recordCounter);
			CoDebugger.debugInformation("Triggers Successfully Created Counter ---> " + successCounter);
			CoDebugger.debugInformation("Failed to Create Triggers Counter ---> " + failureCounter);
			docName = "Form 1095B";
		} catch (Exception e) {
			CoDebugger.debugException(e.getMessage(), e);
			throw new CoException("Exception while creating correspondence trigger for Form1095B");
		}
		return result;
	}

	/**
	 * generates the report take care that the headline passed to this method
	 * does not exceed 120 char - the DB column limit Should not be over-riden
	 * in the sub-class as it is not required
	 * 
	 * @param1 code
	 * @param2[0] readCount/successCount
	 * @param2[1] successCount/failureCount
	 * @param2[2] failureCount/headline
	 * @param2[3] headline
	 */

	protected void generateSummaryReport(String code1, long readCount1, long successCount1, long failureCount1, String code2, long readCount2, long successCount2, long failureCount2, long blankNoticeCount) {
		List<String> reportLines = new ArrayList<String>();
		List<String> columnTypes = new ArrayList<String>();
		try {
			columnTypes.add(tbc.formatString(FwBatchConstants.REPORT_TITLE, 20));
			columnTypes.add(tbc.formatString(FwBatchConstants.REPORT_BODY, 20));
			columnTypes.add(tbc.formatString(FwBatchConstants.REPORT_BODY, 20));
			columnTypes.add(tbc.formatString(FwBatchConstants.REPORT_BODY, 20));
			columnTypes.add(tbc.formatString(FwBatchConstants.REPORT_BODY, 20));
			columnTypes.add(tbc.formatString(FwBatchConstants.REPORT_TITLE, 20));
			columnTypes.add(tbc.formatString(FwBatchConstants.REPORT_BODY, 20));
			columnTypes.add(tbc.formatString(FwBatchConstants.REPORT_BODY, 20));
			columnTypes.add(tbc.formatString(FwBatchConstants.REPORT_BODY, 20));

			reportLines.add(CoConstants.HEADLINE + docName + CoConstants.COLON);
			if (code1 != null && CoConstants.TRIGGER_BATCH_SUMMARY.equals(code1)) {
				// For all Trigger Batch Jobs
				reportLines.add(formatReportLine(CoConstants.TRIGGER_BATCH_SUMMARY + " ---> " + CoConstants.TRIGGER_MESSAGE1, String.valueOf(readCount1)));
				reportLines.add(formatReportLine(CoConstants.TRIGGER_BATCH_SUMMARY + " ---> " + CoConstants.TRIGGER_MESSAGE2, String.valueOf(successCount1)));
				reportLines.add(formatReportLine(CoConstants.TRIGGER_BATCH_SUMMARY + " ---> " + CoConstants.TRIGGER_MESSAGE3, String.valueOf(failureCount1)));
			}
			
			if (code2 != null && CoConstants.NOTICE_BATCH_SUMMARY.equals(code2)) {
				// For all other CO-Notice Batch Jobs
				reportLines.add(formatReportLine(CoConstants.NOTICE_BATCH_SUMMARY + "  ---> " + CoConstants.NOTICE_MESSAGE1, String.valueOf(readCount2)));
				reportLines.add(formatReportLine(CoConstants.NOTICE_BATCH_SUMMARY + "  ---> " + CoConstants.NOTICE_MESSAGE2, String.valueOf(successCount2)));
				reportLines.add(formatReportLine(CoConstants.NOTICE_BATCH_SUMMARY + "  ---> " + CoConstants.NOTICE_MESSAGE3, String.valueOf(failureCount2)));
				reportLines.add(formatReportLine(CoConstants.NOTICE_BATCH_SUMMARY + "  ---> " + CoConstants.NOTICE_MESSAGE4, String.valueOf(blankNoticeCount)));
			}

			/**ND-99344 Reminder to report Changes Alert 502 Summary*/
			if (code1 != null && CoConstants.ALERT_502_BATCH_SUMMARY.equals(code1)) {
				reportLines.add(formatReportLine(CoConstants.ALERT_502_BATCH_SUMMARY + " ---> " + CoConstants.ALERT_502_NOTICE_MESSAGE1, String.valueOf(readCount1)));
				reportLines.add(formatReportLine(CoConstants.ALERT_502_BATCH_SUMMARY + " ---> " + CoConstants.ALERT_502_NOTICE_MESSAGE2, String.valueOf(successCount1)));
				reportLines.add(formatReportLine(CoConstants.ALERT_502_BATCH_SUMMARY + " ---> " + CoConstants.ALERT_502_NOTICE_MESSAGE3, String.valueOf(failureCount1)));
			}
			
			if (reportLines.size() > 0) {

				String[] repArr = reportLines.toArray(new String[reportLines.size()]);

				String[] colArr = columnTypes.toArray(new String[columnTypes.size()]);

				tbc.generateReport(repArr, colArr);
			}
		} catch (Exception e) {
			CoDebugger.debugException("Exception while writing summary to FW_BATCH_SUMMARY table", e);
		}
	}

	/**
	 * 
	 * @param reminderToReportChanges
	 * @param edEligibilityCargos
	 * @param caseNum
	 * @throws CoException 
	 */
	private String identifyACAOnlyMembers(long caseNum) {
		CoDebugger.debugInformation("--> Entered identifyACAOnlyMembers --->CaseNUM"+caseNum);
		List<Long> edgNumList = new ArrayList<Long>();
		String acaFlag=null;

		//ADDED for Reminder to Report Changes
		EdEligibilityCargo[] cargosEdgNumArray = null;
		VEdEligibilityIndvCargo[] vEdEligibilityIndvArr = null;
		try{
			cargosEdgNumArray=(EdEligibilityCargo[])coDAOServices.getEdgesByCaseNum(caseNum);
			CoDebugger.debugInformation("identifyACAOnlyMembers ---> Entered cargosEdgNumArray Block --->");
		} catch (CoException e) {
			CoDebugger.debugInformation("identifyACAOnlyMembers ---> Exception while fetching cargosEdgNumArray --->");
			CoDebugger.debugException(e.getMessage(), e);
		}

		if(cargosEdgNumArray!=null && cargosEdgNumArray.length>0){
			CoDebugger.debugInformation("identifyACAOnlyMembers ---> cargosEdgNumArray!=null && cargosEdgNumArray.length>0 --->");
			StringBuilder edgNums = new StringBuilder();
			for(int i = 0; i<cargosEdgNumArray.length; i++){
				if(!edgNumList.contains(cargosEdgNumArray[i].getEdgNum())){
					CoDebugger.debugInformation("identifyACAOnlyMembers ---> cargosEdgNumArray[i].getEdgNum() --->"+cargosEdgNumArray[i].getEdgNum());
					edgNumList.add(cargosEdgNumArray[i].getEdgNum());
					edgNums.append(String.valueOf(cargosEdgNumArray[i].getEdgNum()));
					if (!((i + 1) == cargosEdgNumArray.length)){
						edgNums.append(CoConstants.COMMA);
					}
				}
			}
			//ND-54086 Unit Test
			if(null!=edgNums && edgNums.charAt(edgNums.length()- 1)==','){
				edgNums.deleteCharAt(edgNums.length() - 1).toString();
			}

			CoDebugger.debugInformation("identifyACAOnlyMembers --->edgNums --->"+edgNums.toString());
			try {
				vEdEligibilityIndvArr = coDAOServices.getAllIndividualsDueForReview(edgNums.toString(),caseNum);
			} catch (NoDataFoundException e) {
				CoDebugger.debugException("identifyACAOnlyMembers.identifyACAOnlyMembers -->NoDataFoundException in VEdEligibilityIndv", e);
			} catch (CoException e) {
				CoDebugger.debugException("identifyACAOnlyMembers.identifyACAOnlyMembers -->CoException in VEdEligibilityIndv", e);
			}

			if(null!=vEdEligibilityIndvArr && vEdEligibilityIndvArr.length>0){
				CoDebugger.debugInformation("identifyACAOnlyMembers --->null!=vEdEligibilityIndvArr && vEdEligibilityIndvArr.length>0 --->"+vEdEligibilityIndvArr);
				for(VEdEligibilityIndvCargo toaCheck: vEdEligibilityIndvArr){
					String toaCdDesc = CoConstants.EMPTY_STRING;
					try {
						toaCdDesc = ReferenceTableAccess.getRefDescription(toaCheck.getTypeOfAssistanceCd(), "EDTOA");
						CoDebugger.debugInformation("identifyACAOnlyMembers ---> ReferenceTableAccess toaCheck.getTypeOfAssistanceCd() EDTOA"+toaCdDesc);
					} catch (NoDataFoundException e) {
						CoDebugger.debugException("identifyACAOnlyMembers.identifyACAOnlyMembers -->NoDataFoundException in EDTOA", e);
					}
					if(null!=toaCdDesc && toaCdDesc.contains(CoConstants.STRING_ACA)){
						CoDebugger.debugInformation("identifyACAOnlyMembers ---> ACAflag=CoConstants.YES_STRING_Y");
						acaFlag=CoConstants.YES_STRING_Y;
					}
					if(null!=toaCdDesc && !toaCdDesc.contains(CoConstants.STRING_ACA)){
						CoDebugger.debugInformation("identifyACAOnlyMembers ---> ACAflag=CoConstants.NO_STRING_N");
						acaFlag=CoConstants.NO_STRING_N;
						break;
					}
				}
			}
		}
		return acaFlag;
	}
	//ND-58777 Changes for cases are flagged for change reporting-BEGIN
	
	/**
	 * getACACaseNumList
	 * @param dcCaseProgramCargo
	 * @return
	 * @throws CoException 
	 */
	private List<Long> getACACaseNumList(Set<EdEligibilityCargo> acaOnlySet) throws CoException{
		
		CoDebugger.debugInformation("ACA ONLY :Entered getACACaseNumList to Return Only CaseNUMs");
		List<Long> triggerCaseNums = new ArrayList<Long>();
		Set<Long> triggerCaseNumSet = new HashSet<Long>();
		
		if(null!=acaOnlySet && acaOnlySet.size()>0){
			for(EdEligibilityCargo cargo:acaOnlySet){
				long caseNum=cargo.getCaseNum();
				if(triggerCaseNumSet.add(caseNum)){
					triggerCaseNums.add(caseNum);
				}
			}
		}
		CoDebugger.debugInformation("EXIT getACACaseNumList with the ACA ONLY LIST :"+triggerCaseNums);
		return triggerCaseNums;
	}
	
	/**
	 * getNonAcaChangeReporting
	 * @param triggerCargos
	 * @return
	 * @throws CoException
	 */
	private List<Long> getNonAcaChangeReporting(Set<EdEligibilityCargo> nonACASet) throws CoException{
	
		CoDebugger.debugInformation("NON ACA Combined :Entered getNonAcaChangeReporting");
		List<Long> triggerCaseNums = new ArrayList<Long>();
		Set<Long> triggerCaseNumSet = new HashSet<Long>();
		
		List<Long> rmcCaseNums = new ArrayList<Long>();
		Set<Long> rmcCaseNumSet = new HashSet<Long>();
		
		EdChangeReportingDtlsCargo[] cargos=null;
		
		if(null!=nonACASet && nonACASet.size()>0){
			for(EdEligibilityCargo cargo:nonACASet){
				long caseNum=cargo.getCaseNum();
				if(triggerCaseNumSet.add(caseNum)){
					triggerCaseNums.add(caseNum);
				}
			}
		}
		CoDebugger.debugInformation("Original List of Non ACA CaseNums 1111:"+triggerCaseNums);
		/**Retrieve all Cases flagged for change reporting*/
		cargos = (EdChangeReportingDtlsCargo[]) coDAOServices.findFlagChangeReportingRTRC();
		if(null!=cargos && cargos.length>0){
			for(EdChangeReportingDtlsCargo cargo:cargos){
				long caseNum=cargo.getCaseNum();
				if(rmcCaseNumSet.add(caseNum)){
					rmcCaseNums.add(caseNum);
				}
			}
		}
		CoDebugger.debugInformation("Change reporting CaseNums from  EdChangeReportingDtlsCargo:"+rmcCaseNums);
		triggerCaseNums.retainAll(rmcCaseNums);
		
		CoDebugger.debugInformation("Returned List of Non ACA CaseNums RETAINED:"+triggerCaseNums);
		return triggerCaseNums;
	}
	
	private EdEligibilityCargo[] checkToaForChangeReporting(EdEligibilityCargo[] edEligibilityCargos){
		CoDebugger.debugInformation("Entered checkToaForChangeReporting:::::***");
		
		List<EdEligibilityCargo> filterList=new ArrayList<EdEligibilityCargo>();
		Set<Long> filterSet = new HashSet<Long>();
		EdEligibilityCargo[] edCargos=null;
			
		if(null!=edEligibilityCargos && edEligibilityCargos.length>0){
			for(EdEligibilityCargo cargo:edEligibilityCargos ){
					if(null!=CONoticeUtility.getTOAsForChangeReporting() && CONoticeUtility.getTOAsForChangeReporting().size()>0 
							&& CONoticeUtility.getTOAsForChangeReporting().contains(cargo.getTypeOfAssistanceCd())){
						if(filterSet.add(cargo.getCaseNum())){
							CoDebugger.debugInformation("Successful Adding Cargo Case:"+cargo.getCaseNum());
							filterList.add(cargo);
						}
					}else{
						CoDebugger.debugInformation("Cargo not eligible for Change Reporting ::"+cargo.getTypeOfAssistanceCd());
					}
				}
			}
		if(filterList.size()>0){
			edCargos=(EdEligibilityCargo[])filterList.toArray(new EdEligibilityCargo[filterList.size()]);
		}
		return edCargos;
	}
	
	/**ND-58777-END*/

	private List<Long> findForAsOfDate(String currentAsOfDate,DcChildSuppNonCoopCargo[] childSuppNonCoopCargos) {
		CoDebugger.debugInformation("findForAsOfDate ---> Entered findForAsOfDate --->");
		CoDebugger.debugInformation("findForAsOfDate ---> AS OF DATE String:  ------> " + currentAsOfDate);
		CoDebugger.debugInformation("findForAsOfDate ---> childSuppNonCoopCargos  ------> " + childSuppNonCoopCargos);
		List<Long> custodialParentIndvIdList=null;
		if(null!=childSuppNonCoopCargos){
			custodialParentIndvIdList=new ArrayList<Long>();
			for(DcChildSuppNonCoopCargo cargo:childSuppNonCoopCargos){
				if(null!=cargo.getGoodCauseEndDt()){
					CoDebugger.debugInformation("findForAsOfDate ---> childSuppNonCoopCargos  ------> " + cargo.getAbsentParentSeqNum());
					CoDebugger.debugInformation("findForAsOfDate ---> childSuppNonCoopCargos  ------> Good Cause START DATE :" + cargo.getGoodCauseStartDt());
					CoDebugger.debugInformation("findForAsOfDate ---> childSuppNonCoopCargos  ------> Good Cause END DATE :" + cargo.getGoodCauseEndDt());

					Timestamp cargoGCEndDate =cargo.getGoodCauseEndDt();
					CoDebugger.debugInformation("findForAsOfDate ---> cargo  ------> cargoGCEndDate :" + cargoGCEndDate);
					/** provides previous month end date */
					Timestamp cargoGcedPrevMonth=ALSOPUtil.addMonths(cargoGCEndDate, -1) ;
					CoDebugger.debugInformation("findForAsOfDate ---> cargo  ------> cargoGcedPrevMonth :" + cargoGcedPrevMonth);
					Timestamp firstOfGcedPrevMonth = ALSOPUtil.getFirstDayOfMonth(cargoGcedPrevMonth);
					CoDebugger.debugInformation("findForAsOfDate ---> cargo  ------> firstOfGcedPrevMonth :" + firstOfGcedPrevMonth);
					Timestamp finalGcedCompareTimestamp=ALSOPUtil.addDays(firstOfGcedPrevMonth, 14);
					CoDebugger.debugInformation("findForAsOfDate ---> cargo  ------> finalGcedCompareTimestamp :" + finalGcedCompareTimestamp);

					SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
					String finalGcedCompareDate = dateFormat.format(finalGcedCompareTimestamp);

					CoDebugger.debugInformation("findForAsOfDate ---> cargo  ------> finalGcedCompareDate :" + finalGcedCompareDate);

					if(finalGcedCompareDate.equalsIgnoreCase(currentAsOfDate)){
						CoDebugger.debugInformation("findForAsOfDate ---> cargo  ------> finalGcedCompareDate.equalsIgnoreCase(currentAsOfDate)");
						custodialParentIndvIdList.add(cargo.getCustodialParentIndvId());
						CoDebugger.debugInformation("findForAsOfDate ---> cargo  ------> getCustodialParentIndvId: "+cargo.getCustodialParentIndvId());
					}
				}else{
					CoDebugger.debugInformation("findForAsOfDate ---> cargo  ------> cargo.getGoodCauseEndDt() is NULL :"+cargo.getAbsentParentSeqNum());
				}
			}
			CoDebugger.debugInformation("findForAsOfDate ---> childSuppNonCoopCargos  ------> custodialParentIndvIdList: "+custodialParentIndvIdList);
		}
		return custodialParentIndvIdList;
	}

	private PmProviderChildAssocCargo[]  getTriggersForNDCCAP061(PmProviderChildAssocCargo[] pmProviderChildAssocCargo, String asOfDate) throws CoException {
		try {
			pmProviderChildAssocCargo =  (PmProviderChildAssocCargo[]) coDAOServices.getProviderClosingRecipients(asOfDate);
		} catch (NoDataFoundException e) {
			CoDebugger.debugException(e.getMessage(), e); 
			CoDebugger.debugInformation("Exception while fetching PmProviderChildAssoc Records for " + CoConstants.CO_DOC_ID_CCAP061+ e);
		}
		return pmProviderChildAssocCargo;
	}
	
	/**
	 * @author karraj
	 * @param asOfDate
	 * @return
	 * @throws CoException
	 */
	private PmProviderChildAssocCargo[]  getTriggersForNDCCAP069(String asOfDate) throws CoException {
		Timestamp asOfDateTime = null;
		PmProviderChildAssocCargo[] pmProviderChildAssocCargo = null;
		java.text.SimpleDateFormat dateFormater = new java.text.SimpleDateFormat(CoBatchConstants.asOfDtMMDDYYYY);
		if(null!=asOfDate && !CoConstants.EMPTY_STRING.equals(asOfDate)){
			Date date = null;
			try {
				date = (Date)dateFormater.parse(asOfDate);
				asOfDateTime = new Timestamp(date.getTime());
			} catch (ParseException e) {
				CoDebugger.debugException(e.getMessage(), e); 
				CoDebugger.debugInformation("Parse Exception while parsing as of date" + CoConstants.DOC_ID_NDCCAP069 + e);
				asOfDateTime = CoDateFactory.getTimestamp();
			}
			
		}else{
			asOfDateTime = CoDateFactory.getTimestamp();
		}
		
		try {
			pmProviderChildAssocCargo =  (PmProviderChildAssocCargo[]) coDAOServices.getCargoForNDCCAP069Trigger(asOfDateTime);
		} catch (NoDataFoundException e) {
			CoDebugger.debugException(e.getMessage(), e); 
			CoDebugger.debugInformation("No Data found while fetching PmProviderChildAssoc records for " + CoConstants.DOC_ID_NDCCAP069 + e);
		} catch (Exception e) {
			CoDebugger.debugException(e.getMessage(), e); 
			CoDebugger.debugInformation("Exception while fetching PmProviderChildAssoc records for " + CoConstants.DOC_ID_NDCCAP069 + e);
		}
		return pmProviderChildAssocCargo;
	}
	
	/**
	 * @author arunt
	 * @param asOfDate
	 * @return
	 * @throws CoException
	 */
	
	private PmProviderChildAssocCargo[]  getTriggersForNDCCAP069MLY(String asOfDate) throws CoException {
		Timestamp asOfDateTime = null;
		PmProviderChildAssocCargo[] pmProviderChildAssocCargo = null;
		java.text.SimpleDateFormat dateFormater = new java.text.SimpleDateFormat(CoBatchConstants.asOfDtMMDDYYYY);
		if(null!=asOfDate && !CoConstants.EMPTY_STRING.equals(asOfDate)){
			Date date = null;
			try {
				date = (Date)dateFormater.parse(asOfDate);
				asOfDateTime = new Timestamp(date.getTime());
			} catch (ParseException e) {
				CoDebugger.debugException(e.getMessage(), e); 
				CoDebugger.debugInformation("Parse Exception while parsing as of date" + CoConstants.DOC_ID_NDCCAP069 + e);
				asOfDateTime = CoDateFactory.getTimestamp();
			}
			
		}else{
			asOfDateTime = CoDateFactory.getTimestamp();
		}
		
		try {
			pmProviderChildAssocCargo =  (PmProviderChildAssocCargo[]) coDAOServices.getCargoForNDCCAP069TriggerMLY(asOfDateTime);
		} catch (NoDataFoundException e) {
			CoDebugger.debugException(e.getMessage(), e); 
			CoDebugger.debugInformation("No Data found while fetching PmProviderChildAssoc records for MLY " + CoConstants.DOC_ID_NDCCAP069 + e);
		} catch (Exception e) {
			CoDebugger.debugException(e.getMessage(), e); 
			CoDebugger.debugInformation("Exception while fetching PmProviderChildAssoc records for MLY " + CoConstants.DOC_ID_NDCCAP069 + e);
		}
		return pmProviderChildAssocCargo;
	}
	

	/**
	 * @author tdatta
	 * @param 
	 * @return
	 * @throws CoException
	 */
	private PmProviderChildAssocCargo[]  getTriggersForNDCCAP062(PmProviderChildAssocCargo[] pmProviderChildAssocCargo, String date) throws CoException {
		try {
			
		pmProviderChildAssocCargo =  (PmProviderChildAssocCargo[]) coDAOServices.getHouseholdClosingRecipients(date);
		} catch (NoDataFoundException e) {
			CoDebugger.debugException(e.getMessage(), e); 
			CoDebugger.debugInformation("Exception while fetching PmProviderChildAssoc Records for " + CoConstants.CO_DOC_ID_CCAP062 + e);
		}
		return pmProviderChildAssocCargo;
	}	
	
	
	/**Added for Child Care Assistance Program (CCAP) - Remittance Advice */

	private BiIssuanceTriggerCargo[] getTriggersForNDCDN070(BiIssuanceTriggerCargo[] biIssuanceTriggerCargo, Timestamp asOfDateTimestamp)
			throws CoException {
		try {
			biIssuanceTriggerCargo =  (BiIssuanceTriggerCargo[]) coDAOServices.findBiTriggerForNDCDN070("CD",asOfDateTimestamp);
		} catch (NoDataFoundException e) {
			CoDebugger.debugException(e.getMessage(), e); 
			CoDebugger.debugInformation("Exception while fetching getTriggersForNDCDN070 " + CoConstants.NDCDN070 + e);
			
		}
		return biIssuanceTriggerCargo;
	}

	/**
	 * @author shreyasingh
	 * @param indvID
	 *            long
	 * @return caseNum long
	 * @throws CoException
	 */
	private long getCaseNumforIndvID(long indvId) throws CoException {
		DcCaseIndividualCargo[] dcCaseIndividualCargo = null;

		long caseNum = 0;
		try {
			dcCaseIndividualCargo = (DcCaseIndividualCargo[]) coDAOServices
					.getDcCaseIndvByIndvId(indvId);
			if(null != dcCaseIndividualCargo && dcCaseIndividualCargo.length>0 ){
			for (DcCaseIndividualCargo cargo : dcCaseIndividualCargo) {
				caseNum = cargo.getCaseNum();
			}
		}
		} catch (NoDataFoundException e) {
			CoDebugger.debugException(e.getMessage(), e);
			CoDebugger
					.debugInformation("Exception while fetching DcIndvLivingArngmntsCargoc Records for "
							+ CoConstants.DOC_ID_NDHCC013 + e);
		}
		return caseNum;
	}
	/**
	 * @author shreyasingh
	 * @param caseNum
	 *            long
	 * @return caseClosed boolean
	 * @throws CoException
	 */
	private boolean checkCaseClosed(long caseNum) throws CoException {
		DcCasesCargo[] dcCasesCargo = null;

		try {
			dcCasesCargo = coDAOServices.checkCaseClosed(caseNum);
		} catch (CoException e) {
			CoDebugger.debugException(e.getMessage(), e);
			CoDebugger
					.debugInformation("Exception while fetching DcCases Records for "
							+ CoConstants.DOC_ID_NDHCC013 + e);
		}
		if(null != dcCasesCargo && dcCasesCargo.length>0 ){
			CoDebugger
			.debugInformation("Case is closed. No trigger to be inserted for "
					+ caseNum );
			return true;
		}
		return false;
	}


	/**
	 * @author shreysingh
	 * @param asOfDate
	 * @return
	 * @throws CoException
	 */
	private EdEligibilityCargo[] getTriggersForNDHCC013(String asOfDate)throws CoException {
		EdEligibilityCargo[] edEligibilityCargo = null;
		Timestamp asOfDateTimestamp = null;
		java.text.SimpleDateFormat dateFormater = new java.text.SimpleDateFormat(CoBatchConstants.asOfDtMMDDYYYY);
		if(null!=asOfDate && !CoConstants.EMPTY_STRING.equals(asOfDate)){
			Date date = null;
			try {
				date = (Date)dateFormater.parse(asOfDate);
				asOfDateTimestamp = new Timestamp(date.getTime());
			} catch (ParseException e) {
				CoDebugger.debugException(e.getMessage(), e); 
				CoDebugger.debugInformation("Parse Exception while parsing as of date" + e);
				asOfDateTimestamp = CoDateFactory.getTimestamp();
			}		
		}else{
			asOfDateTimestamp = CoDateFactory.getTimestamp();
		}
		
		try {
			edEligibilityCargo = (EdEligibilityCargo[]) coDAOServices.getIndvInLTC(asOfDateTimestamp);
		} catch (NoDataFoundException e) {
			CoDebugger.debugException(e.getMessage(), e);
			CoDebugger
					.debugInformation("Exception while fetching edEligibilityCargo Records for "
							+ CoConstants.DOC_ID_NDHCC013 + e);
		}
		return edEligibilityCargo;
	}

	/**
	 * @author shreyasingh
	 * @param a
	 *            Date
	 * @param b
	 *            Date
	 * @return months long
	 * @throws CoException
	 */
	private boolean getMonths(Date a, Date b) {
		Calendar cal1 = null;
		Calendar cal2 = null;
		cal1 = Calendar.getInstance();
		cal2 = Calendar.getInstance();
		cal1.setTime(a);
		cal2.setTime(b);
		int yearsInBetween = cal1.get(Calendar.YEAR) - cal2.get(Calendar.YEAR);
		int monthsDiff = cal1.get(Calendar.MONTH) - cal2.get(Calendar.MONTH);
		long months = yearsInBetween * 12 + monthsDiff;
		if (months == 6) {
			return true;
		} else {
			return false;
		}

	}
	
	/**
	 * createCorrespondenceTriggerForNDLIN084
	 * @param coCorrespondenceList
	 * @param coCorrespondence
	 * @param triggerType
	 * @param wtEdgTraceIdSet
	 * @param liEdgTraceIdSet
	 * @throws CoException 
	 */
	// ND-60844-LIHEAP Payment Summary
	private List<COCorrespondence> createCorrespondenceTriggerForNDLIN084(List<COCorrespondence> coCorrespondenceList, 
			COCorrespondence coCorrespondence,String triggerType,Set<Long> wtEdgTraceIdSet,Set<Long> liEdgTraceIdSet) throws CoException, ParseException{
			
			DcCaseProgramCargo[] dcCaseProgramCargo=null;
			
			VBiWarrantDetailCargo[] warrantDetailCargos = null;
			VBiLiheapPayeeCargo[] liheapPayeeCargos = null;
			StringBuilder caseNums = new StringBuilder();
			coCorrespondenceList = new ArrayList<COCorrespondence>();

			SimpleDateFormat datetimeFormatter1 = new SimpleDateFormat("MM/dd/yyyy");
			Date batchAsOfDate = datetimeFormatter1.parse(asOfDate);
			Timestamp asOfDateTimestamp = new Timestamp(batchAsOfDate.getTime());
			Timestamp effBeginDt=ALSOPUtil.getFirstDayOfMonth(asOfDateTimestamp);
			Timestamp effEndDt=ALSOPUtil.getLastDayOfMonth(asOfDateTimestamp);
			
			CoDebugger.debugInformation("createCorrespondenceTriggerForNDLIN084 ---> AS OF DATE:  ------> " + asOfDate);
			CoDebugger.debugInformation("createCorrespondenceTriggerForNDLIN084 ---> Timestamp effBeginDt:  ------> " + effBeginDt.toString());
			CoDebugger.debugInformation("createCorrespondenceTriggerForNDLIN084 ---> Timestamp effEndDt:  ------> " + effEndDt.toString());
			
			if (triggerType.equalsIgnoreCase(CoConstants.NDLIN084)){
				dcCaseProgramCargo = getCasesForNDLIN084(effBeginDt,effEndDt);
			}			
			
			if(null!=dcCaseProgramCargo && dcCaseProgramCargo.length>0){
				caseNums=getCaseNumList(dcCaseProgramCargo);
				if(null!=caseNums.toString()){
					try{
						warrantDetailCargos	=coDAOServices.getVBiWarrantDetailForLiheapCase(caseNums.toString());
					}catch (NoDataFoundException e) {
						CoDebugger.debugException(e.getMessage(), e); 
						CoDebugger.debugInformation("NoDataFoundException while fetching cases for getVBiWarrantDetailForLiheapCase ");
					}
					try{
						liheapPayeeCargos=coDAOServices.getVBiLiheapPayeeDetailByCaseNum(caseNums.toString());
					}catch (NoDataFoundException e) {
						CoDebugger.debugException(e.getMessage(), e); 
						CoDebugger.debugInformation("NoDataFoundException while fetching cases for getVBiLiheapPayeeDetailByCaseNum ");
					}
				}
				
				if(null!=warrantDetailCargos && warrantDetailCargos.length>0){
					for(VBiWarrantDetailCargo cargo:warrantDetailCargos){
						if(wtEdgTraceIdSet.add(cargo.getCaseNum())){
	
								long caseNum = cargo.getCaseNum();
								coCorrespondence = new COCorrespondence();
								coCorrespondence.setDocId(triggerType);
								coCorrespondence.setCaseAppNumber(String.valueOf(caseNum));
								coCorrespondence.setCaseAppFlag('C');
								coCorrespondence.setDocType(CoConstants.CO_DOC_TYPE_CD_N);
								coCorrespondence.setAssistanceProgramCode(CoConstants.PROGRAM_LI);
								coCorrespondenceList.add(coCorrespondence);
						}
					}
				}
				
				if(null!=liheapPayeeCargos && liheapPayeeCargos.length>0){
					for(VBiLiheapPayeeCargo cargo:liheapPayeeCargos){
						if(liEdgTraceIdSet.add(cargo.getT2CaseNum())){
							
							long caseNum = cargo.getT2CaseNum();
							coCorrespondence = new COCorrespondence();
							coCorrespondence.setDocId(triggerType);
							coCorrespondence.setCaseAppNumber(String.valueOf(caseNum));
							coCorrespondence.setCaseAppFlag('C');
							coCorrespondence.setDocType(CoConstants.CO_DOC_TYPE_CD_N);
							coCorrespondence.setAssistanceProgramCode(CoConstants.PROGRAM_LI);
							coCorrespondenceList.add(coCorrespondence);
						}
					}
				}
			}
		return coCorrespondenceList;
	}
	
	/**
	 * getCasesForNDLIN084
	 * @param benefitStartdt
	 * @param benefitEndDt
	 * @return
	 * @throws CoException
	 */
	private DcCaseProgramCargo[] getCasesForNDLIN084(Timestamp benefitStartdt, Timestamp benefitEndDt)
			throws CoException {
		DcCaseProgramCargo[] dcCaseProgramCargo=null;
		try {
			dcCaseProgramCargo =  (DcCaseProgramCargo[]) coDAOServices.getCasesForClosedLI(benefitStartdt,benefitEndDt,CoConstants.PROGRAM_LI);
		} catch (NoDataFoundException e) {
			CoDebugger.debugException(e.getMessage(), e); 
			CoDebugger.debugInformation("Exception while fetching getCasesForNDLIN084 " + CoConstants.NDLIN084 + e);
			
		}
		return dcCaseProgramCargo;
	}
	/**
	 * getCaseNumList
	 * @param dcCaseProgramCargo
	 * @return
	 */
	private StringBuilder getCaseNumList(DcCaseProgramCargo[] dcCaseProgramCargo){
	
		StringBuilder caseNums = new StringBuilder();
		Set<Long> caseNumSet = new HashSet<Long>();
		
		if(null!=dcCaseProgramCargo && dcCaseProgramCargo.length>0){
			for(DcCaseProgramCargo cargo:dcCaseProgramCargo){
				long caseNum=cargo.getCaseNum();
				if(caseNumSet.add(caseNum)){
					caseNums.append(caseNum);
					caseNums.append(CoConstants.COMMA);
				}
			}
		
		if(null!=caseNums && caseNums.charAt(caseNums.length()- 1)==','){
			caseNums.deleteCharAt(caseNums.length() - 1).toString();
		}}
		
		return caseNums;
	}
	
	private void extractFilterCriteriaLIVendor(Set<Integer> activeVendors,
			DateFormat df, String scheduledDate,
			HashMap<String, ArrayList<String>> filterusingData,
			String filterCriteria) throws CoException {
		Timestamp currentTimeStamp = CoDateFactory.getTimestamp();										
		int currMonth=ALSOPUtil.getMM(currentTimeStamp);
		int currYear=ALSOPUtil.getYYYY(currentTimeStamp);
		
		int eyear=currYear;
		if(currMonth>5){
			 eyear=currYear+1;
		}
		 Calendar starCal=Calendar.getInstance();
		 starCal.set(eyear-1, 9, 1);
		 String sDate= df.format(starCal.getTime());
		 
		 Calendar endCal=Calendar.getInstance();
		 endCal.set(eyear, 8, 30);
		 String eDate= df.format(endCal.getTime());
		 
		 CoDebugger.debugInformation("LIHEAP CURRENT FISCAL YEAR STARTDATE "+ sDate);
		 CoDebugger.debugInformation("LIHEAP CURRENT FISCAL YEAR ENDDATE "+ eDate);
		//each scenario check start
		
		//scenario:VENDOR TYPE
		checkLIVendorType(activeVendors,filterusingData,filterCriteria, sDate,eDate);
		
		//scenario:FUEL or SERVICES PROVIDED
		checkLIVendorFuelServices(activeVendors,filterusingData,filterCriteria, sDate,eDate);
		
		//scenario: VENDOR PRIMARY FUEL TYPE												
		checkLIVendorPrimaryFuelType(activeVendors,filterusingData,filterCriteria, sDate,eDate);
		
		//VENDOR COUNTY
		checkLIVendorCounty(activeVendors,scheduledDate,filterusingData,filterCriteria);
		
		//FISCAL YEAR
		checkLIVendorFiscalYear(activeVendors, df,filterusingData,filterCriteria, eyear,sDate, eDate);
	}
	private void initializeFilterCriteriasValues(String[] arrRecordPerReq,
			HashMap<String, ArrayList<String>> filterusingData,
			Set<String> filterCriterias) {
		if(arrRecordPerReq!=null && arrRecordPerReq.length>0){
			for(String recordPerReq:arrRecordPerReq ){
				if(recordPerReq!=null && recordPerReq.length()>0){
					String[] recordElements=recordPerReq.split(",");
					if(recordElements!=null && recordElements.length>3){	

						if(!(filterCriterias.contains(recordElements[2]))){
							filterCriterias.add(recordElements[2]);								                   
						}																						
						//to store
						if(filterCriterias.contains(recordElements[2])){													
							//already exists criteria then add rest distinct values
							if(!filterusingData.isEmpty() && filterusingData.containsKey(recordElements[2])){

								for(int k=3;k<recordElements.length;k++){
									if(!(filterusingData.get(recordElements[2]).contains(recordElements[k]))){
										filterusingData.get(recordElements[2]).add(recordElements[k]);
									}
								}																	
							}else{//first time criteria added in hashmap
								ArrayList<String> filterValues=new ArrayList<String>();
								for(int k=3;k<recordElements.length;k++){
									filterValues.add(recordElements[k]);
								}															
								filterusingData.put(recordElements[2], filterValues);
							}														
						}												
					}
				}											
			}
		}
	}
	private void checkLIVendorFiscalYear(Set<Integer> activeVendors,
			DateFormat df, HashMap<String, ArrayList<String>> filterusingData,
			String filterCriteria, int eyear, String sDate, String eDate)
			throws CoException {
		if(filterCriteria.equals(CoConstants.FISCAL_YEAR) && filterusingData.containsKey(CoConstants.FISCAL_YEAR) && null!=filterusingData.get(CoConstants.FISCAL_YEAR) && !filterusingData.get(CoConstants.FISCAL_YEAR).isEmpty()){
			CoDebugger.debugInformation("VENDOR FLOW FISCAL YEAR STARTED>>>>>>>>>" );
			
			
			for(String fiscalYear:filterusingData.get(CoConstants.FISCAL_YEAR)){
				
				if(null!=fiscalYear && fiscalYear.equals(CoConstants.PREV_FY)){
					
					 int prevYear=eyear-1;
					 Calendar starCal2=Calendar.getInstance();
					 starCal2.set(prevYear-1, 9, 1);
					 String startDate= df.format(starCal2.getTime());
					 
					 Calendar endCal2=Calendar.getInstance();
					 endCal2.set(prevYear, 8, 30);
					 String endDate= df.format(endCal2.getTime());
					
					PmLiheapVendorCargo[] pmLiheapVendorCargos=coDAOServices.getByFYVendor(startDate,endDate);
					if(null!=pmLiheapVendorCargos && pmLiheapVendorCargos.length>0){
						for(PmLiheapVendorCargo pmLiheapVendorCargo:pmLiheapVendorCargos){
							if(null!=pmLiheapVendorCargo && null != pmLiheapVendorCargo.getSpacesVendorId() && pmLiheapVendorCargo.getSpacesVendorId()>0 && !activeVendors.contains(pmLiheapVendorCargo.getSpacesVendorId())){
								activeVendors.add(pmLiheapVendorCargo.getSpacesVendorId());
							}
						}
					}
				}
				
				if(null!=fiscalYear && fiscalYear.equals(CoConstants.CURR_FY)){
					PmLiheapVendorCargo[] pmLiheapVendorCargos=coDAOServices.getByFYVendor(sDate,eDate);
					if(null!=pmLiheapVendorCargos && pmLiheapVendorCargos.length>0){
						for(PmLiheapVendorCargo pmLiheapVendorCargo:pmLiheapVendorCargos){
							if(null!=pmLiheapVendorCargo && null != pmLiheapVendorCargo.getSpacesVendorId() && pmLiheapVendorCargo.getSpacesVendorId()>0 && !activeVendors.contains(pmLiheapVendorCargo.getSpacesVendorId())){
								activeVendors.add(pmLiheapVendorCargo.getSpacesVendorId());
							}
						}
					}
				}
			}
		}
	}
	private void checkLIVendorCounty(Set<Integer> activeVendors,
			String scheduledDate,
			HashMap<String, ArrayList<String>> filterusingData,
			String filterCriteria) throws CoException {
		if(filterCriteria.equals(CoConstants.COUNTY) && filterusingData.containsKey(CoConstants.COUNTY) && null!=filterusingData.get(CoConstants.COUNTY) && !filterusingData.get(CoConstants.COUNTY).isEmpty()){
			CoDebugger.debugInformation("VENDOR FLOW COUNTY STARTED>>>>>>>>>" );
			String counties="";
			String prefix="";

			for(String county: filterusingData.get(CoConstants.COUNTY)){
				counties=counties+prefix+"'"+county + "'";
				prefix=",";
			}												

			PmAddressCargo[] providersAddrArr=	(PmAddressCargo[])coDAOServices.getProviderCountyMassMail(scheduledDate,counties);

			if(providersAddrArr!=null && providersAddrArr.length>0){
				for(PmAddressCargo cargo:providersAddrArr){
					if(null != cargo.getVendorProviderId()){
					if(cargo.getVendorProviderId()>0 && !activeVendors.contains(cargo.getVendorProviderId())){
						activeVendors.add(cargo.getVendorProviderId());
					}
				}
			}
		}											

	}
	}
	private void checkLIVendorPrimaryFuelType(Set<Integer> activeVendors,
			HashMap<String, ArrayList<String>> filterusingData,
			String filterCriteria, String sDate, String eDate)
			throws CoException {
		if(filterCriteria.equals(CoConstants.PRIMARY_FUEL_TYPE) && filterusingData.containsKey(CoConstants.PRIMARY_FUEL_TYPE) && filterusingData.get(CoConstants.PRIMARY_FUEL_TYPE)!=null && !filterusingData.get(CoConstants.PRIMARY_FUEL_TYPE).isEmpty()){
			CoDebugger.debugInformation("VENDOR PRIMARY FUEL STARTED>>>>>>>>>" );
			String fuelTypes="";
			String prefix="";

			for(String fuelType: filterusingData.get(CoConstants.PRIMARY_FUEL_TYPE)){
				fuelTypes=fuelTypes+prefix+"'%"+fuelType + "%'";
				prefix=",";
			}	

			PmLiheapVendorCargo[] pmLiheapVendorCargoArr=coDAOServices.getByFuelTypeCO(sDate,eDate,fuelTypes);
			if(pmLiheapVendorCargoArr!=null && pmLiheapVendorCargoArr.length>0){
				for(PmLiheapVendorCargo pmLiheapVendor:pmLiheapVendorCargoArr){
					if(pmLiheapVendor.getSpacesVendorId()!=null){
						Integer vendorId=pmLiheapVendor.getSpacesVendorId();
						if(vendorId>0 && !activeVendors.contains(vendorId)){
							activeVendors.add(vendorId);
						}
					}
				}
			}
		}
	}
	private void checkLIVendorFuelServices(Set<Integer> activeVendors,
			HashMap<String, ArrayList<String>> filterusingData,
			String filterCriteria, String sDate, String eDate)
			throws CoException {
		if(filterCriteria.equals(CoConstants.FUEL_SERVICE_TYPE) && filterusingData.containsKey(CoConstants.FUEL_SERVICE_TYPE) && filterusingData.get(CoConstants.FUEL_SERVICE_TYPE)!=null && !filterusingData.get(CoConstants.FUEL_SERVICE_TYPE).isEmpty()){
			CoDebugger.debugInformation("FUEL SERVICE TYPE STARTED>>>>>>>>>" );
			String fuelTypes="";
			String prefix="";

			for(String fuelType: filterusingData.get(CoConstants.FUEL_SERVICE_TYPE)){
				fuelTypes=fuelTypes+prefix+"'%"+fuelType + "%'";
				prefix=",";
			}	

			PmLiheapVendorCargo[] pmLiheapVendorCargoArr=coDAOServices.getByFuelTypeCO(sDate,eDate,fuelTypes);
			if(pmLiheapVendorCargoArr!=null && pmLiheapVendorCargoArr.length>0){
				for(PmLiheapVendorCargo pmLiheapVendor:pmLiheapVendorCargoArr){
					if(pmLiheapVendor.getSpacesVendorId()!=null){
						Integer vendorId=pmLiheapVendor.getSpacesVendorId();
						if(vendorId>0 && !activeVendors.contains(vendorId)){
							activeVendors.add(vendorId);
						}
					}
				}
			}
		}
	}
	private void checkLIVendorType(Set<Integer> activeVendors,
			HashMap<String, ArrayList<String>> filterusingData,
			String filterCriteria, String sDate, String eDate)
			throws CoException {
		if(filterCriteria.equals(CoConstants.VENDOR_TYPE) && filterusingData.containsKey(CoConstants.VENDOR_TYPE) && filterusingData.get(CoConstants.VENDOR_TYPE)!=null && !filterusingData.get(CoConstants.VENDOR_TYPE).isEmpty()){
			CoDebugger.debugInformation("VENDOR TYPE STARTED>>>>>>>>>" );
			String vendorTypes="";
			String prefix="";

			for(String vendorType: filterusingData.get(CoConstants.VENDOR_TYPE)){
				vendorTypes=vendorTypes+prefix+"'"+vendorType + "'";
				prefix=",";
			}	

			PmLiheapVendorCargo[] pmLiheapVendorCargoArr=coDAOServices.getByVendTypeCO(sDate,eDate,vendorTypes);
			if(pmLiheapVendorCargoArr!=null && pmLiheapVendorCargoArr.length>0){
				for(PmLiheapVendorCargo pmLiheapVendor:pmLiheapVendorCargoArr){
					if(pmLiheapVendor.getSpacesVendorId()!=null){
						Integer vendorId=pmLiheapVendor.getSpacesVendorId();
						if(vendorId>0 && !activeVendors.contains(vendorId)){
							activeVendors.add(vendorId);
						}
					}
				}
			}
		}
	}

	private String getXMLFileNameForNoticeForProvidersAndVendors(String docId,
			String docName2, String valueOf, long coReqSeq, long coRptSeq,
			Timestamp generateDt, long providerId, Date generateDate) {


		String groupCd= "";
		String fileType="";
		try{
			groupCd = ReferenceTableAccess.getRefDescription(docId,
					CoBatchConstants.RT_GROUPCODES);
		}catch(Exception e){
			CoDebugger.getLogger().log("Exception caught:",ILog.INFO,e);
			CoDebugger.debugInformation(e.getMessage());
		}
		
		//Added for CR-863 AND ND-101820
		CoRequestHistoryCargo reqHisCargo=new CoRequestHistoryCargo();
		reqHisCargo.setCoReqSeq(coReqSeq);
		Object[] params=new Object[]{reqHisCargo};
		CoRequestHistoryCollection reqHisColl=new CoRequestHistoryCollection();
		try {
			CoRequestHistoryCargo[] reqHisCargoArr=(CoRequestHistoryCargo[])reqHisColl.select("findByReqSeq",params);
			if(reqHisCargoArr!=null && reqHisCargoArr.length>0){
				fileType=reqHisCargoArr[0].getMiscParms();
				if(docId.equals(CoConstants.CO_DOC_ID_NCH049)){
					if(fileType!=null && fileType.equalsIgnoreCase(CoConstants.MASS_MAIL_PROVIDER)){
						groupCd=CoConstants.CO_MASS_MAIL_GRCODE_FOR_PROVIDER;
					}
					else if(fileType!=null && fileType.equalsIgnoreCase(CoConstants.MASS_MAIL_VENDOR)){
						groupCd=CoConstants.CO_MASS_MAIL_GRCODE_FOR_VENDOR;
					}
				}else{
					char requestType=reqHisCargoArr[0].getRequestTypeCd();
					if(requestType==(CoConstants.MASS_MAIL_VENDOR).charAt(0)){
						fileType=CoConstants.MASS_MAIL_VENDOR;
					}else if(requestType==(CoConstants.P).charAt(0) || requestType==(CoConstants.S).charAt(0)){
						fileType=CoConstants.MASS_MAIL_PROVIDER;
					}
				}
			}
		} catch (FrameworkException | ApplicationException e) {
			CoDebugger.getLogger().log("Exception caught:",ILog.INFO,e);
			CoDebugger.debugInformation(e.getMessage());
		}
		//End of code changes for CR-863 AND ND-101820

		StringBuffer sbFileName = new StringBuffer();		
		sbFileName.append(ftpProperty.getProperty(CoConstants.CO_HP_ENV));
		sbFileName.append("_");
		sbFileName.append(FILE_NAME_2);
		sbFileName.append("_");
		sbFileName.append(docId);
		sbFileName.append("_");
		sbFileName.append(docName2);
		sbFileName.append("_");
		if(fileType!=null){
			sbFileName.append(fileType);
		}
		sbFileName.append(providerId);
		sbFileName.append("_");
		sbFileName.append(groupCd);
		sbFileName.append("_");
		sbFileName.append(coReqSeq);
		sbFileName.append("_");
		sbFileName.append(coRptSeq);
		sbFileName.append("_");

		String strDt = (new SimpleDateFormat(CoConstants.DATE_FORMAT_yyyyMMdd)
		.format(generateDate));
		sbFileName.append(strDt);

		sbFileName.append(CoConstants.XML_FILE_EXTN);
		log.log(CoConstants.CO_NAME,ILog.INFO ,"File Name generated for "+docId+" is "+sbFileName);
		return sbFileName.toString();
	
	}
	
	private Timestamp generateNCH0057TriggerDate(COCorrespondence coCorrespondence){
		Timestamp triggerBeginDate=null;
			if(null!=coCorrespondence.getGenerateDate()){
			CoDebugger.debugInformation("COBatch:::::generateNCH0057TriggerDate>>>>>>>>BEGIN");
			triggerBeginDate=coCorrespondence.getGenerateDate();
			CoDebugger.debugInformation("COBatch:::::generateNCH0057TriggerDate>>>>>>>>getGenerateDate"+triggerBeginDate);
			triggerBeginDate=ALSOPUtil.adjustToWorkingDay(triggerBeginDate, 1);
			CoDebugger.debugInformation("COBatch:::::generateNCH0057TriggerDate>>>>>>>>adjustToWorkingDay--PRINT DATE"+triggerBeginDate);
			triggerBeginDate=ALSOPUtil.addDays(triggerBeginDate, 60);
			CoDebugger.debugInformation("COBatch:::::generateNCH0057TriggerDate>>>>>>>>adding 60 CALENDAR Days :"+triggerBeginDate);
		}else{
			CoDebugger.debugInformation("COBatch:::::generateNCH0057TriggerDate>>>>>>>>NULL Generate Date");
		}
		return triggerBeginDate;
	}
	
	/**
	 * generates the report take care that the headline passed to this method
	 * does not exceed 120 char - the DB column limit Should not be over-riden
	 * in the sub-class as it is not required
	 * 
	 * @param1 code
	 * @param2[0] readCount/successCount
	 * @param2[1] successCount/failureCount
	 * @param2[2] failureCount/headline
	 * @param2[3] headline
	 */
	protected void generateSummaryReportForHPEngineAndMerge(String code, long readCount, long successCount, long failureCount,
			String executionPeriod, String xmlProcessed, String pdfProcessed, String pageProcessed) {
		List<String> reportLines = new ArrayList<String>();
		List<String> columnTypes = new ArrayList<String>();
		try {
			columnTypes.add(tbc.formatString(FwBatchConstants.REPORT_TITLE, 20));
			columnTypes.add(tbc.formatString(FwBatchConstants.REPORT_BODY, 20));
			columnTypes.add(tbc.formatString(FwBatchConstants.REPORT_BODY, 20));
			columnTypes.add(tbc.formatString(FwBatchConstants.REPORT_BODY, 20));
			columnTypes.add(tbc.formatString(FwBatchConstants.REPORT_TITLE, 20));
			columnTypes.add(tbc.formatString(FwBatchConstants.REPORT_BODY, 20));
			columnTypes.add(tbc.formatString(FwBatchConstants.REPORT_BODY, 20));
			columnTypes.add(tbc.formatString(FwBatchConstants.REPORT_BODY, 20));
			
			if(CoConstants.PDF_BATCH_SUMMARY.equals(code)){
				reportLines.add(CoConstants.HEADLINE + CoConstants.BATCH_CO_GNPDF_DLY
						+ CoConstants.COLON);
				reportLines.add(formatReportLine(
						CoConstants.BATCH_CO_GNPDF_DLY_LINE_1,
						String.valueOf(readCount)));
				reportLines.add(formatReportLine(
						CoConstants.BATCH_CO_GNPDF_DLY_LINE_2,
						String.valueOf(successCount)));
				reportLines.add(formatReportLine(
						CoConstants.BATCH_CO_GNPDF_DLY_LINE_3,
						String.valueOf(failureCount)));
				reportLines.add(CoConstants.BATCH_CO_GNPDF_DLY_HP_HEADLINE + executionPeriod
						+ CoConstants.COLON);
				reportLines.add(xmlProcessed);
				reportLines.add(pdfProcessed);
				reportLines.add(pageProcessed);
			}

			/** generate summary only when condition met */
			if (reportLines.size() > 0) {

				String[] repArr = reportLines.toArray(new String[reportLines
				                                                 .size()]);

				String[] colArr = columnTypes.toArray(new String[columnTypes
				                                                 .size()]);

				tbc.generateReport(repArr, colArr);
			}
		} catch (Exception e) {
			CoDebugger
			.debugException(
					"Exception while writing summary to FW_BATCH_SUMMARY table",
					e);
		}
	}
	
	
	/**
	 * ND-91320
	 * @param coCorrespondence
	 * @return
	 */
	private EdCaseRecertDatesCargo[] updateEdCaseRecertDateForNDHCREF09(
			COCorrespondence coCorrespondence) {
		CoDebugger
		.debugInformation("CoBatch.getAllProgramsForDueNextMonthForManallyGenerated --> Start");

		EdCaseRecertDatesCargo[] edCaseRecertDatesCargos = this
				.getAllProgramsForDueNextMonthForManallyGenerated(
						coCorrespondence);
		if (edCaseRecertDatesCargos != null
				&& edCaseRecertDatesCargos.length > 0) {

			EdCaseRecertDatesCollection edCaseRecertDatesCollection = new EdCaseRecertDatesCollection();
			boolean isCargoUpdated = false;
			for (EdCaseRecertDatesCargo edCaseRecertDatesCargo : edCaseRecertDatesCargos) {
				if (CoConstants.REVIEW_TYPE_RC
						.equalsIgnoreCase(edCaseRecertDatesCargo
								.getReviewPendingCd())) {
					edCaseRecertDatesCargo
							.setReviewPendingCd(CoConstants.REVIEW_TYPE_RR);
					isCargoUpdated = true;
				}
				if (null == edCaseRecertDatesCargo.getForm1830PrintDt()) {
					edCaseRecertDatesCargo.setForm1830PrintDt(coCorrespondence
							.getGenerateDate());
					isCargoUpdated = true;
				}
				if (null == edCaseRecertDatesCargo.getForm1830DueDt()) {
					edCaseRecertDatesCargo.setForm1830DueDt(ALSOPUtil
							.getLastWorkingDayOfMonth(edCaseRecertDatesCargo
									.getRecertReviewDueDt()));
					isCargoUpdated = true;
				}
				if (isCargoUpdated) {
					edCaseRecertDatesCollection.add(edCaseRecertDatesCargo);
				}
			}
			try {
				edCaseRecertDatesCollection.update();
			} catch (ApplicationException | FrameworkException e) {
				CoDebugger
						.debugException(
								"Co Exception in CoBatch.getAllProgramsForDueNextMonth",
								e);
			}
		}

		return edCaseRecertDatesCargos;

	}

	/**
	 * ND-91320
	 * @param coCorrespondence
	 * @param nextMonth
	 * @return
	 */
	private EdCaseRecertDatesCargo[] getAllProgramsForDueNextMonthForManallyGenerated(
			COCorrespondence coCorrespondence) {
		CoDebugger
				.debugInformation("CoBatch.getAllProgramsForDueNextMonthForManallyGenerated --> Start");
		EdCaseRecertDatesCargo[] edCaseRecertDatesCargos = null;
		try {
			edCaseRecertDatesCargos = (EdCaseRecertDatesCargo[]) coDAOServices
					.getReviewDueForNextMonthForManuallyGenerated(
							Long.parseLong(coCorrespondence.getCaseAppNumber()),
							BiUtils.getLastDayOfNextMonth());
		} catch (NoDataFoundException e) {
			CoDebugger.debugException("No Data found in ED_CASE_RECERT_DATES",
					e);
		} catch (NumberFormatException e) {
			CoDebugger
					.debugException(
							"Number format exception while parsing case number in CoBatch.getAllProgramsForDueNextMonthForManallyGenerated ",
							e);
		} catch (CoException e) {
			CoDebugger
					.debugException(
							"Co Exception in CoBatch.getAllProgramsForDueNextMonthForManallyGenerated",
							e);
		}
		return edCaseRecertDatesCargos;
	}
	
	public void runEdNoeCompariosionUtility(){
		
		CoEdUtilityBO utilityBo= new CoEdUtilityBO();
		utilityBo.runEdNoeCompariosionUtilityInUtilityBO(coDAOServices);
	}
	
	
	public void generateAlert(Long caseNum, int alertId, Timestamp alert502FirstDayOfNextMonth )throws GenericValidationException,Exception
	{
		CoDebugger.debugInformation("CoBatch: Inside generateAlert- Alert 502 for Case: "+caseNum);

		AlIAlerts alertObject;
		ArrayList placeValue = new ArrayList();
		alertObject = AlAlertManager.getInstance();  
		long caseOwnerId = 0;

		/**Fetch Case Worker Details*/
		try{
			Object[] moEmpAppCarArr = coDAOServices.getCaseWorkerDetails(caseNum);
			if (null != moEmpAppCarArr && moEmpAppCarArr.length > 0 && null != moEmpAppCarArr[0]) {
				caseOwnerId = ((MoEmployeesCargo) moEmpAppCarArr[0]).getEmpId();
			}				
		
			Object[] moEmpAppCarArrNew = coDAOServices.getCaseWorkerDetailsNew(caseNum);				
			if (null != moEmpAppCarArrNew && moEmpAppCarArrNew.length > 0 && null != moEmpAppCarArrNew[0]) {
				caseOwnerId = ((MoEmployeesCargo) moEmpAppCarArrNew[0]).getEmpId();
			}
			
		}catch(Exception e){
			CoDebugger.debugException(e.getMessage(),e);
			CoDebugger.debugInformation(" Exception while trying to fetch Case Worker Data for Alert 502 for Case: "+caseNum);
		}
		
		HashMap hshParams = new HashMap();
		
		hshParams.put("CASE_NUM", caseNum);
		hshParams.put("ALERT_ID", Long.valueOf(alertId));
		hshParams.put("CREATE_USER", "MUBEDBCDLY");
		hshParams.put("EMP_ID", caseOwnerId);
		
		hshParams.put("CURRENT_DATE", alert502FirstDayOfNextMonth);
		hshParams.put("CREATE_DT", alert502FirstDayOfNextMonth);
		hshParams.put("ALERT_DUE_DT", alert502FirstDayOfNextMonth);
		hshParams.put("ISSUE_DT", alert502FirstDayOfNextMonth);

		placeValue.add(caseNum);
		hshParams.put("ADDITIONAL_ALERT_PARA", placeValue);
		if(caseOwnerId!=0)
		{
			alertObject.generateAlert(hshParams);
		}
	} 
	
	
	/**CR 932 Begins
	 * 
	 * @param coCorrespondenceList 
	 */

	private void createAndAddCorrespondence(String docId, Long caseNum,
			Integer providerId, char caseAppFlag, Integer indvId, char docTypeCd,
			String programCd, boolean generatedManualy, List<COCorrespondence> coCorrespondenceList) {
		/**
		 * Create a coCorrespondence object for each case
		 * and provider in the list 
		 */
		COCorrespondence coCorrespondence = new COCorrespondence();
		coCorrespondence.setDocId(docId);
		coCorrespondence.setCaseAppNumber(String.valueOf(caseNum));
		coCorrespondence.setProviderId(providerId);
		coCorrespondence.setCaseAppFlag(caseAppFlag);
		coCorrespondence.setIndvId(indvId);
		coCorrespondence.setDocType(docTypeCd);
		coCorrespondence.setAssistanceProgramCode(programCd);
		coCorrespondence.setManualyGenerated(generatedManualy);
		coCorrespondence.setActionCode(CoConstants.CHAR_N);
		/**
		 * add each coCorrespondence object to the
		 * coCorrespondenceList to be inserted as trigger
		 */
		coCorrespondenceList.add(coCorrespondence);
	}
	
		private void getTriggersForUpdatedCopayLOCOrHHSize(Map<Long, Set<Integer>> finalMap, String batchRunDt) {
		EdEligibilityCargo[]  activeEdCargos=null;
		EdEligibilityCargo[]  activeEdCargosWithAssocs=null;
		EdEligibilityCargo[]  activeEdCargosWithoutAssocs=null;
		EdEligibilityCargo[]  cancelledEdCargos=null;
		VEdEligibilityIndvCargo[]  vEdExcludedChildrenCargos=null;
		ArrayList<Long> activeChildrenList= new ArrayList<Long>();
		ArrayList<Long> activeCaseNumbersWithAssocList= new ArrayList<Long>();
		ArrayList<Long> childrenExcludedForAuthPeriodList= new ArrayList<Long>();
		List activeEDCargosCombinedList= null;
		Long[] activeCaseNumbersWithAssoc=null;
		try {
			/**Fetch Active ED Records with existing assocs updated on Batch Run Date*/
			activeEdCargosWithAssocs=(EdEligibilityCargo[]) coDAOServices.getCCAPCertificateTriggersForED(batchRunDt);
			if (activeEdCargosWithAssocs!=null && activeEdCargosWithAssocs.length!=0){
				for (EdEligibilityCargo edEligibilityCargo: activeEdCargosWithAssocs){						
					activeCaseNumbersWithAssocList.add(edEligibilityCargo.getCaseNum());
					activeChildrenList.add(edEligibilityCargo.getChildIndvId());
				}	
				activeEDCargosCombinedList = new ArrayList(Arrays.asList(activeEdCargosWithAssocs));
				activeCaseNumbersWithAssoc = activeCaseNumbersWithAssocList.toArray(new Long[activeCaseNumbersWithAssocList.size()]);
	
			}
			
			/**Fetch Active ED Records without existing assocs updated on Batch Run Date*/
	
			activeEdCargosWithoutAssocs=(EdEligibilityCargo[]) coDAOServices.getCCAPCertificateTriggersWithoutAssocsForED(batchRunDt, activeCaseNumbersWithAssoc);			
			if (activeEdCargosWithoutAssocs!=null && activeEdCargosWithoutAssocs.length!=0){
				if (activeEDCargosCombinedList!=null && activeEDCargosCombinedList.size()>0){
					activeEDCargosCombinedList.addAll(Arrays.asList(activeEdCargosWithoutAssocs));
				}else{
					activeEDCargosCombinedList = new ArrayList(Arrays.asList(activeEdCargosWithoutAssocs));
					
				}
			}
			
			if (activeEDCargosCombinedList!=null && activeEDCargosCombinedList.size()>0){
				activeEdCargos = (EdEligibilityCargo[]) activeEDCargosCombinedList.toArray(new EdEligibilityCargo[activeEDCargosCombinedList.size()]);
			}
			
			
			if (activeEdCargos!=null && activeEdCargos.length>0){
				
				/**ND-110228*/
				vEdExcludedChildrenCargos=(VEdEligibilityIndvCargo[]) coDAOServices.getChildrenExcludedInAuthPeriod(batchRunDt);
				if (vEdExcludedChildrenCargos!=null && vEdExcludedChildrenCargos.length>0){
					for (VEdEligibilityIndvCargo vEdExcludedChildrenCargo: vEdExcludedChildrenCargos ){		
						childrenExcludedForAuthPeriodList.add(vEdExcludedChildrenCargo.getIndvId());
					}
				}
				
				long[] ispIds= new long[activeEdCargos.length];
				int ispIdIndex=0;
	
				for (EdEligibilityCargo ccapProviderChildAssocCustomCargo: activeEdCargos){		
					ispIds[ispIdIndex]=ccapProviderChildAssocCustomCargo.getIspId();
					ispIdIndex++;
				}							
				
				
				if (ispIdIndex>0){
					/**Fetch Cancelled ED Records updated on Batch Run Date*/
					cancelledEdCargos=(EdEligibilityCargo[]) coDAOServices.getCancelledEDRecordsForCCAPCerts(ispIds);
					
					if(cancelledEdCargos!=null && cancelledEdCargos.length>0){
						
						/**Compare active and history ED records*/
						for (EdEligibilityCargo activeEdCargo: activeEdCargos){
							for (EdEligibilityCargo cancelledEdCargo: cancelledEdCargos){
						
								if(activeEdCargo.getIspId()==cancelledEdCargo.getEdgTraceId()){
									
									/**Check if LOC or Copay has changed--> add caseNum and ProviderId to final Map*/
									if (activeEdCargo.getChildIndvId()==cancelledEdCargo.getChildIndvId() 
											&&( (activeEdCargo.getLevelOfCareCd()==null ||!activeEdCargo.getLevelOfCareCd().equalsIgnoreCase(cancelledEdCargo.getLevelOfCareCd()))
											|| activeEdCargo.getCopayAmt()!=cancelledEdCargo.getCopayAmt())){
									
										addToFinalMap(finalMap, activeEdCargo.getCaseNum(),((Long)activeEdCargo.getProviderId()).intValue() );
								
	
									/**Check Household Size is decreased--> add caseNum and ProviderId of the excluded child to final Map*/
									}if( activeEdCargo.getEdgSize()<cancelledEdCargo.getEdgSize() && !activeChildrenList.contains(cancelledEdCargo.getChildIndvId()) ){
										CoDebugger.debugInformation("Household Size is decreased ");
										if (!childrenExcludedForAuthPeriodList.contains(cancelledEdCargo.getChildIndvId())){
											CoDebugger.debugInformation("Not an excluded child: "+cancelledEdCargo.getChildIndvId());
	
											addToFinalMap(finalMap, activeEdCargo.getCaseNum(),((Long)cancelledEdCargo.getProviderId()).intValue() );
										}
										else{
											CoDebugger.debugInformation("Excluded child: "+cancelledEdCargo.getChildIndvId());
	
											addToFinalMap(finalMap, activeEdCargo.getCaseNum(),0 );
										}
											
	
									}
								}
							}
	
						}
					}
				}
			}
		}catch (CoException e) {
			CoDebugger.debugException(e.getMessage(), e);
			CoDebugger.debugInformation("Exception while fetching Updated ED records. Doc Id: "
							+ CoConstants.DOC_NDCCAP063+CoConstants.COMMA+ CoConstants.DOC_NDCCAP064+ e);
		}
	}

	
	private void getTriggersForNewAndUpdatedAssociations(Map<Long, Set<Integer>> finalMap, String batchRunDt) {
		
		PmProviderChildAssocCargo[]  assocCargos=null;

		try {
			 assocCargos=(PmProviderChildAssocCargo[]) coDAOServices.getCCAPCertificateTriggersForVM(batchRunDt);
	
		
		if (assocCargos!=null && assocCargos.length>0){
			
			/**Created for Updated Association Dates*/
			long[] updatedProviderChildAssocIds = new long[assocCargos.length];
			int associationIndex=0;
			
			
			for (PmProviderChildAssocCargo ccapProviderChildAssocCustomCargo: assocCargos){
				
				/**Add new and active associations*/
				if (ALSOPUtil.getStringFromTS(ccapProviderChildAssocCustomCargo.getCreateDt()).equals(asOfDate)){
					addToFinalMap(finalMap, ccapProviderChildAssocCustomCargo.getCaseNum(),ccapProviderChildAssocCustomCargo.getProviderId().intValue() );

			
				/**Store records with updated Associations Dates for later comparison*/
				} else if (ALSOPUtil.getStringFromTS(ccapProviderChildAssocCustomCargo.getUpdateDt()).equals(asOfDate)){
					updatedProviderChildAssocIds[associationIndex]=ccapProviderChildAssocCustomCargo.getProviderChildAssocId();
					associationIndex++;
			
				}					
			}
			
			/**Compare and Check if Associations dates have been updated*/

			if (associationIndex>0){

				PmProviderChildAssocCargo[] pmProviderChildAssocCargos= coDAOServices.getAssocHistoryRecords(updatedProviderChildAssocIds, batchRunDt);
					if (pmProviderChildAssocCargos!=null &&pmProviderChildAssocCargos.length>0){
						for (PmProviderChildAssocCargo pmProviderChildAssocCargo: pmProviderChildAssocCargos){
							for (PmProviderChildAssocCargo assocCargo: assocCargos){
								if (pmProviderChildAssocCargo.getProviderChildAssocId().equals(assocCargo.getProviderChildAssocId())
										&& (pmProviderChildAssocCargo.getAssignBegDt()!=assocCargo.getAssignBegDt()
											|| pmProviderChildAssocCargo.getAssignEndDt()!=assocCargo.getAssignEndDt()))
									addToFinalMap(finalMap, assocCargo.getCaseNum(),assocCargo.getProviderId().intValue() );						
								}
							}
						}
					}		
					/**Note: Final Map contains all the case provider pairs from VM flow for which triggers need to be inserted */	
				}
			} catch (CoException e) {
				CoDebugger.debugException(e.getMessage(), e);
				CoDebugger.debugInformation("Exception while fetching records for New And Updated Associations. Doc Id: "
								+ CoConstants.DOC_NDCCAP063+CoConstants.COMMA+ CoConstants.DOC_NDCCAP064+ e);
			}				
		}
		
	

	private void addToFinalMap(Map<Long, Set<Integer>> finalMap, long caseNum, int providerId) {
		if(!finalMap.containsKey(caseNum)){
			Set<Integer> providers=new TreeSet<Integer>();
			providers.add(providerId);
			finalMap.put(caseNum, providers);
		}else{
			finalMap.get(caseNum).add(providerId);
		}			
	}
	
	private String getBatchRunDt(String asOfDate) {
		SimpleDateFormat format=new SimpleDateFormat(CoConstants.DATE_FORMAT);
		Date parsedDate = null;
		try {
			parsedDate = format.parse(asOfDate);
		} catch (ParseException e) {
			CoDebugger.debugException(e.getMessage(), e);
			CoDebugger
					.debugInformation("Exception while fetching Batch Run Date. Doc Id: "
							+ CoConstants.DOC_NDCCAP063 + e);
		}
		format=new SimpleDateFormat(CoConstants.DATE_FORMAT_DD_MMM_YYYY);
		
		return format.format(parsedDate);
	}	
	/**CR 932 Ends*/
	
	
	
} //end of class;
