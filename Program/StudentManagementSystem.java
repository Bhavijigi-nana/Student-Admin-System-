import java.util.*;
import java.io.*;

public class StudentManagementSystem
{
	private static int  choice;
	private static Scanner sc = new Scanner(System.in);
	private final static int Max_stud = 1000; //Max Students in csv
	private static Student[] studentArr = new Student[Max_stud]; //max for students in the csv file
	private static int Count = 0;
	private static final String filename = "data.csv";
  
	public static void main(String[]args)
	{
		getStudentData(filename);
		MainMenu();
	}
		
	// need to add more later
	public static void MainMenu()	
	{
		do
		{
			Menu();
			choice = getInputInt("Option: ", 1,7);
		//	sc.nextLine();
			switch(choice)
			{
				case 1:
					addNewStudent();
					break;
				case 2: 
					editStudentInfo();
					break;
				case 3:
					viewStudents();
					break;
				case 4:
					highestCWA();
					break;
				case 5:
					avgCWA();
					break;
				case 6:
					creditAnalysis();
					break;
				case 7: 
					saveStudentData(filename);
					System.out.println("Data has been saved. Thank you.");
					System.exit(0);
					break;
				default: 
					System.out.println("Please enter a number between 1 - 7 only.");
					break;
			}
		}while (choice !=7);
		sc.close();
	}
	/* Title: Terminating a Java Program
	 * Stack Overflow
	 * Title: Java Array of Objects
	 * YouTube - BroCode
	 */

	public static void Menu()
	{
		System.out.print( "\n_____________________________________________" + 
				"\n|                                           |"+
				"\n|          STUDENT MANAGEMENT SYSTEM        |" +
				"\n|                                           |"+
				"\n|___________________________________________|");
		System.out.print("\n");
   
		System.out.println(
				"\n1. Add New Student" + 
				"\n2. Edit Student Details" +
				"\n3. View Students." +
				"\n4. Highest CWA" +//highest cwa in each course
				"\n5. Average CWA" +//calculate the average of cwa in each course
				"\n6. Credit Analysis"+ //who is able to graduate 
				"\n7. Exit the program");
	}

	public static void addNewStudent()
	{
		System.out.print("\n_____________________________________________" +
                                "\n|                                           |"+
                                "\n|            ADDING NEW STUDENT             |" +
                                "\n|                                           |"+
                                "\n|___________________________________________|");
		System.out.println("\n");
		
		boolean full = true;
		while(full)
		{
			if(Count >= Max_stud)
			{
				System.out.println("Student Data Is FULL");
				full = false;
				MainMenu();
			}

			String firstname = getInputString("First Name: ");
			String lastname = getInputString("Last Name: ");
			int studentID = getInputInt("Enter Student ID (8 digits long): ", 10000000, 99999999);
			String citizenship = getInputCitizenship("Are you Local/International?(Enter 1 - Local/2 - International): ");
			String course = getInputString("Course Enrolled In: ");
			int year = getInputInt("Year Level: ", 1 , 4);
			double cwa  = getInputDouble("CWA: ", 0.0, 100.0);
			String status = getInputStatus("What is your status? Enter 1 or 2 (1 - FT/ 2 - PT): ");
			int credits = getInputInt("Credits Earned: ", 0,600);
			
			Details addedDetails = new Details(course, year, cwa, status,credits);
			Student addedStudent = new Student(firstname, lastname, studentID, citizenship, addedDetails);
			
			studentArr[Count] = addedStudent;
			Count++;
			System.out.println(addedStudent.toString());
			
			full = getInputBoolean("\nDo you want to add another student?");
		}
	}
	
	public static void editStudentInfo()
	{
		
		System.out.print("\n_____________________________________________" +
                               	 "\n|                                           |"+
                               	 "\n|            EDITING A STUDENT              |" +
                               	 "\n|                                           |"+
                               	 "\n|___________________________________________|");
                System.out.println("\n");
		if (Count == 0)
		{
			System.out.println("There is no student data for editing");
			MainMenu();
		}
		int findID = getInputInt("Enter the Student ID to edit: ",10000000,99999999);
		int stud_no=-1;
		boolean idCheck = false;
		for (int i=0; i < Count; i++) //goes though the array to find studentID
		{
			if(studentArr[i].getStudentID() == findID)
			{
				stud_no = i;
				idCheck = true;
				break;
			}
		}
		if (idCheck==false)
		{
			System.out.print("There is no such existing Student ID to edit.");
			MainMenu();
		}
		
		Student editStudent = studentArr[stud_no];
		Details editDetail = editStudent.getDetail();
		System.out.println("This is the student's current details: \n" + editStudent.toString());
		System.out.println();
		
		int option;
		do
		{
			System.out.println("=====================================" + 
					"\n Which detail would you like to edit?" +
					"\n>1 Change the Course Enrolled." +
					"\n>2 Change the Year Level" + 
					"\n>3 Change Course Weighted Average" + 
					"\n>4 Change Status" + 
					"\n>5 Change Credits Earned"+
					"\n>6 Exit" + "\n");
			option = getInputInt("Option: ", 1,6);
			switch(option)
			{
				case 1:
					System.out.println("Your current Course Enrolled: "+ editDetail.getCourseEnrolled());
					String courseEnrolled = getInputString("Enter new Course Enrolled: ");
					editDetail.setCourseEnrolled(courseEnrolled);
					break;
				case 2:
					System.out.println("Your current Year Level: "+ editDetail.getYearLevel());
					int yearLevel = getInputInt("Enter new Year Level: ", 1, 4);
					editDetail.setYearLevel(yearLevel);
					break;
				case 3:
					System.out.println("Your current CWA: "+ editDetail.getCwa());
					double cwa = getInputDouble("Enter new CWA: ", 0.0, 100.0);
					editDetail.setCwa(cwa);
					break;
				case 4:
					System.out.println("Your current Status: "+ editDetail.getStatus());
					String status = getInputStatus("Enter new Status (1 - Full Time/2 - Part Time): ");
					editDetail.setStatus(status);
					break;
				case 5:
					System.out.println("Your current Credits Earned: "+ editDetail.getCreditsEarned());
					int credits = getInputInt("Enter new Credits Earned: ", 0, 600);
					editDetail.setCreditsEarned(credits);
					break;
				case 6:
					break;
				default: 
				  System.out.println("Please enter only numbers from 1-6");
				  break;
			}
		}while (option != 6);
	}
	  
	public static void viewStudents()
	{
		int viewOption;
		do
		{
			System.out.print("\n_____________________________________________" +
					 "\n|                                           |"+
					 "\n|            VIEWING STUDENTS               |" +
					 "\n|                                           |"+
					 "\n|___________________________________________|");
			System.out.println("\n");
			
			if (Count == 0)
			{
				System.out.println("There is no student data in the file");
				return;
			}
		
			System.out.println("Choose what kind of view would you like to see?" + 
	                    "\n 1> All StudentDetails," + "\n 2> Filter by course" +
			    "\n 3> Filter by status" + "\n 4> Filter by Citizenship" + "\n 5> Exit View");
					
	
			viewOption = getInputInt("Option: ",1,5);
			switch(viewOption)
			{
				case 1:
					viewAllStudents();
					break;
				case 2:
					filterCourse();
					break;
				case 3:
					filterStatus();
					break;
				case 4:
					filterCitizenship();
					break;
				case 5:
					MainMenu();
					break;
				default:
					System.out.println("Please do enter only digits 1 - 5.");
					break;
			}
		}while (viewOption != 5);
	}
  
  public static void viewAllStudents()
  {
	  System.out.print("\n_____________________________________________" + 
			   "\n|               View All Students             |" +
			   "\n|_____________________________________________|");
	  int studentTotal;
	  for (int i =0; i<Count; i++)
	  {
		  System.out.print("\nStudent "+ (i+1)+ ": " + studentArr[i].toString());
	  }
  }
  
  public static void filterCourse()
  {
	  System.out.print("\n_____________________________________________" +
                           "\n|            View Students By Course          |" +
                           "\n|_____________________________________________|");
     System.out.print("\n");                      

	  String fCourse = getInputString("\nEnter the course that you want to filter: ");
	  System.out.println("These are the students that enrolled in " + fCourse);
	  System.out.println("\n");
	  boolean flag = false;
	  for(int i=0; i<Count; i++)
	  {
		  if (studentArr[i].getDetail().getCourseEnrolled().equalsIgnoreCase(fCourse))
		  {

			  System.out.println(studentArr[i].toString());
			  flag = true;
		  }
	  }
	  if (flag == false)
	  {
		  System.out.println("There are no students enrolled in this course.");
	  }
	  
  }         
  
  public static void filterStatus()
  {
	   System.out.print("\n_____________________________________________" +
                           "\n|            View Students By Status          |" +
                           "\n|_____________________________________________|");
      System.out.print("\n");

	  String fStatus = getInputStatus("\nWhich status would you like to filter? ( 1 - FT/2 - PT): ");
	  System.out.println("These are the students with status " + fStatus);
	  System.out.println("\n");
	  boolean flag = false;
	  for (int i=0; i<Count; i++)
	  {
		  if (studentArr[i].getDetail().getStatus().equals(fStatus))
		  {
			  System.out.println(studentArr[i].toString());
			  flag = true;
		  }
	  }
	  if (flag == false)
	  {
		  System.out.println("There are no students of that status");
		  return;
	  }
  }       
  public static void filterCitizenship()
  {
	   System.out.print("\n_____________________________________________" +
                           "\n|         View Students By Citizenship        |" +
                           "\n|_____________________________________________|");
    System.out.print("\n");

	  String fCitizen = getInputCitizenship("\nWhich citizenship would you like to filter? ( 1 - Local/2 - International): ");
	  System.out.println("These are the students who are " + fCitizen);
	  System.out.println("\n");
	  boolean flag = false;
	  for (int i=0; i<Count; i++)
	  {
		  if (studentArr[i].getCitizenship().equals(fCitizen))
		  {
			  System.out.println(studentArr[i].toString());
			  flag = true;
		  }
	  }
	  if (flag == false)
	  {
		  System.out.println("There are no students of that citizenship");
		  return;
	  }
  }       
  
  public static void avgCWA()
  {
	  System.out.print("\n_____________________________________________" +
                         "\n|                                           |"+
                         "\n|      AVERAGE CWA FOR EACH A COURSE        |" +
                         "\n|                                           |" +
			 "\n|___________________________________________|");

	  System.out.println("\n");
	  if (Count == 0)
	  {
		  System.out.println("There is no student data in the file");
		  return;
	  }   
	  String courseCWA = getInputString("Enter the Course to calculate its Average CWA: ");
	  double[] matchCwa = new double[Max_stud];
	  int cwaCount = 0;
	  for (int i=0; i<Count; i++)
	  {
		  //Student currentStudent = studentArr[i];
		  if (studentArr[i].getDetail().getCourseEnrolled().equalsIgnoreCase(courseCWA.trim()))
		  {
			  matchCwa[cwaCount]= studentArr[i].getDetail().getCwa();
			  cwaCount++;      
		  }
	  }
	  if (cwaCount> 0 )
	  {
		  double sum = 0.0;
		  for (int i=0; i<cwaCount; i++)
		  {
			  sum = sum + matchCwa[i];
		  }
		  double avg = sum/cwaCount;
		  System.out.println("The Average CWA of " + courseCWA + " is " + avg);
	  }
	  else
	  {
		  System.out.println("There are no students under that course.");
	  }
  }
	
	public static void highestCWA()
	{
		if (Count == 0)
		{
			System.out.println("There is no student data");
			MainMenu();
		}
		
		System.out.print(  "\n_____________________________________________" +
                                  "\n|                                           |"+
                                  "\n|                HIGHEST CWA                |" +
                                  "\n|                                           |"+
                                  "\n|___________________________________________|");
		System.out.println("\n");
		
		double maxCWA = 0.0;
		for(int i = 0; i <Count; i++)
		{
			double currentCwa = studentArr[i].getDetail().getCwa();
			if (currentCwa > maxCWA)
			{
				maxCWA = currentCwa;
			}
		}
		System.out.print("The highest CWA among all the student is " + maxCWA);
		System.out.println();
		System.out.print("Students who achieved the Highest CWA" );
		System.out.print("\n");
		for (int i = 0; i<Count;i++)
		{
		
			if (studentArr[i].getDetail().getCwa() == maxCWA)
			{
				System.out.println("Name: " + studentArr[i].getFirstName() + " " + studentArr[i].getLastName());
				System.out.println("Student ID: " + studentArr[i].getStudentID());
				System.out.println();
			}
		}
	}
	
	  
	public static void creditAnalysis()
	{
		System.out.print(  "\n_____________________________________________" +
                                  "\n|                                           |"+
                                  "\n|             CREDIT ANALYSIS               |" +
                                  "\n|                                           |"+
                                  "\n|___________________________________________|");
	  System.out.println("\n"); 
	  
	  if (Count == 0)
	  {
		  System.out.println("There is no student data in the file");
		  return;
	  }
	  else 
	  {
		  System.out.println("Students who achieved 400+ Credits can graduate");
		  boolean flag = false;
		  for(int i =0; i<Count; i++)
		  {
			  if(studentArr[i].getDetail().getCreditsEarned() >= 400)
			  {
				  System.out.println(studentArr[i].toString());
				  flag=true;
			  }
		  }
		  
		  if (flag==false)
		  {
			  System.out.println("None of the students can graduate");
		  }
	  }
	}

	public static void getStudentData(String filename)
	{
		  FileInputStream fileStream = null;
		  InputStreamReader isr;
		  BufferedReader bufReader;
		  Count=0;
		  String line;   

		  try
		  {
			  fileStream = new FileInputStream(filename);
			  isr = new InputStreamReader(fileStream);
			  bufReader = new BufferedReader(isr);
			  bufReader.readLine();
			  line = bufReader.readLine();
			  
			  while (line != null && Count<Max_stud)
			  {
				  String[] parts = line.split(",");
				  
				  if (parts.length == 9)
				  {
					  
					  String firstname = parts[0].trim();
					  String lastname = parts[1].trim();
					  int studentID = Integer.parseInt(parts[2].trim());
					  String citizenship = parts[3].trim();
					  String courseEnrolled = parts[4].trim();
					  int yearLevel = Integer.parseInt(parts[5].trim());
					  double cwa = Double.parseDouble(parts[6].trim());
					  String status = parts[7].trim();
					  int credits = Integer.parseInt(parts[8].trim());
					  
					  Details getdetail = new Details(courseEnrolled, yearLevel, cwa, status, credits);
					  Student getstudents = new Student(firstname, lastname, studentID, citizenship, getdetail);
					  studentArr[Count] = getstudents;
					  Count++;
				  }
				  else
				  {
					  System.out.println("Invalid format.");
				  }
				  line = bufReader.readLine();
			  }
		  }
		  catch (FileNotFoundException e)
		  {
			  System.out.println("File not found: " + filename);
		  }
		  catch (IOException e)
		  {
			  System.out.println("An error occured when reading from the file");
		  }
	}

	public static void saveStudentData(String filename)
	{
		FileOutputStream fileStrm = null;
		PrintWriter pw;
		try
		{
			fileStrm = new FileOutputStream(filename);
			pw = new PrintWriter(fileStrm);
			pw.println("FirstName,LastName,StudentID,Citizenship,CourseEnrolled,YearLevel,CWA,Status,CreditsEarned");
			for (int i=0; i<Count; i++)
			{
				pw.println(studentArr[i].toFileString());
			}
			//System.out.println("Data has been saved");
			pw.close();
		}
		catch (IOException e)
		{
			System.out.println("An error occurred while writing to file");
		}
	}

	private static int getInputInt(String statement, int min, int max)
	{
		while(true)
		{
			System.out.print(statement);
			String input = sc.nextLine().trim();

			try
			{
				int num = Integer.parseInt(input);
				if(num >= min && num <= max)
				{
					return num;
				}
				else 
				{
					System.out.println("Please enter a number " + min + " and " + max);
				}
			}
			catch (IllegalArgumentException e)
			{
				System.out.println("Invalid input. Please enter a number.");
			}
		}
	}

	private static String getInputString(String statement)
	{
		while(true)
		{
			System.out.print(statement);
			String input = sc.nextLine().trim();
			
			if(input.trim().isEmpty())
			{
				System.out.println("Please re-enter");
			}
	 		else
	 		{
	 			return input.trim();
	 		}
		}
	}

	private static double getInputDouble(String statement, double min, double max)
	{
		while(true)
		{
			System.out.print(statement);
			String input = sc.nextLine().trim();

			try
			{
				double num = Double.parseDouble(input);
				if(num >= min && num <= max)
				{
					return num;
				}
				else 
				{
					System.out.println("Please enter a number " + min + " and " + max);
				}
			}
			catch (IllegalArgumentException e)
			{
				System.out.println("Invalid input. Please enter a number.");
			}
		}
	}
	private static String getInputStatus(String statement)
	{
		int choice = getInputInt(statement,1,2);
		if (choice==1)
		{
			return "FT";
		}
		else
		{
			return "PT";
		}
	}
	private static String getInputCitizenship(String statement)
	{
	       	int cchoice = getInputInt(statement,1,2);
		if (cchoice == 1)
		{
			return "Local";
		}
		else
		{
			return "International";
		}
	}
	private static boolean getInputBoolean(String statement)
	{	  
	 	while(true)
	 	{
	 		System.out.print(statement);
	 		char choice=sc.nextLine().charAt(0);
		 	if (choice == 'y' || choice == 'Y')
	 		{
        			return true;
			}	
			else if (choice == 'n' || choice == 'N')
			{
				return false;
			}
			else
			{
				System.out.println("Please enter either Y or N.");
			}
		}	
	}
}

/* Data for the csv file was generated by Mockaroo*/
