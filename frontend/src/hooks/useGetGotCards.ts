import { useEffect, useState } from 'react';
import {CardCharacter} from "../model/CardCharacter";

function useGetGotCards() {
    const gotTestCharacters =
        [
            {
                "uuid": "0",
                "id": 0,
                "name": "Daenerys Targaryen",
                "image": "https://thronesapi.com/assets/images/daenerys.jpg",
                "comparison": "House Targaryen"
            },
            {
                "uuid": "1",
                "id": 1,
                "name": "Targaryen2",
                "image": "https://thronesapi.com/assets/images/daenerys.jpg",
                "comparison": "House Targaryen"
            }
        ];
    const [gotCards, setGotCards] = useState<CardCharacter[]>([]);



    useEffect(() => {
        setGotCards(gotTestCharacters.map((gotCards) => (
            { ...gotCards, hidden: true }
        )));
    }, []);


    return { gotCards };
}

export default useGetGotCards;