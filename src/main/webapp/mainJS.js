let canvas, ctx, fieldSize, figureImages;


window.onload = ()=>{
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