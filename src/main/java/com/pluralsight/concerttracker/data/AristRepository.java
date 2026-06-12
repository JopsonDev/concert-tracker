package com.pluralsight.concerttracker.data;

import com.pluralsight.concerttracker.models.Artist;
import com.pluralsight.concerttracker.models.Venue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AristRepository extends JpaRepository<Artist, Long> {
}
