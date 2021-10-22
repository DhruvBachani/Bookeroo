import React, { Component } from 'react'
import axios from "axios";
import CartItem from "./CartItem";

class Cart extends Component{
    constructor(props) {
        super(props);

        this.state = {
            cartItems: [],
            total: 0
        }

    }
    componentDidMount() {
        axios.get("http://localhost:8081/api/books/cartItems").then((res) => {
            this.setState({cartItems:res.data})
            var total  = 0;
            this.state.cartItems.map((item)=>{
                total+= item.price;
            })
            this.setState({total:total })

        })

    }

    render() {
        return(
            <div>
                {this.state.cartItems.map((item)=>{
                    return <CartItem item={item} />
                    }

                )}

                <div>Total: {this.state.total}</div>
            </div>
        )
    }

}

export default Cart;