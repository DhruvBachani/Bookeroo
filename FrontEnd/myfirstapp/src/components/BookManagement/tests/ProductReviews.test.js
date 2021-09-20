import React from 'react';
import { shallow } from 'enzyme';
import ProductReviews from '../ProductReviews';

describe('ProductReviews', () => {
    let wrapper;
    beforeEach(() => {
        const testreview = [
            {
                id: "1",
                userName: "testuserOne",
                review: "amazing"
            },
            {
                id: "2",
                userName: "testuserTwo",
                review: "not bad"
            }
        ];
        wrapper = shallow(<ProductReviews reviews={testreview}/>);
        jest.clearAllMocks();
    })

    it('should display all reviews that belong to the target book', () => {
        const usernames = wrapper.find('h5');
        const reviews = wrapper.find('p');

        expect(usernames.first().text()).toEqual('testuserOne');
        expect(reviews.first().text()).toEqual('amazing');
        expect(usernames.last().text()).toEqual('testuserTwo');
        expect(reviews.last().text()).toEqual('not bad');
    })
})