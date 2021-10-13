import React, { Component } from "react";
import "./App.css";
import Dashboard from "./components/Dashboard";
import Header from "./components/Layout/Header";
import "bootstrap/dist/css/bootstrap.min.css";
import {BrowserRouter as Router, Redirect, Route} from "react-router-dom";
import AddPerson from "./components/Persons/AddPerson";

import CheckOut from "./components/Order/CheckOut";
import UserOrders from "./components/UserManagement/UserOrders";
import AdminOrders from "./components/UserManagement/AdminOrders";

import { Provider } from "react-redux";
import store from "./store";

import Landing from "./components/Layout/Landing";
import Register from "./components/UserManagement/Register";
import Login from "./components/UserManagement/Login";

import BookCatalog from "./components/BookManagement/BookCatalog";
import BookPage from "./components/BookManagement/BookPage";
import ManageBooks from "./components/Books/ManageBooks";
import AddBook from "./components/Books/AddBook";
import AddReview from "./components/Books/AddReview"
import EditBooksList from "./components/Books/EditBooksList";


import jwt_decode from "jwt-decode";
import setJWTToken from "./securityUtils/setJWTToken";
import { SET_CURRENT_USER } from "./actions/types";
import { logout } from "./actions/securityActions";
import SecuredRoute from "./securityUtils/SecureRoute";
import SearchResults from "./components/BookManagement/SearchResults";
import Requests from "./components/UserManagement/Requests";
import AllBooks from "./components/UserManagement/AllBooks";


import Sellers from "./components/BookManagement/Sellers";
import PostAd from "./components/BookManagement/PostAd";

const jwtToken = localStorage.jwtToken;

if (jwtToken) {
  setJWTToken(jwtToken);
  const decoded_jwtToken = jwt_decode(jwtToken);
  store.dispatch({
    type: SET_CURRENT_USER,
    payload: decoded_jwtToken
  });

  const currentTime = Date.now() / 1000;
  if (decoded_jwtToken.exp < currentTime) {
    store.dispatch(logout());
    window.location.href = "/";
  }
}

class App extends Component {
  render() {
    return (
      <Provider store={store}>
        <Router>
          <div className="App">
            <Route component={Header} />
            {
              //Public Routes
            }

            <Route exact path="/">
              <Redirect to="bookCatalog" />
            </Route>
            <Route exact path="/register" component={Register} />
            <Route exact path="/login" component={Login} />

            <Route exact path="/bookCatalog" component={BookCatalog} />
            <Route exact path="/bookPage/:isbn" component={BookPage} />
            <Route exact path="/manageBooks" component={ManageBooks} />
            <Route exact path="/addBook" component={AddBook}/>
            <Route exact path="/editBooksList" component={EditBooksList}/>
            <Route exact path="/search-results" component={SearchResults}/>
            <Route exact path="/bookPage/:isbn/sellers" component={Sellers}/>

            <Route exact path="/addReview" render={props => (
                <AddReview {...props}/>
            )}>
            </Route>
            {
              //Private Routes
            }
            <SecuredRoute exact path="/dashboard" component={Dashboard} />
            <SecuredRoute exact path="/newAd" component={PostAd} />
            <SecuredRoute exact path="/all-books" component={AllBooks}/>

            <SecuredRoute exact path="/addPerson" component={AddPerson} />

            <SecuredRoute exact path="/requests" component={Requests} />
            <SecuredRoute exact path="/checkout" component={CheckOut} />
            {/* TODO: ask about :id */}
            <SecuredRoute exact path="/orders" component={UserOrders} />
            <SecuredRoute exact path="/orders/all" component={AdminOrders} />

          </div>
        </Router>
      </Provider>
    );
  }
}
export default App;