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

import rest.todo.dao.PlatDao;
import rest.todo.dao.RestoDao;
import rest.todo.model.PlatModel;
import rest.todo.model.RestoModel;

public class PlatResource {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	String id;

	public PlatResource(UriInfo uriInfo, Request request, String id) {
		this.uriInfo = uriInfo;
		this.request = request;
		this.id = id;
	}

	// Application integration
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public PlatModel getResto() {
		PlatModel resto = PlatDao.instance.getModel().get(id);
		if (resto == null)
			throw new RuntimeException("Get: plat with " + id + " not found");
		return resto;
	}

	// for the browser
	@GET
	@Produces(MediaType.TEXT_XML)
	public PlatModel getplatHTML() {
		PlatModel resto = PlatDao.instance.getModel().get(id);
		if (resto == null)
			throw new RuntimeException("Get: plat with " + id + " not found");
		return resto;
	}

	@PUT
	@Consumes(MediaType.APPLICATION_XML)
	public Response putplat(JAXBElement<PlatModel> plat) {
		PlatModel c = plat.getValue();
		return putAndGetResponse(c);
	}

	@DELETE
	public void deleteplat() {
		PlatModel c = PlatDao.instance.getModel().remove(id);
		if (c == null)
			throw new RuntimeException("Delete: plat with " + id + " not found");
	}

	private Response putAndGetResponse(PlatModel plat) {
		Response res;
		if (PlatDao.instance.getModel().containsKey(plat.getID())) {
			res = Response.noContent().build();
		} else {
			res = Response.created(uriInfo.getAbsolutePath()).build();
		}
		PlatDao.instance.getModel().put(plat.getID(), plat);
		return res;
	}

}