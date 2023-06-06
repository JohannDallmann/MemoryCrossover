import React from 'react';
import {RandMCharacter} from "../../model/RandMCharacter";
import CharacterCardRM from "../cards/CharacterCardRM";
import './CharacterGallery.css';

type Props ={
    characters:RandMCharacter[]
}

function CharacterGalleryRM(props:Props) {
    return (
        <div className="character-gallery">
            {props.characters.map((currentCharacter:RandMCharacter)=>{
                return <CharacterCardRM key={currentCharacter.id} character={currentCharacter}></CharacterCardRM>
            })
            }
        </div>
    );
}

export default CharacterGalleryRM;