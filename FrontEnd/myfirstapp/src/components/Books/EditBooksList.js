import React, { Component } from "react";
import {connect} from "react-redux";
import {getAllBooks} from "../../actions/bookActions";

class EditBooksList extends Component {
  componentDidMount() {
    this.props.getAllBooks()
  }

  render() {

    return (
      <div className="bookCatalog">
      {this.props.books.map((book) => (
        <div key={book.isbn}>
          {book.name}
          <br/><hr/>
        </div>
      ))}
      </div>
    );
  }
}

const mapStateToProps = (state) => {
  return{
    books: state.book.books
  }
};

export default connect(
    mapStateToProps,
    { getAllBooks }
)(EditBooksList);