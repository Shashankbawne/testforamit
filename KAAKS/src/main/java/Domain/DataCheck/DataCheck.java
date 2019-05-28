
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

	
	 

	public  String[] getDetail () {
	System.out.println("in database");
		String[] tempfile = new String[3];
		try {
			dbconn = new DAO.DAOClass().getConnection();
			Statement stmt = dbconn.createStatement();
			ResultSet rs = stmt.executeQuery("select name,email,uname,status from utility where status='not'");
			while (rs.next()) {
				tempfile[0] = rs.getString("name");
				tempfile[1] = rs.getString("email");
				tempfile[2] = rs.getString("uname");
				System.out.println("" + rs.getString("status"));
				break;
			}
			dbconn.close();
			
		} catch (Exception e) {
			System.out.println(e);
		}
		return tempfile;
	}
	
	public  String[] setDetail (String fileName,String flag ) {
		
		String[] tempfile = new String[3];
		try {
			dbconn = new DAO.DAOClass().getConnection();
			
			
			 // create the java mysql update preparedstatement
		      String query = "UPDATE utility SET STATUS=? WHERE NAME= ?";
		      PreparedStatement preparedStmt = dbconn.prepareStatement(query);
		      preparedStmt.setString (1, flag);
		      preparedStmt.setString(2, fileName);

		      // execute the java preparedstatement
		      preparedStmt.executeUpdate();
			
			
			//System.out.println(getDetail());
			
		/*	Statement stmt = dbconn.createStatement();
			ResultSet rs = stmt.executeQuery("UPDATE utility SET STATUS='sent' WHERE NAME= "+fileName+"");
			while (rs.next()) {
				tempfile[0] = rs.getString("name");
				tempfile[1] = rs.getString("email");
				tempfile[2] = rs.getString("uname");
				//System.out.println("" + tempfile);
				break;
			}*/
			dbconn.close();
			
		} catch (Exception e) {
			System.out.println(e);
		}
		return tempfile;
	} 

}
