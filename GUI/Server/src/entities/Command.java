package entities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

public class Command {
	public static Response Run() 
    { 
		String test = "FAIL";
        try 
        { 
            /*Process p=Runtime.getRuntime().exec(new String[]{cd;pwd}); 
            p.waitFor(); */
        	
        	ProcessBuilder pb = new ProcessBuilder("/home/payal/workspace/CloudManagementServer/src/dependencies/BGP_Testcase.sh");
        	Process p = pb.start();
            BufferedReader reader=new BufferedReader(
                new InputStreamReader(p.getInputStream())
            ); 
            String line; 
            while((line = reader.readLine()) != null) 
            { 
                System.out.println(line);
            }
            test = "PASS";
            return Response.ok(test)
    				.header("Access-Control-Allow-Origin", "*")
    				.header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
    				.header("Access-Control-Allow-Headers", "Content-Type")
    				.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTIONS, HEAD" )
    				.allow("OPTIONS")
    				.build(); 

        }
        catch(IOException e1) { 
        	System.out.println(e1.getMessage());
        	test = "FAIL5";
        	return Response.status(400)
        			.build();
        } 
    }

}
