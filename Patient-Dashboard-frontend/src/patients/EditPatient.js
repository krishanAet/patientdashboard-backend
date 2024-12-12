import React, { useState, useEffect } from "react";
import { useParams, Link } from "react-router-dom";
import {
  AppBar,
  Toolbar,
  Typography,
  Box,
  Button,
  TextField,
  Card,
  CardContent,
} from "@mui/material";

import { fetchPatients, updatePatient } from "../api/patientService";

const EditPatient = () => {
  const { id } = useParams();
  const [formData, setFormData] = useState({
    firstName: "",
    lastName: "",
    address: "",
    city: "",
    state: "",
    zipCode: "",
    phoneNumber: "",
    email: "",
  });
  const [error, setError] = useState(null);

  useEffect(() => {
    loadPatient();
  }, [id]);

  const loadPatient = async () => {
    try {
      const patients = await fetchPatients();
      const patient = patients.find((p) => p.id === parseInt(id, 10));
      if (patient) {
        setFormData({
          firstName: patient.firstName || "",
          lastName: patient.lastName || "",
          address: patient.address || "",
          city: patient.city || "",
          state: patient.state || "",
          zipCode: patient.zipCode || "",
          phoneNumber: patient.phoneNumber || "",
          email: patient.email || "",
        });
      } else {
        setError("Patient not found.");
      }
    } catch (err) {
      setError(err.message || "Failed to load patient data.");
    }
  };

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setFormData({
      ...formData,
      [name]: value,
    });
  };

  const formDataValidation = () => {
    const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/; // Simple email regex
    const phonePattern = /^\(?([0-9]{3})\)?[-. ]?([0-9]{3})[-. ]?([0-9]{4})$/; // US phone number regex

    if (!emailPattern.test(formData.email)) {
      return "Invalid email format.";
    }

    if (!phonePattern.test(formData.phoneNumber)) {
      return "Invalid US phone number format. Example: 123-456-7890";
    }

    return null;
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    // Validate form data
    const validationError = formDataValidation();
    if (validationError) {
      setError(validationError);
      return;
    }

    try {
      setError(null);
      await updatePatient(id, {
        first_name: formData.firstName,
        last_name: formData.lastName,
        address: formData.address,
        city: formData.city,
        state: formData.state,
        zip_code: formData.zipCode,
        phone_number: formData.phoneNumber,
        email: formData.email,
      });
      alert("Patient updated successfully!");
    } catch (err) {
      setError(err.message || "Failed to update patient.");
    }
  };

  return (
    <Box>
      {/* Header Section */}
      <AppBar position="static">
        <Toolbar>
          <Typography variant="h6" component="div" sx={{ flexGrow: 1 }}>
            Edit Patient
          </Typography>
          <Button color="inherit" component={Link} to="/">
            Dashboard
          </Button>
        </Toolbar>
      </AppBar>

      {/* Form Section */}
      <Box
        sx={{
          display: "flex",
          justifyContent: "center",
          alignItems: "center",
          minHeight: "100vh",
          backgroundColor: "#f5f5f5",
        }}
      >
        <Card sx={{ maxWidth: 600, width: "100%", padding: 3 }}>
          <CardContent>
            <Typography variant="h5" gutterBottom textAlign="center">
              Edit Patient
            </Typography>
            {error && <Typography color="error" textAlign="center" mb={2}>{error}</Typography>}
            <form onSubmit={handleSubmit}>
              <TextField
                label="First Name"
                name="firstName"
                value={formData.firstName}
                onChange={handleInputChange}
                variant="outlined"
                fullWidth
                required
                margin="normal"
              />
              <TextField
                label="Last Name"
                name="lastName"
                value={formData.lastName}
                onChange={handleInputChange}
                variant="outlined"
                fullWidth
                required
                margin="normal"
              />
              <TextField
                label="Address"
                name="address"
                value={formData.address}
                onChange={handleInputChange}
                variant="outlined"
                fullWidth
                required
                margin="normal"
              />
              <TextField
                label="City"
                name="city"
                value={formData.city}
                onChange={handleInputChange}
                variant="outlined"
                fullWidth
                required
                margin="normal"
              />
              <TextField
                label="State"
                name="state"
                value={formData.state}
                onChange={handleInputChange}
                variant="outlined"
                fullWidth
                required
                margin="normal"
              />
              <TextField
                label="Zip Code"
                name="zipCode"
                value={formData.zipCode}
                onChange={handleInputChange}
                variant="outlined"
                fullWidth
                required
                margin="normal"
              />
              <TextField
                label="Phone Number"
                name="phoneNumber"
                value={formData.phoneNumber}
                onChange={handleInputChange}
                variant="outlined"
                fullWidth
                required
                margin="normal"
              />
              <TextField
                label="Email"
                name="email"
                value={formData.email}
                onChange={handleInputChange}
                variant="outlined"
                fullWidth
                required
                margin="normal"
              />
              <Box textAlign="center" mt={2}>
                <Button
                  type="submit"
                  variant="contained"
                  color="primary"
                >
                  Update Patient
                </Button>
              </Box>
            </form>
          </CardContent>
        </Card>
      </Box>
    </Box>
  );
};

export default EditPatient;
