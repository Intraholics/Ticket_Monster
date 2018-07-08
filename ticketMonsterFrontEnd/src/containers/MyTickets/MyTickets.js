import React,{ Component } from 'react';
import { Table } from 'react-bootstrap';
import axios from 'axios';
import './MyTickets.css'
import TicketEvent from '../../components/TicketEvent/TicketEvent';

class MyTickets extends Component{
    state = {
        myTickets: []
    }

    componentDidMount(){
        axios.defaults.headers.common['Authorization'] = localStorage.getItem('token');

        axios.get(`http://localhost:8080/TicketMonster/api/orders/users/${parseInt(localStorage.getItem('userID'),10)}`)//userid
        .then(res => { 
            console.log(res.data);
            this.setState({myTickets: res.data});
        })
        .catch(err => {
            this.props.history.replace('/error');
        });
    }

    render(){
        const tickets = this.state.myTickets.map(event => {
           return  <TicketEvent 
                         key={event.orderID}
                         num={event.orderID}
                         name={event.eventDescr}
                         quantity={event.quantity}
                         finalPrice={event.finalPrice}
                         purchaseDate={event.purchaseDate}/>
        });
        return(
         <div className="App">
            <h1 className='ticketsh1'>My tickets</h1>
            <Table hover responsive>
                <thead>
                    <tr>
                    <th>#</th>
                    <th>Event Name</th>
                    <th>Quantity</th>
                    <th>Final Price</th>
                    <th>Purchase Date</th>
                    </tr>
                </thead>
                <tbody>
                {tickets}
                </tbody>
            </Table>
        </div>
        );
    }
}

export default MyTickets;