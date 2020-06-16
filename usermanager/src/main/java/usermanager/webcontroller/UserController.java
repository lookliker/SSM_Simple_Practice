package usermanager.webcontroller;

import java.io.File;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import usermanager.domain.Manager;
import usermanager.domain.User;
import usermanager.mapper.ManagerMapper;
import usermanager.service.IUserService;

@org.springframework.stereotype.Controller
public class UserController{
	@Autowired
	private IUserService userService;
	@Autowired
	private ManagerMapper managerMapper;
	
	@RequestMapping("/login")
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		if(request.getParameter("name")==null) {
			response.sendRedirect("/illegalop.jsp");
			return null;
		}
		Manager manager = managerMapper.isManager(request.getParameter("name"));
		ModelAndView mv = new ModelAndView();
		if(manager!=null && manager.getPassword().equals(request.getParameter("password"))) {
			List<User> users = getUsers(); 
			mv.addObject("users",users);
			mv.setViewName("manager.jsp");
			HttpSession session = request.getSession();
			session.setAttribute("isManager", "true");
			return mv;
		}
		mv.addObject("error", "用户名不存在或密码错误");
		mv.setViewName("loginerror.jsp");
		return mv;
	}
	@RequestMapping("/update")
	public void update(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if(request.getSession().getAttribute("isManager")==null) {
			response.sendRedirect("/illegalop.jsp");
			return;
		}
		request.setCharacterEncoding("utf-8");
		if("before".equals(request.getParameter("op"))) {
			int id = Integer.parseInt(request.getParameter("id"));
			User user = userService.get(id);
			request.setAttribute("user", user);
			request.getRequestDispatcher("/WEB-INF/views/update.jsp").forward(request, response);
		}else if("after".equals(request.getParameter("op"))) {
			int id = Integer.parseInt(request.getParameter("id"));
			User user = new User();
			user.setAge(Integer.parseInt(request.getParameter("age")));
			user.setName(request.getParameter("name"));
			userService.update(user, id);
			List<User> users = getUsers();
			request.setAttribute("users", users);
			request.getRequestDispatcher("/WEB-INF/views/manager.jsp").forward(request, response);
		}
	}
	
	@RequestMapping("/delete")
	public void delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if(request.getSession().getAttribute("isManager")==null) {
			response.sendRedirect("/illegalop.jsp");
			return;
		}
		userService.delete(Integer.parseInt(request.getParameter("id")));
		List<User> users = getUsers();
		request.setAttribute("users", users);
		request.getRequestDispatcher("/WEB-INF/views/manager.jsp").forward(request, response);
	}
	
	@RequestMapping("/insert")
	public void insert(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if(request.getSession().getAttribute("isManager")==null) {
			response.sendRedirect("/illegalop.jsp");
			return;
		}
		if("before".equals(request.getParameter("op"))) {
			request.getRequestDispatcher("/WEB-INF/views/insert.jsp").forward(request, response);
		}else if("after".equals(request.getParameter("op"))) {
			User user = new User();
			user.setAge(Integer.parseInt(request.getParameter("age")));
			String name = new String(request.getParameter("name").getBytes("ISO-8859-1"),"utf-8");
			user.setName(name);
			user.setImage(opImage((MultipartHttpServletRequest)request,"save"));
			userService.insert(user);
			List<User> users = getUsers();
			request.setAttribute("users", users);
			request.getRequestDispatcher("/WEB-INF/views/manager.jsp").forward(request, response);
		}
	}
	
	
	
	
	@RequestMapping("/image")
	public void image(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if(request.getSession().getAttribute("isManager")==null) {
			response.sendRedirect("/illegalop.jsp");
			return;
		}
		if("before".equals(request.getParameter("op"))) {
			int id = Integer.parseInt(request.getParameter("id"));
			User u = userService.get(id);
			request.setAttribute("id", id);
			request.setAttribute("image", u.getImage());
			request.getRequestDispatcher("/WEB-INF/views/changeimage.jsp").forward(request, response);
		}else if("after".equals(request.getParameter("op"))) {
			opImage((MultipartHttpServletRequest)request,"update");
			List<User> users = getUsers();
			request.setAttribute("users", users);
			request.getRequestDispatcher("/WEB-INF/views/manager.jsp").forward(request, response);
		}
	}
	
	private List<User> getUsers() {
		List<User> users = userService.getAll();
		return users;
	}
	private String opImage(MultipartHttpServletRequest request, String op) throws Exception {
		if("update".equals(op)) {
			int id = Integer.parseInt(request.getParameter("id"));
			MultipartFile file = request.getFile("newImage");
			String name = file.getOriginalFilename();
			String realPath = request.getServletContext().getRealPath("/images");
			String newName = UUID.randomUUID().toString()+name.substring(name.lastIndexOf('.'));
			file.transferTo(new File(realPath,newName));
			userService.updateImage(newName, id);
			return null;
		}else if("save".equals(op)) {
			try {
				MultipartFile file = request.getFile("image");				
				String name = file.getOriginalFilename();
				String realPath = request.getServletContext().getRealPath("/images");
				String newName = UUID.randomUUID().toString()+name.substring(name.lastIndexOf('.'));
				file.transferTo(new File(realPath,newName));
				return newName;
			}catch(Exception e) {
				return null;
			}
		}
		return null;
	}
}
