function showUser(){
    console.log("result showing");
    $.post("ShowUser",function(responseText){
       var addcand=document.getElementById("data");
    console.log("add candidate"+addcand+"responseText:"+responseText);
    //addcand.innerHTML="";
    addcand.innerHTML=addcand.innerHTML+responseText;
    });
}


