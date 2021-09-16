import React, { Component } from "react";

class ProductDetails extends Component {
  render() {
    return (
      <div className="bookCatalog">
        <div className="light-overlay landing-inner text-dark">
          <div className="container">
            <div className="row">
              <div className="col-md-4">
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
              </div>
              <div className="col-md-4">
                <h4>Title: {this.props.book.name}</h4> <br />
                <h4>Author: {this.props.book.author}</h4> <br />
                <h4>ISBN: {this.props.book.isbn}</h4> <br />
                <h4>Category: {this.props.book.category}</h4> <br />
              </div>
            </div>
            <br /> <br />
            <div className="row>">
              <h4>Description: {this.props.book.description}</h4>
            </div>
            <br /> <hr />
          </div>
        </div>
      </div>
    );
  }
}

export default ProductDetails;

// <div className="col-md-4 text-align-center">
//   <h4>Price: ${this.props.book.price}</h4>
// </div>;
