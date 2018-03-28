package DAO;
import bean.*;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class StudentDAO {

	DataSource ds;
	StudentBean sb;
	
	public StudentDAO() throws ClassNotFoundException{
		
		try{
			ds = (DataSource) (new InitialContext()).lookup("java:/comp/env/jdbc/EECS");
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public Map<String, StudentBean> retrieveStudent(String namePrefix, int credit_taken) throws SQLException{
		String query = String.format("select S.SID, S.GIVENNAME, S.SURNAME, S.CREDIT_TAKEN, S.CREDIT_GRADUATE, E.CREDIT_TAKING"
				+ " from STUDENTS as S, (select SID, SUM(CREDIT) as CREDIT_TAKING from ENROLLMENT group by SID) as E"
				+ " where S.SID = E.SID and S.SURNAME like '%%%s%%' and S.CREDIT_TAKEN >= %d", namePrefix, credit_taken);
		
		Map<String, StudentBean> rv = new HashMap<String, StudentBean>();
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		ResultSet r = p.executeQuery();
		while(r.next())
		{
			String name = r.getString("GIVENNAME") + ", " + r.getString("SURNAME");
			String sid = r.getString("SID");
			int min_credit = r.getInt("CREDIT_TAKEN");
			int credit_graduate = r.getInt("CREDIT_GRADUATE");
			int credit_taking = r.getInt("CREDIT_TAKING");
			rv.put(sid,new StudentBean(sid, name, min_credit, credit_graduate, credit_taking));
		}
		r.close();
		p.close();
		con.close();
		return rv;
		
	}
}
