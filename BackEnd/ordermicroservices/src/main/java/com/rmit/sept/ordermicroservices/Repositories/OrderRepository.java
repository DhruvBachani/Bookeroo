package com.rmit.sept.ordermicroservices.Repositories;

import com.rmit.sept.ordermicroservices.model.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long>{

}
