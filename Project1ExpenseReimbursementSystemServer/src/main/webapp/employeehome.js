window.addEventListener("load", function(){
    getLoggedEmployee();
    setTime();
})
document.getElementById("view_btn").addEventListener("click", function(){
    document.getElementById("options").style.visibility = "hidden";
    document.getElementById("sort_options").style.visibility = "visible";
    document.getElementById("info").style.visibility = "hidden";
    document.getElementById("reimbursements").style.visibility = "visible";
    viewAllReimbursements();
});

document.getElementById("back_btn").addEventListener("click", function(){
    window.open("employeehome.html","_self");
});
document.getElementById("pending_btn").addEventListener("click", viewPendingReimbursements);

async function getLoggedEmployee() {

    let httpResponse = await fetch(`http://${window.location.hostname}:8080/Project1ExpenseReimbursementSystemServer/api/getloggedemployee`);
    let employee = await httpResponse.json();
    
    if (employee.employeeId == 0) {
        document.getElementById("info").innerHTML = "<h2>LOGIN CREDENTIALS FAILED!<h2><br> Returning to home page!";
        setTimeout(function(){window.open("index.html","_self");},3000);
    }
    else {
        let httpResponse2 = await fetch(`http://${window.location.hostname}:8080/Project1ExpenseReimbursementSystemServer/api/getemployeereimbursements`);
        let reimbursements = await httpResponse2.json();
        let httpResponse3 = await fetch(`http://${window.location.hostname}:8080/Project1ExpenseReimbursementSystemServer/api/getemployeemanager`);
        let manager = await httpResponse3.json();
        employee.division = manager.division;
        document.getElementById("username").innerHTML = document.getElementById("username").innerHTML + `${employee.userName}`;
        document.getElementById("id").innerHTML = document.getElementById("id").innerHTML + `${employee.employeeId}`;
        document.getElementById("full_name").innerHTML = document.getElementById("full_name").innerHTML + `${employee.firstName} ${employee.lastName}`;
        document.getElementById("division").innerHTML = document.getElementById("division").innerHTML + `${employee.division}`;
        document.getElementById("balance").innerHTML = document.getElementById("balance").innerHTML + `${employee.balance} Gil`;
        document.getElementById("reimburse").innerHTML = document.getElementById("reimburse").innerHTML + `${reimbursements.length}`;
        console.log(employee);
        console.log(reimbursements);
        console.log(manager);
    }
}

async function viewAllReimbursements(){
    let httpResponse2 = await fetch(`http://${window.location.hostname}:8080/Project1ExpenseReimbursementSystemServer/api/getsessionreimbursements`);
    let reimbursements = await httpResponse2.json();
    document.getElementById("list").innerHTML = "<thead><th>ID</th><th>Category</th><th>Requested</th><th>Status</th></thead>";
    for (i = 0; i < reimbursements.length; i++) {
        document.getElementById("list").innerHTML = document.getElementById("list").innerHTML +
        `<tr id="${i}"><td>${reimbursements[i].reimbursementId}</td><td>${reimbursements[i].category}</td><td>${reimbursements[i].amountRequested}</td><td>${reimbursements[i].status}</td></tr>`;
    }
    console.log("6");
    console.log(reimbursements.length);
}

async function viewPendingReimbursements(){
    let httpResponse2 = await fetch(`http://${window.location.hostname}:8080/Project1ExpenseReimbursementSystemServer/api/getsessionreimbursements`);
    let reimbursements = await httpResponse2.json();
    document.getElementById("list").innerHTML = "<thead><th>ID</th><th>Category</th><th>Requested</th><th>Status</th></thead>";
    for (i = 0; i < reimbursements.length; i++) {
        if (reimbursements[i].status == "Pending") {
        document.getElementById("list").innerHTML = document.getElementById("list").innerHTML +
        `<tr id="${i}"><td>${reimbursements[i].reimbursementId}</td><td>${reimbursements[i].category}</td><td>${reimbursements[i].amountRequested}</td><td>${reimbursements[i].status}</td></tr>`;
    }}
    console.log("6");
    console.log(reimbursements.length);
}

function setTime(){
    let time = new Date();
    let hours = ("0" + time.getHours()).slice(-2);
    let minutes = ("0" + time.getMinutes()).slice(-2);
    let seconds = ("0" + time.getSeconds()).slice(-2);
    let merid  = hours >= 12 ? "PM" : "AM";
    hours = hours % 12 ? hours % 12 : 12;
    document.getElementById("time").innerHTML = `<br>${hours}:${minutes}:${seconds} ${merid}<br>${time.getMonth()} - ${time.getDay()} - ${time.getFullYear()}`;
}
setInterval(setTime,1000);