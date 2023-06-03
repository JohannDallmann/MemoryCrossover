import React from 'react';
import {GoTCharacter} from "../model/GoTCharacter";
import CharacterCardGoT from "../characterCard/CharacterCardGoT";

type Props ={
    gotCharacter:GoTCharacter[]
}

function CharacterGalleryGoT(props:Props) {
    return (
        <div className="character-gallery">
            {props.gotCharacter.map((currentCharacter:GoTCharacter)=>{
                return <CharacterCardGoT key={currentCharacter.id} gotCharacter={currentCharacter}></CharacterCardGoT>
            })
            }
        </div>
    );
}

export default CharacterGalleryGoT;