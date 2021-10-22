package co.usa.ciclo3.ciclo3.web;

import co.usa.ciclo3.ciclo3.model.Ortopedic;
import co.usa.ciclo3.ciclo3.service.OrtopedicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/Ortopedic")
@CrossOrigin(origins= "*", methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})

public class OrtopedicController {
    @Autowired
    private OrtopedicService services;
    @GetMapping("/all")
    public List<Ortopedic> getOrtopedics(){
        return services.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Ortopedic> getOrtopedic(@PathVariable("id")int idOrtopedic) {return services.getOrtopedic(idOrtopedic);}

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Ortopedic save(@RequestBody Ortopedic ortopedic){
        return services.save(ortopedic);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public Ortopedic update(@RequestBody Ortopedic ortopedic){return services.update(ortopedic);}

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean delete(@PathVariable("id") int ortopedicId){return services.deleteOrtopedic(ortopedicId);}
}