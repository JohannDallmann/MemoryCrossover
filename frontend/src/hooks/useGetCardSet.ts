import useGetRmCards from "./useGetRmCards";
import useGetGotCards from "./useGetGotCards";
import useGetMixedCards from "./useGetMixedCards";


function useGetCardSet(){
    const rmUrl: string = "/api/randm/game/board/generate?m=2&n=1&condition=SPECIES";
    const gotUrl: string = "/api/got/game/board/generate";
    const mixedUrl: string = "/api/mixed/game/board/generate";

    const {cards: rmCards, loadRandomCharacters: loadRandomCharactersRM} = useGetRmCards(rmUrl);
    const {cards: gotCards, loadRandomCharacters: loadRandomCharactersGOT} =useGetGotCards(gotUrl);
    const {cards: mixedCardSet, loadRandomCharacters: loadRandomCharactersMixed} =useGetMixedCards(mixedUrl);

    return {rmCards, gotCards, mixedCardSet, loadRandomCharactersRM, loadRandomCharactersGOT, loadRandomCharactersMixed}
}
export default useGetCardSet;