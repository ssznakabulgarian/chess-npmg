let canvas, ctx, fieldSize, figureImages;
let socket;

function onSocketOpen(){
    console.log("connected");
    console.log(socket);
}

function onSocketMessage(event){
    const msg = JSON.parse(event.data);
    //use data from msg
    console.log(msg);
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

}