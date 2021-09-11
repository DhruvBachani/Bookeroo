import React, { Component } from "react";

class ProductDetails extends Component {
  render() {
    return (
      <div className="bookCatalog">
        <div className="light-overlay landing-inner text-dark">
          <div className="container">
            <div className="row">
              <div className="col-md-12">
                <div className="book-cover-image-container">
                  <img
                    src=""
                    alt="Image not found"
                    onError={(e) => {
                      e.target.onerror = null;
                      e.target.src = "/default.jpg";
                    }}
                  />
                </div>
                <h4>{this.props.book.name}</h4>
              </div>
            </div>
          </div>
        </div>
      </div>
    );
  }
}

export default ProductDetails;
