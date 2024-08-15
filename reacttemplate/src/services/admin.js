import axios from 'axios';


export async function login(email, pwd) {
    const body = { email, pwd }
    try {
      const response = await axios.post('http://localhost:8080/users/signin', body)
      return response.data
    } catch (ex) {
      console.log(`exception: `, ex)
    }

    return null

}

export async function registeruser(firstName, lastName,email,password,address) {
  const body = { firstName,  lastName,email,password,address }
  try {
    const response = await axios.post('http://localhost:8080/users/register', body)
    return response.data
  } catch (ex) {
    console.log(`exception: `, ex)
  }

  return null

}
