import React from 'react';
import GalleryComponent from "../components/GalleryComponent";

type Props ={
    character: string
}

function Home(props:Props) {
    return (
        <div>
            <div className="Frankenstein">
                <div className="play-container">
                    <h2 className="h2"> Java-Bo-23-1 </h2>
                    <p className="p1"> Die zeit ist gekommen dich zu beweisen.
                        <br/>
                        Bereite dich auf das Ultimative Memory vor. </p>
                    <div className="available-button">
                        <div className="circle">
                            <div className="arrow"></div>
                        </div>
                        <div className="text">Jetzt verfügbar</div>
                    </div>
                </div>
                <div className="character-gallery-wrapper">
                    <GalleryComponent character={props.character}/>
                </div>
            </div>
        </div>
    );
}

export default Home;