import React, { Component } from "react";

class ProductReviews extends Component {
  render() {
    return (
      <div className="bookCatalog">
        <div className="container">
          <div className="row">
            <div className="col-md-12">
              <h2>Reviews</h2>
              {this.props.reviews.map((review) => (
                <div key={review.id}>
                  <h5>
                    {review.userName}<br/>
                  </h5>
                  <p>{review.review}<br/></p>
                </div>
              ))}
            </div>
          </div>
        </div>
      </div>
    );
  }
}

export default ProductReviews;
