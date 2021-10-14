import axios from "axios";
import {GET_ERRORS, GET_BOOKS, GET_BOOK, GET_REVIEWS, GET_CATEGORIES} from "./types";


export const createBook = (newBook, history) => async dispatch => {
    try {
        const res = await axios.post("http://bookeroobookservice-env-1.eba-timxttf9.us-east-2.elasticbeanstalk.com/api/books/create", newBook);
        history.push("/bookCatalog");
    } catch (err) {
        dispatch({
            type: GET_ERRORS,
            payload: err.response.data
        });
    }
};

export const postAd = (AdRequest, history) => async dispatch => {
    try {
        const res = await axios.post(`http://bookeroobookservice-env-1.eba-timxttf9.us-east-2.elasticbeanstalk.com/api/books/createAd`, AdRequest);
        history.push("/bookCatalog");

        dispatch({
            type: GET_ERRORS,
            payload: {}
        });
    } catch (err) {
        dispatch({
            type: GET_ERRORS,
            payload: err.response.data
        });
    }
};

export const getAllBooks = () => async dispatch => {
    const res = await axios.get("http://bookeroobookservice-env-1.eba-timxttf9.us-east-2.elasticbeanstalk.com/api/books/bookCatalog");
    dispatch({
        type: GET_BOOKS,
        payload: res.data
    });
};

export const getAllCategories = () => async dispatch => {
    const res = await axios.get("http://bookeroobookservice-env-1.eba-timxttf9.us-east-2.elasticbeanstalk.com/api/books/allCategories");
    dispatch({
        type: GET_CATEGORIES,
        payload: res.data
    });
};

export const getBook = (isbn, history) => async dispatch => {
        const res = await axios.get(`http://bookeroobookservice-env-1.eba-timxttf9.us-east-2.elasticbeanstalk.com/api/books/${isbn}`);
        dispatch({
            type: GET_BOOK,
            payload: res.data
        });
};

export const search = (searchForm, history) => async dispatch => {
    try {
        const res = await axios.post(`http://bookeroobookservice-env-1.eba-timxttf9.us-east-2.elasticbeanstalk.com/api/books/search`, searchForm);
        dispatch({
            type: GET_BOOKS,
            payload: res.data
        });
    } catch (error) {

    }
};

export const getBookReviews = (bookISBN) => async dispatch => {
    const res = await axios.get(`http://bookerooreviewservice-env.eba-e42sq3p2.us-east-2.elasticbeanstalk.com/api/reviews/review_by_ISBN/${bookISBN}`);
    dispatch({
        type: GET_REVIEWS,
        payload: res.data
    });
};

export const addNewReview = (review) => async dispatch => {
    try {
        await axios.post(`http://bookerooreviewservice-env.eba-e42sq3p2.us-east-2.elasticbeanstalk.com/api/reviews/create`, review);
        dispatch({
            type: GET_ERRORS,
            payload: {}
        });
    } catch (err) {

    }
};