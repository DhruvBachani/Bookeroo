import React, { Component } from "react";
import {createNewUser, login} from "../../actions/securityActions";
import {connect} from "react-redux";
class Login extends Component {
  constructor(){
    super();

    this.state = {
      username: "",
      password: "",
      errors: {}
    };
    this.onChange = this.onChange.bind(this);
    this.onSubmit = this.onSubmit.bind(this);
  }

  componentWillReceiveProps(nextProps){
    if (nextProps.errors){
      this.setState ({
        errors: nextProps.errors
      });

    }
  }

  onSubmit(e) {
    e.preventDefault();
    const loginRequest = {
      username: this.state.username,
      password: this.state.password,

    };

    this.props.login(loginRequest, this.props.history.push("/bookCatalog"));
  }

  onChange(e) {
    this.setState({ [e.target.name]: e.target.value });
  }
  render() {
    return (
      <div className="login">
        <div className="container">
          <div className="row">
            <div className="col-md-8 m-auto">
              <h1 className="display-4 text-center">Log In</h1>
              <form onSubmit={this.onSubmit}>
                <div className="form-group">
                  <input
                    type="email"
                    className="form-control form-control-lg"
                    placeholder="Email Address"
                    name="username"
                    onChange={this.onChange}
                    value = {this.state.username}
                  />
                </div>
                <div className="form-group">
                  <input
                    type="password"
                    className="form-control form-control-lg"
                    placeholder="Password"
                    name="password"
                    onChange={this.onChange}
                    value = {this.state.password}


                  />
                </div>
                <input type="submit" className="btn btn-info btn-block mt-4" />
              </form>
            </div>
          </div>
        </div>
      </div>
    );
  }
}

export default connect(
    null,
    { login }
)(Login);
