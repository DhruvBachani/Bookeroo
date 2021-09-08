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
        await axios.post("http://localhost:8081/api/books/create", newBook);
        history.push("/manageBooks");
    } catch (err) {
        dispatch({
        type: GET_ERRORS,
        payload: err.response.data,
        });
    }

};
