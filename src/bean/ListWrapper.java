package bean;

import java.io.File;
import java.io.FileWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.transform.stream.StreamResult;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;


@XmlRootElement(name="sisReport")
public class ListWrapper {
	
	
	private String namePrefix;
	
	private int credit_taken;
	
	private List<StudentBean> list;
	
	public ListWrapper()
	{
		this("", 0, new LinkedList<StudentBean>());
		
//		this.namePrefix = "";
//		this.list = new LinkedList<StudentBean>();
//		this.credit_taken = 0;
	}
	
	public ListWrapper(String namePrefix, int credit_taken, List<StudentBean> list) {
		this.namePrefix = namePrefix;
		this.credit_taken = credit_taken;
		this.list = list;
	}

	@XmlAttribute
	public String getNamePrefix() {
		return namePrefix;
	}

	public void setNamePrefix(String namePrefix) {
		this.namePrefix = namePrefix;
	}

	@XmlAttribute(name="creditTaken")
	public int getCredit_taken() {
		return credit_taken;
	}

	public void setCredit_taken(int credit_taken) {
		this.credit_taken = credit_taken;
	}

	@XmlElement(name="studentList")
	public List<StudentBean> getList() {
		return list;
	}

	public void setList(List<StudentBean> list) {
		this.list = list;
	}
	
}
