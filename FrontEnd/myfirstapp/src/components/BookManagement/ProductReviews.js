import React, { Component } from "react";
import { connect } from "react-redux";

class ProductReviews extends Component {
  // constructor() {
  //   super();
  // }

  // componentDidMount() {
    // if (this.props.bookReview.bookReviews) {
      // console.log(this.props.bookReview.bookReviews);
      // this.props.bookReview.bookReviews.map((review) => (
      //   console.log(review)
      // ))
    // }
  // }

  render() {
    return (
      <div className="bookCatalog">
        <div className="container">
          <div className="row">
            <div className="col-md-12">
              <h3>Reviews</h3>
              {this.props.reviews.map((review) => (
                <div key={review.id}>
                  <h4>
                    {review.rating} <br/>
                  </h4>
                </div>
              ))}
            </div>
          </div>
        </div>
      </div>
    );
  }
}

// export default ProductReviews;

const mapStateToProps = state => ({
  bookReview: state.bookReview
});

export default connect(
  mapStateToProps
)(ProductReviews);
