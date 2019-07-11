## Terms
* *Docker image* - a read-only template with instructions for creating a Docker container. It is a combination of file system and parameters.
* *Docker container* - a runnable instance of docker image. A container is isolated from the host by default. 
 
## Commands
Common syntax `docker COMMAND SUBCOMMAND`

* download a docker image
** we can use pull command to get an image `docker pull [OPTIONS] [PATH/]IMAGE_NAME[:TAG]`
``` 
docker pull hello-world
docker pull hello-world:latest
```
If a registry is password protected, you should log in to it before pulling 'docker login REGISTRY_SERVER:REGISTRY_PORT'

** create a container — when we create a container from an image, it downloads the image from the repository if it is not available in the host.
``` 
docker run hello-world
```

* `docker image ls` — list available images

* `docker image rm IMAGE_NAME` or `docker image remove IMAGE_NAME` — remove an image

* `docker image inspect IMAGE_NAME` — inspect an image

* list containers
** `docker container ls`
** `docker container ls -a`

* `docker container run CONTAINER_NAME` — run a container

* `docker container start CONTAINER_NAME` — start a stopped container

* `docker container stop CONTAINER_NAME` — stop a running container