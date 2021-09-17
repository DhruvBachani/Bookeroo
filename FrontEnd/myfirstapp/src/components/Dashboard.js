import React, { Component } from 'react'
import { connect } from "react-redux";
import {logout} from "../actions/securityActions";
import CreateRequestButton from "./UserManagement/CreateRequestButton";
import PropTypes from "prop-types";


class Dashboard extends Component {
    constructor() {
        super();
        this.onChange = this.onChange.bind(this);
        this.onSubmit = this.onSubmit.bind(this);
    }

    componentDidMount() {
        if (!this.props.security.validToken) {
            this.props.history.push("/login");
        }
    }
    onSubmit(e) {
        e.preventDefault();
        this.props.logout();
    }

    onChange(e) {
        this.setState({ [e.target.name]: e.target.value });
    }

    render() {
        console.log(this);
        return (
            <div className="Dashboard">
            <div className="container">
                <div className="row">
                    <div className="col-md-12">
                        <h1 className="display-4 text-left">Dashboard</h1>
                        <br />
                        {(this.props.security.user.userType === "Admin") && <CreateRequestButton />}
                        <hr />
                        <form onSubmit={this.onSubmit}><button className="btn btn-lg btn-info"> Log out </button>
                        </form>
                        <hr />
                        <p> Name: {this.props.security.user.fullName}</p>
                        <p> Role: {this.props.security.user.userType}</p>
                        <p> Address: {this.props.security.user.address}</p>
                        <p> Phone Number: {this.props.security.user.phoneNumber}</p>
                        <br />
                        <br />
                        <hr />
                    </div>
                </div>
            </div>
        </div>

        )
    }
}

Dashboard.propTypes = {
    logout: PropTypes.func.isRequired,
    errors: PropTypes.object.isRequired,
    security: PropTypes.object.isRequired
};

const mapStateToProps = state => ({
    security: state.security,
    errors: state.errors
});

export default connect(
    mapStateToProps, {logout}
)(Dashboard);

