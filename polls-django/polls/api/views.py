from django.db import transaction
from django.db.models import F
from django.shortcuts import get_object_or_404
from django.utils import timezone

from rest_framework.decorators import api_view, authentication_classes
from rest_framework.response import Response
from rest_framework.exceptions import ValidationError

from polls.models import Question, Choice
from .serialisers import QuestionSerializer, QuestionDetailSerializer

@api_view(["GET"])
def index(request):
    latest_questions = (Question.objects.filter(pub_date__lte=timezone.now())
                        .order_by("-pub_date")[:5])
    return Response({
        "latest_questions": QuestionSerializer(latest_questions, many=True).data
    })


@api_view(["GET"])
def detail(request, question_id):
    question = get_object_or_404(Question, pk=question_id)
    return Response(QuestionDetailSerializer(question).data)


@api_view(["POST"])
@authentication_classes([])
def vote(request, question_id):
    question = get_object_or_404(Question, pk=question_id)
    try:
        selected_choice = question.choices.get(pk=request.data["choice"])
    except (KeyError, Choice.DoesNotExist):
        raise ValidationError("You didn't select a choice.")
    else:
        with transaction.atomic():
            selected_choice.votes = F("votes") + 1
            selected_choice.save(update_fields=["votes"])
        return Response(QuestionDetailSerializer(question).data)
