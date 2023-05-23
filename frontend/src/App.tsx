import React from 'react';
import './App.css';
import useLoadRandMCharacters from "./hooks/useLoadRandMCharacters";
import CharacterGallery from "./characterGallery/CharacterGallery";
import {Route, Link, Routes} from "react-router-dom";
import useHighscore from "./hooks/useHighscore";
import HighscoreList from "./highscoreList/HighscoreList";


function App() {
    const {characters} = useLoadRandMCharacters();
    const {scores} = useHighscore();


    return (
        <div className="App">
            <Link to="home">
            <h1 className="Dashboard">Memory Crossover</h1>
        </Link>
            <Link to="/rickandmortygallery">
                <button className="button"> Rick and Morty Gallery</button>
            </Link>
            <Link to="/highscorelist">
                <button className="button">Highscore List</button>
            </Link>
            <Routes>
                <Route path="/rickandmortygallery" element=
                    {<CharacterGallery characters={characters}/>}/>
                <Route path="/highscorelist" element=
                    {<HighscoreList scores={scores}/>}/>
            </Routes>

        </div>
    );
}

export default App;
