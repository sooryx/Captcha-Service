package java_code_house.jcaptcha;

import com.octo.captcha.service.CaptchaServiceException;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/captcha-generator")
public class CaptchaGeneratorServlet extends HttpServlet{
 private static final long serialVersionUID=1L;
 
 protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
  ByteArrayOutputStream imgStream = new ByteArrayOutputStream();
  byte[] captchaBytes=null;

   try{
   // Session ID is used to identify the particular captcha
   String captchaId = request.getSession().getId();
   // Generate the captcha image
   BufferedImage img = CaptchaService.getInstance().getImageChallengeForID(
     captchaId, request.getLocale() );
   ImageIO.write(img, "jpeg", imgStream );
   captchaBytes = imgStream.toByteArray();

    // Clear any existing flag
   request.getSession().removeAttribute("result");
  }catch( CaptchaServiceException|IOException ex ) {
   System.out.println(ex);
  }

   // Set appropriate http headers
  response.setHeader( "Cache-Control", "no-store" );
  response.setHeader( "Pragma", "no-cache" );
  response.setDateHeader( "Expires", 0 );
  response.setContentType( "image/jpeg");

   // Write the image to the client
  OutputStream os = response.getOutputStream();
  os.write(captchaBytes);
  os.flush();
  os.close();
 }

}
