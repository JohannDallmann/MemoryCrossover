import React, {useState} from 'react';
import {RandMCharacter} from "../model/RandMCharacter";
import './CharacterCard.css';


type Props={
    character:RandMCharacter,
    increaseCounter: () => void,
    counter:number
}

function GameCard(props:Props) {

    const {character} = props;
    const [hidden, setHidden] = useState(true);


    function showCard() {
        setHidden(false);
        console.log(props.counter + 1);
        props.increaseCounter();

    }

    return (
        <div>
            {
                hidden === true
                ? <button className="card-back" onClick={showCard}></button>
                : <div className="character-card">
                    <img src={character.image}  alt={character.name}/>
                    <h3>{props.character.name}</h3>
                    <h4>{props.character.species}</h4>
                </div>
            }

        </div>

    );
}

export default GameCard;