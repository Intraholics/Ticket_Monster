import React, { Component } from 'react';
import './ErrorPage.css';

class ErrorPage extends Component {


  componentDidMount(){
   
  }
  
  onClickHandler = () => {
 
    }

    render() {
      
      return (
        <div className="error-page">
        <header className="error-page__header">
        <img className="error-page__header-image" src="https://static.tutsplus.com/assets/sad-computer-128aac0432b34e270a8d528fb9e3970b.gif" alt="Sad computer"></img>
        <h1 className="error-page__title nolinks">Page Not Found</h1>
        </header><p className="error-page__message">The page you are looking for could not be found.<a href="/">Try again.</a></p>
        </div>
      );
    }
  }
  
  export default ErrorPage;