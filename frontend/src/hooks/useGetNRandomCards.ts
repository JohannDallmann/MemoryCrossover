import { useEffect, useState } from 'react';
import { RandMCharacter } from "../model/RandMCharacter";
import axios from "axios";
import {CardCharacter} from "../model/CardCharacter";

function useGetNRandomCards() {
    const [randomNCharacters, setRandomNCharacters] = useState<RandMCharacter[]>([]);
    const [cards, setCards] = useState<CardCharacter[]>([]);

    useEffect(() => {
        loadRandomCharacters();
    }, []);

    function loadRandomCharacters(url: string = "/api/randm/game/board/generate?condition=SPECIES") {
        axios.get(url).then(response => {
            setRandomNCharacters(response.data);
        });
    }

    useEffect(() => {
        setCards(randomNCharacters.map((card) => (
            { ...card, hidden: true, comparison: card.species }
        )));
    }, [randomNCharacters]);


    return { cards };
}

export default useGetNRandomCards;