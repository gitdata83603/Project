import Navbar from '../components/navbar'
import { useState,useEffect } from 'react'
import { Link, useNavigate,useLocation } from 'react-router-dom'
import { toast } from 'react-toastify'
import {getTrainDetails} from '../services/Train'
import React from 'react';
import {Getstation} from '../services/Station'
import {SaveRoute} from '../services/Route'

function Routes1()
{
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

    /*
(arrival,departure,startStationId,distance,train.id)

    "id": 2,
  "number": "INDMH1212",
  "name": "MAHARASHTRA EXPRESS",
  "date": null,
  "sourcearrival": "10:00:00",
  "destinationStationName": "DELHI",
  "destinatiionarrival": "01:10:00",
  "totalseats": 100,
  "distance": 1000,
  "fare": null,
    */
    const loadtrainDetails = async (id) => {
      const result = await getTrainDetails(id)
      setTrain(result)
      setRoutes(result.routes)
    }

    const loadStations = async () => {
        const result = await Getstation();
          setStations(result);        
      }

    useEffect(() => {
      loadtrainDetails(location.state.id) 
      loadStations()
      setUpdate(0)
    }, [update])


    const handleStartStationChange = (e) => {
        setStartStationId(e.target.value);
      };

  /*

      <div className='mb-3'>
                  <label htmlFor='startStation' className='form-label'>Start Station</label>
                  <select
                    id='startStation'
                    className='form-select'
                    value={startStationId}
                    onChange={handleStartStationChange}
                  >
                    <option value="">Select a station</option>
                    {stations.map((station) => (
                      <option key={station.id} value={station.id}>{station.name}</option>
                    ))}
                  </select>
                </div>
  */


 const onSave = async () => {
    if (arrival.length == 0 || departure.length==0) {
      toast.error('please enter arrival and departure time')
    } else if (distance.length == 0) {
      toast.error('please enter distance')
    } 
    else 
    {
      const result = await SaveRoute(arrival,departure,startStationId,distance,train.id)
      toast.warning(result.message)
      setUpdate(1)
     // navigate('/routes',{ state: { id: result.number } })
    }
  }






 return(
  <div>
    <Navbar/>
    {/* <h2 className='page-header'>Add Route</h2> */}
    <div className='row'>
      <div className='col'></div>
      <div className='col'>
        <div className='form'>
        <div className='mb-3'>
        <h5 style={{marginTop:'40px'}}>Train Info</h5>
            </div>
          <div className='mb-3'>
              {/* <h5 style={{marginTop:'40px' ,marginBottom:'1px'}}>Train Info</h5> */}
            <table className='table table-striped mt-5'>
             <thead value='Train Info'>
                <tr>
                <th>NUMBER</th>
                <th>NAME</th>
                <th>FROM</th>
                <th>TO</th>
                <th>DISTANCE</th>
                <th>ARRIVAL</th>
                <th>END</th>
                </tr>
             </thead>
            <tbody>
            
                {/* "id": 2,
            "number": "INDMH1212",
            "name": "MAHARASHTRA EXPRESS",
            "date": null,
            "sourcearrival": "10:00:00",
            "destinationStationName": "DELHI",
            "destinatiionarrival": "01:10:00",
            "totalseats": 100,
            "distance": 1000,
            "fare": null, */}
                
                <tr>
                    <td>{train.number}</td>
                    <td>{train.name}</td>
                    <td>{train.sourceStationName}</td>
                    <td>{train.destinationStationName}</td>
                    <td>{train.distance}</td>
                    <td>{train.sourcearrival}</td>
                    <td>{train.destinatiionarrival}</td>
                </tr>
                

            </tbody>
            </table>
            {/* <div className='mt-3'>
             {
                 routes.map((route)=>{
                     return(
                     <h5>{route.stationName}--></h5>
                     
                     )
                 })
             }
               </div> */}
           <h5 style={{marginTop:'80px'}}>Routes[distance](arrivalTime)</h5>
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
   
                        {/* {
                    "distance": 0,
                    "arrival": "string",
                    "departure": "string",
                    "train_id": 0,
                    "station_id": 0
                    } */}  

<div className="container mt-4">
      <h2>Add Route</h2>
      <div className='row'>
        <div className='col'></div>
        <div className='col-8'>
          <div className='card'>
            <div className='card-body'>
              <form>   
                <div className='mb-3'>
                  <label htmlFor='startStation' className='form-label'>Station Name</label>
                  <select
                    id='startStation'
                    className='form-select'
                    value={startStationId}
                    onChange={handleStartStationChange}
                  >
                    <option value="">Select a station</option>
                    {stations.map((station) => (
                      <option key={station.id} value={station.id}>{station.name}</option>
                    ))}
                  </select>
                </div>
             
                <div className='row'>
                  <div className='col'>
                    <div className='mb-3'>
                      <label htmlFor='arrivalTime' className='form-label'>Arrival Time</label>
                      <input
                        id='arrivalTime'
                        type='time'
                        className='form-control'
                        value={arrival}
                        onChange={(e) => setArrival(e.target.value)}
                      />
                    </div>
                  </div>
                  <div className='col'>
                    <div className='mb-3'>
                      <label htmlFor='departureTime' className='form-label'>Departure Time</label>
                      <input
                        id='departureTime'
                        type='time'
                        className='form-control'
                        value={departure}
                        onChange={(e) => setDeparture(e.target.value)}
                      />
                    </div>
                  </div>
                </div>        
                <div className='mb-3'>
                  <label htmlFor='totalDistance' className='form-label'>Total Distance</label>
                  <input
                    id='totalDistance'
                    type='number'
                    className='form-control'
                    value={distance}
                    onChange={(e) => setDistance(e.target.value)}
                  />
                </div>
                <div className='text-center'>
                  <button type='button' onClick={onSave} className='btn btn-success'>
                    Add Route
                  </button>
                </div>
              </form>
            </div>
          </div>
        </div>
        <div className='col'></div>
      </div>
    </div>






          </div>
        </div>
      </div>
      <div className='col'></div>
    </div> 
</div>
 )
}
export default Routes1

