import React, {useEffect, useState} from 'react';
import GameCard from "../characterCard/GameCard";
import {CardCharacter} from "../model/CardCharacter";
import './Game.css';

type Props = {
    cards:CardCharacter[];
    counter:number;
    setCounter: (counter: number) => void;
}

function Game(props:Props) {
    const [selectedCards, setSelectedCards] = useState<CardCharacter[]>([])


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
    }, [selectedCards]);

    function compareCards() {
        const firstCard = selectedCards[0];
        const secondCard = selectedCards[1];

        if (firstCard.comparison === secondCard.comparison){
            firstCard.image = "https://t4.ftcdn.net/jpg/01/14/37/81/360_F_114378130_Zn6r0Vi0io6jTaKNEwW1B0F7dNyLAlva.jpg";
            secondCard.image = "https://t4.ftcdn.net/jpg/01/14/37/81/360_F_114378130_Zn6r0Vi0io6jTaKNEwW1B0F7dNyLAlva.jpg"
        } else {
            setTimeout(() => hideCards(firstCard,secondCard),0);
        }
    }

    function hideCards(firstCard:CardCharacter,secondCard:CardCharacter){
        firstCard.hidden = true;
        secondCard.hidden = true;
    }

    return (
        <div >
            <div className={"status-bar"}>
                <div className={"turns-counter"}>
                    {"Current Counter: " + props.counter}
                </div>
                <div className={"timer"}>Time left: time-test</div>
            </div>

            <div className="card-container">
                {props.cards.map((currentCharacter:CardCharacter)=>{
                    return <GameCard key={currentCharacter.id}
                                     character={currentCharacter}
                                     putCardsInArrayToCompare={putCardsInArrayToCompare}
                                     increaseCounter={increaseCounter}
                                     counter ={props.counter}></GameCard>
                })
                }
            </div>
        </div>
    );
}

export default Game;