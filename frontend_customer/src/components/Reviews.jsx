import React, { useEffect, useState } from "react";
import { Card, CardBody, CardText, CardFooter, CardTitle } from 'react-bootstrap';
import axios from 'axios';
import './Reviews.css';
import Person1 from '../utils/img/person1.jpg';
import Person2 from '../utils/img/person2.jpg';
import Person3 from '../utils/img/person3.jpg';
import Person4 from '../utils/img/person4.jpg';

export function Reviews() {
    const [reviews, setReviews] = useState([]);
    const [error, setError] = useState("");
    const [loading, setLoading] = useState(true);

    const fetchReviews = async () => {
        try {
            const response = await axios.get("http://localhost:8080/api/v1/review");
            console.log("API Response:", response.data); // Log the response data
            
            const reviewsData = response.data.data; // Get the 'data' array
            
            if (Array.isArray(reviewsData)) {
                const lastFourReviews = reviewsData.slice(-4); // Get the last four reviews
                setReviews(lastFourReviews); // Set the reviews state
            } else {
                setError("Unexpected data format");
            }
        } catch (error) {
            console.error("Error fetching reviews:", error.response ? error.response.data : error.message);
            setError("Error fetching reviews");
        } finally {
            setLoading(false);
        }
    };

    useEffect(() => {
        fetchReviews();
    }, []);

    // Function to render the rating as stars
    const renderStars = (rating) => {
        return (
            <div>
                {[...Array(5)].map((_, index) => (
                    <span key={index} className={index < rating ? "text-warning" : "text-secondary"}>
                        ★
                    </span>
                ))}
            </div>
        );
    };

    return (
        <div className="reviews-section container">
            <h2 className="text-center mb-5 text-uppercase fw-bold fs-1">Reviews</h2>
            {loading ? (
                <p>Loading reviews...</p>
            ) : error ? (
                <p>{error}</p>
            ) : (
                <div className="row g-4">
                    {reviews.map((review, index) => (
                        <div className="col-lg-6" key={index}>
                            <Card className="h-100 shadow">
                                <CardBody>
                                    <div className="p-4">
                                        <CardText>{review.review}</CardText>
                                        {renderStars(review.rating)} {/* Display the rating */}
                                    </div>
                                </CardBody>
                                <CardFooter className="d-flex align-items-center">
                                    <img src={index === 0 ? Person1 : index === 1 ? Person2 : index === 2 ? Person3 : Person4} className="img-fluid rounded-circle mx-3 shadow" alt="" />
                                    <CardTitle className="text-success">{review.name}</CardTitle>
                                </CardFooter>
                            </Card>
                        </div>
                    ))}
                </div>
            )}
        </div>
    );
}
