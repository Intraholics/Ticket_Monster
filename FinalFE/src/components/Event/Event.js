import React from 'react';
import PropTypes from 'prop-types';
import { Button } from 'react-bootstrap';

const Event = (props) => {
    return(    
       <tr>
        <td>{props.name}</td>
        <td>{props.date}</td>
        <td>{props.tickets}</td>
        <td>{props.price}</td>
        <td><input type="number" value={props.quantity} min="1" max={props.tickets} onChange={props.change}/></td>
        <td><Button bsStyle="success" bsSize="large" onClick={props.click}>Add to Cart</Button></td>
      </tr>);
}

Event.propTypes = {
  name: PropTypes.string,
  date: PropTypes.string,
  tickets: PropTypes.number,
  price: PropTypes.number,
  change: PropTypes.func,
  click: PropTypes.func
}

export default Event;