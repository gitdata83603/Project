import { useState } from 'react'
import { Link, useNavigate } from 'react-router-dom'
import { toast } from 'react-toastify'
import Navbar from '../components/navbar'
import {Searchtrainbynumber} from '../services/Train'
function AddRoute()
{

    const [number, setNumber] = useState('')
    const navigate = useNavigate()
    
    const onSave = async () => {
        if (number.length == 0) {
          toast.error('please enter train number')
        } 
        else 
        {
          const result = await Searchtrainbynumber(number)
         // toast.warning(result.message)
         console.log(result.number)
         if(result.message=='success')
         {
           navigate('/routes',{ state: { id: result.number } })
         }
         else
         {
             toast.error('Train Not Found')
         }
        }
      }

  return(
    <div>
    <Navbar/>
    <h2 className='page-header'>Add Route</h2>
    <div className='row'>
      <div className='col'></div>
      <div className='col'>
        <div className='form'>
          <div className='mb-3'>
            <label htmlFor=''>Train Number</label>
            <input
              onChange={(e) => {setNumber(e.target.value)}}
              type='text'
              className='form-control'
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

export default AddRoute