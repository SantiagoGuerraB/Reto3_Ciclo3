package co.usa.ciclo3.ciclo3.repository.crud;

import java.util.Date;
import java.util.List;
import co.usa.ciclo3.ciclo3.model.Reservation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReservationCrudRepository extends CrudRepository <Reservation,Integer> {
    public List<Reservation> findAllByStatus (String status);
    public List<Reservation> findAllByStartDateAfterAndStartDateBefore (Date dateOne, Date dateTwo);

    @Query("SELECT c.client, COUNT(c.client) from Reservation AS c group by c.client ORDER BY COUNT(c.client)DESC")
    public List<Object[]> countTotalReservationsByClient();

}
