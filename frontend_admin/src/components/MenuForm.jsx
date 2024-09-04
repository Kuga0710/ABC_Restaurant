import React, { useState } from 'react';
import './MenuForm.css';
import axios from 'axios';
import { Form, Button } from 'react-bootstrap';

const MenuForm = ({ menuData, onClose, onSuccess }) => {
    const [menuId, setMenuId] = useState(menuData ? menuData.menuId : '');
    const [name, setName] = useState(menuData ? menuData.name : '');
    const [description, setDescription] = useState(menuData ? menuData.description : '');
    const [price, setPrice] = useState(menuData ? menuData.price : '');
    const [availabilityStatus, setAvailabilityStatus] = useState(menuData ? menuData.availabilityStatus : '');
    const [image, setImage] = useState(null);
    const [successMessage, setSuccessMessage] = useState('');

    const handleSubmit = (event) => {
        event.preventDefault();
        const formData = new FormData();
        formData.append('name', name);
        formData.append('description', description);
        formData.append('price', price);
        formData.append('availabilityStatus', availabilityStatus);
        if (image) {
            formData.append('image', image);
        }

        const apiUrl = menuId ? `http://localhost:8080/api/v1/menu/${menuId}` : 'http://localhost:8080/api/v1/menu';
        const method = menuId ? 'put' : 'post';

        axios({
            method: method,
            url: apiUrl,
            data: formData,
            headers: {
                'Content-Type': 'multipart/form-data',
            },
        })
        .then(response => {
            setSuccessMessage('Menu item saved successfully');
            setTimeout(() => {
                setSuccessMessage('');
                onSuccess(); // Call the success handler to refresh the menu
                onClose();   // Close the form
            }, 2000);
        })
        .catch(error => {
            console.error('There was an error saving the menu item!', error);
        });
    };

    return (
        <div className="menu-form-container">
            <h2>{menuId ? 'Edit Menu' : 'Add Menu'}</h2>
            {successMessage && <div className="alert alert-success">{successMessage}</div>}
            <Form onSubmit={handleSubmit}>
                <Form.Group controlId="formMenuName">
                    <Form.Label>Name</Form.Label>
                    <Form.Control 
                        type="text" 
                        value={name} 
                        onChange={(e) => setName(e.target.value)} 
                        required 
                    />
                </Form.Group>

                <Form.Group controlId="formMenuDescription">
                    <Form.Label>Description</Form.Label>
                    <Form.Control 
                        as="textarea" 
                        rows={3} 
                        value={description} 
                        onChange={(e) => setDescription(e.target.value)} 
                        required 
                    />
                </Form.Group>

                <Form.Group controlId="formMenuPrice">
                    <Form.Label>Price</Form.Label>
                    <Form.Control 
                        type="number" 
                        value={price} 
                        onChange={(e) => setPrice(e.target.value)} 
                        required 
                    />
                </Form.Group>

                <Form.Group controlId="formMenuAvailability">
                    <Form.Label>Availability Status</Form.Label>
                    <Form.Control 
                        type="text" 
                        value={availabilityStatus} 
                        onChange={(e) => setAvailabilityStatus(e.target.value)} 
                        required 
                    />
                </Form.Group>

                <Form.Group controlId="formMenuImage">
                    <Form.Label>Image</Form.Label>
                    <Form.Control 
                        type="file" 
                        onChange={(e) => setImage(e.target.files[0])} 
                        required 
                    />
                </Form.Group>

                <Button variant="primary" type="submit">
                    Submit
                </Button>
                <Button variant="secondary" onClick={onClose} style={{ marginLeft: '10px' }}>
                    Cancel
                </Button>
            </Form>
        </div>
    );
};

export default MenuForm;
