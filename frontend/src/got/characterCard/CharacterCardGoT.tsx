import React from 'react';
import {GoTCharacter} from "../model/GoTCharacter";

type Props={
    character:GoTCharacter
}

function CharacterCardGoT(props:Props) {

    const {character} = props;

    return (
        <div className="character-card">
            <img src={character.imageUrl}  alt={character.fullName}/>
            <h3>{props.character.fullName}</h3>
            <h4>{props.character.family}</h4>
        </div>
    );
}

export default CharacterCardGoT;