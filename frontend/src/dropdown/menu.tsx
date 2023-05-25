import React, { useState, useEffect, useRef } from 'react';
import { Link } from 'react-router-dom';
import './menu.css';
import '../characterGallery/CharacterGallery.css';

function DropdownMenu(props: any) {
    const [isCharacterGalleryOpen, setIsCharacterGalleryOpen] = useState(false);
    const [isRickDropdownOpen, setIsRickDropdownOpen] = useState(false);
    const dropdownRef = useRef<HTMLDivElement | null>(null);

    const toggleCharacterGallery = (character: string) => {
        props.updateCharacter(character);
        setIsCharacterGalleryOpen(!isCharacterGalleryOpen);
    };

    const toggleRickDropdown = () => {
        setIsRickDropdownOpen(!isRickDropdownOpen);
        setIsCharacterGalleryOpen(false);
    };

    const handleOutsideClick = (event: MouseEvent) => {
        if (dropdownRef.current && !dropdownRef.current.contains(event.target as Node)) {
            setIsCharacterGalleryOpen(false);
            setIsRickDropdownOpen(false);
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
            <button className="costume-button" onClick={toggleRickDropdown}>
                Gallery
            </button>
            {isRickDropdownOpen && (
                <Link to="/rickandmortygallery">
                    <button className="dropdown-rick" onClick={() => toggleCharacterGallery('Rick and morty')}>
                        Rick and Morty
                    </button>
                </Link>
            )}
            {isCharacterGalleryOpen}
        </div>
    );
}

export default DropdownMenu;

