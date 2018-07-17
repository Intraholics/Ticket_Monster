import React, { PureComponent } from 'react';
import { Table,Button } from 'react-bootstrap';
import axios from 'axios';
import CartEvent from '../../components/CartEvent/CartEvent';
import './toCart.css';

class ToCart extends PureComponent {

    state = {
        cartEvents : []
    }

    componentDidMount(){
        axios.defaults.headers.common['Authorization'] = sessionStorage.getItem('token');
        axios.get(`http://localhost:8080/TicketMonster/api/cart/user/${parseInt(sessionStorage.getItem('userID'),10)}`)//userid
            .then(res => {
                
                this.setState({cartEvents: res.data});
            })
            .catch(err => {
                this.props.history.replace('/error');
            });
    }

    onDeleteHandler = (id) => {
        axios.delete(`http://localhost:8080/TicketMonster/api/cart/${id}`)
            .then(res => {
                
                axios.get(`http://localhost:8080/TicketMonster/api/cart/user/${parseInt(sessionStorage.getItem('userID'),10)}`)//userid
                .then(res => {
                    this.setState({cartEvents: res.data});
                })
                .catch(err => {
                    this.props.history.replace('/error');
                });
                
            })
            .catch(err => {
                this.props.history.replace('/error');
            });
    }

    onCheckoutHandler= () => {
        this.props.history.push('/tocart/checkout');
    }
    
    render(){

        const CartEvents = this.state.cartEvents.map(event => {
           return  <CartEvent key={event.cartID} 
                       name={event.eventDescr}
                       quantity={event.quantity}
                       finalPrice={event.finalPrice}
                       click={() => this.onDeleteHandler(event.cartID)}
            />
        });

        return(
        <div className="App">
            <h1 className='Carth1'>My Cart</h1>
            <Table hover responsive>
                <thead>
                    <tr>
                    <th>Event Name</th>
                    <th>Quantity</th>
                    <th>Final Price</th>
                    <th>Remove</th>
                    </tr>
                </thead>
                <tbody>
                {CartEvents}
                </tbody>
            </Table>
            <Button bsStyle="info" bsSize="large" bsClass="btnInf" onClick={() => this.onCheckoutHandler()}>Checkout</Button>
        </div>
        );
    }
}

export default ToCart;