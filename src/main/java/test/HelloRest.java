package main.java.test;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("/")
public class HelloRest {

    @GET
    @Path("/{id}")
    public Response printMessage(@PathParam("id") String id){

        String res = "Good job - " +id;

        return Response.status(200).entity(res).build();
    }
}
