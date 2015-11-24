package demoVerifyElement;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DemoFirefox {
	WebDriver driver;
	String myXLPath="C:\\Users\\golamhassan1\\workspace\\nyseData.xls";
	boolean linkPresent, linkNotPresent;
	int xRows, xCols; 
	String xData[][]; 
	int vResult;
	String vURL = "http://google.com"; 
	long vSleep;
	String exPath, cSelector;
	@Before
	public void setUp() throws Exception {
		
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		vSleep = 5000;
		wait_time(vSleep);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	    xlRead(myXLPath);
	    
	}

	@Test
	public void demoNyse() throws Exception {
		
		String vSearch;
		
				vResult = 1;
		     	
				int  i;
		  
		  for ( i=1; i<xRows; i=i+1){
			  if (xData[i][2].equals("Y")){
			vSearch = xData[i][0];
		appInput(vSearch);
		vSleep = 2000;
		wait_time(vSleep);
		   codeOutput();
		  // Converting boolean to String
	      String s1, s2;
	      s1 = Boolean.toString(linkPresent);
	      s2 = Boolean.toString(linkNotPresent);
	      try{
		  xData [i][vResult] = s1;
	      }catch (Exception e){
	    	  System.out.println(e.getMessage());
	    	  xData [i][vResult] = s2; 
	      }
	      // Implement Wait keyword
	      vSleep = 1000;
		  wait_time(vSleep);
		   	}
		  }
		 }

	@After
	public void tearDown() throws Exception {
		String res_path="C:\\Users\\golamhassan1\\workspace\\nyseResultFF2.xls";
		 // Implement Close Browser keyword
	   		close_browser(driver);
		    xlwrite(res_path, xData);
	}
	
	public  void appInput(String vSearch) throws Exception{
			  
		 // Implement navigate to keyword to open URL
		   navigate_to(driver, vURL);
		   driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		   driver.manage().window().maximize();
		   vSleep = 2000;
			wait_time(vSleep);
		   // Implement SendKeys Keyword Method: Find a search field and type text on it
		     cSelector = "#lst-ib";
		   driver.findElement(By.cssSelector(cSelector)).clear();
		// driver.findElement(By.cssSelector(cSelector)).sendKeys(vSearch);
		   send_keys(driver,cSelector, vSearch);
		  
		   //Implement Click Keyword Method: Find an element and click on it
		   exPath =".//*[@id='sblsbb']/button";
		   click_element(driver, exPath);
		  // driver.findElement(By.xpath(exPath)).click();
		  
		   vSleep = 5000;
			wait_time(vSleep);
		   driver.findElement(By.xpath("//a[contains(text(),'The New York Stock Exchange')]")).click();
		  
		   System.out.println("The test is Pass");
		   
		   vSleep = 5000;
			wait_time(vSleep);
		  	}
	
	public void codeOutput(){
		try{
		linkPresent = driver.findElement(By.linkText("List With NYSE")).isDisplayed();
		System.out.println(linkPresent);
		}catch (Exception e){
			System.out.println("Error: " + e.getMessage());
			e.printStackTrace();
			System.out.println(linkNotPresent);
		}
	//	System.out.println("Pass");
		
	}
public static void navigate_to(WebDriver nD, String fURL){
		
		nD.navigate().to(fURL);
	}
	public static void send_keys(WebDriver nD, String fcSelector, String fvSearch){
		
		nD.findElement(By.cssSelector(fcSelector)).sendKeys(fvSearch);
	}
    public static void click_element(WebDriver nD, String fxPath){
		
		nD.findElement(By.xpath(fxPath)).click();
	}

	public static void close_browser(WebDriver nD){
		nD.close();
	}
	public static void wait_time(Long fWait)throws Exception{
	Thread.sleep(fWait);
	}
	
	
	public void xlRead(String sPath) throws Exception{ 
	    File myxl = new File(sPath); 
	    FileInputStream myStream = new FileInputStream(myxl); 
	    HSSFWorkbook myWB = new HSSFWorkbook(myStream); 
	    HSSFSheet mySheet = myWB.getSheet("Data"); // Referring to Sheet1

	    xRows = mySheet.getLastRowNum()+1; 
	    xCols = mySheet.getRow(0).getLastCellNum(); 
	    System.out.println("Rows are " + xRows); 
	    System.out.println("Cols are " + xCols); 
	    xData = new String[xRows][xCols]; 
	    
	    
	    for (int i = 0; i < xRows; i++) { 
	           HSSFRow row = mySheet.getRow(i); 
	           
	            for (int j = 0; j < xCols; j++) { 
	               HSSFCell cell = row.getCell(j); // To read value from each col in each row 
	               
	               String value = cellToString(cell); 
	               xData[i][j] = value; 
	               
	               System.out.print(value); 
	               System.out.print("  "); 
	               } 
	            System.out.println(""); 
	        }    
	} 


	public static String cellToString(HSSFCell cell) { 
	    // This function will convert an object of type excel cell to a string value 
	      
		
		int type = cell.getCellType(); 
	        Object result; 
	        
	        
	        switch (type) { 
	            case HSSFCell.CELL_TYPE_NUMERIC: //0 
	                result = cell.getNumericCellValue(); 
	                break; 
	            case HSSFCell.CELL_TYPE_STRING: //1 
	                result = cell.getStringCellValue(); 
	                break; 
	            case HSSFCell.CELL_TYPE_FORMULA: //2 
	                throw new RuntimeException("We can't evaluate formulas in Java"); 
	            case HSSFCell.CELL_TYPE_BLANK: //3 
	                result = "-"; 
	                break; 
	            case HSSFCell.CELL_TYPE_BOOLEAN: //4 
	                result = cell.getBooleanCellValue(); 
	                break; 
	            case HSSFCell.CELL_TYPE_ERROR: //5 
	                throw new RuntimeException ("This cell has an error"); 
	            default: 
	                throw new RuntimeException("We don't support this cell type: " + type); 
	        } 
	        return result.toString(); 
	}

	public void xlwrite(String xlPath, String[][] xldata) throws Exception { 
	    System.out.println("Inside XL Write"); 
	    File outFile = new File(xlPath); 
	    HSSFWorkbook wb = new HSSFWorkbook(); 
	       // Make a worksheet in the XL document created 
	    /*HSSFSheet osheet = wb.setSheetName(1,"TEST");*/
	    HSSFSheet osheet = wb.createSheet("SearchResultFF1"); 
	    // Create row at index zero ( Top Row) 
	    for (int myrow = 0; myrow < xRows; myrow++) { 
	        //System.out.println("Inside XL Write"); 
	        HSSFRow row = osheet.createRow(myrow); 
	        // Create a cell at index zero ( Top Left) 
	        for (int mycol = 0; mycol < xCols; mycol++) { 
	            HSSFCell cell = row.createCell(mycol); 
	            // Lets make the cell a string type 
	            cell.setCellType(HSSFCell.CELL_TYPE_STRING); 
	            // Type some content 
	            cell.setCellValue(xldata[myrow][mycol]); 
	            //System.out.print("..." + xldata[myrow][mycol]); 
	        } 
	        //System.out.println(".................."); 
	        // The Output file is where the xls will be created 
	        FileOutputStream fOut = new FileOutputStream(outFile); 
	        // Write the XL sheet 
	        wb.write(fOut); 
	        fOut.flush(); 
//	      // Done Deal.. 
	        fOut.close(); 
	    } 
	}
}
