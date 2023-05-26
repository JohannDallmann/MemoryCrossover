import { useState } from 'react';
import axios from 'axios';
import {Score} from "../model/Score";

const useHighscores = (): [Score[], (sortOption: string) => void] => {
    const [highscores, setHighscores] = useState<Score[]>([]);

    const fetchHighscores = (sortOption: string) => {
        let url = '';
        if (sortOption === 'score-asc') {
            url = '/api/randm/highscore/sorted/score/asc';
        } else if (sortOption === 'score-desc') {
            url = '/api/randm/highscore/sorted/score/desc';
        } else if (sortOption === 'timestamp-asc') {
            url = '/api/randm/highscore/sorted/timestamp/asc';
        } else if (sortOption === 'timestamp-desc') {
            url = '/api/randm/highscore/sorted/timestamp/desc';
        }

        axios
            .get(url)
            .then((response) => {
                setHighscores(response.data);
            })
            .catch((error) => {
                console.error('Error fetching highscores:', error);
                setHighscores([]);
            });
    };

    return [highscores, fetchHighscores];
};

export default useHighscores;