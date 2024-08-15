
import './App.css';
import Login from './screens/login';
import Register from './screens/Register';
import { Route, Routes } from 'react-router-dom'
import { ToastContainer } from 'react-toastify'
import 'react-toastify/dist/ReactToastify.css'
import Home from './screens/Home';
import Userhome from './screens/Userhome'
import Addtrain from './screens/Addtrain'
import Addstation from './screens/Addstation'
import Viewstation from './screens/Viewstation'
import AddRoute from './screens/AddRoute'
import Routes1 from './screens/Routes1'
import Update from './screens/Update'
import Updatetrainhome from './screens/Updatetrainhome'
import Updatetraintraveldate from './screens/Updatetraintraveldate'
import Updatetrainseatscapacity from './screens/Updatetrainseatscapacity'
import Updatetrainarrivaltime from './screens/Updatetrainarrivaltime'
import Trainlist from './screens/Trainlist'
import Onetraindetails from './screens/Onetraindetails'
import Bookingsforadmin from './screens/Bookingsforadmin'
import Bookingtraveldateadmin from './screens/Bookingtraveldateadmin'
import Bookinginfotraveldate from './screens/Bookinginfotraveldate'
import Searchtrainadmin from './screens/Searchtrainadmin'
import Searchdestination from './screens/Searchdestination'
import Usersearchtrainlist from './screens/Usersearchtrainlist'
import Userbooktrain from './screens/Userbooktrain'
import Userbookings from './screens/Userbookings'
import Cancelbooking from './screens/Cancelbooking'
import Priceperkm from './screens/Priceperkm'

function App() {
  return (
    <div className="App">
         <Routes>
        <Route path='' element={<Login />} />
        <Route path='login' element={<Login />} />
        <Route path='register' element={<Register />} />
        <Route path='adminhome' element={<Home />} />
        <Route path='userhome' element={<Userhome />} />

        {/* train */}
        <Route path='addtrain' element={<Addtrain />} />
        <Route path='addstation' element={<Addstation />} />
        <Route path='updatetrain' element={<Update />} />
        <Route path='updatetrainhome' element={<Updatetrainhome />} />
        <Route path='updatetraintraveldate' element={<Updatetraintraveldate />} />
        <Route path='updatetrainseatscapacity' element={<Updatetrainseatscapacity />} />
        <Route path='updatetrainarrivaltime' element={<Updatetrainarrivaltime />} />
        <Route path='trainlist' element={<Trainlist />} />
        <Route path='onetraindetails' element={<Onetraindetails />} />
        <Route path='searchtrain' element={<Searchtrainadmin />} />
        

        {/* bookings */}
        <Route path='bookings' element={<Bookingsforadmin />} />
        <Route path='bookingtraveldate' element={<Bookingtraveldateadmin />} />
        <Route path='bookinginfotraveldate' element={<Bookinginfotraveldate />} />
        <Route path='userbookings' element={<Userbookings />} />
        <Route path='cancelbooking' element={<Cancelbooking />} />
        


  
        {/* <Route path='viewtrains' element={<Viewtrains />} />
        <Route path='searchtrain' element={<Searchtrain />} /> */}





       {/* station */}
        <Route path='viewstations' element={<Viewstation />} />

   {/* routes */}
   <Route path='addroute' element={<AddRoute />} />
   <Route path='routes' element={<Routes1 />} />
  

       {/* user */}
       <Route path='searchdestination' element={<Searchdestination />} />
       <Route path='usersearchtrainlist' element={<Usersearchtrainlist />} />
       <Route path='userbooktrain' element={<Userbooktrain />} />
       

     {/*priceperkm */}
     <Route path='priceperkm' element={<Priceperkm />} />


      </Routes>
      <ToastContainer/>
    </div>
  );
}

export default App;
