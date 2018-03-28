package model;
import java.util.Map;

import java.io.File;
import java.io.FileWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.transform.stream.StreamResult;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import com.sun.xml.internal.txw2.annotation.XmlAttribute;
import com.sun.xml.internal.txw2.annotation.XmlElement;

import DAO.*;
import bean.*;

public class SIS {

	private StudentDAO students;
	private EnrollmentDAO enrollments;
	
	public SIS() throws ClassNotFoundException
	{
		students = new StudentDAO();
		enrollments = new EnrollmentDAO();
	}
	
	public Map<String, StudentBean> retrieveStudent(String namePrefix, String credit_taken) throws Exception
	{
		try{
			int credits = Integer.parseInt(credit_taken);
			return students.retrieveStudent(namePrefix, credits);
		}
		catch (Exception e)
		{
			throw new Exception();
		}
	}
	public void export(String namePrefix, String credit_taken, String filename, String f) throws Exception{
			int credits = Integer.parseInt(credit_taken);
			List<StudentBean> list = new ArrayList<StudentBean>();
			Collection<StudentBean> sbean = this.retrieveStudent(namePrefix, credit_taken).values();
			Iterator<StudentBean> item = sbean.iterator();
			while(item.hasNext())
			{
				list.add(item.next());
			}
			ListWrapper lw = new ListWrapper(namePrefix, credits, list);
		
			
			
			
			JAXBContext jc = JAXBContext.newInstance(lw.getClass());
			Marshaller marshaller = jc.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
			StringWriter sw = new StringWriter();
			sw.write("\n");
			
			
			//debugging the SIS.xsd
			SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Schema schema = sf.newSchema(new File(f+"/export/SIS.xsd"));
			marshaller.setSchema(schema);
			
			
			marshaller.marshal(lw, new StreamResult(sw));

			System.out.println(sw.toString()); // for debugging
			FileWriter fw = new FileWriter(filename);
			fw.write(sw.toString());
			fw.close();
	}

	
}
