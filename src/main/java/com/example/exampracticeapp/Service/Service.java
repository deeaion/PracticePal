//package com.example.exampracticeapp.Service;//package com.example.exampracticeapplication.Service;
//
//
//import com.example.exampracticeapplication.Exception.ServiceException;
//import com.example.exampracticeapplication.Repository.Repository;
//import com.example.exampracticeapplication.Utils.ChangeEvent;
//import com.example.exampracticeapplication.Utils.ChangeEventType;
//
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Observable;
//import java.util.Observer;
//import java.util.stream.Collectors;
//
//public class Service implements Observable<ChangeEvent> {
//
//    private final Repository flightRepository;
//    private final Repository ticketRepository;
//    private final Repository clientRepository;
//
//
//
//
//    private List<Observer<ChangeEvent>> observers=new ArrayList<>();
//
//    public Service(FlightRepository flightRepository, TicketRepository ticketRepository, ClientRepository clientRepository) {
//        this.flightRepository=flightRepository;
//        this.clientRepository=clientRepository;
//        this.ticketRepository=ticketRepository;
//
//    }
//
//
//    @Override
//    public void addObserver(Observer<ChangeEvent> e) {
//        observers.add(e);
//    }
//
//    @Override
//    public void removeObserver(Observer<ChangeEvent> e) {
//        observers.remove(e);
//    }
//
//    @Override
//    public void notifyObservers(ChangeEvent t) {
//        observers.forEach(x->x.update(t));
//
//    }
//
////    public Client getClientByUsername(String username) {
////        List<Client> clients= (List<Client>) clientRepository.findAll();
////        List<Client> searchClient=clients.stream().filter(client -> {
////            return client.getUsername().equals(username);
////        }).collect(Collectors.toList());
////        if(searchClient.size()==1)
////        {
////            return searchClient.get(0);
////        }
////        return null;//nu l am gasit
////    }
////
////    public List<Ticket> getUserTickets(Client client) {
////        List<Ticket> tickete= (List<Ticket>) ticketRepository.findAll();
////        return tickete.stream().filter(ticket -> {
////            return ticket.getUsername().equals(client.getUsername());
////        }).collect(Collectors.toList());
////    }
////
////    public List<Ticket> getTicketeZiSpecifica(LocalDate date)
////    {
////        List<Ticket> tickete= (List<Ticket>) ticketRepository.findAll();
////        return tickete.stream().filter(ticket -> {
////            return ticket.getPurchaseTime().toLocalDate().equals(date);
////        }).collect(Collectors.toList());
////    }
////
////    public List<Flight> getFilteredFrom(String from,List<Flight> flights)
////    {
////        return flights.stream().filter(
////                flight -> {
////                    return flight.getFrom().equals(from);
////                }
////        ).collect(Collectors.toList());
////
////    }
////    public List<Flight> getFilteredTo(String to,List<Flight> flights)
////    {
////        return flights.stream().filter(flight ->
////                {
////                       return flight.getTo().equals(to);
////                }
////        ).collect(Collectors.toList());
////
////    }
////    public  List<String> getAllLocationsFrom() {
////        List<Flight> flights=(List<Flight>) flightRepository.findAll();
////
////        return flights.stream()
////                .map(Flight::getFrom)
////                .collect(Collectors.toSet()).stream().toList();
////    }
////
////    public List<String> getAllLocationsTo() {
////        List<Flight> flights=(List<Flight>) flightRepository.findAll();
////
////        return flights.stream()
////                .map(Flight::getTo)
////                .collect(Collectors.toSet()).stream().toList();
////    }
////    public List<Flight> filterByDeparture(LocalDate departure,List<Flight> flights)
////    {
////        return flights.stream().filter(flight ->
////        {
////            return flight.getDepartureTime().toLocalDate().equals(departure);
////        }).collect(Collectors.toList());
////
////    }
////    public List<Flight> getFlightsFiltrate(String from,String to,LocalDate departure)
////    {
////        List<Flight> flights=(List<Flight>) flightRepository.findAll();
////        //daca from !="" filtrez dupa el
////        if(from!=null)
////        {
////            flights=getFilteredFrom(from,flights);
////        }
////        if(to!=null)
////        {
////            flights=getFilteredTo(to,flights);
////        }
////        return filterByDeparture(departure,flights);
////    }
////
////
////    public void addTicket(Flight selectedFlight, Client client,int purchased) {
////        Ticket ticket=new Ticket(client.getUsername(),LocalDateTime.now(),selectedFlight.getId());
////
//////        flightRepository.update(selectedFlight);
////        if(purchased==selectedFlight.getSeats())
////        {
////            throw new ServiceException("No seats available!");
////        }
////        ticketRepository.save(ticket);
////        notifyObservers(new ChangeEvent<>(ChangeEventType.TicketAdded,ticket));
////    }
////    private int getNumarBileteAchizitionateFlight(Flight flight)
////    {
////        List<Ticket> tickets= (List<Ticket>) ticketRepository.findAll();
////        List<Ticket> ticketeZbor=tickets.stream().filter(ticket->{
////            return flight.getId()==ticket.getFlightId();
////        }).collect(Collectors.toList());
////        return ticketeZbor.size();
////    }
////    public List<FlightDTO> initializeDTOs(List<Flight> flightsFiltrati) {
////            return flightsFiltrati.stream()
////                    .map(flight -> {
////                        int numarBilete = getNumarBileteAchizitionateFlight(flight);
////                        FlightDTO flightDTO=new FlightDTO(flight.getFrom(),flight.getTo(),flight.getDepartureTime(),flight.getLandingTime(),flight.getSeats(),flight.getSeats()-numarBilete);
////                        flightDTO.setId(flight.getId());
////                        return flightDTO;
////                    })
////                    .collect(Collectors.toList());
////        }
//
//}
