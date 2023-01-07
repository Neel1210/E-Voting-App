function DeleteUser(){
    removecandidateForm();
   console.log("delete user form start");
   var newdiv=document.createElement("div");
   newdiv.setAttribute("id","candidateform");
   newdiv.setAttribute("float","left");
   newdiv.setAttribute("padding-left","12px");
   newdiv.setAttribute("border","solid 2px red");
   newdiv.innerHTML="<h3>Delete Candidate</h3>";
   newdiv.innerHTML=newdiv.innerHTML+"<form method='POST' enctype='multipart/form-data' id='fileUploadForm'>>\n\
<table id='table'><tr><th>Adhar No/User ID:</th><td><select id='cid' onchange='show()'></select></td></tr>\n\
<tr><th><label for='name'>UserName</label></th><td><input type='text' id='name'></td></tr>\n\
<tr><th><label for='address'>Address </label></th><td><input type='text' id='address'></td></tr>\n\
<tr><th><label for='city'>city </label></th><td><input type='text' id='city'></td></tr>\n\
\n\<tr><th><label for='mail'>mail </label></th><td><input type='text' id='mail'></td></tr>\n\
\n\<tr><th><label for='mno'>mobile no</label></th><td><input type='text' id='mno'></td></tr>\n\
<tr><th><input type='button' value='Delete user' onclick='deleteUser()' id='addcnd'></th>\n\
<th><input type='reset' value='Clear' onclick='clearText()'></th></tr></table></form>";
newdiv.innerHTML=newdiv.innerHTML+"<br><span id='addresp'></span>";
console.log("delete candidate form end");
var addcand=$("#result")[0];
addcand.appendChild(newdiv);
$("#candidateform").hide();
$("#candidateform").fadeIn("3500");
data={id:"cid"};
$.post("DeleteUserServlet",data,function(responseText){
    console.log("response text is "+responseText)
    var details=JSON.parse(responseText);
            let cid=details.cid;
            console.log(cid);
            let id=$("#cid");
            console.log("candidate id"+id);
            $("#cid").append(cid);
            console.log("before "+id);
});
}
function show(){
    let select=$("#cid").val();
console.log("value of selected item "+select)
if(select=='select value'){
    swal("Error","plzz select candidate id","error");
    return;
}
data={id:select};
   $.post("DeleteUserServlet",data,function(responseText){
       var user=JSON.parse(responseText); 
       $("#name").val(user.name);
       $("#city").val(user.city);
       $("#address").val(user.address);
       $("#mail").val(user.mail);
       $("#mno").val(user.mno);
   });
    
}
function deleteUser(){
     let select=$("#cid").val();
console.log("value of selected item "+select)
if(select=='select value'){
    swal("Error","plzz select candidate id","error");
    return;
}
data={id:select};
 $.post("DeleteUser",data,function(responseText){
     console.log(responseText)
      if(responseText.trim()=="success"){
            console.log("success")
                swal("Admin!","User deleted sucessfully", "success").then((value)=>{
                     deleteUser();
                });                   
            }
            else{
                 console.log("fail")
                 swal("Admin!","user not Deleted", "error")
            }
 });

}
function removecandidateForm()
{
    var addcand=document.getElementById("data");
    addcand.innerHTML="";
    var contdiv=document.getElementById("result");
    var formdiv=document.getElementById("candidateform");
    if(formdiv!==null)
    {
        $("#candidateform").fadeOut("3500");
        contdiv.removeChild(formdiv);   
    }   
}

