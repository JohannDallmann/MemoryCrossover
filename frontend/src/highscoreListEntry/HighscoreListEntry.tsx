import React from 'react';
import {Score} from "../model/Score";
import moment from "moment";
import "./HighscoreListEntry.css"
import challengerRank from "../images/challengerRank.png"
import grandmasterRank from "../images/grandmasterRank.png"
import masterRank from "../images/masterRank.png"
import diamondRank from "../images/diamondRank.png"
import platinumRank from "../images/platinumRank.png"
import goldRank from "../images/goldRank.png"
import silverRank from "../images/silverRank.png"
import bronzeRank from "../images/bronzeRank.png"
import ironRank from "../images/ironRank.png"

type Props = {
    score:Score,
    entryNumber: number
}
function HighscoreListEntry(props: Props) {
    const {score, entryNumber} = props;

    let rank = "";
    let rankImage = null;

    const highscoreRanking = [
        {rank: "Challenger", rankImage: challengerRank},
        {rank: "Grandmaster", rankImage: grandmasterRank},
        {rank: "Master", rankImage: masterRank},
        {rank: "Diamond", rankImage: diamondRank},
        {rank: "Platinum", rankImage: platinumRank},
        {rank: "Gold", rankImage: goldRank},
        {rank : "Silver", rankImage: silverRank},
        {rank: "Bronze", rankImage: bronzeRank},
        {rank: "Iron", rankImage: ironRank},
    ];

    if (entryNumber < highscoreRanking.length) {
        const rankEntry = highscoreRanking[entryNumber];
        rank = rankEntry.rank;
        rankImage = rankEntry.rankImage;
    } else {
        rank = `Rank #${entryNumber+1}`;
    }

    return (
        <div className="highscore-list-entry">
            {rankImage && (
                <div className="rank-image">
                    <img src={rankImage} alt="Rank"/>
                </div>
            )}
            <div className="highscore-content">
                <h4>{rank}</h4>
                <h4>{moment(props.score.timestamp).format('DD.MM.YYYY HH:mm:ss [Uhr]')}</h4>
                <h4>{props.score.playerName}</h4>
                <h4>{props.score.score}</h4>
            </div>
        </div>
    );

}
 export default HighscoreListEntry;