import React, { Component } from 'react'
import PropTypes from "prop-types";
import { connect } from "react-redux";
import {createOrder} from "../../actions/orderActions";


class CheckOut extends Component {
    constructor(){
        super();

        this.state= {

        };
        this.onChange = this.onChange.bind(this);
        this.onSubmit = this.onSubmit.bind(this);

    }

    onChange(e){
        this.setState({[e.target.name]: e.target.value});
    }
    onSubmit(e){
        e.preventDefault();
        const newOrder = {

        }

        this.props.createPerson(newOrder, this.props.history);
    }
    render() {
        return (
            <div className="Person">
                <div className="container">
                    <div className="row">
                        <div className="col-md-8 m-auto">
                            <h5 className="display-4 text-center">Order form</h5>
                            <hr />
                            <form onSubmit={this.onSubmit}>
                                {/*<div className="form-group">*/}
                                {/*    <input type="text" className="form-control form-control-lg "*/}
                                {/*           placeholder="Person Name"*/}
                                {/*           name="name"*/}
                                {/*           value= {this.state.name}*/}
                                {/*           onChange = {this.onChange}*/}
                                {/*    />*/}

                                {/*</div>*/}
                                {/*<div className="form-group">*/}
                                {/*    <input type="text" className="form-control form-control-lg"*/}
                                {/*           placeholder="Unique Person ID"*/}
                                {/*           name="personIdentifier"*/}
                                {/*           value= {this.state.personIdentifier}*/}
                                {/*           onChange = {this.onChange}*/}
                                {/*    />*/}
                                {/*</div>*/}

                                {/*<div className="form-group">*/}
                                {/*<textarea className="form-control form-control-lg"*/}
                                {/*          placeholder="Person Description"*/}
                                {/*          name = "desc"*/}
                                {/*          value= {this.state.desc}*/}
                                {/*          onChange = {this.onChange}*/}
                                {/*/>*/}


                                {/*</div>*/}
                                {/*<h6>Start Date</h6>*/}
                                {/*<div className="form-group">*/}
                                {/*    <input type="date" className="form-control form-control-lg"*/}
                                {/*           name="start_date"*/}
                                {/*           value= {this.state.start_date}*/}
                                {/*           onChange = {this.onChange}*/}
                                {/*    />*/}
                                {/*</div>*/}
                                {/*<h6>Estimated End Date</h6>*/}
                                {/*<div className="form-group">*/}
                                {/*    <input type="date" className="form-control form-control-lg"*/}
                                {/*           name="end_date"*/}
                                {/*           value= {this.state.end_date}*/}
                                {/*           onChange = {this.onChange}*/}
                                {/*    />*/}
                                {/*</div>*/}

                                <input type="submit" className="btn btn-primary btn-block mt-4" />
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        )
    }
}
CheckOut.propTypes = {
    createProject: PropTypes.func.isRequired
};

export default connect(
    null,
    { createOrder }
)(CheckOut);
