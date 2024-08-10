import { useState,useEffect } from 'react'
import { Link, useNavigate,useLocation } from 'react-router-dom'
import { toast } from 'react-toastify'
import Navbar from '../components/navbar'
import {Searchtrainbynumber} from '../services/Train'
import {getTrainDetails} from '../services/Train'
function Updatetrainhome() {

    const [train, setTrain] = useState('')
    const[traindates,setTraindate]=useState([])
    const navigate=useNavigate()
    const location = useLocation()
    const [number,setNumber]=useState()
    
   function onUpdateTravelDate()
   {
    navigate('/updatetraintraveldate',{ state: { id: train.number } })
   }

   function onUpdateSeats()
   {
    navigate('/updatetrainseatscapacity',{ state: { id: train.number } })
    /*
        {
        "arrival": "string",
        "destarrival": "string"
        }   
    */

   }
   function UpdateArrivalTime()
   {
    navigate('/updatetrainarrivaltime',{ state: { id: train.number } })
   }
   

   const loadtrainDetails = async (id) => {
    const result = await getTrainDetails(id)
    setTrain(result)
    setTraindate(result.dates)
  }

  useEffect(() => {
    loadtrainDetails(location.state.id) 
    if(location.state.message!=null)
    {
        toast.success(location.state.message)
    }
  }, [])

    return(

        <div>
            <Navbar/>
            <h2>Update Train</h2>
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
            <div className='row'>
                <div className='col'></div>
                <div className='col'>
                    <div className='form'>
                        <div className='row justify-content-center'>
                            <div className='col-auto mb-3'>
                                <button onClick={UpdateArrivalTime} className='btn btn-info mt-2'>
                                    Update Arrival Time
                                </button>
                            </div>
                            <div className='col-auto mb-3'>
                                <button onClick={onUpdateSeats} className='btn btn-dark mt-2'>
                                    Update Seats
                                </button>
                            </div>
                            
                            <div className='col-auto mb-3'>
                                <button onClick={onUpdateTravelDate} className='btn btn-warning mt-2'>
                                    Add Travel date
                                </button>
                            </div>

                            {/* <textarea name="" id="" cols="30" rows="1" onChange={(e)=>setNumber(e.target.value)}>{train.number}</textarea> */}
                        </div>
                    </div>
                </div>
                <div className='col'></div>
            </div>
        </div>
    )
}
export default Updatetrainhome