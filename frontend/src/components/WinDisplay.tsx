import React, {ChangeEvent, useState} from 'react';
import "./WinDisplay.css"
import axios from "axios";
import {useNavigate} from "react-router-dom";
type Props = {
    score: number
}
function WinDisplay(props:Props) {
    const [inputValue, setInputValue] = useState("")
    const handlerInputChange = (event:ChangeEvent<HTMLInputElement> ) => {setInputValue(event.target.value)}
    const nav = useNavigate();

    function addScore() {
        axios.post("/api/randm/highscore", { playerName: inputValue, score: props.score })
            .then(() => {
                nav("/highscorelist")
            });
    }

    return (
        <div className="score-window">
            <h2>Congratulations! You won!</h2>
            <p>Your score: {props.score} points !</p>
            <input type="text" value={inputValue} onChange={handlerInputChange}></input>
            <button onClick={addScore}>Submit</button>
            <button>Highscore</button>
            <button>Home</button>

        </div>

    );
}

export default WinDisplay;