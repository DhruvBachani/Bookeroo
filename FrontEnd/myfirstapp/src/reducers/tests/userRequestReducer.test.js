import React from 'react';
import reducer from '../../../src/reducers'
import { shallow } from 'enzyme';
import { initialState } from '../userRequestReducer';

describe('userRequestReducer', () => {
    it('should return the initial state', () => {
        expect(reducer(undefined, {})).toMatchSnapshot();
    })

    it('should handle GET_USER_REQUESTS', () => {
        expect(
            reducer(initialState,
                {
                    type: 'GET_USER_REQUESTS'
                })
        ).toMatchSnapshot();
    })
})