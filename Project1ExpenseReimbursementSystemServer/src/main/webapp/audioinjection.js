let backSound = new Audio("back.mp3");
let buttonSound = new Audio("button.mp3");
let cursorSound = new Audio("cursor.mp3");
let errorSound = new Audio("error.mp3");
let successSound = new Audio("login.mp3");

window.addEventListener("keydown", function(){cursorSound.play();});
document.getElementById("detail_back_btn").addEventListener("click", function(){backSound.play();})