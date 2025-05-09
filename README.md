# spring-local-vs-global

Local
Transaction per resource.

VS

Global
One transaction per multiple resources.

---
https://docs.spring.io/spring-framework/reference/data-access/transaction/motivation.html

---
Notes:
Kafka do not support XA, so I believe it cannot use atomikos 
and take part in global transaction, or it is very hard to make it works.

---
What's new?
2PC
https://en.wikipedia.org/wiki/Two-phase_commit_protocol

One node as coordinator sends "Can commit?" to all participants.
Participants tests if can commit changes and sends Yes/No.

If all votes are Yes then coordinator sends "Commit." to all participants.
Participants completes transaction.
