import React, { useState } from 'react';
import axios from 'axios';
import './Login.css';

function Login() {
    const [isRegistering, setIsRegistering] = useState(false);
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [username, setUsername] = useState(''); // State for username
    const [error, setError] = useState('');
    const [success, setSuccess] = useState('');

    const handleLogin = async (e) => {
        e.preventDefault();
        setError('');
        setSuccess('');
        try {
            const response = await axios.post('http://localhost:8089/api/v1/auth/login', {
                email,
                password,
            });
            if (response.status === 200) {
                setSuccess('Login successful!');
                setEmail('');
                setPassword('');
                setTimeout(() => setSuccess(''), 2000); // Clear success message after 2 seconds
            }
        } catch (err) {
            setError('Invalid email or password. Please try again.');
            setEmail('');
            setPassword('');
            setTimeout(() => setError(''), 2000); // Clear error message after 2 seconds
        }
    };

    const handleRegister = async (e) => {
        e.preventDefault();
        setError('');
        setSuccess('');
        try {
            const response = await axios.post('http://localhost:8089/api/v1/auth/register', {
                username,
                email,
                password,
            });
            if (response.status === 200) {
                setSuccess('Registration successful! You can now log in.');
                setUsername('');
                setEmail('');
                setPassword('');
                setTimeout(() => setSuccess(''), 2000); // Clear success message after 2 seconds
                setIsRegistering(false); // Toggle back to login
            }
        } catch (err) {
            setError('Failed to register. Please try again.');
            setUsername('');
            setEmail('');
            setPassword('');
            setTimeout(() => setError(''), 2000); // Clear error message after 2 seconds
        }
    };

    return (
        <div className="login-container">
            <h2>{isRegistering ? 'Sign Up' : 'Login'}</h2>
            <form onSubmit={isRegistering ? handleRegister : handleLogin}>
                {isRegistering && (
                    <div className="form-group">
                        <label htmlFor="username">Username</label>
                        <input
                            type="text"
                            id="username"
                            name="username"
                            value={username}
                            onChange={(e) => setUsername(e.target.value)}
                            required
                        />
                    </div>
                )}
                <div className="form-group">
                    <label htmlFor="email">Email</label>
                    <input
                        type="email"
                        id="email"
                        name="email"
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                        required
                    />
                </div>
                <div className="form-group">
                    <label htmlFor="password">Password</label>
                    <input
                        type="password"
                        id="password"
                        name="password"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        required
                    />
                </div>
                <button type="submit" className="login-button">
                    {isRegistering ? 'Sign Up' : 'Login'}
                </button>
            </form>
            {error && <div className="error-message">{error}</div>}
            {success && <div className="success-message">{success}</div>}
            <div className="footer-space"></div> {/* Space between form and footer */}
            <button
                type="button"
                className="login-button"
                onClick={() => {
                    setIsRegistering((prev) => !prev);
                    setError(''); // Clear error message when toggling
                    setSuccess(''); // Clear success message when toggling
                }}
            >
                {isRegistering ? 'Already have an account? Login' : 'New user? Sign Up'}
            </button>
        </div>
    );
}

export default Login;
