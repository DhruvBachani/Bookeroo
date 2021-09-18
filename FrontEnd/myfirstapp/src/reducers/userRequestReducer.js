import { GET_USER_REQUESTS } from "../actions/types";

const initialState = {
    userRequests: []
};

export default function(state = initialState, action) {
    switch (action.type) {
        case GET_USER_REQUESTS:

            return {
                ...state,
                userRequests: action.payload
            };
        default:
            return state;
    }
}
