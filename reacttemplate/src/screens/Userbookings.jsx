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
import {loaduserbookings} from '../services/Booking'

function Userbookings() {

    // return(
    //     <div>
    //       <Usernavbar/>
    //       <h2>My Bookings</h2>

    //     </div>
    // )
    const [bookings,setBookings]=useState([])
    const [update,setUpdate]=useState(0)
    const location=useLocation()
    const navigate=useNavigate()
   
    const loadallbookings=async()=>{
       const result=await loaduserbookings(sessionStorage['id'])
       setBookings(result)
    }
   
    useEffect(() => {
        loadallbookings()
      }, [update])

      const onSave = async (bookingid,trainName,fromStation,toStation,totalSeatsBooked,startDate,refundprice) => {
        const arr={trainName,fromStation,toStation,totalSeatsBooked,startDate,refundprice}
         navigate('/cancelbooking',{state:{id:bookingid,arr:arr}})
      }
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
           <Usernavbar/>
            <h2 className='page-header' style={{marginTop:'20px'}}>My Booking</h2>
            {/* <Trainsimple trainnumber={location.state.trainnumber}/> */}
            {/* <h5 style={{marginTop:'100px'}}>TravelDate : {location.state.date}</h5> */}

                {/* <div className="container mt-6" style={{width:'100%'}}> */}
                <div className="table-responsive">
                <table className='table table-striped mt-5'>
                    <thead className="table-dark">
                        <tr>
                        <th>#</th>
                        <th>TrainNumber</th>
                        <th>TrainName</th>
                        <th>fromStation(Arrival)</th>
                        <th>toStation(EndTime)</th>
                        <th>startDate</th>
                        <th>endDate</th>
                        <th>totalSeatsBooked</th>
                        <th>allocatedSeatNuumbers</th>
                        <th>passangerInfo</th>
                        <th>distance</th>
                        <th>ticketprice</th>
                        <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        
                        {
                        bookings.map((booking,index)=>{
                                return(
                                    <tr>
                                        <td>{index+1}</td>
                                        <td>{booking.trainnumber}</td>
                                        <td>{booking.trainName}</td>
                                        <td>{booking.fromStation}({booking.sourceArrival})</td>
                                        <td>{booking.toStation}({booking.arrivalAtDestination})</td>
                                        <td>{booking.startDate}</td>
                                        <td>{booking.endDate}</td>
                                        <td>{booking.totalSeatsBooked}</td>
                                        <td>{booking.allocatedSeatNuumbers}</td>
                                        <td>{booking.passangerInfo}</td>
                                        <td>{booking.distance}</td>
                                        <td>{booking.ticketprice}</td>
                                        <td>
                                        <div className='text-center'>
                                            <button type='button' onClick={()=>{onSave(booking.id,booking.trainName,booking.fromStation,booking.toStation,booking.totalSeatsBooked,booking.startDate,booking.ticketprice)}} className='btn btn-danger'>
                                                Cancel
                                            </button>
                                            </div>
                                        </td>
                                    </tr>
                                )
                            })
                        }
                        
                    </tbody>
                    </table>
                </div>
            {/* </div> */}
        </div>
    )
}
export default Userbookings

// {
//     "id": 9,
//"trainnumber": "INDMH9941",
//"trainName": "KADEGAON EXPRESS",
//     "fromStation": "SANGLEE",
//     "toStation": "PALUS",
//     "startDate": "2024-07-11",
//     "endDate": "2024-07-11",
//     "totalSeatsBooked": 4,
//     "allocatedSeatNuumbers": "45E , 46E , 58F , 59F , ",
//     "ticketprice": 7000,
//     "distance": 50,
//     "passangerInfo": "avdhut(M)",
//     "sourceArrival": "10:15:00",
//     "sourceDeparture": "10:45:00",
//     "arrivalAtDestination": "12:25:00",
//     "cancellationStatus": null,
//     "cancelled": "NO"
//   }