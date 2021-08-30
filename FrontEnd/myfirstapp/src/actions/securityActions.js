import axios from "axios";
import {GET_ERRORS, SET_CURRENT_USER} from "./types";
import setJWTToken from "../securityUtils/setJWTToken";
import jwt_decode from "jwt-decode";


export const createNewUser = (newUser, history) => async dispatch => {

    try{

        await axios.post("http://localhost:8080/api/users/register", newUser);
        // history.push("/login");
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














