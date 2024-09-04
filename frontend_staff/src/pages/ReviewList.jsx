import React, { useEffect, useState } from 'react';
import axios from 'axios';
import './ReviewList.css';

const ReviewList = () => {
    const [reviews, setReviews] = useState([]);

    useEffect(() => {
        axios.get('http://localhost:8080/api/v1/review')
            .then(response => setReviews(response.data.data))
            .catch(error => console.error('Error fetching reviews:', error));
    }, []);

    return (
        <div className="review-list-container">
            <h2>ALL Review List</h2>
            <table className="review-table">
                <thead>
                    <tr>
                        <th>ReviewId</th>
                        <th>Name</th>
                        <th>Review</th>
                        <th>Rating</th>
                    </tr>
                </thead>
                <tbody>
                    {reviews.map(review => (
                        <tr key={review.reviewId}>
                            <td>{review.reviewId}</td>
                            <td>{review.name}</td>
                            <td>{review.review}</td>
                            <td>{review.rating}</td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
};

export default ReviewList;
