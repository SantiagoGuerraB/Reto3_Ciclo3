package co.usa.ciclo3.ciclo3.repository;

import java.util.List;
import java.util.Optional;

import co.usa.ciclo3.ciclo3.model.Message;
import co.usa.ciclo3.ciclo3.repository.crud.MessageCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MessageRepository {
    @Autowired
    private MessageCrudRepository CrudRepository;

    public List<Message> getAll(){return (List<Message>) CrudRepository.findAll();
    }
    public Optional<Message> getMessage(int id){
        return CrudRepository.findById(id);
    }

    public Message save(Message message){
        return CrudRepository.save(message);
    }
    public void delete(Message message){
        CrudRepository.delete(message);
    }

}
