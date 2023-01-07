function showCityWise(){
     
    console.log("result showing");
    $.post("ElectionResultControllerServlet",function(responseText){
    var addresult=document.getElementById("data");
    console.log("add candidate"+addresult);
   // addresult.innerHTML=" ";
    addresult.innerHTML=addresult.innerHTML+responseText;
    });
}
function showPartyWise(){
    console.log("result showing party wise");
    $.post("ElectionResult",function(responseText){
       var addresult=document.getElementById("data");
    console.log("add candidate"+addresult);
    addresult.innerHTML=addresult.innerHTML+responseText;
    });
}
function showGenderWise(){
    console.log("result showing party wise");
    $.post("ElectionResultByGender",function(responseText){
       var addresult=document.getElementById("data");
    console.log("add candidate"+addresult);
    //addresult.innerHTML="";
    addresult.innerHTML=addresult.innerHTML+responseText;
    console.log("Response is:"+responseText);
    });
}


