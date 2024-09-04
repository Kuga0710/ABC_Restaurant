import React from 'react';
import { MenuBtn } from '../components/MenuBtn';
import './Home.css';
import AboutImg from '../utils/img/about-img.jpg';
import { Link } from 'react-router-dom';
import { ImageGallery } from '../components/ImageGallery';
import { ContactInfo } from '../components/ContactInfo';

function Home() {
    return (
        <div className='home-page'>
            <header className='h-100 min-vh-100 d-flex align-items-center text-light shadow'>
                <div className='container'>
                    <div className='row'>
                        <div className='col-sm-6 d-flex d-sm-block flex-column align-items-center'>
                            <h2 className='mb-0 fw-bold'>Welcome To</h2>
                            <h1 className='mb-5 fw-bold text-center text-sm-start'>ABC Restaurant</h1>
                            <MenuBtn />
                        </div>
                        <div className='col-sm-6 d-flex justify-content-center'>
                            <div className='rotating-image'>
                                <img src={require('../utils/img/header-img.png')} alt="ABC Restaurant" className='img-fluid' />
                            </div>
                        </div>
                    </div>
                </div>
            </header>

            <div className='container my-5'>
                <div className='row'>
                    <div className='col-lg-6 d-flex justify-content-center d-none d-lg-flex'>
                        <img src={AboutImg} className='img-fluid w-50' alt="about img" />
                    </div>
                    <div className='col-lg-6 d-flex flex-column align-items-center justify-content-center'>
                        <h2 className='fs-1 mb-5 text-uppercase fw-bold'>About Us</h2>
                        <p className='mb-5'>Welcome to ABC Restaurant, located in the heart of Jaffna. We offer a diverse menu that blends local flavors with international cuisines. Whether it's breakfast, lunch, or dinner, we ensure every meal is memorable. Join us for an exceptional dining experience in a warm and inviting atmosphere.</p>
                        <Link to="/about">
                            <button type='button' className='btn btn-outline-success btn-lg'>More About Us</button>
                        </Link>
                    </div>
                </div>
            </div>

            <div className='menu-section py-5 text-light shadow'>
                <div className='container d-flex flex-column align-items-center'>
                    <h2 className='fs-1 mb-5 text-uppercase fw-bold'>Our Favorites</h2>
                    <div className='row mb-5 w-100'>
                        <div className='col-lg-6 d-flex flex-column align-items-center mb-5 mb-lg-0'>
                            <h3 className='fs-2 mb-5'>Food</h3>
                            <ul className='px-0'>
                                <li className='d-flex justify-content-between'>
                                    <p className='fs-3 mx-2'>Mutton Briyani</p>
                                    <p className='fs-3 mx-2 text-success fw-bold'>Rs1800</p>
                                </li>
                                <li className='d-flex justify-content-between'>
                                    <p className='fs-3 mx-2'>Jaffna Kottu</p>
                                    <p className='fs-3 mx-2 text-success fw-bold'>Rs1000</p>
                                </li>
                                <li className='d-flex justify-content-between'>
                                    <p className='fs-3 mx-2'>Crab Curry</p>
                                    <p className='fs-3 mx-2 text-success fw-bold'>Rs600</p>
                                </li>
                            </ul>
                        </div>
                        <div className='col-lg-6 d-flex flex-column align-items-center mb-5 mb-lg-0'>
                            <h3 className='fs-2 mb-5'>Drinks</h3>
                            <ul className='px-0'>
                                <li className='d-flex justify-content-between'>
                                    <p className='fs-3 mx-2'>MasalaTea</p>
                                    <p className='fs-3 mx-2 text-success fw-bold'>Rs180</p>
                                </li>
                                <li className='d-flex justify-content-between'>
                                    <p className='fs-3 mx-2'>AvacodaJuice</p>
                                    <p className='fs-3 mx-2 text-success fw-bold'>Rs400</p>
                                </li>
                                <li className='d-flex justify-content-between'>
                                    <p className='fs-3 mx-2'>Mojito</p>
                                    <p className='fs-3 mx-2 text-success fw-bold'>Rs600</p>
                                </li>
                            </ul>
                        </div>
                    </div>
                    <MenuBtn />
                </div>
            </div>

            <ImageGallery />

            <div className='bg-dark text-light py-5 shadow'>
                <div className='container'>
                    <div className='row'>
                        <div className='col-lg-6 d-flex flex-column align-items-center justify-content-center mb-5 mb-lg-0'>
                            <ContactInfo />
                        </div>
                        <div className='col-lg-6 d-flex justify-content-center'>
                            <iframe
                                src="https://www.google.com/maps/embed?pb=!1m14!1m8!1m3!1d15725.493749684632!2d80.2337189!3d9.8189691!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x3aff03f990744377%3A0xa8f94f14b61e297a!2sABC%20Restaurant!5e0!3m2!1sen!2slk!4v1724300439502!5m2!1sen!2slk"
                                width="400"
                                height="300"
                                style={{ border: 0 }}
                                allowFullScreen=""
                                loading="lazy"
                                referrerPolicy="no-referrer-when-downgrade"
                                className='img-fluid w-50'>
                            </iframe>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default Home;
