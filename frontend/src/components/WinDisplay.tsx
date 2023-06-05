import React, {ChangeEvent, useState} from 'react';
import "./WinDisplay.css"
import axios from "axios";
import {useNavigate} from "react-router-dom";
import {Achievement} from "../achievements/Achievements";
type Props = {
    score: number,
    remainingTime: number,
    numberOfSteps: number,
    // achievements: Achievement[],
}
function WinDisplay(props:Props) {
    const [inputValue, setInputValue] = useState("")
    const handlerInputChange = (event:ChangeEvent<HTMLInputElement> ) => {setInputValue(event.target.value)}
    const nav = useNavigate();

    function addScore() {
        axios.post("/api/randm/highscore", {
            playerName: inputValue,
            score: props.score,
            remainingTime: props.remainingTime,
            numberOfSteps: props.numberOfSteps
        })
            .then(() => {
                nav("/highscorelist")
            });
    }

    // const saveAchievement = () => {
    //     props.achievements.forEach(achievement => {
    //         axios.post('/achievements/earned', achievement)
    //             .then(response => {
    //             })
    //             .catch(error => {
    //             });
    //     });
    // };

    const handleSubmit = () => {
        addScore();
        // saveAchievement();
    };


    return (
        <div className="score-window">
            <h2>Congratulations! You won!</h2>
            <p>Your score: {props.score} / 10 points !</p>
            <input type="text" value={inputValue} onChange={handlerInputChange}></input>
            <button onClick={handleSubmit}>Submit</button>
            <button>Highscore</button>
            <button>Home</button>

        </div>

    );
}

export default WinDisplay;