import { Link } from 'react-router-dom'
function Usernavbar()
{
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
                    to='/userhome'
                    className='nav-link'
                    aria-current='page'
                    href='#'
                  >
                    Home
                  </Link>
                </li>

                <li className='nav-item'>
                  <Link
                    to='/searchdestination'
                    className='nav-link'
                    aria-current='page'
                    href='#'
                  >
                    SearchTrains
                  </Link>
                </li>

                <li className='nav-item'>
                  <Link
                    to='/userbookings'
                    className='nav-link'
                    aria-current='page'
                    href='#'
                  >
                    Mybookings
                  </Link>
                </li>

                <li className='nav-item'>
                  <Link
                    to='/userporfile'
                    className='nav-link'
                    aria-current='page'
                    href='#'
                  >
                    Profile
                  </Link>
                </li>


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

export default Usernavbar