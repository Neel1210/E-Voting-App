

let username,password,cpassword,city,address,adhar,email,mobile,gender;
function addUser()
{
    username=$("#username").val();
    password=$("#password").val();
    cpassword=$("#cpassword").val();
    city=$("#city").val();
    address=$("#address").val();
    adhar=$("#adhar").val();
    email=$("#email").val();
    mobile=$("#mobile").val();
    gender=$('input[name="gender"]:checked').val();
    if(validateUser())
    {
      if(password!==cpassword)
      {
          swal("Error!","Password do not match!","error");
          return;
      }
      else
      {
          if(checkEmail()===false)
              return;
          
          let data={
              username:username,
              password:password,
              city:city,
              address:address,
              userid:adhar,
              email:email,
              mobile:mobile,
              gender:gender
          };
          alert(gender);
          console.log("Gender is:"+gender);
          let xhr=$.post("RegistrationControllerServlet",data,processResponse);
          xhr.fail(handleError);
      }
    }
}
function processResponse(responseText,textStatus,xhr)
{
    let str=responseText.trim();
    if(str==="Seccess"){
        
        swal("Success!","Registration Successfully Done!you can now login.","success");
        setTimeout(redirectUser,3000);
    }
    else if(str==="uap")
        swal(str,"Sorry! the userid is already present","error!");
    else
        swal(str,"Registration Failed! Try again","error");
}
function handleError(xhr)
{
   swal("Error!","Problem in server communication:"+xhr.statusText,"error"); 
}
function validateUser()
{
    if(username===""||password===""||cpassword===""||city===""||address===""||adhar===""
            ||email===""||mobile===""){
        swal("Error","All Fields are mandatory","error");
        return false;
    }
    return true;
}
function checkEmail()
{
    let attheratepos=email.indexOf("@");
   let dotpos=email.indexOf(".");
    if(attheratepos<1 || dotpos<attheratepos+2 || dotpos+2>=email.length)
    {
     swal("Error!","Please enter a valid Email!","error");
     return false;
    }
    return true;
}
function redirectUser()
{
  window.location="login.html"; 
}