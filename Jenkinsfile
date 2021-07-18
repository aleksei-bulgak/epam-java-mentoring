def image = null;

pipeline{
    agent any

    stages{
        stage("Build"){
            steps{
                git branch: 'jenkins-3', credentialsId: 'github', url: 'git@github.com:aleksei-bulgak/epam-java-mentoring.git'
                
                script {
                    env.PATH = "${env.PATH}:/usr/local/bin:/usr/bin:/usr/sbin:/sbin"
                    def dockerfile = 'Dockerfile.withBuild'
                    image = docker.build("alekseibulgak/petclinic:${env.BUILD_ID}")
                }
            }
        }

        stage("Push") {
            steps {
                script {
                    withCredentials([usernamePassword(credentialsId: 'dockerhub_id', passwordVariable: 'password', usernameVariable: 'username')]) {
                        sh"""
                            docker login -u ${username} -p ${password} https://index.docker.io/v1/
                            docker push alekseibulgak/petclinic:${env.BUILD_ID}
                            docker tag alekseibulgak/petclinic:${env.BUILD_ID} alekseibulgak/petclinic:latest
                            docker push alekseibulgak/petclinic:latest
                        """
                    }
                    //Caused: java.io.IOException: Cannot run program "docker": error=2, No such file or directory
                    // withDockerRegistry(credentialsId: 'dockerhub_id') {
                    //     image.push()
                    //     image.push('latest')
                    // }
                }
            }
        }

        stage("Deploy") {
            steps {
                script {
                    kubernetesDeploy configs: 'infrastructure/k8s/*', deleteResource: true, kubeconfigId: 'docker-desktop'
                }
            }
        }
    }
}