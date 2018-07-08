import React from 'react';
import PropTypes from 'prop-types';
import { Button } from 'react-bootstrap';

const CartEvent = (props) => {
    return(
        <tr>
        <td>{props.name}</td>
        <td>{props.quantity}</td>
        <td>{props.finalPrice}</td>
        <td><Button bsStyle="danger" bsSize="large" onClick={props.click}>X</Button></td>
        </tr>
    );
}

CartEvent.propTypes = {
    name: PropTypes.string,
    quantity: PropTypes.number,
    finalPrice: PropTypes.number,
    click: PropTypes.func
  }

export default CartEvent;