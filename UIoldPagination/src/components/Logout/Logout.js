import React, { Component } from 'react';
import axios from 'axios';

class Logout extends Component{

    componentDidMount(){
        axios.post(`http://localhost:8080/TicketMonster/api/users/${parseInt(localStorage.getItem('userID'),10)}`)
        .then( res => {
            if (res.status === 200){
                localStorage.clear();
            }
        })
        .then( res => {
            setTimeout(() => {
                this.props.history.replace('./signin');
            },500)
        })
        .catch( err => {
            //throw new Error(err);
            this.props.history.push('/error');
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