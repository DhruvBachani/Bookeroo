import React, { Component } from "react";
import bookCatalogActions from "../../actions/bookCatalogActions";

class BookPage extends Component {
  constructor() {
    super();

    this.state = {
      allBooks: [],
      arr: [],
    };
  }

  componentDidMount(res) {
    bookCatalogActions.getAllBooks().then((res) => {
      console.log(res.data);
      this.setState({ allBooks: res.data });
    });
  }

  render() {
    return (
      <div className="bookCatalog">
        <div className="light-overlay landing-inner text-dark">
          <div className="container">
            <div className="col-md-12 text-center">
              This is the book page
            </div>
          </div>
        </div>
      </div>
    );
  }
}

export default BookPage;
