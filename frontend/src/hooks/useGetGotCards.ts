import { useEffect, useState } from 'react';
import axios from "axios";
import {CardCharacter} from "../model/CardCharacter";
import {GoTCharacter} from "../got/model/GoTCharacter";

function useGetGotCards(url:string) {
    const [randomNCharacters, setRandomNCharacters] = useState<GoTCharacter[]>([]);
    const [cards, setCards] = useState<CardCharacter[]>([]);

    // eslint-disable-next-line react-hooks/exhaustive-deps
    useEffect(loadRandomCharacters, []);

    function loadRandomCharacters() {
        axios.get(url).then(response => {
            setRandomNCharacters(response.data);
        });
    }

    useEffect(() => {
        setCards(randomNCharacters.map((card) => (
            { ...card, hidden: true, comparison: card.family, name: card.fullName, image: card.imageUrl }
        )));
        // eslint-disable-next-line react-hooks/exhaustive-deps
    }, [randomNCharacters]);


    return { cards, loadRandomCharacters };
}

export default useGetGotCards;