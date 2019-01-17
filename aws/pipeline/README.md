# Background

Infrastructure as Code for laa-not-on-libra-autosearch pipeline

# Deployment

## Pipeline

Create the Code pipeline by running the following. It also sets up Codebuild projects. Note that LAA_APP designates a Code pipeline instance - there could be more than one and therefore more than one set of application stacks per application.

```
gitdir="$( git rev-parse --show-toplevel )"
## Shared Services
LAA_APP=laa-not-on-libra-autosearch-deployment-pipeline
LAA_ENV=shared-services
LAA_ACCOUNT=902837325998
LAA_OWNER=laa-team-nolasa@digital.justice.gov.uk
LAA_TAGS=$(
cat <<-JSON
  [
      {
          "Key": "business-unit",
          "Value": "LAA"
      },
      {
          "Key": "application",
          "Value": "${LAA_APP}"
      },
      {
          "Key": "component",
          "Value": "app-server"
      },
      {
          "Key": "is-production",
          "Value": "true"
      },
      {
          "Key": "environment-name",
          "Value": "${LAA_ENV}"
      },
      {
          "Key": "owner",
          "Value": "${LAA_OWNER}"
      },
      {
          "Key": "infrastructure-support",
          "Value": "laa-role-sre@digital.justice.gov.uk"
      },
      {
          "Key": "runbook",
          "Value": "https://dsdmoj.atlassian.net/wiki/spaces/LM/pages/760545284/Runbooks"
      },
      {
          "Key": "source-code",
          "Value": "https://github.com/ministryofjustice/laa-nolasa"
      }
  ]
JSON
)

aws cloudformation validate-template --template-body file://${gitdir}/aws/pipeline/${LAA_APP}.template --profile laa-${LAA_ENV}-lz

aws cloudformation package --template-file ${gitdir}/aws/pipeline/${LAA_APP}.template --s3-bucket laa-cfn${LAA_ACCOUNT}-eu-west-2 --s3-prefix ${LAA_APP}/$LAA_ENV --output-template-file ${gitdir}/aws/pipeline/${LAA_APP}.packaged --profile laa-${LAA_ENV}-lz

aws cloudformation create-stack --stack-name LAA-${LAA_APP} --template-body file://${gitdir}/aws/pipeline/${LAA_APP}.packaged --parameters file://${gitdir}/aws/pipeline/parameters/${LAA_APP}-${LAA_ENV}.json --tags ${LAA_TAGS} --capabilities CAPABILITY_NAMED_IAM --region eu-west-2 --profile laa-${LAA_ENV}-lz

aws cloudformation update-stack --stack-name LAA-${LAA_APP} --template-body file://${gitdir}/aws/pipeline/${LAA_APP}.packaged --parameters file://${gitdir}/aws/pipeline/parameters/${LAA_APP}-${LAA_ENV}.json --tags ${LAA_TAGS} --capabilities CAPABILITY_NAMED_IAM --region eu-west-2 --profile laa-${LAA_ENV}-lz

# Recommendation is to use change-set over update-stack to validate changes made prior to making them
aws cloudformation create-change-set --change-set-name LAA-${LAA_APP} --stack-name LAA-${LAA_APP} --template-body file://${gitdir}/aws/pipeline/${LAA_APP}.packaged --parameters file://${gitdir}/aws/pipeline/parameters/${LAA_APP}-${LAA_ENV}.json --tags ${LAA_TAGS} --capabilities CAPABILITY_NAMED_IAM --region eu-west-2 --profile laa-${LAA_ENV}-lz

aws cloudformation execute-change-set --change-set-name LAA-${LAA_APP} --stack-name LAA-${LAA_APP} --region eu-west-2 --profile laa-${LAA_ENV}-lz

```
