// package ua.tqs.homework.controller;

// import org.apache.http.HttpStatus;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import ua.tqs.homework.models.Reservation;
// import ua.tqs.homework.services.ReservationService;

// @RestController
// @RequestMapping("/api/reservations")
// public class ReservationController {

//     @Autowired
//     private ReservationService reservationService;

//     // @PostMapping
//     // public ResponseEntity<?> createReservation(@RequestBody Reservation reservation) {
//     //     try {
//     //         Reservation createdReservation = reservationService.createReservation(reservation);
//     //         return new ResponseEntity<>(createdReservation, HttpStatus.CREATED);
//     //     } catch (Exception e) {
//     //         return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
//     //     }
//     // }

// //     @GetMapping("/{id}")
// //     public ResponseEntity<?> getReservation(@PathVariable("id") Long id) {
// //         Reservation reservation = reservationService.getReservation(id);
// //         if (reservation != null) {
// //             return new ResponseEntity<>(reservation, HttpStatus.OK);
// //         } else {
// //             return new ResponseEntity<>("Reservation not found", HttpStatus.NOT_FOUND);
// //         }
// //     }
// }