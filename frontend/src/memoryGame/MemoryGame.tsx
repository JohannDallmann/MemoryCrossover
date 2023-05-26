import React, {useState} from 'react';
import './MemoryGame.css';
import * as Board from "../board/Board";
import useLoadRandMCharacters from "../hooks/useLoadRandMCharacters";
import {RandMCharacter} from "../model/RandMCharacter";
import {generate} from "../board/Board";
import {type} from "os";

type Props ={
    characters:RandMCharacter[]
}

// export type card = {
//     id:number,
//     name:string,
//     imageUrl:string,
//     lastName:string,
//     isFlipped:boolean
// }

export type card = {
    uuid:string,
    id: number,
    name: string,
    species: string,
    image: string,
    isFlipped:boolean
}
export enum Status {
    Stopped, Running, Won, Lost
}

type State = {
    board : Board.Board,
    secondsLeft : number,
    status : Status
}

function MemoryGame(props:Props) {
    // const randNCharacters = useLoadRandMCharacters();

    console.log("props.characters in Game.tsx: "+ props.characters)

    // console.log(" Board.generate(4,3, props.characters): " + Board.generate(4,3, props.characters).length)


    // let extractedElements: card[] = [];
    // let common_property: string = "Group"
    // let common_property_counter: number = 1;
    //
    // while (props.characters.length >= 2) {
    //     // Extract two elements from the array
    //     let extracted = props.characters.splice(0, 2);
    //
    //     extracted[0].species = extracted[1].species = common_property + common_property_counter;
    //     common_property_counter++;
    //
    //     for (let i = 0; i < extracted.length; i++) {
    //         let secondObj = {
    //             ...extracted[i], // Copy all properties from the first object
    //             isFlipped: false // Assign value to the additional field
    //         };
    //         // Add the extracted elements to the result array
    //         extractedElements.push(secondObj)
    //     }
    // }
    // console.log("extractedElements in GAME TSX :" + extractedElements);

    let tt:card[] = generate(1,2,props.characters);
    console.log("Game.tsx call generate(): " + tt.keys())

    const new_array:card[] = [];

    for (var key in tt) {
        var obj = tt[key];
        console.log(obj)
        console.log(key)
        new_array.push(obj)
    }

    console.log("new_array: " + new_array)



    let static_array: card[]  = [
        { uuid: "64f6691e-ce5a-4de1-a522-ea5120e218a5", id: 1, name: 'Karte 1', image: 'https://rickandmortyapi.com/api/character/avatar/1.jpeg', species: 'lastName1', isFlipped: false},
        { uuid: "64f6691e-ce5a-4de1-a522-ea5120e218a5", id: 2, name: 'Karte 2', image: 'https://rickandmortyapi.com/api/character/avatar/2.jpeg', species: 'lastName2', isFlipped: false},
        { uuid: "64f6691e-ce5a-4de1-a522-ea5120e218a5", id: 3, name: 'Karte 3', image: 'https://rickandmortyapi.com/api/character/avatar/3.jpeg', species: 'lastName3', isFlipped: false},
        { uuid: "64f6691e-ce5a-4de1-a522-ea5120e218a5", id: 4, name: 'Karte 4', image: 'https://rickandmortyapi.com/api/character/avatar/4.jpeg', species: 'lastName4', isFlipped: false},
        { uuid: "64f6691e-ce5a-4de1-a522-ea5120e218a5", id: 5, name: 'Karte 5', image: 'https://rickandmortyapi.com/api/character/avatar/5.jpeg', species: 'lastName5', isFlipped: false},
        { uuid: "6", id: 6, name: 'Karte 6', image: 'https://rickandmortyapi.com/api/character/avatar/6.jpeg', species: 'lastName6', isFlipped: false},
        { uuid: "1", id: 7, name: 'Karte 1', image: 'https://rickandmortyapi.com/api/character/avatar/1.jpeg', species: 'lastName1', isFlipped: false},
        { uuid: "2", id: 8, name: 'Karte 2', image: 'https://rickandmortyapi.com/api/character/avatar/2.jpeg', species: 'lastName2', isFlipped: false},
        { uuid: "3", id: 9, name: 'Karte 3', image: 'https://rickandmortyapi.com/api/character/avatar/3.jpeg', species: 'lastName3', isFlipped: false},
        { uuid: "4", id: 10, name: 'Karte 4', image: 'https://rickandmortyapi.com/api/character/avatar/4.jpeg', species: 'lastName4', isFlipped: false},
        { uuid: "5", id: 11, name: 'Karte 5', image: 'https://rickandmortyapi.com/api/character/avatar/5.jpeg', species: 'lastName5', isFlipped: false},
        { uuid: "6", id: 12, name: 'Karte 6', image: 'https://rickandmortyapi.com/api/character/avatar/6.jpeg', species: 'lastName6', isFlipped: false}
    ];

    // const [cards, setCards] = useState<card[]>(Board.generate(4,3, props.characters));
    const [cards, setCards] = useState<card[]>(new_array);


    const [clickCounter, setClickCounter] = useState(0);
    const [selectedCards, setSelectedCards] = useState<card[]>([]);

    const flipCard = (card:card) => {
        if (!card.isFlipped && selectedCards.length < 2) {
            card.isFlipped = true;
            setSelectedCards([...selectedCards, card]);
            setClickCounter(clickCounter + 1);
        }
    };

    const checkCards = () => {
        const [firstCard, secondCard] = selectedCards;
        if (firstCard.species === secondCard.species) {
            // Karten sind richtig
            setCards(cards.map((card) => {
                if (card.id === firstCard.id || card.id === secondCard.id) {
                    return { ...card, isFlipped: true };
                }
                return card;
            }));
        } else {
            // Karten sind falsch
            setCards(cards.map((card) => {
                if (card.id === firstCard.id || card.id === secondCard.id) {
                    return { ...card, isFlipped: false };
                }
                return card;
            }));
        }

        setSelectedCards([]);
    };

    return (
        <div>
            <div className={"status-bar"}>
                <div className={"turns-counter"}>Klick-Counter: {clickCounter} {selectedCards.length === 2 && (
                    <button onClick={checkCards}>Karten überprüfen</button>
                )}</div>
            <div className={"timer"}>Time left: 01:26</div>
            </div>
            <div className="card-container">
                {cards.map((card) => (
                    <div
                        key={card.id}
                        className={`card ${card.isFlipped ? 'flipped' : ''}`}
                        onClick={() => flipCard(card)}
                    >
                        {card.isFlipped ? (
                            <div className="character-card">
                                <img src={card.image}  alt={card.name}/>
                                <h3>{card.species}</h3>
                            </div>
                        ) : (
                            <div className="card-back"><p className="text-card-back">Karte umdrehen</p></div>
                        )}
                    </div>
                ))}
            </div>
        </div>
    );
}

export default MemoryGame;