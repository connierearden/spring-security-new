package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class UserController {

	private UserService userService;
	@Autowired
	public UserController (UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/admin")
	public String getAllUsers(Model model) {
		List list = userService.getAllUsers();
		model.addAttribute("users", list);
		return "admin";
	}

	@GetMapping("/admin/new")  // по /admin/new вернется форма для создания человека
	public String createPerson (User user) { // если используем таймлиф, мы должны передать объект, для которого форма нужна
		return "new";
	}

	@PostMapping("/admin/new")
	public String create (User user) {
		userService.saveUser(user);
		return "redirect:/admin";
	}

	@GetMapping("/admin/{id}")
	public String deleteUser (@PathVariable("id") Long id) {
		userService.deleteUser(id);
		return "redirect:/admin";
	}

	@GetMapping("/admin/update/{id}")
	public String updatePerson (@PathVariable("id") Long id, Model model) {
		User user = userService.findUserById(id);
		//System.out.println(user);
		model.addAttribute("user", user);
		return "update";
	}
	@PostMapping("/admin/update")
	public String update (@ModelAttribute("user") User user) {
		userService.updateUser(user);
		return "redirect:/admin";
	}

	@GetMapping("/user/{name}")
	public String getUser (@PathVariable("name") String name, Model model) {
		User user = userService.findUserByName(name);
		model.addAttribute("user", user);
		return "user";
	}
	@PostMapping("/user")
	public String getUserPage (@ModelAttribute("user") User user, Model model) {
		model.addAttribute("user", user);
		return "redirect:/user";
	}



	@RequestMapping(value = "hello", method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {
		List<String> messages = new ArrayList<>();
		messages.add("Hello!");
		messages.add("I'm Spring MVC-SECURITY application");
		messages.add("5.2.0 version by sep'19 ");
		model.addAttribute("messages", messages);
		return "hello";
	}

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String loginPage() {
        return "login";
    }

}