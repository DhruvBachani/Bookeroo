import React, { Component } from "react";
import { connect } from "react-redux";
import ProductDetails from "./ProductDetails";
import ProductReviews from "./ProductReviews";
import { getBook, getBookReviews } from "../../actions/bookActions";
import {Link} from "react-router-dom";

class BookPage extends Component {
  componentDidMount() {
    const bookID = this.props.match.params.isbn;
    this.props.getBook(bookID);
    this.props.getBookReviews(bookID);
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

// componentDidMount(res, res2) {
//   const bookID = this.props.match.params.bookId;
// bookCatalogActions.getBook(bookID).then((res) => {
//   this.setState({ book: res.data });

//   bookCatalogActions.getReviews(bookID).then((res2) => {
//     this.setState({ reviews: res2.data });
//   });

//     this.props.getBookReviews(bookID);

//   });
// }
// componentDidMount(res) {
//   bookCatalogActions.getBook(this.props.match.params.bookId).then((res) => {
//     this.setState({ book: res.data });
//   });
// }
