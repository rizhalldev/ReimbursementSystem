//--------------EVENT LISTENERS--------------//
window.addEventListener("load", function(){
    getLoggedEmployee();
    setTime();
});
document.getElementById("view_btn").addEventListener("click", function(){
    document.getElementById("options").style.visibility = "hidden";
    document.getElementById("sort_options").style.visibility = "visible";
    document.getElementById("info").style.visibility = "hidden";
    document.getElementById("reimbursements").style.visibility = "visible";
    document.getElementById("location").innerHTML = "Reimbursements Page";
    buttonSound.play();
    viewAllReimbursements();
});
document.getElementById("back_btn").addEventListener("click", function(){
    window.open("employeehome.html","_self");
});
document.getElementById("pending_btn").addEventListener("click", function(){ viewStatusReimbursements("Pending"); buttonSound.play();});
document.getElementById("granted_btn").addEventListener("click", function(){ viewStatusReimbursements("Granted"); buttonSound.play();});
document.getElementById("partial_btn").addEventListener("click", function(){ viewStatusReimbursements("Partial"); buttonSound.play();});
document.getElementById("declined_btn").addEventListener("click", function(){ viewStatusReimbursements("Declined"); buttonSound.play();});
document.getElementById("all_btn").addEventListener("click", viewAllReimbursements);
document.getElementById("detail_back_btn").addEventListener("click", function(){
    document.getElementById("cancel_btn").style.visibility = "hidden";
    document.getElementById("details").style.visibility = "hidden";
    document.getElementById("sort_options").style.visibility = "visible";
    backSound.play();
    document.getElementById("location").innerHTML = "Reimbursements Page";
});
document.getElementById("cancel_btn").addEventListener("click", function(){
    cancelReimbursement();
    successSound.play();
    viewAllReimbursements();
});
document.getElementById("create_btn").addEventListener("click", function(){
    document.getElementById("options").style.visibility = "hidden";
    // document.getElementById("info").style.visibility = "hidden";
    document.getElementById("creator").style.visibility = "visible";
    document.getElementById("location").innerHTML = "Request Form Page"
    buttonSound.play();
});
document.getElementById("form_back_btn").addEventListener("click", function(){
    window.open("employeehome.html","_self");
});
document.getElementById("submit_btn").addEventListener("click", function(){
    successSound.play();
    submitRequest();
});
document.getElementById("exit_btn").addEventListener("click", function(){
    clearSession();
    setTimeout(function(){window.open("index.html","_self");},500);
});

//---------------FUNCTIONS---------------//
async function getLoggedEmployee() {

    let httpResponse = await fetch(`http://${window.location.hostname}:8080/Project1ExpenseReimbursementSystemServer/api/getloggedemployee`);
    let employee = await httpResponse.json();
    
    if (employee.employeeId == 0) {
        document.getElementById("info").innerHTML = "<h2>LOGIN CREDENTIALS FAILED!<h2><br> Returning to home page!";
        errorSound.play();
        setTimeout(function(){window.open("index.html","_self");},3000);
    }
    else {
        let httpResponse2 = await fetch(`http://${window.location.hostname}:8080/Project1ExpenseReimbursementSystemServer/api/getemployeereimbursements`);
        let reimbursements = await httpResponse2.json();
        let httpResponse3 = await fetch(`http://${window.location.hostname}:8080/Project1ExpenseReimbursementSystemServer/api/getemployeemanager`);
        let manager = await httpResponse3.json();
        employee.division = manager.division;
        document.getElementById("username").innerHTML = document.getElementById("username").innerHTML + `${employee.userName}`;
        // document.getElementById("id").innerHTML = document.getElementById("id").innerHTML + `${employee.employeeId}`;
        document.getElementById("full_name").innerHTML = document.getElementById("full_name").innerHTML + `${employee.firstName} ${employee.lastName}`;
        document.getElementById("division").innerHTML = document.getElementById("division").innerHTML + `${employee.division}`;
        document.getElementById("balance").innerHTML = document.getElementById("balance").innerHTML + `${employee.balance} Gil`;
        document.getElementById("reimburse").innerHTML = document.getElementById("reimburse").innerHTML + `${reimbursements.length}`;
        document.getElementById("avatar").src = `${employee.firstName}${employee.lastName}.png`;
    }
}

async function viewAllReimbursements(){
    let httpResponse2 = await fetch(`http://${window.location.hostname}:8080/Project1ExpenseReimbursementSystemServer/api/getemployeereimbursements`);
    let reimbursements = await httpResponse2.json();
    document.getElementById("list").innerHTML = "<thead><th>Det.</th><th>Category</th><th>Requested</th><th>Status</th></thead>";
    for (i = 0; i < reimbursements.length; i++) {
        document.getElementById("list").innerHTML = document.getElementById("list").innerHTML +
        `<tr><td><button id="${i}" class="reimbursement" onclick="viewDetails(${i})">...</button></td><td>${reimbursements[i].category}</td><td>${reimbursements[i].amountRequested}</td><td>${reimbursements[i].status}</td></tr>`;
    }
    console.log("6");
    console.log(reimbursements.length);
}

async function viewStatusReimbursements(aStatus){
    let httpResponse2 = await fetch(`http://${window.location.hostname}:8080/Project1ExpenseReimbursementSystemServer/api/getemployeereimbursements`);
    let reimbursements = await httpResponse2.json();
    document.getElementById("list").innerHTML = "<thead><th>Det.</th><th>Category</th><th>Requested</th><th>Status</th></thead>";
    for (i = 0; i < reimbursements.length; i++) {
        if (reimbursements[i].status == aStatus) {
        document.getElementById("list").innerHTML = document.getElementById("list").innerHTML +
        `<tr><td><button id="${i}" class="reimbursement" onclick="viewDetails(${i})">...</button></td><td>${reimbursements[i].category}</td><td>${reimbursements[i].amountRequested}</td><td>${reimbursements[i].status}</td></tr>`;
    }}
    console.log("6");
    console.log(reimbursements.length);
}

async function viewDetails(i){
    console.log(i);
    document.getElementById("details").style.visibility = "visible";
    document.getElementById("sort_options").style.visibility = "hidden";
    document.getElementById("location").innerHTML = "Reimb. Details Page";
    let httpResponse2 = await fetch(`http://${window.location.hostname}:8080/Project1ExpenseReimbursementSystemServer/api/getsessionreimbursements`);
    let reimbursements = await httpResponse2.json();
    let theReimbursement = reimbursements[i];
    if (theReimbursement.status == "Pending")
        document.getElementById("cancel_btn").style.visibility = "visible";
    document.getElementById("reimb_id").innerHTML = theReimbursement.reimbursementId;
    document.getElementById("reimb_category").innerHTML = theReimbursement.category;
    document.getElementById("reimb_date").innerHTML = theReimbursement.date;
    document.getElementById("reimb_status").innerHTML = theReimbursement.status;
    document.getElementById("reimb_requested").innerHTML = theReimbursement.amountRequested;
    document.getElementById("reimb_paid").innerHTML = theReimbursement.amountPaid;
    if (theReimbursement.details.length > 0)
        document.getElementById("reimb_detail1").innerHTML = theReimbursement.details.substring(0,200);
    if (theReimbursement.details.length > 200)
        document.getElementById("reimb_detail2").innerHTML = theReimbursement.details.substring(200);
}

async function cancelReimbursement(){
    let anId = document.getElementById("reimb_id").innerHTML;
    let req = `http://${window.location.hostname}:8080/Project1ExpenseReimbursementSystemServer/api/cancelreimbursement?id=${document.getElementById("reimb_id").innerHTML}`;
    let httpResponse = await fetch(req);
    let reimb = await httpResponse.json();
    document.getElementById("cancel_btn").style.visibility = "hidden";
    document.getElementById("details").style.visibility = "hidden";
    document.getElementById("sort_options").style.visibility = "visible";
}

async function submitRequest(){
    let category = document.getElementById("category_select").value;
    let amount = document.getElementById("amount_input").value;
    let details = document.getElementById("comment_input").value.replace(/[ .!?]/g,"_");
    let request = `http://${window.location.hostname}:8080/Project1ExpenseReimbursementSystemServer/api/submitrequest?amount=${amount}&category=${category}&details=${details}`;
    console.log(request);
    let httpResponse = await fetch(request);
    let reimb = await httpResponse.json();
    window.open("employeehome.html","_self");
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