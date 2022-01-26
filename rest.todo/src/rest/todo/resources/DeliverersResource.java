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

import rest.todo.dao.DelivererDao;
import rest.todo.model.DelivererModel;

@Path("/delivers")
public class DeliverersResource {

	// Allows to insert contextual objects into the class,
	// e.g. ServletContext, Request, Response, UriInfo
	@Context
	UriInfo uriInfo;
	@Context
	Request request;

	// Return the list of todos to the user in the browser
	@GET
	@Produces(MediaType.TEXT_XML)
	public List<DelivererModel> getTodosBrowser() {
		List<DelivererModel> restos = new ArrayList<DelivererModel>();
		restos.addAll(DelivererDao.instance.getModel().values());
		return restos;
	}

	// Return the list of todos for applications
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<DelivererModel> getTodos() {
		List<DelivererModel> restos = new ArrayList<DelivererModel>();
		restos.addAll(DelivererDao.instance.getModel().values());
		return restos;
	}

	// retuns the number of todos
	// Use http://localhost:8080/com.vogella.jersey.todo/rest/todos/count
	// to get the total number of records
	@GET
	@Path("count")
	@Produces(MediaType.TEXT_PLAIN)
	public String getCount() {
		int count = DelivererDao.instance.getModel().size();
		return String.valueOf(count);
	}

	@PUT
	@Path("/put/{NAME}/{PHONENB}")
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public void newDelivrer(
			// @FormParam("ID") int ID,
			@FormParam("NAME") String NAME, 
			@FormParam("PHONENB") String PHONENB, 
			@Context HttpServletResponse servletResponse)
			throws IOException {
		DelivererModel resto = new DelivererModel( NAME,  PHONENB);
		
		// DelivererDao.instance.getModel().put(ID, resto);
		DelivererDao.instance.put(resto);

		// servletResponse.sendRedirect("../create_resto.html");
	}

	// form update

	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public void updateResto(@FormParam("ID") int ID, @FormParam("NAME") String NAME,
			@FormParam("PHONENB") String PHONENB,  @Context HttpServletResponse servletResponse)
			throws IOException {
		DelivererModel resto = new DelivererModel(ID, NAME, PHONENB);
	

		DelivererDao.instance.post(resto);

		servletResponse.sendRedirect("../update_resto.html");
	}

	// Postman Test
	@DELETE
	@Path("/delete/{ID}")
	@Consumes(MediaType.TEXT_HTML)
	public String deleteResto(@PathParam("ID") int ID) {
		DelivererDao.instance.delete(ID);

		return "The item having the ID=" + ID + " is deleted with success";
	}

	// Defines that the next path parameter after todos is
	// treated as a parameter and passed to the TodoResources
	// Allows to type http://localhost:8080/rest.todo/rest/todos/1
	// 1 will be treaded as parameter todo and passed to TodoResource
	@Path("{deliverer}")
	public DelivererResource getTodo(@PathParam("deliverer") String id) {
		return new DelivererResource(uriInfo, request, id);
	}

}