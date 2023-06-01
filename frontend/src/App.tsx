import './App.css';
import {Link, Route, Routes} from "react-router-dom";
import fische from './images/fische.png'
import DropdownMenu from "./dropdown/menu";
import React, {useState} from "react";
import useLoadRandMCharacters from "./hooks/useLoadRandMCharacters";
import CharacterGallery from "./characterGallery/CharacterGallery";
import {Route, Link, Routes} from "react-router-dom";
import Game from "./characterGallery/Game";
import {CardCharacter} from "./model/CardCharacter";
import Home from "./Home/Home";
import useGetNRandomCards from "./hooks/useGetNRandomCards";

import GalleryComponent from "./components/GalleryComponent";
import HighscoreList from "./highscoreList/HighscoreList";

function App() {
    const [character, setCharacter] = useState("");
    const {characters} = useLoadRandMCharacters();
    const {randomNCharacters} = useGetNRandomCards();


    return (

        <div style={{ backgroundImage: "url('https://cdnb.artstation.com/p/assets/images/images/019/672/653/large/mohammed-gadi-rnm.jpg?1564526784')", backgroundAttachment: "fixed", backgroundSize: "cover", backgroundPosition: "center", minHeight: "100vh" }}>
            <div className="header-container">
                <div className="placeholder">
                    <Link to="https://www.neuefische.de" target="_blank">
                    <img className="neuefischeHeader" src={fische} alt="Gräte"/>
                    </Link>
                    <Link to="/home" className="home">
                        <h1 className="Dashboard">Memory Crossover</h1>
                    </Link>
                </div>

                <div className="buttons-container">
                    <DropdownMenu updateCharacter = {setCharacter}/>
                    <Link to="/home">
                        <button className="costume-button"> Home </button>
                    </Link>
                    <button className="costume-button"> Play </button>
                    <Link to="/highscorelist">
                        <button className="costume-button">Highscore </button>
                    </Link>
                </div>
            </div>
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
                    <GalleryComponent character={character}/>
                    <Routes>
                        <Route path="/highscorelist" element=
                            {<HighscoreList/>}/>
                    </Routes>
                </div>
            </div>


        </div>
    );
}
export default App;
