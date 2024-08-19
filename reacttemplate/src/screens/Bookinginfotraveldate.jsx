import Navbar from '../components/navbar'
import { useState,useEffect } from 'react'
import { Link, useNavigate,useLocation } from 'react-router-dom'
import { toast } from 'react-toastify'
import {getTrainDetails} from '../services/Train'
import {loadtraintraveldates} from '../services/Train'
import React from 'react';
import Trainsimple from '../components/Trainsimple'
import {loadtraindatebookings} from '../services/Booking'

function Bookinginfotraveldate() {
 
    const [bookings,setBookings]=useState([])
    const [update,setUpdate]=useState(0)
    const location=useLocation()
    const navigate=useNavigate()
   
    const loadallbookings=async()=>{
       const result=await loadtraindatebookings(location.state.id)
       setBookings(result)
    }
   
    useEffect(() => {
        loadallbookings()
      }, [update])
    /*
{
    "id": 23,
    "fromStation": "KADEGAON",
    "toStation": "PALUS",
    "startDate": "2024-08-15",
    "endDate": "2024-08-15",
    "totalSeatsBooked": 10,
    "allocatedSeatNuumbers": "1E , 2E , 3E , 4E , 5E , 1F , 2F , 3F , 4F , 5F , ",
    "ticketprice": 6250,
    "distance": 50,
    "passangerInfo": "string",
    "cancellationStatus": 0,
    "cancelled": "NO"
  }
    */

    return(
        <div>
        
            <Navbar/>
            <h2 className='page-header' style={{marginTop:'20px'}}> Booking Details</h2>
            <Trainsimple trainnumber={location.state.trainnumber}/>
            <h5 style={{marginTop:'100px'}}>TravelDate : {location.state.date}</h5>

                <div className="container mt-4">
                <div className="table-responsive">
                    <table className="table table-bordered">
                    <thead className="table-dark">
                        <tr>
                        <th>#</th>
                        <th>fromStation</th>
                        <th>toStation</th>
                        <th>startDate</th>
                        <th>endDate</th>
                        <th>totalSeatsBooked</th>
                        <th>allocatedSeatNuumbers</th>
                        <th>passangerInfo</th>
                        <th>distance</th>
                        <th>ticketprice</th>
                        </tr>
                    </thead>
                    <tbody>
                        
                        {
                        bookings.map((booking,index)=>{
                                return(
                                    <tr>
                                        <td>{index+1}</td>
                                        <td>{booking.fromStation}</td>
                                        <td>{booking.toStation}</td>
                                        <td>{booking.startDate}</td>
                                        <td>{booking.endDate}</td>
                                        <td>{booking.totalSeatsBooked}</td>
                                        <td>{booking.allocatedSeatNuumbers}</td>
                                        <td>{booking.passangerInfo}</td>
                                        <td>{booking.distance}</td>
                                        <td>{booking.ticketprice}</td>
                                        
                                    </tr>
                                )
                            })
                        }
                        
                    </tbody>
                    </table>
                </div>
            </div>
        </div>
    )
}
export default Bookinginfotraveldate