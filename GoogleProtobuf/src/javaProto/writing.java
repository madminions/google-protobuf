package javaProto;

import javaProto.ResultProto.CourseMarks;
import javaProto.ResultProto.Result;
import javaProto.ResultProto.Student;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintStream;


class writing {
	// This function fills in a Person message based on user input.
	public static Student PromptForAddress(BufferedReader stdin,
			PrintStream stdout,String readSingleLine) throws IOException {

		int iteration = 0;
		Student.Builder student = Student.newBuilder();
		String retval[] = readSingleLine.split(":");
		for (int i_ret =0;i_ret<retval.length;i_ret++)
		{
			String name = retval[i_ret].split(",")[0];
			int rollNo = Integer.parseInt(retval[i_ret].split(",")[1]);
			if(iteration==0)
			{
				//set student name and roll num
				student.setName(name);
				student.setRollNum(rollNo);
				iteration=1;
			}
			else
			{
				//set course name and id
				//CourseMarks.Builder
				CourseMarks.Builder course_marks = CourseMarks.newBuilder();
				course_marks.setName(name);
				course_marks.setScore(rollNo);
				student.addMarks(course_marks);
			}
		}

		return student.build();
	}

	// Main function:  Reads the entire address book from a file,
	//   adds one person based on user input, then writes it back out to the same
	//   file.
	public static void main(String[] args) throws Exception {
		if (args.length != 1) {
			System.err.println("Usage:  AddStudent FILE");
			System.exit(-1);
		}


		String readSingleLine;
		FileOutputStream output = null;
		long startTime = System.currentTimeMillis();
		try {
			BufferedReader br = new BufferedReader(new FileReader("sample"));
			while((readSingleLine = br.readLine())!=null )
			{

				Result.Builder result = Result.newBuilder();

				// Read the existing address book.
				try {
					result.mergeFrom(new FileInputStream(args[0]));
				} catch (FileNotFoundException e) {
					System.out.println(args[0] + ": File not found.  Creating a new file.");
				}

				// Add an address.
				result.addStudent(
						PromptForAddress(new BufferedReader(new InputStreamReader(System.in)),
								System.out, readSingleLine));

				// Write the new address book back to disk.
				output = new FileOutputStream(args[0]);
				result.build().writeTo(output);
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		long endTime = System.currentTimeMillis();
		File csvFile = new File("sample");
		long length = csvFile.length();
		System.out.println("------ CSV to Protobuf -------------");
		System.out.println("Time taken in ms is " + (endTime - startTime));
		System.out.println("Speed of Serialization is  " + 1.0*(endTime - startTime) / length + "Bps");

		output.close();
		


		/*------------Reading----------------------------*/
		reading readvar = new reading();
		readvar.readingCSV(args);
		/*------------Reading----------------------------*/

		/*------------csvToJson----------------------------*/
		csvToJson obj = new csvToJson();
		try{
			obj.convertToJson();
		}catch(Exception e){
			e.printStackTrace();
		}
	/*------------csvToJson----------------------------*/
	
	/*------------Json to CSV----------------------------*/
	try{
		jsonToCsv obj1 = new jsonToCsv();
		obj1.convertJsonToCsv();
	}catch(Exception e){
		e.printStackTrace();
	}
}
/*------------Json to CSV----------------------------*/


}