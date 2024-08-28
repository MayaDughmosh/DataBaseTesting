import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;



public class MyTestCases {
	WebDriver driver=new ChromeDriver();
	Connection con;
	Statement stat;
	ResultSet rs;
	Random rand=new Random();
	
	Random rand2=new Random();

	
	@BeforeTest 
	public void mySetUp() throws SQLException {
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels","root","maya1234");
		
	}
	
	
	@Test (priority=1)
	public void AddedToDataBase() throws SQLException {
		int RandomNumber=rand.nextInt(889) * rand2.nextInt(453);
		System.out.println(RandomNumber);
		
		String query=" INSERT INTO customers (customerNumber,customerName,contactLastName,contactFirstName,phone,addressLine1,addressLine2,city,state,postalCode,country,salesRepEmployeeNumber,creditLimit) VALUES (501,'Innovate Ltd','Jones','Emily','555-6789', '456 Oak St','Apt 789', 'Riverside', 'CA', '92501', 'USA', 103, 60000.00 );  ";
		stat=con.createStatement();
		int rowInserted=stat.executeUpdate(query);
	
		System.out.println(rowInserted);
		
	}
	
	@Test (priority=2)
	public void UpdateDate() throws SQLException {
		String query=" update customers set contactFirstName='Nooralhuda' where customerNumber=501 ";
		stat=con.createStatement();
		int rowInserted=stat.executeUpdate(query);
		System.out.println(rowInserted);
	}
	
	
	@Test (priority=3)
	public void GetData() throws SQLException {
		stat=con.createStatement();
		rs=stat.executeQuery("select * from customers where customerNumber=501");
		while (rs.next()) {
			int CustomerNumber=rs.getInt("customerNumber");
			String CustomerName=rs.getString("CustomerName");
					System.out.print(CustomerName);
					System.out.println(CustomerNumber);
					
					String firstname=rs.getString("contactFirstName");
					String lastname=rs.getString("contactLastName");
					driver.get("https://magento.softwaretestingboard.com/customer/account/create/");
					WebElement FirstNameInput=driver.findElement(By.id("firstname"));
					WebElement LastNameInput=driver.findElement(By.id("lastname"));
					FirstNameInput.sendKeys(firstname);
					LastNameInput.sendKeys(lastname);
					
		}
		
	}
	@Test (priority=4 , enabled=false)
	public void DeleteData() throws SQLException {
		String query="delete from customers where customerNumber=501";
		stat=con.createStatement();
		int rowInserted=stat.executeUpdate(query);
		System.out.println(rowInserted);
	}
	
		
	}

