import React, { Component } from "react";
import { FormGroup, FormControl, ControlLabel, Button } from "react-bootstrap";
import "./SignUp.css";
import axios from 'axios';

class SignUp extends Component {

    state = { 
        email: "",
        password: "",
        confirmPassword: "",
        confirmationCode: "",
        username: "",
        surname: "",
        name: ""   
      };

 

      onClickHandler = () => { //onClick method post!
        const post = {
          name: this.state.name,
          surname: this.state.surname,
          username: this.state.username,
          password: this.state.password,
          email: this.state.email,
          userRole: 0           
        }; 
        console.log(post)
        axios.post('http://localhost:8080/TicketMonster/api/users', post)
            .then(response => {
                console.log(response);
            })
            .then(response => {
              setTimeout(() => {
                this.props.history.replace('/signin');
              },1000); 
            })
            .catch(err => {
              this.props.history.replace('/error');
            })
    }

  
    validateForm() {
      return (
        this.state.username.length > 0 &&
        this.state.surname.length > 0 &&
        this.state.name.length > 0 &&
        this.state.email.length > 0 &&
        this.state.password.length > 0 &&
        this.state.password === this.state.confirmPassword
      );
    }
  
    handleChange = (event) => {

      this.setState({
        [event.target.id]: event.target.value
      });
    }
  
    handleSubmit = (event) => {
      event.preventDefault();
  
    }
    render(){

      return (

        <div className="signUp">
          <form onSubmit={this.handleSubmit}>
          <FormGroup controlId="name" bsSize="large">
              <ControlLabel>Name</ControlLabel>
              <FormControl
                autoFocus
                type="text"
                value={this.state.name}
                onChange={this.handleChange}
              />
              </FormGroup>
              <FormGroup controlId="surname" bsSize="large">
              <ControlLabel>Surname</ControlLabel>
              <FormControl
                type="text"
                value={this.state.surname}
                onChange={this.handleChange}
              />
              </FormGroup>

           <FormGroup controlId="username" bsSize="large">
              <ControlLabel>Username</ControlLabel>
              <FormControl
                type="text"
                value={this.state.username}
                onChange={this.handleChange}
              />
              </FormGroup>
            <FormGroup controlId="email" bsSize="large">
              <ControlLabel>Email</ControlLabel>
              <FormControl
                type="email"
                value={this.state.email}
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
            <FormGroup controlId="confirmPassword" bsSize="large">
              <ControlLabel>Confirm Password</ControlLabel>
              <FormControl
                value={this.state.confirmPassword}
                onChange={this.handleChange}
                type="password"
              />
            </FormGroup>
            <Button
              block
              bsSize="large"
              disabled={!this.validateForm()}
              type="submit"
              onClick={() => this.onClickHandler()}
            >
              Sign Up
            </Button>
          </form>
        </div>
      );
    }
}

export default SignUp;