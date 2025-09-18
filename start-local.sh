#!/bin/bash

# Ensure the script stops if any command fails
set -euo pipefail

# Specify the vault and the document name in 1Password
VAULT="LAA Crime Apps"
DOCUMENT="EnvironmentVariables-nolasa-App"
APP_ENV_FILE="./.env"

function remove_env_file()
{
    echo "Removing .env file from locally"
    rm -f "$APP_ENV_FILE"
}

trap remove_env_file EXIT

echo "Signing into 1Password..."
eval $(op signin --account ministryofjustice)

echo "Fetching latest .env file from 1Password..."
op document get "$DOCUMENT" --vault "$VAULT" --output "$APP_ENV_FILE" --force

if [ ! -f "$APP_ENV_FILE" ]; then
    echo "Failed to retrieve .env file from 1Password."
    exit 1
fi

echo ".env file successfully retrieved."

echo "Building and running Docker container..."

docker compose build
docker compose up app
