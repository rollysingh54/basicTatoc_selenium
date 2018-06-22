package tatoc_selenium;



import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;

public class tatoc {
	
	public static void main(String[] args)
	{
		//System.setProperty("webdriver.chrome.driver","D:\\tools\\chromedriver.exe");
		boolean frame_dungeon=true;
		WebDriver driver= new ChromeDriver();
		driver.get("http://10.0.1.86/tatoc");
		driver.findElement(By.cssSelector("a")).click();
		driver.findElement(By.className("greenbox")).click();
		//driver.switchTo().frame("child");
		/*driver.switchTo().frame(driver.findElement(By.id("main")));
		String mainBoxColor=driver.findElements(By.id("answer")).get(0).getCssValue("background-color").toString();
		//System.out.println(mainBoxColor);
		String mainhex = Color.fromString(mainBoxColor).asHex();
		System.out.println(mainhex);
		driver.switchTo().frame(driver.findElement(By.id("child")));
		//driver.findElement(By.xpath("/html/body/center/a[1]")).click();
		while(frame_dungeon)
		{
			
		String childBoxColor=driver.findElements(By.id("answer")).get(0).getCssValue("background-color").toString();
		String childhex = Color.fromString(childBoxColor).asHex();
		System.out.println(childhex);
		if(mainhex.equals(childhex))
		{
			frame_dungeon=false;
			driver.findElement(By.xpath("/html/body/center/a[2]")).click();
		}
		else
		{
			driver.switchTo().defaultContent();
			driver.switchTo().frame(driver.findElement(By.tagName("center")));
		driver.findElement(By.xpath("/html/body/center/a[1]")).click();
		}
		}*/
		
		WebElement mainframe=driver.findElement(By.id("main"));
		driver.switchTo().frame(mainframe);
		WebElement box1=driver.findElement(By.xpath("//div[text()='Box 1']"));
		String box1Color=box1.getAttribute("class");
		WebElement childframe=driver.findElement(By.id("child"));
		driver.switchTo().frame(childframe);
		
		while(true)
		{
			driver.switchTo().defaultContent();
			driver.switchTo().frame(mainframe);
			WebElement childframe2=driver.findElement(By.id("child"));
			driver.switchTo().frame(childframe2);
			WebElement box2=driver.findElement(By.xpath("//div[text()='Box 2']"));
			String box2Color=box2.getAttribute("class");
			if(box2Color.equalsIgnoreCase(box1Color))
			{
				driver.switchTo().defaultContent();
				driver.switchTo().frame(mainframe);
				break;
			}
			else
			{driver.switchTo().defaultContent();
			driver.switchTo().frame(mainframe);
			WebElement repaintLink=driver.findElement(By.xpath("//a[text()='Repaint Box 2']"));
			repaintLink.click();
			}
		}
		WebElement proceedLink=driver.findElement(By.xpath("//a[text()='Proceed']"));
		
		proceedLink.click();
		
		WebElement dragbox=driver.findElement(By.id("dragbox"));
		WebElement dropbox=driver.findElement(By.id("dropbox"));
		
		Actions a=new Actions(driver);
		a.dragAndDrop(dragbox, dropbox).build().perform();
		
		
		
        WebElement proceedDD=driver.findElement(By.xpath("//a[text()='Proceed']"));
		
		proceedDD.click();
		
		String parentWindow=driver.getWindowHandle();
		
		WebElement launch=driver.findElement(By.xpath("//a[text()='Launch Popup Window']"));
		launch.click();
		
		Set<String> windowHandles=driver.getWindowHandles();
		windowHandles.remove(parentWindow);
		
		driver.switchTo().window((String)(windowHandles.toArray())[0]);
		System.out.println("Switched to new window");
		
		WebElement nameTextBox=driver.findElement(By.id("name"));
		nameTextBox.sendKeys("hjrdfhjier");
		
		WebElement submitButton=driver.findElement(By.id("submit"));
		submitButton.click();
		
		driver.switchTo().window(parentWindow);
		System.out.println("Switched back to original window");
		
		 WebElement proceedPopup=driver.findElement(By.xpath("//a[text()='Proceed']"));
	     proceedPopup.click(); 
		
	     WebElement generateToken=driver.findElement(By.xpath("//a[text()='Generate Token']"));
	     generateToken.click();
	     
	 	WebElement token=driver.findElement(By.id("token"));
	     String tokenValue=token.getText().substring(7);
	     
	     System.out.println("TOKEN: "+tokenValue);
	     Cookie name=new Cookie("Token",tokenValue);
	     driver.manage().addCookie(name);
	     
	     WebElement proceedCookie=driver.findElement(By.xpath("//a[text()='Proceed']"));
	     proceedCookie.click(); 
		
		
		
	}

}
