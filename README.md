# Office

## Getting started

```
sudo su
sudo apt update && sudo apt upgrade -y
sudo apt install -y openjdk-17-jdk openjdk-17-jre
sudo apt install maven -y
sudo curl -sL https://deb.nodesource.com/setup_18.x | bash -
sudo apt install nodejs
sudo npm install npm@latest -g
curl -o- https://raw.githubusercontent.com/nvm-sh/nvm/v0.39.1/install.sh | bash
source ~/.bashrc
nvm install 16.14
nvm use 16.14
{git clone}
cd frontend

cat <<EOF > .env
VUE_APP_API_ENDPOINT="http://192.168.0.46"
VUE_APP_SUB_ROUTE="/"
EOF

npm install
npm run serve {-- --port 3000}
# 실패시 'rm -rf node_modules, package-lock.json' 실행 후 다시 설치
```
#  새 터미널에서 스프링 실행

```
cat <<EOF > .env
KEYCLOAK_URL="http://192.168.0.46:8080"
CLIENT_SECRET="fi33bJWZExI0DYTF7IwFZ2xsxfBS2KfK"
DB_USER="sa"
DB_PW="1"
DB_URL="jdbc:mariadb://192.168.0.46:3306/TEST"
EOF

sudo mvn clean package
java -jar -Dspring.profiles.active=prod target/office-0.0.1-SNAPSHOT.jar

```
