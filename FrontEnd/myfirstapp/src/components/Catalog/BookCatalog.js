import React, { Component } from "react";
import bookCatalogActions from "../../actions/bookCatalogActions";

class BookCatalog extends Component {
  constructor(){
    super();

    this.state = {
      allBooks: []
    }
    
  }

  componentDidMount(res) {

    bookCatalogActions.getAllBooks().then((res) => {
      console.log(res.data);
      this.setState({ allBooks: res.data });
    })

  }

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
                {
                  this.state.allBooks.map(key =>
                    <div>
                      <h5>
                        Book name: {key.name} <br></br>
                        Book author: {key.author} <br></br>
                        Book category: {key.category} <br></br>
                        Book description: {key.description} <br></br>
                        Book ISBN: {key.isbn}
                      </h5>
                    </div>
                  )
                }
              </div>
            </div>
          </div>
        </div>
      </div>
    );
  }
}

export default BookCatalog;