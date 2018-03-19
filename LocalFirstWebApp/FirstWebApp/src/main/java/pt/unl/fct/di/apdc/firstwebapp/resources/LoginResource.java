package pt.unl.fct.di.apdc.firstwebapp.resources;

import java.util.logging.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.google.appengine.repackaged.com.google.gson.Gson;

import pt.unl.fct.di.apdc.firstwebapp.util.*;

@Path("/login")
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
public class LoginResource {
	
	/**
	 * A logger object.
	 */
	private static final Logger LOG = Logger.getLogger(LoginResource.class.getName());
	private final Gson g = new Gson();
	
	public LoginResource() {}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response doLogin(LoginData data) {
		 LOG.fine("Attempt to login user: " + data.username);
		 if(data.username.equals("jleitao") && data.password.equals("password")) {
			 AuthToken token = new AuthToken(data.username);
			 LOG.info("User '" + data.username + "' logged in sucessfully.");
			 return Response.ok(g.toJson(token)).build();
		 }
		 LOG.warning("Failed login attempt for username: " + data.username);
		 return Response.status(Status.FORBIDDEN).entity(g.toJson("Incorrect username or password.")).build();
	}
	
	@GET
	@Path("/{username}")
	public Response checkUsernameAvailable(@PathParam("username") String username) {
		if(!username.equals("jleitao")) {
			return Response.ok().entity(g.toJson(false)).build();
		} else {
			return Response.ok().entity(g.toJson(true)).build();
		}
	}

}
