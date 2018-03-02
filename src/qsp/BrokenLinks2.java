package qsp;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class BrokenLinks2 {
	static
	{
		System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
	}
	public static void main(String[] args) {
		
		HttpURLConnection huc=null;
		String homePage="https://www.facebook.com/";
		int status =200;
		WebDriver driver= new ChromeDriver();
		driver.get("https://www.facebook.com/");
//		List<WebElement> allLinks=driver.findElements(By.xpath("//a"));
		List<WebElement> allLinks=driver.findElements(By.tagName("a"));
		for(WebElement link:allLinks)
		{
			String url = link.getAttribute("href");
			if(url==null ||url.isEmpty())
			{
				System.out.println(url+"Anchor is not configured");
				continue;
			}
		
			if(!url.startsWith(homePage))
			{
				System.out.println(url+" : Third Party Links");
				continue;
			}
			else
			{
				try {
					huc=(HttpURLConnection)new URL(url).openConnection();
//					huc.setRequestMethod("HEAD");
					huc.connect();
					status=huc.getResponseCode();
					if(status!=200)
					{
						System.out.println(url+" : The Link broken");
					}
					else
					{
						System.out.println(url+" : The Link is working fine");
					}
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		int noOfLinks=allLinks.size();
		System.out.println("NO of LInks: "+noOfLinks);
		driver.close();
}
}
