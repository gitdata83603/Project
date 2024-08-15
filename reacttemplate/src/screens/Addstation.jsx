
import { useState } from 'react'
import { Link, useNavigate } from 'react-router-dom'
import { toast } from 'react-toastify'
import { Savestation } from '../services/Station'
import Navbar from '../components/navbar'
function Addstation()
{


    const [stationcode,setStationcode] = useState('')
    const [name,setName] = useState('')

  
    // get the navigation hook
   // const navigate = useNavigate()
  
    const onSave = async () => {
      if (stationcode.length == 0) {
        toast.error('please enter Station code name')
      } else if (name.length == 0) {
        toast.error('please enter station name')
      } 
      else 
      {
        //message Savestation
        // console.log(stationcode)
        // console.log(name)
        const result = await Savestation(stationcode, name)
        toast.warning(result.message)
      }
    }

  return(
<div>
<Navbar/>

<h2 className='page-header'>Add Station</h2>
<div className='row'>
  <div className='col'></div>
  <div className='col'>
    <div className='form'>
      <div className='mb-3'>
        <label htmlFor=''>Station Code</label>
        <input
          onChange={(e) => setStationcode(e.target.value)}
          type='text'
          className='form-control'
        />
      </div>
      <div className='mb-3'>
        <label htmlFor=''>Station Name</label>
        <input
          onChange={(e) => setName(e.target.value)}
          type='text'
          className='form-control'
        />
      </div>  
      <div className='mb-3'>
        <button onClick={onSave} className='btn btn-success mt-2'>
          Add Station
        </button>
      </div>
    </div>
  </div>
  <div className='col'></div>
</div>
</div>


  )    
}

export default Addstation

// {
//     "code": "string",
//     "name": "string"
//   }