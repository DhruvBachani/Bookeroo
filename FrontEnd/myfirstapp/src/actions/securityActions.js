import axios from "axios";
import {GET_ERRORS, SET_CURRENT_USER} from "./types";
import setJWTToken from "../securityUtils/setJWTToken";
import jwt_decode from "jwt-decode";


export const createNewUser = (newUser, history) => async dispatch => {

    try{

        await axios.post("http://localhost:8080/api/users/register", newUser);
        history.push("/login");
        dispatch({
            type: GET_ERRORS,
            payload: {}
        });
    }
    catch (err){

        dispatch ({
            type: GET_ERRORS,
            payload: err.response.data
        });



    }

};

export const login = (LoginRequest, history) => async dispatch => {


    try {
        await axios.post("http://localhost:8080/api/users/login", LoginRequest).then((response) => {
            console.log(response.data.token);
            let token= response.data.token
        //post => login request

        //extract token from res.data
            localStorage.setItem("token", token[1]);
        //set our token in the local storage
            axios.defaults.headers["Authorization"] = token;

        // set our token in header

        //decode the token on React
            let user = jwt_decode(token);
        // dispatch to our securityReducer
            dispatch({
                type: SET_CURRENT_USER,
                payload: user
            });
        });
        history.push("/dashboard");



        // await axios.post("http://localhost:8080/api/users/login", LoginRequest).then((response) => console.log(response));

        // dispatch({
        //     type: GET_ERRORS,
        //     payload: {}
        // });


    }
    catch (err)
    {
        console.log(err.response)
        dispatch({
            type: GET_ERRORS,
            payload: err.response.data
        });
    }

}




export const catalog = (books, history) => async dispatch => {

    try {
        await axios.get("http://localhost:8081/api/catalog/allBooks", books);

        dispatch({
            type: GET_ERRORS,
            payload: {}
        });
        
        history.push("/dashboard");

    }
    catch (err)
    {
        console.log(err.response)
        dispatch({
            type: GET_ERRORS,
            payload: err.response.data
        });
    }

}







// export const login = LoginRequest => async dispatch => {
//     try {
//       // post => Login Request
//       const res = await axios.post("/api/users/login", LoginRequest);
//       // extract token from res.data
//       const { token } = res.data;
//       // store the token in the localStorage
//       localStorage.setItem("jwtToken", token);
//       // set our token in header ***
//       setJWTToken(token);
//       // decode token on React
//       const decoded = jwt_decode(token);
//       // dispatch to our securityReducer
//       dispatch({
//         type: SET_CURRENT_USER,
//         payload: decoded
//       });
//     } catch (err) {
//       dispatch({
//         type: GET_ERRORS,
//         payload: err.response.data
//       });
//     }
//   };

//   export const logout = () => dispatch => {
//     localStorage.removeItem("jwtToken");
//     setJWTToken(false);
//     dispatch({
//       type: SET_CURRENT_USER,
//       payload: {}
//     });
//   };









