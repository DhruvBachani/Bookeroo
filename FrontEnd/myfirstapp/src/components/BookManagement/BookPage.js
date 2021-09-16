import React, { Component } from "react";
import { connect } from "react-redux";
import bookCatalogActions from "../../actions/bookCatalogActions";
import ProductDetails from "./ProductDetails";
import ProductReviews from "./ProductReviews";
import { getBookReviews } from "../../actions/bookCatalogActions";

class BookPage extends Component {
  constructor() {
    super();

    this.state = {
      book: [],
      reviews: [],
    };
  }

  componentDidMount(res, res2) {
    const bookID = this.props.match.params.bookId;
    bookCatalogActions.getBook(bookID).then((res) => {
      this.setState({ book: res.data });

      bookCatalogActions.getReviews(bookID).then((res2) => {
        this.setState({ reviews: res2.data });
      });

      this.props.getBookReviews(bookID);
    
    });
  }

  render() {
    return (
      <div className="bookCatalog">
        <div className="light-overlay landing-inner text-dark">
          <div className="container">
            <div className="row">
              <div className="col-md-12">
                <ProductDetails book={this.state.book} />
                <br />
                <ProductReviews reviews={this.state.reviews} />
              </div>
            </div>
          </div>
        </div>
      </div>
    );
  }
}

const mapStateToProps = state => ({
});

export default connect(
  mapStateToProps,
  { getBookReviews }
)(BookPage);
// export default BookPage;
