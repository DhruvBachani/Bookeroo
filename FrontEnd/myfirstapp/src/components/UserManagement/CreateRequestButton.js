import React from 'react'
import {link, Link} from "react-router-dom";

const CreateRequestButton=() => {
    console.log(this);
    return (
        <React.Fragment>
            <Link to="/request"
                  className="btn btn-lg btn-info">
                Show All Request
            </Link>
        </React.Fragment>
    )
};
export default CreateRequestButton;