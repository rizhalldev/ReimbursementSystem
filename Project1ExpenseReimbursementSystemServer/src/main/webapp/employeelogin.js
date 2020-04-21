document.getElementById("employee-login-btn").addEventListener("click", employeeLogin);

async function employeeLogin(){
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
    
    let httpResponse = await fetch("ec2-3-88-205-173.compute-1.amazonaws.com:8080/Project1ExpenseReimbursementSystemServer/api/employeelogin", settings);
    window.open("employeehome.html","_self");
}