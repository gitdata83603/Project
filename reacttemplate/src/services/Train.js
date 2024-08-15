import axios from 'axios';

export async function Savetrain(number, name,startStationId,endStationId,date,arrival,departure,totalSeats,totalEconomyClassSeats,totalFirstClassSeats
,bookedSeats,totalEconomyClassBookedSeats,totalFirstClassBookedSeats,distance,endDate,destArrival) {
    
    console.log(startStationId )
    console.log(endStationId )
    const body = {number, name,startstationid:startStationId,endstationid:endStationId,date,arrival,departure,totalseats:totalSeats,totalEconomyClassSeats,totalFirstClassSeats
        ,bookedseats:bookedSeats,totalEconomyClassBookedSeats,totalFirstClassBookedSeats,distance,enddate:endDate,destarrival:destArrival }
    try {
      const response = await axios.post('http://localhost:8080/train/add', body)
      return response.data
    } catch (ex) {
      console.log(`exception: `, ex)
    }

    
/*
{
  "number": "string",
  "name": "string",
  "startstationid": 0,
  "endstationid": 0,
  "date": "2024-08-03",
  "arrival": "string",
  "departure": "string",
  "totalseats": 0,
  "totalEconomyClassSeats": 0,
  "totalFirstClassSeats": 0,
  "bookedseats": 0,
  "totalEconomyClassBookedSeats": 0,
  "totalFirstClassBookedSeats": 0,
  "distance": 0,
  "enddate": "2024-08-03",
  "destarrival": "string"
}
*/
}



export async function Searchtrainbynumber(number) {
  //const body = {code, name }
  try {
    const response = await axios.get(`http://localhost:8080/train/onenumber/abc/${number}`)//('http://localhost:8080/station/get', body)
    return response.data
  } catch (ex) {
    console.log(`exception: `, ex)
  }

  

}


export async function getTrainDetails(number) {
  //const body = {code, name }
  try {
    const response = await axios.get(`http://localhost:8080/train/number/${number}`)//('http://localhost:8080/station/get', body)
    return response.data
  } catch (ex) {
    console.log(`exception: `, ex)
  }

  

}


export async function loadalleverytrains() {
  //const body = {code, name }
  try {
    const response = await axios.get('http://localhost:8080/train/traininfo')//('http://localhost:8080/station/get', body)
    return response.data
  } catch (ex) {
    console.log(`exception: `, ex)
  }

  

}


//user-->SearchTrainForUser(/http://host:8080/train/{source}/{destination}/{traveldate})

export async function SearchTrainForUser(source,destination,date) {
  //const body = {code, name }
  try {
    const response = await axios.get(`http://localhost:8080/train/search/dest/${source}/${destination}/${date}`)//('http://localhost:8080/station/get', body)
    return response.data
  } catch (ex) {
    console.log(`exception: `, ex)
  }

  

}


//loadtraintraveldates(id)

export async function loadtraintraveldates(number) {
  //const body = {code, name }
  try {
    const response = await axios.get(`http://localhost:8080/train/traveldate/${number}`)//('http://localhost:8080/station/get', body)
    return response.data
  } catch (ex) {
    console.log(`exception: `, ex)
  }

  

}



export async function loadpriceperkm() {
  //const body = {code, name }
  try {
    const response = await axios.get('http://localhost:8080/price/perkm')//('http://localhost:8080/station/get', body)
    return response.data
  } catch (ex) {
    console.log(`exception: `, ex)
  }

  

}






export async function Savetraveldate(startDate, endDate,trainid) {
      
  // {
  //   "startDate": "string",
  //   "endDate": "string",
  //   "trainid": 0
  // }
      const body = {startDate, endDate,trainid }
      try {
        const response = await axios.post('http://localhost:8080/set/train/date/add', body)
        return response.data
      } catch (ex) {
        console.log(`exception: `, ex)
      }
  
    
  }



  export async function   Updateprice(firstClassPrice, economyClassPrice) {
 
        const body = {firstClassPrice, economyClassPrice}
        try {
          const response = await axios.put('http://localhost:8080/price/update', body)
          return response.data
        } catch (ex) {
          console.log(`exception: `, ex)
        }
    
      
    }




 

  export async function  Savetrainseats(number,name,totalseats,totalEconomyClassSeats,totalFirstClassSeats) {
    // {
    //   "number": "string",
    //   "name": "string",
    //   "totalseats": 0,
    //   "totalEconomyClassSeats": 0,
    //   "totalFirstClassSeats": 0
    //   }

        const body = {number,name,totalseats,totalEconomyClassSeats,totalFirstClassSeats }
        try {
          const response = await axios.put(`http://localhost:8080/train/updatetrain/set/${number}`, body)
          return response.data
        } catch (ex) {
          console.log(`exception: `, ex)
        }
    
      
    }

    
    
  export async function  UpdateArrivalTime(arrival,number) {
    

        const body = {arrival,number}
        try {
          const response = await axios.put(`http://localhost:8080/train/updatetime/${number}`, body)
          return response.data
        } catch (ex) {
          console.log(`exception: `, ex)
        }
        
      
    }