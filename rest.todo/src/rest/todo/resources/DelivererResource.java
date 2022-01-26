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

import rest.todo.dao.DelivererDao;
import rest.todo.model.DelivererModel;


public class DelivererResource {
    @Context
    UriInfo uriInfo;
    @Context
    Request request;
    String id;
    public DelivererResource(UriInfo uriInfo, Request request, String id) {
        this.uriInfo = uriInfo;
        this.request = request;
        this.id = id;
    }

    //Application integration
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public DelivererModel getResto() {
        DelivererModel resto = DelivererDao.instance.getModel().get(id);
        if(resto==null)
            throw new RuntimeException("Get: Todo with " + id +  " not found");
        return resto;
    }

    // for the browser
    @GET
    @Produces(MediaType.TEXT_XML)
    public DelivererModel getTodoHTML() {
    	DelivererModel resto = DelivererDao.instance.getModel().get(id);
        if(resto==null)
            throw new RuntimeException("Get: Todo with " + id +  " not found");
        return resto;
    }

    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public Response putTodo(JAXBElement<DelivererModel> todo) {
    	DelivererModel c = todo.getValue();
        return putAndGetResponse(c);
    }

    @DELETE
    public void deleteTodo() {
    	DelivererModel c = DelivererDao.instance.getModel().remove(id);
        if(c==null)
            throw new RuntimeException("Delete: Todo with " + id +  " not found");
    }

    private Response putAndGetResponse(DelivererModel todo) {
        Response res;
        if(DelivererDao.instance.getModel().containsKey(todo.getID())) {
            res = Response.noContent().build();
        } else {
            res = Response.created(uriInfo.getAbsolutePath()).build();
        }
        DelivererDao.instance.getModel().put(todo.getID(), todo);
        return res;
    }



}