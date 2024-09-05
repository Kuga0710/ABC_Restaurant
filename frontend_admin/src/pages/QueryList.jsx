import React, { useEffect, useState } from 'react';
import axios from 'axios';
import './QueryList.css';

const QueryList = () => {
    const [queries, setQueries] = useState([]);

    useEffect(() => {
        axios.get('http://localhost:8080/api/v1/query')
            .then(response => setQueries(response.data.data))
            .catch(error => console.error('Error fetching queries:', error));
    }, []);

    // Function to handle CSV download
    const downloadCsv = async () => {
        try {
            const response = await axios.get('http://localhost:8080/api/v1/query/csv', {
                responseType: 'blob', // Important for file downloads
            });
            const url = window.URL.createObjectURL(new Blob([response.data]));
            const link = document.createElement('a');
            link.href = url;
            link.setAttribute('download', 'query_report.csv'); // Filename for the downloaded file
            document.body.appendChild(link);
            link.click();
            link.remove();
        } catch (error) {
            console.error('Error downloading the CSV file:', error);
        }
    };
    return (
        <div className="query-list-container">
            <h2>ALL Query List</h2>
            <table className="query-table">
                <thead>
                    <tr>
                        <th>QueryId</th>
                        <th>CreatedAt</th>
                        <th>Email</th>
                        <th>Name</th>
                        <th>Subject</th>
                        <th>Message</th>
                    </tr>
                </thead>
                <tbody>
                    {queries.map(query => (
                        <tr key={query.queryId}>
                            <td>{query.queryId}</td>
                            <td>{query.createdAt ? new Date(query.createdAt).toLocaleString() : 'N/A'}</td>
                            <td>{query.email}</td>
                            <td>{query.name}</td>
                            <td>{query.subject}</td>
                            <td>{query.message}</td>
                        </tr>
                    ))}
                </tbody>
            </table>
            <button className="csv-button" onClick={downloadCsv}>Generate CSV Report</button>
        </div>
    );
};

export default QueryList;
