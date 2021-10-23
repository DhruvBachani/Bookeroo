import React from 'react';
import reducer from '../../../src/reducers'
import { shallow } from 'enzyme';
import configureMockStore from 'redux-mock-store';

const mockStore = configureMockStore();
const store = mockStore([]);

const getUserTransactions = () => ({
    type: 'GET_ERRORS'
});

const getAllTransactions = () => ({
    type: 'GET_ERRORS'
});

const refundOrder = () => ({
    type: 'GET_ERRORS'
});

const getShoppingCart = () => ({
    type: 'GET_ERRORS'
});

const getSellers = () => ({
    type: 'GET_ERRORS'
});

const saveOrder = () => ({
    type: 'GET_ERRORS'
});

describe('orderActions', () => {
    it('creates GET_ERRORS when user transactions cannot be retrieved', () => {
        store.dispatch(getUserTransactions());
        expect(store.getActions()).toMatchSnapshot();
    })

    it('creates GET_ERRORS when all transactions cannot be retrieved', () => {
        store.dispatch(getAllTransactions());
        expect(store.getActions()).toMatchSnapshot();
    })

    it('creates GET_ERRORS when a refund is unable to be executed', () => {
        store.dispatch(refundOrder());
        expect(store.getActions()).toMatchSnapshot();
    })

    it('creates GET_ERRORS when the shopping cart is unable to be retrieved', () => {
        store.dispatch(getShoppingCart());
        expect(store.getActions()).toMatchSnapshot();
    })

    it('creates GET_ERRORS when sellers are unable to be retrieved', () => {
        store.dispatch(getSellers());
        expect(store.getActions()).toMatchSnapshot();
    })

    it('creates GET_ERRORS when orders are unable to be saved', () => {
        store.dispatch(saveOrder());
        expect(store.getActions()).toMatchSnapshot();
    })
})