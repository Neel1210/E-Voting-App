function showupdatecandidateform(){
     removecandidateForm();
   console.log("show candidate form start");
   var newdiv=document.createElement("div");
   newdiv.setAttribute("id","candidateform");
   newdiv.setAttribute("float","left");
   newdiv.setAttribute("padding-left","12px");
   newdiv.setAttribute("border","solid 2px red");
   newdiv.innerHTML="<h3>Update Candidate</h3>";
   newdiv.innerHTML=newdiv.innerHTML+"<form method='POST' enctype='multipart/form-data' id='fileUploadForm'>>\n\
<table><tr><th>Candidate ID:</th><td><select id='cid' onchange='show()'></select></td></tr>\n\
<tr><th><label for='name'>UserName</label></th><td><input type='text' id='name'></td></tr>\n\
<tr><th><label for='party'>party  </label></th><td><input type='text' id='party'></td></tr>\n\
<tr><th><label for='city'>city  </label></th><td><input type='text' id='city'></td></tr>\n\
<tr><th><label for='image'>image </label></th><td><div id='image' style='width:300px;height:200px;border:solid 2px red'></div>\n\
<tr><td colspan='2'><input type='file' name='files' value='Select Image'></td></tr>\n\
<tr><th><input type='button' value='Update Candidate' onclick='updateCandidate()' id='addcnd'></th>\n\
<th><input type='reset' value='Clear' onclick='clearText()'></th></tr></table></form>";
newdiv.innerHTML=newdiv.innerHTML+"<br><span id='addresp'></span>";
console.log("show candidate form end");
var addcand=$("#result")[0];
addcand.appendChild(newdiv);
$("#candidateform").hide();
$("#candidateform").fadeIn("3500");
data={id:"cid"};
$.post("UpdateServlet",data,function(responseText){
    console.log("response text is "+responseText);
    var details=JSON.parse(responseText);
            let cid=details.cid;
            console.log(cid);
            let id=$("#cid");
            console.log("candidate id"+id);
            $("#cid").append(cid);
             console.log("before "+id);
});
}

function showAllCandidate(){
    swal("success","all candidtes ..","success");
}
function show(){
let select=$("#cid").val();
console.log("value of selected item "+select);
if(select==='select value'){
    swal("Error","plzz select candidate id","error");
    return;
}
data={id:select};
$.post("UpdateServlet",data,function(responseText){
    console.log("responseText in show "+responseText);
     var details=JSON.parse(responseText);
     var name=details.name;
     var city=details.city;
     var party=details.party;
     var src=details.src;
     console.log("name is"+name);
     console.log(""+city);
     console.log(party);
     
    var username=document.getElementById("name");
    var usercity=document.getElementById("city");
    var userparty=document.getElementById("party");
    var img=document.getElementById("image");
    username.value=name;
    usercity.value=city;
    userparty.value=party;
    img.innerHTML=src;
   
});

}

function removecandidateForm()
{
    var addcand=document.getElementById("data");
    //addcand.innerHTML="";
    var contdiv=document.getElementById("result");
    var formdiv=document.getElementById("candidateform");
    if(formdiv!==null)
    {
        $("#candidateform").fadeOut("3500");
        contdiv.removeChild(formdiv);   
    }
    
}
function updateCandidate(){
 var form = $('#fileUploadForm')[0];
    var data = new FormData(form);
    var cid=$("#cid").val();
    var cname=$("#name").val();
    var city=$("#city").val();
    var party=$("#party").val();
    data.append("cid",cid);
    data.append("cname",cname);
    data.append("party",party);
    data.append("city",city);
    $.ajax({
            type: "POST",
            enctype: 'multipart/form-data',
            url: "UpdateCandidateServlet",
            data: data,
            processData: false,
            contentType: false,
            cache: false,
            timeout: 600000,
            success: function (data) {
                str=data.trim();
                console.log("responseText in added "+str);
                if(str==="success"){
                swal("Admin!","candidate Updated sucessfully", "success").then((value)=>{
               showupdatecandidateform();
    });}
else{
    swal("Admin!","candidate not Updated", "error");
}
    },
error: function (e) {
swal("Admin!", e, "success");
}
           
    });
    }






