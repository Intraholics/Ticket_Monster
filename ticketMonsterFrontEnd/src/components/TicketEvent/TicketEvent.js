import React from 'react';
import PropTypes from 'prop-types';

const TicketEvent = (props) => {
    return(
        <tr onClick={props.click}>
        <td>{props.num}</td>
        <td>{props.name}</td>
        <td>{props.quantity}</td>
        <td>{props.finalPrice}</td>
        <td>{props.purchaseDate}</td>
        </tr>
    );
}

TicketEvent.propTypes = {
    name: PropTypes.string,
    quantity: PropTypes.number,
    finalPrice: PropTypes.number,
    purchaseDate: PropTypes.string,
    num: PropTypes.number
  }

export default TicketEvent;