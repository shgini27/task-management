docker pull taskmanagement/task-management:$1
docker container stop taskmanagement
docker run --detach --rm --name taskmanagement --env-file ./env.list \
  -e "SPRING_PROFILES_ACTIVE=staging,docker" \
  -p 8000:8000 -p 9000:9000 taskmanagement/task-management:$1
