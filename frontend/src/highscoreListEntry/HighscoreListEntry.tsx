import React from 'react';
import { Score } from '../model/Score';
import moment from 'moment';
import './HighscoreListEntry.css';
import challengerRank from '../images/challengerRank.png';
import grandmasterRank from '../images/grandmasterRank.png';
import masterRank from '../images/masterRank.png';
import diamondRank from '../images/diamondRank.png';
import platinumRank from '../images/platinumRank.png';
import goldRank from '../images/goldRank.png';
import silverRank from '../images/silverRank.png';
import bronzeRank from '../images/bronzeRank.png';
import ironRank from '../images/ironRank.png';

type Props = {
    score: Score;
    entryNumber: number;
};

function HighscoreListEntry(props: Props) {
    const { entryNumber } = props;

    let rank: string;
    let rankImage = null;

    const highscoreRanking = [
        { rank: 'Iron', rankImage: ironRank },
        { rank: 'Bronze', rankImage: bronzeRank },
        { rank: 'Silver', rankImage: silverRank },
        { rank: 'Gold', rankImage: goldRank },
        { rank: 'Platinum', rankImage: platinumRank },
        { rank: 'Diamond', rankImage: diamondRank },
        { rank: 'Master', rankImage: masterRank },
        { rank: 'Grandmaster', rankImage: grandmasterRank },
        { rank: 'Challenger', rankImage: challengerRank },
    ];


    if (entryNumber < highscoreRanking.length) {
        const rankEntry = highscoreRanking[highscoreRanking.length -  entryNumber -1 ];
        rank = rankEntry.rank;
        rankImage = rankEntry.rankImage;
    } else {
        rank = `Rank #${entryNumber + 1}`;
    }

    return (
        <div className="highscore-list-entry">
            {rankImage && (
                <div className="rank-image">
                    <img src={rankImage} alt="Rank" />
                </div>
            )}
            <div className="highscore-content">
                <h4>{rank}</h4>
                <h4>{props.score.playerName}</h4>
                <h4>{"Score :" + " "+ props.score.score}</h4>
                <h5>{moment(props.score.timestamp).format('DD.MM.YYYY HH:mm:ss [Uhr]')}</h5>
            </div>
        </div>
    );
}

export default HighscoreListEntry;
