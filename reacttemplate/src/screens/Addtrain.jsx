import Navbar from '../components/navbar'
import { useState,useEffect } from 'react'
import { Link, useNavigate } from 'react-router-dom'
import { toast } from 'react-toastify'
import {Savetrain} from '../services/Train'
import {Getstation} from '../services/Station'

function Addtrain()
{
  const [number, setNumber] = useState("");//
  const [name, setName] = useState("");//
  const [startStationId, setStartStationId] = useState(0);//
  const [endStationId, setEndStationId] = useState(0);//
  const [date, setDate] = useState("2024-08-03");//
  const [arrival, setArrival] = useState("");//
  const [departure, setDeparture] = useState("");//
  const [totalSeats, setTotalSeats] = useState(0);//
  const [totalEconomyClassSeats, setTotalEconomyClassSeats] = useState(0);//
  const [totalFirstClassSeats, setTotalFirstClassSeats] = useState(0);//
  const [bookedSeats, setBookedSeats] = useState(0);//
  const [totalEconomyClassBookedSeats, setTotalEconomyClassBookedSeats] = useState(0);//
  const [totalFirstClassBookedSeats, setTotalFirstClassBookedSeats] = useState(0);//
  const [distance, setDistance] = useState(0);//
  const [endDate, setEndDate] = useState("2024-08-03");//
  const [destArrival, setDestArrival] = useState("");//
  const [stations, setStations] = useState([])
  
/*
  const [number, setNumber] = useState("");//
  const [name, setName] = useState("");//
  const [totalSeats, setTotalSeats] = useState(0);//
  const [totalEconomyClassSeats, setTotalEconomyClassSeats] = useState(0);//
  const [totalFirstClassSeats, setTotalFirstClassSeats] = useState(0);//
  


*/

  const onSave = async () => {
    if (number.length == 0) {
      toast.error('please enter train number')
    } else if (name.length == 0) {
      toast.error('please enter train name')
    } 
    else 
    {
      const result = await Savetrain(number, name,startStationId,endStationId,date,arrival,departure,totalSeats,totalEconomyClassSeats,totalFirstClassSeats
        ,bookedSeats,totalEconomyClassBookedSeats,totalFirstClassBookedSeats,distance,endDate,destArrival)
      toast.warning(result.message)
    }
  }

  
  const loadStations = async () => {
    const result = await Getstation();
      setStations(result);        
  }

  useEffect(() => {loadStations()}, [])



  const handleStartStationChange = (e) => {
    setStartStationId(e.target.value);
  };

  // Handler for end station selection
  const handleEndStationChange = (e) => {
    setEndStationId(e.target.value);
  };
  

  return (
    <div>
  
      <Navbar/>
    <div className="container mt-4">
      <h2>Add Train</h2>
      <div className='row'>
        <div className='col'></div>
        <div className='col-8'>
          <div className='card'>
            <div className='card-body'>
              <form>
                <div className='mb-3'>
                  <label htmlFor='trainNumber' className='form-label'>Train Number</label>
                  <input
                    id='trainNumber'
                    type='text'
                    className='form-control'
                    value={number}
                    onChange={(e) => setNumber(e.target.value)}
                  />
                </div>
                <div className='mb-3'>
                  <label htmlFor='trainName' className='form-label'>Train Name</label>
                  <input
                    id='trainName'
                    type='text'
                    className='form-control'
                    value={name}
                    onChange={(e) => setName(e.target.value)}
                  />
                </div>
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
                <div className='mb-3'>
                  <label htmlFor='endStation' className='form-label'>Destination Station</label>
                  <select
                    id='endStation'
                    className='form-select'
                    value={endStationId}
                    onChange={handleEndStationChange}
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
                      <label htmlFor='travelStartDate' className='form-label'>Travel Start Date</label>
                      <input
                        id='travelStartDate'
                        type='date'
                        className='form-control'
                        value={date}
                        onChange={(e) => setDate(e.target.value)}
                      />
                    </div>
                  </div>
                  <div className='col'>
                    <div className='mb-3'>
                      <label htmlFor='travelEndDate' className='form-label'>Travel End Date</label>
                      <input
                        id='travelEndDate'
                        type='date'
                        className='form-control'
                        value={endDate}
                        onChange={(e) => setEndDate(e.target.value)}
                      />
                    </div>
                  </div>
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
                  <label htmlFor='destArrivalTime' className='form-label'>Destination Arrival Time</label>
                  <input
                    id='destArrivalTime'
                    type='time'
                    className='form-control'
                    value={destArrival}
                    onChange={(e) => setDestArrival(e.target.value)}
                  />
                </div>
                <div className='row'>
                  <div className='col'>
                    <div className='mb-3'>
                      <label htmlFor='totalSeats' className='form-label'>Total Seats</label>
                      <input
                        id='totalSeats'
                        type='number'
                        className='form-control'
                        value={totalSeats}
                        onChange={(e) => setTotalSeats(e.target.value)}
                      />
                    </div>
                  </div>
                  <div className='col'>
                    <div className='mb-3'>
                      <label htmlFor='totalEconomyClassSeats' className='form-label'>Total Economy Class Seats</label>
                      <input
                        id='totalEconomyClassSeats'
                        type='number'
                        className='form-control'
                        value={totalEconomyClassSeats}
                        onChange={(e) => setTotalEconomyClassSeats(e.target.value)}
                      />
                    </div>
                  </div>
                  <div className='col'>
                    <div className='mb-3'>
                      <label htmlFor='totalFirstClassSeats' className='form-label'>Total First Class Seats</label>
                      <input
                        id='totalFirstClassSeats'
                        type='number'
                        className='form-control'
                        value={totalFirstClassSeats}
                        onChange={(e) => setTotalFirstClassSeats(e.target.value)}
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
                    Add Train
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
  )
}

export default Addtrain


// {
//     "number": "string",
//     "name": "string",
//     "startstationid": 0, //dropdown
//     "endstationid": 0,    //dropdown
//     "date": "2024-08-02",  //input type date
//     "arrival": "string",   //input type time
//     "departure": "string", //input type time
//     "totalseats": 0,
//     "totalEconomyClassSeats": 0,
//     "totalFirstClassSeats": 0,
//     "bookedseats": 0,
//     "totalEconomyClassBookedSeats": 0,
//     "totalFirstClassBookedSeats": 0,
//     "distance": 0,
//     "enddate": "2024-08-02",  //input type date
//     "destarrival": "string"   //input type time
//   }

//****************************************************OLD CODE *******************/

  // const handleStationChange = (e) => {
  //   setStartStationId(e.target.value); // Update startStationId state when selection changes
  // };
  // const handleStationChangeabc = (e) => {
  //   setEndStationId(e.target.value); // Update startStationId state when selection changes
  // };


  // return(
  //     <div>
  //          <Navbar/>
  //          <h2>Add Train</h2>
  //          <div className='row'>
  //           <div className='col'></div>
  //           <div className='col'>
  //             <div className='form'>
  //               <div className='mb-3'>
  //                 <label htmlFor=''>Train Number</label>
  //                 <input
  //                   onChange={(e) => setNumber(e.target.value)}
  //                   type='text'
  //                   className='form-control'
  //                 />
  //               </div>
  //               <div className='mb-3'>
  //                 <label htmlFor=''>Train Name</label>
  //                 <input
  //                   onChange={(e) => setName(e.target.value)}
  //                   type='text'
  //                   className='form-control'
  //                 />
  //               </div>  

  //               {/* station */}
  //               <div className='mb-3'>
  //                 <form>
  //                <label htmlFor="station">Start Station:</label>
  //                 <select name="station" id="station" value={startStationId} onChange={handleStationChange}>
  //                       <option value="">Select a station</option>
  //                          {stations.map((station) => (
  //                       <option key={station.id} value={station.id}>
  //                         {station.name}
  //                       </option>
  //                       ))}
  //                   </select>
  //                </form>
  //              </div>
  //              <div className='mb-3'>
  //              <form>
  //                <label htmlFor="station">Destination Station:</label>
  //                 <select name="station" id="station" value={endStationId} onChange={handleStationChangeabc}>
  //                       <option value="">Select a station</option>
  //                          {stations.map((station) => (
  //                       <option key={station.id} value={station.id}>
  //                         {station.name}
  //                       </option>
  //                       ))}
  //                   </select>
  //                </form>
  //              </div>

              

  //               {/* date */}
  //               <div className='mb-3'>
  //                 <label htmlFor=''>Travel start Date</label>
  //                 <input
  //                   onChange={(e) => setDate(e.target.value)}
  //                   type='date'
  //                   className='form-control'
  //                 />
  //               </div>  
  //               <div className='mb-3'>
  //                 <label htmlFor=''>Travel End Date</label>
  //                 <input
  //                   onChange={(e) => setEndDate(e.target.value)}
  //                   type='date'
  //                   className='form-control'
  //                 />
  //               </div>
                
  //               {/* time */}
  //               <div className='mb-3'>
  //                 <label htmlFor=''>Arrival Time</label>
  //                 <input
  //                   onChange={(e) => setArrival(e.target.value)}
  //                   type='time'
  //                   className='form-control'
  //                 />
  //               </div>
  //               <div className='mb-3'>
  //                 <label htmlFor=''>Departure Time</label>
  //                 <input
  //                   onChange={(e) => setDeparture(e.target.value)}
  //                   type='time'
  //                   className='form-control'
  //                 />
  //               </div>
  //               <div className='mb-3'>
  //                 <label htmlFor=''>Destination arrival Time</label>
  //                 <input
  //                   onChange={(e) => setDestArrival(e.target.value)}
  //                   type='time'
  //                   className='form-control'
  //                 />
  //               </div>



  //               {/* seats 
  //               */}
  //               <div className='mb-3'>
  //                 <label htmlFor=''>Total Seats</label>
  //                 <input
  //                   onChange={(e) => setTotalSeats(e.target.value)}
  //                   type='number'
  //                   className='form-control'
  //                 />
  //               </div>
  //               <div className='mb-3'>
  //                 <label htmlFor=''>Total Economy class Seats</label>
  //                 <input
  //                   onChange={(e) => setTotalEconomyClassSeats(e.target.value)}
  //                   type='number'
  //                   className='form-control'
  //                 />
  //               </div>
  //               <div className='mb-3'>
  //                 <label htmlFor=''>Total First class Seats</label>
  //                 <input
  //                   onChange={(e) => setTotalFirstClassSeats(e.target.value)}
  //                   type='number'
  //                   className='form-control'
  //                 />
  //               </div>

  //               <div className='mb-3'>
  //                 <label htmlFor=''>Total Distance</label>
  //                 <input
  //                   onChange={(e) => setDistance(e.target.value)}
  //                   type='number'
  //                   className='form-control'
  //                 />
  //               </div>



  //               <div className='mb-3'>
  //                 <button onClick={onSave} className='btn btn-success mt-2'>
  //                   Add Train
  //                 </button>
  //               </div>
  //             </div>
  //           </div>
  //           <div className='col'></div>
  //         </div>
          

  //     </div>
  // )    
