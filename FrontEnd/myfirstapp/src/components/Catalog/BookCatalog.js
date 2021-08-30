import React, { Component } from "react";

class BookCatalog extends Component {
  render() {
    return (
      <div className="bookCatalog">
        <div className="light-overlay landing-inner text-dark">
          <div className="container">
            <div className="row">
              <div className="col-md-12 text-center">
                <h1 className="display-3 mb-4">
                  New Books
                </h1>
              </div>
            </div>
          </div>
        </div>
      </div>
    );
  }
}

export default BookCatalog;