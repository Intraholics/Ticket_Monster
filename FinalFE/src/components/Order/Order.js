import React from 'react';
import PropTypes from 'prop-types';
import { Button } from 'react-bootstrap';

const Order = (props) => {
    return(    
       <tr>
        <td>{props.mykey}</td>
        <td>{props.username}</td>
        <td>{props.date}</td>
        <td>{props.quantity}</td>
        <td>{props.finalprice}</td>
        <td><Button bsStyle="warning" bsSize="large" onClick={props.click}>Remove</Button></td>
      </tr>);
}

Order.propTypes = {
  mykey: PropTypes.number,
  date: PropTypes.string,
  quantity: PropTypes.number,
  finalprice: PropTypes.number,
  click: PropTypes.func
}

export default Order;