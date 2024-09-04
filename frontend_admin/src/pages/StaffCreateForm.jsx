import React, { useState } from 'react';
import './StaffCreateForm.css';

const StaffCreateForm = () => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [email, setEmail] = useState('');
  const [message, setMessage] = useState('');

  const handleSubmit = (e) => {
    e.preventDefault();

    const staffData = {
      username: username,
      password: password,
      email: email,
      role: 'staff'
    };

    fetch('https://meet.google.com/kmh-vduw-gvi', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(staffData)
    })
      .then(response => response.json())
      .then(data => {
        if (data.success) {
          setMessage('Staff created successfully');
          setUsername('');
          setPassword('');
          setEmail('');
        } else {
          setMessage('Failed to create staff');
        }
      })
      .catch(error => {
        console.error('Error:', error);
        setMessage('An error occurred');
      });
  };

  return (
    <div className="staff-create-form-container">
      <h2>Create Staff</h2>
      <form onSubmit={handleSubmit} className="staff-create-form">
        <div className="form-group">
          <label htmlFor="username">Username:</label>
          <input
            type="text"
            id="username"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
            required
          />
        </div>
        <div className="form-group">
          <label htmlFor="email">Email:</label>
          <input
            type="email"
            id="email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            required
          />
        </div>
        <div className="form-group">
          <label htmlFor="password">Password:</label>
          <input
            type="password"
            id="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            required
          />
        </div>
        <button type="submit">Create</button>
      </form>
      {message && <p className="message">{message}</p>}
    </div>
  );
};

export default StaffCreateForm;
