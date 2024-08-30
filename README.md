# GitHub Repository Details REST Service
## Overview
This project provides a RESTful web service that retrieves and caches details of a given GitHub repository. The service returns information including the full name, description, Git clone URL, number of stars, and creation date of the repository. The details are fetched from the GitHub API and cached in a database for efficiency.

## API Endpoint
### Get Repository Details

- **Endpoint:** `GET /repositories/{owner}/{repository-name}`
- **Response:**
    ```json
    {
      "fullName": "...",
      "description": "...",
      "cloneUrl": "...",
      "stars": 0,
      "createdAt": "..."
    }
    ```

## Features

- Fetch repository details from GitHub API.
- Cache the repository details in a database to minimize API calls.
- Return the repository details in a structured JSON format.
- Written in Java (version 21) using the Spring Framework.
- Includes a set of unit and integration tests.

## Technologies Used

- **Java Version:** 21
- **Framework:** Spring Boot
- **Database:**  H2
- **Build Tool:** Maven

## Setup Instructions

### Prerequisites

- JDK 21 installed on your machine.
- Maven installed on your machine.
- A database H2 installed and configured.

### Clone the Repository

```sh
git clone https://github.com/javamohan/gitrepodetails.git
cd gitrepodetails
