package polls.polls

import jakarta.validation.ValidationException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/polls", produces = ["application/json"])
class PollsApiController(
    private val pollsService: PollsService
) {
    @GetMapping("")
    fun index(): List<Question> = pollsService.getLatestQuestions().map { it.toQuestion() }

    @GetMapping("/{questionId:\\d+}")
    fun detail(@PathVariable questionId: Int): QuestionDetail =
        pollsService.getQuestion(questionId).toQuestionDetail()


    @PostMapping( "/{questionId:\\d+}/vote", consumes = ["application/json"])
    fun vote(@PathVariable questionId: Int, @RequestBody voteRequest: VoteRequest): ResponseEntity<VoteResponse> {
        val question = pollsService.getQuestion(questionId)
        return try {
            pollsService.vote(question, voteRequest.choice)
            ResponseEntity.ok(VoteResponse(question.toQuestionDetail()))
        } catch (err: ValidationException) {
            ResponseEntity.badRequest().body(VoteResponse(question.toQuestionDetail(), err.message))
        }
    }

    data class VoteRequest(val choice: Int)
    data class VoteResponse(val question: QuestionDetail, val errorMessage: String? = null)
    data class Question(val id: Int, val questionText: String)
    data class QuestionDetail(val id: Int, val questionText: String, val choices: List<Choice>)
    data class Choice(val id: Int, val choiceText: String, val votes: Int)

    private fun QuestionEntity.toQuestion() = Question(id!!, questionText)
    private fun QuestionEntity.toQuestionDetail() = QuestionDetail(id!!, questionText, choices.map { it.toChoice() })
    private fun ChoiceEntity.toChoice() = Choice(id!!, choiceText, votes)
}