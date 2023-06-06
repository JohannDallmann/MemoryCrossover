import './App.css';
import React, {useState} from "react";
import useLoadRandMCharacters from "./hooks/useLoadRMCharacters";
import CharacterGalleryRM from "./components/characterGallery/CharacterGalleryRM";
import {Route, Routes} from "react-router-dom";
import Game from "./components/game/Game";
import Home from "./components/Home/Home";
import useLoadGoTCharacters from "./hooks/useLoadGoTCharacters";
import CharacterGalleryGoT from "./components/characterGallery/CharacterGalleryGoT";
import Header from "./components/header/Header";
import HighscoreList from "./components/highscore/highscoreList/HighscoreList";
import useGetCardSet from "./hooks/useGetCardSet";


function App() {
    const {characters} = useLoadRandMCharacters();
    const [counter, setCounter] = useState<number>(0);
    const [character] = useState('');
    const {rmCards, gotCards, mixedCardSet, loadRandomCharactersRM, loadRandomCharactersGOT, loadRandomCharactersMixed} = useGetCardSet();
    const {gameOfThronesCharacters} = useLoadGoTCharacters();

    function playButtonHandler(){
        loadRandomCharactersRM();
        loadRandomCharactersGOT();
        loadRandomCharactersMixed();
        setCounter(0);
    }


    return (
        <div style={{ backgroundImage: "url('https://cdnb.artstation.com/p/assets/images/images/019/672/653/large/mohammed-gadi-rnm.jpg?1564526784')", backgroundAttachment: "fixed", backgroundSize: "cover", backgroundPosition: "center", minHeight: "100vh" }}>
            <header>
            <Header playButtonHandler={playButtonHandler}/>
            </header>
                <Routes>
                    <Route path="/" element={<Home character={character} playButtonHandler = {playButtonHandler}/>} />
                    <Route path="/play" element={
                        <div className="gameBoard">
                            <Game key="play" cards={mixedCardSet} counter={counter} setCounter={setCounter} />
                        </div>}
                    />
                    <Route path="/playrm" element={
                        <div className="gameBoard">
                            <Game key="playrm" cards={rmCards} counter={counter} setCounter={setCounter} />
                        </div>}
                    />
                    <Route path="/playgot" element={
                        <div className="gameBoard">
                            <Game key="playgot" cards={gotCards} counter={counter} setCounter={setCounter} />
                        </div>}
                    />

                    <Route path="/highscorelist" element={<HighscoreList />} />
                    <Route path="/rickandmortygallery" element={<CharacterGalleryRM characters={characters} />} />
                    <Route path="/gameofthronesgallery" element=
                        {<CharacterGalleryGoT gotCharacter={gameOfThronesCharacters}/>}/>
                </Routes>
        </div>

);
}

export default App;
