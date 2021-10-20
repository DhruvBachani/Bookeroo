import React from 'react'
import {Link} from "react-router-dom";

const CreateAllBooksButtons=() => {
    return (
        <React.Fragment>
            <Link to="/all-books"
                  className="btn btn-lg btn-info">
                All Books
            </Link>
        </React.Fragment>
    )
};
export default CreateAllBooksButtons;