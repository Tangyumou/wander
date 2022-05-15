package cn.ebbandflow.taco.repository;

import cn.ebbandflow.taco.pojo.Ingredient;
import org.springframework.data.repository.CrudRepository;

public interface IngredientRepository extends CrudRepository<Ingredient,String> {
}
