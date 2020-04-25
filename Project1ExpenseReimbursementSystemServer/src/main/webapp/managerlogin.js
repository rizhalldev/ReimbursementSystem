document.getElementById("manager-login-btn").addEventListener("click", managerLogin);

async function managerLogin(){
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
    window.open("managerhome.html","_self");
}