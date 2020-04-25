//-------------EVENT LISTENERS-------------//
window.addEventListener("load", function(){
    getLoggedManager();
    setTime();
});

//----------------FUNCTIONS----------------//
async function getLoggedManager() {

    let httpResponse = await fetch(`http://${window.location.hostname}:8080/Project1ExpenseReimbursementSystemServer/api/getloggedmanager`);
    let manager = await httpResponse.json();
    
    if (manager.managerId == 0) {
        document.getElementById("info").innerHTML = "<h2>LOGIN CREDENTIALS FAILED!<h2><br> Returning to home page!";
        setTimeout(function(){window.open("index.html","_self");},3000);
    }
    else {
        console.log(manager);
        let httpResponse2 = await fetch(`http://${window.location.hostname}:8080/Project1ExpenseReimbursementSystemServer/api/getmanageremployees`);
        let employees = await httpResponse2.json();
        console.log(employees);
        document.getElementById("username").innerHTML = document.getElementById("username").innerHTML + manager.username;
        document.getElementById("id").innerHTML = document.getElementById("id").innerHTML + manager.managerId;
        document.getElementById("full_name").innerHTML = document.getElementById("full_name").innerHTML + manager.firstName + " " + manager.lastName;
        document.getElementById("division").innerHTML = document.getElementById("division").innerHTML + manager.division;
        document.getElementById("employee_count").innerHTML = document.getElementById("employee_count").innerHTML + employees.length;
    }
}

//---------OTHER FUNCTIONALITIES----------//
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