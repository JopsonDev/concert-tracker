package com.pluralsight.concerttracker.services;

import com.pluralsight.concerttracker.data.ArtistRepository;
import com.pluralsight.concerttracker.data.ConcertRepository;
import com.pluralsight.concerttracker.data.PromoterRepository;
import com.pluralsight.concerttracker.data.VenueRepository;
import com.pluralsight.concerttracker.models.Artist;
import com.pluralsight.concerttracker.models.Concert;
import com.pluralsight.concerttracker.models.Promoter;
import com.pluralsight.concerttracker.models.Venue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConcertService {
    private final ArtistRepository artistRepository;
    private final ConcertRepository concertRepository;
    private final VenueRepository venueRepository;
    private final PromoterRepository promoterRepository;

    @Autowired
    public ConcertService(ArtistRepository artistRepository, ConcertRepository concertRepository, VenueRepository venueRepository, PromoterRepository promoterRepository) {
        this.artistRepository = artistRepository;
        this.concertRepository = concertRepository;
        this.venueRepository = venueRepository;
        this.promoterRepository = promoterRepository;
    }

    public List<Artist> allArtist(){
        return artistRepository.findAll();
    }

    public List<Concert> allConcerts(){
        return concertRepository.findAll();
    }

    public List<Venue> allVenues(){
        return venueRepository.findAll();
    }

    public List<Promoter> allPromoter(){
        return promoterRepository.findAll();
    }

    public Artist saveArtist(Artist artist){
        return artistRepository.save(artist);
    }

    public Concert saveConcert(Concert concert){
        return concertRepository.save(concert);
    }

    public Venue saveVenue(Venue venue){
        return venueRepository.save(venue);
    }

    public Promoter savePromoter(Promoter promoter){
        return promoterRepository.save(promoter);
    }

    public void deleteArtist(Artist artist){
        artistRepository.delete(artist);
    }

    public void deleteConcert(Concert concert){
        concertRepository.delete(concert);
    }

    public void deleteVenue(Venue venue){
        venueRepository.delete(venue);
    }

    public void deletePromoter(Promoter promoter){
        promoterRepository.delete(promoter);
    }
    public Concert findConcertById(long input){
        return concertRepository.findConcertById(input);
    }

    public Artist findArtistById(long input){
        return artistRepository.findArtistsById(input);
    }

    public Venue findVenueById(long input){
        return venueRepository.findVenueById(input);
    }

    public Promoter findPromoterById(long input){
        return promoterRepository.findPromoterById(input);
    }

    public List<Concert> findConcertByYear(int year){
        return concertRepository.findConcertByYear(year);
    }

    public List<Concert> findConcertByArtist(long id){
        return concertRepository.findConcertByArtistId(id);
    }

    public List<Concert> findConcertByVenue(long id){
        return concertRepository.findConcertByVenueId(id);
    }

    public List<Venue> findVenueByCity(String city){
        return venueRepository.findVenueByCityIs(city);
    }

    public List<Venue> findVenueByName(String name){
        return venueRepository.findVenueByName(name);
    }

    public List<Venue> findVenueWithMoreThanCap(int minCapacity){
        return venueRepository.findVenueByCapacityGreaterThan(minCapacity);
    }

    public List<Concert> findConcertByCity(String city){
        return concertRepository.search(city);
    }

    public List<Concert> findByMaxPrice(double price){
        return concertRepository.findConcertByTicketPriceLessThanEqual(price);
    }

    public List<Concert> findPriceRange(double max, double min){
        return concertRepository.search(max, min);
    }

    public List<Concert> findByMaxPriceMinYear(double maxP, int minY){
        return concertRepository.search(maxP, minY);
    }




}
