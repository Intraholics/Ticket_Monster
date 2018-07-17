import React,{ Component } from 'react';
import { Animated } from "react-animated-css";
import axios from 'axios';
import { Table, Button, FormGroup, FormControl,ControlLabel } from 'react-bootstrap';
import CheckoutEvent from '../../components/CheckoutEvent/CheckoutEvent';
import './Checkout.css';
import PopUp from '../../components/PopUp/PopUp';

class Checkout extends Component{
    state = {
        name: "",
        surname: "",
        email: "",
        checkoutEvents: [],
        creditCard: "",
        phone: "",
        purchaseDate: "",
        address: "",
        gif: false
}


    componentDidMount(){
        axios.defaults.headers.common['Authorization'] = sessionStorage.getItem('token');

        axios.get(`http://localhost:8080/TicketMonster/api/cart/user/${parseInt(sessionStorage.getItem('userID'),10)}`)//user id
            .then(res => {
                this.setState({checkoutEvents: res.data});
            })
            .catch(err => {
                this.props.history.replace('/error');
            });

         axios.get(`http://localhost:8080/TicketMonster/api/users/${parseInt(sessionStorage.getItem('userID'),10)}`)//user id
            .then(res2 => {
                this.setState({
                    name: res2.data.name,
                    surname: res2.data.surname,
                    email: res2.data.email
                });
            })
            .catch(err => {
                this.props.history.replace('/error');
            });
    }

    onPhoneChange = (event) =>{
        const newPhone = event.target.value;
        this.setState({
            phone: newPhone
     });
    }

    onCreditChange = (event) =>{
        const newCreditCard = event.target.value;
        this.setState({
            creditCard: newCreditCard
     });
    }

    onNameChange = (event) =>{
        const newName = event.target.value;
        this.setState({
            name: newName
     });
    }

    onSurnameChange = (event) =>{
        const newSurname = event.target.value;
        this.setState({
            surname: newSurname
     });
    }

    onEmailChange = (event) =>{
        const newEmail = event.target.value;
        this.setState({
            email: newEmail
     });
    }
    onAddressChange = (event) =>{
        const newAddress = event.target.value;
        this.setState({
            address: newAddress
     });
    }

    validateForm() {
        return this.state.name.length > 0 && 
        this.state.surname.length > 0  && 
        this.state.email.length > 0 &&
        this.state.address.length > 0 &&
        this.state.phone.length > 0 &&
        this.state.creditCard.length === 16;
      }

    onSubmitHandler = (e) => {
        e.preventDefault();
        this.setState({gif: true});

        setTimeout(function(){ 
            this.setState({gif: false});
            const postOrder = this.state.checkoutEvents.map(event => {
                return {cartID: event.cartID,
                        phone: this.state.phone,
                        creditcard: this.state.creditCard,
                        purchaseDate: this.state.purchaseDate}
            });
                axios.post('http://localhost:8080/TicketMonster/api/orders/orders', postOrder)
                    .then(response => {
                            this.props.history.replace('/events');
                    })
                    .catch(err => {
                        this.props.history.replace('/error');
                    })
            // redirect ---> event page
    }.bind(this), 2000);
    }

    render(){

        const checkoutEvents = this.state.checkoutEvents.map(event =>{
            return <CheckoutEvent   key={event.cartID} 
                                    name={event.eventDescr}
                                    quantity={event.quantity}
                                    finalPrice={event.finalPrice}/>
                                });

        let thegif;
            if(this.state.gif){
                thegif = (
                <div>
                    <PopUp text='Close Me' />
                </div>);
            }else{
                thegif = null;
            }

        return(
            <Animated animationIn="fadeIn" isVisible={true}>
                <div className='App'>
                    <h1 className='Checkouth1'>Checkout</h1>
                    <Table bordered condensed>
                        <thead>
                            <tr>
                            <th>Event Name</th>
                            <th>Quantity</th>
                            <th>Final Price</th>
                            </tr>
                        </thead>
                        <tbody>
                        {checkoutEvents}
                        </tbody>
                    </Table>
                    <h4 className='Checkouth2'>Please complete the form below</h4>
                    <div className="PayForm">
                        <form onSubmit={this.onSubmitHandler}>
                            <FormGroup controlId="phone">
                                <ControlLabel>Name</ControlLabel>
                                <FormControl value={this.state.name} 
                                            autoFocus 
                                            type="text" 
                                            onChange={(e) => this.onNameChange(e)} />
                            </FormGroup>
                            <FormGroup>
                                <ControlLabel>Surname</ControlLabel>
                                <FormControl value={this.state.surname}
                                            type="text"
                                            onChange={(e) => this.onSurnameChange(e)} />
                            </FormGroup>
                            <FormGroup>
                                <ControlLabel>Email</ControlLabel>
                                <FormControl value={this.state.email} 
                                            type="email"
                                            placeholder="johntsou@example.com" 
                                            onChange={(e) => this.onEmailChange(e)} />
                            </FormGroup>
                            <FormGroup>
                            <ControlLabel>Address</ControlLabel>
                            <FormControl type="text" 
                                     placeholder="354 Fifth Avenue" 
                                     value={this.state.address}
                                     onChange={(e) => this.onAddressChange(e)}
                                     />
                            </FormGroup>
                            <FormGroup controlId="phone">
                                <ControlLabel>Phone</ControlLabel>
                                <FormControl type="text"
                                            maxLength='11'
                                            placeholder="210 4551120"
                                            value={this.state.phone}
                                            onChange={(e) => this.onPhoneChange(e)}/>
                            </FormGroup>
                            <FormGroup controlId="creditCard">
                                <ControlLabel>Credit Card</ControlLabel>
                                <FormControl type="text"
                                            maxLength='16'
                                            placeholder="0004************"
                                            value={this.state.creditCard}
                                            onChange={(e) => this.onCreditChange(e)} />
                            </FormGroup>
                            {thegif}
                            <div className="alignCenter">            
                <Button
                    bsSize="large"
                    bsStyle="info"
                    disabled={!this.validateForm()}
                    type="submit"
                    // onClick={this.onClickHandler}
                >
                    Checkout
                </Button>
                </div>
                        </form>
                    </div>
                </div>
            </Animated>
        );
    }
}

export default Checkout;