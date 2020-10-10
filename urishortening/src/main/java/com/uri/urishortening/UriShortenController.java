package com.uri.urishortening;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UriShortenController {
	
	@Autowired
	UriShortenService uriService;

	@PostMapping("/uriShorten")
	public String greeting(@RequestBody UrlLongRequest request, HttpServletRequest httpRequest) {
		try {
			
			String domain = httpRequest.getScheme()+"://"+httpRequest.getServerName()+  ":" + httpRequest.getServerPort()+"/";
			
			return domain+uriService.getShortenURI(request.getUri());
		}catch (Exception e) {
			e.printStackTrace();
			return "Something went wrong! Please try again later";
		}
		
	}
	
	@GetMapping("{shortUrl}")
	public String getAndRedirect(@PathVariable String shortUrl,HttpServletResponse response) {
		try {
			response.sendRedirect(uriService.getLongUri(shortUrl));
		}catch (Exception e) {
			e.printStackTrace();
			return "Wrong URI";
		}
		return "redirection....";
	}
}
