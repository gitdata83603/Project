import { useState,useEffect } from 'react'
import { Link, useNavigate,useLocation } from 'react-router-dom'
import { toast } from 'react-toastify'
import Navbar from '../components/navbar'
import {Searchtrainbynumber} from '../services/Train'
import {getTrainDetails} from '../services/Train'
import {Savetraveldate} from '../services/Train'
import {Savetrainseats} from '../services/Train'

function Updatetrainseatscapacity()
{

    const [train, setTrain] = useState('')
    const[traindates,setTraindate]=useState([])
    const [number, setNumber] = useState('');//
    const [name, setName] = useState('');//
    const [totalSeats, setTotalSeats] = useState('');//
    const [totalEconomyClassSeats, setTotalEconomyClassSeats] = useState(0);//
    const [totalFirstClassSeats, setTotalFirstClassSeats] = useState(0);//
    const [update,setUpdate]=useState(0)

    //const [traincopy, setTrainCopy] = useState({ numbercopy: '', namecopy:'',totalSeatscopy:'',totalEconomyClassSeatscopy:'',totalFirstClassSeatscopy:''});

    const [formData, setFormData] = useState({
      numbercopy: '',
      namecopy: '',
      totalSeatscopy: '',
      totalEconomyClassSeatscopy: 0,
      totalFirstClassSeatscopy: 0
  });

  const handleInputChange = (event) => {
    const { name, value } = event.target;
    setFormData({
        ...formData,
        [name]: value
    });

 };


    const navigate=useNavigate()
    const location = useLocation()
    
    const loadtrainDetails = async (id) => {
        const result = await getTrainDetails(id)
        setTrain(result)
        setTraindate(result.dates)
        //setNumber(result.number)
        formData.numbercopy=result.number
        formData.namecopy=result.name
        formData.totalSeatscopy=result.totalseats
      }
    
      useEffect(() => {
        loadtrainDetails(location.state.id) 
        setUpdate(0)
      }, [update])
    /*
        {
        "number": "string",
        "name": "string",
        "totalseats": 0,
        "totalEconomyClassSeats": 0,
        "totalFirstClassSeats": 0
        }
    */
  

   const onSave = async () => {
    if (formData.numbercopy.length == 0) {
      toast.error('please enter train number')
    } else if (formData.namecopy.length == 0) {
      toast.error('please enter train name')
    } 
    else 
    {
      
      const result = await Savetrainseats(formData.numbercopy, formData.namecopy,formData.totalSeatscopy,formData.totalEconomyClassSeats,formData.totalFirstClassSeats)
      toast.warning(result.message)
      setUpdate(1)
    }  
  }

    return(
        <div>
               <Navbar/>
            <h2>Update Train seats</h2>
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
            
            {/* <input type="text" name="numbercopy" value={formData.numbercopy} onChange={handleChange}/>

            <input
                    type="text"
                    name="firstName"
                    value={formData.firstName}
                    onChange={handleChange}
                /> */}
    {/* const [formData, setFormData] = useState({
      numbercopy: train.number,
      namecopy: '',
      totalSeatscopy: '',
      totalEconomyClassSeatscopy: '',
      totalFirstClassSeatscopy: ''
  }); */}

{}
<div className="container">
      <div className="row">
        <div className="col-md-6">
          <div className="form-group">
            <label htmlFor="number">Train Number</label>
            <input
              type="text"
              className="form-control"
              id="number"
              name="numbercopy"
              placeholder="Enter Train number"
              value={formData.numbercopy}
              onChange={handleInputChange}
              required
            />
          </div>
        </div>
        <div className="col-md-6">
          <div className="form-group">
            <label htmlFor="name">Train Name</label>
            <input
              type="text"
              className="form-control"
              id="name"
              name="namecopy"
              placeholder="Enter Train name"
              value={formData.namecopy}
              onChange={handleInputChange}
              required
            />
          </div>
        </div>
      </div>
      <div className="row">
        <div className="col-md-4">
          <div className="form-group">
            <label htmlFor="totalseats">Total Seats</label>
            <input
              type="number"
              className="form-control"
              id="totalseats"
              name="totalSeatscopy"
              placeholder="Enter total seats"
              value={formData.totalSeatscopy}
              onChange={handleInputChange}
              required
            />
          </div>
        </div>
        <div className="col-md-4">
          <div className="form-group">
            <label htmlFor="totalEconomyClassSeats">Total Economy Class Seats</label>
            <input
              type="number"
              className="form-control"
              id="totalEconomyClassSeats"
              name="totalEconomyClassSeatscopy"
              placeholder="Enter total economy class seats"
              value={formData.totalEconomyClassSeatscopy}
              onChange={handleInputChange}
              required
            />
          </div>
        </div>
        <div className="col-md-4">
          <div className="form-group">
            <label htmlFor="totalFirstClassSeats">Total First Class Seats</label>
            <input
              type="number"
              className="form-control"
              id="totalFirstClassSeats"
              name="totalFirstClassSeatscopy"
              placeholder="Enter total first class seats"
              value={formData.totalFirstClassSeatscopy}
              onChange={handleInputChange}
              required
            />
          </div>
        </div>
        <div className='col-auto mb-3'>
              <button onClick={onSave} className='btn btn-warning mt-2'>
                  Update Train
                </button>
         </div>

      </div>
    </div>     

        </div>
    )
  
}
export default Updatetrainseatscapacity
