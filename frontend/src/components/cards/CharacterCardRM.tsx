import React from 'react';
import {RandMCharacter} from "../../model/RandMCharacter";
import './CharacterCard.css';

type Props={
    character:RandMCharacter
}

function CharacterCardRM(props:Props) {

    const {character} = props;

    return (
        <div className="character-card">
            <img src={character.image}  alt={character.name}/>
            <h3>{props.character.name}</h3>
            <h4>{props.character.species}</h4>
        </div>
    );
}

export default CharacterCardRM;