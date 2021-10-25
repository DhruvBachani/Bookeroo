import React from 'react';
import reducer from '../../../src/reducers'
import { shallow } from 'enzyme';
import { initialState } from '../personReducer';

describe('personReducer', () => {
    it('should return the initial state', () => {
        expect(reducer(undefined, {})).toMatchSnapshot();
    })

    it('should handle GET_PERSONS', () => {
        expect(
            reducer(initialState,
                {
                    type: 'GET_PERSONS'
                })
        ).toMatchSnapshot();
    })

    it('should handle GET_PERSON', () => {
        expect(
            reducer(initialState,
                {
                    type: 'GET_PERSON'
                })
        ).toMatchSnapshot();
    })
})