
## Not on Libra Autosearch

# Background

Infrastructure as Code for LAA Not on Libra Autosearch tooling.

# Deployment

```
GIT_DIR=$HOME/dev/git/laa/laa-nolasa
LAA_ACCOUNT=902837325998
LAA_ENV=shared-services

# Package the stack in S3 bucket and derive a packaged Cloudformation template
aws cloudformation package --template-file $GIT_DIR/aws/sharedservices/templates/laa-nolasa-main.template --s3-bucket laa-cfn-${LAA_ACCOUNT}-eu-west-2 --s3-prefix ${LAA_ENV} --output-template-file $GIT_DIR/aws/sharedservices/templates/laa-nolasa-main.packaged --profile laa-${LAA_ENV}-lz

# Create the Cloudformation stack
aws cloudformation create-stack --stack-name LAA-nolasa --template-body file://$GIT_DIR/sharedservices/templates/laa-nolasa-main.packaged --parameters file://$GIT_DIR/sharedservices/parameters/${LAA_ENV}.json --tags Key=Name,Value=laa-nolasa Key=Owner,Value=aws+LAA+Shared+services@digital.justice.gov.uk Key=AgencyName,Value=LAA Key=ApplicationID,Value=LandingZone Key=Environment,Value=$LAA_ENV --capabilities CAPABILITY_NAMED_IAM --region eu-west-2 --profile laa-${LAA_ENV}-lz

# Update the Cloudformation stack
aws cloudformation update-stack --stack-name LAA-nolasa --template-body file://$GIT_DIR/sharedservices/templates/laa-nolasa-main.packaged --parameters file://$GIT_DIR/sharedservices/parameters/${LAA_ENV}.json --tags Key=Owner,Value=aws+LAA+Shared+services@digital.justice.gov.uk Key=AgencyName,Value=LAA Key=ApplicationID,Value=LandingZone Key=Environment,Value=$LAA_ENV --capabilities CAPABILITY_NAMED_IAM --region eu-west-2 --profile laa-${LAA_ENV}-lz
```
