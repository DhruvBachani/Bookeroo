import React, { Component } from "react";
import { createNewUser } from "../../actions/securityActions";
import * as PropTypes from 'prop-types'
import { connect } from "react-redux";
import classnames from "classnames";

class Register extends Component {
  constructor() {
    super();

    this.state = {
      username: "",
      fullName: "",
      password: "",
      confirmPassword: "",
      phoneNumber: "",
      address: "",
      abnNumber: "",
      userType: "",

      errors: {}
    };
    this.onChange = this.onChange.bind(this);
    this.onSubmit = this.onSubmit.bind(this);
  }

  onChange(e) {
    this.setState({
      [e.target.name]: e.target.value
    });

  }

  onSubmit(e) {
    e.preventDefault();
    console.log(this.state.userType);
    console.log(this.state.phoneNumber);
    console.log(this.state.abnNumber);
    console.log(this.state.address);
    console.log(this.state.username);

    const newUser = {
      username: this.state.username,
      fullName: this.state.fullName,
      password: this.state.password,
      confirmPassword: this.state.confirmPassword,
      phoneNumber: this.state.phoneNumber,
      abnNumber: this.state.abnNumber,
      address : this.state.address,
      userType: this.state.userType
    };

    this.props.createNewUser(newUser, this.props.history);
  }

  render() {
    return (
      <div className="register">
        <div className="container">
          <div className="row">
            <div className="col-md-8 m-auto">
              <h1 className="display-4 text-center">Sign Up</h1>
              <p className="lead text-center">Create your Account</p>
              <p className="display-6"> User Type </p>
              <form onSubmit={this.onSubmit}>
                <div className="form-group">
                  <label> User : </label>
                  <input
                      type="radio"
                      name="userType"
                      value="User"
                      // checked={this.state.userType === "User"}
                      onChange={this.onChange}
                  />
                  <label> Publisher: </label>
                  <input
                      type="radio"
                      name="userType"
                      value="Publisher"
                      // checked={this.state.userType === "Publisher"}
                      onChange={this.onChange}
                  />
                  <label> Shop Owner: </label>
                  <input
                      type="radio"
                      name="userType"
                      value="Shop owner"
                      // checked={this.state.userType === "Shop owner"}
                      onChange={this.onChange}
                  />
                </div>
                <div className="form-group">
                  <input
                    type="text"
                    className="form-control form-control-lg"
                    placeholder="Full Name"
                    name="fullName"
                    value={this.state.fullName}
                    onChange={this.onChange}
                  />
                </div>
                <div className="form-group">
                  <input
                    type="text"
                    className="form-control form-control-lg"
                    placeholder="Email Address (Username)"
                    name="username"
                    value={this.state.username}
                    onChange={this.onChange}
                  />
                </div>
                <div className="form-group">
                  <input
                      type="text"
                      className="form-control form-control-lg"
                      placeholder="Phone Number"
                      name="phoneNumber"
                      value={this.state.phoneNumber}
                      onChange={this.onChange}
                  />
                </div>
                <div className="form-group">
                  <input
                      type="text"
                      className="form-control form-control-lg"
                      placeholder="Address "
                      name="address"
                      value={this.state.address}
                      onChange={this.onChange}
                  />
                </div>
                <div className="form-group">
                  <input
                      type="text"
                      className="form-control form-control-lg"
                      placeholder="ABN Number "
                      name="abnNumber"
                      value={this.state.abnNumber}
                      onChange={this.onChange}
                  />
                </div>
                <div className="form-group">
                  <input
                    type="password"
                    className="form-control form-control-lg"
                    placeholder="Password"
                    name="password"
                    value={this.state.password}
                    onChange={this.onChange}
                  />
                </div>
                <div className="form-group">
                  <input
                    type="password"
                    className="form-control form-control-lg"
                    placeholder="Confirm Password"
                    name="confirmPassword"
                    value={this.state.confirmPassword}
                    onChange={this.onChange}
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

Register.propTypes = {
  createNewUser: PropTypes.func.isRequired,
  errors: PropTypes.object.isRequired
};

const mapStateToProps = state => ({
  errors: state.errors
});
export default connect(
  mapStateToProps,
  { createNewUser }
)(Register);

// import React, { Component } from "react";
// import { createNewUser } from "../../actions/securityActions";
// import * as PropTypes from 'prop-types'
// import { connect } from "react-redux";
// import classnames from "classnames";
//
// class Register extends Component {
//   constructor() {
//     super();
//
//     this.state = {
//       username: "",
//       fullName: "",
//       password: "",
//       confirmPassword: "",
//       errors: {}
//     };
//     this.onChange = this.onChange.bind(this);
//     this.onSubmit = this.onSubmit.bind(this);
//   }
//
//   onSubmit(e) {
//     e.preventDefault();
//     const newUser = {
//       username: this.state.username,
//       fullName: this.state.fullName,
//       password: this.state.password,
//       confirmPassword: this.state.confirmPassword
//     };
//
//     this.props.createNewUser(newUser, this.props.history.push("/bookCatalog"));
//   }
//
//   onChange(e) {
//     this.setState({ [e.target.name]: e.target.value });
//   }
//
//   render() {
//     return (
//
//         <div className="register">
//           <div className="container">
//             <div className="row">
//               <div className="col-md-8 m-auto">
//                 <h1 className="display-4 text-center">Sign Up</h1>
//                 <p className="lead text-center">Create your Account</p>
//                 <form onSubmit={this.onSubmit}>
//                   <div className="form-group">
//                     <input
//                         type="text"
//                         className="form-control form-control-lg"
//                         placeholder="Full Name"
//                         name="fullName"
//                         value={this.state.fullName}
//                         onChange={this.onChange}
//                     />
//                   </div>
//                   <div className="form-group">
//                     <input
//                         type="text"
//                         className="form-control form-control-lg"
//                         placeholder="Email Address (Username)"
//                         name="username"
//                         value={this.state.username}
//                         onChange={this.onChange}
//                     />
//                   </div>
//                   <div className="form-group">
//                     <input
//                         type="password"
//                         className="form-control form-control-lg"
//                         placeholder="Password"
//                         name="password"
//                         value={this.state.password}
//                         onChange={this.onChange}
//                     />
//                   </div>
//                   <div className="form-group">
//                     <input
//                         type="password"
//                         className="form-control form-control-lg"
//                         placeholder="Confirm Password"
//                         name="confirmPassword"
//                         value={this.state.confirmPassword}
//                         onChange={this.onChange}
//                     />
//                   </div>
//                   <input type="submit" className="btn btn-info btn-block mt-4" />
//                 </form>
//               </div>
//             </div>
//           </div>
//         </div>
//     );
//   }
// }
//
// Register.propTypes = {
//   createNewUser: PropTypes.func.isRequired,
//   errors: PropTypes.object.isRequired
// };
//
// const mapStateToProps = state => ({
//   errors: state.errors
// });
// export default connect(
//     mapStateToProps,
//     { createNewUser }
// )(Register);