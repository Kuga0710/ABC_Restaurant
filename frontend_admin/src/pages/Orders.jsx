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
      <h2>All Orders list</h2>
      {orders.length === 0 ? (
        <p>No orders available.</p>
      ) : (
        orders.map(order => (
          <div key={order.ordersId} className="order-card">
            <h3>Order ID: {order.ordersId}</h3>
            <p><strong>User Name:</strong> {order.userName}</p>
            <p><strong>Payment Type:</strong> {order.paymentType}</p>
            <p><strong>Order Type:</strong> {order.orderType}</p>
            <p><strong>Mobile Number:</strong> {order.mobileNumber || 'N/A'}</p>
            <p><strong>Address:</strong> {order.address || 'N/A'}</p>
            <p><strong>Total Price:</strong> ${order.totalPrice.toFixed(2)}</p>
            <p><strong>Menu Names:</strong> {order.menuNames}</p>
            <p><strong>Status:</strong> {order.status}</p>
          </div>
        ))
      )}
    </div>
  );
};

export default Orders;
