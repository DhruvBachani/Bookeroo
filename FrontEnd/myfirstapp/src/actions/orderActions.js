import axios from "axios";
import { GET_ERRORS } from "./types";

export const createOrder = (order, history) => async dispatch => {

  try{

    await axios.post("http://localhost:8000/pay", order);
    history.push("/dashboard");
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

