import React from 'react';
import reducer from '../../../src/reducers'
import { shallow } from 'enzyme';
import { initialState } from '../bookReviewReducer';

describe('bookReviewReducer', () => {
    it('should return the initial state', () => {
        expect(reducer(undefined, {})).toMatchSnapshot();
    })

    it('should handle GET_REVIEWS', () => {
        expect(
            reducer(initialState,
                {
                    type: 'GET_REVIEWS'
                })
        ).toMatchSnapshot();
    })
})