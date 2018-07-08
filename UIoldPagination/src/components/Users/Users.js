import React from 'react';
import PropTypes from 'prop-types';
import { Button } from 'react-bootstrap';

const User = (props) => {
    return(    
       <tr>
        <td>{props.name}</td>
        <td>{props.surname}</td>
        <td>{props.username}</td>
        <td>{props.curRole.toString()}</td>
        <td><Button bsStyle="warning" bsSize="large" onClick={props.clickReset}>Reset</Button></td>
        <td><Button bsStyle="warning" bsSize="large" onClick={props.clickToggle}>Toggle</Button></td>
      </tr>);
}

User.propTypes = {
    name: PropTypes.string,
    surname: PropTypes.string,
    username: PropTypes.string,
    curRole: PropTypes.bool,
    clickReset: PropTypes.func,
    clickToggle: PropTypes.func
}

export default User;