import { useEffect, useState } from 'react';
import { RandMCharacter } from "../model/RandMCharacter";
import axios from "axios";
import {CardCharacter} from "../model/CardCharacter";

function useGetRmCards(url:string) {
    const [randomNCharacters, setRandomNCharacters] = useState<RandMCharacter[]>([]);
    const [cards, setCards] = useState<CardCharacter[]>([]);

    // eslint-disable-next-line react-hooks/exhaustive-deps
    useEffect(loadRandomCharacters, []);

    function loadRandomCharacters() {
        axios.get(url).then(response => {
            setRandomNCharacters(response.data);
        });
    }

    useEffect(() => {
        setCards(randomNCharacters.map((card) => {
                return {...card, hidden: true, comparison: card.species}
        } ));
        // eslint-disable-next-line react-hooks/exhaustive-deps
    }, [randomNCharacters]);


    return { cards, loadRandomCharacters };
}

export default useGetRmCards;