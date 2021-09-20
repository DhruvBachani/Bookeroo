import React from 'react';
import reducer from '../../../src/reducers'
import { shallow } from 'enzyme';
import configureMockStore from 'redux-mock-store';

const mockStore = configureMockStore();
const store = mockStore([]);

const createPerson = () => ({
    type: 'GET_ERRORS'
})

const getPersons = () => ({
    type: 'GET_PERSONS'
});

const getPerson = () => ({
    type: 'GET_PERSON'
});

describe('personActions', () => {
    it('creates GET_ERRORS when a person cannot be created', () => {
        store.dispatch(createPerson());
        expect(store.getActions()).toMatchSnapshot();
    })

    it('creates GET_PERSONS when it is called successfully', () => {
        store.dispatch(getPersons());
        expect(store.getActions()).toMatchSnapshot();
    })

    it('creates GET_PERSON when it is called successfully', () => {
        store.dispatch(getPerson());
        expect(store.getActions()).toMatchSnapshot();
    })
})