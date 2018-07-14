package com.lames.picserver.servlet;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.lames.picserver.model.JsonResult;
import com.lames.picserver.util.ImageUtil;
import com.lames.picserver.util.JsonUtil;

/**
 * Servlet implementation class UploadServlet
 */
@WebServlet("/upload")
@MultipartConfig
public class UploadServlet extends HttpServlet {
       
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Part> parts = (List<Part>) request.getParts();
		
		String path = request.getServletContext().getRealPath("/");
		JsonResult result = new JsonResult();
		Part part = parts.get(0);
		try {
			String url = ImageUtil.saveImage(part.getInputStream(), path);
			if(url != null) {
				result.setStatus("success");
				result.setMessage("图片上传成功");
				result.setData("url",url);
			}else {
				result.setStatus("failure");
				result.setMessage("图片上传失败");
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(JsonUtil.objectToJson(result));
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
