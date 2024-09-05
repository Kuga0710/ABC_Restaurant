import React, { useEffect, useState } from 'react';
import './Offers.css';
import axios from 'axios';
import { Card, Button } from 'react-bootstrap';
import offersImage from '../utils/img/offers.jpg';


function Offers() {
    const [offers, setOffers] = useState([]);
    const [error, setError] = useState(null);

    const fetchOffers = () => {
        axios.get('http://localhost:8080/api/v1/offers')
            .then(response => {
                setOffers(response.data.data);
            })
            .catch(error => {
                console.error('There was an error fetching the offer data!', error);
                setError(error);
            });
    };

    useEffect(() => {
        fetchOffers();
    }, []);

    return (
        <div className='offers-page'>
            <header className='offers-page-header'>
                <img src={offersImage} alt="offers Header" className='img-fluid offers-header-image'/>
            </header>
        <div className='container mt-5'>
               
                <div className='row'>
                    {offers.map((offer, index) => (
                        <div key={index} className='col-lg-4 mb-5'>
                            <Card className='menu-item'>
                                <img
                                    src={`data:image/jpeg;base64,${offer.image}`}
                                    className='img-fluid menu-image'
                                    alt={offer.name}
                                />
                                <Card.Body className='d-flex flex-column justify-content-between'>
                                    <div>
                                        <Card.Title className='text-center fs-3'>
                                            {offer.name}
                                        </Card.Title>
                                        <Card.Text className='text-center fs-5 description'>
                                            {offer.description}
                                        </Card.Text>
                                    </div>
                                    <div>
                                        <Card.Text className='text-center fs-3 fw-bold text-success'>
                                            Rs {offer.price}
                                        </Card.Text>
                                        <Card.Text className='text-center fs-5'>
                                            {offer.availabilityStatus}
                                        </Card.Text>
                                    </div>
                                    <Button
                                        variant='success'
                                        className='mt-3'
                                    >
                                        Add To Cart
                                    </Button>
                                </Card.Body>
                            </Card>
                        </div>
                    ))}
                </div>
            </div>
        </div>
    );
}

export default Offers;
