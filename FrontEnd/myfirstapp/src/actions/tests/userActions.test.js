import React from 'react';
import reducer from '../../../src/reducers'
import { shallow } from 'enzyme';
import configureMockStore from 'redux-mock-store';

const mockStore = configureMockStore();
const store = mockStore([]);

const getAllUnapprovedUsers = () => ({
    type: 'GET_USER_REQUESTS'
});

const getAllUsers = () => ({
    type: 'GET_ALL_USERS'
});

const approveUser = () => ({
    type: 'GET_USER_REQUESTS'
});

const banUser = () => ({
    type: 'GET_USER_REQUESTS'
});

describe('personActions', () => {
    it('creates GET_USER_REQUESTS when getting all unapproved users', () => {
        store.dispatch(getAllUnapprovedUsers());
        expect(store.getActions()).toMatchSnapshot();
    })

    it('creates GET_ALL_USERS when it is called successfully', () => {
        store.dispatch(getAllUsers());
        expect(store.getActions()).toMatchSnapshot();
    })

    it('creates GET_USER_REQUESTS when approving a user', () => {
        store.dispatch(approveUser());
        expect(store.getActions()).toMatchSnapshot();
    })

    it('creates GET_USER_REQUESTS when banning a user', () => {
        store.dispatch(banUser());
        expect(store.getActions()).toMatchSnapshot();
    })
})