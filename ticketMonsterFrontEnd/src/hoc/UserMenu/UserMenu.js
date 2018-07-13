import React from "react";
import { NavLink } from 'react-router-dom';
import { Animated } from 'react-animated-css';
import './UserMenu.css'

export default function UserMenu(Component) {
  return class EnhancedComponent extends React.Component {
    render() {
      return (
              <div>

              <header className=" header">
                <Animated animationIn="fadeInDown" isVisible={true}>
                  <h2><NavLink to="/" exact>Ticket Monster</NavLink></h2>
                </Animated>
                <Animated animationIn="fadeInDown" isVisible={true}>
                  <nav>
                    <li><NavLink to="/mytickets" exact>My Tickets</NavLink></li>
                    <li><NavLink to="/tocart" exact>To Cart</NavLink></li>
                    <li><NavLink to="/logout" exact>Logout</NavLink></li>
                  </nav>
                </Animated>
              </header>

              <Component {...this.props} />
              
          </div>
          
      );
    }
  };
}
