import './App.css';
import { Link, Routes, Route } from 'react-router-dom';
import Home from './pages/Home';
import Menu from './pages/Menu';
import ReviewList from './pages/ReviewList';
import QueryList from './pages/QueryList';
import AllReservations from './pages/AllReservations';
import Orders from './pages/Orders';


function App() {
  return (
    <div className="admin-container">
      <nav className="sidebar">
        <div className="sidebar-header">
          <h2>ABC Restaurant</h2>
        </div>
        <ul className="nav-list">
          <li><Link to="/" className="nav-link">Home</Link></li>
          <li><Link to="/menu" className="nav-link">Menu</Link></li>
          <li><Link to="/orders" className="nav-link">Orders</Link></li>
          <li><Link to="/review-list" className="nav-link">Review List</Link></li>
          <li><Link to="/query-list" className="nav-link">Query List</Link></li>
          <li><Link to="/all-reservations" className="nav-link">All Reservations</Link></li>

        </ul>
      </nav>
      <main className="content">
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/menu" element={<Menu />} />
          <Route path="/review-list" element={<ReviewList />} />
          <Route path="/query-list" element={<QueryList />} />
          <Route path="/all-reservations" element={<AllReservations />} />
          <Route path="/orders" element={<Orders />} />
          {/* Add other routes here */}
        </Routes>
      </main>
    </div>
  );
}

export default App;
