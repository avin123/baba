package generics;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.WorkbookFactory;

public class Excel {
	
	
	
	
	
	public int getRowCount(String sheet,String path)
	{
		int r=0;
		try
		{
			r=WorkbookFactory.create(new FileInputStream(path)).getSheet(sheet).getLastRowNum();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return r;
		
	}
	public String getCellValue(String path,String sheet,int r,int c)
	{
		String v="";
		try
		{
			v = WorkbookFactory.create(new FileInputStream(path)).getSheet(sheet).getRow(r).getCell(c).toString();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return v;
	}

}
