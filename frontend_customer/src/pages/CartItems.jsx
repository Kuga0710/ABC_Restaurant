import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import './CartItems.css';
import axios from 'axios';

function CartItems() {
  const [cartItems, setCartItems] = useState([]);
  const [subtotal, setSubtotal] = useState(0);
  const [paymentType, setPaymentType] = useState('Cash'); // Default value
  const [orderType, setOrderType] = useState('Delivery'); // Default value
  const [mobileNumber, setMobileNumber] = useState('');
  const [address, setAddress] = useState('');
  const userId = 1; // Replace with actual userId
  const navigate = useNavigate();

  useEffect(() => {
    fetchCartItems();
  }, []);

  const fetchCartItems = async () => {
    try {
      const response = await axios.get(`http://localhost:8080/api/v1/cart/${userId}`);
      const items = response.data.data;
      setCartItems(items);
      calculateSubtotal(items);
    } catch (error) {
      console.error('Error fetching cart items:', error);
    }
  };

  const calculateSubtotal = (items) => {
    const subtotal = items.reduce((acc, item) => acc + item.menuPrice * item.quantity, 0);
    setSubtotal(subtotal);
  };

  const handleQuantityChange = async (index, increment) => {
    const updatedCartItems = [...cartItems];
    const cartId = updatedCartItems[index].cartId;
    const apiEndpoint = increment > 0 ? 
      `http://localhost:8080/api/v1/cart/${cartId}/increase` : 
      `http://localhost:8080/api/v1/cart/${cartId}/decrease`;

    try {
      await axios.put(apiEndpoint);
      updatedCartItems[index].quantity += increment;

      if (updatedCartItems[index].quantity < 1) {
        updatedCartItems[index].quantity = 1;
      }

      setCartItems(updatedCartItems);
      calculateSubtotal(updatedCartItems);
    } catch (error) {
      console.error(`Error ${increment > 0 ? 'increasing' : 'decreasing'} cart item quantity:`, error);
    }
  };

  const handleRemoveItem = async (index) => {
    const itemToRemove = cartItems[index];
    await deleteCartItem(itemToRemove.cartId);
    const updatedCartItems = cartItems.filter((_, i) => i !== index);
    setCartItems(updatedCartItems);
    calculateSubtotal(updatedCartItems);
  };

  const deleteCartItem = async (cartId) => {
    try {
      await axios.delete(`http://localhost:8080/api/v1/cart/${cartId}`);
      console.log('Cart item deleted');
    } catch (error) {
      console.error('Error deleting cart item:', error);
    }
  };

  const handleCheckout = async () => {
    try {
      const response = await axios.post('http://localhost:8080/api/v1/cart/confirm-order', {
        paymentType,
        orderType,
        mobileNumber,
        address,
      });
      console.log('Checkout successful:', response.data);
  
      // Show a success message
      alert('Order Placed Successfully');
  
      // Optionally, refresh the page or navigate to another page
      // navigate('/order-confirmation');
      window.location.reload(); // Refresh the page
    } catch (error) {
      console.error('Error during checkout:', error);
    }
  };
  

  const handleContinueShopping = () => {
    navigate('/menu');
  };

  return (
    <div className="cart-items-container">
      <h2 className="cart-title">Your Cart Items</h2>
      <form className="cart-form">
        <div className="cart-items">
          {cartItems.map((item, index) => (
            <div key={index} className="cart-item">
              <div className="item-details">
                <span className="item-name">{item.menuName}</span>
                <span className="item-price">Rs. {item.menuPrice.toFixed(2)}</span>
              </div>
              <div className="item-quantity">
                <button
                  type="button"
                  onClick={() => handleQuantityChange(index, -1)}
                  className="quantity-btn"
                >
                  -
                </button>
                <span className="quantity-value">{item.quantity}</span>
                <button
                  type="button"
                  onClick={() => handleQuantityChange(index, 1)}
                  className="quantity-btn"
                >
                  +
                </button>
              </div>
              <div className="item-total-price">
                Rs. {(item.menuPrice * item.quantity).toFixed(2)}
              </div>
              <button
                type="button"
                onClick={() => handleRemoveItem(index)}
                className="remove-btn"
              >
                🗑
              </button>
            </div>
          ))}
        </div>
        <div className="cart-summary">
          <div className="subtotal">
            <span>Subtotal:</span>
            <span>Rs. {subtotal.toFixed(2)}</span>
          </div>
          <div className="total-items">
            <span>{cartItems.length} item(s)</span>
          </div>
        </div>
        
        {/* Dropdowns for payment type and order type */}
        <div className="order-details">
  <label>
    Payment Type:
    <select value={paymentType} onChange={(e) => setPaymentType(e.target.value)}>
      <option value="Cash">Cash</option>
      <option value="Credit Card">Credit Card</option>
    </select>
  </label>
  <label>
    Order Type:
    <select value={orderType} onChange={(e) => setOrderType(e.target.value)}>
      <option value="Delivery">Delivery</option>
      <option value="Takeaway">Takeaway</option>
    </select>
  </label>
  {orderType === 'Delivery' && (
    <>
      <label>
        Address:
        <input
          type="text"
          value={address}
          onChange={(e) => setAddress(e.target.value)}
          placeholder="Enter your address"
          required
        />
      </label>
      <label>
        Mobile Number:
        <input
          type="text"
          value={mobileNumber}
          onChange={(e) => setMobileNumber(e.target.value)}
          placeholder="Enter your mobile number"
          required
        />
      </label>
    </>
  )}
</div>

        
        <div className="cart-actions">
          <button type="button" className="continue-shopping" onClick={handleContinueShopping}>
            Continue Shopping
          </button>
          <button type="button" className="checkout" onClick={handleCheckout}>
            Checkout
          </button>
        </div>
      </form>
      <div className="spacer"></div>
    </div>
  );
}

export default CartItems;
