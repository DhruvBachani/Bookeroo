import { GET_REVIEWS } from "../actions/types";

const initialState = {
  bookReviews: [],
  review: {}
};

export default function(state = initialState, action) {
  switch (action.type) {
    case GET_REVIEWS:
      console.log(action.payload);
      return {
        ...state,
        bookReviews: action.payload
      };

    default:
      return state;
  }
}
