def image = null;

pipeline{
    agent any

    stages{
        stage("Build"){
            steps{
                git branch: 'jenkins', credentialsId: 'github', url: 'git@github.com:aleksei-bulgak/epam-java-mentoring.git'
                
                script {
                    env.PATH = "${env.PATH}:/usr/local/bin:/usr/bin:/usr/sbin:/sbin"
                    def dockerfile = 'Dockerfile.withBuild'
                    image = docker.build("petclinic:${env.BUILD_ID}", "-f ${dockerfile} .")
                }
            }
        }

        stage("Push") {
            steps {
                script {
                    image.push()
                    image.push('latest')
                }
            }
        }
    }
}