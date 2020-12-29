package com.neighbors.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neighbors.messages.MessageResponse;
import com.neighbors.models.ERole;
import com.neighbors.models.Role;
import com.neighbors.models.User;
import com.neighbors.payload.request.LoginRequest;
import com.neighbors.payload.request.SignupRequest;
import com.neighbors.payload.response.JwtResponse;
import com.neighbors.repositories.RoleRepository;
import com.neighbors.repositories.UserRepository;
import com.neighbors.security.jwt.JwtUtils;
import com.neighbors.security.service.UserDetailsImpl;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("api/auth")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;
	
//	@Autowired
//	CustomerRepository customerRepository;
	


	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;
	
	

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

				
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();		
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(new JwtResponse(jwt, 
												 userDetails.getId(), 
												 userDetails.getUsername(), 
												 userDetails.getEmail(), 
												 roles));
	}
	
/*
 * ---------------------------------------------------------------------------------------
 * Registering without email verification
 * ---------------------------------------------------------------------------------------
 */
	
	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username is already taken!"));
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use!"));
		}

		// Create new user's account
		User user = new User(signUpRequest.getUsername(), 
							 signUpRequest.getEmail(),
							 encoder.encode(signUpRequest.getPassword()));
		
		

		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			Role userRole = roleRepository.findByName(
					ERole.USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					Role adminRole = roleRepository.findByName(
							ERole.ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(adminRole);

					break;

				default:
					Role userRole = roleRepository.findByName(
							ERole.USER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(userRole);
				}
			});
		}

		user.setRoles(roles);
		userRepository.save(user);

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
/*
 * 
 * ---------------------------------------------------------------------------------------
 */
	

/*
 * ---------------------------------------------------------------------------------------
 * Registering with email verification
 * ---------------------------------------------------------------------------------------
 */
//	@Autowired
//    private ConfirmationTokenRepository confirmationTokenRepository;
//	
//	@Autowired
//    private EmailSenderService emailSenderService;
//	
//	@RequestMapping(value="/register", method = RequestMethod.GET)
//    public ModelAndView displayRegistration(ModelAndView modelAndView, User user)
//    {
//        modelAndView.addObject("user", user);
////        modelAndView.setViewName("register");
//        modelAndView.setViewName("signup");
//        return modelAndView;
//    }
//	
//	@PostMapping("/signup")
//	public ModelAndView registerUser(@Valid @RequestBody SignupRequest signUpRequest, ModelAndView modelAndView) 
//	{
//		if (userRepository.existsByUsername(signUpRequest.getUsername())) 
//		{
//
//			modelAndView.addObject("message","Error: Username is already taken!");
//            modelAndView.setViewName("error");
//		}else if (userRepository.existsByEmail(signUpRequest.getEmail())) {
//			
//			modelAndView.addObject("message","Error: Email is already in use!");
//            modelAndView.setViewName("error");
//		}else
//		{
//			// Create new user's account
//			User user = new User(signUpRequest.getUsername(), 
//								 signUpRequest.getEmail(),
//								 encoder.encode(signUpRequest.getPassword()));
//			
//			
//
//			Set<String> strRoles = signUpRequest.getRole();
//			Set<Role> roles = new HashSet<>();
//
//			if (strRoles == null) {
//				Role userRole = roleRepository.findByName(
//						ERole.USER)
//						.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//				roles.add(userRole);
//			} else {
//				strRoles.forEach(role -> {
//					switch (role) {
//					case "admin":
//						Role adminRole = roleRepository.findByName(
//								ERole.ADMIN)
//								.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//						roles.add(adminRole);
//
//						break;
//
//					default:
//						Role userRole = roleRepository.findByName(
//								ERole.USER)
//								.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//						roles.add(userRole);
//					}
//				});
//			}
//
//			user.setRoles(roles);
//			userRepository.save(user);
//			
//			ConfirmationToken confirmationToken = new ConfirmationToken(user);
//
//	        confirmationTokenRepository.save(confirmationToken);
//
//	        SimpleMailMessage mailMessage = new SimpleMailMessage();
//	        mailMessage.setTo(user.getEmail());
//	        mailMessage.setSubject("Complete Registration!");
//	        mailMessage.setFrom("pavonsig@yahoo.com");
//	        mailMessage.setText("To confirm your account, please click here : "
//	        +"http://localhost:8080/confirm-account?token="+confirmationToken.getConfirmationToken());
//
//	        emailSenderService.sendEmail(mailMessage);
//
//	        modelAndView.addObject("email", user.getEmail());
//
//	        modelAndView.setViewName("successfulRegistration");
//		}
//
//        return modelAndView;
//	}
//	
//	@RequestMapping(value="/confirm-account", method= {RequestMethod.GET, RequestMethod.POST})
//    public ModelAndView confirmUserAccount(ModelAndView modelAndView, @RequestParam("token")String confirmationToken)
//    {
//
//        ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);
//        
//        if(token != null)
//        {
//            User user = userRepository.findByEmailIgnoreCase(token.getUser().getEmail());
//            user.setEnabled(true);
//            userRepository.save(user);
//            modelAndView.setViewName("accountVerified");
//        }
//        else
//        {
//            modelAndView.addObject("message","The link is invalid or broken!");
//            modelAndView.setViewName("error");
//        }
//
//        return modelAndView;
//    }
	
/*
 * ---------------------------------------------------------------------------------------
 * end of code Registering with email verification
 * ---------------------------------------------------------------------------------------
 */

}
