import axios from "axios";


class BookCatalogService {

    getAllBooks() {
        return axios.get("http://localhost:8081/api/catalog/allBooks");
    }

}
export default new BookCatalogService()