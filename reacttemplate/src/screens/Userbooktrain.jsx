import Usernavbar from "../components/Usernavbar"
import { useState,useEffect } from 'react'
import { Link, useNavigate,useLocation } from 'react-router-dom'
import { toast } from 'react-toastify'
import {SaveBooking} from '../services/Booking'

function Userbooktrain() {
    
    const [firstarr,setFirstarr]=useState([])
    const [ecoarr,setEcoarr]=useState([])
    const [update,setUpdate]=useState(0)
    const [firstseats,setFirstseats]=useState(0)
    const [economyseats,setEconomyseats]=useState(0)
    const [finfo,setFinfo]=useState([])
    const [passangers,setPassangers]=useState('')

    const navigate=useNavigate()
    const location=useLocation()


    const [stationcode,setStationcode] = useState('')
    const [name,setName] = useState('')

  
    // get the navigation hook
   // const navigate = useNavigate()
  
    const onSave = async () => {
      if (firstseats.length == 0) {
        toast.error('please enter first class seats')
      } else if (economyseats.length == 0) {
        toast.error('please enter Economy class seats')
      } 
      else 
      {
        const result = await SaveBooking(location.state.trainid, location.state.userid,location.state.tavelDateId,location.state.sourceRouteid,
            location.state.destinationRouteid,firstseats,economyseats,passangers )
         //  console.log(result)
             toast.warning(result.message)
      }
    }
     {/* {
                "trainId": 0,//
                "userId": 0,//
                "trainScheduleId": 0,//
                "destinationRouteid": 0,//
                "totalFirstSeats": 0,
                "totalEconomySeats": 0,
                "passangerInfo": "string",
                "sourceRouteid": 0//
                } */}


        {/* trainid,tavelDateId,sourceRouteid,destinationRouteid,userid,  source,sourcearrival,destination,destinatiionarrival,date */}
         {/* <h5>{location.state.trainid}</h5>
         <h5>{location.state.tavelDateId}</h5>
         <h5>{location.state.sourceRouteid}</h5>
         <h5>{location.state.destinationRouteid}</h5>
         <h5>{location.state.userid}</h5>
         <h5>{location.state.source}</h5> */}


 
//     const loadtrainDetails = () => {
//      // const result = await getTrainDetails(id)
//      setFirstseats(sessionStorage['F'])
//      setEconomyseats(sessionStorage['E'])

//    }
 
    //  for (let index = 0; index < sessionStorage['F']; index++) {
    //      firstarr.push(index)
         
    //  }
    //  for (let index = 0; index < sessionStorage['E']; index++) {
    //      ecoarr.push(index)
    //  }


//     useEffect(() => {
//      loadtrainDetails()
//      setUpdate(0)
//    }, [update])



   return(
       <div>
           <Usernavbar/>
           <h2>Booking</h2>
        
    
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
                    </tr>
                </thead>
                <tbody>                
                                <tr>
                                   <td>{1}</td>
                                   <td>{location.state.number}</td>
                                   <td>{location.state.name}</td>
                                   <td>{location.state.source}({location.state.sourcearrival})</td>
                                   <td>{location.state.destination}({location.state.destinatiionarrival})</td>
                                   <td>{location.state.availableseats}</td>
                                   <td>{location.state.dist}</td>
                                </tr>
                </tbody>
                </table>
            </div>
         </div>

    


            <div className='row'>
            <div className='col'></div>
            <div className='col'>
                <div className='form'>
                <div className='mb-3'>
                    {/* <label htmlFor=''>Station Code</label> */}
                    <input
                    onChange={(e) => setEconomyseats(e.target.value)}
                    type='number'
                    className='form-control'
                    placeholder='economy seats'
                    />
                </div>
                <div className='mb-3'>
                    {/* <label htmlFor=''>Station Name</label> */}
                    <input
                    onChange={(e) => setFirstseats(e.target.value)}
                    type='number'
                    className='form-control'
                    placeholder='first class seats'
                    />
                </div>  
                <div className='mb-3'>
                    <label htmlFor=''>Passanger Info</label>
                    <br/>
                    <br/>
                    <textarea name="" id="" cols="30" rows="10" onChange={(e) => setPassangers(e.target.value)} placeholder='passanger info'></textarea>
                  
                </div> 
                <div className='mb-3'>
                    <button onClick={()=>{onSave()}} className='btn btn-success mt-2'>
                       Book Train
                    </button>
                </div>
                </div>
            </div>
            <div className='col'></div>
            </div>









         {/* <h4>First Class Seats</h4>
          {
              
              firstarr.map((ele,index)=>{
                  return(
                    //   <h2>First</h2>
                    <div>
                        passanger {index+1} : <input type="text" placeholder='name' />
                        <br/>
                        <br/>
                    </div>
                    
                  )
              })
          } */}


    
     
         {/* <h4>Economy Class Seats</h4>
      
        {        
              
              ecoarr.map((ele,index)=>{
                  return(
                      <h2>Economy</h2>
                  )
              })
          } */}



       </div>
   )

}
export default Userbooktrain