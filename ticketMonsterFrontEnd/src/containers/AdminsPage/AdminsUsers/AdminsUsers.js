import React, { Component } from 'react';
import { Table } from 'react-bootstrap';
import User from '../../../components/Users/Users';
import axios from 'axios';
import './AdminsUsers.css';

class AdminsUsers extends Component {

    state = {
        users : []
    }

    componentDidMount(){ //get method
        axios.defaults.headers.common['Authorization'] = sessionStorage.getItem('token');
        // if((sessionStorage.getItem('token')===null) || (sessionStorage.getItem('token')===undefined)){
        //     this.props.history.push('/signin');
        //   }else{
        //     if(!(sessionStorage.getItem('userRole'))){    
        //       this.props.history.push('/events');
        //     }
        //   }
        this.getUsers();
        console.log("Component did mount");
    }

    getUsers(){
        axios.get('http://localhost:8080/TicketMonster/api/users')
        .then(response => {
            console.log(response.data);
            const res = response.data;
            
            console.log(res);  //output example
            this.setState({ users: res});
            
        })
        .catch(err => {
            this.props.history.replace('/error');
        });
    }

    onClickHandlerReset = (id) => { 
        console.log('Reset pass of user with id:'+ id);
        axios.put(`http://localhost:8080/TicketMonster/api/users/${id}&false`)
        .then(response => {
            console.log(response);  //output example
            this.getUsers();
            console.log('Reseted');
            
        })
        .catch(err => {
            this.props.history.replace('/error');
        });
    
    }
    onClickHandlerToggle = (id) => { 
        console.log('Toggle role of user with id:'+ id);
        axios.put(`http://localhost:8080/TicketMonster/api/users/${id}&true`)
        .then(response => {
            console.log(response);  //output example
            this.getUsers();
            console.log('Toggled');
        })
        .catch(err => {
            this.props.history.replace('/error');
        });
    
    }


  render() {
      const users = this.state.users.map(event => {
          return <User    key={event.userID}
                          name={event.name}
                          surname={event.surname} 
                          username={event.username} 
                          curRole={event.userRole} 
                          clickReset={() => this.onClickHandlerReset(event.userID)}
                          clickToggle={() => this.onClickHandlerToggle(event.userID)}
                        />
      });
    return (
      <div className="App">
      <h1 className='Usersh1'>All Users</h1>
        <Table striped bordered condensed hover responsive>
            <thead>
                <tr>
                <th>Name</th>
                <th>Surname</th>
                <th>Username</th>
                <th>Current Role</th>
                <th>Reset Pass</th>
                <th>Toggle Role</th>
                </tr>
            </thead>
            <tbody>
            {users}
            </tbody>
        </Table>
      </div>
    );
  }
}

export default AdminsUsers;