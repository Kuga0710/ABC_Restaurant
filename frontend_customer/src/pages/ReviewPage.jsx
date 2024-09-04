import React, { useState } from 'react';
import './ReviewPage.css';
import axios from 'axios';
import restaurantReviewImage from '../utils/img/restaurantreview.jpg';
import reviewImage from '../utils/img/Review1.jpg';

function ReviewPage() {
    const [formData, setFormData] = useState({
        name: '',
        review: '',
        rating: 0,
    });

    const [responseMessage, setResponseMessage] = useState(null);
    const [validationErrors, setValidationErrors] = useState({});
    const [hoverRating, setHoverRating] = useState(0);

    const handleChange = (e) => {
        setFormData({
            ...formData,
            [e.target.id]: e.target.value,
        });
    };

    const handleRatingChange = (ratingValue) => {
        setFormData({
            ...formData,
            rating: ratingValue,
        });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        try {
            const response = await axios.post('http://localhost:8080/api/v1/review', formData);
            if (response.status === 200) {
                setResponseMessage('Thank you for your review!');
                setFormData({
                    name: '',
                    review: '',
                    rating: 0,
                });
                setValidationErrors({});
            }
        } catch (error) {
            if (error.response && error.response.status === 400) {
                const errors = error.response.data.data;
                setValidationErrors(errors);
                setResponseMessage('Failed to submit the review due to validation errors.');
            } else {
                setResponseMessage('Failed to submit the review. Please try again.');
            }
        }
    };

    return (
        <div className='review-page'>
            <header className='review-page-header'>
                <img src={reviewImage} alt="Review Header" className='img-fluid review-header-image'/>
            </header>
            <div className='container my-4'>
                <div className='row'>
                    <div className='col-md-5'>
                        <div className='wow fadeInUp'>
                            <form onSubmit={handleSubmit}>
                                <div className='form-group mb-2'>
                                    <label htmlFor='name'>Your Name</label>
                                    <input
                                        type='text'
                                        id='name'
                                        className='form-control'
                                        placeholder='Your Name'
                                        value={formData.name}
                                        onChange={handleChange}
                                        isInvalid={!!validationErrors.name}
                                    />
                                    <div className='invalid-feedback'>
                                        {validationErrors.name}
                                    </div>
                                </div>
                                <div className='form-group mb-2'>
                                    <label htmlFor='review'>Your Review</label>
                                    <textarea
                                        id='review'
                                        className='form-control'
                                        placeholder='Your Review'
                                        value={formData.review}
                                        onChange={handleChange}
                                        isInvalid={!!validationErrors.review}
                                    />
                                    <div className='invalid-feedback'>
                                        {validationErrors.review}
                                    </div>
                                </div>
                                <div className='form-group mb-2'>
                                    <label>Rating</label>
                                    <div className='star-rating'>
                                        {[...Array(5)].map((star, index) => {
                                            const ratingValue = index + 1;
                                            return (
                                                <span
                                                    key={ratingValue}
                                                    className='star'
                                                    onClick={() => handleRatingChange(ratingValue)}
                                                    onMouseEnter={() => setHoverRating(ratingValue)}
                                                    onMouseLeave={() => setHoverRating(0)}
                                                    style={{ color: ratingValue <= (hoverRating || formData.rating) ? '#ffc107' : '#e4e5e9' }}
                                                >
                                                    ★
                                                </span>
                                            );
                                        })}
                                    </div>
                                    {validationErrors.rating && (
                                        <div className='invalid-feedback d-block'>
                                            {validationErrors.rating}
                                        </div>
                                    )}
                                </div>
                                <button type='submit' className='btn btn-primary w-100'>Submit Review</button>
                            </form>
                            {responseMessage && (
                                <div className={`alert ${validationErrors ? 'alert-danger' : 'alert-success'} mt-3`}>
                                    {responseMessage}
                                </div>
                            )}
                        </div>
                    </div>
                    <div className='col-md-7'>
                        <img src={restaurantReviewImage} alt='Review' className='img-fluid review-background-image' />
                    </div>
                </div>
            </div>
        </div>
    );
}

export default ReviewPage;
