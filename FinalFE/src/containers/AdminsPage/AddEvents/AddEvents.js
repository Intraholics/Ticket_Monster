import React, { Component } from "react";
import { FormGroup, FormControl, ControlLabel, Button } from "react-bootstrap";
import "./AddEvents.css";
import axios from 'axios';
import swal from 'sweetalert';


class AddEvents extends Component {

    state = { 
        description: "",
        date: "",
        ticketsLeft: "",
        price: ""  
      };

 

      onClickHandler = () => { //onClick method post
        const post = {
          description: this.state.description,
          date: Date.parse(new Date(this.state.date)),
          ticketsLeft: this.state.ticketsLeft,
          price: this.state.price         
        }; 
        axios.post('http://localhost:8080/TicketMonster/api/events', post)
            .then(response => {
                swal("Success!", "New event added!", "success");
            })
            .then(response => {
              setTimeout(() => {
                this.props.history.replace('/admins');
              },1000); 
            })
            .catch(err => {
              this.props.history.replace('/error');
              
            })
    }

  
    validateForm() {
      return (
        this.state.description.length > 0 &&
        this.state.date.length > 0 &&
        this.state.ticketsLeft.length > 0 &&
        this.state.price.length > 0
      );
    }
  
    handleChange = (event) => {

      this.setState({
        [event.target.id]: event.target.value
      });
    }
  
    handleSubmit = (event) => {
      event.preventDefault();
  
    }
    render(){

      return (

        <div className="AddEvents">
          <form onSubmit={this.handleSubmit}>
          <FormGroup controlId="description" bsSize="large">
              <ControlLabel>Description</ControlLabel>
              <FormControl
                autoFocus
                type="text"
                placeholder="Presentation"
                value={this.state.description}
                onChange={this.handleChange}
              />
              </FormGroup>
              <FormGroup controlId="date" bsSize="large">
              <ControlLabel>Date</ControlLabel>
              <FormControl
                type="text"
                placeholder="2018-07-15 10:30:00"
                value={this.state.date}
                onChange={this.handleChange}
              />
              </FormGroup>
            {/* <TimePicker start="10:00" end="21:00" step={30} /> */}
           <FormGroup controlId="ticketsLeft" bsSize="large">
              <ControlLabel>Total Tickets</ControlLabel>
              <FormControl
                type="text"
                placeholder="29"
                value={this.state.ticketsLeft}
                onChange={this.handleChange}
              />
              </FormGroup>
            <FormGroup controlId="price" bsSize="large">
              <ControlLabel>Price</ControlLabel>
              <FormControl
                type="text"
                placeholder="1"
                value={this.state.price}
                onChange={this.handleChange}
              />
            </FormGroup>
            <Button
              block
              bsSize="large"
              disabled={!this.validateForm()}
              type="submit"
              onClick={() => this.onClickHandler()}
            >
              Add Event
            </Button>
          </form>
        </div>
      );
    }
}

export default AddEvents;