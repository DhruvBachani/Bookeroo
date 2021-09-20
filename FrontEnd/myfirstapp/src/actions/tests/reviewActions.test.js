import React from 'react';
import reducer from '../../../src/reducers'
import { shallow } from 'enzyme';
import configureMockStore from 'redux-mock-store';

const mockStore = configureMockStore();
const store = mockStore([]);

const createReview = () => ({
    type: 'GET_ERRORS'
})

describe('reviewActions', () => {
    it('creates GET_ERRORS when a review cannot be created', () => {
        store.dispatch(createReview());
        expect(store.getActions()).toMatchSnapshot();
    })
})