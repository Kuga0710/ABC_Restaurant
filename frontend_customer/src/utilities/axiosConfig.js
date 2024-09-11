import axios from 'axios';

// Create an instance of Axios
const axiosInstance = axios.create({
    baseURL: 'http://localhost:8080/api/v1/', // Set your backend API base URL here
    headers: {
        'Content-Type': 'application/json',
    }
});

// Add a request interceptor to include the bearer token
axiosInstance.interceptors.request.use(
    (config) => {
        const token = localStorage.getItem('token');
        if (token) {
            config.headers['Authorization'] = `Bearer ${token}`;
        }
        return config;
    },
    (error) => {
        return Promise.reject(error);
    }
);

export default axiosInstance;
