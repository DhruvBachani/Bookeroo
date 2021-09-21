import React, { useState } from "react";
import PropTypes from "prop-types";
import { connect } from "react-redux";
import "./AddReview.css";

function AddReview(props) {
    const [fields, setFields] = useState({review: "", rating: ""})

    const handleInputChange = (event) => {
        const name = event.target.name;
        const value = event.target.value;

        //Setting inputted values to fields.
        const temp = { review: fields.review, rating: fields.rating };

        temp[name] = value;
        setFields(temp);
    }

    const handleSubmit = (event) => {
        event.preventDefault();
        const newReview = {
            userId: props.userId,
            bookId: props.bookId,
            review: fields.review,
            rating: fields.rating
        };
        console.log(newReview);
    }

    return (
        <form className="review-form" onSubmit={handleSubmit}>
            <div className="review-container">
                <label>Write your review:</label>
                <div className="rating-container">
                    <label>
                        <input type="radio" name="rating" value="1" onChange={handleInputChange} required/>
                        <span className="icon">★</span>
                    </label>
                    <label>
                        <input type="radio" name="rating" value="2" onChange={handleInputChange}/>
                        <span className="icon">★</span>
                        <span className="icon">★</span>
                    </label>
                    <label>
                        <input type="radio" name="rating" value="3" onChange={handleInputChange}/>
                        <span className="icon">★</span>
                        <span className="icon">★</span>
                        <span className="icon">★</span>
                    </label>
                    <label>
                        <input type="radio" name="rating" value="4" onChange={handleInputChange}/>
                        <span className="icon">★</span>
                        <span className="icon">★</span>
                        <span className="icon">★</span>
                        <span className="icon">★</span>
                    </label>
                    <label>
                        <input type="radio" name="rating" value="5" onChange={handleInputChange}/>
                        <span className="icon">★</span>
                        <span className="icon">★</span>
                        <span className="icon">★</span>
                        <span className="icon">★</span>
                        <span className="icon">★</span>
                    </label>
                </div>
                <textarea id="review" name="review" value={fields.review} onChange={handleInputChange} placeholder="Write your review here..." rows="4" cols="80" required/>
                <hr/>
                <button type="submit" className="review-btn">Submit Review</button>
            </div>
        </form>
    );
}
export default AddReview;

