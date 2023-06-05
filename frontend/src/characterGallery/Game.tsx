import React, {useEffect, useMemo, useState} from 'react';
import GameCard from "../characterCard/GameCard";
import {CardCharacter} from "../model/CardCharacter";
import './Game.css';
import defaultcardback from '../images/defaultcardback.jpeg'
import cardback1 from '../images/cardback1.gif';
import cardback3 from '../images/cardback3.gif';
import cardback6 from '../images/cardback6.gif';
import WinDisplay from "../components/WinDisplay";


type Props = {
    cards:CardCharacter[];
    counter:number;
    setCounter: (counter: number) => void;
}

function Game(props: Props) {
    const [selectedCards, setSelectedCards] = useState<CardCharacter[]>([]);
    const [selectedCard, setSelectedCard] = useState<number | null>(null);
    const [selectedCardImage, setSelectedCardImage] = useState<string | null>(defaultcardback);

    function isGameWon(){
        for (let i = 0; i < props.cards.length; i++) {
            if (props.cards[i].hidden) {
                return false;
            }
        }
        return true;
    }

    function increaseCounter(){
        props.setCounter(props.counter + 1);
    }

    function putCardsInArrayToCompare(currentCard:CardCharacter){
        setSelectedCards([...selectedCards, currentCard]);
    }

    useEffect(() => {
        if (selectedCards.length === 2) {
            compareCards();
            setSelectedCards([]);
        }
        // eslint-disable-next-line react-hooks/exhaustive-deps
    }, [selectedCards]);

    function compareCards() {
        const firstCard = selectedCards[0];
        const secondCard = selectedCards[1];

        if (firstCard.comparison === secondCard.comparison){
            firstCard.image = "https://t4.ftcdn.net/jpg/01/14/37/81/360_F_114378130_Zn6r0Vi0io6jTaKNEwW1B0F7dNyLAlva.jpg";
            secondCard.image = "https://t4.ftcdn.net/jpg/01/14/37/81/360_F_114378130_Zn6r0Vi0io6jTaKNEwW1B0F7dNyLAlva.jpg";
        } else {
            setTimeout(() => hideCards(firstCard,secondCard),0);
        }
    }

    function hideCards(firstCard:CardCharacter,secondCard:CardCharacter){
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
            {isGameWon() && <WinDisplay score={props.counter }

            />}
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
                            key={currentCharacter.uuid}
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