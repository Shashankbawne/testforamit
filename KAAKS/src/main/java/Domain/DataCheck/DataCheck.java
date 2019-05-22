
package Domain.DataCheck;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

 
public class DataCheck {

	static Connection dbconn = null;
	String username = "";
	String password = "";
	PreparedStatement prep = null;
	ResultSet rs = null;

	public boolean statusCheck() {
		
		return false;
	}

	
	 

	public static String[] getDetail () {
	
		String[] tempfile = new String[2];
		try {
			dbconn = new DAO.DAOClass().getConnection();
			Statement stmt = dbconn.createStatement();
			ResultSet rs = stmt.executeQuery("select name,email from utility where status='not2'");
			while (rs.next()) {
				tempfile[0] = rs.getString("name");
				tempfile[1] = rs.getString("email");
				//System.out.println("" + tempfile);
				break;
			}
			dbconn.close();
			
		} catch (Exception e) {
			System.out.println(e);
		}
		return tempfile;
	}
	
	public static void main(String args[]){
		String data[]=getDetail();
		if("".equals(data)){
				
		System.out.println(data[0]);
		}
		else{
			System.out.println("tt");
		}
	}

}
