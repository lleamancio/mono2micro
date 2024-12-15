package com.leticiaamancio.travelorder;

import com.leticiaamancio.travelorder.flight.FlightService;
import com.leticiaamancio.travelorder.hotel.HotelService;
import jakarta.inject.Inject;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Readiness;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@Readiness
public class ReadinessCheck implements HealthCheck {

    private static final String FLIGHT_HEALTH_CHECK = "flight-health-check";
    private static final String HOTELS_HEALTH_CHECK = "hotels-health-check";

    @Inject
    @RestClient
    FlightService flightService;

    @Inject
    @RestClient
    HotelService hotelService;

    @Override
    public HealthCheckResponse call() {
        var flights = flightService.flightsHealthCheck();
        var hotels = hotelService.hotelsHealthCheck();
        if (FLIGHT_HEALTH_CHECK.equals(flights) && HOTELS_HEALTH_CHECK.equals(hotels)) {
            return HealthCheckResponse.up("App is ready");
        } else {
            return HealthCheckResponse.down("App is not ready");
        }
    }
}
