import React from 'react';
import reducer from '../../../src/reducers'
import { shallow } from 'enzyme';
import { initialState } from '../errorReducer';

describe('errorReducer', () => {
    it('should return the initial state', () => {
        expect(reducer(initialState, {})).toMatchSnapshot();
    })
})