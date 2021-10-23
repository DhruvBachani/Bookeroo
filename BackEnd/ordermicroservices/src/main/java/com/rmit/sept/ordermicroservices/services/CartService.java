package com.rmit.sept.ordermicroservices.services;


import com.rmit.sept.ordermicroservices.Repositories.CartRepository;
import com.rmit.sept.ordermicroservices.model.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {
    @Autowired
    CartRepository cartRepository;

    public Cart saveCart(Long user_id, List<Long> adIds){
        Cart cart = cartRepository.findByUserId(user_id);
        if(cart!=null){

            for(Long ad: adIds){
                if(cart.getAds().contains(ad)){
                    //quantitycheck goes here
                }
                else{
                    List<Long> tmpAds = cart.getAds();
                    tmpAds.add(ad);
                    cart.setAds(tmpAds);
                }
            }

        }
        else{
            cart = new Cart();
            cart.setAds(adIds);
            cart.setUserId(user_id);
        }
        Cart newCart = cartRepository.save(cart);
        return  newCart;
    }

    public List<Long> getCartItems(Long userId){
        Cart cart = cartRepository.findByUserId(userId);
        return ((cart!=null)?cart.getAds(): null);
    }

    public void deleteCart(Long userId){
        Cart cart = cartRepository.findByUserId(userId);
        List<Long> list = ((cart!=null)?cart.getAds(): null);
        for (Long item : list) {
            deleteItem(userId, item);
        }
    }

    public void deleteItem(Long userId, Long adId){
        Cart cart =cartRepository.findByUserId(userId);
        List<Long> tmp = cart.getAds();
        int removingIndex = 0;
        boolean matchFound = false;
        for(int i=0; i<tmp.size(); i++){
            if(tmp.get(i).equals(adId)){
                matchFound = true;
                removingIndex = i;
                break;
            }
        }
        if(matchFound) tmp.remove(removingIndex);
        cart.setAds(tmp);
        cartRepository.save(cart);
    }

}