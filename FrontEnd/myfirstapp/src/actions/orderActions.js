import axios from "axios";
import {GET_ERRORS, GET_ALL_TRANSACTIONS, GET_USER_TRANSACTIONS, GET_ALL_BOOK_TRANSACTIONS} from "./types";

export const getUserTransactions = (userId) => async dispatch => {
  try{
    const res = await axios.get(`http://localhost:8083/api/orders/user/${userId}`);
    console.log(res);
    dispatch({
      type: GET_USER_TRANSACTIONS,
      payload: res.data
    });
  }
  catch (err){
    dispatch ({
      type: GET_ERRORS,
      payload: err.response.data
    });

  }
};

export const getAllTransactions = () => async dispatch => {
  try{
    const res = await axios.get("http://localhost:8083/api/orders/user/all");
    dispatch({
      type: GET_ALL_TRANSACTIONS,
      payload: res.data
    });
  }
  catch (err){
    dispatch ({
      type: GET_ERRORS,
      payload: err.response.data
    });

  }
};

export const getAllPurchasedBooks = () => async dispatch => {
  try{
    const res = await axios.get("http://localhost:8083/api/orders/book/all");
    dispatch({
      type: GET_ALL_BOOK_TRANSACTIONS,
      payload: res.data
    });
  }
  catch (err){
    dispatch ({
      type: GET_ERRORS,
      payload: err.response.data
    });

  }
};

export const getShoppingCart = () => async dispatch => {
};

export const getSellers = () => async dispatch => {
};

export const saveOrder = () => async dispatch => {
};