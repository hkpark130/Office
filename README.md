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
sudo npm install -g @vue/cli
{ < cannot find module (vue-cli-service) 에러 발생시 >
    sudo npm uninstall vue-cil -g
    sudo npm install -g @vue/cli
}
sudo vue create frontend

sudo mvn clean package
```
