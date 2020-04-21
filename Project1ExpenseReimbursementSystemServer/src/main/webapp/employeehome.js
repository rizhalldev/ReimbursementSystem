window.addEventListener("load", function(){
    getLoggedEmployee();
    adjustTime();
})

async function getLoggedEmployee() {

    let httpResponse = await fetch("http://ec2-3-88-205-173.compute-1.amazonaws.com:8080/Project1ExpenseReimbursementSystemServer/api/getloggedemployee");
    let employee = await httpResponse.json();
    
    if (employee.employeeId == 0) {
        document.getElementById("info").innerHTML = "<h2>LOGIN CREDENTIALS FAILED!<h2><br> Returning to home page!";
        setTimeout(function(){window.open("index.html","_self");},3000);
    }
    else {
        let httpResponse2 = await fetch("http://ec2-3-88-205-173.compute-1.amazonaws.com:8080/Project1ExpenseReimbursementSystemServer/api/getemployeereimbursements");
        let reimbursements = await httpResponse2.json();
        console.log(reimbursements);
        document.getElementById("username").innerHTML = document.getElementById("username").innerHTML + `${employee.userName}`;
        document.getElementById("id").innerHTML = document.getElementById("id").innerHTML + `${employee.employeeId}`;
        document.getElementById("full_name").innerHTML = document.getElementById("full_name").innerHTML + `${employee.firstName} ${employee.lastName}`;
        // document.getElementById("division").innerHTML = document.getElementById("division").innerHTML + `${employee.division}`;
        document.getElementById("balance").innerHTML = document.getElementById("balance").innerHTML + `${employee.balance} Gil`;
        document.getElementById("reimburse").innerHTML = document.getElementById("reimburse").innerHTML + `${reimbursements.length}`;
        
    }
}

function adjustTime(){
    let time = new Date();
    let hours = ("0" + time.getHours()).slice(-2);
    let minutes = ("0" + time.getMinutes()).slice(-2);
    let seconds = ("0" + time.getSeconds()).slice(-2);
    let merid  = hours >= 12 ? "PM" : "AM";
    hours = hours % 12 ? hours % 12 : 12;
    document.getElementById("time").innerHTML = `<br>${hours}:${minutes}:${seconds} ${merid}<br>${time.getMonth()} - ${time.getDay()} - ${time.getFullYear()}`;
}

setInterval(adjustTime,1000);