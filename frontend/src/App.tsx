import React, {useEffect, useState} from 'react';
import './App.css';
import useLoadRandMCharacters from "./hooks/useLoadRandMCharacters";
import CharacterGallery from "./characterGallery/CharacterGallery";
import Axios from "axios";
import {RandMCharacter} from "./model/RandMCharacter";
import {BrowserRouter as Router, Route, Link, Routes} from "react-router-dom";


function App() {
  const {characters} = useLoadRandMCharacters();


  return (
    <div className="App">
        <Routes>
      <h1 className="Dashboard">Memory Crossover</h1>
        <Link to="/rickandmortygallery">
      <button className="button"> Rick and Morty Gallery</button>
        </Link>
            <Route path="/rickandmortygallery" element=
                {<CharacterGallery characters={characters} />} />
        </Routes>

    </div>
  );
}

export default App;
