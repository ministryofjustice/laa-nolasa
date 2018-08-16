# Background
Infrastructure as Code for NOLASA stack that will deploy to the landing zone Shared Services account.

Note that the templates are used by the CI pipeline to create and update the application stack.

# Deployment

```
GIT_DIR=$HOME/dev/git/laa/laa-nolasa
## Shared Services
LAA_APP=laa-nolasa
LAA_ENV=development
LAA_ACCOUNT=411213865113
## Test
LAA_APP=laa-nolasa
LAA_ENV=test
LAA_ACCOUNT=013163512034
## UAT
LAA_APP=laa-nolasa
LAA_ENV=uat
LAA_ACCOUNT=140455166311
## Staging
LAA_APP=laa-nolasa
LAA_ENV=staging
LAA_ACCOUNT=484221692666
## Production
LAA_APP=laa-nolasa
LAA_ENV=production
LAA_ACCOUNT=842522700642
## Production Support
LAA_APP=laa-nolasa
LAA_ENV=productionsupport
LAA_ACCOUNT=441527764669

aws cloudformation package --template-file $GIT_DIR/aws/application/app-main.template --s3-bucket laa-cfn-${LAA_ACCOUNT}-eu-west-2 --s3-prefix ${LAA_APP}/$LAA_ENV --output-template-file $GIT_DIR/aws/application/app-main.packaged --profile laa-${LAA_ENV}-lz

aws cloudformation create-stack --stack-name LAA-${LAA_APP}-${LAA_ENV} --template-body file://$GIT_DIR/aws/application/app-main.packaged --parameters file://$GIT_DIR/aws/application/parameters/${LAA_ENV}.json --tags Key=Name,Value=laa-nolasa Key=Owner,Value=aws+LAA+Shared+services@digital.justice.gov.uk Key=AgencyName,Value=LAA Key=ApplicationID,Value=${LAA_APP} Key=Environment,Value=$LAA_ENV --capabilities CAPABILITY_NAMED_IAM --region eu-west-2 --profile laa-${LAA_ENV}-lz

aws cloudformation update-stack --stack-name LAA-${LAA_APP}-${LAA_ENV} --template-body file://$GIT_DIR/aws/application/app-main.packaged --parameters file://$GIT_DIR/aws/application/parameters/${LAA_ENV}.json --tags Key=Name,Value=laa-nolasa Key=Owner,Value=aws+LAA+Shared+services@digital.justice.gov.uk Key=AgencyName,Value=LAA Key=ApplicationID,Value=${LAA_APP} Key=Environment,Value=$LAA_ENV --capabilities CAPABILITY_NAMED_IAM --region eu-west-2 --profile laa-${LAA_ENV}-lz

# Recommendation is to use change-set over update-stack to validate changes made prior to making them
aws cloudformation create-change-set --change-set-name LAA-${LAA_APP}-${LAA_ENV} --stack-name LAA-${LAA_APP}-${LAA_ENV} --template-body file://$GIT_DIR/aws/application/app-main.packaged --parameters file://$GIT_DIR/aws/application/parameters/${LAA_ENV}.json --tags Key=Name,Value=laa-nolasa Key=Owner,Value=aws+LAA+Shared+services@digital.justice.gov.uk Key=AgencyName,Value=LAA Key=ApplicationID,Value=${LAA_APP} Key=Environment,Value=$LAA_ENV --capabilities CAPABILITY_NAMED_IAM --region eu-west-2 --profile laa-${LAA_ENV}-lz

aws cloudformation execute-change-set --change-set-name LAA-${LAA_APP}-${LAA_ENV} --stack-name LAA-${LAA_APP}-${LAA_ENV} --region eu-west-2 --profile laa-${LAA_ENV}-lz

```

