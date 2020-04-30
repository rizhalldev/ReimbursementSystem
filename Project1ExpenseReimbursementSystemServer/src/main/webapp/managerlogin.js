document.getElementById("manager-login-btn").addEventListener("click", managerLogin);

async function managerLogin(){
    successSound.play();
    let manager = {
        username:"",
        password:""
    }
    manager.username = document.getElementById("manager-username-input").value ;
    manager.password = document.getElementById("manager-password-input").value ;
    
    let settings = {
        method:"POST",
        headers:{'Content-Type':'application/json'},
        body: JSON.stringify(manager)
    }
    
    let httpResponse = await fetch(`http://${window.location.hostname}:8080/Project1ExpenseReimbursementSystemServer/api/managerlogin`, settings);
    setTimeout(function(){window.open("managerhome.html","_self");},500);
}

let backSound = new Audio("back.mp3");
let buttonSound = new Audio("button.mp3");
let cursorSound = new Audio("cursor.mp3");
let errorSound = new Audio("error.mp3");
let successSound = new Audio("login.mp3");

window.addEventListener("keydown", function(){cursorSound.play();});