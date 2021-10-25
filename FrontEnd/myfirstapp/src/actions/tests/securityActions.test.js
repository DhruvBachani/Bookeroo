import React from 'react';
import reducer from '../../../src/reducers'
import { shallow } from 'enzyme';
import configureMockStore from 'redux-mock-store';

const mockStore = configureMockStore();
const store = mockStore([]);

const login = () => ({
    type: 'SET_CURRENT_USER'
});

const logout = () => ({
    type: 'SET_CURRENT_USER'
});

describe('securityActions', () => {
    it('creates SET_CURRENT_USER when logging in', () => {
        store.dispatch(login());
        expect(store.getActions()).toMatchSnapshot();
    })

    it('creates SET_CURRENT_USER when logging out', () => {
        store.dispatch(logout());
        expect(store.getActions()).toMatchSnapshot();
    })
})