import {GET_BOOKS, GET_CATEGORIES} from "../actions/types";

const initialState = {
    books: [],
    allCategories: []
};

export default function(state = initialState, action) {
    switch (action.type) {
        case GET_BOOKS:
            return {
                ...state,
                books: action.payload
            };
        case GET_CATEGORIES:
            return {
                ...state,
                allCategories: action.payload
            };
        default:
            return state;
    }
}
