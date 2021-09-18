import React, { Component } from "react";
import { connect } from "react-redux";
import ProductDetails from "./ProductDetails";
import ProductReviews from "./ProductReviews";
import { getBook, getBookReviews } from "../../actions/bookActions";
import {Link} from "react-router-dom";

class BookPage extends Component {
  componentDidMount() {
    const bookISBN = this.props.match.params.isbn;
    this.props.getBook(bookISBN);
    this.props.getBookReviews(bookISBN);
  }

  render() {
    return (
      <div className="bookCatalog">
        <div className="container">
          <div className="row">
            <div className="col-md-12">
              <ProductDetails book={this.props.book} />
              <br />
              <ProductReviews reviews={this.props.reviews} />
            </div>
            <Link className="btn btn-lg btn-primary mr-2" to={window.location.pathname+"/sellers"}>
              Choose Seller
            </Link>
          </div>
        </div>
      </div>
    );
  }
}

const mapStateToProps = (state) => {
  return {
    book: state.book.book,
    reviews: state.review.bookReviews,
  };
};

export default connect(mapStateToProps, { getBook, getBookReviews })(BookPage);
