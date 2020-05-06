//-------------EVENT LISTENERS-------------//
window.addEventListener("load", function(){
    getLoggedManager();
    setTime();
});
document.getElementById("view_pending_btn").addEventListener("click", function(){
    document.getElementById("options").style.visibility = "hidden";
    document.getElementById("info").style.visibility = "hidden";
    document.getElementById("reimbursements").style.visibility = "visible";
    buttonSound.play();
    document.getElementById("location").innerHTML = "Your Pending Requests";
    viewPendingByManager();
});
document.getElementById("view_div_btn").addEventListener("click", function(){
    document.getElementById("options").style.visibility = "hidden";
    document.getElementById("info").style.visibility = "hidden";
    document.getElementById("reimbursements").style.visibility = "visible";
    document.getElementById("location").innerHTML = "Division Requests";
    buttonSound.play();
    viewPendingByDivision();
});
document.getElementById("back_btn_1").addEventListener("click", function(){
    window.open("managerhome.html","_self");
});
document.getElementById("back_btn_2").addEventListener("click", function(){
    document.getElementById("reimbursements").style.visibility = "visible";
    document.getElementById("details").style.visibility = "hidden";
    document.getElementById("location").innerHTML = "Requests Page";
    backSound.play();
});
document.getElementById("settle_btn").addEventListener("click", function(){
    settleReimbursement();
    successSound.play();
});
document.getElementById("exit_btn").addEventListener("click", function(){
    clearSession();
    setTimeout(function(){window.open("index.html","_self");},500);
});

//----------------FUNCTIONS----------------//
async function getLoggedManager() {

    let httpResponse = await fetch(`http://${window.location.hostname}:8080/Project1ExpenseReimbursementSystemServer/api/getloggedmanager`);
    let manager = await httpResponse.json();
    
    if (manager.managerId == 0) {
        document.getElementById("info").innerHTML = "<h2>LOGIN CREDENTIALS FAILED!<h2><br> Returning to home page!";
        errorSound.play();
        
        setTimeout(function(){window.open("index.html","_self");},3000);
    }
    else {
        console.log(manager);
        let httpResponse2 = await fetch(`http://${window.location.hostname}:8080/Project1ExpenseReimbursementSystemServer/api/getmanageremployees`);
        let employees = await httpResponse2.json();
        console.log(employees);
        document.getElementById("username").innerHTML = document.getElementById("username").innerHTML + manager.username;
        // document.getElementById("id").innerHTML = document.getElementById("id").innerHTML + manager.managerId;
        document.getElementById("full_name").innerHTML = document.getElementById("full_name").innerHTML + manager.firstName + " " + manager.lastName;
        document.getElementById("division").innerHTML = document.getElementById("division").innerHTML + manager.division;
        document.getElementById("employee_count").innerHTML = document.getElementById("employee_count").innerHTML + employees.length;
        document.getElementById("avatar").src = `${manager.firstName}${manager.lastName}.png`;
    }
}

async function viewPendingByManager(){
    let httpResponse1 = await fetch(`http://${window.location.hostname}:8080/Project1ExpenseReimbursementSystemServer/api/getmanagerreimbursements`);
    let reimbursements = await httpResponse1.json();
    document.getElementById("list").innerHTML = "<thead><th>Det.</th><th>Category</th><th>Requested</th><th>Status</th></thead>";
    for (i = 0; i < reimbursements.length; i++) {
        if (reimbursements[i].status == "Pending") {
        document.getElementById("list").innerHTML = document.getElementById("list").innerHTML +
        `<tr><td><button id="${i}" class="reimbursement" onclick="viewDetailsManager(${i})">...</button></td><td>${reimbursements[i].category}</td><td>${reimbursements[i].amountRequested}</td><td>${reimbursements[i].status}</td></tr>`;
    }}
}

async function viewPendingByDivision(){
    let httpResponse1 = await fetch(`http://${window.location.hostname}:8080/Project1ExpenseReimbursementSystemServer/api/getdivisionreimbursements`);
    let reimbursements = await httpResponse1.json();
    document.getElementById("list").innerHTML = "<thead><th>Det.</th><th>Category</th><th>Requested</th><th>Status</th></thead>";
    for (i = 0; i < reimbursements.length; i++) {
        if (reimbursements[i].status == "Pending") {
        document.getElementById("list").innerHTML = document.getElementById("list").innerHTML +
        `<tr><td><button id="${i}" class="reimbursement" onclick="viewDetailsDivision(${i})">...</button></td><td>${reimbursements[i].category}</td><td>${reimbursements[i].amountRequested}</td><td>${reimbursements[i].status}</td></tr>`;
    }}
}

async function viewDetailsManager(i){
    document.getElementById("reimbursements").style.visibility = "hidden";
    document.getElementById("details").style.visibility = "visible";
    document.getElementById("location").innerHTML = "Settle Requests Page";
    let httpResponse1 = await fetch(`http://${window.location.hostname}:8080/Project1ExpenseReimbursementSystemServer/api/getreimbursementfrommanager?index=${i}`);
    let reimbursement = await httpResponse1.json();
    let httpResponse2 = await fetch(`http://${window.location.hostname}:8080/Project1ExpenseReimbursementSystemServer/api/getemployee?id=${reimbursement.employeeId}`);
    let employee = await httpResponse2.json();
    document.getElementById("emp_name").innerHTML = employee.firstName + " " + employee.lastName;
    document.getElementById("reimb_date").innerHTML = reimbursement.date;
    document.getElementById("reimb_category").innerHTML = reimbursement.category;
    document.getElementById("reimb_requested").innerHTML = reimbursement.amountRequested;
    document.getElementById("reimb_detail1").innerHTML = reimbursement.details.substring(0,200);
}

async function viewDetailsDivision(i){
    document.getElementById("reimbursements").style.visibility = "hidden";
    document.getElementById("details").style.visibility = "visible";
    document.getElementById("location").innerHTML = "Settle Requests Page";
    let httpResponse1 = await fetch(`http://${window.location.hostname}:8080/Project1ExpenseReimbursementSystemServer/api/getreimbursementfromdivision?index=${i}`);
    let reimbursement = await httpResponse1.json();
    let httpResponse2 = await fetch(`http://${window.location.hostname}:8080/Project1ExpenseReimbursementSystemServer/api/getemployee?id=${reimbursement.employeeId}`);
    let employee = await httpResponse2.json();
    document.getElementById("emp_name").innerHTML = employee.firstName + " " + employee.lastName;
    document.getElementById("reimb_date").innerHTML = reimbursement.date;
    document.getElementById("reimb_category").innerHTML = reimbursement.category;
    document.getElementById("reimb_requested").innerHTML = reimbursement.amountRequested;
    document.getElementById("reimb_detail1").innerHTML = reimbursement.details.substring(0,200);
}

async function settleReimbursement(){
    let amount = document.getElementById("reimb_pay").value;
    let details = document.getElementById("reimb_detail2").value.replace(/[ .!?]/g,"_");
    let httpResponse1 = await fetch(`http://${window.location.hostname}:8080/Project1ExpenseReimbursementSystemServer/api/settlereimbursement?amount=${amount}&details=${details}`);
    let reimb2 = await httpResponse1.json();
    window.open("managerhome.html","_self");
}

async function clearSession(){
    let httpResponse = await fetch(`http://${window.location.hostname}:8080/Project1ExpenseReimbursementSystemServer/api/clearsession`);
    let res = await httpResponse;
    console.log(res);
}

//---------OTHER FUNCTIONALITIES----------//
function setTime(){
    let time = new Date();
    let hours = ("0" + time.getHours()).slice(-2);
    let minutes = ("0" + time.getMinutes()).slice(-2);
    let seconds = ("0" + time.getSeconds()).slice(-2);
    let merid  = hours >= 12 ? "PM" : "AM";
    hours = hours % 12 ? hours % 12 : 12;
    document.getElementById("time").innerHTML = `<br>${hours}:${minutes}:${seconds} ${merid}<br>${time.getMonth()+1} - ${time.getDate()} - ${time.getFullYear()}`;
}
setInterval(setTime,1000);
let backSound = new Audio("back.mp3");
let buttonSound = new Audio("button.mp3");
let cursorSound = new Audio("cursor.mp3");
let errorSound = new Audio("error.mp3");
let successSound = new Audio("login.mp3");
window.addEventListener("keydown", function(){cursorSound.play();});
window.addEventListener("load", function(){backSound.play();});