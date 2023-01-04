package com.focus.Pages;

import java.awt.AWTException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.focus.base.BaseEngine;
import com.focus.supporters.ExcelReader;
import com.focus.utilities.POJOUtility;

public class ItemQueryPage extends BaseEngine {
	
	
    private static String resPass="Pass";
    private static String resFail="Fail";
    private static ExcelReader excelReader;
    private static String xlfile=getBaseDir()+"\\src\\main\\resources\\testdata\\FocusTestData.xlsx";
    private static String xlSheetName = "ItemQueryPage";

	public ItemQueryPage(WebDriver driver) {
		PageFactory.initElements(driver, this);

	}	

	public static String checkValidationMessage(String ExpMessage)
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		
		Thread.sleep(2000);
		try {
			getFluentWebDriverWait().until(ExpectedConditions.visibilityOf(errorMessage));
			String actErrorMessage = errorMessage.getText();
			String expErrorMessage = ExpMessage;

			try {
				getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(errorMessageCloseBtn));
				errorMessageCloseBtn.click();

				System.out.println("ValidationMessage  :  " + actErrorMessage + " Value Expected : " + expErrorMessage);

				return actErrorMessage;
			} catch (Exception ee) {

				System.out.println("ValidationMessage  :  " + actErrorMessage + " Value Expected : " + expErrorMessage);

				return actErrorMessage;
			}
		} catch (Exception e) {
			System.err.println("Error Message NOT Found or NOT Clickable");
			System.err.println(e.getMessage());

			String Exception = e.getMessage();

			return Exception;
		}
	}
	
	
	public boolean checkLogin() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException, AWTException 
	{
		Thread.sleep(2000);

		LoginPage lp=new LoginPage(getDriver()); 
		
	    String unamelt="su";
	  
	    String pawslt="su";
	      
	    lp.enterUserName(unamelt);
	    
	    lp.enterPassword(pawslt);
	    
	    String compname="Maintain Quantity a";

		Select oSelect = new Select(companyDropDownList);

		List <WebElement> elementCount = oSelect.getOptions();

		int cqSize = elementCount.size();

		System.out.println("CompanyDropdownList Count :"+cqSize);

		int i;

		for(i=0; i<elementCount.size(); i++) 
		{

			elementCount.get(i).getText();

			String optionName = elementCount.get(i).getText();
			if(optionName.toUpperCase().startsWith(compname.toUpperCase()))
			{
				System.out.println("q"+elementCount.get(i).getText());
				elementCount.get(i).click();
			}
		}


		Thread.sleep(2000);
	    
	    lp.clickOnSignInBtn();
	    
	  
		Thread.sleep(5000);
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(userNameDisplay));
		String actUserInfo=userNameDisplay.getText();
		
		System.out.println("User Info  : "+actUserInfo);
		
		System.out.println("User Info Capture Text  :  "+userNameDisplay.getText());
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(companyLogo));
		companyLogo.click();
		
		String getCompanyTxt=companyName.getText();
		String getLoginCompanyName=getCompanyTxt.substring(0, 19);
		System.out.println("company name  :  "+ getLoginCompanyName);
		companyLogo.click();
		
		boolean actDashboard = dashboard.isDisplayed();
		
		
		String expUserInfo           ="SU";
		String expLoginCompanyName   ="Maintain Quantity a";
		boolean expDashboard = true;
		
		System.err.println(actDashboard);
		System.err.println(expDashboard);
		
		System.err.println(getLoginCompanyName);
		System.err.println(expLoginCompanyName);
		
		
		Thread.sleep(2000);
	
		
		if (actUserInfo.equalsIgnoreCase(expUserInfo) && getLoginCompanyName.contains(expLoginCompanyName) && actDashboard==expDashboard) 
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	

	public boolean validteItemQueryStocksInBatchItem() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {

		
		 excelReader=new ExcelReader(POJOUtility.getExcelPath());
		
		click(inventoryMenu);
		click(InventoryReportsMenu);
		click(itemqueryMenu);
		click(itemTxt);
		Thread.sleep(2000);
		enterText(itemTxt, excelReader.getCellData(xlSheetName, 8, 5));
		Thread.sleep(3000);
		click(getStockBtn);
		Thread.sleep(3000);
		
		String actlOpenStockQuantity  = getText(openStockQuantityTxt);
		String expOpenStockQuantity   = excelReader.getCellData(xlSheetName, 8, 6);
		excelReader.setCellData(xlfile, xlSheetName, 8, 7, actlOpenStockQuantity);

		String actlCurrentStock       = getText(currentStockTxt);
		String expCurrentStock        = excelReader.getCellData(xlSheetName, 9, 6);
		excelReader.setCellData(xlfile, xlSheetName, 9, 7, actlCurrentStock);

		String actlavgStockRate       = getText(avgStockRateTxt);
		String expavgStockRate        = excelReader.getCellData(xlSheetName, 10, 6);
		excelReader.setCellData(xlfile, xlSheetName, 10, 7, actlavgStockRate);

		String actlstockValueTxt      = getText(stockValueTxt);
		String expstockValueTxt       = excelReader.getCellData(xlSheetName, 11, 6);
		excelReader.setCellData(xlfile, xlSheetName, 11, 7, actlstockValueTxt);
		

		System.out.println("=====Stocks In Batch Items======");

		System.out.println("actlOpenStockQuantity      :" + actlOpenStockQuantity);
		System.out.println("expOpenStockQuantity       :" + expOpenStockQuantity);

		System.out.println("actlCurrentStock           :" + actlCurrentStock);
		System.out.println("expCurrentStock            :" + expCurrentStock);

		System.out.println("actlavgStockRate           :" + actlavgStockRate);
		System.out.println("expavgStockRate            :" + expavgStockRate);

		System.out.println("actlstockValueTxt          :" + actlstockValueTxt);
		System.out.println("expstockValueTxt           :" + expstockValueTxt);

		if (actlOpenStockQuantity.equalsIgnoreCase(expOpenStockQuantity)
				&& actlCurrentStock.equalsIgnoreCase(expCurrentStock)
				&& actlavgStockRate.equalsIgnoreCase(expavgStockRate)
				&& actlstockValueTxt.equalsIgnoreCase(expstockValueTxt)) {
			
			excelReader.setCellData(xlfile, xlSheetName, 8, 8, resPass);
			return true;
		} else {
			excelReader.setCellData(xlfile, xlSheetName, 8, 8, resFail);
			return false;
		}

	}

	public boolean validteItemQueryStocksInFifoItem() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {

		excelReader=new ExcelReader(POJOUtility.getExcelPath());
		
		click(itemTxt);
		Thread.sleep(2000);
		itemTxt.sendKeys(Keys.CONTROL, "a");
		Thread.sleep(2000);
		itemTxt.sendKeys(Keys.BACK_SPACE);
		enterText(itemTxt, excelReader.getCellData(xlSheetName, 12, 5));
		Thread.sleep(3000);
		click(getStockBtn);
		Thread.sleep(3000);

		String actlOpenStockQuantity = getText(openStockQuantityTxt);
		String expOpenStockQuantity  = excelReader.getCellData(xlSheetName, 12, 6);
		excelReader.setCellData(xlfile, xlSheetName, 12, 7, actlOpenStockQuantity);

		String actlCurrentStock      = getText(currentStockTxt);
		String expCurrentStock       = excelReader.getCellData(xlSheetName, 13, 6);
		excelReader.setCellData(xlfile, xlSheetName, 13, 7, actlCurrentStock);

		String actlavgStockRate      = getText(avgStockRateTxt);
		String expavgStockRate       = excelReader.getCellData(xlSheetName, 14, 6);
		excelReader.setCellData(xlfile, xlSheetName, 14, 7, actlavgStockRate);

		String actlstockValueTxt     = getText(stockValueTxt);
		String expstockValueTxt      = excelReader.getCellData(xlSheetName, 15, 6);
		excelReader.setCellData(xlfile, xlSheetName, 15, 7, actlstockValueTxt);

		System.out.println("=====Stocks In FIFO Items======");

		System.out.println("actlOpenStockQuantity      :" + actlOpenStockQuantity);
		System.out.println("expOpenStockQuantity       :" + expOpenStockQuantity);

		System.out.println("actlCurrentStock           :" + actlCurrentStock);
		System.out.println("expCurrentStock            :" + expCurrentStock);

		System.out.println("actlavgStockRate           :" + actlavgStockRate);
		System.out.println("expavgStockRate            :" + expavgStockRate);

		System.out.println("actlstockValueTxt          :" + actlstockValueTxt);
		System.out.println("expstockValueTxt           :" + expstockValueTxt);

		if (actlOpenStockQuantity.equalsIgnoreCase(expOpenStockQuantity)
				&& actlCurrentStock.equalsIgnoreCase(expCurrentStock)
				&& actlavgStockRate.equalsIgnoreCase(expavgStockRate)
				&& actlstockValueTxt.equalsIgnoreCase(expstockValueTxt)) {
			excelReader.setCellData(xlfile, xlSheetName, 12, 8, resPass);
			return true;
		} else {
			excelReader.setCellData(xlfile, xlSheetName, 12, 8, resFail);
			return false;
		}

	}

	public boolean validteItemQueryStocksInWAItem() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {

		excelReader=new ExcelReader(POJOUtility.getExcelPath());
		
		click(itemTxt);
		Thread.sleep(2000);
		itemTxt.sendKeys(Keys.CONTROL, "a");
		Thread.sleep(2000);
		itemTxt.sendKeys(Keys.BACK_SPACE);
		enterText(itemTxt, excelReader.getCellData(xlSheetName, 16, 5));
		Thread.sleep(3000);
		click(getStockBtn);
		Thread.sleep(3000);

		String actlOpenStockQuantity = getText(openStockQuantityTxt);
		String expOpenStockQuantity  = excelReader.getCellData(xlSheetName, 16, 6);
		excelReader.setCellData(xlfile, xlSheetName, 16, 7, actlOpenStockQuantity);

		String actlCurrentStock      = getText(currentStockTxt);
		String expCurrentStock       = excelReader.getCellData(xlSheetName, 17, 6);
		excelReader.setCellData(xlfile, xlSheetName, 17, 7, actlCurrentStock);

		String actlavgStockRate      = getText(avgStockRateTxt);
		String expavgStockRate       = excelReader.getCellData(xlSheetName, 18, 6);
		excelReader.setCellData(xlfile, xlSheetName, 18, 7, actlavgStockRate);

		String actlstockValueTxt     = getText(stockValueTxt);
		String expstockValueTxt      = excelReader.getCellData(xlSheetName, 19, 6);
		excelReader.setCellData(xlfile, xlSheetName, 19, 7, actlstockValueTxt);
		
		Thread.sleep(2000);

		ScrollToElement(balanceByWarehouseBtn);
		click(balanceByWarehouseBtn);

		Thread.sleep(3000);

		int count = row1BalanceByWareHouseList.size();

		ArrayList<String> warehouseRow1 = new ArrayList<String>();

		for (int i = 0; i < count; i++) {
			String data = row1BalanceByWareHouseList.get(i).getText();
			warehouseRow1.add(data);
		}

		String actwarehouseRow1List = warehouseRow1.toString();
		String expWarehouseRow1List = excelReader.getCellData(xlSheetName, 20, 6);
		excelReader.setCellData(xlfile, xlSheetName, 20, 7, actwarehouseRow1List);

		int count1 = row2BalanceByWareHouseList.size();

		ArrayList<String> warehouseRow2 = new ArrayList<String>();

		for (int i = 0; i < count1; i++) {
			String data = row2BalanceByWareHouseList.get(i).getText();
			warehouseRow2.add(data);
		}

		String actwarehouseRow2List = warehouseRow2.toString();
		String expWarehouseRow2List = excelReader.getCellData(xlSheetName, 21, 6);
		excelReader.setCellData(xlfile, xlSheetName, 21, 7, actwarehouseRow2List);

		int count2 = row3BalanceByWareHouseList.size();

		ArrayList<String> warehouseRow3 = new ArrayList<String>();

		for (int i = 0; i < count2; i++) {
			String data = row3BalanceByWareHouseList.get(i).getText();
			warehouseRow3.add(data);
		}

		String actwarehouseRow3List = warehouseRow3.toString();
		String expWarehouseRow3List = excelReader.getCellData(xlSheetName, 22, 6);
		excelReader.setCellData(xlfile, xlSheetName, 22, 7, actwarehouseRow3List);

		System.out.println("=====Stocks In WA Items======");
		
		

		System.out.println("actlOpenStockQuantity      :" + actlOpenStockQuantity);
		System.out.println("expOpenStockQuantity       :" + expOpenStockQuantity);

		System.out.println("actlCurrentStock           :" + actlCurrentStock);
		System.out.println("expCurrentStock            :" + expCurrentStock);

		System.out.println("actlavgStockRate           :" + actlavgStockRate);
		System.out.println("expavgStockRate            :" + expavgStockRate);

		System.out.println("actlstockValueTxt          :" + actlstockValueTxt);
		System.out.println("expstockValueTxt           :" + expstockValueTxt);

	
		
		System.out.println("===========Balance By Warehouse=======");

		System.out.println(actwarehouseRow1List);
		System.out.println(expWarehouseRow1List);

		System.out.println(actwarehouseRow2List);
		System.out.println(expWarehouseRow2List);

		System.out.println(actwarehouseRow3List);
		System.out.println(expWarehouseRow3List);

		if (actlOpenStockQuantity.equalsIgnoreCase(expOpenStockQuantity)
				&& actlCurrentStock.equalsIgnoreCase(expCurrentStock)
				&& actlavgStockRate.equalsIgnoreCase(expavgStockRate)
				&& actlstockValueTxt.equalsIgnoreCase(expstockValueTxt)
				&& actwarehouseRow1List.equalsIgnoreCase(expWarehouseRow1List)
				&& actwarehouseRow2List.equalsIgnoreCase(expWarehouseRow2List)
				&& actwarehouseRow3List.equalsIgnoreCase(expWarehouseRow3List)) 
		{
			
			excelReader.setCellData(xlfile, xlSheetName, 16, 8, resPass);
			return true;
		} else {
			excelReader.setCellData(xlfile, xlSheetName, 16, 8, resFail);
			return false;
		}

	}

	

	

	public boolean checkStockLedgerReportForBrBatchItem()	
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		
		excelReader=new ExcelReader(POJOUtility.getExcelPath());
		
		click(inventoryMenu);
		click(InventoryReportsMenu);
		click(InventoryStockLedgerMenu);
		Thread.sleep(3000);
		
		int rowcount = stockLedgerHometableRowCount.size();

		System.out.println(rowcount);

		for (int i = 0; i < rowcount; i++) {
			String actName = ledgerHometableItemNamesList.get(i).getText();

			System.out.println(actName);

			if (actName.equalsIgnoreCase(excelReader.getCellData(xlSheetName, 23, 5))) {
				stockLedgerHometableItemChkboxList.get(i).click();
			}
		}

		dropDown(sl_DateOptionDropdown, excelReader.getCellData(xlSheetName, 24, 5));
		Thread.sleep(2000);
		click(sl_OkBtn);
		Thread.sleep(2000);
		click(stockLedgerLastBtn);
		Thread.sleep(3000);

		String actStockLedgerBrBatchBalanceQty    = getText(stockLedgerBrBatchBalanceQty);
		String expStockLedgerBrBatchBalanceQty    = excelReader.getCellData(xlSheetName, 23, 6);
		excelReader.setCellData(xlfile, xlSheetName, 23, 7, actStockLedgerBrBatchBalanceQty);

		String actStockLedgerBrBatchBalanceValue  = getText(stockLedgerBrBatchBalanceValue);
		String expStockLedgerBrBatchBalanceValue  = excelReader.getCellData(xlSheetName, 24, 6);
		excelReader.setCellData(xlfile, xlSheetName, 24, 7, actStockLedgerBrBatchBalanceValue);

		String actStockLedgerBrBatchAvgRate       = getText(stockLedgerBrBatchAvgRate);
		String expStockLedgerBrBatchAvgRate       = excelReader.getCellData(xlSheetName, 25, 6);
		excelReader.setCellData(xlfile, xlSheetName, 25, 7, actStockLedgerBrBatchAvgRate);

		System.out.println("actStockLedgerBrBatchBalanceQty      :" + actStockLedgerBrBatchBalanceQty);
		System.out.println("expStockLedgerBrBatchBalanceQty      :" + expStockLedgerBrBatchBalanceQty);

		System.out.println("actStockLedgerBrBatchBalanceValue    :" + actStockLedgerBrBatchBalanceValue);
		System.out.println("expStockLedgerBrBatchBalanceValue    :" + expStockLedgerBrBatchBalanceValue);

		System.out.println("actStockLedgerBrBatchAvgRate         :" + actStockLedgerBrBatchAvgRate);
		System.out.println("expStockLedgerBrBatchAvgRate         :" + expStockLedgerBrBatchAvgRate);

		if (actStockLedgerBrBatchBalanceQty.equalsIgnoreCase(expStockLedgerBrBatchBalanceQty)
				&& actStockLedgerBrBatchBalanceValue.equalsIgnoreCase(expStockLedgerBrBatchBalanceValue)
				&& actStockLedgerBrBatchAvgRate.equalsIgnoreCase(expStockLedgerBrBatchAvgRate)) {

			click(report_CloseBtn);
			excelReader.setCellData(xlfile, xlSheetName, 23, 8, resPass);

			return true;
		} else {
			click(report_CloseBtn);

			excelReader.setCellData(xlfile, xlSheetName, 23, 8, resPass);
			return false;
		}
	}

	

	public boolean checkStockLedgerReportForFifoItem()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		
		excelReader=new ExcelReader(POJOUtility.getExcelPath());
		
		Thread.sleep(3000);

		if (fifoItemCheckBox.isSelected() == false) {
			click(fifoItemCheckBox);
		}

		if (brBatchItemCheckBox.isSelected() == true) {
			click(brBatchItemCheckBox);
		}

		if (rmaItemCheckBox.isSelected() == true) {
			click(rmaItemCheckBox);

		}

		dropDown(sl_DateOptionDropdown, excelReader.getCellData(xlSheetName, 26, 5));
		Thread.sleep(2000);
		click(sl_OkBtn);
		Thread.sleep(2000);
		click(stockLedgerLastBtn);
		Thread.sleep(3000);

		String actstockLedgerFifoItemBalanceQty   = getText(stockLedgerFifoItemBalanceQty);
		String expstockLedgerFifoItemBalanceQty   = excelReader.getCellData(xlSheetName, 26, 6);
		excelReader.setCellData(xlfile, xlSheetName, 26, 7, actstockLedgerFifoItemBalanceQty);

		String actstockLedgerFifoItemBalanceValue = getText(stockLedgerFifoItemBalanceValue);
		String expstockLedgerFifoItemBalanceValue = excelReader.getCellData(xlSheetName, 27, 6);
		excelReader.setCellData(xlfile, xlSheetName, 27, 7, actstockLedgerFifoItemBalanceValue);

		String actstockLedgerFifoItemAvgRate      = getText(stockLedgerFifoItemAvgRate);
		String expstockLedgerFifoItemAvgRate      = excelReader.getCellData(xlSheetName, 28, 6);
		excelReader.setCellData(xlfile, xlSheetName, 28, 7, actstockLedgerFifoItemAvgRate);

		System.out.println("actstockLedgerFifoItemBalanceQty      :" + actstockLedgerFifoItemBalanceQty);
		System.out.println("expstockLedgerFifoItemBalanceQty      :" + expstockLedgerFifoItemBalanceQty);

		System.out.println("actstockLedgerFifoItemBalanceValue    :" + actstockLedgerFifoItemBalanceValue);
		System.out.println("expstockLedgerFifoItemBalanceValue    :" + expstockLedgerFifoItemBalanceValue);

		System.out.println("actstockLedgerFifoItemAvgRate         :" + actstockLedgerFifoItemAvgRate);
		System.out.println("expstockLedgerFifoItemAvgRate         :" + expstockLedgerFifoItemAvgRate);

		if (actstockLedgerFifoItemBalanceQty.equalsIgnoreCase(expstockLedgerFifoItemBalanceQty)
				&& actstockLedgerFifoItemBalanceValue.equalsIgnoreCase(expstockLedgerFifoItemBalanceValue)
				&& actstockLedgerFifoItemAvgRate.equalsIgnoreCase(expstockLedgerFifoItemAvgRate)) {

			click(report_CloseBtn);
			excelReader.setCellData(xlfile, xlSheetName, 26, 8, resPass);
			return true;
		} else {
			click(report_CloseBtn);
			excelReader.setCellData(xlfile, xlSheetName, 26, 8, resFail);
			return false;
		}
	}
	
	
	

	public boolean checkStockLedgerReportForRmaItem()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		
		excelReader=new ExcelReader(POJOUtility.getExcelPath());
		
		Thread.sleep(3000);

		if (fifoItemCheckBox.isSelected() == true) {
			click(fifoItemCheckBox);

		}

		if (brBatchItemCheckBox.isSelected() == true) {
			click(brBatchItemCheckBox);
		}

		if (rmaItemCheckBox.isSelected() == false) {
			click(rmaItemCheckBox);

		}

		dropDown(sl_DateOptionDropdown, excelReader.getCellData(xlSheetName, 29, 5));
		Thread.sleep(2000);
		click(sl_OkBtn);
		Thread.sleep(2000);
		click(stockLedgerLastBtn);
		Thread.sleep(2000);
		
		//click(stockLedgerPreviousBtn);
		Thread.sleep(3000);

		String actstockLedgerRmaItemBalanceQty   = getText(stockLedgerRmaItemBalanceQty);
		String expstockLedgerRmaItemBalanceQty   = excelReader.getCellData(xlSheetName, 29, 6);
		excelReader.setCellData(xlfile, xlSheetName, 29, 7, actstockLedgerRmaItemBalanceQty);


		String actstockLedgerRmaItemBalanceValue = getText(stockLedgerRmaItemBalanceValue);
		String expstockLedgerRmaItemBalanceValue = excelReader.getCellData(xlSheetName, 30, 6);
		excelReader.setCellData(xlfile, xlSheetName, 30, 7, actstockLedgerRmaItemBalanceValue);


		String actstockLedgerrmaItemAvgRate      = getText(stockLedgerrmaItemAvgRate);
		String expstockLedgerrmaItemAvgRate      = excelReader.getCellData(xlSheetName, 31, 6);
		excelReader.setCellData(xlfile, xlSheetName, 31, 7, actstockLedgerrmaItemAvgRate);


		System.out.println("actstockLedgerRmaItemBalanceQty      :" + actstockLedgerRmaItemBalanceQty);
		System.out.println("expstockLedgerRmaItemBalanceQty      :" + expstockLedgerRmaItemBalanceQty);

		System.out.println("actstockLedgerRmaItemBalanceValue    :" + actstockLedgerRmaItemBalanceValue);
		System.out.println("expstockLedgerRmaItemBalanceValue    :" + expstockLedgerRmaItemBalanceValue);

		System.out.println("actstockLedgerrmaItemAvgRate         :" + actstockLedgerrmaItemAvgRate);
		System.out.println("expstockLedgerrmaItemAvgRate         :" + expstockLedgerrmaItemAvgRate);
		
		
		click(stockLedgerFilterBtn);
		
		Thread.sleep(2000);
		
		click(stockLedgerFilterField);
		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(stockLedgerFilterText));

		String actFilterpopupIsDisplaying = Boolean.toString(stockLedgerFilterText.isDisplayed());
		String expFilterpopupIsDisplaying = excelReader.getCellData(xlSheetName, 32, 6);
		excelReader.setCellData(xlfile, xlSheetName, 32, 7, actFilterpopupIsDisplaying);


		System.out.println("Filter Popup isDisplaying  : " + actFilterpopupIsDisplaying + "  Value Expected  "
				+ expFilterpopupIsDisplaying);
		
		click(stockLedgerFilterWarehouseField);
		enterText(stockLedgerFilterWarehouseField, excelReader.getCellData(xlSheetName, 30, 5));
		Thread.sleep(2000);
		click(stockLedgerFilterOkBtn);
		Thread.sleep(1000);
		click(stockLedgerLastBtn);
		Thread.sleep(2000);
		
		String actstockLedgerRmaItemHydBalanceQty   = getText(stockLedgerRmaItemHydBalanceQty);
		String expstockLedgerRmaItemHydBalanceQty   = excelReader.getCellData(xlSheetName, 33, 6);
		excelReader.setCellData(xlfile, xlSheetName, 33, 7, actstockLedgerRmaItemHydBalanceQty);
		

		String actstockLedgerRmaItemHydBalanceValue = getText(stockLedgerRmaItemHydBalanceValue);
		String expstockLedgerRmaItemHydBalanceValue = excelReader.getCellData(xlSheetName, 34, 6);
		excelReader.setCellData(xlfile, xlSheetName, 34, 7, actstockLedgerRmaItemHydBalanceValue);

		String actstockLedgerrmaItemHydAvgRate      = getText(stockLedgerrmaItemHydAvgRate);
		String expstockLedgerrmaItemHydAvgRate      = excelReader.getCellData(xlSheetName, 35, 6);
		excelReader.setCellData(xlfile, xlSheetName, 35, 7, actstockLedgerrmaItemHydAvgRate);
		
		System.out.println("=========actstockLedgerRmaItem  HydWareHouse  Values============   : ");
		
		System.out.println("actstockLedgerRmaItemHydBalanceQty      :" + actstockLedgerRmaItemHydBalanceQty);
		System.out.println("expstockLedgerRmaItemHydBalanceQty      :" + expstockLedgerRmaItemHydBalanceQty);

		System.out.println("actstockLedgerRmaItemHydBalanceValue    :" + actstockLedgerRmaItemHydBalanceValue);
		System.out.println("expstockLedgerRmaItemHydBalanceValue    :" + expstockLedgerRmaItemHydBalanceValue);

		System.out.println("actstockLedgerrmaItemHydAvgRate         :" + actstockLedgerrmaItemHydAvgRate);
		System.out.println("expstockLedgerrmaItemHydAvgRate         :" + expstockLedgerrmaItemHydAvgRate);
		
		click(report_CloseBtn);
		Thread.sleep(1000);
		click(stockLedgerHomePageFilterBtn);
		Thread.sleep(2000);
		click(stockLedgerFilterWarehouseField2);
		Thread.sleep(1000);
		click(stockLedgerFilterWarehouseField3);
		Thread.sleep(1500);
		stockLedgerFilterWarehouseField4.sendKeys(Keys.CONTROL, "a");
		Thread.sleep(1000);
		stockLedgerFilterWarehouseField4.sendKeys(Keys.BACK_SPACE);
		Thread.sleep(1000);
		enterText(stockLedgerFilterWarehouseField4, excelReader.getCellData(xlSheetName, 31, 5));
		Thread.sleep(1000);
		tab(stockLedgerFilterWarehouseField4);
		Thread.sleep(1000);
		click(stockLedgerFilterOkBtn);
		Thread.sleep(1000);
		click(sl_OkBtn);
		Thread.sleep(1000);
		click(stockLedgerLastBtn);
		Thread.sleep(2000);
		
		
		
		String actstockLedgerRmaItemSecBalanceQty   = getText(stockLedgerRmaItemSecBalanceQty);
		String expstockLedgerRmaItemSecBalanceQty   = excelReader.getCellData(xlSheetName, 36, 6);
		excelReader.setCellData(xlfile, xlSheetName, 36, 7, actstockLedgerRmaItemSecBalanceQty);

		String actstockLedgerRmaItemSecBalanceValue = getText(stockLedgerRmaItemSecBalanceValue);
		String expstockLedgerRmaItemSecBalanceValue = excelReader.getCellData(xlSheetName, 37, 6);
		excelReader.setCellData(xlfile, xlSheetName, 37, 7, actstockLedgerRmaItemSecBalanceValue);

		String actstockLedgerrmaItemSecAvgRate      = getText(stockLedgerrmaItemSecAvgRate);
		String expstockLedgerrmaItemSecAvgRate      = excelReader.getCellData(xlSheetName, 38, 6);
		excelReader.setCellData(xlfile, xlSheetName, 38, 7, actstockLedgerrmaItemSecAvgRate);
		
		System.out.println("=========actstockLedgerRmaItem  SEC WareHouse  Values============   : ");
		
		System.out.println("actstockLedgerRmaItemSecBalanceQty      :" + actstockLedgerRmaItemSecBalanceQty);
		System.out.println("expstockLedgerRmaItemSecBalanceQty      :" + expstockLedgerRmaItemSecBalanceQty);

		System.out.println("actstockLedgerRmaItemSecBalanceValue    :" + actstockLedgerRmaItemSecBalanceValue);
		System.out.println("expstockLedgerRmaItemSecBalanceValue    :" + expstockLedgerRmaItemSecBalanceValue);

		System.out.println("actstockLedgerrmaItemSecAvgRate         :" + actstockLedgerrmaItemSecAvgRate);
		System.out.println("expstockLedgerrmaItemSecAvgRate         :" + expstockLedgerrmaItemSecAvgRate);
	

		if (actstockLedgerRmaItemBalanceQty.equalsIgnoreCase(expstockLedgerRmaItemBalanceQty)
				&& actstockLedgerRmaItemBalanceValue.equalsIgnoreCase(expstockLedgerRmaItemBalanceValue)
				&& actstockLedgerrmaItemAvgRate.equalsIgnoreCase(expstockLedgerrmaItemAvgRate) && actstockLedgerRmaItemHydBalanceQty.equalsIgnoreCase(expstockLedgerRmaItemHydBalanceQty)
				&& actstockLedgerRmaItemHydBalanceValue.equalsIgnoreCase(expstockLedgerRmaItemHydBalanceValue) && actstockLedgerrmaItemHydAvgRate.equalsIgnoreCase(expstockLedgerrmaItemHydAvgRate)
				&& actstockLedgerRmaItemSecBalanceQty.equalsIgnoreCase(expstockLedgerRmaItemSecBalanceQty) && actstockLedgerRmaItemSecBalanceValue.equalsIgnoreCase(expstockLedgerRmaItemSecBalanceValue)
				&& actstockLedgerrmaItemSecAvgRate.equalsIgnoreCase(expstockLedgerrmaItemSecAvgRate)) {

			click(report_CloseBtn);
			Thread.sleep(2000);
			click(sl_CloseBtn);
			excelReader.setCellData(xlfile, xlSheetName, 29, 8, resPass);
			return true;
		} else {
			click(report_CloseBtn);
			Thread.sleep(2000);
			click(sl_CloseBtn);
			excelReader.setCellData(xlfile, xlSheetName, 29, 8, resFail);
			return false;
		}
	}
	
	
	
	public boolean checkSavePurchasesVoucher()
			throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException {
		
		
		excelReader=new ExcelReader(POJOUtility.getExcelPath());
		
		
		click(financialsMenu);
		click(financialsTransactionMenu);
		click(financialsTransactionsPurchaseMenu);
		click(PurchasesVoucher);
		click(newBtn);
		checkValidationMessage("Screen opened");
		click(purchaseDatetxt);
		purchaseDatetxt.sendKeys(Keys.CONTROL, "a");
		Thread.sleep(2000);
		purchaseDatetxt.sendKeys(Keys.BACK_SPACE);
		enterText(purchaseDatetxt, excelReader.getCellData(xlSheetName, 39, 5));
		tab(purchaseDatetxt);
		Thread.sleep(2000);
		enterText(purchasesAccounttxt, excelReader.getCellData(xlSheetName, 40, 5));
		tab(purchasesAccounttxt);
		Thread.sleep(2000);
		enterText(vendorAccounttxt, excelReader.getCellData(xlSheetName, 41, 5));
		Thread.sleep(2000);
		//click(purchasesDepartmenttxt);
		enterText(purchasesDepartmenttxt, excelReader.getCellData(xlSheetName, 42, 5));
		Thread.sleep(1000);
		tab(purchasesDepartmenttxt);
		Thread.sleep(1000);

		//ScrollToElement(select1stRow_1stColumn);

		click(select1stRow_1stColumn);
		
		enterText(enterWarehousettxt, excelReader.getCellData(xlSheetName, 43, 5));
		Thread.sleep(2000);
		tab(enterWarehousettxt);
		enterText(enterItemttxt, excelReader.getCellData(xlSheetName, 44, 5));
		Thread.sleep(2000);
		tab(enterItemttxt);
		enterText(enterQuantitytxt, excelReader.getCellData(xlSheetName, 45, 5));
		tab(enterQuantitytxt);
		enterText(enterRatetxt, excelReader.getCellData(xlSheetName, 46, 5));
		tab(enterRatetxt);
		Thread.sleep(2000);
		click(select1stRow_9thColumn);
		enterText(enterBatchtxt, excelReader.getCellData(xlSheetName, 47, 5));
		tab(enterBatchtxt);
		

		// 2nd Row

		click(select2ndRow_1stColumn);
		Thread.sleep(2000);
		enterWarehousettxt.sendKeys(Keys.CONTROL, "a");
		Thread.sleep(2000);
		enterWarehousettxt.sendKeys(Keys.BACK_SPACE);
		enterText(enterWarehousettxt, excelReader.getCellData(xlSheetName, 48, 5));
		Thread.sleep(2000);
		tab(enterWarehousettxt);
		enterText(enterItemttxt, excelReader.getCellData(xlSheetName, 49, 5));
		Thread.sleep(2000);
		tab(enterItemttxt);
		enterText(enterQuantitytxt, excelReader.getCellData(xlSheetName, 50, 5));
		tab(enterQuantitytxt);
		enterText(enterRatetxt, excelReader.getCellData(xlSheetName, 51, 5));
		tab(enterRatetxt);
		click(select2ndRow_9thColumn);
		enterText(enterBatchtxt, excelReader.getCellData(xlSheetName, 52, 5));
		tab(enterBatchtxt);
		

		// 3rd Row

		click(select3rdRow_1stColumn);
		enterWarehousettxt.sendKeys(Keys.CONTROL, "a");
		Thread.sleep(2000);
		enterWarehousettxt.sendKeys(Keys.BACK_SPACE);
		enterText(enterWarehousettxt, excelReader.getCellData(xlSheetName, 53, 5));
		Thread.sleep(2000);
		tab(enterWarehousettxt);
		enterText(enterItemttxt, excelReader.getCellData(xlSheetName, 54, 5));
		Thread.sleep(2000);
		tab(enterItemttxt);
		enterText(enterQuantitytxt, excelReader.getCellData(xlSheetName, 55, 5));
		tab(enterQuantitytxt);
		enterText(enterRatetxt, excelReader.getCellData(xlSheetName, 56, 5));
		tab(enterRatetxt);
		click(select3rdRow_10thColumn);
		
		String actual3rdAvgRate 		= select3rdRow_AvgRate.getText();
		String exp3rdAvgRate 			= excelReader.getCellData(xlSheetName, 39, 6);
		excelReader.setCellData(xlfile, xlSheetName, 39, 7, actual3rdAvgRate);
		
		String actual3rdOverallAvgRate 	= select3rdRow_OverAllAvgRate.getText();
		String exp3rdoverallAvgRate 	= excelReader.getCellData(xlSheetName, 40, 6);
		excelReader.setCellData(xlfile, xlSheetName, 40, 7, actual3rdOverallAvgRate);

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(binSearchTxt));

		String actBinpopupIsDisplaying = Boolean.toString(binSearchTxt.isDisplayed());
		String expBinpopupIsDisplaying = excelReader.getCellData(xlSheetName, 41, 6);
		excelReader.setCellData(xlfile, xlSheetName, 41, 7, actBinpopupIsDisplaying);

		System.out.println("Bin Popup isDisplaying  : " + actBinpopupIsDisplaying + "  Value Expected  "
				+ expBinpopupIsDisplaying);

		click(binSearchBtn);
		Thread.sleep(3000);
		click(binAutoAllocateBtn);
		Thread.sleep(2000);
		click(binOkBtn);
		Thread.sleep(2000);
		

		// 4th Row

		click(select4thRow_1stColumn);
		enterWarehousettxt.sendKeys(Keys.CONTROL, "a");
		Thread.sleep(2000);
		enterWarehousettxt.sendKeys(Keys.BACK_SPACE);
		enterText(enterWarehousettxt, excelReader.getCellData(xlSheetName, 57, 5));
		Thread.sleep(2000);
		tab(enterWarehousettxt);
		enterText(enterItemttxt, excelReader.getCellData(xlSheetName, 58, 5));
		Thread.sleep(2000);
		tab(enterItemttxt);
		enterText(enterQuantitytxt, excelReader.getCellData(xlSheetName, 59, 5));
		tab(enterQuantitytxt);
		enterText(enterRatetxt, excelReader.getCellData(xlSheetName, 60, 5));
		tab(enterRatetxt);
		click(select4thRow_10thColumn);
		
		String actual4throwAvgRate 			= select4thRow_AvgRate.getText();
		String exp4throwAvgRate		   	    = excelReader.getCellData(xlSheetName, 42, 6);
		excelReader.setCellData(xlfile, xlSheetName, 42, 7, actual4throwAvgRate);
		
		String actual4throwOverallAvgRate   = select4thRow_OverAllAvgRate.getText();
		String exp4throwoverallAvgRate      = excelReader.getCellData(xlSheetName, 43, 6);
		excelReader.setCellData(xlfile, xlSheetName, 43, 7, actual4throwOverallAvgRate);

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(binSearchTxt));

		String actBinpopupIsDisplaying1 = Boolean.toString(binSearchTxt.isDisplayed());
		String expBinpopupIsDisplaying1 = excelReader.getCellData(xlSheetName, 44, 6);
		excelReader.setCellData(xlfile, xlSheetName, 44, 7, actBinpopupIsDisplaying1);

		System.out.println("Bin Popup isDisplaying  : " + actBinpopupIsDisplaying1 + "  Value Expected  "
				+ expBinpopupIsDisplaying1);

		click(binSearchBtn);
		Thread.sleep(3000);
		click(binAutoAllocateBtn);
		Thread.sleep(2000);
		click(binOkBtn);
		Thread.sleep(2000);
		

		// 5th Row

		click(select5thRow_1stColumn);
		enterWarehousettxt.sendKeys(Keys.CONTROL, "a");
		Thread.sleep(2000);
		enterWarehousettxt.sendKeys(Keys.BACK_SPACE);
		enterText(enterWarehousettxt, excelReader.getCellData(xlSheetName, 61, 5));
		Thread.sleep(2000);
		tab(enterWarehousettxt);
		enterText(enterItemttxt, excelReader.getCellData(xlSheetName, 62, 5));
		Thread.sleep(2000);
		tab(enterItemttxt);
		enterText(enterQuantitytxt, excelReader.getCellData(xlSheetName, 63, 5));
		tab(enterQuantitytxt);
		enterText(enterRatetxt, excelReader.getCellData(xlSheetName, 64, 5));
		tab(enterRatetxt);
		click(select5thRow_11thColumn);
		Thread.sleep(2000);
		String actual5throwAvgRate 			= select5thRow_AvgRate.getText();
		String exp5throwAvgRate			    = excelReader.getCellData(xlSheetName, 45, 6);
		excelReader.setCellData(xlfile, xlSheetName, 45, 7, actual5throwAvgRate);
		
		String actual5throwOverallAvgRate   = select5thRow_OverAllAvgRate.getText();
		String exp5throwoverallAvgRate      = excelReader.getCellData(xlSheetName, 46, 6);
		excelReader.setCellData(xlfile, xlSheetName, 46, 7, actual5throwOverallAvgRate);

		
		//enterRmatxt.sendKeys(Keys.SPACE);
		Thread.sleep(1000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(rmaScreenTitle));

		String actRMAPopupIsDisplaying = Boolean.toString(rmaScreenTitle.isDisplayed());
		String expRMAPopupIsDisplaying = excelReader.getCellData(xlSheetName, 47, 6);
		excelReader.setCellData(xlfile, xlSheetName, 47, 7, actRMAPopupIsDisplaying);

		System.out.println("RMA Popup isDisplaying  : " + actRMAPopupIsDisplaying + "  Value Expected  "
				+ expRMAPopupIsDisplaying);
		Thread.sleep(1000);
		enterText(rmaSerialNumberTxtField, excelReader.getCellData(xlSheetName, 65, 5));
		click(rmaAddBtn);
		Thread.sleep(2000);
		click(RMAPopupOkBtn);
		Thread.sleep(3000);
		tab(enterRmatxt);
		tab(enterBatCodetxt);

		// 6th Row
		Thread.sleep(1000);
		click(select6thRow_1stColumn);
		enterWarehousettxt.sendKeys(Keys.CONTROL, "a");
		Thread.sleep(2000);
		enterWarehousettxt.sendKeys(Keys.BACK_SPACE);
		enterText(enterWarehousettxt, excelReader.getCellData(xlSheetName, 66, 5));
		Thread.sleep(2000);
		tab(enterWarehousettxt);
		enterText(enterItemttxt, excelReader.getCellData(xlSheetName, 67, 5));
		Thread.sleep(2000);
		tab(enterItemttxt);
		enterText(enterQuantitytxt, excelReader.getCellData(xlSheetName, 68, 5));
		tab(enterQuantitytxt);
		enterText(enterRatetxt, excelReader.getCellData(xlSheetName, 69, 5));
		tab(enterRatetxt);
		click(select6thRow_11thColumn);
		Thread.sleep(2000);
		
		String actual6throwAvgRate 				= select6thRow_AvgRate.getText();
		String exp6throwAvgRate 				= excelReader.getCellData(xlSheetName, 48, 6);
		excelReader.setCellData(xlfile, xlSheetName, 48, 7, actual6throwAvgRate);
		
		String actual6throwOverallAvgRate		= select6thRow_OverAllAvgRate.getText();
		String exp6throwoverallAvgRate			= excelReader.getCellData(xlSheetName, 49, 6);
		excelReader.setCellData(xlfile, xlSheetName, 49, 7, actual6throwOverallAvgRate);

		System.out.println("actual3rdAvgRate       		 :" + actual3rdAvgRate);
		System.out.println("exp3rdAvgRate           	 :" + exp3rdAvgRate);

		System.out.println("actual3rdOverallAvgRate      :" + actual3rdOverallAvgRate);
		System.out.println("exp3rdoverallAvgRate         :" + exp3rdoverallAvgRate);

		System.out.println("actual4thAvgRate        	 :" + actual4throwAvgRate);
		System.out.println("exp4throwAvgRate          	 :" + exp4throwAvgRate);

		System.out.println("actual4throwOverallAvgRate   :" + actual4throwOverallAvgRate);
		System.out.println("exp4throwoverallAvgRate      :" + exp4throwoverallAvgRate);

		System.out.println("actual5throwAvgRate       	 :" + actual5throwAvgRate);
		System.out.println("exp5throwAvgRate             :" + exp5throwAvgRate);

		System.out.println("actual5throwOverallAvgRate   :" + actual5throwOverallAvgRate);
		System.out.println("exp5throwoverallAvgRate      	:" + exp5throwoverallAvgRate);

		System.out.println("actual6throwAvgRate      	 :" + actual6throwAvgRate);
		System.out.println("exp6throwAvgRate          	 :" + exp6throwAvgRate);

		System.out.println("actual6throwOverallAvgRate   :" + actual6throwOverallAvgRate);
		System.out.println("exp6throwoverallAvgRate      :" + exp6throwoverallAvgRate);

		
		//enterRmatxt.sendKeys(Keys.SPACE);
		Thread.sleep(1000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(rmaScreenTitle));

		String actRMAPopupIsDisplaying1 = Boolean.toString(rmaScreenTitle.isDisplayed());
		String expRMAPopupIsDisplaying1 = excelReader.getCellData(xlSheetName, 50, 6);
		excelReader.setCellData(xlfile, xlSheetName, 50, 7, actRMAPopupIsDisplaying1);

		System.out.println("RMA Popup isDisplaying  : " + actRMAPopupIsDisplaying1 + "  Value Expected  "
				+ expRMAPopupIsDisplaying1);
		enterText(rmaSerialNumberTxtField, excelReader.getCellData(xlSheetName, 70, 5));
		click(rmaAddBtn);
		Thread.sleep(2000);
		click(RMAPopupOkBtn);
		Thread.sleep(2000);		
		tab(enterRmatxt);
		
		//return true;
		
		Thread.sleep(2000);
		
		String docno=documentNumberTxt.getAttribute("value");
		click(voucherSaveBtn);
		
		Thread.sleep(2000);

	/*	HashSet<String> actMessage = new HashSet<String>();

		for (int i = 0; i < 2; i++) {
			String data = checkValidationMessage("");
			actMessage.add(data);
		}

		HashSet<String> expMessage = new HashSet<String>();

		expMessage.add("Voucher saved successfully : 35");
		expMessage.add("Saving in background."); */
		/*
		String actMessage = SavingInBackgroundTrial();

		HashSet<String> expMsg = new HashSet();

		expMsg.add(excelReader.getCellData(xlSheetName, 51, 6));
		expMsg.add(excelReader.getCellData(xlSheetName, 52, 6));
		excelReader.setCellData(xlfile, xlSheetName, 51, 7, actMessage);
		
		String expMessage = expMsg.toString();



		System.out.println("Actual Message    : " + actMessage);
		System.out.println("Expected Message  : " + expMessage);*/

		/*boolean actSaving=SavingInBackground(docno);
		boolean expSaving=true;*/
		
		String expMessage1= excelReader.getCellData(xlSheetName, 51, 6);
		String expMessage2= excelReader.getCellData(xlSheetName, 52, 6);
		
		String actMessage = checkValidationMessage(expMessage1);
		excelReader.setCellData(xlfile, xlSheetName, 51, 7, actMessage);
		
	   
	   
	   System.out.println("SavingMessage  :  "+actMessage +" Value Expected : "+expMessage1+" "+expMessage2);
		
		if (actMessage.startsWith(expMessage1) && actMessage.endsWith(expMessage2) && actual3rdAvgRate.equalsIgnoreCase(exp3rdAvgRate)
				&& actual3rdOverallAvgRate.equalsIgnoreCase(exp3rdoverallAvgRate)
				&& actual4throwAvgRate.equalsIgnoreCase(exp4throwAvgRate)
				&& actual4throwOverallAvgRate.equalsIgnoreCase(exp4throwoverallAvgRate)
				&& actual5throwAvgRate.equalsIgnoreCase(exp5throwAvgRate)
				&& actual5throwOverallAvgRate.equalsIgnoreCase(exp5throwoverallAvgRate)
				&& actual6throwAvgRate.equalsIgnoreCase(exp6throwAvgRate)
				&& actual6throwOverallAvgRate.equalsIgnoreCase(exp6throwoverallAvgRate)) {
			
			click(new_CloseBtn);			
			Thread.sleep(1000);
			click(voucherhomeCloseBtn);
			excelReader.setCellData(xlfile, xlSheetName, 39, 8, resPass);
			return true;
			
		} else {
			click(new_CloseBtn);			
			Thread.sleep(1000);
			click(voucherhomeCloseBtn);
			excelReader.setCellData(xlfile, xlSheetName, 39, 8, resFail);
			return false;

		} 

	}

	public boolean validteItemQueryStocksInBatchItemAfterSavingPurchaseVocher() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {

		excelReader=new ExcelReader(POJOUtility.getExcelPath());
		
		click(inventoryMenu);
		click(InventoryReportsMenu);
		click(itemqueryMenu);
		click(itemTxt);
		Thread.sleep(2000);
		enterText(itemTxt, excelReader.getCellData(xlSheetName, 71, 5));
		Thread.sleep(3000);
		click(getStockBtn);
		Thread.sleep(3000);

		String actlOpenStockQuantity = getText(openStockQuantityTxt);
		String expOpenStockQuantity  = excelReader.getCellData(xlSheetName, 71, 6);
		excelReader.setCellData(xlfile, xlSheetName, 71, 7, actlOpenStockQuantity);

		String actlCurrentStock      = getText(currentStockTxt);
		String expCurrentStock       = excelReader.getCellData(xlSheetName, 72, 6);
		excelReader.setCellData(xlfile, xlSheetName, 72, 7, actlCurrentStock);

		String actlavgStockRate      = getText(avgStockRateTxt);
		String expavgStockRate       = excelReader.getCellData(xlSheetName, 73, 6);
		excelReader.setCellData(xlfile, xlSheetName, 73, 7, actlavgStockRate);

		String actlstockValueTxt     = getText(stockValueTxt);
		String expstockValueTxt      = excelReader.getCellData(xlSheetName, 74, 6);
		excelReader.setCellData(xlfile, xlSheetName, 74, 7, actlstockValueTxt);

		System.out.println("=====Stocks In Batch Items======");

		System.out.println("actlOpenStockQuantity      :" + actlOpenStockQuantity);
		System.out.println("expOpenStockQuantity       :" + expOpenStockQuantity);

		System.out.println("actlCurrentStock           :" + actlCurrentStock);
		System.out.println("expCurrentStock            :" + expCurrentStock);

		System.out.println("actlavgStockRate           :" + actlavgStockRate);
		System.out.println("expavgStockRate            :" + expavgStockRate);

		System.out.println("actlstockValueTxt          :" + actlstockValueTxt);
		System.out.println("expstockValueTxt           :" + expstockValueTxt);

		if (actlOpenStockQuantity.equalsIgnoreCase(expOpenStockQuantity)
				&& actlCurrentStock.equalsIgnoreCase(expCurrentStock)
				&& actlavgStockRate.equalsIgnoreCase(expavgStockRate)
				&& actlstockValueTxt.equalsIgnoreCase(expstockValueTxt)) {
			
			excelReader.setCellData(xlfile, xlSheetName, 71, 8, resPass);
			return true;
		} else {
			
			excelReader.setCellData(xlfile, xlSheetName, 71, 8, resFail);
			return false;
		}

	}

	public boolean validteItemQueryStocksInFifoItemAfterSavingPurchaseVocher() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {

		excelReader=new ExcelReader(POJOUtility.getExcelPath());
		
		click(itemTxt);
		Thread.sleep(2000);
		itemTxt.sendKeys(Keys.CONTROL, "a");
		Thread.sleep(2000);
		itemTxt.sendKeys(Keys.BACK_SPACE);
		enterText(itemTxt, excelReader.getCellData(xlSheetName, 75, 5));
		Thread.sleep(3000);
		click(getStockBtn);
		Thread.sleep(3000);

		String actlOpenStockQuantity = getText(openStockQuantityTxt);
		String expOpenStockQuantity  = excelReader.getCellData(xlSheetName, 75, 6);
		excelReader.setCellData(xlfile, xlSheetName, 75, 7, actlOpenStockQuantity);

		String actlCurrentStock      = getText(currentStockTxt);
		String expCurrentStock       = excelReader.getCellData(xlSheetName, 76, 6);
		excelReader.setCellData(xlfile, xlSheetName, 76, 7, actlCurrentStock);

		String actlavgStockRate      = getText(avgStockRateTxt);
		String expavgStockRate       = excelReader.getCellData(xlSheetName, 77, 6);
		excelReader.setCellData(xlfile, xlSheetName, 77, 7, actlavgStockRate);

		String actlstockValueTxt     = getText(stockValueTxt);
		String expstockValueTxt      = excelReader.getCellData(xlSheetName, 78, 6);
		excelReader.setCellData(xlfile, xlSheetName, 78, 7, actlstockValueTxt);

		System.out.println("=====Stocks In FIFO Items======");

		System.out.println("actlOpenStockQuantity :" + actlOpenStockQuantity);
		System.out.println("expOpenStockQuantity  :" + expOpenStockQuantity);

		System.out.println("actlCurrentStock      :" + actlCurrentStock);
		System.out.println("expCurrentStock       :" + expCurrentStock);

		System.out.println("actlavgStockRate      :" + actlavgStockRate);
		System.out.println("expavgStockRate       :" + expavgStockRate);

		System.out.println("actlstockValueTxt     :" + actlstockValueTxt);
		System.out.println("expstockValueTxt      :" + expstockValueTxt);

		if (actlOpenStockQuantity.equalsIgnoreCase(expOpenStockQuantity)
				&& actlCurrentStock.equalsIgnoreCase(expCurrentStock)
				&& actlavgStockRate.equalsIgnoreCase(expavgStockRate)
				&& actlstockValueTxt.equalsIgnoreCase(expstockValueTxt)) {
			
			excelReader.setCellData(xlfile, xlSheetName, 75, 8, resPass);
			return true;
		} else {
			
			excelReader.setCellData(xlfile, xlSheetName, 75, 8, resFail);
			return false;
		}

	}

	public boolean validteItemQueryStocksInWAItemAfterSavingPurchaseVocher() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {

		
		excelReader=new ExcelReader(POJOUtility.getExcelPath());
		
		click(itemTxt);
		Thread.sleep(2000);
		itemTxt.sendKeys(Keys.CONTROL, "a");
		Thread.sleep(2000);
		itemTxt.sendKeys(Keys.BACK_SPACE);
		enterText(itemTxt, excelReader.getCellData(xlSheetName, 79, 5));
		Thread.sleep(3000);
		click(getStockBtn);
		Thread.sleep(3000);

		String actlOpenStockQuantity = getText(openStockQuantityTxt);
		String expOpenStockQuantity  = excelReader.getCellData(xlSheetName, 79, 6);
		excelReader.setCellData(xlfile, xlSheetName, 79, 7, actlOpenStockQuantity);

		String actlCurrentStock      = getText(currentStockTxt);
		String expCurrentStock       = excelReader.getCellData(xlSheetName, 80, 6);
		excelReader.setCellData(xlfile, xlSheetName, 80, 7, actlCurrentStock);

		String actlavgStockRate      = getText(avgStockRateTxt);
		String expavgStockRate       = excelReader.getCellData(xlSheetName, 81, 6);
		excelReader.setCellData(xlfile, xlSheetName, 81, 7, actlavgStockRate);

		String actlstockValueTxt     = getText(stockValueTxt);
		String expstockValueTxt      = excelReader.getCellData(xlSheetName, 82, 6);
		excelReader.setCellData(xlfile, xlSheetName, 82, 7, actlstockValueTxt);

		ScrollToElement(balanceByWarehouseBtn);
		click(balanceByWarehouseBtn);

		Thread.sleep(3000);

		int count = row1BalanceByWareHouseList.size();

		ArrayList<String> warehouseRow1 = new ArrayList<String>();

		for (int i = 0; i < count; i++) {
			String data = row1BalanceByWareHouseList.get(i).getText();
			warehouseRow1.add(data);
		}

		String actwarehouseRow1List = warehouseRow1.toString();
		String expWarehouseRow1List = excelReader.getCellData(xlSheetName, 83, 6);
		excelReader.setCellData(xlfile, xlSheetName, 83, 7, actwarehouseRow1List);

		int count1 = row2BalanceByWareHouseList.size();

		ArrayList<String> warehouseRow2 = new ArrayList<String>();

		for (int i = 0; i < count1; i++) {
			String data = row2BalanceByWareHouseList.get(i).getText();
			warehouseRow2.add(data);
		}

		String actwarehouseRow2List = warehouseRow2.toString();
		String expWarehouseRow2List = excelReader.getCellData(xlSheetName, 84, 6);
		excelReader.setCellData(xlfile, xlSheetName, 84, 7, actwarehouseRow2List);

		int count2 = row3BalanceByWareHouseList.size();

		ArrayList<String> warehouseRow3 = new ArrayList<String>();

		for (int i = 0; i < count2; i++) {
			String data = row3BalanceByWareHouseList.get(i).getText();
			warehouseRow3.add(data);
		}

		String actwarehouseRow3List = warehouseRow3.toString();
		String expWarehouseRow3List = excelReader.getCellData(xlSheetName, 85, 6);
		excelReader.setCellData(xlfile, xlSheetName, 85, 7, actwarehouseRow3List);

		System.out.println("=====Stocks In WA Items======");

		System.out.println("actlOpenStockQuantity :" + actlOpenStockQuantity);
		System.out.println("expOpenStockQuantity  :" + expOpenStockQuantity);

		System.out.println("actlCurrentStock      :" + actlCurrentStock);
		System.out.println("expCurrentStock       :" + expCurrentStock);

		System.out.println("actlavgStockRate      :" + actlavgStockRate);
		System.out.println("expavgStockRate       :" + expavgStockRate);

		System.out.println("actlstockValueTxt     :" + actlstockValueTxt);
		System.out.println("expstockValueTxt      :" + expstockValueTxt);

		System.out.println("===========Balance By Warehouse=======");

		System.out.println(actwarehouseRow1List);
		System.out.println(expWarehouseRow1List);

		System.out.println(actwarehouseRow2List);
		System.out.println(expWarehouseRow2List);

		System.out.println(actwarehouseRow3List);
		System.out.println(expWarehouseRow3List);

		if (actlOpenStockQuantity.equalsIgnoreCase(expOpenStockQuantity)
				&& actlCurrentStock.equalsIgnoreCase(expCurrentStock)
				&& actlavgStockRate.equalsIgnoreCase(expavgStockRate)
				&& actlstockValueTxt.equalsIgnoreCase(expstockValueTxt)
				&& actwarehouseRow1List.equalsIgnoreCase(expWarehouseRow1List)
				&& actwarehouseRow2List.equalsIgnoreCase(expWarehouseRow2List)
				&& actwarehouseRow3List.equalsIgnoreCase(expWarehouseRow3List)) {
			
			excelReader.setCellData(xlfile, xlSheetName, 79, 8, resPass);
			return true;
		} else {
			
			excelReader.setCellData(xlfile, xlSheetName, 79, 8, resFail);
			return false;
		}

	}  

	public boolean checkStockLedgerReportFortBrBatchItemAfterSavingPurchaseVocher()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {

		excelReader=new ExcelReader(POJOUtility.getExcelPath());
		
		
		click(inventoryMenu);
		click(InventoryReportsMenu);
		click(InventoryStockLedgerMenu);
		Thread.sleep(3000);

		int rowcount = stockLedgerHometableRowCount.size();

		System.out.println(rowcount);

		for (int i = 0; i < rowcount; i++) {
			String actName = ledgerHometableItemNamesList.get(i).getText();

			System.out.println(actName);

			if (actName.equalsIgnoreCase(excelReader.getCellData(xlSheetName, 86, 5))) {
				stockLedgerHometableItemChkboxList.get(i).click();
			}
		}
		
		dropDown(sl_DateOptionDropdown, excelReader.getCellData(xlSheetName, 87, 5));		
		Thread.sleep(2000);
		click(sl_OkBtn);
		Thread.sleep(2000);
		click(stockLedgerLastBtn);
		//Thread.sleep(2000);
		
		//click(stockLedgerPreviousBtn);
		Thread.sleep(3000);

		String actStockLedgerBrBatchBalanceQty   = getText(stockLedgerBrBatchAfterSavingpurchaseBalanceQty);
		String expStockLedgerBrBatchBalanceQty   = excelReader.getCellData(xlSheetName, 86, 6);
		excelReader.setCellData(xlfile, xlSheetName, 86, 7, actStockLedgerBrBatchBalanceQty);

		String actStockLedgerBrBatchBalanceValue = getText(stockLedgerBrBatchBalanceValueAfterSavingpurchase);
		String expStockLedgerBrBatchBalanceValue = excelReader.getCellData(xlSheetName, 87, 6);
		excelReader.setCellData(xlfile, xlSheetName, 87, 7, actStockLedgerBrBatchBalanceValue);

		String actStockLedgerBrBatchAvgRate      = getText(stockLedgerBrBatchAvgRateAfterSavingpurchase);
		String expStockLedgerBrBatchAvgRate      = excelReader.getCellData(xlSheetName, 88, 6);
		excelReader.setCellData(xlfile, xlSheetName, 88, 7, actStockLedgerBrBatchAvgRate);
		

		System.out.println("actStockLedgerBrBatchBalanceQty        :" + actStockLedgerBrBatchBalanceQty);
		System.out.println("expStockLedgerBrBatchBalanceQty        :" + expStockLedgerBrBatchBalanceQty);

		System.out.println("actStockLedgerBrBatchBalanceValue      :" + actStockLedgerBrBatchBalanceValue);
		System.out.println("expStockLedgerBrBatchBalanceValue      :" + expStockLedgerBrBatchBalanceValue);

		System.out.println("actStockLedgerBrBatchAvgRate      	   :" + actStockLedgerBrBatchAvgRate);
		System.out.println("expStockLedgerBrBatchAvgRate    	   :" + expStockLedgerBrBatchAvgRate);

		if (actStockLedgerBrBatchBalanceQty.equalsIgnoreCase(expStockLedgerBrBatchBalanceQty)
				&& actStockLedgerBrBatchBalanceValue.equalsIgnoreCase(expStockLedgerBrBatchBalanceValue)
				&& actStockLedgerBrBatchAvgRate.equalsIgnoreCase(expStockLedgerBrBatchAvgRate)) {

			click(report_CloseBtn);
			excelReader.setCellData(xlfile, xlSheetName, 86, 8, resPass);

			return true;
		} else {
			click(report_CloseBtn);
			excelReader.setCellData(xlfile, xlSheetName, 86, 8, resFail);

			return false;
		}
	}

	public boolean checkStockLedgerReportForFifoItemAfterSavingPurchaseVocher()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		
		excelReader=new ExcelReader(POJOUtility.getExcelPath());

		Thread.sleep(3000);

		if (fifoItemCheckBox.isSelected() == false) {
			click(fifoItemCheckBox);
		}

		if (brBatchItemCheckBox.isSelected() == true) {
			click(brBatchItemCheckBox);
		}

		if (rmaItemCheckBox.isSelected() == true) {
			click(rmaItemCheckBox);

		}

		
		dropDown(sl_DateOptionDropdown, excelReader.getCellData(xlSheetName, 89, 5));	
		Thread.sleep(2000);
		click(sl_OkBtn);
		Thread.sleep(2000);
		click(stockLedgerLastBtn);
		Thread.sleep(2000);
		click(stockLedgerPreviousBtn);
		Thread.sleep(2000);

		String actstockLedgerFifoItemBalanceQty 	= getText(stockLedgerFifoItemBalanceQtyAfterSavingPurchase);
		String expstockLedgerFifoItemBalanceQty 	= excelReader.getCellData(xlSheetName, 89, 6);
		excelReader.setCellData(xlfile, xlSheetName, 89, 7, actstockLedgerFifoItemBalanceQty);

		String actstockLedgerFifoItemBalanceValue 	= getText(stockLedgerFifoItemBalanceValueAfterSavingPurchase);
		String expstockLedgerFifoItemBalanceValue   = excelReader.getCellData(xlSheetName, 90, 6);
		excelReader.setCellData(xlfile, xlSheetName, 90, 7, actstockLedgerFifoItemBalanceValue);

		String actstockLedgerFifoItemAvgRate		= getText(stockLedgerFifoItemAvgRateAfterSavingPurchase);
		String expstockLedgerFifoItemAvgRate		= excelReader.getCellData(xlSheetName, 91, 6);
		excelReader.setCellData(xlfile, xlSheetName, 91, 7, actstockLedgerFifoItemAvgRate);

		System.out.println("actstockLedgerFifoItemBalanceQty      	:" + actstockLedgerFifoItemBalanceQty);
		System.out.println("expstockLedgerFifoItemBalanceQty      	:" + expstockLedgerFifoItemBalanceQty);

		System.out.println("actstockLedgerFifoItemBalanceValue      :" + actstockLedgerFifoItemBalanceValue);
		System.out.println("expstockLedgerFifoItemBalanceValue      :" + expstockLedgerFifoItemBalanceValue);

		System.out.println("actstockLedgerFifoItemAvgRate     		:" + actstockLedgerFifoItemAvgRate);
		System.out.println("expstockLedgerFifoItemAvgRate      		:" + expstockLedgerFifoItemAvgRate);

		if (actstockLedgerFifoItemBalanceQty.equalsIgnoreCase(expstockLedgerFifoItemBalanceQty)
				&& actstockLedgerFifoItemBalanceValue.equalsIgnoreCase(expstockLedgerFifoItemBalanceValue)
				&& actstockLedgerFifoItemAvgRate.equalsIgnoreCase(expstockLedgerFifoItemAvgRate)) {

			click(report_CloseBtn);
			
			excelReader.setCellData(xlfile, xlSheetName, 89, 8, resPass);

			return true;
		} else {
			click(report_CloseBtn);
			
			excelReader.setCellData(xlfile, xlSheetName, 89, 8, resFail);

			return false;
		}
	}
	
	

	public boolean checkStockLedgerReportForRmaItemAfterSavingPurchaseVocher()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {

		excelReader=new ExcelReader(POJOUtility.getExcelPath());
		
		Thread.sleep(3000);

		if (fifoItemCheckBox.isSelected() == true) {
			click(fifoItemCheckBox);

		}

		if (brBatchItemCheckBox.isSelected() == true) {
			click(brBatchItemCheckBox);
		}

		if (rmaItemCheckBox.isSelected() == false) {
			click(rmaItemCheckBox);

		}

	
		dropDown(sl_DateOptionDropdown, excelReader.getCellData(xlSheetName, 92, 5));
		Thread.sleep(2000);
		click(sl_OkBtn);
		Thread.sleep(2000);
		click(stockLedgerLastBtn);
		Thread.sleep(3000);

		String actstockLedgerRmaItemBalanceQty 		= getText(stockLedgerRmaItemBalanceQtyAftersavingPurchase);
		String expstockLedgerRmaItemBalanceQty 		= excelReader.getCellData(xlSheetName, 92, 6);
		excelReader.setCellData(xlfile, xlSheetName, 92, 7, actstockLedgerRmaItemBalanceQty);

		String actstockLedgerRmaItemBalanceValue 	= getText(stockLedgerRmaItemBalanceValueAfterSavingPurchase);
		String expstockLedgerRmaItemBalanceValue 	= excelReader.getCellData(xlSheetName, 93, 6);
		excelReader.setCellData(xlfile, xlSheetName, 93, 7, actstockLedgerRmaItemBalanceValue);

		String actstockLedgerrmaItemAvgRate 		= getText(stockLedgerrmaItemAvgRateAfterSavingPurchase);
		String expstockLedgerrmaItemAvgRate 		= excelReader.getCellData(xlSheetName, 94, 6);
		excelReader.setCellData(xlfile, xlSheetName, 94, 7, actstockLedgerrmaItemAvgRate);

		System.out.println("actstockLedgerRmaItemBalanceQty      	:" + actstockLedgerRmaItemBalanceQty);
		System.out.println("expstockLedgerRmaItemBalanceQty      	:" + expstockLedgerRmaItemBalanceQty);

		System.out.println("actstockLedgerRmaItemBalanceValue       :" + actstockLedgerRmaItemBalanceValue);
		System.out.println("expstockLedgerRmaItemBalanceValue       :" + expstockLedgerRmaItemBalanceValue);

		System.out.println("actstockLedgerrmaItemAvgRate      		:" + actstockLedgerrmaItemAvgRate);
		System.out.println("expstockLedgerrmaItemAvgRate      		:" + expstockLedgerrmaItemAvgRate);
		
		click(stockLedgerFilterBtn);
		
		Thread.sleep(2000);
		
		click(stockLedgerFilterField);
		
		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(stockLedgerFilterText));

		String actFilterpopupIsDisplaying = Boolean.toString(stockLedgerFilterText.isDisplayed());
		String expFilterpopupIsDisplaying = excelReader.getCellData(xlSheetName, 95, 6);
		excelReader.setCellData(xlfile, xlSheetName, 95, 7, actFilterpopupIsDisplaying);

		System.out.println("Filter Popup isDisplaying  : " + actFilterpopupIsDisplaying + "  Value Expected  "
				+ expFilterpopupIsDisplaying);
		
		click(stockLedgerFilterWarehouseField);
		enterText(stockLedgerFilterWarehouseField, excelReader.getCellData(xlSheetName, 93, 5));
		Thread.sleep(2000);
		click(stockLedgerFilterOkBtn);
		Thread.sleep(1000);
		click(stockLedgerLastBtn);
		Thread.sleep(2000);
		
		String actstockLedgerRmaItemHydBalanceQtyAfterSavingPurchaseVocher 		= getText(stockLedgerRmaItemHydBalanceQtyAfterSavingPurchaseVocher);
		String expstockLedgerRmaItemHydBalanceQtyAfterSavingPurchaseVocher 		= excelReader.getCellData(xlSheetName, 96, 6);
		excelReader.setCellData(xlfile, xlSheetName, 96, 7, actstockLedgerRmaItemHydBalanceQtyAfterSavingPurchaseVocher);

		String actstockLedgerRmaItemHydBalanceValueAfterSavingPurchaseVocher 	= getText(stockLedgerRmaItemHydBalanceValueAfterSavingPurchaseVocher);
		String expstockLedgerRmaItemHydBalanceValueAfterSavingPurchaseVocher 	= excelReader.getCellData(xlSheetName, 97, 6);
		excelReader.setCellData(xlfile, xlSheetName, 97, 7, actstockLedgerRmaItemHydBalanceValueAfterSavingPurchaseVocher);

		String actstockLedgerrmaItemHydAvgRateAfterSavingPurchaseVocher 		= getText(stockLedgerrmaItemHydAvgRateAfterSavingPurchaseVocher);
		String expstockLedgerrmaItemHydAvgRateAfterSavingPurchaseVocher 		= excelReader.getCellData(xlSheetName, 98, 6);
		excelReader.setCellData(xlfile, xlSheetName, 98, 7, actstockLedgerrmaItemHydAvgRateAfterSavingPurchaseVocher);
		
		System.out.println("=========actstockLedgerRmaItem  HydWareHouse  Values============   : ");
		
		System.out.println("actstockLedgerRmaItemHydBalanceQtyAfterSavingPurchaseVocher      :" + actstockLedgerRmaItemHydBalanceQtyAfterSavingPurchaseVocher);
		System.out.println("expstockLedgerRmaItemHydBalanceQtyAfterSavingPurchaseVocher      :" + expstockLedgerRmaItemHydBalanceQtyAfterSavingPurchaseVocher);
		System.out.println("actstockLedgerRmaItemHydBalanceValueAfterSavingPurchaseVocher    :" + actstockLedgerRmaItemHydBalanceValueAfterSavingPurchaseVocher);
		System.out.println("expstockLedgerRmaItemHydBalanceValueAfterSavingPurchaseVocher    :" + expstockLedgerRmaItemHydBalanceValueAfterSavingPurchaseVocher);

		System.out.println("actstockLedgerrmaItemHydAvgRateAfterSavingPurchaseVocher         :" + actstockLedgerrmaItemHydAvgRateAfterSavingPurchaseVocher);
		System.out.println("expstockLedgerrmaItemHydAvgRateAfterSavingPurchaseVocher         :" + expstockLedgerrmaItemHydAvgRateAfterSavingPurchaseVocher);		
		
		click(report_CloseBtn);
		Thread.sleep(1000);
		click(stockLedgerHomePageFilterBtn);
		Thread.sleep(2000);
		click(stockLedgerFilterWarehouseField2);
		Thread.sleep(1000);
		click(stockLedgerFilterWarehouseField3);
		Thread.sleep(1500);
		stockLedgerFilterWarehouseField4.sendKeys(Keys.CONTROL, "a");
		Thread.sleep(1000);
		stockLedgerFilterWarehouseField4.sendKeys(Keys.BACK_SPACE);
		Thread.sleep(1000);
		enterText(stockLedgerFilterWarehouseField4, excelReader.getCellData(xlSheetName, 94, 5));
		Thread.sleep(1000);
		tab(stockLedgerFilterWarehouseField4);
		Thread.sleep(1500);
		click(stockLedgerFilterOkBtn);
		Thread.sleep(1000);
		click(sl_OkBtn);
		Thread.sleep(1000);
		click(stockLedgerLastBtn);
		Thread.sleep(2000);
		
		
		
		String actstockLedgerRmaItemSecBalanceQtyAfterSavingPurchaseVocher 		= getText(stockLedgerRmaItemSecBalanceQtyAfterSavingPurchaseVocher);
		String expstockLedgerRmaItemSecBalanceQtyAfterSavingPurchaseVocher 		= excelReader.getCellData(xlSheetName, 99, 6);
		excelReader.setCellData(xlfile, xlSheetName, 99, 7, actstockLedgerRmaItemSecBalanceQtyAfterSavingPurchaseVocher);

		String actstockLedgerRmaItemSecBalanceValueAfterSavingPurchaseVocher 	= getText(stockLedgerRmaItemSecBalanceValueAfterSavingPurchaseVocher);
		String expstockLedgerRmaItemSecBalanceValueAfterSavingPurchaseVocher 	= excelReader.getCellData(xlSheetName, 100, 6);
		excelReader.setCellData(xlfile, xlSheetName, 100, 7, actstockLedgerRmaItemSecBalanceValueAfterSavingPurchaseVocher);

		String actstockLedgerrmaItemSecAvgRateAfterSavingPurchaseVocher 		= getText(stockLedgerrmaItemSecAvgRateAfterSavingPurchaseVocher);
		String expstockLedgerrmaItemSecAvgRateAfterSavingPurchaseVocher 		= excelReader.getCellData(xlSheetName, 101, 6);
		excelReader.setCellData(xlfile, xlSheetName, 101, 7, actstockLedgerrmaItemSecAvgRateAfterSavingPurchaseVocher);
		
		System.out.println("=========actstockLedgerRmaItem  SEC WareHouse  Values============   : ");
		
		System.out.println("actstockLedgerRmaItemSecBalanceQtyAfterSavingPurchaseVocher      :" + actstockLedgerRmaItemSecBalanceQtyAfterSavingPurchaseVocher);
		System.out.println("expstockLedgerRmaItemSecBalanceQtyAfterSavingPurchaseVocher      :" + expstockLedgerRmaItemSecBalanceQtyAfterSavingPurchaseVocher);

		System.out.println("actstockLedgerRmaItemSecBalanceValueAfterSavingPurchaseVocher    :" + actstockLedgerRmaItemSecBalanceValueAfterSavingPurchaseVocher);
		System.out.println("expstockLedgerRmaItemSecBalanceValueAfterSavingPurchaseVocher    :" + expstockLedgerRmaItemSecBalanceValueAfterSavingPurchaseVocher);

		System.out.println("actstockLedgerrmaItemSecAvgRateAfterSavingPurchaseVocher         :" + actstockLedgerrmaItemSecAvgRateAfterSavingPurchaseVocher);
		System.out.println("expstockLedgerrmaItemSecAvgRateAfterSavingPurchaseVocher         :" + expstockLedgerrmaItemSecAvgRateAfterSavingPurchaseVocher);
	

		
		

		if (actstockLedgerRmaItemBalanceQty.equalsIgnoreCase(expstockLedgerRmaItemBalanceQty)
				&& actstockLedgerRmaItemBalanceValue.equalsIgnoreCase(expstockLedgerRmaItemBalanceValue)
				&& actstockLedgerrmaItemAvgRate.equalsIgnoreCase(expstockLedgerrmaItemAvgRate)
				&& actstockLedgerRmaItemHydBalanceQtyAfterSavingPurchaseVocher.equalsIgnoreCase(expstockLedgerRmaItemHydBalanceQtyAfterSavingPurchaseVocher)
				&& actstockLedgerRmaItemHydBalanceValueAfterSavingPurchaseVocher.equalsIgnoreCase(expstockLedgerRmaItemHydBalanceValueAfterSavingPurchaseVocher)
				&& actstockLedgerrmaItemHydAvgRateAfterSavingPurchaseVocher.equalsIgnoreCase(expstockLedgerrmaItemHydAvgRateAfterSavingPurchaseVocher)
				&& actstockLedgerRmaItemSecBalanceQtyAfterSavingPurchaseVocher.equalsIgnoreCase(expstockLedgerRmaItemSecBalanceQtyAfterSavingPurchaseVocher)
				&& actstockLedgerRmaItemSecBalanceValueAfterSavingPurchaseVocher.equalsIgnoreCase(expstockLedgerRmaItemSecBalanceValueAfterSavingPurchaseVocher)
				&& actstockLedgerrmaItemSecAvgRateAfterSavingPurchaseVocher.equalsIgnoreCase(expstockLedgerrmaItemSecAvgRateAfterSavingPurchaseVocher)) {

			click(report_CloseBtn);
			Thread.sleep(2000);
			click(sl_CloseBtn);
			excelReader.setCellData(xlfile, xlSheetName, 92, 8, resPass);
			return true;
		} else {
			click(report_CloseBtn);
			Thread.sleep(2000);
			click(sl_CloseBtn);
			excelReader.setCellData(xlfile, xlSheetName, 92, 8, resFail);
			return false;
		}
	}
	
	

	
	public boolean checkLoginToInvUserAndValidteItemQueryStocksInBatchItem() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException
	{
		excelReader=new ExcelReader(POJOUtility.getExcelPath());
		
		/*Thread.sleep(3000);
		click(userNameDisplayLogo);
		Thread.sleep(1000);
		click(logoutOption);		
		Thread.sleep(3000);
		
		String unamelt=excelReader.getCellData(xlSheetName, 102, 5);

		String pawslt=excelReader.getCellData(xlSheetName, 103, 5);
		
		LoginPage.enterUserName(unamelt);	     
        LoginPage.enterPassword(pawslt);
        
		
		 Select company = new Select(companyDropDownList);
		 company.selectByValue("108");
		 Thread.sleep(2000);
		 
  
        LoginPage.clickOnSignInBtn();  
        
        Thread.sleep(3000);
        
        System.out.println("Signing");*/
		
		reLogin("inv", "su");
        
        // Login User Name is Verify
        
        getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(userNameDisplay));
		String actUserInfo                      = userNameDisplay.getText();
		String expUserInfo                      = excelReader.getCellData(xlSheetName, 102, 6);	
		excelReader.setCellData(xlfile, xlSheetName, 102, 7, actUserInfo);

		System.out.println("Login With Valid User and Check User Name Display Value Actual : ." + actUserInfo + ". Value Expected : " + expUserInfo);
		
		
		// Login Company Name is Verify
		Thread.sleep(1000);
		click(companyLogo);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(companyName));
		String actGetLoginCompanyNameInformation = companyName.getText();
		String actGetLoginCompanyName            = actGetLoginCompanyNameInformation.substring(0, 19);
		String expGetLoginCompanyName            = excelReader.getCellData(xlSheetName, 103, 6); 
		excelReader.setCellData(xlfile, xlSheetName, 103, 7, actGetLoginCompanyName);
		companyLogo.click();
		
		System.out.println("Company Name Display Value Actual                : ." + actGetLoginCompanyName +  ". Value Expected : " + expGetLoginCompanyName);
		
		
		Thread.sleep(2000);		
		click(inventoryMenu);
		click(InventoryReportsMenu);
		click(itemqueryMenu);
		click(itemTxt);
		Thread.sleep(2000);
		enterText(itemTxt, excelReader.getCellData(xlSheetName, 104, 5));
		Thread.sleep(2000);
		click(getStockBtn);
		Thread.sleep(3000);

		String actlOpenStockQuantity 	= getText(openStockQuantityTxt);
		String expOpenStockQuantity 	= excelReader.getCellData(xlSheetName, 104, 6);
		excelReader.setCellData(xlfile, xlSheetName, 104, 7, actlOpenStockQuantity);

		String actlCurrentStock 		= getText(currentStockTxt);
		String expCurrentStock 			= excelReader.getCellData(xlSheetName, 105, 6);
		excelReader.setCellData(xlfile, xlSheetName, 105, 7, actlCurrentStock);

		String actlavgStockRate 		= getText(avgStockRateTxt);
		String expavgStockRate 			= excelReader.getCellData(xlSheetName, 106, 6);
		excelReader.setCellData(xlfile, xlSheetName, 106, 7, actlavgStockRate);

		String actlstockValueTxt 		= getText(stockValueTxt);
		String expstockValueTxt 		= excelReader.getCellData(xlSheetName, 107, 6);
		excelReader.setCellData(xlfile, xlSheetName, 107, 7, actlstockValueTxt);

		System.out.println("=====Stocks In Batch Items======");

		System.out.println("actlOpenStockQuantity      :" + actlOpenStockQuantity);
		System.out.println("expOpenStockQuantity       :" + expOpenStockQuantity);

		System.out.println("actlCurrentStock      	   :" + actlCurrentStock);
		System.out.println("expCurrentStock       	   :" + expCurrentStock);

		System.out.println("actlavgStockRate     	   :" + actlavgStockRate);
		System.out.println("expavgStockRate      	   :" + expavgStockRate);

		System.out.println("actlstockValueTxt    	   :" + actlstockValueTxt);
		System.out.println("expstockValueTxt       	   :" + expstockValueTxt);

		if (actlOpenStockQuantity.equalsIgnoreCase(expOpenStockQuantity)
				&& actlCurrentStock.equalsIgnoreCase(expCurrentStock)
				&& actlavgStockRate.equalsIgnoreCase(expavgStockRate)
				&& actlstockValueTxt.equalsIgnoreCase(expstockValueTxt)) {
			
			excelReader.setCellData(xlfile, xlSheetName, 102, 8, resPass);
			return true;
		} else {
			
			excelReader.setCellData(xlfile, xlSheetName, 102, 8, resFail);
			return false;
		}
		
		}
	
	
	public boolean validteItemQueryStocksInFifoItemAfterLoginToInvUser() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {

		excelReader=new ExcelReader(POJOUtility.getExcelPath());
		
		click(itemTxt);
		Thread.sleep(2000);
		itemTxt.sendKeys(Keys.CONTROL, "a");
		Thread.sleep(2000);
		itemTxt.sendKeys(Keys.BACK_SPACE);
		enterText(itemTxt, excelReader.getCellData(xlSheetName, 108, 5));
		Thread.sleep(3000);
		click(getStockBtn);
		Thread.sleep(3000);

		String actlOpenStockQuantity = getText(openStockQuantityTxt);
		String expOpenStockQuantity  = excelReader.getCellData(xlSheetName, 108, 6);
		excelReader.setCellData(xlfile, xlSheetName, 108, 7, actlOpenStockQuantity);

		String actlCurrentStock 	 = getText(currentStockTxt);
		String expCurrentStock 		 = excelReader.getCellData(xlSheetName, 109, 6);
		excelReader.setCellData(xlfile, xlSheetName, 109, 7, actlCurrentStock);

		String actlavgStockRate 	 = getText(avgStockRateTxt);
		String expavgStockRate 		 = excelReader.getCellData(xlSheetName, 110, 6);
		excelReader.setCellData(xlfile, xlSheetName, 110, 7, actlavgStockRate);
		
		String actlstockValueTxt 	 = getText(stockValueTxt);
		String expstockValueTxt 	 = excelReader.getCellData(xlSheetName, 111, 6);
		excelReader.setCellData(xlfile, xlSheetName, 111, 7, actlstockValueTxt);

		System.out.println("=====Stocks In FIFO Items======");

		System.out.println("actlOpenStockQuantity      :" + actlOpenStockQuantity);
		System.out.println("expOpenStockQuantity       :" + expOpenStockQuantity);

		System.out.println("actlCurrentStock      	   :" + actlCurrentStock);
		System.out.println("expCurrentStock            :" + expCurrentStock);

		System.out.println("actlavgStockRate           :" + actlavgStockRate);
		System.out.println("expavgStockRate            :" + expavgStockRate);

		System.out.println("actlstockValueTxt          :" + actlstockValueTxt);
		System.out.println("expstockValueTxt           :" + expstockValueTxt);

		if (actlOpenStockQuantity.equalsIgnoreCase(expOpenStockQuantity)
				&& actlCurrentStock.equalsIgnoreCase(expCurrentStock)
				&& actlavgStockRate.equalsIgnoreCase(expavgStockRate)
				&& actlstockValueTxt.equalsIgnoreCase(expstockValueTxt)) {
			
			excelReader.setCellData(xlfile, xlSheetName, 108, 8, resPass);
			return true;
		} else {
			excelReader.setCellData(xlfile, xlSheetName, 108, 8, resPass);
			return false;
		}

	}
	
	
	public boolean validteItemQueryStocksInWAItemAfterLoginToInvUser() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {

		excelReader=new ExcelReader(POJOUtility.getExcelPath());
		
		click(itemTxt);
		Thread.sleep(2000);
		itemTxt.sendKeys(Keys.CONTROL, "a");
		Thread.sleep(2000);
		itemTxt.sendKeys(Keys.BACK_SPACE);
		enterText(itemTxt, excelReader.getCellData(xlSheetName, 112, 5));
		Thread.sleep(3000);
		click(getStockBtn);
		Thread.sleep(3000);

		String actlOpenStockQuantity = getText(openStockQuantityTxt);
		String expOpenStockQuantity  = excelReader.getCellData(xlSheetName, 112, 6);
		excelReader.setCellData(xlfile, xlSheetName, 112, 7, actlOpenStockQuantity);

		String actlCurrentStock 	 = getText(currentStockTxt);
		String expCurrentStock       = excelReader.getCellData(xlSheetName, 113, 6);
		excelReader.setCellData(xlfile, xlSheetName, 113, 7, actlCurrentStock);

		String actlavgStockRate      = getText(avgStockRateTxt);
		String expavgStockRate       = excelReader.getCellData(xlSheetName, 114, 6);
		excelReader.setCellData(xlfile, xlSheetName, 114, 7, actlavgStockRate);

		String actlstockValueTxt     = getText(stockValueTxt);
		String expstockValueTxt      = excelReader.getCellData(xlSheetName, 115, 6);
		excelReader.setCellData(xlfile, xlSheetName, 115, 7, actlstockValueTxt);

		ScrollToElement(balanceByWarehouseBtn);
		click(balanceByWarehouseBtn);
		Thread.sleep(3000);

		int count = row1BalanceByWareHouseList.size();

		ArrayList<String> warehouseRow1 = new ArrayList<String>();

		for (int i = 0; i < count; i++) {
			String data = row1BalanceByWareHouseList.get(i).getText();
			warehouseRow1.add(data);
		}

		String actwarehouseRow1List = warehouseRow1.toString();
		String expWarehouseRow1List = excelReader.getCellData(xlSheetName, 116, 6);
		excelReader.setCellData(xlfile, xlSheetName, 116, 7, actwarehouseRow1List);

		int count1 = row2BalanceByWareHouseList.size();

		ArrayList<String> warehouseRow2 = new ArrayList<String>();

		for (int i = 0; i < count1; i++) {
			String data = row2BalanceByWareHouseList.get(i).getText();
			warehouseRow2.add(data);
		}

		String actwarehouseRow2List = warehouseRow2.toString();
		String expWarehouseRow2List = excelReader.getCellData(xlSheetName, 117, 6);
		excelReader.setCellData(xlfile, xlSheetName, 117, 7, actwarehouseRow2List);

		System.out.println("=====Stocks In WA Items======");

		System.out.println("actlOpenStockQuantity      :" + actlOpenStockQuantity);
		System.out.println("expOpenStockQuantity       :" + expOpenStockQuantity);

		System.out.println("actlCurrentStock           :" + actlCurrentStock);
		System.out.println("expCurrentStock            :" + expCurrentStock);

		System.out.println("actlavgStockRate           :" + actlavgStockRate);
		System.out.println("expavgStockRate            :" + expavgStockRate);

		System.out.println("actlstockValueTxt          :" + actlstockValueTxt);
		System.out.println("expstockValueTxt           :" + expstockValueTxt);

		System.out.println("===========Balance By Warehouse=======");

		System.out.println(actwarehouseRow1List);
		System.out.println(expWarehouseRow1List);

		System.out.println(actwarehouseRow2List);
		System.out.println(expWarehouseRow2List);

		
		if (actlOpenStockQuantity.equalsIgnoreCase(expOpenStockQuantity)
				&& actlCurrentStock.equalsIgnoreCase(expCurrentStock)
				&& actlavgStockRate.equalsIgnoreCase(expavgStockRate)
				&& actlstockValueTxt.equalsIgnoreCase(expstockValueTxt)
				&& actwarehouseRow1List.equalsIgnoreCase(expWarehouseRow1List)
				&& actwarehouseRow2List.equalsIgnoreCase(expWarehouseRow2List)) {
			
			excelReader.setCellData(xlfile, xlSheetName, 112, 8, resPass);
			return true;
		} else {
			
			excelReader.setCellData(xlfile, xlSheetName, 112, 8, resFail);
			return false;
		}

	}
	
	
	public boolean checkStockLedgerReportForBrBatchItemAfterLoginToInvUser()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {

		excelReader=new ExcelReader(POJOUtility.getExcelPath());
		
		click(inventoryMenu);
		click(InventoryReportsMenu);
		click(InventoryStockLedgerMenu);
		Thread.sleep(3000);

		int rowcount = stockLedgerHometableRowCount.size();

		System.out.println(rowcount);

		for (int i = 0; i < rowcount; i++) {
			String actName = ledgerHometableItemNamesList.get(i).getText();

			System.out.println(actName);

			if (actName.equalsIgnoreCase(excelReader.getCellData(xlSheetName, 118, 5))) {
				stockLedgerHometableItemChkboxList.get(i).click();
			}
		}

		
		dropDown(sl_DateOptionDropdown, excelReader.getCellData(xlSheetName, 119, 5));	
		Thread.sleep(2000);
		click(sl_OkBtn);
		Thread.sleep(2000);
		click(stockLedgerLastBtn);
		Thread.sleep(1000);
		//click(stockLedgerPreviousBtn);
		Thread.sleep(3000);

		String actStockLedgerBrBatchBalanceQty   = getText(stockLedgerBrBatchBalanceQtyAfterLoginToInvUser);
		String expStockLedgerBrBatchBalanceQty   = excelReader.getCellData(xlSheetName, 118, 6);
		excelReader.setCellData(xlfile, xlSheetName, 118, 7, actStockLedgerBrBatchBalanceQty);

		String actStockLedgerBrBatchBalanceValue = getText(stockLedgerBrBatchBalanceValueAfterLoginToInvUser);
		String expStockLedgerBrBatchBalanceValue = excelReader.getCellData(xlSheetName, 119, 6);
		excelReader.setCellData(xlfile, xlSheetName, 119, 7, actStockLedgerBrBatchBalanceValue);

		String actStockLedgerBrBatchAvgRate      = getText(stockLedgerBrBatchAvgRateAfterLoginToInvUser);
		String expStockLedgerBrBatchAvgRate      = excelReader.getCellData(xlSheetName, 120, 6);
		excelReader.setCellData(xlfile, xlSheetName, 120, 7, actStockLedgerBrBatchAvgRate);

		System.out.println("actStockLedgerBrBatchBalanceQty      :" + actStockLedgerBrBatchBalanceQty);
		System.out.println("expStockLedgerBrBatchBalanceQty      :" + expStockLedgerBrBatchBalanceQty);

		System.out.println("actStockLedgerBrBatchBalanceValue    :" + actStockLedgerBrBatchBalanceValue);
		System.out.println("expStockLedgerBrBatchBalanceValue    :" + expStockLedgerBrBatchBalanceValue);

		System.out.println("actStockLedgerBrBatchAvgRate         :" + actStockLedgerBrBatchAvgRate);
		System.out.println("expStockLedgerBrBatchAvgRate         :" + expStockLedgerBrBatchAvgRate);

		if (actStockLedgerBrBatchBalanceQty.equalsIgnoreCase(expStockLedgerBrBatchBalanceQty)
				&& actStockLedgerBrBatchBalanceValue.equalsIgnoreCase(expStockLedgerBrBatchBalanceValue)
				&& actStockLedgerBrBatchAvgRate.equalsIgnoreCase(expStockLedgerBrBatchAvgRate)) {

			click(report_CloseBtn);
			excelReader.setCellData(xlfile, xlSheetName, 118, 8, resPass);

			return true;
		} else {
			click(report_CloseBtn);
			excelReader.setCellData(xlfile, xlSheetName, 118, 8, resFail);

			return false;
		}
	}
	
	
	public boolean checkStockLedgerReportForFifoItemAfterLoginToInvUser()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {

		excelReader=new ExcelReader(POJOUtility.getExcelPath());
		Thread.sleep(3000);

		if (fifoItemCheckBox.isSelected() == false) {
			click(fifoItemCheckBox);
		}

		if (brBatchItemCheckBox.isSelected() == true) {
			click(brBatchItemCheckBox);
		}

		if (rmaItemCheckBox.isSelected() == true) {
			click(rmaItemCheckBox);

		}

		
		dropDown(sl_DateOptionDropdown, excelReader.getCellData(xlSheetName, 121, 5));
		Thread.sleep(2000);
		click(sl_OkBtn);
		Thread.sleep(2000);
		click(stockLedgerLastBtn);
		Thread.sleep(3000);

		String actstockLedgerFifoItemBalanceQty    = getText(stockLedgerFifoItemBalanceQtyAfterLoginToInvUser);
		String expstockLedgerFifoItemBalanceQty    = excelReader.getCellData(xlSheetName, 121, 6);
		excelReader.setCellData(xlfile, xlSheetName, 121, 7, actstockLedgerFifoItemBalanceQty);

		String actstockLedgerFifoItemBalancevalu   = getText(stockLedgerFifoItemBalancevaluAfterLoginToInvUser);
		String expstockLedgerFifoItemBalancevalu   = excelReader.getCellData(xlSheetName, 122, 6);
		excelReader.setCellData(xlfile, xlSheetName, 122, 7, actstockLedgerFifoItemBalancevalu);

		String actstockLedgerFifoItemAvgRate       = getText(stockLedgerFifoItemAvgRateAfterLoginToInvUser);
		String expstockLedgerFifoItemAvgRate       = excelReader.getCellData(xlSheetName, 123, 6);
		excelReader.setCellData(xlfile, xlSheetName, 123, 7, actstockLedgerFifoItemAvgRate);

		System.out.println("actstockLedgerFifoItemBalanceQty      :" + actstockLedgerFifoItemBalanceQty);
		System.out.println("expstockLedgerFifoItemBalanceQty      :" + expstockLedgerFifoItemBalanceQty);

		System.out.println("actstockLedgerFifoItemBalancevalu     :" + actstockLedgerFifoItemBalancevalu);
		System.out.println("expstockLedgerFifoItemBalancevalu     :" + expstockLedgerFifoItemBalancevalu);

		System.out.println("actstockLedgerFifoItemAvgRate         :" + actstockLedgerFifoItemAvgRate);
		System.out.println("expstockLedgerFifoItemAvgRate         :" + expstockLedgerFifoItemAvgRate);

		if (actstockLedgerFifoItemBalanceQty.equalsIgnoreCase(expstockLedgerFifoItemBalanceQty)
				&& actstockLedgerFifoItemBalancevalu.equalsIgnoreCase(expstockLedgerFifoItemBalancevalu)
				&& actstockLedgerFifoItemAvgRate.equalsIgnoreCase(expstockLedgerFifoItemAvgRate)) {
			
			excelReader.setCellData(xlfile, xlSheetName, 121, 8, resPass);

			click(report_CloseBtn);

			return true;
		} else {
			
			excelReader.setCellData(xlfile, xlSheetName, 121, 8, resFail);
			click(report_CloseBtn);

			return false;
		}
	}
	
	
	
	
	public boolean checkStockLedgerReportForRmaItemAfterLoginToInvUser()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {

		
		excelReader=new ExcelReader(POJOUtility.getExcelPath());
		Thread.sleep(3000);

		if (fifoItemCheckBox.isSelected() == true) {
			click(fifoItemCheckBox);

		}

		if (brBatchItemCheckBox.isSelected() == true) {
			click(brBatchItemCheckBox);
		}

		if (rmaItemCheckBox.isSelected() == false) {
			click(rmaItemCheckBox);

		}

		
		dropDown(sl_DateOptionDropdown, excelReader.getCellData(xlSheetName, 124, 5));		
		Thread.sleep(2000);
		click(sl_OkBtn);
		Thread.sleep(2000);
		click(stockLedgerLastBtn);
		Thread.sleep(3000);

		String actstockLedgerRmaItemBalanceQty   = getText(stockLedgerRmaItemBalanceQtyAfterLoginToInvUser);
		String expstockLedgerRmaItemBalanceQty   = excelReader.getCellData(xlSheetName, 124, 6);
		excelReader.setCellData(xlfile, xlSheetName, 124, 7, actstockLedgerRmaItemBalanceQty);

		String actstockLedgerRmaItemBalanceValue = getText(stockLedgerRmaItemBalanceValueAfterLoginToInvUser);
		String expstockLedgerRmaItemBalanceValue = excelReader.getCellData(xlSheetName, 125, 6);
		excelReader.setCellData(xlfile, xlSheetName, 125, 7, actstockLedgerRmaItemBalanceValue);

		String actstockLedgerrmaItemAvgRate      = getText(stockLedgerrmaItemAvgRateAfterLogintoInvUser);
		String expstockLedgerrmaItemAvgRate      = excelReader.getCellData(xlSheetName, 126, 6);
		excelReader.setCellData(xlfile, xlSheetName, 126, 7, actstockLedgerrmaItemAvgRate);

		System.out.println("actstockLedgerRmaItemBalanceQty      :" + actstockLedgerRmaItemBalanceQty);
		System.out.println("expstockLedgerRmaItemBalanceQty      :" + expstockLedgerRmaItemBalanceQty);

		System.out.println("actstockLedgerRmaItemBalanceValue    :" + actstockLedgerRmaItemBalanceValue);
		System.out.println("expstockLedgerRmaItemBalanceValue    :" + expstockLedgerRmaItemBalanceValue);

		System.out.println("actstockLedgerrmaItemAvgRate         :" + actstockLedgerrmaItemAvgRate);
		System.out.println("expstockLedgerrmaItemAvgRate         :" + expstockLedgerrmaItemAvgRate);
		
		click(stockLedgerFilterBtn);
		
		Thread.sleep(2000);
		

		
		click(stockLedgerFilterField);
		
		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(stockLedgerFilterText));

		String actFilterpopupIsDisplaying = Boolean.toString(stockLedgerFilterText.isDisplayed());
		String expFilterpopupIsDisplaying = excelReader.getCellData(xlSheetName, 127, 6);
		excelReader.setCellData(xlfile, xlSheetName, 127, 7, actFilterpopupIsDisplaying);

		System.out.println("Filter Popup isDisplaying  : " + actFilterpopupIsDisplaying + "  Value Expected  "
				+ expFilterpopupIsDisplaying);
		
		click(stockLedgerFilterWarehouseField);
		enterText(stockLedgerFilterWarehouseField, excelReader.getCellData(xlSheetName, 125, 5));
		Thread.sleep(2000);
		click(stockLedgerFilterOkBtn);
		Thread.sleep(1000);
		click(stockLedgerLastBtn);
		Thread.sleep(2000);
		
		String actstockLedgerRmaItemHydBalanceQtyAfterLoginToInvUser   = getText(stockLedgerRmaItemHydBalanceQtyAfterLoginToInvUser);
		String expstockLedgerRmaItemHydBalanceQtyAfterLoginToInvUser   = excelReader.getCellData(xlSheetName, 128, 6);
		excelReader.setCellData(xlfile, xlSheetName, 128, 7, actstockLedgerRmaItemHydBalanceQtyAfterLoginToInvUser);

		String actstockLedgerRmaItemHydBalanceValueAfterLoginToInvUser = getText(stockLedgerRmaItemHydBalanceValueAfterLoginToInvUser);
		String expstockLedgerRmaItemHydBalanceValueAfterLoginToInvUser = excelReader.getCellData(xlSheetName, 129, 6);
		excelReader.setCellData(xlfile, xlSheetName, 129, 7, actstockLedgerRmaItemHydBalanceValueAfterLoginToInvUser);

		String actstockLedgerrmaItemHydAvgRateAfterLoginToInvUser      = getText(stockLedgerrmaItemHydAvgRateAfterLoginToInvUser);
		String expstockLedgerrmaItemHydAvgRateAfterLoginToInvUser      = excelReader.getCellData(xlSheetName, 130, 6);
		excelReader.setCellData(xlfile, xlSheetName, 130, 7, actstockLedgerrmaItemHydAvgRateAfterLoginToInvUser);
		
		System.out.println("=========actstockLedgerRmaItem  HydWareHouse  Values============   : ");
		
		System.out.println("actstockLedgerRmaItemHydBalanceQtyAfterLoginToInvUser      :" + actstockLedgerRmaItemHydBalanceQtyAfterLoginToInvUser);
		System.out.println("expstockLedgerRmaItemHydBalanceQtyAfterLoginToInvUser      :" + expstockLedgerRmaItemHydBalanceQtyAfterLoginToInvUser);
		System.out.println("actstockLedgerRmaItemHydBalanceValueAfterLoginToInvUser    :" + actstockLedgerRmaItemHydBalanceValueAfterLoginToInvUser);
		System.out.println("expstockLedgerRmaItemHydBalanceValueAfterLoginToInvUser    :" + expstockLedgerRmaItemHydBalanceValueAfterLoginToInvUser);

		System.out.println("actstockLedgerrmaItemHydAvgRateAfterLoginToInvUser         :" + actstockLedgerrmaItemHydAvgRateAfterLoginToInvUser);
		System.out.println("expstockLedgerrmaItemHydAvgRateAfterLoginToInvUser         :" + expstockLedgerrmaItemHydAvgRateAfterLoginToInvUser);		
		
		click(report_CloseBtn);
		Thread.sleep(1000);
		click(stockLedgerHomePageFilterBtn);
		Thread.sleep(2000);
		click(stockLedgerFilterWarehouseField2);
		Thread.sleep(1000);
		click(stockLedgerFilterWarehouseField3);
		Thread.sleep(1500);
		stockLedgerFilterWarehouseField4.sendKeys(Keys.CONTROL, "a");
		Thread.sleep(1000);
		stockLedgerFilterWarehouseField4.sendKeys(Keys.BACK_SPACE);
		Thread.sleep(1000);
		enterText(stockLedgerFilterWarehouseField4, excelReader.getCellData(xlSheetName, 126, 5));
		Thread.sleep(1000);
		tab(stockLedgerFilterWarehouseField4);
		Thread.sleep(1500);
		click(stockLedgerFilterOkBtn);
		Thread.sleep(1000);
		click(sl_OkBtn);
		Thread.sleep(1000);
		click(stockLedgerLastBtn);
		Thread.sleep(2000);
		
		
		
		String actstockLedgerRmaItemSecBalanceQtyAfterLoginToInvUser   = getText(stockLedgerRmaItemSecBalanceQtyAfterLoginToInvUser);
		String expstockLedgerRmaItemSecBalanceQtyAfterLoginToInvUser   = excelReader.getCellData(xlSheetName, 131, 6);
		excelReader.setCellData(xlfile, xlSheetName, 131, 7, actstockLedgerRmaItemSecBalanceQtyAfterLoginToInvUser);

		String actstockLedgerRmaItemSecBalanceValueAfterLoginToInvUser = getText(stockLedgerRmaItemSecBalanceValueAfterLoginToInvUser);
		String expstockLedgerRmaItemSecBalanceValueAfterLoginToInvUser = excelReader.getCellData(xlSheetName, 132, 6);
		excelReader.setCellData(xlfile, xlSheetName, 132, 7, actstockLedgerRmaItemSecBalanceValueAfterLoginToInvUser);

		String actstockLedgerrmaItemSecAvgRateAfterLoginToInvUser      = getText(stockLedgerrmaItemSecAvgRateAfterLoginToInvUser);
		String expstockLedgerrmaItemSecAvgRateAfterLoginToInvUser      = excelReader.getCellData(xlSheetName, 133, 6);
		excelReader.setCellData(xlfile, xlSheetName, 133, 7, actstockLedgerrmaItemSecAvgRateAfterLoginToInvUser);
		
		System.out.println("=========actstockLedgerRmaItem  SEC WareHouse  Values============   : ");
		
		System.out.println("actstockLedgerRmaItemSecBalanceQtyAfterLoginToInvUser      :" + actstockLedgerRmaItemSecBalanceQtyAfterLoginToInvUser);
		System.out.println("expstockLedgerRmaItemSecBalanceQtyAfterLoginToInvUser      :" + expstockLedgerRmaItemSecBalanceQtyAfterLoginToInvUser);

		System.out.println("actstockLedgerRmaItemSecBalanceValueAfterLoginToInvUser    :" + actstockLedgerRmaItemSecBalanceValueAfterLoginToInvUser);
		System.out.println("expstockLedgerRmaItemSecBalanceValueAfterLoginToInvUser    :" + expstockLedgerRmaItemSecBalanceValueAfterLoginToInvUser);

		System.out.println("actstockLedgerrmaItemSecAvgRateAfterLoginToInvUser         :" + actstockLedgerrmaItemSecAvgRateAfterLoginToInvUser);
		System.out.println("expstockLedgerrmaItemSecAvgRateAfterLoginToInvUser         :" + expstockLedgerrmaItemSecAvgRateAfterLoginToInvUser);


		if (actstockLedgerRmaItemBalanceQty.equalsIgnoreCase(expstockLedgerRmaItemBalanceQty)
				&& actstockLedgerRmaItemBalanceValue.equalsIgnoreCase(expstockLedgerRmaItemBalanceValue)
				&& actstockLedgerrmaItemAvgRate.equalsIgnoreCase(expstockLedgerrmaItemAvgRate)
				&& actstockLedgerRmaItemHydBalanceQtyAfterLoginToInvUser.equalsIgnoreCase(expstockLedgerRmaItemHydBalanceQtyAfterLoginToInvUser)
				&& actstockLedgerRmaItemHydBalanceValueAfterLoginToInvUser.equalsIgnoreCase(expstockLedgerRmaItemHydBalanceValueAfterLoginToInvUser)
				&& actstockLedgerrmaItemHydAvgRateAfterLoginToInvUser.equalsIgnoreCase(expstockLedgerrmaItemHydAvgRateAfterLoginToInvUser)
				&& actstockLedgerRmaItemSecBalanceQtyAfterLoginToInvUser.equalsIgnoreCase(expstockLedgerRmaItemSecBalanceQtyAfterLoginToInvUser)
				&& actstockLedgerRmaItemSecBalanceValueAfterLoginToInvUser.equalsIgnoreCase(expstockLedgerRmaItemSecBalanceValueAfterLoginToInvUser)
				&& actstockLedgerrmaItemSecAvgRateAfterLoginToInvUser.equalsIgnoreCase(expstockLedgerrmaItemSecAvgRateAfterLoginToInvUser)) {

			click(report_CloseBtn);
			Thread.sleep(2000);
			click(sl_CloseBtn);
			excelReader.setCellData(xlfile, xlSheetName, 124, 8, resPass);
			return true;
		} else {
			click(report_CloseBtn);
			Thread.sleep(2000);
			click(sl_CloseBtn);
			excelReader.setCellData(xlfile, xlSheetName, 124, 8, resFail);
			return false;
		}
	}
	
	
	public boolean checkSavingSalesInvoiceVoucherBrBatchItemWithHydWarehouse()
			throws EncryptedDocumentException, InvalidFormatException, InterruptedException, IOException {
		
		excelReader=new ExcelReader(POJOUtility.getExcelPath());
		
		
		
		click(financialsMenu);
		click(financialsTransactionMenu);
		click(financialsTransactionsSalesMenu);
		click(salesInvoiceVoucher);
		click(newBtn);
		checkValidationMessage("Screen opened");
		click(salesDatetxt);
		salesDatetxt.sendKeys(Keys.CONTROL, "a");
		Thread.sleep(2000);
		salesDatetxt.sendKeys(Keys.BACK_SPACE);
		enterText(salesDatetxt, excelReader.getCellData(xlSheetName, 134, 5));
		tab(salesDatetxt);
		Thread.sleep(2000);
		enterText(salesAccounttxt, excelReader.getCellData(xlSheetName, 135, 5));
		tab(salesAccounttxt);
		Thread.sleep(2000);
		enterText(customerAccounttxt, excelReader.getCellData(xlSheetName, 136, 5));
		Thread.sleep(2000);
		click(customerAccounttxt);
		enterText(salesDepartmenttxt, excelReader.getCellData(xlSheetName, 137, 5));
		Thread.sleep(1000);
		tab(salesDepartmenttxt);
		Thread.sleep(3000);		
		click(select1stRow_1stColumn);
		//click(enterWarehousettxt);
		enterWarehousettxt.sendKeys(Keys.SPACE);
		Thread.sleep(2000);
		
		int warehousecount=svwareHouseListCount.size();
		System.err.println(warehousecount);
		ArrayList<String> warehouse = new ArrayList<String>();
		for(int i=0 ; i < warehousecount ;i++)
	  	{
	  		String data=svwareHouseListCount.get(i).getText();
	  		warehouse.add(data);
	  	}
		
		String actWarehouseList = warehouse.toString();
		String expWarehouseList = excelReader.getCellData(xlSheetName, 134, 6);
		excelReader.setCellData(xlfile, xlSheetName, 134, 7, actWarehouseList);
		
		System.out.println("actWarehouseList       :"+actWarehouseList);		
		System.out.println("expWarehouseList       :"+expWarehouseList);
		
		enterWarehousettxt.sendKeys(Keys.CONTROL, "a");
		Thread.sleep(2000);
		enterWarehousettxt.sendKeys(Keys.BACK_SPACE);		
		enterText(enterWarehousettxt, excelReader.getCellData(xlSheetName, 138, 5));
		Thread.sleep(2000);
		tab(enterWarehousettxt);
		enterText(enterItemttxt, excelReader.getCellData(xlSheetName, 139, 5));
		Thread.sleep(2000);
		tab(enterItemttxt);
		Thread.sleep(2000);
		
		String actAvailableStock      =enterAvailableStocktxt.getAttribute("value");
		String expAvailableStock	  =excelReader.getCellData(xlSheetName, 135, 6);
		excelReader.setCellData(xlfile, xlSheetName, 135, 7, actAvailableStock);
		
		
		System.out.println("actAvailableStock       :"+actAvailableStock);
		System.out.println("expAvailableStock       :"+expAvailableStock);
		
		
		tab(enterAvailableStocktxt);
		
		enterText(enterQuantitytxt, excelReader.getCellData(xlSheetName, 140, 5));
		tab(enterQuantitytxt);
		enterText(enterRatetxt, excelReader.getCellData(xlSheetName, 141, 5));
		tab(enterRatetxt);
		Thread.sleep(2000);
		click(select1stRow_10thColumn);
		
		Thread.sleep(2000);
		
		String actRow1Baseqty		=batchPopUPRow1BaseQuantityTxt.getText();
		String expRow1Baseqty		=excelReader.getCellData(xlSheetName, 136, 6);
		excelReader.setCellData(xlfile, xlSheetName, 136, 7, actRow1Baseqty);
		
		String actRow1Rate			=batchPopUPRow1RateTxt.getText();
		String expRow1Rate			=excelReader.getCellData(xlSheetName, 137, 6);
		excelReader.setCellData(xlfile, xlSheetName, 137, 7, actRow1Rate);
		
		String actRow2Baseqty		=batchPopUPRow2BaseQuantityTxt.getText();
		String expRow2Baseqty		=excelReader.getCellData(xlSheetName, 138, 6);
		excelReader.setCellData(xlfile, xlSheetName, 138, 7, actRow2Baseqty);
		
		String actRow2Rate			=batchPopUPRow2RateTxt.getText();
		String expRow2Rate			=excelReader.getCellData(xlSheetName, 139, 6);
		excelReader.setCellData(xlfile, xlSheetName, 139, 7, actRow2Rate);
		
		String actRow3Baseqty		=batchPopUPRow3BaseQuantityTxt.getText();
		String expRow3Baseqty		=excelReader.getCellData(xlSheetName, 140, 6);
		excelReader.setCellData(xlfile, xlSheetName, 140, 7, actRow3Baseqty);
		
		String actRow3Rate			=batchPopUPRow3RateTxt.getText();
		String expRow3Rate			=excelReader.getCellData(xlSheetName, 141, 6);
		excelReader.setCellData(xlfile, xlSheetName, 141, 7, actRow3Rate);
		
		System.out.println("actRow1Baseqty             :"+actRow1Baseqty);
		System.out.println("expRow1Baseqty             :"+expRow1Baseqty);
		
		System.out.println("actRow1Rate                :"+actRow1Rate);
		System.out.println("expRow1Rate                :"+expRow1Rate);
		
		System.out.println("actRow2Baseqty             :"+actRow2Baseqty);
		System.out.println("expRow2Baseqty             :"+expRow2Baseqty);
		
		System.out.println("actRow2Rate                :"+actRow2Rate);
		System.out.println("expRow2Rate                :"+expRow2Rate);
		
		System.out.println("actRow3Baseqty             :"+actRow3Baseqty);
		System.out.println("expRow3Baseqty             :"+expRow3Baseqty);
		
		System.out.println("actRow3Rate                :"+actRow3Rate);
		System.out.println("expRow3Rate                :"+expRow3Rate);
		
		
		
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(batchPickOnFIFOIcon));
		
		String actBatchpopupIsDisplaying = Boolean.toString(batchPickOnFIFOIcon.isDisplayed());
		String expBatchpopupIsDisplaying = excelReader.getCellData(xlSheetName, 142, 6);
		excelReader.setCellData(xlfile, xlSheetName, 142, 7, actBatchpopupIsDisplaying);
		
		System.out.println("Batch Popup isDisplaying  : "+actBatchpopupIsDisplaying+"  Value Expected  "+expBatchpopupIsDisplaying);
		click(batchPickOnFIFOIcon);
    	click(batchOkIcon);
    	
    	Thread.sleep(2000);
    	click(select4thRow_1stColumn);
    	
    	int row1List=Row1SalesInvoicevocherBodyList.size();
		System.err.println(row1List);
		ArrayList<String> row1SalesList = new ArrayList<String>();
		for(int i=0 ; i < row1List ;i++)
	  	{
	  		String data=Row1SalesInvoicevocherBodyList.get(i).getText();
	  		row1SalesList.add(data);
	  	}
		
		String actrow1SalesList = row1SalesList.toString();
		String exprow1SalesList = excelReader.getCellData(xlSheetName, 143, 6);
		excelReader.setCellData(xlfile, xlSheetName, 143, 7, actrow1SalesList);
		
		System.out.println("actrow1SalesList       :"+actrow1SalesList);		
		System.out.println("exprow1SalesList       :"+exprow1SalesList);
		
		int row2List=Row2SalesInvoicevocherBodyList.size();
		System.err.println(row2List);
		ArrayList<String> row2SalesList = new ArrayList<String>();
		for(int i=0 ; i < row2List ;i++)
	  	{
	  		String data=Row2SalesInvoicevocherBodyList.get(i).getText();
	  		row2SalesList.add(data);
	  	}
		
		String actrow2SalesList = row2SalesList.toString();
		String exprow2SalesList = excelReader.getCellData(xlSheetName, 144, 6);
		excelReader.setCellData(xlfile, xlSheetName, 144, 7, actrow2SalesList);
		
		System.out.println("actrow2SalesList       :"+actrow2SalesList);		
		System.out.println("exprow2SalesList       :"+exprow2SalesList);
		
		
		int row3List=Row3SalesInvoicevocherBodyList.size();
		System.err.println(row3List);
		ArrayList<String> row3SalesList = new ArrayList<String>();
		for(int i=0 ; i < row3List ;i++)
	  	{
	  		String data=Row3SalesInvoicevocherBodyList.get(i).getText();
	  		row3SalesList.add(data);
	  	}
		
		String actrow3SalesList = row3SalesList.toString();
		String exprow3SalesList = excelReader.getCellData(xlSheetName, 145, 6);
		excelReader.setCellData(xlfile, xlSheetName, 145, 7, actrow3SalesList);
		
		System.out.println("actrow3SalesList       :"+actrow3SalesList);		
		System.out.println("exprow3SalesList       :"+exprow3SalesList);
		
    	Thread.sleep(2000);
    	
    	if(actWarehouseList.equalsIgnoreCase(expWarehouseList) && actAvailableStock.equalsIgnoreCase(expAvailableStock)
    			&& actRow1Baseqty.equalsIgnoreCase(expRow1Baseqty) && actRow1Rate.equalsIgnoreCase(expRow1Rate)
    			&& actRow2Baseqty.equalsIgnoreCase(expRow2Baseqty) && actRow2Rate.equalsIgnoreCase(expRow2Rate)
    			&& actRow3Baseqty.equalsIgnoreCase(expRow3Baseqty) && actRow3Rate.equalsIgnoreCase(expRow3Rate)
    			&& actrow1SalesList.equalsIgnoreCase(exprow1SalesList) && actrow2SalesList.equalsIgnoreCase(exprow2SalesList)
    			&& actrow3SalesList.equalsIgnoreCase(exprow3SalesList)) {
    		
    		excelReader.setCellData(xlfile, xlSheetName, 134, 8, resPass);
    		
    		return true;
    	}
    		else{
    			
    			excelReader.setCellData(xlfile, xlSheetName, 134, 8, resFail);
    			return false;
    		}
    		
    	}
	
	
			
	public boolean checkSavingSalesInvoiceVoucherBrBatchItemWithSECWarehouse() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException{	
		
		excelReader=new ExcelReader(POJOUtility.getExcelPath());

		// 4th Row
		Thread.sleep(2000);

		//click(select4thRow_1stColumn);
		Thread.sleep(2000);
		enterWarehousettxt.sendKeys(Keys.CONTROL, "a");
		Thread.sleep(2000);
		enterWarehousettxt.sendKeys(Keys.BACK_SPACE);
		enterText(enterWarehousettxt, excelReader.getCellData(xlSheetName, 146, 5));
		Thread.sleep(3000);
		tab(enterWarehousettxt);
		enterText(enterItemttxt, excelReader.getCellData(xlSheetName, 147, 5));
		Thread.sleep(2000);
		tab(enterItemttxt);
		Thread.sleep(2000);
		
		String actAvailableStock1      =enterAvailableStocktxt.getAttribute("value");
		String expAvailableStock1	   =excelReader.getCellData(xlSheetName, 146, 6);
		excelReader.setCellData(xlfile, xlSheetName, 146, 7, actAvailableStock1);
		
		
		System.out.println("actAvailableStock1       :"+actAvailableStock1);
		System.out.println("expAvailableStock1       :"+expAvailableStock1);		
		
		
		tab(enterAvailableStocktxt);
		enterText(enterQuantitytxt, excelReader.getCellData(xlSheetName, 148, 5));
		tab(enterQuantitytxt);
		enterText(enterRatetxt, excelReader.getCellData(xlSheetName, 149, 5));
		tab(enterRatetxt);
		click(select4thRow_10thColumn);
		
		Thread.sleep(2000);
		
		
		String actRow1Baseqty1		=batchPopUPRow1BaseQuantityTxt.getText();
		String expRow1Baseqty1		=excelReader.getCellData(xlSheetName, 147, 6);
		excelReader.setCellData(xlfile, xlSheetName, 147, 7, actRow1Baseqty1);
		
		String actRow1Rate1			=batchPopUPRow1RateTxt.getText();
		String expRow1Rate1			=excelReader.getCellData(xlSheetName, 148, 6);
		excelReader.setCellData(xlfile, xlSheetName, 148, 7, actRow1Rate1);
		
		String actRow2Baseqty1		=batchPopUPRow2BaseQuantityTxt.getText();
		String expRow2Baseqty1		=excelReader.getCellData(xlSheetName, 149, 6);
		excelReader.setCellData(xlfile, xlSheetName, 149, 7, actRow2Baseqty1);
		
		String actRow2Rate1			=batchPopUPRow2RateTxt.getText();
		String expRow2Rate1			=excelReader.getCellData(xlSheetName, 150, 6);
		excelReader.setCellData(xlfile, xlSheetName, 150, 7, actRow2Rate1);
		
	
		
		System.out.println("actRow1Baseqty1             :"+actRow1Baseqty1);
		System.out.println("expRow1Baseqty1             :"+expRow1Baseqty1);
		
		System.out.println("actRow1Rate1                :"+actRow1Rate1);
		System.out.println("expRow1Rate1                :"+expRow1Rate1);
		
		System.out.println("actRow2Baseqty1             :"+actRow2Baseqty1);
		System.out.println("expRow2Baseqty1             :"+expRow2Baseqty1);
		
		System.out.println("actRow2Rate1                :"+actRow2Rate1);
		System.out.println("expRow2Rate1                :"+expRow2Rate1);			
		
		
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(batchPickOnFIFOIcon));
		
		String actBatchpopupIsDisplaying1 = Boolean.toString(batchPickOnFIFOIcon.isDisplayed());
		String expBatchpopupIsDisplaying2 = excelReader.getCellData(xlSheetName, 151, 6);
		excelReader.setCellData(xlfile, xlSheetName, 151, 7, actBatchpopupIsDisplaying1);
		
		System.out.println("Batch Popup isDisplaying  : "+actBatchpopupIsDisplaying1+"  Value Expected  "+expBatchpopupIsDisplaying2);
		click(batchPickOnFIFOIcon);
    	click(batchOkIcon);
    	
    	Thread.sleep(3000);
    	
    	int row4List=Row4SalesInvoicevocherBodyList.size();
		System.err.println(row4List);
		ArrayList<String> row4SalesList = new ArrayList<String>();
		for(int i=0 ; i < row4List ;i++)
	  	{
	  		String data=Row4SalesInvoicevocherBodyList.get(i).getText();
	  		row4SalesList.add(data);
	  	}
		
		String actrow4SalesList = row4SalesList.toString();
		String exprow4SalesList = excelReader.getCellData(xlSheetName, 152, 6);
		excelReader.setCellData(xlfile, xlSheetName, 152, 7, actrow4SalesList);
		
		System.out.println("actrow4SalesList       :"+actrow4SalesList);		
		System.out.println("exprow4SalesList       :"+exprow4SalesList);
		
		Thread.sleep(2000);
		click(select6thRow_1stColumn);
		
		int row5List=Row5SalesInvoicevocherBodyList.size();
		System.err.println(row5List);
		ArrayList<String> row5SalesList = new ArrayList<String>();
		for(int i=0 ; i < row5List ;i++)
	  	{
	  		String data=Row5SalesInvoicevocherBodyList.get(i).getText();
	  		row5SalesList.add(data);
	  	}
		
		String actrow5SalesList = row5SalesList.toString();
		String exprow5SalesList = excelReader.getCellData(xlSheetName, 153, 6);
		excelReader.setCellData(xlfile, xlSheetName, 153, 7, actrow5SalesList);
		
		System.out.println("actrow5SalesList       :"+actrow5SalesList);		
		System.out.println("exprow5SalesList       :"+exprow5SalesList);
		
		Thread.sleep(2000);
		
		if(actAvailableStock1.equalsIgnoreCase(expAvailableStock1) && actRow1Baseqty1.equalsIgnoreCase(expRow1Baseqty1) 
				&& actRow1Rate1.equalsIgnoreCase(expRow1Rate1) && actRow2Baseqty1.equalsIgnoreCase(expRow2Baseqty1) 
				&& actRow2Rate1.equalsIgnoreCase(expRow2Rate1) && actrow4SalesList.equalsIgnoreCase(exprow4SalesList)
				&& actrow5SalesList.equalsIgnoreCase(exprow5SalesList)){
			
			excelReader.setCellData(xlfile, xlSheetName, 146, 8, resPass);			
			return true;
		}
			else{
				
				excelReader.setCellData(xlfile, xlSheetName, 146, 8, resFail);
				return false;
			}
			}
			
		
		
	public boolean checkSavingSalesInvoiceVoucherFifoItemWith2Warehouse() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException{ 	
		
		
		excelReader=new ExcelReader(POJOUtility.getExcelPath());
		Thread.sleep(2000);

		//click(select6thRow_1stColumn);
		enterWarehousettxt.sendKeys(Keys.CONTROL, "a");
		Thread.sleep(2000);
		enterWarehousettxt.sendKeys(Keys.BACK_SPACE);
		enterText(enterWarehousettxt, excelReader.getCellData(xlSheetName, 154, 5));
		Thread.sleep(2000);
		tab(enterWarehousettxt);
		enterText(enterItemttxt, excelReader.getCellData(xlSheetName, 155, 5));
		Thread.sleep(2000);
		tab(enterItemttxt);
		
		
		Thread.sleep(2000);
		
		String actAvailableStockInHyd      =enterAvailableStocktxt.getAttribute("value");
		String expAvailableStockInHyd	   =excelReader.getCellData(xlSheetName, 154, 6);
		excelReader.setCellData(xlfile, xlSheetName, 154, 7, actAvailableStockInHyd);
		
		
		System.out.println("actAvailableStockInHyd        :"+actAvailableStockInHyd);
		System.out.println("expAvailableStockInHyd        :"+expAvailableStockInHyd);
		
		
		tab(enterAvailableStocktxt);
		enterText(enterQuantitytxt, excelReader.getCellData(xlSheetName, 156, 5));
		tab(enterQuantitytxt);
		enterText(enterRatetxt, excelReader.getCellData(xlSheetName, 157, 5));
		tab(enterRatetxt);
		click(select6thRow_11thColumn);
		

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(binSearchTxt));

		String actBinpopupIsDisplaying = Boolean.toString(binSearchTxt.isDisplayed());
		String expBinpopupIsDisplaying = excelReader.getCellData(xlSheetName, 155, 6);
		excelReader.setCellData(xlfile, xlSheetName, 155, 7, actBinpopupIsDisplaying);

		System.out.println("Bin Popup isDisplaying  : " + actBinpopupIsDisplaying + "  Value Expected  "
				+ expBinpopupIsDisplaying);

		click(binSearchBtn);
		Thread.sleep(3000);
		click(binAutoAllocateBtn);
		Thread.sleep(2000);
		click(binOkBtn);
		
		
		

		// 7th Row

		click(select7thRow_1stColumn);
		enterWarehousettxt.sendKeys(Keys.CONTROL, "a");
		Thread.sleep(2000);
		enterWarehousettxt.sendKeys(Keys.BACK_SPACE);
		enterText(enterWarehousettxt, excelReader.getCellData(xlSheetName, 158, 5));
		Thread.sleep(2000);
		tab(enterWarehousettxt);
		enterText(enterItemttxt, excelReader.getCellData(xlSheetName, 159, 5));
		Thread.sleep(2000);
		tab(enterItemttxt);
		
		
		Thread.sleep(2000);
		
		String actAvailableStockInSec      =enterAvailableStocktxt.getAttribute("value");
		String expAvailableStockInSec	   =excelReader.getCellData(xlSheetName, 156, 6);
		excelReader.setCellData(xlfile, xlSheetName, 156, 7, actAvailableStockInSec);
		
		
		System.out.println("actAvailableStockInSec       :"+actAvailableStockInSec);
		System.out.println("expAvailableStockInSec       :"+expAvailableStockInSec);
		
		
		tab(enterAvailableStocktxt);
		enterText(enterQuantitytxt, excelReader.getCellData(xlSheetName, 160, 5));
		tab(enterQuantitytxt);
		enterText(enterRatetxt, excelReader.getCellData(xlSheetName, 161, 5));
		tab(enterRatetxt);
		click(select7thRow_11thColumn);
		
	
		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(binSearchTxt));

		String actBinpopupIsDisplaying1 = Boolean.toString(binSearchTxt.isDisplayed());
		String expBinpopupIsDisplaying1 = excelReader.getCellData(xlSheetName, 157, 6);
		excelReader.setCellData(xlfile, xlSheetName, 157, 7, actBinpopupIsDisplaying1);

		System.out.println("Bin Popup isDisplaying  : " + actBinpopupIsDisplaying1 + "  Value Expected  "
				+ expBinpopupIsDisplaying1);

		click(binSearchBtn);
		Thread.sleep(3000);
		click(binAutoAllocateBtn);
		Thread.sleep(2000);
		click(binOkBtn);
		
		Thread.sleep(2000);
		
		if(actAvailableStockInHyd.equalsIgnoreCase(expAvailableStockInHyd) && actBinpopupIsDisplaying.equalsIgnoreCase(expBinpopupIsDisplaying)
					&& actAvailableStockInSec.equalsIgnoreCase(expAvailableStockInSec) && actBinpopupIsDisplaying1.equalsIgnoreCase(expBinpopupIsDisplaying1)){
			excelReader.setCellData(xlfile, xlSheetName, 154, 8, resPass);

			return true;
		}
		else{
			excelReader.setCellData(xlfile, xlSheetName, 154, 8, resFail);
			return false;
			
		}
	}
		
	public boolean checkSavingSalesInvoiceVoucherRmaItemWithHYDWarehouse() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException{	
	
		excelReader=new ExcelReader(POJOUtility.getExcelPath());

		// 8th Row
		Thread.sleep(2000);

		click(select8thRow_1stColumn);
		enterWarehousettxt.sendKeys(Keys.CONTROL, "a");
		Thread.sleep(2000);
		enterWarehousettxt.sendKeys(Keys.BACK_SPACE);
		enterText(enterWarehousettxt, excelReader.getCellData(xlSheetName, 162, 5));
		Thread.sleep(2000);
		tab(enterWarehousettxt);
		enterText(enterItemttxt, excelReader.getCellData(xlSheetName, 163, 5));
		Thread.sleep(2000);
		tab(enterItemttxt);
		
		
		
		Thread.sleep(2000);
		
		String actAvailableStock4      =enterAvailableStocktxt.getAttribute("value");
		String expAvailableStock4	   =excelReader.getCellData(xlSheetName, 162, 6);
		excelReader.setCellData(xlfile, xlSheetName, 162, 7, actAvailableStock4);
		
		
		System.out.println("actAvailableStock4       :"+actAvailableStock4);
		System.out.println("expAvailableStock4       :"+expAvailableStock4);	
		
		
		tab(enterAvailableStocktxt);
		enterText(enterQuantitytxt, excelReader.getCellData(xlSheetName, 164, 5));
		tab(enterQuantitytxt);
		enterText(enterRatetxt, excelReader.getCellData(xlSheetName, 165, 5));
		tab(enterRatetxt);
		click(select8thRow_12thColumn);
		
		Thread.sleep(1000);
		//enterRmatxt.sendKeys(Keys.SPACE);
		Thread.sleep(1000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(rmaScreenTitle));

		String actRMAPopupIsDisplaying = Boolean.toString(rmaScreenTitle.isDisplayed());
		String expRMAPopupIsDisplaying = excelReader.getCellData(xlSheetName, 163, 6);
		excelReader.setCellData(xlfile, xlSheetName, 163, 7, actRMAPopupIsDisplaying);

		System.out.println("RMA Popup isDisplaying  : " + actRMAPopupIsDisplaying + "  Value Expected  "
				+ expRMAPopupIsDisplaying);
		
		click(rmaSearchGridField);
		Thread.sleep(1000);
		
		int count = rmaSerialNos.size();
		
		ArrayList<String> SearchRMA = new ArrayList<String>();
		
		for (int i = 0; i < count; i++) 
		{
			String data = rmaSerialNos.get(i).getText();
			SearchRMA.add(data);
			
			if (data.equalsIgnoreCase(excelReader.getCellData(xlSheetName, 166, 5)) ||
					data.equalsIgnoreCase(excelReader.getCellData(xlSheetName, 167, 5)) ||
					data.equalsIgnoreCase(excelReader.getCellData(xlSheetName, 168, 5)) || 
					data.equalsIgnoreCase(excelReader.getCellData(xlSheetName, 169, 5)) || 
					data.equalsIgnoreCase(excelReader.getCellData(xlSheetName, 170, 5))) 
			{
				rmaSerialNoCheckBoxes.get(i).click();
			}
		}
		
		String actSearchRMASerialNoList = SearchRMA.toString();
		String expSearchRMASerialNoList = excelReader.getCellData(xlSheetName, 164, 6); 
		excelReader.setCellData(xlfile, xlSheetName, 164, 7, actSearchRMASerialNoList);
		
		System.out.println("actSearchRMASerialNoList       :"+actSearchRMASerialNoList);
		System.out.println("expSearchRMASerialNoList       :"+expSearchRMASerialNoList);
		
		
		//click(searchRmaSelectAllGridCheckBox);
		click(searchRmaOkBtn);
		
		int serialcount	=rmaSerialNoList.size();
		ArrayList<String> rmaSerialnos = new ArrayList<String>();
		for (int i = 0; i < serialcount; i++) {
			String data=rmaSerialNoList.get(i).getText();
			rmaSerialnos.add(data);
		}
		
		String actRmaSerialNo 	=rmaSerialnos.toString();
		String expRmaSerialNo	=excelReader.getCellData(xlSheetName, 165, 6);
		excelReader.setCellData(xlfile, xlSheetName, 165, 7, actRmaSerialNo);
		
		System.out.println("actRmaSerialNo       :"+actRmaSerialNo);
		System.out.println("expRmaSerialNo       :"+expRmaSerialNo);
		
	
		click(RMAPopupOkBtn);	
		Thread.sleep(2000);
		tab(enterRmatxt);
		
				
    	Thread.sleep(3000);
    	
    	
    	String actual8throwAvgRate 				= select8thRow_AvgRate.getText();
		String exp8throwAvgRate 				= excelReader.getCellData(xlSheetName, 166, 6);
		excelReader.setCellData(xlfile, xlSheetName, 166, 7, actual8throwAvgRate);
		
		String actual8throwOverallAvgRate		= select8thRow_OverAllAvgRate.getText();
		String exp8throwoverallAvgRate			= excelReader.getCellData(xlSheetName, 167, 6);
		excelReader.setCellData(xlfile, xlSheetName, 167, 7, actual8throwOverallAvgRate);
		
		String act8throwAvailableStock  		=select8thRow_AvailaleStock.getText();
		String exp8throwAvailableStock 			=excelReader.getCellData(xlSheetName, 168, 6);
		excelReader.setCellData(xlfile, xlSheetName, 168, 7, act8throwAvailableStock);
		
		System.out.println("actual8throwAvgRate             :"+actual8throwAvgRate);
		System.out.println("exp8throwAvgRate                :"+exp8throwAvgRate);
		
		System.out.println("actual8throwOverallAvgRate      :"+actual8throwOverallAvgRate);
		System.out.println("exp8throwoverallAvgRate         :"+exp8throwoverallAvgRate);
		
		System.out.println("act8throwAvailableStock         :"+act8throwAvailableStock);
		System.out.println("exp8throwAvailableStock         :"+exp8throwAvailableStock);
		
		if(actAvailableStock4.equalsIgnoreCase(expAvailableStock4) && actRMAPopupIsDisplaying.equalsIgnoreCase(expRMAPopupIsDisplaying) 
				&& actSearchRMASerialNoList.equalsIgnoreCase(expSearchRMASerialNoList) && actRmaSerialNo.equalsIgnoreCase(expRmaSerialNo)
				&& actual8throwAvgRate.equalsIgnoreCase(exp8throwAvgRate) && actual8throwOverallAvgRate.equalsIgnoreCase(exp8throwoverallAvgRate)
				&& act8throwAvailableStock.equalsIgnoreCase(exp8throwAvailableStock)){
			
			excelReader.setCellData(xlfile, xlSheetName, 162, 8, resPass);
			return true;
			
		}else{
			
			excelReader.setCellData(xlfile, xlSheetName, 162, 8, resFail);
			return false;
		}
	}
	
	public boolean checkSavingSalesInvoiceVoucherRmaItemWithSECWarehouse() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException{

		excelReader=new ExcelReader(POJOUtility.getExcelPath());
		
		// 9th Row
		Thread.sleep(1000);
		//getAction().moveToElement(select9thRow_1stColumn).click().perform();
		click(select9thRow_1stColumn);
		enterWarehousettxt.sendKeys(Keys.CONTROL, "a");
		Thread.sleep(2000);
		enterWarehousettxt.sendKeys(Keys.BACK_SPACE);
		enterText(enterWarehousettxt, excelReader.getCellData(xlSheetName, 171, 5));
		Thread.sleep(2000);
		tab(enterWarehousettxt);
		enterText(enterItemttxt, excelReader.getCellData(xlSheetName, 172, 5));
		Thread.sleep(2000);
		tab(enterItemttxt);
		
		
		Thread.sleep(2000);
		
		String actAvailableStock5      =enterAvailableStocktxt.getAttribute("value");
		String expAvailableStock5	   =excelReader.getCellData(xlSheetName, 171, 6);
		excelReader.setCellData(xlfile, xlSheetName, 171, 7, actAvailableStock5);
		
		
		System.out.println("actAvailableStock5       :"+actAvailableStock5);
		System.out.println("expAvailableStock5       :"+actAvailableStock5);	
		
		
		
		tab(enterAvailableStocktxt);
		enterText(enterQuantitytxt, excelReader.getCellData(xlSheetName, 173, 5));
		tab(enterQuantitytxt);
		enterText(enterRatetxt, excelReader.getCellData(xlSheetName, 174, 5));
		tab(enterRatetxt);
		click(select9thRow_12thColumn);
		
		

		Thread.sleep(1000);
		//enterRmatxt.sendKeys(Keys.SPACE);
		Thread.sleep(1000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(rmaScreenTitle));

		String actRMAPopupIsDisplaying1 = Boolean.toString(rmaScreenTitle.isDisplayed());
		String expRMAPopupIsDisplaying1 = excelReader.getCellData(xlSheetName, 172, 6);
		excelReader.setCellData(xlfile, xlSheetName, 172, 7, actRMAPopupIsDisplaying1);

		System.out.println("RMA Popup isDisplaying  : " + actRMAPopupIsDisplaying1 + "  Value Expected  "
				+ expRMAPopupIsDisplaying1);
		
		click(rmaSearchGridField);
		Thread.sleep(1000);
		
		int count = rmaSerialNos.size();
		
		ArrayList<String> SearchRMA = new ArrayList<String>();
		
		for (int i = 0; i < count; i++) 
		{
			String data = rmaSerialNos.get(i).getText();
			SearchRMA.add(data);
			
			if (data.equalsIgnoreCase(excelReader.getCellData(xlSheetName, 175, 5)) || data.equalsIgnoreCase(excelReader.getCellData(xlSheetName, 176, 5)) 
					|| data.equalsIgnoreCase(excelReader.getCellData(xlSheetName, 177, 5)) || data.equalsIgnoreCase(excelReader.getCellData(xlSheetName, 178, 5)) || 
					data.equalsIgnoreCase(excelReader.getCellData(xlSheetName, 179, 5))) 
			{
				rmaSerialNoCheckBoxes.get(i).click();
			}
		}
		
		String actSearchRMASerialNoList = SearchRMA.toString();
		String expSearchRMASerialNoList = excelReader.getCellData(xlSheetName, 173, 6); 
		excelReader.setCellData(xlfile, xlSheetName, 173, 7, actSearchRMASerialNoList);
		
		System.out.println("actSearchRMASerialNoList       :"+actSearchRMASerialNoList);
		System.out.println("expSearchRMASerialNoList       :"+expSearchRMASerialNoList);
		
		
		//click(searchRmaSelectAllGridCheckBox);
		click(searchRmaOkBtn);
		
		int serialcount	=rmaSerialNoList.size();
		ArrayList<String> rmaSerialnos = new ArrayList<String>();
		for (int i = 0; i < serialcount; i++) {
			String data=rmaSerialNoList.get(i).getText();
			rmaSerialnos.add(data);
		}
		
		String actRmaSerialNo 	=rmaSerialnos.toString();
		String expRmaSerialNo	=excelReader.getCellData(xlSheetName, 174, 6);
		excelReader.setCellData(xlfile, xlSheetName, 174, 7, actRmaSerialNo);
		
		System.out.println("actRmaSerialNo       :"+actRmaSerialNo);
		System.out.println("expRmaSerialNo       :"+expRmaSerialNo);		
		
		
		click(RMAPopupOkBtn);	
		Thread.sleep(1000);
		tab(enterRmatxt);
		
		
		Thread.sleep(3000);
    	
    	
    	String actual9throwAvgRate 				= select9thRow_AvgRate.getText();
		String exp9throwAvgRate 				= excelReader.getCellData(xlSheetName, 175, 6);
		excelReader.setCellData(xlfile, xlSheetName, 175, 7, actual9throwAvgRate);
		
		String actual9throwOverallAvgRate		= select9thRow_OverAllAvgRate.getText();
		String exp9throwoverallAvgRate			= excelReader.getCellData(xlSheetName, 176, 6);
		excelReader.setCellData(xlfile, xlSheetName, 176, 7, actual9throwOverallAvgRate);
		
		String act9throwAvailableStock  		=select9thRow_AvailaleStock.getText();
		String exp9throwAvailableStock 			=excelReader.getCellData(xlSheetName, 177, 6);
		excelReader.setCellData(xlfile, xlSheetName, 177, 7, act9throwAvailableStock);
		
		System.out.println("actual9throwAvgRate             :"+actual9throwAvgRate);
		System.out.println("exp9throwAvgRate                :"+exp9throwAvgRate);
		
		System.out.println("actual9throwOverallAvgRate      :"+actual9throwOverallAvgRate);
		System.out.println("exp9throwoverallAvgRate         :"+exp9throwoverallAvgRate);
		
		System.out.println("act9throwAvailableStock         :"+act9throwAvailableStock);
		System.out.println("exp9throwAvailableStock         :"+exp9throwAvailableStock);
		
				
	
		Thread.sleep(2000);
		
		String docno=documentNumberTxt.getAttribute("value");
		click(voucherSaveBtn);
		
		Thread.sleep(2000);

	/*	HashSet<String> actMessage = new HashSet<String>();

		for (int i = 0; i < 2; i++) {
			String data = checkValidationMessage("");
			actMessage.add(data);
		}

		HashSet<String> expMessage = new HashSet<String>();

		expMessage.add("Voucher saved successfully : 35");
		expMessage.add("Saving in background."); */
		
		/*String actMessage = SavingInBackgroundTrial();

		HashSet<String> expMsg = new HashSet();

		expMsg.add(excelReader.getCellData(xlSheetName, 178, 6));
		expMsg.add(excelReader.getCellData(xlSheetName, 179, 6));
		excelReader.setCellData(xlfile, xlSheetName, 178, 7, actMessage);
		
		String expMessage = expMsg.toString();
		
		

		System.out.println("Actual Message    : " + actMessage);
		System.out.println("Expected Message  : " + expMessage);*/
		
		
		/*boolean actSaving=SavingInBackground(docno);
		boolean expSaving=true;*/
		
		String expMessage1= excelReader.getCellData(xlSheetName, 178, 6);
		String expMessage2= excelReader.getCellData(xlSheetName, 179, 6);
		
		String actMessage = checkValidationMessage(expMessage1);
		excelReader.setCellData(xlfile, xlSheetName, 178, 7, actMessage);
		
	   
	   
	   System.out.println("SavingMessage  :  "+actMessage +" Value Expected : "+expMessage1+" "+expMessage2);
		
		
		
		if(actMessage.startsWith(expMessage1) && actMessage.endsWith(expMessage2) && actAvailableStock5.equalsIgnoreCase(expAvailableStock5) && actRMAPopupIsDisplaying1==expRMAPopupIsDisplaying1 
				&& actSearchRMASerialNoList.equalsIgnoreCase(expSearchRMASerialNoList) && actRmaSerialNo.equalsIgnoreCase(expRmaSerialNo)
				&& actual9throwAvgRate.equalsIgnoreCase(exp9throwAvgRate) && actual9throwOverallAvgRate.equalsIgnoreCase(exp9throwoverallAvgRate)
				&& act9throwAvailableStock.equalsIgnoreCase(exp9throwAvailableStock)){	
			click(new_CloseBtn);			
			Thread.sleep(2000);
			click(voucherhomeCloseBtn);
			excelReader.setCellData(xlfile, xlSheetName, 171, 8, resPass);
			return true;
			
		} else {
			click(new_CloseBtn);			
			Thread.sleep(2000);
			click(voucherhomeCloseBtn);
			excelReader.setCellData(xlfile, xlSheetName, 171, 8, resFail);
			return false;

		}

	}
	
	public boolean checkStockLedgerReportForBrBatchItemAfterSavingSalesInvoiceVocher()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		
		
		excelReader=new ExcelReader(POJOUtility.getExcelPath());
		
		
		
		click(inventoryMenu);
		click(InventoryReportsMenu);
		click(InventoryStockLedgerMenu);
		Thread.sleep(3000);

		int rowcount = stockLedgerHometableRowCount.size();

		System.out.println(rowcount);

		for (int i = 0; i < rowcount; i++) {
			String actName = ledgerHometableItemNamesList.get(i).getText();

			System.out.println(actName);

			if (actName.equalsIgnoreCase(excelReader.getCellData(xlSheetName, 180, 5))) {
				stockLedgerHometableItemChkboxList.get(i).click();
			}
		}
		
		dropDown(sl_DateOptionDropdown, excelReader.getCellData(xlSheetName, 181, 5));		
		Thread.sleep(2000);
		click(sl_OkBtn);
		Thread.sleep(2000);
		click(stockLedgerLastBtn);
		Thread.sleep(2000);

		String actStockLedgerBrBatchIssuedrateTxt = getText(stockLedgerBrBatchAfterSavingSalesInvoiceIssuedrateTxt);
		String expStockLedgerBrBatchIssuedrateTxt = excelReader.getCellData(xlSheetName, 180, 6);
		excelReader.setCellData(xlfile, xlSheetName, 180, 7, actStockLedgerBrBatchIssuedrateTxt);
		
		
		
		
		System.out.println("actStockLedgerBrBatchIssuedrateTxt      :" + actStockLedgerBrBatchIssuedrateTxt);
		System.out.println("expStockLedgerBrBatchIssuedrateTxt      :" + expStockLedgerBrBatchIssuedrateTxt);
		
		
	

		if (actStockLedgerBrBatchIssuedrateTxt.equalsIgnoreCase(expStockLedgerBrBatchIssuedrateTxt)) {

			click(report_CloseBtn);
			excelReader.setCellData(xlfile, xlSheetName, 180, 8, resPass);

			return true;
		} else {
			click(report_CloseBtn);
			excelReader.setCellData(xlfile, xlSheetName, 180, 8, resFail);
			return false;
		}
	}
	
	public boolean checkFinanacialLedgerReportFifoItemAfterSavingSalesInvoiceVocher() // NOt Implemented
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {

		excelReader=new ExcelReader(POJOUtility.getExcelPath());
		Thread.sleep(3000);

		if (fifoItemCheckBox.isSelected() == false) {
			click(fifoItemCheckBox);
		}

		if (brBatchItemCheckBox.isSelected() == true) {
			click(brBatchItemCheckBox);
		}

		if (rmaItemCheckBox.isSelected() == true) {
			click(rmaItemCheckBox);

		}

		
		dropDown(sl_DateOptionDropdown, "1");	
		Thread.sleep(2000);
		click(sl_OkBtn);
		Thread.sleep(2000);
		click(stockLedgerLastBtn);
		Thread.sleep(2000);

		String actStockLedgerFifoItemIssuedrateTxt = getText(stockLedgerFifoItemAfterSavingSalesInvoiceIssuedrateTxt);
		String expStockLedgerFifoItemIssuedrateTxt = "1,000.0000";
		
		
		String actstockLedgerFifoItemBalanceQty    = getText(stockLedgerFifoItemBalanceQtyAfterSavingPurchase);
		String expstockLedgerFifoItemBalanceQty    = "13.0000";

		String actstockLedgerFifoItemBalanceValue  = getText(stockLedgerFifoItemBalanceValueAfterSavingPurchase);
		String expstockLedgerFifoItemBalanceValue  = "30,041.0807";

		String actstockLedgerFifoItemAvgRate       = getText(stockLedgerFifoItemAvgRateAfterSavingPurchase);
		String expstockLedgerFifoItemAvgRate       = "1,201.6432";
		
		System.out.println("actStockLedgerFifoItemIssuedrateTxt      :" + actStockLedgerFifoItemIssuedrateTxt);
		System.out.println("expStockLedgerFifoItemIssuedrateTxt      :" + expStockLedgerFifoItemIssuedrateTxt);		

		System.out.println("actstockLedgerFifoItemBalanceQty         :" + actstockLedgerFifoItemBalanceQty);
		System.out.println("expstockLedgerFifoItemBalanceQty         :" + expstockLedgerFifoItemBalanceQty);

		System.out.println("actstockLedgerFifoItemBalanceValue       :" + actstockLedgerFifoItemBalanceValue);
		System.out.println("expstockLedgerFifoItemBalanceValue       :" + expstockLedgerFifoItemBalanceValue);

		System.out.println("actstockLedgerFifoItemAvgRate            :" + actstockLedgerFifoItemAvgRate);
		System.out.println("expstockLedgerFifoItemAvgRate            :" + expstockLedgerFifoItemAvgRate);

		if (actstockLedgerFifoItemBalanceQty.equalsIgnoreCase(expstockLedgerFifoItemBalanceQty)
				&& actstockLedgerFifoItemBalanceValue.equalsIgnoreCase(expstockLedgerFifoItemBalanceValue)
				&& actstockLedgerFifoItemAvgRate.equalsIgnoreCase(expstockLedgerFifoItemAvgRate)) {

			click(report_CloseBtn);

			return true;
		} else {
			click(report_CloseBtn);

			return false;
		}
	}

	public boolean checkStockLedgerReportForRmaItemAfterSavingSalesInvoiceVocher()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		
		excelReader=new ExcelReader(POJOUtility.getExcelPath());
		
		Thread.sleep(3000);

		if (fifoItemCheckBox.isSelected() == true) {
			click(fifoItemCheckBox);

		}

		if (brBatchItemCheckBox.isSelected() == true) {
			click(brBatchItemCheckBox);
		}

		if (rmaItemCheckBox.isSelected() == false) {
			click(rmaItemCheckBox);

		}

	
		dropDown(sl_DateOptionDropdown, excelReader.getCellData(xlSheetName, 182, 5));
		Thread.sleep(2000);
		click(sl_OkBtn);
		Thread.sleep(2000);
		click(stockLedgerLastBtn);
		Thread.sleep(2000);
		
		
		String actStockLedgerRmaItemIssuedrateTxt = getText(stockLedgerRmaItemAfterSavingSalesInvoiceIssuedrateTxt);
		String expStockLedgerRmaItemIssuedrateTxt = excelReader.getCellData(xlSheetName, 182, 6);
		excelReader.setCellData(xlfile, xlSheetName, 182, 7, actStockLedgerRmaItemIssuedrateTxt);

		
		System.out.println("actStockLedgerRmaItemIssuedrateTxt      :" + actStockLedgerRmaItemIssuedrateTxt);
		System.out.println("expStockLedgerRmaItemIssuedrateTxt      :" + expStockLedgerRmaItemIssuedrateTxt);
		

	
		if (actStockLedgerRmaItemIssuedrateTxt.equalsIgnoreCase(expStockLedgerRmaItemIssuedrateTxt)) {

			click(report_CloseBtn);
			Thread.sleep(2000);
			click(sl_CloseBtn);
			excelReader.setCellData(xlfile, xlSheetName, 182, 8, resPass);
			return true;
		} else {
			click(report_CloseBtn);
			Thread.sleep(2000);
			click(sl_CloseBtn);
			excelReader.setCellData(xlfile, xlSheetName, 182, 8, resFail);
			return false;
		}
	}
	
	
	
	
	public boolean checkLoginToSUUserAndSavingStockTransferVocher1() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException
	{
		excelReader=new ExcelReader(POJOUtility.getExcelPath());
		
		/*Thread.sleep(3000);
		click(userNameDisplayLogo);
		Thread.sleep(1000);
		click(logoutOption);		
		Thread.sleep(3000);
		
		String unamelt=excelReader.getCellData(xlSheetName, 183, 5);

		String pawslt=excelReader.getCellData(xlSheetName, 184, 5);
		
		LoginPage.enterUserName(unamelt);	     
        LoginPage.enterPassword(pawslt);
        
		
		 Select company = new Select(companyDropDownList);
		 company.selectByValue(excelReader.getCellData(xlSheetName, 185, 5));
		 Thread.sleep(2000);
		 
  
        LoginPage.clickOnSignInBtn();  */
		
		reLogin(excelReader.getCellData(xlSheetName, 183, 5), excelReader.getCellData(xlSheetName, 184, 5));
        
        Thread.sleep(3000);
        
        System.out.println("Signing");
        
        // Login User Name is Verify
        
        getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(userNameDisplay));
		String actUserInfo                      = userNameDisplay.getText();
		String expUserInfo                      = excelReader.getCellData(xlSheetName, 183, 6);	
		excelReader.setCellData(xlfile, xlSheetName, 183, 7, actUserInfo);

		System.out.println("Login With Valid User and Check User Name Display Value Actual : ." + actUserInfo + ". Value Expected : " + expUserInfo);
		
		
		// Login Company Name is Verify
		Thread.sleep(1000);
		click(companyLogo);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(companyName));
		String actGetLoginCompanyNameInformation = companyName.getText();
		String actGetLoginCompanyName            = actGetLoginCompanyNameInformation.substring(0, 19);
		String expGetLoginCompanyName            = excelReader.getCellData(xlSheetName, 184, 6); 
		excelReader.setCellData(xlfile, xlSheetName, 184, 7, actGetLoginCompanyName);
		companyLogo.click();
		
		System.out.println("Company Name Display Value Actual                : ." + actGetLoginCompanyName +  ". Value Expected : " + expGetLoginCompanyName);



	Thread.sleep(2000);
	click(inventoryMenu);
	click(inventoryTransactionsMenu);
	click(inventoryTransactionsStockMenu);
	click(inventoryTransactionsStockMenuStockTransfersMenu);	
	click(newBtn);
	
	checkValidationMessage("Screen opened");
	
	click(stockTransferDatetxt);
	stockTransferDatetxt.sendKeys(Keys.CONTROL, "a");
	Thread.sleep(2000);
	stockTransferDatetxt.sendKeys(Keys.BACK_SPACE);
	enterText(stockTransferDatetxt, excelReader.getCellData(xlSheetName, 186, 5));
	tab(stockTransferDatetxt);
	tab(stockTransferIssuestxt);
	Thread.sleep(1000);
	enterText(stockTransferWarehousetxt, excelReader.getCellData(xlSheetName, 187, 5));
	Thread.sleep(1000);
	tab(stockTransferWarehousetxt);
	Thread.sleep(2000);
	
	
	click(select1stRow_1stColumn);
	enterText(enterWarehousettxt2, excelReader.getCellData(xlSheetName, 188, 5));
	Thread.sleep(2000);
	
	

	tab(enterWarehousettxt2);
	enterText(enterItemttxt, excelReader.getCellData(xlSheetName, 189, 5));
	Thread.sleep(2000);
	tab(enterItemttxt);
	
	

	
	enterText(enterQuantitytxt, excelReader.getCellData(xlSheetName, 190, 5));
	tab(enterQuantitytxt);
	enterText(enterRatetxt, excelReader.getCellData(xlSheetName, 191, 5));
	tab(enterRatetxt);
	Thread.sleep(2000);
	
	
	String actRow1AvgRate				=select1stRow_AvgRate.getText();
	String expRow1AvgRate				=excelReader.getCellData(xlSheetName, 185, 6);
	excelReader.setCellData(xlfile, xlSheetName, 185, 7, actRow1AvgRate);
	
	String actRow1OverAllAvgRate		=select1stRow_OverAllAvgRate.getText();
	String expRow1OverAllAvgRate		=excelReader.getCellData(xlSheetName, 186, 6);
	excelReader.setCellData(xlfile, xlSheetName, 186, 7, actRow1OverAllAvgRate);
	
	
	
	System.out.println("actRow1AvgRate             :"+actRow1AvgRate);
	System.out.println("expRow1AvgRate             :"+expRow1AvgRate);
	
	System.out.println("actRow1OverAllAvgRate      :"+actRow1OverAllAvgRate);
	System.out.println("expRow1OverAllAvgRate      :"+expRow1OverAllAvgRate);
	
	
	click(select1stRow_8thColumn);
	
	Thread.sleep(2000);
	
	getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(batchPickOnFIFOIcon));
	
	String actBatchpopupIsDisplaying = Boolean.toString(batchPickOnFIFOIcon.isDisplayed());
	String expBatchpopupIsDisplaying = excelReader.getCellData(xlSheetName, 187, 6);
	excelReader.setCellData(xlfile, xlSheetName, 187, 7, actBatchpopupIsDisplaying);
	
	System.out.println("Batch Popup isDisplaying  : "+actBatchpopupIsDisplaying+"  Value Expected  "+expBatchpopupIsDisplaying);
	click(batchPickOnFIFOIcon);
	Thread.sleep(1000);
	click(batchOkIcon);
	
	Thread.sleep(2000);
	
	
	String actRow1AvgRate1				=select1stRow_AvgRate.getText();
	String expRow1AvgRate1				=excelReader.getCellData(xlSheetName, 188, 6);
	excelReader.setCellData(xlfile, xlSheetName, 188, 7, actRow1AvgRate);
	
	
	String actRow1OverAllAvgRate1		=select1stRow_OverAllAvgRate.getText();
	String expRow1OverAllAvgRate1		=excelReader.getCellData(xlSheetName, 189, 6);
	excelReader.setCellData(xlfile, xlSheetName, 189, 7, actRow1OverAllAvgRate1);
	
	String actRow1BatchText				=select1stRow_BatchText.getText();
	String expRow1BatchText				=excelReader.getCellData(xlSheetName, 190, 6);
	excelReader.setCellData(xlfile, xlSheetName, 190, 7, actRow1BatchText);
	
	
	
	System.out.println("actRow1AvgRate1             :"+actRow1AvgRate1);
	System.out.println("expRow1AvgRate1             :"+expRow1AvgRate1);
	
	System.out.println("actRow1OverAllAvgRate1      :"+actRow1OverAllAvgRate1);
	System.out.println("expRow1OverAllAvgRate1      :"+expRow1OverAllAvgRate1);
	
	System.out.println("actRow1BatchText            :"+actRow1BatchText);
	System.out.println("expRow1BatchText            :"+expRow1BatchText);



	click(select2ndRow_1stColumn);
	
	enterWarehousettxt2.sendKeys(Keys.CONTROL, "a");
	Thread.sleep(2000);
	enterWarehousettxt2.sendKeys(Keys.BACK_SPACE);	
	enterText(enterWarehousettxt2, excelReader.getCellData(xlSheetName, 192, 5));
	Thread.sleep(2000);
	tab(enterWarehousettxt2);
	enterText(enterItemttxt, excelReader.getCellData(xlSheetName, 193, 5));
	Thread.sleep(2000);
	tab(enterItemttxt);
	
	

	
	enterText(enterQuantitytxt, excelReader.getCellData(xlSheetName, 194, 5));
	tab(enterQuantitytxt);
	enterText(enterRatetxt, excelReader.getCellData(xlSheetName, 195, 5));
	tab(enterRatetxt);
	Thread.sleep(2000);	
	
	click(select2ndRow_11thColumn);
	
	Thread.sleep(1000);
	//enterRmatxt.sendKeys(Keys.SPACE);
	Thread.sleep(1000);

	getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(rmaScreenTitle));

	String actRMAPopupIsDisplaying1 = Boolean.toString(rmaScreenTitle.isDisplayed());
	String expRMAPopupIsDisplaying1 = excelReader.getCellData(xlSheetName, 191, 6);
	excelReader.setCellData(xlfile, xlSheetName, 191, 7, actRMAPopupIsDisplaying1);

	System.out.println("RMA Popup isDisplaying  : " + actRMAPopupIsDisplaying1 + "  Value Expected  "
			+ expRMAPopupIsDisplaying1);
	
	click(rmaSearchGridField);
	Thread.sleep(1000);
	
	int count = rmaSerialNos.size();
	
	ArrayList<String> SearchRMA = new ArrayList<String>();
	
	for (int i = 0; i < count; i++) 
	{
		String data = rmaSerialNos.get(i).getText();
		SearchRMA.add(data);
		
		if (data.equalsIgnoreCase(excelReader.getCellData(xlSheetName, 196, 5)) ||
				data.equalsIgnoreCase(excelReader.getCellData(xlSheetName, 197, 5)) ||
				data.equalsIgnoreCase(excelReader.getCellData(xlSheetName, 198, 5))) 
		{
			rmaSerialNoCheckBoxes.get(i).click();
		}
	}
	
	String actSearchRMASerialNoList = SearchRMA.toString();
	String expSearchRMASerialNoList = excelReader.getCellData(xlSheetName, 192, 6); 
	excelReader.setCellData(xlfile, xlSheetName, 192, 7, actSearchRMASerialNoList);
	
	System.out.println("actSearchRMASerialNoList       :"+actSearchRMASerialNoList);
	System.out.println("expSearchRMASerialNoList       :"+expSearchRMASerialNoList);
	
	
	//click(searchRmaSelectAllGridCheckBox);
	click(searchRmaOkBtn);
	
	int serialcount	=rmaSerialNoList.size();
	ArrayList<String> rmaSerialnos = new ArrayList<String>();
	for (int i = 0; i < serialcount; i++) {
		String data=rmaSerialNoList.get(i).getText();
		rmaSerialnos.add(data);
	}
	
	String actRmaSerialNo 	=rmaSerialnos.toString();
	String expRmaSerialNo	=excelReader.getCellData(xlSheetName, 193, 6);
	excelReader.setCellData(xlfile, xlSheetName, 193, 7, actRmaSerialNo);
	
	System.out.println("actRmaSerialNo       :"+actRmaSerialNo);
	System.out.println("expRmaSerialNo       :"+expRmaSerialNo);
	
	
	click(RMAPopupOkBtn);	
	Thread.sleep(1000);
	tab(enterRmatxt);
	
	
	Thread.sleep(3000);
	
	
	String actRow2AvgRate				=select2ndRow_AvgRate.getText();
	String expRow2AvgRate				=excelReader.getCellData(xlSheetName, 194, 6);
	excelReader.setCellData(xlfile, xlSheetName, 194, 7, actRow2AvgRate);
	
	String actRow2OverAllAvgRate		=select2ndRow_OverAllAvgRate.getText();
	String expRow2OverAllAvgRate		=excelReader.getCellData(xlSheetName, 195, 6);
	excelReader.setCellData(xlfile, xlSheetName, 195, 7, actRow2OverAllAvgRate);
	
	
	
	System.out.println("actRow2AvgRate             		  :"+actRow2AvgRate);
	System.out.println("expRow2AvgRate             		  :"+expRow2AvgRate);
	
	System.out.println("actRow2OverAllAvgRate             :"+actRow2OverAllAvgRate);
	System.out.println("expRow2OverAllAvgRate             :"+expRow2OverAllAvgRate);
	
	// 3rd Row
	
	click(select3rdRow_1stColumn);
	
	enterWarehousettxt2.sendKeys(Keys.CONTROL, "a");
	Thread.sleep(2000);
	enterWarehousettxt2.sendKeys(Keys.BACK_SPACE);	
	enterWarehousettxt2.sendKeys(Keys.SPACE);
	Thread.sleep(1000);
	enterText(enterWarehousettxt2, excelReader.getCellData(xlSheetName, 199, 5));
	Thread.sleep(2000);
	tab(enterWarehousettxt2);
	enterText(enterItemttxt, excelReader.getCellData(xlSheetName, 200, 5));
	Thread.sleep(2000);
	tab(enterItemttxt);
	
	

	
	enterText(enterQuantitytxt, excelReader.getCellData(xlSheetName, 201, 5));
	tab(enterQuantitytxt);
	enterText(enterRatetxt, excelReader.getCellData(xlSheetName, 202, 5));
	tab(enterRatetxt);
	Thread.sleep(2000);	
	
	click(select3rdRow_9thColumn);
	Thread.sleep(2000);

	
	getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(binSearchTxt));

	String actBinpopupIsDisplaying = Boolean.toString(binSearchTxt.isDisplayed());
	String expBinpopupIsDisplaying = excelReader.getCellData(xlSheetName, 196, 6);
	excelReader.setCellData(xlfile, xlSheetName, 196, 7, actBinpopupIsDisplaying);

	System.out.println("Bin Popup isDisplaying  : " + actBinpopupIsDisplaying + "  Value Expected  "
			+ expBinpopupIsDisplaying);

	click(binSearchBtn);
	Thread.sleep(3000);
	click(binAutoAllocateBtn);
	Thread.sleep(2000);
	click(binOkBtn);
	
	Thread.sleep(2000);
	
	tab(bin1Txt);
	
	//bin2Txt.sendKeys(Keys.SPACE);
	Thread.sleep(2000);
	
	getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(binSearchTxt));

	String actBinpopupIsDisplaying1 = Boolean.toString(binSearchTxt.isDisplayed());
	String expBinpopupIsDisplaying1 = excelReader.getCellData(xlSheetName, 197, 6);
	excelReader.setCellData(xlfile, xlSheetName, 197, 7, actBinpopupIsDisplaying1);

	System.out.println("Bin Popup isDisplaying  : " + actBinpopupIsDisplaying1 + "  Value Expected  "
			+ expBinpopupIsDisplaying1);

		click(binSearchBtn);
		Thread.sleep(3000);
		click(binAutoAllocateBtn);
		Thread.sleep(2000);
		click(binOkBtn);

		Thread.sleep(2000);
		
		String docno=documentNumberTxt.getAttribute("value");
		click(voucherSaveBtn);

		Thread.sleep(2000);
/*
		String actMessage = SavingInBackgroundTrial();

		HashSet<String> expMsg = new HashSet();

		expMsg.add(excelReader.getCellData(xlSheetName, 198, 6));
		expMsg.add(excelReader.getCellData(xlSheetName, 199, 6));
		
		excelReader.setCellData(xlfile, xlSheetName, 198, 7, actMessage);

		String expMessage = expMsg.toString();

		System.out.println("Actual Message    : " + actMessage);
		System.out.println("Expected Message  : " + expMessage);*/
		
		/*boolean actSaving=SavingInBackground(docno);
		boolean expSaving=true;*/
		
		String expMessage1= excelReader.getCellData(xlSheetName, 198, 6);
		String expMessage2= excelReader.getCellData(xlSheetName, 199, 6);
		
		String actMessage = checkValidationMessage(expMessage1);
		excelReader.setCellData(xlfile, xlSheetName, 198, 7, actMessage);
		
	   
	   
	   System.out.println("SavingMessage  :  "+actMessage +" Value Expected : "+expMessage1+" "+expMessage2);

	if(actMessage.startsWith(expMessage1) && actMessage.endsWith(expMessage2) && actRow1AvgRate.equalsIgnoreCase(expRow1AvgRate) && actRMAPopupIsDisplaying1.equalsIgnoreCase(expRMAPopupIsDisplaying1) 
			&& actRow1OverAllAvgRate.equalsIgnoreCase(expRow1OverAllAvgRate) && actRow1AvgRate1.equalsIgnoreCase(expRow1AvgRate1)
			&& actRow1OverAllAvgRate1.equalsIgnoreCase(expRow1OverAllAvgRate1) && actRow1BatchText.equalsIgnoreCase(expRow1BatchText)
			&& actSearchRMASerialNoList.equalsIgnoreCase(expSearchRMASerialNoList) && actRmaSerialNo.equalsIgnoreCase(expRmaSerialNo)
			&& actRow2AvgRate.equalsIgnoreCase(expRow2AvgRate) && actRow2OverAllAvgRate.equalsIgnoreCase(expRow2OverAllAvgRate)){	
		click(new_CloseBtn);			
		Thread.sleep(2000);
		click(voucherhomeCloseBtn);
		excelReader.setCellData(xlfile, xlSheetName, 183, 8, resPass);
		return true;
		
	} else {
		click(new_CloseBtn);			
		Thread.sleep(2000);
		click(voucherhomeCloseBtn);
		excelReader.setCellData(xlfile, xlSheetName, 183, 8, resFail);
		return false;

	  }
	}	
	
	
	public boolean validteItemQueryStocksInBatchItemAfterSavingStockTransferVoucher() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {

		excelReader=new ExcelReader(POJOUtility.getExcelPath());
		
		click(inventoryMenu);
		click(InventoryReportsMenu);
		click(itemqueryMenu);
		click(itemTxt);
		Thread.sleep(2000);
		enterText(itemTxt, excelReader.getCellData(xlSheetName, 203, 5));
		Thread.sleep(3000);
		click(getStockBtn);
		Thread.sleep(3000);

		String actlOpenStockQuantity = getText(openStockQuantityTxt);
		String expOpenStockQuantity  = excelReader.getCellData(xlSheetName, 203, 6);
		excelReader.setCellData(xlfile, xlSheetName, 203, 7, actlOpenStockQuantity);

		String actlCurrentStock      = getText(currentStockTxt);
		String expCurrentStock       = excelReader.getCellData(xlSheetName, 204, 6);
		excelReader.setCellData(xlfile, xlSheetName, 204, 7, actlCurrentStock);

		String actlavgStockRate      = getText(avgStockRateTxt);
		String expavgStockRate       = excelReader.getCellData(xlSheetName, 205, 6);
		excelReader.setCellData(xlfile, xlSheetName, 205, 7, actlavgStockRate);

		String actlstockValueTxt 	 = getText(stockValueTxt);
		String expstockValueTxt		 = excelReader.getCellData(xlSheetName, 206, 6);
		excelReader.setCellData(xlfile, xlSheetName, 206, 7, actlstockValueTxt);

		System.out.println("=====Stocks In Batch Items======");

		System.out.println("actlOpenStockQuantity      :" + actlOpenStockQuantity);
		System.out.println("expOpenStockQuantity       :" + expOpenStockQuantity);

		System.out.println("actlCurrentStock           :" + actlCurrentStock);
		System.out.println("expCurrentStock            :" + expCurrentStock);

		System.out.println("actlavgStockRate           :" + actlavgStockRate);
		System.out.println("expavgStockRate            :" + expavgStockRate);

		System.out.println("actlstockValueTxt          :" + actlstockValueTxt);
		System.out.println("expstockValueTxt           :" + expstockValueTxt);

		if (actlOpenStockQuantity.equalsIgnoreCase(expOpenStockQuantity)
				&& actlCurrentStock.equalsIgnoreCase(expCurrentStock)
				&& actlavgStockRate.equalsIgnoreCase(expavgStockRate)
				&& actlstockValueTxt.equalsIgnoreCase(expstockValueTxt)) {
			
			excelReader.setCellData(xlfile, xlSheetName, 203, 8, resPass);
			return true;
		} else {
			
			excelReader.setCellData(xlfile, xlSheetName, 203, 8, resFail);
			return false;
		}

	}

	public boolean validteItemQueryStocksInFifoItemAfterSavingStockTransferVoucher() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {

		excelReader=new ExcelReader(POJOUtility.getExcelPath());
		
		click(itemTxt);
		Thread.sleep(2000);
		itemTxt.sendKeys(Keys.CONTROL, "a");
		Thread.sleep(2000);
		itemTxt.sendKeys(Keys.BACK_SPACE);
		enterText(itemTxt, excelReader.getCellData(xlSheetName, 207, 5));
		Thread.sleep(3000);
		click(getStockBtn);
		Thread.sleep(3000);

		String actlOpenStockQuantity = getText(openStockQuantityTxt);
		String expOpenStockQuantity  = excelReader.getCellData(xlSheetName, 207, 6);
		excelReader.setCellData(xlfile, xlSheetName, 207, 7, actlOpenStockQuantity);

		String actlCurrentStock      = getText(currentStockTxt);
		String expCurrentStock       = excelReader.getCellData(xlSheetName, 208, 6);
		excelReader.setCellData(xlfile, xlSheetName, 208, 7, actlCurrentStock);

		String actlavgStockRate      = getText(avgStockRateTxt);
		String expavgStockRate       = excelReader.getCellData(xlSheetName, 209, 6);
		excelReader.setCellData(xlfile, xlSheetName, 209, 7, actlavgStockRate);

		String actlstockValueTxt     = getText(stockValueTxt);
		String expstockValueTxt      = excelReader.getCellData(xlSheetName, 210, 6);
		excelReader.setCellData(xlfile, xlSheetName, 210, 7, actlstockValueTxt);

		System.out.println("=====Stocks In FIFO Items======");

		System.out.println("actlOpenStockQuantity      :" + actlOpenStockQuantity);
		System.out.println("expOpenStockQuantity       :" + expOpenStockQuantity);

		System.out.println("actlCurrentStock           :" + actlCurrentStock);
		System.out.println("expCurrentStock            :" + expCurrentStock);

		System.out.println("actlavgStockRate           :" + actlavgStockRate);
		System.out.println("expavgStockRate            :" + expavgStockRate);

		System.out.println("actlstockValueTxt          :" + actlstockValueTxt);
		System.out.println("expstockValueTxt           :" + expstockValueTxt);

		if (actlOpenStockQuantity.equalsIgnoreCase(expOpenStockQuantity)
				&& actlCurrentStock.equalsIgnoreCase(expCurrentStock)
				&& actlavgStockRate.equalsIgnoreCase(expavgStockRate)
				&& actlstockValueTxt.equalsIgnoreCase(expstockValueTxt)) {
			
			excelReader.setCellData(xlfile, xlSheetName, 207, 8, resPass);
			return true;
		} else {
			
			excelReader.setCellData(xlfile, xlSheetName, 207, 8, resFail);
			return false;
		}

	}

	public boolean validteItemQueryStocksInWAItemAfterSavingStockTransferVoucher() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {

		excelReader=new ExcelReader(POJOUtility.getExcelPath());
		
		click(itemTxt);
		Thread.sleep(2000);
		itemTxt.sendKeys(Keys.CONTROL, "a");
		Thread.sleep(2000);
		itemTxt.sendKeys(Keys.BACK_SPACE);
		enterText(itemTxt, excelReader.getCellData(xlSheetName, 211, 5));
		Thread.sleep(3000);
		click(getStockBtn);
		Thread.sleep(3000);

		String actlOpenStockQuantity = getText(openStockQuantityTxt);
		String expOpenStockQuantity  = excelReader.getCellData(xlSheetName, 211, 6);
		excelReader.setCellData(xlfile, xlSheetName, 211, 7, actlOpenStockQuantity);

		String actlCurrentStock      = getText(currentStockTxt);
		String expCurrentStock       = excelReader.getCellData(xlSheetName, 212, 6);
		excelReader.setCellData(xlfile, xlSheetName, 212, 7, actlCurrentStock);

		String actlavgStockRate      = getText(avgStockRateTxt);
		String expavgStockRate       = excelReader.getCellData(xlSheetName, 213, 6);
		excelReader.setCellData(xlfile, xlSheetName, 213, 7, actlavgStockRate);

		String actlstockValueTxt     = getText(stockValueTxt);
		String expstockValueTxt      = excelReader.getCellData(xlSheetName, 214, 6);
		excelReader.setCellData(xlfile, xlSheetName, 214, 7, actlstockValueTxt);

		ScrollToElement(balanceByWarehouseBtn);
		click(balanceByWarehouseBtn);

		Thread.sleep(3000);

		int count = row1BalanceByWareHouseList.size();

		ArrayList<String> warehouseRow1 = new ArrayList<String>();

		for (int i = 0; i < count; i++) {
			String data = row1BalanceByWareHouseList.get(i).getText();
			warehouseRow1.add(data);
		}

		String actwarehouseRow1List = warehouseRow1.toString();
		String expWarehouseRow1List = excelReader.getCellData(xlSheetName, 215, 6);
		excelReader.setCellData(xlfile, xlSheetName, 215, 7, actwarehouseRow1List);

		int count1 = row2BalanceByWareHouseList.size();

		ArrayList<String> warehouseRow2 = new ArrayList<String>();

		for (int i = 0; i < count1; i++) {
			String data = row2BalanceByWareHouseList.get(i).getText();
			warehouseRow2.add(data);
		}

		String actwarehouseRow2List = warehouseRow2.toString();
		String expWarehouseRow2List = excelReader.getCellData(xlSheetName, 216, 6);
		excelReader.setCellData(xlfile, xlSheetName, 216, 7, actwarehouseRow2List);

		int count2 = row3BalanceByWareHouseList.size();

		ArrayList<String> warehouseRow3 = new ArrayList<String>();

		for (int i = 0; i < count2; i++) {
			String data = row3BalanceByWareHouseList.get(i).getText();
			warehouseRow3.add(data);
		}

		String actwarehouseRow3List = warehouseRow3.toString();
		String expWarehouseRow3List = excelReader.getCellData(xlSheetName, 217, 6);
		excelReader.setCellData(xlfile, xlSheetName, 217, 7, actwarehouseRow3List);
		
		

		System.out.println("=====Stocks In WA Items======");

		System.out.println("actlOpenStockQuantity      :" + actlOpenStockQuantity);
		System.out.println("expOpenStockQuantity       :" + expOpenStockQuantity);

		System.out.println("actlCurrentStock           :" + actlCurrentStock);
		System.out.println("expCurrentStock            :" + expCurrentStock);

		System.out.println("actlavgStockRate           :" + actlavgStockRate);
		System.out.println("expavgStockRate            :" + expavgStockRate);

		System.out.println("actlstockValueTxt          :" + actlstockValueTxt);
		System.out.println("expstockValueTxt           :" + expstockValueTxt);

		System.out.println("===========Balance By Warehouse=======");

		System.out.println(actwarehouseRow1List);
		System.out.println(expWarehouseRow1List);

		System.out.println(actwarehouseRow2List);
		System.out.println(expWarehouseRow2List);

		System.out.println(actwarehouseRow3List);
		System.out.println(expWarehouseRow3List);

		
	
		
		
		
		if (actlOpenStockQuantity.equalsIgnoreCase(expOpenStockQuantity)
				&& actlCurrentStock.equalsIgnoreCase(expCurrentStock)
				&& actlavgStockRate.equalsIgnoreCase(expavgStockRate)
				&& actlstockValueTxt.equalsIgnoreCase(expstockValueTxt)
				&& actwarehouseRow1List.equalsIgnoreCase(expWarehouseRow1List)
				&& actwarehouseRow2List.equalsIgnoreCase(expWarehouseRow2List)
				&& actwarehouseRow3List.equalsIgnoreCase(expWarehouseRow3List))
		{
			
			excelReader.setCellData(xlfile, xlSheetName, 211, 8, resPass);
			return true;
		} else {
			
			excelReader.setCellData(xlfile, xlSheetName, 211, 8, resFail);
			return false;
		}

	}
	
	
	
	


	
	
	
	public boolean checkStockLedgerReportForBrBatchItemAfterStockTransferVoucher()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {

		excelReader=new ExcelReader(POJOUtility.getExcelPath());
		
		click(inventoryMenu);
		click(InventoryReportsMenu);
		click(InventoryStockLedgerMenu);
		Thread.sleep(3000);

		int rowcount = stockLedgerHometableRowCount.size();

		System.out.println(rowcount);

		for (int i = 0; i < rowcount; i++) {
			String actName = ledgerHometableItemNamesList.get(i).getText();

			System.out.println(actName);

			if (actName.equalsIgnoreCase(excelReader.getCellData(xlSheetName, 218, 5))) {
				stockLedgerHometableItemChkboxList.get(i).click();
			}
		}
		
		dropDown(sl_DateOptionDropdown, excelReader.getCellData(xlSheetName, 219, 5));		
		Thread.sleep(2000);
		click(sl_OkBtn);
		Thread.sleep(2000);
		click(stockLedgerLastBtn);
		Thread.sleep(2000);

		String actStockLedgerBrBatchReceivedRate 	= getText(stockLedgerBrBatchReceivedRateAfterSavingStockTransfer);
		String expStockLedgerBrBatchReceivedRate	= excelReader.getCellData(xlSheetName, 218, 6);
		excelReader.setCellData(xlfile, xlSheetName, 218, 7, actStockLedgerBrBatchReceivedRate);
		
		String actStockLedgerBrBatchIssuedRate 		= getText(stockLedgerBrBatchIssuedRateAfterSavingStockTransfer);
		String expStockLedgerBrBatchIssuedRate		= excelReader.getCellData(xlSheetName, 219, 6);
		excelReader.setCellData(xlfile, xlSheetName, 219, 7, actStockLedgerBrBatchIssuedRate);
		
		
		String actStockLedgerBrBatchBalanceQty 		= getText(stockLedgerBrBatchBalanceQtyAfterSavingStockTransfer);
		String expStockLedgerBrBatchBalanceQty 	    = excelReader.getCellData(xlSheetName, 220, 6);
		excelReader.setCellData(xlfile, xlSheetName, 220, 7, actStockLedgerBrBatchBalanceQty);

		String actStockLedgerBrBatchBalanceValue    = getText(stockLedgerBrBatchBalanceValueAfterSavingStockTransfer);
		String expStockLedgerBrBatchBalanceValue    = excelReader.getCellData(xlSheetName, 221, 6);
		excelReader.setCellData(xlfile, xlSheetName, 221, 7, actStockLedgerBrBatchBalanceValue);

		String actStockLedgerBrBatchAvgRate 		= getText(stockLedgerBrBatchAvgRateAfterSavingStockTransfer);
		String expStockLedgerBrBatchAvgRate			= excelReader.getCellData(xlSheetName, 222, 6);
		excelReader.setCellData(xlfile, xlSheetName, 222, 7, actStockLedgerBrBatchAvgRate);

		
		
		System.out.println("actStockLedgerBrBatchReceivedRate    :" + actStockLedgerBrBatchReceivedRate);
		System.out.println("expStockLedgerBrBatchReceivedRate    :" + expStockLedgerBrBatchReceivedRate);

		System.out.println("actStockLedgerBrBatchIssuedRate      :" + actStockLedgerBrBatchIssuedRate);
		System.out.println("expStockLedgerBrBatchIssuedRate      :" + expStockLedgerBrBatchIssuedRate);
		
		System.out.println("actStockLedgerBrBatchBalanceQty      :" + actStockLedgerBrBatchBalanceQty);
		System.out.println("expStockLedgerBrBatchBalanceQty      :" + expStockLedgerBrBatchBalanceQty);


		System.out.println("actStockLedgerBrBatchBalanceValue    :" + actStockLedgerBrBatchBalanceValue);
		System.out.println("expStockLedgerBrBatchBalanceValue    :" + expStockLedgerBrBatchBalanceValue);

		System.out.println("actStockLedgerBrBatchAvgRate         :" + actStockLedgerBrBatchAvgRate);
		System.out.println("expStockLedgerBrBatchAvgRate         :" + expStockLedgerBrBatchAvgRate);

		if (actStockLedgerBrBatchBalanceQty.equalsIgnoreCase(expStockLedgerBrBatchBalanceQty)
				&& actStockLedgerBrBatchBalanceValue.equalsIgnoreCase(expStockLedgerBrBatchBalanceValue)
				&& actStockLedgerBrBatchAvgRate.equalsIgnoreCase(expStockLedgerBrBatchAvgRate) && actStockLedgerBrBatchReceivedRate.equalsIgnoreCase(expStockLedgerBrBatchReceivedRate)
				&& actStockLedgerBrBatchIssuedRate.equalsIgnoreCase(expStockLedgerBrBatchIssuedRate)) {

			click(report_CloseBtn);
			excelReader.setCellData(xlfile, xlSheetName, 218, 8, resPass);
			return true;
		} else {
			click(report_CloseBtn);
			excelReader.setCellData(xlfile, xlSheetName, 218, 8, resFail);
			return false;
		}
	}
	
	
	

	public boolean checkStockLedgerReportForFifoItemAfterStockTransferVoucher()  
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {

		excelReader=new ExcelReader(POJOUtility.getExcelPath());
		
		Thread.sleep(3000);

		if (fifoItemCheckBox.isSelected() == false) {
			click(fifoItemCheckBox);
		}

		if (brBatchItemCheckBox.isSelected() == true) {
			click(brBatchItemCheckBox);
		}

		if (rmaItemCheckBox.isSelected() == true) {
			click(rmaItemCheckBox);

		}

		
		dropDown(sl_DateOptionDropdown, excelReader.getCellData(xlSheetName, 223, 5));	
		Thread.sleep(2000);
		click(sl_OkBtn);
		Thread.sleep(2000);
		click(stockLedgerLastBtn);
		Thread.sleep(2000);

		String actstockLedgerFifoItemBalanceQty   = getText(stockLedgerFifoItemBalanceQtyAfterSavingStockTransfer);
		String expstockLedgerFifoItemBalanceQty   = excelReader.getCellData(xlSheetName, 223, 6);
		excelReader.setCellData(xlfile, xlSheetName, 223, 7, actstockLedgerFifoItemBalanceQty);

		String actstockLedgerFifoItemBalanceValue = getText(stockLedgerFifoItemBalanceValueAfterSavingStockTransfer);
		String expstockLedgerFifoItemBalanceValue = excelReader.getCellData(xlSheetName, 224, 6);
		excelReader.setCellData(xlfile, xlSheetName, 224, 7, actstockLedgerFifoItemBalanceValue);

		String actstockLedgerFifoItemAvgRate      = getText(stockLedgerFifoItemAvgRateAfterSavingStockTransfer);
		String expstockLedgerFifoItemAvgRate      = excelReader.getCellData(xlSheetName, 225, 6);
		excelReader.setCellData(xlfile, xlSheetName, 225, 7, actstockLedgerFifoItemAvgRate);

		System.out.println("actstockLedgerFifoItemBalanceQty        :" + actstockLedgerFifoItemBalanceQty);
		System.out.println("expstockLedgerFifoItemBalanceQty        :" + expstockLedgerFifoItemBalanceQty);

		System.out.println("actstockLedgerFifoItemBalanceValue      :" + actstockLedgerFifoItemBalanceValue);
		System.out.println("expstockLedgerFifoItemBalanceValue      :" + expstockLedgerFifoItemBalanceValue);

		System.out.println("actstockLedgerFifoItemAvgRate           :" + actstockLedgerFifoItemAvgRate);
		System.out.println("expstockLedgerFifoItemAvgRate           :" + expstockLedgerFifoItemAvgRate);

		if (actstockLedgerFifoItemBalanceQty.equalsIgnoreCase(expstockLedgerFifoItemBalanceQty)
				&& actstockLedgerFifoItemBalanceValue.equalsIgnoreCase(expstockLedgerFifoItemBalanceValue)
				&& actstockLedgerFifoItemAvgRate.equalsIgnoreCase(expstockLedgerFifoItemAvgRate)) {

			click(report_CloseBtn);
			excelReader.setCellData(xlfile, xlSheetName, 223, 8, resPass);
			return true;
		} else {
			click(report_CloseBtn);
			excelReader.setCellData(xlfile, xlSheetName, 223, 8, resFail);
			return false;
		}
	}
	
	
	
	

	public boolean checkStockLedgerReportForRmaItemAfterStockTransferVoucher()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {

		excelReader=new ExcelReader(POJOUtility.getExcelPath());

		
		Thread.sleep(3000);

		if (fifoItemCheckBox.isSelected() == true) {
			click(fifoItemCheckBox);

		}

		if (brBatchItemCheckBox.isSelected() == true) {
			click(brBatchItemCheckBox);
		}

		if (rmaItemCheckBox.isSelected() == false) {
			click(rmaItemCheckBox);

		}

	
		dropDown(sl_DateOptionDropdown, excelReader.getCellData(xlSheetName, 226, 5));
		Thread.sleep(2000);
		click(sl_OkBtn);
		Thread.sleep(2000);
		click(stockLedgerLastBtn);
		Thread.sleep(2000);
		

		String actStockLedgerRmaItemReceivedRate 	= getText(stockLedgerRmaItemReceivedRateAfterSavingStockTransfer);
		String expStockLedgerRmaItemReceivedRate	= excelReader.getCellData(xlSheetName, 226, 6);
		excelReader.setCellData(xlfile, xlSheetName, 226, 7, actStockLedgerRmaItemReceivedRate);
		
		String actStockLedgerRmaItemIssuedRate 		= getText(stockLedgerRmaItemIssuedRateAfterSavingStockTransfer);
		String expStockLedgerRmaItemIssuedRate		= excelReader.getCellData(xlSheetName, 227, 6);
		excelReader.setCellData(xlfile, xlSheetName, 227, 7, actStockLedgerRmaItemIssuedRate);
		

		String actstockLedgerRmaItemBalanceQty		= getText(stockLedgerRmaItemBalanceQtyAfterSavingStockTransfer);
		String expstockLedgerRmaItemBalanceQty 		= excelReader.getCellData(xlSheetName, 228, 6);
		excelReader.setCellData(xlfile, xlSheetName, 228, 7, actstockLedgerRmaItemBalanceQty);

		String actstockLedgerRmaItemBalanceValue 	= getText(stockLedgerRmaItemBalanceValueAfterSavingStockTransfer);
		String expstockLedgerRmaItemBalanceValue 	= excelReader.getCellData(xlSheetName, 229, 6);
		excelReader.setCellData(xlfile, xlSheetName, 229, 7, actstockLedgerRmaItemBalanceValue);

		String actstockLedgerrmaItemAvgRate 		= getText(stockLedgerrmaItemAvgRateAfterSavingStockTransfer);
		String expstockLedgerrmaItemAvgRate 		= excelReader.getCellData(xlSheetName, 230, 6);
		excelReader.setCellData(xlfile, xlSheetName, 230, 7, actstockLedgerrmaItemAvgRate);
		
		
		System.out.println("actStockLedgerRmaItemReceivedRate      :" + actStockLedgerRmaItemReceivedRate);
		System.out.println("expStockLedgerRmaItemReceivedRate      :" + expStockLedgerRmaItemReceivedRate);

		System.out.println("actStockLedgerRmaItemIssuedRate        :" + actStockLedgerRmaItemIssuedRate);
		System.out.println("expStockLedgerRmaItemIssuedRate        :" + expStockLedgerRmaItemIssuedRate);

		System.out.println("actstockLedgerRmaItemBalanceQty        :" + actstockLedgerRmaItemBalanceQty);
		System.out.println("expstockLedgerRmaItemBalanceQty        :" + expstockLedgerRmaItemBalanceQty);

		System.out.println("actstockLedgerRmaItemBalanceValue      :" + actstockLedgerRmaItemBalanceValue);
		System.out.println("expstockLedgerRmaItemBalanceValue      :" + expstockLedgerRmaItemBalanceValue);

		System.out.println("actstockLedgerrmaItemAvgRate           :" + actstockLedgerrmaItemAvgRate);
		System.out.println("expstockLedgerrmaItemAvgRate           :" + expstockLedgerrmaItemAvgRate);

		if (actstockLedgerRmaItemBalanceQty.equalsIgnoreCase(expstockLedgerRmaItemBalanceQty)
				&& actstockLedgerRmaItemBalanceValue.equalsIgnoreCase(expstockLedgerRmaItemBalanceValue)
				&& actstockLedgerrmaItemAvgRate.equalsIgnoreCase(expstockLedgerrmaItemAvgRate) && actStockLedgerRmaItemReceivedRate.equalsIgnoreCase(expStockLedgerRmaItemReceivedRate)
				&& actStockLedgerRmaItemIssuedRate.equalsIgnoreCase(expStockLedgerRmaItemIssuedRate)) {

			click(report_CloseBtn);
			Thread.sleep(2000);
			click(sl_CloseBtn);
			excelReader.setCellData(xlfile, xlSheetName, 226, 8, resPass);
			return true;
		} else {
			click(report_CloseBtn);
			Thread.sleep(2000);
			click(sl_CloseBtn);
			excelReader.setCellData(xlfile, xlSheetName, 226, 8, resFail);
			return false;
		}
	}
	
	
	public boolean checkSavingStockTransferVocher2() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException
	{

		excelReader = new ExcelReader(POJOUtility.getExcelPath());

		Thread.sleep(2000);
		click(inventoryMenu);
		click(inventoryTransactionsMenu);
		click(inventoryTransactionsStockMenu);
		click(inventoryTransactionsStockMenuStockTransfersMenu);
		click(newBtn);

		checkValidationMessage("Screen opened");

		click(stockTransferDatetxt);
		stockTransferDatetxt.sendKeys(Keys.CONTROL, "a");
		Thread.sleep(2000);
		stockTransferDatetxt.sendKeys(Keys.BACK_SPACE);
		enterText(stockTransferDatetxt, excelReader.getCellData(xlSheetName, 231, 5));
		tab(stockTransferDatetxt);
		tab(stockTransferIssuestxt);
		Thread.sleep(1000);
		enterText(stockTransferWarehousetxt, excelReader.getCellData(xlSheetName, 232, 5));
		Thread.sleep(1000);
		tab(stockTransferWarehousetxt);
		Thread.sleep(2000);

		click(select1stRow_1stColumn);
		enterText(enterWarehousettxt2, excelReader.getCellData(xlSheetName, 233, 5));
		Thread.sleep(2000);

		tab(enterWarehousettxt2);
		enterText(enterItemttxt, excelReader.getCellData(xlSheetName, 234, 5));
		Thread.sleep(2000);
		tab(enterItemttxt);

		enterText(enterQuantitytxt, excelReader.getCellData(xlSheetName, 235, 5));
		tab(enterQuantitytxt);
		enterText(enterRatetxt, excelReader.getCellData(xlSheetName, 236, 5));
		tab(enterRatetxt);
		Thread.sleep(2000);

		String actRow1AvgRate = select1stRow_AvgRate.getText();
		String expRow1AvgRate = excelReader.getCellData(xlSheetName, 231, 6);
		excelReader.setCellData(xlfile, xlSheetName, 231, 7, actRow1AvgRate);

		String actRow1OverAllAvgRate = select1stRow_OverAllAvgRate.getText();
		String expRow1OverAllAvgRate = excelReader.getCellData(xlSheetName, 232, 6);
		excelReader.setCellData(xlfile, xlSheetName, 232, 7, actRow1OverAllAvgRate);

		System.out.println("actRow1AvgRate             :" + actRow1AvgRate);
		System.out.println("expRow1AvgRate             :" + expRow1AvgRate);

		System.out.println("actRow1OverAllAvgRate      :" + actRow1OverAllAvgRate);
		System.out.println("expRow1OverAllAvgRate      :" + expRow1OverAllAvgRate);

		click(select1stRow_8thColumn);

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(batchPickOnFIFOIcon));

		String actBatchpopupIsDisplaying = Boolean.toString(batchPickOnFIFOIcon.isDisplayed());
		String expBatchpopupIsDisplaying = excelReader.getCellData(xlSheetName, 233, 6);
		excelReader.setCellData(xlfile, xlSheetName, 233, 7, actBatchpopupIsDisplaying);

		System.out.println("Batch Popup isDisplaying  : " + actBatchpopupIsDisplaying + "  Value Expected  "
				+ expBatchpopupIsDisplaying);
		click(batchPickOnFIFOIcon);
		click(batchOkIcon);

		Thread.sleep(2000);

		String actRow1AvgRate1 = select1stRow_AvgRate.getText();
		String expRow1AvgRate1 = excelReader.getCellData(xlSheetName, 234, 6);
		excelReader.setCellData(xlfile, xlSheetName, 234, 7, actRow1AvgRate1);

		String actRow1OverAllAvgRate1 = select1stRow_OverAllAvgRate.getText();
		String expRow1OverAllAvgRate1 = excelReader.getCellData(xlSheetName, 235, 6);
		excelReader.setCellData(xlfile, xlSheetName, 235, 7, actRow1OverAllAvgRate1);

		String actRow1BatchText = select1stRow_BatchText.getText();
		String expRow1BatchText = excelReader.getCellData(xlSheetName, 236, 6);
		excelReader.setCellData(xlfile, xlSheetName, 236, 7, actRow1BatchText);

		System.out.println("actRow1AvgRate1             :" + actRow1AvgRate1);
		System.out.println("expRow1AvgRate1             :" + expRow1AvgRate1);

		System.out.println("actRow1OverAllAvgRate1      :" + actRow1OverAllAvgRate1);
		System.out.println("expRow1OverAllAvgRate1      :" + expRow1OverAllAvgRate1);

		System.out.println("actRow1BatchText            :" + actRow1BatchText);
		System.out.println("expRow1BatchText            :" + expRow1BatchText);

		click(select2ndRow_1stColumn);

		enterWarehousettxt2.sendKeys(Keys.CONTROL, "a");
		Thread.sleep(2000);
		enterWarehousettxt2.sendKeys(Keys.BACK_SPACE);
		enterText(enterWarehousettxt2, excelReader.getCellData(xlSheetName, 237, 5));
		Thread.sleep(2000);
		tab(enterWarehousettxt2);
		enterText(enterItemttxt, excelReader.getCellData(xlSheetName, 238, 5));
		Thread.sleep(2000);
		tab(enterItemttxt);

		enterText(enterQuantitytxt, excelReader.getCellData(xlSheetName, 239, 5));
		tab(enterQuantitytxt);
		enterText(enterRatetxt, excelReader.getCellData(xlSheetName, 240, 5));
		tab(enterRatetxt);
		Thread.sleep(2000);

		click(select2ndRow_11thColumn);

		Thread.sleep(1000);
		// enterRmatxt.sendKeys(Keys.SPACE);
		Thread.sleep(1000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(rmaScreenTitle));

		String actRMAPopupIsDisplaying1 = Boolean.toString(rmaScreenTitle.isDisplayed());
		String expRMAPopupIsDisplaying1 = excelReader.getCellData(xlSheetName, 237, 6);
		excelReader.setCellData(xlfile, xlSheetName, 237, 7, actRMAPopupIsDisplaying1);

		System.out.println("RMA Popup isDisplaying  : " + actRMAPopupIsDisplaying1 + "  Value Expected  "
				+ expRMAPopupIsDisplaying1);

		click(rmaSearchGridField);
		Thread.sleep(1000);

		int count = rmaSerialNos.size();

		ArrayList<String> SearchRMA = new ArrayList<String>();

		for (int i = 0; i < count; i++) {
			String data = rmaSerialNos.get(i).getText();
			SearchRMA.add(data);

			if (data.equalsIgnoreCase(excelReader.getCellData(xlSheetName, 241, 5))
					|| data.equalsIgnoreCase(excelReader.getCellData(xlSheetName, 242, 5))
					|| data.equalsIgnoreCase(excelReader.getCellData(xlSheetName, 243, 5))) {
				rmaSerialNoCheckBoxes.get(i).click();
			}
		}

		String actSearchRMASerialNoList = SearchRMA.toString();
		String expSearchRMASerialNoList = excelReader.getCellData(xlSheetName, 238, 6);
		excelReader.setCellData(xlfile, xlSheetName, 238, 7, actSearchRMASerialNoList);

		System.out.println("actSearchRMASerialNoList       :" + actSearchRMASerialNoList);
		System.out.println("expSearchRMASerialNoList       :" + expSearchRMASerialNoList);

		click(searchRmaOkBtn);

		int serialcount = rmaSerialNoList.size();
		ArrayList<String> rmaSerialnos = new ArrayList<String>();
		for (int i = 0; i < serialcount; i++) {
			String data = rmaSerialNoList.get(i).getText();
			rmaSerialnos.add(data);
		}

		String actRmaSerialNo = rmaSerialnos.toString();
		String expRmaSerialNo = excelReader.getCellData(xlSheetName, 239, 6);
		excelReader.setCellData(xlfile, xlSheetName, 239, 7, actRmaSerialNo);

		System.out.println("actRmaSerialNo       :" + actRmaSerialNo);
		System.out.println("expRmaSerialNo       :" + expRmaSerialNo);

		click(RMAPopupOkBtn);
		Thread.sleep(1000);
		tab(enterRmatxt);

		Thread.sleep(3000);

		String actRow2AvgRate = select2ndRow_AvgRate.getText();
		String expRow2AvgRate = excelReader.getCellData(xlSheetName, 240, 6);
		excelReader.setCellData(xlfile, xlSheetName, 240, 7, actRow2AvgRate);

		String actRow2OverAllAvgRate = select2ndRow_OverAllAvgRate.getText();
		String expRow2OverAllAvgRate = excelReader.getCellData(xlSheetName, 241, 6);
		excelReader.setCellData(xlfile, xlSheetName, 241, 7, actRow2OverAllAvgRate);

		System.out.println("actRow2AvgRate             		  :" + actRow2AvgRate);
		System.out.println("expRow2AvgRate             		  :" + expRow2AvgRate);

		System.out.println("actRow2OverAllAvgRate             :" + actRow2OverAllAvgRate);
		System.out.println("expRow2OverAllAvgRate             :" + expRow2OverAllAvgRate);

		// 3rd Row

		click(select3rdRow_1stColumn);

		enterWarehousettxt2.sendKeys(Keys.CONTROL, "a");
		Thread.sleep(2000);
		enterWarehousettxt2.sendKeys(Keys.BACK_SPACE);
		enterWarehousettxt2.sendKeys(Keys.SPACE);
		Thread.sleep(1000);
		enterText(enterWarehousettxt2, excelReader.getCellData(xlSheetName, 244, 5));
		Thread.sleep(2000);
		tab(enterWarehousettxt2);
		enterText(enterItemttxt, excelReader.getCellData(xlSheetName, 245, 5));
		Thread.sleep(2000);
		tab(enterItemttxt);

		enterText(enterQuantitytxt, excelReader.getCellData(xlSheetName, 246, 5));
		tab(enterQuantitytxt);
		enterText(enterRatetxt, excelReader.getCellData(xlSheetName, 247, 5));
		tab(enterRatetxt);
		Thread.sleep(2000);

		click(select3rdRow_9thColumn);
		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(binSearchTxt));

		String actBinpopupIsDisplaying = Boolean.toString(binSearchTxt.isDisplayed());
		String expBinpopupIsDisplaying = excelReader.getCellData(xlSheetName, 242, 6);
		excelReader.setCellData(xlfile, xlSheetName, 242, 7, actBinpopupIsDisplaying);

		System.out.println("Bin Popup isDisplaying  : " + actBinpopupIsDisplaying + "  Value Expected  "
				+ expBinpopupIsDisplaying);

		click(binSearchBtn);
		Thread.sleep(3000);
		click(binAutoAllocateBtn);
		Thread.sleep(2000);
		click(binOkBtn);

		Thread.sleep(2000);
		
		tab(bin1Txt);
		
		//bin2Txt.sendKeys(Keys.SPACE);
		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(binSearchTxt));

		String actBinpopupIsDisplaying1 = Boolean.toString(binSearchTxt.isDisplayed());
		String expBinpopupIsDisplaying1 = excelReader.getCellData(xlSheetName, 243, 6);
		excelReader.setCellData(xlfile, xlSheetName, 243, 7, actBinpopupIsDisplaying1);

		System.out.println("Bin Popup isDisplaying  : " + actBinpopupIsDisplaying1 + "  Value Expected  "
				+ expBinpopupIsDisplaying1);

		click(binSearchBtn);
		Thread.sleep(3000);
		click(binAutoAllocateBtn);
		Thread.sleep(2000);
		click(binOkBtn);
		
		
		String docno=documentNumberTxt.getAttribute("value");

		Thread.sleep(2000);
		
		
		click(voucherSaveBtn);

		/*String actMessage = SavingInBackgroundTrial();

		HashSet<String> expMsg = new HashSet();

		expMsg.add(excelReader.getCellData(xlSheetName, 244, 6));
		expMsg.add(excelReader.getCellData(xlSheetName, 245, 6));
		excelReader.setCellData(xlfile, xlSheetName, 244, 7, actMessage);

		String expMessage = expMsg.toString();

		System.out.println("Actual Message    : " + actMessage);
		System.out.println("Expected Message  : " + expMessage);*/
		
		/*boolean actSaving=SavingInBackground(docno);
		boolean expSaving=true;*/
		
		
		String expMessage1= excelReader.getCellData(xlSheetName, 244, 6);
		String expMessage2= excelReader.getCellData(xlSheetName, 245, 6);
		
		String actMessage = checkValidationMessage(expMessage1);
		excelReader.setCellData(xlfile, xlSheetName, 244, 7, actMessage);
		
	   
	   
	   System.out.println("SavingMessage  :  "+actMessage +" Value Expected : "+expMessage1+" "+expMessage2);

		if (actMessage.startsWith(expMessage1) && actMessage.endsWith(expMessage2) && actRow1AvgRate.equalsIgnoreCase(expRow1AvgRate)
				&& actRMAPopupIsDisplaying1.equalsIgnoreCase(expRMAPopupIsDisplaying1)
				&& actRow1OverAllAvgRate.equalsIgnoreCase(expRow1OverAllAvgRate)
				&& actRow1AvgRate1.equalsIgnoreCase(expRow1AvgRate1)
				&& actRow1OverAllAvgRate1.equalsIgnoreCase(expRow1OverAllAvgRate1)
				&& actRow1BatchText.equalsIgnoreCase(expRow1BatchText)
				&& actSearchRMASerialNoList.equalsIgnoreCase(expSearchRMASerialNoList)
				&& actRmaSerialNo.equalsIgnoreCase(expRmaSerialNo) && actRow2AvgRate.equalsIgnoreCase(expRow2AvgRate)
				&& actRow2OverAllAvgRate.equalsIgnoreCase(expRow2OverAllAvgRate)) {
			click(new_CloseBtn);
			Thread.sleep(2000);
			click(voucherhomeCloseBtn);
			excelReader.setCellData(xlfile, xlSheetName, 231, 8, resPass);
			return true;

		} else {
			click(new_CloseBtn);
			Thread.sleep(2000);
			click(voucherhomeCloseBtn);
			excelReader.setCellData(xlfile, xlSheetName, 231, 8, resFail);
			return false;

		}
	}
	@FindBy(xpath = "//input[@id='id_header_1']")
	private static WebElement documentNumberTxt;
	
	public boolean validteItemQueryStocksInBatchItemAfterSavingStockTransferVoucher2() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {

		excelReader=new ExcelReader(POJOUtility.getExcelPath());
		
		click(inventoryMenu);
		click(InventoryReportsMenu);
		click(itemqueryMenu);
		click(itemTxt);
		Thread.sleep(2000);
		enterText(itemTxt, excelReader.getCellData(xlSheetName, 248, 5));
		Thread.sleep(3000);
		click(getStockBtn);
		Thread.sleep(3000);

		String actlOpenStockQuantity = getText(openStockQuantityTxt);
		String expOpenStockQuantity  = excelReader.getCellData(xlSheetName, 248, 6);
		excelReader.setCellData(xlfile, xlSheetName, 248, 7, actlOpenStockQuantity);

		String actlCurrentStock      = getText(currentStockTxt);
		String expCurrentStock       = excelReader.getCellData(xlSheetName, 249, 6);
		excelReader.setCellData(xlfile, xlSheetName, 249, 7, actlCurrentStock);

		String actlavgStockRate      = getText(avgStockRateTxt);
		String expavgStockRate       = excelReader.getCellData(xlSheetName, 250, 6);
		excelReader.setCellData(xlfile, xlSheetName, 250, 7, actlavgStockRate);

		String actlstockValueTxt 	 = getText(stockValueTxt);
		String expstockValueTxt		 = excelReader.getCellData(xlSheetName, 251, 6);
		excelReader.setCellData(xlfile, xlSheetName, 251, 7, actlstockValueTxt);

		System.out.println("=====Stocks In Batch Items======");

		System.out.println("actlOpenStockQuantity      :" + actlOpenStockQuantity);
		System.out.println("expOpenStockQuantity       :" + expOpenStockQuantity);

		System.out.println("actlCurrentStock           :" + actlCurrentStock);
		System.out.println("expCurrentStock            :" + expCurrentStock);

		System.out.println("actlavgStockRate           :" + actlavgStockRate);
		System.out.println("expavgStockRate            :" + expavgStockRate);

		System.out.println("actlstockValueTxt          :" + actlstockValueTxt);
		System.out.println("expstockValueTxt           :" + expstockValueTxt);

		if (actlOpenStockQuantity.equalsIgnoreCase(expOpenStockQuantity)
				&& actlCurrentStock.equalsIgnoreCase(expCurrentStock)
				&& actlavgStockRate.equalsIgnoreCase(expavgStockRate)
				&& actlstockValueTxt.equalsIgnoreCase(expstockValueTxt)) {
			
			excelReader.setCellData(xlfile, xlSheetName, 248, 8, resPass);
			return true;
		} else {
			
			excelReader.setCellData(xlfile, xlSheetName, 248, 8, resFail);
			return false;
		}

	}

	public boolean validteItemQueryStocksInFifoItemAfterSavingStockTransferVoucher2() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {

		excelReader=new ExcelReader(POJOUtility.getExcelPath());

		
		click(itemTxt);
		Thread.sleep(2000);
		itemTxt.sendKeys(Keys.CONTROL, "a");
		Thread.sleep(2000);
		itemTxt.sendKeys(Keys.BACK_SPACE);
		enterText(itemTxt, excelReader.getCellData(xlSheetName, 252, 5));
		Thread.sleep(3000);
		click(getStockBtn);
		Thread.sleep(2000);

		String actlOpenStockQuantity = getText(openStockQuantityTxt);
		String expOpenStockQuantity  = excelReader.getCellData(xlSheetName, 252, 6);
		excelReader.setCellData(xlfile, xlSheetName, 252, 7, actlOpenStockQuantity);

		String actlCurrentStock      = getText(currentStockTxt);
		String expCurrentStock       = excelReader.getCellData(xlSheetName, 253, 6);
		excelReader.setCellData(xlfile, xlSheetName, 253, 7, actlCurrentStock);

		String actlavgStockRate      = getText(avgStockRateTxt);
		String expavgStockRate       = excelReader.getCellData(xlSheetName, 254, 6);
		excelReader.setCellData(xlfile, xlSheetName, 254, 7, actlavgStockRate);
 
		String actlstockValueTxt     = getText(stockValueTxt);
		String expstockValueTxt      = excelReader.getCellData(xlSheetName, 255, 6);
		excelReader.setCellData(xlfile, xlSheetName, 255, 7, actlstockValueTxt);

		System.out.println("=====Stocks In FIFO Items======");

		System.out.println("actlOpenStockQuantity :" + actlOpenStockQuantity);
		System.out.println("expOpenStockQuantity  :" + expOpenStockQuantity);

		System.out.println("actlCurrentStock      :" + actlCurrentStock);
		System.out.println("expCurrentStock       :" + expCurrentStock);

		System.out.println("actlavgStockRate      :" + actlavgStockRate);
		System.out.println("expavgStockRate       :" + expavgStockRate);

		System.out.println("actlstockValueTxt     :" + actlstockValueTxt);
		System.out.println("expstockValueTxt      :" + expstockValueTxt);

		if (actlOpenStockQuantity.equalsIgnoreCase(expOpenStockQuantity)
				&& actlCurrentStock.equalsIgnoreCase(expCurrentStock)
				&& actlavgStockRate.equalsIgnoreCase(expavgStockRate)
				&& actlstockValueTxt.equalsIgnoreCase(expstockValueTxt)) {
			
			excelReader.setCellData(xlfile, xlSheetName, 252, 8, resPass);
			return true;
		} else {
			excelReader.setCellData(xlfile, xlSheetName, 252, 8, resFail);
			return false;
		}

	}

	public boolean validteItemQueryStocksInWAItemAfterSavingStockTransferVoucher2() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {

		excelReader=new ExcelReader(POJOUtility.getExcelPath());
		
		click(itemTxt);
		Thread.sleep(2000);
		itemTxt.sendKeys(Keys.CONTROL, "a");
		Thread.sleep(2000);
		itemTxt.sendKeys(Keys.BACK_SPACE);
		enterText(itemTxt, excelReader.getCellData(xlSheetName, 256, 5));
		Thread.sleep(3000);
		click(getStockBtn);
		Thread.sleep(3000);

		String actlOpenStockQuantity = getText(openStockQuantityTxt);
		String expOpenStockQuantity  = excelReader.getCellData(xlSheetName, 256, 6);
		excelReader.setCellData(xlfile, xlSheetName, 256, 7, actlOpenStockQuantity);

		String actlCurrentStock      = getText(currentStockTxt);
		String expCurrentStock       = excelReader.getCellData(xlSheetName, 257, 6);
		excelReader.setCellData(xlfile, xlSheetName, 257, 7, actlCurrentStock);

		String actlavgStockRate      = getText(avgStockRateTxt);
		String expavgStockRate       = excelReader.getCellData(xlSheetName, 258, 6);
		excelReader.setCellData(xlfile, xlSheetName, 258, 7, actlavgStockRate);

		String actlstockValueTxt     = getText(stockValueTxt);
		String expstockValueTxt      = excelReader.getCellData(xlSheetName, 259, 6);
		excelReader.setCellData(xlfile, xlSheetName, 259, 7, actlstockValueTxt);

		ScrollToElement(balanceByWarehouseBtn);
		click(balanceByWarehouseBtn);

		Thread.sleep(3000);

		int count = row1BalanceByWareHouseList.size();

		ArrayList<String> warehouseRow1 = new ArrayList<String>();

		for (int i = 0; i < count; i++) {
			String data = row1BalanceByWareHouseList.get(i).getText();
			warehouseRow1.add(data);
		}

		String actwarehouseRow1List = warehouseRow1.toString();
		String expWarehouseRow1List = excelReader.getCellData(xlSheetName, 260, 6);
		excelReader.setCellData(xlfile, xlSheetName, 260, 7, actwarehouseRow1List);

		int count1 = row2BalanceByWareHouseList.size();

		ArrayList<String> warehouseRow2 = new ArrayList<String>();

		for (int i = 0; i < count1; i++) {
			String data = row2BalanceByWareHouseList.get(i).getText();
			warehouseRow2.add(data);
		}

		String actwarehouseRow2List = warehouseRow2.toString();
		String expWarehouseRow2List = excelReader.getCellData(xlSheetName, 261, 6);
		excelReader.setCellData(xlfile, xlSheetName, 261, 7, actwarehouseRow2List);

		int count2 = row3BalanceByWareHouseList.size();

		ArrayList<String> warehouseRow3 = new ArrayList<String>();

		for (int i = 0; i < count2; i++) {
			String data = row3BalanceByWareHouseList.get(i).getText();
			warehouseRow3.add(data);
		}

		String actwarehouseRow3List = warehouseRow3.toString();
		String expWarehouseRow3List = excelReader.getCellData(xlSheetName, 262, 6);
		excelReader.setCellData(xlfile, xlSheetName, 262, 7, actwarehouseRow3List);
		
		


		System.out.println("=====Stocks In WA Items======");

		System.out.println("actlOpenStockQuantity :" + actlOpenStockQuantity);
		System.out.println("expOpenStockQuantity  :" + expOpenStockQuantity);

		System.out.println("actlCurrentStock      :" + actlCurrentStock);
		System.out.println("expCurrentStock       :" + expCurrentStock);

		System.out.println("actlavgStockRate      :" + actlavgStockRate);
		System.out.println("expavgStockRate       :" + expavgStockRate);

		System.out.println("actlstockValueTxt     :" + actlstockValueTxt);
		System.out.println("expstockValueTxt      :" + expstockValueTxt);

		System.out.println("===========Balance By Warehouse=======");

		System.out.println(actwarehouseRow1List);
		System.out.println(expWarehouseRow1List);

		System.out.println(actwarehouseRow2List);
		System.out.println(expWarehouseRow2List);

		System.out.println(actwarehouseRow3List);
		System.out.println(expWarehouseRow3List);

		
	
		
		
		if (actlOpenStockQuantity.equalsIgnoreCase(expOpenStockQuantity)
				&& actlCurrentStock.equalsIgnoreCase(expCurrentStock)
				&& actlavgStockRate.equalsIgnoreCase(expavgStockRate)
				&& actlstockValueTxt.equalsIgnoreCase(expstockValueTxt)
				&& actwarehouseRow1List.equalsIgnoreCase(expWarehouseRow1List)
				&& actwarehouseRow2List.equalsIgnoreCase(expWarehouseRow2List)
				&& actwarehouseRow3List.equalsIgnoreCase(expWarehouseRow3List)) 
		{
			excelReader.setCellData(xlfile, xlSheetName, 256, 8, resPass);
			return true;
		} else {
			excelReader.setCellData(xlfile, xlSheetName, 256, 8, resFail);
			return false;
		}

	}
	
	
	
	
	public boolean checkStockLedgerReportForBrBatchItemAfterStockTransferVoucher2()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {

		excelReader=new ExcelReader(POJOUtility.getExcelPath());
		
		click(inventoryMenu);
		click(InventoryReportsMenu);
		click(InventoryStockLedgerMenu);
		Thread.sleep(3000);

		int rowcount = stockLedgerHometableRowCount.size();

		System.out.println(rowcount);

		for (int i = 0; i < rowcount; i++) {
			String actName = ledgerHometableItemNamesList.get(i).getText();

			System.out.println(actName);

			if (actName.equalsIgnoreCase(excelReader.getCellData(xlSheetName, 263, 5))) {
				stockLedgerHometableItemChkboxList.get(i).click();
			}
		}
		
		dropDown(sl_DateOptionDropdown, excelReader.getCellData(xlSheetName, 264, 5));		
		Thread.sleep(2000);
		click(sl_OkBtn);
		Thread.sleep(2000);
		click(stockLedgerLastBtn);
		Thread.sleep(2000);
		//click(stockLedgerPreviousBtn);
		Thread.sleep(2000);

		String actStockLedgerBrBatchReceivedRate 	= getText(stockLedgerBrBatchReceivedRateAfterSavingStockTransfer2);
		String expStockLedgerBrBatchReceivedRate	= excelReader.getCellData(xlSheetName, 263, 6);
		excelReader.setCellData(xlfile, xlSheetName, 263, 7, actStockLedgerBrBatchReceivedRate);
		
		String actStockLedgerBrBatchIssuedRate 		= getText(stockLedgerBrBatchIssuedRateAfterSavingStockTransfer2);
		String expStockLedgerBrBatchIssuedRate		= excelReader.getCellData(xlSheetName, 264, 6);
		excelReader.setCellData(xlfile, xlSheetName, 264, 7, actStockLedgerBrBatchIssuedRate);
		
		
		String actStockLedgerBrBatchBalanceQty 		= getText(stockLedgerBrBatchBalanceQtyAfterSavingStockTransfer2);
		String expStockLedgerBrBatchBalanceQty 	    = excelReader.getCellData(xlSheetName, 265, 6);
		excelReader.setCellData(xlfile, xlSheetName, 265, 7, actStockLedgerBrBatchBalanceQty);

		String actStockLedgerBrBatchBalanceValue    = getText(stockLedgerBrBatchBalanceValueAfterSavingStockTransfer2);
		String expStockLedgerBrBatchBalanceValue    = excelReader.getCellData(xlSheetName, 266, 6);
		excelReader.setCellData(xlfile, xlSheetName, 266, 7, actStockLedgerBrBatchBalanceValue);

		String actStockLedgerBrBatchAvgRate 		= getText(stockLedgerBrBatchAvgRateAfterSavingStockTransfer2);
		String expStockLedgerBrBatchAvgRate			= excelReader.getCellData(xlSheetName, 267, 6);
		excelReader.setCellData(xlfile, xlSheetName, 267, 7, actStockLedgerBrBatchAvgRate);

		
		
		System.out.println("actStockLedgerBrBatchReceivedRate    :" + actStockLedgerBrBatchReceivedRate);
		System.out.println("expStockLedgerBrBatchReceivedRate    :" + expStockLedgerBrBatchReceivedRate);

		System.out.println("actStockLedgerBrBatchIssuedRate      :" + actStockLedgerBrBatchIssuedRate);
		System.out.println("expStockLedgerBrBatchIssuedRate      :" + expStockLedgerBrBatchIssuedRate);
		
		System.out.println("actStockLedgerBrBatchBalanceQty      :" + actStockLedgerBrBatchBalanceQty);
		System.out.println("expStockLedgerBrBatchBalanceQty      :" + expStockLedgerBrBatchBalanceQty);


		System.out.println("actStockLedgerBrBatchBalanceValue    :" + actStockLedgerBrBatchBalanceValue);
		System.out.println("expStockLedgerBrBatchBalanceValue    :" + expStockLedgerBrBatchBalanceValue);

		System.out.println("actStockLedgerBrBatchAvgRate         :" + actStockLedgerBrBatchAvgRate);
		System.out.println("expStockLedgerBrBatchAvgRate         :" + expStockLedgerBrBatchAvgRate);

		if (actStockLedgerBrBatchBalanceQty.equalsIgnoreCase(expStockLedgerBrBatchBalanceQty)
				&& actStockLedgerBrBatchBalanceValue.equalsIgnoreCase(expStockLedgerBrBatchBalanceValue)
				&& actStockLedgerBrBatchAvgRate.equalsIgnoreCase(expStockLedgerBrBatchAvgRate) && actStockLedgerBrBatchReceivedRate.equalsIgnoreCase(expStockLedgerBrBatchReceivedRate)
				&& actStockLedgerBrBatchIssuedRate.equalsIgnoreCase(expStockLedgerBrBatchIssuedRate)) {

			click(report_CloseBtn);
			excelReader.setCellData(xlfile, xlSheetName, 263, 8, resPass);
			return true;
		} else {
			click(report_CloseBtn);
			excelReader.setCellData(xlfile, xlSheetName, 263, 8, resFail);
			return false;
		}
	}

	 
	
	
	
	public boolean checkStockLedgerReportForFifoItemAfterStockTransferVoucher2()  
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {

		excelReader=new ExcelReader(POJOUtility.getExcelPath());

		Thread.sleep(3000);

		if (fifoItemCheckBox.isSelected() == false) {
			click(fifoItemCheckBox);
		}

		if (brBatchItemCheckBox.isSelected() == true) {
			click(brBatchItemCheckBox);
		}

		if (rmaItemCheckBox.isSelected() == true) {
			click(rmaItemCheckBox);

		}

		
		dropDown(sl_DateOptionDropdown, excelReader.getCellData(xlSheetName, 268, 5));	
		Thread.sleep(2000);
		click(sl_OkBtn);
		Thread.sleep(2000);
		click(stockLedgerLastBtn);
		Thread.sleep(2000);

		String actstockLedgerFifoItemBalanceQty   = getText(stockLedgerFifoItemBalanceQtyAfterSavingStockTransfer2);
		String expstockLedgerFifoItemBalanceQty   = excelReader.getCellData(xlSheetName, 268, 6);
		excelReader.setCellData(xlfile, xlSheetName, 268, 7, actstockLedgerFifoItemBalanceQty);

		String actstockLedgerFifoItemBalanceValue = getText(stockLedgerFifoItemBalanceValueAfterSavingStockTransfer2);
		String expstockLedgerFifoItemBalanceValue = excelReader.getCellData(xlSheetName, 269, 6);
		excelReader.setCellData(xlfile, xlSheetName, 269, 7, actstockLedgerFifoItemBalanceValue);

		String actstockLedgerFifoItemAvgRate      = getText(stockLedgerFifoItemAvgRateAfterSavingStockTransfer2);
		String expstockLedgerFifoItemAvgRate      = excelReader.getCellData(xlSheetName, 270, 6);
		excelReader.setCellData(xlfile, xlSheetName, 270, 7, actstockLedgerFifoItemAvgRate);

		System.out.println("actstockLedgerFifoItemBalanceQty      :" + actstockLedgerFifoItemBalanceQty);
		System.out.println("expstockLedgerFifoItemBalanceQty      :" + expstockLedgerFifoItemBalanceQty);

		System.out.println("actstockLedgerFifoItemBalanceValue    :" + actstockLedgerFifoItemBalanceValue);
		System.out.println("expstockLedgerFifoItemBalanceValue    :" + expstockLedgerFifoItemBalanceValue);

		System.out.println("actstockLedgerFifoItemAvgRate         :" + actstockLedgerFifoItemAvgRate);
		System.out.println("expstockLedgerFifoItemAvgRate         :" + expstockLedgerFifoItemAvgRate);

		if (actstockLedgerFifoItemBalanceQty.equalsIgnoreCase(expstockLedgerFifoItemBalanceQty)
				&& actstockLedgerFifoItemBalanceValue.equalsIgnoreCase(expstockLedgerFifoItemBalanceValue)
				&& actstockLedgerFifoItemAvgRate.equalsIgnoreCase(expstockLedgerFifoItemAvgRate)) {

			click(report_CloseBtn);
			excelReader.setCellData(xlfile, xlSheetName, 268, 8, resPass);

			return true;
		} else {
			click(report_CloseBtn);
			excelReader.setCellData(xlfile, xlSheetName, 268, 8, resFail);

			return false;
		}
	}
	
	

	

	public boolean checkStockLedgerReportForRmaItemAfterStockTransferVoucher2()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {

		excelReader=new ExcelReader(POJOUtility.getExcelPath());
		
		Thread.sleep(3000);

		if (fifoItemCheckBox.isSelected() == true) {
			click(fifoItemCheckBox);

		}

		if (brBatchItemCheckBox.isSelected() == true) {
			click(brBatchItemCheckBox);
		}

		if (rmaItemCheckBox.isSelected() == false) {
			click(rmaItemCheckBox);

		}

	
		dropDown(sl_DateOptionDropdown, excelReader.getCellData(xlSheetName, 271, 5));
		Thread.sleep(2000);
		click(sl_OkBtn);
		Thread.sleep(2000);
		click(stockLedgerLastBtn);
		Thread.sleep(2000);
		

		String actStockLedgerRmaItemReceivedRate 	= getText(stockLedgerRmaItemReceivedRateAfterSavingStockTransfer2);
		String expStockLedgerRmaItemReceivedRate	= excelReader.getCellData(xlSheetName, 271, 6);
		excelReader.setCellData(xlfile, xlSheetName, 271, 7, actStockLedgerRmaItemReceivedRate);
		
		String actStockLedgerRmaItemIssuedRate 		= getText(stockLedgerRmaItemIssuedRateAfterSavingStockTransfer2);
		String expStockLedgerRmaItemIssuedRate		= excelReader.getCellData(xlSheetName, 272, 6);	
		excelReader.setCellData(xlfile, xlSheetName, 272, 7, actStockLedgerRmaItemIssuedRate);
		

		String actstockLedgerRmaItemBalanceQty		= getText(stockLedgerRmaItemBalanceQtyAfterSavingStockTransfer2);
		String expstockLedgerRmaItemBalanceQty 		= excelReader.getCellData(xlSheetName, 273, 6);
		excelReader.setCellData(xlfile, xlSheetName, 273, 7, actstockLedgerRmaItemBalanceQty);

		String actstockLedgerRmaItemBalanceValue 	= getText(stockLedgerRmaItemBalanceValueAfterSavingStockTransfer2);
		String expstockLedgerRmaItemBalanceValue 	= excelReader.getCellData(xlSheetName, 274, 6);
		excelReader.setCellData(xlfile, xlSheetName, 274, 7, actstockLedgerRmaItemBalanceValue);

		String actstockLedgerrmaItemAvgRate 		= getText(stockLedgerrmaItemAvgRateAfterSavingStockTransfer2);
		String expstockLedgerrmaItemAvgRate 		= excelReader.getCellData(xlSheetName, 275, 6);
		excelReader.setCellData(xlfile, xlSheetName, 275, 7, actstockLedgerrmaItemAvgRate);
		
		
		System.out.println("actStockLedgerRmaItemReceivedRate    :" + actStockLedgerRmaItemReceivedRate);
		System.out.println("expStockLedgerRmaItemReceivedRate    :" + expStockLedgerRmaItemReceivedRate);

		System.out.println("actStockLedgerRmaItemIssuedRate      :" + actStockLedgerRmaItemIssuedRate);
		System.out.println("expStockLedgerRmaItemIssuedRate      :" + expStockLedgerRmaItemIssuedRate);

		System.out.println("actstockLedgerRmaItemBalanceQty      :" + actstockLedgerRmaItemBalanceQty);
		System.out.println("expstockLedgerRmaItemBalanceQty      :" + expstockLedgerRmaItemBalanceQty);

		System.out.println("actstockLedgerRmaItemBalanceValue    :" + actstockLedgerRmaItemBalanceValue);
		System.out.println("expstockLedgerRmaItemBalanceValue    :" + expstockLedgerRmaItemBalanceValue);

		System.out.println("actstockLedgerrmaItemAvgRate         :" + actstockLedgerrmaItemAvgRate);
		System.out.println("expstockLedgerrmaItemAvgRate         :" + expstockLedgerrmaItemAvgRate);

		if (actstockLedgerRmaItemBalanceQty.equalsIgnoreCase(expstockLedgerRmaItemBalanceQty)
				&& actstockLedgerRmaItemBalanceValue.equalsIgnoreCase(expstockLedgerRmaItemBalanceValue)
				&& actstockLedgerrmaItemAvgRate.equalsIgnoreCase(expstockLedgerrmaItemAvgRate) && actStockLedgerRmaItemReceivedRate.equalsIgnoreCase(expStockLedgerRmaItemReceivedRate)
				&& actStockLedgerRmaItemIssuedRate.equalsIgnoreCase(expStockLedgerRmaItemIssuedRate)) {

			click(report_CloseBtn);
			Thread.sleep(2000);
			click(sl_CloseBtn);
			excelReader.setCellData(xlfile, xlSheetName, 271, 8, resPass);
			return true;
		} else {
			click(report_CloseBtn);
			Thread.sleep(2000);
			click(sl_CloseBtn);
			excelReader.setCellData(xlfile, xlSheetName, 271, 8, resFail);
			return false;
		}
	}
	
	
	
	
	
	public boolean  checkSavingexcessInStocksVoucherWithALLItemsInInvUser() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException
	{

		excelReader=new ExcelReader(POJOUtility.getExcelPath());
		
		/*Thread.sleep(3000);
		click(userNameDisplayLogo);
		Thread.sleep(1000);
		click(logoutOption);		
		Thread.sleep(3000);
		
		String unamelt	=excelReader.getCellData(xlSheetName, 276, 5);

		String pawslt	=excelReader.getCellData(xlSheetName, 277, 5);
		
		LoginPage.enterUserName(unamelt);	     
        LoginPage.enterPassword(pawslt);
        
		
		 Select company = new Select(companyDropDownList);
		 company.selectByValue(excelReader.getCellData(xlSheetName, 278, 5));
		 Thread.sleep(2000);
		 
  
        LoginPage.clickOnSignInBtn();  */
		
		reLogin(excelReader.getCellData(xlSheetName, 276, 5), excelReader.getCellData(xlSheetName, 277, 5));
        
        Thread.sleep(3000);
        
        System.out.println("Signing");
        
        // Login User Name is Verify
        
        getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(userNameDisplay));
		String actUserInfo                      = userNameDisplay.getText();
		String expUserInfo                      = excelReader.getCellData(xlSheetName, 276, 6);	
		excelReader.setCellData(xlfile, xlSheetName, 276, 7, actUserInfo);

		System.out.println("Login With Valid User and Check User Name Display Value Actual : ." + actUserInfo + ". Value Expected : " + expUserInfo);
		
		
		// Login Company Name is Verify
		Thread.sleep(1000);
		click(companyLogo);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(companyName));
		String actGetLoginCompanyNameInformation = companyName.getText();
		String actGetLoginCompanyName            = actGetLoginCompanyNameInformation.substring(0, 19);
		String expGetLoginCompanyName            = excelReader.getCellData(xlSheetName, 277, 6);   
		excelReader.setCellData(xlfile, xlSheetName, 277, 7, actGetLoginCompanyName);
		companyLogo.click();
		
		System.out.println("Company Name Display Value Actual                : ." + actGetLoginCompanyName +  ". Value Expected : " + expGetLoginCompanyName);
		
		Thread.sleep(2000);
		click(inventoryMenu);
		click(inventoryTransactionsMenu);
		click(inventoryTransactionsStockMenu);
		click(inventoryTransactionsExcessesStocks);
		click(newBtn);
		
		checkValidationMessage("Screen opened");
		
		click(excessesStockDatetxt);
		excessesStockDatetxt.sendKeys(Keys.CONTROL, "a");
		Thread.sleep(2000);
		excessesStockDatetxt.sendKeys(Keys.BACK_SPACE);
		Thread.sleep(1000);
		enterText(excessesStockDatetxt, excelReader.getCellData(xlSheetName, 279, 5));
		tab(excessesStockDatetxt);
		
		
		click(select1stRow_1stColumn);
		
		
		enterText(enterWarehousettxt, excelReader.getCellData(xlSheetName, 280, 5));
		Thread.sleep(1000);
		tab(enterWarehousettxt);
				
		enterText(enterItemttxt, excelReader.getCellData(xlSheetName, 281, 5));
		Thread.sleep(2000);
		tab(enterItemttxt);
		
		enterText(enterQuantitytxt, excelReader.getCellData(xlSheetName, 282, 5));
		tab(enterQuantitytxt);
		enterText(enterRatetxt, excelReader.getCellData(xlSheetName, 283, 5));
		tab(enterRatetxt);
		
		click(select1stRow_8thColumn);
		
		enterText(enterBatchtxt, excelReader.getCellData(xlSheetName, 284, 5));
		tab(enterBatchtxt);
		
		// 2nd Row
		
		click(select2ndRow_1stColumn);
		Thread.sleep(2000);
		enterWarehousettxt.sendKeys(Keys.CONTROL, "a");
		Thread.sleep(2000);
		enterWarehousettxt.sendKeys(Keys.BACK_SPACE);		
		enterText(enterWarehousettxt, excelReader.getCellData(xlSheetName, 285, 5));
		Thread.sleep(1000);
		tab(enterWarehousettxt);				
		enterText(enterItemttxt, excelReader.getCellData(xlSheetName, 286, 5));
		Thread.sleep(2000);
		tab(enterItemttxt);		
		enterText(enterQuantitytxt, excelReader.getCellData(xlSheetName, 287, 5));
		tab(enterQuantitytxt);
		enterText(enterRatetxt, excelReader.getCellData(xlSheetName, 288, 5));
		tab(enterRatetxt);
		
		
		click(select2ndRow_8thColumn);
		
		enterText(enterBatchtxt, excelReader.getCellData(xlSheetName, 289, 5));
		tab(enterBatchtxt);
		
		//3rd Row
		
		click(select3rdRow_1stColumn);
		enterWarehousettxt.sendKeys(Keys.CONTROL, "a");
		Thread.sleep(2000);
		enterWarehousettxt.sendKeys(Keys.BACK_SPACE);
		enterText(enterWarehousettxt, excelReader.getCellData(xlSheetName, 290, 5));
		Thread.sleep(2000);
		tab(enterWarehousettxt);
		enterText(enterItemttxt, excelReader.getCellData(xlSheetName, 291, 5));
		Thread.sleep(2000);
		tab(enterItemttxt);
		enterText(enterQuantitytxt, excelReader.getCellData(xlSheetName, 292, 5));
		tab(enterQuantitytxt);
		enterText(enterRatetxt, excelReader.getCellData(xlSheetName, 293, 5));
		tab(enterRatetxt);
		
		
		click(select3rdRow_9thColumn);		
		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(binSearchTxt));

		String actBinpopupIsDisplaying = Boolean.toString(binSearchTxt.isDisplayed());
		String expBinpopupIsDisplaying = excelReader.getCellData(xlSheetName, 278, 6);
		excelReader.setCellData(xlfile, xlSheetName, 278, 7, actBinpopupIsDisplaying);

		System.out.println("Bin Popup isDisplaying  : " + actBinpopupIsDisplaying + "  Value Expected  "
				+ expBinpopupIsDisplaying);

		click(binSearchBtn);
		Thread.sleep(3000);
		click(binAutoAllocateBtn);
		Thread.sleep(2000);
		click(binOkBtn);
		Thread.sleep(2000);

		// 4th Row

		click(select4thRow_1stColumn);
		enterWarehousettxt.sendKeys(Keys.CONTROL, "a");
		Thread.sleep(2000);
		enterWarehousettxt.sendKeys(Keys.BACK_SPACE);
		enterText(enterWarehousettxt, excelReader.getCellData(xlSheetName, 294, 5));
		Thread.sleep(2000);
		tab(enterWarehousettxt);
		enterText(enterItemttxt, excelReader.getCellData(xlSheetName, 295, 5));
		Thread.sleep(2000);
		tab(enterItemttxt);
		
		
		enterText(enterQuantitytxt, excelReader.getCellData(xlSheetName, 296, 5));
		tab(enterQuantitytxt);
		enterText(enterRatetxt, excelReader.getCellData(xlSheetName, 297, 5));
		tab(enterRatetxt);
		
		
		click(select4thRow_9thColumn);		
		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(binSearchTxt));

		String actBinpopupIsDisplaying1 = Boolean.toString(binSearchTxt.isDisplayed());
		String expBinpopupIsDisplaying1 = excelReader.getCellData(xlSheetName, 279, 6);
		excelReader.setCellData(xlfile, xlSheetName, 279, 7, actBinpopupIsDisplaying1);

		System.out.println("Bin Popup isDisplaying  : " + actBinpopupIsDisplaying1 + "  Value Expected  "
				+ expBinpopupIsDisplaying1);

		click(binSearchBtn);
		Thread.sleep(3000);
		click(binAutoAllocateBtn);
		Thread.sleep(2000);
		click(binOkBtn);

		// 5th Row

		click(select5thRow_1stColumn);
		enterWarehousettxt.sendKeys(Keys.CONTROL, "a");
		Thread.sleep(2000);
		enterWarehousettxt.sendKeys(Keys.BACK_SPACE);
		enterText(enterWarehousettxt, excelReader.getCellData(xlSheetName, 298, 5));
		Thread.sleep(2000);
		tab(enterWarehousettxt);
		
		enterText(enterItemttxt, excelReader.getCellData(xlSheetName, 299, 5));
		Thread.sleep(2000);
		tab(enterItemttxt);
		
		enterText(enterQuantitytxt, excelReader.getCellData(xlSheetName, 300, 5));
		tab(enterQuantitytxt);
		enterText(enterRatetxt, excelReader.getCellData(xlSheetName, 301, 5));
		tab(enterRatetxt);
		
		click(select5thRow_10thColumn);
		
		Thread.sleep(2000);
		
		String actual5throwAvgRate 			= select5thRow_AvgRate.getText();
		String exp5throwAvgRate			    = excelReader.getCellData(xlSheetName, 280, 6);
		excelReader.setCellData(xlfile, xlSheetName, 280, 7, actual5throwAvgRate);
		
		String actual5throwOverallAvgRate   = select5thRow_ExcessStockOverAllAvgRate.getText();
		String exp5throwoverallAvgRate      = excelReader.getCellData(xlSheetName, 281, 6);
		excelReader.setCellData(xlfile, xlSheetName, 281, 7, actual5throwOverallAvgRate);
		
		System.out.println("actual5throwAvgRate       	 :" + actual5throwAvgRate);
		System.out.println("exp5throwAvgRate             :" + exp5throwAvgRate);

		System.out.println("actual5throwOverallAvgRate   :" + actual5throwOverallAvgRate);
		System.out.println("exp5throwoverallAvgRate      :" + exp5throwoverallAvgRate);
		
		Thread.sleep(1000);
		//enterRmatxt.sendKeys(Keys.SPACE);
		Thread.sleep(1000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(rmaScreenTitle));

		String actRMAPopupIsDisplaying = Boolean.toString(rmaScreenTitle.isDisplayed());
		String expRMAPopupIsDisplaying = excelReader.getCellData(xlSheetName, 282, 6);
		excelReader.setCellData(xlfile, xlSheetName, 282, 7, actRMAPopupIsDisplaying);

		System.out.println("RMA Popup isDisplaying  : " + actRMAPopupIsDisplaying + "  Value Expected  "
				+ expRMAPopupIsDisplaying);
		
		enterText(rmaSerialNumberTxtField, excelReader.getCellData(xlSheetName, 302, 5));
		click(rmaAddBtn);
		Thread.sleep(2000);
		click(RMAPopupOkBtn);
		Thread.sleep(3000);
		tab(enterRmatxt);

		// 6th Row
		Thread.sleep(1000);
		click(select6thRow_1stColumn);
		enterWarehousettxt.sendKeys(Keys.CONTROL, "a");
		Thread.sleep(2000);
		enterWarehousettxt.sendKeys(Keys.BACK_SPACE);
		enterText(enterWarehousettxt, excelReader.getCellData(xlSheetName, 303, 5));
		Thread.sleep(2000);
		tab(enterWarehousettxt);
		
		enterText(enterItemttxt, excelReader.getCellData(xlSheetName, 304, 5));
		Thread.sleep(2000);		
		tab(enterItemttxt);
		
		enterText(enterQuantitytxt, excelReader.getCellData(xlSheetName, 305, 5));
		tab(enterQuantitytxt);
		enterText(enterRatetxt, excelReader.getCellData(xlSheetName, 306, 5));
		tab(enterRatetxt);
		
		click(select6thRow_10thColumn);
		
		Thread.sleep(2000);
		
		String actual6throwAvgRate 				= select6thRow_AvgRate.getText();
		String exp6throwAvgRate 				= excelReader.getCellData(xlSheetName, 283, 6);
		excelReader.setCellData(xlfile, xlSheetName, 283, 7, actual6throwAvgRate);
		
		String actual6throwOverallAvgRate		= select6thRow_ExcessStockOverAllAvgRate.getText();
		String exp6throwoverallAvgRate			= excelReader.getCellData(xlSheetName, 284, 6);
		excelReader.setCellData(xlfile, xlSheetName, 284, 7, actual6throwOverallAvgRate);
		
		System.out.println("actual6throwAvgRate      	 :" + actual6throwAvgRate);
		System.out.println("exp6throwAvgRate          	 :" + exp6throwAvgRate);

		System.out.println("actual6throwOverallAvgRate   :" + actual6throwOverallAvgRate);
		System.out.println("exp6throwoverallAvgRate      :" + exp6throwoverallAvgRate);
		
		Thread.sleep(1000);
		//enterRmatxt.sendKeys(Keys.SPACE);
		Thread.sleep(1000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(rmaScreenTitle));

		String actRMAPopupIsDisplaying1 = Boolean.toString(rmaScreenTitle.isDisplayed());
		String expRMAPopupIsDisplaying1 = excelReader.getCellData(xlSheetName, 285, 6);
		excelReader.setCellData(xlfile, xlSheetName, 285, 7, actRMAPopupIsDisplaying1);

		System.out.println("RMA Popup isDisplaying  : " + actRMAPopupIsDisplaying1 + "  Value Expected  "
				+ expRMAPopupIsDisplaying1);
		enterText(rmaSerialNumberTxtField, excelReader.getCellData(xlSheetName, 307, 5));
		click(rmaAddBtn);
		Thread.sleep(2000);
		click(RMAPopupOkBtn);
		Thread.sleep(2000);		
		tab(enterRmatxt);
		String docno=documentNumberTxt.getAttribute("value");
		
		Thread.sleep(2000);
		click(voucherSaveBtn);
		Thread.sleep(2000);

		
		
		 /*String actMessage = SavingInBackgroundTrial();

         HashSet<String> expMsg = new HashSet();

         expMsg.add(excelReader.getCellData(xlSheetName, 286, 6));
         expMsg.add(excelReader.getCellData(xlSheetName, 287, 6));
         excelReader.setCellData(xlfile, xlSheetName, 286, 7, actMessage);
         
         String expMessage = expMsg.toString();

		System.out.println("Actual Message         : " + actMessage);
		System.out.println("Expected Message       : " + expMessage);
*/
		
		/*boolean actSaving=SavingInBackground(docno);
		boolean expSaving=true;*/
		
		String expMessage1= excelReader.getCellData(xlSheetName, 286, 6);
		String expMessage2= excelReader.getCellData(xlSheetName, 287, 6);
		
		String actMessage = checkValidationMessage(expMessage1);
		excelReader.setCellData(xlfile, xlSheetName, 286, 7, actMessage);
		
	   
	   
	   System.out.println("SavingMessage  :  "+actMessage +" Value Expected : "+expMessage1+" "+expMessage2);
		
		if (actMessage.startsWith(expMessage1) && actMessage.endsWith(expMessage2) && actual5throwAvgRate.equalsIgnoreCase(exp5throwAvgRate)
				&& actual5throwOverallAvgRate.equalsIgnoreCase(exp5throwoverallAvgRate)
				&& actual6throwAvgRate.equalsIgnoreCase(exp6throwAvgRate)
				&& actual6throwOverallAvgRate.equalsIgnoreCase(exp6throwoverallAvgRate)) {
			
			click(new_CloseBtn);			
			Thread.sleep(1000);
			click(voucherhomeCloseBtn);
			excelReader.setCellData(xlfile, xlSheetName, 276, 8, resPass);
			return true;
			
		} else {
			click(new_CloseBtn);			
			Thread.sleep(1000);
			click(voucherhomeCloseBtn);
			excelReader.setCellData(xlfile, xlSheetName, 276, 8, resFail);
			return false;

		}

	}
	
	
	
	public boolean  checkSavingShortagesinStockVoucherWithBrItemHydSecWareHosueInSUUser() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException
	{
		
		excelReader=new ExcelReader(POJOUtility.getExcelPath());
		
		/*Thread.sleep(3000);
		click(userNameDisplayLogo);
		Thread.sleep(1000);
		click(logoutOption);		
		Thread.sleep(3000);
		
		String unamelt =excelReader.getCellData(xlSheetName, 308, 5);

		String pawslt  =excelReader.getCellData(xlSheetName, 309, 5);
		
		LoginPage.enterUserName(unamelt);	     
        LoginPage.enterPassword(pawslt);
        
		
		 Select company = new Select(companyDropDownList);
		 company.selectByValue(excelReader.getCellData(xlSheetName, 310, 5));
		 Thread.sleep(2000);
		 
  
        LoginPage.clickOnSignInBtn();  */
		
		reLogin(excelReader.getCellData(xlSheetName, 308, 5), excelReader.getCellData(xlSheetName, 309, 5));
        
        Thread.sleep(3000);
        
        System.out.println("Signing");
        
        // Login User Name is Verify
        
        getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(userNameDisplay));
		String actUserInfo                      = userNameDisplay.getText();
		String expUserInfo                      = excelReader.getCellData(xlSheetName, 308, 6);	
		excelReader.setCellData(xlfile, xlSheetName, 308, 7, actUserInfo);

		System.out.println("Login With Valid User and Check User Name Display Value Actual : ." + actUserInfo + ". Value Expected : " + expUserInfo);
		
		
		// Login Company Name is Verify
		Thread.sleep(1000);
		click(companyLogo);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(companyName));
		String actGetLoginCompanyNameInformation = companyName.getText();
		String actGetLoginCompanyName            = actGetLoginCompanyNameInformation.substring(0, 19);
		String expGetLoginCompanyName            = excelReader.getCellData(xlSheetName, 309, 6);   
		excelReader.setCellData(xlfile, xlSheetName, 309, 7, actGetLoginCompanyName);
		companyLogo.click();
		
		System.out.println("Company Name Display Value Actual                : ." + actGetLoginCompanyName +  ". Value Expected : " + expGetLoginCompanyName);
		
		Thread.sleep(2000);

		
		
		click(inventoryMenu);
		click(inventoryTransactionsMenu);
		click(inventoryTransactionsStockMenu);
		click(inventoryTransactionsShortagesInStocks);
		click(newBtn);	
		
		checkValidationMessage("Screen opened");
		
		
		click(shortagesInStockDatetxt);
		shortagesInStockDatetxt.sendKeys(Keys.CONTROL, "a");
		Thread.sleep(2000);
		shortagesInStockDatetxt.sendKeys(Keys.BACK_SPACE);
		enterText(shortagesInStockDatetxt, excelReader.getCellData(xlSheetName, 311, 5));
		Thread.sleep(1000);
		tab(shortagesInStockDatetxt);
		
		
		click(select1stRow_1stColumn);		
		enterText(enterWarehousettxt, excelReader.getCellData(xlSheetName, 312, 5));
		Thread.sleep(1000);
		tab(enterWarehousettxt);	
		
		enterText(enterItemttxt, excelReader.getCellData(xlSheetName, 313, 5));
		Thread.sleep(2000);
		tab(enterItemttxt);	
		
		enterText(enterQuantitytxt, excelReader.getCellData(xlSheetName, 314, 5));
		tab(enterQuantitytxt);
		enterText(enterRatetxt, excelReader.getCellData(xlSheetName, 315, 5));
		tab(enterRatetxt);	
		
		click(select1stRow_8thColumn);
		
		Thread.sleep(2000);
		
		String actRow1BatchNo		=batchPopUPRow1BatchNoTxt.getText();
		String expRow1BatchNo		=excelReader.getCellData(xlSheetName, 310, 6);
		excelReader.setCellData(xlfile, xlSheetName, 310, 7, actRow1BatchNo);
		
		String actRow2BatchNo		=batchPopUPRow2BatchNoTxt.getText();
		String expRow2BatchNo		=excelReader.getCellData(xlSheetName, 311, 6);
		excelReader.setCellData(xlfile, xlSheetName, 311, 7, actRow2BatchNo);
		
		String actRow1Baseqty		=batchPopUPRow1BaseQuantityTxt.getText();
		String expRow1Baseqty		=excelReader.getCellData(xlSheetName, 312, 6);
		excelReader.setCellData(xlfile, xlSheetName, 312, 7, actRow1Baseqty);
		
		String actRow1Rate			=batchPopUPRow1RateTxt.getText();
		String expRow1Rate			=excelReader.getCellData(xlSheetName, 313, 6);
		excelReader.setCellData(xlfile, xlSheetName, 313, 7, actRow1Rate);
		
		String actRow2Baseqty		=batchPopUPRow2BaseQuantityTxt.getText();
		String expRow2Baseqty		=excelReader.getCellData(xlSheetName, 314, 6);
		excelReader.setCellData(xlfile, xlSheetName, 314, 7, actRow2Baseqty);
		
		String actRow2Rate			=batchPopUPRow2RateTxt.getText();
		String expRow2Rate			=excelReader.getCellData(xlSheetName, 315, 6);
		excelReader.setCellData(xlfile, xlSheetName, 315, 7, actRow2Rate);
		
	
		System.out.println("actRow1BatchNo             :"+actRow1BatchNo);
		System.out.println("expRow1BatchNo             :"+expRow1BatchNo);
		
		System.out.println("actRow2BatchNo             :"+actRow2BatchNo);
		System.out.println("expRow2BatchNo             :"+expRow2BatchNo);		
		
		
		System.out.println("actRow1Baseqty             :"+actRow1Baseqty);
		System.out.println("expRow1Baseqty             :"+expRow1Baseqty);
		
		System.out.println("actRow1Rate                :"+actRow1Rate);
		System.out.println("expRow1Rate                :"+expRow1Rate);
		
		System.out.println("actRow2Baseqty             :"+actRow2Baseqty);
		System.out.println("expRow2Baseqty             :"+expRow2Baseqty);
		
		System.out.println("actRow2Rate                :"+actRow2Rate);
		System.out.println("expRow2Rate                :"+expRow2Rate);
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(batchPickOnFIFOIcon));
		
		String actBatchpopupIsDisplaying = Boolean.toString(batchPickOnFIFOIcon.isDisplayed());
		String expBatchpopupIsDisplaying = excelReader.getCellData(xlSheetName, 316, 6);
		excelReader.setCellData(xlfile, xlSheetName, 316, 7, actBatchpopupIsDisplaying);
		
		System.out.println("Batch Popup isDisplaying  : " + actBatchpopupIsDisplaying + "  Value Expected  "
				+ expBatchpopupIsDisplaying);
		click(batchPickOnFIFOIcon);
		Thread.sleep(1500);
		click(batchOkIcon);
		Thread.sleep(1500);

		// 2nd Row

		click(select2ndRow_1stColumn);
		Thread.sleep(2000);
		enterWarehousettxt.sendKeys(Keys.CONTROL, "a");
		Thread.sleep(2000);
		enterWarehousettxt.sendKeys(Keys.BACK_SPACE);
		enterText(enterWarehousettxt, excelReader.getCellData(xlSheetName, 316, 5));
		Thread.sleep(3000);
		tab(enterWarehousettxt);
		
		enterText(enterItemttxt, excelReader.getCellData(xlSheetName, 317, 5));
		Thread.sleep(2000);
		tab(enterItemttxt);
		Thread.sleep(2000);	
		
		enterText(enterQuantitytxt, excelReader.getCellData(xlSheetName, 318, 5));
		tab(enterQuantitytxt);
		enterText(enterRatetxt, excelReader.getCellData(xlSheetName, 319, 5));
		tab(enterRatetxt);	
		
		click(select2ndRow_8thColumn);
		
		Thread.sleep(2000);
		
		String actRow1BatchNoSecLocation		=batchPopUPRow1BatchNoTxt.getText();
		String expRow1BatchNoSecLocation		=excelReader.getCellData(xlSheetName, 317, 6);
		excelReader.setCellData(xlfile, xlSheetName, 317, 7, actRow1BatchNoSecLocation);
		
		String actRow2BatchNoSecLocation		=batchPopUPRow2BatchNoTxt.getText();
		String expRow2BatchNoSecLocation		=excelReader.getCellData(xlSheetName, 318, 6);
		excelReader.setCellData(xlfile, xlSheetName, 318, 7, actRow2BatchNoSecLocation);
		
		String actRow1BaseqtySecLocation		=batchPopUPRow1BaseQuantityTxt.getText();
		String expRow1BaseqtySecLocation		=excelReader.getCellData(xlSheetName, 319, 6);
		excelReader.setCellData(xlfile, xlSheetName, 319, 7, actRow1BaseqtySecLocation);
		
		String actRow1RateSecLocation			=batchPopUPRow1RateTxt.getText();
		String expRow1RateSecLocation			=excelReader.getCellData(xlSheetName, 320, 6);
		excelReader.setCellData(xlfile, xlSheetName, 320, 7, actRow1RateSecLocation);
		
		String actRow2BaseqtySecLocation		=batchPopUPRow2BaseQuantityTxt.getText();
		String expRow2BaseqtySecLocation		=excelReader.getCellData(xlSheetName, 321, 6);
		excelReader.setCellData(xlfile, xlSheetName, 321, 7, actRow2BaseqtySecLocation);
		
		String actRow2RateSecLocation			=batchPopUPRow2RateTxt.getText();
		String expRow2RateSecLocation			=excelReader.getCellData(xlSheetName, 322, 6);
		excelReader.setCellData(xlfile, xlSheetName, 322, 7, actRow2RateSecLocation);
		
	
		System.out.println("actRow1BatchNoSecLocation             :"+actRow1BatchNoSecLocation);
		System.out.println("expRow1BatchNoSecLocation             :"+expRow1BatchNoSecLocation);
		
		System.out.println("actRow2BatchNoSecLocation             :"+actRow2BatchNoSecLocation);
		System.out.println("expRow2BatchNoSecLocation             :"+expRow2BatchNoSecLocation);
		
		
		
		System.out.println("actRow1BaseqtySecLocation             :"+actRow1BaseqtySecLocation);
		System.out.println("expRow1BaseqtySecLocation             :"+expRow1BaseqtySecLocation);
		
		System.out.println("actRow1RateSecLocation                :"+actRow1RateSecLocation);
		System.out.println("expRow1RateSecLocation                :"+expRow1RateSecLocation);
		
		System.out.println("actRow2BaseqtySecLocation             :"+actRow2BaseqtySecLocation);
		System.out.println("expRow2BaseqtySecLocation             :"+expRow2BaseqtySecLocation);
		
		System.out.println("actRow2RateSecLocation                :"+actRow2RateSecLocation);
		System.out.println("expRow2RateSecLocation                :"+expRow2RateSecLocation);
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(batchPickOnFIFOIcon));
		
		String actBatchpopupIsDisplaying1 = Boolean.toString(batchPickOnFIFOIcon.isDisplayed());
		String expBatchpopupIsDisplaying1 = excelReader.getCellData(xlSheetName, 323, 6);
		excelReader.setCellData(xlfile, xlSheetName, 323, 7, actBatchpopupIsDisplaying1);
		
		System.out.println("Batch Popup isDisplaying  : " + actBatchpopupIsDisplaying1 + "  Value Expected  "
				+ expBatchpopupIsDisplaying1);
		click(batchPickOnFIFOIcon);
		click(batchOkIcon);
		
		if(actRow1BatchNo.equalsIgnoreCase(expRow1BatchNo) && actRow2BatchNo.equalsIgnoreCase(expRow2BatchNo) 
				&& actRow1Baseqty.equalsIgnoreCase(expRow1Baseqty) && actRow1Rate.equalsIgnoreCase(expRow1Rate) 
				&& actRow2Baseqty.equalsIgnoreCase(expRow2Baseqty) && actRow2Rate.equalsIgnoreCase(expRow2Rate)
				&& actRow1BatchNoSecLocation.equalsIgnoreCase(expRow1BatchNoSecLocation) && actRow2BatchNoSecLocation.equalsIgnoreCase(expRow2BatchNoSecLocation)
				&& actRow1BaseqtySecLocation.equalsIgnoreCase(expRow1BaseqtySecLocation) && actRow1RateSecLocation.equalsIgnoreCase(expRow1RateSecLocation)
				&& actRow2BaseqtySecLocation.equalsIgnoreCase(expRow2BaseqtySecLocation) && actRow2RateSecLocation.equalsIgnoreCase(expRow2RateSecLocation)){
			
			excelReader.setCellData(xlfile, xlSheetName, 308, 8, resPass);
			return true;
		}
			else{
				
				excelReader.setCellData(xlfile, xlSheetName, 308, 8, resFail);
				return false;
			}
		
	}
	
	public boolean  checkSavingShortagesinStockVoucherWithBrItemTestWareHosue() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException{		
		
		excelReader=new ExcelReader(POJOUtility.getExcelPath());
		// 3rd Row

				click(select3rdRow_1stColumn);
				Thread.sleep(2000);
				enterWarehousettxt.sendKeys(Keys.CONTROL, "a");
				Thread.sleep(2000);
				enterWarehousettxt.sendKeys(Keys.BACK_SPACE);
				enterText(enterWarehousettxt, excelReader.getCellData(xlSheetName, 324, 5));
				Thread.sleep(3000);
				tab(enterWarehousettxt);
				
				enterText(enterItemttxt, excelReader.getCellData(xlSheetName, 325, 5));
				Thread.sleep(2000);
				tab(enterItemttxt);
				Thread.sleep(2000);	
				
				enterText(enterQuantitytxt, excelReader.getCellData(xlSheetName, 326, 5));
				tab(enterQuantitytxt);
				
				enterText(enterRatetxt, excelReader.getCellData(xlSheetName, 327, 5));
				tab(enterRatetxt);	
				
				click(select3rdRow_8thColumn);
				
				Thread.sleep(3000);
				
				String actRow1BatchNoTestLocation		=batchPopUPRow1BatchNoTxt.getText();
				String expRow1BatchNoTestLocation		=excelReader.getCellData(xlSheetName, 324, 6);
				excelReader.setCellData(xlfile, xlSheetName, 324, 7, actRow1BatchNoTestLocation);
				
				String actRow2BatchNoTestLocation		=batchPopUPRow2BatchNoTxt.getText();
				String expRow2BatchNoTestLocation		=excelReader.getCellData(xlSheetName, 325, 6);
				excelReader.setCellData(xlfile, xlSheetName, 325, 7, actRow2BatchNoTestLocation);
				
				String actRow3BatchNoTestLocation		=batchPopUPRow3BatchNoTxt.getText();
				String expRow3BatchNoTestLocation		=excelReader.getCellData(xlSheetName, 326, 6);
				excelReader.setCellData(xlfile, xlSheetName, 326, 7, actRow3BatchNoTestLocation);
				
				String actRow1BaseqtyTestLocation		=batchPopUPRow1BaseQuantityTxt.getText();
				String expRow1BaseqtyTestLocation		=excelReader.getCellData(xlSheetName, 327, 6);
				excelReader.setCellData(xlfile, xlSheetName, 327, 7, actRow1BaseqtyTestLocation);
				
				String actRow1RateTestLocation			=batchPopUPRow1RateTxt.getText();
				String expRow1RateTestLocation			=excelReader.getCellData(xlSheetName, 328, 6);
				excelReader.setCellData(xlfile, xlSheetName, 328, 7, actRow1RateTestLocation);
				
				String actRow2BaseqtyTestLocation		=batchPopUPRow2BaseQuantityTxt.getText();
				String expRow2BaseqtyTestLocation		=excelReader.getCellData(xlSheetName, 329, 6);
				excelReader.setCellData(xlfile, xlSheetName, 329, 7, actRow2BaseqtyTestLocation);
				
				String actRow2RateTestLocation			=batchPopUPRow2RateTxt.getText();
				String expRow2RateTestLocation			=excelReader.getCellData(xlSheetName, 330, 6);
				excelReader.setCellData(xlfile, xlSheetName, 330, 7, actRow2RateTestLocation);
				
				String actRow3BaseqtyTestLocation		=batchPopUPRow3BaseQuantityTxt.getText();
				String expRow3BaseqtyTestLocation		=excelReader.getCellData(xlSheetName, 331, 6);
				excelReader.setCellData(xlfile, xlSheetName, 331, 7, actRow3BaseqtyTestLocation);
				
				String actRow3RateTestLocation			=batchPopUPRow3RateTxt.getText();
				String expRow3RateTestLocation			=excelReader.getCellData(xlSheetName, 332, 6);
				excelReader.setCellData(xlfile, xlSheetName, 332, 7, actRow3RateTestLocation);
				
				
			
				System.out.println("actRow1BatchNoTestLocation             :"+actRow1BatchNoTestLocation);
				System.out.println("expRow1BatchNoTestLocation             :"+expRow1BatchNoTestLocation);
				
				System.out.println("actRow2BatchNoTestLocation             :"+actRow2BatchNoTestLocation);
				System.out.println("expRow2BatchNoTestLocation             :"+expRow2BatchNoTestLocation);
				
				System.out.println("actRow3BatchNoTestLocation             :"+actRow3BatchNoTestLocation);
				System.out.println("expRow3BatchNoTestLocation             :"+expRow3BatchNoTestLocation);
				
				System.out.println("actRow3RateTestLocation                :"+actRow3RateTestLocation);
				System.out.println("expRow3RateTestLocation                :"+expRow3RateTestLocation);
				
				System.out.println("actRow3BaseqtyTestLocation             :"+actRow3BaseqtyTestLocation);
				System.out.println("expRow3BaseqtyTestLocation             :"+expRow3BaseqtyTestLocation);
				
				System.out.println("actRow1BaseqtyTestLocation             :"+actRow1BaseqtyTestLocation);
				System.out.println("expRow1BaseqtyTestLocation             :"+expRow1BaseqtyTestLocation);
				
				System.out.println("actRow1RateTestLocation                :"+actRow1RateTestLocation);
				System.out.println("expRow1RateTestLocation                :"+expRow1RateTestLocation);
				
				System.out.println("actRow2BaseqtyTestLocation             :"+actRow2BaseqtyTestLocation);
				System.out.println("expRow2BaseqtyTestLocation             :"+expRow2BaseqtyTestLocation);
				
				System.out.println("actRow2RateTestLocation                :"+actRow2RateTestLocation);
				System.out.println("expRow2RateTestLocation                :"+expRow2RateTestLocation);
				
				getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(batchPickOnFIFOIcon));
				
				String actBatchpopupIsDisplaying2 = Boolean.toString(batchPickOnFIFOIcon.isDisplayed());
				String expBatchpopupIsDisplaying2 = excelReader.getCellData(xlSheetName, 333, 6);
				excelReader.setCellData(xlfile, xlSheetName, 333, 7, actBatchpopupIsDisplaying2);
				
				System.out.println("Batch Popup isDisplaying  : " + actBatchpopupIsDisplaying2 + "  Value Expected  "
						+ expBatchpopupIsDisplaying2);
				click(batchPickOnFIFOIcon);
				click(batchOkIcon);
				
				Thread.sleep(2000);
				click(select6thRow_1stColumn);

				int row3List=Row3ShortageInStockVoucherBodyList.size();
				System.err.println(row3List);
				ArrayList<String> row3ShortageStockList = new ArrayList<String>();
				for(int i=0 ; i < row3List ;i++)
			  	{
			  		String data=Row3ShortageInStockVoucherBodyList.get(i).getText();
			  		row3ShortageStockList.add(data);
			  	}
				
				String actrow3ShortageStockList = row3ShortageStockList.toString();
				String exprow3ShortageStockList = excelReader.getCellData(xlSheetName, 334, 6);
				excelReader.setCellData(xlfile, xlSheetName, 334, 7, actrow3ShortageStockList);
				
				System.out.println("actrow3ShortageStockList       :"+actrow3ShortageStockList);		
				System.out.println("exprow3ShortageStockList       :"+exprow3ShortageStockList);
				
				
				int row4List=Row4ShortageInStockVoucherBodyList.size();
				System.err.println(row4List);
				ArrayList<String> row4ShortageStockList = new ArrayList<String>();
				for(int i=0 ; i < row4List ;i++)
			  	{
			  		String data=Row4ShortageInStockVoucherBodyList.get(i).getText();
			  		row4ShortageStockList.add(data);
			  	}
				
				String actrow4ShortageStockList = row4ShortageStockList.toString();
				String exprow4ShortageStockList = excelReader.getCellData(xlSheetName, 335, 6);
				excelReader.setCellData(xlfile, xlSheetName, 335, 7, actrow4ShortageStockList);
				
				System.out.println("actrow4ShortageStockList       :"+actrow4ShortageStockList);		
				System.out.println("exprow4ShortageStockList       :"+exprow4ShortageStockList);
				
				int row5List=Row5ShortageInStockVoucherBodyList.size();
				System.err.println(row5List);
				ArrayList<String> row5ShortageStockList = new ArrayList<String>();
				for(int i=0 ; i < row5List ;i++)
			  	{
			  		String data=Row5ShortageInStockVoucherBodyList.get(i).getText();
			  		row5ShortageStockList.add(data);
			  	}
				
				String actrow5ShortageStockList = row5ShortageStockList.toString();
				String exprow5ShortageStockList = excelReader.getCellData(xlSheetName, 336, 6);
				excelReader.setCellData(xlfile, xlSheetName, 336, 7, actrow5ShortageStockList);
				
				System.out.println("actrow5ShortageStockList       :"+actrow5ShortageStockList);		
				System.out.println("exprow5ShortageStockList       :"+exprow5ShortageStockList);
		
				if(actRow1BatchNoTestLocation.equalsIgnoreCase(expRow1BatchNoTestLocation) && actRow2BatchNoTestLocation.equalsIgnoreCase(expRow2BatchNoTestLocation) 
						&& actRow3BatchNoTestLocation.equalsIgnoreCase(expRow3BatchNoTestLocation) && actRow3BaseqtyTestLocation.equalsIgnoreCase(expRow3BaseqtyTestLocation) 
						&& actRow3RateTestLocation.equalsIgnoreCase(expRow3RateTestLocation) && actRow1BaseqtyTestLocation.equalsIgnoreCase(expRow1BaseqtyTestLocation)
						&& actRow1RateTestLocation.equalsIgnoreCase(expRow1RateTestLocation) && actRow2BaseqtyTestLocation.equalsIgnoreCase(expRow2BaseqtyTestLocation) 
						&& actRow2RateTestLocation.equalsIgnoreCase(expRow2RateTestLocation) && actrow3ShortageStockList.equalsIgnoreCase(exprow3ShortageStockList)
						&& actrow4ShortageStockList.equalsIgnoreCase(exprow4ShortageStockList) && actrow5ShortageStockList.equalsIgnoreCase(exprow5ShortageStockList)){
					
					excelReader.setCellData(xlfile, xlSheetName, 324, 8, resPass);
					return true;
				}
					else{
						
						excelReader.setCellData(xlfile, xlSheetName, 324, 8, resFail);
						return false;
					}
		
	          }
	
	
	
	
	
		public boolean  checkSavingShortagesinStockVoucherWithFifoItem3WareHosue() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException{		
		
			    excelReader=new ExcelReader(POJOUtility.getExcelPath());
		// 3rd Row

				//click(select6thRow_1stColumn);
				Thread.sleep(2000);
				enterWarehousettxt.sendKeys(Keys.CONTROL, "a");
				Thread.sleep(2000);
				enterWarehousettxt.sendKeys(Keys.BACK_SPACE);
				enterText(enterWarehousettxt, excelReader.getCellData(xlSheetName, 337, 5));
				Thread.sleep(3000);
				tab(enterWarehousettxt);
				
				enterText(enterItemttxt, excelReader.getCellData(xlSheetName, 338, 5));
				Thread.sleep(2000);
				tab(enterItemttxt);
				Thread.sleep(2000);	
				
				enterText(enterQuantitytxt, excelReader.getCellData(xlSheetName, 339, 5));
				tab(enterQuantitytxt);
				enterText(enterRatetxt, excelReader.getCellData(xlSheetName, 340, 5));
				tab(enterRatetxt);	
				
				click(select6thRow_9thColumn);
				
				Thread.sleep(2000);

				getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(binSearchTxt));

				String actBinpopupIsDisplaying = Boolean.toString(binSearchTxt.isDisplayed());
				String expBinpopupIsDisplaying = excelReader.getCellData(xlSheetName, 337, 6);
				excelReader.setCellData(xlfile, xlSheetName, 337, 7, actBinpopupIsDisplaying);

				System.out.println("Bin Popup isDisplaying  : " + actBinpopupIsDisplaying + "  Value Expected  "
						+ expBinpopupIsDisplaying);

				click(binSearchBtn);
				Thread.sleep(3000);
				click(binAutoAllocateBtn);
				Thread.sleep(2000);
				click(binOkBtn);
				
				//7th Row
				
				click(select7thRow_1stColumn);
				Thread.sleep(2000);
				enterWarehousettxt.sendKeys(Keys.CONTROL, "a");
				Thread.sleep(2000);
				enterWarehousettxt.sendKeys(Keys.BACK_SPACE);
				enterText(enterWarehousettxt, excelReader.getCellData(xlSheetName, 341, 5));
				Thread.sleep(3000);
				tab(enterWarehousettxt);
				
				enterText(enterItemttxt, excelReader.getCellData(xlSheetName, 342, 5));
				Thread.sleep(2000);
				tab(enterItemttxt);
				Thread.sleep(2000);	
				
				enterText(enterQuantitytxt, excelReader.getCellData(xlSheetName, 343, 5));
				tab(enterQuantitytxt);
				enterText(enterRatetxt, excelReader.getCellData(xlSheetName, 344, 5));
				tab(enterRatetxt);	
				
				click(select7thRow_9thColumn);
				
				Thread.sleep(2000);

				getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(binSearchTxt));

				String actBinpopupIsDisplaying1 = Boolean.toString(binSearchTxt.isDisplayed());
				String expBinpopupIsDisplaying1 = excelReader.getCellData(xlSheetName, 338, 6);
				excelReader.setCellData(xlfile, xlSheetName, 338, 7, actBinpopupIsDisplaying1);

				System.out.println("Bin Popup isDisplaying  : " + actBinpopupIsDisplaying1 + "  Value Expected  "
						+ expBinpopupIsDisplaying1);

				click(binSearchBtn);
				Thread.sleep(3000);
				click(binAutoAllocateBtn);
				Thread.sleep(2000);
				click(binOkBtn);
				Thread.sleep(2000);
				//tab(bin1Txt);
				
				//8th Row
				
				click(select8thRow_1stColumn);
				Thread.sleep(2000);
				enterWarehousettxt.sendKeys(Keys.CONTROL, "a");
				Thread.sleep(2000);
				enterWarehousettxt.sendKeys(Keys.BACK_SPACE);
				enterText(enterWarehousettxt, excelReader.getCellData(xlSheetName, 345, 5));
				Thread.sleep(3000);
				tab(enterWarehousettxt);
				
				enterText(enterItemttxt, excelReader.getCellData(xlSheetName, 346, 5));
				Thread.sleep(2000);
				tab(enterItemttxt);
				Thread.sleep(2000);	
				
				enterText(enterQuantitytxt, excelReader.getCellData(xlSheetName, 347, 5));
				tab(enterQuantitytxt);
				
				enterText(enterRatetxt, excelReader.getCellData(xlSheetName, 348, 5));
				tab(enterRatetxt);	
				
				click(select8thRow_9thColumn);
				
				Thread.sleep(2000);

				getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(binSearchTxt));

				String actBinpopupIsDisplaying2 = Boolean.toString(binSearchTxt.isDisplayed());
				String expBinpopupIsDisplaying2 = excelReader.getCellData(xlSheetName, 339, 6);
				excelReader.setCellData(xlfile, xlSheetName, 339, 7, actBinpopupIsDisplaying2);

				System.out.println("Bin Popup isDisplaying  : " + actBinpopupIsDisplaying2 + "  Value Expected  "
						+ expBinpopupIsDisplaying2);

				click(binSearchBtn);
				Thread.sleep(3000);
				click(binAutoAllocateBtn);
				Thread.sleep(2000);
				click(binOkBtn);
				Thread.sleep(2000);
				//tab(bin1Txt);
				Thread.sleep(2000);

				if(actBinpopupIsDisplaying.equalsIgnoreCase(expBinpopupIsDisplaying) && 
						actBinpopupIsDisplaying1.equalsIgnoreCase(expBinpopupIsDisplaying1) && 
						actBinpopupIsDisplaying2.equalsIgnoreCase(expBinpopupIsDisplaying2)){
					
					excelReader.setCellData(xlfile, xlSheetName, 337, 8, resPass);
					return true;
					
				}else{
					
					excelReader.setCellData(xlfile, xlSheetName, 337, 8, resFail);
					return false;
				}
				
		}
		
		
		
		
		public boolean  checkSavingShortagesinStockVoucherWithRmaItemHydWareHosue() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException{		
			
			excelReader=new ExcelReader(POJOUtility.getExcelPath());	
		// 9th Row

		click(select9thRow_1stColumn);
		Thread.sleep(2000);
		enterWarehousettxt.sendKeys(Keys.CONTROL, "a");
		Thread.sleep(2000);
		enterWarehousettxt.sendKeys(Keys.BACK_SPACE);
		enterText(enterWarehousettxt, excelReader.getCellData(xlSheetName, 349, 5));
		Thread.sleep(3000);
		tab(enterWarehousettxt);
		
		enterText(enterItemttxt, excelReader.getCellData(xlSheetName, 350, 5));
		Thread.sleep(2000);
		tab(enterItemttxt);
		Thread.sleep(2000);
		
		enterText(enterQuantitytxt, excelReader.getCellData(xlSheetName, 351, 5));
		tab(enterQuantitytxt);
		enterText(enterRatetxt, excelReader.getCellData(xlSheetName, 352, 5));
		tab(enterRatetxt);
		
		click(select9thRow_10thColumn);
	
		Thread.sleep(1000);
		//enterRmatxt.sendKeys(Keys.SPACE);
		Thread.sleep(1000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(rmaScreenTitle));

		String actRMAPopupIsDisplaying = Boolean.toString(rmaScreenTitle.isDisplayed());
		String expRMAPopupIsDisplaying = excelReader.getCellData(xlSheetName, 349, 6);
		excelReader.setCellData(xlfile, xlSheetName, 349, 7, actRMAPopupIsDisplaying);

		System.out.println("RMA Popup isDisplaying  : " + actRMAPopupIsDisplaying + "  Value Expected  "
				+ expRMAPopupIsDisplaying);
		
		click(rmaSearchGridField);
		Thread.sleep(1000);
		
		int count = rmaSerialNos.size();
		
		ArrayList<String> SearchRMA = new ArrayList<String>();
		
		for (int i = 0; i < count; i++) 
		{
			String data = rmaSerialNos.get(i).getText();
			SearchRMA.add(data);
			
			if (data.equalsIgnoreCase(excelReader.getCellData(xlSheetName, 353, 5)) || 
					data.equalsIgnoreCase(excelReader.getCellData(xlSheetName, 354, 5)) || 
					data.equalsIgnoreCase(excelReader.getCellData(xlSheetName, 355, 5))) 
			{
				rmaSerialNoCheckBoxes.get(i).click();
			}
		}
		
		String actSearchRMASerialNoList = SearchRMA.toString();
		String expSearchRMASerialNoList = excelReader.getCellData(xlSheetName, 350, 6); 
		excelReader.setCellData(xlfile, xlSheetName, 350, 7, actSearchRMASerialNoList);
		
		System.out.println("actSearchRMASerialNoList       :"+actSearchRMASerialNoList);
		System.out.println("expSearchRMASerialNoList       :"+expSearchRMASerialNoList);
		
		
		//click(searchRmaSelectAllGridCheckBox);
		click(searchRmaOkBtn);
		
		int serialcount	=rmaSerialNoList.size();
		ArrayList<String> rmaSerialnos = new ArrayList<String>();
		for (int i = 0; i < serialcount; i++) {
			String data=rmaSerialNoList.get(i).getText();
			rmaSerialnos.add(data);
		}
		
		String actRmaSerialNo 	=rmaSerialnos.toString();
		String expRmaSerialNo	=excelReader.getCellData(xlSheetName, 351, 6);
		excelReader.setCellData(xlfile, xlSheetName, 351, 7, actRmaSerialNo);
		
		System.out.println("actRmaSerialNo       :"+actRmaSerialNo);
		System.out.println("expRmaSerialNo       :"+expRmaSerialNo);
		
	
		click(RMAPopupOkBtn);	
		Thread.sleep(2000);
		tab(enterRmatxt);
		
		Thread.sleep(2000);
		int row9List=Row9ShortageInStockVoucherBodyList.size();
		System.err.println(row9List);
		ArrayList<String> row9ShortageStockList = new ArrayList<String>();
		for(int i=0 ; i < row9List ;i++)
	  	{
	  		String data=Row9ShortageInStockVoucherBodyList.get(i).getText();
	  		row9ShortageStockList.add(data);
	  	}
		
		String actrow9ShortageStockList = row9ShortageStockList.toString();
		String exprow9ShortageStockList = excelReader.getCellData(xlSheetName, 352, 6);
		excelReader.setCellData(xlfile, xlSheetName, 352, 7, actrow9ShortageStockList);
		
		System.out.println("actrow9ShortageStockList       :"+actrow9ShortageStockList);		
		System.out.println("exprow9ShortageStockList       :"+exprow9ShortageStockList);
		
		
		
		
		
		if(actRMAPopupIsDisplaying.equalsIgnoreCase(expRMAPopupIsDisplaying) && actSearchRMASerialNoList.equalsIgnoreCase(expSearchRMASerialNoList) 
				&& actRmaSerialNo.equalsIgnoreCase(expRmaSerialNo) && actrow9ShortageStockList.equalsIgnoreCase(exprow9ShortageStockList)){
			
			excelReader.setCellData(xlfile, xlSheetName, 349, 8, resPass);
			return true;
			
		}else{
			
			excelReader.setCellData(xlfile, xlSheetName, 349, 8, resFail);
			return false;
		}


				
		}
		
		
		
		public boolean  checkSavingShortagesinStockVoucherWithRmaItemSecWareHosue() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException{		
			
			
			excelReader=new ExcelReader(POJOUtility.getExcelPath());
			
			// 10th Row

			click(select10thRow_1stColumn);
			Thread.sleep(2000);
			enterWarehousettxt.sendKeys(Keys.CONTROL, "a");
			Thread.sleep(2000);
			enterWarehousettxt.sendKeys(Keys.BACK_SPACE);
			enterText(enterWarehousettxt, excelReader.getCellData(xlSheetName, 356, 5));
			Thread.sleep(3000);
			tab(enterWarehousettxt);
			
			enterText(enterItemttxt, excelReader.getCellData(xlSheetName, 357, 5));
			Thread.sleep(2000);
			tab(enterItemttxt);
			Thread.sleep(2000);
			
			enterText(enterQuantitytxt, excelReader.getCellData(xlSheetName, 358, 5));
			tab(enterQuantitytxt);
			enterText(enterRatetxt, excelReader.getCellData(xlSheetName, 359, 5));
			tab(enterRatetxt);
			
			click(select10thRow_10thColumn);
			
			Thread.sleep(1000);
			//enterRmatxt.sendKeys(Keys.SPACE);
			Thread.sleep(1000);

			getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(rmaScreenTitle));

			String actRMAPopupIsDisplaying = Boolean.toString(rmaScreenTitle.isDisplayed());
			String expRMAPopupIsDisplaying = excelReader.getCellData(xlSheetName, 356, 6);
			excelReader.setCellData(xlfile, xlSheetName, 356, 7, actRMAPopupIsDisplaying);


			System.out.println("RMA Popup isDisplaying  : " + actRMAPopupIsDisplaying + "  Value Expected  "
					+ expRMAPopupIsDisplaying);
			
			click(rmaSearchGridField);
			Thread.sleep(1000);
			
			int count = rmaSerialNos.size();
			
			ArrayList<String> SearchRMA = new ArrayList<String>();
			
			for (int i = 0; i < count; i++) 
			{
				String data = rmaSerialNos.get(i).getText();
				SearchRMA.add(data);
				
				if (data.equalsIgnoreCase(excelReader.getCellData(xlSheetName, 360, 5)) ||
						data.equalsIgnoreCase(excelReader.getCellData(xlSheetName, 361, 5)) || 
						data.equalsIgnoreCase(excelReader.getCellData(xlSheetName, 362, 5))) 
				{
					rmaSerialNoCheckBoxes.get(i).click();
				}
			}
			
			String actSearchRMASerialNoList = SearchRMA.toString();
			String expSearchRMASerialNoList = excelReader.getCellData(xlSheetName, 357, 6); 
			excelReader.setCellData(xlfile, xlSheetName, 357, 7, actSearchRMASerialNoList);

			
			System.out.println("actSearchRMASerialNoList       :"+actSearchRMASerialNoList);
			System.out.println("expSearchRMASerialNoList       :"+expSearchRMASerialNoList);
			
			
			//click(searchRmaSelectAllGridCheckBox);
			click(searchRmaOkBtn);
			
			int serialcount	=rmaSerialNoList.size();
			ArrayList<String> rmaSerialnos = new ArrayList<String>();
			for (int i = 0; i < serialcount; i++) {
				String data=rmaSerialNoList.get(i).getText();
				rmaSerialnos.add(data);
			}
			
			String actRmaSerialNo 	=rmaSerialnos.toString();
			String expRmaSerialNo	=excelReader.getCellData(xlSheetName, 358, 6);
			excelReader.setCellData(xlfile, xlSheetName, 358, 7, actRmaSerialNo);

			
			System.out.println("actRmaSerialNo       :"+actRmaSerialNo);
			System.out.println("expRmaSerialNo       :"+expRmaSerialNo);
			
		
			click(RMAPopupOkBtn);	
			Thread.sleep(2000);
			tab(enterRmatxt);
			
			Thread.sleep(2000);
			int row10List=Row10ShortageInStockVoucherBodyList.size();
			System.err.println(row10List);
			ArrayList<String> row10ShortageStockList = new ArrayList<String>();
			for(int i=0 ; i < row10List ;i++)
		  	{
		  		String data=Row10ShortageInStockVoucherBodyList.get(i).getText();
		  		row10ShortageStockList.add(data);
		  	}
			
			String actrow10ShortageStockList = row10ShortageStockList.toString();
			String exprow10ShortageStockList = excelReader.getCellData(xlSheetName, 359, 6);
			excelReader.setCellData(xlfile, xlSheetName, 359, 7, actrow10ShortageStockList);

			
			System.out.println("actrow10ShortageStockList       :"+actrow10ShortageStockList);		
			System.out.println("exprow10ShortageStockList       :"+exprow10ShortageStockList);
			
			
			if(actRMAPopupIsDisplaying.equalsIgnoreCase(expRMAPopupIsDisplaying) && actSearchRMASerialNoList.equalsIgnoreCase(expSearchRMASerialNoList) 
					&& actRmaSerialNo.equalsIgnoreCase(expRmaSerialNo) && actrow10ShortageStockList.equalsIgnoreCase(exprow10ShortageStockList)){
				
				excelReader.setCellData(xlfile, xlSheetName, 356, 8, resPass);
				return true;
				
			}else{
				
				excelReader.setCellData(xlfile, xlSheetName, 356, 8, resFail);
				return false;
			}


					
			}
		
		
		
		
		public boolean  checkSavingShortagesinStockVoucherWithRmaItemTestWareHosue() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException{		
			
			
			excelReader=new ExcelReader(POJOUtility.getExcelPath());
			
			// 11th Row

			click(select11thRow_1stColumn);
			Thread.sleep(2000);
			enterWarehousettxt.sendKeys(Keys.CONTROL, "a");
			Thread.sleep(2000);
			enterWarehousettxt.sendKeys(Keys.BACK_SPACE);
			enterText(enterWarehousettxt, excelReader.getCellData(xlSheetName, 363, 5));
			Thread.sleep(3000);
			tab(enterWarehousettxt);
			
			enterText(enterItemttxt, excelReader.getCellData(xlSheetName, 364, 5));
			Thread.sleep(2000);
			tab(enterItemttxt);
			Thread.sleep(2000);
			
			enterText(enterQuantitytxt, excelReader.getCellData(xlSheetName, 365, 5));
			tab(enterQuantitytxt);
			enterText(enterRatetxt, excelReader.getCellData(xlSheetName, 366, 5));
			tab(enterRatetxt);

			click(select11thRow_10thColumn);
			
			Thread.sleep(1000);
			//enterRmatxt.sendKeys(Keys.SPACE);
			Thread.sleep(1000);

			getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(rmaScreenTitle));

			String actRMAPopupIsDisplaying = Boolean.toString(rmaScreenTitle.isDisplayed());
			String expRMAPopupIsDisplaying = excelReader.getCellData(xlSheetName, 363, 6);
			excelReader.setCellData(xlfile, xlSheetName, 363, 7, actRMAPopupIsDisplaying);

			System.out.println("RMA Popup isDisplaying  : " + actRMAPopupIsDisplaying + "  Value Expected  "
					+ expRMAPopupIsDisplaying);
			
			click(rmaSearchGridField);
			Thread.sleep(1000);
			
			int count = rmaSerialNos.size();
			
			ArrayList<String> SearchRMA = new ArrayList<String>();
			
			for (int i = 0; i < count; i++) 
			{
				String data = rmaSerialNos.get(i).getText();
				SearchRMA.add(data);
				
				if (data.equalsIgnoreCase(excelReader.getCellData(xlSheetName, 367, 5)) || data.equalsIgnoreCase(excelReader.getCellData(xlSheetName, 368, 5)) ||
						data.equalsIgnoreCase(excelReader.getCellData(xlSheetName, 369, 5)) || data.equalsIgnoreCase(excelReader.getCellData(xlSheetName, 370, 5)) || 
						data.equalsIgnoreCase(excelReader.getCellData(xlSheetName, 371, 5)) || data.equalsIgnoreCase(excelReader.getCellData(xlSheetName, 372, 5))) 
				{
					rmaSerialNoCheckBoxes.get(i).click();
				}
			}
			
			String actSearchRMASerialNoList = SearchRMA.toString();
			String expSearchRMASerialNoList = excelReader.getCellData(xlSheetName, 364, 6); 
			excelReader.setCellData(xlfile, xlSheetName, 364, 7, actSearchRMASerialNoList);
			
			System.out.println("actSearchRMASerialNoList       :"+actSearchRMASerialNoList);
			System.out.println("expSearchRMASerialNoList       :"+expSearchRMASerialNoList);
			
			
			//click(searchRmaSelectAllGridCheckBox);
			click(searchRmaOkBtn);
			
			int serialcount	=rmaSerialNoList.size();
			ArrayList<String> rmaSerialnos = new ArrayList<String>();
			for (int i = 0; i < serialcount; i++) {
				String data=rmaSerialNoList.get(i).getText();
				rmaSerialnos.add(data);
			}
			
			String actRmaSerialNo 	=rmaSerialnos.toString();
			String expRmaSerialNo	=excelReader.getCellData(xlSheetName, 365, 6);
			excelReader.setCellData(xlfile, xlSheetName, 365, 7, actRmaSerialNo);
			
			System.out.println("actRmaSerialNo       :"+actRmaSerialNo);
			System.out.println("expRmaSerialNo       :"+expRmaSerialNo);
			
		
			click(RMAPopupOkBtn);	
			Thread.sleep(2000);
			tab(enterRmatxt);
			
			Thread.sleep(2000);
			int row11List=Row11ShortageInStockVoucherBodyList.size();
			System.err.println(row11List);
			ArrayList<String> row11ShortageStockList = new ArrayList<String>();
			for(int i=0 ; i < row11List ;i++)
		  	{
		  		String data=Row11ShortageInStockVoucherBodyList.get(i).getText();
		  		row11ShortageStockList.add(data);
		  	}
			
			String actrow11ShortageStockList = row11ShortageStockList.toString();
			String exprow11ShortageStockList = excelReader.getCellData(xlSheetName, 366, 6);
			excelReader.setCellData(xlfile, xlSheetName, 366, 7, actrow11ShortageStockList);
			
			System.out.println("actrow11ShortageStockList       :"+actrow11ShortageStockList);		
			System.out.println("exprow11ShortageStockList       :"+exprow11ShortageStockList);
			
			String docno=documentNumberTxt.getAttribute("value");
			Thread.sleep(2000);
			click(voucherSaveBtn);

			/* String actMessage = SavingInBackgroundTrial();

             HashSet<String> expMsg = new HashSet();

             expMsg.add(excelReader.getCellData(xlSheetName, 367, 6));
             expMsg.add(excelReader.getCellData(xlSheetName, 368, 6));
             excelReader.setCellData(xlfile, xlSheetName, 367, 7, actMessage);
             
             String expMessage = expMsg.toString();

			System.out.println("Actual Message    : " + actMessage);
			System.out.println("Expected Message  : " + expMessage);*/
			
			/*boolean actSaving=SavingInBackground(docno);
			boolean expSaving=true;*/
			
			String expMessage1= excelReader.getCellData(xlSheetName, 367, 6);
			String expMessage2= excelReader.getCellData(xlSheetName, 368, 6);
			
			String actMessage = checkValidationMessage(expMessage1);
			excelReader.setCellData(xlfile, xlSheetName, 367, 7, actMessage);
			
		   
		   
		   System.out.println("SavingMessage  :  "+actMessage +" Value Expected : "+expMessage1+" "+expMessage2);
			
			
			if(actRMAPopupIsDisplaying.equalsIgnoreCase(expRMAPopupIsDisplaying) && actSearchRMASerialNoList.equalsIgnoreCase(expSearchRMASerialNoList) 
					&& actRmaSerialNo.equalsIgnoreCase(expRmaSerialNo) && actMessage.startsWith(expMessage1) && actMessage.endsWith(expMessage2) && actrow11ShortageStockList.equalsIgnoreCase(exprow11ShortageStockList)){
				click(new_CloseBtn);			
				Thread.sleep(1000);
				click(voucherhomeCloseBtn);
				excelReader.setCellData(xlfile, xlSheetName, 363, 8, resPass);
				return true;
				
			}else{
				
				click(new_CloseBtn);			
				Thread.sleep(1000);
				click(voucherhomeCloseBtn);
				excelReader.setCellData(xlfile, xlSheetName, 363, 8, resFail);
				return false;
			}


					
			}
		
		
		
		public boolean  checkSavingPurchaseReturnsVoucherWithBrItemHydSecWareHosueInINVUser() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException{
			
			excelReader=new ExcelReader(POJOUtility.getExcelPath());
			
			/*Thread.sleep(3000);
			click(userNameDisplayLogo);
			Thread.sleep(1000);
			click(logoutOption);		
			Thread.sleep(3000);
			
			String unamelt=excelReader.getCellData(xlSheetName, 373, 5);

			String pawslt=excelReader.getCellData(xlSheetName, 374, 5);
			
			LoginPage.enterUserName(unamelt);	     
	        LoginPage.enterPassword(pawslt);
	        
			
			 Select company = new Select(companyDropDownList);
			 company.selectByValue(excelReader.getCellData(xlSheetName, 375, 5));
			 Thread.sleep(2000);
			 
	  
	        LoginPage.clickOnSignInBtn();  */
			
			reLogin(excelReader.getCellData(xlSheetName, 373, 5), excelReader.getCellData(xlSheetName, 374, 5));
	        
	        Thread.sleep(3000);
	        
	        System.out.println("Signing");
	        
	        // Login User Name is Verify
	        
	        getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(userNameDisplay));
			String actUserInfo                      = userNameDisplay.getText();
			String expUserInfo                      = excelReader.getCellData(xlSheetName, 373, 6);	
			excelReader.setCellData(xlfile, xlSheetName, 373, 7, actUserInfo);

			System.out.println("Login With Valid User and Check User Name Display Value Actual : ." + actUserInfo + ". Value Expected : " + expUserInfo);
			
			
			// Login Company Name is Verify
			Thread.sleep(1000);
			click(companyLogo);
			getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(companyName));
			String actGetLoginCompanyNameInformation = companyName.getText();
			String actGetLoginCompanyName            = actGetLoginCompanyNameInformation.substring(0, 19);
			String expGetLoginCompanyName            = excelReader.getCellData(xlSheetName, 374, 6); 
			excelReader.setCellData(xlfile, xlSheetName, 374, 7, actGetLoginCompanyName);
			companyLogo.click();
			
			System.out.println("Company Name Display Value Actual                : ." + actGetLoginCompanyName +  ". Value Expected : " + expGetLoginCompanyName);
			
			
			Thread.sleep(1000);
			
			
			click(financialsMenu);
			click(financialsTransactionMenu);
			click(financialsTransactionsPurchaseMenu);
			click(financialsTransactionsPurchaseMenuPurchasesReturns);
			click(newBtn);	
			
			checkValidationMessage("Screen opened");
			
			click(purchaseReturnsDatetxt);
			purchaseReturnsDatetxt.sendKeys(Keys.CONTROL, "a");
			Thread.sleep(2000);
			purchaseReturnsDatetxt.sendKeys(Keys.BACK_SPACE);
			enterText(purchaseReturnsDatetxt, excelReader.getCellData(xlSheetName, 376, 5));
			tab(purchaseReturnsDatetxt);
			
			Thread.sleep(2000);
			enterText(purchasesReturnsAccounttxt, excelReader.getCellData(xlSheetName, 377, 5));
			tab(purchasesReturnsAccounttxt);
			Thread.sleep(2000);
			enterText(purchasesReturnsvendorAccounttxt, excelReader.getCellData(xlSheetName, 378, 5));
			Thread.sleep(2000);
			//click(purchasesReturnsDepartmenttxt);
			enterText(purchasesReturnsDepartmenttxt, excelReader.getCellData(xlSheetName, 379, 5));
			Thread.sleep(1000);
			tab(purchasesReturnsDepartmenttxt);			
			Thread.sleep(2000);
			
			click(select1stRow_1stColumn);
			enterText(enterWarehousettxt, excelReader.getCellData(xlSheetName, 380, 5));
			Thread.sleep(2000);
			tab(enterWarehousettxt);
			
			enterText(enterItemttxt, excelReader.getCellData(xlSheetName, 381, 5));
			Thread.sleep(2000);
			tab(enterItemttxt);
			
			enterText(enterQuantitytxt, excelReader.getCellData(xlSheetName, 382, 5));
			tab(enterQuantitytxt);
			enterText(enterRatetxt, excelReader.getCellData(xlSheetName, 383, 5));
			tab(enterRatetxt);
			Thread.sleep(2000);
			
			click(select1stRow_9thColumn);			
			Thread.sleep(2000);
			
			String actRow1BatchNo		=batchPopUPRow1BatchNoTxt.getText();
			String expRow1BatchNo		=excelReader.getCellData(xlSheetName, 375, 6);
			excelReader.setCellData(xlfile, xlSheetName, 375, 7, actRow1BatchNo);
			
			String actRow2BatchNo		=batchPopUPRow2BatchNoTxt.getText();
			String expRow2BatchNo		=excelReader.getCellData(xlSheetName, 376, 6);
			excelReader.setCellData(xlfile, xlSheetName, 376, 7, actRow2BatchNo);
			
			String actRow1Baseqty		=batchPopUPRow1BaseQuantityTxt.getText();
			String expRow1Baseqty		=excelReader.getCellData(xlSheetName, 377, 6);
			excelReader.setCellData(xlfile, xlSheetName, 377, 7, actRow1Baseqty);
			
			String actRow1Rate			=batchPopUPRow1RateTxt.getText();
			String expRow1Rate			=excelReader.getCellData(xlSheetName, 378, 6);
			excelReader.setCellData(xlfile, xlSheetName, 378, 7, actRow1Rate);
			
			String actRow2Baseqty		=batchPopUPRow2BaseQuantityTxt.getText();
			String expRow2Baseqty		=excelReader.getCellData(xlSheetName, 379, 6);
			excelReader.setCellData(xlfile, xlSheetName, 379, 7, actRow2Baseqty);
			
			String actRow2Rate			=batchPopUPRow2RateTxt.getText();
			String expRow2Rate			=excelReader.getCellData(xlSheetName, 380, 6);
			excelReader.setCellData(xlfile, xlSheetName, 380, 7, actRow2Rate);
			
		
			System.out.println("actRow1BatchNo             :"+actRow1BatchNo);
			System.out.println("expRow1BatchNo             :"+expRow1BatchNo);
			
			System.out.println("actRow2BatchNo             :"+actRow2BatchNo);
			System.out.println("expRow2BatchNo             :"+expRow2BatchNo);		
			
			
			System.out.println("actRow1Baseqty             :"+actRow1Baseqty);
			System.out.println("expRow1Baseqty             :"+expRow1Baseqty);
			
			System.out.println("actRow1Rate                :"+actRow1Rate);
			System.out.println("expRow1Rate                :"+expRow1Rate);
			
			System.out.println("actRow2Baseqty             :"+actRow2Baseqty);
			System.out.println("expRow2Baseqty             :"+expRow2Baseqty);
			
			System.out.println("actRow2Rate                :"+actRow2Rate);
			System.out.println("expRow2Rate                :"+expRow2Rate);
			
			getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(batchPickOnFIFOIcon));
			
			String actBatchpopupIsDisplaying = Boolean.toString(batchPickOnFIFOIcon.isDisplayed());
			String expBatchpopupIsDisplaying = excelReader.getCellData(xlSheetName, 381, 6);
			excelReader.setCellData(xlfile, xlSheetName, 381, 7, actBatchpopupIsDisplaying);
			
			System.out.println("Batch Popup isDisplaying  : " + actBatchpopupIsDisplaying + "  Value Expected  "
					+ expBatchpopupIsDisplaying);
			click(batchPickOnFIFOIcon);
			click(batchOkIcon);

			// 2nd Row
			
			click(select2ndRow_1stColumn);
			Thread.sleep(2000);
			enterWarehousettxt.sendKeys(Keys.CONTROL, "a");
			Thread.sleep(2000);
			enterWarehousettxt.sendKeys(Keys.BACK_SPACE);
			enterText(enterWarehousettxt, excelReader.getCellData(xlSheetName, 384, 5));
			Thread.sleep(3000);
			tab(enterWarehousettxt);
			
			enterText(enterItemttxt, excelReader.getCellData(xlSheetName, 385, 5));
			Thread.sleep(2000);
			tab(enterItemttxt);
			Thread.sleep(2000);	
			
			enterText(enterQuantitytxt, excelReader.getCellData(xlSheetName, 386, 5));
			tab(enterQuantitytxt);
			enterText(enterRatetxt, excelReader.getCellData(xlSheetName, 387, 5));
			tab(enterRatetxt);
			
			click(select2ndRow_9thColumn);
			
			Thread.sleep(2000);
			
			String actRow1BatchNoSecLocation		=batchPopUPRow1BatchNoTxt.getText();
			String expRow1BatchNoSecLocation		=excelReader.getCellData(xlSheetName, 382, 6);
			excelReader.setCellData(xlfile, xlSheetName, 382, 7, actRow1BatchNoSecLocation);
		
			
			String actRow1BaseqtySecLocation		=batchPopUPRow1BaseQuantityTxt.getText();
			String expRow1BaseqtySecLocation		=excelReader.getCellData(xlSheetName, 383, 6);
			excelReader.setCellData(xlfile, xlSheetName, 383, 7, actRow1BaseqtySecLocation);
			
			String actRow1RateSecLocation			=batchPopUPRow1RateTxt.getText();
			String expRow1RateSecLocation			=excelReader.getCellData(xlSheetName, 384, 6);
			excelReader.setCellData(xlfile, xlSheetName, 384, 7, actRow1RateSecLocation);
			
		
		
			System.out.println("actRow1BatchNoSecLocation             :"+actRow1BatchNoSecLocation);
			System.out.println("expRow1BatchNoSecLocation             :"+expRow1BatchNoSecLocation);			
			
			System.out.println("actRow1BaseqtySecLocation             :"+actRow1BaseqtySecLocation);
			System.out.println("expRow1BaseqtySecLocation             :"+expRow1BaseqtySecLocation);
			
			System.out.println("actRow1RateSecLocation                :"+actRow1RateSecLocation);
			System.out.println("expRow1RateSecLocation                :"+expRow1RateSecLocation);
			
		
			
			getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(batchPickOnFIFOIcon));
			
			String actBatchpopupIsDisplaying1 = Boolean.toString(batchPickOnFIFOIcon.isDisplayed());
			String expBatchpopupIsDisplaying1 = excelReader.getCellData(xlSheetName, 385, 6);
			excelReader.setCellData(xlfile, xlSheetName, 385, 7, actBatchpopupIsDisplaying1);
			
			System.out.println("Batch Popup isDisplaying  : " + actBatchpopupIsDisplaying1 + "  Value Expected  "
					+ expBatchpopupIsDisplaying1);
			click(batchPickOnFIFOIcon);
			click(batchOkIcon);
			
			if(actRow1BatchNo.equalsIgnoreCase(expRow1BatchNo) && actRow2BatchNo.equalsIgnoreCase(expRow2BatchNo) 
					&& actRow1Baseqty.equalsIgnoreCase(expRow1Baseqty) && actRow1Rate.equalsIgnoreCase(expRow1Rate) 
					&& actRow2Baseqty.equalsIgnoreCase(expRow2Baseqty) && actRow2Rate.equalsIgnoreCase(expRow2Rate)
					&& actRow1BatchNoSecLocation.equalsIgnoreCase(expRow1BatchNoSecLocation) 
					&& actRow1BaseqtySecLocation.equalsIgnoreCase(expRow1BaseqtySecLocation) 
					&& actRow1RateSecLocation.equalsIgnoreCase(expRow1RateSecLocation)){
				
				excelReader.setCellData(xlfile, xlSheetName, 373, 8, resPass);
				
				return true;
			}
				else{
					
					excelReader.setCellData(xlfile, xlSheetName, 373, 8, resFail);
					return false;
				} 
		
		
		}
		
		
		public boolean  checkSavingPurchaseReturnsVoucherWithFifoItemHydSecWareHosueInINVUser() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException{		
			
		excelReader=new ExcelReader(POJOUtility.getExcelPath());
		// 3rd Row

		click(select3rdRow_1stColumn);
		Thread.sleep(2000);
		enterWarehousettxt.sendKeys(Keys.CONTROL, "a");
		Thread.sleep(2000);
		enterWarehousettxt.sendKeys(Keys.BACK_SPACE);
		enterText(enterWarehousettxt, excelReader.getCellData(xlSheetName, 388, 5));
		Thread.sleep(3000);
		tab(enterWarehousettxt);
		
		enterText(enterItemttxt, excelReader.getCellData(xlSheetName, 389, 5));
		Thread.sleep(2000);
		tab(enterItemttxt);
		Thread.sleep(2000);
		
		enterText(enterQuantitytxt, excelReader.getCellData(xlSheetName, 390, 5));
		tab(enterQuantitytxt);
		enterText(enterRatetxt, excelReader.getCellData(xlSheetName, 391, 5));
		tab(enterRatetxt);
		
		click(select3rdRow_10thColumn);

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(binSearchTxt));

		String actBinpopupIsDisplaying = Boolean.toString(binSearchTxt.isDisplayed());
		String expBinpopupIsDisplaying = excelReader.getCellData(xlSheetName, 388, 6);
		excelReader.setCellData(xlfile, xlSheetName, 388, 7, actBinpopupIsDisplaying);


		System.out.println("Bin Popup isDisplaying  : " + actBinpopupIsDisplaying + "  Value Expected  "
				+ expBinpopupIsDisplaying);

		click(binSearchBtn);
		Thread.sleep(3000);
		click(binAutoAllocateBtn);
		Thread.sleep(2000);
		click(binOkBtn);

		// 4th Row

		click(select4thRow_1stColumn);
		Thread.sleep(2000);
		enterWarehousettxt.sendKeys(Keys.CONTROL, "a");
		Thread.sleep(2000);
		enterWarehousettxt.sendKeys(Keys.BACK_SPACE);
		enterText(enterWarehousettxt, excelReader.getCellData(xlSheetName, 392, 5));
		Thread.sleep(3000);
		tab(enterWarehousettxt);
		
		enterText(enterItemttxt, excelReader.getCellData(xlSheetName, 393, 5));
		Thread.sleep(2000);
		tab(enterItemttxt);
		Thread.sleep(2000);
		
		enterText(enterQuantitytxt, excelReader.getCellData(xlSheetName, 394, 5));
		tab(enterQuantitytxt);
		enterText(enterRatetxt, excelReader.getCellData(xlSheetName, 395, 5));
		tab(enterRatetxt);
		
		
		click(select4thRow_10thColumn);

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(binSearchTxt));

		String actBinpopupIsDisplaying1 = Boolean.toString(binSearchTxt.isDisplayed());
		String expBinpopupIsDisplaying1 = excelReader.getCellData(xlSheetName, 389, 6);
		excelReader.setCellData(xlfile, xlSheetName, 389, 7, actBinpopupIsDisplaying1);


		System.out.println("Bin Popup isDisplaying  : " + actBinpopupIsDisplaying1 + "  Value Expected  "
				+ expBinpopupIsDisplaying1);

		click(binSearchBtn);
		Thread.sleep(3000);
		click(binAutoAllocateBtn);
		Thread.sleep(2000);
		click(binOkBtn);

		if (actBinpopupIsDisplaying.equalsIgnoreCase(expBinpopupIsDisplaying)
				&& actBinpopupIsDisplaying1.equalsIgnoreCase(expBinpopupIsDisplaying1)) {
			
			excelReader.setCellData(xlfile, xlSheetName, 388, 8, resPass);
			return true;

		} else {
			excelReader.setCellData(xlfile, xlSheetName, 388, 8, resFail);
			return false;
		}

	}
		
		public boolean  checkSavingPurchaseReturnsVoucherWithRmaItemHydWareHosueInINVUser() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException{		
			
			excelReader=new ExcelReader(POJOUtility.getExcelPath());	
		// 5th Row

		click(select5thRow_1stColumn);
		Thread.sleep(2000);
		enterWarehousettxt.sendKeys(Keys.CONTROL, "a");
		Thread.sleep(2000);
		enterWarehousettxt.sendKeys(Keys.BACK_SPACE);
		enterText(enterWarehousettxt, excelReader.getCellData(xlSheetName, 396, 5));
		Thread.sleep(3000);
		tab(enterWarehousettxt);
		
		enterText(enterItemttxt, excelReader.getCellData(xlSheetName, 397, 5));
		Thread.sleep(2000);
		tab(enterItemttxt);
		Thread.sleep(2000);
		
		enterText(enterQuantitytxt, excelReader.getCellData(xlSheetName, 398, 5));
		tab(enterQuantitytxt);
		enterText(enterRatetxt, excelReader.getCellData(xlSheetName, 399, 5));
		tab(enterRatetxt);
		
		click(select5thRow_11thColumn);
		
		Thread.sleep(2000);
		
		//enterRmatxt.sendKeys(Keys.SPACE);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(rmaScreenTitle));

		String actRMAPopupIsDisplaying = Boolean.toString(rmaScreenTitle.isDisplayed());
		String expRMAPopupIsDisplaying = excelReader.getCellData(xlSheetName, 396, 6);
		excelReader.setCellData(xlfile, xlSheetName, 396, 7, actRMAPopupIsDisplaying);


		System.out.println("RMA Popup isDisplaying  : " + actRMAPopupIsDisplaying + "  Value Expected  "
				+ expRMAPopupIsDisplaying);
		
		
		Thread.sleep(1000);
		click(rmaSearchGridField);
		Thread.sleep(1000);
		
		int count = rmaSerialNos.size();
		
		ArrayList<String> SearchRMA = new ArrayList<String>();
		
		for (int i = 0; i < count; i++) 
		{
			String data = rmaSerialNos.get(i).getText();
			SearchRMA.add(data);
			
			if (data.equalsIgnoreCase(excelReader.getCellData(xlSheetName, 400, 5))) 
			{
				rmaSerialNoCheckBoxes.get(i).click();
			}
		}
		
		String actSearchRMASerialNoList = SearchRMA.toString();
		String expSearchRMASerialNoList = excelReader.getCellData(xlSheetName, 397, 6); 
		excelReader.setCellData(xlfile, xlSheetName, 397, 7, actSearchRMASerialNoList);

		
		System.out.println("actSearchRMASerialNoList       :"+actSearchRMASerialNoList);
		System.out.println("expSearchRMASerialNoList       :"+expSearchRMASerialNoList);
		
		
		//click(searchRmaSelectAllGridCheckBox);
		click(searchRmaOkBtn);
		
		int serialcount	=rmaSerialNoList.size();
		ArrayList<String> rmaSerialnos = new ArrayList<String>();
		for (int i = 0; i < serialcount; i++) {
			String data=rmaSerialNoList.get(i).getText();
			rmaSerialnos.add(data);
		}
		
		String actRmaSerialNo 	=rmaSerialnos.toString();
		String expRmaSerialNo	=excelReader.getCellData(xlSheetName, 398, 6);
		excelReader.setCellData(xlfile, xlSheetName, 398, 7, actRmaSerialNo);

		
		System.out.println("actRmaSerialNo       :"+actRmaSerialNo);
		System.out.println("expRmaSerialNo       :"+expRmaSerialNo);
		
	
		click(RMAPopupOkBtn);	
		Thread.sleep(2000);
		tab(enterRmatxt);
		
		Thread.sleep(2000);
		int row5List=Row5ShortageInStockVoucherBodyList.size();
		System.err.println(row5List);
		ArrayList<String> row5ShortageStockList = new ArrayList<String>();
		for(int i=0 ; i < row5List ;i++)
	  	{
	  		String data=Row5ShortageInStockVoucherBodyList.get(i).getText();
	  		row5ShortageStockList.add(data);
	  	}
		
		String actrow5ShortageStockList = row5ShortageStockList.toString();
		String exprow5ShortageStockList = excelReader.getCellData(xlSheetName, 399, 6);
		excelReader.setCellData(xlfile, xlSheetName, 399, 7, actrow5ShortageStockList);

		
		System.out.println("actrow5ShortageStockList       :"+actrow5ShortageStockList);		
		System.out.println("exprow5ShortageStockList       :"+exprow5ShortageStockList);
		
		
		
		
		
		if(actRMAPopupIsDisplaying.equalsIgnoreCase(expRMAPopupIsDisplaying) && actSearchRMASerialNoList.equalsIgnoreCase(expSearchRMASerialNoList) 
				&& actRmaSerialNo.equalsIgnoreCase(expRmaSerialNo) && actrow5ShortageStockList.equalsIgnoreCase(exprow5ShortageStockList)){
			
			excelReader.setCellData(xlfile, xlSheetName, 396, 8, resPass);
			return true;
			
		}else{
			
			excelReader.setCellData(xlfile, xlSheetName, 396, 8, resFail);
			return false;
		}


				
		}
		
		public boolean  checkSavingPurchaseReturnsVoucherWithRmaItemSECWareHosueInINVUser() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException{		
			
			
		excelReader=new ExcelReader(POJOUtility.getExcelPath());	
		// 6th Row

		click(select6thRow_1stColumn);
		Thread.sleep(2000);
		enterWarehousettxt.sendKeys(Keys.CONTROL, "a");
		Thread.sleep(2000);
		enterWarehousettxt.sendKeys(Keys.BACK_SPACE);
		enterText(enterWarehousettxt, excelReader.getCellData(xlSheetName, 401, 5));
		Thread.sleep(3000);
		tab(enterWarehousettxt);
		
		enterText(enterItemttxt, excelReader.getCellData(xlSheetName, 402, 5));
		Thread.sleep(2000);
		tab(enterItemttxt);
		Thread.sleep(2000);
		
		enterText(enterQuantitytxt, excelReader.getCellData(xlSheetName, 403, 5));
		tab(enterQuantitytxt);
		enterText(enterRatetxt, excelReader.getCellData(xlSheetName, 404, 5));
		tab(enterRatetxt);
		
		click(select6thRow_11thColumn);
		
		Thread.sleep(2000);

		
		//enterRmatxt.sendKeys(Keys.SPACE);
		Thread.sleep(1000);


		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(rmaScreenTitle));

		String actRMAPopupIsDisplaying = Boolean.toString(rmaScreenTitle.isDisplayed());
		String expRMAPopupIsDisplaying = excelReader.getCellData(xlSheetName, 401, 6);
		excelReader.setCellData(xlfile, xlSheetName, 401, 7, actRMAPopupIsDisplaying);

		System.out.println("RMA Popup isDisplaying  : " + actRMAPopupIsDisplaying + "  Value Expected  "
				+ expRMAPopupIsDisplaying);
		
		click(rmaSearchGridField);
		Thread.sleep(1000);
		
		int count = rmaSerialNos.size();
		
		ArrayList<String> SearchRMA = new ArrayList<String>();
		
		for (int i = 0; i < count; i++) 
		{
			String data = rmaSerialNos.get(i).getText();
			SearchRMA.add(data);
			
			if (data.equalsIgnoreCase(excelReader.getCellData(xlSheetName, 405, 5))) 
			{
				rmaSerialNoCheckBoxes.get(i).click();
			}
		}
		
		String actSearchRMASerialNoList = SearchRMA.toString();
		String expSearchRMASerialNoList = excelReader.getCellData(xlSheetName, 402, 6); 
		excelReader.setCellData(xlfile, xlSheetName, 402, 7, actSearchRMASerialNoList);
		
		System.out.println("actSearchRMASerialNoList       :"+actSearchRMASerialNoList);
		System.out.println("expSearchRMASerialNoList       :"+expSearchRMASerialNoList);
		
		
		//click(searchRmaSelectAllGridCheckBox);
		click(searchRmaOkBtn);
		
		int serialcount	=rmaSerialNoList.size();
		ArrayList<String> rmaSerialnos = new ArrayList<String>();
		for (int i = 0; i < serialcount; i++) {
			String data=rmaSerialNoList.get(i).getText();
			rmaSerialnos.add(data);
		}
		
		String actRmaSerialNo 	=rmaSerialnos.toString();
		String expRmaSerialNo	=excelReader.getCellData(xlSheetName, 403, 6);
		excelReader.setCellData(xlfile, xlSheetName, 403, 7, actRmaSerialNo);
		
		System.out.println("actRmaSerialNo       :"+actRmaSerialNo);
		System.out.println("expRmaSerialNo       :"+expRmaSerialNo);
		
	
		click(RMAPopupOkBtn);	
		Thread.sleep(2000);
		tab(enterRmatxt);
		
		Thread.sleep(2000);
		int row6List=Row6ShortageInStockVoucherBodyList.size();
		System.err.println(row6List);
		ArrayList<String> row6ShortageStockList = new ArrayList<String>();
		for(int i=0 ; i < row6List ;i++)
	  	{
	  		String data=Row6ShortageInStockVoucherBodyList.get(i).getText();
	  		row6ShortageStockList.add(data);
	  	}
		
		String actrow6ShortageStockList = row6ShortageStockList.toString();
		String exprow6ShortageStockList = excelReader.getCellData(xlSheetName, 404, 6);
		excelReader.setCellData(xlfile, xlSheetName, 404, 7, actrow6ShortageStockList);
		
		System.out.println("actrow6ShortageStockList       :"+actrow6ShortageStockList);		
		System.out.println("exprow6ShortageStockList       :"+exprow6ShortageStockList);
		
		
		String docno=documentNumberTxt.getAttribute("value");
		Thread.sleep(2000);
		click(voucherSaveBtn);

		/*String actMessage = SavingInBackgroundTrial();

        HashSet<String> expMsg = new HashSet();

        expMsg.add(excelReader.getCellData(xlSheetName, 405, 6));
        expMsg.add(excelReader.getCellData(xlSheetName, 406, 6));
        excelReader.setCellData(xlfile, xlSheetName, 405, 7, actMessage);
        
        String expMessage = expMsg.toString();

		System.out.println("Actual Message    : " + actMessage);
		System.out.println("Expected Message  : " + expMessage);*/
		
		/*boolean actSaving=SavingInBackground(docno);
		boolean expSaving=true;*/
		
		String expMessage1= excelReader.getCellData(xlSheetName, 405, 6);
		String expMessage2= excelReader.getCellData(xlSheetName, 406, 6);
		
		String actMessage = checkValidationMessage(expMessage1);
		excelReader.setCellData(xlfile, xlSheetName, 405, 7, actMessage);
		
	   
	   
	   System.out.println("SavingMessage  :  "+actMessage +" Value Expected : "+expMessage1+" "+expMessage2);
		
		
		
		if(actRMAPopupIsDisplaying.equalsIgnoreCase(expRMAPopupIsDisplaying) && actSearchRMASerialNoList.equalsIgnoreCase(expSearchRMASerialNoList) 
				&& actRmaSerialNo.equalsIgnoreCase(expRmaSerialNo) && actrow6ShortageStockList.equalsIgnoreCase(exprow6ShortageStockList)
				&& actMessage.startsWith(expMessage1) && actMessage.endsWith(expMessage2)){
			click(new_CloseBtn);			
			Thread.sleep(1000);
			click(voucherhomeCloseBtn);
			excelReader.setCellData(xlfile, xlSheetName, 401, 8, resPass);
			
			return true;
			
		}else{
			
			click(new_CloseBtn);			
			Thread.sleep(1000);
			click(voucherhomeCloseBtn);
			excelReader.setCellData(xlfile, xlSheetName, 401, 8, resFail);
			return false;
		}
				
	}
		
		
		
		
		public boolean  checkSavingSalesReturnsVoucherWithAllItemsINVUser() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException
		{
		
		
		excelReader=new ExcelReader(POJOUtility.getExcelPath());
		
		
		
	    click(financialsMenu);
		click(financialsTransactionMenu);
		click(financialsTransactionsSalesMenu);
		click(financialsTransactionsSalesMenuSalesReturn);
		click(newBtn);

		checkValidationMessage("Screen opened");

		click(salesDatetxt);
		salesDatetxt.sendKeys(Keys.CONTROL, "a");
		Thread.sleep(2000);
		salesDatetxt.sendKeys(Keys.BACK_SPACE);
		enterText(salesDatetxt, excelReader.getCellData(xlSheetName, 407, 5));
		tab(salesDatetxt);
		Thread.sleep(2000);
		enterText(salesAccounttxt, excelReader.getCellData(xlSheetName, 408, 5));
		tab(salesAccounttxt);
		Thread.sleep(2000);
		enterText(customerAccounttxt, excelReader.getCellData(xlSheetName, 409, 5));
		Thread.sleep(2000);
		click(customerAccounttxt);
		enterText(salesDepartmenttxt, excelReader.getCellData(xlSheetName, 410, 5));
		Thread.sleep(1000);
		tab(salesDepartmenttxt);
		Thread.sleep(3000);

		//1st Row
		
		click(select1stRow_1stColumn);
		Thread.sleep(2000);
		enterWarehousettxt.sendKeys(Keys.CONTROL, "a");
		Thread.sleep(2000);
		enterWarehousettxt.sendKeys(Keys.BACK_SPACE);
		enterText(enterWarehousettxt, excelReader.getCellData(xlSheetName, 411, 5));
		Thread.sleep(3000);
		tab(enterWarehousettxt);
		enterText(enterItemttxt, excelReader.getCellData(xlSheetName, 412, 5));
		Thread.sleep(2000);
		tab(enterItemttxt);
		Thread.sleep(2000);

		
		enterText(enterQuantitytxt, excelReader.getCellData(xlSheetName, 413, 5));
		tab(enterQuantitytxt);
		enterText(enterRatetxt, excelReader.getCellData(xlSheetName, 414, 5));
		tab(enterRatetxt);
		
		click(select1stRow_9thColumn);
		
		enterText(enterBatchtxt, excelReader.getCellData(xlSheetName, 415, 5));
		tab(enterBatchtxt);

		// 2nd Row

		click(select2ndRow_1stColumn);
		Thread.sleep(2000);
		enterWarehousettxt.sendKeys(Keys.CONTROL, "a");
		Thread.sleep(2000);
		enterWarehousettxt.sendKeys(Keys.BACK_SPACE);
		enterText(enterWarehousettxt, excelReader.getCellData(xlSheetName, 416, 5));
		Thread.sleep(3000);
		tab(enterWarehousettxt);
		enterText(enterItemttxt, excelReader.getCellData(xlSheetName, 417, 5));
		Thread.sleep(2000);
		tab(enterItemttxt);
		Thread.sleep(2000);

		enterText(enterQuantitytxt, excelReader.getCellData(xlSheetName, 418, 5));
		tab(enterQuantitytxt);
		enterText(enterRatetxt, excelReader.getCellData(xlSheetName, 419, 5));
		tab(enterRatetxt);

		click(select2ndRow_9thColumn);

		enterText(enterBatchtxt, excelReader.getCellData(xlSheetName, 420, 5));
		tab(enterBatchtxt);

		// 3rd Row

		click(select3rdRow_1stColumn);
		Thread.sleep(2000);
		enterWarehousettxt.sendKeys(Keys.CONTROL, "a");
		Thread.sleep(2000);
		enterWarehousettxt.sendKeys(Keys.BACK_SPACE);
		enterText(enterWarehousettxt, excelReader.getCellData(xlSheetName, 421, 5));
		Thread.sleep(3000);
		tab(enterWarehousettxt);
		enterText(enterItemttxt, excelReader.getCellData(xlSheetName, 422, 5));
		Thread.sleep(2000);
		tab(enterItemttxt);
		Thread.sleep(2000);

		enterText(enterQuantitytxt, excelReader.getCellData(xlSheetName, 423, 5));
		tab(enterQuantitytxt);
		enterText(enterRatetxt, excelReader.getCellData(xlSheetName, 424, 5));
		tab(enterRatetxt);

		click(select3rdRow_10thColumn);

		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(binSearchTxt));

		String actBinpopupIsDisplaying = Boolean.toString(binSearchTxt.isDisplayed());
		String expBinpopupIsDisplaying = excelReader.getCellData(xlSheetName, 407, 6);
		excelReader.setCellData(xlfile, xlSheetName, 407, 7, actBinpopupIsDisplaying);

		System.out.println("Bin Popup isDisplaying  : " + actBinpopupIsDisplaying + "  Value Expected  "
				+ expBinpopupIsDisplaying);

		click(binSearchBtn);
		Thread.sleep(3000);
		click(binAutoAllocateBtn);
		Thread.sleep(2000);
		click(binOkBtn);
		
		
		// 4th Row
		
		click(select4thRow_1stColumn);
		enterWarehousettxt.sendKeys(Keys.CONTROL, "a");
		Thread.sleep(2000);
		enterWarehousettxt.sendKeys(Keys.BACK_SPACE);
		enterText(enterWarehousettxt, excelReader.getCellData(xlSheetName, 425, 5));
		Thread.sleep(2000);
		tab(enterWarehousettxt);
		enterText(enterItemttxt, excelReader.getCellData(xlSheetName, 426, 5));
		Thread.sleep(2000);
		tab(enterItemttxt);
		enterText(enterQuantitytxt, excelReader.getCellData(xlSheetName, 427, 5));
		tab(enterQuantitytxt);
		enterText(enterRatetxt, excelReader.getCellData(xlSheetName, 428, 5));
		tab(enterRatetxt);
		click(select4thRow_10thColumn);
		
	
		Thread.sleep(2000);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(binSearchTxt));

		String actBinpopupIsDisplaying1 = Boolean.toString(binSearchTxt.isDisplayed());
		String expBinpopupIsDisplaying1 = excelReader.getCellData(xlSheetName, 408, 6);
		excelReader.setCellData(xlfile, xlSheetName, 408, 7, actBinpopupIsDisplaying1);

		System.out.println("Bin Popup isDisplaying  : " + actBinpopupIsDisplaying1 + "  Value Expected  "
				+ expBinpopupIsDisplaying1);

		click(binSearchBtn);
		Thread.sleep(3000);
		click(binAutoAllocateBtn);
		Thread.sleep(2000);
		click(binOkBtn);
		Thread.sleep(2000);
		

		// 5th Row

		click(select5thRow_1stColumn);
		enterWarehousettxt.sendKeys(Keys.CONTROL, "a");
		Thread.sleep(2000);
		enterWarehousettxt.sendKeys(Keys.BACK_SPACE);
		enterText(enterWarehousettxt, excelReader.getCellData(xlSheetName, 429, 5));
		Thread.sleep(2000);
		tab(enterWarehousettxt);
		enterText(enterItemttxt, excelReader.getCellData(xlSheetName, 430, 5));
		Thread.sleep(2000);
		tab(enterItemttxt);
		enterText(enterQuantitytxt, excelReader.getCellData(xlSheetName, 431, 5));
		tab(enterQuantitytxt);
		enterText(enterRatetxt, excelReader.getCellData(xlSheetName, 432, 5));
		tab(enterRatetxt);
		click(select5thRow_11thColumn);
		Thread.sleep(2000);
		String actual5throwAvgRate 			= select5thRow_SalesReturnsAvgRate.getText();
		String exp5throwAvgRate			    = excelReader.getCellData(xlSheetName, 409, 6);
		excelReader.setCellData(xlfile, xlSheetName, 409, 7, actual5throwAvgRate);
		
		
		String actual5throwOverallAvgRate   = select5thRow_SalesReturnsOverallAvgRate.getText();
		String exp5throwoverallAvgRate      = excelReader.getCellData(xlSheetName, 410, 6);
		excelReader.setCellData(xlfile, xlSheetName, 410, 7, actual5throwOverallAvgRate);

		
		//enterRmatxt.sendKeys(Keys.SPACE);
		Thread.sleep(1000);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(rmaScreenTitle));

		String actRMAPopupIsDisplaying = Boolean.toString(rmaScreenTitle.isDisplayed());
		String expRMAPopupIsDisplaying = excelReader.getCellData(xlSheetName, 411, 6);
		excelReader.setCellData(xlfile, xlSheetName, 411, 7, actRMAPopupIsDisplaying);

		System.out.println("RMA Popup isDisplaying  : " + actRMAPopupIsDisplaying + "  Value Expected  "
				+ expRMAPopupIsDisplaying);
		
		enterText(rmaSerialNumberTxtField, excelReader.getCellData(xlSheetName, 433, 5));
		click(rmaAddBtn);
		Thread.sleep(2000);
		click(RMAPopupOkBtn);
		Thread.sleep(3000);
		tab(enterRmatxt);
		//tab(enterBatCodetxt);

		// 6th Row
		Thread.sleep(1000);
		click(select6thRow_1stColumn);
		enterWarehousettxt.sendKeys(Keys.CONTROL, "a");
		Thread.sleep(2000);
		enterWarehousettxt.sendKeys(Keys.BACK_SPACE);
		enterText(enterWarehousettxt, excelReader.getCellData(xlSheetName, 434, 5));
		Thread.sleep(2000);
		tab(enterWarehousettxt);
		enterText(enterItemttxt, excelReader.getCellData(xlSheetName, 435, 5));
		Thread.sleep(2000);
		tab(enterItemttxt);
		enterText(enterQuantitytxt, excelReader.getCellData(xlSheetName, 436, 5));
		tab(enterQuantitytxt);
		enterText(enterRatetxt, excelReader.getCellData(xlSheetName, 437, 5));
		tab(enterRatetxt);
		click(select6thRow_11thColumn);
		Thread.sleep(2000);
		
		String actual6throwAvgRate 				= select6thRow_SalesReturnsAvgRate.getText();
		String exp6throwAvgRate 				= excelReader.getCellData(xlSheetName, 412, 6);
		excelReader.setCellData(xlfile, xlSheetName, 412, 7, actual6throwAvgRate);
		
		String actual6throwOverallAvgRate		= select6thRow_SalesReturnsOverallAvgRate.getText();
		String exp6throwoverallAvgRate			= excelReader.getCellData(xlSheetName, 413, 6);
		excelReader.setCellData(xlfile, xlSheetName, 413, 7, actual6throwOverallAvgRate);
		

		System.out.println("actual5throwAvgRate       	 :" + actual5throwAvgRate);
		System.out.println("exp5throwAvgRate             :" + exp5throwAvgRate);

		System.out.println("actual5throwOverallAvgRate   :" + actual5throwOverallAvgRate);
		System.out.println("exp5throwoverallAvgRate      	:" + exp5throwoverallAvgRate);

		System.out.println("actual6throwAvgRate      	 :" + actual6throwAvgRate);
		System.out.println("exp6throwAvgRate          	 :" + exp6throwAvgRate);

		System.out.println("actual6throwOverallAvgRate   :" + actual6throwOverallAvgRate);
		System.out.println("exp6throwoverallAvgRate      :" + exp6throwoverallAvgRate);

		
		
		//enterRmatxt.sendKeys(Keys.SPACE);
		Thread.sleep(1000);    
		
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(rmaScreenTitle));

		String actRMAPopupIsDisplaying1 = Boolean.toString(rmaScreenTitle.isDisplayed());
	    String expRMAPopupIsDisplaying1 = excelReader.getCellData(xlSheetName, 414, 6);
		excelReader.setCellData(xlfile, xlSheetName, 414, 7, actRMAPopupIsDisplaying1);

		System.out.println("RMA Popup isDisplaying  : " + actRMAPopupIsDisplaying1 + "  Value Expected  "
				+ expRMAPopupIsDisplaying1);
		enterText(rmaSerialNumberTxtField, excelReader.getCellData(xlSheetName, 438, 5));
		click(rmaAddBtn);
		Thread.sleep(2000);
		click(RMAPopupOkBtn);
		Thread.sleep(2000);		
		tab(enterRmatxt);
		
		String docno=documentNumberTxt.getAttribute("value");
		
		Thread.sleep(3000);
		click(voucherSaveBtn);

		/*String actMessage = SavingInBackgroundTrial();

        HashSet<String> expMsg = new HashSet();

        //expMsg.add(excelReader.getCellData(xlSheetName, 415, 6));
        expMsg.add(excelReader.getCellData(xlSheetName, 416, 6));
        excelReader.setCellData(xlfile, xlSheetName, 415, 7, actMessage);
        
        String expMessage = expMsg.toString();


		System.out.println("Actual Message    : " + actMessage);
		System.out.println("Expected Message  : " + expMessage);*/
		
		/*boolean actSaving=SavingInBackground(docno);
		boolean expSaving=true;*/
		
		String expMessage1= excelReader.getCellData(xlSheetName, 415, 6);
		String expMessage2= excelReader.getCellData(xlSheetName, 416, 6);
		
		String actMessage = checkValidationMessage(expMessage1);
		excelReader.setCellData(xlfile, xlSheetName, 415, 7, actMessage);
		
	   
	   
	   System.out.println("SavingMessage  :  "+actMessage +" Value Expected : "+expMessage1+" "+expMessage2);

		if (actMessage.startsWith(expMessage1) && actMessage.endsWith(expMessage2) && actual5throwAvgRate.equalsIgnoreCase(exp5throwAvgRate)
				&& actual5throwOverallAvgRate.equalsIgnoreCase(exp5throwoverallAvgRate)
				&& actual6throwAvgRate.equalsIgnoreCase(exp6throwAvgRate)
				&& actual6throwOverallAvgRate.equalsIgnoreCase(exp6throwoverallAvgRate)) {
			
			click(new_CloseBtn);			
			Thread.sleep(1000);
			click(voucherhomeCloseBtn);
			excelReader.setCellData(xlfile, xlSheetName, 407, 8, resPass);
			return true;
			
		} else {
			click(new_CloseBtn);			
			Thread.sleep(1000);
			click(voucherhomeCloseBtn);
			excelReader.setCellData(xlfile, xlSheetName, 407, 8, resFail);
			return false;

		}
		
		
			
	}
		
		public boolean	validteItemQueryStocksInBatchItemAfterSavingSalesReturnsVocher() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
			
			excelReader=new ExcelReader(POJOUtility.getExcelPath());
			
			click(inventoryMenu);
			click(InventoryReportsMenu);
			click(itemqueryMenu);
			click(itemTxt);
			Thread.sleep(2000);
			enterText(itemTxt, excelReader.getCellData(xlSheetName, 439, 5));
			Thread.sleep(3000);
			click(getStockBtn);
			Thread.sleep(3000);

			String actlOpenStockQuantity  = getText(openStockQuantityTxt);
			String expOpenStockQuantity   = excelReader.getCellData(xlSheetName, 439, 6);
			excelReader.setCellData(xlfile, xlSheetName, 439, 7, actlOpenStockQuantity);

			String actlCurrentStock       = getText(currentStockTxt);
			String expCurrentStock        = excelReader.getCellData(xlSheetName, 440, 6);
			excelReader.setCellData(xlfile, xlSheetName, 440, 7, actlCurrentStock);

			String actlavgStockRate       = getText(avgStockRateTxt);
			String expavgStockRate        = excelReader.getCellData(xlSheetName, 441, 6);
			excelReader.setCellData(xlfile, xlSheetName, 441, 7, actlavgStockRate);

			String actlstockValueTxt      = getText(stockValueTxt);
			String expstockValueTxt       = excelReader.getCellData(xlSheetName, 442, 6);
			excelReader.setCellData(xlfile, xlSheetName, 442, 7, actlstockValueTxt);
			

			System.out.println("=====Stocks In Batch Items======");
			

			System.out.println("actlOpenStockQuantity :" + actlOpenStockQuantity);
			System.out.println("expOpenStockQuantity  :" + expOpenStockQuantity);

			System.out.println("actlCurrentStock      :" + actlCurrentStock);
			System.out.println("expCurrentStock       :" + expCurrentStock);

			System.out.println("actlavgStockRate      :" + actlavgStockRate);
			System.out.println("expavgStockRate       :" + expavgStockRate);

			System.out.println("actlstockValueTxt     :" + actlstockValueTxt);
			System.out.println("expstockValueTxt      :" + expstockValueTxt);
			
			
			ScrollToElement(balanceByWarehouseBtn);
			click(balanceByWarehouseBtn);

			Thread.sleep(3000);

			int count = row1BalanceByWareHouseList.size();

			ArrayList<String> warehouseRow1 = new ArrayList<String>();

			for (int i = 0; i < count; i++) {
				String data = row1BalanceByWareHouseList.get(i).getText();
				warehouseRow1.add(data);
			}

			String actwarehouseRow1List = warehouseRow1.toString();
			String expWarehouseRow1List = excelReader.getCellData(xlSheetName, 443, 6);
			excelReader.setCellData(xlfile, xlSheetName, 443, 7, actwarehouseRow1List);

			int count1 = row2BalanceByWareHouseList.size();

			ArrayList<String> warehouseRow2 = new ArrayList<String>();

			for (int i = 0; i < count1; i++) {
				String data = row2BalanceByWareHouseList.get(i).getText();
				warehouseRow2.add(data);
			}

			String actwarehouseRow2List = warehouseRow2.toString();
			String expWarehouseRow2List = excelReader.getCellData(xlSheetName, 444, 6);
			excelReader.setCellData(xlfile, xlSheetName, 444, 7, actwarehouseRow2List);
			
			System.out.println(actwarehouseRow1List);
			System.out.println(expWarehouseRow1List);

			System.out.println(actwarehouseRow2List);
			System.out.println(expWarehouseRow2List);
			

			if (actlOpenStockQuantity.equalsIgnoreCase(expOpenStockQuantity)
					&& actlCurrentStock.equalsIgnoreCase(expCurrentStock)
					&& actlavgStockRate.equalsIgnoreCase(expavgStockRate)
					&& actlstockValueTxt.equalsIgnoreCase(expstockValueTxt)
					&& actwarehouseRow1List.equalsIgnoreCase(expWarehouseRow1List)
					&& actwarehouseRow2List.equalsIgnoreCase(expWarehouseRow2List))
					 {
				
				excelReader.setCellData(xlfile, xlSheetName, 439, 8, resPass);
				return true;
			} else {
				
				excelReader.setCellData(xlfile, xlSheetName, 439, 8, resFail);
				return false;
			}


		}
		
		public boolean validteItemQueryStocksInFifoItemAfterSavingSalesReturnsVocher() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {

			excelReader=new ExcelReader(POJOUtility.getExcelPath());
			
			click(itemTxt);
			Thread.sleep(2000);
			itemTxt.sendKeys(Keys.CONTROL, "a");
			Thread.sleep(2000);
			itemTxt.sendKeys(Keys.BACK_SPACE);
			enterText(itemTxt, excelReader.getCellData(xlSheetName, 445, 5));
			Thread.sleep(3000);
			click(getStockBtn);
			Thread.sleep(3000);

			String actlOpenStockQuantity  = getText(openStockQuantityTxt);
			String expOpenStockQuantity   = excelReader.getCellData(xlSheetName, 445, 6);
			excelReader.setCellData(xlfile, xlSheetName, 445, 7, actlOpenStockQuantity);

			String actlCurrentStock       = getText(currentStockTxt);
			String expCurrentStock        = excelReader.getCellData(xlSheetName, 446, 6);
			excelReader.setCellData(xlfile, xlSheetName, 446, 7, actlCurrentStock);

			String actlavgStockRate       = getText(avgStockRateTxt);
			String expavgStockRate        = excelReader.getCellData(xlSheetName, 447, 6);
			excelReader.setCellData(xlfile, xlSheetName, 447, 7, actlavgStockRate);

			String actlstockValueTxt      = getText(stockValueTxt);
			String expstockValueTxt       = excelReader.getCellData(xlSheetName, 448, 6);
			excelReader.setCellData(xlfile, xlSheetName, 448, 7, actlstockValueTxt);

			System.out.println("=====Stocks In FIFO Items======");

			System.out.println("actlOpenStockQuantity :" + actlOpenStockQuantity);
			System.out.println("expOpenStockQuantity  :" + expOpenStockQuantity);

			System.out.println("actlCurrentStock      :" + actlCurrentStock);
			System.out.println("expCurrentStock       :" + expCurrentStock);

			System.out.println("actlavgStockRate      :" + actlavgStockRate);
			System.out.println("expavgStockRate       :" + expavgStockRate);

			System.out.println("actlstockValueTxt     :" + actlstockValueTxt);
			System.out.println("expstockValueTxt      :" + expstockValueTxt);
			
			ScrollToElement(balanceByWarehouseBtn);
			//click(balanceByWarehouseBtn);

			Thread.sleep(3000);

			int count = row1BalanceByWareHouseList.size();

			ArrayList<String> warehouseRow1 = new ArrayList<String>();

			for (int i = 0; i < count; i++) {
				String data = row1BalanceByWareHouseList.get(i).getText();
				warehouseRow1.add(data);
			}

			String actwarehouseRow1List = warehouseRow1.toString();
			String expWarehouseRow1List = excelReader.getCellData(xlSheetName, 449, 6);
			excelReader.setCellData(xlfile, xlSheetName, 449, 7, actwarehouseRow1List);

			int count1 = row2BalanceByWareHouseList.size();

			ArrayList<String> warehouseRow2 = new ArrayList<String>();

			for (int i = 0; i < count1; i++) {
				String data = row2BalanceByWareHouseList.get(i).getText();
				warehouseRow2.add(data);
			}

			String actwarehouseRow2List = warehouseRow2.toString();
			String expWarehouseRow2List = excelReader.getCellData(xlSheetName, 450, 6);
			excelReader.setCellData(xlfile, xlSheetName, 450, 7, actwarehouseRow2List);
			
			System.out.println(actwarehouseRow1List);
			System.out.println(expWarehouseRow1List);

			System.out.println(actwarehouseRow2List);
			System.out.println(expWarehouseRow2List);
			

			if (actlOpenStockQuantity.equalsIgnoreCase(expOpenStockQuantity)
					&& actlCurrentStock.equalsIgnoreCase(expCurrentStock)
					&& actlavgStockRate.equalsIgnoreCase(expavgStockRate)
					&& actlstockValueTxt.equalsIgnoreCase(expstockValueTxt)
					&& actwarehouseRow1List.equalsIgnoreCase(expWarehouseRow1List)
					&& actwarehouseRow2List.equalsIgnoreCase(expWarehouseRow2List))
					 {
				
				excelReader.setCellData(xlfile, xlSheetName, 445, 8, resPass);
				return true;
			} else {
				
				excelReader.setCellData(xlfile, xlSheetName, 445, 8, resFail);
				return false;
			}

		}
		
		
		public boolean validteItemQueryStocksInWAItemAfterSavingSalesReturnsVocher() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {

			
			excelReader=new ExcelReader(POJOUtility.getExcelPath());
			
			click(itemTxt);
			Thread.sleep(2000);
			itemTxt.sendKeys(Keys.CONTROL, "a");
			Thread.sleep(2000);
			itemTxt.sendKeys(Keys.BACK_SPACE);
			enterText(itemTxt, excelReader.getCellData(xlSheetName, 451, 5));
			Thread.sleep(3000);
			click(getStockBtn);
			Thread.sleep(3000);

			String actlOpenStockQuantity = getText(openStockQuantityTxt);
			String expOpenStockQuantity  = excelReader.getCellData(xlSheetName, 451, 6);
			excelReader.setCellData(xlfile, xlSheetName, 451, 7, actlOpenStockQuantity);

			String actlCurrentStock      = getText(currentStockTxt);
			String expCurrentStock       = excelReader.getCellData(xlSheetName, 452, 6);
			excelReader.setCellData(xlfile, xlSheetName, 452, 7, actlCurrentStock);

			String actlavgStockRate      = getText(avgStockRateTxt);
			String expavgStockRate       = excelReader.getCellData(xlSheetName, 453, 6);
			excelReader.setCellData(xlfile, xlSheetName, 453, 7, actlavgStockRate);

			String actlstockValueTxt     = getText(stockValueTxt);
			String expstockValueTxt      = excelReader.getCellData(xlSheetName, 454, 6);
			excelReader.setCellData(xlfile, xlSheetName, 454, 7, actlstockValueTxt);

			ScrollToElement(balanceByWarehouseBtn);
			//click(balanceByWarehouseBtn);

			Thread.sleep(3000);

			int count = row1BalanceByWareHouseList.size();

			ArrayList<String> warehouseRow1 = new ArrayList<String>();

			for (int i = 0; i < count; i++) {
				String data = row1BalanceByWareHouseList.get(i).getText();
				warehouseRow1.add(data);
			}

			String actwarehouseRow1List = warehouseRow1.toString();
			String expWarehouseRow1List = excelReader.getCellData(xlSheetName, 455, 6);
			excelReader.setCellData(xlfile, xlSheetName, 455, 7, actwarehouseRow1List);

			int count1 = row2BalanceByWareHouseList.size();

			ArrayList<String> warehouseRow2 = new ArrayList<String>();

			for (int i = 0; i < count1; i++) {
				String data = row2BalanceByWareHouseList.get(i).getText();
				warehouseRow2.add(data);
			}

			String actwarehouseRow2List = warehouseRow2.toString();
			String expWarehouseRow2List = excelReader.getCellData(xlSheetName, 456, 6);
			excelReader.setCellData(xlfile, xlSheetName, 456, 7, actwarehouseRow2List);

			
			System.out.println("=====Stocks In WA Items======");

			System.out.println("actlOpenStockQuantity :" + actlOpenStockQuantity);
			System.out.println("expOpenStockQuantity  :" + expOpenStockQuantity);

			System.out.println("actlCurrentStock      :" + actlCurrentStock);
			System.out.println("expCurrentStock       :" + expCurrentStock);

			System.out.println("actlavgStockRate      :" + actlavgStockRate);
			System.out.println("expavgStockRate       :" + expavgStockRate);

			System.out.println("actlstockValueTxt     :" + actlstockValueTxt);
			System.out.println("expstockValueTxt      :" + expstockValueTxt);

			System.out.println("===========Balance By Warehouse=======");

			System.out.println(actwarehouseRow1List);
			System.out.println(expWarehouseRow1List);

			System.out.println(actwarehouseRow2List);
			System.out.println(expWarehouseRow2List);

			

			if (actlOpenStockQuantity.equalsIgnoreCase(expOpenStockQuantity)
					&& actlCurrentStock.equalsIgnoreCase(expCurrentStock)
					&& actlavgStockRate.equalsIgnoreCase(expavgStockRate)
					&& actlstockValueTxt.equalsIgnoreCase(expstockValueTxt)
					&& actwarehouseRow1List.equalsIgnoreCase(expWarehouseRow1List)
					&& actwarehouseRow2List.equalsIgnoreCase(expWarehouseRow2List)) {
				
				excelReader.setCellData(xlfile, xlSheetName, 451, 8, resPass);
				return true;
			} else {
				
				excelReader.setCellData(xlfile, xlSheetName, 451, 8, resFail);
				return false;
			}

		}
		
		

	
		
		
		public boolean checkStockLedgerReportForBrBatchItemAfterSavingSalesReturnsVocher()
				throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {

			excelReader=new ExcelReader(POJOUtility.getExcelPath());
			
			//reLogin("inv", "su");
			
			click(inventoryMenu);
			click(InventoryReportsMenu);
			click(InventoryStockLedgerMenu);
			Thread.sleep(3000);

			int rowcount = stockLedgerHometableRowCount.size();

			System.out.println(rowcount);

			for (int i = 0; i < rowcount; i++) {
				String actName = ledgerHometableItemNamesList.get(i).getText();

				System.out.println(actName);

				if (actName.equalsIgnoreCase(excelReader.getCellData(xlSheetName, 457, 5))) {
					stockLedgerHometableItemChkboxList.get(i).click();
				}
			}
			
			dropDown(sl_DateOptionDropdown, excelReader.getCellData(xlSheetName, 458, 5));		
			Thread.sleep(2000);
			click(sl_OkBtn);
			Thread.sleep(2000);
			click(stockLedgerLastBtn);
			Thread.sleep(2000);

			String actStockLedgerBrBatchBalanceQty   = getText(stockLedgerBrBatchAfterSavingSalesReturnsBalanceQty);
			String expStockLedgerBrBatchBalanceQty   = excelReader.getCellData(xlSheetName, 457, 6);
			excelReader.setCellData(xlfile, xlSheetName, 457, 7, actStockLedgerBrBatchBalanceQty);

			String actStockLedgerBrBatchBalanceValue = getText(stockLedgerBrBatchAfterSavingSalesReturnsBalanceValue);
			String expStockLedgerBrBatchBalanceValue = excelReader.getCellData(xlSheetName, 458, 6);
			excelReader.setCellData(xlfile, xlSheetName, 458, 7, actStockLedgerBrBatchBalanceValue);

			String actStockLedgerBrBatchAvgRate      = getText(stockLedgerBrBatchAfterSavingSalesReturnsAvgRate);
			String expStockLedgerBrBatchAvgRate      = excelReader.getCellData(xlSheetName, 459, 6);
			excelReader.setCellData(xlfile, xlSheetName, 459, 7, actStockLedgerBrBatchAvgRate);

			System.out.println("actStockLedgerBrBatchBalanceQty        :" + actStockLedgerBrBatchBalanceQty);
			System.out.println("expStockLedgerBrBatchBalanceQty        :" + expStockLedgerBrBatchBalanceQty);

			System.out.println("actStockLedgerBrBatchBalanceValue      :" + actStockLedgerBrBatchBalanceValue);
			System.out.println("expStockLedgerBrBatchBalanceValue      :" + expStockLedgerBrBatchBalanceValue);

			System.out.println("actStockLedgerBrBatchAvgRate      	   :" + actStockLedgerBrBatchAvgRate);
			System.out.println("expStockLedgerBrBatchAvgRate    	   :" + expStockLedgerBrBatchAvgRate);
			
			
			click(stockLedgerFilterBtn);
			
			Thread.sleep(2000);
			
			click(stockLedgerFilterField);
			Thread.sleep(2000);

			getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(stockLedgerFilterText));

			String actFilterpopupIsDisplaying = Boolean.toString(stockLedgerFilterText.isDisplayed());
			String expFilterpopupIsDisplaying = excelReader.getCellData(xlSheetName, 460, 6);
			excelReader.setCellData(xlfile, xlSheetName, 460, 7, actFilterpopupIsDisplaying);

			System.out.println("Filter Popup isDisplaying  : " + actFilterpopupIsDisplaying + "  Value Expected  "
					+ expFilterpopupIsDisplaying);
			
			click(stockLedgerFilterWarehouseField);
			enterText(stockLedgerFilterWarehouseField, excelReader.getCellData(xlSheetName, 459, 5));
			Thread.sleep(2000);
			click(stockLedgerFilterOkBtn);
			Thread.sleep(1000);
			click(stockLedgerLastBtn);
			Thread.sleep(2000);
			
			String actstockLedgerBRItemHydBalanceQtyAfterSavingSalesReturns 		= getText(stockLedgerBRItemHydBalanceQtyAfterSavingSalesReturns);
			String expstockLedgerBRItemHydBalanceQtyAfterSavingSalesReturns 		= excelReader.getCellData(xlSheetName, 461, 6);
			excelReader.setCellData(xlfile, xlSheetName, 461, 7, actstockLedgerBRItemHydBalanceQtyAfterSavingSalesReturns);

			String actstockLedgerBRItemHydBalanceValueAfterSavingSalesReturns 	    = getText(stockLedgerBRItemHydBalanceValueAfterSavingSalesReturns);
			String expstockLedgerBRItemHydBalanceValueAfterSavingSalesReturns 	    = excelReader.getCellData(xlSheetName, 462, 6);
			excelReader.setCellData(xlfile, xlSheetName, 462, 7, actstockLedgerBRItemHydBalanceValueAfterSavingSalesReturns);

			String actstockLedgerBRItemHydAvgRateAfterSavingSalesReturns 		    = getText(stockLedgerBRItemHydAvgRateAfterSavingSalesReturns);
			String expstockLedgerBRItemHydAvgRateAfterSavingSalesReturns 		    = excelReader.getCellData(xlSheetName, 463, 6);
			excelReader.setCellData(xlfile, xlSheetName, 463, 7, actstockLedgerBRItemHydAvgRateAfterSavingSalesReturns);
			
			System.out.println("=========actstockLedger BRItem  HydWareHouse  Values============   : ");
			
			
			System.out.println("actstockLedgerBRItemHydBalanceQtyAfterSavingSalesReturns      :" + actstockLedgerBRItemHydBalanceQtyAfterSavingSalesReturns);
			System.out.println("expstockLedgerBRItemHydBalanceQtyAfterSavingSalesReturns      :" + expstockLedgerBRItemHydBalanceQtyAfterSavingSalesReturns);
			
			System.out.println("actstockLedgerBRItemHydBalanceValueAfterSavingSalesReturns    :" + actstockLedgerBRItemHydBalanceValueAfterSavingSalesReturns);
			System.out.println("expstockLedgerBRItemHydBalanceValueAfterSavingSalesReturns    :" + expstockLedgerBRItemHydBalanceValueAfterSavingSalesReturns);

			System.out.println("actstockLedgerBRItemHydAvgRateAfterSavingSalesReturns         :" + actstockLedgerBRItemHydAvgRateAfterSavingSalesReturns);
			System.out.println("expstockLedgerBRItemHydAvgRateAfterSavingSalesReturns         :" + expstockLedgerBRItemHydAvgRateAfterSavingSalesReturns);		
			
			click(report_CloseBtn);
			Thread.sleep(1000);
			click(stockLedgerHomePageFilterBtn);
			Thread.sleep(2000);
			click(stockLedgerFilterWarehouseField2);
			Thread.sleep(1000);
			click(stockLedgerFilterWarehouseField3);
			Thread.sleep(1500);
			stockLedgerFilterWarehouseField4.sendKeys(Keys.CONTROL, "a");
			Thread.sleep(1000);
			stockLedgerFilterWarehouseField4.sendKeys(Keys.BACK_SPACE);
			Thread.sleep(1000);
			enterText(stockLedgerFilterWarehouseField4, excelReader.getCellData(xlSheetName, 460, 5));
			Thread.sleep(1000);
			tab(stockLedgerFilterWarehouseField4);
			Thread.sleep(1500);
			click(stockLedgerFilterOkBtn);
			Thread.sleep(1000);
			click(sl_OkBtn);
			Thread.sleep(1000);
			click(stockLedgerLastBtn);
			Thread.sleep(2000);
			//click(stockLedgerPreviousBtn);
			
			
			
			String actstockLedgerBRItemSecBalanceQtyAfterSavingSalesReturns 		= getText(stockLedgerBRItemSecBalanceQtyAfterSavingSalesReturns);
			String expstockLedgerBRItemSecBalanceQtyAfterSavingSalesReturns 		= excelReader.getCellData(xlSheetName, 464, 6);
			excelReader.setCellData(xlfile, xlSheetName, 464, 7, actstockLedgerBRItemSecBalanceQtyAfterSavingSalesReturns);

			String actstockLedgerBRItemSecBalanceValueAfterSavingSalesReturns 	    = getText(stockLedgerBRItemSecBalanceValueAfterSavingSalesReturns);
			String expstockLedgerBRItemSecBalanceValueAfterSavingSalesReturns 	    = excelReader.getCellData(xlSheetName, 465, 6);
			excelReader.setCellData(xlfile, xlSheetName, 465, 7, actstockLedgerBRItemSecBalanceValueAfterSavingSalesReturns);

			String actstockLedgerBRItemSecAvgRateAfterAfterSavingSalesReturns 		= getText(stockLedgerBRItemSecAvgRateAfterAfterSavingSalesReturns);
			String expstockLedgerBRItemSecAvgRateAfterAfterSavingSalesReturns 		= excelReader.getCellData(xlSheetName, 466, 6);
			excelReader.setCellData(xlfile, xlSheetName, 466, 7, actstockLedgerBRItemSecAvgRateAfterAfterSavingSalesReturns);
			
			System.out.println("=========actstockLedgerbrItem  SEC WareHouse  Values============   : ");
			
			System.out.println("actstockLedgerBRItemSecBalanceQtyAfterSavingSalesReturns      :" + actstockLedgerBRItemSecBalanceQtyAfterSavingSalesReturns);
			System.out.println("expstockLedgerBRItemSecBalanceQtyAfterSavingSalesReturns      :" + expstockLedgerBRItemSecBalanceQtyAfterSavingSalesReturns);

			System.out.println("actstockLedgerBRItemSecBalanceValueAfterSavingSalesReturns    :" + actstockLedgerBRItemSecBalanceValueAfterSavingSalesReturns);
			System.out.println("expstockLedgerBRItemSecBalanceValueAfterSavingSalesReturns    :" + expstockLedgerBRItemSecBalanceValueAfterSavingSalesReturns);

			System.out.println("actstockLedgerBRItemSecAvgRateAfterAfterSavingSalesReturns    :" + actstockLedgerBRItemSecAvgRateAfterAfterSavingSalesReturns);
			System.out.println("expstockLedgerBRItemSecAvgRateAfterAfterSavingSalesReturns    :" + expstockLedgerBRItemSecAvgRateAfterAfterSavingSalesReturns);
		
			
			click(stockLedgerFilterBtn);
			Thread.sleep(1000);
			click(stockLedgerFilterField);
			Thread.sleep(1000);
			click(stockLedgerFilterRefreshBtn);
			Thread.sleep(1000);
			click(stockLedgerFilterOkBtn);
			
			Thread.sleep(1000);

			if (actStockLedgerBrBatchBalanceQty.equalsIgnoreCase(expStockLedgerBrBatchBalanceQty)
					&& actStockLedgerBrBatchBalanceValue.equalsIgnoreCase(expStockLedgerBrBatchBalanceValue)
					&& actStockLedgerBrBatchAvgRate.equalsIgnoreCase(expStockLedgerBrBatchAvgRate)
					&& actstockLedgerBRItemHydBalanceQtyAfterSavingSalesReturns.equalsIgnoreCase(expstockLedgerBRItemHydBalanceQtyAfterSavingSalesReturns)
					&& actstockLedgerBRItemHydBalanceValueAfterSavingSalesReturns.equalsIgnoreCase(expstockLedgerBRItemHydBalanceValueAfterSavingSalesReturns)
					&& actstockLedgerBRItemHydAvgRateAfterSavingSalesReturns.equalsIgnoreCase(expstockLedgerBRItemHydAvgRateAfterSavingSalesReturns)
					&& actstockLedgerBRItemSecBalanceQtyAfterSavingSalesReturns.equalsIgnoreCase(expstockLedgerBRItemSecBalanceQtyAfterSavingSalesReturns)
					&& actstockLedgerBRItemSecBalanceValueAfterSavingSalesReturns.equalsIgnoreCase(expstockLedgerBRItemSecBalanceValueAfterSavingSalesReturns)
					&& actstockLedgerBRItemSecAvgRateAfterAfterSavingSalesReturns.equalsIgnoreCase(expstockLedgerBRItemSecAvgRateAfterAfterSavingSalesReturns)) {

				click(report_CloseBtn);
				
				excelReader.setCellData(xlfile, xlSheetName, 457, 8, resPass);
				return true;
			} else {
				click(report_CloseBtn);
				excelReader.setCellData(xlfile, xlSheetName, 457, 8, resFail);
				return false;
			}

		}
		
		

		
		
		
		
		public boolean checkStockLedgerReportForFifoItemAfterSavingSalesReturnVocher() // values taken from item query
				throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {

			excelReader=new ExcelReader(POJOUtility.getExcelPath());
			
			Thread.sleep(3000);

			if (fifoItemCheckBox.isSelected() == false) {
				click(fifoItemCheckBox);
			}

			if (brBatchItemCheckBox.isSelected() == true) {
				click(brBatchItemCheckBox);
			}

			if (rmaItemCheckBox.isSelected() == true) {
				click(rmaItemCheckBox);

			}

			
			dropDown(sl_DateOptionDropdown, excelReader.getCellData(xlSheetName, 467, 5));		
			Thread.sleep(2000);
			click(sl_OkBtn);
			Thread.sleep(2000);
			click(stockLedgerLastBtn);
			Thread.sleep(2000);
 
			String actstockLedgerFifoItemAfterSavingSalesReturnsBalanceQty   = getText(stockLedgerFifoItemAfterSavingSalesReturnsBalanceQty);
			String expstockLedgerFifoItemAfterSavingSalesReturnsBalanceQty   = excelReader.getCellData(xlSheetName, 467, 6);
			excelReader.setCellData(xlfile, xlSheetName, 467, 7, actstockLedgerFifoItemAfterSavingSalesReturnsBalanceQty);

			String actstockLedgerFifoItemAfterSavingSalesReturnsBalanceValue = getText(stockLedgerFifoItemAfterSavingSalesReturnsBalanceValue);
			String expstockLedgerFifoItemAfterSavingSalesReturnsBalanceValue = excelReader.getCellData(xlSheetName, 468, 6);
			excelReader.setCellData(xlfile, xlSheetName, 468, 7, actstockLedgerFifoItemAfterSavingSalesReturnsBalanceValue);

			String actstockLedgerFifoItemAfterSavingSalesReturnsAvgRate      = getText(stockLedgerFifoItemAfterSavingSalesReturnsAvgRate);
			String expstockLedgerFifoItemAfterSavingSalesReturnsAvgRate      = excelReader.getCellData(xlSheetName, 469, 6);
			excelReader.setCellData(xlfile, xlSheetName, 469, 7, actstockLedgerFifoItemAfterSavingSalesReturnsAvgRate);

			System.out.println("actstockLedgerFifoItemAfterSavingSalesReturnsBalanceQty        :" + actstockLedgerFifoItemAfterSavingSalesReturnsBalanceQty);
			System.out.println("expstockLedgerFifoItemAfterSavingSalesReturnsBalanceQty        :" + expstockLedgerFifoItemAfterSavingSalesReturnsBalanceQty);

			System.out.println("actstockLedgerFifoItemAfterSavingSalesReturnsBalanceValue      :" + actstockLedgerFifoItemAfterSavingSalesReturnsBalanceValue);
			System.out.println("expstockLedgerFifoItemAfterSavingSalesReturnsBalanceValue      :" + expstockLedgerFifoItemAfterSavingSalesReturnsBalanceValue);

			System.out.println("actstockLedgerFifoItemAfterSavingSalesReturnsAvgRate      	   :" + actstockLedgerFifoItemAfterSavingSalesReturnsAvgRate);
			System.out.println("expstockLedgerFifoItemAfterSavingSalesReturnsAvgRate    	   :" + expstockLedgerFifoItemAfterSavingSalesReturnsAvgRate);
			
			
			click(stockLedgerFilterBtn);
			
			Thread.sleep(2000);
			click(stockLedgerFilterField);
			Thread.sleep(2000);

			getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(stockLedgerFilterText));

			String actFilterpopupIsDisplaying = Boolean.toString(stockLedgerFilterText.isDisplayed());
			String expFilterpopupIsDisplaying = excelReader.getCellData(xlSheetName, 470, 6);
			excelReader.setCellData(xlfile, xlSheetName, 470, 7, actFilterpopupIsDisplaying);

			System.out.println("Filter Popup isDisplaying  : " + actFilterpopupIsDisplaying + "  Value Expected  "
					+ expFilterpopupIsDisplaying);
			
			click(stockLedgerFilterWarehouseField);
			enterText(stockLedgerFilterWarehouseField, excelReader.getCellData(xlSheetName, 468, 5));
			Thread.sleep(2000);
			click(stockLedgerFilterOkBtn);
			Thread.sleep(1000);
			click(stockLedgerLastBtn);
			Thread.sleep(2000);
			
			String actstockLedgerFifoItemHydBalanceQtyAfterSavingSalesReturns 		= getText(stockLedgerFifoItemHydBalanceQtyAfterSavingSalesReturns);
			String expstockLedgerFifoItemHydBalanceQtyAfterSavingSalesReturns 		= excelReader.getCellData(xlSheetName, 471, 6);
			excelReader.setCellData(xlfile, xlSheetName, 471, 7, actstockLedgerFifoItemHydBalanceQtyAfterSavingSalesReturns);

			String actstockLedgerFifoItemHydBalanceValueAfterSavingSalesReturns 	= getText(stockLedgerFifoItemHydBalanceValueAfterSavingSalesReturns);
			String expstockLedgerFifoItemHydBalanceValueAfterSavingSalesReturns 	= excelReader.getCellData(xlSheetName, 472, 6);
			excelReader.setCellData(xlfile, xlSheetName, 472, 7, actstockLedgerFifoItemHydBalanceValueAfterSavingSalesReturns);

			String actstockLedgerFifoItemHydAvgRateAfterSavingSalesReturns 		    = getText(stockLedgerFifoItemHydAvgRateAfterSavingSalesReturns);
			String expstockLedgerFifoItemHydAvgRateAfterSavingSalesReturns 		    = excelReader.getCellData(xlSheetName, 473, 6);
			excelReader.setCellData(xlfile, xlSheetName, 473, 7, actstockLedgerFifoItemHydAvgRateAfterSavingSalesReturns);
			
			System.out.println("=========actstockLedgerFIFOItem  HydWareHouse  Values============   : ");
			
			System.out.println("actstockLedgerFifoItemHydBalanceQtyAfterSavingSalesReturns      :" + actstockLedgerFifoItemHydBalanceQtyAfterSavingSalesReturns);
			System.out.println("expstockLedgerFifoItemHydBalanceQtyAfterSavingSalesReturns      :" + expstockLedgerFifoItemHydBalanceQtyAfterSavingSalesReturns);
		
			System.out.println("actstockLedgerFifoItemHydBalanceValueAfterSavingSalesReturns    :" + actstockLedgerFifoItemHydBalanceValueAfterSavingSalesReturns);
			System.out.println("expstockLedgerFifoItemHydBalanceValueAfterSavingSalesReturns    :" + expstockLedgerFifoItemHydBalanceValueAfterSavingSalesReturns);

			System.out.println("actstockLedgerFifoItemHydAvgRateAfterSavingSalesReturns         :" + actstockLedgerFifoItemHydAvgRateAfterSavingSalesReturns);
			System.out.println("expstockLedgerFifoItemHydAvgRateAfterSavingSalesReturns         :" + expstockLedgerFifoItemHydAvgRateAfterSavingSalesReturns);		
			
			click(report_CloseBtn);
			Thread.sleep(1000);
			click(stockLedgerHomePageFilterBtn);
			Thread.sleep(2000);
			click(stockLedgerFilterWarehouseField2);
			Thread.sleep(1000);
			stockLedgerFilterWarehouseField3.sendKeys(Keys.CONTROL, "a");
			Thread.sleep(1000);
			stockLedgerFilterWarehouseField3.sendKeys(Keys.BACK_SPACE);
			Thread.sleep(1000);
			enterText(stockLedgerFilterWarehouseField3, excelReader.getCellData(xlSheetName, 469, 5));
			Thread.sleep(1000);
			click(stockLedgerFilterOkBtn);
			Thread.sleep(1000);
			click(sl_OkBtn);
			Thread.sleep(1000);
			click(stockLedgerLastBtn);
			Thread.sleep(2000);
			
			
			
			String actstockLedgerFifoItemSecBalanceQtyAfterSavingSalesReturns 		= getText(stockLedgerFifoItemSecBalanceQtyAfterSavingSalesReturns);
			String expstockLedgerFifoItemSecBalanceQtyAfterSavingSalesReturns 		= excelReader.getCellData(xlSheetName, 474, 6);
			excelReader.setCellData(xlfile, xlSheetName, 474, 7, actstockLedgerFifoItemSecBalanceQtyAfterSavingSalesReturns);

			String actstockLedgerFifoItemSecBalanceValueAfterSavingSalesReturns 	= getText(stockLedgerFifoItemSecBalanceValueAfterSavingSalesReturns);
			String expstockLedgerFifoItemSecBalanceValueAfterSavingSalesReturns 	= excelReader.getCellData(xlSheetName, 475, 6);
			excelReader.setCellData(xlfile, xlSheetName, 475, 7, actstockLedgerFifoItemSecBalanceValueAfterSavingSalesReturns);

			String actstockLedgerFifoItemSecAvgRateAfterAfterSavingSalesReturns     = getText(stockLedgerFifoItemSecAvgRateAfterAfterSavingSalesReturns);
			String expstockLedgerFifoItemSecAvgRateAfterAfterSavingSalesReturns 	= excelReader.getCellData(xlSheetName, 476, 6);
			excelReader.setCellData(xlfile, xlSheetName, 476, 7, actstockLedgerFifoItemSecAvgRateAfterAfterSavingSalesReturns);
			
			System.out.println("=========actstockLedgerFIFOItem  SEC WareHouse  Values============   : ");
			
			
			
			System.out.println("actstockLedgerFifoItemSecBalanceQtyAfterSavingSalesReturns      :" + actstockLedgerFifoItemSecBalanceQtyAfterSavingSalesReturns);
			System.out.println("expstockLedgerFifoItemSecBalanceQtyAfterSavingSalesReturns      :" + expstockLedgerFifoItemSecBalanceQtyAfterSavingSalesReturns);

			System.out.println("actstockLedgerFifoItemSecBalanceValueAfterSavingSalesReturns    :" + actstockLedgerFifoItemSecBalanceValueAfterSavingSalesReturns);
			System.out.println("expstockLedgerFifoItemSecBalanceValueAfterSavingSalesReturns    :" + expstockLedgerFifoItemSecBalanceValueAfterSavingSalesReturns);

			System.out.println("actstockLedgerFifoItemSecAvgRateAfterAfterSavingSalesReturns    :" + actstockLedgerFifoItemSecAvgRateAfterAfterSavingSalesReturns);
			System.out.println("expstockLedgerFifoItemSecAvgRateAfterAfterSavingSalesReturns    :" + expstockLedgerFifoItemSecAvgRateAfterAfterSavingSalesReturns);
		
			
			click(stockLedgerFilterBtn);
			Thread.sleep(1000);
			click(stockLedgerFilterField);
			Thread.sleep(1000);
			click(stockLedgerFilterRefreshBtn);
			Thread.sleep(1000);
			click(stockLedgerFilterOkBtn);
			
			Thread.sleep(1000);

			if (actstockLedgerFifoItemAfterSavingSalesReturnsBalanceQty.equalsIgnoreCase(expstockLedgerFifoItemAfterSavingSalesReturnsBalanceQty)
					&& actstockLedgerFifoItemAfterSavingSalesReturnsBalanceValue.equalsIgnoreCase(expstockLedgerFifoItemAfterSavingSalesReturnsBalanceValue)
					&& actstockLedgerFifoItemAfterSavingSalesReturnsAvgRate.equalsIgnoreCase(expstockLedgerFifoItemAfterSavingSalesReturnsAvgRate)
					&& actstockLedgerFifoItemHydBalanceQtyAfterSavingSalesReturns.equalsIgnoreCase(expstockLedgerFifoItemHydBalanceQtyAfterSavingSalesReturns)
					&& actstockLedgerFifoItemHydBalanceValueAfterSavingSalesReturns.equalsIgnoreCase(expstockLedgerFifoItemHydBalanceValueAfterSavingSalesReturns)
					&& actstockLedgerFifoItemHydAvgRateAfterSavingSalesReturns.equalsIgnoreCase(expstockLedgerFifoItemHydAvgRateAfterSavingSalesReturns)
					&& actstockLedgerFifoItemSecBalanceQtyAfterSavingSalesReturns.equalsIgnoreCase(expstockLedgerFifoItemSecBalanceQtyAfterSavingSalesReturns)
					&& actstockLedgerFifoItemSecBalanceValueAfterSavingSalesReturns.equalsIgnoreCase(expstockLedgerFifoItemSecBalanceValueAfterSavingSalesReturns)
					&& actstockLedgerFifoItemSecAvgRateAfterAfterSavingSalesReturns.equalsIgnoreCase(expstockLedgerFifoItemSecAvgRateAfterAfterSavingSalesReturns)) {

				click(report_CloseBtn);
				excelReader.setCellData(xlfile, xlSheetName, 467, 8, resPass);				
				return true;
			} else {
				
				click(report_CloseBtn);
				excelReader.setCellData(xlfile, xlSheetName, 467, 8, resFail);
				return false;
			}

		}
		
		

		
		
		public boolean checkStockLedgerReportForRmaItemAfterSavingSalesReturnvoucher()
				throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {

			
			excelReader=new ExcelReader(POJOUtility.getExcelPath());

			
			Thread.sleep(3000);

			if (fifoItemCheckBox.isSelected() == true) {
				click(fifoItemCheckBox);

			}

			if (brBatchItemCheckBox.isSelected() == true) {
				click(brBatchItemCheckBox);
			}

			if (rmaItemCheckBox.isSelected() == false) {
				click(rmaItemCheckBox);

			}
			
			dropDown(sl_DateOptionDropdown, excelReader.getCellData(xlSheetName, 477, 5));		
			Thread.sleep(2000);
			click(sl_OkBtn);
			Thread.sleep(2000);
			click(stockLedgerLastBtn);
			Thread.sleep(2000);
			//click(stockLedgerPreviousBtn);
			Thread.sleep(3000);

			String actstockLedgerRmaItemAfterSavingSalesReturnsBalanceQty   = getText(stockLedgerRmaItemAfterSavingSalesReturnsBalanceQty);
			String expstockLedgerRmaItemAfterSavingSalesReturnsBalanceQty   = excelReader.getCellData(xlSheetName, 477, 6);
			excelReader.setCellData(xlfile, xlSheetName, 477, 7, actstockLedgerRmaItemAfterSavingSalesReturnsBalanceQty);

			String actstockLedgerRmaItemAfterSavingSalesReturnsBalanceValue = getText(stockLedgerRmaItemAfterSavingSalesReturnsBalanceValue);
			String expstockLedgerRmaItemAfterSavingSalesReturnsBalanceValue = excelReader.getCellData(xlSheetName, 478, 6);
			excelReader.setCellData(xlfile, xlSheetName, 478, 7, actstockLedgerRmaItemAfterSavingSalesReturnsBalanceValue);

			String actstockLedgerRmaItemAfterSavingSalesReturnsAvgRate      = getText(stockLedgerRmaItemAfterSavingSalesReturnsAvgRate);
			String expstockLedgerRmaItemAfterSavingSalesReturnsAvgRate      = excelReader.getCellData(xlSheetName, 479, 6);
			excelReader.setCellData(xlfile, xlSheetName, 479, 7, actstockLedgerRmaItemAfterSavingSalesReturnsAvgRate);

			System.out.println("actstockLedgerRmaItemAfterSavingSalesReturnsBalanceQty        :" + actstockLedgerRmaItemAfterSavingSalesReturnsBalanceQty);
			System.out.println("expstockLedgerRmaItemAfterSavingSalesReturnsBalanceQty        :" + expstockLedgerRmaItemAfterSavingSalesReturnsBalanceQty);

			System.out.println("actstockLedgerRmaItemAfterSavingSalesReturnsBalanceValue      :" + actstockLedgerRmaItemAfterSavingSalesReturnsBalanceValue);
			System.out.println("expstockLedgerRmaItemAfterSavingSalesReturnsBalanceValue      :" + expstockLedgerRmaItemAfterSavingSalesReturnsBalanceValue);

			System.out.println("actstockLedgerRmaItemAfterSavingSalesReturnsAvgRate      	  :" + actstockLedgerRmaItemAfterSavingSalesReturnsAvgRate);
			System.out.println("expstockLedgerRmaItemAfterSavingSalesReturnsAvgRate    	      :" + expstockLedgerRmaItemAfterSavingSalesReturnsAvgRate);
			
			
			click(stockLedgerFilterBtn);
			
			Thread.sleep(2000);
		
			click(stockLedgerFilterField);
			
			Thread.sleep(2000);

			getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(stockLedgerFilterText));

			String actFilterpopupIsDisplaying = Boolean.toString(stockLedgerFilterText.isDisplayed());
			String expFilterpopupIsDisplaying = excelReader.getCellData(xlSheetName, 480, 6);
			excelReader.setCellData(xlfile, xlSheetName, 480, 7, actFilterpopupIsDisplaying);

			System.out.println("Filter Popup isDisplaying  : " + actFilterpopupIsDisplaying + "  Value Expected  "
					+ expFilterpopupIsDisplaying);
			
			click(stockLedgerFilterWarehouseField);
			enterText(stockLedgerFilterWarehouseField, excelReader.getCellData(xlSheetName, 478, 5));
			Thread.sleep(2000);
			click(stockLedgerFilterOkBtn);
			Thread.sleep(1000);
			click(stockLedgerLastBtn);
			Thread.sleep(2000);
			
			String actstockLedgerRmaItemHydBalanceQtyAfterSavingSalesReturns 	= getText(stockLedgerRmaItemHydBalanceQtyAfterSavingSalesReturns);
			String expstockLedgerRmaItemHydBalanceQtyAfterSavingSalesReturns 	= excelReader.getCellData(xlSheetName, 481, 6);
			excelReader.setCellData(xlfile, xlSheetName, 481, 7, actstockLedgerRmaItemHydBalanceQtyAfterSavingSalesReturns);

			String actstockLedgerRmaItemHydBalanceValueAfterSavingSalesReturns 	= getText(stockLedgerRmaItemHydBalanceValueAfterSavingSalesReturns);
			String expstockLedgerRmaItemHydBalanceValueAfterSavingSalesReturns 	= excelReader.getCellData(xlSheetName, 482, 6);
			excelReader.setCellData(xlfile, xlSheetName, 482, 7, actstockLedgerRmaItemHydBalanceValueAfterSavingSalesReturns);

			String actstockLedgerRmaItemHydAvgRateAfterSavingSalesReturns 		= getText(stockLedgerRmaItemHydAvgRateAfterSavingSalesReturns);
			String expstockLedgerRmaItemHydAvgRateAfterSavingSalesReturns 		= excelReader.getCellData(xlSheetName, 483, 6);
			excelReader.setCellData(xlfile, xlSheetName, 483, 7, actstockLedgerRmaItemHydAvgRateAfterSavingSalesReturns);
			
			System.out.println("=========actstockLedgerRmaItem  HydWareHouse  Values============   : ");
			
			
			System.out.println("actstockLedgerRmaItemHydBalanceQtyAfterSavingSalesReturns      :" + actstockLedgerRmaItemHydBalanceQtyAfterSavingSalesReturns);
			System.out.println("expstockLedgerRmaItemHydBalanceQtyAfterSavingSalesReturns      :" + expstockLedgerRmaItemHydBalanceQtyAfterSavingSalesReturns);
			
			System.out.println("actstockLedgerRmaItemHydBalanceValueAfterSavingSalesReturns    :" + actstockLedgerRmaItemHydBalanceValueAfterSavingSalesReturns);
			System.out.println("expstockLedgerRmaItemHydBalanceValueAfterSavingSalesReturns    :" + expstockLedgerRmaItemHydBalanceValueAfterSavingSalesReturns);

			System.out.println("actstockLedgerRmaItemHydAvgRateAfterSavingSalesReturns         :" + actstockLedgerRmaItemHydAvgRateAfterSavingSalesReturns);
			System.out.println("expstockLedgerRmaItemHydAvgRateAfterSavingSalesReturns         :" + expstockLedgerRmaItemHydAvgRateAfterSavingSalesReturns);		
			
			click(report_CloseBtn);
			Thread.sleep(1000);
			click(stockLedgerHomePageFilterBtn);
			Thread.sleep(2000);
			click(stockLedgerFilterWarehouseField2);
			Thread.sleep(1000);
			click(stockLedgerFilterWarehouseField3);
			Thread.sleep(1500);
			stockLedgerFilterWarehouseField4.sendKeys(Keys.CONTROL, "a");
			Thread.sleep(1000);
			stockLedgerFilterWarehouseField4.sendKeys(Keys.BACK_SPACE);
			Thread.sleep(1000);
			enterText(stockLedgerFilterWarehouseField4, excelReader.getCellData(xlSheetName, 479, 5));
			Thread.sleep(1000);
			tab(stockLedgerFilterWarehouseField4);
			Thread.sleep(1500);
			click(stockLedgerFilterOkBtn);
			Thread.sleep(1000);
			click(sl_OkBtn);
			Thread.sleep(1000);
			click(stockLedgerLastBtn);
			Thread.sleep(2000);
			click(stockLedgerPreviousBtn);
			Thread.sleep(2000);
			
			
			
			String actstockLedgerRmaItemSecBalanceQtyAfterSavingSalesReturns 		= getText(stockLedgerRmaItemSecBalanceQtyAfterSavingSalesReturns);
			String expstockLedgerRmaItemSecBalanceQtyAfterSavingSalesReturns 		= excelReader.getCellData(xlSheetName, 484, 6);
			excelReader.setCellData(xlfile, xlSheetName, 484, 7, actstockLedgerRmaItemSecBalanceQtyAfterSavingSalesReturns);

			String actstockLedgerRmaItemSecBalanceValueAfterSavingSalesReturns   	= getText(stockLedgerRmaItemSecBalanceValueAfterSavingSalesReturns);
			String expstockLedgerRmaItemSecBalanceValueAfterSavingSalesReturns 	    = excelReader.getCellData(xlSheetName, 485, 6);
			excelReader.setCellData(xlfile, xlSheetName, 485, 7, actstockLedgerRmaItemSecBalanceValueAfterSavingSalesReturns);

			String actstockLedgerRmaItemSecAvgRateAfterAfterSavingSalesReturns 		= getText(stockLedgerRmaItemSecAvgRateAfterAfterSavingSalesReturns);
			String expstockLedgerRmaItemSecAvgRateAfterAfterSavingSalesReturns 		= excelReader.getCellData(xlSheetName, 486, 6);
			excelReader.setCellData(xlfile, xlSheetName, 486, 7, actstockLedgerRmaItemSecAvgRateAfterAfterSavingSalesReturns);
			
			System.out.println("=========actstockLedgerRmaItem  SEC WareHouse  Values============   : ");
			
			System.out.println("actstockLedgerRmaItemSecBalanceQtyAfterSavingSalesReturns      :" + actstockLedgerRmaItemSecBalanceQtyAfterSavingSalesReturns);
			System.out.println("expstockLedgerRmaItemSecBalanceQtyAfterSavingSalesReturns      :" + expstockLedgerRmaItemSecBalanceQtyAfterSavingSalesReturns);

			System.out.println("actstockLedgerRmaItemSecBalanceValueAfterSavingSalesReturns    :" + actstockLedgerRmaItemSecBalanceValueAfterSavingSalesReturns);
			System.out.println("expstockLedgerRmaItemSecBalanceValueAfterSavingSalesReturns    :" + expstockLedgerRmaItemSecBalanceValueAfterSavingSalesReturns);

			System.out.println("actstockLedgerRmaItemSecAvgRateAfterAfterSavingSalesReturns    :" + actstockLedgerRmaItemSecAvgRateAfterAfterSavingSalesReturns);
			System.out.println("expstockLedgerRmaItemSecAvgRateAfterAfterSavingSalesReturns    :" + expstockLedgerRmaItemSecAvgRateAfterAfterSavingSalesReturns);
		
			
			click(stockLedgerFilterBtn);
			Thread.sleep(1000);
			click(stockLedgerFilterField);
			Thread.sleep(1000);
			click(stockLedgerFilterRefreshBtn);
			Thread.sleep(1000);
			click(stockLedgerFilterOkBtn);
			
			Thread.sleep(1000);

			if (actstockLedgerRmaItemAfterSavingSalesReturnsBalanceQty.equalsIgnoreCase(expstockLedgerRmaItemAfterSavingSalesReturnsBalanceQty)
					&& actstockLedgerRmaItemAfterSavingSalesReturnsBalanceValue.equalsIgnoreCase(expstockLedgerRmaItemAfterSavingSalesReturnsBalanceValue)
					&& actstockLedgerRmaItemAfterSavingSalesReturnsAvgRate.equalsIgnoreCase(expstockLedgerRmaItemAfterSavingSalesReturnsAvgRate)
					&& actstockLedgerRmaItemHydBalanceQtyAfterSavingSalesReturns.equalsIgnoreCase(expstockLedgerRmaItemHydBalanceQtyAfterSavingSalesReturns)
					&& actstockLedgerRmaItemHydBalanceValueAfterSavingSalesReturns.equalsIgnoreCase(expstockLedgerRmaItemHydBalanceValueAfterSavingSalesReturns)
					&& actstockLedgerRmaItemHydAvgRateAfterSavingSalesReturns.equalsIgnoreCase(expstockLedgerRmaItemHydAvgRateAfterSavingSalesReturns)
					&& actstockLedgerRmaItemSecBalanceQtyAfterSavingSalesReturns.equalsIgnoreCase(expstockLedgerRmaItemSecBalanceQtyAfterSavingSalesReturns)
					&& actstockLedgerRmaItemSecBalanceValueAfterSavingSalesReturns.equalsIgnoreCase(expstockLedgerRmaItemSecBalanceValueAfterSavingSalesReturns)
					&& actstockLedgerRmaItemSecAvgRateAfterAfterSavingSalesReturns.equalsIgnoreCase(expstockLedgerRmaItemSecAvgRateAfterAfterSavingSalesReturns)) {

				click(report_CloseBtn);
				Thread.sleep(2000);
				click(sl_CloseBtn);
				excelReader.setCellData(xlfile, xlSheetName, 477, 8, resPass);

				return true;
			} else {
				click(report_CloseBtn);
				Thread.sleep(2000);
				click(sl_CloseBtn);
				excelReader.setCellData(xlfile, xlSheetName, 477, 8, resFail);
				return false;
			}

			
			

		}	
		
		
		
		

		
		public boolean checkStockMovementReportByDefault()
				throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
			
			excelReader=new ExcelReader(POJOUtility.getExcelPath());
			
			//reLogin("inv", "su");
						
			click(inventoryMenu);
			click(InventoryReportsMenu);
			click(InventoryReportsStockMovement);
			click(sl_HeaderSelectChkBox);
			
			dropDown(sl_DateOptionDropdown, excelReader.getCellData(xlSheetName, 487, 5));		
			Thread.sleep(2000);
			click(sl_OkBtn);
			Thread.sleep(2000);
			
			int reportsByWarehouseRow1ListCount = reportsRow1List.size();
			ArrayList<String> reportsByWarehouseRow1ListArray = new ArrayList<String>();
			for(int i=0;i<reportsByWarehouseRow1ListCount;i++)
			{
				String data = reportsRow1List.get(i).getText();
				reportsByWarehouseRow1ListArray.add(data);
			}
			String actRow1List = reportsByWarehouseRow1ListArray.toString();
			String expRow1List = excelReader.getCellData(xlSheetName, 487, 6);
			excelReader.setCellData(xlfile, xlSheetName, 487, 7, actRow1List);
			
			

			int reportsByWarehouseRow2ListCount = reportsRow2List.size();
			ArrayList<String> reportsByWarehouseRow2ListArray = new ArrayList<String>();
			for(int i=0;i<reportsByWarehouseRow2ListCount;i++)
			{
				String data = reportsRow2List.get(i).getText();
				reportsByWarehouseRow2ListArray.add(data);
			}
			String actRow2List = reportsByWarehouseRow2ListArray.toString();
			String expRow2List = excelReader.getCellData(xlSheetName, 488, 6);
			excelReader.setCellData(xlfile, xlSheetName, 488, 7, actRow2List);
			
			
			
			int reportsRow3ListCount = reportsRow3List.size();
			ArrayList<String> reportsRow3ListArray = new ArrayList<String>();
			for(int i=0;i<reportsRow3ListCount;i++)
			{
				String data = reportsRow3List.get(i).getText();
				reportsRow3ListArray.add(data);
			}
			String actRow3List = reportsRow3ListArray.toString();
			String expRow3List = excelReader.getCellData(xlSheetName, 489, 6);
			excelReader.setCellData(xlfile, xlSheetName, 489, 7, actRow3List);
			
			
			
			int reportsRow4ListCount = reportsRow4List.size();
			ArrayList<String> reportsRow4ListArray = new ArrayList<String>();
			for(int i=0;i<reportsRow4ListCount;i++)
			{
				String data = reportsRow4List.get(i).getText();
				reportsRow4ListArray.add(data);
			}
			String actRow4List = reportsRow4ListArray.toString();
			String expRow4List = excelReader.getCellData(xlSheetName, 490, 6);
			excelReader.setCellData(xlfile, xlSheetName, 490, 7, actRow4List);
			
			
			
		
			
			System.out.println("*********************************checkStockMovementReport*****************************************");
			
			System.out.println(actRow1List);
			System.out.println(expRow1List);
			
			System.out.println(actRow2List);
			System.out.println(expRow2List);
			
			System.out.println(actRow3List);
			System.out.println(expRow3List);
			
			System.out.println(actRow4List);
			System.out.println(expRow4List);
			
			click(stockLedgerFilterBtn);
			
			Thread.sleep(2000);
			
			click(stockLedgerFilterField);
			Thread.sleep(1500);

			getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(stockLedgerFilterText));

			boolean actFilterpopupIsDisplaying = stockLedgerFilterText.isDisplayed();
			boolean expFilterpopupIsDisplaying = true;

			System.out.println("Filter Popup isDisplaying  : " + actFilterpopupIsDisplaying + "  Value Expected  "
					+ expFilterpopupIsDisplaying);
			
			
			
			
			
			
			click(stockMovementFilterWarehouseField);
			enterText(stockMovementFilterWarehouseField, "HYD");
			Thread.sleep(2000);
			click(stockLedgerFilterOkBtn);
			Thread.sleep(2000);
			
			int reportsByWarehouseRow1ListCount1 = reportsRow1List.size();
			ArrayList<String> reportsByWarehouseRow1ListArrayAfterFilter = new ArrayList<String>();
			for(int i=0;i<reportsByWarehouseRow1ListCount1;i++)
			{
				String data = reportsRow1List.get(i).getText();
				reportsByWarehouseRow1ListArrayAfterFilter.add(data);
			}
			String actRow1ListAfterFilter = reportsByWarehouseRow1ListArrayAfterFilter.toString();
			String expRow1ListAfterFilter = "[1, WA-RMA, 10.00, 101.0100, 426.00, 15,380.6989, 434.00, -14,105.8441, 2.00, 1,375.8648, 687.9324]";
			
			

			int reportsByWarehouseRow2ListCount1 = reportsRow2List.size();
			ArrayList<String> reportsByWarehouseRow2ListArrayAfterFilter = new ArrayList<String>();
			for(int i=0;i<reportsByWarehouseRow2ListCount1;i++)
			{
				String data = reportsRow2List.get(i).getText();
				reportsByWarehouseRow2ListArrayAfterFilter.add(data);
			}
			String actRow2ListAfterFilter = reportsByWarehouseRow2ListArrayAfterFilter.toString();
			String expRow2ListAfterFilter = "[2, FIFO-BIN, 10.00, 101.0100, 425.00, 14,783.0365, 433.00, -14,684.0465, 2.00, 200.0000, 100.0000]";
			
			
			
			int reportsRow3ListCount1 = reportsRow3List.size();
			ArrayList<String> reportsRow3ListArrayAfterFilter = new ArrayList<String>();
			for(int i=0;i<reportsRow3ListCount1;i++)
			{
				String data = reportsRow3List.get(i).getText();
				reportsRow3ListArrayAfterFilter.add(data);
			}
			String actRow3ListAfterFilter = reportsRow3ListArrayAfterFilter.toString();
			String expRow3ListAfterFilter = "[3, BR-BATCH, 10.00, 101.0100, 425.00, 14,715.6628, 433.00, -14,696.4708, 2.00, 120.2020, 60.1010]";
			
			
			
			int reportsRow4ListCount1 = reportsRow4List.size();
			ArrayList<String> reportsRow4ListArrayAfterFilter = new ArrayList<String>();
			for(int i=0;i<reportsRow4ListCount1;i++)
			{
				String data = reportsRow4List.get(i).getText();
				reportsRow4ListArrayAfterFilter.add(data);
			}
			String actRow4ListAfterFilter = reportsRow4ListArrayAfterFilter.toString();
			String expRow4ListAfterFilter = "[4, Grand Total, 30.00, 303.0300, 1,276.00, 44,879.3982, 1,300.00, -43,486.3614, 6.00, 1,696.0668, 848.0334]";
			
			System.out.println("=========StcokMovement HydWareHouse  Values============   : ");
			
			System.out.println(actRow1ListAfterFilter);
			System.out.println(expRow1ListAfterFilter);
			
			System.out.println(actRow2ListAfterFilter);
			System.out.println(expRow2ListAfterFilter);
			
			System.out.println(actRow3ListAfterFilter);
			System.out.println(expRow3ListAfterFilter);
			
			System.out.println(actRow4ListAfterFilter);
			System.out.println(expRow4ListAfterFilter);
			
			
			
			
			
			
			click(report_CloseBtn);
			Thread.sleep(1000);
			click(stockLedgerHomePageFilterBtn);
			Thread.sleep(2000);
			click(stockMovementWarehouseField2);
			Thread.sleep(1000);
			click(stockMovementFilterWarehouseField3);
			Thread.sleep(1500);
			stockMovementFilterWarehouseField4.sendKeys(Keys.CONTROL, "a");
			Thread.sleep(1000);
			stockMovementFilterWarehouseField4.sendKeys(Keys.BACK_SPACE);
			Thread.sleep(1000);
			enterText(stockMovementFilterWarehouseField4, "SEC");
			Thread.sleep(1000);
			click(stockLedgerFilterOkBtn);
			Thread.sleep(1000);
			click(sl_OkBtn);
			
			Thread.sleep(2000);
			
			
			
			int reportsByWarehouseRow1ListCount2 = reportsRow1List.size();
			ArrayList<String> reportsByWarehouseRow1ListArraySecLoction = new ArrayList<String>();
			for(int i=0;i<reportsByWarehouseRow1ListCount2;i++)
			{
				String data = reportsRow1List.get(i).getText();
				reportsByWarehouseRow1ListArraySecLoction.add(data);
			}
			String actRow1ListSecLoction = reportsByWarehouseRow1ListArraySecLoction.toString();
			String expRow1ListSecLoction = "[1, WA-RMA, 10.00, 101.1110, 425.00, 26,311.7128, 434.00, -24,998.5707, 1.00, 1,414.2531, 1,414.2531]";
			
			

			int reportsByWarehouseRow2ListCount2 = reportsRow2List.size();
			ArrayList<String> reportsByWarehouseRow2ListArraySecLoction = new ArrayList<String>();
			for(int i=0;i<reportsByWarehouseRow2ListCount2;i++)
			{
				String data = reportsRow2List.get(i).getText();
				reportsByWarehouseRow2ListArraySecLoction.add(data);
			}
			String actRow2ListSecLoction = reportsByWarehouseRow2ListArraySecLoction.toString();
			String expRow2ListSecLoction = "[2, FIFO-BIN, 10.00, 101.1110, 425.00, 25,108.7496, 434.00, -25,009.8606, 1.00, 200.0000, 200.0000]";
			
			
			
			int reportsRow3ListCount2 = reportsRow3List.size();
			ArrayList<String> reportsRow3ListArraySecLoction = new ArrayList<String>();
			for(int i=0;i<reportsRow3ListCount2;i++)
			{
				String data = reportsRow3List.get(i).getText();
				reportsRow3ListArraySecLoction.add(data);
			}
			String actRow3ListSecLoction = reportsRow3ListArraySecLoction.toString();
			String expRow3ListSecLoction = "[3, BR-BATCH, 10.00, 101.1110, 425.00, 24,941.6565, 434.00, -25,012.4645, 1.00, 30.3030, 30.3030]";
			
			
			
			int reportsRow4ListCount2 = reportsRow4List.size();
			ArrayList<String> reportsRow4ListArraySecLoction = new ArrayList<String>();
			for(int i=0;i<reportsRow4ListCount2;i++)
			{
				String data = reportsRow4List.get(i).getText();
				reportsRow4ListArraySecLoction.add(data);
			}
			String actRow4ListSecLoction = reportsRow4ListArraySecLoction.toString();
			String expRow4ListSecLoction = "[4, Grand Total, 30.00, 303.3330, 1,275.00, 76,362.1189, 1,302.00, -75,020.8958, 3.00, 1,644.5561, 1,644.5561]";
			
			System.out.println("=========StcokMovement  SEC WareHouse  Values============   : ");
			
			
			System.out.println(actRow1ListSecLoction);
			System.out.println(expRow1ListSecLoction);
			
			System.out.println(actRow2ListSecLoction);
			System.out.println(expRow2ListSecLoction);
			
			System.out.println(actRow3ListSecLoction);
			System.out.println(expRow3ListSecLoction);
			
			System.out.println(actRow4ListSecLoction);
			System.out.println(expRow4ListSecLoction);
			
			
		
			
			click(stockLedgerFilterBtn);
			Thread.sleep(1000);
			click(stockLedgerFilterField);
			Thread.sleep(1500);
			click(stockLedgerFilterRefreshBtn);
			Thread.sleep(1000);
			click(stockLedgerFilterOkBtn);
			
			Thread.sleep(3000);
			
		
			
			if(actRow1List.equalsIgnoreCase(expRow1List) && actRow2List.equalsIgnoreCase(expRow2List) && actRow3List.equalsIgnoreCase(expRow3List) 
				&& actRow4List.equalsIgnoreCase(expRow4List) && actRow1ListAfterFilter.equalsIgnoreCase(expRow1ListAfterFilter) && actRow2ListAfterFilter.equalsIgnoreCase(expRow2ListAfterFilter)
				&& actRow3ListAfterFilter.equalsIgnoreCase(expRow3ListAfterFilter) && actRow4ListAfterFilter.equalsIgnoreCase(expRow4ListAfterFilter)
				&& actRow1ListSecLoction.equalsIgnoreCase(expRow1ListSecLoction) && actRow2ListSecLoction.equalsIgnoreCase(expRow2ListSecLoction)
				&& actRow3ListSecLoction.equalsIgnoreCase(expRow3ListSecLoction) && actRow4ListSecLoction.equalsIgnoreCase(expRow4ListSecLoction))
			{
				click(report_CloseBtn);
				excelReader.setCellData(xlfile, xlSheetName, 487, 8, resPass);
				return true;
			}
			else
			{
				click(report_CloseBtn);
				excelReader.setCellData(xlfile, xlSheetName, 487, 8, resFail);
				return false;
			}
		}
		
		
		
		
		public boolean checkStockMovementReportByWareHouse()
				throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
			
			excelReader=new ExcelReader(POJOUtility.getExcelPath());
			
			//Thread.sleep(2000);
			
			dropDown(stockMovementDropdown, excelReader.getCellData(xlSheetName, 491, 5));		
			Thread.sleep(2000);
			click(sl_OkBtn);
			Thread.sleep(4000);
			
			int reportsByWarehouseRow1ListCount = reportsRow1List.size();
			ArrayList<String> reportsByWarehouseRow1ListArray = new ArrayList<String>();
			for(int i=0;i<reportsByWarehouseRow1ListCount;i++)
			{
				String data = reportsRow1List.get(i).getText();
				reportsByWarehouseRow1ListArray.add(data);
			}
			String actRow1List = reportsByWarehouseRow1ListArray.toString();
			String expRow1List = excelReader.getCellData(xlSheetName, 491, 6);
			excelReader.setCellData(xlfile, xlSheetName, 491, 7, actRow1List);
			
			

			int reportsByWarehouseRow2ListCount = reportsRow2List.size();
			ArrayList<String> reportsByWarehouseRow2ListArray = new ArrayList<String>();
			for(int i=0;i<reportsByWarehouseRow2ListCount;i++)
			{
				String data = reportsRow2List.get(i).getText();
				reportsByWarehouseRow2ListArray.add(data);
			}
			String actRow2List = reportsByWarehouseRow2ListArray.toString();
			String expRow2List = excelReader.getCellData(xlSheetName, 492, 6);
			excelReader.setCellData(xlfile, xlSheetName, 492, 7, actRow2List);
			
			
			
			int reportsRow3ListCount = reportsRow3List.size();
			ArrayList<String> reportsRow3ListArray = new ArrayList<String>();
			for(int i=0;i<reportsRow3ListCount;i++)
			{
				String data = reportsRow3List.get(i).getText();
				reportsRow3ListArray.add(data);
			}
			String actRow3List = reportsRow3ListArray.toString();
			String expRow3List = excelReader.getCellData(xlSheetName, 493, 6);
			excelReader.setCellData(xlfile, xlSheetName, 493, 7, actRow3List);
			
			
			
			int reportsRow4ListCount = reportsRow4List.size();
			ArrayList<String> reportsRow4ListArray = new ArrayList<String>();
			for(int i=0;i<reportsRow4ListCount;i++)
			{
				String data = reportsRow4List.get(i).getText();
				reportsRow4ListArray.add(data);
			}
			String actRow4List = reportsRow4ListArray.toString();
			String expRow4List = excelReader.getCellData(xlSheetName, 494, 6);
			excelReader.setCellData(xlfile, xlSheetName, 494, 7, actRow4List);
			
			int reportsRow5ListCount = reportsRow5List.size();
			ArrayList<String> reportsRow5ListArray = new ArrayList<String>();
			for(int i=0;i<reportsRow5ListCount;i++)
			{
				String data = reportsRow5List.get(i).getText();
				reportsRow5ListArray.add(data);
			}
			String actRow5List = reportsRow5ListArray.toString();
			String expRow5List = excelReader.getCellData(xlSheetName, 495, 6);
			excelReader.setCellData(xlfile, xlSheetName, 495, 7, actRow5List);
			
			
			int report6thRowListCount = reportsRow6List.size();
			ArrayList<String> report6thRowListArray = new ArrayList<String>();
			for(int i=0;i<report6thRowListCount;i++)
			{
				String data = reportsRow6List.get(i).getText();
				report6thRowListArray.add(data);
			}
			String actRow6List = report6thRowListArray.toString();
			String expRow6List = excelReader.getCellData(xlSheetName, 496, 6);
			excelReader.setCellData(xlfile, xlSheetName, 496, 7, actRow6List);
			
			
			int report7thRowListCount = reportsRow7List.size();
			ArrayList<String> report7thRowListArray = new ArrayList<String>();
			for(int i=0;i<report7thRowListCount;i++)
			{
				String data = reportsRow7List.get(i).getText();
				report7thRowListArray.add(data);
			}
			String actRow7List = report7thRowListArray.toString();
			String expRow7List = excelReader.getCellData(xlSheetName, 497, 6);
			excelReader.setCellData(xlfile, xlSheetName, 497, 7, actRow7List);
			
			int report8thRowListCount = reportsRow8List.size();
			ArrayList<String> report8thRowListArray = new ArrayList<String>();
			for(int i=0;i<report8thRowListCount;i++)
			{
				String data = reportsRow8List.get(i).getText();
				report8thRowListArray.add(data);
			}
			String actRow8List = report8thRowListArray.toString();
			String expRow8List = excelReader.getCellData(xlSheetName, 498, 6);
			excelReader.setCellData(xlfile, xlSheetName, 498, 7, actRow8List);
			
			
			
			int report9thRowListCount = reportsRow9List.size();
			ArrayList<String> report9thRowListArray = new ArrayList<String>();
			for(int i=0;i<report9thRowListCount;i++)
			{
				String data = reportsRow9List.get(i).getText();
				report9thRowListArray.add(data);
			}
			String actRow9List = report9thRowListArray.toString();
			String expRow9List = excelReader.getCellData(xlSheetName, 499, 6);
			excelReader.setCellData(xlfile, xlSheetName, 499, 7, actRow9List);
			
			
			
			int report10thRowListCount = reportsRow10List.size();
			ArrayList<String> report10thRowListArray = new ArrayList<String>();
			for(int i=0;i<report10thRowListCount;i++)
			{
				String data = reportsRow10List.get(i).getText();
				report10thRowListArray.add(data);
			}
			String actRow10List = report10thRowListArray.toString();
			String expRow10List = excelReader.getCellData(xlSheetName, 500, 6);
			excelReader.setCellData(xlfile, xlSheetName, 500, 7, actRow10List);
			
			
			
			int reportsRow11ListCount = reportsRow11List.size();
			ArrayList<String> reportsRow11ListArray = new ArrayList<String>();
			for(int i=0;i<reportsRow11ListCount;i++)
			{
				String data = reportsRow11List.get(i).getText();
				reportsRow11ListArray.add(data);
			}
			String actRow11List = reportsRow11ListArray.toString();
			String expRow11List = excelReader.getCellData(xlSheetName, 501, 6);
			excelReader.setCellData(xlfile, xlSheetName, 501, 7, actRow11List);
			
			
			
			
			System.out.println("*********************************checkStockMovementReport BY WareHouse*****************************************");
			
			
			
			System.out.println(actRow1List);
			System.out.println(expRow1List);
			
			System.out.println(actRow2List);
			System.out.println(expRow2List);
			
			System.out.println(actRow3List);
			System.out.println(expRow3List);
			
			System.out.println(actRow4List);
			System.out.println(expRow4List);
			
			System.out.println(actRow5List);
			System.out.println(expRow5List);
			
			System.out.println(actRow6List);
			System.out.println(expRow6List);
			
			System.out.println(actRow7List);
			System.out.println(expRow7List);
			
			System.out.println(actRow8List);
			System.out.println(expRow8List);
			
			System.out.println(actRow9List);
			System.out.println(expRow9List);
			
			System.out.println(actRow10List);
			System.out.println(expRow10List);
			
			System.out.println(actRow11List);
			System.out.println(expRow11List);
			
			click(stockLedgerFilterBtn);
			
			Thread.sleep(2000);
			click(stockLedgerFilterField);
			Thread.sleep(1500);

			getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(stockLedgerFilterText));

			boolean actFilterpopupIsDisplaying = stockLedgerFilterText.isDisplayed();
			boolean expFilterpopupIsDisplaying = true;

			System.out.println("Filter Popup isDisplaying  : " + actFilterpopupIsDisplaying + "  Value Expected  "
					+ expFilterpopupIsDisplaying);
			
			
			
			
			
			
			click(stockMovementFilterWarehouseField);
			enterText(stockMovementFilterWarehouseField, "HYD");
			Thread.sleep(2000);
			click(stockLedgerFilterOkBtn);
			Thread.sleep(2000);
			
			int reportsByWarehouseRow1ListCount1 = reportsRow1List.size();
			ArrayList<String> reportsByWarehouseRow1ListArrayAfterFilter = new ArrayList<String>();
			for(int i=0;i<reportsByWarehouseRow1ListCount1;i++)
			{
				String data = reportsRow1List.get(i).getText();
				reportsByWarehouseRow1ListArrayAfterFilter.add(data);
			}
			String actRow1ListAfterFilter = reportsByWarehouseRow1ListArrayAfterFilter.toString();
			String expRow1ListAfterFilter = "[1, HYD]";
			
			

			int reportsByWarehouseRow2ListCount1 = reportsRow2List.size();
			ArrayList<String> reportsByWarehouseRow2ListArrayAfterFilter = new ArrayList<String>();
			for(int i=0;i<reportsByWarehouseRow2ListCount1;i++)
			{
				String data = reportsRow2List.get(i).getText();
				reportsByWarehouseRow2ListArrayAfterFilter.add(data);
			}
			String actRow2ListAfterFilter = reportsByWarehouseRow2ListArrayAfterFilter.toString();
			String expRow2ListAfterFilter = "[2, WA-RMA, 10.00, 101.0100, 426.00, 15,380.6989, 434.00, -14,105.8441, 2.00, 1,375.8648, 687.9324]";
			
			
			
			int reportsRow3ListCount1 = reportsRow3List.size();
			ArrayList<String> reportsRow3ListArrayAfterFilter = new ArrayList<String>();
			for(int i=0;i<reportsRow3ListCount1;i++)
			{
				String data = reportsRow3List.get(i).getText();
				reportsRow3ListArrayAfterFilter.add(data);
			}
			String actRow3ListAfterFilter = reportsRow3ListArrayAfterFilter.toString();
			String expRow3ListAfterFilter = "[3, FIFO-BIN, 10.00, 101.0100, 425.00, 14,783.0365, 433.00, -14,684.0465, 2.00, 200.0000, 100.0000]";
			
			
			
			int reportsRow4ListCount1 = reportsRow4List.size();
			ArrayList<String> reportsRow4ListArrayAfterFilter = new ArrayList<String>();
			for(int i=0;i<reportsRow4ListCount1;i++)
			{
				String data = reportsRow4List.get(i).getText();
				reportsRow4ListArrayAfterFilter.add(data);
			}
			String actRow4ListAfterFilter = reportsRow4ListArrayAfterFilter.toString();
			String expRow4ListAfterFilter = "[4, BR-BATCH, 10.00, 101.0100, 425.00, 14,715.6628, 433.00, -14,696.4708, 2.00, 120.2020, 60.1010]";
			
			
			int reportsRow5ListCount1 = reportsRow5List.size();
			ArrayList<String> reportsRow5ListArrayAfterFilter = new ArrayList<String>();
			for(int i=0;i<reportsRow5ListCount1;i++)
			{
				String data = reportsRow5List.get(i).getText();
				reportsRow5ListArrayAfterFilter.add(data);
			}
			String actRow5ListAfterFilter = reportsRow5ListArrayAfterFilter.toString();
			String expRow5ListAfterFilter = "[5, Grand Total, 30.00, 303.0300, 1,276.00, 44,879.3982, 1,300.00, -43,486.3614, 6.00, 1,696.0668, 848.0334]";
			
			System.out.println("=========StcokMovement HydWareHouse  Values============   : ");
			
			System.out.println(actRow1ListAfterFilter);
			System.out.println(expRow1ListAfterFilter);
			
			System.out.println(actRow2ListAfterFilter);
			System.out.println(expRow2ListAfterFilter);
			
			System.out.println(actRow3ListAfterFilter);
			System.out.println(expRow3ListAfterFilter);
			
			System.out.println(actRow4ListAfterFilter);
			System.out.println(expRow4ListAfterFilter);
			
			System.out.println(actRow5ListAfterFilter);
			System.out.println(expRow5ListAfterFilter);
			
			
			click(report_CloseBtn);
			Thread.sleep(1000);
			click(stockLedgerHomePageFilterBtn);
			Thread.sleep(2000);
			click(stockMovementWarehouseField2);
			Thread.sleep(1000);
			click(stockMovementFilterWarehouseField3);
			Thread.sleep(1500);
			stockMovementFilterWarehouseField4.sendKeys(Keys.CONTROL, "a");
			Thread.sleep(1000);
			stockMovementFilterWarehouseField4.sendKeys(Keys.BACK_SPACE);
			Thread.sleep(1000);
			enterText(stockMovementFilterWarehouseField4, "SEC");
			Thread.sleep(1000);
			click(stockLedgerFilterOkBtn);
			Thread.sleep(1000);
			click(sl_OkBtn);
			
			Thread.sleep(2000);
			
			
			
			int reportsByWarehouseRow1ListCount2 = reportsRow1List.size();
			ArrayList<String> reportsByWarehouseRow1ListArraySecLoction = new ArrayList<String>();
			for(int i=0;i<reportsByWarehouseRow1ListCount2;i++)
			{
				String data = reportsRow1List.get(i).getText();
				reportsByWarehouseRow1ListArraySecLoction.add(data);
			}
			String actRow1ListSecLoction = reportsByWarehouseRow1ListArraySecLoction.toString();
			String expRow1ListSecLoction = "[1, SEC]";
			
			

			int reportsByWarehouseRow2ListCount2 = reportsRow2List.size();
			ArrayList<String> reportsByWarehouseRow2ListArraySecLoction = new ArrayList<String>();
			for(int i=0;i<reportsByWarehouseRow2ListCount2;i++)
			{
				String data = reportsRow2List.get(i).getText();
				reportsByWarehouseRow2ListArraySecLoction.add(data);
			}
			String actRow2ListSecLoction = reportsByWarehouseRow2ListArraySecLoction.toString();
			String expRow2ListSecLoction = "[2, WA-RMA, 10.00, 101.1110, 425.00, 26,311.7128, 434.00, -24,998.5707, 1.00, 1,414.2531, 1,414.2531]";
			
			
			
			int reportsRow3ListCount2 = reportsRow3List.size();
			ArrayList<String> reportsRow3ListArraySecLoction = new ArrayList<String>();
			for(int i=0;i<reportsRow3ListCount2;i++)
			{
				String data = reportsRow3List.get(i).getText();
				reportsRow3ListArraySecLoction.add(data);
			}
			String actRow3ListSecLoction = reportsRow3ListArraySecLoction.toString();
			String expRow3ListSecLoction = "[3, FIFO-BIN, 10.00, 101.1110, 425.00, 25,108.7496, 434.00, -25,009.8606, 1.00, 200.0000, 200.0000]";
			
			
			
			int reportsRow4ListCount2 = reportsRow4List.size();
			ArrayList<String> reportsRow4ListArraySecLoction = new ArrayList<String>();
			for(int i=0;i<reportsRow4ListCount2;i++)
			{
				String data = reportsRow4List.get(i).getText();
				reportsRow4ListArraySecLoction.add(data);
			}
			String actRow4ListSecLoction = reportsRow4ListArraySecLoction.toString();
			String expRow4ListSecLoction = "[4, BR-BATCH, 10.00, 101.1110, 425.00, 24,941.6565, 434.00, -25,012.4645, 1.00, 30.3030, 30.3030]";
			
			
			int reportsRow5ListCount2 = reportsRow5List.size();
			ArrayList<String> reportsRow5ListArraySecLoction = new ArrayList<String>();
			for(int i=0;i<reportsRow5ListCount2;i++)
			{
				String data = reportsRow5List.get(i).getText();
				reportsRow5ListArraySecLoction.add(data);
			}
			String actRow5ListSecLoction = reportsRow5ListArraySecLoction.toString();
			String expRow5ListSecLoction = "[5, Grand Total, 30.00, 303.3330, 1,275.00, 76,362.1189, 1,302.00, -75,020.8958, 3.00, 1,644.5561, 1,644.5561]";
			
			System.out.println("=========StcokMovement  SEC WareHouse  Values============   : ");
			
			
			System.out.println(actRow1ListSecLoction);
			System.out.println(expRow1ListSecLoction);
			
			System.out.println(actRow2ListSecLoction);
			System.out.println(expRow2ListSecLoction);
			
			System.out.println(actRow3ListSecLoction);
			System.out.println(expRow3ListSecLoction);
			
			System.out.println(actRow4ListSecLoction);
			System.out.println(expRow4ListSecLoction);
			
			System.out.println(actRow5ListSecLoction);
			System.out.println(expRow5ListSecLoction);
			
			
		
			
			click(stockLedgerFilterBtn);
			Thread.sleep(1000);
			click(stockLedgerFilterField);
			Thread.sleep(1500);
			click(stockLedgerFilterRefreshBtn);
			Thread.sleep(1000);
			click(stockLedgerFilterOkBtn);
			
			Thread.sleep(2000);
			
			
			
			if(actRow1List.equalsIgnoreCase(expRow1List) && actRow2List.equalsIgnoreCase(expRow2List) && actRow3List.equalsIgnoreCase(expRow3List)
					&& actRow4List.equalsIgnoreCase(expRow4List) && actRow5List.equalsIgnoreCase(expRow5List) && actRow6List.equalsIgnoreCase(expRow6List)
					&& actRow7List.equalsIgnoreCase(expRow7List) && actRow8List.equalsIgnoreCase(expRow8List) && actRow9List.equalsIgnoreCase(expRow9List) 
					&& actRow10List.equalsIgnoreCase(expRow10List) && actRow11List.equalsIgnoreCase(expRow11List) && actRow1ListAfterFilter.equalsIgnoreCase(expRow1ListAfterFilter) && actRow2ListAfterFilter.equalsIgnoreCase(expRow2ListAfterFilter)
					&& actRow3ListAfterFilter.equalsIgnoreCase(expRow3ListAfterFilter) && actRow4ListAfterFilter.equalsIgnoreCase(expRow4ListAfterFilter)
					&& actRow5ListAfterFilter.equalsIgnoreCase(expRow5ListAfterFilter) && actRow1ListSecLoction.equalsIgnoreCase(expRow1ListSecLoction) && actRow2ListSecLoction.equalsIgnoreCase(expRow2ListSecLoction)
					&& actRow3ListSecLoction.equalsIgnoreCase(expRow3ListSecLoction) && actRow4ListSecLoction.equalsIgnoreCase(expRow4ListSecLoction)
					&& actRow5ListSecLoction.equalsIgnoreCase(expRow5ListSecLoction))
			{
				click(report_CloseBtn);				
				Thread.sleep(2000);
				click(sl_CloseBtn);	
				excelReader.setCellData(xlfile, xlSheetName, 491, 8, resPass);
				return true;
			}
			else
			{
				click(report_CloseBtn);				
				Thread.sleep(2000);
				click(sl_CloseBtn);
				excelReader.setCellData(xlfile, xlSheetName, 491, 8, resFail);
				return false;
			}
        }
		
		
		
		
		public boolean checkStockValuationReportByProduct()
				throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
			
			excelReader=new ExcelReader(POJOUtility.getExcelPath());
			
			click(inventoryMenu);
			click(InventoryReportsMenu);
			click(InventoryReportsStockValuation);
			//click(sl_HeaderSelectChkBox);
			click(selectAllItemChkBox);
			
			dropDown(sl_DateOptionDropdown, excelReader.getCellData(xlSheetName, 502, 5));		
			Thread.sleep(2000);
			click(sl_OkBtn);
			Thread.sleep(2000);
			
			int reportsByWarehouseRow1ListCount = reportsRow1List.size();
			ArrayList<String> reportsByWarehouseRow1ListArray = new ArrayList<String>();
			for(int i=0;i<reportsByWarehouseRow1ListCount;i++)
			{
				String data = reportsRow1List.get(i).getText();
				reportsByWarehouseRow1ListArray.add(data);
			}
			String actRow1List = reportsByWarehouseRow1ListArray.toString();
			String expRow1List = excelReader.getCellData(xlSheetName, 502, 6);
			excelReader.setCellData(xlfile, xlSheetName, 502, 7, actRow1List);
			
			

			int reportsByWarehouseRow2ListCount = reportsRow2List.size();
			ArrayList<String> reportsByWarehouseRow2ListArray = new ArrayList<String>();
			for(int i=0;i<reportsByWarehouseRow2ListCount;i++)
			{
				String data = reportsRow2List.get(i).getText();
				reportsByWarehouseRow2ListArray.add(data);
			}
			String actRow2List = reportsByWarehouseRow2ListArray.toString();
			String expRow2List = excelReader.getCellData(xlSheetName, 503, 6);
			excelReader.setCellData(xlfile, xlSheetName, 503, 7, actRow2List);
			
			
			
			int reportsRow3ListCount = reportsRow3List.size();
			ArrayList<String> reportsRow3ListArray = new ArrayList<String>();
			for(int i=0;i<reportsRow3ListCount;i++)
			{
				String data = reportsRow3List.get(i).getText();
				reportsRow3ListArray.add(data);
			}
			String actRow3List = reportsRow3ListArray.toString();
			String expRow3List = excelReader.getCellData(xlSheetName, 504, 6);
			excelReader.setCellData(xlfile, xlSheetName, 504, 7, actRow3List);
			
			
			
			int reportsRow4ListCount = reportsRow4List.size();
			ArrayList<String> reportsRow4ListArray = new ArrayList<String>();
			for(int i=0;i<reportsRow4ListCount;i++)
			{
				String data = reportsRow4List.get(i).getText();
				reportsRow4ListArray.add(data);
			}
			String actRow4List = reportsRow4ListArray.toString();
			String expRow4List = excelReader.getCellData(xlSheetName, 505, 6);
			excelReader.setCellData(xlfile, xlSheetName, 505, 7, actRow4List);
			
			
			
		
			
			System.out.println("*********************************checkStockValuationReport By Product*****************************************");
			
			System.out.println(actRow1List);
			System.out.println(expRow1List);
			
			System.out.println(actRow2List);
			System.out.println(expRow2List);
			
			System.out.println(actRow3List);
			System.out.println(expRow3List);
			
			System.out.println(actRow4List);
			System.out.println(expRow4List);
			
			click(stockLedgerFilterBtn);
			
			Thread.sleep(2000);
			click(stockLedgerFilterField);
			Thread.sleep(1500);

			getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(stockLedgerFilterText));

			boolean actFilterpopupIsDisplaying = stockLedgerFilterText.isDisplayed();
			boolean expFilterpopupIsDisplaying = true;

			System.out.println("Filter Popup isDisplaying  : " + actFilterpopupIsDisplaying + "  Value Expected  "
					+ expFilterpopupIsDisplaying);
			
			
			
			
			
			
			click(stockValuationFilterWarehouseField);
			enterText(stockValuationFilterWarehouseField, "HYD");
			Thread.sleep(2000);
			click(stockLedgerFilterOkBtn);
			Thread.sleep(2000);
			
			int reportsByWarehouseRow1ListCount1 = reportsRow1List.size();
			ArrayList<String> reportsByWarehouseRow1ListArrayAfterFilter = new ArrayList<String>();
			for(int i=0;i<reportsByWarehouseRow1ListCount1;i++)
			{
				String data = reportsRow1List.get(i).getText();
				reportsByWarehouseRow1ListArrayAfterFilter.add(data);
			}
			String actRow1ListAfterFilter = reportsByWarehouseRow1ListArrayAfterFilter.toString();
			String expRow1ListAfterFilter = "[1, WA-RMA, WA-RMA, WA-RMA, 2.0000, 1,375.8648, 687.9324, , ]";
			
			

			int reportsByWarehouseRow2ListCount1 = reportsRow2List.size();
			ArrayList<String> reportsByWarehouseRow2ListArrayAfterFilter = new ArrayList<String>();
			for(int i=0;i<reportsByWarehouseRow2ListCount1;i++)
			{
				String data = reportsRow2List.get(i).getText();
				reportsByWarehouseRow2ListArrayAfterFilter.add(data);
			}
			String actRow2ListAfterFilter = reportsByWarehouseRow2ListArrayAfterFilter.toString();
			String expRow2ListAfterFilter = "[2, FIFO-BIN, FIFO-BIN, FIFO-BIN, 2.0000, 200.0000, 100.0000, , ]";
			
			
			
			int reportsRow3ListCount1 = reportsRow3List.size();
			ArrayList<String> reportsRow3ListArrayAfterFilter = new ArrayList<String>();
			for(int i=0;i<reportsRow3ListCount1;i++)
			{
				String data = reportsRow3List.get(i).getText();
				reportsRow3ListArrayAfterFilter.add(data);
			}
			String actRow3ListAfterFilter = reportsRow3ListArrayAfterFilter.toString();
			String expRow3ListAfterFilter = "[3, BR-BATCH, BR-BATCH, BR-BATCH, 2.0000, 120.2020, 60.1010, , ]";
			
			
			
			int reportsRow4ListCount1 = reportsRow4List.size();
			ArrayList<String> reportsRow4ListArrayAfterFilter = new ArrayList<String>();
			for(int i=0;i<reportsRow4ListCount1;i++)
			{
				String data = reportsRow4List.get(i).getText();
				reportsRow4ListArrayAfterFilter.add(data);
			}
			String actRow4ListAfterFilter = reportsRow4ListArrayAfterFilter.toString();
			String expRow4ListAfterFilter = "[4, Grand Total, , , 6.0000, 1,696.0668, 848.0334, , ]";
			
		
			
			System.out.println("=========StcokMovement HydWareHouse  Values============   : ");
			
			System.out.println(actRow1ListAfterFilter);
			System.out.println(expRow1ListAfterFilter);
			
			System.out.println(actRow2ListAfterFilter);
			System.out.println(expRow2ListAfterFilter);
			
			System.out.println(actRow3ListAfterFilter);
			System.out.println(expRow3ListAfterFilter);
			
			System.out.println(actRow4ListAfterFilter);
			System.out.println(expRow4ListAfterFilter);
			
		
			
			click(report_CloseBtn);
			Thread.sleep(1000);
			click(stockLedgerHomePageFilterBtn);
			Thread.sleep(2000);
			click(stockValuationWarehouseField2);
			Thread.sleep(1000);
			click(stockValuationFilterWarehouseField3);
			Thread.sleep(1500);
			stockValuationFilterWarehouseField4.sendKeys(Keys.CONTROL, "a");
			Thread.sleep(1000);
			stockValuationFilterWarehouseField4.sendKeys(Keys.BACK_SPACE);
			Thread.sleep(1000);
			enterText(stockValuationFilterWarehouseField4, "SEC");
			Thread.sleep(1000);
			click(stockLedgerFilterOkBtn);
			Thread.sleep(1000);
			click(sl_OkBtn);
			
			Thread.sleep(2000);
			
			
			
			int reportsByWarehouseRow1ListCount2 = reportsRow1List.size();
			ArrayList<String> reportsByWarehouseRow1ListArraySecLoction = new ArrayList<String>();
			for(int i=0;i<reportsByWarehouseRow1ListCount2;i++)
			{
				String data = reportsRow1List.get(i).getText();
				reportsByWarehouseRow1ListArraySecLoction.add(data);
			}
			String actRow1ListSecLoction = reportsByWarehouseRow1ListArraySecLoction.toString();
			String expRow1ListSecLoction = "[1, WA-RMA, WA-RMA, WA-RMA, 1.0000, 1,414.2531, 1,414.2531, , ]";
			
			

			int reportsByWarehouseRow2ListCount2 = reportsRow2List.size();
			ArrayList<String> reportsByWarehouseRow2ListArraySecLoction = new ArrayList<String>();
			for(int i=0;i<reportsByWarehouseRow2ListCount2;i++)
			{
				String data = reportsRow2List.get(i).getText();
				reportsByWarehouseRow2ListArraySecLoction.add(data);
			}
			String actRow2ListSecLoction = reportsByWarehouseRow2ListArraySecLoction.toString();
			String expRow2ListSecLoction = "[2, FIFO-BIN, FIFO-BIN, FIFO-BIN, 1.0000, 200.0000, 200.0000, , ]";
			
			
			
			int reportsRow3ListCount2 = reportsRow3List.size();
			ArrayList<String> reportsRow3ListArraySecLoction = new ArrayList<String>();
			for(int i=0;i<reportsRow3ListCount2;i++)
			{
				String data = reportsRow3List.get(i).getText();
				reportsRow3ListArraySecLoction.add(data);
			}
			String actRow3ListSecLoction = reportsRow3ListArraySecLoction.toString();
			String expRow3ListSecLoction = "[3, BR-BATCH, BR-BATCH, BR-BATCH, 1.0000, 30.3030, 30.3030, , ]";
			
			
			
			int reportsRow4ListCount2 = reportsRow4List.size();
			ArrayList<String> reportsRow4ListArraySecLoction = new ArrayList<String>();
			for(int i=0;i<reportsRow4ListCount2;i++)
			{
				String data = reportsRow4List.get(i).getText();
				reportsRow4ListArraySecLoction.add(data);
			}
			String actRow4ListSecLoction = reportsRow4ListArraySecLoction.toString();
			String expRow4ListSecLoction = "[4, Grand Total, , , 3.0000, 1,644.5561, 1,644.5561, , ]";
			
			
		
			System.out.println("=========StcokMovement  SEC WareHouse  Values============   : ");
			
			
			System.out.println(actRow1ListSecLoction);
			System.out.println(expRow1ListSecLoction);
			
			System.out.println(actRow2ListSecLoction);
			System.out.println(expRow2ListSecLoction);
			
			System.out.println(actRow3ListSecLoction);
			System.out.println(expRow3ListSecLoction);
			
			System.out.println(actRow4ListSecLoction);
			System.out.println(expRow4ListSecLoction);
			
		
			
		
			
			click(stockLedgerFilterBtn);
			Thread.sleep(1000);
			click(stockLedgerFilterField);
			Thread.sleep(1500);
			click(stockLedgerFilterRefreshBtn);
			Thread.sleep(1000);
			click(stockLedgerFilterOkBtn);
			
			Thread.sleep(3000);
			
		
			
			if(actRow1List.equalsIgnoreCase(expRow1List) && actRow2List.equalsIgnoreCase(expRow2List) && actRow3List.equalsIgnoreCase(expRow3List) 
				&& actRow4List.equalsIgnoreCase(expRow4List) && actRow1ListAfterFilter.equalsIgnoreCase(expRow1ListAfterFilter) && actRow2ListAfterFilter.equalsIgnoreCase(expRow2ListAfterFilter)
				&& actRow3ListAfterFilter.equalsIgnoreCase(expRow3ListAfterFilter) && actRow4ListAfterFilter.equalsIgnoreCase(expRow4ListAfterFilter)
				&& actRow1ListSecLoction.equalsIgnoreCase(expRow1ListSecLoction) && actRow2ListSecLoction.equalsIgnoreCase(expRow2ListSecLoction)
				&& actRow3ListSecLoction.equalsIgnoreCase(expRow3ListSecLoction) && actRow4ListSecLoction.equalsIgnoreCase(expRow4ListSecLoction) )
			{
				click(report_CloseBtn);
				excelReader.setCellData(xlfile, xlSheetName, 502, 8, resPass);
				return true;
			}
			else
			{
				click(report_CloseBtn);
				excelReader.setCellData(xlfile, xlSheetName, 502, 8, resFail);
				return false;
			}
		}
		
		
		
		
		
		public boolean checkStockValuationReportByProductbyInventoryTag()
				throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
			
			excelReader = new ExcelReader(POJOUtility.getExcelPath());
			
			Thread.sleep(2000);
			
			dropDown(stockValuationDropdown, excelReader.getCellData(xlSheetName, 506, 5));		
			Thread.sleep(2000);
			click(sl_OkBtn);
			Thread.sleep(2000);
			
			int reportsByWarehouseRow1ListCount = reportsRow1List.size();
			ArrayList<String> reportsByWarehouseRow1ListArray = new ArrayList<String>();
			for(int i=0;i<reportsByWarehouseRow1ListCount;i++)
			{
				String data = reportsRow1List.get(i).getText();
				reportsByWarehouseRow1ListArray.add(data);
			}
			String actRow1List = reportsByWarehouseRow1ListArray.toString();
			String expRow1List = excelReader.getCellData(xlSheetName, 506, 6);
			excelReader.setCellData(xlfile, xlSheetName, 506, 7, actRow1List);
			
			

			int reportsByWarehouseRow2ListCount = reportsRow2List.size();
			ArrayList<String> reportsByWarehouseRow2ListArray = new ArrayList<String>();
			for(int i=0;i<reportsByWarehouseRow2ListCount;i++)
			{
				String data = reportsRow2List.get(i).getText();
				reportsByWarehouseRow2ListArray.add(data);
			}
			String actRow2List = reportsByWarehouseRow2ListArray.toString();
			String expRow2List = excelReader.getCellData(xlSheetName, 507, 6);
			excelReader.setCellData(xlfile, xlSheetName, 507, 7, actRow2List);
			
			
			
			int reportsRow3ListCount = reportsRow3List.size();
			ArrayList<String> reportsRow3ListArray = new ArrayList<String>();
			for(int i=0;i<reportsRow3ListCount;i++)
			{
				String data = reportsRow3List.get(i).getText();
				reportsRow3ListArray.add(data);
			}
			String actRow3List = reportsRow3ListArray.toString();
			String expRow3List = excelReader.getCellData(xlSheetName, 508, 6);
			excelReader.setCellData(xlfile, xlSheetName, 508, 7, actRow3List);
			
			
			
			int reportsRow4ListCount = reportsRow4List.size();
			ArrayList<String> reportsRow4ListArray = new ArrayList<String>();
			for(int i=0;i<reportsRow4ListCount;i++)
			{
				String data = reportsRow4List.get(i).getText();
				reportsRow4ListArray.add(data);
			}
			String actRow4List = reportsRow4ListArray.toString();
			String expRow4List = excelReader.getCellData(xlSheetName, 509, 6);
			excelReader.setCellData(xlfile, xlSheetName, 509, 7, actRow4List);
			
			int reportsRow5ListCount = reportsRow5List.size();
			ArrayList<String> reportsRow5ListArray = new ArrayList<String>();
			for(int i=0;i<reportsRow5ListCount;i++)
			{
				String data = reportsRow5List.get(i).getText();
				reportsRow5ListArray.add(data);
			}
			String actRow5List = reportsRow5ListArray.toString();
			String expRow5List = excelReader.getCellData(xlSheetName, 510, 6);
			excelReader.setCellData(xlfile, xlSheetName, 510, 7, actRow5List);
			
			
			int report6thRowListCount = reportsRow6List.size();
			ArrayList<String> report6thRowListArray = new ArrayList<String>();
			for(int i=0;i<report6thRowListCount;i++)
			{
				String data = reportsRow6List.get(i).getText();
				report6thRowListArray.add(data);
			}
			String actRow6List = report6thRowListArray.toString();
			String expRow6List = excelReader.getCellData(xlSheetName, 511, 6);
			excelReader.setCellData(xlfile, xlSheetName, 511, 7, actRow6List);
			
			
			int report7thRowListCount = reportsRow7List.size();
			ArrayList<String> report7thRowListArray = new ArrayList<String>();
			for(int i=0;i<report7thRowListCount;i++)
			{
				String data = reportsRow7List.get(i).getText();
				report7thRowListArray.add(data);
			}
			String actRow7List = report7thRowListArray.toString();
			String expRow7List = excelReader.getCellData(xlSheetName, 512, 6);
			excelReader.setCellData(xlfile, xlSheetName, 512, 7, actRow7List);
		
			
			System.out.println("*********************************checkStockValuationReport By ProductbyInventoryTag*****************************************");
			
			System.out.println(actRow1List);
			System.out.println(expRow1List);
			
			System.out.println(actRow2List);
			System.out.println(expRow2List);
			
			System.out.println(actRow3List);
			System.out.println(expRow3List);
			
			System.out.println(actRow4List);
			System.out.println(expRow4List);
			
			System.out.println(actRow5List);
			System.out.println(expRow5List);
			
			System.out.println(actRow6List);
			System.out.println(expRow6List);
			
			System.out.println(actRow7List);
			System.out.println(expRow7List);
			
			click(stockLedgerFilterBtn);
			
			Thread.sleep(2000);
			click(stockLedgerFilterField);
			Thread.sleep(1500);

			getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(stockLedgerFilterText));

			boolean actFilterpopupIsDisplaying = stockLedgerFilterText.isDisplayed();
			boolean expFilterpopupIsDisplaying = true;

			System.out.println("Filter Popup isDisplaying  : " + actFilterpopupIsDisplaying + "  Value Expected  "
					+ expFilterpopupIsDisplaying);
			
			
			
			
			
			
			click(stockValuationFilterWarehouseField);
			enterText(stockValuationFilterWarehouseField, "HYD");
			Thread.sleep(2000);
			click(stockLedgerFilterOkBtn);
			Thread.sleep(2000);
			
			int reportsByWarehouseRow1ListCount1 = reportsRow1List.size();
			ArrayList<String> reportsByWarehouseRow1ListArrayAfterFilter = new ArrayList<String>();
			for(int i=0;i<reportsByWarehouseRow1ListCount1;i++)
			{
				String data = reportsRow1List.get(i).getText();
				reportsByWarehouseRow1ListArrayAfterFilter.add(data);
			}
			String actRow1ListAfterFilter = reportsByWarehouseRow1ListArrayAfterFilter.toString();
			String expRow1ListAfterFilter = "[1, WA-RMA, WA-RMA, WA-RMA, 2.0000, 1,375.8648, 687.9324, HYD, ]";
			
			

			int reportsByWarehouseRow2ListCount1 = reportsRow2List.size();
			ArrayList<String> reportsByWarehouseRow2ListArrayAfterFilter = new ArrayList<String>();
			for(int i=0;i<reportsByWarehouseRow2ListCount1;i++)
			{
				String data = reportsRow2List.get(i).getText();
				reportsByWarehouseRow2ListArrayAfterFilter.add(data);
			}
			String actRow2ListAfterFilter = reportsByWarehouseRow2ListArrayAfterFilter.toString();
			String expRow2ListAfterFilter = "[2, FIFO-BIN, FIFO-BIN, FIFO-BIN, 2.0000, 200.0000, 100.0000, HYD, ]";
			
			
			
			int reportsRow3ListCount1 = reportsRow3List.size();
			ArrayList<String> reportsRow3ListArrayAfterFilter = new ArrayList<String>();
			for(int i=0;i<reportsRow3ListCount1;i++)
			{
				String data = reportsRow3List.get(i).getText();
				reportsRow3ListArrayAfterFilter.add(data);
			}
			String actRow3ListAfterFilter = reportsRow3ListArrayAfterFilter.toString();
			String expRow3ListAfterFilter = "[3, BR-BATCH, BR-BATCH, BR-BATCH, 2.0000, 120.2020, 60.1010, HYD, ]";
			
			
			
			int reportsRow4ListCount1 = reportsRow4List.size();
			ArrayList<String> reportsRow4ListArrayAfterFilter = new ArrayList<String>();
			for(int i=0;i<reportsRow4ListCount1;i++)
			{
				String data = reportsRow4List.get(i).getText();
				reportsRow4ListArrayAfterFilter.add(data);
			}
			String actRow4ListAfterFilter = reportsRow4ListArrayAfterFilter.toString();
			String expRow4ListAfterFilter = "[4, Grand Total, , , 6.0000, 1,696.0668, 848.0334, , ]";
			

			
			System.out.println("=========StcokMovement HydWareHouse  Values============   : ");
			
			System.out.println(actRow1ListAfterFilter);
			System.out.println(expRow1ListAfterFilter);
			
			System.out.println(actRow2ListAfterFilter);
			System.out.println(expRow2ListAfterFilter);
			
			System.out.println(actRow3ListAfterFilter);
			System.out.println(expRow3ListAfterFilter);
			
			System.out.println(actRow4ListAfterFilter);
			System.out.println(expRow4ListAfterFilter);
			
		
			
			click(report_CloseBtn);
			Thread.sleep(1000);
			click(stockLedgerHomePageFilterBtn);
			Thread.sleep(2000);
			click(stockValuationWarehouseField2);
			Thread.sleep(1000);
			click(stockValuationFilterWarehouseField3);
			Thread.sleep(1500);
			stockValuationFilterWarehouseField4.sendKeys(Keys.CONTROL, "a");
			Thread.sleep(1000);
			stockValuationFilterWarehouseField4.sendKeys(Keys.BACK_SPACE);
			Thread.sleep(1000);
			enterText(stockValuationFilterWarehouseField4, "SEC");
			Thread.sleep(1000);
			click(stockLedgerFilterOkBtn);
			Thread.sleep(1000);
			click(sl_OkBtn);
			
			Thread.sleep(2000);
			
			
			
			int reportsByWarehouseRow1ListCount2 = reportsRow1List.size();
			ArrayList<String> reportsByWarehouseRow1ListArraySecLoction = new ArrayList<String>();
			for(int i=0;i<reportsByWarehouseRow1ListCount2;i++)
			{
				String data = reportsRow1List.get(i).getText();
				reportsByWarehouseRow1ListArraySecLoction.add(data);
			}
			String actRow1ListSecLoction = reportsByWarehouseRow1ListArraySecLoction.toString();
			String expRow1ListSecLoction = "[1, WA-RMA, WA-RMA, WA-RMA, 1.0000, 1,414.2531, 1,414.2531, SEC, ]";
			
			

			int reportsByWarehouseRow2ListCount2 = reportsRow2List.size();
			ArrayList<String> reportsByWarehouseRow2ListArraySecLoction = new ArrayList<String>();
			for(int i=0;i<reportsByWarehouseRow2ListCount2;i++)
			{
				String data = reportsRow2List.get(i).getText();
				reportsByWarehouseRow2ListArraySecLoction.add(data);
			}
			String actRow2ListSecLoction = reportsByWarehouseRow2ListArraySecLoction.toString();
			String expRow2ListSecLoction = "[2, FIFO-BIN, FIFO-BIN, FIFO-BIN, 1.0000, 200.0000, 200.0000, SEC, ]";
			
			
			
			int reportsRow3ListCount2 = reportsRow3List.size();
			ArrayList<String> reportsRow3ListArraySecLoction = new ArrayList<String>();
			for(int i=0;i<reportsRow3ListCount2;i++)
			{
				String data = reportsRow3List.get(i).getText();
				reportsRow3ListArraySecLoction.add(data);
			}
			String actRow3ListSecLoction = reportsRow3ListArraySecLoction.toString();
			String expRow3ListSecLoction = "[3, BR-BATCH, BR-BATCH, BR-BATCH, 1.0000, 30.3030, 30.3030, SEC, ]";
			
			
			
			int reportsRow4ListCount2 = reportsRow4List.size();
			ArrayList<String> reportsRow4ListArraySecLoction = new ArrayList<String>();
			for(int i=0;i<reportsRow4ListCount2;i++)
			{
				String data = reportsRow4List.get(i).getText();
				reportsRow4ListArraySecLoction.add(data);
			}
			String actRow4ListSecLoction = reportsRow4ListArraySecLoction.toString();
			String expRow4ListSecLoction = "[4, Grand Total, , , 3.0000, 1,644.5561, 1,644.5561, , ]";
			
			
		
			
			System.out.println("=========StcokMovement  SEC WareHouse  Values============   : ");
			
			
			System.out.println(actRow1ListSecLoction);
			System.out.println(expRow1ListSecLoction);
			
			System.out.println(actRow2ListSecLoction);
			System.out.println(expRow2ListSecLoction);
			
			System.out.println(actRow3ListSecLoction);
			System.out.println(expRow3ListSecLoction);
			
			System.out.println(actRow4ListSecLoction);
			System.out.println(expRow4ListSecLoction);
		
		
			
			click(stockLedgerFilterBtn);
			Thread.sleep(1000);
			click(stockLedgerFilterField);
			Thread.sleep(1500);
			click(stockLedgerFilterRefreshBtn);
			Thread.sleep(1000);
			click(stockLedgerFilterOkBtn);
			
			Thread.sleep(2000);
			
		
			
			
			
			if(actRow1List.equalsIgnoreCase(expRow1List) && actRow2List.equalsIgnoreCase(expRow2List) && actRow3List.equalsIgnoreCase(expRow3List)
					&& actRow4List.equalsIgnoreCase(expRow4List) && actRow5List.equalsIgnoreCase(expRow5List) && actRow6List.equalsIgnoreCase(expRow6List)
					&& actRow7List.equalsIgnoreCase(expRow7List) && actRow1ListAfterFilter.equalsIgnoreCase(expRow1ListAfterFilter) && actRow2ListAfterFilter.equalsIgnoreCase(expRow2ListAfterFilter)
					&& actRow3ListAfterFilter.equalsIgnoreCase(expRow3ListAfterFilter) && actRow4ListAfterFilter.equalsIgnoreCase(expRow4ListAfterFilter)
					&& actRow1ListSecLoction.equalsIgnoreCase(expRow1ListSecLoction) && actRow2ListSecLoction.equalsIgnoreCase(expRow2ListSecLoction)
					&& actRow3ListSecLoction.equalsIgnoreCase(expRow3ListSecLoction) && actRow4ListSecLoction.equalsIgnoreCase(expRow4ListSecLoction))
			{
				click(report_CloseBtn);				
				excelReader.setCellData(xlfile, xlSheetName, 506, 8, resPass);			
				return true;
			}
			else
			{
				click(report_CloseBtn);				
				excelReader.setCellData(xlfile, xlSheetName, 506, 8, resFail);
				return false;
			}
        }
		
		
		public boolean checkStockValuationReportByInventoryTagByProduct()
				throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
			
			excelReader =new ExcelReader(POJOUtility.getExcelPath());
			
			Thread.sleep(2000);
			
			dropDown(stockValuationDropdown, excelReader.getCellData(xlSheetName, 513, 5));		
			Thread.sleep(2000);
			click(sl_OkBtn);
			Thread.sleep(5000);
			
			int reportsByWarehouseRow1ListCount = reportsRow1List.size();
			ArrayList<String> reportsByWarehouseRow1ListArray = new ArrayList<String>();
			for(int i=0;i<reportsByWarehouseRow1ListCount;i++)
			{
				String data = reportsRow1List.get(i).getText();
				reportsByWarehouseRow1ListArray.add(data);
			}
			String actRow1List = reportsByWarehouseRow1ListArray.toString();
			String expRow1List = excelReader.getCellData(xlSheetName, 513, 6);
			excelReader.setCellData(xlfile, xlSheetName, 513, 7, actRow1List);
			
			

			int reportsByWarehouseRow2ListCount = reportsRow2List.size();
			ArrayList<String> reportsByWarehouseRow2ListArray = new ArrayList<String>();
			for(int i=0;i<reportsByWarehouseRow2ListCount;i++)
			{
				String data = reportsRow2List.get(i).getText();
				reportsByWarehouseRow2ListArray.add(data);
			}
			String actRow2List = reportsByWarehouseRow2ListArray.toString();
			String expRow2List = excelReader.getCellData(xlSheetName, 514, 6);
			excelReader.setCellData(xlfile, xlSheetName, 514, 7, actRow2List);
			
			
			
			int reportsRow3ListCount = reportsRow3List.size();
			ArrayList<String> reportsRow3ListArray = new ArrayList<String>();
			for(int i=0;i<reportsRow3ListCount;i++)
			{
				String data = reportsRow3List.get(i).getText();
				reportsRow3ListArray.add(data);
			}
			String actRow3List = reportsRow3ListArray.toString();
			String expRow3List = excelReader.getCellData(xlSheetName, 515, 6);
			excelReader.setCellData(xlfile, xlSheetName, 515, 7, actRow3List);
			
			
			
			int reportsRow4ListCount = reportsRow4List.size();
			ArrayList<String> reportsRow4ListArray = new ArrayList<String>();
			for(int i=0;i<reportsRow4ListCount;i++)
			{
				String data = reportsRow4List.get(i).getText();
				reportsRow4ListArray.add(data);
			}
			String actRow4List = reportsRow4ListArray.toString();
			String expRow4List = excelReader.getCellData(xlSheetName, 516, 6);
			excelReader.setCellData(xlfile, xlSheetName, 516, 7, actRow4List);
			
			int reportsRow5ListCount = reportsRow5List.size();
			ArrayList<String> reportsRow5ListArray = new ArrayList<String>();
			for(int i=0;i<reportsRow5ListCount;i++)
			{
				String data = reportsRow5List.get(i).getText();
				reportsRow5ListArray.add(data);
			}
			String actRow5List = reportsRow5ListArray.toString();
			String expRow5List = excelReader.getCellData(xlSheetName, 517, 6);
			excelReader.setCellData(xlfile, xlSheetName, 517, 7, actRow5List);
			
			
			int report6thRowListCount = reportsRow6List.size();
			ArrayList<String> report6thRowListArray = new ArrayList<String>();
			for(int i=0;i<report6thRowListCount;i++)
			{
				String data = reportsRow6List.get(i).getText();
				report6thRowListArray.add(data);
			}
			String actRow6List = report6thRowListArray.toString();
			String expRow6List = excelReader.getCellData(xlSheetName, 518, 6);
			excelReader.setCellData(xlfile, xlSheetName, 518, 7, actRow6List);
			
			
			int report7thRowListCount = reportsRow7List.size();
			ArrayList<String> report7thRowListArray = new ArrayList<String>();
			for(int i=0;i<report7thRowListCount;i++)
			{
				String data = reportsRow7List.get(i).getText();
				report7thRowListArray.add(data);
			}
			String actRow7List = report7thRowListArray.toString();
			String expRow7List = excelReader.getCellData(xlSheetName, 519, 6);
			excelReader.setCellData(xlfile, xlSheetName, 519, 7, actRow7List);
		
			
		
			
			int report8thRowListCount = reportsRow8List.size();
			ArrayList<String> report8thRowListArray = new ArrayList<String>();
			for(int i=0;i<report8thRowListCount;i++)
			{
				String data = reportsRow8List.get(i).getText();
				report8thRowListArray.add(data);
			}
			String actRow8List = report8thRowListArray.toString();
			String expRow8List = excelReader.getCellData(xlSheetName, 520, 6);
			excelReader.setCellData(xlfile, xlSheetName, 520, 7, actRow8List);
			
			
			
			int report9thRowListCount = reportsRow9List.size();
			ArrayList<String> report9thRowListArray = new ArrayList<String>();
			for(int i=0;i<report9thRowListCount;i++)
			{
				String data = reportsRow9List.get(i).getText();
				report9thRowListArray.add(data);
			}
			String actRow9List = report9thRowListArray.toString();
			String expRow9List = excelReader.getCellData(xlSheetName, 521, 6);
			excelReader.setCellData(xlfile, xlSheetName, 521, 7, actRow9List);
			
			
			
			int report10thRowListCount = reportsRow10List.size();
			ArrayList<String> report10thRowListArray = new ArrayList<String>();
			for(int i=0;i<report10thRowListCount;i++)
			{
				String data = reportsRow10List.get(i).getText();
				report10thRowListArray.add(data);
			}
			String actRow10List = report10thRowListArray.toString();
			String expRow10List = excelReader.getCellData(xlSheetName, 522, 6);
			excelReader.setCellData(xlfile, xlSheetName, 522, 7, actRow10List);
			
			
			
			int reportsRow11ListCount = reportsRow11List.size();
			ArrayList<String> reportsRow11ListArray = new ArrayList<String>();
			for(int i=0;i<reportsRow11ListCount;i++)
			{
				String data = reportsRow11List.get(i).getText();
				reportsRow11ListArray.add(data);
			}
			String actRow11List = reportsRow11ListArray.toString();
			String expRow11List = excelReader.getCellData(xlSheetName, 523, 6);
			excelReader.setCellData(xlfile, xlSheetName, 523, 7, actRow11List);
			
			
			
			System.out.println("*********************************checkStockValuationReport By ProductbyInventoryTag*****************************************");
			
		
			
			
			
			System.out.println(actRow1List);
			System.out.println(expRow1List);
			
			System.out.println(actRow2List);
			System.out.println(expRow2List);
			
			System.out.println(actRow3List);
			System.out.println(expRow3List);
			
			System.out.println(actRow4List);
			System.out.println(expRow4List);
			
			System.out.println(actRow5List);
			System.out.println(expRow5List);
			
			System.out.println(actRow6List);
			System.out.println(expRow6List);
			
			System.out.println(actRow7List);
			System.out.println(expRow7List);
			
			System.out.println(actRow8List);
			System.out.println(expRow8List);
			
			System.out.println(actRow9List);
			System.out.println(expRow9List);
			
			System.out.println(actRow10List);
			System.out.println(expRow10List);
			
			System.out.println(actRow11List);
			System.out.println(expRow11List);
			
			click(stockLedgerFilterBtn);
			
			Thread.sleep(2000);
			click(stockLedgerFilterField);
			Thread.sleep(1500);

			getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(stockLedgerFilterText));

			boolean actFilterpopupIsDisplaying = stockLedgerFilterText.isDisplayed();
			boolean expFilterpopupIsDisplaying = true;

			System.out.println("Filter Popup isDisplaying  : " + actFilterpopupIsDisplaying + "  Value Expected  "
					+ expFilterpopupIsDisplaying);
			
			
			
			
			
			
			click(stockValuationFilterWarehouseField);
			enterText(stockValuationFilterWarehouseField, "HYD");
			Thread.sleep(2000);
			click(stockLedgerFilterOkBtn);
			Thread.sleep(2000);
			
			int reportsByWarehouseRow1ListCount1 = reportsRow1List.size();
			ArrayList<String> reportsByWarehouseRow1ListArrayAfterFilter = new ArrayList<String>();
			for(int i=0;i<reportsByWarehouseRow1ListCount1;i++)
			{
				String data = reportsRow1List.get(i).getText();
				reportsByWarehouseRow1ListArrayAfterFilter.add(data);
			}
			String actRow1ListAfterFilter = reportsByWarehouseRow1ListArrayAfterFilter.toString();
			String expRow1ListAfterFilter = "[1, HYD]";
			
			

			int reportsByWarehouseRow2ListCount1 = reportsRow2List.size();
			ArrayList<String> reportsByWarehouseRow2ListArrayAfterFilter = new ArrayList<String>();
			for(int i=0;i<reportsByWarehouseRow2ListCount1;i++)
			{
				String data = reportsRow2List.get(i).getText();
				reportsByWarehouseRow2ListArrayAfterFilter.add(data);
			}
			String actRow2ListAfterFilter = reportsByWarehouseRow2ListArrayAfterFilter.toString();
			String expRow2ListAfterFilter = "[2, BR-BATCH, BR-BATCH, BR-BATCH, 2.0000, 120.2020, 60.1010, HYD, ]";
			
			
			
			int reportsRow3ListCount1 = reportsRow3List.size();
			ArrayList<String> reportsRow3ListArrayAfterFilter = new ArrayList<String>();
			for(int i=0;i<reportsRow3ListCount1;i++)
			{
				String data = reportsRow3List.get(i).getText();
				reportsRow3ListArrayAfterFilter.add(data);
			}
			String actRow3ListAfterFilter = reportsRow3ListArrayAfterFilter.toString();
			String expRow3ListAfterFilter = "[3, FIFO-BIN, FIFO-BIN, FIFO-BIN, 2.0000, 200.0000, 100.0000, HYD, ]";
			
			
			
			int reportsRow4ListCount1 = reportsRow4List.size();
			ArrayList<String> reportsRow4ListArrayAfterFilter = new ArrayList<String>();
			for(int i=0;i<reportsRow4ListCount1;i++)
			{
				String data = reportsRow4List.get(i).getText();
				reportsRow4ListArrayAfterFilter.add(data);
			}
			String actRow4ListAfterFilter = reportsRow4ListArrayAfterFilter.toString();
			String expRow4ListAfterFilter = "[4, WA-RMA, WA-RMA, WA-RMA, 2.0000, 1,375.8648, 687.9324, HYD, ]";
			
			
			int reportsRow5ListCount1 = reportsRow5List.size();
			ArrayList<String> reportsRow5ListArrayAfterFilter = new ArrayList<String>();
			for(int i=0;i<reportsRow5ListCount1;i++)
			{
				String data = reportsRow5List.get(i).getText();
				reportsRow5ListArrayAfterFilter.add(data);
			}
			String actRow5ListAfterFilter = reportsRow5ListArrayAfterFilter.toString();
			String expRow5ListAfterFilter = "[5, Grand Total, , , 6.0000, 1,696.0668, 848.0334, , ]";
			
			System.out.println("=========StcokMovement HydWareHouse  Values============   : ");
			
			System.out.println(actRow1ListAfterFilter);
			System.out.println(expRow1ListAfterFilter);
			
			System.out.println(actRow2ListAfterFilter);
			System.out.println(expRow2ListAfterFilter);
			
			System.out.println(actRow3ListAfterFilter);
			System.out.println(expRow3ListAfterFilter);
			
			System.out.println(actRow4ListAfterFilter);
			System.out.println(expRow4ListAfterFilter);
			
			System.out.println(actRow5ListAfterFilter);
			System.out.println(expRow5ListAfterFilter);
			
			
			click(report_CloseBtn);
			Thread.sleep(1000);
			click(stockLedgerHomePageFilterBtn);
			Thread.sleep(2000);
			click(stockValuationWarehouseField2);
			Thread.sleep(1000);
			click(stockValuationFilterWarehouseField3);
			Thread.sleep(1500);
			stockValuationFilterWarehouseField4.sendKeys(Keys.CONTROL, "a");
			Thread.sleep(1000);
			stockValuationFilterWarehouseField4.sendKeys(Keys.BACK_SPACE);
			Thread.sleep(1000);
			enterText(stockValuationFilterWarehouseField4, "SEC");
			Thread.sleep(1000);
			click(stockLedgerFilterOkBtn);
			Thread.sleep(1000);
			click(sl_OkBtn);
			
			Thread.sleep(2000);
			
			
			
			int reportsByWarehouseRow1ListCount2 = reportsRow1List.size();
			ArrayList<String> reportsByWarehouseRow1ListArraySecLoction = new ArrayList<String>();
			for(int i=0;i<reportsByWarehouseRow1ListCount2;i++)
			{
				String data = reportsRow1List.get(i).getText();
				reportsByWarehouseRow1ListArraySecLoction.add(data);
			}
			String actRow1ListSecLoction = reportsByWarehouseRow1ListArraySecLoction.toString();
			String expRow1ListSecLoction = "[1, SEC]";
			
			

			int reportsByWarehouseRow2ListCount2 = reportsRow2List.size();
			ArrayList<String> reportsByWarehouseRow2ListArraySecLoction = new ArrayList<String>();
			for(int i=0;i<reportsByWarehouseRow2ListCount2;i++)
			{
				String data = reportsRow2List.get(i).getText();
				reportsByWarehouseRow2ListArraySecLoction.add(data);
			}
			String actRow2ListSecLoction = reportsByWarehouseRow2ListArraySecLoction.toString();
			String expRow2ListSecLoction = "[2, BR-BATCH, BR-BATCH, BR-BATCH, 1.0000, 30.3030, 30.3030, SEC, ]";
			
			
			
			int reportsRow3ListCount2 = reportsRow3List.size();
			ArrayList<String> reportsRow3ListArraySecLoction = new ArrayList<String>();
			for(int i=0;i<reportsRow3ListCount2;i++)
			{
				String data = reportsRow3List.get(i).getText();
				reportsRow3ListArraySecLoction.add(data);
			}
			String actRow3ListSecLoction = reportsRow3ListArraySecLoction.toString();
			String expRow3ListSecLoction = "[3, FIFO-BIN, FIFO-BIN, FIFO-BIN, 1.0000, 200.0000, 200.0000, SEC, ]";
			
			
			
			int reportsRow4ListCount2 = reportsRow4List.size();
			ArrayList<String> reportsRow4ListArraySecLoction = new ArrayList<String>();
			for(int i=0;i<reportsRow4ListCount2;i++)
			{
				String data = reportsRow4List.get(i).getText();
				reportsRow4ListArraySecLoction.add(data);
			}
			String actRow4ListSecLoction = reportsRow4ListArraySecLoction.toString();
			String expRow4ListSecLoction = "[4, WA-RMA, WA-RMA, WA-RMA, 1.0000, 1,414.2531, 1,414.2531, SEC, ]";
			
			
			int reportsRow5ListCount2 = reportsRow5List.size();
			ArrayList<String> reportsRow5ListArraySecLoction = new ArrayList<String>();
			for(int i=0;i<reportsRow5ListCount2;i++)
			{
				String data = reportsRow5List.get(i).getText();
				reportsRow5ListArraySecLoction.add(data);
			}
			String actRow5ListSecLoction = reportsRow5ListArraySecLoction.toString();
			String expRow5ListSecLoction = "[5, Grand Total, , , 3.0000, 1,644.5561, 1,644.5561, , ]";
			
			System.out.println("=========StcokMovement  SEC WareHouse  Values============   : ");
			
			
			System.out.println(actRow1ListSecLoction);
			System.out.println(expRow1ListSecLoction);
			
			System.out.println(actRow2ListSecLoction);
			System.out.println(expRow2ListSecLoction);
			
			System.out.println(actRow3ListSecLoction);
			System.out.println(expRow3ListSecLoction);
			
			System.out.println(actRow4ListSecLoction);
			System.out.println(expRow4ListSecLoction);
			
			System.out.println(actRow5ListSecLoction);
			System.out.println(expRow5ListSecLoction);
			
			
		
			
			click(stockLedgerFilterBtn);
			Thread.sleep(1000);
			click(stockLedgerFilterField);
			Thread.sleep(1500);
			click(stockLedgerFilterRefreshBtn);
			Thread.sleep(1000);
			click(stockLedgerFilterOkBtn);
			
			Thread.sleep(3000);
			
			
			
			
			
			
			
			if(actRow1List.equalsIgnoreCase(expRow1List) && actRow2List.equalsIgnoreCase(expRow2List) && actRow3List.equalsIgnoreCase(expRow3List)
					&& actRow4List.equalsIgnoreCase(expRow4List) && actRow5List.equalsIgnoreCase(expRow5List) && actRow6List.equalsIgnoreCase(expRow6List)
					&& actRow7List.equalsIgnoreCase(expRow7List) && actRow8List.equalsIgnoreCase(expRow8List) && actRow9List.equalsIgnoreCase(expRow9List) 
					&& actRow10List.equalsIgnoreCase(expRow10List) && actRow11List.equalsIgnoreCase(expRow11List) && actRow1ListAfterFilter.equalsIgnoreCase(expRow1ListAfterFilter) && actRow2ListAfterFilter.equalsIgnoreCase(expRow2ListAfterFilter)
					&& actRow3ListAfterFilter.equalsIgnoreCase(expRow3ListAfterFilter) && actRow4ListAfterFilter.equalsIgnoreCase(expRow4ListAfterFilter)
					&& actRow5ListAfterFilter.equalsIgnoreCase(expRow5ListAfterFilter) && actRow1ListSecLoction.equalsIgnoreCase(expRow1ListSecLoction) && actRow2ListSecLoction.equalsIgnoreCase(expRow2ListSecLoction)
					&& actRow3ListSecLoction.equalsIgnoreCase(expRow3ListSecLoction) && actRow4ListSecLoction.equalsIgnoreCase(expRow4ListSecLoction)
					&& actRow5ListSecLoction.equalsIgnoreCase(expRow5ListSecLoction))
			{
				click(report_CloseBtn);				
				Thread.sleep(2000);
				click(sl_CloseBtn);	
				excelReader.setCellData(xlfile, xlSheetName, 513, 8, resPass);
				return true;
			}
			else
			{
				click(report_CloseBtn);				
				Thread.sleep(2000);
				click(sl_CloseBtn);
				excelReader.setCellData(xlfile, xlSheetName, 513, 8, resFail);
				return false;
			}
        
		
		
		}	
		
		
		
		public boolean checkStockAgeingAnalysisReportByDefault()
				throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
			
			excelReader = new ExcelReader(POJOUtility.getExcelPath());
			
	
			
			click(inventoryMenu);
			click(InventoryReportsMenu);
			ClickUsingJs(InventoryReportsStockAgeingAnalysisMenu);
			ClickUsingJs(InventoryReportsAgeingAnalysis);
			//click(sl_HeaderSelectChkBox);
			click(selectAllItemChkBox);
			
			dropDown(sl_DateOptionDropdown, excelReader.getCellData(xlSheetName, 524, 5));		
			Thread.sleep(2000);
			click(sl_OkBtn);
			Thread.sleep(2000);
			
			int reportsByWarehouseRow1ListCount = reportsRow1List.size();
			ArrayList<String> reportsByWarehouseRow1ListArray = new ArrayList<String>();
			for(int i=0;i<4;i++)
			{
				String data = reportsRow1List.get(i).getText();
				reportsByWarehouseRow1ListArray.add(data);
			}
			String actRow1List = reportsByWarehouseRow1ListArray.toString();
			String expRow1List = excelReader.getCellData(xlSheetName, 524, 6);
			excelReader.setCellData(xlfile, xlSheetName, 524, 7, actRow1List);
			
			

			int reportsByWarehouseRow2ListCount = reportsRow2List.size();
			ArrayList<String> reportsByWarehouseRow2ListArray = new ArrayList<String>();
			for(int i=0;i<4;i++)
			{
				String data = reportsRow2List.get(i).getText();
				reportsByWarehouseRow2ListArray.add(data);
			}
			String actRow2List = reportsByWarehouseRow2ListArray.toString();
			String expRow2List = excelReader.getCellData(xlSheetName, 525, 6);
			excelReader.setCellData(xlfile, xlSheetName, 525, 7, actRow2List);
			
			
			
			int reportsRow3ListCount = reportsRow3List.size();
			ArrayList<String> reportsRow3ListArray = new ArrayList<String>();
			for(int i=0;i<4;i++)
			{
				String data = reportsRow3List.get(i).getText();
				reportsRow3ListArray.add(data);
			}
			String actRow3List = reportsRow3ListArray.toString();
			String expRow3List = excelReader.getCellData(xlSheetName, 526, 6);
			excelReader.setCellData(xlfile, xlSheetName, 526, 7, actRow3List);
			
			
			
			int reportsRow4ListCount = reportsRow4List.size();
			ArrayList<String> reportsRow4ListArray = new ArrayList<String>();
			for(int i=0;i<4;i++)
			{
				String data = reportsRow4List.get(i).getText();
				reportsRow4ListArray.add(data);
			}
			String actRow4List = reportsRow4ListArray.toString();
			String expRow4List = excelReader.getCellData(xlSheetName, 527, 6);
			excelReader.setCellData(xlfile, xlSheetName, 527, 7, actRow4List);
			
			
			
		
			
			System.out.println("*********************************checkStockAgeing AnalysisReport By Default*****************************************");
			
			System.out.println(actRow1List);
			System.out.println(expRow1List);
			
			System.out.println(actRow2List);
			System.out.println(expRow2List);
			
			System.out.println(actRow3List);
			System.out.println(expRow3List);
			
			System.out.println(actRow4List);
			System.out.println(expRow4List);
			
			
			click(stockLedgerFilterBtn);
			
			Thread.sleep(2000);
			click(stockLedgerFilterField);
			Thread.sleep(1500);

			getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(stockLedgerFilterText));

			boolean actFilterpopupIsDisplaying = stockLedgerFilterText.isDisplayed();
			boolean expFilterpopupIsDisplaying = true;

			System.out.println("Filter Popup isDisplaying  : " + actFilterpopupIsDisplaying + "  Value Expected  "
					+ expFilterpopupIsDisplaying);
			
			
			
			
			click(stockAgeingFilterWarehouseField);
			enterText(stockAgeingFilterWarehouseField, "HYD");
			Thread.sleep(2000);
			click(stockLedgerFilterOkBtn);
			Thread.sleep(2000);
			
			int reportsByWarehouseRow1ListCount1 = reportsRow1List.size();
			ArrayList<String> reportsByWarehouseRow1ListArrayAfterFilter = new ArrayList<String>();
			for(int i=0;i<reportsByWarehouseRow1ListCount1;i++)
			{
				String data = reportsRow1List.get(i).getText();
				if(data.isEmpty()==false)
				{
					reportsByWarehouseRow1ListArrayAfterFilter.add(data);
				}
				
			}
			String actRow1ListAfterFilter = reportsByWarehouseRow1ListArrayAfterFilter.toString();
			String expRow1ListAfterFilter = "[1, WA-RMA, 106.00, 1,375.8648, 12.00, 8,255.19, 94.00, 64,665.64]";
			
			

			int reportsByWarehouseRow2ListCount1 = reportsRow2List.size();
			ArrayList<String> reportsByWarehouseRow2ListArrayAfterFilter = new ArrayList<String>();
			for(int i=0;i<reportsByWarehouseRow2ListCount1;i++)
			{
				String data = reportsRow2List.get(i).getText();
				if(data.isEmpty()==false)
				{
					reportsByWarehouseRow2ListArrayAfterFilter.add(data);
				}
				
			}
			String actRow2ListAfterFilter = reportsByWarehouseRow2ListArrayAfterFilter.toString();
			String expRow2ListAfterFilter = "[2, FIFO-BIN, 106.00, 200.0000, 12.00, 1,200.00, 94.00, 9,400.00]";
			
			
			
			int reportsRow3ListCount1 = reportsRow3List.size();
			ArrayList<String> reportsRow3ListArrayAfterFilter = new ArrayList<String>();
			for(int i=0;i<reportsRow3ListCount1;i++)
			{
				String data = reportsRow3List.get(i).getText();
				if(data.isEmpty()==false)
				{
					reportsRow3ListArrayAfterFilter.add(data);
				}
				
			}
			String actRow3ListAfterFilter = reportsRow3ListArrayAfterFilter.toString();
			String expRow3ListAfterFilter = "[3, BR-BATCH, 106.00, 120.2020, 12.00, 721.21, 94.00, 5,649.49]";
			
			
			
			int reportsRow4ListCount1 = reportsRow4List.size();
			ArrayList<String> reportsRow4ListArrayAfterFilter = new ArrayList<String>();
			for(int i=0;i<reportsRow4ListCount1;i++)
			{
				String data = reportsRow4List.get(i).getText();
				if(data.isEmpty()==false)
				{
					reportsRow4ListArrayAfterFilter.add(data);
				}
				
			}
			String actRow4ListAfterFilter = reportsRow4ListArrayAfterFilter.toString();
			String expRow4ListAfterFilter = "[4, Grand Total, 318.00, 1,696.0668, 36.00, 10,176.40, 282.00, 79,715.14]";
			

			
			System.out.println("=========StcokMovement HydWareHouse  Values============   : ");
			
			System.out.println(actRow1ListAfterFilter);
			System.out.println(expRow1ListAfterFilter);
			
			System.out.println(actRow2ListAfterFilter);
			System.out.println(expRow2ListAfterFilter);
			
			System.out.println(actRow3ListAfterFilter);
			System.out.println(expRow3ListAfterFilter);
			
			System.out.println(actRow4ListAfterFilter);
			System.out.println(expRow4ListAfterFilter);
			
		
			
			click(report_CloseBtn);
			Thread.sleep(1000);
			click(stockLedgerHomePageFilterBtn);
			Thread.sleep(2000);
			click(stockAgeingWarehouseField2);
			Thread.sleep(1000);
			click(stockAgeingFilterWarehouseField3);
			Thread.sleep(1500);
			stockAgeingFilterWarehouseField4.sendKeys(Keys.CONTROL, "a");
			Thread.sleep(1000);
			stockAgeingFilterWarehouseField4.sendKeys(Keys.BACK_SPACE);
			Thread.sleep(1000);
			enterText(stockAgeingFilterWarehouseField4, "SEC");
			Thread.sleep(1000);
			click(stockLedgerFilterOkBtn);
			Thread.sleep(1000);
			click(sl_OkBtn);
			
			Thread.sleep(2000);
			
			
			
			int reportsByWarehouseRow1ListCount2 = reportsRow1List.size();
			ArrayList<String> reportsByWarehouseRow1ListArraySecLoction = new ArrayList<String>();
			for(int i=0;i<reportsByWarehouseRow1ListCount2;i++)
			{
				String data = reportsRow1List.get(i).getText();
				if(data.isEmpty()==false)
				{
					reportsByWarehouseRow1ListArraySecLoction.add(data);
				}
				
			}
			String actRow1ListSecLoction = reportsByWarehouseRow1ListArraySecLoction.toString();
			String expRow1ListSecLoction = "[1, WA-RMA, 103.00, 1,414.2531, 12.00, 16,971.04, 91.00, 1,28,697.03]";
			
			

			int reportsByWarehouseRow2ListCount2 = reportsRow2List.size();
			ArrayList<String> reportsByWarehouseRow2ListArraySecLoction = new ArrayList<String>();
			for(int i=0;i<reportsByWarehouseRow2ListCount2;i++)
			{
				String data = reportsRow2List.get(i).getText();
				if(data.isEmpty()==false)
				{
					reportsByWarehouseRow2ListArraySecLoction.add(data);
				}
				
			}
			String actRow2ListSecLoction = reportsByWarehouseRow2ListArraySecLoction.toString();
			String expRow2ListSecLoction = "[2, FIFO-BIN, 103.00, 200.0000, 12.00, 2,400.00, 91.00, 18,200.00]";
			
			
			
			int reportsRow3ListCount2 = reportsRow3List.size();
			ArrayList<String> reportsRow3ListArraySecLoction = new ArrayList<String>();
			for(int i=0;i<reportsRow3ListCount2;i++)
			{
				String data = reportsRow3List.get(i).getText();
				if(data.isEmpty()==false)
				{
					reportsRow3ListArraySecLoction.add(data);
				}
				
			}
			String actRow3ListSecLoction = reportsRow3ListArraySecLoction.toString();
			String expRow3ListSecLoction = "[3, BR-BATCH, 103.00, 30.3030, 12.00, 363.64, 91.00, 2,757.57]";
			
			
			
			int reportsRow4ListCount2 = reportsRow4List.size();
			ArrayList<String> reportsRow4ListArraySecLoction = new ArrayList<String>();
			for(int i=0;i<reportsRow4ListCount2;i++)
			{
				String data = reportsRow4List.get(i).getText();
				if(data.isEmpty()==false)
				{
					reportsRow4ListArraySecLoction.add(data);
				}
				
			}
			String actRow4ListSecLoction = reportsRow4ListArraySecLoction.toString();
			String expRow4ListSecLoction = "[4, Grand Total, 309.00, 1,644.5561, 36.00, 19,734.67, 273.00, 1,49,654.61]";
			
			
		
			
			System.out.println("=========StcokMovement  SEC WareHouse  Values============   : ");
			
			
			System.out.println(actRow1ListSecLoction);
			System.out.println(expRow1ListSecLoction);
			
			System.out.println(actRow2ListSecLoction);
			System.out.println(expRow2ListSecLoction);
			
			System.out.println(actRow3ListSecLoction);
			System.out.println(expRow3ListSecLoction);
			
			System.out.println(actRow4ListSecLoction);
			System.out.println(expRow4ListSecLoction);
		
		
			
			click(stockLedgerFilterBtn);
			Thread.sleep(1000);
			click(stockLedgerFilterField);
			Thread.sleep(1500);
			click(stockLedgerFilterRefreshBtn);
			Thread.sleep(1000);
			click(stockLedgerFilterOkBtn);
			
			Thread.sleep(2000);
			
		
			
			if(actRow1List.equalsIgnoreCase(expRow1List) && actRow2List.equalsIgnoreCase(expRow2List) && actRow3List.equalsIgnoreCase(expRow3List) 
				&& actRow4List.equalsIgnoreCase(expRow4List) && actRow1ListAfterFilter.equalsIgnoreCase(expRow1ListAfterFilter) && actRow2ListAfterFilter.equalsIgnoreCase(expRow2ListAfterFilter)
				&& actRow3ListAfterFilter.equalsIgnoreCase(expRow3ListAfterFilter) && actRow4ListAfterFilter.equalsIgnoreCase(expRow4ListAfterFilter)
				&& actRow1ListSecLoction.equalsIgnoreCase(expRow1ListSecLoction) && actRow2ListSecLoction.equalsIgnoreCase(expRow2ListSecLoction)
				&& actRow3ListSecLoction.equalsIgnoreCase(expRow3ListSecLoction) && actRow4ListSecLoction.equalsIgnoreCase(expRow4ListSecLoction))
			{
				click(report_CloseBtn);
				excelReader.setCellData(xlfile, xlSheetName, 524, 8, resPass);
				return true;
			}
			else
			{
				click(report_CloseBtn);
				excelReader.setCellData(xlfile, xlSheetName, 524, 8, resFail);
				return false;
			}
		}

		
		
		
		public boolean checkStockAgeingAnalysisReportByWarehouse()
				throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
			
			excelReader =new ExcelReader(POJOUtility.getExcelPath());
			
			Thread.sleep(2000);
			
			dropDown(stockAgeingDropdown, excelReader.getCellData(xlSheetName, 528, 5));		
			Thread.sleep(3000);
			click(sl_OkBtn);
			Thread.sleep(2000);
			
			int reportsByWarehouseRow1ListCount = reportsRow1List.size();
			ArrayList<String> reportsByWarehouseRow1ListArray = new ArrayList<String>();
			for(int i=0;i<reportsByWarehouseRow1ListCount;i++)
			{
				String data = reportsRow1List.get(i).getText();
				reportsByWarehouseRow1ListArray.add(data);
			}
			String actRow1List = reportsByWarehouseRow1ListArray.toString();
			String expRow1List = excelReader.getCellData(xlSheetName, 528, 6);
			excelReader.setCellData(xlfile, xlSheetName, 528, 7, actRow1List);
			
			

			int reportsByWarehouseRow2ListCount = reportsRow2List.size();
			ArrayList<String> reportsByWarehouseRow2ListArray = new ArrayList<String>();
			for(int i=0;i<4;i++)
			{
				String data = reportsRow2List.get(i).getText();
				reportsByWarehouseRow2ListArray.add(data);
			}
			String actRow2List = reportsByWarehouseRow2ListArray.toString();
			String expRow2List = excelReader.getCellData(xlSheetName, 529, 6);
			excelReader.setCellData(xlfile, xlSheetName, 529, 7, actRow2List);
			
			
			
			int reportsRow3ListCount = reportsRow3List.size();
			ArrayList<String> reportsRow3ListArray = new ArrayList<String>();
			for(int i=0;i<4;i++)
			{
				String data = reportsRow3List.get(i).getText();
				reportsRow3ListArray.add(data);
			}
			String actRow3List = reportsRow3ListArray.toString();
			String expRow3List = excelReader.getCellData(xlSheetName, 530, 6);
			excelReader.setCellData(xlfile, xlSheetName, 530, 7, actRow3List);
			
			
			
			int reportsRow4ListCount = reportsRow4List.size();
			ArrayList<String> reportsRow4ListArray = new ArrayList<String>();
			for(int i=0;i<4;i++)
			{
				String data = reportsRow4List.get(i).getText();
				reportsRow4ListArray.add(data);
			}
			String actRow4List = reportsRow4ListArray.toString();
			String expRow4List = excelReader.getCellData(xlSheetName, 531, 6);
			excelReader.setCellData(xlfile, xlSheetName, 531, 7, actRow4List);
			
			int reportsRow5ListCount = reportsRow5List.size();
			ArrayList<String> reportsRow5ListArray = new ArrayList<String>();
			for(int i=0;i<4;i++)
			{
				String data = reportsRow5List.get(i).getText();
				reportsRow5ListArray.add(data);
			}
			String actRow5List = reportsRow5ListArray.toString();
			String expRow5List = excelReader.getCellData(xlSheetName, 532, 6);
			excelReader.setCellData(xlfile, xlSheetName, 532, 7, actRow5List);
			
			
			int report6thRowListCount = reportsRow6List.size();
			ArrayList<String> report6thRowListArray = new ArrayList<String>();
			for(int i=0;i<report6thRowListCount;i++)
			{
				String data = reportsRow6List.get(i).getText();
				report6thRowListArray.add(data);
			}
			String actRow6List = report6thRowListArray.toString();
			String expRow6List = excelReader.getCellData(xlSheetName, 533, 6);
			excelReader.setCellData(xlfile, xlSheetName, 533, 7, actRow6List);
			
			
			int report7thRowListCount = reportsRow7List.size();
			ArrayList<String> report7thRowListArray = new ArrayList<String>();
			for(int i=0;i<4;i++)
			{
				String data = reportsRow7List.get(i).getText();
				report7thRowListArray.add(data);
			}
			String actRow7List = report7thRowListArray.toString();
			String expRow7List = excelReader.getCellData(xlSheetName, 534, 6);
			excelReader.setCellData(xlfile, xlSheetName, 534, 7, actRow7List);
		
			
		
			
			int report8thRowListCount = reportsRow8List.size();
			ArrayList<String> report8thRowListArray = new ArrayList<String>();
			for(int i=0;i<4;i++)
			{
				String data = reportsRow8List.get(i).getText();
				report8thRowListArray.add(data);
			}
			String actRow8List = report8thRowListArray.toString();
			String expRow8List = excelReader.getCellData(xlSheetName, 535, 6);
			excelReader.setCellData(xlfile, xlSheetName, 535, 7, actRow8List);
			
			
			
			int report9thRowListCount = reportsRow9List.size();
			ArrayList<String> report9thRowListArray = new ArrayList<String>();
			for(int i=0;i<4;i++)
			{
				String data = reportsRow9List.get(i).getText();
				report9thRowListArray.add(data);
			}
			String actRow9List = report9thRowListArray.toString();
			String expRow9List = excelReader.getCellData(xlSheetName, 536, 6);
			excelReader.setCellData(xlfile, xlSheetName, 536, 7, actRow9List);
			
			
			
			int report10thRowListCount = reportsRow10List.size();
			ArrayList<String> report10thRowListArray = new ArrayList<String>();
			for(int i=0;i<4;i++)
			{
				String data = reportsRow10List.get(i).getText();
				report10thRowListArray.add(data);
			}
			String actRow10List = report10thRowListArray.toString();
			String expRow10List = excelReader.getCellData(xlSheetName, 537, 6);
			excelReader.setCellData(xlfile, xlSheetName, 537, 7, actRow10List);
			
			
			
			int reportsRow11ListCount = reportsRow11List.size();
			ArrayList<String> reportsRow11ListArray = new ArrayList<String>();
			for(int i=0;i<4;i++)
			{
				String data = reportsRow11List.get(i).getText();
				reportsRow11ListArray.add(data);
			}
			String actRow11List = reportsRow11ListArray.toString();
			String expRow11List = excelReader.getCellData(xlSheetName, 538, 6);
			excelReader.setCellData(xlfile, xlSheetName, 538, 7, actRow11List);
			
			
			
			System.out.println("*********************************checkStock Ageing Analysis Report By WareHouse*****************************************");
			
		
			
			
			
			System.out.println(actRow1List);
			System.out.println(expRow1List);
			
			System.out.println(actRow2List);
			System.out.println(expRow2List);
			
			System.out.println(actRow3List);
			System.out.println(expRow3List);
			
			System.out.println(actRow4List);
			System.out.println(expRow4List);
			
			System.out.println(actRow5List);
			System.out.println(expRow5List);
			
			System.out.println(actRow6List);
			System.out.println(expRow6List);
			
			System.out.println(actRow7List);
			System.out.println(expRow7List);
			
			System.out.println(actRow8List);
			System.out.println(expRow8List);
			
			System.out.println(actRow9List);
			System.out.println(expRow9List);
			
			System.out.println(actRow10List);
			System.out.println(expRow10List);
			
			System.out.println(actRow11List);
			System.out.println(expRow11List);
			
			
			
			if(actRow1List.equalsIgnoreCase(expRow1List) && actRow2List.equalsIgnoreCase(expRow2List) && actRow3List.equalsIgnoreCase(expRow3List)
					&& actRow4List.equalsIgnoreCase(expRow4List) && actRow5List.equalsIgnoreCase(expRow5List) && actRow6List.equalsIgnoreCase(expRow6List)
					&& actRow7List.equalsIgnoreCase(expRow7List) && actRow8List.equalsIgnoreCase(expRow8List) && actRow9List.equalsIgnoreCase(expRow9List) 
					&& actRow10List.equalsIgnoreCase(expRow10List) && actRow11List.equalsIgnoreCase(expRow11List))
			{
				click(report_CloseBtn);				
				excelReader.setCellData(xlfile, xlSheetName, 528, 8, resPass);			
				return true;
			}
			else
			{
				click(report_CloseBtn);				
				excelReader.setCellData(xlfile, xlSheetName, 528, 8, resFail);
				return false;
			}
        
		
		
		}	
		
		
		
		public boolean checkStockAgeingAnalysisReportByWarehouseHyd()
				throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
			
			excelReader =new ExcelReader(POJOUtility.getExcelPath());
			
			
			Thread.sleep(2000);
			click(stockAgeingAnalysisWarehouseField);
			enterText(stockAgeingAnalysisWarehouseField, excelReader.getCellData(xlSheetName, 539, 5));
			Thread.sleep(2000);
			dropDown(stockAgeingDropdown, excelReader.getCellData(xlSheetName, 540, 5));
			
			Thread.sleep(2000);
			click(sl_OkBtn);
			Thread.sleep(3000);
			
			int reportsByWarehouseRow1ListCount = reportsRow1List.size();
			ArrayList<String> reportsByWarehouseRow1ListArray = new ArrayList<String>();
			for(int i=0;i<4;i++)
			{
				String data = reportsRow1List.get(i).getText();
				reportsByWarehouseRow1ListArray.add(data);
			}
			String actRow1List = reportsByWarehouseRow1ListArray.toString();
			String expRow1List = excelReader.getCellData(xlSheetName, 539, 6);
			excelReader.setCellData(xlfile, xlSheetName, 539, 7, actRow1List);
			
			

			int reportsByWarehouseRow2ListCount = reportsRow2List.size();
			ArrayList<String> reportsByWarehouseRow2ListArray = new ArrayList<String>();
			for(int i=0;i<4;i++)
			{
				String data = reportsRow2List.get(i).getText();
				reportsByWarehouseRow2ListArray.add(data);
			}
			String actRow2List = reportsByWarehouseRow2ListArray.toString();
			String expRow2List = excelReader.getCellData(xlSheetName, 540, 6);
			excelReader.setCellData(xlfile, xlSheetName, 540, 7, actRow2List);
			
			
			
			int reportsRow3ListCount = reportsRow3List.size();
			ArrayList<String> reportsRow3ListArray = new ArrayList<String>();
			for(int i=0;i<4;i++)
			{
				String data = reportsRow3List.get(i).getText();
				reportsRow3ListArray.add(data);
			}
			String actRow3List = reportsRow3ListArray.toString();
			String expRow3List = excelReader.getCellData(xlSheetName, 541, 6);
			excelReader.setCellData(xlfile, xlSheetName, 541, 7, actRow3List);
			
			
			
			int reportsRow4ListCount = reportsRow4List.size();
			ArrayList<String> reportsRow4ListArray = new ArrayList<String>();
			for(int i=0;i<4;i++)
			{
				String data = reportsRow4List.get(i).getText();
				reportsRow4ListArray.add(data);
			}
			String actRow4List = reportsRow4ListArray.toString();
			String expRow4List = excelReader.getCellData(xlSheetName, 542, 6);
			excelReader.setCellData(xlfile, xlSheetName, 542, 7, actRow4List);
			
		

			System.out.println("*********************************checkStock Ageing Analysis Report By WareHouse HYD*****************************************");
			
		
			System.out.println(actRow1List);
			System.out.println(expRow1List);
			
			System.out.println(actRow2List);
			System.out.println(expRow2List);
			
			System.out.println(actRow3List);
			System.out.println(expRow3List);
			
			System.out.println(actRow4List);
			System.out.println(expRow4List);
			
		
			
		
			if(actRow1List.equalsIgnoreCase(expRow1List) && actRow2List.equalsIgnoreCase(expRow2List) && actRow3List.equalsIgnoreCase(expRow3List)
					&& actRow4List.equalsIgnoreCase(expRow4List))
			{
				click(report_CloseBtn);				
				excelReader.setCellData(xlfile, xlSheetName, 539, 8, resPass);			
				return true;
			}
			else
			{
				click(report_CloseBtn);				
				excelReader.setCellData(xlfile, xlSheetName, 539, 8, resFail);
				return false;
			}
       
		}	
		
		
		public boolean checkStockAgeingAnalysisReportByWarehouseSEC()
				throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
			
			excelReader =new ExcelReader(POJOUtility.getExcelPath());
			
			Thread.sleep(2000);
			click(stockAgeingAnalysisWarehouseField);			
			stockAgeingAnalysisWarehouseField.sendKeys(Keys.CONTROL, "a");
			Thread.sleep(2000);
			stockAgeingAnalysisWarehouseField.sendKeys(Keys.BACK_SPACE);			
			enterText(stockAgeingAnalysisWarehouseField, excelReader.getCellData(xlSheetName, 543, 5));			
			Thread.sleep(4000);
			click(sl_OkBtn);
			Thread.sleep(2000);
			
			int reportsByWarehouseRow1ListCount = reportsRow1List.size();
			ArrayList<String> reportsByWarehouseRow1ListArray = new ArrayList<String>();
			for(int i=0;i<4;i++)
			{
				String data = reportsRow1List.get(i).getText();
				reportsByWarehouseRow1ListArray.add(data);
			}
			String actRow1List = reportsByWarehouseRow1ListArray.toString();
			String expRow1List = excelReader.getCellData(xlSheetName, 543, 6);
			excelReader.setCellData(xlfile, xlSheetName, 543, 7, actRow1List);
			
			

			int reportsByWarehouseRow2ListCount = reportsRow2List.size();
			ArrayList<String> reportsByWarehouseRow2ListArray = new ArrayList<String>();
			for(int i=0;i<4;i++)
			{
				String data = reportsRow2List.get(i).getText();
				reportsByWarehouseRow2ListArray.add(data);
			}
			String actRow2List = reportsByWarehouseRow2ListArray.toString();
			String expRow2List = excelReader.getCellData(xlSheetName, 544, 6);
			excelReader.setCellData(xlfile, xlSheetName, 544, 7, actRow2List);
			
			
			
			int reportsRow3ListCount = reportsRow3List.size();
			ArrayList<String> reportsRow3ListArray = new ArrayList<String>();
			for(int i=0;i<4;i++)
			{
				String data = reportsRow3List.get(i).getText();
				reportsRow3ListArray.add(data);
			}
			String actRow3List = reportsRow3ListArray.toString();
			String expRow3List = excelReader.getCellData(xlSheetName, 545, 6);
			excelReader.setCellData(xlfile, xlSheetName, 545, 7, actRow3List);
			
			
			
			int reportsRow4ListCount = reportsRow4List.size();
			ArrayList<String> reportsRow4ListArray = new ArrayList<String>();
			for(int i=0;i<4;i++)
			{
				String data = reportsRow4List.get(i).getText();
				reportsRow4ListArray.add(data);
			}
			String actRow4List = reportsRow4ListArray.toString();
			String expRow4List = excelReader.getCellData(xlSheetName, 546, 6);
			excelReader.setCellData(xlfile, xlSheetName, 546, 7, actRow4List);
			
			
			

			System.out.println("*********************************checkStock Ageing Analysis Report By WareHouse SEC*****************************************");
			
			
			System.out.println(actRow1List);
			System.out.println(expRow1List);
			
			System.out.println(actRow2List);
			System.out.println(expRow2List);
			
			System.out.println(actRow3List);
			System.out.println(expRow3List);
			
			System.out.println(actRow4List);
			System.out.println(expRow4List);
			
		
		
			if(actRow1List.equalsIgnoreCase(expRow1List) && actRow2List.equalsIgnoreCase(expRow2List) && actRow3List.equalsIgnoreCase(expRow3List)
					&& actRow4List.equalsIgnoreCase(expRow4List))
			{
				click(report_CloseBtn);				
				Thread.sleep(2000);
				click(sl_CloseBtn);	
				excelReader.setCellData(xlfile, xlSheetName, 543, 8, resPass);
				return true;
			}
			else
			{
			   click(report_CloseBtn);
			   Thread.sleep(2000);
			   click(sl_CloseBtn);
			   excelReader.setCellData(xlfile, xlSheetName, 543, 8, resFail);
			   return false;
			}
        
		
		
		}	
		
		
		
		
		public boolean checkStockBalanceByWarehouseReport()
				throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
			
			excelReader =new ExcelReader(POJOUtility.getExcelPath());
			
			
			
			click(inventoryMenu);
			click(InventoryReportsMenu);
			Thread.sleep(1000);
			ClickUsingJs(InventoryReportsStockBalanceByWarehouseMenu);
			//click(sl_HeaderSelectChkBox);
			click(selectAllItemChkBox);
			
			
			dropDown(sl_DateOptionDropdown, excelReader.getCellData(xlSheetName, 547, 5));		
			Thread.sleep(2000);
			click(sl_OkBtn);
			Thread.sleep(2000);
			
			int reportsByWarehouseRow1ListCount = reportsRow1List.size();
			ArrayList<String> reportsByWarehouseRow1ListArray = new ArrayList<String>();
			for(int i=0;i<reportsByWarehouseRow1ListCount;i++)
			{
				String data = reportsRow1List.get(i).getText();
				reportsByWarehouseRow1ListArray.add(data);
			}
			String actRow1List = reportsByWarehouseRow1ListArray.toString();
			String expRow1List = excelReader.getCellData(xlSheetName, 547, 6);
			excelReader.setCellData(xlfile, xlSheetName, 547, 7, actRow1List);
			
			

			int reportsByWarehouseRow2ListCount = reportsRow2List.size();
			ArrayList<String> reportsByWarehouseRow2ListArray = new ArrayList<String>();
			for(int i=0;i<reportsByWarehouseRow2ListCount;i++)
			{
				String data = reportsRow2List.get(i).getText();
				reportsByWarehouseRow2ListArray.add(data);
			}
			String actRow2List = reportsByWarehouseRow2ListArray.toString();
			String expRow2List = excelReader.getCellData(xlSheetName, 548, 6);
			excelReader.setCellData(xlfile, xlSheetName, 548, 7, actRow2List);
			
			
			
			int reportsRow3ListCount = reportsRow3List.size();
			ArrayList<String> reportsRow3ListArray = new ArrayList<String>();
			for(int i=0;i<reportsRow3ListCount;i++)
			{
				String data = reportsRow3List.get(i).getText();
				reportsRow3ListArray.add(data);
			}
			String actRow3List = reportsRow3ListArray.toString();
			String expRow3List = excelReader.getCellData(xlSheetName, 549, 6);
			excelReader.setCellData(xlfile, xlSheetName, 549, 7, actRow3List);
			
			
			
			int reportsRow4ListCount = reportsRow4List.size();
			ArrayList<String> reportsRow4ListArray = new ArrayList<String>();
			for(int i=0;i<reportsRow4ListCount;i++)
			{
				String data = reportsRow4List.get(i).getText();
				reportsRow4ListArray.add(data);
			}
			String actRow4List = reportsRow4ListArray.toString();
			String expRow4List = excelReader.getCellData(xlSheetName, 550, 6);
			excelReader.setCellData(xlfile, xlSheetName, 550, 7, actRow4List);
			
		
			int reportsRow5ListCount = reportsRow5List.size();
			ArrayList<String> reportsRow5ListArray = new ArrayList<String>();
			for(int i=0;i<reportsRow5ListCount;i++)
			{
				String data = reportsRow5List.get(i).getText();
				reportsRow5ListArray.add(data);
			}
			String actRow5List = reportsRow5ListArray.toString();
			String expRow5List = excelReader.getCellData(xlSheetName, 551, 6);
			excelReader.setCellData(xlfile, xlSheetName, 551, 7, actRow5List);
			
			
			int report6thRowListCount = reportsRow6List.size();
			ArrayList<String> report6thRowListArray = new ArrayList<String>();
			for(int i=0;i<report6thRowListCount;i++)
			{
				String data = reportsRow6List.get(i).getText();
				report6thRowListArray.add(data);
			}
			String actRow6List = report6thRowListArray.toString();
			String expRow6List = excelReader.getCellData(xlSheetName, 552, 6);
			excelReader.setCellData(xlfile, xlSheetName, 552, 7, actRow6List);
			
			
			int report7thRowListCount = reportsRow7List.size();
			ArrayList<String> report7thRowListArray = new ArrayList<String>();
			for(int i=0;i<report7thRowListCount;i++)
			{
				String data = reportsRow7List.get(i).getText();
				report7thRowListArray.add(data);
			}
			String actRow7List = report7thRowListArray.toString();
			String expRow7List = excelReader.getCellData(xlSheetName, 553, 6);
			excelReader.setCellData(xlfile, xlSheetName, 553, 7, actRow7List);
		
			
		
			
			int report8thRowListCount = reportsRow8List.size();
			ArrayList<String> report8thRowListArray = new ArrayList<String>();
			for(int i=0;i<report8thRowListCount;i++)
			{
				String data = reportsRow8List.get(i).getText();
				report8thRowListArray.add(data);
			}
			String actRow8List = report8thRowListArray.toString();
			String expRow8List = excelReader.getCellData(xlSheetName, 554, 6);
			excelReader.setCellData(xlfile, xlSheetName, 554, 7, actRow8List);
			
			
			
			int report9thRowListCount = reportsRow9List.size();
			ArrayList<String> report9thRowListArray = new ArrayList<String>();
			for(int i=0;i<report9thRowListCount;i++)
			{
				String data = reportsRow9List.get(i).getText();
				report9thRowListArray.add(data);
			}
			String actRow9List = report9thRowListArray.toString();
			String expRow9List = excelReader.getCellData(xlSheetName, 555, 6);
			excelReader.setCellData(xlfile, xlSheetName, 555, 7, actRow9List);
			
			
			
			int report10thRowListCount = reportsRow10List.size();
			ArrayList<String> report10thRowListArray = new ArrayList<String>();
			for(int i=0;i<report10thRowListCount;i++)
			{
				String data = reportsRow10List.get(i).getText();
				report10thRowListArray.add(data);
			}
			String actRow10List = report10thRowListArray.toString();
			String expRow10List = excelReader.getCellData(xlSheetName, 556, 6);
			excelReader.setCellData(xlfile, xlSheetName, 556, 7, actRow10List);
			
			int reportsRow11ListCount = reportsRow11List.size();
			ArrayList<String> reportsRow11ListArray = new ArrayList<String>();
			for(int i=0;i<reportsRow11ListCount;i++)
			{
				String data = reportsRow11List.get(i).getText();
				reportsRow11ListArray.add(data);
			}
			String actRow11List = reportsRow11ListArray.toString();
			String expRow11List = excelReader.getCellData(xlSheetName, 557, 6);
			excelReader.setCellData(xlfile, xlSheetName, 557, 7, actRow11List);
			
			
			
			System.out.println("*********************************checkStockBalanceByWarehouseReport*****************************************");
			
		
			
			
			
			System.out.println(actRow1List);
			System.out.println(expRow1List);
			
			System.out.println(actRow2List);
			System.out.println(expRow2List);
			
			System.out.println(actRow3List);
			System.out.println(expRow3List);
			
			System.out.println(actRow4List);
			System.out.println(expRow4List);
			
			System.out.println(actRow5List);
			System.out.println(expRow5List);
			
			System.out.println(actRow6List);
			System.out.println(expRow6List);
			
			System.out.println(actRow7List);
			System.out.println(expRow7List);
			
			System.out.println(actRow8List);
			System.out.println(expRow8List);
			
			System.out.println(actRow9List);
			System.out.println(expRow9List);
			
			System.out.println(actRow10List);
			System.out.println(expRow10List);
			
			System.out.println(actRow11List);
			System.out.println(expRow11List);
			
			
			
			if(actRow1List.equalsIgnoreCase(expRow1List) && actRow2List.equalsIgnoreCase(expRow2List) && actRow3List.equalsIgnoreCase(expRow3List)
					&& actRow4List.equalsIgnoreCase(expRow4List) && actRow5List.equalsIgnoreCase(expRow5List) && actRow6List.equalsIgnoreCase(expRow6List)
					&& actRow7List.equalsIgnoreCase(expRow7List) && actRow8List.equalsIgnoreCase(expRow8List) && actRow9List.equalsIgnoreCase(expRow9List) 
					&& actRow10List.equalsIgnoreCase(expRow10List) && actRow11List.equalsIgnoreCase(expRow11List))
			{
				click(report_CloseBtn);				
				Thread.sleep(2000);
				click(sl_CloseBtn);		
				excelReader.setCellData(xlfile, xlSheetName, 547, 8, resPass);
				return true;
			}
			else
			{
				click(report_CloseBtn);				
				Thread.sleep(2000);
				click(sl_CloseBtn);	
				excelReader.setCellData(xlfile, xlSheetName, 547, 8, resFail);
				return false;
			}
        
		
		
		}	
		
		
		
	public boolean checkStockStatementReporFortBrBatchItem()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		
		excelReader =new ExcelReader(POJOUtility.getExcelPath());
		
		
		
		click(inventoryMenu);
		click(InventoryReportsMenu);
		click(InventoryStockStatementMenu);
		Thread.sleep(3000);

		int rowcount = stockLedgerHometableRowCount.size();

		System.out.println(rowcount);

		for (int i = 0; i < rowcount; i++) {
			String actName = ledgerHometableItemNamesList.get(i).getText();

			System.out.println(actName);

			if (actName.equalsIgnoreCase(excelReader.getCellData(xlSheetName, 558, 5))) {
				stockLedgerHometableItemChkboxList.get(i).click();
			}
		}

		dropDown(sl_DateOptionDropdown, excelReader.getCellData(xlSheetName, 559, 5));
		Thread.sleep(2000);
		click(sl_OkBtn);
		Thread.sleep(2000);
		click(stockLedgerLastBtn);
		Thread.sleep(15000);

		int reportsByWarehouseRow14ListCount1 = reportsRow14List.size();
		ArrayList<String> reportsByWarehouseRow14ListArray1 = new ArrayList<String>();
		for (int i = 0; i < reportsByWarehouseRow14ListCount1; i++) {
			String data = reportsRow14List.get(i).getText();
			reportsByWarehouseRow14ListArray1.add(data);
		}
		String actRow14List1 = reportsByWarehouseRow14ListArray1.toString();
		String expRow14List1 = excelReader.getCellData(xlSheetName, 558, 6);
		excelReader.setCellData(xlfile, xlSheetName, 558, 7, actRow14List1);

		System.out.println(actRow14List1);
		System.out.println(expRow14List1);
		
		
		click(stockLedgerFilterBtn);
		
		Thread.sleep(2000);
		click(stockLedgerFilterField);
		Thread.sleep(1500);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(stockLedgerFilterText));

		boolean actFilterpopupIsDisplaying = stockLedgerFilterText.isDisplayed();
		boolean expFilterpopupIsDisplaying = true;

		System.out.println("Filter Popup isDisplaying  : " + actFilterpopupIsDisplaying + "  Value Expected  "
				+ expFilterpopupIsDisplaying);
		
		
		click(stockStatementFilterWarehouseField);
		enterText(stockStatementFilterWarehouseField, "HYD");
		Thread.sleep(2000);
		click(stockLedgerFilterOkBtn);
		Thread.sleep(2000);
		click(stockLedgerLastBtn);
		Thread.sleep(20000);
		
		
		int reportsByWarehouseRow13ListCount1 = reportsRow13List.size();
		ArrayList<String> reportsByWarehouseRow13ListArray1 = new ArrayList<String>();
		for(int i=0;i<reportsByWarehouseRow13ListCount1;i++)
		{
			String data = reportsRow13List.get(i).getText();
			reportsByWarehouseRow13ListArray1.add(data);
		}
		String actRow13List1 = reportsByWarehouseRow13ListArray1.toString();
		String expRow13List1 = "[166, 07/09/2021, 1.0000, 1.0000, , 2.0000, 120.2020, BR-BATCH]";
		
		int reportsByWarehouseRow14ListCount = reportsRow14List.size();
		ArrayList<String> reportsByWarehouseRow14ListArray = new ArrayList<String>();
		for(int i=0;i<reportsByWarehouseRow14ListCount;i++)
		{
			String data = reportsRow14List.get(i).getText();
			reportsByWarehouseRow14ListArray.add(data);
		}
		String actRow14List = reportsByWarehouseRow14ListArray.toString();
		String expRow14List = "[167, Total, 870.0000, 425.0000, 433.0000, 2.0000, 120.2020, ]";
		
		System.out.println("===========Stock Statement Report for Bt Item HYD Location====================");
		
		System.out.println(actRow13List1);
		System.out.println(expRow13List1);
		
		System.out.println(actRow14List);
		System.out.println(expRow14List);
		
		
		click(report_CloseBtn);
		Thread.sleep(1000);
		click(stockLedgerHomePageFilterBtn);
		Thread.sleep(2000);
		click(stockStatementWarehouseField2);
		Thread.sleep(1000);
		click(stockStatementFilterWarehouseField3);
		Thread.sleep(1500);
		stockStatementFilterWarehouseField4.sendKeys(Keys.CONTROL, "a");
		Thread.sleep(1000);
		stockStatementFilterWarehouseField4.sendKeys(Keys.BACK_SPACE);
		Thread.sleep(1000);
		enterText(stockStatementFilterWarehouseField4, "SEC");
		Thread.sleep(1000);
		click(stockLedgerFilterOkBtn);
		Thread.sleep(1000);
		click(sl_OkBtn);
		
		Thread.sleep(2000);
		click(stockLedgerLastBtn);
		Thread.sleep(20000);
		
		int reportsByWarehouseRow16ListCount = reportsRow16List.size();
		ArrayList<String> reportsByWarehouseRow16ListArray = new ArrayList<String>();
		for(int i=0;i<reportsByWarehouseRow16ListCount;i++)
		{
			String data = reportsRow16List.get(i).getText();
			reportsByWarehouseRow16ListArray.add(data);
		}
		String actRow16List = reportsByWarehouseRow16ListArray.toString();
		String expRow16List = "[169, 07/09/2021, , 1.0000, , 1.0000, 30.3030, BR-BATCH]";
		
		int reportsByWarehouseRow17ListCount = reportsRow17List.size();
		ArrayList<String> reportsByWarehouseRow17ListArray = new ArrayList<String>();
		for(int i=0;i<reportsByWarehouseRow17ListCount;i++)
		{
			String data = reportsRow17List.get(i).getText();
			reportsByWarehouseRow17ListArray.add(data);
		}
		String actRow17List = reportsByWarehouseRow17ListArray.toString();
		String expRow17List = "[170, Total, 1,273.0000, 425.0000, 434.0000, 1.0000, 30.3030, ]";
		
		System.out.println("===========Stock Statement Report for Bt Item SCE Location====================");
		
		System.out.println(actRow16List);
		System.out.println(expRow16List);
		
		System.out.println(actRow17List);
		System.out.println(expRow17List);
		
		click(stockLedgerFilterBtn);
		Thread.sleep(1000);
		click(stockLedgerFilterField);
		Thread.sleep(1500);
		click(stockLedgerFilterRefreshBtn);
		Thread.sleep(1000);
		click(stockLedgerFilterOkBtn);
		
		Thread.sleep(2000);

		if (actRow14List1.equalsIgnoreCase(expRow14List1) && actRow13List1.equalsIgnoreCase(expRow13List1) && actRow14List.equalsIgnoreCase(expRow14List)
				&& actRow17List.equalsIgnoreCase(expRow17List) && actRow16List.equalsIgnoreCase(expRow16List)) {
			click(report_CloseBtn);
			excelReader.setCellData(xlfile, xlSheetName, 558, 8, resPass);
			return true;
		} else {
			click(report_CloseBtn);
			excelReader.setCellData(xlfile, xlSheetName, 558, 8, resFail);
			return false;
		}

	}

	public boolean checkStockStatementReportForFifoItem()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		
		excelReader =new ExcelReader(POJOUtility.getExcelPath());

		Thread.sleep(3000);

		if (fifoItemCheckBox.isSelected() == false) {
			click(fifoItemCheckBox);
		}

		if (brBatchItemCheckBox.isSelected() == true) {
			click(brBatchItemCheckBox);
		}

		if (rmaItemCheckBox.isSelected() == true) {
			click(rmaItemCheckBox);

		}

		dropDown(sl_DateOptionDropdown, excelReader.getCellData(xlSheetName, 560, 5));
		Thread.sleep(2000);
		click(sl_OkBtn);
		Thread.sleep(2000);
		click(stockLedgerLastBtn);
		Thread.sleep(15000);

		int reportsByWarehouseRow14ListCount1 = reportsRow14List.size();
		ArrayList<String> reportsByWarehouseRow14ListArray1 = new ArrayList<String>();
		for (int i = 0; i < reportsByWarehouseRow14ListCount1; i++) {
			String data = reportsRow14List.get(i).getText();
			reportsByWarehouseRow14ListArray1.add(data);
		}
		String actRow14List1 = reportsByWarehouseRow14ListArray1.toString();
		String expRow14List1 = excelReader.getCellData(xlSheetName, 560, 6);
		excelReader.setCellData(xlfile, xlSheetName, 560, 7, actRow14List1);

		System.out.println(actRow14List1);
		System.out.println(expRow14List1);
		
		click(stockLedgerFilterBtn);
		
		Thread.sleep(2000);
		click(stockLedgerFilterField);
		Thread.sleep(1500);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(stockLedgerFilterText));

		boolean actFilterpopupIsDisplaying = stockLedgerFilterText.isDisplayed();
		boolean expFilterpopupIsDisplaying = true;

		System.out.println("Filter Popup isDisplaying  : " + actFilterpopupIsDisplaying + "  Value Expected  "
				+ expFilterpopupIsDisplaying);
		
		
		click(stockStatementFilterWarehouseField);
		enterText(stockStatementFilterWarehouseField, "HYD");
		Thread.sleep(2000);
		click(stockLedgerFilterOkBtn);
		Thread.sleep(2000);
		click(stockLedgerLastBtn);
		Thread.sleep(20000);
		
		
		int reportsByWarehouseRow13ListCount1 = reportsRow13List.size();
		ArrayList<String> reportsByWarehouseRow13ListArray1 = new ArrayList<String>();
		for(int i=0;i<reportsByWarehouseRow13ListCount1;i++)
		{
			String data = reportsRow13List.get(i).getText();
			reportsByWarehouseRow13ListArray1.add(data);
		}
		String actRow13List1 = reportsByWarehouseRow13ListArray1.toString();
		String expRow13List1 = "[166, 07/09/2021, 1.0000, 1.0000, , 2.0000, 200.0000, FIFO-BIN]";
		
		int reportsByWarehouseRow14ListCount = reportsRow14List.size();
		ArrayList<String> reportsByWarehouseRow14ListArray = new ArrayList<String>();
		for(int i=0;i<reportsByWarehouseRow14ListCount;i++)
		{
			String data = reportsRow14List.get(i).getText();
			reportsByWarehouseRow14ListArray.add(data);
		}
		String actRow14List = reportsByWarehouseRow14ListArray.toString();
		String expRow14List = "[167, Total, 870.0000, 425.0000, 433.0000, 2.0000, 200.0000, ]";
		
		System.out.println("===========Stock Statement Report for FIFO Item HYD Location====================");
		
		System.out.println(actRow13List1);
		System.out.println(expRow13List1);
		
		System.out.println(actRow14List);
		System.out.println(expRow14List);
		
		
		click(report_CloseBtn);
		Thread.sleep(1000);
		click(stockLedgerHomePageFilterBtn);
		Thread.sleep(2000);
		click(stockStatementWarehouseField2);
		Thread.sleep(1000);
		click(stockStatementFilterWarehouseField3);
		Thread.sleep(1500);
		stockStatementFilterWarehouseField4.sendKeys(Keys.CONTROL, "a");
		Thread.sleep(1000);
		stockStatementFilterWarehouseField4.sendKeys(Keys.BACK_SPACE);
		Thread.sleep(1000);
		enterText(stockStatementFilterWarehouseField4, "SEC");
		Thread.sleep(1000);
		click(stockLedgerFilterOkBtn);
		Thread.sleep(1000);
		click(sl_OkBtn);
		
		Thread.sleep(2000);
		click(stockLedgerLastBtn);
		Thread.sleep(20000);
		
		int reportsByWarehouseRow16ListCount = reportsRow16List.size();
		ArrayList<String> reportsByWarehouseRow16ListArray = new ArrayList<String>();
		for(int i=0;i<reportsByWarehouseRow16ListCount;i++)
		{
			String data = reportsRow16List.get(i).getText();
			reportsByWarehouseRow16ListArray.add(data);
		}
		String actRow16List = reportsByWarehouseRow16ListArray.toString();
		String expRow16List = "[169, 07/09/2021, , 1.0000, , 1.0000, 200.0000, FIFO-BIN]";
		
		int reportsByWarehouseRow17ListCount = reportsRow17List.size();
		ArrayList<String> reportsByWarehouseRow17ListArray = new ArrayList<String>();
		for(int i=0;i<reportsByWarehouseRow17ListCount;i++)
		{
			String data = reportsRow17List.get(i).getText();
			reportsByWarehouseRow17ListArray.add(data);
		}
		String actRow17List = reportsByWarehouseRow17ListArray.toString();
		String expRow17List = "[170, Total, 1,275.0000, 425.0000, 434.0000, 1.0000, 200.0000, ]";
		
		System.out.println("===========Stock Statement Report for FIFO Item SCE Location====================");
		
		System.out.println(actRow17List);
		System.out.println(expRow17List);
		
		System.out.println(actRow16List);
		System.out.println(expRow16List);
		
		click(stockLedgerFilterBtn);
		Thread.sleep(1000);
		click(stockLedgerFilterField);
		Thread.sleep(1500);
		click(stockLedgerFilterRefreshBtn);
		Thread.sleep(1000);
		click(stockLedgerFilterOkBtn);
		
		Thread.sleep(2000);

		if (actRow14List1.equalsIgnoreCase(expRow14List1) && actRow13List1.equalsIgnoreCase(expRow13List1) && actRow14List.equalsIgnoreCase(expRow14List)
				&& actRow17List.equalsIgnoreCase(expRow17List) && actRow16List.equalsIgnoreCase(expRow16List)) {
			click(report_CloseBtn);
			excelReader.setCellData(xlfile, xlSheetName, 560, 8, resPass);
			return true;
		} else {
			click(report_CloseBtn);
			excelReader.setCellData(xlfile, xlSheetName, 560, 8, resFail);
			return false;
		}

	}

	public boolean checkStockStatementReportForRmaItem()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		
		excelReader =new ExcelReader(POJOUtility.getExcelPath());

		Thread.sleep(3000);

		if (fifoItemCheckBox.isSelected() == true) {
			click(fifoItemCheckBox);

		}

		if (brBatchItemCheckBox.isSelected() == true) {
			click(brBatchItemCheckBox);
		}

		if (rmaItemCheckBox.isSelected() == false) {
			click(rmaItemCheckBox);

		}

		dropDown(sl_DateOptionDropdown, excelReader.getCellData(xlSheetName, 561, 5));
		Thread.sleep(2000);
		click(sl_OkBtn);
		Thread.sleep(2000);
		click(stockLedgerLastBtn);
		Thread.sleep(15000);

		int reportsByWarehouseRow15ListCount1 = reportsRow15List.size();
		ArrayList<String> reportsByWarehouseRow15ListArray1 = new ArrayList<String>();
		for (int i = 0; i < reportsByWarehouseRow15ListCount1; i++) {
			String data = reportsRow15List.get(i).getText();
			reportsByWarehouseRow15ListArray1.add(data);
		}
		String actRow15List1 = reportsByWarehouseRow15ListArray1.toString();
		String expRow15List1 = excelReader.getCellData(xlSheetName, 561, 6);
		excelReader.setCellData(xlfile, xlSheetName, 561, 7, actRow15List1);

		System.out.println(actRow15List1);
		System.out.println(expRow15List1);
		
		click(stockLedgerFilterBtn);
		
		Thread.sleep(2000);
		click(stockLedgerFilterField);
		Thread.sleep(1500);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(stockLedgerFilterText));

		boolean actFilterpopupIsDisplaying = stockLedgerFilterText.isDisplayed();
		boolean expFilterpopupIsDisplaying = true;

		System.out.println("Filter Popup isDisplaying  : " + actFilterpopupIsDisplaying + "  Value Expected  "
				+ expFilterpopupIsDisplaying);
		
		
		click(stockStatementFilterWarehouseField);
		enterText(stockStatementFilterWarehouseField, "HYD");
		Thread.sleep(2000);
		click(stockLedgerFilterOkBtn);
		Thread.sleep(2000);
		click(stockLedgerLastBtn);
		Thread.sleep(20000);
		
		
		
		
		int reportsByWarehouseRow14ListCount1 = reportsRow14List.size();
		ArrayList<String> reportsByWarehouseRow14ListArray1 = new ArrayList<String>();
		for(int i=0;i<reportsByWarehouseRow14ListCount1;i++)
		{
			String data = reportsRow14List.get(i).getText();
			reportsByWarehouseRow14ListArray1.add(data);
		}
		String actRow14List1 = reportsByWarehouseRow14ListArray1.toString();
		String expRow14List1 = "[167, 07/09/2021, 1.0000, 1.0000, , 2.0000, 1,375.8648, WA-RMA]";
		
		int reportsByWarehouseRow15ListCount = reportsRow15List.size();
		ArrayList<String> reportsByWarehouseRow15ListArray = new ArrayList<String>();
		for(int i=0;i<reportsByWarehouseRow15ListCount;i++)
		{
			String data = reportsRow15List.get(i).getText();
			reportsByWarehouseRow15ListArray.add(data);
		}
		String actRow15List = reportsByWarehouseRow15ListArray.toString();
		String expRow15List = "[168, Total, 873.0000, 426.0000, 434.0000, 2.0000, 1,375.8648, ]";
		
		
		
		System.out.println("===========Stock Statement Report for RMA Item HYD Location====================");
		
		
		
		System.out.println(actRow14List1);
		System.out.println(expRow14List1);
		
		System.out.println(actRow15List);
		System.out.println(expRow15List);
		
		
		click(report_CloseBtn);
		Thread.sleep(1000);
		click(stockLedgerHomePageFilterBtn);
		Thread.sleep(2000);
		click(stockStatementWarehouseField2);
		Thread.sleep(1000);
		click(stockStatementFilterWarehouseField3);
		Thread.sleep(1500);
		stockStatementFilterWarehouseField4.sendKeys(Keys.CONTROL, "a");
		Thread.sleep(1000);
		stockStatementFilterWarehouseField4.sendKeys(Keys.BACK_SPACE);
		Thread.sleep(1000);
		enterText(stockStatementFilterWarehouseField4, "SEC");
		Thread.sleep(1000);
		click(stockLedgerFilterOkBtn);
		Thread.sleep(1000);
		click(sl_OkBtn);
		
		Thread.sleep(2000);
		click(stockLedgerLastBtn);
		Thread.sleep(20000);
		
		int reportsByWarehouseRow16ListCount = reportsRow16List.size();
		ArrayList<String> reportsByWarehouseRow16ListArray = new ArrayList<String>();
		for(int i=0;i<reportsByWarehouseRow16ListCount;i++)
		{
			String data = reportsRow16List.get(i).getText();
			reportsByWarehouseRow16ListArray.add(data);
		}
		String actRow16List = reportsByWarehouseRow16ListArray.toString();
		String expRow16List = "[169, 07/09/2021, , 1.0000, , 1.0000, 1,414.2531, WA-RMA]";
		
		int reportsByWarehouseRow17ListCount = reportsRow17List.size();
		ArrayList<String> reportsByWarehouseRow17ListArray = new ArrayList<String>();
		for(int i=0;i<reportsByWarehouseRow17ListCount;i++)
		{
			String data = reportsRow17List.get(i).getText();
			reportsByWarehouseRow17ListArray.add(data);
		}
		String actRow17List = reportsByWarehouseRow17ListArray.toString();
		String expRow17List = "[170, Total, 1,275.0000, 425.0000, 434.0000, 1.0000, 1,414.2531, ]";
		
		System.out.println("===========Stock Statement Report for RMA Item SCE Location====================");
		
		System.out.println(actRow16List);
		System.out.println(expRow16List);
		
		System.out.println(actRow17List);
		System.out.println(expRow17List);
		
		click(stockLedgerFilterBtn);
		Thread.sleep(1000);
		click(stockLedgerFilterField);
		Thread.sleep(1500);
		click(stockLedgerFilterRefreshBtn);
		Thread.sleep(1000);
		click(stockLedgerFilterOkBtn);
		
		Thread.sleep(2000);

		if (actRow15List1.equalsIgnoreCase(expRow15List1) && actRow14List1.equalsIgnoreCase(expRow14List1) && actRow15List.equalsIgnoreCase(expRow15List)
				&& actRow17List.equalsIgnoreCase(expRow17List) && actRow16List.equalsIgnoreCase(expRow16List)) {
			click(report_CloseBtn);
			Thread.sleep(2000);
			click(sl_CloseBtn);
			excelReader.setCellData(xlfile, xlSheetName, 561, 8, resPass);
			return true;
		} else {
			click(report_CloseBtn);
			Thread.sleep(2000);
			click(sl_CloseBtn);
			excelReader.setCellData(xlfile, xlSheetName, 561, 8, resFail);
			return false;
		}

	}
	
	
public void FilterRefine(String str1, String str2) throws InterruptedException{
		
		click(stockLedgerFilterBtn);
		Thread.sleep(1500);
		click(stockLedgerFilterRefineBtn);
		//Thread.sleep(18000);
		
		new WebDriverWait(getDriver(), 50).until(ExpectedConditions.elementToBeClickable(filterRefineLookInDropdownBtn));
		dropDown(str1, filterRefineLookInDropdownBtn);
		
		Thread.sleep(2000);
		
		//dropDown(str2, filterRefineLookIn2DropdownBtn);
		
		enterText(filterRefineFindWhatTxt, str2);
		
		
		Thread.sleep(2000);
		
		click(filterRefineOkBtn);
		
		
		
	}
	
	
	public boolean checkStockLedgerReportAfterSavingSalesReturnsVocherWithRefineFilterFirstPage()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException 
	
	{
		
		reLogin("su", "su");
		
		click(inventoryMenu);
		click(InventoryReportsMenu);
		click(InventoryStockLedgerMenu);
		Thread.sleep(3000);

	
		click(selectAllItemsCheckBox);
		
		dropDown(sl_DateOptionDropdown, "1");		
		Thread.sleep(2000);
		click(sl_OkBtn);
		Thread.sleep(2000);
		
		FilterRefine("Voucher", "PurVou:1");
		
		Thread.sleep(3000);
		
		
		int reportsByWarehouseRow1ListCount = reportsRow1List.size();
		ArrayList<String> reportsByWarehouseRow1ListArray = new ArrayList<String>();
		for(int i=0;i<reportsByWarehouseRow1ListCount;i++)
		{
			String data = reportsRow1List.get(i).getText();
			reportsByWarehouseRow1ListArray.add(data);
		}
		String actRow1List = reportsByWarehouseRow1ListArray.toString();
		String expRow1List = "[1, 01/10/2020, PurVou:1, HYD, 10.0000, 20.2020, , , 30.0000, 202.0200, , 404.1410, 13.4714, 01102020PH, , ]";
		
		

		int reportsByWarehouseRow2ListCount = reportsRow2List.size();
		ArrayList<String> reportsByWarehouseRow2ListArray = new ArrayList<String>();
		for(int i=0;i<reportsByWarehouseRow2ListCount;i++)
		{
			String data = reportsRow2List.get(i).getText();
			reportsByWarehouseRow2ListArray.add(data);
		}
		String actRow2List = reportsByWarehouseRow2ListArray.toString();
		String expRow2List = "[2, 01/10/2020, PurVou:1, SEC, 10.0000, 30.3030, , , 40.0000, 303.0300, , 707.1710, 17.6793, 01102020PS, , ]";
		
		
		
		int reportsRow3ListCount = reportsRow3List.size();
		ArrayList<String> reportsRow3ListArray = new ArrayList<String>();
		for(int i=0;i<reportsRow3ListCount;i++)
		{
			String data = reportsRow3List.get(i).getText();
			reportsRow3ListArray.add(data);
		}
		String actRow3List = reportsRow3ListArray.toString();
		String expRow3List = "[3, 01/10/2020, PurVou:1, TEST, 10.0000, 40.4040, , , 50.0000, 404.0400, , 1,111.2110, 22.2242, 01102020PT, , ]";
		
		
		
		/*int reportsRow4ListCount = reportsRow4List.size();
		ArrayList<String> reportsRow4ListArray = new ArrayList<String>();
		for(int i=0;i<reportsRow4ListCount;i++)
		{
			String data = reportsRow4List.get(i).getText();
			reportsRow4ListArray.add(data);
		}
		String actRow4List = reportsRow4ListArray.toString();
		String expRow4List = "[4, 01/10/2020, PurVou:1, TEST, 10.0000, 40.4040, , , 50.0000, 404.0400, , 1,111.2110, 22.2242, 01102020PT, , ]";
		*/
		
		
		System.out.println("*********************************checkStockLedgerReport Using Refine Filter 1st Page Report*****************************************");
		
		System.out.println(actRow1List);
		System.out.println(expRow1List);
		
		System.out.println(actRow2List);
		System.out.println(expRow2List);
		
		System.out.println(actRow3List);
		System.out.println(expRow3List);
		
		/*System.out.println(actRow4List);
		System.out.println(expRow4List);
		*/
		
		if(actRow1List.equalsIgnoreCase(expRow1List) && actRow2List.equalsIgnoreCase(expRow2List) && actRow3List.equalsIgnoreCase(expRow3List)
				/*&& actRow4List.equalsIgnoreCase(expRow4List)*/)
		{
			click(report_CloseBtn);				
			Thread.sleep(2000);
			click(sl_CloseBtn);				
			return true;
		}
		else
		{
			click(report_CloseBtn);				
			Thread.sleep(2000);
			click(sl_CloseBtn);
			return false;
		}
	}
	
	
	
	public boolean checkStockLedgerReportAfterSavingSalesReturnsVocherWithRefineFilterLastPage()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException 
	
	{
		click(inventoryMenu);
		click(InventoryReportsMenu);
		click(InventoryStockLedgerMenu);
		Thread.sleep(3000);

	
		click(selectAllItemsCheckBox);
		
		dropDown(sl_DateOptionDropdown, "1");		
		Thread.sleep(2000);
		click(sl_OkBtn);
		Thread.sleep(2000);
		click(stockLedgerLastBtn);
		Thread.sleep(8000);
		
		
		FilterRefine("Voucher", "PurVou:35");
		
		Thread.sleep(3000);
		
		
		int reportsByWarehouseRow1ListCount = reportsRow1List.size();
		ArrayList<String> reportsByWarehouseRow1ListArray = new ArrayList<String>();
		for(int i=0;i<reportsByWarehouseRow1ListCount;i++)
		{
			String data = reportsRow1List.get(i).getText();
			reportsByWarehouseRow1ListArray.add(data);
		}
		String actRow1List = reportsByWarehouseRow1ListArray.toString();
		String expRow1List = "[1922, 01/09/2021, PurVou:35, HYD, 10.0000, 1,000.0000, , , 15.0000, 10,000.0000, , 10,040.3131, 669.3542, 01092021PH, , ]";
		
		

		int reportsByWarehouseRow2ListCount = reportsRow2List.size();
		ArrayList<String> reportsByWarehouseRow2ListArray = new ArrayList<String>();
		for(int i=0;i<reportsByWarehouseRow2ListCount;i++)
		{
			String data = reportsRow2List.get(i).getText();
			reportsByWarehouseRow2ListArray.add(data);
		}
		String actRow2List = reportsByWarehouseRow2ListArray.toString();
		String expRow2List = "[1923, 01/09/2021, PurVou:35, SEC, 10.0000, 2,000.0000, , , 25.0000, 20,000.0000, , 30,040.3131, 1,201.6125, 01092021PH, , ]";
		
		
		
		int reportsRow3ListCount = reportsRow3List.size();
		ArrayList<String> reportsRow3ListArray = new ArrayList<String>();
		for(int i=0;i<reportsRow3ListCount;i++)
		{
			String data = reportsRow3List.get(i).getText();
			reportsRow3ListArray.add(data);
		}
		String actRow3List = reportsRow3ListArray.toString();
		String expRow3List = "[1924, 01/09/2021, PurVou:35, HYD, 10.0000, 1,000.0000, , , 15.0000, 10,000.0000, , 10,041.0807, 669.4054, , BIN1-10.00, ]";
		
		
		
		int reportsRow4ListCount = reportsRow4List.size();
		ArrayList<String> reportsRow4ListArray = new ArrayList<String>();
		for(int i=0;i<reportsRow4ListCount;i++)
		{
			String data = reportsRow4List.get(i).getText();
			reportsRow4ListArray.add(data);
		}
		String actRow4List = reportsRow4ListArray.toString();
		String expRow4List = "[1925, 01/09/2021, PurVou:35, SEC, 10.0000, 2,000.0000, , , 25.0000, 20,000.0000, , 30,041.0807, 1,201.6432, , BIN2-10.00, ]";
		
		int reportsRow5ListCount = reportsRow5List.size();
		ArrayList<String> reportsRow5ListArray = new ArrayList<String>();
		for(int i=0;i<reportsRow5ListCount;i++)
		{
			String data = reportsRow5List.get(i).getText();
			reportsRow5ListArray.add(data);
		}
		String actRow5List = reportsRow5ListArray.toString();
		String expRow5List = "[1926, 01/09/2021, PurVou:35, HYD, 10.0000, 1,000.0000, , , 15.0000, 10,000.0000, , 10,048.0097, 669.8673, , , 01092021PS, 01092021PS1, 01092021PS2, 01092021PS3, 01092021PS4, 01092021PS5, 01092021PS6, 01092021PS7, 01092021PS8, 01092021PS9]";
		
		
		int report6thRowListCount = reportsRow6List.size();
		ArrayList<String> report6thRowListArray = new ArrayList<String>();
		for(int i=0;i<report6thRowListCount;i++)
		{
			String data = reportsRow6List.get(i).getText();
			report6thRowListArray.add(data);
		}
		String actRow6List = report6thRowListArray.toString();
		String expRow6List = "[1927, 01/09/2021, PurVou:35, SEC, 10.0000, 2,000.0000, , , 25.0000, 20,000.0000, , 30,048.0097, 1,201.9204, , , 01092021PH10, 01092021PH11, 01092021PH12, 01092021PH13, 01092021PH14, 01092021PH15, 01092021PH16, 01092021PH17, 01092021PH18, 01092021PH9]";
		
		
		/*int report7thRowListCount = reportsRow7List.size();
		ArrayList<String> report7thRowListArray = new ArrayList<String>();
		for(int i=0;i<report7thRowListCount;i++)
		{
			String data = reportsRow7List.get(i).getText();
			report7thRowListArray.add(data);
		}
		String actRow7List = report7thRowListArray.toString();
		String expRow7List = "[1926, , Opening Balance, , 20.0000, , , , 20.0000, , , 202.1210, 10.1061, , , ]";
	
		
	
		
		int report8thRowListCount = reportsRow8List.size();
		ArrayList<String> report8thRowListArray = new ArrayList<String>();
		for(int i=0;i<report8thRowListCount;i++)
		{
			String data = reportsRow8List.get(i).getText();
			report8thRowListArray.add(data);
		}
		String actRow8List = report8thRowListArray.toString();
		String expRow8List = "[1927, 01/09/2021, PurVou:35, HYD, 10.0000, 1,000.0000, , , 15.0000, 10,000.0000, , 10,048.0097, 669.8673, , , 01092021PS, 01092021PS1, 01092021PS2, 01092021PS3, 01092021PS4, 01092021PS5, 01092021PS6, 01092021PS7, 01092021PS8, 01092021PS9]";
		
		
		
		int report9thRowListCount = reportsRow9List.size();
		ArrayList<String> report9thRowListArray = new ArrayList<String>();
		for(int i=0;i<report9thRowListCount;i++)
		{
			String data = reportsRow9List.get(i).getText();
			report9thRowListArray.add(data);
		}
		String actRow9List = report9thRowListArray.toString();
		String expRow9List = "[1928, 01/09/2021, PurVou:35, SEC, 10.0000, 2,000.0000, , , 25.0000, 20,000.0000, , 30,048.0097, 1,201.9204, , , 01092021PH10, 01092021PH11, 01092021PH12, 01092021PH13, 01092021PH14, 01092021PH15, 01092021PH16, 01092021PH17, 01092021PH18, 01092021PH9]";
		*/
		
		System.out.println("*********************************checkStockLedgerReport Using Refine Filter 2nd Page Report*****************************************");
		
		
		System.out.println(actRow1List);
		System.out.println(expRow1List);
		
		System.out.println(actRow2List);
		System.out.println(expRow2List);
		
		System.out.println(actRow3List);
		System.out.println(expRow3List);
		
		System.out.println(actRow4List);
		System.out.println(expRow4List);
		
		System.out.println(actRow5List);
		System.out.println(expRow5List);
		
		System.out.println(actRow6List);
		System.out.println(expRow6List);
		
		/*System.out.println(actRow7List);
		System.out.println(expRow7List);
		
		System.out.println(actRow8List);
		System.out.println(expRow8List);
		
		System.out.println(actRow9List);
		System.out.println(expRow9List);*/
		
		
		
		if(actRow1List.equalsIgnoreCase(expRow1List) && actRow2List.equalsIgnoreCase(expRow2List) && actRow3List.equalsIgnoreCase(expRow3List)
				&& actRow4List.equalsIgnoreCase(expRow4List) && actRow5List.equalsIgnoreCase(expRow5List) && actRow6List.equalsIgnoreCase(expRow6List)
				/*&& actRow7List.equalsIgnoreCase(expRow7List) && actRow8List.equalsIgnoreCase(expRow8List)
				&& actRow9List.equalsIgnoreCase(expRow9List)*/)
		{
			click(report_CloseBtn);				
			Thread.sleep(2000);
			click(sl_CloseBtn);				
			return true;
		}
		else
		{
			click(report_CloseBtn);				
			Thread.sleep(2000);
			click(sl_CloseBtn);
			return false;
		}
	}
	
	
	public boolean checkStockMovementReportWithRefineFilter()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		
		
		
		click(inventoryMenu);
		click(InventoryReportsMenu);
		click(InventoryReportsStockMovement);
		click(sl_HeaderSelectChkBox);
		
		dropDown(sl_DateOptionDropdown, "1");		
		Thread.sleep(2000);
		click(sl_OkBtn);
		Thread.sleep(2000);
		
		
		FilterRefine("Item", "FIFO-BIN");
		
		int reportsByWarehouseRow1ListCount = reportsRow1List.size();
		ArrayList<String> reportsByWarehouseRow1ListArray = new ArrayList<String>();
		for(int i=0;i<reportsByWarehouseRow1ListCount;i++)
		{
			String data = reportsRow1List.get(i).getText();
			reportsByWarehouseRow1ListArray.add(data);
		}
		String actRow1List = reportsByWarehouseRow1ListArray.toString();
		String expRow1List = "[1, FIFO-BIN, 20.00, 202.1210, 1,076.00, 51,459.1768, 1,091.00, -49,061.2978, 5.00, 2,600.0000, 520.0000]";
		
		System.out.println("*********************************checkStockMovementReport With Filter Refine*****************************************");
		
		System.out.println(actRow1List);
		System.out.println(expRow1List);
		
		
		if(actRow1List.equalsIgnoreCase(expRow1List))
		{
			click(report_CloseBtn);				
			Thread.sleep(2000);
			click(sl_CloseBtn);
			return true;
		}
		else
		{
			click(report_CloseBtn);				
			Thread.sleep(2000);
			click(sl_CloseBtn);
			return false;
		}
	}
	
	
	public boolean checkStockValuationReportByProductWithRefineFilter()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		
		click(inventoryMenu);
		click(InventoryReportsMenu);
		click(InventoryReportsStockValuation);
		click(sl_HeaderSelectChkBox);
		
		dropDown(sl_DateOptionDropdown, "1");		
		Thread.sleep(2000);
		click(sl_OkBtn);
		Thread.sleep(2000);
		
		
		
		FilterRefine("Item", "WA-RMA");
		
		
		
		int reportsByWarehouseRow1ListCount = reportsRow1List.size();
		ArrayList<String> reportsByWarehouseRow1ListArray = new ArrayList<String>();
		for(int i=0;i<reportsByWarehouseRow1ListCount;i++)
		{
			String data = reportsRow1List.get(i).getText();
			reportsByWarehouseRow1ListArray.add(data);
		}
		String actRow1List = reportsByWarehouseRow1ListArray.toString();
		String expRow1List = "[1, WA-RMA, WA-RMA, WA-RMA, 5.0000, 5,507.9347, 1,101.5869, , ]";
		
		
		
		System.out.println("*********************************checkStockValuationReport With Filter refine*****************************************");
		
		System.out.println(actRow1List);
		System.out.println(expRow1List);
		
		
		if(actRow1List.equalsIgnoreCase(expRow1List))
		{
			click(report_CloseBtn);				
			Thread.sleep(2000);
			click(sl_CloseBtn);
			return true;
		}
		else
		{
			click(report_CloseBtn);				
			Thread.sleep(2000);
			click(sl_CloseBtn);
			return false;
		}
	}
	
	
	public boolean checkStockAgeingAnalysisReportByDefaultWithRefineFilter()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		
		click(inventoryMenu);
		click(InventoryReportsMenu);
		//click(InventoryReportsStockAgeingAnalysisMenu);
		ClickUsingJs(InventoryReportsStockAgeingAnalysisMenu);
		//click(InventoryReportsAgeingAnalysis);
		ClickUsingJs(InventoryReportsAgeingAnalysis);
		click(sl_HeaderSelectChkBox);
		
		dropDown(sl_DateOptionDropdown, "1");		
		Thread.sleep(2000);
		click(sl_OkBtn);
		Thread.sleep(2000);
		
		FilterRefine("Particulars", "BR-BATCH");
		
		Thread.sleep(3000);
		
		
		int reportsByWarehouseRow1ListCount = reportsRow1List.size();
		ArrayList<String> reportsByWarehouseRow1ListArray = new ArrayList<String>();
		for(int i=0;i<4;i++)
		{
			String data = reportsRow1List.get(i).getText();
			reportsByWarehouseRow1ListArray.add(data);
		}
		String actRow1List = reportsByWarehouseRow1ListArray.toString();
		String expRow1List = "[1, BR-BATCH, 5.00, 4,150.5050]";
		
	
		
		System.out.println("*********************************checkStockAgeing AnalysisReport With Filter Refine*****************************************");
		
		System.out.println(actRow1List);
		System.out.println(expRow1List);
		
		
		if(actRow1List.equalsIgnoreCase(expRow1List))
		{
			click(report_CloseBtn);				
			Thread.sleep(2000);
			click(sl_CloseBtn);
			return true;
		}
		else
		{
			click(report_CloseBtn);				
			Thread.sleep(2000);
			click(sl_CloseBtn);
			return false;
		}
	}
	
	
	public boolean checkStockBalanceByWarehouseReportWithRefineFilter()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		
		
		click(inventoryMenu);
		click(InventoryReportsMenu);
		Thread.sleep(2000);
		//moveToElement(InventoryReportsStockBalanceByWarehouseMenu);
		ClickUsingJs(InventoryReportsStockBalanceByWarehouseMenu);
		click(sl_HeaderSelectChkBox);
		
		dropDown(sl_DateOptionDropdown, "1");		
		Thread.sleep(2000);
		click(sl_OkBtn);
		Thread.sleep(2000);
		
		
		FilterRefine("Quantity", "2.0000000");
		
		
		
		int reportsByWarehouseRow1ListCount = reportsRow1List.size();
		ArrayList<String> reportsByWarehouseRow1ListArray = new ArrayList<String>();
		for(int i=0;i<reportsByWarehouseRow1ListCount;i++)
		{
			String data = reportsRow1List.get(i).getText();
			reportsByWarehouseRow1ListArray.add(data);
		}
		String actRow1List = reportsByWarehouseRow1ListArray.toString();
		String expRow1List = "[1, FIFO-BIN, 0, 0, 2.0000000, , 2.0000000, 100.00, 520.0000]";
		
		

		int reportsByWarehouseRow2ListCount = reportsRow2List.size();
		ArrayList<String> reportsByWarehouseRow2ListArray = new ArrayList<String>();
		for(int i=0;i<reportsByWarehouseRow2ListCount;i++)
		{
			String data = reportsRow2List.get(i).getText();
			reportsByWarehouseRow2ListArray.add(data);
		}
		String actRow2List = reportsByWarehouseRow2ListArray.toString();
		String expRow2List = "[2, BR-BATCH, 0, 0, 2.0000000, , 2.0000000, 60.10, 830.1010]";
		
		
		
		int reportsRow3ListCount = reportsRow3List.size();
		ArrayList<String> reportsRow3ListArray = new ArrayList<String>();
		for(int i=0;i<reportsRow3ListCount;i++)
		{
			String data = reportsRow3List.get(i).getText();
			reportsRow3ListArray.add(data);
		}
		String actRow3List = reportsRow3ListArray.toString();
		String expRow3List = "[3, WA-RMA, 0, 0, 2.0000000, , 2.0000000, 687.93, 1,101.5869]";
		
		
		
		int reportsRow4ListCount = reportsRow4List.size();
		ArrayList<String> reportsRow4ListArray = new ArrayList<String>();
		for(int i=0;i<reportsRow4ListCount;i++)
		{
			String data = reportsRow4List.get(i).getText();
			reportsRow4ListArray.add(data);
		}
		String actRow4List = reportsRow4ListArray.toString();
		String expRow4List = "[4, WA-RMA, 0, 0, 2.0000000, , 2.0000000, 687.93, 1,101.5869]";
		
	
		int reportsRow5ListCount = reportsRow5List.size();
		ArrayList<String> reportsRow5ListArray = new ArrayList<String>();
		for(int i=0;i<reportsRow5ListCount;i++)
		{
			String data = reportsRow5List.get(i).getText();
			reportsRow5ListArray.add(data);
		}
		String actRow5List = reportsRow5ListArray.toString();
		String expRow5List = "[5, FIFO-BIN, 0, 0, 2.0000000, , 2.0000000, 100.00, 520.0000]";
		
		
		int report6thRowListCount = reportsRow6List.size();
		ArrayList<String> report6thRowListArray = new ArrayList<String>();
		for(int i=0;i<report6thRowListCount;i++)
		{
			String data = reportsRow6List.get(i).getText();
			report6thRowListArray.add(data);
		}
		String actRow6List = report6thRowListArray.toString();
		String expRow6List = "[6, BR-BATCH, 0, 0, 2.0000000, , 2.0000000, 60.10, 830.1010]";
		
		System.out.println("*********************************checkStockBalanceByWarehouseReport*****************************************");
		
		
		System.out.println(actRow1List);
		System.out.println(expRow1List);
		
		System.out.println(actRow2List);
		System.out.println(expRow2List);
		
		System.out.println(actRow3List);
		System.out.println(expRow3List);
		
		System.out.println(actRow4List);
		System.out.println(expRow4List);
		
		System.out.println(actRow5List);
		System.out.println(expRow5List);
		
		System.out.println(actRow6List);
		System.out.println(expRow6List);
		
		if(actRow1List.equalsIgnoreCase(expRow1List) && actRow2List.equalsIgnoreCase(expRow2List) && actRow3List.equalsIgnoreCase(expRow3List)
				&& actRow4List.equalsIgnoreCase(expRow4List) && actRow5List.equalsIgnoreCase(expRow5List) &&
				actRow6List.equalsIgnoreCase(expRow6List))
		{
					
			return true;
		}
		else
		{
			click(report_CloseBtn);				
			Thread.sleep(2000);
			click(sl_CloseBtn);		
			return false;
		}
    
	
	
	}
	
	
	public boolean checkLedgerReportWithRefineFilterFirstPage()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException 
	
	{
		
		click(financialsMenu);
		click(financialReports);
		click(financialReportsLedger);
		Thread.sleep(3000);
			
		click(selectAllItemsCheckBox);
		
		dropDown(sl_DateOptionDropdown, "1");		
		Thread.sleep(2000);
		click(sl_OkBtn);
		Thread.sleep(2000);
	
		
		FilterRefine("Debit", "1,500.0000000");
		
		Thread.sleep(3000);
		
		
		int reportsByWarehouseRow1ListCount = reportsRow1List.size();
		ArrayList<String> reportsByWarehouseRow1ListArray = new ArrayList<String>();
		for(int i=0;i<reportsByWarehouseRow1ListCount;i++)
		{
			String data = reportsRow1List.get(i).getText();
			reportsByWarehouseRow1ListArray.add(data);
		}
		String actRow1List = reportsByWarehouseRow1ListArray.toString();
		String expRow1List = "[1, 02/09/2021, SalInv : 35, Sales - Computers, 1,500.0000000, , 86,808.7907000, , , , 1,500.0000000, , 86,808.7907000, Indian Rupees, BR-BATCH]";
		
		

		int reportsByWarehouseRow2ListCount = reportsRow2List.size();
		ArrayList<String> reportsByWarehouseRow2ListArray = new ArrayList<String>();
		for(int i=0;i<reportsByWarehouseRow2ListCount;i++)
		{
			String data = reportsRow2List.get(i).getText();
			reportsByWarehouseRow2ListArray.add(data);
		}
		String actRow2List = reportsByWarehouseRow2ListArray.toString();
		String expRow2List = "[2, 02/09/2021, SalInv : 35, Sales - Computers, 1,500.0000000, , 85,308.7907000, , , , 1,500.0000000, , 85,308.7907000, Indian Rupees, BR-BATCH]";
		
			
		System.out.println("*********************************check LedgerReport Using Refine Filter 1st Page Report*****************************************");
		
		System.out.println(actRow1List);
		System.out.println(expRow1List);
		
		System.out.println(actRow2List);
		System.out.println(expRow2List);		
		
		if(actRow1List.equalsIgnoreCase(expRow1List) && actRow2List.equalsIgnoreCase(expRow2List))
		{
			click(report_CloseBtn);				
			Thread.sleep(2000);
			click(sl_CloseBtn);				
			return true;
		}
		else
		{
			click(report_CloseBtn);				
			Thread.sleep(2000);
			click(sl_CloseBtn);
			return false;
		}
	}
	
	public boolean checkLedgerReportWithRefineFilterLastPage()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException 
	
	{
		
		click(financialsMenu);
		click(financialReports);
		click(financialReportsLedger);
		Thread.sleep(3000);
	
		click(selectAllItemsCheckBox);
		
		dropDown(sl_DateOptionDropdown, "1");		
		Thread.sleep(2000);
		click(sl_OkBtn);
		Thread.sleep(2000);
		click(stockLedgerLastBtn);
		Thread.sleep(4000);
	
		
		FilterRefine("Balance", "3,310.0000000");
		
		Thread.sleep(3000);
		
		
		int reportsByWarehouseRow1ListCount = reportsRow1List.size();
		ArrayList<String> reportsByWarehouseRow1ListArray = new ArrayList<String>();
		for(int i=0;i<reportsByWarehouseRow1ListCount;i++)
		{
			String data = reportsRow1List.get(i).getText();
			reportsByWarehouseRow1ListArray.add(data);
		}
		String actRow1List = reportsByWarehouseRow1ListArray.toString();
		String expRow1List = "[545, 07/11/2020, SalInv : 5, Sales - Computers, 825.0000000, , 3,310.0000000, , , , 825.0000000, , 3,310.0000000, Indian Rupees, BR-BATCH]";
		
			
		System.out.println("*********************************check LedgerReport Using Refine Filter Last Page Report*****************************************");
			
		System.out.println(actRow1List);
		System.out.println(expRow1List);
		
		
		if(actRow1List.equalsIgnoreCase(expRow1List))
		{
			click(report_CloseBtn);				
			Thread.sleep(2000);
			click(sl_CloseBtn);				
			return true;
		}
		else
		{
			click(report_CloseBtn);				
			Thread.sleep(2000);
			click(sl_CloseBtn);
			return false;
		}
	}
	
	
	public boolean checkPurchaseRegisterReportWithRefineFilter()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException 
	
	{

		click(financialsMenu);
		click(financialReports);
		click(financialPurchaseReportMenu);
		click(financialPurchaseRegister);
		Thread.sleep(3000);

		dropDown(sl_DateOptionDropdown, "1");
		Thread.sleep(2000);

		enterText(accountDropdown, "Cost of goods sold - Computers");
		Thread.sleep(2000);

		click(sl_OkBtn);
		Thread.sleep(2000);

	
		FilterRefine("Party", "Bank");

		Thread.sleep(3000);
		
		
		int reportsByWarehouseRow1ListCount = reportsRow1List.size();
		ArrayList<String> reportsByWarehouseRow1ListArray = new ArrayList<String>();
		for(int i=0;i<reportsByWarehouseRow1ListCount;i++)
		{
			String data = reportsRow1List.get(i).getText();
			reportsByWarehouseRow1ListArray.add(data);
		}
		String actRow1List = reportsByWarehouseRow1ListArray.toString();
		String expRow1List = "[1, 01/09/2021, PurVou : 35, Bank, 10,000.00, BR-BATCH, 10.00, 1,000.00]";
		
		int reportsByWarehouseRow2ListCount = reportsRow2List.size();
		ArrayList<String> reportsByWarehouseRow2ListArray = new ArrayList<String>();
		for(int i=0;i<reportsByWarehouseRow2ListCount;i++)
		{
			String data = reportsRow2List.get(i).getText();
			reportsByWarehouseRow2ListArray.add(data);
		}
		String actRow2List = reportsByWarehouseRow2ListArray.toString();
		String expRow2List = "[2, 01/09/2021, PurVou : 35, Bank, 20,000.00, BR-BATCH, 10.00, 2,000.00]";
		
		
		
		int reportsRow3ListCount = reportsRow3List.size();
		ArrayList<String> reportsRow3ListArray = new ArrayList<String>();
		for(int i=0;i<reportsRow3ListCount;i++)
		{
			String data = reportsRow3List.get(i).getText();
			reportsRow3ListArray.add(data);
		}
		String actRow3List = reportsRow3ListArray.toString();
		String expRow3List = "[3, 01/09/2021, PurVou : 35, Bank, 10,000.00, FIFO-BIN, 10.00, 1,000.00]";
		
		
		
		int reportsRow4ListCount = reportsRow4List.size();
		ArrayList<String> reportsRow4ListArray = new ArrayList<String>();
		for(int i=0;i<reportsRow4ListCount;i++)
		{
			String data = reportsRow4List.get(i).getText();
			reportsRow4ListArray.add(data);
		}
		String actRow4List = reportsRow4ListArray.toString();
		String expRow4List = "[4, 01/09/2021, PurVou : 35, Bank, 20,000.00, FIFO-BIN, 10.00, 2,000.00]";
		
		int reportsRow5ListCount = reportsRow5List.size();
		ArrayList<String> reportsRow5ListArray = new ArrayList<String>();
		for(int i=0;i<reportsRow5ListCount;i++)
		{
			String data = reportsRow5List.get(i).getText();
			reportsRow5ListArray.add(data);
		}
		String actRow5List = reportsRow5ListArray.toString();
		String expRow5List = "[5, 01/09/2021, PurVou : 35, Bank, 10,000.00, WA-RMA, 10.00, 1,000.00]";
		
		
		int report6thRowListCount = reportsRow6List.size();
		ArrayList<String> report6thRowListArray = new ArrayList<String>();
		for(int i=0;i<report6thRowListCount;i++)
		{
			String data = reportsRow6List.get(i).getText();
			report6thRowListArray.add(data);
		}
		String actRow6List = report6thRowListArray.toString();
		String expRow6List = "[6, 01/09/2021, PurVou : 35, Bank, 20,000.00, WA-RMA, 10.00, 2,000.00]";

		
		
		System.out.println("*********************************check Purchase Register Report Using Refine Filter Last Page Report*****************************************");
		
		
		
		System.out.println(actRow1List);
		System.out.println(expRow1List);
		
		System.out.println(actRow2List);
		System.out.println(expRow2List);
		
		System.out.println(actRow3List);
		System.out.println(expRow3List);
		
		System.out.println(actRow4List);
		System.out.println(expRow4List);
		
		System.out.println(actRow5List);
		System.out.println(expRow5List);
		
		System.out.println(actRow6List);
		System.out.println(expRow6List);
		
	
		
		if(actRow1List.equalsIgnoreCase(expRow1List) && actRow2List.equalsIgnoreCase(expRow2List) && actRow3List.equalsIgnoreCase(expRow3List)
				&& actRow4List.equalsIgnoreCase(expRow4List) && actRow5List.equalsIgnoreCase(expRow5List) && actRow6List.equalsIgnoreCase(expRow6List))
		{
			click(report_CloseBtn);				
			Thread.sleep(2000);
			click(sl_CloseBtn);				
			return true;
		}
		else
		{
			click(report_CloseBtn);				
			Thread.sleep(2000);
			click(sl_CloseBtn);
			return false;
		}
	}
	
	public boolean checkTrailBalanceReportWithRefineFilter()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException 
	
	{

		click(financialsMenu);
		click(financialFinalAccountsMenu);
		click(trialBalance);
		
		Thread.sleep(1500);

		dropDown(sl_DateOptionDropdown, "1");
		Thread.sleep(1000);

		click(sl_OkBtn);
		Thread.sleep(2000);

	
		FilterRefine("Alias", "Cash & bank");

		Thread.sleep(3000);
		
		
		int reportsByWarehouseRow1ListCount = reportsRow1List.size();
		ArrayList<String> reportsByWarehouseRow1ListArray = new ArrayList<String>();
		for(int i=0;i<reportsByWarehouseRow1ListCount;i++)
		{
			String data = reportsRow1List.get(i).getText();
			reportsByWarehouseRow1ListArray.add(data);
		}
		String actRow1List = reportsByWarehouseRow1ListArray.toString();
		String expRow1List = "[1, Cash & bank, 121, Cash & bank, , 82,082.97, , , , 82,082.97, 9,383.27, 91,466.24, , 82,082.97, , , , 82,082.97, 9,383.27, 91,466.24, , , , , , , , , 76.88, 76.88, ]";
		
		
		System.out.println("*********************************check Trial Report Using Refine Filter*****************************************");
		
		System.out.println(actRow1List);
		System.out.println(expRow1List);
		
		
		if(actRow1List.equalsIgnoreCase(expRow1List))
		{
			click(report_CloseBtn);				
			Thread.sleep(2000);
			click(sl_CloseBtn);				
			return true;
		}
		else
		{
			click(report_CloseBtn);				
			Thread.sleep(2000);
			click(sl_CloseBtn);
			return false;
		}
	}
	
	public boolean checkCustomerAgeingSummeryReportWithRefineFilter()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException 
	
	{

		click(financialsMenu);
		click(financialReceivableAndPayableMenu);
		click(customerSummeryMenu);
		click(customerAgeingSummery);
		Thread.sleep(1500);
		
		click(selectAllItemsCheckBox);
		
		dropDown(sl_DateOptionDropdown, "1");
		Thread.sleep(1000);

		click(sl_OkBtn);
		Thread.sleep(2000);
		
		FilterRefine("Account", "Rathod BN");

		Thread.sleep(3000);
		
		
		int reportsByWarehouseRow1ListCount = reportsRow1List.size();
		ArrayList<String> reportsByWarehouseRow1ListArray = new ArrayList<String>();
		for(int i=0;i<reportsByWarehouseRow1ListCount;i++)
		{
			String data = reportsRow1List.get(i).getText();
			
			if(data.isEmpty()==false)
			{
				reportsByWarehouseRow1ListArray.add(data);
			}
		}
		String actRow1List = reportsByWarehouseRow1ListArray.toString();
		String expRow1List = "[1, Rathod BN, 1,405.45, 1,405.45, 1,405.45, 1,405.45, 1,405.45, 1,405.45, 1,405.45, 1,405.45, 1,405.45, 1,405.45, 1,405.45, 1,405.45, RBN]";
		
		
		System.out.println("*********************************check Customer Ageing Summary Report Using Refine Filter*****************************************");
		
	
		System.out.println(actRow1List);
		System.out.println(expRow1List);
		
		
		if(actRow1List.equalsIgnoreCase(expRow1List))
		{
			click(report_CloseBtn);				
			Thread.sleep(2000);
			click(sl_CloseBtn);				
			return true;
		}
		else
		{
			click(report_CloseBtn);				
			Thread.sleep(2000);
			click(sl_CloseBtn);
			return false;
		}
	}
	
	public boolean checkVendorAgeingSummeryReportWithRefineFilter()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException 
	
	{

		click(financialsMenu);
		click(financialReceivableAndPayableMenu);
		click(vendorSummeryMenu);
		click(vendorAgeingSummery);
		Thread.sleep(1500);
		
		click(selectAllItemsCheckBox);
		
		dropDown(sl_DateOptionDropdown, "1");
		Thread.sleep(1000);

		click(sl_OkBtn);
		Thread.sleep(2000);
	
		FilterRefine("Balance Amount", "2,400.09");

		Thread.sleep(3000);
		
		
		int reportsByWarehouseRow1ListCount = reportsRow1List.size();
		ArrayList<String> reportsByWarehouseRow1ListArray = new ArrayList<String>();
		for(int i=0;i<reportsByWarehouseRow1ListCount;i++)
		{
			String data = reportsRow1List.get(i).getText();			
			
			if(data.isEmpty()==false)
			{
				reportsByWarehouseRow1ListArray.add(data);
			}
		}
		String actRow1List = reportsByWarehouseRow1ListArray.toString();
		String expRow1List = "[1, DHANA Bnk, 2,400.09, 2,400.09, 2,400.09, 2,400.09, 2,400.09, 2,400.09, 2,400.09, 2,400.09, 2,400.09, 2,400.09, 2,400.09, 2,400.09, DHANA Bnk]";
		
		
		System.out.println("*********************************check Customer Ageing Summary Report Using Refine Filter*****************************************");
		
	
		System.out.println(actRow1List);
		System.out.println(expRow1List);
		
		
		if(actRow1List.equalsIgnoreCase(expRow1List))
		{
			click(report_CloseBtn);				
			Thread.sleep(2000);
			click(sl_CloseBtn);				
			return true;
		}
		
		{
			click(report_CloseBtn);				
			Thread.sleep(2000);
			click(sl_CloseBtn);
			return false;
		}
	}
	
	
	

	
	// Stock In Stock Out  Stock In Vouchers  

	public boolean checkSavingStockOutVoucher()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException 
	{
		
		excelReader =new ExcelReader(POJOUtility.getExcelPath());
		
		reLogin("su", "su");

		/*Thread.sleep(3000);
		click(userNameDisplayLogo);
		Thread.sleep(1000);
		click(logoutOption);
		Thread.sleep(3000);

		String unamelt = excelReader.getCellData(xlSheetName, 562, 5);

		String pawslt = excelReader.getCellData(xlSheetName, 563, 5);

		LoginPage.enterUserName(unamelt);
		LoginPage.enterPassword(pawslt);

		Select company = new Select(companyDropDownList);
		company.selectByValue(excelReader.getCellData(xlSheetName, 564, 5));
		Thread.sleep(2000);

		LoginPage.clickOnSignInBtn();

		Thread.sleep(3000);

		System.out.println("Signing");*/

		// Login User Name is Verify

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(userNameDisplay));
		String actUserInfo = userNameDisplay.getText();
		String expUserInfo = excelReader.getCellData(xlSheetName, 562, 6);
		excelReader.setCellData(xlfile, xlSheetName, 562, 7, actUserInfo);

		System.out.println("Login With Valid User and Check User Name Display Value Actual : ." + actUserInfo
				+ ". Value Expected : " + expUserInfo);

		// Login Company Name is Verify
		Thread.sleep(1000);
		click(companyLogo);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(companyName));
		String actGetLoginCompanyNameInformation = companyName.getText();
		String actGetLoginCompanyName = actGetLoginCompanyNameInformation.substring(0, 19);
		String expGetLoginCompanyName = excelReader.getCellData(xlSheetName, 563, 6);
		excelReader.setCellData(xlfile, xlSheetName, 563, 7, actGetLoginCompanyName);
		companyLogo.click();

		System.out.println("Company Name Display Value Actual                : ." + actGetLoginCompanyName
				+ ". Value Expected : " + expGetLoginCompanyName);

		click(inventoryMenu);
		click(inventoryTransactionsMenu);
		click(inventoryTransactionsStockMenu);
		click(inventoryTransactionsStockOut);
		click(newBtn);

		checkValidationMessage("Screen opened");

		enterText(stockAccounttxt, excelReader.getCellData(xlSheetName, 565, 5));
		Thread.sleep(2000);
		stockAccounttxt.sendKeys(Keys.TAB);
		Thread.sleep(2000);
		enterText(partyAccounttxt, excelReader.getCellData(xlSheetName, 566, 5));
		Thread.sleep(2000);
		partyAccounttxt.sendKeys(Keys.TAB);
		enterText(Warehouse1txt, excelReader.getCellData(xlSheetName, 567, 5));
		Thread.sleep(2000);
		Warehouse1txt.sendKeys(Keys.TAB);
		enterText(Warehouse2txt, excelReader.getCellData(xlSheetName, 568, 5));
		Thread.sleep(2000);
		Warehouse2txt.sendKeys(Keys.TAB);
		enterText(stockOutDepartmenttxt, excelReader.getCellData(xlSheetName, 569, 5));
		Thread.sleep(2000);
		stockOutDepartmenttxt.sendKeys(Keys.TAB);
		click(select1stRow_1stColumn);
		enterText(enterItemttxt, excelReader.getCellData(xlSheetName, 570, 5));
		Thread.sleep(2000);
		enterItemttxt.sendKeys(Keys.TAB);
		Thread.sleep(2000);
		enterText(enterQuantitytxt, excelReader.getCellData(xlSheetName, 571, 5));
		Thread.sleep(2000);
		enterQuantitytxt.sendKeys(Keys.TAB);
		Thread.sleep(1000); 
		if (validationConfirmationMessage.getText().isEmpty()==false) 
		{
			checkValidationMessage(excelReader.getCellData(xlSheetName, 572, 5));
		}
		Thread.sleep(1000);
		enterRatetxt.clear();
		Thread.sleep(1000);
		enterText(enterRatetxt, excelReader.getCellData(xlSheetName, 573, 5));
		Thread.sleep(2000);
		enterRatetxt.sendKeys(Keys.TAB);
		
		
		
		String docno=documentNumberTxt.getAttribute("value");
		click(voucherSaveBtn);

		Thread.sleep(2000);

		String actMessage = SavingInBackgroundTrial();

        HashSet<String> expMsg = new HashSet();

        expMsg.add(excelReader.getCellData(xlSheetName, 564, 6));
        expMsg.add(excelReader.getCellData(xlSheetName, 565, 6));
        //expMsg.add(excelReader.getCellData(xlSheetName, 566, 6));        
        //expMsg.add(excelReader.getCellData(xlSheetName, 567, 6));
        excelReader.setCellData(xlfile, xlSheetName, 564, 7, actMessage);
        
        String expMessage = expMsg.toString();
        

		System.out.println("Actual Message    : " + actMessage);
		System.out.println("Expected Message  : " + expMessage);
		
		/*boolean actSaving=SavingInBackground(docno);
		boolean expSaving=true;*/

		if (/*actSaving==expSaving*/actMessage.equalsIgnoreCase(expMessage)) {
			excelReader.setCellData(xlfile, xlSheetName, 562, 8, resPass);

			return true;
		} else {

			excelReader.setCellData(xlfile, xlSheetName, 562, 8, resFail);
			
			return false;
		}
	}

	public boolean checkSavingStockInVoucher()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		
		excelReader = new ExcelReader(POJOUtility.getExcelPath());
		
		
		click(inventoryMenu);
		click(inventoryTransactionsMenu);
		click(inventoryTransactionsStockMenu);
		click(inventoryTransactionsStockOut);
		Thread.sleep(1500);
		getAction().doubleClick(stockoutHomepageRpw1Checkbx).build().perform();

		
		checkValidationMessage("Voucher loaded successfully");

		Thread.sleep(1000);
		click(toggleBtn);
		Thread.sleep(1000);

		moveToElement(copyToClipBoardOption);
		Thread.sleep(1000);

		enterText(homepageManuSearchField, excelReader.getCellData(xlSheetName, 574, 5));
		Thread.sleep(1000);
		moveToElement(stockIn);
		// Thread.sleep(1000);
		click(newBtn);
		// Thread.sleep(1000);
		checkValidationMessage("Screen opened");
		// Thread.sleep(1000);
		click(toggleBtn);
		Thread.sleep(1000);
		click(pasteFromClipBoardOption);
		// Thread.sleep(2000);

		checkValidationMessage(excelReader.getCellData(xlSheetName, 575, 5));
		Thread.sleep(1000);
		click(Warehouse2txt);
		Thread.sleep(1000);
		Warehouse2txt.sendKeys(Keys.CONTROL,"a");
		Thread.sleep(1000);
		Warehouse2txt.sendKeys(Keys.BACK_SPACE);
		Thread.sleep(2000);
		enterText(Warehouse2txt, excelReader.getCellData(xlSheetName, 576, 5));
		Thread.sleep(1000);
		tab(Warehouse2txt);
		

		Thread.sleep(2000);
		
		String docno=documentNumberTxt.getAttribute("value");
		click(voucherSaveBtn);

		Thread.sleep(2000);

		String actMessage = SavingInBackgroundTrial();

        HashSet<String> expMsg = new HashSet();

        expMsg.add(excelReader.getCellData(xlSheetName, 574, 6));
        expMsg.add(excelReader.getCellData(xlSheetName, 575, 6));
        //expMsg.add(excelReader.getCellData(xlSheetName, 576, 6));        
        //expMsg.add(excelReader.getCellData(xlSheetName, 577, 6));
        
        excelReader.setCellData(xlfile, xlSheetName, 574, 7, actMessage);
        
        String expMessage = expMsg.toString();

		System.out.println("Actual Message    : " + actMessage);
		System.out.println("Expected Message  : " + expMessage);

		/*boolean actSaving=SavingInBackground(docno);
		boolean expSaving=true;*/
		
		if (/*actSaving==expSaving*/actMessage.equalsIgnoreCase(expMessage)) {

			click(new_CloseBtn);
			Thread.sleep(1000);
			click(voucherhomeCloseBtn);
			excelReader.setCellData(xlfile, xlSheetName, 574, 8, resPass);
			return true;
		} else {
			click(new_CloseBtn);
			Thread.sleep(1000);
			click(voucherhomeCloseBtn);
			excelReader.setCellData(xlfile, xlSheetName, 574, 8, resFail);
			return false;
		}
	}

	public boolean checkStockLedgerReportForBrBatchItemAfterStockInAndStockOutVouchers()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		
		excelReader =new ExcelReader(POJOUtility.getExcelPath());

		click(inventoryMenu);
		click(InventoryReportsMenu);
		click(InventoryStockLedgerMenu);
		Thread.sleep(3000);

		int rowcount = stockLedgerHometableRowCount.size();

		System.out.println(rowcount);

		for (int i = 0; i < rowcount; i++) {
			String actName = ledgerHometableItemNamesList.get(i).getText();

			System.out.println(actName);

			if (actName.equalsIgnoreCase(excelReader.getCellData(xlSheetName, 578, 5))) {
				stockLedgerHometableItemChkboxList.get(i).click();
			}
		}

		dropDown(sl_DateOptionDropdown, excelReader.getCellData(xlSheetName, 579, 5));
		Thread.sleep(2000);
		click(sl_OkBtn);
		Thread.sleep(2000);

		int reportsByWarehouseRow3ListCount = reportsRow2List.size();
		ArrayList<String> reportsByWarehouseRow3ListArray = new ArrayList<String>();
		for (int i = 0; i < reportsByWarehouseRow3ListCount; i++) {
			String data = reportsRow2List.get(i).getText();
			reportsByWarehouseRow3ListArray.add(data);
		}
		String actRow3List = reportsByWarehouseRow3ListArray.toString();
		String expRow3List = excelReader.getCellData(xlSheetName, 578, 6)+getCurrentDateF2()+excelReader.getCellData(xlSheetName, 579, 6);
		excelReader.setCellData(xlfile, xlSheetName, 578, 7, actRow3List);

		System.out.println(actRow3List);
		System.out.println(expRow3List);

		int reportsByWarehouseRow4ListCount = reportsRow3List.size();
		ArrayList<String> reportsByWarehouseRow4ListArray = new ArrayList<String>();
		for (int i = 0; i < reportsByWarehouseRow4ListCount; i++) {
			String data = reportsRow3List.get(i).getText();
			reportsByWarehouseRow4ListArray.add(data);
		}
		String actRow4List = reportsByWarehouseRow4ListArray.toString();
		String expRow4List = excelReader.getCellData(xlSheetName, 580, 6)+getCurrentDateF2()+excelReader.getCellData(xlSheetName, 581, 6);
		excelReader.setCellData(xlfile, xlSheetName, 580, 7, actRow4List);

		System.out.println(actRow4List);
		System.out.println(expRow4List);

		if (actRow3List.equalsIgnoreCase(expRow3List) && actRow4List.equalsIgnoreCase(expRow4List)) {
			click(report_CloseBtn);
			Thread.sleep(2000);
			click(sl_CloseBtn);
			excelReader.setCellData(xlfile, xlSheetName, 578, 8, resPass);
			return true;
		} else {
			click(report_CloseBtn);
			Thread.sleep(2000);
			click(sl_CloseBtn);
			excelReader.setCellData(xlfile, xlSheetName, 578, 8, resFail);
			return false;
		}
	}

	public boolean checkLoginToTest1UserAndAuthorizeStockOutVocher()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		
		excelReader =new ExcelReader(POJOUtility.getExcelPath());
		
		reLogin("Test1", "su");
		
		/*Thread.sleep(3000);
		click(userNameDisplayLogo);
		Thread.sleep(1000);
		click(logoutOption);
		Thread.sleep(3000);

		String unamelt = excelReader.getCellData(xlSheetName, 582, 5);

		String pawslt = excelReader.getCellData(xlSheetName, 583, 5);

		LoginPage.enterUserName(unamelt);
		LoginPage.enterPassword(pawslt);

		Select company = new Select(companyDropDownList);
		company.selectByValue(excelReader.getCellData(xlSheetName, 584, 5));
		Thread.sleep(2000);

		LoginPage.clickOnSignInBtn();

		Thread.sleep(3000);

		System.out.println("Signing");*/

		// Login User Name is Verify
		
		Thread.sleep(1500);

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(userNameDisplay));
		String actUserInfo = userNameDisplay.getText();
		String expUserInfo = excelReader.getCellData(xlSheetName, 582, 6);
		excelReader.setCellData(xlfile, xlSheetName, 582, 7, actUserInfo);

		System.out.println("Login With Valid User and Check User Name Display Value Actual : ." + actUserInfo
				+ ". Value Expected : " + expUserInfo);

		// Login Company Name is Verify
		Thread.sleep(1000);
		click(companyLogo);
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(companyName));
		String actGetLoginCompanyNameInformation = companyName.getText();
		String actGetLoginCompanyName = actGetLoginCompanyNameInformation.substring(0, 19);
		String expGetLoginCompanyName = excelReader.getCellData(xlSheetName, 583, 6);
		excelReader.setCellData(xlfile, xlSheetName, 583, 7, actGetLoginCompanyName);
		companyLogo.click();

		System.out.println("Company Name Display Value Actual                : ." + actGetLoginCompanyName
				+ ". Value Expected : " + expGetLoginCompanyName);

		click(inventoryMenu);
		click(inventoryTransactionsMenu);
		click(inventoryTransactionsStockMenu);
		click(inventoryTransactionsStockOut);
		Thread.sleep(1000);
		click(homePageVoucherArrowBtn);
		click(stockOutPendingAuthLink);
		// stockOutHomeAuthBtn
		
		Thread.sleep(2000);

		int count = grid_VoucherNoList.size();

		for (int i = 0; i < count; i++) {
			String VoucherNo = grid_VoucherNoList.get(i).getText();

			if (VoucherNo.equalsIgnoreCase(excelReader.getCellData(xlSheetName, 585, 5))) {
				grid_CheckBoxList.get(i).click();
				break;
			}
		}

		click(stockOutHomeAuthBtn);
		Thread.sleep(2000);
		click(homePageVoucherArrowBtn);
		Thread.sleep(2000);
        click(homePageAllVouchersView);
		
		Thread.sleep(2000);
		
		String actRow1AuthorizationStatus = voucherGridRow1AuthorizationStatus.getText();
		String expRow1AuthorizationStatus = excelReader.getCellData(xlSheetName, 584, 6);
		excelReader.setCellData(xlfile, xlSheetName, 584, 7, actRow1AuthorizationStatus);
		
		
		System.out.println("Row1AuthorizationStatus  : "+actRow1AuthorizationStatus  +" Value Expected : "+expRow1AuthorizationStatus);
		
		
		if(actRow1AuthorizationStatus.equalsIgnoreCase(expRow1AuthorizationStatus))
		{
			click(voucherhomeCloseBtn);			
			Thread.sleep(1000);
			excelReader.setCellData(xlfile, xlSheetName, 582, 8, resPass);
			return true;
		}
		else
		{
			click(voucherhomeCloseBtn);
			Thread.sleep(1000);
			excelReader.setCellData(xlfile, xlSheetName, 582, 8, resFail);
			return false;
		}

	}
	
	
	public boolean checkStockLedgerReportForStockItemAfterAutrhroizedStockOutVoucher()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		
		excelReader =new ExcelReader(POJOUtility.getExcelPath());

		click(inventoryMenu);
		click(InventoryReportsMenu);
		click(InventoryStockLedgerMenu);
		Thread.sleep(3000);

		int rowcount = stockLedgerHometableRowCount.size();

		System.out.println(rowcount);

		for (int i = 0; i < rowcount; i++) {
			String actName = ledgerHometableItemNamesList.get(i).getText();

			System.out.println(actName);

			if (actName.equalsIgnoreCase(excelReader.getCellData(xlSheetName, 586, 5))) {
				stockLedgerHometableItemChkboxList.get(i).click();
			}
		}
		
		dropDown(sl_DateOptionDropdown, excelReader.getCellData(xlSheetName, 587, 5));		
		Thread.sleep(2000);
		click(sl_OkBtn);
		Thread.sleep(2000);
		
		
		int reportsByWarehouseRow2ListCount = reportsRow2List.size();
		ArrayList<String> reportsByWarehouseRow2ListArray = new ArrayList<String>();
		for(int i=0;i<reportsByWarehouseRow2ListCount;i++)
		{
			String data = reportsRow2List.get(i).getText();
			reportsByWarehouseRow2ListArray.add(data);
		}
		String actRow2List = reportsByWarehouseRow2ListArray.toString();
		String expRow2List = excelReader.getCellData(xlSheetName, 586, 6)+getCurrentDateF2()+excelReader.getCellData(xlSheetName, 587, 6);
		excelReader.setCellData(xlfile, xlSheetName, 586, 7, actRow2List);
		
		
		
		System.out.println(actRow2List);
		System.out.println(expRow2List);
		
		int reportsByWarehouseRow3ListCount = reportsRow3List.size();
		ArrayList<String> reportsByWarehouseRow3ListArray = new ArrayList<String>();
		for(int i=0;i<reportsByWarehouseRow3ListCount;i++)
		{
			String data = reportsRow3List.get(i).getText();
			reportsByWarehouseRow3ListArray.add(data);
		}
		String actRow3List = reportsByWarehouseRow3ListArray.toString();
		String expRow3List = excelReader.getCellData(xlSheetName, 588, 6)+getCurrentDateF2()+excelReader.getCellData(xlSheetName, 589, 6);
		excelReader.setCellData(xlfile, xlSheetName, 588, 7, actRow3List);
		
		
		
		System.out.println(actRow3List);
		System.out.println(expRow3List);
		
		int reportsByWarehouseRow4ListCount = reportsRow4List.size();
		ArrayList<String> reportsByWarehouseRow4ListArray = new ArrayList<String>();
		for(int i=0;i<reportsByWarehouseRow4ListCount;i++)
		{
			String data = reportsRow4List.get(i).getText();
			reportsByWarehouseRow4ListArray.add(data);
		}
		String actRow4List = reportsByWarehouseRow4ListArray.toString();
		String expRow4List = excelReader.getCellData(xlSheetName, 590, 6)+getCurrentDateF2()+excelReader.getCellData(xlSheetName, 591, 6);
		excelReader.setCellData(xlfile, xlSheetName, 590, 7, actRow4List);
		
		
		
		System.out.println(actRow4List);
		System.out.println(expRow4List);
		
		int reportsByWarehouseRow5ListCount = reportsRow5List.size();
		ArrayList<String> reportsByWarehouseRow5ListArray = new ArrayList<String>();
		for(int i=0;i<reportsByWarehouseRow5ListCount;i++)
		{
			String data = reportsRow5List.get(i).getText();
			reportsByWarehouseRow5ListArray.add(data);
		}
		String actRow5List = reportsByWarehouseRow5ListArray.toString();
		String expRow5List = excelReader.getCellData(xlSheetName, 592, 6)+getCurrentDateF2()+excelReader.getCellData(xlSheetName, 593, 6);
		excelReader.setCellData(xlfile, xlSheetName, 592, 7, actRow5List);
		
		
		
		System.out.println(actRow5List);
		System.out.println(expRow5List);
		
		
		
		

		if(actRow2List.equalsIgnoreCase(expRow2List) && actRow3List.equalsIgnoreCase(expRow3List)
				&& actRow4List.equalsIgnoreCase(expRow4List) && actRow5List.equalsIgnoreCase(expRow5List))
		{
			click(report_CloseBtn);				
			Thread.sleep(2000);
			click(sl_CloseBtn);	
			excelReader.setCellData(xlfile, xlSheetName, 586, 8, resPass);
			return true;
		}
		else
		{
			click(report_CloseBtn);				
			Thread.sleep(2000);
			click(sl_CloseBtn);	
			excelReader.setCellData(xlfile, xlSheetName, 586, 8, resFail);
			return false;
		}
	}
	
	
	public boolean checkLogout()
			throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(userNameDisplayLogo));
		userNameDisplayLogo.click();

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(logoutOption));
		logoutOption.click();

		Thread.sleep(2000);

		System.out.println("Logout  Successfully");

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(username));
		boolean actUserName = username.isDisplayed();
		boolean expUserName = true;

		getFluentWebDriverWait().until(ExpectedConditions.elementToBeClickable(password));
		boolean actPassword = password.isDisplayed();
		boolean expPassword = true;

		System.out.println("*************************CheckLogout***************************");

		System.out.println("UserName is Displaying : " + actUserName + "  Value Expected  " + expUserName);
		System.out.println("Password is Displaying : " + actPassword + "  Value Expected  " + expPassword);

		if (actUserName == expUserName && actPassword == expPassword) {
			return true;
		} else {
			return false;
		}
	}
	
	
	
}		
			
		




	 




					    
					    
						
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
