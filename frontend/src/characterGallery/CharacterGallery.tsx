import React from 'react';
import {RandMCharacter} from "../model/RandMCharacter";
import CharacterCard from "../characterCard/CharacterCard";

type Props ={
    characters:RandMCharacter[]
}

function CharacterGallery(props:Props) {
    return (
        <div>
            {props.characters.map((currentCharacter:RandMCharacter)=>{
                return <CharacterCard key={currentCharacter.id} character={currentCharacter}></CharacterCard>
            })
            }
        </div>
    );
}

export default CharacterGallery;