import React from 'react';
import GalleryComponent from '../components/GalleryComponent';
import './Home.css';
import {Link} from "react-router-dom";

type Props = {
    character: string;
    playButtonHandler: () => void;
};

function Home(props: Props) {

    return (
        <div>

            <div className="play-container">
                <h2 className="h2"> Welcome to your adventure! </h2>
                <p className="p1"> Choose between three different cardsets for the memory game. <br/>
                    You can either play with characters from Game of Thrones (GOT), Rick and Morty (RM) and a mixed cardset (Mixed).
                    <br/> Be fast, because the time is ticking...</p>
                <Link to="/play" onClick={props.playButtonHandler} className="available-button">
                    <div className="circle">
                        <div className="arrow"></div>
                    </div>
                    <div className="text">Play Now</div>
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

