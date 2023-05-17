import React, {useEffect, useState} from 'react';
import './App.css';
import useLoadRandMCharacters from "./hooks/useLoadRandMCharacters";
import CharacterGallery from "./characterGallery/CharacterGallery";
import axios from "axios";
import {RandMCharacter} from "./model/RandMCharacter";

function App() {
  const {characters} = useLoadRandMCharacters();


  return (
    <div className="App">
      <CharacterGallery characters={characters}></CharacterGallery>
    </div>
  );
}

export default App;
