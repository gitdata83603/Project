import { useState,useEffect } from 'react'
import { Link, useNavigate } from 'react-router-dom'
import { toast } from 'react-toastify'
import Navbar from '../components/navbar'
import {loadalleverytrains} from '../services/Train'
import Trainroutes from '../components/Trainroutes'

function Trainlist()
{
    const [trains, setTrains] = useState([])
    const [update,setUpdate]=useState(0)
    const navigate=useNavigate()

    const loadalltrains=async()=>{
       const result=await loadalleverytrains()
        setTrains(result)
    }

    useEffect(() => {
        loadalltrains()
      }, [update])

   function loadonetrain(trainnumber) {
       navigate('/onetraindetails',{state:{id:trainnumber}})
   }


    return(
        <div>
           <Navbar/>
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
                    <th>Seats</th>
                    <th>Distance</th>
                    <th></th>
                    </tr>
                </thead>
                <tbody>
                    
                     {
                       
                      trains.map((train,index)=>{
                            return(
                                <tr>
                                   <td>{index+1}</td>
                                   <td>{train.number}</td>
                                   <td>{train.name}</td>
                                   <td>{train.source}({train.sourcearrival})</td>
                                   <td>{train.destination}({train.destinatiionarrival})</td>
                                   <td>{train.totalseats}</td>
                                   <td>{train.dist}</td>
                                   <td>
                                   <button onClick={()=>{loadonetrain(train.number)}} class="btn btn-dark">Details</button>
                                   
                                  
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

export default Trainlist