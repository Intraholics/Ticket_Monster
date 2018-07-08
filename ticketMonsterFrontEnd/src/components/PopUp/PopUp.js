import React from 'react';
import './PopUp.css';
import logo from './gimmemoney.gif';

const PopUp   = (props) => {
      return (
        <div className='popup'>
          <div className='popup_inner'>
            
            <img src={logo} alt="loading..." className="center" />
            <h2 className="calcprops">Calculating...</h2> 
          </div>
        </div>
      );
  }

  export default PopUp;