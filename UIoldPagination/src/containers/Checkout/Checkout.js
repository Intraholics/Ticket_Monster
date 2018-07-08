import React,{ Component } from 'react';
import axios from 'axios';
import { Table, Button, FormGroup, FormControl,ControlLabel } from 'react-bootstrap';
import CheckoutEvent from '../../components/CheckoutEvent/CheckoutEvent';
import './Checkout.css';
import PopUp from '../../components/PopUp/PopUp';

class Checkout extends Component{
    state = {
            checkoutEvents: [],
            creditCard: "",
            phone: "",
            purchaseDate: "",
            gif: false,
            showPopup: false
        
    }

    componentDidMount(){
        axios.defaults.headers.common['Authorization'] = localStorage.getItem('token');

        axios.get(`http://localhost:8080/TicketMonster/api/cart/user/${parseInt(localStorage.getItem('userID'),10)}`)//user id
            .then(res => {
                this.setState({checkoutEvents: res.data});
            })
            .catch(err => {
                this.props.history.push('/error');
               //throw new Error(err);
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

    onSubmitHandler = (e) => {
        e.preventDefault();
        console.log(this.state);
        this.setState({gif: true});
        

        setTimeout(function(){ 
            this.setState({gif: false});

            const postOrder = this.state.checkoutEvents.map(event => {
                return {cartID: event.cartID,
                        phone: this.state.phone,
                        creditcard: this.state.creditCard,
                        purchaseDate: this.state.purchaseDate}
            });
            console.log(postOrder);
                axios.post('http://localhost:8080/TicketMonster/api/orders/orders', postOrder)
                    .then(response => {
                        console.log(response);
                    })
                    .then(response => {
                        setTimeout(() => {
                            this.props.history.replace('/events');
                          },1000); 
                    })
                    .catch(err => {
                        this.props.history.push('/error');
                        //throw new Error(err);
                    })
            // redirect ---> event page
        }.bind(this), 2000);

       
    }

    togglePopup() {
        this.setState({
          showPopup: !this.state.showPopup
        });
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
                    <FormGroup>
                        <ControlLabel>Name</ControlLabel>
                        <FormControl autoFocus type="text" placeholder="John" />
                    </FormGroup>
                    <FormGroup>
                        <ControlLabel>Surname</ControlLabel>
                        <FormControl type="text" placeholder="Tsou" />
                    </FormGroup>
                    <FormGroup>
                        <ControlLabel>Email</ControlLabel>
                        <FormControl type="email" placeholder="johntsou@example.com" />
                    </FormGroup>
                    <FormGroup>
                        <ControlLabel>Address</ControlLabel>
                        <FormControl type="text" placeholder="354 Fifth Avenue" />
                    </FormGroup>
                    <FormGroup controlId="phone">
                        <ControlLabel>Phone</ControlLabel>
                        <FormControl type="text"
                                     placeholder="212-455-1120"
                                     value={this.state.phone}
                                     onChange={(e) => this.onPhoneChange(e)}/>
                    </FormGroup>
                    <FormGroup controlId="creditCard">
                        <ControlLabel>Credit Card</ControlLabel>
                        <FormControl type="text"
                                     placeholder="0004 **** **** ****"
                                     value={this.state.creditCard}
                                     onChange={(e) => this.onCreditChange(e)} />
                    </FormGroup>
                    {thegif}
                    <Button bsStyle="info"
                            bsClass="btnInf2"
                            type="submit"
                            >Checkout</Button>
                </form>
            </div>
        </div>
        );
    }
}

export default Checkout;