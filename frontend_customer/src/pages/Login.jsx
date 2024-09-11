import React, { useState } from 'react';
import axios from 'axios';
import './Login.css';

function Login() {
    const [isRegistering, setIsRegistering] = useState(false);
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [email, setEmail] = useState('');
    const [error, setError] = useState('');
    const [success, setSuccess] = useState('');

    // Login handler
    const handleLogin = async (e) => {
        e.preventDefault();
        setError('');
        setSuccess('');
        try {
            const response = await axios.post('http://localhost:8080/api/v1/user/login', {
                username,
                password,
            });

            if (response.status === 200) {
                const token = response.data; // This depends on the exact response structure
                // In case your token is directly returned in response body as seen in Postman
                localStorage.setItem('token', token);

                setSuccess('Login successful!');
                setTimeout(() => setSuccess(''), 2000); // Clear success message after 2 seconds
            }
        } catch (err) {
            setError('Invalid username or password. Please try again.');
            setTimeout(() => setError(''), 2000); // Clear error message after 2 seconds
        }
    };
    // Sign up handler
    const handleRegister = async (e) => {
        e.preventDefault();
        setError('');
        setSuccess('');
        try {
            const response = await axios.post('http://localhost:8080/api/v1/user/register', {
                username,
                password,
                email, // Include email for registration
            });

            if (response.status === 200) {
                setSuccess('Registration successful! You can now log in.');
                setUsername('');
                setPassword('');
                setEmail('');
                setTimeout(() => setSuccess(''), 3000); // Clear success message after 3 seconds
                setIsRegistering(false); // Toggle back to login after successful registration
            }
        } catch (err) {
            setError('Registration failed! Please check your details and try again.');
            setUsername('');
            setPassword('');
            setEmail('');
            setTimeout(() => setError(''), 3000); // Clear error message after 3 seconds
        }
    };

    return (
        <div className="login-container">
            <h2>{isRegistering ? 'Sign Up' : 'Login'}</h2>
            <form onSubmit={isRegistering ? handleRegister : handleLogin}>
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
                {isRegistering && (
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
                )}
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
            <div className="footer-space"></div>
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
