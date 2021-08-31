import axios from "axios";
import { GET_ERRORS, GET_BOOKS, GET_BOOK } from "./types";

export const createBook = (book, history) => async dispatch => {
    try {
        const res = await axios.post(`http://localhost:8080/api/book/${this.props.name}`, book);
        history.push("/dashboard");
    } catch (err) {
        dispatch({
            type: GET_ERRORS,
            payload: err.response.data
        });
    }
};
export const getBooks = () => async dispatch => {
    const res = await axios.get("/api/catalog");
    dispatch({
        type: GET_BOOKS,
        payload: res.data
    });
};

export const getBook = (id, history) => async dispatch => {
    try {
        const res = await axios.get(`/api/book/${id}`);
        dispatch({
            type: GET_BOOK,
            payload: res.data
        });
    } catch (error) {
        history.push("/dashboard");
    }
};
