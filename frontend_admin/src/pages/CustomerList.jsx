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
                const response = await axios.get('http://localhost:8080/api/v1/user/users?role=customer');
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

       // Function to handle CSV download
       const downloadCsv = async () => {
        try {
            const response = await axios.get('http://localhost:8080/api/v1/user/users/csv?role=customer', {
                responseType: 'blob', // Important for file downloads
            });
            const url = window.URL.createObjectURL(new Blob([response.data]));
            const link = document.createElement('a');
            link.href = url;
            link.setAttribute('download', 'customer_report.csv'); // Filename for the downloaded file
            document.body.appendChild(link);
            link.click();
            link.remove();
        } catch (error) {
            console.error('Error downloading the CSV file:', error);
        }
    };
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
            <button className="csv-button" onClick={downloadCsv}>Generate CSV Report</button>

        </div>
    );
};

export default CustomerList; // Ensure this line is present
