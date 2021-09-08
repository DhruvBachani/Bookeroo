import axios from "axios";
import { GET_ERRORS, GET_PERSONS, GET_PERSON } from "./types";


class BookCatalogService {

    getAllBooks() {
        return axios.get("http://localhost:8081/api/books/bookCatalog");
    }

}
export default new BookCatalogService();


export const createBook = (newBook, history) => async (dispatch) => {

    try {
        const res = await axios.post("http://localhost:8081/api/", newBook);
        history.push("/dashboard");
    } catch (err) {
        dispatch({
        type: GET_ERRORS,
        payload: err.response.data,
        });
    }

};
