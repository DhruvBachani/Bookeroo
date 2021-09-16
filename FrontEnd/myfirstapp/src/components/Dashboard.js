import React, { Component } from 'react'
import Person from './Persons/Person'
import CreatePersonButton from './Persons/CreatePersonButton';
import { connect } from "react-redux";
import {login} from "../actions/securityActions";


class Dashboard extends Component {

    constructor(props, context) {
        super(props, context);
        this.handleChangeOnUser = this.handleChangeOnUser.bind(this);
    }
    handleChangeOnUser(e){
        this.props.setState(e.target.id, e.target.value);
    }
    render() {
        const { userInfo } = this.props.security.user;
        console.log(this.props);
        return (
            <div className="Dashboard">
            <div className="container">
                <div className="row">
                    <div className="col-md-12">
                        <h1 className="display-4 text-left">Dashboard</h1>
                        <br />
                        <h1 className="display-4 text-right"></h1>
                       <CreatePersonButton />
                        <br />
                        <hr />
                        <Person/>
                    </div>
                </div>
            </div>
        </div>

        )
    }
}

const mapStateToProps = state => ({
    security: state.security,
    errors: state.errors
});

export default connect(
    mapStateToProps
)(Dashboard);

