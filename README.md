# Test task for megogo product

## Local setup of the project
1. Unzip or clone repository from GitHub
2. Go to root folder `cd megogoTestTask`
3. Install all dependencies `./gradlew build`

## Run tests using gradle wrapper
1. Execute command: `./gradlew clean test`
2. Generate allure report `./gradlew allureReport`
3. Go to `build/reports/allure-report`
4. Open via any browser `index.html` file

## Build docker image
### Pre-requirements
Install docker on your machine related to your OS using this [documentation](https://docs.docker.com/desktop/)
1. Go to root folder `cd megogoTestTask`
2. Execute command to build docker image `docker build -t {your image tag}`

## Environment variables
URL - base URL for API tests in format `{protocol}://{damain}`