import React from 'react'
import {Link} from "react-router-dom";

const CreateUserOrderButton=() => {
    return (
        <React.Fragment>
            <Link to="/orders"
                  className="btn btn-lg btn-info">
                Orders
            </Link>
        </React.Fragment>
    )
};
export default CreateUserOrderButton;