import React from 'react';
import './App.css';
import useLoadRandMCharacters from "./hooks/useLoadRandMCharacters";
import CharacterGallery from "./characterGallery/CharacterGallery";
import {Route, Link, Routes} from "react-router-dom";


function App() {
    const {characters} = useLoadRandMCharacters();


    return (
        <div className="App">
            <Link to="home">
            <h1 className="Dashboard">Memory Crossover</h1>
        </Link>
            <Link to="/rickandmortygallery">
                <button className="button"> Rick and Morty Gallery</button>
            </Link>
            <Routes>
                <Route path="/rickandmortygallery" element=
                    {<CharacterGallery characters={characters}/>}/>
            </Routes>

        </div>
    );
}

export default App;
