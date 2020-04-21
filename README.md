# wip
Project ports &  description:
gitserver: 7999
eureka:8000
zuul: 8001 (remote read routing configuration,jwt)
app:8002 (feign call  log in ,registration and list funtions  respectively from support and content)
support:8003 (login,registration,junit test)
admin:8004 (feign call  news list and detail funtions of content)
content: 8005 (news crud,elasticsearch sychronization when inserting or deleting)

Docker Container ports:
Mysql:3306.              (used by  support,content)
Elasticsearch:9300  (used by  content)

git remote config uri:
https://github.com/yinyyang/wip-springcloud-config-repo.git
