import React, { useState, useEffect, useRef } from 'react';
import { Link } from 'react-router-dom';
import './DropdownMenu.css';
import '../../characterGallery/CharacterGallery.css';

function DropdownMenu(props: any) {
    const [isCharacterGalleryOpen, setIsCharacterGalleryOpen] = useState(false);
    const dropdownRef = useRef<HTMLDivElement | null>(null);

    const toggleCharacterGallery = (character: string) => {
        props.updateCharacter(character);
        setIsCharacterGalleryOpen(!isCharacterGalleryOpen);
    };

    const handleOutsideClick = (event: MouseEvent) => {
        if (dropdownRef.current && !dropdownRef.current.contains(event.target as Node)) {
            setIsCharacterGalleryOpen(false);
        }
    };

    useEffect(() => {
        document.addEventListener('mousedown', handleOutsideClick);
        return () => {
            document.removeEventListener('mousedown', handleOutsideClick);
        };
    }, []);

    return (
        <div className="dropdown-column" ref={dropdownRef}>
            <button className="costume-button" onClick={() => setIsCharacterGalleryOpen(!isCharacterGalleryOpen)}>
                Gallery
            </button>
            {isCharacterGalleryOpen && (
                <div className="dropdown-menu">
                    <Link to="/rickandmortygallery">
                        <button className="dropdown-rick" onClick={() => toggleCharacterGallery('Rick and morty')}>
                            Rick and Morty
                        </button>
                    </Link>
                    <Link to="/gameofthronesgallery">
                        <button className="dropdown-got" onClick={() => toggleCharacterGallery('Game of Thrones')}>
                            Game of Thrones
                        </button>
                    </Link>
                </div>
            )}
        </div>
    );
}

export default DropdownMenu;




