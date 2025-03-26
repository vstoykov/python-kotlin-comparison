from rest_framework.serializers import ModelSerializer
from polls.models import Question, Choice

class QuestionSerializer(ModelSerializer):
    class Meta:
        model = Question
        fields = "id", "question_text", "pub_date",


class ChoiceSerializer(ModelSerializer):
    class Meta:
        model = Choice
        fields = "id", "choice_text", "votes",


class QuestionDetailSerializer(ModelSerializer):

    choices = ChoiceSerializer(many=True)

    class Meta:
        model = Question
        fields = "id", "question_text", "pub_date", "choices",
