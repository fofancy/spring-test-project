# test-task-ossystem
Test task for ossystem

The architecture of a whole project is following : there is a frontend server and backend. 
They work separately. Frontend server is based on webpack-dev-server and angularjs which starts at 8081 port on a localhost and
has a proxy to 8080 backend port. And backend is deployed on Tomcat application server using Spring Boot technology.

The idea is following: user does some actions on a web-site, client sends http request to webpack-dev-server and frontend web server 
redirects it to backend application server which interacts with database server and processes requests.

The backend architecture is classic. It has 3 layers : controller, service and repository. There were 
created Picture entity class and PictureDto class for interacting with database and client-side. For data layer was used Spring data.

The frontend architecture also very simple. Angularjs controllers handel user actions and uses service wrappers for http requests
and sends them to a backend. Bootstrap styles was atively used for html/css. Npm modules and webpack were used to load dependencies
and build project.



<h2> How to launch </h2>
<h3> Prerequisites </h3>
    1. NPM
    2. Webpack
    3. com.oracle:ojdbc7:12.1.0.1 dependency in Local Maven Repository 
        You can do this using following command:
            mvn install:install-file
                -Dfile=<path-to-file>/ojdbc7.jar
                -DgroupId=com.oracle
                -DartifactId=ojdbc7
                -Dversion=12.1.0.1.0
                -Dpackaging=jar
    4. Docker
    5. Oracle Virtual Box
    6. docker-machine
    7. DockerHub acount (optional)

    
 <h3> Launch project </h3>    
Clone the Repo 
    git clone https://github.com/fofancy/test-task-ossystem.git


<h3> Build </h3>
All images are pushed to public repositories so it isn't needed to build them.
But if necessary

Build each projects with commands
sudo gradle clean assemble

or 

sudo mvn clean package

To login do you docker account run 

```
sudo docker login
```

Enter your creds.
Then build each project like:

```
docker build -t anrejeru/config .
```

and push them into your repository

```
docker push andrejeru/config:latest
```

<h3>Swarm</h3>
Create virtual machines for swarm

```
docker-machine create --driver virtualbox vm1
docker-machine create --driver virtualbox vm2
docker-machine create --driver virtualbox vm3
docker-machine ssh vm1 "docker swarm init --advertise-addr $(docker-machine ip vm1)"
```

You will receive response something like 

```
To add a worker to this swarm, run the following command:

    docker swarm join --token SWMTKN-1-21csh01wf1om5csmyfgwg11cf372ioz3q69puc0qkyg76l0ki3-1ohplrspnlm4t6mrsjq6i600s 192.168.99.103:2377
```

Add workers to manager:

```
docker-machine ssh vm2 "docker swarm join --token SWMTKN-1-21csh01wf1om5csmyfgwg11cf372ioz3q69puc0qkyg76l0ki3-1ohplrspnlm4t6mrsjq6i600s 192.168.99.103:2377"
docker-machine ssh vm3 "docker swarm join --token SWMTKN-1-21csh01wf1om5csmyfgwg11cf372ioz3q69puc0qkyg76l0ki3-1ohplrspnlm4t6mrsjq6i600s 192.168.99.103:2377"
```

Than cd devops and connect to master node:

```
docker-machine env vm1
eval $(docker-machine env vm1)
```

Deploy stack to swarm

```
docker stack deploy -c docker-compose.yml app
```

Open a browser and enter [vm1-host-ip]:[8061]/

It will show services connected to eureka.
