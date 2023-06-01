import React, {useEffect, useState} from 'react';
import {RandMCharacter} from "../model/RandMCharacter";
import GameCard from "../characterCard/GameCard";
import {CardCharacter} from "../model/CardCharacter";
import './Game.css';

type Props = {
    randomNCharacters:RandMCharacter[]
}

function Game(props:Props) {
    const [counter, setCounter] = useState<number>(0);
    const [selectedCards, setSelectedCards] = useState<CardCharacter[]>([])

    const [cards] = useState<CardCharacter[]>(() =>
        props.randomNCharacters.map((card) => ({ ...card, hidden: true }))
    );

    function increaseCounter(){
        setCounter(counter + 1);
    }

    function putCardsInArrayToCompare(currentCard:CardCharacter){
        setSelectedCards([...selectedCards, currentCard]);
    }

    useEffect(() => {
        if (selectedCards.length === 2) {
            compareCards();
            setSelectedCards([]);
        }
    }, [selectedCards]);

    function compareCards() {
        const firstCard = selectedCards[0];
        const secondCard = selectedCards[1];

        if (firstCard.species === secondCard.species){
            firstCard.image = "https://t4.ftcdn.net/jpg/01/14/37/81/360_F_114378130_Zn6r0Vi0io6jTaKNEwW1B0F7dNyLAlva.jpg";
            secondCard.image = "https://t4.ftcdn.net/jpg/01/14/37/81/360_F_114378130_Zn6r0Vi0io6jTaKNEwW1B0F7dNyLAlva.jpg"
        } else {
            setTimeout(() => hideCards(firstCard,secondCard),0);
            //setImmediate(() => hideCards(firstCard,secondCard));
            //hideCards(firstCard,secondCard)
        }
    }

    function hideCards(firstCard:CardCharacter,secondCard:CardCharacter){
        firstCard.hidden = true;
        secondCard.hidden = true;
    }

    return (
        <div className = "gameBoard">
            <div className={"status-bar"}>
                <div className={"turns-counter"}>
                    {"Current Counter: " + counter}
                </div>
                <div className={"timer"}>Time left: time-test</div>
            </div>

            <div className="card-container">
                {cards.map((currentCharacter:CardCharacter)=>{
                    return <GameCard key={currentCharacter.id}
                                     character={currentCharacter}
                                     putCardsInArrayToCompare={putCardsInArrayToCompare}
                                     increaseCounter={increaseCounter}
                                     counter ={counter}></GameCard>
                })
                }
            </div>
        </div>
    );
}

export default Game;