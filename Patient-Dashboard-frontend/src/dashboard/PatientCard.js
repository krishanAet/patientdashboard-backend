import React, { useState } from "react";
import { Link } from "react-router-dom";
import {
  Card,
  CardContent,
  CardActions,
  Grid,
  Typography,
  Button,
  TextField,
} from "@mui/material";

const PatientCard = ({ patient, handleDelete, handleUpdate }) => {
  const [editingField, setEditingField] = useState(null);
  const [editValue, setEditValue] = useState("");

  const startEditing = (field, currentValue) => {
    setEditingField(field);
    setEditValue(currentValue);
  };

  const handleEditKeyDown = async (event) => {
    if (event.key === "Enter") {
      await handleUpdate(patient.id, { [editingField]: editValue });
      setEditingField(null); // Exit editing mode
    }
  };

  return (
    <Card sx={{ mb: 2, padding: 2 }}>
      <CardContent>
        <Grid container spacing={2} alignItems="center">
          <Grid item xs={1}>
            <Typography>
              <strong>ID:</strong> {patient.id}
            </Typography>
          </Grid>
          <Grid item xs={2}>
            <Typography>
              <strong>First Name:</strong>{" "}
              {editingField === "firstName" ? (
                <TextField
                  value={editValue}
                  onChange={(e) => setEditValue(e.target.value)}
                  onKeyDown={handleEditKeyDown}
                  onBlur={() => setEditingField(null)}
                  autoFocus
                  size="small"
                />
              ) : (
                <span
                  style={{ cursor: "pointer", color: "blue" }}
                  onClick={() => startEditing("firstName", patient.firstName)}
                >
                  {patient.firstName}
                </span>
              )}
            </Typography>
          </Grid>
          <Grid item xs={2}>
            <Typography>
              <strong>Last Name:</strong>{" "}
              {editingField === "lastName" ? (
                <TextField
                  value={editValue}
                  onChange={(e) => setEditValue(e.target.value)}
                  onKeyDown={handleEditKeyDown}
                  onBlur={() => setEditingField(null)}
                  autoFocus
                  size="small"
                />
              ) : (
                <span
                  style={{ cursor: "pointer", color: "blue" }}
                  onClick={() => startEditing("lastName", patient.lastName)}
                >
                  {patient.lastName}
                </span>
              )}
            </Typography>
          </Grid>
          <Grid item xs={3}>
            <Typography>
              <strong>Email:</strong>{" "}
              {editingField === "email" ? (
                <TextField
                  value={editValue}
                  onChange={(e) => setEditValue(e.target.value)}
                  onKeyDown={handleEditKeyDown}
                  onBlur={() => setEditingField(null)}
                  autoFocus
                  size="small"
                />
              ) : (
                <span
                  style={{ cursor: "pointer", color: "blue" }}
                  onClick={() => startEditing("email", patient.email)}
                >
                  {patient.email}
                </span>
              )}
            </Typography>
          </Grid>
          <Grid item xs={2}>
            <CardActions>
              <Button
                size="small"
                variant="contained"
                component={Link}
                to={`/edit-patient/${patient.id}`}
              >
                Edit
              </Button>
              <Button
                size="small"
                variant="outlined"
                color="error"
                onClick={() => handleDelete(patient.id)}
              >
                Delete
              </Button>
            </CardActions>
          </Grid>
        </Grid>
      </CardContent>
    </Card>
  );
};

export default PatientCard;
