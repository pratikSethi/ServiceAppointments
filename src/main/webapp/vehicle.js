/**
 * Created by agrawaay on 9/1/2016.
 */
function logout() {
    $.ajax({
        type:"post",
        url:"/logout.do",
        success: function (result) {
            window.history.forward(-1);
            window.location.replace("/login.html");

        },
        error:function (result) {
            alert("Problem in Logging out");
            window.location.replace("login.html");
        }
    })
}
function showRecords() {

    $.ajax({
        type: "post",
        url:"/customerVehicleList.do",
        dataType:"text",
        success: function(response){
            if(typeof records === null) {
                window.location.replace("/login.html");
                return;
            }
            records=JSON.parse(response);
            var htmlText = "<h2>Select Service Type</h2>" +
                "<input type='radio' name='service' value='regular'>Regular Service" +
                "<input type='radio' name='service' value='premium'>Regular Service" +
                "<input type='radio' name='service' value='repair'>Repairing" +
                "<table class='table' id='myTable'>" +
                "<thread>" +
                "<tr><th><h4>Select</h4></th>" +
                "<th><h4>Vehicle VIN</h4></th>" +
                "<th><h4>Model</h4></th>" +
                "<th><h4>Company</h4></th>" +
                "<th><h4>Status</h4></th>" +
                "</tr>" +
                "</thread>" +
                "<tbody>";
            for(var i = 0; i < records.Vehicles.length;i++){
                htmlText += "<tr><td><input type='radio' id='"+records.Vehicles[i].vin+"' value='"+records.Vehicles[i].vin+"' name = 'vin' >"+
                    "</td><td>"+records.Vehicles[i].vin+
                    "</td><td>" + records.Vehicles[i].model +
                    "</td><td>" + records.Vehicles[i].company+
                    "</td><td>" + records.Vehicles[i].status+
                    "</td></tr>";
                if(records.Vehicles[i].status.localeCompare("done")==0){
                    $('input[name="'+records.Vehicles[i].vin+'"]').attr('disabled', 'disabled');
                }
                else
                    $('input[name="'+records.Vehicles[i].vin+'"]').attr('disabled', 'disabled');
            }

            htmlText +="</tbody>" + "</table>";
            htmlText+="<label>Select Date <input type='Date' id='selectDate' placeholder='Click here'></label><br>";
            htmlText +='<button type="button" id="select" class="btn btn-primary" onclick="selectCar();" style="margin-bottom: 2.5%" value="">Submit</button>';

            document.getElementById("showData").innerHTML = htmlText;

        },
        error: function(response){
            document.getElementById("showData").innerHTML = "<h2>No records to show or something went wrong</h2>";
        }
    });


}

function displayForm() {
    var vehicleForm = document.getElementById("vehicleForm");
    vehicleForm.style.visibility = "visible"
    document.getElementById("done").style.visibility = "visible";
}

function addRecord() {
   var formData = new FormData(document.vehicleForm);
    $.ajax({
        type: "post",
        url:"/addVehicle.do",
        data:formData,
        processData: false,
        contentType: false,
        dataType:"json",
        success: function(response){
            if(typeof response === null) {
                window.location.replace("/login.html");
                return;
            }
            document.getElementById("showData").innerHTML += "<h2>Vehicle Successfully added to list</h2>";
        },
        error: function(response){
            document.getElementById("showData").innerHTML += "<h2>Please check details</h2>";
        }
    });

}
function selectCar(){
    var val = $('input[name=service]:checked').val();
    if (val.localeCompare("regular")==0||val.localeCompare("premium")==0||val.localeCompare("repair")==0){
        $.ajax({
            type: "get",
            url:"/addVehicleForService.do?vin="+$('input[name=vin]:checked').val()+"&dateOfServicing="+$("#selectDate").val()+"&type="+val,
            dataType:"text",
            success: function(response){
                if(response === null){
                    window.location.replace("/login.html");
                    return;
                }
                $("#showData").html("Successfully register");
            },
            error: function(response){
                $("#showData").html("Slot Full pls select another");
            }
        });
    }
    else{
        $("#showData").html("Pls select service");
    }

}

function checkAvailable(){
    $.ajax({
            type: "get",
            url:"/checkAvailable.do?date="+$("#checkAvailable").val(),
            dataType:"text",
            success: function(response){
                if(response === null){
                    window.location.replace("/login.html");
                    return;
                }
                $("#showData").html(response);
            },
            error: function(response){
                $("#showData").html("Something went wrong");
            }

    });
}