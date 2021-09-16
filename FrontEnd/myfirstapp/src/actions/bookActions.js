import axios from "axios";
import {GET_ERRORS, GET_BOOKS, GET_BOOK, SET_CURRENT_USER, GET_REVIEWS} from "./types";
import setJWTToken from "../securityUtils/setJWTToken";
import jwt_decode from "jwt-decode";

export const createBook = (newBook, history) => async dispatch => {
    try {
        const res = await axios.post("http://localhost:8081/api/books/create", newBook);
        history.push("/dashboard");
    } catch (err) {
        dispatch({
            type: GET_ERRORS,
            payload: err.response.data
        });
    }
};

export const getAllBooks = () => async dispatch => {
    const res = await axios.get("http://localhost:8081/api/books/bookCatalog");
    console.log("request")
    dispatch({
        type: GET_BOOKS,
        payload: res.data
    });
};

export const getBook = (bookId) => async dispatch => {
    const res = await axios.get(`http://localhost:8081/api/books/book_by_id/${bookId}`);
    dispatch({
        type: GET_BOOK,
        payload: res.data
    });
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

export const getBookReviews = (bookId) => async dispatch => {
    const res = await axios.get(`http://localhost:8082/api/reviews/review_by_id/${bookId}`);
    dispatch({
        type: GET_REVIEWS,
        payload: res.data
    });
};
