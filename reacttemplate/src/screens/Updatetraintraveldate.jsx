import { useState,useEffect } from 'react'
import { Link, useNavigate,useLocation } from 'react-router-dom'
import { toast } from 'react-toastify'
import Navbar from '../components/navbar'
import {Searchtrainbynumber} from '../services/Train'
import {getTrainDetails} from '../services/Train'
import {Savetraveldate} from '../services/Train'

function Updatetraintraveldate() {
    const [train, setTrain] = useState('')
    const [date, setDate] = useState("");//
    const [endDate, setEndDate] = useState("");// 
    const [traindates,setTraindate]=useState([])
    const [update,setUpdate]=useState(0)

    const location = useLocation() 
    const navigate=useNavigate()


    const loadtrainDetails = async (id) => {
      const result = await getTrainDetails(id)
      setTrain(result)
      setTraindate(result.dates)
    }

    useEffect(() => {
      loadtrainDetails(location.state.id) 
      setUpdate(0)
    }, [update])







    const onSave = async () => {
        if (date.length == 0) {
          toast.error('please enter travel start date')
        } else if (endDate.length == 0) {
          toast.error('please enter travel end date')
        } 
        else 
        {
          
          const result = await Savetraveldate(date, endDate,train.id)
          toast.warning(result.message)
          setUpdate(1)
        //   if(result.message!=null)
        //   {
           
        //   }
         // navigate('/updatetrainhome',{ state: { id: train.number, message:result.message} })
        }  //location.state.message
      }
      
    return(
        <div>
            <Navbar/>
            <h2 className='page-header'>Add New Train Travel Date </h2>
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
            <div className="container mt-4">
                 <div className='row' style={{marginTop:'20px'}}>
                <div className='col'></div>
                <div className='col'>
                    <div className='form'>
                        <div className='row justify-content-center'>
                            <div className='col-auto mb-3'>
                            <label htmlFor='travelStartDate' className='form-label'>Travel Start Date</label>
                            <input
                                id='travelStartDate'
                                type='date'
                                className='form-control'
                                value={date}
                                onChange={(e) => setDate(e.target.value)}
                            />
                            </div>
                            <div className='col-auto mb-3'>
                            <label htmlFor='travelEndDate' className='form-label'>Travel End Date</label>
                            <input
                                id='travelEndDate'
                                type='date'
                                className='form-control'
                                value={endDate}
                                onChange={(e) => setEndDate(e.target.value)}
                            />
                            </div>
                            <div className='col-auto mb-3'>
                                <button onClick={onSave} className='btn btn-warning mt-2'>
                                    Add Travel date
                                </button>
                            </div>
                         
                        </div>
                    </div>
                </div>
                <div className='col'></div>
            </div>
        </div>
       </div> 
    )
    
}
export default Updatetraintraveldate

/*
{
  "startDate": "string",
  "endDate": "string",
  "trainid": 0
}
*/