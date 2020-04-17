import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.List;

import org.junit.jupiter.api.Test;

import entity.Flight;

public class GetFlightsTests {

	@Test
	public void getFlights() {
		GetFlights getFlights = new GetFlights();
		List<Flight> flights = (List<Flight>) getFlights.handleRequest(null, null);
		assertFalse(flights.isEmpty());
	}

}
