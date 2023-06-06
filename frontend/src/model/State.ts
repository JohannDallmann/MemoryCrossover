import {Status} from "./Status";

export type State = {
    steps: number;
    timeLimit: number;
    secondsLeft : number;
    status : Status;
    cardsLeft: number;
    score: number;
}