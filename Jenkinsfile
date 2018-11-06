pipeline {
  agent {
    docker {
      image 'maven:3.3.3'
    }

  }
  stages {
    stage('build') {
      parallel {
        stage('build') {
          steps {
            sh 'mvn --version'
          }
        }
        stage('haha1') {
          steps {
            echo 'hello haha1'
            echo 'ff'
          }
        }
        stage('hah11122') {
          steps {
            echo 'fd'
          }
        }
      }
    }
    stage('test') {
      steps {
        echo 'test........'
      }
    }
    stage('deploy') {
      steps {
        mail(subject: 'subjeeeeeee', body: 'bnody111111', to: 'xiaoqiaotq@gmail.com')
      }
    }
  }
}