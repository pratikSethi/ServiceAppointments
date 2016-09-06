function login() {
    var formData = new FormData(document.forms[0]);

    $.ajax({
        type: "post",
        url:"login.do",
        data:formData,
        processData: false,
        contentType: false,
        dataType:"json",
        success: function(response){
            if(response === null){
                window.location.replace("/login.html");
                return;
            }
            if(response.email == "admin@admin.com")
                window.location.replace("/adminView.html");
            else
            window.location.replace("/vehicleView.html");

        },
        error: function(response){
            $("#loginstatus").html("Invalid Details");
        }

    })

}

function register() {
    var formData = new FormData(document.forms[1]);
    $.ajax({
        type: "post",
        url:"add.do",
        data:formData,
        processData: false,
        contentType: false,
        dataType:"json",
        success: function(response){
            alert("Register successfully Please login to continue");
            window.location.replace("/login.html");
        },
        error: function(response){
            $("#registerStatus").html("Email id is alredy exist");
        }

    })

}
function getServiceVehicles(){
    var date = $("#date").val();
    $.ajax({
        type: "get",
        url:"/getDetailsByDate.do?date="+date,
        dataType:"text",
        success: function(response){
            if(typeof records === null) {
                window.location.replace("/login.html");
                return;
            }
            records=JSON.parse(response);
            var htmlText = "<table class='table myTable'><thead> <tr> <th>ServiceID</th> <th>Date Of Service</th> " +
                "<th>Service Type</th> <th>Status</th> " +
                "<th>Name</th> <th>Address</th><th>Update Status</th> </tr> </thead>";
            htmlText+="<tbody id='my-tbody'>";
            for(var i = 0; i < records.Vehicles.length;i++){
                htmlText += "<tr><td>"+records.Vehicles[i].serviceId +
                    "</td><td>" + records.Vehicles[i].dateOfService +
                    "</td><td>" + records.Vehicles[i].type+
                    "</td><td>" + records.Vehicles[i].status+
                    "</td><td>" + records.Vehicles[i].name+
                    "</td><td>" + records.Vehicles[i].address+
                    "</td><td><button type='button' onclick='update("+records.Vehicles[i].serviceId+");'>Update Status</button> "+
                    "</td></tr>";
            }

            htmlText +="</tbody>" + "</table>";
            document.getElementById("show").innerHTML = htmlText;

        },
        error: function(response){
            document.getElementById("show").innerHTML = "No records to show or something went wrong";
        }
    });

}
function update(serviceId){
    htmlText = "Service Id:<input type='text' id='service_id' value='"+serviceId+"'  readonly>";
    htmlText += "Select Status:<br> <input type='radio' name='status' value='done'>Done";
    htmlText +="<br> <input type='radio' name='status' value='inprocess'>In Process";
    htmlText += "<br> <input type='radio' name='status' value='not_start'>not start";
    htmlText +="<br> <input type='button' name='update' value='update' onclick='updateDetails();'>";
    document.getElementById("show").innerHTML = htmlText;
}
function updateDetails() {
    var service_id = $("#service_id").val();
    alert(service_id);
$.ajax({
    type:"get",
    url:"/updateStatus.do?sid="+service_id+"&status="+$('input[name=status]:checked').val(),
    dataType:"text",
    success:function (result) {
        document.getElementById("show").innerHTML = result;
    },
    error:function (result) {
        document.getElementById("show").innerHTML = "error";
    }
});
}