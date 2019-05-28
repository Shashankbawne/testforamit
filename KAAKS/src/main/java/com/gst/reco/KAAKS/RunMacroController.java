package com.gst.reco.KAAKS;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComThread;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

public class RunMacroController {

	 

	public void runMacro(String name) {
		String fName="C:\\instantreco\\"+name+".xlsm";
		// TODO Auto-generated method stub
		 DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		   LocalDateTime now = LocalDateTime.now();  
		   System.out.println(dtf.format(now));  
		System.out.println("started");
		File file = new File(fName);
		String macroName = "Macro_1";
		callExcelMacro(file, macroName);
		System.out.println("done");
		 LocalDateTime now2 = LocalDateTime.now(); 
		System.out.println(dtf.format(now2));

	}

	private static void callExcelMacro(File file, String macroName) {
		ComThread.InitSTA(true);
		final ActiveXComponent excel = new ActiveXComponent("Excel.Application");
		try {
			//excel.setProperty("EnableEvents", new Variant(false));
			Dispatch workbooks = excel.getProperty("Workbooks").toDispatch();
			Dispatch workBook = Dispatch.call(workbooks, "Open", file.getAbsolutePath()).toDispatch();

			// Calls the macro
		//	Variant V1 = new Variant(file.getName() + macroName);
			Variant result = Dispatch.call(excel, "Run", "Macro_1");

			// Saves and closes
			Dispatch.call(workBook, "Save");

			com.jacob.com.Variant f = new com.jacob.com.Variant(true);
			Dispatch.call(workBook, "Close", f);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			excel.invoke("Quit", new Variant[0]);
			ComThread.Release();
		}
	}
}