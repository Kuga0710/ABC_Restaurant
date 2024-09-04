import React from 'react';
import './About.css';
import AboutChef1 from '../utils/img/about-chef1.jpg';
import AboutChef2 from '../utils/img/about-chef2.jpg';
import { ImageGallery } from '../components/ImageGallery';
import { Reviews } from '../components/Reviews';

function About() {
    return (
        <div className='about-page'>
            <header className='mt-5'>
                <div className='container h-100 d-flex align-items-center justify-content-center'>
                    <h1 className='text-light'>About</h1>
                </div>
            </header>

            <div className='container my-5'>
                <p>Welcome to ABC Restaurant, located in the heart of Jaffna. We offer a diverse menu that blends local flavors with international cuisines. Whether it's breakfast, lunch, or dinner, we ensure every meal is memorable. Join us for an exceptional dining experience in a warm and inviting atmosphere.</p>
                <p>Since 1977, we have been serving the best of Sri Lankan style and South Indian tasty foods. Our chefs use fresh, local ingredients to create dishes that celebrate the rich culinary traditions of the region. We invite you to savor the flavors that have made us a beloved dining destination in Jaffna.</p>

                <div className='row'>
                    <div className='col-lg-6'>
                        <img src={AboutChef1} className='img-fluid chef-image' alt="Our Chef 1" />
                    </div>
                    <div className='col-lg-6'>
                    <img src={AboutChef2} className='img-fluid chef-image' alt="Our Chef 2" />
                    </div>
                </div>

                <p>At ABC Restaurant, we pride ourselves on providing a welcoming atmosphere where you can enjoy our signature dishes. Our commitment to quality and service has been the cornerstone of our success for over four decades. We look forward to serving you and making your dining experience unforgettable.</p>
            </div>


            <div className='bg-dark text-light'>
                <ImageGallery />
            </div>

            <div className='my-5'>
                <Reviews />
            </div>
        </div>
    )
}

export default About;