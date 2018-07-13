import React, { Component } from 'react';
import { Table } from 'react-bootstrap';
import User from '../../../components/Users/Users';
import axios from 'axios';
import './AdminsUsers.css';
import swal from 'sweetalert';

class AdminsUsers extends Component {

    state = {
        users : []
    }

    componentDidMount(){ //get method
        axios.defaults.headers.common['Authorization'] = sessionStorage.getItem('token');
        this.getUsers();
    }

    getUsers(){
        axios.get('http://localhost:8080/TicketMonster/api/users')
        .then(response => {
            const res = response.data;
            this.setState({ users: res});
            
        })
        .catch(err => {
            this.props.history.replace('/error');
        });
    }

    onClickHandlerReset = (id) => { 
        axios.put(`http://localhost:8080/TicketMonster/api/users/${id}&false`)
        .then(response => {
            this.getUsers();
            swal("Success!", "Password Reset", "success");
            
        })
        .catch(err => {
            this.props.history.replace('/error');
        });
    
    }
    onClickHandlerToggle = (id) => { 
        axios.put(`http://localhost:8080/TicketMonster/api/users/${id}&true`)
        .then(response => {
            this.getUsers();
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