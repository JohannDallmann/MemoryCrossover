import React, { useEffect, useState } from 'react';
import { Score } from '../model/Score';
import HighscoreListEntry from '../highscoreListEntry/HighscoreListEntry';
import SortDropdown from '../sortDropdown/SortDropdown';
import useHighscores from '../hooks/useHighscore';

function HighscoreList() {
    const [selectedSort, setSelectedSort] = useState('score-asc');
    const [highscores, fetchHighscores] = useHighscores();

    useEffect(() => {
        fetchHighscores(selectedSort);
    }, [selectedSort]);

    const handleSortChange = (selectedOption: string) => {
        setSelectedSort(selectedOption);
    };

    return (
        <div>
            <SortDropdown onChange={handleSortChange} />
            <div className="highscore-list">
                {highscores.map((currentScore: Score, index: number) => {
                    const entryNumber = index + 1;
                    return (
                        <HighscoreListEntry
                            key={index}
                            score={currentScore}
                            entryNumber={entryNumber}
                        ></HighscoreListEntry>
                    );
                })}
            </div>
        </div>
    );
}

export default HighscoreList;