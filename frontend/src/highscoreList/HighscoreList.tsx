import React from 'react';
import {Score} from "../model/Score";
import HighscoreListEntry from "../highscoreListEntry/HighscoreListEntry";

type Props = {
    scores:Score[]
}

function HighscoreList(props:Props) {
    return (
        <div className="highscore-list">
            {props.scores.map((currentScore:Score, index:number)=>{
                const entryNumber = index + 1; // Fortlaufende Nummer berechnen
                return <HighscoreListEntry key={index} score={currentScore} entryNumber={entryNumber}></HighscoreListEntry>
            })
            }
        </div>
    );
}

export default HighscoreList;