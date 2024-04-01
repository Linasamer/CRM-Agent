//package com.code.secretary.controller.util;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.code.secretary.service.util.LoggingService;
//
//import io.swagger.v3.oas.annotations.Hidden;
//
//@Hidden
//@RestController
//@RequestMapping("/api/util/logging")
//@CrossOrigin(origins = "*")
//public class LoggingController {
//
//	private LoggingService loggingService;
//
//	@Autowired
//	public LoggingController(LoggingService loggingService) {
//		this.loggingService = loggingService;
//	}
//
//	@PostMapping()
//	public void logClientError(@RequestBody String stackTrace) {
//		this.loggingService.logClientError(stackTrace);
//	}
//
//}
