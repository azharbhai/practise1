����   3c  *gov/state/nextgen/co/bo/NDCCAP064Assembler  #gov/state/nextgen/co/bo/DCAssembler  'gov/state/nextgen/co/bo/CoBaseAssembler <init> ()V Code
     LineNumberTable LocalVariableTable this ,Lgov/state/nextgen/co/bo/NDCCAP064Assembler; getChimesCorrespondence �(Lgov/state/nextgen/common/cargo/custom/COCorrespondence;)Lgov/state/nextgen/co/util/xsd/schema/othernotices/ChimesCorrespondence; 
Exceptions  .gov/state/nextgen/common/exception/CoException  Fgov/state/nextgen/co/util/xsd/schema/othernotices/ChimesCorrespondence
    java/text/SimpleDateFormat  dd-MMM-yyyy
     (Ljava/lang/String;)V
   " ! 6gov/state/nextgen/common/cargo/custom/COCorrespondence # $ getGenerateDate ()Ljava/sql/Timestamp;
 & ( ' java/sql/Timestamp ) * getTime ()J
 , . - java/lang/Long / 0 valueOf (J)Ljava/lang/Long;
 2 4 3 java/text/DateFormat 5 6 format &(Ljava/lang/Object;)Ljava/lang/String; 8 
MM/dd/yyyy : Jgov/state/nextgen/co/util/xsd/schema/othernotices/HouseholdCopyCertificate
 9 
   = > ? getCaseAppNumber ()Ljava/lang/String;
 , A B C 	parseLong (Ljava/lang/String;)J
   E F ? getChipAppNum	  H I J coDAOServices +Lgov/state/nextgen/common/bo/CoDAOServices;
   L M * getCoReqSeq
 O Q P )gov/state/nextgen/common/bo/CoDAOServices R S getCoRequestHistoryByCoReqSeq (J)[Ljava/lang/Object; U >[Lgov/state/nextgen/common/cargo/custom/CoRequestHistoryCargo;
 W Y X ;gov/state/nextgen/common/cargo/custom/CoRequestHistoryCargo Z [ 
getCaseNum ()Ljava/lang/Long; ] java/util/HashMap
 \  ` java/util/ArrayList
 _  c   e T-NDCCAP064
 g i h )gov/state/nextgen/co/util/CONoticeUtility j k getMetaData �(Lgov/state/nextgen/common/cargo/custom/COCorrespondence;Ljava/lang/String;)Lgov/state/nextgen/co/util/xsd/schema/othernotices/MetaData;
  m n o setMetaData ?(Lgov/state/nextgen/co/util/xsd/schema/othernotices/MetaData;)V q <gov/state/nextgen/co/util/xsd/schema/othernotices/NoticeData
 p 
  t u v populateManualData(Lgov/state/nextgen/common/cargo/custom/COCorrespondence;Lgov/state/nextgen/co/util/xsd/schema/othernotices/HouseholdCopyCertificate;Lgov/state/nextgen/co/util/xsd/schema/othernotices/NoticeData;)Lgov/state/nextgen/co/util/xsd/schema/othernotices/NoticeData;
  x y z setCoPayCertDatesEdEligibility �(Ljava/text/DateFormat;Lgov/state/nextgen/co/util/xsd/schema/othernotices/HouseholdCopyCertificate;Ljava/lang/Long;)Lgov/state/nextgen/co/util/xsd/schema/othernotices/HouseholdCopyCertificate;
 , | } * 	longValue
 O  � � findByCasenumHistnavAssocDates E(J)[Lgov/state/nextgen/common/cargo/custom/PmProviderChildAssocCargo;
 � � � ?gov/state/nextgen/common/cargo/custom/PmProviderChildAssocCargo � � getProviderId ()Ljava/lang/Integer;
 � � � java/lang/Integer � � intValue ()I
 O � � S getProviderDetailsMetaData � =[Lgov/state/nextgen/common/cargo/custom/PmCcapProvidersCargo; � Bgov/state/nextgen/co/util/xsd/schema/othernotices/HccChildCareList
 � 
  � � � providertype Q([Lgov/state/nextgen/common/cargo/custom/PmCcapProvidersCargo;)Ljava/lang/String;
  � � � populateProviderName d(Ljava/lang/Integer;[Lgov/state/nextgen/common/cargo/custom/PmCcapProvidersCargo;)Ljava/lang/String; �  
 � � � * getChildIndvId
 \ � � � put 8(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
 _ � � � add (Ljava/lang/Object;)Z
 O � � S getDcIndividualDetails � 4[Lgov/state/nextgen/common/cargo/custom/DcIndvCargo;
 � � � 1gov/state/nextgen/common/cargo/custom/DcIndvCargo � * 	getIndvId
 O � � � getLevelofCarePartstatusASC +(JJLjava/sql/Timestamp;)[Ljava/lang/Object; � @[Lgov/state/nextgen/common/cargo/custom/VEdEligibilityIndvCargo;
 � � � $ getAssignBegDt
 � � � =gov/state/nextgen/common/cargo/custom/VEdEligibilityIndvCargo � $ getPaymentBegDt
 & � � � equals (Ljava/sql/Timestamp;)Z
 � � � ? getLevelOfCareCd � XC
 � � � ? getPartStatusCd
 � � � java/lang/String � �
 � � � 'gov/state/nextgen/common/util/ALSOPUtil � � getLastDayOfMonth *(Ljava/sql/Timestamp;)Ljava/sql/Timestamp;
 \ � � � containsKey
 � � � $ getDobDt
 & � � � 	compareTo (Ljava/sql/Timestamp;)I
 � � � $ getAssignEndDt
 � � � � getStringFromTS ((Ljava/sql/Timestamp;)Ljava/lang/String;
 � � � � getAllMonths :(Ljava/sql/Timestamp;Ljava/sql/Timestamp;)Ljava/util/List; � � � java/util/List � � iterator ()Ljava/util/Iterator; � � � java/util/Iterator � � next ()Ljava/lang/Object;
 � � �  setHccAssociationStartDate
 � � �  setHccAssociationEndDate � � � hasNext ()Z
 � ? getFirstName
 �	
 ? 
getMidName
 � ? getLastName
 g getFormattedName Q(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/StringBuilder;
 � / 6
 �  setHccChildName
  agegroup Y(Ljava/lang/String;Lgov/state/nextgen/common/cargo/custom/DcIndvCargo;)Ljava/lang/String;
 �  setHccProviderName
 � 
 � ! ? getHccAssociationStartDate
 �#$ ? getDob
 �&' ? getHccAssociationEndDate
 )*+ checkifAgeGroupChanges 9(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z
 �-. ? getHccChildName
 �01 ? getHccProviderName3 HT
 �567 equalsIgnoreCase (Ljava/lang/String;)Z9 PT; HO= HR
 �?@  setHccLevelOfCare
 �BC ? getHccLevelOfCare
EGF #gov/state/nextgen/common/bo/BiUtils �H &(Ljava/util/Date;)Ljava/sql/Timestamp;
EJKH getFirstDayOfNextMonth
EMNH getLastDayOfPreviousMonth
 �PQR getTSfromString ((Ljava/lang/String;)Ljava/sql/Timestamp; �TU � size
WYX java/lang/ExceptionZ ? 
getMessage
\^] (gov/state/nextgen/common/util/CoDebugger_  debugInformationa FT
 �cd ? getHccCareLocation
 fgh setlocationofcare �(Ljava/lang/Integer;[Lgov/state/nextgen/common/cargo/custom/PmCcapProvidersCargo;Lgov/state/nextgen/co/util/xsd/schema/othernotices/HccChildCareList;)V
 �jk  setHccCareLocationm 	01-OCT-17o 	01-MAR-17q 
10/01/2017
 �st ? getHccStateMaximumRate
 vwx getAgeGroupforassocDate J(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;z IN
 O|}~ getOLDStateMaxRate ](Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)[Ljava/lang/Object;� >[Lgov/state/nextgen/common/cargo/custom/CcapStateMaxRateCargo;
 O��~ getStateMaxRate
��� ;gov/state/nextgen/common/cargo/custom/CcapStateMaxRateCargo�� 
getMaxRate ()D
 g��� getFormattedAmount (D)Ljava/lang/String;
 ���  setHccStateMaximumRate� 	Part Time� 	Full Time
 \T
 \��� values ()Ljava/util/Collection;�T� java/util/Collection
 9��� getHccChildCare ()Ljava/util/List; ���� addAll (Ljava/util/Collection;)Z
 O�� S getEligibilityInfo� ;[Lgov/state/nextgen/common/cargo/custom/EdEligibilityCargo;
��� 8gov/state/nextgen/common/cargo/custom/EdEligibilityCargo� $ getEligibilityBegDt
 9��  setEligibilityStartDate
 p��� setHouseholdCopyCertificate O(Lgov/state/nextgen/co/util/xsd/schema/othernotices/HouseholdCopyCertificate;)V� java/lang/StringBuilder� ! NDCCAP064Assembler -->Exception 
� 
���� append -(Ljava/lang/String;)Ljava/lang/StringBuilder;
��� ? toString
\��� debugException *(Ljava/lang/String;Ljava/lang/Throwable;)V
 ��� setNoticeData A(Lgov/state/nextgen/co/util/xsd/schema/othernotices/NoticeData;)V coCorrespondence 8Lgov/state/nextgen/common/cargo/custom/COCorrespondence; chimesCorrespondence HLgov/state/nextgen/co/util/xsd/schema/othernotices/ChimesCorrespondence; dateFormat1 Ljava/text/DateFormat; refDate Ljava/lang/String; dateFormat2 householdCopyCertificate LLgov/state/nextgen/co/util/xsd/schema/othernotices/HouseholdCopyCertificate; case_num Ljava/lang/Long; coRequestHistorycargo combo Ljava/util/HashMap; childTableMap allowableActivityTableList Ljava/util/List; childIndvIds Ljava/util/ArrayList; age levelofcare assnEndDate metaData <Lgov/state/nextgen/co/util/xsd/schema/othernotices/MetaData; 
noticeData >Lgov/state/nextgen/co/util/xsd/schema/othernotices/NoticeData; 
childTable newchildTable associationDates childCareListExisting DLgov/state/nextgen/co/util/xsd/schema/othernotices/HccChildCareList; pmProviderChildAssocCargos B[Lgov/state/nextgen/common/cargo/custom/PmProviderChildAssocCargo; pmProviderChildAssocCargo ALgov/state/nextgen/common/cargo/custom/PmProviderChildAssocCargo; pmProviderCargos providerName dcIndvCargos dcIndvCargo 3Lgov/state/nextgen/common/cargo/custom/DcIndvCargo; edIndvEligibilityCargos futureChild Z excludedChild excludedChildMonth Ljava/sql/Timestamp; prevLevelOfCare edIndvEligibilityCargo1 ?Lgov/state/nextgen/common/cargo/custom/VEdEligibilityIndvCargo; edIndvEligibilityCargo months 	beginDate lastday 
assocDates 	childName count I agecheck edIndvEligibilityCargoTest edIndvEligibilityCargoLast cnt childCareListCurr lastDay newCurrentchildCareList firstDayOfNextMonth e Ljava/lang/Exception; 
childLists effectiveDt oldEffectiveDt 
eff_beg_dt ageGroup ccapStateMaxRateCargos newchildLists maxrate edEligibilityDao LocalVariableTypeTable 8Ljava/util/HashMap<Ljava/lang/Long;Ljava/lang/Integer;>; �Ljava/util/HashMap<Ljava/util/HashMap<Ljava/lang/Long;Ljava/lang/Integer;>;Ljava/util/List<Lgov/state/nextgen/co/util/xsd/schema/othernotices/HccChildCareList;>;>; \Ljava/util/List<Lgov/state/nextgen/co/util/xsd/schema/othernotices/HccActivityAllowedList;>; 'Ljava/util/ArrayList<Ljava/lang/Long;>; VLjava/util/List<Lgov/state/nextgen/co/util/xsd/schema/othernotices/HccChildCareList;>; &Ljava/util/List<Ljava/sql/Timestamp;>; StackMapTable! :gov/state/nextgen/co/util/xsd/schema/othernotices/MetaData�
$&% :gov/state/nextgen/common/cargo/custom/PmCcapProvidersCargo' ? getOrganizationType
$)*+ getHistNavInd ()C
$-. ? 	getCareSw0 P2 PA
 O456 getLocationofcareforAR :(Ljava/lang/Integer;Ljava/lang/String;)[Ljava/lang/Object;8 7[Lgov/state/nextgen/common/cargo/custom/PmAddressCargo;
 :;< locationofcare ~(Lgov/state/nextgen/co/util/xsd/schema/othernotices/HccChildCareList;[Lgov/state/nextgen/common/cargo/custom/PmAddressCargo;)V> C
 O@A S getLocationofcareforARtypeCC >[Lgov/state/nextgen/common/cargo/custom/PmProviderPersonCargo;
EGF ;gov/state/nextgen/common/cargo/custom/PmProviderPersonCargoH ? getInHomeSwJ YL CTN NP OR
 RS  printStackTrace provider_id Ljava/lang/Integer; 	pmaddress pmProviderPerson 0Lgov/state/nextgen/common/exception/CoException;
Z\[ 4gov/state/nextgen/common/cargo/custom/PmAddressCargo] ? getAddrLine1
Z_` ? getAddrLine2
bdc java/lang/Systeme ? lineSeparator
 ��
Zhi ? getAddrLine3
Zkl ? getAddrCity
Zno ? getAddrStateCd
Zqr ? getAddrZip5
Ztu ? getAddrZip4w -
�y�z -(Ljava/lang/Object;)Ljava/lang/StringBuilder;
| �} java/lang/Object addressLine Ljava/lang/StringBuilder; 
adresLine4 addrCity addrStateCd addrZip5
 ��� ? getAge
 ���� parseInt (Ljava/lang/String;)I� TO� PS� OT
 O��� findAllProgramsCaseAsc >(J)[Lgov/state/nextgen/common/cargo/custom/EdEligibilityCargo;� Fgov/state/nextgen/co/util/xsd/schema/othernotices/HccBenefitPeriodList
� 
�� Z *
��� * 	getEdgNum
 O��� getReviewDate (JJ)[Ljava/lang/Object;� ?[Lgov/state/nextgen/common/cargo/custom/EdCaseRecertDatesCargo;
��� <gov/state/nextgen/common/cargo/custom/EdCaseRecertDatesCargo� $ getRecertReviewDueDt
��� +gov/state/nextgen/common/util/DateFormatter�� dateToString $(Ljava/util/Date;)Ljava/lang/String;
� �
 2� 5�
���  setHccStartDate
��� $ getPaymentEndDt
���  setHccEndDate
���� getCopayAmt
���  setHccCopayAmount
��� ? getHccCopayAmount
��� ? getHccStartDate
��� ? getHccEndDate
 9��� getHccBenfitPeriod
 9��  setRecertDate� BNDCCAP064Assembler -->Exception in populating Cert date and copay  edEligibilityCargo benfitPeriodList prevBenefitPeriod HLgov/state/nextgen/co/util/xsd/schema/othernotices/HccBenefitPeriodList; currBenfitPeriod 
reviewDate edCaseRecertDatesCargo edCaseRecertDatesCargo1 >Lgov/state/nextgen/common/cargo/custom/EdCaseRecertDatesCargo; edEligCargo :Lgov/state/nextgen/common/cargo/custom/EdEligibilityCargo; coPay ZLjava/util/List<Lgov/state/nextgen/co/util/xsd/schema/othernotices/HccBenefitPeriodList;>;
 ��� getAgeInYears 8(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/Double;
 ��� getAgeGroup &(Ljava/lang/Double;)Ljava/lang/String;
 ��� � getFirstDayOfMonth dob assocBeginDt 
assocEnddt ageGroupBegin ageGroupEnd ageCheck ageBegin Ljava/lang/Double; ageEnd ageCurrentMonth agePrevMonth� java/lang/Double
��� java/util/Calendar�� getInstance ()Ljava/util/Calendar;
���� setTime (Ljava/util/Date;)V
� � after "java/lang/IllegalArgumentException Can't be born in the future
 
�	
 get (I)I
� / (D)Ljava/lang/Double; 	assocDate 
assocDate1 Ljava/util/Calendar; dob1 year1 year2 month1 month2 day1 day2
 java/lang/Boolean / (Z)Ljava/lang/Boolean; begin_dt end_dt Ljava/lang/Boolean;
�"#� doubleValue@       @      @      
$
$	
$
$./ ? getBusinessName1 ;NDCCAP064Assembler -->Exception in populating providerName 3 CC5 ML7 OS9 SA
;=< *edu/emory/mathcs/backport/java/util/Arrays>? asList %([Ljava/lang/Object;)Ljava/util/List;
$AB ? getLicenseTypeD ARF APH FCJ GFL GHN LFP SDR TR �UV � containsX CE CE_licenseTypes [Ljava/lang/String; CE_licenseTypeList licenseTypeZ L(Lgov/state/nextgen/common/cargo/custom/COCorrespondence;)Ljava/lang/Object;
 `   
SourceFile NDCCAP064Assembler.java !            	   3     *� 
�       
    D  E                       	  �  4  
|� Y� M� Y� N-+� � %� +� 1:� Y7� :� 9Y� ;::+� <� +� <� @� +:� N+� D� +� D� @� +:� 8*� G+� K� N� T:� #�� 2� 2� V� 2� V:� \Y� ^:� \Y� ^:	� _Y� a:
� _Y� a:b:b:b:+d� f:,� l� pY� r:*+� s::� _Y� a:::*� w:*� G� {� ~:�	Z��	TY:�66�f2:*� G� �� ��� �� �:� �Y� �:� _Y� a:*� �:*� �� �:� �:� .� �� +� �� �W	� �W� �� +� �W*� G� �� �� �:�����Y:!�6 6��!2:��*� G� {� �+� � �� �:"6#6$:%b:&:'"Y:+�6*6)� $+)2:(� �(� �� Ù 
(:'� �))*���'�B'� ��:"Y:+�6*6)� ,+)2:($� �(� ̶ ϙ (� �� �:%6$�))*���	� ٙU� �� �� ߞ 6#� �� �� ��� �� _Y� a:� � �:� �� � �:(%� %� �:� �%� �:((� � :*� =*� � � &:))� �:+� �Y� �:,,)� � �,+� � �,�  W*� ���� �� � �� ������:((� �:((�*�:�6)"�4"��.� � :+�+� � � �:*6,:-� �Y�:."�d6/"/2:."Y:3�6261� 0312:0*�0� �� � ϙ � �Y�:-0:-� �112���#� *�"*�*�%�(6,� �Y� �:00�� �0�%� �0�,�0�/�-� 92-� Ƕ4� 8:� V:-� Ƕ4� <:� @-� �:� 6.� �2�4� 8:�  :.� Ƕ4� <:� 
.� �:� b� Ϛ 
:&� � b� ϙ $� &:)� �>0�>���A�4� ,� 0�>0:�t,� Q�A�4� D-� ?-� ��D:10-� ��I� � �1� � ��  W0�>0:�!-� A,� <-� ��L:10-� �� � �1� � ��  W0�>0:� �,� �-� �� �Y� �:1-� ��L� � ��  W1*�� �1*�%� �1�>1�,�1�/�1�  W0-� ��I� � �0� �0�>0:� S,� N-� I*��O�D:1*��O�I:202� � �1� � ��  W0�A�>0:�))�S � 0�  W+� ���  "� �:�>� :))�V�[� � :)� |)� � � �:((�A� a(�A8�4� (�A`�4� (�A<�4� 7(�b� 6*� �(�e(�b� (�bb�4� (��i� 
(��i)� ���l:(n:)p:*:+:,� � :.� �.� � � �:--��O*�O� ߜ -�%�O*�O� ߞ l� �Y� �://-�%� �/*� �/-�b�i/-�,�/-�/�/-�A�>-*�O�L� � �-�  W/�  W� -�  W.� ��T� � :.�.� � � �:--�A� �-�A8�4� -�A`�4� -�A<�4� �-�r� �#� *-�-�%�"�u:+� y:+-��O*�O� ߜ *� G+-�A)�{�:,� *� G+-�A(���:,,� :,�� 4,2� -,2����://� -/��� (/� #-���� ,� -���� 
-���.� ���� :--�V�[� �� +� �� �W	� �W� ��I����� � :� >� � � �:�A8� ϙ ��>� �A`� ϙ ��>� ���	� :	��� 2	��� *	���� � � ��� ���� W*� G� {����:�� 2��� ����� :��Y����V������,��,� ���W�	]	`W �
U
XW    "   R  T  U " W - X 6 Z 9 \ @ ] L ^ V _ b ` e a u b � c � d � h � i � j � k � l � m � n � r � s � u � w � y � z � { � | � 
 � �# �8 �M �V �_ �g �t �y �} �� �� �� �� �� �� �� �� �� �  � � �
 � �" �2 �6 �9 �C �P �e �w �� �� �� �� �� �� �� �� �� �� �� �� �� �� � � �  �* �4 �> �H �U �\ �s �x �| �� �� �� �� �� �� �� �� �� �� �� �� �� � �  �3	<
FPZdiw|��������� �"�'�(�)�* 1347:(>/@3C6DMFWGgHqI{J�K�L�P�R�S�T�U�V�W�Y�Z�\�]�_�abcde(h2iBjIkPlToWparns{t�u�v�w�y������ ����������������4�<�I�^�e�h�o�y�~����������������������������(�2�5�?�I�a�������������������	�	�	�	$�	+�	3�	:�	B�	I�	L�	S�	]�	b�	j�	}�	� �	� �	�	�		�	�	�	�	�	�


(
9
?!
N&
U)
Z+
t.
z/   � F  
|      
|��  
t��  
j��  "
Z��  -
O��  6
F��  9
C��  u %� U  �	���  �	��� 	 �	��� 
 �	���  �	���  �	���  �	���  �	���  �	���  �	���  �	���  �	���  �	~�� 	=�� 8Y�� MD� � g* �� t�� ��� � ���� ��� � " ��� #��� $��� %
}�� &z�� '" �� (e �� (� |�� ( .�� ) ' � +  � ,sw� (�F )�� *�� ,� � -��� .�� /� #�� 0<�	� 0W /
� 1� ,
� 1� u� 1n 9
� 1{ ,� 2�  ) m� (~	� (�� )��� *��� +��� ,� �� -� ]� /a �� -	 � /	b  -	� /� 
9 � 
Z     R  �	��  �	�� 	 �	�� 
 �	��  �	��  �	��  �	�� � |� (  � L� O      2 � 2 9 ,  4� �      2 � 2 9 , \ \ � _ � � �  p � � � �" "  � K      2 � 2 9 , \ \ � _ � � �  p � � � �" �" � � �  /� ) "     2 � 2 9 , \ \ � _ � � �  p � � � �" �" � � � �  �  � C ,     2 � 2 9 , \ \ � _ � � �  p � � � �" �" � � � � � � � & � �  �  �  (     2 � 2 9 , \ \ � _ � � �  p � � � �" �" � � � � � � � & � �  �  ,     2 � 2 9 , \ \ � _ � � �  p � � � �" �" � � � � � � � & � �  �  %� # (     2 � 2 9 , \ \ � _ � � �  p � � � �" �" � � � � � � � & � �  � L ��  +     2 � 2 9 , \ \ � _ � � �  p � � � �" �" � � � � � � � & � � �  �  9�  (     2 � 2 9 , \ \ � _ � � �  p � � � �" �" � � � � � � � & � �  �  �� 1 ,     2 � 2 9 , \ \ � _ � � �  p � � � �" �" � � � � � � � & � � �  �  � 6 4     2 � 2 9 , \ \ � _ � � �  p � � � �" �" � � � � � � � & � � � � � � �  �  )�  0     2 � 2 9 , \ \ � _ � � �  p � � � �" �" � � � � � � � & � � � � � � �  � K �	� R� B� �� O�  ,     2 � 2 9 , \ \ � _ � � �  p � � � �" �" � � � � � � � & � � �  �  �  *     2 � 2 9 , \ \ � _ � � �  p � � � �" �" � � � � � � � & � � �  �  )     2 � 2 9 , \ \ � _ � � �  p � � � �" �" � � � � � � � & � � � W� 	�  *     2 � 2 9 , \ \ � _ � � �  p � � � �" �" � � � � � � � & � �  �  � = *     2 � 2 9 , \ \ � _ � � �  p � � � �" �" � � � � � � � & � � � �  )	�  *     2 � 2 9 , \ \ � _ � � �  p � � � �" �" � � � � � � � & � �  �  � * /     2 � 2 9 , \ \ � _ � � �  p � � � �" �" � � � � � � � & � � � � � �  �  � � /     2 � 2 9 , \ \ � _ � � �  p � � � �" �" � � � � � � � & � � � � � � � �  � 	 /     2 � 2 9 , \ \ � _ � � �  p � � � �" �" � � � � � � � & � � � � � �  �  � = /     2 � 2 9 , \ \ � _ � � �  p � � � �" �" � � � � � � � & � � � � � � � �  $,� , �� �  /     2 � 2 9 , \ \ � _ � � �  p � � � �" �" � � � � � � � & � � � � � �  �  �  -     2 � 2 9 , \ \ � _ � � �  p � � � �" �" � � � � � � � & � � � � � � W	�  "     2 � 2 9 , \ \ � _ � � �  p � � � �" �" � � � �  �  �       2 � 2 9 , \ \ � _ � � �  p � � � �" "  �       2 � 2 9 , \ \ � _ � � �  p � � � �"  �  � $      2 � 2 9 , \ \ � _ � � �  p � � � �" � �  �       2 � 2 9 , \ \ � _ � � �  p � � � �"  �  � E      2 � 2 9 , \ \ � _ � � �  p � � � �"  � %�� 	      2 � 2 9 , \ \ � _ � � �  p � � � � W gh  	  �    �,��,���,2��,2�#�\y,2�#� ϙM,2�(S� ,2�(P�7,2�,� ,,2�,/�4� *� G+1�3�7:*-�9�T,2�,� �,2�,=�4� �*� G+� ��� +� {�?�B:� u�� o2� h2�D� *2�DI�4� *� G+K�3�7:*-�92�D� �2�DM�4� �*� G+1�3�7:*-�9� �*� G+1�3�7:� ��� �2� �*-�9� �+� ~*� G+1�3�7:� i�� c2� \*-�9� R,2�#� IO,2�#� ϙ :,2�(S� ,2�(P� $*� G+1�3�7:*-�9� 
:�Q�   ��      v   5 7 =9 U; e= l@ �A �B �D �F �G �J �KLOP%Q7R>WAZE\U]g^nb�d�e�j�l�n    z   �      �TU   �� �   ���  e V8  � �WC  � V8  V8 % V8 U V8 � V8 � X     	=1� qB3� +/-Y  ;<  	  |    `,�^,��Y,2�S��Yb��N,2�Y� -,2�Y��W,2�^� $-��Y�a���,2�^�f������W,2�g� $-��Y�a���,2�g�f������W��Yb��:,2�j:,2�m:,2�p:� ��Y����������W� ��Y����������W� ��Y����������W,2�s� ��Yv��,2�s������W� -��Y�a����x����W-b�{� +��i� +-���i�       n   r s t "u -w 6x Wz `{ �~ � �� �� �� �� �� �� �� ���
�&�+�E�N�T�W�_�    R   `      `��   `V8  F~  � ��  � ���  � ���  � ���    > 
� -�))� A   �7�� � � �  $�    �7     	   �     _,����� 
yL� N,����� ,����� 
�L� 1,����� ,����� 
�L� ,����� �L+�       & 	  � � � (� ,� F� J� Y� ]�         _       _��    _��       y z  	  �    �*� G-� {��:� _Y� a:��Y��::6b:	:
:�(��"Y:�66�2:*� G��������:

� �
�� �
2:����:	��Y��:��� +��������� +������b:���� ����:��� ���� ��:��� 
:� 8�����4� �ö��ƶ�� �  W::��� 	���  W����,���� W,	�̧ :��YϷ��V������,�   knW     � .  � � � � "� %� )� ,� /� :� O� e� p� v� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� ��
���!�+�/�3�6�>�E�O�Y�e�kp�    �   �      ���   ���   ���  ^��  U��  L��  "I��  %F  )B�� 	 ,?�� 
 /<��  O ��  � ��� p       U��    � � H   2 9 ,� ��� ��� �  � V   2 9 ,� ��� �����  � ! �&�    2 9 ,� ��� ��� �  �    2 9 ,� ��� ���  �    2 9 , W  u v        	   S     -,��-�       
        *           ��    ��    ��  *+  	  �     �::6*+,��:*+-��:*��:*��:� ϙ 	6� 6+�O+�O�� Ù ?*+,��:	*+,�O�L� ��:
*	��:*
��:� ϙ 	6� 6�       N      	   ! ) 3 9 < M! U" f# n$ v% �& �( �+    p    �       ���    ���    ���   ���   ���  	 ���   {��   s��  U 4�� 	 f #�� 
   / � 9 	  � � � � ���  � I���  ��  	  �     ���N��:+�O��-,�O��-� � �Y��-�6�6d6-�6�6		� -�6
�6
� ��� 	� �����       R   0 1 	2 3 4 #5 .7 58 =9 D: K; S< Z= a> i? p@ sB vC }D �E    z    �       ���    ��   �  	 ~  5 R  = J  D C�  K <  S 4 	 a  
 i 
    ' � .��� G 
  � ���  	 wx  	   �  
   2:::�:*-+��:*-,��:	*��:*	��:�       & 	  M N O 	P Q R T 'U /[    f 
   2       2�    2�    2��   /�   ,��  	 )��   #�    ��   �� 	 ��  	   �     TM+�!$�� 
yM� A+�!&�� +�!$�� 
�M� $+�!(�� +�!&�� 
�M� �M,�       * 
  ` a b c *d .e Gf Kg Nh Rj         T       T��   R�     �  �  � �  	  u     �bN,� �,�� �,2� �,2�#� .y,2�#� ϙ ,2�*,2�+,2�,��N� �,2�#� xO,2�#� ϙ i,2�-� ,2�-N� V,2�*� ,2�,� ,2�+� ;,2�*,2�+,2�,��N� :��Y0���V������-�   � �W     F   n q s *u 0v <u Cx Oy ^z g{ n| �} �~ �} �� �� ��    4    �       �TU    �� �   ���  �      � F �*[W  � �  	  s     �bM� �Y2SY4SY6SY�SY8SN-�::+� �+2� �+2�@� }+2�@:C�4� 
EM� cG�4� I�4� K�4� 
MM� ;O�4� Q�4� S�4� 
OM� �T � WM,�       :   � � &� ,� ?� G� R� V� z� ~� �� �� �� ��    >    �       �� �   � ��  & �YZ  , �[�  G r\�    " � Y   � �] � �    � A ^        	   &     *+�_�                  a   b