
import { useState ,useEffect} from 'react'
import { Link, useNavigate } from 'react-router-dom'
import { toast } from 'react-toastify'
import { useLocation } from "react-router-dom"
import {registeruser} from '../services/admin'

function Register()
{
    const [id,setid]=useState(0)
    const [firstName,setFirstName]=useState('')
    const [lastName,setLastName]=useState('')
    const [email,setEmail]=useState('')
    const [password,setPassword]=useState('')
    const [address,setAddress]=useState('')
    const [update,setUpdate]=useState(0)
    const location=useLocation()

/*
{
  "firstName": "rohit",
  "lastName": "sharma",
  "email": "rs.com",
  "password": "rs",
  "address": "mumbai"
}


*/

useEffect(() => {
    setUpdate(1)}, [update])

 
const onSave = async () => {
    if (firstName.length == 0) {
      toast.error('please enter first name')
    } else if (lastName.length == 0) {
      toast.error('please enter last name')
    } 
    else if (email.length == 0) {
        toast.error('please enter email')
      }
      else if (password.length == 0) {
        toast.error('please enter password')
      }

    else 
    {
      //message Savestation
      // console.log(stationcode)
      // console.log(name)
      const result = await registeruser(firstName, lastName,email,password,address)
      
      setUpdate(0)
      toast.warning(result.message)
    }
  }

    return(
        <div>
        <h2 className='page-header'>Register</h2>
            <div className='row'>
            <div className='col'></div>
            <div className='col'>
                <div className='form'>
                <div className='mb-3'>
                    {/* <label htmlFor=''>Station Code</label> */}
                    <input
                    onChange={(e) => setFirstName(e.target.value)}
                    type='text'
                    className='form-control'
                    placeholder='First Name'
                    />
                </div>
                <div className='mb-3'>
                    {/* <label htmlFor=''>Station Code</label> */}
                    <input
                    onChange={(e) => setLastName(e.target.value)}
                    type='text'
                    className='form-control'
                    placeholder='Last Name'
                    />
                </div>
                  
                <div className='mb-3'>
                    {/* <label htmlFor=''>Station Code</label> */}
                    <input
                    onChange={(e) => setAddress(e.target.value)}
                    type='text'
                    className='form-control'
                    placeholder='address'
                    />
                </div>

                <div className='mb-3'>
                    {/* <label htmlFor=''>Station Code</label> */}
                    <input
                    onChange={(e) => setEmail(e.target.value)}
                    type='email'
                    className='form-control'
                    placeholder='Email'
                    />
                </div>

                <div className='mb-3'>
                    {/* <label htmlFor=''>Station Code</label> */}
                    <input
                    onChange={(e) => setPassword(e.target.value)}
                    type='password'
                    className='form-control'
                    placeholder='password'
                    />
                </div>
  
                <div className='mb-3'>
                    <div>
                     Already Have account ? <Link to='/login'>LoginPage</Link>
                    </div>
                    <button onClick={onSave} className='btn btn-success mt-2'>
                      Register
                    </button>
                    
                </div>
                </div>
            </div>
            <div className='col'></div>
            </div>
         </div>
    )
}

export default Register