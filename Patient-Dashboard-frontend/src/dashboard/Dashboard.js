import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import { AppBar, Toolbar, Box, Typography, Button } from "@mui/material";

import { fetchPatients, deletePatient, updatePatient } from "../api/patientService";
import PatientCard from "./PatientCard";

const Dashboard = () => {
  const [patients, setPatients] = useState([]);
  const [error, setError] = useState(null);

  useEffect(() => {
    loadPatients();
  }, []);

  const loadPatients = async () => {
    try {
      const data = await fetchPatients();
      setPatients(data);
    } catch (err) {
      setError(err.message || "Failed to load patients.");
      console.error("Error:", err);
    }
  };

  const handleDelete = async (id) => {
    const confirmation = window.confirm(`Are you sure you want to delete patient ID ${id}?`);
    if (confirmation) {
      try {
        await deletePatient(id);
        
        // Update the State and make sure the deleted id will not be there
        setPatients(function (prevPatients) {
          const updatedPatients = [];
          prevPatients.forEach(function (patient) {
            if (patient.id !== id) {
              updatedPatients.push(patient);
            }
          });
          return updatedPatients;
        });

        alert("Patient record got deleted successfully");
      } catch (error) {
        console.error("Error deleting patient:", error.message || error);
        alert("Failed to delete the patient record. Please try again.");
      }
    }
  };

  const handleUpdate = async (id, updates) => {
    try {
      const patientToUpdate = patients.find((patient) => patient.id === id);

      // Prepare updated patient object
      const updatedData = {
        first_name: updates.firstName || patientToUpdate.firstName,
        last_name: updates.lastName || patientToUpdate.lastName,
        email: updates.email || patientToUpdate.email,
        address: patientToUpdate.address,
        city: patientToUpdate.city,
        state: patientToUpdate.state,
        zip_code: patientToUpdate.zipCode,
        phone_number: patientToUpdate.phoneNumber,
      };

      // Call the update API
      const updatedPatient = await updatePatient(id, updatedData);

      // Update the local state
      setPatients((prevPatients) =>
        prevPatients.map((patient) =>
          patient.id === id ? { ...patient, ...updatedPatient } : patient
        )
      );

      alert("Patient updated successfully!");
    } catch (error) {
      console.error("Error updating patient:", error.message || error);
      alert("Failed to update the patient. Please try again.");
    }
  };

  return (
    <Box>
      <AppBar position="static">
        <Toolbar>
          <Typography variant="h6" component="div" sx={{ flexGrow: 1 }}>
            Patient Management System
          </Typography>
        </Toolbar>
      </AppBar>

      <Box sx={{ p: 3 }}>
        <Box
          sx={{
            display: "flex",
            justifyContent: "space-between",
            alignItems: "center",
            mb: 3,
          }}
        >
          <Button
            variant="contained"
            color="primary"
            component={Link}
            to="/add-patient"
          >
            Add New Patient
          </Button>
        </Box>

        {error ? (
          <Typography color="error">{error}</Typography>
        ) : (
          <Box>
            {patients.map((patient) => (
              <PatientCard
                key={patient.id}
                patient={patient}
                handleDelete={handleDelete}
                handleUpdate={handleUpdate}
              />
            ))}
          </Box>
        )}
      </Box>
    </Box>
  );
};

export default Dashboard;
