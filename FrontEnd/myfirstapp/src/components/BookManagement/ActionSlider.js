import React, { Component } from "react";
import BookCard from "./BookCard";
import bookCatalogActions from "../../actions/bookCatalogActions";

class ActionSlider extends Component {
  constructor() {
    super();

    this.state = {
      allBooks: [],
      arr: [],
    };
  }

  componentDidMount(res) {
    bookCatalogActions.getAllBooks().then((res) => {
      this.setState({ allBooks: res.data });
    });
  }

  render() {
    return (
      <div className="bookCatalog">
        <div className="sliderContainer">
          {this.state.allBooks.map((book) => (
            <div key={book.isbn}>
              <BookCard arr={book} />
            </div>
          ))}
        </div>
      </div>
    );
  }
}

export default ActionSlider;

// {this.state.allBooks.map((book) => (
//   <div key={book.isbn}>
//     <BookCard arr={book} />
//   </div>
// ))}

// <div className="book">Item</div>
//           <div className="book">Item</div>
//           <div className="book">Item</div>
//           <div className="book">Item</div>
//           <div className="book">Item</div>
//           <div className="book">Item</div>
//           <div className="book">Item</div>
//           <div className="book">Item</div>
//           <div className="book">Item</div>
//           <div className="book">Item</div>
//           <div className="book">Item</div>
//           <div className="book">Item</div>
