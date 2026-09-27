let boxes = document.querySelectorAll(".box");
let resetButton = document.querySelector("#reset");
let turnX=true;
let winningPatterns = [[0,1,2],[0,3,6],[1,4,7],[2,5,8],[3,4,5],[6,7,8],[0,4,8],[2,4,6]];
let newGameButton = document.querySelector("#newGame");
let winDiv = document.querySelector(".win");
let winnerHeading = document.querySelector("#winner");

const resetGame = () => {
    enableBoxes();
    turnX=true;
}
const disableBoxes = () =>{
    for(box of boxes) {
        box.disabled = true;
    }
}
const enableBoxes = () =>{
    for(box of boxes) {
        box.disabled = false;
        box.innerText="";
        box.style.color="";
        winDiv.classList.add("hide");
    }
}
const showWinner=(winner) => {
        winnerHeading.innerText=`Congratulations!! Winner is ${winner}`;
        console.log(winnerHeading);
        winDiv.classList.remove("hide");
        disableBoxes();
}
const checkWinner = ()=>{
    for(let pattern of winningPatterns) {
        let pos0 = boxes[pattern[0]].innerText;
        let pos1 = boxes[pattern[1]].innerText;
        let pos2 = boxes[pattern[2]].innerText;
        if(pos0!="" && pos1!="" && pos2!="") {
            if(pos0==pos1 && pos1==pos2) {
                showWinner(pos0);
            }
        }

    }
}

boxes.forEach((box) => {
    box.addEventListener("click",()=> {
        if(turnX) {
            box.innerText="X";
            turnX=false;
        } else {
            box.innerText="O";
            box.style.color="#e09f3e";
            turnX=true;
        }
        box.disabled=true;
        checkWinner();
    });
});

resetButton.addEventListener("click",resetGame);
newGameButton.addEventListener("click",resetGame);

