package polls.polls

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.springframework.boot.test.context.SpringBootTest
import java.time.ZonedDateTime

@SpringBootTest
class PollsServiceTest(
    service: PollsService,
    questionRepository: QuestionRepository,
    ChoiceRepository: ChoiceRepository,
) {


    @BeforeEach
    fun setUp() {
        val question = QuestionEntity(
            questionText = "What is your favorite color?",
            pubDate = ZonedDateTime.now()
        )
    }


    @Test
    fun getLatestQuestions() {

    }

    @Test
    fun getQuestion() {
    }

    @Test
    fun vote() {
    }
}