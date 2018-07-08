import React from "react";
import { NavLink } from 'react-router-dom';
import { Animated } from 'react-animated-css';
import './HighOrderMenu.css'

export default function higherOrderMenu(Component) {
  return class EnhancedComponent extends React.Component {

    state = {
      connected: false
    }

    

    componentDidMount(){
      if((localStorage.getItem('token')===null) || (localStorage.getItem('token')===undefined)){
          this.setState({connected: false});
      }else{
        this.setState({connected: true});
      }
    }

    render() {
      let nav;
      
      if(this.state.connected){
        nav = 
      (
        <nav>
      <li><NavLink to="/logout" exact>Logout</NavLink></li>
      </nav>);
      }else{
        nav = ( <nav>
          <li><NavLink to="/signin" exact>Sign In</NavLink></li>
          <li><NavLink to="/signup" exact>Sign Up</NavLink></li>
        </nav>);
      }



      return (
              <div>

                <header className=" header">
                <Animated animationIn="fadeInDown" isVisible={true}>
                  <h2><NavLink to="/" exact>Ticket Monster</NavLink></h2>
                </Animated>
                <Animated animationIn="fadeInDown" isVisible={true}>         
                 {nav}
                </Animated>
              </header>

              <Component {...this.props} />
              
          </div>
          
      );
    }
  };
}
