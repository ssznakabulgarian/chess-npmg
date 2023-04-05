let canvas, ctx, fieldSize, figureImages, board;
let socket;

function onSocketOpen(){
    console.log("connected");
    console.log(socket);
}

function onSocketMessage(event){
    let msg = event.data.toString();
    if(msg.startsWith("board:")){
        board = JSON.parse(msg.slice(6));
        console.log(board);
    }else{
        //other events
        console.log(msg);
    }
}

function onSocketError(error){
    console.log(error);
}

window.onload = ()=>{
    socket = new WebSocket("ws://localhost:8080/chess_npmg/server");
    socket.onopen = onSocketOpen;
    socket.onmessage = onSocketMessage;
    socket.onerror = onSocketError;

    canvas = document.getElementById("canvas");
    ctx = canvas.getContext("2d");
    fieldSize = 80;

    figureImages = [];
    figureImages["pawnW"] = document.getElementById("pawnW");
    figureImages["pawnB"] = document.getElementById("pawnB");
    figureImages["rookW"] = document.getElementById("rookW");
    figureImages["rookB"] = document.getElementById("rookB");
    figureImages["knightW"] = document.getElementById("knightW");
    figureImages["knightB"] = document.getElementById("knightB");
    figureImages["bishopW"] = document.getElementById("bishopW");
    figureImages["bishopB"] = document.getElementById("bishopB");
    figureImages["queenW"] = document.getElementById("queenW");
    figureImages["queenB"] = document.getElementById("queenB");
    figureImages["kingW"] = document.getElementById("kingW");
    figureImages["kingB"] = document.getElementById("kingB");

    drawBoard(ctx);
}

function drawBoard(ctx)
{
    for (let i = 0; i < 8; i++) {
        for (let j = 0; j < 8; j++) {
            if ((i + j) % 2 === 0) {
                ctx.fillStyle = "#ffffff";
                ctx.fillRect(i * fieldSize, j * fieldSize, fieldSize, fieldSize);
            } else {
                ctx.fillStyle = "#808080";
                ctx.fillRect(i * fieldSize, j * fieldSize, fieldSize, fieldSize);
            }
        }
    }
}

function drawFigures()
{
    //board.
}