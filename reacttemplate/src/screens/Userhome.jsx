function Userhome() 
{
  return(
      <div>
          <h1>Welcome CUSTOMER</h1>

    {/* <div className="container mt-4">
      <h2>Add Train</h2>
      <div className='row'>
        <div className='col'></div>
        <div className='col-8'>
          <div className='card'>
            <div className='card-body'>
              <form>   
                <div className='mb-3'>
                  <label htmlFor='startStation' className='form-label'>Start Station</label>
                  <select
                    id='startStation'
                    className='form-select'
                    value={startStationId}
                    onChange={handleStartStationChange}
                  >
                    <option value="">Select a station</option>
                    {stations.map((station) => (
                      <option key={station.id} value={station.id}>{station.name}</option>
                    ))}
                  </select>
                </div>
             
                <div className='row'>
                  <div className='col'>
                    <div className='mb-3'>
                      <label htmlFor='arrivalTime' className='form-label'>Arrival Time</label>
                      <input
                        id='arrivalTime'
                        type='time'
                        className='form-control'
                        value={arrival}
                        onChange={(e) => setArrival(e.target.value)}
                      />
                    </div>
                  </div>
                  <div className='col'>
                    <div className='mb-3'>
                      <label htmlFor='departureTime' className='form-label'>Departure Time</label>
                      <input
                        id='departureTime'
                        type='time'
                        className='form-control'
                        value={departure}
                        onChange={(e) => setDeparture(e.target.value)}
                      />
                    </div>
                  </div>
                </div>        
                <div className='mb-3'>
                  <label htmlFor='totalDistance' className='form-label'>Total Distance</label>
                  <input
                    id='totalDistance'
                    type='number'
                    className='form-control'
                    value={distance}
                    onChange={(e) => setDistance(e.target.value)}
                  />
                </div>
                <div className='text-center'>
                  <button type='button' onClick={onSave} className='btn btn-success'>
                    Add Route
                  </button>
                </div>
              </form>
            </div>
          </div>
        </div>
        <div className='col'></div>
      </div>
    </div> */}


{/* <div className='row'>
  <div className='col'></div>
  <div className='col'>
    <div className='form'> 
          <div className='row'>
                  <div className='col'>
                    <div className='mb-3'>
                      <button onClick={onSave} className='btn btn-success mt-2'>
                        Add Station
                      </button>
                    </div>
                    </div>
                  <div className='col'>
                    <div className='mb-3'>
                      <button onClick={onSave} className='btn btn-success mt-2'>
                        Add Station
                      </button>
                    </div>
                  </div>
            </div> 
    </div>
  </div>
  <div className='col'></div>
</div>
</div> */}












      </div>
  )    
}
export default Userhome