import React, { Component } from "react";
import ActionSlider from "./ActionSlider";

class BookCatalog extends Component {
  render() {
    return (
      <div className="bookCatalog">
        <div className="light-overlay landing-inner text-dark">
          <div className="container">
            <div className="col-md-12 text-center">
              <h1 className="display-3 mb-4">The Great Book Catalog</h1>
            </div>
            <br />
            <hr />
            <div className="col-md-12 align-left">
              <h3>Thriller</h3>
              <ActionSlider />
              <br />
              <hr />
              <h3>Sci-Fi</h3>
              <ActionSlider />
            </div>
          </div>
        </div>
      </div>
    );
  }
}

export default BookCatalog;
