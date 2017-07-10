package rf;

import entities.*;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("device")

public class DeviceRestful {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getall")
	public Response findAll(){
		return database.getDevices();
	}
	
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/execute")
	public Response execute(BgpRequestBody BGPdata) 
    { 
		boolean result;
		System.out.println("Inside execute func");
		System.out.println(BGPdata);
		result = database.updateVariables(BGPdata);
		if (result){
			System.out.println(result);
			return Command.Run();
		}else{
			System.out.println(result);
			return Response.ok("No data received from the client")
					.header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
					.header("Access-Control-Allow-Headers", "Content-Type")
					.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTIONS, HEAD" )
					.allow("OPTIONS")
					.build(); 
		}
    }
}
