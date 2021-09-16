import React, { Component } from "react";
// import BookCarousal from "./BookCarousal";
import {connect} from "react-redux";
import {login} from "../../actions/securityActions";
import {getAllBooks} from "../../actions/bookActions";
import ActionSlider from "./ActionSlider";

class BookCatalog extends Component {
  componentDidMount() {
    this.props.getAllBooks()
  }

  render() {

    return (
      <div className="bookCatalog">
          <div className="container">
            <div className="col-md-12 text-center">
              <h1 className="display-3 mb-4">The Great Book Catalog</h1>
            </div>
            <br />
            <hr />
            <div className="col-md-12 align-left">
              <h3>Thriller</h3>
              <br />
              <ActionSlider />
              <hr />
              <h3>Sci-Fi</h3>
\            </div>
          </div>
      </div>
    );
  }
}

const mapStateToProps = (state) => {
  return{
    books: state.book.books
  }
};

export default connect(
    mapStateToProps,
    { getAllBooks }
)(BookCatalog);

// <BookCarousal books={this.props.books}/>