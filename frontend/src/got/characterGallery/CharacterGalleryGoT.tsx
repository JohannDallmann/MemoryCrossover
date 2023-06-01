import React from 'react';
import {GoTCharacter} from "../model/GoTCharacter";
import CharacterCardGoT from "../characterCard/CharacterCardGoT";

type Props ={
    characters:GoTCharacter[]
}

function CharacterGalleryGoT(props:Props) {
    return (
        <div className="character-gallery">
            {props.characters.map((currentCharacter:GoTCharacter)=>{
                return <CharacterCardGoT key={currentCharacter.id} character={currentCharacter}></CharacterCardGoT>
            })
            }
        </div>
    );
}

export default CharacterGalleryGoT;