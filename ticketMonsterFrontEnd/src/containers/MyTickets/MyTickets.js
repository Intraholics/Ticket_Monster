import React,{ Component } from 'react';
import { Table,Pagination } from 'react-bootstrap';
import axios from 'axios';
import './MyTickets.css'
import TicketEvent from '../../components/TicketEvent/TicketEvent';
import jsPDF from 'jspdf';

class MyTickets extends Component{
    state = {
        myTickets: [],
        page: 1,
        userDetails: {}
    }

    componentDidMount(){
        axios.defaults.headers.common['Authorization'] = sessionStorage.getItem('token');

        axios.get(`http://localhost:8080/TicketMonster/api/orders/users/${parseInt(sessionStorage.getItem('userID'),10)}`)//userid
        .then(res => { 
            console.log(res.data);
            this.setState({myTickets: res.data});
        })
        .catch(err => {
            this.props.history.replace('/error');
        });
    }

    onClickHandler = (id) => {
        axios.get(`http://localhost:8080/TicketMonster/api/users/${parseInt(sessionStorage.getItem('userID'),10)}`)//userid
            .then(res2 => {
                console.log(res2.data);
                this.setState({userDetails: res2.data});
            })
            .then(re2 => {
                var doc = new jsPDF();
                doc.setFillColor(200);
                doc.rect(0, 0, 300, 300, "F");
                doc.setTextColor(150);
                doc.setFontSize(25);
                doc.text('TicketMonster', 70, 15);

                doc.setTextColor(0,0,0);
                doc.setFontSize(15);
                doc.text('Event Name: ' + this.state.myTickets[id].eventDescr, 70, 50);
                doc.text('Name: ' + this.state.userDetails.name, 70, 65);
                doc.text('Surname: ' + this.state.userDetails.surname, 70, 80);
                doc.text('E-mail: ' + this.state.userDetails.email, 70, 95);
                doc.text('Quantity: ' + this.state.myTickets[id].quantity.toString(), 70, 110);
                doc.text('Price: ' + this.state.myTickets[id].finalPrice.toString(), 70, 125);
                doc.text('Purchase Date: ' + this.state.myTickets[id].purchaseDate, 70, 140);
                doc.save('a4.pdf');
            })
            .catch(err => {
                this.props.history.replace('/error');
            });

    };


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
                          purchaseDate={event.purchaseDate}
                          click = {() => this.onClickHandler(i)}/>
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