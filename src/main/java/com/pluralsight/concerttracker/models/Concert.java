package com.pluralsight.concerttracker.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Concert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int year;
    private double ticketPrice;
    private int ticketsSold;
    private long artistId;
    private long venueId;
    private long promoterId;

    public Concert(int year, double ticketPrice, int ticketsSold, long artistId, long venueId, long promoterId) {
        this.year = year;
        this.ticketPrice = ticketPrice;
        this.ticketsSold = ticketsSold;
        this.artistId = artistId;
        this.venueId = venueId;
        this.promoterId = promoterId;
    }

    public Concert() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public int getTicketsSold() {
        return ticketsSold;
    }

    public void setTicketsSold(int ticketsSold) {
        this.ticketsSold = ticketsSold;
    }

    public long getArtistId() {
        return artistId;
    }

    public void setArtistId(long artistId) {
        this.artistId = artistId;
    }

    public long getVenueId() {
        return venueId;
    }

    public void setVenueId(long venueId) {
        this.venueId = venueId;
    }

    public long getPromoterId() {
        return promoterId;
    }

    public void setPromoterId(long promoterId) {
        this.promoterId = promoterId;
    }
}
