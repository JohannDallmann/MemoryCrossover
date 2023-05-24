import React, {useState} from 'react';
import {RandMCharacter} from "../model/RandMCharacter";
import CharacterCard from "../characterCard/CharacterCard";

type Props ={
    characters:RandMCharacter[]
}

type MemoryCard = {
    uuid:string,
    id: number,
    name: string,
    species: string,
    image: string
    isFlipped:boolean
}

function MemoryGame2(props:Props) {

    const [cards, setCards] = useState<MemoryCard[]>(() =>
        props.characters.map((card) => ({ ...card, isFlipped: false }))
    );

    const [clickCounter, setClickCounter] = useState(0);
    const [selectedCards, setSelectedCards] = useState<MemoryCard[]>([]);

    const flipCard = (card:MemoryCard) => {
        if (!card.isFlipped && selectedCards.length < 2) {
            card.isFlipped = true;
            setSelectedCards([...selectedCards, card]);
            setClickCounter(clickCounter + 1);
        }
    };

    const checkCards = () => {
        const [firstCard, secondCard] = selectedCards;
        if (firstCard.species === secondCard.species) {
            // Karten sind richtig
            setCards(cards.map((card) => {
                if (card.id === firstCard.id || card.id === secondCard.id) {
                    return { ...card, isFlipped: true };
                }
                return card;
            }));
        } else {
            // Karten sind falsch
            setCards(cards.map((card) => {
                if (card.id === firstCard.id || card.id === secondCard.id) {
                    return { ...card, isFlipped: false };
                }
                return card;
            }));
        }

        setSelectedCards([]);
    };

    return (
        <div>
            <h2>Klick-Counter: {clickCounter}</h2>
            {selectedCards.length === 2 && (
                <button className="card-back" onClick={checkCards}></button>
            )}
            <div className="card-container">
                {cards.map((card) => (
                    <div
                        key={card.id}
                        className={`card ${card.isFlipped ? 'flipped' : ''}`}
                        onClick={() => flipCard(card)}
                    >
                        {card.isFlipped ? (
                            <div className="character-card">
                                <img src={card.image}  alt={card.name}/>
                                <h3>{card.name}</h3>
                                <h4>{card.species}</h4>
                            </div>
                        ) : (
                            <div className="card-back">Karte umdrehen</div>
                        )}
                    </div>
                ))}
            </div>
            {/*<div className="character-gallery">
            {props.characters.map((currentCharacter:RandMCharacter)=>{
                return <CharacterCard key={currentCharacter.id} character={currentCharacter}></CharacterCard>
            })
            }
        </div>*/}
        </div>
    );
}

export default MemoryGame2;