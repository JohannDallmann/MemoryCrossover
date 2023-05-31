import './App.css';
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


function App() {
    const [character, setCharacter] = useState("");
    const {characters} = useLoadRandMCharacters();
    const {randomNCharacters} = useGetNRandomCards();
    /*const gameCharacters : CardCharacter[] = [
        {
            "uuid" : "1",
            "id" : 1,
            "name" : "Rick 1",
            "species" : "Human",
            "image" : "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
            "hidden" : true
        },
        {
            "uuid" : "2",
            "id" : 2,
            "name" : "Rick 2",
            "species" : "Human",
            "image" : "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
            "hidden" : true
        },
        {
            "uuid" : "3",
            "id" : 3,
            "name" : "Alien 1",
            "species" : "Alien",
            "image" : "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
            "hidden" : true
        },
        {
            "uuid" : "4",
            "id" : 4,
            "name" : "Alien 2",
            "species" : "Alien",
            "image" : "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
            "hidden" : true
        },
        {
            "uuid" : "5",
            "id" : 5,
            "name" : "Wizard 1",
            "species" : "Wizard",
            "image" : "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
            "hidden" : true
        },
        {
            "uuid" : "6",
            "id" : 6,
            "name" : "Wizard 2",
            "species" : "Wizard",
            "image" : "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
            "hidden" : true
        }
    ];

     */


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
                    <Link to={"/game"}>
                        <button className="costume-button" > Play </button>
                    </Link>
                </div>
            </div>

            <Routes>
                <Route path="/game" element={<Game randomNCharacters={randomNCharacters}/>}/>
                <Route path="/home" element={<Home character={character}/>}/>
                <Route path="/rickandmortygallery" element={<CharacterGallery characters={characters}/>}/>
            </Routes>
        </div>

);
}

export default App;
