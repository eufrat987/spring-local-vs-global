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