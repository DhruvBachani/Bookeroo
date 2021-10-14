import axios from "axios";
import { GET_ERRORS, GET_BOOK, GET_REVIEWS } from "./types";


class BookCatalogService {
    getAllBooks() {
        return axios.get("http://bookeroobookservice-env-1.eba-timxttf9.us-east-2.elasticbeanstalk.com/api/books/bookCatalog");
    }

    getBook(bookId) {
        return axios.get(`http://bookeroobookservice-env-1.eba-timxttf9.us-east-2.elasticbeanstalk.com/api/books/book_by_id/${bookId}`);
    }

    getReviews(bookId) {
        return axios.get(`http://localhost:8082/api/reviews/review_by_id/${bookId}`);
    }
}
export default new BookCatalogService();


export const createBook = (newBook, history) => async (dispatch) => {

    try {
        await axios.post("http://bookeroobookservice-env-1.eba-timxttf9.us-east-2.elasticbeanstalk.com/api/books/create", newBook);
        history.push("/manageBooks");
    } catch (err) {
        dispatch({
            type: GET_ERRORS,
            payload: err.response.data,
        });
    }

};
