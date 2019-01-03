
# Background

Infrastructure as Code for laa-not-on-libra-autosearch pipeline

# Deployment

## Pipeline

Create the Code pipeline by running the following. It also sets up Codebuild projects. Note that LAA_APP designates a Code pipeline instance - there could be more than one and therefore more than one set of application stacks per application.

```
GIT_DIR=$HOME/dev/git/laa
## Shared Services
LAA_APP=laa-not-on-libra-autosearch-deployment-pipeline
LAA_ENV=shared-services
LAA_ACCOUNT=902837325998

aws cloudformation validate-template --template-body file://$GIT_DIR/laa-not-on-libra-autosearch/aws/pipeline/${LAA_APP}.template --profile laa-${LAA_ENV}-lz

aws cloudformation package --template-file $GIT_DIR/laa-not-on-libra-autosearch/aws/pipeline/${LAA_APP}.template --s3-bucket laa-cfn${LAA_ACCOUNT}-eu-west-2 --s3-prefix ${LAA_APP}/$LAA_ENV --output-template-file $GIT_DIR/laa-not-on-libra-autosearch/aws/pipeline/${LAA_APP}.packaged --profile laa-${LAA_ENV}-lz

aws cloudformation create-stack --stack-name LAA-${LAA_APP} --template-body file://$GIT_DIR/laa-not-on-libra-autosearch/aws/pipeline/${LAA_APP}.packaged --parameters file://$GIT_DIR/laa-not-on-libra-autosearch/aws/pipeline/parameters/${LAA_APP}-${LAA_ENV}.json --tags Key=Name,Value=laa-saml-mock Key=Owner,Value=aws+LAA+Shared+services@digital.justice.gov.uk Key=AgencyName,Value=LAA Key=ApplicationID,Value=${LAA_APP} Key=Environment,Value=$LAA_ENV --capabilities CAPABILITY_NAMED_IAM --region eu-west-2 --profile laa-${LAA_ENV}-lz

aws cloudformation update-stack --stack-name LAA-${LAA_APP} --template-body file://$GIT_DIR/laa-not-on-libra-autosearch/aws/pipeline/${LAA_APP}.packaged --parameters file://$GIT_DIR/laa-not-on-libra-autosearch/aws/pipeline/parameters/${LAA_APP}-${LAA_ENV}.json --tags Key=Owner,Value=aws+LAA+Shared+services@digital.justice.gov.uk Key=AgencyName,Value=LAA Key=ApplicationID,Value=${LAA_APP} Key=Environment,Value=$LAA_ENV --capabilities CAPABILITY_NAMED_IAM --region eu-west-2 --profile laa-${LAA_ENV}-lz

# Recommendation is to use change-set over update-stack to validate changes made prior to making them
aws cloudformation create-change-set --change-set-name LAA-${LAA_APP} --stack-name LAA-${LAA_APP} --template-body file://$GIT_DIR/laa-not-on-libra-autosearch/aws/pipeline/${LAA_APP}.packaged --parameters file://$GIT_DIR/laa-not-on-libra-autosearch/aws/pipeline/parameters/${LAA_APP}-${LAA_ENV}.json --tags Key=Owner,Value=aws+LAA+Shared+services@digital.justice.gov.uk Key=AgencyName,Value=LAA Key=ApplicationID,Value=${LAA_APP} Key=Environment,Value=$LAA_ENV --capabilities CAPABILITY_NAMED_IAM --region eu-west-2 --profile laa-${LAA_ENV}-lz

aws cloudformation execute-change-set --change-set-name LAA-${LAA_APP} --stack-name LAA-${LAA_APP} --region eu-west-2 --profile laa-${LAA_ENV}-lz

```
