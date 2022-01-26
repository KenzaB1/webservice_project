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

import rest.todo.dao.OrderDao;
import rest.todo.model.OrderModel;

/// Will map the resource to the URL todos
@Path("/orders")
public class OrdersResource {

	// Allows to insert contextual objects into the class,
	// e.g. ServletContext, Request, Response, UriInfo
	@Context
	UriInfo uriInfo;
	@Context
	Request request;

	// Return the list of todos to the user in the browser
	@GET
	@Produces(MediaType.TEXT_XML)
	public List<OrderModel> getTodosBrowser() {
		List<OrderModel> orders = new ArrayList<OrderModel>();
		orders.addAll(OrderDao.instance.getModel().values());
		return orders;
	}

	// Return the list of todos for applications
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<OrderModel> getTodos() {
		List<OrderModel> orders = new ArrayList<OrderModel>();
		orders.addAll(OrderDao.instance.getModel().values());
		return orders;
	}

	// retuns the number of todos
	// Use http://localhost:8080/com.vogella.jersey.todo/rest/todos/count
	// to get the total number of records
	@GET
	@Path("count")
	@Produces(MediaType.TEXT_PLAIN)
	public String getCount() {
		int count = OrderDao.instance.getModel().size();
		return String.valueOf(count);
	}

	@PUT
	@Path("/put/{CUSTOMERID}/{DELIVERERID}/{TOTALPRICE}")
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public void newOrder(
			// @FormParam("ID") int ID,
			@FormParam("CUSTOMERID") String CUSTOMERID, @FormParam("DELIVERERID") String DELIVERERID,
			@FormParam("TOTALPRICE") double TOTALPRICE, @Context HttpServletResponse servletResponse)
			throws IOException {
		OrderModel order = new OrderModel(CUSTOMERID, DELIVERERID, TOTALPRICE);

		OrderDao.instance.put(order);

		// servletResponse.sendRedirect("../create_order.html");
	}

	// form update

	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public void updateorder(@FormParam("ID") int ID, @FormParam("CUSTOMERID") String CUSTOMERID,
			@FormParam("DELIVERERID") String DELIVERERID, @FormParam("TOTALPRICE") double TOTALPRICE,
			@Context HttpServletResponse servletResponse) throws IOException {
		OrderModel order = new OrderModel(ID, CUSTOMERID, DELIVERERID, TOTALPRICE);

		OrderDao.instance.post(order);

		servletResponse.sendRedirect("../update_order.html");
	}

	// Postman Test
	@DELETE
	@Path("/delete/{ID}")
	@Consumes(MediaType.TEXT_HTML)
	public String deleteOrder(@PathParam("ID") int ID) {
		OrderDao.instance.delete(ID);

		return "The item having the ID=" + ID + " is deleted with success";
	}

	// Defines that the next path parameter after todos is
	// treated as a parameter and passed to the TodoResources
	// Allows to type http://localhost:8080/rest.todo/rest/todos/1
	// 1 will be treaded as parameter todo and passed to TodoResource
	@Path("{order}")
	public OrderResource getTodo(@PathParam("order") String id) {
		return new OrderResource(uriInfo, request, id);
	}

}