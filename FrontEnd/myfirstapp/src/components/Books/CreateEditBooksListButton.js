import React from 'react'
import {link, Link} from "react-router-dom";

 const CreateEditBooksListButton=() => {
    return (
        <React.Fragment>
        <Link to="/editBooksList"
        className="btn btn-lg btn-info">
        Edit Books
        </Link>
        </React.Fragment>
    )
};
export default CreateEditBooksListButton;