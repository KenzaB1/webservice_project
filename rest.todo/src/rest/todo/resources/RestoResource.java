package rest.todo.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.JAXBElement;

import rest.todo.model.RestoModel;
import rest.todo.dao.RestoDao;
import rest.todo.dao.TodoDao;
import rest.todo.model.Todo;

public class RestoResource {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	String id;

	public RestoResource(UriInfo uriInfo, Request request, String id) {
		this.uriInfo = uriInfo;
		this.request = request;
		this.id = id;
	}

	// Application integration
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public RestoModel getResto() {
		RestoModel resto = RestoDao.instance.getModel().get(id);
		if (resto == null)
			throw new RuntimeException("Get: Todo with " + id + " not found");
		return resto;
	}

	// for the browser
	@GET
	@Produces(MediaType.TEXT_XML)
	public RestoModel getTodoHTML() {
		RestoModel resto = RestoDao.instance.getModel().get(id);
		if (resto == null)
			throw new RuntimeException("Get: Todo with " + id + " not found");
		return resto;
	}

	@PUT
	@Consumes(MediaType.APPLICATION_XML)
	public Response putTodo(JAXBElement<RestoModel> todo) {
		RestoModel c = todo.getValue();
		return putAndGetResponse(c);
	}

	@DELETE
	public void deleteTodo() {
		Todo c = TodoDao.instance.getModel().remove(id);
		if (c == null)
			throw new RuntimeException("Delete: Todo with " + id + " not found");
	}

	private Response putAndGetResponse(RestoModel resto) {
		Response res;
		if (RestoDao.instance.getModel().containsKey(resto.getID())) {
			res = Response.noContent().build();
		} else {
			res = Response.created(uriInfo.getAbsolutePath()).build();
		}
		RestoDao.instance.getModel().put(resto.getID(), resto);
		return res;
	}

}