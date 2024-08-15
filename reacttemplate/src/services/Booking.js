import axios from 'axios';

export async function loadtraindatebookings(traveldateid) {
    //const body = {code, name }
    try {
      const response = await axios.get(`http://localhost:8080/booking/${traveldateid}`)//('http://localhost:8080/station/get', body)
      return response.data
    } catch (ex) {
      console.log(`exception: `, ex)
    }

    

}
/*


{
  "trainId": 0,
  "userId": 0,
  "trainScheduleId": 0,
  "destinationRouteid": 0,
  "totalFirstSeats": 0,
  "totalEconomySeats": 0,
  "passangerInfo": "string",
  "sourceRouteid": 0
}*/

export async function SaveBooking(trainId, userId,trainScheduleId,sourceRouteid,destinationRouteid,totalFirstSeats,totalEconomySeats,passangerInfo ) {
  const body = {trainId, userId,trainScheduleId,sourceRouteid,destinationRouteid,totalFirstSeats,totalEconomySeats,passangerInfo  }
  try {
    const response = await axios.post('http://localhost:8080/booking/add',body)//('http://localhost:8080/station/get', body)
    return response.data
  } catch (ex) {
    console.log(`exception: `, ex)
  }

  

}

export async function loaduserbookings(userId) {
  //const body = {code, name }
  try {
    const response = await axios.get(`http://localhost:8080/booking/user/${userId}`)//('http://localhost:8080/station/get', body)
    return response.data
  } catch (ex) {
    console.log(`exception: `, ex)
  }

  

}


export async function CancelBooking(booking_id, reasonForCancellation){
  const body = {booking_id, reasonForCancellation}
  try {
    const response = await axios.post('http://localhost:8080/booking/cancel',body)//('http://localhost:8080/station/get', body)
    return response.data
  } catch (ex) {
    console.log(`exception: `, ex)
  }

  

}





