// CustomerList.jsx
import React, { useEffect, useState } from 'react';
import axios from 'axios';
import './CustomerList.css'; // Import the CSS file for styles

const CustomerList = () => {
    const [customers, setCustomers] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        const fetchCustomers = async () => {
            try {
                const response = await axios.get('http://localhost:8080/api/v1/users?role=customer');
                if (response.data.statusCode === 200) {
                    setCustomers(response.data.data);
                } else {
                    setError('Failed to retrieve customers');
                }
            } catch (err) {
                setError('Error fetching customers');
            } finally {
                setLoading(false);
            }
        };

        fetchCustomers();
    }, []);

    return (
        <div className="customer-list-container">
            <h1>All Customer List</h1>
            {loading && <p>Loading...</p>}
            {error && <p>{error}</p>}
            {!loading && !error && (
                <table className="customer-table">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Username</th>
                            <th>Email</th>
                        </tr>
                    </thead>
                    <tbody>
                        {customers.map((customer) => (
                            <tr key={customer.id}>
                                <td>{customer.id}</td>
                                <td>{customer.username}</td>
                                <td>{customer.email}</td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            )}
        </div>
    );
};

export default CustomerList; // Ensure this line is present
