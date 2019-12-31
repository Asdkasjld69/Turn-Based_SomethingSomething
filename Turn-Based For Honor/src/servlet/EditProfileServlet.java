package servlet;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import bean.User;
import security.FileUploadUtils;
import service.UserService;

/**
 * Servlet implementation class EditProfileServlet
 */
@WebServlet("/EditProfileServlet")
@MultipartConfig
public class EditProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}
	
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		UserService uservice = new UserService();
		String gender = request.getParameter("gender");
		int id = Integer.parseInt(request.getParameter("id"));
		int isself = Integer.parseInt(request.getParameter("isself"));
		Collection<Part> FILES = request.getParts();
		boolean flag = false;
		User user = uservice.findUserById(id);
		if(user==null) {
			response.sendRedirect(this.getServletContext().getContextPath()+"/user/UNF.html");
			return;
		}
		String url = null;
		String DIR = null;
		String FILE_NAME_RANDOM = null;
		url = user.getAvatar();
		if(url!=null) {
			DIR = FileUploadUtils.getDirectory(url);
			FILE_NAME_RANDOM = FileUploadUtils.getFileName(url);
		}
		
		for (Part F : FILES) {
			String FILE_NAME = F.getSubmittedFileName();
			if (FILE_NAME == null || FILE_NAME.trim().equals("") || FILE_NAME.lastIndexOf("/") != -1
					|| FILE_NAME.lastIndexOf("\\") != -1)continue;
			if (F.getSize()>5*1024*1024) {
				flag = true;
				request.setAttribute("imageMessage", "*文件必须<=5mb*");
				break;
			}
			else if(!FILE_NAME.matches("^.*(\\.bmp|\\.png|\\.jpg|\\.gif)$")) {
				flag = true;
				request.setAttribute("imageMessage", "*文件格式必须为bmp/png/jpg/gif*");
				break;
			}	
			else {
				request.setAttribute("imageMessage", "");
			}
			if (url == null) {
				FILE_NAME_RANDOM = FileUploadUtils.generateRandonFileName(FILE_NAME);
				String DIR_RANDOM = FileUploadUtils.generateRandomDir(FILE_NAME_RANDOM);
				DIR = "/user/avatar" + DIR_RANDOM;
				url = DIR + "/" + FILE_NAME_RANDOM;
			}

			File path = new File(this.getServletContext().getRealPath(DIR));
			if (!path.exists()) {
				path.mkdirs();
			}
			writeTo(DIR, FILE_NAME_RANDOM, F);
		}
		user.setGender(gender);
		user.setAvatar(url);
		if(!flag) {
			uservice.editUser(user);
		}
		request.setAttribute("user", user);
		request.setAttribute("isself", isself);
		request.getRequestDispatcher("/user/profile.jsp").forward(request, response);
	}
	
	private void writeTo(String path, String filename, Part part) throws IOException, FileNotFoundException {
		InputStream in = part.getInputStream();
		OutputStream out = null;
		String dir = this.getServletContext().getRealPath(path);
		File destFile = new File(dir, filename);
		File dupFile = null;
		boolean dupflag = false;
		if (destFile.exists() && !destFile.isDirectory()) {
			dupFile = new File(dir, "$" + filename);
			out = new FileOutputStream(dupFile);
			dupflag = true;
		} else {
			out = new FileOutputStream(destFile);
		}

		byte[] buffer = new byte[1024];
		int length = -1;
		while ((length = in.read(buffer)) != -1) {
			out.write(buffer, 0, length);
		}
		in.close();
		out.close();
		if (dupflag) {
			destFile.delete();
			dupFile.renameTo(destFile);
		}
	}

}
