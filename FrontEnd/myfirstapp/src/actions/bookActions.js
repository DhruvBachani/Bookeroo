import axios from "axios";
import {GET_ERRORS, GET_BOOKS, GET_BOOK, SET_CURRENT_USER, GET_CATEGORIES} from "./types";
import setJWTToken from "../securityUtils/setJWTToken";
import jwt_decode from "jwt-decode";

export const createBook = (book, history) => async dispatch => {
    try {
        const res = await axios.post(`http://localhost:8081/api/book/${this.props.name}`, book);
        history.push("/dashboard");
    } catch (err) {
        dispatch({
            type: GET_ERRORS,
            payload: err.response.data
        });
    }
};

export const postAd = (AdRequest, history) => async dispatch => {
    try {
        const res = await axios.post(`http://localhost:8081/api/book/postAd`, AdRequest);
        history.push("/bookCatalog");
    } catch (err) {
        dispatch({
            type: GET_ERRORS,
            payload: err.response.data
        });
    }
};

export const getAllBooks = () => async dispatch => {
    const res = await axios.get("http://localhost:8081/api/books/bookCatalog");
    dispatch({
        type: GET_BOOKS,
        payload: res.data
    });
};

export const getAllCategories = () => async dispatch => {
    const res = await axios.get("http://localhost:8081/api/books/allCategories");
    dispatch({
        type: GET_CATEGORIES,
        payload: res.data
    });
};

export const getBook = (id, history) => async dispatch => {
    try {
        const res = await axios.get(`http://localhost:8081/api/book/${id}`);
        dispatch({
            type: GET_BOOK,
            payload: res.data
        });
    } catch (error) {
        history.push("/dashboard");
    }
};

export const search = (searchForm, history) => async dispatch => {
    try {
        const res = await axios.post(`http://localhost:8081/api/books/search`, searchForm);
        dispatch({
            type: GET_BOOKS,
            payload: res.data
        });
    } catch (error) {

    }
};
