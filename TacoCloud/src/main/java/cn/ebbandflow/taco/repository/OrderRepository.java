package cn.ebbandflow.taco.repository;

import cn.ebbandflow.taco.pojo.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order,Long> {
}
