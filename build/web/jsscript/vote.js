function addvote()
{
   var id=$('input[type=radio][name=flat]:checked').attr('id');
   data={candidateid:id};
   $.post("AddVoteControllerServlet",data,processresponse);
 }
function processresponse(responseText)
{
   responseText=responseText.trim();
   if(responseText==="success")
   {
       swal("Success","Voting Done","success").then((value)=>{
             window.location="votingresponse.jsp";
       });
   }
   else
       swal("Failure","voting Failed","error").then((value)=>{

           window.location="failure.jsp";
       });
}
