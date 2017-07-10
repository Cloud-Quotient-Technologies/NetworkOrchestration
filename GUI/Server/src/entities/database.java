package entities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import com.google.gson.*;

public class database {
	static String JSONFilePath = "/home/payal/workspace/CloudManagementServer/src/Device_details.json";
	static String JSONVariablePath = "/home/payal/workspace/CloudManagementServer/src/dependencies/variable.json";
	 public static Response getDevices(){
		 List<Device> result = new ArrayList<Device>();
			try {
				File file = new File(JSONFilePath);
				if (!file.exists()) {
					return Response.ok("Topology file does not exist")
							.build();
					}
				else {
					System.out.println(System.getProperty("catalina.base"));
					System.out.println("File exists and reading from the file");
					FileInputStream fis = new FileInputStream(file); 
		            ObjectInputStream ois = new ObjectInputStream(fis); 
		            List<Device> readObject = (List<Device>) ois.readObject();
					result = readObject; 
		            ois.close(); 
				}
			} catch (IOException e) { 
		         e.printStackTrace(); 
		      } catch (ClassNotFoundException e) { 
		         e.printStackTrace(); 
		      }
					
			return Response.ok().entity(new GenericEntity<List<Device>>(result){})
					.header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
					.header("Access-Control-Allow-Headers", "Content-Type")
					.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTIONS, HEAD" )
					.allow("OPTIONS")
					.build(); 
	 }
	 
	 public static boolean updateVariables(BgpRequestBody BGPdata){
		 JsonObject jsonObject = new JsonObject();
		 try {
			 System.out.println("Inside update variable");
			 JsonParser parser = new JsonParser();
	         JsonElement jsonElement = parser.parse(new FileReader(JSONVariablePath));
	         jsonObject = jsonElement.getAsJsonObject();
	        } catch (FileNotFoundException e) {
	        	return false;
	        }
	     System.out.println(BGPdata );
		 jsonObject.addProperty("Network", BGPdata.IpNetw);
		 jsonObject.addProperty("number_of_instances", BGPdata.NumInst);
		 System.out.println("The jsonObject content is as displayed below");
	     System.out.println(jsonObject);
	     try (FileWriter JSONfile = new FileWriter(JSONVariablePath)) 
         {
             JSONfile.write(jsonObject.toString());
             }catch (FileNotFoundException e){
            	 return false;
            	 }
	     catch (IOException e){
	    	 return false;
	    	 }
	     return true;
	 }
}
