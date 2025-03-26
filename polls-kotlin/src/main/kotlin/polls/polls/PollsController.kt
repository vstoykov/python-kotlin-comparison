package polls.polls

import jakarta.validation.ValidationException
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("/polls")
class PollsController(
    private val pollsService: PollsService,
) {

    @GetMapping("")
    fun index(model: Model): String {
        model.addAttribute("latestQuestions", pollsService.getLatestQuestions())
        return "polls/index"
    }

    @GetMapping("/{questionId:\\d+}")
    fun detail(@PathVariable questionId: Int, model: Model): String {
        model.addAttribute("question", pollsService.getQuestion(questionId))
        return "polls/detail"
    }

    @GetMapping("/{questionId:\\d+}/results")
    fun results(@PathVariable questionId: Int, model: Model): String {
        model.addAttribute("question", pollsService.getQuestion(questionId))
        return "polls/results"
    }

    @PostMapping("/{questionId:\\d+}/vote")
    fun vote(@PathVariable questionId: Int, @RequestParam("choice") choiceId: Int?, model: Model): String {
        val question = pollsService.getQuestion(questionId)
        return try {
            pollsService.vote(question, choiceId)
            "redirect:/polls/${questionId}/results"
        } catch (err: ValidationException) {
            model.addAttribute("question", question)
            model.addAttribute("error_message", err.message)
            "polls/detail"
        }
    }
}