package my_project.model;


import java.sql.Date;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
//import javax.persistence.Temporal;
//import javax.persistence.TemporalType;

import org.hibernate.Hibernate;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

//import lombok.Getter;
//import lombok.Setter;



@Entity
@Table(name = "concerts")

public class Concert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name="name")
    private String name;

    @Column(nullable = false, name = "location")
    private String location;

    @Column(nullable = false, name = "ticket_price_s")
    private double ticketPriceS;

    @Column(nullable = false, name = "ticket_price_v")
    private double ticketPriceV;

    @Column(nullable = false, name = "date")
    private Date date;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "concert_musicians",
        joinColumns = @JoinColumn(name = "concert_id"),
        inverseJoinColumns = @JoinColumn(name = "musician_id"))
    private Set<Musician> musicians = new HashSet<>();
    

    public Concert(){}


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


    public Set<Musician> getMusicians() {
        return musicians;
    }


    public void setMusicians(Set<Musician> musicians) {
        this.musicians = musicians;
    }


    public Concert(String name, String location, double ticketPriceS, double ticketPriceV, Date date,
            Set<Musician> musicians) {
        this.name = name;
        this.location = location;
        this.ticketPriceS = ticketPriceS;
        this.ticketPriceV = ticketPriceV;
        this.date = date;
        this.musicians = musicians;
    }


    @Override
    public String toString() {
        return "Concert [id=" + id + ", name=" + name + ", location=" + location + ", ticketPriceS=" + ticketPriceS
                + ", ticketPriceV=" + ticketPriceV + ", date=" + date + ", musicians=" + musicians + "]";
    }


    public Long getId() {
        return id;
    }

   
}
