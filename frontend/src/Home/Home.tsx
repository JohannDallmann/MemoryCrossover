import React from 'react';
import GalleryComponent from '../components/GalleryComponent';
import './Home.css';
import {Link} from "react-router-dom";

type Props = {
    character: string;
};

function Home(props: Props) {

    return (
        <div>

            <div className="play-container">
                <h2 className="h2"> Java-Bo-23-1 </h2>
                <p className="p1"> Die Zeit ist gekommen, dich zu beweisen.
                    <br />
                    Bereite dich auf das ultimative Memory vor. </p>
                <Link to="/play" className="available-button">
                    <div className="circle">
                        <div className="arrow"></div>
                    </div>
                    <div className="text">Jetzt verfügbar</div>
                </Link>
            </div>

            <div className="Frankenstein">
                <div className="character-gallery-wrapper">
                    <GalleryComponent character={props.character} />
                </div>
            </div>
        </div>
    );
}

export default Home;

