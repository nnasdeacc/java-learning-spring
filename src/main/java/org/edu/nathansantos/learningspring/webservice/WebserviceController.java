package org.edu.nathansantos.learningspring.webservice;

import org.edu.nathansantos.learningspring.business.ReservationService;
import org.edu.nathansantos.learningspring.business.RoomReservation;
import org.edu.nathansantos.learningspring.data.Guest;
import org.edu.nathansantos.learningspring.data.GuestRepository;
import org.edu.nathansantos.learningspring.data.Room;
import org.edu.nathansantos.learningspring.util.DateUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class WebserviceController {
    private final DateUtils dateUtils;
    private final ReservationService reservationService;

    public WebserviceController(DateUtils dateUtils, ReservationService reservationService) {
        this.dateUtils = dateUtils;
        this.reservationService = reservationService;
    }

    @RequestMapping(path = "/reservations", method = RequestMethod.GET)
    public List<RoomReservation> getReservations(@RequestParam(value="date", required = false) String dateString) {
        Date date = this.dateUtils.createDateFromDateString(dateString);
        return this.reservationService.getRoomReservationsForDate(date);
    }

    @GetMapping("/guests")
//    @RequestMapping(path = "/guests", method = RequestMethod.GET)
    public List<Guest> getGuests() {
        return this.reservationService.getHotelGuests();
    }

    @PostMapping("/guests")
//    @RequestMapping(path = "/guests", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void addGuest(@RequestBody Guest guest) {
        this.reservationService.addGuest(guest);
    }

    @GetMapping(path = "/rooms")
    public List<Room> getRooms() {
        return this.reservationService.getHotelRooms();
    }
}
