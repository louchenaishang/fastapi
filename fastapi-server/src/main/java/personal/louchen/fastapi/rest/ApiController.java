package personal.louchen.fastapi.rest;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/api")
public class ApiController{

	//@RequestMapping(value = "", method = RequestMethod.GET)
	public void packages(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String content = RestResourceUtil.packages();
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.print(content);
	}

	//@RequestMapping("/class")
	public void getClassInfo(@RequestParam("className") String className,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String content = RestResourceUtil.getClassInfo(Class.forName(className));
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.print(content);
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView bootstrap(){
		ModelAndView mv = new ModelAndView("index");
		List<RestDescriptor> list = RestResourceUtil.restDescriptorList;
		mv.addObject("list",list);
		return mv;
	}

	@RequestMapping("/restClass")
	@ResponseBody
	public List<RestClassWrapper> getRestClass(@RequestParam("className") String className) throws Exception{
		return RestResourceUtil.getRestClassWrapper(Class.forName(className));
	}

}
