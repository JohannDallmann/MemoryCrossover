import React from 'react';
import {GoTCharacter} from "../model/GoTCharacter";

type Props={
    gotCharacter:GoTCharacter
}

function CharacterCardGoT(props:Props) {

    const {gotCharacter} = props;

    return (
        <div className="character-card">
            <img src={gotCharacter.imageUrl}  alt={gotCharacter.fullName}/>
            <h3>{props.gotCharacter.fullName}</h3>
            <h4>{props.gotCharacter.family}</h4>
        </div>
    );
}

export default CharacterCardGoT;