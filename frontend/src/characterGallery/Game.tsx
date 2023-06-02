import React, {useEffect, useMemo, useState} from 'react';
import GameCard from "../characterCard/GameCard";
import { CardCharacter } from "../model/CardCharacter";
import './Game.css';
import defaultcardback from '../images/defaultcardback.jpeg'
import cardback1 from '../images/cardback1.gif';
import cardback3 from '../images/cardback3.gif';
import cardback6 from '../images/cardback6.gif';


type Props = {
    cards: CardCharacter[];
    counter: number;
    setCounter: (counter: number) => void;
};

function Game(props: Props) {
    const [selectedCards, setSelectedCards] = useState<CardCharacter[]>([]);
    const [selectedCard, setSelectedCard] = useState<number | null>(null);
    const [selectedCardImage, setSelectedCardImage] = useState<string | null>(defaultcardback);

    function increaseCounter() {
        props.setCounter(props.counter + 1);
    }

    function putCardsInArrayToCompare(currentCard: CardCharacter) {
        setSelectedCards([...selectedCards, currentCard]);
    }

    useEffect(() => {
        if (selectedCards.length === 2) {
            // eslint-disable-next-line react-hooks/exhaustive-deps
            compareCards();
            setSelectedCards([]);
        }
    }, [selectedCards]);

    function compareCards() {
        const firstCard = selectedCards[0];
        const secondCard = selectedCards[1];

        if (firstCard.comparison === secondCard.comparison) {
            firstCard.image = "https://t4.ftcdn.net/jpg/01/14/37/81/360_F_114378130_Zn6r0Vi0io6jTaKNEwW1B0F7dNyLAlva.jpg";
            secondCard.image = "https://t4.ftcdn.net/jpg/01/14/37/81/360_F_114378130_Zn6r0Vi0io6jTaKNEwW1B0F7dNyLAlva.jpg";
        } else {
            setTimeout(() => hideCards(firstCard, secondCard), 0);
        }
    }

    function hideCards(firstCard: CardCharacter, secondCard: CardCharacter) {
        firstCard.hidden = true;
        secondCard.hidden = true;
    }

    const cards = useMemo(() => [
        { id: 1, image: cardback1 },
        { id: 3, image: cardback3 },
        { id: 6, image: cardback6 },
        { id: 7, image: defaultcardback }
    ], []);

    useEffect(() => {
        if (selectedCard !== null) {
            const selectedCardObject = cards.find((card) => card.id === selectedCard);
            if (selectedCardObject) {
                setSelectedCardImage(selectedCardObject.image);
            }
        }
    }, [selectedCard, cards]);

    return (
        <div>
            <div className="status-bar">
                <div className="turns-counter">Current Counter: {props.counter}</div>
                {cards.map((card) => (
                    <div
                        key={card.id}
                        className={`mini-card ${selectedCard === card.id ? "selected" : ""}`}
                        onClick={() => setSelectedCard(card.id)}
                    >
                        <img src={card.image} alt={`Card ${card.id}`} />
                    </div>
                ))}
                <div className="timer">Time left: Test-time</div>
            </div>

            <div className="card-container">
                {props.cards.map((currentCharacter: CardCharacter) => {
                    return (
                        <GameCard
                            key={currentCharacter.id}
                            character={currentCharacter}
                            putCardsInArrayToCompare={putCardsInArrayToCompare}
                            increaseCounter={increaseCounter}
                            counter={props.counter}
                            selectedCardImage={selectedCardImage}
                        />
                    );
                })}
            </div>
        </div>
    );
}

export default Game;
