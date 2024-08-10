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