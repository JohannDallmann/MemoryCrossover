import React, {useEffect, useState} from 'react';
import {RandMCharacter} from "../model/RandMCharacter";
import CharacterCard from "../characterCard/CharacterCard";
import GameCard from "../characterCard/GameCard";
import {CardCharacter} from "../model/CardCharacter";

type Props = {
    gameCharacters:CardCharacter[]
}

function Game(props:Props) {
    const [counter, setCounter] = useState<number>(0);
    const [selectedCards, setSelectedCards] = useState<CardCharacter[]>([])

    function increaseCounter(){
        setCounter(counter + 1);
    }

    function putCardsInArrayToCompare(currentCard:CardCharacter){
        if(selectedCards.length < 2){
            //console.log(selectedCards);
            setSelectedCards([...selectedCards, currentCard]);

        } else {
            compareCards();
            setSelectedCards([]);
        }
    }

    function compareCards() {
        const firstCard = selectedCards[0];
        const secondCard = selectedCards[1];

        if (firstCard.species === secondCard.species){
            firstCard.name = "changedname";
        } else {
            firstCard.hidden = true;
            secondCard.hidden = true;
            //console.log(firstCard.species + " and " + secondCard.species + " are not the same species.")
        }
    }

    return (
        <div className="character-gallery">
            <div>{"Current Counter: " + counter}</div>
            {props.gameCharacters.map((currentCharacter:CardCharacter)=>{
                return <GameCard key={currentCharacter.id}
                                 character={currentCharacter}
                                 putCardsInArrayToCompare={putCardsInArrayToCompare}
                                 increaseCounter={increaseCounter}
                                 counter ={counter}></GameCard>
            })
            }
        </div>
    );
}

export default Game;