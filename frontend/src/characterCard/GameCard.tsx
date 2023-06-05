import React from 'react';
import './CharacterCard.css';
import {CardCharacter} from "../model/CardCharacter";
import {Status} from "../characterGallery/Game";


type Props = {
    character: CardCharacter;
    increaseCounter: () => void;
    putCardsInArrayToCompare: (currentCard: CardCharacter) => void;
    counter: number;
    selectedCardImage?: string | null;
    gameStatus: Status;
};

function GameCard(props: Props) {
    const { character, gameStatus, increaseCounter, putCardsInArrayToCompare, selectedCardImage } = props;

    function showCard() {
        character.hidden = false;
        increaseCounter();
        putCardsInArrayToCompare(character);
    }

    const backgroundImage = selectedCardImage || 'images/defaultcardback.jpeg';

    return (
        <div>
            {character.hidden ? (
                <div
                    onClick={showCard}
                    // disabled={gameStatus === Status.Lost}
                    // pointerEvents: "none"
                    className="card-back"
                    style={{ backgroundImage: `url(${backgroundImage})`
                        }}
                ></div>
            ) : (
                <div className="character-card">
                    <img src={character.image} alt={character.name} />
                    <h3>{props.character.name}</h3>
                    <h4>{props.character.comparison}</h4>
                </div>
            )}
        </div>
    );
}

export default GameCard;