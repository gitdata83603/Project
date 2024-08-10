import axios from 'axios';

export async function SaveRoute(arrival,departure,station_id,distance,train_id) 
{        
        const body = {arrival,departure,station_id,distance,train_id}
        try {
          const response = await axios.post('http://localhost:8080/route/add', body)
          return response.data
        } catch (ex) {
          console.log(`exception: `, ex)
        }
 /*
{
  "distance": 0,
  "arrival": "string",
  "departure": "string",
  "train_id": 0,
  "station_id": 0
}
 */
}