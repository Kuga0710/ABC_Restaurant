import React, { useEffect, useState } from 'react';
import axios from 'axios';
import './AllReservations.css';

const AllReservations = () => {
    const [reservations, setReservations] = useState([]);
    const [error, setError] = useState('');

    useEffect(() => {
        axios.get('http://localhost:8080/api/v1/reservation')
            .then(response => {
                if (response.data.statusCode === 200) {
                    setReservations(response.data.data);
                } else {
                    setError('Error retrieving reservations');
                }
            })
            .catch(() => setError('Error retrieving reservations'));
    }, []);

    return (
        <div className="reservation-container">
            <h1>All Reservation List</h1>
            {error && <p className="error">{error}</p>}
            <table className="reservation-table">
                <thead>
                    <tr>
                        <th>Select</th>
                        <th>Reservation ID</th>
                        <th>Name</th>
                        <th>Email</th>
                        <th>Date</th>
                        <th>Number of People</th>
                        <th>Special Requests</th>
                    </tr>
                </thead>
                <tbody>
                    {reservations.map((reservation) => (
                        <tr key={reservation.reservationId}>
                            <td><input type="checkbox" /></td>
                            <td>{reservation.reservationId}</td>
                            <td>{reservation.name}</td>
                            <td>{reservation.email}</td>
                            <td>{reservation.date}</td>
                            <td>{reservation.numberOfPeople}</td>
                            <td>{reservation.specialRequests}</td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
};

export default AllReservations;
