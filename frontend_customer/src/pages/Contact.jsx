import React, { useState } from 'react';
import './Contact.css';
import { Form } from 'react-bootstrap';
import axios from 'axios';

function Contact() {
    const [formData, setFormData] = useState({
        name: '',
        email: '',
        subject: '',
        message: ''
    });

    const [responseMessage, setResponseMessage] = useState(null);
    const [validationErrors, setValidationErrors] = useState({});

    const handleChange = (e) => {
        setFormData({
            ...formData,
            [e.target.id]: e.target.value
        });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        try {
            const response = await axios.post('http://localhost:8080/api/v1/query', formData);
            if (response.status === 200) {
                setResponseMessage('Your message has been sent successfully!');
                setValidationErrors({});
                
            }
        } catch (error) {
            if (error.response && error.response.status === 400) {
                const errors = error.response.data.data; // Assuming validation errors are sent in this format
                setValidationErrors(errors); // Set validation errors to display in the form
                setResponseMessage('Failed to send your message due to validation errors.');
            } else {
                setResponseMessage('Failed to send your message. Please try again.');
            }
        }
    };

    return (
        <div className='contact-page'>
            <header className='mt-4'>
                <div className='container h-100 d-flex align-items-center justify-content-center'>
                    <h3 className='section-title'>- Contact For Any Query -</h3>
                </div>
            </header>
            <div className='container my-4'>
                <div className='row'>
                    <div className='col-md-5'>
                        <div className='wow fadeInUp'>
                            <Form onSubmit={handleSubmit}>
                                <Form.Group className='mb-2'>
                                    <Form.Label htmlFor='name'>Your Name</Form.Label>
                                    <Form.Control
                                        type='text'
                                        id='name'
                                        placeholder='Your Name'
                                        value={formData.name}
                                        onChange={handleChange}
                                        isInvalid={!!validationErrors.name}
                                    />
                                    <Form.Control.Feedback type='invalid'>
                                        {validationErrors.name}
                                    </Form.Control.Feedback>
                                </Form.Group>
                                <Form.Group className='mb-2'>
                                    <Form.Label htmlFor='email'>Your Email</Form.Label>
                                    <Form.Control
                                        type='email'
                                        id='email'
                                        placeholder='Your Email'
                                        value={formData.email}
                                        onChange={handleChange}
                                        isInvalid={!!validationErrors.email}
                                    />
                                    <Form.Control.Feedback type='invalid'>
                                        {validationErrors.email}
                                    </Form.Control.Feedback>
                                </Form.Group>
                                <Form.Group className='mb-2'>
                                    <Form.Label htmlFor='subject'>Subject</Form.Label>
                                    <Form.Control
                                        type='text'
                                        id='subject'
                                        placeholder='Subject'
                                        value={formData.subject}
                                        onChange={handleChange}
                                        isInvalid={!!validationErrors.subject}
                                    />
                                    <Form.Control.Feedback type='invalid'>
                                        {validationErrors.subject}
                                    </Form.Control.Feedback>
                                </Form.Group>
                                <Form.Group className='mb-3'>
                                    <Form.Label htmlFor='message'>Message</Form.Label>
                                    <Form.Control
                                        as='textarea'
                                        id='message'
                                        placeholder='Leave a message here'
                                        style={{ height: '100px' }}
                                        value={formData.message}
                                        onChange={handleChange}
                                        isInvalid={!!validationErrors.message}
                                    />
                                    <Form.Control.Feedback type='invalid'>
                                        {validationErrors.message}
                                    </Form.Control.Feedback>
                                </Form.Group>
                                <button type='submit' className='btn btn-primary w-100 py-3'>Send Message</button>
                            </Form>
                            {responseMessage && (
                                <div className={`alert ${validationErrors ? 'alert-danger' : 'alert-success'} mt-3`}>
                                    {responseMessage}
                                </div>
                            )}
                        </div>
                    </div>
                    <div className='col-md-7'>
                        <iframe
                            className='position-relative rounded w-100 h-100'
                            src="https://www.google.com/maps/embed?pb=!1m14!1m8!1m3!1d15725.493749684632!2d80.2337189!3d9.8189691!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x3aff03f990744377%3A0xa8f94f14b61e297a!2sABC%20Restaurant!5e0!3m2!1sen!2slk!4v1724300439502!5m2!1sen!2slk"
                            frameBorder='0'
                            style={{ minHeight: '350px', border: '0' }}
                            allowFullScreen=''
                            aria-hidden='false'
                            tabIndex='0'
                        ></iframe>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default Contact;
