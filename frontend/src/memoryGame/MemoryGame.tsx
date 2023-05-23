import React, {useState} from 'react';
import './MemoryGame.css';

type card = {
    id:number,
    name:string,
    imageUrl:string,
    lastName:string,
    isFlipped:boolean
}

function MemoryGame() {

    const [cards, setCards] = useState<card[]>([
        { id: 1, name: 'Karte 1', imageUrl: 'https://rickandmortyapi.com/api/character/avatar/1.jpeg', lastName: 'Group1', isFlipped: false },
        { id: 2, name: 'Karte 2', imageUrl: 'https://rickandmortyapi.com/api/character/avatar/2.jpeg', lastName: 'Group2', isFlipped: false },
        { id: 3, name: 'Karte 3', imageUrl: 'https://rickandmortyapi.com/api/character/avatar/3.jpeg', lastName: 'Group3', isFlipped: false },
        { id: 4, name: 'Karte 4', imageUrl: 'https://rickandmortyapi.com/api/character/avatar/4.jpeg', lastName: 'Group3', isFlipped: false },
        { id: 5, name: 'Karte 5', imageUrl: 'https://rickandmortyapi.com/api/character/avatar/5.jpeg', lastName: 'Group2', isFlipped: false },
        { id: 6, name: 'Karte 6', imageUrl: 'https://rickandmortyapi.com/api/character/avatar/6.jpeg', lastName: 'Group1', isFlipped: false }
    ]);

    const [clickCounter, setClickCounter] = useState(0);
    const [selectedCards, setSelectedCards] = useState<card[]>([]);

    const flipCard = (card:card) => {
        if (!card.isFlipped && selectedCards.length < 2) {
            card.isFlipped = true;
            setSelectedCards([...selectedCards, card]);
            setClickCounter(clickCounter + 1);
        }
    };

    const checkCards = () => {
        const [firstCard, secondCard] = selectedCards;
        if (firstCard.lastName === secondCard.lastName) {
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
            <h2>Klick-Counter: {clickCounter} {selectedCards.length === 2 && (
                <button onClick={checkCards}>Karten überprüfen</button>
            )}</h2>
            <div className="card-container">
                {cards.map((card) => (
                    <div
                        key={card.id}
                        className={`card ${card.isFlipped ? 'flipped' : ''}`}
                        onClick={() => flipCard(card)}
                    >
                        {card.isFlipped ? (
                            <div className="character-card">
                                <img src={card.imageUrl}  alt={card.name}/>
                                <h3>{card.lastName}</h3>
                            </div>
                        ) : (
                            <div className="card-back"><div className="text-card-back">Karte umdrehen</div></div>
                        )}
                    </div>
                ))}
            </div>
        </div>
    );
}

export default MemoryGame;