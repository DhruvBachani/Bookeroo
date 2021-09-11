import React, { Component } from "react";
import bookCatalogActions from "../../actions/bookCatalogActions";
import ProductDetails from "./ProductDetails";

class BookPage extends Component {
  constructor() {
    super();

    this.state = {
      book: [],
    };
  }

  componentDidMount(res) {
    bookCatalogActions.getBook(this.props.match.params.bookId).then((res) => {
      this.setState({ book: res.data });
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
              </div>
            </div>
          </div>
        </div>
      </div>
    );
  }
}

export default BookPage;
