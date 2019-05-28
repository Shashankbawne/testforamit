package com.gst.reco.KAAKS;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
 
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
public class DownloadFileHttpCilent {
	  public static void main(String[] args) throws Exception {
	        DownloadFileHttpCilent client = new DownloadFileHttpCilent();
	        client.download("fileName");
	    }
	 
	    public int download(String fileName) {
	        try {
	            CloseableHttpClient client = HttpClientBuilder.create().build();
	            HttpGet request = new HttpGet(
	                "http://www.jbworlds.com/jbworlds.com/instantreco/upload/A_"+fileName+".xlsx");
	 
	            HttpResponse response = client.execute(request);
	            HttpEntity entity = response.getEntity();
	 
	            int responseCode = response.getStatusLine().getStatusCode();
	 
	            System.out.println("Request Url: " + request.getURI());
	            System.out.println("Response Code: " + responseCode);
	 
	            InputStream is = entity.getContent();
	 
	            String filePath = "c:\\instantreco\\A_"+fileName+".xlsx";
	            FileOutputStream fos = new FileOutputStream(new File(filePath));
	 
	            int inByte;
	            while ((inByte = is.read()) != -1) {
	                fos.write(inByte);
	            }
	            is.close();
	            fos.close();
	 
	            client.close();
	            System.out.println("File Download Completed!!!");
	            return responseCode;
	        } catch (ClientProtocolException e) {
	        	
	            e.printStackTrace();
	            return 404;
	        } catch (UnsupportedOperationException e) {
	            e.printStackTrace();
	            return 404;
	        } catch (IOException e) {
	            e.printStackTrace();
	            return 404;
	        }
	    }
	    public int downloadReg(String fileName) {
	        try {
	            CloseableHttpClient client = HttpClientBuilder.create().build();
	            HttpGet request = new HttpGet(
	                "http://www.jbworlds.com/jbworlds.com/instantreco/upload/Reg_"+fileName+".xlsx");
	 
	            HttpResponse response = client.execute(request);
	            HttpEntity entity = response.getEntity();
	 
	            int responseCode = response.getStatusLine().getStatusCode();
	 
	            System.out.println("Request Url: " + request.getURI());
	            System.out.println("Response Code: " + responseCode);
	 
	            InputStream is = entity.getContent();
	 
	            String filePath = "c:\\instantreco\\Reg_"+fileName+".xlsx";
	            FileOutputStream fos = new FileOutputStream(new File(filePath));
	 
	            int inByte;
	            while ((inByte = is.read()) != -1) {
	                fos.write(inByte);
	            }
	            is.close();
	            fos.close();
	 
	            client.close();
	            System.out.println(" Reg File Download Completed!!!");
	            return responseCode;
	        } catch (ClientProtocolException e) {
	            e.printStackTrace();
	            return 404;
	        } catch (UnsupportedOperationException e) {
	            e.printStackTrace();
	            return 404;
	        } catch (IOException e) {
	            e.printStackTrace();
	            return 404;
	        }
	    }

}
