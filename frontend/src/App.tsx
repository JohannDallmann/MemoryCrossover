import './App.css';
import {Link} from "react-router-dom";
import neuefischehd from './images/neuefischehd.png';
import fische from './images/fische.png'
import DropdownMenu from "./dropdown/menu";
import React, {useState} from "react";
import GalleryComponent from "./components/GalleryComponent";


function App() {


    const [character, setCharacter] = useState("");


    return (

        <div style={{ backgroundImage: "url('https://cdnb.artstation.com/p/assets/images/images/019/672/653/large/mohammed-gadi-rnm.jpg?1564526784')", backgroundAttachment: "fixed", backgroundSize: "cover", backgroundPosition: "center", minHeight: "100vh" }}>
            <div className="header-container">
                <div className="placeholder">
                    <Link to="https://www.neuefische.de">
                    <img className="neuefischeHeader" src={fische} alt="Gräte"/>
                    </Link>
                    <Link to="home" className="home">
                        <h1 className="Dashboard">Memory Crossover</h1>
                    </Link>
                </div>
                <div className="buttons-container">
                    <DropdownMenu updateCharacter = {setCharacter}/>
                    <button className="costume-button"> Home </button>
                    <button className="costume-button"> Play </button>
                </div>
            </div>
            <div className="Frankenstein">
                <div className="play-container">
                    {/*<img className="neuefischeLogo" src={neuefischehd} alt="Neue Fische Logo" />*/}
                    <h2 className="h2"> Java-Bo-23-1 </h2>
                    <p className="p1"> Die zeit ist gekommen dich zu beweisen.
                            <br/>
                            Bereite dich auf das Ultimative Memory vor. </p>
                    <div className="available-button">
                        <div className="circle">
                            <div className="arrow"></div>
                        </div>
                            <div className="text">Jetzt verfügbar</div>

                    </div>

                </div>
                <GalleryComponent character={character}/>
            </div>
        </div>

);
}
export default App;
