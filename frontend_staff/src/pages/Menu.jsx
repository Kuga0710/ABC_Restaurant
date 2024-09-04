import React, { useEffect, useState } from 'react';
import './Menu.css';
import axios from 'axios';
import { Card, Button, Form, InputGroup } from 'react-bootstrap';
import MenuForm from '../components/MenuForm'; // Correct path


function Menu() {
    const [menus, setMenus] = useState([]);
    const [searchTerm, setSearchTerm] = useState("");
    const [error, setError] = useState(null);
    const [deleteMessage, setDeleteMessage] = useState("");
    const [showForm, setShowForm] = useState(false);
    const [editMenuData, setEditMenuData] = useState(null);

    const fetchMenus = (name = "") => {
        const url = name ? `http://localhost:8080/api/v1/menu?name=${name}` : 'http://localhost:8080/api/v1/menu';

        axios.get(url)
            .then(response => {
                setMenus(response.data.data);
            })
            .catch(error => {
                console.error('There was an error fetching the menu data!', error);
                setError(error);
            });
    };

    useEffect(() => {
        fetchMenus();
    }, []);

    const handleSearchSubmit = (event) => {
        event.preventDefault();
        fetchMenus(searchTerm.trim());
    };

    const handleDelete = (menuId) => {
        axios.delete(`http://localhost:8080/api/v1/menu/${menuId}`)
            .then(response => {
                console.log('Delete response:', response); // Debug log
                if (response.data.statusCode === 200) {
                    setDeleteMessage('Menu item deleted successfully');
                    setTimeout(() => {
                        setDeleteMessage('');
                        fetchMenus(); // Refresh the menu list after displaying the message
                    }, 2000);
                } else {
                    alert('Failed to delete item');
                }
            })
            .catch(error => {
                console.error('There was an error deleting the item!', error);
                alert('Failed to delete item');
            });
    };

    const handleEdit = (menu) => {
        setEditMenuData(menu);
        setShowForm(true);
    };

    return (
        <div className='menu-page'>
            <header className='mt-5'>
                <div className='container h-100 d-flex align-items-center justify-content-center'>
                    <h1 className='text-light'>All Menu List</h1>
                </div>
            </header>

            <div className='container mt-5'>
                {deleteMessage && (
                    <div className="alert alert-success text-center">
                        {deleteMessage}
                    </div>
                )}
                <div className="search-bar-container">
                    <Form onSubmit={handleSearchSubmit} className="mb-5">
                        <InputGroup>
                            <Form.Control
                                type="text"
                                placeholder="Search for a menu..."
                                value={searchTerm}
                                onChange={(e) => setSearchTerm(e.target.value)}
                            />
                            <Button type="submit" variant="primary">Search</Button>
                        </InputGroup>
                    </Form>
                </div>

                <div className="text-center mb-3">
                    <Button variant="success" onClick={() => { setShowForm(true); setEditMenuData(null); }}>
                        Add Menu
                    </Button>
                </div>

                {showForm && (
                    <MenuForm 
                        menuData={editMenuData} 
                        onClose={() => setShowForm(false)} 
                        onSuccess={fetchMenus} 
                    />
                )}

                <div className='row'>
                    {menus.map((menuCategory, index) => (
                        <div key={index} className='col-lg-4 mb-5'>
                            <Card className='menu-item'>
                                <div className='d-flex flex-column'>
                                    <img 
                                        src={`data:image/jpeg;base64,${menuCategory.image}`} 
                                        className='img-fluid menu-image' 
                                        alt={menuCategory.name} 
                                    />
                                    <Card.Body className='d-flex flex-column justify-content-between'>
                                        <div>
                                            <Card.Title className='text-center fs-3'>
                                                {menuCategory.name}
                                            </Card.Title>
                                            <Card.Text className='text-center fs-5 description'>
                                                {menuCategory.description}
                                            </Card.Text>
                                        </div>
                                        <div>
                                            <Card.Text className='text-center fs-3 fw-bold text-success'>
                                                Rs{menuCategory.price}
                                            </Card.Text>
                                            <Card.Text className='text-center fs-5'>
                                                {menuCategory.availabilityStatus}
                                            </Card.Text>
                                        </div>
                                        <div className="d-flex justify-content-center">
                                            <Button 
                                                variant='danger' 
                                                className='me-2' 
                                                onClick={() => handleDelete(menuCategory.menuId)}
                                            >
                                                Delete
                                            </Button>
                                            <Button 
                                                variant='warning' 
                                                className='ms-2'
                                                onClick={() => handleEdit(menuCategory)}
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

export default Menu;
