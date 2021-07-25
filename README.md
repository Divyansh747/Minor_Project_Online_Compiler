# Online Compiler with Code Editor

## About Project

>Online Compiler helps to executes program on centralised server with the help of Cloud Computing technology. It works on Software As a Service (SaaS) model. In Online Compiler you don't have to worry about installation / Setup process of multiple programming language environment and tools everything will be maintained by SaaS provider. This can save lot of programmers time and compatibility issues. It can be accessed from anywhere on your PC/Laptop, iPad or even in your smartphone with the help of Browser with internet connectivity. 

## Technology Used

    Backend          => Spring Boot(Java)
    Frontend         => React.js, HTML/CSS, BootStrap
    Database         => MySQL
    API              => RestAPI (Java Spring Boot)
    Authentication   => Java Web Token
    Container Engine => Docker
    
## Project System Design

![image](https://user-images.githubusercontent.com/15107919/126906999-bc72d3a1-9ed2-48f2-885e-dd361a6ed240.png)


## Requirements (For Development and Testing) 

- Java 8 (JDK+JRE)
- React.Js and NPM
- SpringBootToolSuite IDE
- Docker Engine
- Linux Based OS (Fedora/Ubuntu)
- MySQL Database
- Maven 

## Setup for Deployment

- Clone project repository
```
git clone https://github.com/Divyansh747/Minor_Project_Online_Compiler.git 
```
- Open project workspace
```
cd Minor_Project_Online_Compiler
```
- First goto Backend/spring_boot_online_compiler/
```
cd Backend/spring_boot_online_compiler/
```
- Build SpringBoot project using maven
```
mvn package -DskipTests
```
- Build Backend Docker image ( Note* Image name should be compiler-backend )
```
sudo docker build -t compiler-backend .
```
- Go back to Minor_Project_Online_Compiler/ 
```
cd ../..
```
- Now goto Frontend/compiler/
```
cd Frontend/compiler/
```
- Build Frontend Docker image ( Note* Image name should be compiler-frontend )
```
sudo docker build -t compiler-frontend .
```
- Go back to Minor_Project_Online_Compiler/ 
```
cd ../..
```
- Final step to deploy project with the help of docker-compose file
```
sudo docker-compose up
```
- Wait for few minutes to complete deployment process
- To verify project is running open below URL in browser
```
http://localhost:8080
```
(Note* you can replace localhost with your system IP where you deployed project)




