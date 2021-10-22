import React, {Component} from "react";
import axios from "axios";
import Table from "../Layout/Table"
import {connect} from "react-redux";
import {login} from "../../actions/securityActions";
import {Button} from "react-bootstrap";


class Sellers extends Component {
    constructor(props) {
        super(props);
        this.state = {
            sellers: [],
            condition: "new",
            isbn: this.props.match.params.isbn,
            selectedAds: []
        }
        this.onChange = this.onChange.bind(this);
        this.addToCart = this.addToCart.bind(this);
    }


    componentDidMount() {
        axios.get("http://localhost:8081/api/books/" + this.state.isbn + "/allAds?condition=" + this.state.condition).then((res) => {
            this.setState({sellers: res.data})
        })
    }

    async onChange(e) {
        await axios.get("http://localhost:8081/api/books/" + this.state.isbn + "/allAds?condition=" + e.target.value).then((res) => {
            this.setState({sellers: res.data})
        })

    }

    addToCart(e) {
        const res = axios.post(`http://localhost:8081/api/books/addToCart`, this.state.selectedAds).then(() => {
            this.props.history.push("/cart")
        });
    }

    columns = [{
        Header: 'Available Options',
        columns:
            [
                {
                    Header: 'Seller-id',
                    accessor: 'userId'
                },

                {
                    Header: 'Price',
                    accessor: 'price'
                }

            ]
    }]


    render() {
        return (
            <div>
                <select className="form-control" onChange={this.onChange}>
                    <option value="new">New</option>
                    <option value="old">Old</option>
                </select>
                {/*{this.state.sellers.map((value)=>{*/}
                {/*    return <div key={value.userId}>Seller's user id-{value.userId} Seller's price-{value.price}</div>*/}
                {/*})}*/}

                <Table columns={this.columns} data={this.state.sellers}
                       onRowSelect={rows => this.state.selectedAds = rows}/>
                <Button onClick={this.addToCart}>Add to cart</Button>
            </div>
        )
    }


}


export default Sellers;

