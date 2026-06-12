package com.pluralsight.concerttracker.data;

import com.pluralsight.concerttracker.models.Concert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ConcertRepository extends JpaRepository<Concert, Long> {
    Concert findConcertById(Long id);

    List<Concert> findConcertByYear(int year);

    List<Concert> findConcertByArtistId(long id);

    List<Concert> findConcertByVenueId(long venueId);

    @Query("SELECT c FROM Concert c  WHERE c.ticketPrice <= :maxPrice AND c.year >= :minYear")
    List<Concert> search(double maxPrice, int minYear);

    @Query("SELECT c FROM Concert c JOIN Venue v ON v.id = c.venueId WHERE v.city = :city")
    List<Concert> search(String city);

    List<Concert> findConcertByTicketPriceLessThanEqual(double ticketPriceIsLessThan);

    @Query("SELECT c FROM Concert c WHERE c.ticketPrice <= :maxPrice AND c.ticketPrice >= :minPrice")
    List<Concert> search(double maxPrice, double minPrice);



}
