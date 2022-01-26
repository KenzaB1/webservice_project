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

import rest.todo.dao.OrderDao;
import rest.todo.model.OrderModel;

public class OrderResource {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	String id;

	public OrderResource(UriInfo uriInfo, Request request, String id) {
		this.uriInfo = uriInfo;
		this.request = request;
		this.id = id;
	}

	// Application integration
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public OrderModel getResto() {
		OrderModel resto = OrderDao.instance.getModel().get(id);
		if (resto == null)
			throw new RuntimeException("Get: OrderModel with " + id + " not found");
		return resto;
	}

	// for the browser
	@GET
	@Produces(MediaType.TEXT_XML)
	public OrderModel getOrderModelHTML() {
		OrderModel resto = OrderDao.instance.getModel().get(id);
		if (resto == null)
			throw new RuntimeException("Get: OrderModel with " + id + " not found");
		return resto;
	}

	@PUT
	@Consumes(MediaType.APPLICATION_XML)
	public Response putOrderModel(JAXBElement<OrderModel> OrderModel) {
		OrderModel c = OrderModel.getValue();
		return putAndGetResponse(c);
	}

	@DELETE
	public void deleteOrderModel() {
		OrderModel c = OrderDao.instance.getModel().remove(id);
		if (c == null)
			throw new RuntimeException("Delete: OrderModel with " + id + " not found");
	}

	private Response putAndGetResponse(OrderModel OrderModel) {
		Response res;
		if (OrderDao.instance.getModel().containsKey(OrderModel.getID())) {
			res = Response.noContent().build();
		} else {
			res = Response.created(uriInfo.getAbsolutePath()).build();
		}
		OrderDao.instance.getModel().put(OrderModel.getID(), OrderModel);
		return res;
	}

}