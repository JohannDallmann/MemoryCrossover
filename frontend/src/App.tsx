import './App.css';
import React, {useState} from "react";
import useLoadRandMCharacters from "./hooks/useLoadRandMCharacters";
import CharacterGallery from "./characterGallery/CharacterGallery";
import {Route, Routes} from "react-router-dom";
import Game from "./characterGallery/Game";
import Home from "./Home/Home";
import useLoadGoTCharacters from "./got/hooks/useLoadGoTCharacters";
import CharacterGalleryGoT from "./got/characterGallery/CharacterGalleryGoT";
import Header from "./components/Header";
import HighscoreList from "./highscoreList/HighscoreList";
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
                            <Game cards={mixedCardSet} counter={counter} setCounter={setCounter} />
                        </div>}
                    />
                    <Route path="/playrm" element={
                        <div className="gameBoard">
                            <Game cards={rmCards} counter={counter} setCounter={setCounter} />
                        </div>}
                    />
                    <Route path="/playgot" element={
                        <div className="gameBoard">
                            <Game cards={gotCards} counter={counter} setCounter={setCounter} />
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
