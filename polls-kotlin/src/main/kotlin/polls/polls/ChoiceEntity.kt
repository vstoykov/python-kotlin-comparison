package polls.polls

import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank
import org.hibernate.annotations.ColumnDefault


@Entity
@Table(name = "polls_choice")
class ChoiceEntity(
    @ManyToOne(optional = false)
    @JoinColumn(name = "question_id", nullable = false)
    val question: QuestionEntity,

    @NotBlank
    @Column(name = "choice_text", nullable = false)
    var choiceText: String
) {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    val id: Int? = null

    @Column(name = "votes", nullable = false)
    @ColumnDefault("0")
    var votes: Int = 0

    override fun toString(): String = choiceText

    override fun hashCode(): Int = id?.hashCode() ?: javaClass.hashCode()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as QuestionEntity
        if (id != other.id) return false
        return true
    }
}
