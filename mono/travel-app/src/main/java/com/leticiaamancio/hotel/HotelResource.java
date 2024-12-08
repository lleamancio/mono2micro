package com.leticiaamancio.hotel;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("hotels")
public class HotelResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Hotel> hotels(){
        return Hotel.listAll();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Hotel findById(@PathParam("id") long id){
        return Hotel.findById(id);
    }

    @GET
    @Path("findByTravelOrderId")
    @Produces(MediaType.APPLICATION_JSON)
    public Hotel findByTravelOrderId(@QueryParam("travelOrderId") long travelOrderId){
        return Hotel.findByTravelOrderId(travelOrderId);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Hotel create(Hotel hotel){
        hotel.id = null;
        hotel.persist();
        return hotel;
    }
}
