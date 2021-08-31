import axios from "axios";


class BookCatalogService {

    getAllBooks() {
        return axios.get("http://localhost:8081/api/books/bookCatalog");
    }

}
export default new BookCatalogService()