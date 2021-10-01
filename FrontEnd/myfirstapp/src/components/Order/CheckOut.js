import React, { Component } from 'react'
import PropTypes from "prop-types";
import { connect } from "react-redux";
import PayPalCheckout from "./PayPalCheckout";
import {saveOrder, getShoppingCart, getSellers} from "../../actions/orderActions";
import classnames from "classnames";


class CheckOut extends Component {
    constructor(){
        super();

        this.state = {
            address_line_1: "",
            address_line_2: "",
            admin_area_2: "", // city
            admin_area_1: "", // state
            postal_code: "",
            country_code: "",
            errors: {}
        };
        this.onChange = this.onChange.bind(this);
        this.onSubmit = this.onSubmit.bind(this);
    }

    componentWillReceiveProps(nextProps){
        if (nextProps.errors){
            this.setState ({
                errors: nextProps.errors
            });

        }
    }

    onSubmit(e) {
        e.preventDefault();
        const address = {
            address_line_1: this.state.address_line_1,
            address_line_2: this.state.address_line_2,
            admin_area_2: this.state.admin_area_2,
            postal_code: this.state.postal_code,
            country_code: this.state.country_code
        };

        this.props.createNewUser(address, this.props.history);
    }

    onChange(e) {
        this.setState({ [e.target.name]: e.target.value });
    }
    render() {
        const { errors } = this.state;
        return (
            <div className="container">
                <div className="row">
                    <div className="col">
                        <h2 className="display-5 text-center">Shipping Information</h2>
                        <br/>
                            <div className="form-group">
                                <input
                                    type="text"
                                    className= {classnames("form-control form-control-lg", {
                                        "is-invalid": errors.name
                                    }) }
                                    placeholder="Address Line 1"
                                    name="address_line_1"
                                    value= {this.state.address_line_1}
                                    onChange={this.onChange}
                                    required
                                />
                                {errors.name && (
                                    <div className= "invalid-feedback">{errors.name}</div>
                                )}
                            </div>
                            <div className="form-group">
                                <input
                                    type="text"
                                    className="form-control form-control-lg"
                                    placeholder="Address Line 2"
                                    name="address_line_2"
                                    value= {this.state.address_line_2}
                                    onChange={this.onChange}
                                />
                            </div>
                            <div className="form-group">
                                <input
                                    type="text"
                                    className="form-control form-control-lg"
                                    placeholder="City"
                                    name="admin_area_2"
                                    value= {this.state.admin_area_2}
                                    onChange={this.onChange}
                                />
                            </div>
                            <div className="form-group">
                                <select defaultValue={this.state.admin_area_1}
                                        onChange={this.onChange}
                                        className="form-control form-control-lg"
                                >
                                    <option value="ACT">Australian Capital Territory</option>
                                    <option value="NSW">New South Wales</option>
                                    <option value="NT">Northern Territory</option>
                                    <option value="QLD">Queensland</option>
                                    <option value="SA">South Australia</option>
                                    <option value="VIC">Victoria</option>
                                    <option value="WA">Western Australia</option>
                                </select>
                            </div>
                            <div className="form-group">
                                <input
                                    type="number"
                                    className="form-control form-control-lg"
                                    placeholder="Postcode"
                                    name="postal_code"
                                    value= {this.state.postal_code}
                                    onChange={this.onChange}
                                />
                            </div>
                            <div className="form-group">
                                <input
                                    type="text"
                                    className="form-control form-control-lg"
                                    placeholder="Postcode"
                                    name="country_code"
                                    value= {"AU"}
                                    onChange={this.onChange}
                                />
                            </div>
                            <br/>
                            <PayPalCheckout checkout={this.props}/>
                    </div>
                    <div className="col">
                        <h2 className="display-5 text-center">Order Information</h2>
                            Get order information from the cart, chuck to props send to paypal
                    </div>
                </div>
            </div>
        );
    }
}
CheckOut.propTypes = {
    createProject: PropTypes.func.isRequired
};

const mapStateToProps = (state) => {
    return{
        buyer: state.user,
        seller: state.seller,
        shoppingCart: state.shoppingCart
    }
};

export default connect(
    mapStateToProps,
    {saveOrder, getShoppingCart, getSellers}
)(CheckOut);
