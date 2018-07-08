import React, { Component } from 'react';
import { Table } from 'react-bootstrap';
import Order from '../../components/Order/Order';
import axios from 'axios';
import './AdminsPage.css';


class AdminsPage extends Component {

    state = {
        orders : []
    }

    componentDidMount(){ //get method
        axios.defaults.headers.common['Authorization'] = localStorage.getItem('token');
        if((localStorage.getItem('token')===null) || (localStorage.getItem('token')===undefined)){
            this.props.history.push('/signin');
          }else{
            if(!(localStorage.getItem('userRole'))){    
              this.props.history.push('/events');
            }
          }
        axios.get('http://localhost:8080/TicketMonster/api/orders')
            .then(response => {
                const res = response.data;

                console.log(res);  //output example
                this.setState({ orders: res});
                
            })
            .catch(err => {
                this.props.history.replace('/error');
            });
    }

    onClickHandler = (id) => { 
        axios.delete(`http://localhost:8080/TicketMonster/api/orders/${id}`)
            .then(res => {
                console.log(res);
                axios.get('http://localhost:8080/TicketMonster/api/orders')
                    .then(response => {
                        const newResponse = response.data;
                        console.log(newResponse);  //output example
                        this.setState({ orders: newResponse});
                    })
                    .catch(err => {
                        this.props.history.replace('/error');
                    });
            })
            .catch(err => {
                this.props.history.replace('/error');
            });
    
    }

  render() {
      const orders = this.state.orders.map(event => {
          return <Order  key={event.orderID}
                          mykey={event.orderID}
                          username={event.username}
                          date={event.purchaseDate} 
                          quantity={event.quantity} 
                          finalprice={event.finalPrice} 
                          click={() => this.onClickHandler(event.orderID)}
                        />
      });
    return (
      <div className="App">
      <h1 className='Adminsh1'>All Orders</h1>
        <Table striped bordered condensed hover responsive>
            <thead>
                <tr>
                <th>Order Id</th>
                <th>Username</th>
                <th>Purchase Date</th>
                <th>Quantity</th>
                <th>Final Price</th>
                <th>Options</th>
                </tr>
            </thead>
            <tbody>
            {orders}
            </tbody>
        </Table>
      </div>
    );
  }
}

export default AdminsPage;