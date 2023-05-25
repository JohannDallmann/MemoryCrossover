import React, {useState} from 'react';
import {Link, Route, Routes} from "react-router-dom";
import CharacterGallery from "../characterGallery/CharacterGallery";
import useLoadRandMCharacters from "../hooks/useLoadRandMCharacters";

function GalleryComponent(props:any) {
    console.log(props.character)
    const {characters} = useLoadRandMCharacters();

    const [isRickDropdownOpen, setIsRickDropdownOpen] = useState(false);

    const closeDropdown = () => {
        setIsRickDropdownOpen(false);
    }

    return (


        <div className="galllery-component">
            <>
                <Link to="/rickandmortygallery">
                    {props.character.length != 0 && (
                        <>
                            <Routes>
                                <Route
                                    path="/rickandmortygallery"
                                    element={
                                        <>
                                            <Link to="/" onClick={closeDropdown}></Link>
                                            <CharacterGallery characters={characters} />
                                        </>
                                    }
                                />
                            </Routes>
                        </>
                    )}
                </Link>
            </>

        </div>
    );
}

export default GalleryComponent;