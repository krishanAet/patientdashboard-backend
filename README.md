# Patient Management System

## Overview
The Patient Management System provides RESTful APIs to manage patient records. This includes creating, retrieving, updating, and deleting patient data. It ensures proper error handling and returns structured responses for seamless integration with frontend applications.

---

## API Contract

### 1. Create Patient
- **Endpoint**: `POST /patient/`
- **Description**: Creates a new patient record.
- **Request Body**:
    ```json
    {
      "first_name": "John",
      "last_name": "Doe",
      "address": "123 Main Street",
      "city": "Springfield",
      "state": "IL",
      "zip_code": "62704",
      "phone_number": "555-123-4567",
      "email": "john.doe@example.com"
    }
    ```
- **Response Body**:
    - **Success**:
      ```json
      {
        "code": "200",
        "errors": [],
        "data": [
          {
            "id": 1,
            "firstName": "John",
            "lastName": "Doe",
            "address": "123 Main Street",
            "city": "Springfield",
            "state": "IL",
            "zipCode": "62704",
            "phoneNumber": "555-123-4567",
            "email": "john.doe@example.com"
          }
        ]
      }
      ```
    - **Error (Duplicate Email)**:
      ```json
      {
        "code": "400",
        "errors": ["Error on Creating Patient", "Duplicate entry: The provided email is already in use."],
        "data": null
      }
      ```

---

### 2. Get Patient by ID
- **Endpoint**: `GET /patient/{id}`
- **Description**: Retrieves the patient record by its unique ID.
- **Path Parameters**:
  - `id` (Long): The ID of the patient to retrieve.
- **Response Body**:
    - **Success**:
      ```json
      {
        "code": "200",
        "errors": [],
        "data": [
          {
            "id": 1,
            "firstName": "John",
            "lastName": "Doe",
            "address": "123 Main Street",
            "city": "Springfield",
            "state": "IL",
            "zipCode": "62704",
            "phoneNumber": "555-123-4567",
            "email": "john.doe@example.com"
          }
        ]
      }
      ```
    - **Error (Not Found)**:
      ```json
      {
        "code": "404",
        "errors": ["Update Patient", "Error on Retrieving Patient"],
        "data": null
      }
      ```

---

### 3. Get All Patients
- **Endpoint**: `GET /patient/`
- **Description**: Retrieves all patient records.
- **Response Body**:
    - **Success**:
      ```json
      [
        {
          "code": "200",
          "errors": [],
          "data": [
            {
              "id": 1,
              "firstName": "John",
              "lastName": "Doe",
              "address": "123 Main Street",
              "city": "Springfield",
              "state": "IL",
              "zipCode": "62704",
              "phoneNumber": "555-123-4567",
              "email": "john.doe@example.com"
            },
            {
              "id": 2,
              "firstName": "Jane",
              "lastName": "Smith",
              "address": "456 Elm Street",
              "city": "Springfield",
              "state": "IL",
              "zipCode": "62704",
              "phoneNumber": "555-987-6543",
              "email": "jane.smith@example.com"
            }
          ]
        }
      ]
      ```
    - **Error**:
      ```json
      [
        {
          "code": "500",
          "errors": ["Retrieve Patients", "Error on Retrieving All Patients"],
          "data": null
        }
      ]
      ```

---

### 4. Update Patient
- **Endpoint**: `PUT /patient/{id}`
- **Description**: Updates the patient record with the provided ID.
- **Path Parameters**:
  - `id` (Long): The ID of the patient to update.
- **Request Body**:
    ```json
    {
      "first_name": "John",
      "last_name": "Smith",
      "address": "456 Updated Street",
      "city": "Updated City",
      "state": "IL",
      "zip_code": "62705",
      "phone_number": "555-111-2222",
      "email": "john.smith@example.com"
    }
    ```
- **Response Body**:
    - **Success**:
      ```json
      {
        "code": "200",
        "errors": [],
        "data": [
          {
            "id": 1,
            "firstName": "John",
            "lastName": "Smith",
            "address": "456 Updated Street",
            "city": "Updated City",
            "state": "IL",
            "zipCode": "62705",
            "phoneNumber": "555-111-2222",
            "email": "john.smith@example.com"
          }
        ]
      }
      ```
    - **Error (Not Found)**:
      ```json
      {
        "code": "404",
        "errors": ["Update Patient", "Error on Updating Patient: Patient not found"],
        "data": null
      }
      ```
    - **Error**:
      ```json
      {
        "code": "500",
        "errors": ["Update Patient", "Error on Updating Patient"],
        "data": null
      }
      ```

---

### 5. Delete Patient
- **Endpoint**: `DELETE /patient/{id}`
- **Description**: Deletes the patient record with the provided ID.
- **Path Parameters**:
  - `id` (Long): The ID of the patient to delete.
- **Response Body**:
    - **Success**:
      ```json
      {
        "code": "200",
        "errors": [],
        "data": null
      }
      ```
    - **Error (Not Found)**:
      ```json
      {
        "code": "404",
        "errors": ["Delete Patient", "Error on Deleting Patient: Patient not found"],
        "data": null
      }
      ```
    - **Error**:
      ```json
      {
        "code": "500",
        "errors": ["Delete Patient", "Error on Deleting Patient"],
        "data": null
      }
      ```

---

## Error Handling
- **404 (Not Found)**: Returned when a patient with the specified ID is not found.
- **400 (Bad Request)**: Returned when validation fails or duplicate entries are attempted (e.g., duplicate email).
- **500 (Internal Server Error)**: Returned for unexpected server-side errors.

---

## How to Run

### Backend
1. Clone the repository:
    ```
    git clone <repository-url>
    ```
2. Navigate to the project directory:
    ```
    cd patient-management-system
    ```
3. Build the project:
    ```
    ./gradlew build
    ```
4. Run the application:
    ```
    ./gradlew bootRun
    ```
5. Access the API at:
    ```
    http://localhost:8080/
    ```

---

### Frontend

#### Prerequisites
- Install [Node.js](https://nodejs.org/).

#### Steps
1. Clone the frontend repository:
    ```
    git clone <frontend-repository-url>
    ```
2. Navigate to the frontend directory:
    ```
    cd patient-management-frontend
    ```
3. Install dependencies:
    ```
    npm install
    ```
4. Start the development server:
    ```
    npm start
    ```
5. Access the frontend at:
    ```
    http://localhost:3000/
    ```

---
