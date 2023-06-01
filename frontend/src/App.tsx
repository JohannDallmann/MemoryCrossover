import './App.css';
import fische from './images/fische.png'
import DropdownMenu from "./dropdown/menu";
import React, {useState} from "react";
import useLoadRandMCharacters from "./hooks/useLoadRandMCharacters";
import CharacterGallery from "./characterGallery/CharacterGallery";
import {Route, Link, Routes} from "react-router-dom";
import Game from "./characterGallery/Game";
import Home from "./Home/Home";
import useGetNRandomCards from "./hooks/useGetNRandomCards";
import useLoadGoTCharacters from "./got/hooks/useLoadGoTCharacters";
import CharacterGalleryGoT from "./got/characterGallery/CharacterGalleryGoT";


function App() {
    const {characters} = useLoadRandMCharacters();
    const {cards, loadRandomCharacters} = useGetNRandomCards();
    const [counter, setCounter] = useState<number>(0);
    const [character] = useState('');
    const [gotCharacter, setCharacter] = useState("");
    const {gameOfThronesCharacters} = useLoadGoTCharacters();

    function playButtonHandler(){
        loadRandomCharacters();
        setCounter(0);
    }


    return (

        <div style={{ backgroundImage: "url('https://cdnb.artstation.com/p/assets/images/images/019/672/653/large/mohammed-gadi-rnm.jpg?1564526784')", backgroundAttachment: "fixed", backgroundSize: "cover", backgroundPosition: "center", minHeight: "100vh" }}>
            <header>
            <Header />
            </header>
                <Routes>
                    <Route path="/" element={<Home character={character}/>} />
                    <Route path="/play" element={
                        <div className="gameBoard">
                            <Game cards={cards} counter={counter} setCounter={setCounter} />
                        </div>}
                    />
                    <Route path="/highscorelist" element={<HighscoreList />} />
                    <Route path="/rickandmortygallery" element={<CharacterGallery characters={characters} />} />
                    <Route path="/gameofthronesgallery" element=
                        {<CharacterGalleryGoT gotCharacter={gameOfThronesCharacters}/>}/>
                </Routes>
        </div>

);
}

export default App;
