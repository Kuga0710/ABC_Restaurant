import './App.css';
import { Link, Routes, Route } from 'react-router-dom';
import Home from './pages/Home';
import Menu from './pages/Menu';
import ReviewList from './pages/ReviewList';
import QueryList from './pages/QueryList';
import CustomerList from './pages/CustomerList';
import AllReservations from './pages/AllReservations';
import Gallery from './pages/Gallery';
import Orders from './pages/Orders';
import StaffCreateForm from './pages/StaffCreateForm';
import StaffList from './pages/StaffList';

function App() {
  return (
    <div className="admin-container">
      <nav className="sidebar">
        <div className="sidebar-header">
          <h2>ABC Restaurant</h2>
        </div>
        <ul className="nav-list">
          <li><Link to="/" className="nav-link">Home</Link></li>
          <li><Link to="/menu" className="nav-link">Customize Menu</Link></li>
          <li><Link to="/orders" className="nav-link">Orders</Link></li>
          <li><Link to="/review-list" className="nav-link">Review List</Link></li>
          <li><Link to="/query-list" className="nav-link">Query List</Link></li>
          <li><Link to="/customer-list" className="nav-link">Customer List</Link></li>
          <li><Link to="/all-reservations" className="nav-link">All Reservations</Link></li>
          <li><Link to="/gallery" className="nav-link">All Gallery</Link></li>
          <li><Link to="/staff-list" className="nav-link">Staff List</Link></li>
          <li><Link to="/staff-create-form" className="nav-link">StaffCreateForm</Link></li>
          <li><Link to="/create-offer" className="nav-link">CreateOffer</Link></li>
        </ul>
      </nav>
      <main className="content">
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/menu" element={<Menu />} />
          <Route path="/review-list" element={<ReviewList />} />
          <Route path="/query-list" element={<QueryList />} />
          <Route path="/customer-list" element={<CustomerList />} />
          <Route path="/all-reservations" element={<AllReservations />} />
          <Route path="/gallery" element={<Gallery />} />
          <Route path="/orders" element={<Orders />} />
          <Route path="/staff-create-form" element={<StaffCreateForm />} />
          <Route path="/staff-list" element={<StaffList />} />



          {/* Add other routes here */}
        </Routes>
      </main>
    </div>
  );
}

export default App;
