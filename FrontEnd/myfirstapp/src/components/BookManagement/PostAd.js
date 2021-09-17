import React, {Component} from "react";
import {connect} from "react-redux";
import {getAllBooks, getAllCategories, postAd} from "../../actions/bookActions";
import axios from "axios";
import {GET_BOOKS} from "../../actions/types";


class PostAd extends Component{
    constructor() {
        super();
        this.state = {
            book: "",
            condition: "",
            price:"",
        }
        this.onChange = this.onChange.bind(this);
        this.onSubmit = this.onSubmit.bind(this);

    }

    onSubmit(e) {
        e.preventDefault();
        const AdRequest = {
            book: this.state.book,
            condition: this.state.condition,
            price: this.state.price,
            userId: this.props.user.id
        };
        this.props.postAd(AdRequest);
    }

    onChange(e) {
        this.setState({ [e.target.name]: e.target.value });
    }


    async componentDidMount() {
        this.props.getAllBooks();
        this.props.getAllCategories()
    }

    render(){
        return(

            <div className="postAd">
                <div className="container">
                    <div className="row">
                        <div className="col-md-8 m-auto">
                            <h5 className="display-4 text-center">Post an Ad.</h5>
                            <hr />
                            <form onSubmit={this.onSubmit}>
                                <div className="form-group">
                                    <select className="form-control">
                                        {
                                            this.props.books.map((value)=>{
                                                return <option value={value.isbn} key={value}>{value.isbn}-{value.name}</option>
                                            })
                                        }
                                    </select>
                                </div>
                                    <div className="form-group">

                                    <select className="form-control">
                                        {
                                            this.props.categories.map((value)=>{
                                                return <option value={value} key={value}>{value}</option>
                                            })
                                        }
                                    </select>
                                </div>
                                <div className="form-check form-check-inline">
                                    <input className="form-check-input" type="radio" name="inlineRadioOptions" id="new"
                                           value="new" disabled={(this.props.user.type === "Customer")? true:false}/>
                                        <label className="form-check-label" htmlFor="new">New</label>
                                </div>
                                <div className="form-check form-check-inline">
                                    <input className="form-check-input" name="inlineRadioOptions" type="radio" id="old"
                                           value="old" />
                                        <label className="form-check-label" htmlFor="old">Old</label>
                                </div>
                            <br />
                                <div className="form-inline">
                                    <label htmlFor="price" className="mr-sm-2">Price</label>
                                    <input type="number" min="1" step="any" className="form-control mr-sm-2 mb-2" id="price"
                                            placeholder="price"/>
                                </div>

                                <input
                                    type="submit"
                                    className="btn btn-primary btn-block mt-4"
                                />
                            </form>

                </div>
                </div>
                </div>
            </div>
        )

    }
}

const mapStateToProps = (state) => {
    return({
        books: state.book.books,
        user: state.security.user,
        categories: state.book.allCategories
    })
};

export default connect(
    mapStateToProps,
    {getAllBooks, postAd, getAllCategories}
)(PostAd);