import { useState } from "react"
import { useLocation } from "react-router-dom"

function Register()
{
    const [id,setid]=useState(0)
    const location=useLocation()



   console.log(location.state.id);
    return(
        <div>
         <h1>Register</h1>
         {location.state.id && (
         <p>{location.state.id}</p>
         ) 
        }
         </div>
    )
}

export default Register