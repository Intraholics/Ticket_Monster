import React, { Component } from 'react';
import axios from 'axios';

class Logout extends Component{

    componentDidMount(){
        axios.post(`http://localhost:8080/TicketMonster/api/users/${parseInt(sessionStorage.getItem('userID'),10)}`)
        .then( res => {
            if (res.status === 200){
                sessionStorage.clear();
            }
        })
        .then( res => {
            setTimeout(() => {
                this.props.history.replace('/');
            },500)
        })
        .catch( err => {
            this.props.history.replace('/error');
        })
    }
    
    render(){
        
        return(
        <div className='App'>
            </div>
        );
}
}

export default Logout;