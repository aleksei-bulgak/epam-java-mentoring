def image = null;

pipeline{
    agent any

    stages{
        stage("Build"){
            steps{
                git branch: 'jenkins-2', credentialsId: 'github', url: 'git@github.com:aleksei-bulgak/epam-java-mentoring.git'
                
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
                    env.PATH = "${env.PATH}:/usr/local/bin:/usr/bin:/usr/sbin:/sbin"
                    docker.withRegistry( '', 'dockerhub_id' ) {
                        image.push()
                        image.push('latest')
                    }
                }
            }
        }
    }
}