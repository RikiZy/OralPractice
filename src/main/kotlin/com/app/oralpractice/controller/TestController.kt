package com.app.oralpractice.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.ui.Model
import java.time.LocalDateTime

@Controller
@RequestMapping("/test")
class TestController {

    @GetMapping("/api/hello")
    @ResponseBody
    fun helloApi(): Map<String, Any> {
        return mapOf(
            "message" to "Hello from OralPractice API!",
            "timestamp" to LocalDateTime.now(),
            "status" to "success"
        )
    }

    @GetMapping("/api/status")
    @ResponseBody
    fun statusApi(): Map<String, Any> {
        return mapOf(
            "application" to "OralPractice",
            "version" to "1.0.0",
            "status" to "running",
            "timestamp" to LocalDateTime.now()
        )
    }

    @GetMapping("/page")
    fun testPage(model: Model): String {
        model.addAttribute("title", "OralPractice 测试页面")
        model.addAttribute("message", "欢迎使用口语练习系统！")
        model.addAttribute("timestamp", LocalDateTime.now())
        return "test"
    }

    @GetMapping("/")
    fun indexPage(): String {
        return "redirect:/test/page"
    }
}