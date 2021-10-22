package co.usa.ciclo3.ciclo3.service;

import co.usa.ciclo3.ciclo3.model.Category;
import co.usa.ciclo3.ciclo3.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository metodosCrud;

    public List<Category> getAll() {return metodosCrud.getAll();}

    public Optional<Category> getCategory(int idCategory) {return metodosCrud.getCategory(idCategory);}

    public Category save(Category category){
        if (category.getId()==null){
            return metodosCrud.save(category);
        }else{
            Optional<Category> evt=metodosCrud.getCategory(category.getId());
            if(evt.isEmpty()){
                return metodosCrud.save(category);
            }else{
                return category;
            }
        }
    }

    public Category update(Category category){
        if(category.getId()!=null){
            Optional<Category>g=metodosCrud.getCategory(category.getId());
            if(!g.isEmpty()){
                if(category.getDescription()!=null){
                    g.get().setDescription(category.getDescription());
                }
                if(category.getName()!=null){
                    g.get().setName(category.getName());
                }
                return metodosCrud.save(g.get());
            }
        }
        return category;
    }
    public boolean deletecategory(int categoryId){
        Boolean d=getCategory(categoryId).map(category -> {metodosCrud.delete(category);
            return true;
        }).orElse(false);
        return d;
    }
}
