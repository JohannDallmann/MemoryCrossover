import React from 'react';
import {findAllByDisplayValue} from "@testing-library/react";

export enum Status {
    Open, Closed, Done, Failed
}

export type Cell = {
    symbol : string
    status : Status
}

function Cell() {

    return (
        <div></div>
        // <div className="cell" onClick={onClick}>
        //     {status == Status.Closed ? "" : symbol}
        // </div>
    );
}

export default Cell;