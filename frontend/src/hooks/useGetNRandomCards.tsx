// import {useEffect, useState} from 'react';
// import {RandMCharacter} from "../model/RandMCharacter";
// import axios from "axios";
//
// function useGetNRandomCards() {
//     const [randomNCharacters, setCharacters] = useState<RandMCharacter[]>([])
//
//     useEffect(loadRandomCharacters,[])
//
//     function loadRandomCharacters(url: string = "/api/randm/game/board/generate") {
//         axios.get(url).then(response => {
//             setCharacters(response.data)
//         })
//     }
//
//     return {randomNCharacters};
// }
//
// export default useGetNRandomCards;


import { useEffect, useState } from 'react';
import { RandMCharacter } from "../model/RandMCharacter";
import axios from "axios";

function useGetNRandomCards() {
    const [randomNCharacters, setCharacters] = useState<RandMCharacter[]>([]);

    useEffect(() => {
        loadRandomCharacters();
    }, []);

    function loadRandomCharacters(url: string = "/api/randm/game/board/generate") {
        axios.get(url).then(response => {
            setCharacters(response.data);
        });
    }

    return { randomNCharacters };
}

export default useGetNRandomCards;