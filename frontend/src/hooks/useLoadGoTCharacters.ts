import {useEffect, useState} from 'react';
import axios from "axios";
import {GoTCharacter} from "../model/GoTCharacter";

function UseLoadGoTCharacters() {
    const [gameOfThronesCharacters, setGameOfThronesCharacters] = useState<GoTCharacter[]>([])

    useEffect(loadCharacters,[])

    function loadCharacters(url: string = "/api/got/characters") {
        axios.get(url).then(response => {
            setGameOfThronesCharacters(response.data)
        })
    }

    return {gameOfThronesCharacters};
}

export default UseLoadGoTCharacters;