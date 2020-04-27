document.getElementById("manager-login-btn").addEventListener("click", function(){
    buttonSound.play();
    setTimeout(function(){window.open("managerlogin.html","_self");},500);
    // window.open("managerlogin.html","_self");
})

document.getElementById("employee-login-btn").addEventListener("click", function(){
    buttonSound.play();
    setTimeout(function(){window.open("employeelogin.html","_self");},500);
})

let buttonSound = new Audio("login.mp3");