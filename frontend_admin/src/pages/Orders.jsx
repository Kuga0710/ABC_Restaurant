import React, { useEffect, useState } from 'react';
import './Orders.css';

const Orders = () => {
  const [orders, setOrders] = useState([]);

  useEffect(() => {
    fetch('http://localhost:8080/api/v1/orders')
      .then(response => response.json())
      .then(data => {
        if (data.statusCode === 200) {
          setOrders(data.data);
        }
      })
      .catch(error => console.error('Error fetching orders:', error));
  }, []);

  return (
    <div className="orders-container">
      <h2>All Orders List</h2>
      {orders.length === 0 ? (
        <p>No orders available.</p>
      ) : (
        <table className="orders-table">
          <thead>
            <tr>
              <th className="col-id">Order ID</th>
              <th className="col-username">User Name</th>
              <th className="col-payment">Payment Type</th>
              <th className="col-order">Order Type</th>
              <th className="col-mobile">Mobile Number</th>
              <th className="col-address">Address</th>
              <th className="col-price">Total Price</th>
              <th className="col-menu">Menu Names</th>
              <th className="col-status">Status</th>
            </tr>
          </thead>
          <tbody>
            {orders.map(order => (
              <tr key={order.ordersId}>
                <td className="col-id">{order.ordersId}</td>
                <td className="col-username">{order.userName}</td>
                <td className="col-payment">{order.paymentType}</td>
                <td className="col-order">{order.orderType}</td>
                <td className="col-mobile">{order.mobileNumber || 'N/A'}</td>
                <td className="col-address">{order.address || 'N/A'}</td>
                <td className="col-price">Rs.{order.totalPrice.toFixed(2)}</td>
                <td className="col-menu">{order.menuNames}</td>
                <td className="col-status">{order.status}</td>
              </tr>
            ))}
          </tbody>
        </table>
      )}
      <button className="csv-button">Generate CSV Report</button>
    </div>
  );
};

export default Orders;
