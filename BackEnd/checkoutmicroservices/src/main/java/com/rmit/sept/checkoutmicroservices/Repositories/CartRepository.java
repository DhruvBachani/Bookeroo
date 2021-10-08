package com.rmit.sept.checkoutmicroservices.Repositories;


import com.rmit.sept.checkoutmicroservices.model.CartItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CartRepository extends CrudRepository<CartItem, Long> {


}
