import React from 'react'
import {Button} from "react-bootstrap";
import axios from "axios";

function deleteItem(id){
    alert(id)
    axios.delete("http://localhost:8081/api/books/deleteCartItem", {data: {id}})
}

function CartItem ({item}){

    return(
        <div className="card" style={{flexDirection: "row",alignItems: "center", justifyContent:"space-around"}}>

            <div>
            <div>Ad-id: {item.id}</div>
            <div>Price: {item.price}</div>
            </div>
            <Button  onClick={()=>deleteItem(item.id)}/>
        </div>
    )
}

export default CartItem