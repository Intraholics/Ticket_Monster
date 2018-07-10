import React, { Component } from 'react';
import { Table, Pagination } from 'react-bootstrap';
import Order from '../../components/Order/Order';
import axios from 'axios';
import './AdminsPage.css';


class AdminsPage extends Component {

    state = {
        orders : [],
        page: 1
    }

    componentDidMount(){ //get method
        axios.defaults.headers.common['Authorization'] = sessionStorage.getItem('token');
        if((sessionStorage.getItem('token')===null) || (sessionStorage.getItem('token')===undefined)){
            this.props.history.push('/signin');
          }else{
            if(!(sessionStorage.getItem('userRole'))){    
              this.props.history.push('/events');
            }
          }
        axios.get('http://localhost:8080/TicketMonster/api/orders')
            .then(response => {
                const res = response.data;

                console.log(res);  //output example
                this.setState({ orders: res});
                
            })
            .catch(err => {
                this.props.history.replace('/error');
            });
    }

    onClickHandler = (id) => { 
        axios.delete(`http://localhost:8080/TicketMonster/api/orders/${id}`)
            .then(res => {
                console.log(res);
                axios.get('http://localhost:8080/TicketMonster/api/orders')
                    .then(response => {
                        const newResponse = response.data;
                        console.log(newResponse);  //output example
                        this.setState({ orders: newResponse});
                        //this.renderPagination();
                    })
                    .catch(err => {
                        this.props.history.replace('/error');
                    });
            })
            .catch(err => {
                this.props.history.replace('/error');
            });
    
    }


    renderList() {
        const list = this.state.orders;
        const ref = (this.state.page-1)*10;
       // console.log(ref);
        const pageList = list.slice(ref, ref+10);
         const orders = pageList.map((event,i) => {
            return <Order  key={event.orderID}
                            // mykey={event.orderID}
                            mykey={i+ref+1}
                            username={event.username}
                            date={event.purchaseDate} 
                            quantity={event.quantity} 
                            finalprice={event.finalPrice} 
                            click={() => this.onClickHandler(event.orderID)}
                          />
        });


        return(
            <Table striped bordered condensed hover responsive>
            <thead>
                <tr>
                <th>Order Id</th>
                <th>Username</th>
                <th>Purchase Date</th>
                <th>Quantity</th>
                <th>Final Price</th>
                <th>Options</th>
                </tr>
            </thead>
            <tbody>
            {orders}
            </tbody>
           </Table>
          );
        }


    itemclick(num){
        //console.log(num);
        this.setState({page: num});
        let active = this.state.orders.length/10 + 1;
        

    }

    renderPagination() {
        let active = this.state.orders.length/10 + 1;
        let items = [];
        for (let number = 1; number <= active; number++) {
          items.push(
            <Pagination.Item active={number === active} key={number} id={number} onClick={()=>this.itemclick(number)}>{number}</Pagination.Item>
          );
        }
        
        return (
          <div className="alignCenter">
            <Pagination bsSize="large">{items}</Pagination>
            <br />
          </div>
        );
        
      }

  render() {
 
    return (
      <div className="App">
      <h1 className='Adminsh1'>All Orders</h1>
      {this.renderList()}
      {this.renderPagination()}
      </div>
    );
  }
}

export default AdminsPage;