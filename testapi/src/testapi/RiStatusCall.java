package testapi;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("/user/{id}")
public class RiStatusCall {
	@GET
	@Produces("text/json")
	public String getvin(@PathParam("id") String id) {
		return id;
	}

}
