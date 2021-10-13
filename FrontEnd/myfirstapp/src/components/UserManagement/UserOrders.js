import React, { Component } from "react";
import {connect} from "react-redux";
import {getUserTransactions} from "../../actions/orderActions";

class UserOrders extends Component {
    componentDidMount() {
        this.props.getUserTransactions(this.props.security.user.id)
    }

    // Change value of the id based on which button was pressed
    onChange(e) {
        this.setState({ [e.target.name]: e.target.value });
    }

    render() {
        console.log(this.props.userTransactions)
        return (
            <div className="requests">
                <div className="container">
                    <div className="col-md-12 text-center">
                        <h1 className="display-4 mb-4">Your orders</h1>
                    </div>
                    <br />
                    <hr />
                    <div className="col-md-12 align-left">
                    </div>
                </div>
                <div className="container">
                    <div className="row">
                        {this.props.userTransactions.map((transaction) => {
                            return (
                                <div key={transaction.id} className="col mb-4">
                                    <div className="card">
                                        <div className="card-body">
                                            {/* Publisher/Shop Owner Information */}
                                            <h5 className="card-title">OrderID: {transaction.id} </h5>
                                            <h5 className="card-title">Paypal OrderID: {transaction.payPalOrderId} </h5>
                                            <h6 className="card-subtitle mb-2 text-muted"> Date Ordered: {transaction.create_At} </h6>
                                            <br />
                                            <h5 className="card-text"> Books </h5>
                                            <hr/>
                                            {transaction.purchases.map((book) => {
                                                return (
                                                    <div key={book.id} class="row">
                                                    {/* TODO: get book link maybe */}
                                                    <p className="card-text" className="col"> Title: Get book object </p>
                                                    <p className="card-text" class="col"> ISBN: {book.isbn} </p>
                                                    <p className="card-text" class="col"> Condition: {book.condition} </p>
                                                    <p className="card-text" class="col"> Quantity: {book.quantity} </p>
                                                    {/* TODO: get seller name? */}
                                                    <p className="card-text" class="col"> Seller: {book.seller} </p>
                                                    <p className="card-text" class="col"> Delivery Status: {book.deliveryStatus} </p>
                                                    <p className="card-text" class="col"> Price: ${book.cost} </p>
                                                        <hr/>
                                                    </div>
                                            );})}
                                            <p className="card-text">  Address: {transaction.address} </p>
                                            <p className="card-text">  Total Cost: ${transaction.totalCost} </p>
                                            <p className="card-text"> Refund Button </p>
                                        </div>
                                    </div>
                                </div>
                            );
                        })}
                    </div>
                </div>

            </div>
        )

    }
}

const mapStateToProps = (state) => {
    return{
        userTransactions: state.order.userTransactions,
        security: state.security
    }
};

export default connect(
    mapStateToProps,
    { getUserTransactions }
)(UserOrders);


