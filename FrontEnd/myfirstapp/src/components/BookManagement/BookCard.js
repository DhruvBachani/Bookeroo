import React, { Component } from "react";

class BookCard extends Component {
  render() {
    return (
      <div className="book">
        <a className="book-title-text" href="/bookPage">
          <h5>
            {this.props.arr.name} <br />
          </h5>
        </a>
      </div>
    );
  }
}

export default BookCard;

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
