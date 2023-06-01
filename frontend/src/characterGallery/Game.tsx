import React, {useEffect, useState} from 'react';
import GameCard from "../characterCard/GameCard";
import {CardCharacter} from "../model/CardCharacter";
import './Game.css';

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
    // TODO move the 'counter' variable from props to 'type State'
    // I assume the counter should be used at this point.
    // It refers to the state of the game.
    // Further, parameters such as counter and secondsLeft
    // can be used for statistics or saving the result of the user's game.
    counter: number;
    secondsLeft : number;
    status : Status;
    // TODO Change the win condition
    // this condition is used as an example or a quick solution
    // assuming there are always 12 cards (or six pairs) on the board.
    // After each pair is found, the counter is incremented.
    // When all 6 of 6 pairs are found, the win condition is reached.
    // dumbWinCondition variable should not be in the State type.
    // Instead, all checks must be done in the function hasWinningCondition = (state : State)
    // In a correct solution, it is necessary to check the state of all cards on the board.
    // When all cards are turned over (or not hidden, etc.), the win condition is reached.
    dumbWinCondition: number;
}

const startGame = () : State => ({
    counter: 0,
    secondsLeft: 60,
    status: Status.Running,
    dumbWinCondition: 0,
})

const hasWinningCondition = (state : State) : boolean => {
    return state.dumbWinCondition === 6;
}

const hasLosingCondition = (state : State) : boolean => {
    return state.secondsLeft === 0;
}


function Game(props:Props) {
    const [selectedCards, setSelectedCards] = useState<CardCharacter[]>([])
    const [gameState, setGameState] = useState<State>(startGame());

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

            gameState.dumbWinCondition = gameState.dumbWinCondition + 1;

            firstCard.image = "https://t4.ftcdn.net/jpg/01/14/37/81/360_F_114378130_Zn6r0Vi0io6jTaKNEwW1B0F7dNyLAlva.jpg";
            secondCard.image = "https://t4.ftcdn.net/jpg/01/14/37/81/360_F_114378130_Zn6r0Vi0io6jTaKNEwW1B0F7dNyLAlva.jpg";
        } else {
            setTimeout(() => hideCards(firstCard,secondCard),0);
        }

        if (gameState.dumbWinCondition === 6) {
            setGameState((prevState) => ({ ...prevState, status: Status.Won }));
        }
    }

    function hideCards(firstCard:CardCharacter,secondCard:CardCharacter){
        firstCard.hidden = true;
        secondCard.hidden = true;
    }


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
            <div className={"status-bar"}>
                <div className={"turns-counter"}>
                    {"Current Counter: " + props.counter}
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