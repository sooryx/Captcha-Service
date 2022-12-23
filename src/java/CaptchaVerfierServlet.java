package java_code_house.jcaptcha;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.octo.captcha.service.CaptchaServiceException;

@WebServlet("/captcha-verifier")
public class CaptchaVerifierServlet extends HttpServlet {
 private static final long serialVersionUID = 1L;

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  // Get the request params
  String cId = request.getParameter("captchaID");
  String inChars = request.getParameter("inChars");

   // Validate whether input from user is correct
  boolean hasPassed = validateCaptcha(cId, inChars );
  
  // Set flag into session
  request.getSession().setAttribute( "result", new Boolean(hasPassed) );

   // Forward request to results page
  request.getRequestDispatcher( "/results.jsp" ).forward( request, response );
 }

  private boolean validateCaptcha( String captchaId, String inputChars ){
  boolean b = false;
  try{
   b = CaptchaService.getInstance().validateResponseForID( captchaId, inputChars );
  }catch( CaptchaServiceException cse ){}
  return b;
 }

}

