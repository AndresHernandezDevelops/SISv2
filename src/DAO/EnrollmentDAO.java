package DAO;
import bean.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class EnrollmentDAO {

	DataSource ds;
	//Statement stmn;
	
	public EnrollmentDAO() throws ClassNotFoundException{
		
		try{
			ds = (DataSource) (new InitialContext()).lookup("java:/comp/env/jdbc/EECS");
			//con = ds.getConnection();
			//con.createStatement();
		} catch (NamingException e){
			e.printStackTrace();
		}
	}
	
	public Map<String, EnrollmentBean> retrieveEnrollment(String namePrefix, int credit_taken) throws SQLException{
		String query = "select * from enrollment where sid in (select sid from students where surname like '%" + namePrefix + "%' and credit_taken >= " + credit_taken + ") order by CID";
		Map<String, EnrollmentBean> rv = new HashMap<String, EnrollmentBean>();
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		ResultSet r = p.executeQuery();
		String previous_CID = "";
		while(r.next())
		{

			ArrayList<String> students = new ArrayList<String>();
			String cid = r.getString("CID");
			int credit = r.getInt("CREDIT");
			String studentID = r.getString("SID");
			students.add(studentID);
			//names = r.getString("GIVENNAME") + ", " + r.getString("SURNAME");
			while(r.next())
			{
				if(r.getString("CID").equals(cid))
				{
					students.add(r.getString("SID"));
				}
				else
				{
					r.previous();
					break;
				}
			}		
			rv.put(cid, new EnrollmentBean(cid, students, credit));
		}
		r.close();
		p.close();
		con.close();
		return rv;
		
	}
}
