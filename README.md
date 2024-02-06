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
cd frontend
sudo npm install
{ < cannot find module (vue-cli-service) 에러 발생시 >
    sudo npm uninstall vue-cil -g
    sudo npm install -g @vue/cli
}

sudo mvn clean package
java -DKEYCLOAK_URL="$KEYCLOAK_URL" -DCLIENT_SECRET="$CLIENT_SECRET" -jar {jar}

```
