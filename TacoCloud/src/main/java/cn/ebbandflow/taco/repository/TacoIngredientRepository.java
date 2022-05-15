package cn.ebbandflow.taco.repository;

import cn.ebbandflow.taco.vo.TacoIngredient;
import org.springframework.data.repository.CrudRepository;

public interface TacoIngredientRepository extends CrudRepository<TacoIngredient, Long> {
}
