import React,{ Component } from 'react';
import { Table,Pagination } from 'react-bootstrap';
// import Pagination from 'react-bootstrap/lib/Pagination';
import axios from 'axios';
import './MyTickets.css'
import TicketEvent from '../../components/TicketEvent/TicketEvent';

class MyTickets extends Component{
    state = {
        myTickets: [],
        page: 1
    }

    componentDidMount(){
        axios.defaults.headers.common['Authorization'] = localStorage.getItem('token');

        axios.get(`http://localhost:8080/TicketMonster/api/orders/users/${parseInt(localStorage.getItem('userID'),10)}`)//userid
        .then(res => { 
            console.log(res.data);
            this.setState({myTickets: res.data});
        })
        .catch(err => {
            this.props.history.push('/error');
           // throw new Error(err);
        });
    }

    renderList() {
        const list = this.state.myTickets;
        const ref = (this.state.page-1)*10;
       // console.log(ref);
        const pageList = list.slice(ref, ref+10);
        const tickets = pageList.map((event,i) => {
            return  <TicketEvent 
                          key={event.orderID}
                          num={i+ref+1}
                          name={event.eventDescr}
                          quantity={event.quantity}
                          finalPrice={event.finalPrice}
                          purchaseDate={event.purchaseDate}/>
         });


        return(
            <Table responsive hover>
            <thead>
                <tr>
                <th>#</th>
                <th>Event Name</th>
                <th>Quantity</th>
                <th>Final Price</th>
                <th>Purchase Date</th>
                </tr>
            </thead>
            <tbody>
            {tickets}
            </tbody>
        </Table>
          );
        }

        itemclick(num){
            //console.log(num);
            this.setState({page: num});
        }

        renderPagination() {
            let active = this.state.myTickets.length/10 + 1;
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
        
      

    render(){
      
        return(
         <div className="App">
            <h1 className='ticketsh1'>My tickets</h1>
            {this.renderList()}
            {this.renderPagination()}
        </div>
        );
    }
}

export default MyTickets;