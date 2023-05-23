import React from 'react';
import {Score} from "../model/Score";
import moment from "moment";
import "./HighscoreListEntry.css"

type Props = {
    score:Score,
    entryNumber:number
}

function HighscoreListEntry(props:Props) {
    return (
        <div className="highscore-list-entry">
            <h4>{props.entryNumber}</h4>
            <h4>{moment(props.score.timestamp).format('DD.MM.YYYY HH:mm:ss [Uhr]')}</h4>
            <h4>{props.score.playerName}</h4>
            <h4>{props.score.score}</h4>
        </div>
    );
}

export default HighscoreListEntry;