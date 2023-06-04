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

// TODO The rating has the correct values only for the board with 12 cards.
// For other board sizes, additional checks are needed,
// as well as statistical data to determine the boundaries of the best and worst score
const calculateTimeRating = (remainingTime:number) => {
    const timeValues = [30, 26, 22, 18, 14]; // Input values
    const ratingValues = [5, 4, 3, 2, 1]; // Output values

    // If remainingTime is even longer than the time required for the highest rating, return the highest rating
    // Otherwise x1 will be undefined
    if (remainingTime > timeValues[0]) {
        return ratingValues[0];
    }

    // Find the index of the nearest lower value in the timeValues array
    const index = timeValues.findIndex((value) => remainingTime >= value);

    // If remainingTime is less than the smallest value, return the lowest rating
    if (index === -1) {
        return ratingValues[ratingValues.length - 1];
    }

    // If remainingTime is exactly equal to a value in timeValues, return the corresponding rating
    if (remainingTime === timeValues[index]) {
        return ratingValues[index];
    }

    // Perform linear interpolation to calculate the rating
    const x0 = timeValues[index];
    const x1 = timeValues[index - 1];
    const y0 = ratingValues[index];
    const y1 = ratingValues[index - 1];

    return y0 + ((y1 - y0) / (x1 - x0)) * (remainingTime - x0);
};

const calculateStepsRating = (steps: number) => {
    const stepsValues = [8, 10, 12, 14, 16]; // Input values
    const ratingValues = [5, 4, 3, 2, 1]; // Output values

    if (steps < stepsValues[0]) {
        return ratingValues[0];
    }

    // Find the index of the nearest larger value in the stepsValues array
    const index = stepsValues.findIndex((value) => steps <= value);

    // If steps is greater than the largest value, return the lowest rating
    if (index === -1) {
        return ratingValues[ratingValues.length - 1];
    }

    // If steps is exactly equal to a value in stepsValues, return the corresponding rating
    if (steps === stepsValues[index]) {
        return ratingValues[index];
    }

    // Perform linear interpolation to calculate the rating
    const x0 = stepsValues[index-1];
    const x1 = stepsValues[index];
    const y0 = ratingValues[index -1];
    const y1 = ratingValues[index];

    return y0 + ((y1 - y0) / (x1 - x0)) * (steps - x0);
};

// A rating system based on a 10-point scale. 5 points for time and 5 points for steps
const calculateScore = (remainingTime: number, steps: number) : number => {

    const timeRating = calculateTimeRating(remainingTime);
    const stepsRating = calculateStepsRating(steps);

    return parseFloat((timeRating + stepsRating).toFixed(2));
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
    const [isTimerPulsating, setTimerPulsating] = useState(false);


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
            setTimeout(() => hideCards(firstCard,secondCard),250);
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
                    if (newSecondsLeft === 5) {
                        setTimerPulsating(true);
                    }
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

    useEffect(() => {
        if (gameState.status !== Status.Running) {
            setTimerPulsating(false);
        }
    }, [gameState.status]);


    return (
        <div >
            {gameState.status === Status.Won && <WinDisplay score={calculateScore(gameState.secondsLeft, gameState.steps)}
                                                            remainingTime={gameState.secondsLeft} numberOfSteps={gameState.steps} />}
            <div className={"status-bar"}>
                <div className={"turns-counter"}>
                    {"Turns: " + gameState.steps}
                </div>
                <div className={`timer ${isTimerPulsating ? 'pulsate' : ''}`}>Time left: {gameState.secondsLeft} seconds
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
                                     counter ={props.counter}
                                     gameStatus={gameState.status}></GameCard>
                })
                }
            </div>
        </div>
    );
}

export default Game;