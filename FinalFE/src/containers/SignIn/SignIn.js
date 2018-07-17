import React, { Component } from "react";
import axios from 'axios';
import { Button, FormGroup, FormControl, ControlLabel } from "react-bootstrap";
import "./SignIn.css";
var md5 = require('md5');

class SignIn extends Component {
  state = {
        username: "",
        password: "",
        redirect: ""
      };

      onClickHandler = () => { //onClick method post!
        let userName = this.state.username;
        let passWord = md5(this.state.password);
          
        let url = `http://localhost:8080/TicketMonster/api/users/${userName}&${passWord}`
        axios.get(url)
        .then((response)=> {
          sessionStorage.setItem('token',response.headers.authorization);
          axios.defaults.headers.common['Authorization'] = sessionStorage.getItem('token');
          sessionStorage.setItem('userID',response.data.userID);
          sessionStorage.setItem('username',response.data.username);
          sessionStorage.setItem('userRole',response.data.userRole);
           setTimeout(() =>{
            if(response.data.userRole){
               this.setState({redirect: "admins"});
            }
            else{
               this.setState({redirect: "events"});
            }
           }, 500);
         })
        .catch(function (error) {
          this.props.history.replace('/error');
        });


    }


  validateForm() {
    return this.state.username.length > 0 && this.state.password.length > 0;
  }

  handleChange = event => {
    this.setState({
      [event.target.id]: event.target.value
    });
  }

  handleSubmit = event => {
    event.preventDefault();  
  }

  render() {

    if(this.state.redirect === "admins"){
      return setTimeout(() => {
          this.props.history.push('/admins');
        },500); 
    }else{
      if(this.state.redirect ==="events"){
        return setTimeout(() => {
          this.props.history.push('/events');
        },500); 
      }
    }
    

    return (
      <div className="SignIn" token="">
        <form onSubmit={this.handleSubmit}>
          <FormGroup controlId="username" bsSize="large">
            <ControlLabel>Username</ControlLabel>
            <FormControl
              autoFocus
              type="username"
              value={this.state.username}
              onChange={this.handleChange}
            />
          </FormGroup>
          <FormGroup controlId="password" bsSize="large">
            <ControlLabel>Password</ControlLabel>
            <FormControl
              value={this.state.password}
              onChange={this.handleChange}
              type="password"
            />
          </FormGroup>
          <Button
            block
            bsSize="large"
            disabled={!this.validateForm()}
            type="submit"
            onClick={this.onClickHandler}
          >
            Login
          </Button>
        </form>
      </div>
    );
  }
}

export default SignIn;