import React, { useEffect, useState } from 'react';
import './Gallery.css';
import axios from 'axios';
import { Card, Button } from 'react-bootstrap';

function Gallery() {
    const [galleries, setGalleries] = useState([]);
    const [error, setError] = useState(null);

    const fetchGalleries = () => {
        axios.get('http://localhost:8080/api/v1/gallery')
            .then(response => {
                setGalleries(response.data.data);
            })
            .catch(error => {
                console.error('There was an error fetching the gallery data!', error);
                setError(error);
            });
    };

    useEffect(() => {
        fetchGalleries();
    }, []);

    const handleEdit = (gallery) => {
        // Implement the edit functionality
        console.log("Edit gallery:", gallery);
    };

    return (
        <div className='gallery-page'>
            <header className='mt-5'>
                <div className='container h-100 d-flex align-items-center justify-content-center'>
                    <h1 className='text-light'>Gallery List Images</h1>
                </div>
            </header>

            <div className='container mt-5'>
                {error && (
                    <div className="alert alert-danger text-center">
                        {error.message}
                    </div>
                )}

                <div className='row'>
                    {galleries.map((gallery, index) => (
                        <div key={index} className='col-lg-4 mb-5'>
                            <Card className='gallery-item'>
                                <div className='d-flex flex-column'>
                                    <img 
                                        src={`data:image/jpeg;base64,${gallery.image}`} 
                                        className='img-fluid gallery-image' 
                                        alt={gallery.type} 
                                    />
                                    <Card.Body className='d-flex flex-column justify-content-between'>
                                        <div>
                                            <Card.Title className='text-center fs-3'>
                                                {gallery.type}
                                            </Card.Title>
                                        </div>
                                        <div className="d-flex justify-content-center">
                                            <Button 
                                                variant='warning' 
                                                className='ms-2'
                                                onClick={() => handleEdit(gallery)}
                                            >
                                                Edit
                                            </Button>
                                        </div>
                                    </Card.Body>
                                </div>
                            </Card>
                        </div>
                    ))}
                </div>
            </div>
        </div>
    );
}

export default Gallery;
