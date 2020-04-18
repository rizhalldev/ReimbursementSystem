window.addEventListener("load", getLoggedEmployee);

async function getLoggedEmployee() {

    let httpResponse = await fetch("http://localhost:8080/Project1ExpenseReimbursementSystemServer/api/getloggedemployee");
    let employee = await httpResponse.json();
    
    if (employee.employeeId == 0) {
        document.getElementById("sample-text").innerHTML = "<h2>LOGIN CREDENTIALS FAILED!<h2><br> Returning to home page!";
        setTimeout(function(){window.open("index.html","_self");},3000);
    }
    else {
        document.getElementById("sample-text").innerHTML = `Hello ${employee.firstName} ${employee.lastName}, with username ${employee.userName}.<br>Your employee ID is ${employee.employeeId}.<br>Your balance is ${employee.balance}.<br>Your manager's ID is ${employee.managerId} AND YOUR PASSWORD IS ${employee.password}!!`;
    }
}