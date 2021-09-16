import React, {Component} from "react";
import axios from "axios";

class Sellers extends Component{
    constructor() {
        super();
        this.state = {
            sellers:[],
            condition: "new"
        }
        this.onChange = this.onChange.bind(this);
    }

    componentDidMount() {
        this.state.sellers = getAllAds(this.state.condition);
    }

    onChange(e){
        this.state.sellers = getAllAds(this.state.condition);
    }

    render(){
        return(
            <div>
                <select className="form-control" onChange={this.onChange}>
                    <option value="new" >New</option>
                    <option value="old">Old</option>
                </select>
                {this.state.sellers.map((value)=>{
                    return <div>value.price</div>
                })}
            </div>
        )
    }


}

const getAllAds = (condition) => async dispatch => {
    await axios.get("http://localhost:8081/api/books/allAds?condition="+ condition).then((res)=>{
        return res.data
    });

};

