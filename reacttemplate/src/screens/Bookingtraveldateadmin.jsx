import Navbar from '../components/navbar'
import { useState,useEffect } from 'react'
import { Link, useNavigate,useLocation } from 'react-router-dom'
import { toast } from 'react-toastify'
import {getTrainDetails} from '../services/Train'
import {loadtraintraveldates} from '../services/Train'
import React from 'react';
import Trainsimple from '../components/Trainsimple'
function Bookingtraveldateadmin() {
    


  /*

   {
    "travelDateId": 5,
    "trainid": 2,
    "number": "INDMH1212",
    "startDate": "2024-07-14",
    "totalseats": 100,
    "bookedSeats": 8,
    "totalEconomyClassBookedSeats": 2,
    "totalFirstClassBookedSeats": 6,
    "totalEconomyClassSeats": 80,
    "totalFirstClassSeats": 20
  }
  */  
 const [trains, setTrains] = useState([])
 const [update,setUpdate]=useState(0)
 const location=useLocation()
 const navigate=useNavigate()

 const loadalltrains=async()=>{
    const result=await loadtraintraveldates(location.state.id)
     setTrains(result)
 }

 useEffect(() => {
     loadalltrains()
   }, [update])

function loadonetrain(travleid,startDate) {
    navigate('/bookinginfotraveldate',{state:{id:travleid,trainnumber:location.state.id,date:startDate}})
}


 return(
     <div>
        <Navbar/>
        <h2 className='page-header' style={{marginTop:'20px'}}>Train Booking Details</h2>
        <Trainsimple trainnumber={location.state.id}/>
        <div className="container mt-4">
         <div className="table-responsive">
             <table className="table table-bordered">
             <thead className="table-dark">
                 <tr>
                 <th>#</th>
                 <th>startDate</th>
                 <th>totalseats</th>
                 <th>totalEconomyClassSeats</th>
                 <th>totalFirstClassSeats</th>
                 <th>bookedSeats</th>
                 <th>totalEconomyClassBookedSeats</th>
                 <th>totalFirstClassBookedSeats</th>
                 <th>Details</th>
                 </tr>
             </thead>
             <tbody>
                 
                  {
                  trains.map((train,index)=>{
                         return(
                             <tr>
                                <td>{index+1}</td>
                                <td>{train.startDate}</td>
                                <td>{train.totalseats}</td>
                                <td>{train.totalEconomyClassSeats}</td>
                                <td>{train.totalFirstClassSeats}</td>
                                <td>{train.bookedSeats}</td>
                                <td>{train.totalEconomyClassBookedSeats}</td>
                                <td>{train.totalFirstClassBookedSeats}</td>
                                <td>
                                <button onClick={()=>{loadonetrain(train.travelDateId,train.startDate)}} class="btn btn-dark">Details</button>
                                </td>
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

export default Bookingtraveldateadmin