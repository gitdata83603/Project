import { useState,useEffect } from 'react'
import { Link, useNavigate,useLocation } from 'react-router-dom'
import { toast } from 'react-toastify'
import Usernavbar from "../components/Usernavbar";

function Usersearchtrainlist() {
    
   const [trains,setTrains]=useState([])
   const [update,setUpdate]=useState(0)
   const navigate=useNavigate()
   const location=useLocation()

   const loadtrainDetails = () => {
    // const result = await getTrainDetails(id)
    setTrains(location.state.id)
    
  }

  function loadonetrain(trainid,tavelDateId,sourceRouteid,destinationRouteid,userid,  source,sourcearrival,destination,destinatiionarrival,date,number,name,availableseats,dist) {
    navigate('/userbooktrain',{state:{trainid:trainid,tavelDateId:tavelDateId,sourceRouteid:sourceRouteid,destinationRouteid:destinationRouteid,userid:userid,source:source,sourcearrival:sourcearrival,destination:destination,destinatiionarrival:destinatiionarrival,date:date,number:number,name:name
    ,availableseats:availableseats,dist:dist}})
}
   useEffect(() => {
    loadtrainDetails()
    setUpdate(0)
  }, [update])

   return(
       <div>
           <Usernavbar/>
           <h1>result of search trains</h1>
           

           <div className="container mt-4">
            <div className="table-responsive">
                <table className="table table-bordered">
                <thead className="table-dark">
                    <tr>
                    <th>#</th>
                    <th>Number</th>
                    <th>Name</th>
                    <th>SourceStation</th>
                    <th>DestStation</th>
                    <th>AvailableSeats</th>
                    <th>Distance</th>
                    <th>Price</th>
                    <th></th>
                    </tr>
                </thead>
                <tbody>
                               {/* [
  {
    "id": 5,
    "tavelDateId": 20,
    "destinationRouteid": 18,
    "number": "MH1020",//
    "name": "palus",//
    "date": "2024-08-16",
    "sourcearrival": "10:00:00",//
    "destination": "satara",//
    "destinatiionarrival": "14:00:00",//
    "totalseats": 200,
    "availableseats": 200,//
    "dist": 100,//
    "fare": null,
    "sourceRouteid": 17,
    "source": "palus"//
  }
] */}
                     {
                       
                      trains.map((train,index)=>{
                            return(
                                <tr>
                                   <td>{index+1}</td>
                                   <td>{train.number}</td>
                                   <td>{train.name}</td>
                                   <td>{train.source}({train.sourcearrival})</td>
                                   <td>{train.destination}({train.destinatiionarrival})</td>
                                   <td>{train.availableseats}</td>
                                   <td>{train.dist}</td>
                                   <td>{train.price}</td>
                                   <td>
                                   <button onClick={()=>{loadonetrain(train.id,train.tavelDateId,train.sourceRouteid,train.destinationRouteid,sessionStorage['id'],train.source,train.sourcearrival,train.destination,train.destinatiionarrival,train.date,train.number,train.name,train.availableseats,train.dist)}} class="btn btn-dark">Book</button>
                                   {/* {
  "trainId": 0,//
  "userId": 0,
  "trainScheduleId": 0,//
  "destinationRouteid": 0,//
  "totalFirstSeats": 0,
  "totalEconomySeats": 0,
  "passangerInfo": "string",
  "sourceRouteid": 0//
}                        */}
                                  
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

export default Usersearchtrainlist