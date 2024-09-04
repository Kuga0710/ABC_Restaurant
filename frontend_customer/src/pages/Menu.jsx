import React, { useEffect, useState } from 'react';
import './Menu.css';
import axios from 'axios';
import { Card, Button, Form, InputGroup } from 'react-bootstrap';

function Menu() {
    const [menus, setMenus] = useState([]);
    const [searchTerm, setSearchTerm] = useState("");
    const [error, setError] = useState(null);

    // Fetch the logged-in user's ID from the front-end context or state (mocking it here)
    const getUserId = () => {
        // You should replace this with your actual method of retrieving the user ID
        return 1; // Assuming 1 is the logged-in user's ID
    };

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
console.log(menus)
    useEffect(() => {
        fetchMenus(); 
    }, []);

    const handleSearchSubmit = (event) => {
        event.preventDefault();
        fetchMenus(searchTerm.trim()); 
    };

    const handleAddToCart = (menuId) => {
        const userId = getUserId();
        const cartData = {
            userId: userId,
            menuId: menuId
        };

        axios.post('http://localhost:8080/api/v1/cart', cartData)
            .then(response => {
                if (response.data.statusCode === 201) {
                    alert('Item added to cart successfully');
                } else {
                    alert('Failed to add item to cart');
                }
            })
            .catch(error => {
                console.error('There was an error adding the item to the cart!', error);
                alert('Failed to add item to cart');
            });
    };

    return (
        <div className='menu-page'>
            <header className='mt-5'>
                <div className='container h-100 d-flex align-items-center justify-content-center'>
                    <h1 className='text-light'>Menu</h1>
                </div>
            </header>

            <div className='container mt-5'>
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
                                        <Button 
                                            variant='success' 
                                            className='mt-3' 
                                            onClick={() => handleAddToCart(menuCategory.menuId)}
                                        >
                                            Add To Cart
                                        </Button>
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
