import org.json.simple.*;
import org.json.simple.parser.*;
import java.util.*;
import java.io.*;
import java.time.LocalDate;
public class AttendanceModel {
	private ArrayList<String> students;
	private ArrayList<String> idnumbers;
	private ArrayList<String> classes;
	
	public void readDataOf(String c) {
		students = new ArrayList<String>();
		idnumbers = new ArrayList<String>();
		JSONParser parser = new JSONParser();
		try {
			Object obj = parser.parse(new FileReader("./data/students.json"));
			JSONObject jsonObj = (JSONObject)obj;
			JSONArray arr = (JSONArray)jsonObj.get("students");
			for(int i=0;i<arr.size();i++) {
				JSONObject jobj =(JSONObject) arr.get(i);
				String name = (String)jobj.get("name");
				String idno = (String)jobj.get("id");
				String cls = (String)jobj.get("class");
				System.out.println(i+" "+cls);
				if(cls.equals(c)) {
					System.out.println(idno+" "+name);
					students.add(name);
					idnumbers.add(idno);
				}
			}

		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public ArrayList<String> getStudents() {
		return students;
	}
	public ArrayList<String> getIdNumbers() {
		return idnumbers;
	}
	public boolean evaluate(String user,String pwd) {
		if(user.equals("sujoy") && pwd.equals("rgukt123"))
			return true;
		return false;
	}
	@SuppressWarnings("unchecked")
	public void writeTodaysAttendance(ArrayList<String> present,String fname, String cls) {
		String date = LocalDate.now().toString();
		try {
			FileWriter fw = new FileWriter("./data/attendance.json",true);
			JSONArray ids = new JSONArray();
			for(int i=0;i<present.size();i++) {
				ids.add(present.get(i));
			}
			JSONObject obj = new JSONObject();
			obj.put("date",date);
			obj.put("fname", fname);
			obj.put("class", cls);
			obj.put("id",ids);
			fw.write(obj.toJSONString()+"\n");
			System.out.println(obj.toString());
			fw.flush();
			fw.close();
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public boolean isSubmitted(String fname, String cls) {
		String today = LocalDate.now().toString();
		JSONParser parser = new JSONParser();
		boolean flag=false;
		try {
			BufferedReader br = new BufferedReader(new FileReader("./data/attendance.json"));
			String line;
			while((line = br.readLine())!=null ) {
				JSONObject jsonObj =(JSONObject) parser.parse(line);
				String date = jsonObj.get("date").toString();
				String name = jsonObj.get("fname").toString();
				String cl = jsonObj.get("class").toString();
				if(today.equals(date) && fname.equals(name) && cls.equals(cl)) {
					flag=true;break;
				}
			}
			br.close();
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return flag;
	}
	public ArrayList<String> getClassesOf(String fname) {
		JSONParser parser = new JSONParser();
		try {
			Object obj = parser.parse(new FileReader("./data/faculty.json"));
			JSONObject jsonObj = (JSONObject)obj;
			JSONArray arr = (JSONArray)jsonObj.get("faculty");
			for(int i=0;i<arr.size();i++) {
				JSONObject jObj = (JSONObject) arr.get(i);
				String name = jObj.get("name").toString();
				if(name.equals(fname)) {
					classes = new ArrayList<String>();
					JSONArray cArr = (JSONArray)jObj.get("classes");
					for(int j=0;j<cArr.size();j++) {
						//System.out.println(cArr.get(j).toString());
						classes.add(cArr.get(j).toString());
					}
				}
			}
			return classes;
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return classes;
	}
}