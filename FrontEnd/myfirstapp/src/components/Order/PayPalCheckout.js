import axios from "axios";
import React from "react";
import ReactDOM from "react-dom";


const PayPalButton = window.paypal.Buttons.driver("react", { React, ReactDOM });

// This is my attempt at v2 paypal before I gave up and worked on v1 on the backend :(
// It is here for prosperity
// Not to be confused, v1 paypal has NOT been integrated with the frontend
function PayPalCheckout() {
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
                    address_line_1: "123McHigh Street", // etc "[[${user.addressLine1}]]"
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
            purchase_units: [{
                amount: {
                    currency_code: "AUD",
                    value: "200",
                    breakdown: {
                        item_total: {  /* Required when including the `items` array */
                            currency_code: "AUD",
                            value: "200" //values need to equal
                        }
                    }
                },
                items: [
                    {
                        name: "First Product Name",
                        description: "Optional descriptive text..",
                        sku: "1234", // The stock keeping unit (SKU) for the item. In our case, the book id
                        unit_amount: {
                            currency_code: "AUD",
                            value: "50"
                        },
                        quantity: "2"
                    },
                    {
                        name: "Second Product Name",
                        description: "Weird",
                        sku: "1234",
                        unit_amount: {
                            currency_code: "AUD",
                            value: "50"
                        },
                        quantity: "2"
                    }
                ]
            }]
        });
    }
    async function _onApprove(data, actions) {
        let order = await actions.order.capture();
        console.log(order);
        let orderId = order.id;
        let orderStatus = order.status;
        alert("Thanks for purchasing! OrderID: " + orderId + " Status: "+ orderStatus);
        await axios.post("http://localhost:8000/api/saveOrder/", orderId);
        //how do I save the book now..
        return order;
    }
    function _onError(err) {
        console.log(err);
        alert(err);
    }

    return (
        <div className="App">
            <PayPalButton
                createOrder={(data, actions) => _createOrder(data, actions)}
                onApprove={(data, actions) => _onApprove(data, actions)}
                onCancel={() => _onError("Payment has been cancelled")}
                onError={(err) => _onError("Something wrong with the order details")}
            />
        </div>
    );
}
export default PayPalCheckout;