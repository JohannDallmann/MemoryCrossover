import React, { useState } from 'react';
import { Link, Routes, Route } from 'react-router-dom';
import GalleryComponent from '../components/GalleryComponent';
import Game from '../characterGallery/Game';
import CharacterGallery from '../characterGallery/CharacterGallery';
import HighscoreList from '../highscoreList/HighscoreList';
import fische from '../images/fische.png';
import DropdownMenu from '../dropdown/menu';
import './Home.css';
import gameCard from "../characterCard/GameCard";
import useLoadRandMCharacters from "../hooks/useLoadRandMCharacters";
import useGetNRandomCards from "../hooks/useGetNRandomCards";


type Props = {
    character: string;
};

function Home(props: Props) {
    const [character, setCharacter] = useState('');
    const {characters} = useLoadRandMCharacters();
    const { randomNCharacters } = useGetNRandomCards();

    return (
        <div>
            <header>
                <div className="Frankenstein">
                    <div className="play-container">
                        <h2 className="h2"> Java-Bo-23-1 </h2>
                        <p className="p1">
                            Die Zeit ist gekommen, dich zu beweisen.
                            <br />
                            Bereite dich auf das ultimative Memory vor.
                        </p>
                        <div className="available-button">
                            <div className="circle">
                                <div className="arrow"></div>
                            </div>
                            <div className="text">Jetzt verfügbar</div>
                        </div>
                    </div>
                    <div className="character-gallery-wrapper">
                        <GalleryComponent character={props.character} />
                    </div>
                </div>

                <div className="header-container">
                    <div className="placeholder">
                        <a href="https://www.neuefische.de" target="_blank" rel="noopener noreferrer">
                            <img className="neuefischeHeader" src={fische} alt="Gräte" />
                        </a>
                        <Link to="/home" className="home">
                            <h1 className="Dashboard">Memory Crossover</h1>
                        </Link>
                    </div>

                    <div className="buttons-container">
                        <DropdownMenu updateCharacter={setCharacter} />
                        <Link to="/home">
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

            <Routes>
                <Route path="/home" element={<Home character={character} />} />
                <Route path="/play" element={<Game randomNCharacters={randomNCharacters} />} />
                <Route path="/highscorelist" element={<HighscoreList />} />
                <Route path="/rickandmortygallery" element={<CharacterGallery  characters={characters}/>} />
            </Routes>
        </div>
    );
}

export default Home;
