package polls.polls

import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank
import java.time.ZonedDateTime

@Entity
@Table(name = "polls_question")
class QuestionEntity(
    @NotBlank
    @Column(name = "question_text", nullable = false)
    var questionText: String,

    @Column(name = "pub_date", nullable = false)
    var pubDate: ZonedDateTime = ZonedDateTime.now(),

    choices: List<ChoiceEntity>? = null
) {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    val id: Int? = null

    @OneToMany
    @JoinColumn(name = "question_id")
    val choices: MutableSet<ChoiceEntity> = choices?.toMutableSet() ?: mutableSetOf()

    fun wasPublishedRecently(): Boolean {
        val now = ZonedDateTime.now()
        return pubDate.isAfter(now.minusDays(1)) && pubDate.isBefore(now)
    }

    override fun toString(): String = questionText

    override fun hashCode(): Int = id?.hashCode() ?: 13

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as QuestionEntity
        if (id != other.id) return false
        return true
    }
}
