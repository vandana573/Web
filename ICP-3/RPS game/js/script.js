const rock_div = document.getElementById("r");
const paper_div = document.getElementById("p");
const scissors_div = document.getElementById("s");

function getComputerChoice(){
    const choices =['r', 'p', 's'];
    const randomNumber = Math.floor(Math.random() * 3);
    return choices[randomNumber];
    
}

function win(user, computer){
    alert("You Won, Your choice:" + user + "   " + "Computer choice:" + computer);
}

function lose(user, computer){
    alert("You lose, Your choice:" + user + "   " + "Computer choice:" + computer);
}

function tie(user, computer){
    alert("It's a tie, Your choice:" + user + "   " + "Computer choice:" + computer);
}

function game(userChoice){
const computerChoice = getComputerChoice();
switch( userChoice + computerChoice){
    case "rs":
    case "rp":
    case "sp":
        win(userChoice, computerChoice);
        break;
    case "pr":
    case "ps":
    case "sr":
        lose(userChoice, computerChoice);
        break;
    case "rr":
    case "pp":
    case "ss":
        tie(userChoice, computerChoice);
        break;    
}

}

function main(){
rock_div.addEventListener('click', function(){
    game("r");
})


paper_div.addEventListener('click', function(){
    game("p");
})


scissors_div.addEventListener('click', function(){
    game("s");
})
}

main();