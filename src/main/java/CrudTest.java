import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import static org.testng.AssertJUnit.*;

public class CrudTest {
    private final ClientCrudService clientCrudService;
    private final PlanetCrudService planetCrudService;
    private final TicketCrudService ticketCrudService;

    public CrudTest() {
        clientCrudService = new ClientCrudService();
        planetCrudService = new PlanetCrudService();
        ticketCrudService = new TicketCrudService();
    }

    public void testClientCrudService() {

        Client client = new Client();
        client.setName("Johnny");
        clientCrudService.create(client);

        Client savedClient = clientCrudService.read(1L);
        System.out.println(savedClient);

        clientCrudService.delete(11L);

        assertNull(clientCrudService.read(11L));
    }

    public void testPlanetCrudService() {

        Planet planet = new Planet();
        planet.setId("NEP");
        planet.setName("Neptune");
        planetCrudService.create(planet);
        Planet savedPlanet = planetCrudService.read("NEP");
        System.out.println(savedPlanet);
        planetCrudService.delete("NEP");
    }

    public void testTicketCrudService() throws Exception {

        Ticket ticket = new Ticket();
        Client client = clientCrudService.read(1L);
        Planet planetFrom = planetCrudService.read("MARS");
        Planet planetTo = planetCrudService.read("SAT");

        ticket.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
        ticket.setClient(client);
        ticket.setToPlanet(planetTo);
        ticket.setFromPlanet(planetFrom);
        ticketCrudService.saveTicket(ticket);

        Ticket getTicket = ticketCrudService.getTicketById(1L);
        System.out.println(getTicket);

        ticketCrudService.deleteTicket(1L);

        List<Ticket> tickets= ticketCrudService.findAll();
        tickets.forEach(ticket1 -> System.out.println(ticket1));
    }

    public static void main(String[] args) throws Exception {
        CrudTest crudTest = new CrudTest();
//        crudTest.testClientCrudService();
//        crudTest.testPlanetCrudService();
        crudTest.testTicketCrudService();
        HibernateUtil.getInstance().close();
    }
}
