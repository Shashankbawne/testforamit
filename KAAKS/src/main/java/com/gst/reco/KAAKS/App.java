package com.gst.reco.KAAKS;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Variant;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	 
			  	String sDir = "c:\\java\\jacob\\";
			    String sInputDoc = sDir + "file_in.docx";
			    String sOutputDoc = sDir + "file_out.docx";
			    String sOldText = "[label:import:1]";
			    String sNewText = "I am some horribly long sentence, so long that [insert bullshit here]";
			    boolean tVisible = true;
			    boolean tSaveOnExit = false;	  
			    
			    ActiveXComponent oWord = new ActiveXComponent("e.Application");
			    oWord.setProperty("Visible", tVisible);
			    ActiveXComponent oDocuments = oWord.getPropertyAsComponent("Documents");
			    ActiveXComponent oDocument = oDocuments.invokeGetComponent("Open", new Variant(sInputDoc)); 
			    ActiveXComponent oSelection = oWord.getPropertyAsComponent("Selection");
			    ActiveXComponent oFind = oSelection.getPropertyAsComponent("Find");
	    	 
		 
        
    }
}
