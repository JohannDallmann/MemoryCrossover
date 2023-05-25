import React, {useEffect, useState} from 'react';
import {RandMCharacter} from "../model/RandMCharacter";
import CharacterCard from "../characterCard/CharacterCard";
import GameCard from "../characterCard/GameCard";

type Props = {
    gameCharacters:RandMCharacter[]
}

function Game(props:Props) {
    const [counter, setCounter] = useState<number>(0);
    const [selectedCards, setSelectedCards] = useState<RandMCharacter[]>([])

    useEffect(() => {
        console.log(selectedCards); // Log the updated value of selectedCards
    }, [selectedCards]);

    function increaseCounter(){
        setCounter(counter + 1);
    }

    function putCardsInArrayToCompare(currentCard:RandMCharacter){
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
            //console.log("same species: " + firstCard.species);
            firstCard.name = "changedname"
        } else {
            //console.log(firstCard.species + " and " + secondCard.species + " are not the same species.")
        }
    }

    return (
        <div className="character-gallery">
            <div>{"Current Counter: " + counter}</div>
            {props.gameCharacters.map((currentCharacter:RandMCharacter)=>{
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