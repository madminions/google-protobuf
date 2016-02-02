package javaProto;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;

public class jsonToCsv {

	public void convertJsonToCsv()throws JSONException
	{
		long startTime = System.currentTimeMillis();
		long endTime = 0;
		
		File jsonTextFile = new File("result.json");
		long length = jsonTextFile.length();
		BufferedReader br = null;
		try {
			br = new BufferedReader( new FileReader(jsonTextFile));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			FileWriter fw = new FileWriter(new File("output_json.txt"));
			String readSingleLine;

			while ((readSingleLine = br.readLine()) != null) {
				JSONArray jasonArray = new JSONArray(readSingleLine);
				for (int i = 0; i < jasonArray.length(); i++) {
					JSONObject jsonObject = (JSONObject) jasonArray.get(i);
					//writing Name and Roll Num to file 
					fw.write(jsonObject.get("Name") + ","+jsonObject.get("RollNo"));
					
					JSONArray listForCourse = jsonObject.getJSONArray("CourseMarks");
					for (int j = 0; j < listForCourse.length(); j++) {
						JSONObject listForScore = listForCourse.getJSONObject(j);
						//writing Course Name and Course Score to file
						fw.write(":"+listForScore.get("CourseName")+","+listForScore.get("CourseScore"));
					}
					fw.write("\n");
				}

			}
			endTime = System.currentTimeMillis();
			
			//fw.write(res);
			fw.close();
			br.close();

		}catch (Exception e) {
			e.printStackTrace();}
		System.out.println("------JSON to CSV -------------");
		System.out.println("Time taken in ms is " + (endTime - startTime));
		System.out.println("Speed of Deserialization is  " + 1.0*(endTime - startTime) / length+ "Bps");

	}
}
