import React, { useState, useEffect } from "react";

export function ImageGallery() {
    const [galleryImages, setGalleryImages] = useState({
        gallery1: null,
        gallery2: null,
        gallery3: null,
        gallery4: null,
        gallery5: null,
        gallery6: null,
    });

    useEffect(() => {
        const fetchGalleryImage = async (type) => {
            try {
                const response = await fetch(`http://localhost:8080/api/v1/gallery/type?type=${type}`);
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                const data = await response.json();
                console.log('API Response:', JSON.stringify(data, null, 2)); // Log full response
                if (data && data.data && data.data[0] && data.data[0].image) {
                    const base64Image = `data:image/jpeg;base64,${data.data[0].image}`;
                    return base64Image;
                } else {
                    console.error("Unexpected data format:", data);
                    return null;
                }
            } catch (error) {
                console.error(`Error fetching ${type} image:`, error);
                return null;
            }
        };

        const fetchAllImages = async () => {
            const gallery1Image = await fetchGalleryImage('gallery1');
            const gallery2Image = await fetchGalleryImage('gallery2');
            const gallery3Image = await fetchGalleryImage('gallery3');
            const gallery4Image = await fetchGalleryImage('gallery4');
            const gallery5Image = await fetchGalleryImage('gallery5');
            const gallery6Image = await fetchGalleryImage('gallery6');

            setGalleryImages({
                gallery1: gallery1Image,
                gallery2: gallery2Image,
                gallery3: gallery3Image,
                gallery4: gallery4Image,
                gallery5: gallery5Image,
                gallery6: gallery6Image,
            });
        };

        fetchAllImages();
    }, []);

    return (
        <div className="container py-5">
            <h2 className="text-center fs-1 mb-5 text-uppercase fw-bold">Image Gallery</h2>
            <div className="row">
                <div className="col-md-4 px-2">
                    <div className="my-3">
                        {galleryImages.gallery1 ? (
                            <img src={galleryImages.gallery1} className="img-fluid" alt="Gallery Image 1" />
                        ) : (
                            <p>Loading gallery1 image...</p>
                        )}
                    </div>
                    <div className="my-3">
                        {galleryImages.gallery2 ? (
                            <img src={galleryImages.gallery2} className="img-fluid" alt="Gallery Image 2" />
                        ) : (
                            <p>Loading gallery2 image...</p>
                        )}
                    </div>
                </div>
                <div className="col-md-4 px-2">
                    <div className="my-3">
                        {galleryImages.gallery3 ? (
                            <img src={galleryImages.gallery3} className="img-fluid" alt="Gallery Image 3" />
                        ) : (
                            <p>Loading gallery3 image...</p>
                        )}
                    </div>
                    <div className="my-3">
                        {galleryImages.gallery4 ? (
                            <img src={galleryImages.gallery4} className="img-fluid" alt="Gallery Image 4" />
                        ) : (
                            <p>Loading gallery4 image...</p>
                        )}
                    </div>
                </div>
                <div className="col-md-4 px-2">
                    <div className="my-3">
                        {galleryImages.gallery5 ? (
                            <img src={galleryImages.gallery5} className="img-fluid" alt="Gallery Image 5" />
                        ) : (
                            <p>Loading gallery5 image...</p>
                        )}
                    </div>
                    <div className="my-3">
                        {galleryImages.gallery6 ? (
                            <img src={galleryImages.gallery6} className="img-fluid" alt="Gallery Image 6" />
                        ) : (
                            <p>Loading gallery6 image...</p>
                        )}
                    </div>
                </div>
            </div>
        </div>
    );
}
