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




}
