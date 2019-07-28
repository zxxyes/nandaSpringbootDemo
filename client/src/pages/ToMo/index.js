import React from 'react'
import { Button } from 'antd'
import { connect } from 'dva';
// import { stat } from 'fs';

let count = 1
const mapStateToWrapperProps = (state) =>{
    return {
        name: state.text.name
    }
}

const mapDispatchToWrapperProps = (dispatch) => {
    return  {
        dispatch: dispatch
    }
}

@connect(mapStateToWrapperProps,mapDispatchToWrapperProps)
class MyButtonWrapper extends React.Component {
    constructor(props){
        super(props)
    }

    render(){
        return <Button>{this.props.name}</Button>
    }
}

const mapStateToProps = (state) => {
    return {
        state: state
    }
}

const mapDispatchToProps = (dispatch) =>{
    return {
        dispatch: dispatch,
        update: ()=>{
            count = count +1
            dispatch({
            type: 'text/fetchText',
            payload: {
                name: count
            }
        })}
    }
}

@connect(mapStateToProps,mapDispatchToProps)
class MyButton extends React.Component {
    constructor(props){
        super(props)
    }
    render() {
        const {update} = this.props
        return(
            <Button onClick={update}>Click Me</Button>
        )
    }
}

export default class ToMo extends React.Component {
    state = {
        name: 'HelloWorld',
        num: 0
    }

    handleClick = () => {
        this.setState({
            num: this.state.num+1
        })
    }

    render() {
        console.log(this.state)
        return(
            <div>
                {/* <Button onClick={this.handleClick}>{this.state.num}</Button> */}
                <MyButton/>
                <MyButtonWrapper/>
            </div>
        )
    }
}
