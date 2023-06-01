import React from 'react';
import { Link } from 'react-router-dom';
import GalleryComponent from '../components/GalleryComponent';
import fische from '../images/fische.png';
import DropdownMenu from '../dropdown/menu';
import './Home.css';

type Props = {
    character: string;
};

function Home(props: Props) {
    return (
        <div>
            <header>
                <div className="header-container">
                    <div className="placeholder">
                        <a href="https://www.neuefische.de" target="_blank" rel="noopener noreferrer">
                            <img className="neuefischeHeader" src={fische} alt="Gräte" />
                        </a>
                        <Link to="/home" >
                            <h1 className="Dashboard">Memory Crossover</h1>
                        </Link>
                    </div>

                    <div className="buttons-container">
                        <DropdownMenu updateCharacter={() => {}} />
                        <Link to="/">
                            <button className="costume-button">Home</button>
                        </Link>
                        <Link to="/play">
                            <button className="costume-button">Play</button>
                        </Link>
                        <Link to="/highscorelist">
                            <button className="costume-button">Highscore</button>
                        </Link>
                    </div>
                </div>
            </header>


            <div className="Frankenstein">
                <div className="character-gallery-wrapper">
                    <GalleryComponent character={props.character} />
                </div>
            </div>
        </div>
    );
}

export default Home;

