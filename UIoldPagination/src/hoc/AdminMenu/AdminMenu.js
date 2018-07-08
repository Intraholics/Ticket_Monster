import React from "react";
import { NavLink } from 'react-router-dom';
import { Animated } from 'react-animated-css';
import './AdminMenu.css'

export default function AdminMenu(Component) {
  return class EnhancedComponent extends React.Component {
    render() {
      return (
              <div>

                <header className=" header">
                <Animated animationIn="fadeInDown" isVisible={true}>
                  <h2><NavLink to="/admins" exact>Ticket Monster</NavLink></h2>
                </Animated>
                <Animated animationIn="fadeInDown" isVisible={true}>
                  <nav>
                    <li><NavLink to="/admins" exact>Orders</NavLink></li>
                    <li><NavLink to="/users" exact>Users</NavLink></li>
                    <li><NavLink to="/addevents" exact>Add Events</NavLink></li>
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
