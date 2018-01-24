package generics;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.imageio.ImageIO;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;




public class Utility {
	public static String getValue(String path,String key) 
	{
		String value="";
		Properties ppt=new Properties();
		try
		{
	ppt.load(new FileInputStream(path));
	 value=ppt.getProperty(key);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	return value;
}
	public static String getFormattedDateAndTime()
	{
		SimpleDateFormat SimpleDate=new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		return SimpleDate.format(new Date());
		
	}
	public static ArrayList<String> getAllText(WebElement ListBox)
	{
		ArrayList<String> allText=new ArrayList<String>();
		Select select=new Select(ListBox);
		List<WebElement> allOptions = select.getOptions();
		for(WebElement Option:allOptions)
		{
			String Text=Option.getText();
			allText.add(Text);
		}
		return allText;
				
	}
	public static boolean isListBoxSorted(ArrayList<String> allText)
	{
		ArrayList<String> Clone = new ArrayList<String>(allText);
		Collections.sort(Clone);
		return allText.equals(Clone);
		}
	public static void getScreenshot(WebDriver driver,String imageFolderPath)
	{
	try{	
		
		FileUtils.copyFile(((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE),new File(imageFolderPath+"/"+getFormattedDateAndTime()+".png"));
	
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
}
	public static void getScreenshot(String imageFolderPath)
	{
		try
		{
			Dimension desktopSize=Toolkit.getDefaultToolkit().getScreenSize();
			BufferedImage Image=new Robot().createScreenCapture(new Rectangle(desktopSize));
			ImageIO.write(Image, "png", new File(imageFolderPath+"/"+getFormattedDateAndTime()+".png"));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
