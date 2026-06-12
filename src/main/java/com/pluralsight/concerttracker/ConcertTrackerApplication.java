package com.pluralsight.concerttracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ConcertTrackerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConcertTrackerApplication.class, args);
    }



    /* classes/models(venue(name,city,capacity))(artist(name,genre))(promoter(name))(concert(year,ticket price, tickets that have been sold,artist,venue,promoter)) DONE
    make database myself in mySQL DONE
    make service layers for the models which is responsible for communicating with the database
    make startuprunner that communicates with the service layers


    add all methods to application add/find/update/delete (all four data types) plus the business rules(in instructions)


   search/filters
   by year/by artist/by venue/ by city/ by max price/ by price range/ by a combination

   reports like how many tickets were sold this year
   - Revenue per venue
   - Busiest venue and busiest artist
   - Average ticket price by year
   - Capacity report

   bonus
   a dashboard(refer to instructions)
   sort by ticket price(lowest) or by year
   report of concerts at or above 90% capacity
   store real dates instead of just year
   a fourth relationship of my own you can say tours for example
     */
}
