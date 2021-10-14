import axios from "axios";
import { GET_ERRORS } from "./types";

export const createReview = (review, history) => async dispatch => {
    try {
        const res = await axios.post(`http://bookerooreviewservice-env.eba-e42sq3p2.us-east-2.elasticbeanstalk.com/api/reviews/create`)
    } catch (err) {
        dispatch({
            type: GET_ERRORS,
            payload: err.response.data
        });
    }
};