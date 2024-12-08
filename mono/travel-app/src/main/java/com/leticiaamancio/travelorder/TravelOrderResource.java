package com.leticiaamancio.travelorder;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("travel-orders")
public class TravelOrderResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<TravelOrder> orders(){
        return TravelOrder.listAll();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public TravelOrder findById(@PathParam("id") long id){
        return TravelOrder.findById(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public TravelOrder create(TravelOrder order){
        order.id = null;
        order.persist();
        return order;
    }
}
