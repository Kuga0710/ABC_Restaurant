import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import './Booking.css';

function BookingService() {
    const [searchTerm, setSearchTerm] = useState('');

    const handleSearchChange = (event) => {
        setSearchTerm(event.target.value);
    };

    const services = [
        { name: 'Table Reservation', description: 'Book your table in advance for a seamless dining experience.', link: '/reserve-table', icon: 'fa-calendar', buttonText: 'Reserve Now' },
        { name: 'Hall Booking', description: 'Book our hall for your special events and parties.', link: '/book-hall', icon: 'fa-building', buttonText: 'Book Now' },
        { name: 'Gift Cards', description: 'Purchase a gift card for your loved ones.', link: '/purchase-gift-card', icon: 'fa-gift', buttonText: 'Buy Now' },
        { name: 'Make a Query', description: 'Reach out to us with any queries or special requests.', link: '/contact', icon: 'fa-comment', buttonText: 'Contact Us' },
        { name: 'Give a Review', description: 'Share your dining experience with us.', link: '/review-page', icon: 'fa-star', buttonText: 'Review Now' }
    ];

    const filteredServices = services.filter(service =>
        service.name.toLowerCase().includes(searchTerm.toLowerCase())
    );

    return (
        <div className="booking-service-page">
            <header className="booking-service-header">
                <div className="container h-100 d-flex align-items-center justify-content-center">
                    <h3 className="section-title">- Explore Our Services -</h3>
                </div>
                <div className="search-container">
                    <input 
                        type="text" 
                        placeholder="Search for services..." 
                        className="search-bar" 
                        value={searchTerm}
                        onChange={handleSearchChange}
                    />
                </div>
            </header>
            <div className="container my-4">
                <div className="row">
                    {filteredServices.map((service, index) => (
                        <div className="col-md-3" key={index}>
                            <div className="service-card">
                                <div className="service-icon">
                                    <i className={`fa ${service.icon}`}></i>
                                </div>
                                <h4>{service.name}</h4>
                                <p>{service.description}</p>
                                <Link to={service.link} className="btn btn-primary w-100">{service.buttonText}</Link>
                            </div>
                        </div>
                    ))}
                </div>
            </div>
        </div>
    );
}

export default BookingService;
