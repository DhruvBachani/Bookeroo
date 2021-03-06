import { combineReducers } from "redux";
import errorReducer from "./errorReducer";
import personReducer from "./personReducer";
import securityReducer from "./securityReducer";
import bookReducer from "./bookReducer";
import bookReviewReducer from "./bookReviewReducer";
import userReducer from "./userReducer";
import orderReducer from "./orderReducer";

export default combineReducers({
  errors: errorReducer,
  person: personReducer,
  security: securityReducer,
  book: bookReducer,
  review: bookReviewReducer,
  user: userReducer,
  order: orderReducer
});

