package co.usa.ciclo3.ciclo3.service;

import co.usa.ciclo3.ciclo3.model.Ortopedic;
import co.usa.ciclo3.ciclo3.repository.OrtopedicRepository;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrtopedicService {
    @Autowired
    private OrtopedicRepository metodosCrud;

    public List<Ortopedic> getAll(){return metodosCrud.getAll();}

    public Optional<Ortopedic> getOrtopedic(int ortopedicId){return metodosCrud.getOrtopedic(ortopedicId);}

    public Ortopedic save(Ortopedic ortopedic){
        if(ortopedic.getId()==null){
            return metodosCrud.save(ortopedic);
        }else{
            Optional<Ortopedic> evt=metodosCrud.getOrtopedic(ortopedic.getId());
            if(evt.isEmpty()){
                return metodosCrud.save(ortopedic);
            }else{
                return ortopedic;
            }
        }
    }

    public Ortopedic update (Ortopedic ortopedic){
        if(ortopedic.getId()!=null){
            Optional<Ortopedic> e=metodosCrud.getOrtopedic(ortopedic.getId());
            if(!e.isEmpty()){
                if(ortopedic.getName()!=null){
                    e.get().setName(ortopedic.getName());
                }
                if(ortopedic.getBrand()!=null){
                    e.get().setBrand(ortopedic.getBrand());
                }
                if(ortopedic.getYear()!=null){
                    e.get().setYear(ortopedic.getYear());
                }
                if(ortopedic.getDescription()!=null){
                    e.get().setDescription(ortopedic.getDescription());
                }
                if(ortopedic.getCategory()!=null){
                    e.get().setCategory(ortopedic.getCategory());
                }
                metodosCrud.save(e.get());
                return e.get();
            }else{
                return ortopedic;
            }
        }else{
            return ortopedic;
        }
        }

        public boolean deleteOrtopedic(int ortopedicId) {
            Boolean aBoolean = getOrtopedic(ortopedicId).map(bike -> {
                metodosCrud.delete(bike);
                return true;
            }).orElse(false);
            return aBoolean;
    }
}
