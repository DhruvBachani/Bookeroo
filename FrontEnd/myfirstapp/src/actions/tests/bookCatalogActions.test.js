import React from 'react';
import reducer from '../../../src/reducers'
import { shallow } from 'enzyme';
import configureMockStore from 'redux-mock-store';

const mockStore = configureMockStore();
const store = mockStore([]);

const createBook = () => ({
    type: 'GET_ERRORS'
})

describe('bookCatalogActions', () => {
    it('creates GET_ERRORS when a book cannot be created', () => {
        store.dispatch(createBook());
        expect(store.getActions()).toMatchSnapshot();
    })
})