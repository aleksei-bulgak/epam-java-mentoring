### Docker

To build application you have two ways

1. Build application locally `./mvnw clean build`
2. Build application as part of Docker build process `docker build -t petclinic:latest --build-arg Xms=1024m --build-arg Xmx=2048m -f  Dockerfile.withBuild .`

To pass `Xms` and `Xmx` properties as `ARGs` you need to add addtional params on build stage `--build-arg Xms=1024m --build-arg Xmx=2048m`

To attach properties file please use such command:
`docker run -it -v ${PWD}/src/main/resources/application.properties:/app/application.properties  petclinic:latest`


### K8S

To apply all resources you need to have image in your local docker registry(check previous steps how to build image)

All k8s resources are stored under `./infrastructure/k8s`

To apply specific manifest you need to run such command:
```
kubectl create -f ./infrastructure/k8s/configMap.yaml

kubectl create -f ./infrastructure/k8s/deployment.yaml

kubectl create -f ./infrastructure/k8s/service.yaml
```

If you want to change port or update config you can run
```
kubectl update -f k8s/service.yaml
```

### Helm

Helm chart stored under `./infrastructure/helm/spring-petclinic`

To apply helm chart you need to make sure that previous resources were removed

```
kubectl delete <resource to remove>
```

after that you can apply chart with 
```
helm install petclinic ./infrastructure/helm/spring-petclinic
```
