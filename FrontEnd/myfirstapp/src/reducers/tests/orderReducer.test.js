import React from 'react';
import reducer from '../../../src/reducers'
import { shallow } from 'enzyme';
import { initialState } from '../orderReducer';

describe('orderReducer', () => {
    it('should return the initial state', () => {
        expect(reducer(undefined, {})).toMatchSnapshot();
    })

    it('should handle GET_USER_TRANSACTIONS', () => {
        expect(
            reducer(initialState,
                {
                    type: 'GET_USER_TRANSACTIONS'
                })
        ).toMatchSnapshot();
    })

    it('should handle GET_ALL_TRANSACTIONS', () => {
        expect(
            reducer(initialState,
                {
                    type: 'GET_ALL_TRANSACTIONS'
                })
        ).toMatchSnapshot();
    })

    it('should handle GET_ALL_BOOK_TRANSACTIONS', () => {
        expect(
            reducer(initialState,
                {
                    type: 'GET_ALL_BOOK_TRANSACTIONS'
                })
        ).toMatchSnapshot();
    })

    it('should handle GET_CART_ITEMS', () => {
        expect(
            reducer(initialState,
                {
                    type: 'GET_CART_ITEMS'
                })
        ).toMatchSnapshot();
    })

    it('should handle GET_CART_TOTAL', () => {
        expect(
            reducer(initialState,
                {
                    type: 'GET_CART_TOTAL'
                })
        ).toMatchSnapshot();
    })
})