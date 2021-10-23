import React from 'react';
import { shallow } from 'enzyme';
import ProductDetails from '../ProductDetails';

describe('ProductDetails', () => {
    let wrapper;
    beforeEach(() => {
        const testbook = {
            name: "testname",
            author: "testauthor",
            category: "testcategory",
            description: "testdescription",
            isbn: "12345"
        };
        wrapper = shallow(<ProductDetails book={testbook}/>);
        jest.clearAllMocks();
    })

    it('should render book details with each book property inside a h4 tag', () => {
        const labels = wrapper.find('h4');

        expect(labels.first().text()).toEqual('testauthor');
        expect(labels.last().text()).toEqual('testcategory');
    })

    it('should render default.jpg if the book cover image is failed to be found', () => {
        const handleError = wrapper.find('img').prop('onError');
        expect(typeof handleError).toBe('function')
        const temp = {
            target: {
                src: ''
            }
        }
        handleError(temp);
        expect(temp.target.src).toBe('/default.jpg');
    })
})