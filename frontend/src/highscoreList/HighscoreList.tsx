import React, {useEffect, useMemo, useState} from 'react';
import { Score } from '../model/Score';
import HighscoreListEntry from '../highscoreListEntry/HighscoreListEntry';
import SortDropdown from '../sortDropdown/SortDropdown';
import useHighscores from '../hooks/useHighscore';
import './HighscoreList.css'

function HighscoreList() {
    const [selectedSort, setSelectedSort] = useState('score-desc');
    const [highscores, fetchHighscores] = useHighscores();
    const [highscoresSorted, fetchHighscoresSorted] = useHighscores();


    useEffect(() => {
        fetchHighscores(selectedSort)
        fetchHighscoresSorted('score-desc')
        // eslint-disable-next-line react-hooks/exhaustive-deps
    }, [selectedSort]);

    const ranks = useMemo(() => {
        const ranks = new Map<string, number>();
        highscoresSorted.forEach((value, index) => ranks.set(value.id, index))
        return ranks;
    }, [highscoresSorted])

    const handleSortChange = (selectedOption: string) => {
        setSelectedSort(selectedOption);
    };



    return (
        <div className="highscore-box">
            <SortDropdown onChange={handleSortChange} />
            <div className="highscore-list">
                {highscores.map((currentScore: Score) => {
                    return (
                        <HighscoreListEntry
                            key={currentScore.id}
                            score={currentScore}
                            entryNumber={ranks.get(currentScore.id)!}
                        ></HighscoreListEntry>
                    );
                })}
            </div>
        </div>
    );
}

export default HighscoreList;