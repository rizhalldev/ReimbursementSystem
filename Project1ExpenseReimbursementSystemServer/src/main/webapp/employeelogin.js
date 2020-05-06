document.getElementById("employee-login-btn").addEventListener("click", employeeLogin);

async function employeeLogin(){
    successSound.play();
    let employee = {
        userName:"",
        password:""
    }
    employee.userName = document.getElementById("employee-username-input").value ;
    employee.password = document.getElementById("employee-password-input").value ;
    
    let settings = {
        method:"POST",
        headers:{'Content-Type':'application/json'},
        body: JSON.stringify(employee)
    }
    
    let httpResponse = await fetch(`http://${window.location.hostname}:8080/Project1ExpenseReimbursementSystemServer/api/employeelogin`, settings);
    setTimeout(function(){window.open("employeehome.html","_self");},500);
}

let backSound = new Audio("back.mp3");
let buttonSound = new Audio("button.mp3");
let cursorSound = new Audio("cursor.mp3");
let errorSound = new Audio("error.mp3");
let successSound = new Audio("login.mp3");

window.addEventListener("keydown", function(){cursorSound.play();});