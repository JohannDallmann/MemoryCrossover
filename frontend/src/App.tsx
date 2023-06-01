import React, {useState} from 'react';
import {Route, Routes} from 'react-router-dom';
import HighscoreList from './highscoreList/HighscoreList';
import Game from './characterGallery/Game';
import CharacterGallery from './characterGallery/CharacterGallery';
import Home from './Home/Home';
import useLoadRandMCharacters from './hooks/useLoadRandMCharacters';
import useGetNRandomCards from './hooks/useGetNRandomCards';
import Header from './components/Header'
import './App.css';
import {Link, Route, Routes} from "react-router-dom";
import fische from './images/fische.png'
import DropdownMenu from "./dropdown/menu";
import React, {useState} from "react";
import GalleryComponent from "./components/GalleryComponent";
import HighscoreList from "./highscoreList/HighscoreList";
import CharacterGalleryGoT from "./got/characterGallery/CharacterGalleryGoT";
import useLoadGoTCharacters from "./got/hooks/useLoadGoTCharacters";


function App() {
    const [character] = useState('');
    const { characters } = useLoadRandMCharacters();
    const { randomNCharacters } = useGetNRandomCards();

    const [character, setCharacter] = useState("");
    const {gameOfThronesCharacters} = useLoadGoTCharacters();

    return (

        <div style={{ backgroundImage: "url('https://cdnb.artstation.com/p/assets/images/images/019/672/653/large/mohammed-gadi-rnm.jpg?1564526784')", backgroundAttachment: "fixed", backgroundSize: "cover", backgroundPosition: "center", minHeight: "100vh" }}>
            <header>
            <Header />
            </header>
                <Routes>
                    <Route path="/" element={<Home character={character}/>} />
                    <Route path="/play" element={
                        <div className="gameBoard">
                            <Game randomNCharacters={randomNCharacters} />
                        </div>}
                    />
                    <Route path="/highscorelist" element={<HighscoreList />} />
                    <Route path="/rickandmortygallery" element={<CharacterGallery characters={characters} />} />
                    <Route path="/gameofthronesgallery" element=
                        {<CharacterGalleryGoT characters={gameOfThronesCharacters}/>}/>
                </Routes>
        </div>
    );
}

export default App;
