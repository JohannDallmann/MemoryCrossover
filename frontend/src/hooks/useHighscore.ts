import {useEffect, useState} from 'react';
import {Score} from "../model/Score";
import axios from "axios";

function UseHighscore() {
    const [scores, setScores] = useState<Score[]>([])

    useEffect(loadScores,[])

    function loadScores(url: string = "/api/randm/highscore/sorted/ascending") {
        axios.get(url).then(response => {
            setScores(response.data)
        })
    }

    return {scores};
}

export default UseHighscore;