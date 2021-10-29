package co.usa.ciclo3.ciclo3.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import co.usa.ciclo3.ciclo3.model.Client;
import co.usa.ciclo3.ciclo3.model.Reservation;
import co.usa.ciclo3.ciclo3.reports.ClientCount;
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

    public List<Reservation> ReservationStatus (String status){return CrudRepository.findAllByStatus(status);}
    public List<Reservation> ReservationTime(Date a, Date b){return CrudRepository.findAllByStartDateAfterAndStartDateBefore(a,b);}

    public List<ClientCount> getTopClient(){
        List<ClientCount> res = new ArrayList<>();
        List<Object[]>report = CrudRepository.countTotalReservationsByClient();
        for (int i=0; i<report.size();i++){
            res.add(new ClientCount((long)report.get(i)[1],(Client)report.get(i)[0]));
        }
        return res;
    }
}
