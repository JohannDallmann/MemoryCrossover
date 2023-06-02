import useGetNRandomCards from "./useGetNRandomCards";
import {useEffect, useState} from "react";
import useGetGotCards from "./useGetGotCards";


function useGetCardSet(){
    const rmUrl: string = "/api/randm/game/board/generate?condition=SPECIES";
    const gotUrl: string = "/api/got/game/board/generate";

    const {cards: rmCards, loadRandomCharacters: loadRandomCharactersRM} = useGetNRandomCards(rmUrl);
    const {cards: gotCards, loadRandomCharacters: loadRandomCharactersGOT} =useGetGotCards(gotUrl);

    const mixedCardSet = [...rmCards, ...gotCards];

/*
    useEffect(() =>randomizeCardOrder(),[cards])
    function randomizeCardOrder(){
        mixedCardSet.sort(() => Math.random() - 0.5);
    }

 */

    return {mixedCardSet, loadRandomCharactersRM, loadRandomCharactersGOT}
}
export default useGetCardSet;