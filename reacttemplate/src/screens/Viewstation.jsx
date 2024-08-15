
import { Link, useNavigate } from 'react-router-dom'
import Navbar from '../components/navbar'
import { useEffect, useState } from 'react'
import {Getstation} from '../services/Station'

function Viewstation()
{
    const [stations, setStations] = useState([])
    const navigate=useNavigate();

    const loadStations = async () => {
        const result = await Getstation();
          setStations(result);        
      }
    
      useEffect(() => {loadStations()}, [])
    
      const onDetails = (index) => {
        navigate('/stationdetails',{ state: { id: index } })
      }

  return(
      <div>
          <Navbar />
          <h2 className='page-header'>stations</h2>
        
        {stations.length == 0 && (
        <h3 className='mt-5' style={{ textAlign: 'center' }}>
          There are no Stations at the moment. Please use Add Stations
        </h3>
        )}

      {stations.length > 0 && (
        <table className='table table-striped mt-5'>
          <thead>
            <tr>
              <th>#</th>
              <th>Code</th>
              <th>Name</th>
              <th>Update</th>
            </tr>
          </thead>
          <tbody>
            {stations.map((station, index) => {
              return (
                <tr>
                  <td>{index + 1}</td>
                  <td>{station['code']}</td>
                  <td>{station['name']}</td>
                  <td>  
                    <button
                      onClick={() => {
                        onDetails(station['id'])
                      }}
                      className='btn btn-primary bt-sm'
                    >
                      update
                    </button>
                  </td>
                </tr>
              )
            })}
          </tbody>
        </table>

      )}
    
     {/* {
         <select name="station" id="station">
         {stations.map((station)=>{
            return(
                    <option value={`${station.id}`} >{`${station.name}`}</option>
            ) 
         })}
         </select>
     } */}


      

      </div>
  )    
}
export default Viewstation