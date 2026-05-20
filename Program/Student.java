import java.util.*;

public class Student
{
	private String firstname;
	private String lastname;
	private int studentID;
	private String citizen;
	private Details details;

	public Student() //default constructor
	{
		this.firstname = "Bhavisha";
		this.lastname = "Gaikwad";
		this.studentID = 23628812;
		this.citizen = "International";
		this.details = new Details();
	}

	public Student(String pFirstname, String pLastname, int pStudentID, String pCitizen, Details pDetail)//constructors with parameters
	{
		setFirstName(pFirstname);
		setLastName(pLastname);
		setStudentID(pStudentID);
		setCitizenship(pCitizen);
		setDetail(pDetail);
	}
	//Mutators - get the values from the main  
	public void setFirstName(String pFirstname)
	{
		if(pFirstname == null || pFirstname.isEmpty())
		{
			throw new IllegalArgumentException("Please do enter your firstname");
		}
		else
		{
			this.firstname = pFirstname;
		}
	}
	public void setLastName(String pLastname)
        {
                if(pLastname == null || pLastname.isEmpty())
                {
                        throw new IllegalArgumentException("Please do enter your lastname");
                }
                else
                {
                        this.lastname = pLastname;
                }
        }
	public void setStudentID(int pStudentID)
        {
		int Max = 99999999, Min = 10000000;
                if(pStudentID > Max || pStudentID < Min) 
                {
                        throw new IllegalArgumentException("Please do enter Student ID (8 characters)");
                }
                else
                {
                        this.studentID = pStudentID;
                }
        }
  public void setCitizenship(String pCitizen)
  {
    if(pCitizen == null || pCitizen.isEmpty() || (pCitizen.equals("International") && pCitizen.equals("Local")))
                {
                        throw new IllegalArgumentException("Please do enter your citizenship (International/Local)");
                }
                else
                {
                        this.citizen = pCitizen;
                }
  }              
	public void setDetail(Details pdetails)
	{
		this.details = pdetails;
	}
	
	public Student(Student pStudents)
	{
		this.firstname = pStudents.getFirstName();
		this.lastname = pStudents.getLastName();
		this.studentID = pStudents.getStudentID();
		this.citizen = pStudents.getCitizenship();
		this.details = new Details(pStudents.getDetail());
	}
 //Mutators - stores the data
	public String getFirstName()
	{
		return firstname;
	}
	public String getLastName()
	{
		return lastname;
	}
	public int getStudentID()
	{
		return studentID;
	}
	public String getCitizenship()
	{
	  return citizen;
	}
	public Details getDetail()
	{
		return details;
	}
	
	public String toString()
	{
		String studentDetailString;
		studentDetailString = "\nFirst Name:  " + firstname + "\nLast Name: " + lastname + "\nStudent ID: " + studentID + "\nCitizenship: " + citizen + details.toString();
		return studentDetailString;
	}

	public String toFileString()
	{
		String studentDetailString;
		studentDetailString = firstname + "," + lastname + "," + studentID + "," + citizen + "," + details.toFileString();
		return studentDetailString;
	}

	public boolean equals(Object inObj)
	{
		 boolean equal = false;
    if (inObj instanceof Student)
    {
			 Student inStudent = (Student)inObj;
			 if(this.firstname.equals(inStudent.getFirstName()))
			  if(this.lastname.equals(inStudent.getLastName()))
			    if(this.citizen.equals(inStudent.getCitizenship()))
			      if(this.studentID == inStudent.getStudentID())
				     equal = true;
		}
		return equal;
	}
	/* Title: Composition in Java Examples
	 * YouTube - Mr.Rozon
	 */
}
