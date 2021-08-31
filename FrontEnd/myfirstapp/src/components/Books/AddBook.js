import React, { Component } from 'react'
import PropTypes from "prop-types";
import { connect } from "react-redux";
import { createBook } from "../../actions/bookActions";


class AddBook extends Component {
    constructor(){
        super();

        this.state= {
            name: ""
        };
        this.onChange = this.onChange.bind(this);
        this.onSubmit = this.onSubmit.bind(this);

    }

    onChange(e){
        this.setState({[e.target.name]: e.target.value});
    }
    onSubmit(e){
        e.preventDefault();
        const newBook = {
            name: this.state.name
        }

        this.props.createBook(newBook, this.props.history);
    }
    render() {
        return (
            <div className="Book">
                <div className="container">
                    <div className="row">
                        <div className="col-md-8 m-auto">
                            <h5 className="display-4 text-center">Create / Edit Book form</h5>
                            <hr />
                            <form onSubmit={this.onSubmit}>
                                <div className="form-group">
                                    <input type="text" className="form-control form-control-lg "
                                           placeholder="Book Name"
                                           name="name"
                                           value= {this.state.name}
                                           onChange = {this.onChange}
                                    />
                                </div>

                                <input type="submit" className="btn btn-primary btn-block mt-4" />
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        )
    }
}
AddBook.propTypes = {
    createProject: PropTypes.func.isRequired
};

export default connect(
    null,
    { createBook }
)(AddBook);
