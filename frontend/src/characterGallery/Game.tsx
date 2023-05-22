import React from 'react';
import {RandMCharacter} from "../model/RandMCharacter";
import CharacterCard from "../characterCard/CharacterCard";
import GameCard from "../characterCard/GameCard";

type Props = {
    gameCharacters:RandMCharacter[]
}

function Game(props:Props) {
    return (
        <div className="character-gallery">
            {props.gameCharacters.map((currentCharacter:RandMCharacter)=>{
                return <GameCard key={currentCharacter.id} character={currentCharacter}></GameCard>
            })
            }
        </div>
    );
}

export default Game;