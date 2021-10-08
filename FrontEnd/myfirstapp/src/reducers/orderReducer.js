import { GET_USER_TRANSACTIONS, GET_ALL_TRANSACTIONS } from "../actions/types";

export const initialState = {
    userTransactions: [],
    allTransactions: []
};

export default function(state = initialState, action) {
    switch (action.type) {
        case GET_USER_TRANSACTIONS:
            return {
                ...state,
                userTransactions: action.payload
            };
        case GET_ALL_TRANSACTIONS:
            return {
                ...state,
                allTransactions: action.payload
            };

        default:
            return state;
    }
}
