from django.contrib import admin

from .models import Question, Choice


class ChoiceInline(admin.TabularInline):
    model = Choice
    extra = 3
    readonly_fields = ('votes',)


@admin.register(Question)
class QuestionAdmin(admin.ModelAdmin):
    list_display = ('question_text', 'pub_date', 'was_published_recently')
    list_filter = ['pub_date']
    search_fields = ['question_text']

    inlines = [ChoiceInline]

    @admin.display(boolean=True, description="Published recently?", ordering="pub_date")
    def was_published_recently(self, obj: Question):
        return obj.was_published_recently()
