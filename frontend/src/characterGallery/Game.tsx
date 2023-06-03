import React, {useEffect, useState} from 'react';
import GameCard from "../characterCard/GameCard";
import {CardCharacter} from "../model/CardCharacter";
import './Game.css';
import WinDisplay from "../components/WinDisplay";

type Props = {
    cards:CardCharacter[];
    counter:number;
    setCounter: (counter: number) => void;
}

export enum Status {
    Stopped, Running, Won, Lost
}

// TODO General
// There are a couple of problems with component updates.
// It looks like the cards update along with the timer every second.
// Probably the problem will be solved by separating these entities into components.
export type State = {
    steps: number;
    secondsLeft : number;
    status : Status;
    cardsLeft: number;
}

const startGame = (props: Props): State => {
    const totalCards = props.cards.length
    const timePerCard = 3.5
    const timeLimit = totalCards * timePerCard;

    return {
        steps: 0,
        secondsLeft: timeLimit,
        status: Status.Running,
        cardsLeft: totalCards,
    };
};


// TODO Just an example. Detailed implementation will come later
const calculateScore = (remainingTime: number, steps: number) : number => {

    const timeWeight = 0.7;
    const stepsWeight = 0.3;

    const stepsScore = -steps * stepsWeight;
    const timeScore = remainingTime * timeWeight;

    return parseFloat((timeScore + stepsScore).toFixed(2));
}

const hasWinningCondition = (state : State) : boolean => {
    return state.cardsLeft === 0;
}

const hasLosingCondition = (state : State) : boolean => {
    return state.secondsLeft === 0;
}

const nextStep = (state : State) : number => {
    return state.steps + 1;
}

function Game(props:Props) {

    const [selectedCards, setSelectedCards] = useState<CardCharacter[]>([])
    const [gameState, setGameState] = useState<State>(startGame(props));

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

            // Since two matching cards have been found, remove them from the total number of cards available to play
            setGameState((prevState) => ({ ...prevState, cardsLeft: prevState.cardsLeft - 2 }));

            firstCard.image = "https://t4.ftcdn.net/jpg/01/14/37/81/360_F_114378130_Zn6r0Vi0io6jTaKNEwW1B0F7dNyLAlva.jpg";
            secondCard.image = "https://t4.ftcdn.net/jpg/01/14/37/81/360_F_114378130_Zn6r0Vi0io6jTaKNEwW1B0F7dNyLAlva.jpg";
        } else {
            setTimeout(() => hideCards(firstCard,secondCard),0);
        }

        const newStep = nextStep(gameState);
        setGameState((prevState) => ({ ...prevState, steps: newStep }));

    }

    function hideCards(firstCard:CardCharacter,secondCard:CardCharacter){
        firstCard.hidden = true;
        secondCard.hidden = true;
    }

    useEffect(() => {
        if (props.cards.length > 0) {
            setGameState(startGame(props));
        }
    }, [props.cards]);

    useEffect(() => {
        const interval = setInterval(() => {
            setGameState((prevState) => {
                if (hasLosingCondition(prevState)) {
                    clearInterval(interval);
                    return { ...prevState, status: Status.Lost };
                } else {
                    const newSecondsLeft = prevState.secondsLeft - 1;
                    if (hasWinningCondition({ ...prevState, secondsLeft: newSecondsLeft })) {
                        clearInterval(interval);
                        return { ...prevState, secondsLeft: newSecondsLeft, status: Status.Won };
                    } else {
                        return { ...prevState, secondsLeft: newSecondsLeft };
                    }
                }
            });
        }, 1000);

        return () => clearInterval(interval);
    }, []);


    return (
        <div >
            {gameState.status === Status.Won && <WinDisplay score={calculateScore(gameState.secondsLeft, props.counter)}/>}
            <div className={"status-bar"}>
                <div className={"turns-counter"}>
                    {"Turns: " + gameState.steps}
                </div>
                <div className={"timer"}>Time left: {gameState.secondsLeft} seconds
                    {gameState.status === Status.Won && <span className={"won-text"}> - You won!</span>}
                    {gameState.status === Status.Lost && <span className={"lost-text"}> - You lost!</span>}
                </div>
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