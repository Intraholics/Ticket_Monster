import React, { Component } from 'react';
import { Table } from 'react-bootstrap';
import Event from '../../components/Event/Event';
import axios from 'axios';
import swal from 'sweetalert';
import './EventPage.css';

class EventPage extends Component {

    state = {
        events : []
    }

    componentDidMount(){ //get method
        axios.defaults.headers.common['Authorization'] = localStorage.getItem('token');

        this.getMethod();
    }


    getMethod = () => {
        axios.get('http://localhost:8080/TicketMonster/api/events')
        .then(response => {
            
            const res = response.data.map(val => {
                    return {...val, quantity: 0,finalPrice: 0}; 
                    //vazw quantity,finalPrice sto event sun auta pou phra apo to get!
            });
            console.log(res);  //output example
            this.setState({ events: res});
        
            
        })
        .catch(err => {
            this.props.history.replace('/error');
        });
 
    }


    onClickHandler = (id) => { //onClick method post!
        
        if(this.state.events[id-1].quantity !== 0){
            const post = {eventID: this.state.events[id - 1].eventID,
                        quantity: this.state.events[id - 1].quantity,
                        finalPrice: this.state.events[id - 1].finalPrice,
                        userID: parseInt(localStorage.getItem('userID'),10)}; //kanw post to event pou ekana book
            console.log(post)
            axios.post('http://localhost:8080/TicketMonster/api/cart', post)
                .then(response => {
                    console.log(response);
                    swal("Nice choice!", "Added to your cart!", "success");
                })
                .catch(err => {
                    this.props.history.replace('/error');
                });

            }else{
                swal("Sorry! You can't buy zero tickets", "Please add more!", "info");
            }
    }

    onChangeHandler = (event,id) => {
        const num = event.target.value; //oti pataw!
        const yolo = this.state.events.map(val => { // dhmiourgw to neo mou state me finalPrice kai quantity of tickets
           if(val.eventID === id){ //an to id tou event einai iso me to id tou event pou patithike
                const fullAmount = num * val.price ; //pollaplasiazw ton arithmo eishthriwn me thn timh
                return {...val, quantity: num, finalPrice: fullAmount }; //epistrefeis to quantity
           }else{
               return val;
           }
        });
        console.log(yolo); //example output
        this.setState({events : yolo });
    }

  render() {
      const events = this.state.events.map(event => {
          return <Event key={event.eventID}
                         name={event.description}
                          date={event.date} 
                          tickets={event.ticketsLeft} 
                          price={event.price} 
                          click={() => this.onClickHandler(event.eventID)}
                          change={(e) => this.onChangeHandler(e, event.eventID)}
                          quantity= {event.quantity}
                        />
      });
    return (
      <div className="App">
        <h1 className='Eventsh1'>Available Events</h1>
        <Table striped bordered condensed hover responsive>
            <thead>
                <tr>
                <th>Event Name</th>
                <th>Date</th>
                <th>Tickets left</th>
                <th>Price</th>
                <th>Quantity</th>
                <th>Book</th>
                </tr>
            </thead>
            <tbody>
            {events}
            </tbody>
        </Table>
      </div>
    );
  }
}

export default EventPage;