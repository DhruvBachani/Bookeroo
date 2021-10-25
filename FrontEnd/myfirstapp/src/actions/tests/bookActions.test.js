import React from 'react';
import reducer from '../../../src/reducers'
import { shallow } from 'enzyme';
import configureMockStore from 'redux-mock-store';

const mockStore = configureMockStore();
const store = mockStore([]);

const createBook = () => ({
    type: 'GET_ERRORS'
})

const postAd = () => ({
    type: 'GET_ERRORS'
})

const getAllBooks = () => ({
    type: 'GET_BOOKS'
});

const getAllCategories = () => ({
    type: 'GET_CATEGORIES'
});

const getBook = () => ({
    type: 'GET_BOOK'
});

const search = () => ({
    type: 'GET_BOOKS'
});

const getBookReviews = () => ({
    type: 'GET_REVIEWS'
});

describe('bookActions', () => {
    it('creates GET_ERRORS when a book cannot be created', () => {
        store.dispatch(createBook());
        expect(store.getActions()).toMatchSnapshot();
    })

    it('creates GET_ERRORS when an ad cannot be created', () => {
        store.dispatch(postAd());
        expect(store.getActions()).toMatchSnapshot();
    })

    it('creates GET_BOOKS when it is called successfully', () => {
        store.dispatch(getAllBooks());
        expect(store.getActions()).toMatchSnapshot();
    })

    it('creates GET_CATEGORIES when it is called successfully', () => {
        store.dispatch(getAllCategories());
        expect(store.getActions()).toMatchSnapshot();
    })

    it('creates GET_BOOK when it is called successfully', () => {
        store.dispatch(getBook());
        expect(store.getActions()).toMatchSnapshot();
    })

    it('creates GET_BOOKS when a search query is made', () => {
        store.dispatch(search());
        expect(store.getActions()).toMatchSnapshot();
    })

    it('creates GET_REVIEWS when it is called successfully', () => {
        store.dispatch(getBookReviews());
        expect(store.getActions()).toMatchSnapshot();
    })
})