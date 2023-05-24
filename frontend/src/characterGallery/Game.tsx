import React, {useState} from 'react';
import {RandMCharacter} from "../model/RandMCharacter";
import CharacterCard from "../characterCard/CharacterCard";
import GameCard from "../characterCard/GameCard";

type Props = {
    gameCharacters:RandMCharacter[]
}

function Game(props:Props) {
    const [counter, setCounter] = useState<number>(0);

    function increaseCounter(){
        setCounter(counter + 1);
        //console.log(counter);
        if (counter%2 === 0){
            compareCardAttributes();
        }
    }

    function compareCardAttributes(){
        //console.log("gerade zahl")
    }

    return (
        <div className="character-gallery">
            <div>{"Current Counter: " + counter}</div>
            {props.gameCharacters.map((currentCharacter:RandMCharacter)=>{
                return <GameCard key={currentCharacter.id}
                                 character={currentCharacter}
                                 increaseCounter={increaseCounter} counter ={counter}></GameCard>
            })
            }
        </div>
    );
}

export default Game;