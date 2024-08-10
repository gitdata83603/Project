import { useState,useEffect } from 'react'
import { Link, useNavigate,useLocation } from 'react-router-dom'
import { toast } from 'react-toastify'
import Navbar from '../components/navbar'
import Trainroutes from '../components/Trainroutes'
import {getTrainDetails} from '../services/Train'
import React from 'react';
import {Getstation} from '../services/Station'
import {SaveRoute} from '../services/Route'
import {UpdateArrivalTime} from '../services/Train'
function Updatetrainarrivaltime()
{
//     const location = useLocation()
//     const [update,setUpdate]=useState(0)
//     const [trainnumber,setTrainnumber]=useState('INDMH9941')
    
//     // useEffect(() => {
//     //     setTrainnumber(location.state.id)     
//     //     setUpdate(0)
//     //   }, [update])

//    return(
//        <div>
//            <Navbar/>
//            <Trainroutes trainnumber={location.state.id}/>
//        </div>
//    )


const [train, setTrain] = useState('')
const [routes, setRoutes] = useState([])
const [stations, setStations] = useState([])
const [distance, setDistance] = useState(0);
const [arrival, setArrival] = useState("");//
const [departure, setDeparture] = useState("");//
const [startStationId, setStartStationId] = useState(0);//
const [update,setUpdate]=useState(0)
const location = useLocation() 
const navigate=useNavigate()
const[traindates,setTraindate]=useState([])
const [arrivaltime,setArrivaltime]=useState('')

const loadtrainDetails = async (id) => {
  const result = await getTrainDetails(id)
  setTrain(result)
  setRoutes(result.routes)
  setTraindate(result.dates)
}

const loadStations = async () => {
    const result = await Getstation();
      setStations(result);        
  }

useEffect(() => {
  loadtrainDetails(location.state.id)  //(location.state.id) 
  loadStations()
  setUpdate(0)
}, [update])


const handleStartStationChange = (e) => {
    setStartStationId(e.target.value);
  };


const onSave = async () => {
if (arrivaltime.length == 0) {
  toast.error('please enter arrival time')
}
else 
{
  const result = await UpdateArrivalTime(arrivaltime,train.number)
  toast.warning(result.message)
  setUpdate(1)
 // navigate('/routes',{ state: { id: result.number } })
}
}






return(
<div>
<Navbar/>
{/* <h2 className='page-header'>Add Route</h2> */}
   <div className="container mt-3">
            <div className="table-responsive">
                <table className="table table-striped table-hover">
                    <thead>
                        <tr>
                        <th>NUMBER</th>
                        <th>NAME</th>
                        <th>FROM</th>
                        <th>TO</th>
                        <th>DISTANCE</th>
                        <th>Seats</th>
                        <th>Travel Dates</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                        <td>{train.number}</td>
                        <td>{train.name}</td>
                        <td>{train.sourceStationName}({train.sourcearrival})</td>
                        <td>{train.destinationStationName}({train.destinatiionarrival})</td>
                        <td>{train.distance}</td>
                        <td>{train.totalseats}</td>
                        <td>{
                              traindates.map((date)=>{
                                  return(
                                  <div>{date.startDate}</div>
                                  )
                              })
                            }</td>
                        </tr>
                    </tbody>
                </table>
            </div>
    </div>
       <h5 style={{marginTop:'80px'}}>Routes[distance](arrivalTime)</h5>
       <div className="container mt-3">
      <div style={{
                    backgroundColor: 'burlywood',
                    padding: '10px',
                    border: '1px solid #ccc',
                    borderRadius: '5px',
                    fontSize: '16px',
                    
                    marginTop: '10px',
                }}>
        
        { routes.map((route, index) => (
         <React.Fragment key={index}>
            {route.stationName}
            [{route.distanceFromStart}]
            ({route.arrivalTime})
            {index !== routes.length - 1 && ' --> '}
        </React.Fragment>
            ))}
      </div>
      </div>
      <div className='row' style={{marginTop:'50px'}}>
      <div className='col'></div>
      <div className='col'>
        <div className='form'>
          <div className='mb-3' style={{border:'5px'}}>
            <label htmlFor=''>Train Arrival Time</label>
            <input
              onChange={(e) => {setArrivaltime(e.target.value)}}
              type='time'
              className='form-control'
              style={{textAlign:'center'}}
            />
          </div>
          <div className='mb-3'>
            <button onClick={onSave} className='btn btn-success mt-2'>
              Update Time
            </button>
          </div>
        </div>
      </div>
      <div className='col'></div>
    </div>


</div>

)


}
export default Updatetrainarrivaltime