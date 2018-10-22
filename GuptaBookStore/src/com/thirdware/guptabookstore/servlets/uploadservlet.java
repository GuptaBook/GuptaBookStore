package com.thirdware.guptabookstore.servlets;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * Servlet implementation class uploadservlet
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 10, // 10MB
maxRequestSize = 1024 * 1024 * 50) // 50MB
@WebServlet("/uploadservlet")
public class uploadservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public static final String SAVE_DIRECTORY = "uploadDir";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public uploadservlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			String description = request.getParameter("description");
			System.out.println("Description: " + description);

			// Gets absolute path to root directory of web app.
			String appPath = request.getServletContext().getRealPath("");
			appPath = appPath.replace('\\', '/');

			// The directory to save uploaded file
			String fullSavePath = null;
			if (appPath.endsWith("/")) {
				fullSavePath = appPath +"resources/images/"+ SAVE_DIRECTORY;
			} else {
				fullSavePath = appPath + "/resources/images/" + SAVE_DIRECTORY;
			}

			// Creates the save directory if it does not exists
			File fileSaveDir = new File(fullSavePath);
			if (!fileSaveDir.exists()) {
				fileSaveDir.mkdir();
			}

			// Part list (multi files).
			for (Part part : request.getParts()) {
				int i=0;
				String fileName = String.valueOf(++i);
				if (fileName != null && fileName.length() > 0) {
					String filePath = fullSavePath + File.separator + fileName;
					System.out.println("Write attachment to file: " + filePath);
					// Write to file
					part.write(filePath);
				}
			}
			// Upload successfully!.
			response.sendRedirect(request.getContextPath() + "/uploadFileResults");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Error: " + e.getMessage());
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/insertbook.jsp");
			dispatcher.forward(request, response);
			doGet(request, response);
		}
	}

	private String extractFileName(Part part) {
		// form-data; name="file"; filename="C:\file1.zip"
		// form-data; name="file"; filename="C:\Note\file2.zip"
		String contentDisp = part.getHeader("content-disposition");
		String[] items = contentDisp.split(";");
		for (String s : items) {
			if (s.trim().startsWith("filename")) {
				// C:\file1.zip
				// C:\Note\file2.zip
				String clientFileName = s.substring(s.indexOf("=") + 2, s.length() - 1);
				clientFileName = clientFileName.replace("\\", "/");
				int i = clientFileName.lastIndexOf('/');
				// file1.zip
				// file2.zip
				return clientFileName.substring(i + 1);
			}
		}
		return null;
	}

}
