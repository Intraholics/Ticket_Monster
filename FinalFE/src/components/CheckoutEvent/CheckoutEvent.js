import React from 'react';
import PropTypes from 'prop-types';

const CheckoutEvent = (props) => {
    return(
        <tr>
        <td>{props.name}</td>
        <td>{props.quantity}</td>
        <td>{props.finalPrice}</td>
        </tr>
    );
}

CheckoutEvent.propTypes = {
    name: PropTypes.string,
    quantity: PropTypes.number,
    finalPrice: PropTypes.number
  }

export default CheckoutEvent;