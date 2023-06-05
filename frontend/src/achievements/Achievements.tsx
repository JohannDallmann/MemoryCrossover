import React, {useEffect, useState} from 'react';
import {State, Status} from "../characterGallery/Game";
import './AchievementCard.css'
import { useLocation } from "react-router-dom";

export type Achievement = {
    name: string;
    conditions: string;
    player: string;
};

type AchievementsProps = {
    gameState: State;
};

const Achievements: React.FC<AchievementsProps> = ({ gameState }) => {

    const [achievements, setAchievements] = useState<Achievement[]>([]);

    useEffect(() => {
        if (gameState.status === Status.Won) {
            checkAchievements(gameState);
        }
    }, [gameState]);


    const location = useLocation();

    useEffect(() => {
        // Reset the achievements array when the route changes
        setAchievements([]);
    }, [location]);

    const checkAchievements = (state: State) => {
        const { timeLimit, secondsLeft, steps, cardsLeft, score } = state;
        const achievements: Achievement[] = [];

        if ( (timeLimit-secondsLeft) <= 12) {
            console.log("I Need Speed earned!")
            const newAchievement: Achievement = {
                name: "I Need Speed!",
                conditions: "Complete the game within 12 seconds.",
                player: "",
            };
            achievements.push(newAchievement)
        }

        if (steps === 6 && cardsLeft === 0) {
            console.log("Minesweeper earned!")
            const newAchievement: Achievement = {
                name: "Minesweeper",
                conditions: "Win the game in 6 turns on a board with 12 cards.",
                player: "",
            };
            achievements.push(newAchievement)
        }

        if (secondsLeft === 0) {
            console.log("Long Long Time earned!")
            const newAchievement: Achievement = {
                name: "Long Long Time",
                conditions: "Allow the timer to go down to 0 seconds and then win the game.",
                player: "",
            };
            achievements.push(newAchievement)
        }

        if (score === 10) {
            console.log("I got 99 Problems, but Memory Ain't One earned!")
            const newAchievement: Achievement = {
                name: "I got 99 Problems, but Memory Ain't One",
                conditions: "Get the maximum score for the game.",
                player: "",
            };
            achievements.push(newAchievement)
        }

        setAchievements((prevAchievements) => [...prevAchievements, ...achievements]);
    };

    return (
        <div className="achievement-container">
            {achievements.length > 0 && <h2>Achievements</h2>}
            <ul className="achievement-list">
                {achievements.map((achievement, index) => (
                    <li key={index} className="achievement-item">
                        <div className="achievement-card">
                            <h3 className="achievement-card-title">{achievement.name}</h3>
                            <p className="achievement-card-description">{achievement.conditions}</p>
                            <p className="achievement-card-details"></p>
                        </div>
                    </li>
                ))}
            </ul>
        </div>
    );

    };
export default Achievements;