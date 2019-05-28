package com.gst.reco.KAAKS;

import Domain.DataCheck.*;

public class Home {

	public static void main(String args[]) {
		DataCheck datacheck = new DataCheck();
		MoveFileExample mvfile = new MoveFileExample();
		DownloadFileHttpCilent client = new DownloadFileHttpCilent();
		RunMacroController rnMcro = new RunMacroController();
		SendAttachmentInEmail sndEml = new SendAttachmentInEmail();
		DataCheck datachk=new DataCheck();
		  while(true){
		System.out.println("Hello");
		String data[] = datacheck.getDetail();

		System.out.println(data);
		// check for null reference
		if (null != data[0]) {
			String fileName = data[0];
			String email = data[1];
			String utilityName = data[2];

		    int A_flag =client.download(fileName);
			int Reg_flag=client.downloadReg(fileName);
			if(A_flag==200 && Reg_flag==200)
			{
			mvfile.copy(fileName, utilityName);
			
			rnMcro.runMacro(fileName);
			
			boolean emailFlag=sndEml.sendEmail(fileName + "_ConvertedReport", email);
			 datachk.setDetail(fileName,"send");
			if(emailFlag)
				{
				datachk.setDetail(fileName,"send");
				}
			else{
				datachk.setDetail(fileName,"ERRORINEMAIL");
				}
			
			mvfile.deleteFile(fileName);
			
			}
			else{
				 datachk.setDetail(fileName,"FNFOUND");		
				 }
			
			
			
		}

		  }
	}

}
