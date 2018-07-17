import React,{ Component } from 'react';
import { Table,Pagination } from 'react-bootstrap';
import { Animated } from "react-animated-css";
import axios from 'axios';
import './MyTickets.css'
import TicketEvent from '../../components/TicketEvent/TicketEvent';
import pdfGenerator from './pdfGenerator';

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
            this.setState({myTickets: res.data});
        })
        .catch(err => {
            this.props.history.replace('/error');
        });
    }

    onClickHandler = (id) => {
        axios.get(`http://localhost:8080/TicketMonster/api/users/${parseInt(sessionStorage.getItem('userID'),10)}`)//userid
            .then(res2 => {
                this.setState({userDetails: res2.data});
            })
            .then(re2 => {
                const myTickets = {...this.state.myTickets};
                const userDetails = {...this.state.userDetails};

                pdfGenerator(userDetails.name,userDetails.surname,userDetails.email,myTickets[id].eventDescr,myTickets[id].quantity,myTickets[id].finalPrice,myTickets[id].purchaseDate);
            })
            .catch(err => {
                this.props.history.replace('/error');
            });

    };


    renderList() {
        const list = this.state.myTickets;
        const ref = (this.state.page-1)*10;
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
            this.setState({page: num});
        }

        renderPagination() {
            let PagBtnsNum = this.state.myTickets.length/10 + 1;
            PagBtnsNum = Math.floor(PagBtnsNum);
            let items = [];

            let empty = this.lastPageEmpty();

            if(empty){
                PagBtnsNum=PagBtnsNum-1;
            }

            if(PagBtnsNum>1){
                for (let number = 1; number <= PagBtnsNum; number++) {
                    items.push(
                        <Pagination.Item active={number === this.state.page} key={number} id={number} onClick={()=>this.itemclick(number)}>{number}</Pagination.Item>
                    );
            }
            }
            
            return (
              <div className="alignCenter">
                <Pagination bsSize="large">{items}</Pagination>
                <br />
              </div>
            );
            
          }

          lastPageEmpty(){
            const list = this.state.myTickets;
            let PagBtnsNum= list.length/10 + 1;
            PagBtnsNum= Math.floor(PagBtnsNum);
            const ref = (PagBtnsNum-1)*10;
            const pageLength = list.slice(ref, ref+10).length;
            return (pageLength===0);
        }
      

    render(){
       
        return(
            <Animated animationIn="fadeIn" isVisible={true}>
                <div className="App">
                <h1 className='ticketsh1'>My tickets</h1>
                {this.renderList()}
                {this.renderPagination()}
            </div>
           </Animated>
           );
    }
}

export default MyTickets;