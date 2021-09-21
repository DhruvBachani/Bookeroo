import React from 'react';
import reducer from '../../../src/reducers'
import { shallow } from 'enzyme';
import { initialState } from '../securityReducer';

describe('securityReducer', () => {
    it('should return the initial state', () => {
        expect(reducer(undefined, {})).toMatchSnapshot();
    })

    // it('should handle SET_CURRENT_USER', () => {
    //     expect(
    //         reducer(initialState,
    //             {
    //                 type: 'SET_CURRENT_USER'
    //             })
    //     ).toMatchSnapshot();
    // })
})