package javaProto;

import java.util.ArrayList;

public class studentDetails {
	String Name;
	ArrayList<courseDetails> CourseMarks = new ArrayList<courseDetails>();
	int RollNo;
	public void setStudentDetails(String name,int rollNo)
	{
		this.Name = name;
		this.RollNo = rollNo;
	}
	public void setCoursesDetails(String name,int score)
	{
		courseDetails cd = new courseDetails(name,score);
		this.CourseMarks.add(cd);
	}
	public String getName()
	{
		return this.Name;
	}
	public int getRollNo()
	{
		return this.RollNo;
	}

}
class courseDetails{
	int CourseScore;
	String CourseName;
	courseDetails(String name,int score)
	{
		this.CourseName = name;
		this.CourseScore = score;
	}

}