import React from 'react';
import {RandMCharacter} from "../model/RandMCharacter";

type Props={
    character:RandMCharacter
}

function CharacterCard(props:Props) {
    return (
        <div>
            {props.character.name}
        </div>
    );
}

export default CharacterCard;