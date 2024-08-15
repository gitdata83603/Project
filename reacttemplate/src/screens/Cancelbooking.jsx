import Navbar from '../components/navbar'
import { useState,useEffect } from 'react'
import { Link, useNavigate,useLocation } from 'react-router-dom'
import { toast } from 'react-toastify'
import {getTrainDetails} from '../services/Train'
import {loadtraintraveldates} from '../services/Train'
import React from 'react';
import Trainsimple from '../components/Trainsimple'
import {loadtraindatebookings} from '../services/Booking'
import Usernavbar from "../components/Usernavbar"
import {loaduserbookings,CancelBooking} from '../services/Booking'


function Cancelbooking() {
    const [bookings,setBookings]=useState([])
    const [update,setUpdate]=useState(0)
    const [reason,setReason]=useState('')
    const location=useLocation()
    const navigate=useNavigate()
   
    const onSave = async () => {
        if (reason.length == 0) {
          toast.error('please enter Reason For Cancellation')
        } 
        else 
        {
         const result = await CancelBooking(location.state.id, reason)
         toast.warning(result.message)
        }
      }
    

    return(
        <div>
            <Usernavbar/>
            <h2 className='page-header' style={{marginTop:'20px'}}>Booking Cancellation</h2>
            {/* const arr={trainName,fromStation,toStation,totalSeatsBooked,startDate,refundprice}
         navigate('/cancelbooking',{state:{id:bookingid,arr:arr}}) */}
           {/* <table>
               <tbody>
                     <tr>
        <td>From Station</td>
        <td>{location.state.arr.fromStation}</td>
                     </tr>
               </tbody>
           </table> */}


<div className="container mt-3">
      <div className="table-responsive">
        <table className="table table-bordered">
          <tbody>
           <tr>
              <td>Train Name</td>
             <td>{location.state.arr.trainName}</td>
            </tr>
            <tr>
              <td>Source</td>
             <td>{location.state.arr.fromStation}</td>   
            </tr>
            <tr>
              <td>Destination</td>
             <td>{location.state.arr.toStation}</td>
            </tr>
            <tr>
              <td>Total seats Booked</td>
             <td>{location.state.arr.totalSeatsBooked}</td>
            </tr>
            <tr>
              <td>Journey Date</td>
             <td>{location.state.arr.startDate}</td>
            </tr>
            <tr>
              <td>refundprice</td>
             <td>{location.state.arr.refundprice}</td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>



    <h4 style={{marginTop:'40px'}}>Reason For Cancellation</h4>
    <div className='row' style={{marginTop:'5px'}} >
            <div className='col'></div>
            <div className='col'>
                <div className='form'> 
                <div className='mb-3'>
                    <br/>
                    <br/>
                    <textarea name="" id="" cols="60" rows="5" onChange={(e) => setReason(e.target.value)} ></textarea>
                  
                </div> 
                <div className='mb-3'>
                    <button onClick={()=>{onSave()}} className='btn btn-danger mt-2'>
                       Cancel Bookings
                    </button>
                </div>
                </div>
            </div>
            <div className='col'></div>
            </div>



        </div>



    )
}
export default Cancelbooking