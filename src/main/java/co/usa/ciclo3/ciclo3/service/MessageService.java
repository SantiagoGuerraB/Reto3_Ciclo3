package co.usa.ciclo3.ciclo3.service;

import java.util.List;
import java.util.Optional;

import antlr.debug.MessageAdapter;
import co.usa.ciclo3.ciclo3.model.Message;
import co.usa.ciclo3.ciclo3.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
    @Autowired

    private MessageRepository metodosCrud;

    public List<Message> getAll(){return metodosCrud.getAll();}

    public Optional<Message> getMessage(int messageId){return metodosCrud.getMessage(messageId);}

    public Message save(Message message){
        if (message.getIdMessage()==null){
            return metodosCrud.save(message);
        }else{
            Optional<Message> e=metodosCrud.getMessage(message.getIdMessage());
            if(e.isEmpty()){
                return metodosCrud.save(message);
            }else{
                return message;
            }
        }
    }

    public Message update(Message message){
        if(message.getIdMessage()!=null){
            Optional<Message> e= metodosCrud.getMessage(message.getIdMessage());
            if(!e.isEmpty()){
                if(message.getMessageText()!=null){
                    e.get().setMessageText(message.getMessageText());
                }
                metodosCrud.save(e.get());
                return e.get();
            }else{
                return message;
            }
        }else{
            return message;
        }
    }

    public boolean deleteMessage(int messageId) {
        Boolean aBoolean = getMessage(messageId).map(message -> {
            metodosCrud.delete(message);
            return true;
        }).orElse(false);
        return aBoolean;
    }
}
