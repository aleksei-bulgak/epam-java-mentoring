pipeline{
    agent any

    stages{
        stage("Build"){
            steps{
                git branch: 'jenkins', credentialsId: 'github', url: 'git@github.com:aleksei-bulgak/epam-java-mentoring.git'
                echo "Hello, World"
            }
        }
    }
}