package my_project.dto;

import java.sql.Date;
import java.util.Set;

public class ConcertDTO {
    private Long id;
    private String name;
    private String location;
    private double ticketPriceS;
    private double ticketPriceV;
    private Date date;
    private Set<MusicianDTO> musicians;



    public ConcertDTO() {
    }
    
    public ConcertDTO(Long id, String name, String location, double ticketPriceS, double ticketPriceV, Date date,
            Set<MusicianDTO> musicians) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.ticketPriceS = ticketPriceS;
        this.ticketPriceV = ticketPriceV;
        this.date = date;
        this.musicians = musicians;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public double getTicketPriceS() {
        return ticketPriceS;
    }
    public void setTicketPriceS(double ticketPriceS) {
        this.ticketPriceS = ticketPriceS;
    }
    public double getTicketPriceV() {
        return ticketPriceV;
    }
    public void setTicketPriceV(double ticketPriceV) {
        this.ticketPriceV = ticketPriceV;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public Set<MusicianDTO> getMusicians() {
        return musicians;
    }
    public void setMusicians(Set<MusicianDTO> musicians) {
        this.musicians = musicians;
    }


    
}
