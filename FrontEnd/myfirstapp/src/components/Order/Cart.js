import React, { Component } from 'react'
import axios from "axios";
import CartItem from "./CartItem";
import {connect} from "react-redux";
import {getSellers, getShoppingCart, saveOrder} from "../../actions/orderActions";

class Cart extends Component{
    constructor(props) {
        super(props);
        this.state ={
            updated: false
        }
        this.onRemove = this.onRemove.bind(this)
    }

    componentDidMount() {
        this.props.getShoppingCart()

    }

    onRemove(){
        window.location.reload()
    }

    render() {
        return(
            <div>{this.props.cartItems.length>0 ?
       ( <div>
            {this.props.cartItems.map((item) => {
                return <CartItem item={item} key={item.id} onRemove={this.onRemove}/>
            })}
            <div>Total: {this.props.cartTotal}</div>
        </div>) :(
            <div style={{textAlign:"center"}}>
                <h3>Your Cart is empty ! Go get shopping :)</h3>
            </div>
                    )
            }</div>
        )
    }

}

const mapStateToProps = (state) => {
    return{
        cartItems: state.order.cartItems,
        cartTotal: state.order.cartTotal
    }
};

export default connect(
    mapStateToProps,
    {getShoppingCart }
)(Cart);
