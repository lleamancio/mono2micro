package com.leticiaamancio.travelorder.flight;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.time.temporal.ChronoUnit;

@RegisterRestClient(baseUri = "http://flight-lleamancio-dev.apps.rm1.0a51.p1.openshiftapps.com/flights")
public interface FlightService {

    @GET
    @Path("/flight-health-check")
    @Produces(MediaType.APPLICATION_JSON)
    public String flightsHealthCheck();

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Flight getFlightById(@PathParam("id") long id);

    @GET
    @Path("findByTravelOrderId")
    @Produces(MediaType.APPLICATION_JSON)
    @Timeout(unit = ChronoUnit.SECONDS, value = 2)
    @Fallback(fallbackMethod = "fallback")
    @CircuitBreaker(
            requestVolumeThreshold = 4,
            failureRatio = 0.5,
            delay = 5000,
            successThreshold = 2
    )
    public Flight findByTravelOrderId(@QueryParam("travelOrderId") long travelOrderId);

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Flight createFlight(Flight flight);

    default Flight fallback(long travelOrderId) {
        Flight flight = new Flight();
        flight.setTravelOrderId(travelOrderId);
        flight.setFromAirport("");
        flight.setToAirport("");
        return flight;
    }
}
