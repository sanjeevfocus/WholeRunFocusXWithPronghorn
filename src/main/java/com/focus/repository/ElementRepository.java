package com.focus.repository;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ElementRepository {
	
	
	// INVENTORY OPTIONS ELEMENTS
	
	
	
	@FindBy(xpath="//*[@id='idGlobalError']/div/div[2]")
	public static List<WebElement> errorMessageList;
	
	@FindBy(xpath="//*[@id='idGlobalError']/div/div[1]/button")
	public static List<WebElement> errorMessageCloseBtnList;

	// Elements

	@FindBy(xpath = "//*[@id='txtUsername']")
	public static WebElement username;
	
	@FindBy(xpath = "//*[@id='errmsgDiv']")
	public static WebElement loginPageErrorMesg;

	@FindBy(id = "txtPassword")
	public static WebElement password;

	@FindBy(id = "ddlCompany")
	public static WebElement companyDropDownList;

	@FindBy(id ="btnSignin")
	public static WebElement signIn;

	@FindBy(xpath = "//i[@title='Create Company']")
	public static WebElement companyCreateBtn;

	@FindBy(xpath = "//i[@title='Keyboard ']")
	public static WebElement keyboardBtn;

	@FindBy(xpath = "//i[@title='Refresh']")
	public static WebElement refreshBtn;

	@FindBy(xpath = "//input[@id='chkRememberMe']/following-sibling::span")
	public static WebElement rememberMeChk;

	@FindBy(xpath = "//input[@id='chkRememberPwd']/following-sibling::span")
	public static WebElement rememberPwdChk;

	@FindBy(id = "frgtPwd")
	public static WebElement frgtPwdLnk;

	@FindBy(xpath = "//span[contains(@class,'icon-ok icon-font6')]")
	public static WebElement okButtonInCreateCompany;

	@FindBy(xpath = "//span[contains(@class,'icon-close icon-font6')]")
	public static WebElement cancelButtonInCreateCompany;

	@FindBy(xpath = "//div[@id='Layout_Popup']//input[2]")
	public static WebElement noIn;

	@FindBy(linkText = "Create Company")
	public static WebElement createCompanyScreen;

	@FindBy(xpath = "//*[@id='errmsgDiv']")
	public static WebElement mandatoryMsgs;

	// Fields of Company su Home Page
	@FindBy(xpath = "//*[@id='companyLogo']/parent::a/following-sibling::ul/li[1]")
	public static WebElement companyName;

	@FindBy(xpath = "//*[@id='mainHeader_MainLayout']/a/img")
	public static WebElement focusLogo;

	@FindBy(xpath = "//*[@id='id_focus8_wrapper_default']/aside/section")
	public static WebElement menuBar;

	@FindBy(xpath = "//*[@id='dashName']")
	public static WebElement dashboardName;

	@FindBy(xpath = "//*[@id='dashIcons']")
	public static WebElement dashboardIcons;

	@FindBy(xpath = "(//li[@class='nav-item pt-md-2']/a)[2]")  //div[@id='id_mainlayoutmenu']/ul[2]/li[5]/a
	public static WebElement userNameDisplay;

	@FindBy(xpath = "//div[@class='adminprofile']")     //div[@id='id_mainlayoutmenu']/ul[2]/li[6]
	public static WebElement userNameDisplayLogo;
	
	//*[@id='id_mainlayoutmenu']/ul[2]/li[7]/div  without pronghorn xpath

	@FindBy(xpath = "//div[@id='id_mainlayoutmenu']/ul[2]/li[6]//ul/li[1]")
	public static WebElement changePassword;

	@FindBy(xpath = "//*[@id='exampleFormControlSelect1']")
	public static WebElement languageDropdownInLogout;

	@FindBy(xpath = "//a[contains(text(),'Logout')]")    //div[@id='id_mainlayoutmenu']/ul[2]/li[6]//ul/li[2]
	public static WebElement logoutOption;

	/*@FindBy(xpath = "//*[@id='dashName']")
	public static WebElement dashboard;*/
	
	@FindBy(xpath="//a[text()='Dashboard']")
	public static WebElement dashboard;

	@FindBy(xpath = "//ul[@id='ulCompanyDetails_HomePage']")
	public static WebElement companydetails;

	// Dash board Page
	@FindBy(xpath = "//*[@id='Dashboard_ddlList']//a")
	public static WebElement labelDashboard;

	@FindBy(xpath = "//*[@id='Select_dash']")
	public static WebElement selectDashboard;

	@FindBy(xpath = "//*[@id='Dashboard_AddDash']")
	public static WebElement newAddDashBoard;

	@FindBy(xpath = "//*[@id='Dashboard_Dash_Config']")
	public static WebElement dashboardCustomizationSettings;

	@FindBy(xpath = "//div[@id='login_div']/div[1]//ul/li[3]//i")
	public static WebElement loginPageRestoreBtn;

	@FindBy(xpath = "//div[@id='login_div']/div[1]//ul/li[5]//i")
	public static WebElement loginPageSecurityProtectedCompanyBtn;

	@FindBy(xpath = "//input[@id='donotshow']")
	public static WebElement doNotShowCheckbox;

	@FindBy(xpath = "//span[@class='pull-right']")
	public static WebElement closeBtnInDemoPopupScreen;

	// Financial Menu
	@FindBy(xpath = "//span[@class='icon-financial icon-font1']")
	public static WebElement financialMenu;

	// Fixed Assets Menu
	@FindBy(xpath = "//span[@class='icon-assets icon-font1']")
	public static WebElement fixedAssetsMenu;

	// Production Menu
	@FindBy(xpath = "//span[@class='icon-production icon-font1']")
	public static WebElement productionMenu;

	// Point Of Sale Menu
	@FindBy(xpath = "//div[contains(text(),'Point of Sale')]")
	public static WebElement pointOfSaleMenu;

	// Quality Of Control Menu
	@FindBy(xpath = "//span[@class='icon-quotationanalysis icon-font1']")
	public static WebElement qualityControlMenu;

	// Settings Menu
	@FindBy(xpath = "//span[@class='icon-settings icon-font1']")
	public static WebElement SettingsMenu;
	
	
	@FindBy(xpath = "(//div[@class='btn btn-toolbar dropdown theme_button_color'])[1]")
	public static WebElement stockLedgerFilterExpandBtn;
	
	@FindBy(xpath = "//li[text()='Refine']")
	public static WebElement stockLedgerFilterRefineBtn;
	
	@FindBy(xpath = "//*[@id='cmbColumn']")
	public static WebElement filterRefineLookInDropdownBtn;
	
	@FindBy(xpath = "//*[@id='SearchTxt']")
	public static WebElement filterRefineFindWhatTxt;
	
	@FindBy(xpath = "//*[@id='idDisplay']")
	public static WebElement filterRefineLookIn2DropdownBtn;
	
	@FindBy(xpath = "//*[@id='RefineReports']/div/div[3]/div/input[1]")
	public static WebElement filterRefineOkBtn;
	
	@FindBy(xpath = "(//button[text()='Cancel'])[1]")
	public static WebElement filterRefinCancelBtn;

	// Inventory Menu and Sub Menus
	@FindBy(xpath = "//a[@id='135']/span")
	public static WebElement inventoryMenu;

	@FindBy(xpath = "//div[@id='id_left_mainmenu']/ul/li/a")
	public static List<WebElement> menusList;

	@FindBy(xpath = "//*[@id='mainHeader_MainLayout']/nav/div/ul/li")
	public static List<WebElement> navbarList;

	@FindBy(xpath = "//a[@id='dropdown03']")
	public static WebElement aboutQuickAcessMenu;

	@FindBy(xpath = "//input[@id='id_ai_search_input']")
	public static WebElement aiSearchTextBox;

	@FindBy(xpath = "(//div[@id='mainHeader_MainLayout']//div/input)[3]")
	public static WebElement searchTxtBox;

	@FindBy(xpath = "//div[@id='id_mainlayoutmenu']/ul[2]/li[1]/a")
	public static WebElement productTour;

	@FindBy(xpath = "//div[@id='id_mainlayoutmenu']/ul[2]/li[3]/a")
	public static WebElement alertsOptions;

	@FindBy(xpath = "//div[@id='id_mainlayoutmenu']/ul[2]/li[2]/a")
	public static WebElement recentMenus;

	// AccountsPage

	@FindBy(xpath = "//a[@id='1']/span")
	public static WebElement homeMenu;

	// Masters Menu
	@FindBy(xpath = "//a[@id='1000'][contains(text(),'Masters')]")
	public static WebElement mastersMenu;

	// Accounts
	@FindBy(xpath = "//a[@id='1104']//span[contains(text(),'Account')]")
	public static WebElement accounts;

	// Master Main Header Fields
	@FindBy(xpath = "//a[@id='btnNew']/i")
	public static WebElement masterNewBtn;

	@FindBy(xpath = "//a[@id='btnAddGroup']/i")
	public static WebElement masterAddGroupBtn;

	@FindBy(xpath = "//a[@id='btnMasterEdit']/i")
	public static WebElement masterEditBtn;

	@FindBy(xpath = "//a[@id='btnClone']/i")
	public static WebElement masterCloneBtn;

	@FindBy(xpath = "//a[@id='btnProperties']/i")
	public static WebElement masterPropertiesBtn;

	@FindBy(xpath = "//a[@id='btnDelete']/i")
	public static WebElement masterDeleteBtn;

	@FindBy(xpath = "//i[@class='icon-font6 icon-group']")
	public static WebElement masterGroupBtn;

	@FindBy(xpath = "//*[@id='toggle_ribbon']")
	public static WebElement masterRibbonToExpandOptions;

	@FindBy(xpath = "//i[@class='icon-font6 icon-delete-all']")
	public static WebElement masterDeleteAllBtn;

	@FindBy(xpath = "//i[@class='icon-font6 icon-closed-account']")
	public static WebElement masterCloseAccountOrItemOrUnitsOrDepartmentOrWareHouseOrStateOrCity;

	@FindBy(xpath = "//i[@class='icon-font6 icon-open-close-account']")
	public static WebElement masteropenCloseAccountOrItemOrDepartmentOrWareHouseOrState;

	@FindBy(xpath = "//i[@class='icon-font6 icon-import']")
	public static WebElement masterAdvanceMasterImportORExportBtn;

	@FindBy(xpath = "//i[@class='icon-font6 icon-info']")
	public static WebElement masterAuthorInfoBtn;

	@FindBy(xpath = "//i[@class='icon-font6 icon-up-arrow']")
	public static WebElement masterMoveUpBtn;

	@FindBy(xpath = "//i[@class='icon-font6 icon-down-arrow']")
	public static WebElement masterMoveDownBtn;

	@FindBy(xpath = "//i[@class='icon-font6 icon-sort']")
	public static WebElement masterSortBtn;

	@FindBy(xpath = "//i[@class='icon-font6 icon-mass-update']")
	public static WebElement masterMassUpdateBtn;

	@FindBy(xpath = "//a[@class='lSNext']")
	public static WebElement masterRibbonControlNextBtn;

	@FindBy(xpath = "//*[@id='btnXMLImport']")
	public static WebElement masterImportFromXmlBtn;

	@FindBy(xpath = "//i[@class='icon-font6 icon-xmlexport']")
	public static WebElement masterExportFormatToXmlBtn;

	@FindBy(xpath = "//i[@class='icon-font6 icon-custamize']")
	public static WebElement masterCustamizemasterBtn;

	@FindBy(xpath = "//i[@class='icon-font6 icon-options']")
	public static WebElement masterCustamizeViewBtn;

	@FindBy(xpath = "//i[@class='icon-font6 icon-tree']")
	public static WebElement mastercustamizeTreeBtn;

	@FindBy(xpath = "//i[@class='icon-font6 icon-stock-ledger']")
	public static WebElement masterLedgerBtn;

	@FindBy(xpath = "//i[@class='icon-font6 icon-financial-1']")
	public static WebElement masterManageCreditBtn;

	@FindBy(xpath = "//i[@class='icon-font6 icon-department-appropriation']")
	public static WebElement masterDepartmentAppropriationBtn;

	@FindBy(xpath = "//i[@class='icon-font6 icon-budget']")
	public static WebElement masterBudgetBtn;

	@FindBy(xpath = "//i[@class='icon-font6 icon-backtrack']")
	public static WebElement masterBackTrackBtn;

	@FindBy(xpath = "//i[@class='icon-font6 icon-transfer']")
	public static WebElement masterTranferBtn;

	@FindBy(xpath = "//*[@id='btnNewSets']")
	public static WebElement masterNewSetBtn;

	@FindBy(xpath = "//i[@class='icon-font6 icon-modifier']")
	public static WebElement masterModifierBtn;

	@FindBy(xpath = "//*[@id='btnSetType']")
	public static WebElement masterSetTypeBtn;

	@FindBy(xpath = "//i[@class='icon-convert icon-font6']")
	public static WebElement masterUnitConversionBtn;

	@FindBy(xpath = "//i[@class='icon-font6 icon-alternate-product-1']")
	public static WebElement masterAlternateItemBtn;

	@FindBy(xpath = "//i[@class='icon-font6 icon-related-product']")
	public static WebElement masterRelatedItemBtn;

	@FindBy(xpath = "//i[@class='icon-font6 icon-stock-ledger']")
	public static WebElement masterStockLedgerBtn;

	@FindBy(xpath = "//tbody[@id='LandingGridBody']/tr/td[11]")
	public static List<WebElement> masterAccountsList;

	// New General Elements
	// Name
	@FindBy(xpath = "//input[@id='sName']")
	public static WebElement nameTxt;

	// AccountType
	@FindBy(xpath = "//select[@id='iAccountType']")
	public static WebElement accountTypeDropdown;

	// CreditLimit
	@FindBy(xpath = "//input[@id='fCreditLimit']")
	public static WebElement creditLimitTxt;

	// CreditDays
	@FindBy(xpath = "//input[@id='iCreditDays']")
	public static WebElement creditdaysTxt;

	// ChequeDiscountLimit
	@FindBy(xpath = "//input[@id='fChequeDiscountLimit']")
	public static WebElement chequeDiscountLimitTxt;

	// Rate Of Interest
	@FindBy(xpath = "//input[@id='fRateofinterest']")
	public static WebElement rateofinterestTxt;

	// BankAccount
	@FindBy(xpath = "//input[@id='iBankAc']")
	public static WebElement bankAccountTxt;

	@FindBy(xpath = "//*[@id='iBankAc_input_image']/span")
	public static WebElement bankAccount_ExpansionBtn;

	@FindBy(xpath = "//*[@id='iBankAc_input_settings']/span")
	public static WebElement bankAccount_SettingBtn;

	@FindBy(xpath = "//*[@id='btnMasterSaveClick']")
	public static WebElement saveBtn;

	@FindBy(xpath = "//a[@id='btnClose']/i")
	public static WebElement closeBtn;

	@FindBy(xpath = "//*[@id='btnDelete']/i")
	public static WebElement deleteBtn;

	@FindBy(xpath = "//div[@id='idGlobalError']")
	public static WebElement validationConfirmationMessage;

	@FindBy(xpath = "//*[@id='GenerateNewMasterModel']/div/div[1]/div[2]/div/ul/li[2]/button[5]")
	public static WebElement masterCloseBtn;

	@FindBy(xpath = "//*[@id='LandingGridBody']/tr[1]/td[11]")
	public static WebElement accountCreation;

	@FindBy(xpath = "/html[1]/body[1]/section[1]/div[2]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[2]/div[1]/div[1]/ol[1]/li[4]/span[1]")
	public static WebElement clickOnVendorGroup;

	@FindBy(xpath = "/html[1]/body[1]/section[1]/div[2]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[2]/div[1]/div[1]/div[2]/form[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/input[1]")
	public static WebElement getVendorGroupName;

	@FindBy(xpath = "//button[@class='btn-link theme_icon-color btnGenerateMasterclose closeMasterPartial clsMasterSaveClose']//i[@class='icon-font6 icon-close']")
	public static WebElement closeAccountCreationScreen;

	@FindBy(xpath = "//input[@id='chkRetainSelection']")
	public static WebElement accountMasterRetain;

	@FindBy(xpath = "//input[@id='liSelectAllMasters']")
	public static WebElement accountMasterSelect;

	@FindBy(xpath = "(//label[@id='lblnchecked']/input)[1]")
	public static WebElement accountFirstCheckBox;

	@FindBy(xpath = "(//label[@id='lblnchecked']/input)[2]")
	public static WebElement accountSecondCheckBox;

	@FindBy(xpath = "//button[@class='btn-link theme_icon-color btnGenerateMasterclose closeMasterPartial clsMasterSaveClose']")
	public static WebElement closeAccMasterCreationScreen;

	@FindBy(xpath = "/html[1]/body[1]/section[1]/div[2]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[2]/div[1]/div[1]/div[1]/div[2]/div[1]/ul[1]/li[2]/button[5]/i[1]")
	public static WebElement closeMaster;

	@FindBy(xpath = "//div[@id='innerDivDeleteMasterModal']/div[2]/div[1]//label")
	public static WebElement getMsgOnDelete;

	@FindBy(xpath = "//button[@id='btnOkForDelete']")
	public static WebElement clickOnOkInDelete;

	@FindBy(xpath = "//button[@id='btnCancelForDelete']")
	public static WebElement clickOnCancelInDelete;

	@FindBy(xpath = "//*[@id='idGlobalError']/div/div[2]")
	public static WebElement errorMessage;

	@FindBy(xpath = "//*[@id='idGlobalError']/div/div[1]/button")
	public static WebElement errorMessageCloseBtn;

	@FindBy(xpath = "//*[@id='LandingGridBody']/tr/td[8]//input")
	public static List<WebElement> masterGridBodyChkbox;

	@FindBy(xpath = "//*[@id='LandingGridBody']/tr/td[11]")
	public static List<WebElement> masterGridBodyName;

	@FindBy(xpath = "//span[@id='spnHeaderText']")
	public static WebElement accountsTitle;

	// Item Page

	@FindBy(xpath = "//*[@id='LandingGridBody']/tr/td[12]")
	public static List<WebElement> masterItemGridBodyName;

	@FindBy(xpath = "//*[@id='iTreeId']")
	public static WebElement masterTreeIdDropdown;

	@FindBy(xpath = "//*[@id='spnhiererchy']/div/div[1]/div/div/button/span")
	public static WebElement masterTreeIDToggleBtn;

	@FindBy(xpath = "//*[@id='spnhiererchy']/div/div[1]/div/div/ul/li[1]/a")
	public static WebElement masterTreeIDToggle_CreateTreeBtn;

	@FindBy(xpath = "//*[@id='spnhiererchy']/div/div[1]/div/div/ul/li[2]")
	public static WebElement masterTreeIDToggle_EditBtn;

	@FindBy(xpath = "//*[@id='spnhiererchy']/div/div[1]/div/div/ul/li[3]")
	public static WebElement masterTreeIDToggle_DeleteBtn;

	@FindBy(xpath = "//*[@id='spnhiererchy']/div/div[1]/div/div/ul/li[4]")
	public static WebElement masterTreeIDToggle_setDefaultBtn;

	@FindBy(xpath = "//*[@id='221']")
	public static WebElement homeMasterItemMenu;

	@FindBy(xpath = "//*[@id='1105']")
	public static WebElement homeMasterItem_ItemMenu;

	// Units Test

	@FindBy(xpath = "//a[@id='1114']")
	public static WebElement unitsMenu;

	@FindBy(xpath = "//input[@id='iNoOfDecimals']")
	public static WebElement noOfDecimalsTxt;

	@FindBy(xpath = "//tbody[@id='LandingGridBody']/tr/td[10]")
	public static List<WebElement> masterItemUnitsList;

	// Currency Test

	@FindBy(xpath = "//a[@id='220'][contains(text(),'Currency')]")
	public static WebElement currencyMenu;

	@FindBy(xpath = "//span[contains(text(),'Currency Master')]")
	public static WebElement currencyMasterMenu;

	@FindBy(xpath = "//a[@id='71']/span[contains(text(),'Exchange Rate')]")
	public static WebElement exchangeRateMenu;

	@FindBy(xpath = "//span[contains(text(),'Exchange Rate History')]")
	public static WebElement exchangeRateHistoryMenu;

	@FindBy(xpath = "//*[@id='btnSave']")
	public static WebElement saveIcon;

	@FindBy(xpath = "//input[@id='CoinsName']")
	public static WebElement coinsNameTxt;

	@FindBy(xpath = "//li[@id='btnClose']")
	public static WebElement currencyMasterCancelBtn;

	@FindBy(xpath = "//*[@id='ISOCurrencyCode']")
	public static WebElement ISOCurrencyCodeTxt;

	// Exchange Rate and Exchange Rate History

	@FindBy(xpath = "//*[@id='navbarSupportedContent2']//li[4]")
	public static WebElement exchangeRateDefinitionSaveIcon;

	@FindBy(xpath = "//*[@id='navbarSupportedContent2']//li[2]")
	public static WebElement exchnageRateDeleteOption;

	@FindBy(xpath = "//input[@id='id_option']")
	public static WebElement baseCurrencyInExchangeRateHistory;

	@FindBy(xpath = "//input[@id='ShowData']")
	public static WebElement loadIcon;

	@FindBy(xpath = "//*[@id='id_option_table_body']/tr/td[2]")
	public static List<WebElement> baseCurrencyListCount;

	@FindBy(xpath = "//input[@id='id_option']")
	public static WebElement baseCurrencyTxt;

	@FindBy(xpath = "//input[@id='EffectiveDate']")
	public static WebElement withEffectiveDateTxt;

	@FindBy(xpath = "//input[@id='ExchangeTable_DefinedAs']")
	public static WebElement gridDefineAsTxt;

	@FindBy(xpath = "//input[@id='ExchangeTable_Rate']")
	public static WebElement gridRateTxt;

	@FindBy(xpath = "//input[@id='ExchangeTable_CurrencyName']")
	public static WebElement gridcurrencyName1Txt;

	@FindBy(xpath = "//li[@id='closeExchangeRate']")
	public static WebElement exchangeRateDefinitionPageCloseIcon;

	@FindBy(xpath = "//i[@class='icon-font6 icon-close']")
	public static WebElement exchangeRateHistoryCloseIcon;

	@FindBy(xpath = "//tbody[@id='HistoryTableBody']/tr[2]/td[3]")
	public static WebElement valueUSD;

	@FindBy(xpath = "//tbody[@id='HistoryTableBody']/tr[2]/td[4]")
	public static WebElement valueAED;

	@FindBy(xpath = "//td[@id='ExchangeTable_col_1-2']")
	public static WebElement selCurrencyOne;

	@FindBy(xpath = "//td[@id='ExchangeTable_col_2-2']")
	public static WebElement selCurrecnyTwo;

	@FindBy(xpath = "//td[@id='ExchangeTable_col_1-3']")
	public static WebElement defCurrecnyOne;

	@FindBy(xpath = "//td[@id='ExchangeTable_col_2-3']")
	public static WebElement defCurrecnyTwo;

	// Departments Test

	@FindBy(xpath = "//a[@id='1106']//span[contains(text(),'Department')]")
	public static WebElement departmentMenu;

	@FindBy(xpath = "//*[@id='LandingGridBody']/tr/td[10]")
	public static List<WebElement> masterDepartmentGridBodyName;

	// Bins Test

	@FindBy(xpath = "//a[@id='1115']/span")
	public static WebElement binsMenu;

	@FindBy(xpath = "//*[@id='navigation_menu']/li[1]/ul/li[4]/ul/li")
	public static List<WebElement> masterSubMenusList;

	@FindBy(xpath = "//input[@id='iColNo']")
	public static WebElement binColNoTxt;

	@FindBy(xpath = "//tbody[@id='LandingGridBody']/tr/td[8]/div[1]/label/input")
	public static List<WebElement> masterItemUnitsChkboxList;

	// Unit Conversion Test

	@FindBy(xpath = "//a[@id='72']/span")
	public static WebElement homeMasterItem_UnitConversionMenu;

	@FindBy(xpath = "//input[@id='optionBaseUnit']")
	public static WebElement unitConversionBaseUnitTxt;

	@FindBy(xpath = "//input[@id='optionProduct']")
	public static WebElement unitConversionItemTxt;

	@FindBy(xpath = "//td[@id='myTagsTable_col_1-2']")
	public static WebElement unitConversionTableRow1Col1;

	@FindBy(xpath = "//td[@id='myTagsTable_col_1-3']")
	public static WebElement unitConversionTableRow1Col2;

	@FindBy(xpath = "//td[@id='myTagsTable_col_1-4']")
	public static WebElement unitConversionTableRow1Col3;

	@FindBy(xpath = "//td[@id='myTagsTable_col_1-5']")
	public static WebElement unitConversionTableRow1Col4;

	@FindBy(xpath = "//td[@id='myTagsTable_col_1-6']")
	public static WebElement unitConversionTableRow1Col5;

	@FindBy(xpath = "//input[@id='myTagsTable_UnitName']")
	public static WebElement unitConversion_EnterUnitNameTxt;

	@FindBy(xpath = "//input[@id='myTagsTable_XFactor']")
	public static WebElement unitConversion_EnterXFactorTxt;

	@FindBy(xpath = "//*[@id='ucCancel']/span")
	public static WebElement unitConversionCancelBtn;

	@FindBy(xpath = "//*[@id='btnDeleteUnitConversion']/span")
	public static WebElement unitConversionDeleteBtn;

	// Configure Masters Test

	@FindBy(xpath = "//*[@id='16']/span")
	public static WebElement settingsmenuBtn;

	@FindBy(xpath = "//*[@id='19']/span[text() = 'Configure Masters']")
	public static WebElement settingsConfigureMasters;

	@FindBy(xpath = "//*[@id='navigation_menu']/li[8]/ul/li")
	public static List<WebElement> settingsSubMenusList;

	@FindBy(xpath = "//div[@id='myNavbar']/ul/li/a")
	public static List<WebElement> configMastersRibbonControl;

	@FindBy(xpath = "//*[@id='MasterTabs']/li[2]/a")
	public static WebElement customizationTab;

	@FindBy(xpath = "//select[@id='ddlModule']")
	public static WebElement moduleDropdown;

	@FindBy(xpath = "//input[@id='txtMasterName']")
	public static WebElement nameComboBox;

	@FindBy(xpath = "//tbody[@id='txtMasterName_table_body']/tr/td[2]")
	public static List<WebElement> nameComboBoxList;

	@FindBy(xpath = "//input[@id='txtMasterCaption']")
	public static WebElement captionTxt;

	@FindBy(xpath = "//input[@id='rdoSearchByName_master']")
	public static WebElement searchByNameRadioBtn;

	@FindBy(xpath = "//input[@id='rdoSearchByCode_master']")
	public static WebElement searchByCodeRadioBtn;

	@FindBy(xpath = "//input[@id='chkAllowRivisions']")
	public static WebElement allowReservationChkBox;

	@FindBy(xpath = "//*[@id='chkIsDefault']")
	public static WebElement defaultChkBox;

	@FindBy(xpath = "//input[@id='chkGroupMandatory']")
	public static WebElement groupMandatoryChkBox;

	@FindBy(xpath = "//input[@id='chkPickCode']")
	public static WebElement pickCodeChkBox;

	@FindBy(xpath = "//*[@id='divDependency_Master']/table/thead/tr/td")
	public static List<WebElement> showDependencyHeader;

	@FindBy(xpath = "//a[contains(text(),'Dependent')]")
	public static WebElement dependentTab;

	@FindBy(xpath = "//a[contains(text(),'Used')]")
	public static WebElement usedTab;

	@FindBy(xpath = "//div[@class='msTreeMenuContainer']//div//span[@class='icon-left-and-right-panel-icon icon-font3 clsMenus pull-right']")
	public static WebElement menuMinimizeBtn;

	@FindBy(xpath = "//span[@id='altMenu_Icon']")
	public static WebElement menuExpansionBtn;

	@FindBy(xpath = "//div[@id='masterTreeMenu']//ul//li//a//span")
	public static List<WebElement> menuList;

	@FindBy(xpath = "//div[@id='masterTreeMenu']/ul//li/a/i")
	public static List<WebElement> menuPlus;

	@FindBy(xpath = "//div[contains(text(),'Save')]")
	public static WebElement cmSaveBtn;

	@FindBy(xpath = "//*[@id='btnClose_ConfigureMasters']/div[1]/span")
	public static WebElement cmCloseBtn;

	@FindBy(xpath = "//a[contains(@class,'menu-selected theme_color-inverse')]//span[@class='Flabel'][contains(text(),'Masters')]")
	public static WebElement cmMastermenu;

	@FindBy(xpath = "//div[@class='col-sm-12 btnMain_header_img']//span[@class='icon-delete icon-font7']")
	public static WebElement cmDeleteBtn;

	@FindBy(xpath = "//label[@id='lblMessage_Modal']")
	public static WebElement cmGetMgsonDelete;

	@FindBy(xpath = "//button[@id='btnDeleteMaster_Ok']")
	public static WebElement cmclickOnOkInDelete;

	@FindBy(xpath = "//*[@id='1182']/span")
	public static WebElement ConfigMasterInMastersList;

	@FindBy(xpath = "//*[@id='sName']")
	public static WebElement NameTxt;

	@FindBy(xpath = "//*[@id='sCode']")
	public static WebElement codeTxt;

	@FindBy(xpath = "//*[@id='MasterMenus_div']/div/div[3]/button[1]")
	public static WebElement addGroupBtnInconfigureMaster;

	@FindBy(xpath = "//*[@id='MasterMenus_div']/div/div[3]/button[2]")
	public static WebElement deleteGroupBtnInconfigureMaster;

	@FindBy(xpath = "//*[@id='txtNewGroupName']")
	public static WebElement groupNameTxt;

	@FindBy(xpath = "//*[@id='btnAddGroup_Ok']")
	public static WebElement okBtnInAddgroupPopup;

	@FindBy(xpath = "//li[@id='mMenu1']/a/i")
	public static WebElement homeMenuExpandBtn;

	@FindBy(xpath = "//li[@id='mMenu1000']/a/i")
	public static WebElement masterMenuExpandBtn;

	@FindBy(xpath = "//li[@id='mMenu1000']/a/span")
	public static WebElement masterMenuBtn;

	@FindBy(xpath = "//span[text()='GroupMaster']")
	public static WebElement GroupMasterMenuBtn;

	@FindBy(xpath = "(//span[text()='GroupMaster'])[2]")
	public static WebElement GroupMasterBtn;

	@FindBy(xpath = "//span[text()='GroupMasterToDelete']")
	public static WebElement GroupMasterToDeleteMenuBtn;

	// Seller Price book Test

	@FindBy(xpath = "//*[@id='80']/span")
	public static WebElement homeMasterItem_buyerPriceBookMenu;

	@FindBy(xpath = "//*[@id='76']/span")
	public static WebElement homeMasterItem_sellerPriceBookMenu;

	@FindBy(xpath = "//*[@id='ctrlOptionProPriceBookH']")
	public static WebElement buyerPriceBook_PriceBook;

	// First Row

	@FindBy(xpath = "//*[@id='PriceBookWebGrid_body']/tr[1]/td[2]")
	public static WebElement itemFirstCellSelect;

	@FindBy(xpath = "//*[@id='PriceBookWebGrid_body']/tr[1]/td[4]")
	public static WebElement wareHouseFirstCellSelect;

	@FindBy(xpath = "//*[@id='PriceBookWebGrid_body']/tr[1]/td[5]")
	public static WebElement startingDateFirstCellSelect;

	@FindBy(xpath = "//*[@id='PriceBookWebGrid_body']/tr[1]/td[6]")
	public static WebElement endingDateFirstCellSelect;

	@FindBy(xpath = "//*[@id='PriceBookWebGrid_body']/tr[1]/td[7]")
	public static WebElement vendorFirstCellSelect;

	@FindBy(xpath = "//*[@id='PriceBookWebGrid_body']/tr[1]/td[8]")
	public static WebElement currencyFirstCellSelect;

	@FindBy(xpath = "//*[@id='PriceBookWebGrid_body']/tr[1]/td[9]")
	public static WebElement minQtyFirstCellSelect;

	@FindBy(xpath = "//*[@id='PriceBookWebGrid_body']/tr[1]/td[10]")
	public static WebElement maxQtyFirstCellSelect;

	@FindBy(xpath = "//*[@id='PriceBookWebGrid_body']/tr[1]/td[11]")
	public static WebElement unitFirstCellSelect;

	@FindBy(xpath = "//*[@id='PriceBookWebGrid_body']/tr[1]/td[12]")
	public static WebElement rateFirstCellSelect;

	@FindBy(xpath = "//*[@id='PriceBookWebGrid_body']/tr[1]/td[13]")
	public static WebElement PoFirstCellSelect;

	@FindBy(xpath = "//*[@id='PriceBookWebGrid_body']/tr[1]/td[14]")
	public static WebElement PtFirstCellSelect;

	// Second Row
	@FindBy(xpath = "//*[@id='PriceBookWebGrid_body']/tr[2]/td[2]")
	public static WebElement itemSecondCellSelect;

	@FindBy(xpath = "//*[@id='PriceBookWebGrid_body']/tr[2]/td[4]")
	public static WebElement wareHouseSecondCellSelect;

	@FindBy(xpath = "//*[@id='PriceBookWebGrid_body']/tr[2]/td[5]")
	public static WebElement satrtingDateSecondCellSelect;

	@FindBy(xpath = "//*[@id='PriceBookWebGrid_body']/tr[2]/td[6]")
	public static WebElement endingDateSecondCellSelect;

	@FindBy(xpath = "//*[@id='PriceBookWebGrid_body']/tr[2]/td[7]")
	public static WebElement vendorSecondCellSelect;

	@FindBy(xpath = "//*[@id='PriceBookWebGrid_body']/tr[2]/td[8]")
	public static WebElement currencySecondCellSelect;

	@FindBy(xpath = "//*[@id='PriceBookWebGrid_body']/tr[2]/td[9]")
	public static WebElement minQtySecondCellSelect;

	@FindBy(xpath = "//*[@id='PriceBookWebGrid_body']/tr[2]/td[10]")
	public static WebElement maxQtySecondCellSelect;

	@FindBy(xpath = "//*[@id='PriceBookWebGrid_body']/tr[2]/td[11]")
	public static WebElement unitSecondCellSelect;

	@FindBy(xpath = "//*[@id='PriceBookWebGrid_body']/tr[2]/td[12]")
	public static WebElement rateSecondCellSelect;

	@FindBy(xpath = "//*[@id='PriceBookWebGrid_body']/tr[2]/td[13]")
	public static WebElement PoSecondCellSelect;

	@FindBy(xpath = "//*[@id='PriceBookWebGrid_body']/tr[2]/td[14]")
	public static WebElement PtSecondCellSelect;

	// Third Row
	@FindBy(xpath = "//td[@id='PriceBookWebGrid_col_3-1']")
	public static WebElement itemThirdCellSelect;

	@FindBy(xpath = "//td[@id='PriceBookWebGrid_col_3-3']")
	public static WebElement wareHouseThirdCellSelect;

	@FindBy(xpath = "//td[@id='PriceBookWebGrid_col_3-4']")
	public static WebElement satrtingDateThirdCellSelect;

	@FindBy(xpath = "//td[@id='PriceBookWebGrid_col_3-5']")
	public static WebElement endingDateThirdCellSelect;

	@FindBy(xpath = "//td[@id='PriceBookWebGrid_col_3-6']")
	public static WebElement vendorThirdCellSelect;

	@FindBy(xpath = "//td[@id='PriceBookWebGrid_col_3-7']")
	public static WebElement currencyThirdCellSelect;

	@FindBy(xpath = "//td[@id='PriceBookWebGrid_col_3-8']")
	public static WebElement minQtyThirdCellSelect;

	@FindBy(xpath = "//td[@id='PriceBookWebGrid_col_3-9']")
	public static WebElement maxQtyThirdCellSelect;

	@FindBy(xpath = "//td[@id='PriceBookWebGrid_col_3-10']")
	public static WebElement unitThirdCellSelect;

	@FindBy(xpath = "//td[@id='PriceBookWebGrid_col_3-11']")
	public static WebElement rateThirdCellSelect;

	@FindBy(xpath = "//td[@id='PriceBookWebGrid_col_3-12']")
	public static WebElement PoThirdCellSelect;

	@FindBy(xpath = "//td[@id='PriceBookWebGrid_col_3-13']")
	public static WebElement PtThirdCellSelect;

	// To get Value From Cell

	@FindBy(xpath = "//input[@id='PriceBookWebGrid_option_2']")
	public static WebElement itemValue;

	@FindBy(xpath = "//input[@id='PriceBookWebGrid_option_4']")
	public static WebElement wareHouseValue;

	@FindBy(xpath = "//input[@id='PriceBookWebGrid_option_StartingDate']")
	public static WebElement startingDateValue;

	@FindBy(xpath = "//input[@id='PriceBookWebGrid_option_EndingDate']")
	public static WebElement endingDateValue;

	@FindBy(xpath = "//input[@id='PriceBookWebGrid_option_1']")
	public static WebElement vendorValue;

	@FindBy(xpath = "//input[@id='Currency_option']")
	public static WebElement currencyOptionValue;

	@FindBy(xpath = "//input[@id='PriceBookWebGrid_minqty']")
	public static WebElement minQtyValue;

	@FindBy(xpath = "//input[@id='PriceBookWebGrid_maxqty']")
	public static WebElement maxQtyValue;

	@FindBy(xpath = "//input[@id='PriceBookWebGrid_option_11']")
	public static WebElement unitValue;

	@FindBy(xpath = "//input[@id='PriceBookWebGrid_val0']")
	public static WebElement rateValue;

	@FindBy(xpath = "//input[@id='PriceBookWebGrid_val1']")
	public static WebElement PoValue;

	@FindBy(xpath = "//input[@id='PriceBookWebGrid_val2']")
	public static WebElement PtValue;

	@FindBy(xpath = "//*[@id='chkDateRange']")
	public static WebElement buyerPriceBook_CustamizeDateRangeCheckBox;

	@FindBy(xpath = "//*[@id='chkCustomer']")
	public static WebElement buyerPriceBook_CustomizeVendorCheckBox;

	@FindBy(xpath = "//*[@id='chkDepartment']")
	public static WebElement buyerPriceBook_WarehouseCheckBox;

	@FindBy(xpath = "//*[@id='chkQtyRange']")
	public static WebElement buyerPriceBook_QualityRangeCheckBox;

	@FindBy(xpath = "//*[@id='chkCurrency']")
	public static WebElement buyerPriceBook_CurrencyCheckBox;

	@FindBy(xpath = "//*[@id='chkUnit']")
	public static WebElement buyerPriceBook_UnitCheckBox;

	@FindBy(xpath = "//*[@id='txtAbbreviation']")
	public static WebElement buyerPriceBook_AbbreviationBtn;

	@FindBy(xpath = "//*[@id='PriceBookContainer']/div[2]/nav/div/div[1]/a/div/span[2]")
	public static WebElement buyerPriceBookLabel;

	@FindBy(xpath = "//*[@id='btnClear']")
	public static WebElement buyerPriceBookClearBtn;

	@FindBy(xpath = "//*[@id='btnPaste']")
	public static WebElement buyerPriceBookPasteBtn;

	@FindBy(xpath = "//*[@id='btnCopy']")
	public static WebElement buyerPriceBookCopyBtn;

	@FindBy(xpath = "//*[@id='btnSelectAll']")
	public static WebElement buyerPriceBookSelectAllBtn;

	@FindBy(xpath = "//*[@id='btnClose']")
	public static WebElement buyerPriceBookCloseBtn;

	@FindBy(xpath = "//*[@id='btnSave']")
	public static WebElement buyerPriceBookSaveBtn;

	@FindBy(xpath = "//*[@id='ctrlOptionProPriceBookH_table_body']/tr/td[2]")
	public static List<WebElement> pricebookComboBoxList;

	@FindBy(xpath = "//*[@id='PriceBookWebGrid_option_2_table_body']/tr/td[2]")
	public static List<WebElement> itemComboBoxList;

	@FindBy(xpath = "//*[@id='PriceBookWebGrid_option_4_table_body']/tr/td[2]")
	public static List<WebElement> warehouseComboBoxList;

	@FindBy(xpath = "//*[@id='PriceBookWebGrid_option_1_table_body']/tr/td[2]")
	public static List<WebElement> vendorComboBoxList;

	@FindBy(xpath = "//*[@id='Currency_option_table_body']/tr/td[2]")
	public static List<WebElement> currencyComboBoxList;

	@FindBy(xpath = "//*[@id='PriceBookWebGrid_option_11_table_body']/tr/td[2]")
	public static List<WebElement> unitComboBoxList;

	@FindBy(xpath = "//input[@id='btnLoad']")
	public static WebElement priceBookFilterAndLoadBtn;

	@FindBy(xpath = "//form[@id='formLoadDataXML']//button[@id='btnExporttoExcel']")
	public static WebElement priceBookExportToExcel;

	@FindBy(xpath = "//button[@id='btnPBImportFromExcel']")
	public static WebElement buyerPriceBook_ImportFromExcel;

	@FindBy(xpath = "//button[@id='btnImportData']")
	public static WebElement importButton;

	@FindBy(xpath = "//button[@id='btnCloseImport']")
	public static WebElement closeButtonInImport;

	@FindBy(xpath = "//span[@class='btn btn-sm theme_background-color fileUpload theme_color']")
	public static WebElement browseButton;

	@FindBy(xpath = "//input[@id='rdoFilterName']")
	public static WebElement nameRadioButton;

	@FindBy(xpath = "//input[@id='rdoFilterCode']")
	public static WebElement codeRadioButton;

	@FindBy(xpath = "//input[@id='rdoFilterBoth']")
	public static WebElement bothRadioButton;

	@FindBy(xpath = "//input[@id='chkOverride']")
	public static WebElement overrideCheckbox;

	@FindBy(xpath = "//span[contains(text(),'Select Sheet')]")
	public static WebElement sheetTitle;

	@FindBy(xpath = "//li[@class='clsSheetName']")
	public static WebElement sheetName;

	@FindBy(xpath = "//button[@id='btnSheetOk']")
	public static WebElement sheetOkButton;

	@FindBy(xpath = "//button[@id='btnSheetClose']")
	public static WebElement sheetCloseButton;

	@FindBy(xpath = "//div[@id='myModalImportFromExcel']//tr[1]//td[3]")
	public static WebElement sheetItemHeading;

	@FindBy(xpath = "//select[@id='ddlImportFromExcelItemList0']")
	public static WebElement sheetItem;

	@FindBy(xpath = "//div[@id='myModalImportFromExcel']//tr[2]//td[3]")
	public static WebElement sheetAccountHeading;

	@FindBy(xpath = "//select[@id='ddlImportFromExcelItemList1']")
	public static WebElement sheetAccount;

	@FindBy(xpath = "(//div[@id='myModalImportFromExcel']//tr[3]//td[3])[2]")
	public static WebElement sheetTagHeading;

	@FindBy(xpath = "//select[@id='ddlImportFromExcelItemList2']")
	public static WebElement sheetTag;

	@FindBy(xpath = "//div[@id='myModalImportFromExcel']//tr[4]//td[3]")
	public static WebElement sheetCurrencyHeading;

	@FindBy(xpath = "//select[@id='ddlImportFromExcelItemList3']")
	public static WebElement sheetCurrency;

	@FindBy(xpath = "//div[@id='myModalImportFromExcel']//tr[5]//td[3]")
	public static WebElement sheetStartDateHeading;

	@FindBy(xpath = "//select[@id='ddlImportFromExcelItemList4']")
	public static WebElement sheetStartDate;

	@FindBy(xpath = "//div[@id='myModalImportFromExcel']//tr[6]//td[3]")
	public static WebElement sheetEndDateHeading;

	@FindBy(xpath = "//select[@id='ddlImportFromExcelItemList5']")
	public static WebElement sheetEndDate;

	@FindBy(xpath = "//div[@id='myModalImportFromExcel']//tr[7]//td[3]")
	public static WebElement sheetMinQtyHeading;

	@FindBy(xpath = "//select[@id='ddlImportFromExcelItemList6']")
	public static WebElement sheetMinQty;

	@FindBy(xpath = "//div[@id='myModalImportFromExcel']//tr[8]//td[3]")
	public static WebElement sheetMaxQtyHeading;

	@FindBy(xpath = "//select[@id='ddlImportFromExcelItemList7']")
	public static WebElement sheetMaxQty;

	@FindBy(xpath = "//div[@id='myModalImportFromExcel']//tr[9]//td[3]")
	public static WebElement sheetUnitHeading;

	@FindBy(xpath = "//select[@id='ddlImportFromExcelItemList8']")
	public static WebElement sheetUnitQty;

	@FindBy(xpath = "//div[@id='myModalImportFromExcel']//tr[10]//td[3]")
	public static WebElement sheetRateHeading;

	@FindBy(xpath = "//select[@id='ddlImportFromExcelItemList9']")
	public static WebElement sheetRate;

	@FindBy(xpath = "//div[@id='myModalImportFromExcel']//tr[11]//td[3]")
	public static WebElement sheetPOHeading;

	@FindBy(xpath = "//select[@id='ddlImportFromExcelItemList10']")
	public static WebElement sheetPO;

	@FindBy(xpath = "//div[@id='myModalImportFromExcel']//tr[12]//td[3]")
	public static WebElement sheetPTHeading;

	@FindBy(xpath = "//div[@id='myModalImportFromExcel']//tr[13]//td[3]")
	public static WebElement sheetHeading;

	@FindBy(xpath = "//select[@id='ddlImportFromExcelItemList11']")
	public static WebElement sheetPT;

	@FindBy(xpath = "//select[@id='ddlImportFromExcelItemList12']")
	public static WebElement sheetP;

	@FindBy(xpath = "//*[@id='toggleDropDown']/i")
	public static WebElement buyerPriceBook_CustamizeSettingBtn;

	@FindBy(xpath = "//ul[@id='ddlMenu']/li/label/input")
	public static List<WebElement> buyerPriceBook_CustomizeCheckBoxList;

	@FindBy(xpath = "//tbody[@id='PriceBookWebGrid_body']/tr[1]/td")
	public static List<WebElement> buyerPriceBook_TableRow1List;

	@FindBy(xpath = "//tbody[@id='PriceBookWebGrid_body']/tr[2]/td")
	public static List<WebElement> buyerPriceBook_TableRow2List;

	@FindBy(xpath = "//select[@id='ImportGrid_ExternalFields']")
	public static WebElement buyerPriceBook_MappingDrpdwn;

	@FindBy(xpath = "//a[@id='200']")
	public static WebElement InventoryReportsMenu;

	@FindBy(xpath = "//a[@id='202']")
	public static WebElement itemqueryMenu;

	// Item Query Elements

	@FindBy(xpath = "//input[@id='productQuery']")
	public static WebElement itemTxt;

	@FindBy(xpath = "//input[@class='FButton-Primary']")
	public static WebElement getStockBtn;

	@FindBy(xpath = "//*[@id='openingStock']")
	public static WebElement openStockQuantityTxt;

	@FindBy(xpath = "//*[@id='currentStock']")
	public static WebElement currentStockTxt;

	@FindBy(xpath = "//*[@id='avgStockRate']")
	public static WebElement avgStockRateTxt;

	@FindBy(xpath = "//*[@id='value']")
	public static WebElement stockValueTxt;

	@FindBy(xpath = "//*[contains(text(),'Balance by Warehouse')]")
	public static WebElement balanceByWarehouseBtn;

	@FindBy(xpath = "//*[@id='tagWiseData']/tbody/tr[1]/td")
	public static List<WebElement> row1BalanceByWareHouseList;

	@FindBy(xpath = "//*[@id='tagWiseData']/tbody/tr[2]/td")
	public static List<WebElement> row2BalanceByWareHouseList;

	@FindBy(xpath = "//*[@id='tagWiseData']/tbody/tr[3]/td")
	public static List<WebElement> row3BalanceByWareHouseList;

	@FindBy(xpath = "//a[@id='551']")
	public static WebElement InventoryStockLedgerMenu;
	
	@FindBy(xpath = "//*[@id='selectAllMasters_']/following-sibling::span")
	public	static WebElement selectAllItemsCheckBox;

	// Stock Ledger Elements

	@FindBy(xpath = "//tbody[@id='LandingGridBody']/tr/td[8]/div/label/input")
	public static List<WebElement> stockLedgerHometableItemChkboxList;

	@FindBy(xpath = "//*[@id='LandingGridBody']/tr/td[12]")
	public static List<WebElement> ledgerHometableItemNamesList;

	@FindBy(xpath = "//*[@id='LandingGridBody']/tr")
	public static List<WebElement> stockLedgerHometableRowCount;

	@FindBy(xpath = "//select[@id='DateOptions_']")
	public static WebElement sl_DateOptionDropdown;

	@FindBy(xpath = "//i[@class='icon-ok hiconright2']")
	public static WebElement sl_OkBtn;

	@FindBy(xpath = "//button[contains(text(), 'Last')]")
	public static WebElement stockLedgerLastBtn;

	@FindBy(xpath = "//button[contains(text(), 'Previous')]")
	public static WebElement stockLedgerPreviousBtn;

	@FindBy(xpath = "(//tr[@id='trRender_13'])[1]/td[9]")
	public static WebElement stockLedgerBrBatchBalanceQty;

	@FindBy(xpath = "(//tr[@id='trRender_15'])[1]/td[9]")
	public static WebElement stockLedgerBrBatchAfterSavingpurchaseBalanceQty;

	@FindBy(xpath = "(//tr[@id='trRender_2'])[1]/td[9]")
	public static WebElement stockLedgerBrBatchAfterSavingSalesInvoiceBalanceQty;

	@FindBy(xpath = "(//tr[@id='trRender_2'])[1]/td[12]")
	public static WebElement stockLedgerBrBatchAfterSavingSalesInvoiceBalanceValue;

	@FindBy(xpath = "(//tr[@id='trRender_2'])[1]/td[13]")
	public static WebElement stockLedgerBrBatchAfterSavingSalesInvoiceAvgRate;

	@FindBy(xpath = "(//tr[@id='trRender_2'])[1]/td[8]") //(//tr[@id='trRender_10'])[1]/td[8]
	public static WebElement stockLedgerBrBatchAfterSavingSalesInvoiceIssuedrateTxt;

	@FindBy(xpath = "(//tr[@id='trRender_4'])[1]/td[8]")
	public static WebElement stockLedgerFifoItemAfterSavingSalesInvoiceIssuedrateTxt;

	@FindBy(xpath = "(//tr[@id='trRender_5'])[1]/td[8]")
	public static WebElement stockLedgerRmaItemAfterSavingSalesInvoiceIssuedrateTxt;

	@FindBy(xpath = "(//tr[@id='trRender_14'])[1]/td[9]")
	public static WebElement stockLedgerBrBatchBalanceQtyAfterLoginToInvUser;

	@FindBy(xpath = "(//tr[@id='trRender_2'])[1]/td[9]")
	public static WebElement stockLedgerFifoItemBalanceQtyAfterLoginToInvUser;

	@FindBy(xpath = "(//tr[@id='trRender_2'])[1]/td[12]")
	public static WebElement stockLedgerFifoItemBalancevaluAfterLoginToInvUser;

	@FindBy(xpath = "(//tr[@id='trRender_2'])[1]/td[13]")
	public static WebElement stockLedgerFifoItemAvgRateAfterLoginToInvUser;

	@FindBy(xpath = "(//tr[@id='trRender_13'])[1]/td[12]")
	public static WebElement stockLedgerBrBatchBalanceValue;

	@FindBy(xpath = "(//tr[@id='trRender_15'])[1]/td[12]")
	public static WebElement stockLedgerBrBatchBalanceValueAfterSavingpurchase;

	@FindBy(xpath = "(//tr[@id='trRender_14'])[1]/td[12]")
	public static WebElement stockLedgerBrBatchBalanceValueAfterLoginToInvUser;

	@FindBy(xpath = "(//tr[@id='trRender_13'])[1]/td[13]")
	public static WebElement stockLedgerBrBatchAvgRate;

	@FindBy(xpath = "(//tr[@id='trRender_15'])[1]/td[13]")
	public static WebElement stockLedgerBrBatchAvgRateAfterSavingpurchase;

	@FindBy(xpath = "(//tr[@id='trRender_14'])[1]/td[13]")
	public static WebElement stockLedgerBrBatchAvgRateAfterLoginToInvUser;

	@FindBy(xpath = "(//tr[@id='trRender_14'])[1]/td[9]")
	public static WebElement stockLedgerFifoItemBalanceQty;

	@FindBy(xpath = "(//tr[@id='trRender_16'])[1]/td[9]")
	public static WebElement stockLedgerFifoItemBalanceQtyAfterSavingPurchase;

	@FindBy(xpath = "(//tr[@id='trRender_14'])[1]/td[12]")
	public static WebElement stockLedgerFifoItemBalanceValue;

	@FindBy(xpath = "(//tr[@id='trRender_16'])[1]/td[12]")
	public static WebElement stockLedgerFifoItemBalanceValueAfterSavingPurchase;

	@FindBy(xpath = "(//tr[@id='trRender_14'])[1]/td[13]")
	public static WebElement stockLedgerFifoItemAvgRate;

	@FindBy(xpath = "(//tr[@id='trRender_16'])[1]/td[13]")
	public static WebElement stockLedgerFifoItemAvgRateAfterSavingPurchase;

	@FindBy(xpath = "(//tr[@id='trRender_15'])[1]/td[9]")
	public static WebElement stockLedgerRmaItemBalanceQty;

	@FindBy(xpath = "(//tr[@id='trRender_0'])[1]/td[9]")
	public static WebElement stockLedgerRmaItemBalanceQtyAftersavingPurchase;

	@FindBy(xpath = "(//tr[@id='trRender_3'])[1]/td[9]")
	public static WebElement stockLedgerRmaItemBalanceQtyAfterLoginToInvUser;

	@FindBy(xpath = "(//tr[@id='trRender_15'])[1]/td[12]")
	public static WebElement stockLedgerRmaItemBalanceValue;

	@FindBy(xpath = "(//tr[@id='trRender_0'])[1]/td[12]")
	public static WebElement stockLedgerRmaItemBalanceValueAfterSavingPurchase;

	@FindBy(xpath = "(//tr[@id='trRender_3'])[1]/td[12]")
	public static WebElement stockLedgerRmaItemBalanceValueAfterLoginToInvUser;

	@FindBy(xpath = "(//tr[@id='trRender_15'])[1]/td[13]")
	public static WebElement stockLedgerrmaItemAvgRate;

	@FindBy(xpath = "(//tr[@id='trRender_0'])[1]/td[13]")
	public static WebElement stockLedgerrmaItemAvgRateAfterSavingPurchase;

	@FindBy(xpath = "(//tr[@id='trRender_3'])[1]/td[13]")
	public static WebElement stockLedgerrmaItemAvgRateAfterLogintoInvUser;

	@FindBy(xpath = "(//i[@class='icon-close hiconright2'])[2]")
	public static WebElement report_CloseBtn;

	@FindBy(xpath = "(//i[@class='icon-close hiconright2'])[1]")
	public static WebElement sl_CloseBtn;

	@FindBy(xpath = "//td[contains(text(), 'FIFO-BIN')]//preceding-sibling::td//label/input")
	public static WebElement fifoItemCheckBox;

	@FindBy(xpath = "//td[contains(text(), 'WA-RMA')]//preceding-sibling::td//label/input")
	public static WebElement rmaItemCheckBox;

	@FindBy(xpath = "//td[contains(text(), 'BR-BATCH')]//preceding-sibling::td//label/input")
	public static WebElement brBatchItemCheckBox;

	@FindBy(xpath = "(//i[@class='icon-filter hiconright2'])[2]")
	public static WebElement stockLedgerFilterBtn;
	
	@FindBy(xpath = "(//li[text()='Filter'])[2]")
	public static WebElement stockLedgerFilterField;

	@FindBy(xpath = "(//i[@class='icon-filter hiconright2'])[1]")
	public static WebElement stockLedgerHomePageFilterBtn;

	@FindBy(xpath = "//h5[text()='Filter']")
	public static WebElement stockLedgerFilterText;
	
	
	@FindBy(xpath = "//*[@id='reportViewFilterBtn']")
	public static WebElement stockMovementWarehouseField2;
	
	@FindBy(xpath = "//*[@id='reportViewFilterBtn']")
	public static WebElement stockValuationWarehouseField2;
	
	@FindBy(xpath = "//*[@id='reportViewFilterBtn']")
	public static WebElement stockAgeingWarehouseField2;
	
	@FindBy(xpath = "//*[@id='reportViewFilterBtn']")
	public static WebElement stockStatementWarehouseField2;
	
	@FindBy(xpath = "//*[@id='575_0_AdvanceFilter_']/table/tbody/tr/td[5]/input")
	public static WebElement stockMovementFilterWarehouseField3;
	
	@FindBy(xpath = "//*[@id='advancefilter_master_575_0_']")
	public static WebElement stockMovementFilterWarehouseField4;
	
	@FindBy(xpath = "//*[@id='578_0_AdvanceFilter_']/table/tbody/tr/td[5]")
	public static WebElement stockValuationFilterWarehouseField3;
	
	@FindBy(xpath = "//*[@id='advancefilter_master_578_0_']")
	public static WebElement stockValuationFilterWarehouseField4;
	
	@FindBy(xpath = "//*[@id='582_0_AdvanceFilter_']/table/tbody/tr/td[5]")
	public static WebElement stockAgeingFilterWarehouseField3;
	
	@FindBy(xpath = "//*[@id='advancefilter_master_582_0_']")
	public static WebElement stockAgeingFilterWarehouseField4;
	
	@FindBy(xpath = "//*[@id='557_0_AdvanceFilter_']/table/tbody/tr/td[5]")
	public static WebElement stockStatementFilterWarehouseField3;
	
	@FindBy(xpath = "//*[@id='advancefilter_master_557_0_']")
	public static WebElement stockStatementFilterWarehouseField4;
	
	

	@FindBy(xpath = "//input[@id='FOption_551_0_DefaultFilter_1']")
	public static WebElement stockLedgerFilterWarehouseField;
	
	@FindBy(xpath = "//*[@id='FOption_575_0_DefaultFilter_0']")
	public static WebElement stockMovementFilterWarehouseField;
	
	@FindBy(xpath = "//*[@id='FOption_578_0_DefaultFilter_0']")
	public static WebElement stockValuationFilterWarehouseField;
	
	@FindBy(xpath = "//*[@id='FOption_582_0_DefaultFilter_0']")
	public static WebElement stockAgeingFilterWarehouseField;
	
	@FindBy(xpath = "//*[@id='FOption_557_0_DefaultFilter_0']")
	public static WebElement stockStatementFilterWarehouseField;

	@FindBy(xpath = "//input[@id='filter_Okbtn_']")
	public static WebElement stockLedgerFilterOkBtn;

	@FindBy(xpath = "//input[@id='filterRefresh']")
	public static WebElement stockLedgerFilterRefreshBtn;

	@FindBy(xpath = "//*[@id='reportViewFilterBtn']")
	public static WebElement stockLedgerFilterWarehouseField2;

	@FindBy(xpath = "//*[@id='551_0_AdvanceFilter_']/table/tbody/tr/td[5]/input")
	public static WebElement stockLedgerFilterWarehouseField3;
	
	@FindBy(xpath = "//*[@id='advancefilter_master_551_0_']")
	public static WebElement stockLedgerFilterWarehouseField4;
	
	

	@FindBy(xpath = "(//tr[@id='trRender_10'])[1]/td[9]")
	public static WebElement stockLedgerRmaItemHydBalanceQty;

	@FindBy(xpath = "(//tr[@id='trRender_10'])[1]/td[12]")
	public static WebElement stockLedgerRmaItemHydBalanceValue;

	@FindBy(xpath = "(//tr[@id='trRender_10'])[1]/td[13]")
	public static WebElement stockLedgerrmaItemHydAvgRate;

	@FindBy(xpath = "(//tr[@id='trRender_9'])[1]/td[9]")
	public static WebElement stockLedgerRmaItemSecBalanceQty;

	@FindBy(xpath = "(//tr[@id='trRender_9'])[1]/td[12]")
	public static WebElement stockLedgerRmaItemSecBalanceValue;

	@FindBy(xpath = "(//tr[@id='trRender_9'])[1]/td[13]")
	public static WebElement stockLedgerrmaItemSecAvgRate;

	@FindBy(xpath = "//*[@id='61']")
	public static WebElement financialsTransactionMenu;

	@FindBy(xpath = "//*[@id='2007']")
	public static WebElement financialsTransactionsPurchaseMenu;

	@FindBy(xpath = "//*[@id='2014']")
	public static WebElement financialsTransactionsSalesMenu;

	@FindBy(xpath = "//a[@id='2008']")
	public static WebElement PurchasesVoucher;

	@FindBy(xpath = "//a[@id='2015']")
	public static WebElement salesInvoiceVoucher;

	@FindBy(xpath = "//a[@id='60']/span")
	public static WebElement financialsMenu;
	
	@FindBy(xpath="//a[@id='530']/span")
	public static WebElement  financialFinalAccountsMenu;
	
	@FindBy(xpath="//a[@id='558']/span")
	public static WebElement  financialReceivableAndPayableMenu;
	
	@FindBy(xpath="//a[@id='607']/span")
	public static WebElement  customerSummeryMenu;
	
	@FindBy(xpath="//a[@id='608']/span")
	public static WebElement  vendorSummeryMenu;
	
	@FindBy(xpath="//a[@id='544']/span")
	public static WebElement  vendorAgeingSummery;
	
	@FindBy(xpath="//a[@id='562']/span")
	public static WebElement  customerAgeingSummery;
	
	@FindBy(xpath="//a[@id='531']/span")
	public static WebElement  trialBalance;
	
	@FindBy(xpath="//a[@id='81']/span")
	public static WebElement  financialReports;
	
	@FindBy(xpath="//a[@id='506']/span")
	public static WebElement  financialPurchaseReportMenu;
	
	@FindBy(xpath="//a[@id='507']/span")
	public static WebElement  financialPurchaseRegister;
	
	@FindBy(xpath = "//*[@id='MasterGroup__101']")
	public static WebElement accountDropdown;
	
	@FindBy(xpath="//a[@id='500']/span")
	public static WebElement  financialReportsLedger;

	@FindBy(xpath = "//a[@id='id_transaction_homescreen_new']")
	public static WebElement newBtn;

	@FindBy(xpath = "//input[@id='id_header_3']")
	public static WebElement purchasesAccounttxt;

	@FindBy(xpath = "//input[@id='id_header_3']")
	public static WebElement salesAccounttxt;

	@FindBy(xpath = "//input[@id='id_header_4']")
	public static WebElement vendorAccounttxt;

	@FindBy(xpath = "//input[@id='id_header_4']")
	public static WebElement customerAccounttxt;

	@FindBy(xpath = "//input[@id='id_header_2']")
	public static WebElement purchaseDatetxt;

	@FindBy(xpath = "//input[@id='id_header_2']")
	public static WebElement salesDatetxt;

	@FindBy(xpath = "//input[@id='id_header_268435459']")
	public static WebElement purchasesDepartmenttxt;

	@FindBy(xpath = "//input[@id='id_header_268435459']")
	public static WebElement salesDepartmenttxt;

	@FindBy(xpath = "//input[@id='id_body_38']")
	public static WebElement enterRmatxt;

	@FindBy(xpath = "//*[@id='id_transactionentry_save']")
	public static WebElement voucherSaveBtn;
	
	@FindBy(xpath="//*[@id='id_transactionentry_previous']")
	public static WebElement  previousBtn;
	
	@FindBy(xpath="//span[@class='icon-menudots hiconright2']")
	public static WebElement  toggleBtn;
	
	@FindBy(xpath="//*[@id='id_search_menu']/input")
	public static WebElement  homepageManuSearchField;
	
	@FindBy(xpath="//span[@id='2057']")
	public static WebElement  stockIn;
	
	@FindBy(xpath="//*[@id='id_transactionentry_copytoclipboard']")
	public static WebElement  copyToClipBoardOption;
	
	@FindBy(xpath="//*[@id='id_transactionentry_pastefromclipboard']")
	public static WebElement  pasteFromClipBoardOption;

	@FindBy(xpath = "//*[@id='id_transactionentry_close']")
	public static WebElement new_CloseBtn;

	@FindBy(xpath = "//i[@class='icon-close hiconright2']")
	public static WebElement voucherhomeCloseBtn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[1]/td[2]")
	public static WebElement select1stRow_1stColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[2]/td[2]")
	public static WebElement select2ndRow_1stColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[3]/td[2]")
	public static WebElement select3rdRow_1stColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[4]/td[2]")
	public static WebElement select4thRow_1stColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[5]/td[2]")
	public static WebElement select5thRow_1stColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[6]/td[2]")
	public static WebElement select6thRow_1stColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[7]/td[2]")
	public static WebElement select7thRow_1stColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[8]/td[2]")
	public static WebElement select8thRow_1stColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[9]/td[2]")
	public static WebElement select9thRow_1stColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[1]/td[10]")
	public static WebElement select1stRow_9thColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[1]/td[11]")
	public static WebElement select1stRow_10thColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[1]/td[9]")
	public static WebElement select1stRow_8thColumn;

	@FindBy(xpath = "(//button[@id='div_PICKFIFO'])[1]")
	public static WebElement batchPickOnFIFOIcon;

	@FindBy(xpath = "//button[@id='div_Ok1']")
	public static WebElement batchOkIcon;

	@FindBy(xpath = "//*[@id='Id_MRPBatch_Grid_body']/tr[1]/td[3]")
	public static WebElement batchPopUPRow1BaseQuantityTxt;

	@FindBy(xpath = "//*[@id='Id_MRPBatch_Grid_body']/tr[1]/td[8]")
	public static WebElement batchPopUPRow1RateTxt;

	@FindBy(xpath = "//*[@id='Id_MRPBatch_Grid_body']/tr[2]/td[3]")
	public static WebElement batchPopUPRow2BaseQuantityTxt;

	@FindBy(xpath = "//*[@id='Id_MRPBatch_Grid_body']/tr[2]/td[8]")
	public static WebElement batchPopUPRow2RateTxt;

	@FindBy(xpath = "//*[@id='Id_MRPBatch_Grid_body']/tr[3]/td[3]")
	public static WebElement batchPopUPRow3BaseQuantityTxt;

	@FindBy(xpath = "//*[@id='Id_MRPBatch_Grid_body']/tr[3]/td[8]")
	public static WebElement batchPopUPRow3RateTxt;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[2]/td[10]")
	public static WebElement select2ndRow_9thColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[2]/td[11]")
	public static WebElement select2ndRow_10thColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[3]/td[11]")
	public static WebElement select3rdRow_10thColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[3]/td[12]")
	public static WebElement select3rdRow_11thColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[3]/td[10]")
	public static WebElement select3rdRow_9thColumn;

	@FindBy(xpath = "//*[@id='id_body_84']")
	public static WebElement bin2Txt;
	
	@FindBy(xpath = "//*[@id='id_body_85']")
	public static WebElement bin1Txt;
	
	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[4]/td[11]")
	public static WebElement select4thRow_10thColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[4]/td[12]")
	public static WebElement select4thRow_11thColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[7]/td[12]")
	public static WebElement select7thRow_11thColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[3]/td[09]")
	public static WebElement select3rdRow_8thColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[4]/td[09]")
	public static WebElement select4thRow_8thColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[5]/td[09]")
	public static WebElement select5thRow_8thColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[5]/td[12]")
	public static WebElement select5thRow_11thColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[5]/td[13]")
	public static WebElement select5thRow_12thColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[8]/td[13]")
	public static WebElement select8thRow_12thColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[9]/td[13]")
	public static WebElement select9thRow_12thColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[6]/td[09]")
	public static WebElement select6thRow_8thColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[6]/td[12]")
	public static WebElement select6thRow_11thColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[6]/td[13]")
	public static WebElement select6thRow_12thColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[3]/td[9]")
	public static WebElement select3rdRow_OverAllAvgRate;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[3]/td[8]")
	public static WebElement select3rdRow_AvgRate;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[4]/td[8]")
	public static WebElement select4thRow_AvgRate;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[4]/td[9]")
	public static WebElement select4thRow_OverAllAvgRate;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[5]/td[8]")
	public static WebElement select5thRow_AvgRate;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[5]/td[9]")
	public static WebElement select5thRow_OverAllAvgRate;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[6]/td[8]")
	public static WebElement select6thRow_AvgRate;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[8]/td[10]")
	public static WebElement select8thRow_AvgRate;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[9]/td[10]")
	public static WebElement select9thRow_AvgRate;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[6]/td[9]")
	public static WebElement select6thRow_OverAllAvgRate;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[8]/td[9]")
	public static WebElement select8thRow_OverAllAvgRate;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[8]/td[4]")
	public static WebElement select8thRow_AvailaleStock;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[1]/td[4]")
	public static WebElement select1thRow_AvailaleStock;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[4]/td[4]")
	public static WebElement select4thRow_AvailaleStock;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[6]/td[4]")
	public static WebElement select6thRow_AvailaleStock;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[7]/td[4]")
	public static WebElement select7thRow_AvailaleStock;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[9]/td[4]")
	public static WebElement select9thRow_AvailaleStock;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[9]/td[9]")
	public static WebElement select9thRow_OverAllAvgRate;

	// RMA POpup Window
	@FindBy(xpath = "//div[@id='Modal_Header']/h5")
	public static WebElement rmaScreenTitle;

	@FindBy(xpath = "//input[@id='txtSerialNo']")
	public static WebElement rmaSerialNumberTxtField;

	@FindBy(xpath = "//i[@id='sp_search']")
	public static WebElement rmaSearchGridField;

	@FindBy(xpath = "//*[@id='RMASearchTable']/tbody/tr/td[2]")
	public static List<WebElement> rmaSerialNos;

	@FindBy(xpath = "//*[@id='RMASearchTable']/tbody/tr/td/input")
	public static List<WebElement> rmaSerialNoCheckBoxes;

	@FindBy(xpath = "//*[@id='RMA_Table_body']/tr/td[2]")
	public static List<WebElement> rmaSerialNoList;

	@FindBy(xpath = "//input[@id='chkSelectALL']")
	public static WebElement searchRmaSelectAllGridCheckBox;

	@FindBy(xpath = "(//button[@id='div_OK'])[2]")
	public static WebElement searchRmaOkBtn;

	@FindBy(xpath = "//input[@id='txtQuantity']")
	public static WebElement rmaQuantityTxtField;

	@FindBy(xpath = "//i[@class='icon icon-add']")
	public static WebElement rmaAddBtn;

	@FindBy(xpath = "//button[@id='div_OK']")
	public static WebElement RMAPopupOkBtn;

	///

	@FindBy(xpath = "//input[@id='id_body_536870916']")
	public static WebElement enterWarehousettxt;

	@FindBy(xpath = "//tbody[@id='id_body_536870916_table_body']/tr/td[2]")
	public static List<WebElement> svwareHouseListCount;

	@FindBy(xpath = "//input[@id='id_body_23']")
	public static WebElement enterItemttxt;

	@FindBy(xpath = "//input[@id='id_body_33555468']")
	public static WebElement enterAvailableStocktxt;

	@FindBy(xpath = "//input[@id='id_body_26']")
	public static WebElement enterQuantitytxt;

	@FindBy(xpath = "//input[@id='id_body_27']")
	public static WebElement enterRatetxt;

	@FindBy(xpath="//*[@id='id_transaction_entry_detail_table_body']/tr[1]/td[8]")
	public static WebElement  select1stRow_7thColumn;
	
	@FindBy(xpath = "//input[@id='id_body_13']")
	public static WebElement enterBatchtxt;

	@FindBy(xpath = "//textarea[@id='id_body_16777279']")
	public static WebElement enterBatCodetxt;

	@FindBy(xpath = "//*[@id='TransactionNewBin']/div[2]/div/div[2]/div/div/div[1]/div[3]/div/i")  //*[@id='TransactionNewBin']/div[2]/div/div[2]/div/div/div[1]/div[3]/div/i
	public static WebElement binSearchBtn;  //i[@class='icon-search form-control-feedback searchicon searchicon-extra']

	@FindBy(xpath = "//input[@id='id_btnautoallocate']")
	public static WebElement binAutoAllocateBtn;

	@FindBy(xpath = "(//input[@id='id_bins_ok'])[1]")
	public static WebElement binOkBtn;

	@FindBy(xpath = "//input[@id='srch_bin']")
	public static WebElement binSearchTxt;

	@FindBy(xpath = "(//tr[@id='trRender_11'])[1]/td[9]")
	public static WebElement stockLedgerRmaItemHydBalanceQtyAfterSavingPurchaseVocher;

	@FindBy(xpath = "(//tr[@id='trRender_11'])[1]/td[12]")
	public static WebElement stockLedgerRmaItemHydBalanceValueAfterSavingPurchaseVocher;

	@FindBy(xpath = "(//tr[@id='trRender_11'])[1]/td[13]")
	public static WebElement stockLedgerrmaItemHydAvgRateAfterSavingPurchaseVocher;

	@FindBy(xpath = "(//tr[@id='trRender_10'])[1]/td[9]")
	public static WebElement stockLedgerRmaItemSecBalanceQtyAfterSavingPurchaseVocher;

	@FindBy(xpath = "(//tr[@id='trRender_10'])[1]/td[12]")
	public static WebElement stockLedgerRmaItemSecBalanceValueAfterSavingPurchaseVocher;

	@FindBy(xpath = "(//tr[@id='trRender_10'])[1]/td[13]")
	public static WebElement stockLedgerrmaItemSecAvgRateAfterSavingPurchaseVocher;

	@FindBy(xpath = "//*[@id='companyLogo']")
	public static WebElement companyLogo;

	@FindBy(xpath = "(//tr[@id='trRender_11'])[1]/td[9]")
	public static WebElement stockLedgerRmaItemHydBalanceQtyAfterLoginToInvUser;

	@FindBy(xpath = "(//tr[@id='trRender_11'])[1]/td[12]")
	public static WebElement stockLedgerRmaItemHydBalanceValueAfterLoginToInvUser;

	@FindBy(xpath = "(//tr[@id='trRender_11'])[1]/td[13]")
	public static WebElement stockLedgerrmaItemHydAvgRateAfterLoginToInvUser;

	@FindBy(xpath = "(//tr[@id='trRender_10'])[1]/td[9]")
	public static WebElement stockLedgerRmaItemSecBalanceQtyAfterLoginToInvUser;

	@FindBy(xpath = "(//tr[@id='trRender_10'])[1]/td[12]")
	public static WebElement stockLedgerRmaItemSecBalanceValueAfterLoginToInvUser;

	@FindBy(xpath = "(//tr[@id='trRender_10'])[1]/td[13]")
	public static WebElement stockLedgerrmaItemSecAvgRateAfterLoginToInvUser;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[1]/td")
	public static List<WebElement> Row1SalesInvoicevocherBodyList;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[2]/td")
	public static List<WebElement> Row2SalesInvoicevocherBodyList;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[3]/td")
	public static List<WebElement> Row3SalesInvoicevocherBodyList;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[4]/td")
	public static List<WebElement> Row4SalesInvoicevocherBodyList;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[8]/td")
	public static List<WebElement> Row8SalesInvoicevocherBodyList;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[9]/td")
	public static List<WebElement> Row9SalesInvoicevocherBodyList;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[5]/td")
	public static List<WebElement> Row5SalesInvoicevocherBodyList;

	@FindBy(xpath = "//input[@id='id_header_2']")
	public static WebElement stockTransferDatetxt;

	@FindBy(xpath = "//select[@id='id_header_31']")
	public static WebElement stockTransferIssuestxt;

	@FindBy(xpath = "//input[@id='id_header_86']")
	public static WebElement stockTransferWarehousetxt;

	@FindBy(xpath = "//input[@id='id_body_87']")
	public static WebElement enterWarehousettxt2;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[1]/td[7]")
	public static WebElement select1stRow_AvgRate;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[2]/td[7]")
	public static WebElement select2ndRow_AvgRate;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[1]/td[8]")
	public static WebElement select1stRow_OverAllAvgRate;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[2]/td[8]")
	public static WebElement select2ndRow_OverAllAvgRate;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[1]/td[9]")
	public static WebElement select1stRow_BatchText;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[2]/td[12]")
	public static WebElement select2ndRow_11thColumn;

	@FindBy(xpath = "(//tr[@id='trRender_5'])[1]/td[6]")    //(//tr[@id='trRender_12'])[1]/td[6]
	public static WebElement stockLedgerBrBatchReceivedRateAfterSavingStockTransfer;

	@FindBy(xpath = "(//tr[@id='trRender_4'])[1]/td[8]")   //(//tr[@id='trRender_11'])[1]/td[8]
	public static WebElement stockLedgerBrBatchIssuedRateAfterSavingStockTransfer;

	@FindBy(xpath = "(//tr[@id='trRender_5'])[1]/td[13]")
	public static WebElement stockLedgerBrBatchAvgRateAfterSavingStockTransfer;

	@FindBy(xpath = "(//tr[@id='trRender_5'])[1]/td[9]")   //(//tr[@id='trRender_12'])[1]/td[9]
	public static WebElement stockLedgerBrBatchBalanceQtyAfterSavingStockTransfer;

	@FindBy(xpath = "(//tr[@id='trRender_5'])[1]/td[12]")
	public static WebElement stockLedgerBrBatchBalanceValueAfterSavingStockTransfer;

	@FindBy(xpath = "(//tr[@id='trRender_3'])[1]/td[13]")
	public static WebElement stockLedgerFifoItemAvgRateAfterSavingStockTransfer;

	@FindBy(xpath = "(//tr[@id='trRender_3'])[1]/td[9]")
	public static WebElement stockLedgerFifoItemBalanceQtyAfterSavingStockTransfer;

	@FindBy(xpath = "(//tr[@id='trRender_3'])[1]/td[12]")
	public static WebElement stockLedgerFifoItemBalanceValueAfterSavingStockTransfer;

	@FindBy(xpath = "(//tr[@id='trRender_4'])[1]/td[6]")
	public static WebElement stockLedgerRmaItemReceivedRateAfterSavingStockTransfer;

	@FindBy(xpath = "(//tr[@id='trRender_3'])[1]/td[8]")
	public static WebElement stockLedgerRmaItemIssuedRateAfterSavingStockTransfer;

	@FindBy(xpath = "(//tr[@id='trRender_4'])[1]/td[9]")
	public static WebElement stockLedgerRmaItemBalanceQtyAfterSavingStockTransfer;

	@FindBy(xpath = "(//tr[@id='trRender_4'])[1]/td[12]")
	public static WebElement stockLedgerRmaItemBalanceValueAfterSavingStockTransfer;

	@FindBy(xpath = "(//tr[@id='trRender_4'])[1]/td[13]")
	public static WebElement stockLedgerrmaItemAvgRateAfterSavingStockTransfer;

	@FindBy(xpath = "(//tr[@id='trRender_7'])[1]/td[6]")
	public static WebElement stockLedgerBrBatchReceivedRateAfterSavingStockTransfer2;

	@FindBy(xpath = "(//tr[@id='trRender_6'])[1]/td[8]")
	public static WebElement stockLedgerBrBatchIssuedRateAfterSavingStockTransfer2;

	@FindBy(xpath = "(//tr[@id='trRender_7'])[1]/td[13]")
	public static WebElement stockLedgerBrBatchAvgRateAfterSavingStockTransfer2;

	@FindBy(xpath = "(//tr[@id='trRender_7'])[1]/td[9]")
	public static WebElement stockLedgerBrBatchBalanceQtyAfterSavingStockTransfer2;

	@FindBy(xpath = "(//tr[@id='trRender_7'])[1]/td[12]")
	public static WebElement stockLedgerBrBatchBalanceValueAfterSavingStockTransfer2;

	@FindBy(xpath = "(//tr[@id='trRender_5'])[1]/td[13]")
	public static WebElement stockLedgerFifoItemAvgRateAfterSavingStockTransfer2;

	@FindBy(xpath = "(//tr[@id='trRender_5'])[1]/td[9]")
	public static WebElement stockLedgerFifoItemBalanceQtyAfterSavingStockTransfer2;

	@FindBy(xpath = "(//tr[@id='trRender_5'])[1]/td[12]")
	public static WebElement stockLedgerFifoItemBalanceValueAfterSavingStockTransfer2;

	@FindBy(xpath = "(//tr[@id='trRender_6'])[1]/td[6]")
	public static WebElement stockLedgerRmaItemReceivedRateAfterSavingStockTransfer2;

	@FindBy(xpath = "(//tr[@id='trRender_5'])[1]/td[8]")
	public static WebElement stockLedgerRmaItemIssuedRateAfterSavingStockTransfer2;

	@FindBy(xpath = "(//tr[@id='trRender_6'])[1]/td[9]")
	public static WebElement stockLedgerRmaItemBalanceQtyAfterSavingStockTransfer2;

	@FindBy(xpath = "(//tr[@id='trRender_6'])[1]/td[12]")
	public static WebElement stockLedgerRmaItemBalanceValueAfterSavingStockTransfer2;

	@FindBy(xpath = "(//tr[@id='trRender_6'])[1]/td[13]")
	public static WebElement stockLedgerrmaItemAvgRateAfterSavingStockTransfer2;

	@FindBy(xpath = "//a[@id='2035']")
	public static WebElement inventoryTransactionsExcessesStocks;

	@FindBy(xpath = "//input[@id='id_header_2']")
	public static WebElement excessesStockDatetxt;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[4]/td[10]")
	public static WebElement select4thRow_9thColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[5]/td[11]")
	public static WebElement select5thRow_10thColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[6]/td[11]")
	public static WebElement select6thRow_10thColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[6]/td[7]")
	public static WebElement select6thRow_ExcessStockOverAllAvgRate;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[5]/td[7]")
	public static WebElement select5thRow_ExcessStockOverAllAvgRate;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[2]/td[9]")
	public static WebElement select2ndRow_8thColumn;

	@FindBy(xpath = "//a[@id='2034']")
	public static WebElement inventoryTransactionsShortagesInStocks;

	@FindBy(xpath = "//input[@id='id_header_2']")
	public static WebElement shortagesInStockDatetxt;

	@FindBy(xpath = "//*[@id='Id_MRPBatch_Grid_body']/tr[1]/td[2]")
	public static WebElement batchPopUPRow1BatchNoTxt;

	@FindBy(xpath = "//*[@id='Id_MRPBatch_Grid_body']/tr[2]/td[2]")
	public static WebElement batchPopUPRow2BatchNoTxt;

	@FindBy(xpath = "//*[@id='Id_MRPBatch_Grid_body']/tr[3]/td[2]")
	public static WebElement batchPopUPRow3BatchNoTxt;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[3]/td")
	public static List<WebElement> Row3ShortageInStockVoucherBodyList;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[4]/td")
	public static List<WebElement> Row4ShortageInStockVoucherBodyList;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[5]/td")
	public static List<WebElement> Row5ShortageInStockVoucherBodyList;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[6]/td")
	public static List<WebElement> Row6ShortageInStockVoucherBodyList;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[6]/td[10]")
	public static WebElement select6thRow_9thColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[7]/td[10]")
	public static WebElement select7thRow_9thColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[8]/td[10]")
	public static WebElement select8thRow_9thColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[9]/td[11]")
	public static WebElement select9thRow_10thColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[10]/td[11]")
	public static WebElement select10thRow_10thColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[11]/td[11]")
	public static WebElement select11thRow_10thColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[9]/td")
	public static List<WebElement> Row9ShortageInStockVoucherBodyList;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[10]/td")
	public static List<WebElement> Row10ShortageInStockVoucherBodyList;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[11]/td")
	public static List<WebElement> Row11ShortageInStockVoucherBodyList;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[10]/td[2]")
	public static WebElement select10thRow_1stColumn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[11]/td[2]")
	public static WebElement select11thRow_1stColumn;

	@FindBy(xpath = "//*[@id='2009']")
	public static WebElement financialsTransactionsPurchaseMenuPurchasesReturns;

	@FindBy(xpath = "//input[@id='id_header_2']")
	public static WebElement purchaseReturnsDatetxt;

	@FindBy(xpath = "//input[@id='id_header_3']")
	public static WebElement purchasesReturnsAccounttxt;

	@FindBy(xpath = "//input[@id='id_header_4']")
	public static WebElement purchasesReturnsvendorAccounttxt;

	@FindBy(xpath = "//input[@id='id_header_268435459']")
	public static WebElement purchasesReturnsDepartmenttxt;

	@FindBy(xpath = "//*[@id='2016']")
	public static WebElement financialsTransactionsSalesMenuSalesReturn;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[5]/td[9]")
	public static WebElement select5thRow_SalesReturnsAvgRate;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[5]/td[8]")
	public static WebElement select5thRow_SalesReturnsOverallAvgRate;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[6]/td[9]")
	public static WebElement select6thRow_SalesReturnsAvgRate;

	@FindBy(xpath = "//*[@id='id_transaction_entry_detail_table_body']/tr[6]/td[8]")
	public static WebElement select6thRow_SalesReturnsOverallAvgRate;

	@FindBy(xpath = "(//tr[@id='trRender_12'])[1]/td[9]")
	public static WebElement stockLedgerBrBatchAfterSavingSalesReturnsBalanceQty;

	@FindBy(xpath = "(//tr[@id='trRender_12'])[1]/td[12]")
	public static WebElement stockLedgerBrBatchAfterSavingSalesReturnsBalanceValue;

	@FindBy(xpath = "(//tr[@id='trRender_12'])[1]/td[13]")
	public static WebElement stockLedgerBrBatchAfterSavingSalesReturnsAvgRate;

	@FindBy(xpath = "(//tr[@id='trRender_1'])[1]/td[9]")
	public static WebElement stockLedgerBRItemHydBalanceQtyAfterSavingSalesReturns;

	@FindBy(xpath = "(//tr[@id='trRender_1'])[1]/td[12]")
	public static WebElement stockLedgerBRItemHydBalanceValueAfterSavingSalesReturns;

	@FindBy(xpath = "(//tr[@id='trRender_1'])[1]/td[13]")
	public static WebElement stockLedgerBRItemHydAvgRateAfterSavingSalesReturns;

	@FindBy(xpath = "(//tr[@id='trRender_12'])[1]/td[9]")
	public static WebElement stockLedgerBRItemSecBalanceQtyAfterSavingSalesReturns;

	@FindBy(xpath = "(//tr[@id='trRender_12'])[1]/td[12]")
	public static WebElement stockLedgerBRItemSecBalanceValueAfterSavingSalesReturns;

	@FindBy(xpath = "(//tr[@id='trRender_12'])[1]/td[13]")
	public static WebElement stockLedgerBRItemSecAvgRateAfterAfterSavingSalesReturns;

	@FindBy(xpath = "(//tr[@id='trRender_17'])[1]/td[9]")
	public static WebElement stockLedgerFifoItemAfterSavingSalesReturnsBalanceQty;

	@FindBy(xpath = "(//tr[@id='trRender_17'])[1]/td[12]")
	public static WebElement stockLedgerFifoItemAfterSavingSalesReturnsBalanceValue;

	@FindBy(xpath = "(//tr[@id='trRender_17'])[1]/td[13]")
	public static WebElement stockLedgerFifoItemAfterSavingSalesReturnsAvgRate;

	@FindBy(xpath = "(//tr[@id='trRender_9'])[1]/td[9]")
	public static WebElement stockLedgerFifoItemHydBalanceQtyAfterSavingSalesReturns;

	@FindBy(xpath = "(//tr[@id='trRender_9'])[1]/td[12]")
	public static WebElement stockLedgerFifoItemHydBalanceValueAfterSavingSalesReturns;

	@FindBy(xpath = "(//tr[@id='trRender_9'])[1]/td[13]")
	public static WebElement stockLedgerFifoItemHydAvgRateAfterSavingSalesReturns;

	@FindBy(xpath = "(//tr[@id='trRender_9'])[1]/td[9]")
	public static WebElement stockLedgerFifoItemSecBalanceQtyAfterSavingSalesReturns;

	@FindBy(xpath = "(//tr[@id='trRender_9'])[1]/td[12]")
	public static WebElement stockLedgerFifoItemSecBalanceValueAfterSavingSalesReturns;

	@FindBy(xpath = "(//tr[@id='trRender_9'])[1]/td[13]")
	public static WebElement stockLedgerFifoItemSecAvgRateAfterAfterSavingSalesReturns;

	@FindBy(xpath = "(//tr[@id='trRender_15'])[1]/td[9]")
	public static WebElement stockLedgerRmaItemAfterSavingSalesReturnsBalanceQty;

	@FindBy(xpath = "(//tr[@id='trRender_15'])[1]/td[12]")
	public static WebElement stockLedgerRmaItemAfterSavingSalesReturnsBalanceValue;

	@FindBy(xpath = "(//tr[@id='trRender_15'])[1]/td[13]")
	public static WebElement stockLedgerRmaItemAfterSavingSalesReturnsAvgRate;

	@FindBy(xpath = "(//tr[@id='trRender_0'])[1]/td[9]")
	public static WebElement stockLedgerRmaItemHydBalanceQtyAfterSavingSalesReturns;

	@FindBy(xpath = "(//tr[@id='trRender_0'])[1]/td[12]")
	public static WebElement stockLedgerRmaItemHydBalanceValueAfterSavingSalesReturns;

	@FindBy(xpath = "(//tr[@id='trRender_0'])[1]/td[13]")
	public static WebElement stockLedgerRmaItemHydAvgRateAfterSavingSalesReturns;

	@FindBy(xpath = "(//tr[@id='trRender_16'])[1]/td[9]")
	public static WebElement stockLedgerRmaItemSecBalanceQtyAfterSavingSalesReturns;

	@FindBy(xpath = "(//tr[@id='trRender_16'])[1]/td[12]")
	public static WebElement stockLedgerRmaItemSecBalanceValueAfterSavingSalesReturns;

	@FindBy(xpath = "(//tr[@id='trRender_16'])[1]/td[13]")
	public static WebElement stockLedgerRmaItemSecAvgRateAfterAfterSavingSalesReturns;

	@FindBy(xpath = "//a[@id='575']")
	public static WebElement InventoryReportsStockMovement;

	@FindBy(xpath = "//input[@id='liSelectAllMasters']")
	public static WebElement sl_HeaderSelectChkBox;
	
	@FindBy(xpath = "//*[@id='selectAllMasters_']//..//span")
	public static WebElement selectAllItemChkBox;

	// Reports Table Row List
	@FindBy(xpath = "(//tr[@id='trRender_0'])[1]/td")
	public static List<WebElement> reportsRow1List;

	@FindBy(xpath = "(//tr[@id='trRender_1'])[1]/td")
	public static List<WebElement> reportsRow2List;

	@FindBy(xpath = "(//tr[@id='trRender_2'])[1]/td")
	public static List<WebElement> reportsRow3List;

	@FindBy(xpath = "(//tr[@id='trRender_3'])[1]/td")
	public static List<WebElement> reportsRow4List;

	@FindBy(xpath = "(//tr[@id='trRender_4'])[1]/td")
	public static List<WebElement> reportsRow5List;

	@FindBy(xpath = "(//tr[@id='trRender_5'])[1]/td")
	public static List<WebElement> reportsRow6List;

	@FindBy(xpath = "(//tr[@id='trRender_6'])[1]/td")
	public static List<WebElement> reportsRow7List;

	@FindBy(xpath = "(//tr[@id='trRender_7'])[1]/td")
	public static List<WebElement> reportsRow8List;

	@FindBy(xpath = "(//tr[@id='trRender_8'])[1]/td")
	public static List<WebElement> reportsRow9List;

	@FindBy(xpath = "(//tr[@id='trRender_9'])[1]/td")
	public static List<WebElement> reportsRow10List;

	@FindBy(xpath = "(//tr[@id='trRender_10'])[1]/td")
	public static List<WebElement> reportsRow11List;

	@FindBy(xpath = "(//tr[@id='trRender_11'])[1]/td")
	public static List<WebElement> reportsRow12List;

	@FindBy(xpath = "(//tr[@id='trRender_12'])[1]/td")
	public static List<WebElement> reportsRow13List;
	
	@FindBy(xpath="//*[@id='id_transaction_viewcontainer']/li[1]/a")
	public static WebElement  homePageAllVouchersView;
	
	@FindBy(xpath="//tbody[@id='tblBodyTransRender']/tr[1]/td[13]")
	public static WebElement voucherGridRow1AuthorizationStatus;

	@FindBy(xpath = "(//tr[@id='trRender_13'])[1]/td")
	public static List<WebElement> reportsRow14List;

	@FindBy(xpath = "(//tr[@id='trRender_14'])[1]/td")
	public static List<WebElement> reportsRow15List;

	@FindBy(xpath = "(//tr[@id='trRender_15'])[1]/td")
	public static List<WebElement> reportsRow16List;
	
	@FindBy(xpath="(//tr[@id='trRender_16'])[1]/td")
	public static List<WebElement> reportsRow17List;
	
	@FindBy(xpath="(//tr[@id='trRender_17'])[1]/td")
	public static List<WebElement> reportsRow18List;

	@FindBy(xpath = "//select[@id='RITCombobox__1']")
	public static WebElement stockMovementDropdown;

	@FindBy(xpath = "//a[@id='578']")
	public static WebElement InventoryReportsStockValuation;

	@FindBy(xpath = "//select[@id='RITCombobox__1']")
	public static WebElement stockValuationDropdown;

	@FindBy(xpath = "//a[@id='621']")
	public static WebElement InventoryReportsStockAgeingAnalysisMenu;

	@FindBy(xpath = "//a[@id='582']/span")
	public static WebElement InventoryReportsAgeingAnalysis;

	@FindBy(xpath = "(//select[@id='RITCombobox__1'])[1]")
	public static WebElement stockAgeingDropdown;

	@FindBy(xpath = "//input[@id='MasterGroup__101']")
	public static WebElement stockAgeingAnalysisWarehouseField;

	@FindBy(xpath = "//a[@id='645']")
	public static WebElement InventoryReportsStockBalanceByWarehouseMenu;

	@FindBy(xpath = "//a[@id='557']/span")
	public static WebElement InventoryStockStatementMenu;
	
	@FindBy(xpath = "//a[@id='137']")
	public static WebElement inventoryTransactionsMenu;
	
	@FindBy(xpath = "//a[@id='2033']")
	public static WebElement inventoryTransactionsStockMenu;
	
	@FindBy(xpath = "//a[@id='2056']/span")
	public static WebElement inventoryTransactionsStockOut;
	
	@FindBy(xpath = "//*[@id='trRender_1']/td[2]/input")
	public static WebElement stockoutHomepageRpw1Checkbx;
	
	@FindBy(xpath = "//a[@id='id_transaction_homescreen_authorize']")
	public static WebElement stockOutHomeAuthBtn;

	@FindBy(xpath = "//*[@id='id_transaction_viewcontainer']/li[3]/a")
	public static WebElement stockOutPendingAuthLink;
	
	@FindBy(xpath="//button[@class='accordion-button collapsed']")
	public static WebElement  homePageVoucherArrowBtn;
	
	@FindBy (xpath="//tbody[@id='tblBodyTransRender']/tr/td[5]")
	public static List<WebElement> grid_VoucherNoList;
	
	@FindBy (xpath="//tbody[@id='tblBodyTransRender']/tr/td[2]/input")
	public static List<WebElement> grid_CheckBoxList;
	
	@FindBy(xpath="//input[@id='id_header_3']")
	public static WebElement  stockAccounttxt;
	
	@FindBy(xpath="//input[@id='id_header_4']")
	public static WebElement  partyAccounttxt;
	
	@FindBy(xpath="//input[@id='id_header_86']")
	public static WebElement  Warehouse1txt;
	
	@FindBy(xpath="//input[@id='id_header_87']")
	public static WebElement  Warehouse2txt;
	
	@FindBy(xpath="//input[@id='id_header_268435459']")
	public static WebElement  stockOutDepartmenttxt;
	
	@FindBy(xpath = "//a[@id='2036']")
	public static WebElement inventoryTransactionsStockMenuStockTransfersMenu;

	
	@FindBy(xpath = "//*[@id='tagWiseData']/tbody/tr[4]/td")
	public static List<WebElement> row4BalanceByWareHouseList;
	
	
	
	
	
	// BATCH NEGATIVE SCENARIOS ELEMENTS
	
	
	
	
	
	
	
	
	

}
