package com.leticiaamancio.travelorder;

import com.leticiaamancio.flight.Flight;
import com.leticiaamancio.flight.FlightResource;
import com.leticiaamancio.hotel.Hotel;
import com.leticiaamancio.hotel.HotelResource;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;
import java.util.stream.Collectors;

@Path("travel-orders")
public class TravelOrderResource {

    @Inject
    FlightResource flightResource;

    @Inject
    HotelResource hotelResource;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<TravelOrderDTO> orders() {
        return TravelOrder.<TravelOrder>listAll().stream()
                .map(
                        order -> TravelOrderDTO.of(
                                order,
                                flightResource.findByTravelOrderId(order.id),
                                hotelResource.findByTravelOrderId(order.id)
                        )
                ).collect(Collectors.toList());
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public TravelOrder findById(@PathParam("id") long id) {
        return TravelOrder.findById(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public TravelOrder create(TravelOrderDTO orderDto) {
        TravelOrder travelOrder = new TravelOrder();
        travelOrder.id = null;
        travelOrder.persist();

        Flight flight = new Flight();
        flight.fromAirport = orderDto.getFromAirport();
        flight.toAirport = orderDto.getToAirport();
        flight.travelOrderId = travelOrder.id;
        flightResource.createFlight(flight);

        Hotel hotel = new Hotel();
        hotel.nights = orderDto.getNights();
        hotel.travelOrderId = travelOrder.id;
        hotelResource.create(hotel);

        return travelOrder;
    }
}
