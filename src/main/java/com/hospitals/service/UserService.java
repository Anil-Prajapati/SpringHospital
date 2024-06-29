package com.hospitals.service;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.hospitals.email.EmailNotification;
import com.hospitals.model.Role;
import com.hospitals.model.User;
import com.hospitals.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private EmailNotification emailNotification;
	
	public Iterable<User> getAll(){
		return userRepository.findAll();
	}
	
	public User getSingleData(String userName) {
		return userRepository.findByUserName(userName);
	}
	
	public User create(User user) {
	    // Encrypt the user's password
	    String password = user.getPassword();
	    String encrypt = bCryptPasswordEncoder.encode(password);
	    user.setPassword(encrypt);
	    user.setDate(new Date());

	    // Set roles for the user
	    Set<Role> roles = new HashSet<>();
	    Role role = new Role();
	    role.setRoleName("User");
	    role.setDescription("This Is The User Role");
	    roles.add(role);
	    user.setRoles(roles);

        // Create email content
	    String emailText = "<html>" +
	        "<head>" +
	        "<style>" +
	        "body { font-family: Arial, sans-serif; background-color: #f4f4f4; margin: 0; padding: 0; }" +
	        ".container { width: 100%; max-width: 600px; margin: 0 auto; background-color: #ffffff; padding: 20px; border-radius: 8px; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2); }" +
	        ".header { background-color: #4CAF50; padding: 10px 0; color: white; text-align: center; border-radius: 8px 8px 0 0; }" +
	        ".content { background-color: #36131305; padding: 25px; }" +
	        ".footer { text-align: center; font-size: 12px; color: #777777; margin-top: 20px; }" +
	        "</style>" +
	        "</head>" +
	        "<body>" +
	        "<div class='container'>" +
	            "<div class='header'>" +
	                "<h1>Welcome to Sunita Hospital!</h1>" +
	            "</div>" +
	            "<div class='content'>" +
	                "<p>Hello " + user.getUserName() + ",</p>" +
	                "<p>Congratulations! Your User account at My Application has been successfully created.</p>" +
	                "<p><strong>Account Details:</strong></p>" +
	                "<p>- Address: " + user.getAddress() + "</p>" +
	                "<p>- Email: " + user.getEmail() + "</p>" +
	                "<p>We're excited to have you on board. Your journey with us begins now!</p>" +
	                "<p>If you have any questions or need assistance, feel free to contact our support team.</p>" +
	            "</div>" +
	            "<div class='footer'>" +
	                "<p>Best regards,<br>The Anil Kumar Prajapati Team,<br>Java Developer</p>" +
	            "</div>" +
	        "</div>" +
	        "</body>" +
	        "</html>";

	    // Send the email notification
	    emailNotification.mailSender("Student Account Created", emailText, user.getEmail());

	    // Save the user to the repository
	    return userRepository.save(user);
	}

}
