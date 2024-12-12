import axios from "axios";

const BASE_URL = "http://localhost:8080/v1.0/patient";


export const fetchPatients = async () => {
  try {
    const response = await axios.get(BASE_URL);
    const apiResponse = response.data;
    if (Array.isArray(apiResponse) && apiResponse.length > 0) {
      const { data } = apiResponse[0];
      return data || [];
    }
    throw new Error("Unexpected API response structure.");
  } catch (error) {
    console.error("Error fetching patients:", error.message || error);
    throw new Error("Failed to fetch patients. Please try again later.");
  }
};


export const createPatient = async (patientData) => {
  try {
    const response = await axios.post(BASE_URL, patientData);
    const apiResponse = response.data;
    
    if (apiResponse.errors?.length) {
      throw new Error(apiResponse.errors.join(", "));
    }
    const createdPatient = apiResponse.data?.[0];
    if (!createdPatient) {
      throw new Error("Failed to create patient. No data returned.");
    }
    return createdPatient;
  } catch (error) {
    console.error("Error creating patient:", error.message || error);
    throw new Error("Failed to create patient. Please try again later.");
  }
};


export const updatePatient = async (id, patientData) => {
  try {
    const response = await axios.put(`${BASE_URL}/${id}`, patientData);
    const apiResponse = response.data;
    if (apiResponse.errors?.length) {
      throw new Error(apiResponse.errors.join(", "));
    }
    const updatedPatient = apiResponse.data?.[0];
    if (!updatedPatient) {
      throw new Error("Failed to update patient. No data returned.");
    }
    return updatedPatient;
  } catch (error) {
    console.error(`Error updating patient with ID ${id}:`, error.message || error);
    throw new Error("Failed to update patient. Please try again later.");
  }
};


export const deletePatient = async (id) => {
  try {
    const response = await axios.delete(`${BASE_URL}/${id}`);
    const apiResponse = response.data;
    if (apiResponse.errors?.length) {
      throw new Error(apiResponse.errors.join(", "));
    }
    if (apiResponse.code !== "200" || apiResponse.data !== null) {
      throw new Error("Failed to delete patient. Unexpected response structure.");
    }
    return;
  } catch (error) {
    console.error(`Error deleting patient with ID ${id}:`, error.message || error);
    throw new Error("Failed to delete patient. Please try again later.");
  }
};