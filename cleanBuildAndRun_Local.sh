#!/bin/bash
# Exit on failure (-e) and echo statements before running them (-x)
set -ex

# Get the directory of this script
script_dir="$(dirname "$0")"

# Change to that directory, which will be the root of this application (laa-nolasa)
cd "$script_dir" || { echo "Unable to navigate to the root directory"; exit; }

pwd

./gradlew clean build

docker-compose build

docker-compose up app
