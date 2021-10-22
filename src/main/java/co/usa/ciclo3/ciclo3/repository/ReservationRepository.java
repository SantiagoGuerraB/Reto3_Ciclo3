package co.usa.ciclo3.ciclo3.repository;

import java.util.List;
import java.util.Optional;

import co.usa.ciclo3.ciclo3.model.Reservation;
import co.usa.ciclo3.ciclo3.repository.crud.ReservationCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ReservationRepository {

    @Autowired
    private ReservationCrudRepository CrudRepository;

    public List<Reservation> getAll(){return (List<Reservation>) CrudRepository.findAll();}
    public Optional<Reservation> getReservation(int id){
        return CrudRepository.findById(id);
    }
    public Reservation save(Reservation reservation){
        return CrudRepository.save(reservation);
    }
    public void delete(Reservation reservation){
        CrudRepository.delete(reservation);
    }

}
