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
            <div className="row">
              <div className="col-md-12">
                <div className="book-cover-image-container">
                  <img src="" alt="Image not found" onError={
                    (e)=>{e.target.onerror = null; e.target.src="/default.jpg"}
                  }/>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    );
  }
}

export default BookPage;
