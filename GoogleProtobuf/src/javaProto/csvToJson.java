package javaProto;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.google.gson.Gson;

public class csvToJson {
	String readSingleLine;
	FileOutputStream output = null;
	public void convertToJson()
	{
		long startTime = System.currentTimeMillis();
		long endTime = 0;
		File csvFile = new File("sample");
		long length = csvFile.length();
		
		ArrayList<studentDetails> sd = new ArrayList<studentDetails>();

		try {
			BufferedReader br2 = new BufferedReader(new FileReader("sample"));
			
			while((readSingleLine = br2.readLine())!=null )
			{
				int iteration=0;
				String retval[] = readSingleLine.split(":");
				studentDetails st = new studentDetails();
				
				for (int i_ret =0;i_ret<retval.length;i_ret++)
				{
					String name = retval[i_ret].split(",")[0];
					int rollNo = Integer.parseInt(retval[i_ret].split(",")[1]);
					if(iteration==0)
					{
						//set student name and roll num
						st.setStudentDetails(name, rollNo);
						iteration=1;
					}
					else
					{
						//set course name and score
						//System.out.println(st.Name);
						st.setCoursesDetails(name, rollNo);
					}
				}
				sd.add(st);
			}
			Gson gson = new Gson();
			FileWriter fw2 = new FileWriter("result.json");
			fw2.write(gson.toJson(sd));
			endTime = System.currentTimeMillis();
			fw2.close();
			br2.close();
		} catch (IOException e) {
			e.printStackTrace();}
		System.out.println("------ CSV to JSON -------------");
		System.out.println("Time taken in ms is " + (endTime - startTime));
		System.out.println("Speed of serialization is  " + 1.0*(endTime - startTime) / length+ "Bps");
		
		
	}
}
