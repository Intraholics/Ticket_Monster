import React, { Component } from 'react';
import { Animated } from 'react-animated-css';
import './LandingPage.css';

class LandingPage extends Component {


  componentDidMount(){
    if(localStorage.getItem('userRole')==='true'){
      this.props.history.push('/admins')
    }
  }
  
  onClickHandler = () => {

      if((localStorage.getItem('token')===null) || (localStorage.getItem('token')===undefined)){ //if signed out
        this.props.history.push('/signin');
      }else{
        this.props.history.push('/events');
        console.log("Going to events");
      }
    }

    render() {
      
      return (
        <div className="LandingPage">
        <section className="firstSection">
        <Animated animationIn="fadeInUp" isVisible={true}>
          <div className="firstContentArea">
              <h1>Ticket <span>Monster</span></h1>
              <h3>Life is a ticket to the greatest show in the world!</h3>
              <button  onClick={() => this.onClickHandler()} className="btn">Book your ticket!</button>

          </div>
          </Animated>
        </section>
  
        <section className="secondSection">
          <h3 className="title">Here are some photos of our events :</h3>
          <p>
              Buy your ticket, enjoy the ride!
          </p>
          <hr/>
  
          <ul className="photoGrid animated fadeIn">
              <li className="small pic1"></li>
              <li className="large pic2"></li>
              <li className="large pic3"></li>
              <li className="small pic4"></li>
          </ul>
          </section>
        </div>
      );
    }
  }
  
  export default LandingPage;