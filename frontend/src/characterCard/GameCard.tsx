import React, {useState} from 'react';
import {RandMCharacter} from "../model/RandMCharacter";
import './CharacterCard.css';
import {CardCharacter} from "../model/CardCharacter";
import {Status} from "../characterGallery/Game";



type Props={
    character:CardCharacter,
    increaseCounter: () => void,
    putCardsInArrayToCompare: (currentCard:CardCharacter) => void,
    counter:number,
    gameStatus: Status;
}

function GameCard(props: Props) {

    const {character, gameStatus} = props;


    function showCard() {
        character.hidden = false;
        props.increaseCounter();
        props.putCardsInArrayToCompare(character);
    }

    return (
        <div>
            {
                character.hidden ? (
                    <button
                        className="card-back"
                        onClick={showCard}
                        disabled={gameStatus === Status.Lost} // Disable the click event when game is lost
                    ></button>
                ) : (<div className="character-card">
                        <img src={character.image} alt={character.name}/>
                        <h3>{props.character.name}</h3>
                        <h4>{props.character.comparison}</h4>
                    </div>
                )}

        </div>

    );
}

export default GameCard;