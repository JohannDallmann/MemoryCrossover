import React from 'react';
import {card} from "../memoryGame/MemoryGame";
import useLoadRandMCharacters from "../hooks/useLoadRandMCharacters";
import {RandMCharacter} from "../model/RandMCharacter";
import CharacterCard from "../characterCard/CharacterCard";
// import Board from "./Board";

export type Board = card[];

type Props ={
    characters:RandMCharacter[]
}

export let generate = (m : number, n : number, characters:RandMCharacter[]): Board => {

    let size:number = m*n;

     // data format converter


    let cards_response_from_api: RandMCharacter[] = characters;
    // let cards_response_from_api: card_from_api[]  = [
    //     { uuid: "1", id: 1, name: 'Karte 1', image: 'https://rickandmortyapi.com/api/character/avatar/1.jpeg', species: 'lastName1'},
    //     { uuid: "2", id: 2, name: 'Karte 2', image: 'https://rickandmortyapi.com/api/character/avatar/2.jpeg', species: 'lastName2'},
    //     { uuid: "3", id: 3, name: 'Karte 3', image: 'https://rickandmortyapi.com/api/character/avatar/3.jpeg', species: 'lastName3'},
    //     { uuid: "4", id: 4, name: 'Karte 4', image: 'https://rickandmortyapi.com/api/character/avatar/4.jpeg', species: 'lastName4'},
    //     { uuid: "5", id: 5, name: 'Karte 5', image: 'https://rickandmortyapi.com/api/character/avatar/5.jpeg', species: 'lastName5'},
    //     { uuid: "6", id: 6, name: 'Karte 6', image: 'https://rickandmortyapi.com/api/character/avatar/6.jpeg', species: 'lastName6'},
    //     { uuid: "1", id: 7, name: 'Karte 1', image: 'https://rickandmortyapi.com/api/character/avatar/1.jpeg', species: 'lastName1'},
    //     { uuid: "2", id: 8, name: 'Karte 2', image: 'https://rickandmortyapi.com/api/character/avatar/2.jpeg', species: 'lastName2'},
    //     { uuid: "3", id: 9, name: 'Karte 3', image: 'https://rickandmortyapi.com/api/character/avatar/3.jpeg', species: 'lastName3'},
    //     { uuid: "4", id: 10, name: 'Karte 4', image: 'https://rickandmortyapi.com/api/character/avatar/4.jpeg', species: 'lastName4'},
    //     { uuid: "5", id: 11, name: 'Karte 5', image: 'https://rickandmortyapi.com/api/character/avatar/5.jpeg', species: 'lastName5'},
    //     { uuid: "6", id: 12, name: 'Karte 6', image: 'https://rickandmortyapi.com/api/character/avatar/6.jpeg', species: 'lastName6'}
    // ];

    let extractedElements: card[] = [];
    let common_property: string = "Group"
    let common_property_counter: number = 1;

    while (cards_response_from_api.length >= 2) {
        // Extract two elements from the array
        let extracted = cards_response_from_api.splice(0, 2);

        extracted[0].species = extracted[1].species = common_property + common_property_counter;
        common_property_counter++;

        for (let i = 0; i < extracted.length; i++) {
            let secondObj = {
                ...extracted[i], // Copy all properties from the first object
                isFlipped: false // Assign value to the additional field
            };
            // Add the extracted elements to the result array
            extractedElements.push(secondObj)
        }
    }
    console.log("FIRST extractedElements in Board.tsx: " + extractedElements);

    console.log(extractedElements.toString());

    // let cards: card[]  = [
    // { id: 1, name: 'Karte 1', imageUrl: 'https://rickandmortyapi.com/api/character/avatar/1.jpeg', lastName: 'Group1', isFlipped: false },
    // { id: 2, name: 'Karte 2', imageUrl: 'https://rickandmortyapi.com/api/character/avatar/2.jpeg', lastName: 'Group2', isFlipped: false },
    // { id: 3, name: 'Karte 3', imageUrl: 'https://rickandmortyapi.com/api/character/avatar/3.jpeg', lastName: 'Group3', isFlipped: false },
    // { id: 4, name: 'Karte 4', imageUrl: 'https://rickandmortyapi.com/api/character/avatar/4.jpeg', lastName: 'Group3', isFlipped: false },
    // { id: 5, name: 'Karte 5', imageUrl: 'https://rickandmortyapi.com/api/character/avatar/5.jpeg', lastName: 'Group2', isFlipped: false },
    // { id: 6, name: 'Karte 6', imageUrl: 'https://rickandmortyapi.com/api/character/avatar/6.jpeg', lastName: 'Group1', isFlipped: false }
    // ]

    return extractedElements as Board;
}
export function BoardV(props:Props) {
    // const randNCharacters = useLoadRandMCharacters();

    let tt:RandMCharacter[] = generate(1,2,props.characters);

    return (
        <div className="character-gallery">
            {props.characters.map((currentCharacter:RandMCharacter)=>{
                return <CharacterCard key={currentCharacter.id} character={currentCharacter}></CharacterCard>
            })
            }
        </div>
    );

    // return (
    //     <div className="board">
    //         {/*{board.map((cell, i) =>*/}
    //         {/*    <Cell.CellView key={i} cell={cell} onClick={_ => onClickAt(i)}/>*/}
    //         {/*)}*/}
    //     </div>
    // );
}

// export default Board;
// export default BoardV