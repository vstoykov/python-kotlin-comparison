package polls.polls

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.transaction.annotation.Transactional

interface QuestionRepository : JpaRepository<QuestionEntity, Int> {
    fun findTop5ByOrderByPubDateDesc(): MutableList<QuestionEntity>
}

interface ChoiceRepository : JpaRepository<ChoiceEntity, Int> {

    @Transactional
    @Modifying
    @Query("update ChoiceEntity c set c.votes = c.votes + 1 where c.id = ?1")
    fun voteFor(id: Int)
}
