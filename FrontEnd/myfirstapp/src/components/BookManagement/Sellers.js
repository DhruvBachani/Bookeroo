import React, {Component} from "react";
import axios from "axios";


class Sellers extends Component{
    constructor(props) {
        super(props);
        this.state = {
            sellers:[],
            condition: "new",
            isbn: this.props.match.params.isbn
        }
        this.onChange = this.onChange.bind(this);
    }


    componentDidMount() {
        console.log(this.props.match.params.isbn)
        axios.get("http://localhost:8081/api/books/" + this.state.isbn + "/allAds?condition=" + this.state.condition).then((res) => {
            this.setState({sellers:res.data})
        })
    }

    async onChange(e) {
        await axios.get("http://localhost:8081/api/books/" + this.state.isbn + "/allAds?condition=" + e.target.value).then((res) => {
            this.setState({sellers:res.data})
        })

    }


    render(){
        console.log(this.state.sellers)
        return(
            <div>
                <select className="form-control" onChange={this.onChange} >
                    <option value="new"  >New</option>
                    <option value="old" >Old</option>
                </select>
                {this.state.sellers.map((value)=>{
                    return <div>{value.price}</div>
                })}
            </div>
        )
    }


}



export default Sellers;

