**Profanity-Away REST API**

A simple REST API that gives you the option to Censor, Replace a word or Replace the whole phrase if profanity is present.

Accepts the following JSON:

``{
      "action": "REPLACE_WITH_WORD",
      "text": "Some string here",
      "replacementWord": "replacement word"
  }``
  
action values can be one of:
[CENSOR, REPLACE_WITH_WORD, REPLACE_WITH_FACT]

text dictates which word/sentence should be filtered.

replacementWord substitutes any profanity found with your word of choice.

The result for a filtered response would look like:

``{
      "filteredMessage": "you piece of ****",
      "replacementMethod": "CENSOR"
  }``
  
Also spins up a typical Swagger UI at ``/swagger-ui/index.html#/``