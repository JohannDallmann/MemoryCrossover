import React, {useEffect, useMemo, useState} from 'react';
import { Score } from '../model/Score';
import HighscoreListEntry from '../highscoreListEntry/HighscoreListEntry';
import SortDropdown from '../sortDropdown/SortDropdown';
import useHighscores from '../hooks/useHighscore';

function HighscoreList() {
    const [selectedSort, setSelectedSort] = useState('score-asc');
    const [highscores, fetchHighscores] = useHighscores();
    const [highscoresSorted, fetchHighscoresSorted] = useHighscores();


    useEffect(() => {
        fetchHighscores(selectedSort)
        fetchHighscoresSorted('score-desc')
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
        <div>
            <SortDropdown onChange={handleSortChange} />
            <div className="highscore-list">
                {highscores.map((currentScore: Score, index: number) => {
                    return (
                        <HighscoreListEntry
                            key={index}
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