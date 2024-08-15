
import { useState,useEffect } from 'react'
import { Link, useNavigate ,useLocation} from 'react-router-dom'
import { toast } from 'react-toastify'
import Navbar from '../components/navbar'
import {loadalleverytrains} from '../services/Train'
import Trainroutes from '../components/Trainroutes'
import {Searchtrainbynumber} from '../services/Train'
import {getTrainDetails} from '../services/Train'
import {Savetraveldate} from '../services/Train'

function Trainsimple({trainnumber}) {

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
                loadtrainDetails(trainnumber) 
                setUpdate(0)
                }, [update])
               
                return(
                <div>
                {/* <h2 className='page-header'>Add New Train Travel Date </h2> */}
                <div className="container mt-4">
                    <div className="table-responsive">
                        <table className="table table-striped table-hover">
                            <thead style={{color:'royalblue'}}>
                                <tr>
                                <th>NUMBER</th>
                                <th>NAME</th>
                                <th>FROM</th>
                                <th>TO</th>
                                <th>DISTANCE</th>
                                <th>Seats</th>
                                {/* <th>Travel Dates</th> */}
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
                                {/* <td>{
                                      traindates.map((date)=>{
                                          return(
                                          <div>{date.startDate}</div>
                                          )
                                      })
                                    }</td> */}
                                </tr>
                            </tbody>
                        </table>
                    </div>
            </div>
            </div>
            )


}
export default Trainsimple