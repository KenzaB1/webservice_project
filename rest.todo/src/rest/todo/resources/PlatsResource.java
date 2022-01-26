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

import rest.todo.dao.PlatDao;
import rest.todo.dao.RestoDao;
import rest.todo.model.PlatModel;
import rest.todo.model.RestoModel;

/// Will map the resource to the URL todos
@Path("/plats")
public class PlatsResource {

	// Allows to insert contextual objects into the class,
	// e.g. ServletContext, Request, Response, UriInfo
	@Context
	UriInfo uriInfo;
	@Context
	Request request;

	// Return the list of todos to the user in the browser
	@GET
	@Produces(MediaType.TEXT_XML)
	public List<PlatModel> getTodosBrowser() {
		List<PlatModel> restos = new ArrayList<PlatModel>();
		restos.addAll(PlatDao.instance.getModel().values());
		return restos;
	}

	// Return the list of todos for applications
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<PlatModel> getTodos() {
		List<PlatModel> plats = new ArrayList<PlatModel>();
		plats.addAll(PlatDao.instance.getModel().values());
		return plats;
	}

	// retuns the number of todos
	// Use http://localhost:8080/com.vogella.jersey.todo/rest/todos/count
	// to get the total number of records
	@GET
	@Path("count")
	@Produces(MediaType.TEXT_PLAIN)
	public String getCount() {
		int count = PlatDao.instance.getModel().size();
		return String.valueOf(count);
	}

	@PUT
	@Path("/put/{NAME}/{PRICE}")
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public void newResto(
			// @FormParam("ID") int ID,
			@FormParam("NAME") String NAME, @FormParam("PRICE") double PRICE,
			@Context HttpServletResponse servletResponse) throws IOException {
		PlatModel plat = new PlatModel(NAME, PRICE);

		PlatDao.instance.put(plat);

		// servletResponse.sendRedirect("../create_resto.html");
	}

	// form update

	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public void updatePlat(@FormParam("ID") int ID, @FormParam("NAME") String NAME, @FormParam("PRICE") double PRICE,
			@Context HttpServletResponse servletResponse) throws IOException {
		PlatModel plat = new PlatModel(ID, NAME, PRICE);

		PlatDao.instance.post(plat);

		servletResponse.sendRedirect("../update_resto.html");
	}

	// Postman Test
	@DELETE
	@Path("/delete/{ID}")
	@Consumes(MediaType.TEXT_HTML)
	public String deleteResto(@PathParam("ID") int ID) {
		PlatDao.instance.delete(ID);

		return "The item having the ID=" + ID + " is deleted with success";
	}

	// Defines that the next path parameter after todos is
	// treated as a parameter and passed to the TodoResources
	// Allows to type http://localhost:8080/rest.todo/rest/todos/1
	// 1 will be treaded as parameter todo and passed to TodoResource
	@Path("{plat}")
	public PlatResource getTodo(@PathParam("plat") String id) {
		return new PlatResource(uriInfo, request, id);
	}

}