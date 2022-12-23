<!DOCTYPE html>
<html>
<head>
<title>JCaptcha Demo</title>
</head>
<body>
   <h2>JCaptcha Demo</h2>
   <form action="captcha-verifier" method="post">
      <input type="hidden" name="captchaID" value="<%= session.getId() %>"/>
      <table><tr>
           <td valign="middle">Enter these letters:<br/>
           <img src="./captcha-generator" align="middle" alt="Enter the 
             characters appearing in this image" border="1"/></td>
           <td><input type="text" name="inChars"/></td>
         </tr>
         <tr>
           <td><input type="submit" value="Submit"/></td>
         </tr>
      </table>
   </form>
</body>
</html>