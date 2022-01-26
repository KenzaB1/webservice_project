package rest.todo.resources;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import rest.todo.model.RestoModel;
import rest.todo.dao.RestoDao;

@Path("/restos")
public class RestosResource {

	// Allows to insert contextual objects into the class,
	// e.g. ServletContext, Request, Response, UriInfo
	@Context
	UriInfo uriInfo;
	@Context
	Request request;

	// Return the list of todos to the user in the browser
	@GET
	@Produces({MediaType.APPLICATION_JSON })
	public List<RestoModel> getRestosBrowser() {
		List<RestoModel> restos = new ArrayList<RestoModel>();
		restos.addAll(RestoDao.instance.getModel().values());
		return restos;
	}

	

	// retuns the number of restos
	// to get the total number of records
	@GET
	@Path("count")
	@Produces(MediaType.TEXT_PLAIN)
	public String getCount() {
		int count = RestoDao.instance.getModel().size();
		return String.valueOf(count);
	}

	@PUT
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public void newResto(
			@FormParam("NAME") String NAME, @FormParam("ADDRESS") String ADDRESS, 
			@FormParam("TYPE") String TYPE,
			@FormParam("DESCRIPTION") String DESCRIPTION, @Context HttpServletResponse servletResponse)
			throws IOException {
		RestoModel resto = new RestoModel(NAME, ADDRESS, TYPE, DESCRIPTION);
		
		// RestoDao.instance.getModel().put(ID, resto);
		RestoDao.instance.put(resto);

		// servletResponse.sendRedirect("../create_resto.html");
	}

	// form update
	@POST
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public void updateResto(@FormParam("ID") int ID, @FormParam("NAME") String NAME,
			@FormParam("ADDRESS") String ADDRESS, @FormParam("TYPE") String TYPE,
			@FormParam("DESCRIPTION") String DESCRIPTION, @Context HttpServletResponse servletResponse)
			throws IOException {
		RestoModel resto = new RestoModel(ID, NAME, ADDRESS, TYPE, DESCRIPTION);
		
		RestoDao.instance.post(resto);

	}

	// Postman Test
	@DELETE
	@Path("/{ID}")
	@Produces(MediaType.TEXT_HTML)
	public String deleteResto(@PathParam("ID") int ID) {
		RestoDao.instance.delete(ID);
      return "The item having the ID=" + ID + " is deleted with success";
	}

	// Defines that the next path parameter after todos is
	// treated as a parameter and passed to the TodoResources
	// Allows to type http://localhost:8080/rest.todo/rest/todos/1
	// 1 will be treaded as parameter todo and passed to TodoResource
	@Path("{resto}")
	public RestoResource getResto(@PathParam("resto") String id) {
		return new RestoResource(uriInfo, request, id);
	}

}