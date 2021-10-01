import React from 'react';
import reducer from '../../../src/reducers'
import { shallow } from 'enzyme';
import { initialState } from '../bookReducer';

describe('bookReducer', () => {
    it('should return the initial state', () => {
        expect(reducer(undefined, {})).toMatchSnapshot();
    })

    it('should handle GET_BOOKS', () => {
        expect(
            reducer(initialState,
                {
                    type: 'GET_BOOKS'
                })
        ).toMatchSnapshot();
    })

    it('should handle GET_CATEGORIES', () => {
        expect(
            reducer(initialState,
                {
                    type: 'GET_CATEGORIES'
                })
        ).toMatchSnapshot();
    })

    it('should handle GET_BOOK', () => {
        expect(
            reducer(initialState,
                {
                    type: 'GET_BOOK'
                })
        ).toMatchSnapshot();
    })
})