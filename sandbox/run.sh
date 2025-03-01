# Criar as docker networks
docker network create adm_videos_services
docker network create elastic

# Criar as pastas com permissões
sudo chown root app/filebeat/filebeat.docker.yml
mkdir .docker
chmod 777 .docker

mkdir .docker/es01
chmod 777 .docker/es01

mkdir .docker/keycloak
chmod 777 .docker/keycloak

mkdir .docker/filebeat
chmod 777 .docker/filebeat


docker compose -f services/docker-compose.yml up -d
docker compose -f elk/docker-compose.yml up -d
#docker compose -f app/docker-compose.yml up -d

echo "Inicializando os containers..."
sleep 20