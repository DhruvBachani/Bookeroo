import React, { Component } from "react";
import {connect} from "react-redux";
import {getAllTransactions} from "../../actions/orderActions";
import { CSVLink } from "react-csv";

// const headers = [
//     { label: "User ID", key: "userId" },
//     { label: "Date Ordered", key: "create_At" },
//     { label: "Order ID", key: "id" },
//     { label: "PayPal Order ID", key: "payPalOrderId" },
//     { label: "PayPal Order Email", key: "payPalEmail" },
//     { label: "Books", key: "purchases" },
//     // { label: "Condition", key: "isbn" },
//     // { label: "ISBN", key: "description" },
//     // { label: "Quantity", key: "isbn" },
//     // { label: "Price", key: "image" },
//     { label: "Shipping Address", key: "address" },
//     { label: "Total Cost", key: "totalCost" },
//     { label: "Delivery Status", key: "isbn" }
//
// ];



class AdminOrders extends Component {

    constructor() {
        super();

        this.csvLinkEl = React.createRef();

        this.onChange = this.onChange.bind(this);
        this.downloadTransactionReport = this.downloadTransactionReport.bind(this);

    }

    componentDidMount() {
        this.props.getAllTransactions()
    }

    // Change value of the id based on which button was pressed
    onChange(e) {
        this.setState({ [e.target.name]: e.target.value });
    }

    downloadTransactionReport = async () => {
        console.log(this.props.allTransactions);
        this.csvLinkEl.current.link.click();
    }

    render() {
        console.log(this.props.allTransactions);
        return (
            <div className="requests">
                <div className="container">
                    <div className="col-md-12 text-center">
                        <h1 className="display-4 mb-4">All orders</h1>
                        <input type="button" className="btn btn-lg btn-info" value="Download CSV All Transactions" onClick={this.downloadTransactionReport} />
                        <CSVLink
                            // headers={headers}
                            filename="all_transactions.csv"
                            data={this.props.allTransactions}
                            ref={this.csvLinkEl}
                        />
                    </div>
                    <br />
                    <hr />
                    <div className="col-md-12 align-left">
                    </div>
                </div>
                <div className="container">
                    <div className="row row-cols-1 row-cols-sm-1 row-cols-md-2 row-cols-lg-2 row-cols-xl-2">
                        {this.props.allTransactions.map((transaction) => {
                            return (
                                <div key={transaction.id} className="col mb-4">
                                    <div className="card">
                                        <div className="card-body">
                                            {/* Publisher/Shop Owner Information */}
                                            <h5 className="card-title">OrderID: {transaction.id} </h5>
                                            <h5 className="card-title">Paypal OrderID: {transaction.payPalOrderId} </h5>
                                            <h6 className="card-subtitle mb-2 text-muted"> Date Ordered: {transaction.createAt} </h6>
                                            <br />
                                            <h5 className="card-text"> Books </h5>
                                            <hr/>
                                            {transaction.purchases.map((book) => {
                                                return (
                                                    <div key={book.id}>
                                                    {/* TODO: get book link maybe */}
                                                    <p className="card-text"> Books: {book.isbn} </p>
                                                    <p className="card-text"> Condition: {book.condition} </p>
                                                    <p className="card-text"> Quantity: {book.quantity} </p>
                                                    {/* TODO: get seller name? */}
                                                    <p className="card-text"> Seller: {book.seller} </p>
                                                    <p className="card-text"> Delivery Status: {book.deliveryStatus} </p>
                                                    <p className="card-text"> Price: {book.cost} </p>
                                                        <hr/>
                                                    </div>
                                            );})}
                                            <p className="card-text">  Address: {transaction.address} </p>
                                            <p className="card-text">  Total Cost: {transaction.totalCost} </p>
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
        allTransactions: state.order.allTransactions,
        security: state.security
    }
};

export default connect(
    mapStateToProps,
    { getAllTransactions }
)(AdminOrders);


