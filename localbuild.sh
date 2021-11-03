./gradlew build

cd cloud-config
docker build -t cloud-config:0.0 ./
cd ..

cd cloud-explorer
docker build -t cloud-explorer:0.0 ./
cd ..
