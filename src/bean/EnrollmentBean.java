package bean;

import java.util.ArrayList;
import java.util.Collections;

public class EnrollmentBean {

	private String cid;
	private ArrayList<String> studentIDs = new ArrayList<String>();
	private int credit;
	
	public EnrollmentBean(String cid, ArrayList<String> students, int credit)
	{
		this.cid = cid;
		Collections.copy(studentIDs, students);
		this.credit = credit;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public ArrayList<String> getStudents() {
		return studentIDs;
	}

	public void setStudents(ArrayList<String> students) {
		this.studentIDs = students;
	}

	public int getCredit() {
		return credit;
	}

	public void setCredit(int credit) {
		this.credit = credit;
	}
}
