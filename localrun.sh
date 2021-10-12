./localbuild.sh $1
cd environments
docker-compose --env-file .env.local up
