package com.feng.controller;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginLogoutController {

	@Resource(name="am")
	private AuthenticationManager authenticationManager;
	
	/**
	 * 指向登录页面
	 */
	@RequestMapping(value = "/login",method=RequestMethod.POST)
	public String getLoginPage(
			@RequestParam(value = "error", required = false) boolean error,
			@RequestParam(value = "username", required = false) String username,
			@RequestParam(value = "password", required = false) String password,
			HttpServletRequest request,
			ModelMap model) {


//		if (error == true) {
//			// Assign an error message
//			model.put("error",
//					"You have entered an invalid username or password!");
//		} else {
//			model.put("error", "");
//		}
		
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, password);
		request.getParameter("username");
		Authentication aut = authenticationManager.authenticate(authentication);
		boolean isSuccess = aut.isAuthenticated();
		if(isSuccess) {
			SecurityContextHolder.getContext().setAuthentication(authentication);
		    request.getSession().setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());
		    return "redirect:commonpage";
		}else {
			return "redirect:deniedpage";
		}
		
	  //  SecurityContext securityContext = (SecurityContext) request.getSession().getAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY);
	    //Authentication a = securityContext.getAuthentication();
		

	}
	
	@RequestMapping(value = "/commonpage")
	public String gotoCommonpage() {
		
		return "/commonpage";
	}
	/**
	 * 指向登录页面
	 */
	@RequestMapping(value = "/login2")
	public String getLoginPage2(ModelMap model) {
		System.out.println("------------------------");
		return "loginpage";

	}
	
	/**
	 * 指向登录页面
	 */
	@RequestMapping(value = "/gotoLogin")
	public String getLoginPage3(ModelMap model) {
		System.out.println("+++++++++++++++++++++++++++++++");
		return "loginpage";

	}
	
	/**
	 * 指向登录页面
	 */
	@RequestMapping(value = "/errorLogin")
	public String errorLogin(ModelMap model) {
		System.out.println("errorLogin +++++++++++++++++++++++++++++++");
		return "loginpage";

	}

	/**
	 * 指定无访问额权限页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/denied", method = RequestMethod.GET)
	public String getDeniedPage() {
		return "deniedpage";

	}
	
	/**
	 * 跳转到退出页面
	 * @return
	 */
	@RequestMapping(value="/auth/logout", method=RequestMethod.GET)
	public String getLoginoutPage(HttpServletRequest request,
			ModelMap model) {
		request.getSession().invalidate();
		return "redirect:/gotoLogin";
	}
}

