import Usernavbar from "../components/Usernavbar"
import { useState,useEffect } from 'react'
import { Link, useNavigate } from 'react-router-dom'
import { toast } from 'react-toastify'
import {SearchTrainForUser} from '../services/Train'

function Searchdestination() {
    

    const [source,setSource]=useState('')
    const [destination,setDestination]=useState('')
    const [date,setDate]=useState('')
    const [trains,setTrains]=useState([])
    const [first,setFirst]=useState(0)
    const [economy,setEconomy]=useState(0)
    const navigate=useNavigate()

    const onSave = async () => {
        if (source.length == 0) {
          toast.error('please enter Source Station')
        } else if (destination.length == 0) {
          toast.error('please enter Destinations Station')
        } 
        else 
        {
      
          const result = await SearchTrainForUser(source,destination,date)
          setTrains(result)
          // sessionStorage['E']=economy
          // sessionStorage['F']=first
        //console.log(result)
         navigate('/usersearchtrainlist',{state:{id:result}})
          /*
            [
            {
                "id": 5,
                "tavelDateId": 20,
                "destinationRouteid": 18,
                "number": "MH1020",
                "name": "palus",
                "date": "2024-08-16",
                "sourcearrival": "10:00:00",
                "destination": "SATARA",
                "destinatiionarrival": "14:00:00",
                "totalseats": 100,
                "dist": 100,
                "fare": null,
                "source": "PALUS",
                "sourceRouteid": 17
            }
            ]
          */

          //http://host:8080/train/{source}/{destination}/{traveldate}



        }
      }

    return(
        <div>
           <Usernavbar/>
           <h2 className='page-header' style={{marginTop:'20px'}}>Search Train</h2>
           <div className='row' style={{marginTop:'30px'}}>
          <div className='col'></div>
          <div className='col'>
            <div className='form'>
              <div className='mb-3'>
                {/* <label htmlFor=''></label> */}
                <input
                  onChange={(e) => {setSource(e.target.value)}}
                  type='text'
                  className='form-control'
                  placeholder='Source'
                />
              </div>

              <div className='mb-3'>
                {/* <label htmlFor=''></label> */}
                <input
                  onChange={(e) => {setDestination(e.target.value)}}
                  type='text'
                  className='form-control'
                  placeholder='destination'

                />
              </div>
             
         

              <div className='mb-3'>
                {/* <label htmlFor=''></label> */}
                <input
                  onChange={(e) => {setDate(e.target.value)}}
                  type='date'
                  className='form-control'
                  placeholder='traveldate'
                />
              </div>
            
              <div className='mb-3'>
                <button onClick={onSave} className='btn btn-success mt-2'>
                  Search Train
                </button>
              </div>
            </div>
          </div>
          <div className='col'></div>
        </div>
        </div>
    )
}

export default Searchdestination

{/* <div className='row'>
<div className='col'></div>
<div className='col'>
<div className='form'>
 <div className='mb-3'> */}
   {/* <label htmlFor=''></label> */}
   {/* <input
     onChange={(e) => {setEconomy(e.target.value)}}
     type='number'
     className='form-control'
     placeholder='economy seats'
   />
 </div> */}

 {/* <div className='mb-3'>
   {/* <label htmlFor=''></label> */}
   {/* <input
     onChange={(e) => {setFirst(e.target.value)}}
     type='number'
     className='form-control'
     placeholder='first class seats'  */}

{/* //    />
//  </div>
//  </div>
// </div> */}
{/* // <div className='col'></div>
// </div> */}