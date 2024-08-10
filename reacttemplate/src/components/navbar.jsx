import { Link } from 'react-router-dom'

function Navbar() {
  return (
    <nav
      className='navbar navbar-expand-lg bg-body-tertiary bg-dark'
      data-bs-theme='dark'
    >
      <div className='container-fluid'>
        <a className='navbar-brand' href='#'>
          Railway Reservation
        </a>

        <div className='collapse navbar-collapse' id='navbarSupportedContent'>
          <ul className='navbar-nav me-auto mb-2 mb-lg-0'>
            <li className='nav-item'>
              <Link
                to='/adminhome'
                className='nav-link'
                aria-current='page'
                href='#'
              >
                Home
              </Link>
            </li>
            <li className='nav-item'>
              <Link
                to='/addtrain'
                className='nav-link'
                aria-current='page'
                href='#'
              >
                AddTrain
              </Link>
            </li>
            
            
            <li className='nav-item'>
              <Link
                to='/addstation'
                className='nav-link'
                aria-current='page'
                href='#'
              >
                AddStation
              </Link>
            </li>
            
            <li className='nav-item'>
              <Link
                to='/addroute'
                className='nav-link'
                aria-current='page'
                href='#'
              >
                AddRoute
              </Link>
            </li>

            <li className='nav-item'>
              <Link
                to='/viewstations'
                className='nav-link'
                aria-current='page'
                href='#'
              >
                ViewStation
              </Link>
            </li>


            <li className='nav-item'>
              <Link
                to='/trainlist'
                className='nav-link'
                aria-current='page'
                href='#'
              >
                ViewAllTrains
              </Link>
            </li>
            <li className='nav-item'>
              <Link
                to='/searchtrain'
                className='nav-link'
                aria-current='page'
                href='#'
              >
                SearchTrain
              </Link>
            </li>

                   
            <li className='nav-item'>
              <Link
                to='/updatetrain'
                className='nav-link'
                aria-current='page'
                href='#'
              >
                UpdateTrain
              </Link>
            </li>
            
            <li className='nav-item'>
              <Link
                to='/bookings'
                className='nav-link'
                aria-current='page'
                href='#'
              >
                Bookings
              </Link>
            </li>

            {/* <li className='nav-item'>
              <Link
                to='/users'
                className='nav-link'
                aria-current='page'
                href='#'
              >
                Users
              </Link>
            </li>
            <li className='nav-item'>
              <Link
                to='/bookings'
                className='nav-link'
                aria-current='page'
                href='#'
              >
                Bookings
              </Link>
            </li> */}
            <li className='nav-item'>
              <Link
                to='/login'
                className='nav-link'
                aria-current='page'
                href='#'
              >
                Logout
              </Link>
            </li>
          </ul>
        </div>
      </div>
    </nav>
  )
}

export default Navbar