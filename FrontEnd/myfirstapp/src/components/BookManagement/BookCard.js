import React, { Component } from "react";
import { link, Link } from "react-router-dom";


class BookCard extends Component {
  render() {
    return (
          <div className="book">

              <h5>
                {this.props.book.name} <br />
              </h5>

          </div>

    );
  }
}

export default BookCard;

// <a className="book-title-text" href="/bookPage">
// <h5>
//   {this.props.arr.name} <br />
// </h5>
// </a>;

// {this.state.allBooks.map((key) => (
//   <div key={key.isbn}>
// <h5>
//   Book name: {key.name} <br />
//   Book author: {key.author} <br />
//   Book category: {key.category} <br />
//   Book ISBN: {key.isbn} <br />
// </h5>
//   </div>
// ))}
