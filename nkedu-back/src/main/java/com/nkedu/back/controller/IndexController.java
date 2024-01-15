// package com.nkedu.back.controller;

// import java.util.HashMap;
// import java.util.Map;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.RestController;

// import com.nkedu.back.api.SecurityService;

// @RestController
// @RequestMapping("/")
// public class IndexController {
    
//     @Autowired
//     private SecurityService securityService;

//     @GetMapping("security/generate/token")
//     public Map<String, Object> generateToken(@RequestParam String subject) {
//         String token = securityService.createToken(subject, 1000 * 60 * 60);
//         Map<String, Object> map = new HashMap<>();
//         map.put("userId", subject);
//         map.put("token", token);
//         return map;
//     }

//     @GetMapping("security/get/subject")
//     public String getSubject(@RequestParam String token) {
//         String subject = securityService.getSubject(token);
//         return subject;
//     }
// }
