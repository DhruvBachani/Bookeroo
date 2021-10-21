import axios from "axios";
import React, {Component} from "react";
import ReactDOM from "react-dom";
import {connect} from "react-redux";
import {saveOrder, getShoppingCart, getSellers} from "../../actions/orderActions";
import PropTypes from "prop-types";

const PayPalButton = window.paypal.Buttons.driver("react", { React, ReactDOM });

function PayPalCheckout(props) {
    console.log(props)
    function _createOrder(data, actions) {
        return actions.order.create({
            //   how do I bring data here..
            intent: 'CAPTURE',
            payer: {
                name: {
                    given_name: "John",
                    surname: "Doe"

                },
                address: {
                    address_line_1: "123 McHigh Street", // etc "[[${user.addressLine1}]]"
                    address_line_2: "",
                    admin_area_2: "Melbourne",
                    admin_area_1: "VIC",
                    postal_code: "3011",
                    country_code: "AU"
                },
                email_address: "sb-tazxs7384333@personal.example.com",
                phone: {
                    phone_type: "MOBILE",
                    phone_number: {
                        national_number: "0412345667"
                    }

                }
            },

            purchase_units: [
                {
                    reference_id : "1",
                    // payee: { // probably wont have, hassle to link sellers to paypal
                    //     email_address: "",
                    //     merchant_id: "",
                    // },
                    amount: {
                        currency_code: "AUD",
                        value: "200",
                        breakdown: {
                            item_total: {  /* Required when including the `items` array */
                                currency_code: "AUD",
                                value: "200" // values need to equal
                            }
                        }
                    },
                    items: [
                        {
                            name: "Book 1",
                            description: "New",
                            sku: "1234", // The stock keeping unit (SKU) for the item. In our case, the book id
                            unit_amount: {
                                currency_code: "AUD",
                                value: "50"
                            },
                            quantity: "2"
                        },
                        {
                            name: "Book 2",
                            description: "New",
                            sku: "1234",
                            unit_amount: {
                                currency_code: "AUD",
                                value: "50"
                            },
                            quantity: "2"
                        }
                    ]
                },
                {
                    reference_id : "2",
                    amount: {
                        currency_code: "AUD",
                        value: "50",
                        breakdown: {
                            item_total: {  /* Required when including the `items` array */
                                currency_code: "AUD",
                                value: "50" // values need to equal
                            }
                        }
                    },
                    items: [
                        {
                            name: "Book 3",
                            description: "Old",
                            sku: "1234", // The stock keeping unit (SKU) for the item. In our case, the book id
                            unit_amount: {
                                currency_code: "AUD",
                                value: "50"
                            },
                            quantity: "1"
                        }
                    ]
                }
            ],

        });
    }
    async function _onApprove(data, actions) {
        let order = await actions.order.capture();

        console.log(order);
        let orderId = order.id;
        const CheckOutRequest = {
            paypalOrderId: order.id,
            userId: props.checkout.security.user.id
        };
        let orderStatus = order.status;
        alert("Thanks for purchasing! OrderID: " + orderId + " Status: "+ orderStatus);
        await axios.post("http://localhost:8083/api/orders/checkout", CheckOutRequest);
        return order;
    }
    function _onError(message, err) {
        console.log(err);
        alert(message);
    }

    return (
        <div className="App">
            <PayPalButton
                createOrder={(data, actions) => _createOrder(data, actions)}
                onApprove={(data, actions) => _onApprove(data, actions)}
                onCancel={() => _onError("Payment has been cancelled")}
                onError={(err) => _onError("Something wrong with the order details", err)}
            />
        </div>
    );
}

export default (PayPalCheckout);
