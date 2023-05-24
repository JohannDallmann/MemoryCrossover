import './App.css';
import useLoadRandMCharacters from "./hooks/useLoadRandMCharacters";
import CharacterGallery from "./characterGallery/CharacterGallery";
import {Route, Link, Routes} from "react-router-dom";
import neuefischehd from './images/neuefischehd.png';
import fische from './images/fische.png'

function App() {
    const {characters} = useLoadRandMCharacters();


    return (

        <div style={{ backgroundImage: "url('https://cdnb.artstation.com/p/assets/images/images/019/672/653/large/mohammed-gadi-rnm.jpg?1564526784')", backgroundAttachment: "fixed", backgroundSize: "cover", backgroundPosition: "center", minHeight: "100vh" }}>
            <div className="header-container">
            <img className="neuefischeHeader" src={fische} alt="Gräte"/>

                 <Link to="home">
            <h1 className="Dashboard">Memory Crossover</h1>
        </Link>
            </div>
        <div className="play-container">
            <img className="neuefischeLogo" src={neuefischehd} alt="Neue Fische Logo" />
            <h2 className="h2"> Java-Bo-23-1 </h2>
                <p className="p1"> Die zeit ist gekommen dich zu beweisen.
                    Bereite dich auf das Ultimative Memory vor. </p>
            <div className="available-button">
                <div className="circle">
                    <div className="arrow"></div>
                </div>
                <div className="text">Jetzt verfügbar</div>
            </div>

            <Link to="/rickandmortygallery">
                <button className="button"> Rick and Morty Gallery</button>
            </Link>
            <Routes>
                <Route path="/rickandmortygallery" element=
                    {<CharacterGallery characters={characters}/>}/>
            </Routes>


        </div>
        </div>

);

}

export default App;
