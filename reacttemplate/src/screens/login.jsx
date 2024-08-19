import { useState } from 'react'
import { Link, useNavigate } from 'react-router-dom'
import { toast } from 'react-toastify'
import { login } from '../services/admin'
import Headerfooter from '../components/Headerfooter'
import Footer from '../components/Footer'

function Login() {
  const [email, setEmail] = useState('')
  const [password, setPassword] = useState('')
  const [isEmailEmpty, setEmailEmpty] = useState(false)
  const [isPasswordEmpty, setPasswordEmpty] = useState(false)

  // get navigation hook
  const navigate = useNavigate()

  const onLogin = async () => {
    if (email.length == 0) 
    {
      toast.error('Please enter email')
    }
     else if (password.length == 0) {
      toast.error('Please enter password')
    }
     else {
      // call login API and check its success
      const result = await login(email, password)
      if (result['status'] == 'success' && result.role=='ADMIN') {
        //const data = result['data']
        // persist the token and user name in session storage
        sessionStorage['name'] = result.firstName//data['name']
        sessionStorage['id'] = result.id
        // sessionStorage['token'] = data['token']

        // go to home screen
        navigate('/adminhome')
      } 
      else if(result['status'] == 'success' && result.role=='CUSTOMER')
      {
        sessionStorage['name'] = result.firstName//data['name']
        sessionStorage['id'] = result.id
        navigate('/userhome')
      }

      else {
        toast.error('invalid credentials')
      }
    }
  }

  return (
    <div>
      <Headerfooter/>
      <br/>
      <br/>
      <div className="container mt-4">
      <h2 className='page-header'>Login</h2>
      <div className='row' style={{marginTop:'50px'}}>
        <div className='col'></div>
        <div className='col'>
          <div className='form'>
            <div className='mb-4'>
              {/* <label htmlFor=''>Email</label> */}
              <input
                onChange={(e) => {
                  if (e.target.value.length == 0) {
                    setEmailEmpty(true)
                  } else {
                    setEmailEmpty(false)
                  }
                  setEmail(e.target.value)
                }}
                type='email'
                className='form-control'
                placeholder='email'
              />
              {isEmailEmpty && (
                <p style={{ color: 'red' }}>Email is mandatory</p>
              )}
            </div>
            <div className='mb-4'>
              {/* <label htmlFor=''>Password</label> */}
              <input
                onChange={(e) => {
                  if (e.target.value.length == 0) {
                    setPasswordEmpty(true)
                  } else {
                    setPasswordEmpty(false)
                  }
                  setPassword(e.target.value)
                }}
                type='password'
                className='form-control'
                placeholder='password'
              />
              {isPasswordEmpty && (
                <p style={{ color: 'red' }}>Password is mandatory</p>
              )}
            </div>
            <div className='mb-3'>
              <div>
                Don't have account ? <Link to='/register'>Register here</Link>
              </div>
              <button onClick={onLogin} className='btn btn-success mt-2'>
                Login
              </button>
            </div>
          </div>
        </div>
        <div className='col'></div>
      </div>
      </div>
    </div>
  )
}

export default Login