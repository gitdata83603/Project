import axios from 'axios';

//message Savestation
export async function Savestation(code, name) {
    const body = {code, name }
    try {
      const response = await axios.post('http://localhost:8080/station/add', body)
      return response.data
    } catch (ex) {
      console.log(`exception: `, ex)
    }

    

}


export async function Getstation() {
    //const body = {code, name }
    try {
      const response = await axios.get('http://localhost:8080/station')//('http://localhost:8080/station/get', body)
      return response.data
    } catch (ex) {
      console.log(`exception: `, ex)
    }

    

}