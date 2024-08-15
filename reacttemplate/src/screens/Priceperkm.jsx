import { useState,useEffect } from 'react'
import { Link, useNavigate } from 'react-router-dom'
import { toast } from 'react-toastify'
import Navbar from "../components/navbar"
import {loadpriceperkm,Updateprice} from '../services/Train'

function Priceperkm() {
    
    const [firstclassprice, setFirstclassprice] = useState(0)
    const [economyclassprice,setEconomyclassprice]=useState(0)
    const [price,setPrice]=useState(0)
    const [update,setUpdate]=useState(0)

    const navigate=useNavigate()

    const loadprice=async()=>{
       const result=await loadpriceperkm()
       setPrice(result)
    }

    useEffect(() => {
        loadprice()
        setUpdate(1)
      }, [update])


      const onSave = async () => {
        if (firstclassprice.length == 0) {
          toast.error('please enter firstclassprice')
        } else if (economyclassprice.length == 0) {
          toast.error('please enter economyclassprice')
        } 
        else 
        {
         
          const result = await Updateprice(firstclassprice, economyclassprice)
          toast.warning(result.message)
          setUpdate(3)
        }
      }

    return(
        <div>
            <Navbar/>
            <h2 className='page-header'>Price Details</h2>
           <div className="container mt-3">
                <div className="table-responsive">
                    <table className="table table-striped table-hover">
                        <thead className="table-dark">
                            <tr>
                                <th>Economy class Price</th>
                                <th>First class Price</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>{price.economyClassPrice}(RS.)</td>
                                <td>{price.firstClassPrice}(RS.)</td>
                            </tr>
                        </tbody>
                    </table>
                </div>

           </div>

           






           <h4>Update Price</h4>
           <div className='row'>
            <div className='col'></div>
            <div className='col'>
                <div className='form'>
                <div className='mb-3'>
                 
                <input
                      id='travelStartDate'
                       type='number'
                        className='form-control'
                       onChange={(e) => setFirstclassprice(e.target.value)}
                       placeholder='firstclassprice'
                            />
                </div>
                <div className='mb-3'>
                <input
                                id='travelEndDate'
                                type='number'
                                className='form-control'
                                onChange={(e) => setEconomyclassprice(e.target.value)}
                                placeholder='economyclassprice'
                            />
                </div>  
                <div className='mb-3'>
                    <button onClick={onSave} className='btn btn-success mt-2'>
                      Update Price
                    </button>
                </div>
                </div>
            </div>
                <div className='col'></div>
            </div>

           
        




        </div>
    )
}
/*
{
  "firstClassPrice": 0,
  "economyClassPrice": 0
}
*/

export default Priceperkm