package polls.polls

import jakarta.persistence.EntityManager
import jakarta.persistence.EntityNotFoundException
import jakarta.validation.ValidationException
import org.springframework.stereotype.Service
import org.springframework.transaction.support.TransactionTemplate

@Service
class PollsService(
    private val questionRepository: QuestionRepository,
    private val choiceRepository: ChoiceRepository,
    private val entityManager: EntityManager,
    private val transactionTemplate: TransactionTemplate,
) {
    fun getLatestQuestions(): List<QuestionEntity> = questionRepository.findTop5ByOrderByPubDateDesc()

    fun getQuestion(questionId: Int): QuestionEntity =
        questionRepository.findById(questionId)
            .orElseThrow { EntityNotFoundException("Question $questionId does not exist") }

    fun vote(question: QuestionEntity, choiceId: Int?): QuestionEntity {
        transactionTemplate.execute {
            val choice = question.choices.find { it.id == choiceId }
                ?: throw ValidationException("You didn't select a choice.")

            choiceRepository.voteFor(choice.id!!)
        }
        transactionTemplate.execute {
            entityManager.refresh(question)
        }
        return question
    }
}