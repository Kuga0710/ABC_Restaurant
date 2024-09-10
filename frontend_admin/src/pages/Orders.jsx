import React, { useEffect, useState } from 'react';
import './Orders.css';
import axios from 'axios';

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
  // Function to handle CSV download
  const downloadCsv = async () => {
    try {
        const response = await axios.get('http://localhost:8080/api/v1/orders/csv', {
            responseType: 'blob', // Important for file downloads
        });
        const url = window.URL.createObjectURL(new Blob([response.data]));
        const link = document.createElement('a');
        link.href = url;
        link.setAttribute('download', 'orders_report.csv'); // Filename for the downloaded file
        document.body.appendChild(link);
        link.click();
        link.remove();
    } catch (error) {
        console.error('Error downloading the CSV file:', error);
    }
};
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
      <button className="csv-button" onClick={downloadCsv}>Generate CSV Report</button>
      </div>
  );
};

export default Orders;
