import React from 'react';
import './App.css';
import useLoadRandMCharacters from "./hooks/useLoadRandMCharacters";
import CharacterGallery from "./characterGallery/CharacterGallery";
import {Route, Link, Routes} from "react-router-dom";
import Game from "./characterGallery/Game";
import {RandMCharacter} from "./model/RandMCharacter";


function App() {
    const {characters} = useLoadRandMCharacters();
    const gameCharacters : RandMCharacter[] = [
        {
            "uuid" : "1",
            "id" : 1,
            "name" : "Rick 1",
            "species" : "Human",
            "image" : "https://rickandmortyapi.com/api/character/avatar/1.jpeg"
        },
        {
            "uuid" : "2",
            "id" : 2,
            "name" : "Rick 2",
            "species" : "Human",
            "image" : "https://rickandmortyapi.com/api/character/avatar/1.jpeg"
        },
        {
            "uuid" : "3",
            "id" : 3,
            "name" : "Alien 1",
            "species" : "Alien",
            "image" : "https://rickandmortyapi.com/api/character/avatar/1.jpeg"
        },
        {
            "uuid" : "4",
            "id" : 4,
            "name" : "Alien 2",
            "species" : "Alien",
            "image" : "https://rickandmortyapi.com/api/character/avatar/1.jpeg"
        },
    ];


    return (
        <div className="App">
            <Link to="home">
            <h1 className="Dashboard">Memory Crossover</h1>
            </Link>
            <Link to="/rickandmortygallery">
                <button className="button"> Rick and Morty Gallery</button>
            </Link>
            <Link to="/game">
                <button className="button"> Rick and Morty Game</button>
            </Link>
            <Routes>
                <Route path="/rickandmortygallery" element={<CharacterGallery characters={characters}/>}/>
                <Route path="/game" element={<Game gameCharacters={gameCharacters}/>}/>
            </Routes>

        </div>
    );
}

export default App;
