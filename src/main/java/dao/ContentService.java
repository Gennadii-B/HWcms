package main.java.dao;


import main.java.entity.EnContent;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

@Path("/")
public class ContentService extends ConnectDS {

    @Context
    private HttpHeaders requestHeaders;

    private String getHeaderVersion() {
        return requestHeaders.getRequestHeader("version").get(0);
    }

    @GET
    @Path("/{id}")
    public Response printMessage(@PathParam("id") String id){

        EnContent ec = ContentServiceDAO.getContent(id);
        if(ec != null) {
            System.out.println("Good!!!");
            String res = "<h2>" + ec.getTitle() + "<h2>"
                    + ec.getContent();
            System.out.println(res);
            return Response.status(200).entity(res).build();

//            return success(getHeaderVersion(), ec);

        }else {
            System.out.println("not Good");
            return Response.status(404).build();
        }
    }

//    private static Response success(String version, Object object) {
//        Response.ResponseBuilder response = Response.ok();
//        response.header("version", version);
//        if (object != null) {
//            response.entity(object);
//        } else {
//            response.entity("none");
//        }
//        return response.build();
//    }
}
