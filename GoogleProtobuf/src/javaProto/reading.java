package javaProto;

import javaProto.ResultProto.CourseMarks;
import javaProto.ResultProto.Result;
import javaProto.ResultProto.Student;

import java.io.File;
import java.io.FileInputStream;	
import java.io.FileWriter;
import java.io.IOException;

public class reading {
	static FileWriter fw;
		
		static void Print(Result result) {
			for (Student student: result.getStudentList()) {
				try {
					int curr = student.getMarksList().size();
					int currPtr = 0;
					fw.write(student.getName() + "," + student.getRollNum()+":");
					  for (CourseMarks course_marks : student.getMarksList()) {
						  currPtr++;
						  if(currPtr == curr) 
							  fw.write(course_marks.getName()+","+course_marks.getScore());
						  else
							  fw.write(course_marks.getName()+","+course_marks.getScore()+":");
					      }
					  fw.write("\n");
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}

		// Main function:  Reads the entire address book from a file and prints all
		//   the information inside.

	public void readingCSV(String[] args) throws Exception {
		if (args.length != 1) {
			System.err.println("Usage:  list student FILE");
			System.exit(-1);
		}
		fw = new FileWriter("output_protobuf.txt", true);
		long startTime = System.currentTimeMillis();
		long endTime = 0;
		File protobuf = new File("result_protobuf");
		long length = protobuf.length();
		
		// Read the existing address book.
		Result result =
				Result.parseFrom(new FileInputStream(args[0]));
		Print(result);
		endTime = System.currentTimeMillis();
		fw.close();
		System.out.println("------ ProtoBuf to CSV -------------");
		System.out.println("Time taken in ms is " + (endTime - startTime));
		System.out.println("Speed of Deserialization is  " + 1.0*(endTime - startTime) / length + "Bps");
	}
}
