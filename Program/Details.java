import java.util.*;
public class Details
{
	private String courseEnrolled; //Software eng
	private int yearLevel; // between 1 to 4
	private double cwa; // between 0 to 100
	private String status; // FT - FullTime / PT - PartTime
	private int creditsEarned; // max 400
	
	public Details() //Default Constructor
	{
		this.courseEnrolled = "Unknown";
		this.yearLevel = 1;
		this.cwa = 0.0;
		this.status = "FT";
		this.creditsEarned = 0;
	}

	public Details(String pCourse, int pYear, double pCwa, String pStatus, int pCredits) // Constructor with parameters
	{
		setCourseEnrolled(pCourse);
		setYearLevel(pYear);
		setCwa(pCwa);
		setStatus(pStatus);
		setCreditsEarned(pCredits);
	}
	// Mutators - get the value from the main and assigns
		public void setCourseEnrolled(String pCourse)
		{
			if (pCourse == null || pCourse.isEmpty())
			{
				throw new IllegalArgumentException("Please do enter something");
			}
			else
			{
				this.courseEnrolled = pCourse;
			}
		}
		public void setYearLevel(int pYear)
		{
			if (pYear < 1 || pYear > 4)
			{
				throw new IllegalArgumentException("The Year Level should be between 1 to 4");
			}
			else
			{
				this.yearLevel = pYear;
			}
		}
		public void setCwa(double pCwa)
		{
			if (pCwa <0.0 || pCwa >100.0)
			{
				throw new IllegalArgumentException("CWA is expected to be withing 0 - 100");
			}	
			else 
			{
				this.cwa = pCwa;
			}
		}
		public void setStatus(String pStatus)
		{
			if (pStatus == null ||  (pStatus.equals("FT") && pStatus.equals("PT")))
			{
				throw new IllegalArgumentException("The Status can be either FT(Full Time) or PT(Part Time)");
			}
			else
			{
				this.status = pStatus;
			}
		}
		public void setCreditsEarned(int pCredits)
		{
			if (pCredits < 0 || pCredits > 600)
			{
				throw new IllegalArgumentException("The credits earned are expected to be within 0 - 400");
			}
			else 
			{
				this.creditsEarned = pCredits;
			}
		}

		public Details(Details pDetails) //copies the imported values
        	{
        	        this.courseEnrolled = pDetails.getCourseEnrolled();
        	        this.yearLevel = pDetails.getYearLevel();
                	this.cwa = pDetails.getCwa();
                	this.status = pDetails.getStatus();
                	this.creditsEarned = pDetails.getCreditsEarned();
		}
		// Accessors - saves the values
		public String getCourseEnrolled()
		{
			return courseEnrolled;
		}
		public int getYearLevel()
		{
			return yearLevel;
		}
		public double getCwa()
		{
			return cwa;
		}
		public String getStatus()
		{
			return status;
		}
		public int getCreditsEarned()
		{
			return creditsEarned;
		}
		
		public String toString() //string shows on the terminal
		{
			String detailString;
			detailString = "\nCourse Enrolled: " + courseEnrolled + "\nYear Level: " + yearLevel + "\nCWA: " + cwa + "\nStatus: " + status + "\nCredits Earned: " + creditsEarned;
			return detailString;
		}
		
		public String toFileString() //string that shows in csv file
		{
			String detailString;
			detailString = courseEnrolled + "," + yearLevel + "," + cwa + "," + status + "," + creditsEarned;
			return detailString;

		}

		public boolean equals(Object inObj) //checks similarity
		{
			boolean equal = false;
			if (inObj instanceof Details)
			{
				Details inDetails = (Details)inObj;
				if(this.courseEnrolled.equals(inDetails.getCourseEnrolled()))
					if(this.yearLevel == inDetails.getYearLevel())
						if(this.cwa == inDetails.getCwa())
							if(this.status.equals(inDetails.getStatus()))
									if(this.creditsEarned == inDetails.getCreditsEarned())
									equal = true;
			}
			return equal;
	  }
	}
		
		/* 
		 * Title: Object class equals toString Hashcode
		 * YouTube Video - Telusko
		 */

