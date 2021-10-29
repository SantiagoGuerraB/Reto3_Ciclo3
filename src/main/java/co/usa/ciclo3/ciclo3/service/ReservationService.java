package co.usa.ciclo3.ciclo3.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import co.usa.ciclo3.ciclo3.model.Reservation;
import co.usa.ciclo3.ciclo3.reports.ClientCount;
import co.usa.ciclo3.ciclo3.reports.StatusReservation;
import co.usa.ciclo3.ciclo3.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ReservationService {
    @Autowired
    private ReservationRepository metodosCrud;

    public List<Reservation> getAll() {
        return metodosCrud.getAll();
    }

    public Optional<Reservation> getReservation(int reservationId) {
        return metodosCrud.getReservation(reservationId);
    }

    public Reservation save(Reservation reservation) {
        if (reservation.getIdReservation() == null) {
            return metodosCrud.save(reservation);
        } else {
            Optional<Reservation> e = metodosCrud.getReservation(reservation.getIdReservation());
            if (e.isEmpty()) {
                return metodosCrud.save(reservation);
            } else {
                return reservation;
            }
        }
    }

    public Reservation update(Reservation reservation) {
        if (reservation.getIdReservation() != null) {
            Optional<Reservation> e = metodosCrud.getReservation(reservation.getIdReservation());
            if (!e.isEmpty()) {

                if (reservation.getStartDate() != null) {
                    e.get().setStartDate(reservation.getStartDate());
                }
                if (reservation.getDevolutionDate() != null) {
                    e.get().setDevolutionDate(reservation.getDevolutionDate());
                }
                if (reservation.getStatus() != null) {
                    e.get().setStatus(reservation.getStatus());
                }
                metodosCrud.save(e.get());
                return e.get();
            } else {
                return reservation;
            }
        } else {
            return reservation;
        }
    }

    public boolean deleteReservation(int reservationId) {
        Boolean aBoolean = getReservation(reservationId).map(reservation -> {
            metodosCrud.delete(reservation);
            return true;
        }).orElse(false);
        return aBoolean;
    }

    public StatusReservation getReportStatusReservation() {
        List<Reservation> completed = metodosCrud.ReservationStatus("completed");
        List<Reservation> cancelled = metodosCrud.ReservationStatus("cancelled");
        return new StatusReservation(completed.size(), cancelled.size());
    }

    public List<Reservation> getReportTimeReservation(String dateA, String dateB) {
        SimpleDateFormat parser = new SimpleDateFormat("yyy-MM-dd");
        Date date1 = new Date();
        Date date2 = new Date();

        try {
            date1 = parser.parse(dateA);
            date2 = parser.parse(dateB);
        } catch (ParseException evt) {
            evt.printStackTrace();

        }
        if (date1.before(date2)) {
            return metodosCrud.ReservationTime(date1, date2);

        } else {
            return new ArrayList<>();
        }
    }

    public List<ClientCount> serviceTopClient() {
        return metodosCrud.getTopClient();
    }
}